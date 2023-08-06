package com.agenciacristal.microserver;

import com.agenciacristal.microserver.product.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class MicroserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserverApplication.class, args);
	}


}
