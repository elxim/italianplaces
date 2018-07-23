package it.istat.proxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	/**
	 * Redirect sulla pagina di Swagger
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
		 return new ModelAndView("redirect:swagger-ui.html");
    }


}
