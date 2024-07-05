create table token
(
    id          	int auto_increment primary key,
    user_id     	int not null,
    value			varchar(255) not null,
    expiration_date datetime not null,
    is_used			boolean,
    foreign key (user_id) references user(id)
)
    COLLATE='utf8mb4_polish_ci'
    ENGINE=InnoDB
;