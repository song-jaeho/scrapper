package com.potatosong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ARTICLE")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AT_SEQ", length = 11, nullable = false)
	private Long id;
	
	@Column(name = "AT_TITLE", length = 100, nullable = false)
	private String title;
	
	@Column(name = "AT_USER_NAME", length = 100, nullable = false)
	private String userName;
	
	@Column(name = "AT_CREATE_AT", length = 20, nullable = false)
	private String createdAt; // 약 N시간 전, 혹은 몇월 며칠로 나올 수도 있어 포맷을 정할 수 없다.
	
	@Column(name = "AT_BODY", nullable = false)
	@Lob
	private String bodyHtml;
	
}
