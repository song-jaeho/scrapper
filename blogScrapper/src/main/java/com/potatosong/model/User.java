package com.potatosong.model;

import java.util.List;

import lombok.Data;

@Data
public class User {

	private String userId;
	
	// private String userKey; Ű�� ����ؾ� �� ��� �� �̸�
	
	private List<Article> articles;
}
