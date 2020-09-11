package com.seastore.usersmicroservices.infrastructure.persistence.impl;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;
import com.seastore.usersmicroservices.infrastructure.persistence.repositories.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository("Wallet")
public class WalletRepoImpl implements WalletRepo {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WalletRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Wallet create(Wallet wallet) {
        final String sql = "insert into Wallets" +
                "(ID, User_ID, Balance, Created_At, Updated_At) " +
                "values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                wallet.getID(),
                wallet.getUserID(),
                wallet.getBalance(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt()
        );
        return wallet;
    }

    @Override
    public Wallet getByID(UUID findID) {
        final String sql = "select " +
                "ID, " +
                "User_ID, " +
                "Balance, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "Wallets " +
                "where " +
                "ID=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findID}, ((result, i) -> {
            UUID ID = UUID.fromString(result.getString("ID"));
            UUID userID = UUID.fromString(result.getString("User_ID"));
            BigDecimal balance = result.getBigDecimal("Balance");
            Timestamp createdAt = result.getTimestamp("Created_At");
            Timestamp updatedAt = result.getTimestamp("Updated_At");
            return new Wallet(ID, userID, balance, createdAt, updatedAt);
        }));
    }


    @Override
    public Wallet getByUserID(UUID findUserID) {
        final String sql = "select " +
                "ID, " +
                "User_ID, " +
                "Balance, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "Wallets " +
                "where " +
                "User_ID=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findUserID}, ((result, i) -> {
            UUID ID = UUID.fromString(result.getString("ID"));
            UUID userID = UUID.fromString(result.getString("User_ID"));
            BigDecimal balance = result.getBigDecimal("Balance");
            Timestamp createdAt = result.getTimestamp("Created_At");
            Timestamp updatedAt = result.getTimestamp("Updated_At");
            return new Wallet(ID, userID, balance, createdAt, updatedAt);
        }));
    }

    @Override
    public Wallet getByUsername(String findUsername) {
        final String sql = "select " +
                "Wallets.ID, " +
                "Wallets.User_ID, " +
                "Wallets.Balance, " +
                "Wallets.Created_At, " +
                "Wallets.Updated_At " +
                "from " +
                "Wallets " +
                "inner join Users on Users.ID = Wallets.User_ID " +
                "where " +
                "Users.Username=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{findUsername}, ((result, i) -> {
            UUID ID = UUID.fromString(result.getString("ID"));
            UUID userID = UUID.fromString(result.getString("User_ID"));
            BigDecimal balance = result.getBigDecimal("Balance");
            Timestamp createdAt = result.getTimestamp("Created_At");
            Timestamp updatedAt = result.getTimestamp("Updated_At");
            return new Wallet(ID, userID, balance, createdAt, updatedAt);
        }));
    }

    @Override
    public List<Wallet> getAll() {
        final String sql = "select " +
                "ID, " +
                "User_ID, " +
                "Balance, " +
                "Created_At, " +
                "Updated_At " +
                "from " +
                "Wallets";
        return jdbcTemplate.query(sql, ((result, i) -> {
            UUID ID = UUID.fromString(result.getString("ID"));
            UUID userID = UUID.fromString(result.getString("User_ID"));
            BigDecimal balance = result.getBigDecimal("Balance");
            Timestamp createdAt = result.getTimestamp("Created_At");
            Timestamp updatedAt = result.getTimestamp("Updated_At");
            return new Wallet(ID, userID, balance, createdAt, updatedAt);
        }));
    }

    @Override
    public Integer updateByID(UUID findID, Wallet walletToUpdate) {
        final String sql = "update Wallets set User_ID=?, Balance=?, Created_At=?, Updated_At=? where ID=?";
        return jdbcTemplate.update(sql,
                walletToUpdate.getUserID(),
                walletToUpdate.getBalance(),
                walletToUpdate.getCreatedAt(),
                walletToUpdate.getUpdatedAt(),
                findID);
    }

    @Override
    public Integer deleteByID(UUID findID) {
        final String sql = "delete from Wallets where ID=?";
        return jdbcTemplate.update(sql, findID);
    }

}
