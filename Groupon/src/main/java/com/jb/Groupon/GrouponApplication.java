package com.jb.Groupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrouponApplication {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SpringApplication.run(GrouponApplication.class, args);
			System.out.println("Spring is running");
		} catch (Exception err) {
			System.out.println("Something was terribly wrong , tam tam \n"+err.getMessage());
		}

	}

}
