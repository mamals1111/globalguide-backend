package com.myrmicatech.globalguidebackend;

import com.myrmicatech.globalguidebackend.property.DataSeedProperties;
import com.myrmicatech.globalguidebackend.property.FileStorageProperties;
//import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import com.myrmicatech.globalguidebackend.property.TinifyProperties;
import com.tinify.Tinify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({FileStorageProperties.class, DataSeedProperties.class, TinifyProperties.class})
//@EnableEmailTools
public class GlobalGuideBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobalGuideBackendApplication.class, args);
	}

}
