create table TransactionsHistories
(
    ID         uuid primary key not null,
    User_ID    uuid             not null,
    Wallet_ID  uuid             not null,
    Amount     numeric          not null,
    Type       varchar          not null,
    Created_At timestamp        not null,
    Updated_At timestamp        not null,
    CONSTRAINT FK_TransactionsHistories_Users
        foreign key (User_ID) references Users (ID)
            on update cascade
            on delete cascade,
    CONSTRAINT FK_TransactionsHistories_Wallet
        foreign key (Wallet_ID) references Wallets (ID)
            on update cascade
            on delete cascade

);