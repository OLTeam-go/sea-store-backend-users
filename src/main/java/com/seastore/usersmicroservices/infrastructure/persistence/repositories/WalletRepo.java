package com.seastore.usersmicroservices.infrastructure.persistence.repositories;

import com.seastore.usersmicroservices.infrastructure.persistence.entities.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletRepo {
    Wallet create(Wallet wallet);

    List<Wallet> getAll();

    Wallet getByID(UUID ID);

    Wallet getByUserID(UUID userID);

    Wallet getByUsername(String username);

    Integer updateByID(UUID ID, Wallet walletToUpdate);

    Integer deleteByID(UUID ID);
}
