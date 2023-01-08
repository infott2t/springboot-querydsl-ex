package org.example.firstinstance.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.domain.roleclass.admin.RoleADMIN;
import org.example.domain.roleclass.admin.RoleADMINService;
import org.example.domain.roleclass.company.RoleCOMPANY;
import org.example.domain.roleclass.company.RoleCOMPANYService;
import org.example.domain.roleclass.user.RoleUSER;
import org.example.domain.roleclass.user.RoleUSERService;
import org.example.domain.user.Role;
import org.example.domain.user.User;
import org.example.domain.user.UserApiDto;
import org.example.domain.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;
    private final RoleADMINService roleADMINService;
    private final RoleCOMPANYService roleCOMPANYService;
    private final RoleUSERService roleUSERService;

    @PostMapping(value="/api/user/insert")
    public Boolean write(@RequestBody RequestDto reqDto){

        User user = null; //저장할 USER 엔티티 초기화.

        Role role = null; //User 엔티티에 저장할 칼럼, role 초기화.

        //맵핑된 테이블.
        RoleUSER roleUser = null;
        RoleCOMPANY roleCompany = null;
        RoleADMIN roleADMIN = null;

        //파라메터 값으로 각 맵핑테이블 가져오기.

        //숫자변환 예외처리. try-catch문.
        try {

            if (reqDto.getRoleUserId() != null) {
                roleUser = roleUSERService.findById(Long.valueOf(reqDto.getRoleUserId()));
            }

            if (reqDto.getRoleCompanyId() != null) {
                roleCompany = roleCOMPANYService.findById(Long.valueOf(reqDto.getRoleCompanyId()));
            }

            if (reqDto.getRoleAdminId() != null) {
                roleADMIN = roleADMINService.findById(Long.valueOf(reqDto.getRoleAdminId()));
            }
        }catch(Exception e){
            //e.printStackTrace();
            return false;
        }

        //1. 회원의 기본정보 먼저 저장(회원의 Role 저장, build 저장)., 2.맵핑 테이블의 값 저장.
        try {
            //회원의 Role 저장.
            if(reqDto.getRole().equals("ADMIN")){role = Role.ADMIN;}
            else if(reqDto.getRole().equals("COMPANY")){ role = Role.COMPANY;}
            else if(reqDto.getRole().equals("USER")){ role = Role.USER;}
            else{
                //throw new Exception("Role is not valid");
                return false;
            }
            //builder 저장.
            user = User.builder()
                    .name(reqDto.getName())
                    .email(reqDto.getEmail())
                    .picture(reqDto.getPicture())
                    .role(role)
                    .build();

            //맵핑 테이블의 값 저장.
            if(roleUser != null){user.setRoleUser(roleUser);}
            if(roleCompany != null){user.setRoleCompany(roleCompany);}
            if(roleADMIN != null){user.setRoleAdmin(roleADMIN);}


            userService.save(user);


        } catch (Exception e) {
           //throw new RuntimeException(e);
            return false;
        }


        //return new Result(200, "success", user); JSON 형태로 리턴할 때 사용. Result형 리턴.
        return true;
    }
    @PostMapping(value="/api/user/update")
    public Boolean update(@RequestBody RequestDto reqDto){
        User user = null; //저장할 USER 엔티티 초기화.

        Role role = null; //User 엔티티에 저장할 칼럼, role 초기화.

        //맵핑된 테이블.
        RoleUSER roleUser = null;
        RoleCOMPANY roleCompany = null;
        RoleADMIN roleADMIN = null;

        //파라메터 값으로 각 맵핑테이블 가져오기.

        //숫자변환 예외처리. try-catch문.
        try {

            if (reqDto.getRoleUserId() != null) {
                roleUser = roleUSERService.findById(Long.valueOf(reqDto.getRoleUserId()));
            }

            if (reqDto.getRoleCompanyId() != null) {
                roleCompany = roleCOMPANYService.findById(Long.valueOf(reqDto.getRoleCompanyId()));
            }

            if (reqDto.getRoleAdminId() != null) {
                roleADMIN = roleADMINService.findById(Long.valueOf(reqDto.getRoleAdminId()));
            }
        }catch(Exception e){
            //e.printStackTrace();
            return false;
        }

        //1. 회원의 기본정보 먼저 저장(회원의 Role 저장, build 저장)., 2.맵핑 테이블의 값 저장.
        try {
            //회원의 Role 저장.
            if(reqDto.getRole().equals("ADMIN")){role = Role.ADMIN;}
            else if(reqDto.getRole().equals("COMPANY")){ role = Role.COMPANY;}
            else if(reqDto.getRole().equals("USER")){ role = Role.USER;}
            else{
                //throw new Exception("Role is not valid");
                return false;
            }


            user = userService.findById(Long.valueOf(reqDto.getId()));

            user.setName(reqDto.getName());
            user.setEmail(reqDto.getEmail());
            user.setPicture(reqDto.getPicture());
            user.setModifiedDate(LocalDateTime.now());

            //맵핑 테이블의 값 저장.
            if(roleUser != null){user.setRoleUser(roleUser);}
            if(roleCompany != null){user.setRoleCompany(roleCompany);}
            if(roleADMIN != null){user.setRoleAdmin(roleADMIN);}


            userService.save(user);


        } catch (Exception e) {
            //throw new RuntimeException(e);
            return false;
        }


        //return new Result(200, "success", user); JSON 형태로 리턴할 때 사용. Result형 리턴.
        return true;
    }
    @PostMapping(value="/api/user/delete")
    public Boolean delete(@RequestBody RequestDto reqDto) {
        User user = null; //저장할 USER 엔티티 초기화.

        //1. 회원의 기본정보 먼저 저장(회원의 Role 저장, build 저장)., 2.맵핑 테이블의 값 저장.
        try {
            user = userService.delete(Long.valueOf(reqDto.getId()));



        } catch (Exception e) {
            //throw new RuntimeException(e);
            return false;
        }
        return true;
    }


    @Data
    static class RequestDto {
        private String id;
        private String name;
        private String email;
        private String picture;
        private String role;
        private String roleGuestId;
        private String roleUserId;
        private String roleCompanyId;
        private String roleAdminId;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int code;
        private String message;
        private T data;
    }
}


