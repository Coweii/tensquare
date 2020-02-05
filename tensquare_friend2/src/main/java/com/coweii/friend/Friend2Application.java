package com.coweii.friend;
import com.coweii.common.util.IdWorker;
import com.coweii.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class Friend2Application {

	public static void main(String[] args) {
		SpringApplication.run(Friend2Application.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}

	
}
