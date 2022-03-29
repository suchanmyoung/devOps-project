package com.x1.chan.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class MemberServiceTest {
    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void loginTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .param("loginId", "dev")
                        .param("password", "dev"))
                        .andReturn().getModelAndView().getModelMap();
    }


    @Test
    public void mybatis(){
    }

}