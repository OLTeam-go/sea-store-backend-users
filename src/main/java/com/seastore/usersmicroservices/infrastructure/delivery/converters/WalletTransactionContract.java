package com.seastore.usersmicroservices.infrastructure.delivery.converters;

import java.math.BigDecimal;

public class WalletTransactionContract {
    private final BigDecimal amount;

    public WalletTransactionContract(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
