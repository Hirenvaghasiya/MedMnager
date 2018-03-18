package org.MadManager.medmanager;

import com.sun.media.jfxmedia.MediaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MedManagerApplication extends SpringBootServletInitializer {

	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MediaManager.class);
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(MedManagerApplication.class, args);
	}
}
