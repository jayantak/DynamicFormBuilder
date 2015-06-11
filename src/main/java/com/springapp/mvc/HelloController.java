package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) throws IOException {

        return "hello";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public ResponseEntity fetchData() throws IOException{

		JOps jops1 = new JOps();
		JSONObject input = jops1.JSONRead("data19.json");

		return new ResponseEntity(input.toString(), HttpStatus.OK);
	}
}