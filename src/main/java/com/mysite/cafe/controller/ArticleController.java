package com.mysite.cafe.controller;

import com.mysite.cafe.Service.ArticleService;
import com.mysite.cafe.Ut.Ut;
import com.mysite.cafe.dao.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // C생성 ==============================
    @RequestMapping("/write")
    @ResponseBody
    public String write(String title, String body){
        if(Ut.empty(title)){
            return "제목을 입력해주세요.";
        }
        if(Ut.empty(body)){
            return "내용을 입력해주세요.";
        }

        articleService.write(title, body);
        return "%d번 게시물이 생성 되었습니다.";
    }

    // R읽기 ==============================
    @RequestMapping("/list") // 게시글 전체 조회
    @ResponseBody
    public List<Article> showList(){
        List<Article> articles = articleService.getLists();
        return articles;
    }

    @RequestMapping("/detail") // 게시글 단건 조회
    @ResponseBody
    public Article showDetail(Long id){
        Article article = articleService.getList(id);
        return article;
    }

    // U수정 ==============================
    @RequestMapping("/modify")
    @ResponseBody
    public String modify(Long id, String title, String body){
        if(id == null){
            return "게시물 번호를 입력해주세요.";
        }
        if(articleService.findList(id)){
            return "게시물이 없습니다.";
        }
        if(Ut.empty(title)){
            return "제목을 입력하세요.";
        }
        if(Ut.empty(body)){
            return "내용을 입력하세요.";
        }

        articleService.modify(id, title, body);
        return  "%번 게시물 수정이 완료 되었습니다.";
    }

    // D삭제 ==============================
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id){
        if(articleService.findList(id)){
            return "%d번 게시물이 없습니다.";
        }

        articleService.delete(id);

        return "%d번 게시물을 삭제 했습니다.".formatted(id);
    }
}
