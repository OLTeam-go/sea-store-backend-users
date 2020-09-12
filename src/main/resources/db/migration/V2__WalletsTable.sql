create table Wallets
(
    ID         uuid primary key not null,
    User_ID    uuid             not null,
    Balance    numeric          not null,
    Created_At timestamp        not null,
    Updated_At timestamp        not null,
    CONSTRAINT FK_Wallet_Users
        foreign key (User_ID) references Users (ID)
            on update cascade
            on delete cascade
);