package com.edgewalk.accidence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by: edgewalk
 * 2018-08-20 12:01
 */
@RestController
public class TestController {

	@GetMapping
	public void test(HttpServletRequest request){
		Locale locale = request.getLocale();
		System.out.println(locale.toString());
	}
}
