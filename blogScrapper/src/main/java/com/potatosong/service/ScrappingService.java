package com.potatosong.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.potatosong.model.Article;

@Service
public class ScrappingService {

	private final String VELOG_ROOT_URL = "https://velog.io";
	
	public List<Article> scrapArticlesByUserId(String userId) throws IOException {
	
		List<Article> resultList = new ArrayList<Article>();
		
		String url = VELOG_ROOT_URL + "/@" + userId;
		Connection conn = Jsoup.connect(url);
		Document userMainPage = conn.get();
		
		// ó������ �� ����Ʈ�� ���̴� ������ ���� ������
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
					Document detailPage = Jsoup.connect(href).get(); // article �ܾ���� ���� �ش� �� �� �������� �����Ѵ�.
					
					// article�� �ʿ��� �ʵ�� ��������� ���� �д�.
					String articleTitle = null;
					String articleUserName = null; // ��� �ʿ� ������ velog�� userId�� �����ϴ�
					String articleCreaetedAt = null;
					List<String> articleTags = new ArrayList<String>();
					String articleBodyHtml = null;
					
					/*
					 * ��� ���� ��ŸƮ
					 */
					Element header = detailPage.getElementsByClass("head-wrapper").first();
					Element title = header.child(0);
					if (title != null) {
						articleTitle = removeHtmlTags(title.toString());
					}
					
					
					
					Element tagHeader = header.child(3);
					tagHeader.children().stream()
						.forEach(tag -> {
							String removedTag = removeHtmlTags(tag.toString());
							if (!removedTag.isBlank()) {
								articleTags.add(removedTag);								
							}
						});
					
					Element body = detailPage.select("#root > div").get(1).child(3).child(0).child(0);
					if (body != null) {
						articleBodyHtml = body.toString();
					}
					
					Article article = new Article();
					article.setTitle(articleTitle);
					article.setUserName(articleUserName);
					article.setCreatedAt(url);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
				
		return resultList;
	}
	
	public String removeHtmlTags(String htmlElement) {
		if (htmlElement == null || htmlElement.length() < 1) return "";
		return htmlElement.replaceAll("<([^>]+)>", "");
	}
}
