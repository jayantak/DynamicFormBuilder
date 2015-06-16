package com.springapp.mvc;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/")
public class FormController {
	@RequestMapping(method = RequestMethod.GET)
	public String frontPage(ModelMap model) throws IOException {

        return "hello";
	}

    @RequestMapping(method = RequestMethod.GET, value = "/imageTest")
    public String imageTest
            (ModelMap model) throws IOException {

        return "DisplayImage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pageData")
    public ResponseEntity fetchPageData() throws IOException{

        JSONOperations jops1 = new JSONOperations();
        JSONObject input = jops1.JSONRead("data19.json");

        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/image.jpg", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif", method = RequestMethod.GET)
//    public ResponseEntity
//    getImage() {
//        try {
//            InputStream inputStream = this.getClass().getResourceAsStream("WEB-INF/pages/img.jpg");
//            BufferedImage bufferedImage = ImageIO.read(inputStream);
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
//
//            return new ResponseEntity(byteArrayOutputStream.toByteArray(), HttpStatus.OK);
//
//        } catch (IOException e) {
//
//            throw new RuntimeException(e);
//        }
//    }

	@RequestMapping(method = RequestMethod.GET, value = "/formData")
	public ResponseEntity fetchFormData() throws IOException{

		JSONOperations jops1 = new JSONOperations();
		JSONObject input1 = jops1.JSONRead("data19.json");

		return new ResponseEntity(input1.toString(), HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value = "/dataOut")
    public ResponseEntity fetchFormOutput() throws IOException{

        JSONOperations jops1 = new JSONOperations();
        JSONObject input = jops1.JSONRead("dataOut.json");

        return new ResponseEntity(input.toString(), HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/sendForm")
	public ResponseEntity giveData(@RequestParam String param1) throws IOException {

        JSONOperations jops1 = new JSONOperations();
        JSONParser parser = new JSONParser();

        JSONObject JSONoutput = new JSONObject();
        Object output;

        try {
            output =  parser.parse(param1);
            JSONoutput=(JSONObject) output;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        jops1.JSONWrite(JSONoutput, "dataOut.json");

        return new ResponseEntity(param1, HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, value="/formsubmitted")
    public String formSubmitted(ModelMap model) throws IOException {

        return "formsubmitted";
    }


}