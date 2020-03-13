create user act_track_user with password 'act_track_user';
create database act_track_db;
grant all privileges on database act_track_db to act_track_user;

create table staff_activities (
  id          bigserial    not null,
  create_date timestamp,
  duration    int4         not null,
  name        varchar(255) not null,
  start_time  timestamp    not null,
  staff_id    int8         not null,
  primary key (id)
);

create table staffs (
  id          bigserial not null,
  create_date timestamp,
  staff_id    int8,
  primary key (id)
);

alter table if exists staff_activities
  add constraint FK7s6y5lraagihphavh52e9d4mg foreign key (staff_id) references staffs;

