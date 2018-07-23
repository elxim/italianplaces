package it.istat.proxy;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@ConfigurationProperties(prefix = "it.istat.proxy")
@Slf4j
public @Data class ComuniProperties {

	private String sourceUrl;
	private String digestXls;
	private String dateFormatter;
	private String xlsname;
	private String xlsSoppressi;
	private String localPath;
	private String versionNumber;

	// *** GETTER AND SETTER


	@PostConstruct
	private void checkConfig() throws Exception {
		log.info("CHECK CONFIG ...");
		if (null!=this.sourceUrl) 
			log.info("sourceUrl : "+this.sourceUrl);
			else
			throw new Exception("CONFIG ERROR!");
		log.info("..CONFIG OK!");
	}

}
