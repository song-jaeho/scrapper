package com.potatosong.common;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.potatosong.model.common.CommonResponse;

public class ResponseEntityUtil {

	private HttpHeaders getHttpHeaders() {
		HttpHeaders hedaers = new HttpHeaders();
		hedaers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return hedaers;
	}
}
