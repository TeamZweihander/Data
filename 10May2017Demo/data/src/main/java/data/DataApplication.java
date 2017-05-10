package com.zweihander.navup.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import com.zweihander.navup.data.service.DataService;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.zweihander.navup.data")
public class DataApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(DataApplication.class, args);

	}
}
