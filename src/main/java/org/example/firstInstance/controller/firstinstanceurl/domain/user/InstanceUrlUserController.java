package org.example.firstinstance.controller.firstinstanceurl.domain.user;

import lombok.RequiredArgsConstructor;
import org.example.domain.roleclass.admin.RoleADMIN;
import org.example.domain.roleclass.admin.RoleADMINService;
import org.example.domain.roleclass.company.RoleCOMPANY;
import org.example.domain.roleclass.company.RoleCOMPANYService;
import org.example.domain.roleclass.guest.RoleGUEST;
import org.example.domain.roleclass.guest.RoleGUESTService;
import org.example.domain.roleclass.user.RoleUSER;
import org.example.domain.roleclass.user.RoleUSERService;
import org.example.domain.user.*;
import org.example.firstinstance.controller.firstinstanceurl.form.UserApiDtoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class InstanceUrlUserController {

    private final UserService userService;

    private final RoleUSERService roleUSERService;
    private final RoleADMINService roleADMINService;
    private final RoleCOMPANYService roleCOMPANYService;
    private final RoleGUESTService roleGUESTService;



        @GetMapping("/administer/instanceurl/user")
        public String index(Model model, UserSearchCondition condition,
                            @RequestParam(value="page", required=false) Integer page, @PageableDefault(size= 10)Pageable pageable)
        {

            //firstInstance index의 처음 위치. 리스트 출력.

            //List<UserApiDto> lists = userService.searchFindAllDesc();
            Page<UserApiDto> lists = userService.searchAllV2(condition, pageable);

            model.addAttribute("lists", lists);
            model.addAttribute("condition", condition);

            return "firstinstance/user/index";
        }

        @GetMapping("/administer/instanceurl/user/insert")
    public String insert(Model model,UserSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page, @PageableDefault(size= 10)Pageable pageable){

            Page<UserApiDto> lists = userService.searchAllV2(condition, pageable);

            model.addAttribute("lists", lists);
            model.addAttribute("condition", condition);

            UserApiDtoForm userForm = new UserApiDtoForm();
            model.addAttribute("userForm",userForm);



            return "firstinstance/user/insert";
    }

    @PostMapping("/administer/instanceurl/user/insert_2")
    public String insert_2(Model model, UserApiDtoForm userApiDtoForm){


        //저장할 user 엔티티 초기화.
        User user = null;

        //저장할 엔티티 칼럼 초기화.
        RoleUSER roleUser = null;
        RoleADMIN roleAdmin = null;
        RoleCOMPANY roleCompany = null;
        RoleGUEST roleGuest = null;

        //Role 칼럼의 초기화
        Role role = null;


        try {
                user = User.builder()
                    .name(userApiDtoForm.getName())
                    .email(userApiDtoForm.getEmail())
                    .picture(userApiDtoForm.getPicture())
                        .role(Role.GUEST)
                    .build();

            user.setIsDel("N");
        } catch (Exception e) {

            return "redirect:/administer/instanceurl/user/insert";
        }

        if(!userApiDtoForm.getRoleUserId().equals("")){
            role = Role.USER;
            user.setRole(role);
            try {
                roleUser = roleUSERService.findById(Long.valueOf(userApiDtoForm.getRoleUserId()));
                user.setRoleUser(roleUser);
            } catch (Exception e) {

                return "redirect:/administer/instanceurl/user/insert";
            }

        }else if(!userApiDtoForm.getRoleAdminId().equals("")) {
            role = Role.ADMIN;
            user.setRole(role);
            try{
                roleAdmin = roleADMINService.findById(Long.valueOf(userApiDtoForm.getRoleAdminId()));
                user.setRoleAdmin(roleAdmin);
            }catch(Exception e){

                return "redirect:/administer/instanceurl/user/insert";
            }

        }else if(!userApiDtoForm.getRoleGuestId().equals("")) {
            role = Role.GUEST;
            user.setRole(role);
            try{
                roleGuest = roleGUESTService.findById(Long.valueOf(userApiDtoForm.getRoleGuestId()));
                user.setRoleGuest(roleGuest);
            }catch(Exception e){

                return "redirect:/administer/instanceurl/user/insert";
            }
            user.setRoleGuest(new RoleGUEST());
        }else if(!userApiDtoForm.getRoleCompanyId().equals("")) {
            role = Role.COMPANY;
            user.setRole(role);
            try{
                roleCompany = roleCOMPANYService.findById(Long.valueOf(userApiDtoForm.getRoleCompanyId()));
                user.setRoleCompany(roleCompany);
            }catch(Exception e){

                return "redirect:/administer/instanceurl/user/insert";
            }

        }
        user.setIsDel("N");
        user.setModifiedDate(LocalDateTime.now());
        user.setCreatedDate(LocalDateTime.now());

        userService.save(user);


        return "redirect:/administer/instanceurl/user/insert";
    }

    @GetMapping("/administer/instanceurl/user/delete")
    public String delete(@RequestParam(value="id")Long id, Model model){

            User user = null;
            try{
                user = userService.findById(id);
            }catch(Exception e){
                return "redirect:/administer/instanceurl/user/";
            }
            user.setIsDel("Y");
            userService.save(user);

            return "redirect:/administer/instanceurl/user/";
    }

    @GetMapping("/administer/instanceurl/user/update")
    public String update(@RequestParam(value="id")Long id, Model model,UserSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page, @PageableDefault(size= 10)Pageable pageable){

            User user = null;
            try {
                user = userService.findById(id);
                System.out.println("Debug, select ID: " + user.getId());
            }catch(Exception e){
                return "redirect:/administer/instanceurl/user/insert";
            }
        Page<UserApiDto> lists = userService.searchAllV2(condition, pageable);

        model.addAttribute("lists", lists);
        model.addAttribute("condition", condition);

        UserApiDtoForm userForm = new UserApiDtoForm();

        userForm.setId(user.getId());
        userForm.setName(user.getName());
        userForm.setPicture(user.getPicture());
        userForm.setEmail(user.getEmail());
        userForm.setRole(user.getRole().name());
        if(user.getRoleAdmin()!=null) {
            userForm.setRoleAdminId(String.valueOf(user.getRoleAdmin().getId()));
        }
        if(user.getRoleCompany()!=null){
            userForm.setRoleCompanyId(String.valueOf(user.getRoleCompany().getId()));
        }
        if(user.getRoleUser()!=null){
            userForm.setRoleUserId(String.valueOf(user.getRoleUser().getId()));
        }
        if(user.getRoleGuest()!=null){
            userForm.setRoleGuestId(String.valueOf(user.getRoleGuest().getId()));
        }

        userForm.setCreatedDate(user.getCreatedDate());
        userForm.setModifiedDate(user.getModifiedDate());

        model.addAttribute("userForm",userForm);



        return "firstinstance/user/update";
    }

    @PostMapping("/administer/instanceurl/user/update_2")
    public String update_2(Model model, UserApiDtoForm userApiDtoForm){


        //저장할 user 엔티티 초기화.
        User user = null;
        try {
            user = userService.findById(userApiDtoForm.getId());
        }catch(Exception e){
            return "redirect:/administer/instanceurl/user/insert";
        }
        //저장할 엔티티 칼럼 초기화.
        RoleUSER roleUser = null;
        RoleADMIN roleAdmin = null;
        RoleCOMPANY roleCompany = null;
        RoleGUEST roleGuest = null;

        //Role 칼럼의 초기화
        Role role = null;

        String roleStr = userApiDtoForm.getRole();
        if(("ADMIN").equals(userApiDtoForm.getRole())){
            role = Role.ADMIN;
        }else if(("COMPANY").equals(userApiDtoForm.getRole())){
            role = Role.COMPANY;
        }else if(("GUEST").equals(userApiDtoForm.getRole())){
            role = Role.GUEST;
        }else if(("USER").equals(userApiDtoForm.getRole())){
            role = Role.USER;
        }else{
            role = Role.GUEST;
        }
        try {

            user.setId(userApiDtoForm.getId());
            user.setName(userApiDtoForm.getName());
            user.setEmail(userApiDtoForm.getEmail());
            user.setPicture(userApiDtoForm.getPicture());
            user.setRole(role);
            user.setIsDel("N");
        } catch (Exception e) {

            return "redirect:/administer/instanceurl/user/insert";
        }

        if(userApiDtoForm.getRoleUserId()!=null&&!(userApiDtoForm.getRoleUserId().equals(""))){
            role = Role.USER;
            user.setRole(role);
            try {
                roleUser = roleUSERService.findById(Long.valueOf(userApiDtoForm.getRoleUserId()));
                user.setRoleUser(roleUser);
            } catch (Exception e) {

                return "redirect:/administer/instanceurl/user/insert";
            }

        }else if(userApiDtoForm.getRoleAdminId()!=null&&!(userApiDtoForm.getRoleAdminId().equals(""))) {
            role = Role.ADMIN;
            user.setRole(role);
            try{
                roleAdmin = roleADMINService.findById(Long.valueOf(userApiDtoForm.getRoleAdminId()));
                user.setRoleAdmin(roleAdmin);
            }catch(Exception e){

                return "redirect:/administer/instanceurl/user/insert";
            }

        }else if(userApiDtoForm.getRoleGuestId()!=null&&!(userApiDtoForm.getRoleGuestId().equals(""))) {
            role = Role.GUEST;
            user.setRole(role);
            try{
                roleGuest = roleGUESTService.findById(Long.valueOf(userApiDtoForm.getRoleGuestId()));
                user.setRoleGuest(roleGuest);
            }catch(Exception e){

                return "redirect:/administer/instanceurl/user/insert";
            }
            user.setRoleGuest(new RoleGUEST());
        }else if(userApiDtoForm.getRoleCompanyId()!=null && !(userApiDtoForm.getRoleCompanyId().equals(""))) {
            role = Role.COMPANY;
            user.setRole(role);
            try{
                roleCompany = roleCOMPANYService.findById(Long.valueOf(userApiDtoForm.getRoleCompanyId()));
                user.setRoleCompany(roleCompany);
            }catch(Exception e){

                return "redirect:/administer/instanceurl/user/insert";
            }

        }else{
            role = Role.GUEST;
            user.setRole(role);
            user.setRoleGuest(null);
            user.setRoleGuest(null);
            user.setRoleAdmin(null);
            user.setRoleCompany(null);
            user.setRoleUser(null);
        }
        user.setIsDel("N");
        user.setCreatedDate(LocalDateTime.now());

        userService.save(user);


        return "redirect:/administer/instanceurl/user/insert";
    }


}
