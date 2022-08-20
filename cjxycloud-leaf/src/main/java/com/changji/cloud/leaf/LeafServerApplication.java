package com.changji.cloud.leaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leaf
 */
@SpringBootApplication(scanBasePackages = { "com.changji.cloud" })
public class LeafServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeafServerApplication.class, args);
	}

}
