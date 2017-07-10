package saveup.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import saveup.AbstractSaveUpWebIntegrationTests;
import saveup.repository.UserRepository;

public class RestUserControllerTest extends AbstractSaveUpWebIntegrationTests{

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UserRepository repo;

	@Test
	public void retrieveUserById() throws Exception {
		Long userId = 1L;
		mockMvc.perform(get("/user/{userId}", userId).accept(APPLICATION_JSON))//
				.andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id", is(1)))//
				.andExpect(jsonPath("$.firstName", is("Eva")))//
				.andExpect(jsonPath("$.lastName", is("Dawson")))//
				.andExpect(jsonPath("$.email", is("eva@aol.com")))//
				.andExpect(jsonPath("$.password").doesNotExist());//
	}
	
	@Test
	public void registerNewUser() throws Exception {
		long count = repo.count();
		String json = "{\"password\": \"Passw0rd\", \"email\": \"test_user@example.com\", \"firstName\": \"Test\", \"lastName\": \"User\"}";

		mockMvc.perform(post("/user/signup").contentType(APPLICATION_JSON)//
				.content(json))//
				.andExpect(status().isCreated());

		assertThat(repo.count()).isEqualTo(count + 1);
	}
}
