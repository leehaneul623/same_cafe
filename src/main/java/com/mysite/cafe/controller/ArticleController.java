package com.mysite.cafe.controller;

import com.mysite.cafe.Ut.Ut;
import com.mysite.cafe.dao.Article;
import com.mysite.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

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

        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);
        article.setUserId(1L);

        articleRepository.save(article);

        return "%d번 게시물이 생성 되었습니다.".formatted(article.getId());
    }

    // R읽기 ==============================
    @RequestMapping("/list") // 게시글 전체 조회
    @ResponseBody
    public List<Article> showList(){
        return articleRepository.findAll();
    }

    @RequestMapping("/detail") // 게시글 단건 조회
    @ResponseBody
    public Article showDetail(Long id){
        Article article = articleRepository.findById(id).get();
        return article;
    }

    // U수정 ==============================
    @RequestMapping("/modify")
    @ResponseBody
    public String modify(Long id, String title, String body){
        if(id == null){
            return "게시물 번호를 입력해주세요.";
        }
        if(articleRepository.existsById(id)){
            return "게시물이 없습니다.";
        }
        if(Ut.empty(title)){
            return "제목을 입력하세요.";
        }
        if(Ut.empty(body)){
            return "내용을 입력하세요.";
        }

        Article article = articleRepository.findById(id).get();
        article.setTitle(title);
        article.setBody(body);
        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);

        return  "%번 게시물 수정이 완료 되었습니다.".formatted(article.getId());
    }

    // D삭제 ==============================
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id){
        if(!articleRepository.existsById(id)){
            return "%d번 게시물이 없습니다.".formatted(id);
        }
        Article article = articleRepository.findById(id).get();
        articleRepository.delete(article);

        return "%d번 게시물을 삭제 했습니다.".formatted(id);
    }
}
