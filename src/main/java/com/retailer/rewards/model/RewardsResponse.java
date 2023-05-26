package com.retailer.rewards.model;

public class RewardsResponse {
	private long customerId;
	private int currentMonthRewards;
	private int previousMonthRewards;
	private int previousThirdMonthRewardPoints;
	private int totalRewards;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public int getCurrentMonthRewards() {
		return currentMonthRewards;
	}

	public void setCurrentMonthRewards(int currentMonthRewards) {
		this.currentMonthRewards = currentMonthRewards;
	}

	public int getPreviousMonthRewards() {
		return previousMonthRewards;
	}

	public void setPreviousMonthRewards(int previousMonthRewards) {
		this.previousMonthRewards = previousMonthRewards;
	}

	public int getPreviousThirdMonthRewardPoints() {
		return previousThirdMonthRewardPoints;
	}

	public void setPreviousThirdMonthRewardPoints(int previousThirdMonthRewardPoints) {
		this.previousThirdMonthRewardPoints = previousThirdMonthRewardPoints;
	}

	public int getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(int totalRewards) {
		this.totalRewards = totalRewards;
	}

}
