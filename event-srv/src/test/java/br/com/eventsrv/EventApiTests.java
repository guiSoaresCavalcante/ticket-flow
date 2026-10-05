package br.com.eventsrv;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventApiTests {

	@Autowired
	private MockMvc mockMvc;

	@Value("${jwt.secret}")
	private String secret;

	private String token(long ttlMillis) {
		return "Bearer " + Jwts.builder()
				.subject("account-1")
				.claim("username", "john")
				.claim("profileId", "11111111-1111-1111-1111-111111111111")
				.expiration(new Date(System.currentTimeMillis() + ttlMillis))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
				.compact();
	}

	@Test
	void rejectsRequestsWithoutToken() throws Exception {
		mockMvc.perform(get("/events")).andExpect(status().isUnauthorized());
		mockMvc.perform(get("/event-venue")).andExpect(status().isUnauthorized());
		mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isUnauthorized());
		mockMvc.perform(get("/events/22222222-2222-2222-2222-222222222222"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void rejectsInvalidAndExpiredTokens() throws Exception {
		mockMvc.perform(get("/events").header(HttpHeaders.AUTHORIZATION, "Bearer garbage"))
				.andExpect(status().isUnauthorized());
		mockMvc.perform(get("/events").header(HttpHeaders.AUTHORIZATION, token(-60_000)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void createsAndListsVenuesAndEvents() throws Exception {
		String auth = token(60_000);

		String venueJson = """
				{"name":"Arena","capacity":500,
				 "address":{"street":"Rua A","number":"10","city":"Sao Paulo","state":"SP","country":"BR"}}
				""";
		String body = mockMvc.perform(post("/event-venue").header(HttpHeaders.AUTHORIZATION, auth)
						.contentType(MediaType.APPLICATION_JSON).content(venueJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.address.id").isNotEmpty())
				.andReturn().getResponse().getContentAsString();
		String venueId = body.replaceAll(".*?\"id\":\"([^\"]+)\".*", "$1");

		mockMvc.perform(get("/event-venue").header(HttpHeaders.AUTHORIZATION, auth))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));

		String eventJson = """
				{"name":"Show","eventType":"CONCERT","startAt":"2026-12-01T20:00:00","status":"DRAFT",
				 "venueId":"%s"}
				""".formatted(venueId);
		String createdEvent = mockMvc.perform(post("/events").header(HttpHeaders.AUTHORIZATION, auth)
						.contentType(MediaType.APPLICATION_JSON).content(eventJson))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.venueId").value(venueId))
				.andExpect(jsonPath("$.organizerId").value("11111111-1111-1111-1111-111111111111"))
				.andReturn().getResponse().getContentAsString();
		String eventId = createdEvent.replaceAll(".*?\"id\":\"([^\"]+)\".*", "$1");

		mockMvc.perform(get("/events/" + eventId).header(HttpHeaders.AUTHORIZATION, auth))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(eventId))
				.andExpect(jsonPath("$.name").value("Show"));

		mockMvc.perform(get("/events/22222222-2222-2222-2222-222222222222").header(HttpHeaders.AUTHORIZATION, auth))
				.andExpect(status().isNotFound());

		mockMvc.perform(get("/events").header(HttpHeaders.AUTHORIZATION, auth))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	void validatesEventInput() throws Exception {
		String auth = token(60_000);
		mockMvc.perform(post("/events").header(HttpHeaders.AUTHORIZATION, auth)
						.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"x\"}"))
				.andExpect(status().isBadRequest());

		String unknownVenue = """
				{"name":"Show","eventType":"CONCERT","startAt":"2026-12-01T20:00:00","status":"DRAFT",
				 "venueId":"22222222-2222-2222-2222-222222222222"}
				""";
		mockMvc.perform(post("/events").header(HttpHeaders.AUTHORIZATION, auth)
						.contentType(MediaType.APPLICATION_JSON).content(unknownVenue))
				.andExpect(status().isNotFound());
	}
}
