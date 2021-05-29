package com.bhaskor.clientapi;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import com.bhaskor.clientapi.controller.ClientController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientApiApplicationTests {

	@Autowired
	private ClientController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}