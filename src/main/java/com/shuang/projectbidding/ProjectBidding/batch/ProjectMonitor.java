package com.shuang.projectbidding.ProjectBidding.batch;

import com.shuang.projectbidding.ProjectBidding.model.Project;
import com.shuang.projectbidding.ProjectBidding.service.BidService;
import com.shuang.projectbidding.ProjectBidding.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TimerTask;

@Service
public class ProjectMonitor extends TimerTask {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private BidService bidService;

    @Override
    public void run() {
        for (Project project : projectService.getExpiredOpenedProjects()) {
            project.setSelectedUserId(bidService.getSelectedUserId(project.getId()));
            projectService.saveOrUpdate(project);
        }
    }
}
