package org.example.firstinstance.controller.firstinstanceurl.domain;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.UserApiDto;
import org.example.domain.user.UserSearchCondition;
import org.example.domain.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class InstanceUrlUserController {

    private final UserService userService;

        @GetMapping("/administer/instanceurl/user")
        public String index(Model model, UserSearchCondition condition,
                            @RequestParam(value="page", required=false) Integer page, Pageable pageable){

            //firstInstance index의 처음 위치. 리스트 출력.

            List<UserApiDto> lists = userService.searchFindAllDesc();

            model.addAttribute("lists", lists);

            return "firstinstance/user/index";
        }

        @GetMapping("/administer/instanceurl/user/insert")
    public String insert(){

        return "firstinstance/user/insert";
    }


}
