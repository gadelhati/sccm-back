package br.com.fattoria.sccm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {


    @RequestMapping(value = "/test")
    @ResponseBody
	public String teste() {
    	 
    	 return "teste";
	}

}
