package br.com.lfr.timelog;

import br.com.lfr.timelog.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeLogApplicationTests {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	MongoTemplate mongoTemplate;

	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test public void
	should_receive_response_with_user_json_and_status_code_200_when_call_get_on_user_endpoint_with_id() throws Exception {
		importAndSaveJSON("user_test/user_get_test.json", User.class);
		mockMvc.perform(get("/api/v1/user/5b81a5e76bf7104b3da24054"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user.id", is("5b81a5e76bf7104b3da24054")));
	}

    @Test public void
    should_receive_response_with_error_json_and_status_code_404_when_call_get_on_user_endpoint_with_wrong_id() throws Exception {
        mockMvc.perform(get("/api/v1/user/123456789"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("User 123456789 not found.")));
	}

    @Test public void
	should_receive_response_with_the_created_user_and_status_code_203_when_call_post_on_user_endpoint() throws Exception {
		String json = importJSON("user_test/user_post_test_1.json");
		mockMvc.perform(post("/api/v1/user/")
				.contentType(APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.user.id", notNullValue()));
	}

    @Test public void
    should_receive_response_with_error_json_and_status_code_400_when_call_post_on_user_endpoint_with_same_login_twice() throws Exception {
        String json = importJSON("user_test/user_post_test_2.json");

        mockMvc.perform(post("/api/v1/user")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/user")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("The login posttest2 already exists.")));
    }

    @Test public void
    should_receive_response_with_error_json_and_status_code_400_when_call_post_on_user_endpoint_with_missing_name_property() throws Exception {
        String json = importJSON("user_test/user_post_test_3.json");
        mockMvc.perform(post("/api/v1/user")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("name value must not be null.")));
    }

    @Test public void
    should_receive_empty_response_with_status_code_204_when_call_put_on_user_endpoint() throws Exception {
	    importAndSaveJSON("user_test/user_put_test_1.json", User.class);
	    String json = importJSON("user_test/user_put_test_2.json");
	    mockMvc.perform(put("/api/v1/user/5b8344a06a519c54a7033ecc")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isNoContent());
    }

    @Test public void
    should_receive_response_with_error_json_and_status_code_400_when_call_put_on_user_endpoint_with_missing_email_property() throws Exception {
        String json = importJSON("user_test/user_put_test_3.json");
        mockMvc.perform(put("/api/v1/user/123456789")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("email value must not be null.")));
    }

    @Test public void
    should_receive_response_with_error_json_and_status_code_400_when_call_put_on_user_endpoint_with_same_login_property() throws Exception {
        importAndSaveJSON("user_test/user_put_test_4.json", User.class);
        importAndSaveJSON("user_test/user_put_test_5.json", User.class);
        String json = importJSON("user_test/user_put_test_6.json");
        mockMvc.perform(put("/api/v1/user/5b833fe76a519c50a4d8a408")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("The login puttest5 already exists.")));
    }

    @Test public void
    should_receive_response_with_error_json_and_status_code_404_when_call_put_on_user_endpoint_with_wrong_id() throws Exception {
        String json = importJSON("user_test/user_put_test_6.json");
        mockMvc.perform(put("/api/v1/user/123456")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("User 123456 not found.")));
    }

	@Test public void
	should_receive_response_with_authentication_json_and_status_code_200_when_call_post_on_auth_endpoint_with_valid_login() throws Exception {
		String userjson = importJSON("auth_test/user_auth_test_1.json");
		mockMvc.perform(post("/api/v1/user")
				.contentType(APPLICATION_JSON)
				.content(userjson));

		String json = importJSON("auth_test/login_test_1.json");
		mockMvc.perform(post("/api/v1/authenticate")
				.contentType(APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token", notNullValue()))
				.andExpect(jsonPath("$.user", notNullValue()));
	}

	@Test public void
	should_receive_response_with_error_json_and_status_code_401_when_call_post_on_auth_endpoint_with_invalid_login() throws Exception {
		String userjson = importJSON("auth_test/user_auth_test_2.json");
		mockMvc.perform(post("/api/v1/user")
				.contentType(APPLICATION_JSON)
				.content(userjson));

		String json = importJSON("auth_test/login_test_2.json");
		mockMvc.perform(post("/api/v1/authenticate")
				.contentType(APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("Invalid login: authtest1789")));
	}

	@Test public void
	should_receive_response_with_error_json_and_status_code_401_when_call_post_on_auth_endpoint_with_invalid_password() throws Exception {
		String userjson = importJSON("auth_test/user_auth_test_3.json");
		mockMvc.perform(post("/api/v1/user")
				.contentType(APPLICATION_JSON)
				.content(userjson));

		String json = importJSON("auth_test/login_test_3.json");
		mockMvc.perform(post("/api/v1/authenticate")
				.contentType(APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("Invalid password for user authtest3")));
	}

	private void importAndSaveJSON(String resourceName, Class clazz){
		insertDocument(importJSON(resourceName), clazz);
	}

	private String importJSON(String resourceName){
		InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		return reader.lines().findFirst().get();
	}

	private void insertDocument(String document, Class clazz){
		try {
			mongoTemplate.insert((new ObjectMapper()).readValue(document, clazz));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
