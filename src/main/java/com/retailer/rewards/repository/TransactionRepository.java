package com.retailer.rewards.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retailer.rewards.entity.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    public List<Transaction> findAllByCustomerIdAndDateBetween(Long customerId, Timestamp from,Timestamp to);
}
