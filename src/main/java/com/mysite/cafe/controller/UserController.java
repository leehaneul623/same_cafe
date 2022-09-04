package com.mysite.cafe.controller;

import com.mysite.cafe.Ut.Ut;
import com.mysite.cafe.dao.User;
import com.mysite.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // 회원가입 ==============================
    @RequestMapping("/join")
    @ResponseBody
    public String join(String userId, String password, String name){
        if(Ut.empty(userId)){
            return "아이디를 입력해주세요.";
        }
        if(Ut.empty(password)){
            return "비밀번호를 입력해주세요.";
        }
        if(Ut.empty(name)){
            return "이름을 입력해주세요.";
        }

        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);
        user.setRegDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());

        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

}
