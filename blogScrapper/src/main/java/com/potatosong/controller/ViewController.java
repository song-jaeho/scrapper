package com.potatosong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/"})
public class ViewController {

	@GetMapping(value = {"/index"})
	public String view() {
		return "index.html";
	}
}
