package com.example.demo.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class DemoTest {
		
     private MockMvc mockMvc;

     @BeforeEach
     public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                //.alwaysDo(document("{method-name}/{class-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
     }
    
     @Test
	 public void getUserInfoById() throws Exception{
	       this.mockMvc.perform(RestDocumentationRequestBuilders.get("/demo/user/{id}", "user1"))
	       			.andDo(print())
			       .andExpect(status().isOk())
			       .andDo(document("get-user-info-by-Id", 
			    		   pathParameters( 
								parameterWithName("id").description("유저 고유번호") 
			               ),
			    		   responseFields(
			                    fieldWithPath("id").description("유저 고유번호"),
			                    fieldWithPath("name").description("유저 명"),
			                    fieldWithPath("email").description("유저 이메일")
			               )
			       ));
	  }
     
 	@Test
	 public void registUserInfo() throws Exception{
	      this.mockMvc.perform(post("/demo/user")
	    		  .param("id", "user2")
	    		  .param("name","paul")
	    		  .param("email","paul@gmail.com")
	    		  )
	      		  .andDo(print())
	              .andExpect(status().isOk())
	              .andDo(document("regist-user-info",
	                       responseFields(
	                               fieldWithPath("id").description("유저 고유번호"),
	                               fieldWithPath("name").description("유저 명"),
	                               fieldWithPath("email").description("유저 이메일")
	                              )
	              ));
	  }
}
