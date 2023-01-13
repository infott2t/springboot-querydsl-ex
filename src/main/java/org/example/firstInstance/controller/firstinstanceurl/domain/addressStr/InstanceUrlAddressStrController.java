package org.example.firstinstance.controller.firstinstanceurl.domain.addressStr;

import lombok.RequiredArgsConstructor;
import org.example.domain.address.AddressStr;
import org.example.domain.address.AddressStrApiDto;
import org.example.domain.address.AddressStrSearchCondition;
import org.example.domain.address.AddressStrService;
import org.example.firstinstance.controller.firstinstanceurl.form.AddressStrApiDtoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Controller
public class InstanceUrlAddressStrController {

    private final AddressStrService addressStrService;
 

    @GetMapping("/administer/instanceurl/addressStr")
    public String index( Model model, AddressStrSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10) org.springframework.data.domain.Pageable pageable) throws Exception {
        Page<AddressStrApiDto> boards = null;
        try {
           boards = addressStrService.searchAllV2(condition, pageable);
        }catch (Exception e){
            return "redirect:/administer/instanceurl/addressStr";
        }




        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/addressStr/index";
    }

    @GetMapping("/administer/instanceurl/addressStr/insert")
    public String insert(Model model, AddressStrSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10) Pageable pageable) throws Exception{

        Page<AddressStrApiDto> boards = null;
        try {
            boards = addressStrService.searchAllV2(condition, pageable);
        }catch (Exception e){
            return "redirect:/administer/instanceurl/addressStr/insert";
        }


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        AddressStrApiDtoForm userForm = new AddressStrApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/addressStr/insert";
    }

    @PostMapping("/administer/instanceurl/addressStr/insert_2")
    public String insert_2(Model model, AddressStrApiDtoForm userForm){

        AddressStr addressStr = null;


        try {
             addressStr = AddressStr.builder().build();            /**
             * Setter use ....
             * */
        addressStr.setZipCode(userForm.getZipCode());
        addressStr.setAddr1(userForm.getAddr1());
        addressStr.setAddr2(userForm.getAddr2());
        addressStr.setAddrFull(addressStr.getZipCode() + " " + addressStr.getAddr1() + " " + addressStr.getAddr2());

        addressStr.setCreatedDate(LocalDateTime.now());
        addressStr.setIsDel("N");

            addressStrService.save(addressStr);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administer/instanceurl/addressStr/insert";}

    @GetMapping("/administer/instanceurl/addressStr/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        AddressStr user = null;
        try {
             user = addressStrService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/addressStr/";
        }

        user.setIsDel("Y");
        addressStrService.save(user);


        return "redirect:/administer/instanceurl/addressStr/";
    }

    @GetMapping("/administer/instanceurl/addressStr/update")
    public String update(Model model, @RequestParam(value="id")Long id, AddressStrSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<AddressStrApiDto> boards = null;
        try {
            boards = addressStrService.searchAllV2(condition, pageable);
        }catch (Exception e){
            return "redirect:/administer/instanceurl/addressStr/update";
        }


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        AddressStrApiDtoForm userForm = new AddressStrApiDtoForm();
        AddressStr addressStr = null;

        try {
            addressStr = addressStrService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/addressStr/insert";
        }
        /**
         * Setter Method use ....
         * */
        
        userForm.setId(addressStr.getId());
        userForm.setZipCode(addressStr.getZipCode());
        userForm.setAddr1(addressStr.getAddr1());
        userForm.setAddr2(addressStr.getAddr2());
        userForm.setAddrFull(addressStr.getAddrFull());
        userForm.setIsDel(addressStr.getIsDel());
        
        userForm.setCreatedDate(addressStr.getCreatedDate());
        userForm.setModifiedDate(addressStr.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/addressStr/update";
    }

    @PostMapping("/administer/instanceurl/addressStr/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,AddressStrApiDtoForm userForm, AddressStrSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        AddressStr addressStr = null;
        
        try{
            addressStr = addressStrService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/addressStr/insert";
        }

        /**
         * Setter Method use ....
         * */

        addressStr.setZipCode(userForm.getZipCode());
        addressStr.setAddr1(userForm.getAddr1());
        addressStr.setAddr2(userForm.getAddr2());
        addressStr.setAddrFull(addressStr.getZipCode() + " " + addressStr.getAddr1() + " " + addressStr.getAddr2());
        addressStr.setIsDel(userForm.getIsDel());
        addressStr.setModifiedDate(LocalDateTime.now());

        addressStrService.save(addressStr);
        

        return "redirect:/administer/instanceurl/addressStr/insert";
    }


}
