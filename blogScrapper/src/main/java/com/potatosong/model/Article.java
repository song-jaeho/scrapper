package com.potatosong.model;

import java.util.List;

import lombok.Data;

@Data
public class Article {

	private String id;
	
	private String title;
	
	private String userName;
	
	private String createdAt; // �� N�ð� ��, Ȥ�� ��� ��ĥ�� ���� ���� �־� ������ ���� �� ����.
	
	private List<String> tags;
	
	private String bodyHtml;
	
	
}
