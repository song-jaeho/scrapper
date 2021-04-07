package com.potatosong.controller;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potatosong.model.common.CommonResponse;
import com.potatosong.service.ScrappingService;

@RestController
@RequestMapping(path = {"/scrap"})
public class ScrappingController {

	@Autowired
	ScrappingService scrappingService;
	
	@GetMapping(path = {"/user/{userId}"})
	public ResponseEntity<CommonResponse> scrapUsersAllArticles(
			@PathVariable(value = "userId")
			@NotNull
			String userId) throws IOException {
		
		return ResponseEntity.status(200).body(new CommonResponse(scrappingService.scrapArticlesByUserId(userId)));
	}
}
