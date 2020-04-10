package springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "springbootdemo.dao")
@CrossOrigin(origins = "*")
public class CourseApi {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*")
						.allowedOrigins("*").allowCredentials(true);
			}
		};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(CourseApi.class, args);

	}

}
