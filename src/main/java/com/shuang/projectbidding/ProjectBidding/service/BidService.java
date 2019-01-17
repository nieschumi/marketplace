package com.shuang.projectbidding.ProjectBidding.service;

import com.shuang.projectbidding.ProjectBidding.dao.BidRepository;
import com.shuang.projectbidding.ProjectBidding.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    public Bid bid(int projectId, int userId, int hourlyPrice) {
        return bidRepository.save(new Bid(projectId, userId, hourlyPrice));
    }

    /**
     * In all bids for projectId, find the bid with lowest hourly price
     *
     * @param projectId project ID
     * @return User ID that's selected for this project
     */
    public int getSelectedUserId(int projectId) {
        int userId = 0;
        int lowestPrice = Integer.MAX_VALUE;
        for (Bid bid : bidRepository.findAll()) {
            if (bid.getProjectId() == projectId
                    && bid.getHourlyPrice() < lowestPrice) {
                userId = bid.getUserId();
                lowestPrice = bid.getHourlyPrice();
            }
        }
        return userId;
    }
}
