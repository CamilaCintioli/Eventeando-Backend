package ar.edu.unq.desapp.grupoF.desappgrupoFbackend;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy.Account;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.factory.UserBuilder;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.AccountRepository;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository.UserRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties")
})
public class DesappGrupoFBackendApplication {

	//@Autowired
	//private Environment env;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesappGrupoFBackendApplication.class, args);
	}

	@Bean
	public UserBuilder load() {
		UserBuilder anyBuilderUser = new UserBuilder();

		//load Action//

		//* New Action*//
		userRepository.save(
				anyBuilderUser
						.anyUser()
						.withName("Camila")
						.withLastName("Cintioli")
						.withEmail("camila.cintioli@gmail.com")
						.withPassword("root")
						.get()
		);



		userRepository.save(
				anyBuilderUser
						.anyUser()
						.withName("Uriel")
						.withLastName("Espinoza")
						.withEmail("urielintemperie@gmail.com")
						.withPassword("root")
						.get()
		);

		return anyBuilderUser;





	}
	/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String urls = env.getProperty("cors.urls");
				CorsRegistration reg = registry.addMapping("/**");
				for(String url: urls.split(",")) {
					reg.allowedOrigins(url);
				}
			}
		};
	}*/
}
