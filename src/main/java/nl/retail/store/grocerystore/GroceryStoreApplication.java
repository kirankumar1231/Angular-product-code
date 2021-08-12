package nl.retail.store.grocerystore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.retail.store.grocerystore.entity.ProductEntity;
import nl.retail.store.grocerystore.service.GroceryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class GroceryStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(GroceryService groceryService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<ProductEntity>> typeReference = new TypeReference<List<ProductEntity>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/vegetables.json");
			try {
				List<ProductEntity> products = mapper.readValue(inputStream,typeReference);
				groceryService.save(products);

				log.info("Products loaded into in-memory db!");
			} catch (IOException e){
				log.error("Unable to load products: {}", e.getMessage());
			}
		};
	}

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
