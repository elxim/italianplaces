package it.istat.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("it.istat.proxy.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Istat Proxy")
				.description("Spring REST per la consultazione dei dati territoriali italiani.")
				.termsOfServiceUrl("http://www.istat.it/storage/codici-unita-amministrative/elenco-comuni-italiani.xls")
				.contact("Michele Pizza").license("Notartel SpA").licenseUrl("mpizza@notariato.it")
				.version("v0.2-20171108").build();
	}
}