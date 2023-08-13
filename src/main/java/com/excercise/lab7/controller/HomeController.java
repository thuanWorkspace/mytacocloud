package com.excercise.lab7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * this is replace it config.class
 * 	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
 * @author MyPC
 *
 */
@RestController
public class HomeController {

//	@GetMapping("/")
//	public String home() {
//		System.out.println("ru me");
//		return "home";
//	}
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);

	@RequestMapping("/log")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }
}
