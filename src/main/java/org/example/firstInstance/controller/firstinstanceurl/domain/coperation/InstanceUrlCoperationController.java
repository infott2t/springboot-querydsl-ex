package org.example.firstinstance.controller.firstinstanceurl.domain.coperation;

import lombok.RequiredArgsConstructor;
import org.example.domain.coperation.*;
import org.example.firstinstance.controller.firstinstanceurl.form.CoperationApiDtoForm;
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
public class InstanceUrlCoperationController {

    private final CoperationService coperationService;
 

    @GetMapping("/administer/instanceurl/coperation")
    public String index(Model model, CoperationSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10) Pageable pageable) throws Exception {

        Page<CoperationApiDto> boards = coperationService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/coperation/index";
    }

    @GetMapping("/administer/instanceurl/coperation/insert")
    public String insert(Model model, CoperationSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<CoperationApiDto> boards = coperationService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        CoperationApiDtoForm userForm = new CoperationApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/coperation/insert";
    }

    @PostMapping("/administer/instanceurl/coperation/insert_2")
    public String insert_2(Model model, CoperationApiDtoForm userForm){

        Coperation coperation = null;


        try {
            coperation = new Coperation();
            /**
             * Setter use ....
             * */
        coperation.setCoperationName(userForm.getCoperationName());
        coperation.setCatchPrice(userForm.getCatchPrice());
        //coperation.setIsDel(userForm.getIsDel());
            coperation.setCreatedDate(LocalDateTime.now());
            coperation.setIsDel("N");

            coperationService.save(coperation);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administer/instanceurl/coperation/insert";}

    @GetMapping("/administer/instanceurl/coperation/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        Coperation user = null;
        try {
             user = coperationService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/coperation/";
        }

        user.setIsDel("Y");
        coperationService.save(user);


        return "redirect:/administer/instanceurl/coperation/";
    }

    @GetMapping("/administer/instanceurl/coperation/update")
    public String update(Model model, @RequestParam(value="id")Long id, CoperationSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{
        
        Page<CoperationApiDto> boards = coperationService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        CoperationApiDtoForm userForm = new CoperationApiDtoForm();
        Coperation coperation = null;

        try {
            coperation = coperationService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/coperation/insert";
        }
        /**
         * Setter Method use ....
         * */
        
        userForm.setId(coperation.getId());
        userForm.setCoperationName(coperation.getCoperationName());
        userForm.setCatchPrice(coperation.getCatchPrice());
        userForm.setIsDel(coperation.getIsDel());
        
        userForm.setCreatedDate(coperation.getCreatedDate());
        userForm.setModifiedDate(coperation.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/coperation/update";
    }

    @PostMapping("/administer/instanceurl/coperation/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,CoperationApiDtoForm userForm, CoperationSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        Coperation coperation = null;
        
        try{
            coperation = coperationService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/coperation/insert";
        }

        /**
         * Setter Method use ....
         * */

        coperation.setCoperationName(userForm.getCoperationName());
        coperation.setCatchPrice(userForm.getCatchPrice());
        //coperation.setIsDel(userForm.getIsDel());
        coperation.setModifiedDate(LocalDateTime.now());

        coperationService.save(coperation);
        

        return "redirect:/administer/instanceurl/coperation/insert";
    }


}
