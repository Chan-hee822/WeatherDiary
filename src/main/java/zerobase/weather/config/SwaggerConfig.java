package zerobase.weather.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		Info info = new Info()
				.title("Weather Diary Application")
				.description("Spring Boot API 예시 프로젝트입니다.")
				.version("v0.0.1");

		return new OpenAPI().components(new Components()).info(info);
	}

}
