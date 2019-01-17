package com.shuang.projectbidding.ProjectBidding.dao;

import com.shuang.projectbidding.ProjectBidding.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Integer> {

}
