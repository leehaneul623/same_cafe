package com.mysite.cafe.repository;

import com.mysite.cafe.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
