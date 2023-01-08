package org.example.firstinstance.controller.firstinstanceurl.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstanceUrlRoleUSERController {

        @GetMapping("/administer/instanceurl/roleclass/user")
        public String index(){
            //firstInstance index의 처음 위치.
            return "firstInstance/roleclass/user/index";
        }
}
