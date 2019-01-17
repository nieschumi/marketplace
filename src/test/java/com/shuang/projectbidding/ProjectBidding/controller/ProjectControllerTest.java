package com.shuang.projectbidding.ProjectBidding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuang.projectbidding.ProjectBidding.model.Project;
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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private Project project;


    @Before
    public void setUp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        Date date = calendar.getTime();
        project = new Project();
        project.setEndDate(date);
        project.setName("Project1");
        project.setRequiredHours(20);
        project.setRequirements("Description of project");
    }


    @Test
    public void save() throws Exception {
        String projectJson = mapper.writeValueAsString(project);
        project.setId(1);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
        project.setCreateDate(calendar.getTime());
        String expectedProjectJson = mapper.writeValueAsString(project);
        mvc.perform(MockMvcRequestBuilders.post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(projectJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedProjectJson)));
    }


}