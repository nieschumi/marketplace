package com.shuang.projectbidding.ProjectBidding.controller;

import com.shuang.projectbidding.ProjectBidding.model.Project;
import com.shuang.projectbidding.ProjectBidding.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project) {
        return projectService.saveOrUpdate(project);
    }
}
