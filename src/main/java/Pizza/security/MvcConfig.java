package Pizza.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("Index");
        registry.addViewController("/Index").setViewName("Index");
        registry.addViewController("/Login").setViewName("Login");
        registry.addViewController("/Panel").setViewName("Panel");
        registry.addViewController("/Pracownicy").setViewName("Pracownicy");
        registry.addViewController("/Modyfikujpracownika").setViewName("Modyfikujpracownika");
        registry.addViewController("/Info").setViewName("Info");
        registry.addViewController("/Password").setViewName("Password");
    }
}



