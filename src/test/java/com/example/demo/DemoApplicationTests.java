package com.example.demo;

import com.example.demo.dto.Foo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testFooStream() {

		List<Foo> foos = webClient
				.get().uri("/app/stream-flux")
				.accept(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
				.exchange()
				.expectStatus().isOk()
				.returnResult(Foo.class)
				.getResponseBody()
				.take(10)
				.collectList()
				.block();

		foos.forEach(System.out::println);

		assertEquals(10, foos.size());

	}

}
