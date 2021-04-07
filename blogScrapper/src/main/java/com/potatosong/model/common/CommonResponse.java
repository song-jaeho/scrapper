package com.potatosong.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class CommonResponse {

	public static final String DEFAULT_RESULT_CODE_SUCCESS = "0000";
	public static final String DEFAULT_RESULT_CODE_FAIL = "9999";
	
	public static final String DEFAULT_RESULT_MESSAGE_SUCCES = "";
	public static final String DEFAULT_RESULT_MESSAGE_FAIL = "½ÇÆÐ";
	
	@JsonProperty(value = "body")
	private String body;
	
	private String resultCode;
	private String resultMessage;
	
	public CommonResponse(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		this.body = mapper.writeValueAsString(o);
		this.resultCode = DEFAULT_RESULT_CODE_SUCCESS;
		this.resultMessage = DEFAULT_RESULT_MESSAGE_SUCCES;
	}
	
}
