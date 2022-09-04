package com.mysite.cafe.Service;

import com.mysite.cafe.dao.Article;
import com.mysite.cafe.dao.User;
import com.mysite.cafe.repository.ArticleRepository;
import com.mysite.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    public void write(String title, String body){
        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);
        User user = userRepository.findById(1L).get();
        article.setUser(user);

        articleRepository.save(article);
    }

    public List<Article> getLists(){
        return articleRepository.findAll();
    }

    public Article getList(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public boolean findList(Long id){
        return articleRepository.existsById(id);
    }

    public void modify(Long id, String title, String body){
        Article article = articleRepository.findById(id).get();
        article.setTitle(title);
        article.setBody(body);
        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }
}
