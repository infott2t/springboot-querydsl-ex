package org.example.firstinstance.controller.firstinstanceurl.domain.phonestr;

import lombok.RequiredArgsConstructor;
import org.example.domain.phone.PhoneStr;
import org.example.domain.phone.PhoneStrApiDto;
import org.example.domain.phone.PhoneStrSearchCondition;
import org.example.domain.phone.PhoneStrService;
import org.example.firstinstance.controller.firstinstanceurl.form.PhoneStrApiDtoForm;
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
public class InstanceUrlPhoneStrController {

    private final PhoneStrService phoneStrService;
 

    @GetMapping("/administer/instanceurl/phoneStr")
    public String index(Model model, PhoneStrSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10) Pageable pageable) throws Exception {

        Page<PhoneStrApiDto> boards = null;
        try {
           boards = phoneStrService.searchAllV2(condition, pageable);
        }catch (Exception e){
            return "redirect:/administer/instanceurl/phoneStr/";
        }

        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/phoneStr/index";
    }

    @GetMapping("/administer/instanceurl/phoneStr/insert")
    public String insert(Model model, PhoneStrSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{


        Page<PhoneStrApiDto> boards = null;
        try {
           boards = phoneStrService.searchAllV2(condition, pageable);
        }catch (Exception e){
            return "redirect:/administer/instanceurl/phoneStr/";
        }


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        PhoneStrApiDtoForm userForm = new PhoneStrApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/phoneStr/insert";
    }

    @PostMapping("/administer/instanceurl/phoneStr/insert_2")
    public String insert_2(Model model, PhoneStrApiDtoForm userForm){

        PhoneStr phoneStr = null;


        try {
             phoneStr = PhoneStr.builder().build();            /**
             * Setter use ....
             * */
        phoneStr.setPhoneNumber(userForm.getPhoneNumber());
        phoneStr.setIsDel(userForm.getIsDel());
        phoneStr.setModifiedDate(LocalDateTime.now());
            phoneStr.setCreatedDate(LocalDateTime.now());
            phoneStr.setIsDel("N");

            phoneStrService.save(phoneStr);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administer/instanceurl/phoneStr/insert";}

    @GetMapping("/administer/instanceurl/phoneStr/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        PhoneStr user = null;
        try {
             user = phoneStrService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/phoneStr/";
        }

        user.setIsDel("Y");
        phoneStrService.save(user);


        return "redirect:/administer/instanceurl/phoneStr/";
    }

    @GetMapping("/administer/instanceurl/phoneStr/update")
    public String update(Model model, @RequestParam(value="id")Long id, PhoneStrSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<PhoneStrApiDto> boards = null;
        try {
           boards = phoneStrService.searchAllV2(condition, pageable);
        }catch (Exception e){
            return "redirect:/administer/instanceurl/phoneStr/";
        }

        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        PhoneStrApiDtoForm userForm = new PhoneStrApiDtoForm();
        PhoneStr phoneStr = null;

        try {
            phoneStr = phoneStrService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/phoneStr/insert";
        }
        /**
         * Setter Method use ....
         * */
        
        userForm.setId(phoneStr.getId());
        userForm.setPhoneNumber(phoneStr.getPhoneNumber());
        userForm.setIsDel(phoneStr.getIsDel());
        
        userForm.setCreatedDate(phoneStr.getCreatedDate());
        userForm.setModifiedDate(phoneStr.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/phoneStr/update";
    }

    @PostMapping("/administer/instanceurl/phoneStr/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,PhoneStrApiDtoForm userForm, PhoneStrSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        PhoneStr phoneStr = null;
        
        try{
            phoneStr = phoneStrService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/phoneStr/insert";
        }

        /**
         * Setter Method use ....
         * */

        phoneStr.setPhoneNumber(userForm.getPhoneNumber());
        phoneStr.setIsDel(userForm.getIsDel());
        phoneStr.setModifiedDate(LocalDateTime.now());

        phoneStrService.save(phoneStr);
        

        return "redirect:/administer/instanceurl/phoneStr/insert";
    }


}
