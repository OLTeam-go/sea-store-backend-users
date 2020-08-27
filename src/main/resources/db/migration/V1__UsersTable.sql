create table Users
(
    ID         uuid primary key not null,
    Username   varchar          not null,
    Email      varchar          not null,
    Password   varchar          not null,
    Name       varchar          not null,
    Gender     varchar          not null,
    Active     boolean          not null,
    Created_At date             not null,
    Updated_At date             not null
);