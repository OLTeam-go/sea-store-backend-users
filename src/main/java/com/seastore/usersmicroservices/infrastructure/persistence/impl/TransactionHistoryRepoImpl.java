package com.seastore.usersmicroservices.infrastructure.persistence.impl;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.TransactionHistory;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository("TransactionHistory")
public class TransactionHistoryRepoImpl implements TransactionHistoryRepo {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionHistoryRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TransactionHistory create(TransactionHistory transactionHistory) {
        final String sql = "insert into TransactionsHistories" +
                "(ID, User_ID, Wallet_ID, Amount, Type, Created_At, Updated_At) " +
                "values(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                transactionHistory.getID(),
                transactionHistory.getUserID(),
                transactionHistory.getWalletID(),
                transactionHistory.getAmount(),
                transactionHistory.getType(),
                transactionHistory.getCreatedAt(),
                transactionHistory.getUpdatedAt()
        );
        return transactionHistory;
    }

    @Override
    public TransactionHistory getByID(UUID findID) {
        final String sql = "select " +
                "ID, " +
                "User_ID, " +
                "Wallet_ID, " +
                "Amount, " +
                "Type, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "TransactionsHistories " +
                "where " +
                "ID=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findID}, ((result, i) -> {
            UUID ID = UUID.fromString(result.getString("ID"));
            UUID userID = UUID.fromString(result.getString("User_ID"));
            UUID walletID = UUID.fromString(result.getString("Wallet_ID"));
            BigDecimal amount = result.getBigDecimal("Amount");
            String type = result.getString("Type");
            Timestamp createdAt = result.getTimestamp("Created_At");
            Timestamp updatedAt = result.getTimestamp("Updated_At");
            return new TransactionHistory(ID, userID, walletID, amount, type, createdAt, updatedAt);
        }));
    }


    @Override
    public List<TransactionHistory> getAll() {
        final String sql = "select " +
                "ID, " +
                "User_ID, " +
                "Wallet_ID, " +
                "Amount, " +
                "Type, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "TransactionsHistories";
        return jdbcTemplate.query(sql, ((result, i) -> {
            UUID ID = UUID.fromString(result.getString("ID"));
            UUID userID = UUID.fromString(result.getString("User_ID"));
            UUID walletID = UUID.fromString(result.getString("Wallet_ID"));
            BigDecimal amount = result.getBigDecimal("Amount");
            String type = result.getString("Type");
            Timestamp createdAt = result.getTimestamp("Created_At");
            Timestamp updatedAt = result.getTimestamp("Updated_At");
            return new TransactionHistory(ID, userID, walletID, amount, type, createdAt, updatedAt);
        }));
    }

    @Override
    public Integer updateByID(UUID findID, TransactionHistory transactionHistoryToUpdate) {
        final String sql = "update TransactionsHistories set User_ID=?, Wallet_ID=?, Amount=?, Type=?, Created_At=?, Updated_At=? where ID=?";
        return jdbcTemplate.update(sql,
                transactionHistoryToUpdate.getUserID(),
                transactionHistoryToUpdate.getWalletID(),
                transactionHistoryToUpdate.getAmount(),
                transactionHistoryToUpdate.getType(),
                transactionHistoryToUpdate.getCreatedAt(),
                transactionHistoryToUpdate.getUpdatedAt()
        );
    }

    @Override
    public Integer deleteByID(UUID findID) {
        final String sql = "delete from TransactionsHistories where ID=?";
        return jdbcTemplate.update(sql, findID);
    }

}
