package com.mysite.cafe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/test")
    @ResponseBody
    public String index(){
        return "index";
    }
}
