package com.crud.pruebacovinoc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PruebaCovinocApplication {
	public static String usuario = "admin";
	public static String contraseña = "admin1";
	public static void main(String[] args) {

		SpringApplication.run(PruebaCovinocApplication.class, args);



		//System.out.println(usuario +contraseña);

	}


}

