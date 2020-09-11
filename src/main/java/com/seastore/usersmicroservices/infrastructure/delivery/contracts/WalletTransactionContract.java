package com.seastore.usersmicroservices.infrastructure.delivery.contracts;

import java.math.BigDecimal;

public class WalletTransactionContract {
    private final BigDecimal amount;

    public WalletTransactionContract() {
        this.amount = null;
    }

    public WalletTransactionContract(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

