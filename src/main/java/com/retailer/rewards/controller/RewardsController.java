package com.retailer.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.model.RewardsResponse;
import com.retailer.rewards.service.RewardsService;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    RewardsService rewardsService;

    @GetMapping(value = "/customer/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RewardsResponse> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
       
        RewardsResponse customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards,HttpStatus.OK);
    }

}
