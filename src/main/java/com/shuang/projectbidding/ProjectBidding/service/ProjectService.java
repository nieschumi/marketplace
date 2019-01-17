package com.shuang.projectbidding.ProjectBidding.service;

import com.shuang.projectbidding.ProjectBidding.dao.ProjectRepository;
import com.shuang.projectbidding.ProjectBidding.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private final String TIME_ZONE = "PST";
    private final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Get all projects from db
     *
     * @return all projects
     */
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id).get();
    }

    public Project saveOrUpdate(Project project) {
        project.setCreateDate(currentDate());
        return projectRepository.save(project);
    }

    public void delete(int id) {
        projectRepository.deleteById(id);
    }

    /**
     * Scan all projects, filter projects whose end time is before current time
     *
     * @return List of expired projects
     */
    public List<Project> getExpiredOpenedProjects() {
        List<Project> projects = new ArrayList<>();
        Date currentDate = currentDate();
        projectRepository.findAll().forEach(project -> {
            if (project.getSelectedUserId() == 0 && hasExpired(project.getEndDate(), currentDate)) {
                projects.add(project);
            }
        });
        return projects;
    }

    /**
     * Convert endDate to PST, compare with current date to identify is project has expired
     * @param endDate
     * @param currentDate
     * @return
     */
    private boolean hasExpired(Date endDate, Date currentDate) {
        DateFormat formatter = new SimpleDateFormat(FORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        try {
            Date endDateWithTimeZone = formatter.parse(formatter.format(endDate));
            return endDateWithTimeZone.before(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    private Date currentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("PST"));
        Date date = calendar.getTime();
        return date;
    }
}
