package com.shuang.projectbidding.ProjectBidding.service;

import com.shuang.projectbidding.ProjectBidding.dao.UserRepository;
import com.shuang.projectbidding.ProjectBidding.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User saveOrUpdate(User person) {
    	return userRepository.save(person);
    }

    public void delete(int id) {
    	userRepository.deleteById(id);
    }
    

}
