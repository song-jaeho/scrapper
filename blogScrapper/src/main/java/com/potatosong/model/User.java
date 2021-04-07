package com.potatosong.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private String userId;
	
	// private String userKey; 키로 사용해야 할 경우 이 이름
	
	private List<Article> articles;
}
