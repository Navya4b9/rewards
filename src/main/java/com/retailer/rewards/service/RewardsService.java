package com.retailer.rewards.service;

import com.retailer.rewards.model.RewardsResponse;



public interface RewardsService {
    public RewardsResponse getRewardsByCustomerId(Long customerId);
}
