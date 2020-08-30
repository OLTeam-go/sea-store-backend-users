package com.seastore.usersmicroservices.infrastructure.delivery.converters;

public class OptionActiveContract {
    private final boolean active;

    public OptionActiveContract(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
