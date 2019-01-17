package com.shuang.projectbidding.ProjectBidding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuang.projectbidding.ProjectBidding.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private User user;

    @Before
    public void setup() {
        user = new User();
        user.setEmail("user1@gmail.com");
        user.setName("user1");
        user.setHourlyPrice(100);
    }

    @Test
    public void get() throws Exception {
        save();
        String expectedUserJson = mapper.writeValueAsString(user);
        mvc.perform(MockMvcRequestBuilders.get("/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedUserJson)));
    }

    @Test
    public void save() throws Exception {
        String userJson = mapper.writeValueAsString(user);
        user.setId(1);
        String expectedUserJson = mapper.writeValueAsString(user);
        mvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedUserJson)));
    }

}