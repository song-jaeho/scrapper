package com.potatosong.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.potatosong.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

	boolean existsByTitleAndUserName(String title, String userName);
}
