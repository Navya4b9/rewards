package com.retailer.rewards.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.model.RewardsResponse;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.service.RewardsService;

@Service
public class RewardsServiceImpl implements RewardsService {

	private static final int REWARD_LEVEL_2 = 50;
	private static final int REWARD_LEVEL_1 = 100;

	public static final int MONTH_DAYS = 30;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	CustomerRepository customerRepository;

	public RewardsResponse getRewardsByCustomerId(Long customerId) {

		customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer Does not exist"));

		Timestamp currentTimeStamp = getTimeStampFromDays(MONTH_DAYS);
		Timestamp previousMonthTimeStamp = getTimeStampFromDays(2 * MONTH_DAYS);
		Timestamp lastThirdMonthTimestamp = getTimeStampFromDays(3 * MONTH_DAYS);

		List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndDateBetween(
				customerId, currentTimeStamp, Timestamp.from(Instant.now()));
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerIdAndDateBetween(customerId, previousMonthTimeStamp, currentTimeStamp);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerIdAndDateBetween(customerId, lastThirdMonthTimestamp,
						previousMonthTimeStamp);

		return buildRewardResponse(customerId, getRewardsPerMonth(lastMonthTransactions),
				getRewardsPerMonth(lastSecondMonthTransactions), getRewardsPerMonth(lastThirdMonthTransactions));

	}

	private RewardsResponse buildRewardResponse(Long customerId, int currentMonth,
			int previousMonth, int previousthirdMonth) {
		RewardsResponse customerRewards = new RewardsResponse();
		customerRewards.setCustomerId(customerId);
		customerRewards.setCurrentMonthRewards(currentMonth);
		customerRewards.setPreviousMonthRewards(previousMonth);
		customerRewards.setPreviousThirdMonthRewardPoints(previousthirdMonth);
		customerRewards
				.setTotalRewards(currentMonth + previousMonth + previousthirdMonth);

		return customerRewards;
	}

	private int getRewardsPerMonth(List<Transaction> transactions) {
		return transactions.stream().map(transaction -> calculateLoyaltyPoints(transaction.getAmount()))
				.collect(Collectors.summingInt(r -> r));
	}

	public static int calculateLoyaltyPoints(double purchaseAmount) {
		int loyaltyPoints = 0;

		if (purchaseAmount > REWARD_LEVEL_1) {
			double amountOver100 = purchaseAmount - REWARD_LEVEL_1;
			loyaltyPoints += (int) (amountOver100 * 2);
		}

		if (purchaseAmount > REWARD_LEVEL_2) {
			double amountBetween50And100 = Math.min(purchaseAmount, 100) - REWARD_LEVEL_2;
			loyaltyPoints += (int) (amountBetween50And100 * 1);
		}

		return loyaltyPoints;
	}

	public Timestamp getTimeStampFromDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

}
