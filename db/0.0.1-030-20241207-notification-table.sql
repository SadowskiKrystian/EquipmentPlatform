create table notification
(
    id              int auto_increment primary key,
    sender_login	varchar(200) not null,
    receiver_id     int          not null,
    title           varchar(100)  not null,
    content         varchar(500) not null,
    create_datetime datetime     not null,
    seen_datetime   datetime,
    delete_datetime datetime,
    foreign key (receiver_id) references user (id)
)
    COLLATE='utf8mb4_polish_ci'
    ENGINE=InnoDB
;