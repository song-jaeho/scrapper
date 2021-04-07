package com.potatosong.model;

import java.util.List;

import lombok.Data;

@Data
public class Article {

	private String id;
	
	private String title;
	
	private String userName;
	
	private String createdAt; // 약 N시간 전, 혹은 몇월 며칠로 나올 수도 있어 포맷을 정할 수 없다.
	
	private List<String> tags;
	
	private String bodyHtml;
	
	
}
