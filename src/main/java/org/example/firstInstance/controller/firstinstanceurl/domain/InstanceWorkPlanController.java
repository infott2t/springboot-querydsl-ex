package org.example.firstinstance.controller.firstinstanceurl.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstanceWorkPlanController {

        @GetMapping("/administer/instanceurl/worlplan")
        public String index(){
            //firstInstance index의 처음 위치.
            return "firstInstance/workplan/index";
        }
}
