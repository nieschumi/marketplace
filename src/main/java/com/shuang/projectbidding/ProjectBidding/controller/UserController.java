package com.shuang.projectbidding.ProjectBidding.controller;

import com.shuang.projectbidding.ProjectBidding.model.Bid;
import com.shuang.projectbidding.ProjectBidding.model.User;
import com.shuang.projectbidding.ProjectBidding.service.BidService;
import com.shuang.projectbidding.ProjectBidding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@Component
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BidService bidService;

    @GetMapping("/users/{id}")
    public User get(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @PostMapping("/users/{userId}/projects/{projectId}")
    public Bid bid(@PathVariable Integer userId, @PathVariable Integer projectId) {
        User user = userService.getUserById(userId);
        return bidService.bid(projectId, userId, user. getHourlyPrice());
    }
}

