package net.savantly.mesh.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties("mesh.web")
public class WebMvcConfig {
	
	private String corsAllowed = "*";

	public String getCorsAllowed() {
		return corsAllowed;
	}

	public void setCorsAllowed(String corsAllowed) {
		this.corsAllowed = corsAllowed;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/").allowedOrigins(corsAllowed);
            }
        };
    }

}
