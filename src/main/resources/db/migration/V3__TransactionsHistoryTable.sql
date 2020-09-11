create table TransactionsHistory
(
    ID         uuid primary key not null,
    User_ID    uuid             not null,
    Wallet_ID  uuid             not null,
    Amount     money            not null,
    Created_At timestamp        not null,
    Updated_At timestamp        not null,
    CONSTRAINT FK_TransactionsHistory_Users
        foreign key (User_ID) references Users (ID)
            on update cascade
            on delete cascade,
    CONSTRAINT FK_TransactionsHistory_Wallet
        foreign key (Wallet_ID) references Wallets (ID)
            on update cascade
            on delete cascade

);