package br.com.fattoria.sccm.configuration;

import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;
import static org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
//@RequestMapping(value = "/swagger-ui")
public class SwaggerController {
	
//	private String swaggerUiPath = "index.html";
//
//	@GetMapping(DEFAULT_PATH_SEPARATOR)
//	public String index() {
//		return REDIRECT_URL_PREFIX + swaggerUiPath;
//	}
	
//    @RequestMapping(value = "/")
//    public String index() {
//        System.out.println("swagger-ui.html");
//        return "redirect:swagger-ui.html";
//    }
}
