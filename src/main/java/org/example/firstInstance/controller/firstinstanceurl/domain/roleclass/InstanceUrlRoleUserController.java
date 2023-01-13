package org.example.firstinstance.controller.firstinstanceurl.domain.roleclass;

import lombok.RequiredArgsConstructor;
import org.example.domain.address.AddressStr;
import org.example.domain.address.AddressStrService;
import org.example.domain.phone.PhoneStr;
import org.example.domain.phone.PhoneStrService;
import org.example.domain.roleclass.user.*;

import org.example.firstinstance.controller.firstinstanceurl.form.RoleUSERApiDtoForm;
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
public class InstanceUrlRoleUSERController {

    private final RoleUSERService roleUSERService;
    private final AddressStrService addressStrService;
    private final PhoneStrService phoneStrService;

    @GetMapping("/administer/instanceurl/roleclass/user")
    public String index(Model model, RoleUSERSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10)Pageable pageable) throws Exception {

        Page<RoleUSERApiDto> boards = roleUSERService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/roleclass/user/index";
    }

    @GetMapping("/administer/instanceurl/roleclass/user/insert")
    public String insert(Model model, RoleUSERSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<RoleUSERApiDto> boards = roleUSERService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        RoleUSERApiDtoForm userForm = new RoleUSERApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/roleclass/user/insert";
    }

    @PostMapping("/administer/instanceurl/roleclass/user/insert_2")
    public String insert_2(Model model, RoleUSERApiDtoForm userForm){

        RoleUSER roleUSER = null;
        AddressStr addressStr = null;
        PhoneStr phoneStr = null;

        if(userForm.getAddressStrId()!=null) {
            try {
                  addressStr = addressStrService.findById(userForm.getAddressStrId());
            } catch (Exception e) {
                return "redirect:/administer/instanceurl/roleclass/user/insert";
            }
        }

        if(userForm.getPhoneStrId()!=null){
            try {
                phoneStr = phoneStrService.findById(userForm.getPhoneStrId());
            } catch (Exception e) {
                return "redirect:/administer/instanceurl/roleclass/user/insert";
            }
        }

        try {
            roleUSER = new RoleUSER();
            if(addressStr !=null){roleUSER.setAddressStr(addressStr);}
            if(phoneStr !=null){roleUSER.setPhoneStr(phoneStr);}
            roleUSER.setCreatedDate(LocalDateTime.now());
            roleUSER.setIsDel("N");

            roleUSERService.save(roleUSER);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administer/instanceurl/roleclass/user/insert";}

    @GetMapping("/administer/instanceurl/roleclass/user/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        RoleUSER user = null;
        try {
             user = roleUSERService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/roleclass/user/";
        }

        user.setIsDel("Y");
        roleUSERService.save(user);


        return "redirect:/administer/instanceurl/roleclass/user/";
    }

    @GetMapping("/administer/instanceurl/roleclass/user/update")
    public String update(Model model, @RequestParam(value="id")Long id, RoleUSERSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{
        Page<RoleUSERApiDto> boards = roleUSERService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        RoleUSERApiDtoForm userForm = new RoleUSERApiDtoForm();
        RoleUSER roleUSER = null;

        try {
            roleUSER = roleUSERService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/roleclass/user/insert";
        }

        userForm.setId(roleUSER.getId());
        if(roleUSER.getAddressStr()!=null) {
            userForm.setAddressStrId(roleUSER.getAddressStr().getId());
        }
        if(roleUSER.getPhoneStr()!=null) {
            userForm.setPhoneStrId(roleUSER.getPhoneStr().getId());
        }
        userForm.setCreatedDate(roleUSER.getCreatedDate());
        userForm.setModifiedDate(roleUSER.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/roleclass/user/update";
    }

    @PostMapping("/administer/instanceurl/roleclass/user/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,RoleUSERApiDtoForm userForm, RoleUSERSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        RoleUSER roleUSER = null;
        AddressStr addressStr = null;
        PhoneStr phoneStr = null;
        try{
            roleUSER = roleUSERService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/roleclass/user/insert";
        }

        if(userForm.getAddressStrId()!=null){
         try{
             addressStr = addressStrService.findById(userForm.getAddressStrId());
             roleUSER.setAddressStr(addressStr);
         }catch(Exception e){
             return "redirect:/administer/instanceurl/roleclass/user/insert";
         }
        }
        if(userForm.getPhoneStrId()!=null){
            try{
                phoneStr = phoneStrService.findById(userForm.getPhoneStrId());
                roleUSER.setPhoneStr(phoneStr);
            }catch(Exception e){
                return "redirect:/administer/instanceurl/roleclass/user/insert";
            }
        }

        roleUSER.setModifiedDate(LocalDateTime.now());

        roleUSERService.save(roleUSER);




        return "redirect:/administer/instanceurl/roleclass/user/insert";
    }


}
