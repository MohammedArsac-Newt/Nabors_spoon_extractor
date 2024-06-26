package org.newtglobal.dmapextension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableConfigurationProperties
@SpringBootApplication
public class DmapExtensionApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DmapExtensionApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DmapExtensionApplication.class);
	}

}
