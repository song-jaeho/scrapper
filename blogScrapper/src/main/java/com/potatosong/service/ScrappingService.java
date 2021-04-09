package com.potatosong.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.potatosong.model.Article;
import com.potatosong.repository.ArticleRepository;

@Service
public class ScrappingService {

	@Autowired
	private ArticleRepository articleRepository;
	
	private final String VELOG_ROOT_URL = "https://velog.io";
	
	public List<Article> scrapArticlesByUserId(String userId) throws IOException {
	
		List<Article> articleList = new ArrayList<Article>();
		
		String url = VELOG_ROOT_URL + "/@" + userId;
		Connection conn = Jsoup.connect(url);
		Document userMainPage = conn.get();
		
		// 처음으로 글 리스트가 보이는 유저의 메인 페이지
		userMainPage.getElementsByTag("h2").stream()
			.filter(element -> {
				boolean result = false;
				if (element.hasParent()) {
					Element e = element.parent();
					if (e.is("a")) {
						result = true;
					}
				}
				return result;
			})
			.map(element -> {
				return element.parent().attr("href");
			})
			.forEach(href -> {
				
				href = VELOG_ROOT_URL + href;
				try {
					Document detailPage = Jsoup.connect(href).get(); // article 긁어오기 위해 해당 글 상세 페이지로 변경한다.
					
					// article에 필요한 필드들 명시적으로 적어 둔다.
					String articleTitle = null;
					String articleUserName = null; // 사실 필요 없긴함 velog는 userId가 동일하다
					String articleCreaetedAt = null;
					/*
					 * 헤더 세팅 스타트
					 */
					Element header = detailPage.getElementsByClass("head-wrapper").first();
					Element title = header.child(0);
					if (title != null) {
						articleTitle = removeHtmlTags(title.toString());
					}
					
					Element information = header.select(".information").first();
					articleUserName = removeHtmlTags(information.child(0).child(0).toString());
					articleCreaetedAt = removeHtmlTags(information.child(2).toString());
					
					String articleBodyHtml = detailPage.select("#root > div").get(1).child(3).child(0).child(0).toString();
					articleBodyHtml = escapeHtmlTags(articleBodyHtml);
					
					Article article = new Article();
					article.setTitle(articleTitle);
					article.setCreatedAt(articleCreaetedAt);
					article.setUserName(articleUserName);
					article.setBodyHtml(articleBodyHtml);
					
					if (!articleRepository.existsByTitleAndUserName(article.getTitle(), article.getUserName())) {
						articleList.add(article);						
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
				
		return saveUserArticles(articleList);
	}
	
	private List<Article> saveUserArticles(List<Article> userArticles) {
		return articleRepository.saveAll(userArticles);
	}
	
	private String removeHtmlTags(String htmlElement) {
		if (htmlElement == null || htmlElement.length() < 1) return "";
		return htmlElement.replaceAll("<([^>]+)>", "");
	}
	
	private String escapeHtmlTags(String str) {
		return HtmlUtils.htmlEscape(str);
	}
}
