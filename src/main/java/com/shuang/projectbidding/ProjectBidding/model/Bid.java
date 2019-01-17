package com.shuang.projectbidding.ProjectBidding.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Bid {

    private int projectId;
    private int userId;
    private int hourlyPrice;

    @Id
    private String id;

    public Bid() {
    }

    public Bid(int projectId, int userId, int hourlyPrice) {
        this.projectId = projectId;
        this.userId = userId;
        this.hourlyPrice = hourlyPrice;
        id = projectId + "_" + userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(int hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
