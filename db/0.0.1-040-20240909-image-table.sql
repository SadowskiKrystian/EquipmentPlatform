alter table equipment add column picture_id int after user_id;
alter table equipment add constraint foreign key(picture_id) references picture(id);