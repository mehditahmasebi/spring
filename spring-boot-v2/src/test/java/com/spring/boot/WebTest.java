package com.spring.boot;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.boot.model.JsonInputModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTest extends BaseTest {
	
	static HttpSession session = null;
	
	@Test
	public void a_session() throws Exception
	{
		this.mockMvc.perform(get("/public/test"))
				.andDo(new ResultHandler() {
					
					@Override
					public void handle(MvcResult result) throws Exception {
						session = result.getRequest().getSession();
					}
				});
	}
	
	@Test
	public void b_testHelloWorld() throws Exception
	{
		MvcResult result = this.mockMvc.perform(get("/public/test").session((MockHttpSession) session))
								.andDo(new ResultHandler() {
									
									@Override
									public void handle(MvcResult result) throws Exception {
										System.out.println("Response Header (Domain) :: " + result.getResponse().getHeader("domain"));
									}
								})
								.andExpect(status().is2xxSuccessful())
								.andDo(print())
								.andReturn();
		System.out.println("Print result body : " + result.getResponse().getContentAsString());
	}
	
	@Test
	public void c_jsonResultTest() throws Exception
	{
		String username = "admin";
		String password = "admin";
		this.mockMvc.perform(get("/public/login/{username}/{password}",username, password)
					.session((MockHttpSession) session))
						.andDo(new ResultHandler() {
							
							@Override
							public void handle(MvcResult result) throws Exception {
								Gson gson = new Gson();
								JsonObject jsonResult = gson.fromJson(result.getResponse().getContentAsString(), JsonObject.class);
								Assert.assertNotNull(jsonResult.get(username));
							}
						})
						.andExpect(status().is2xxSuccessful());
	}
	@Test
	public void d_jsonInput() throws Exception
	{
		JsonInputModel input = new JsonInputModel();
		input.setInputKey("mehdi");
		input.setInputKey("tahmasebi");
		Gson gson = new Gson();
		this.mockMvc.perform(post("/public/test/jsoninput/{pv}?rp=bar","foo")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(gson.toJson(input)))
			.andExpect(status().is2xxSuccessful());			
	}

}
