drop table parking_lot_order if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table parking_lot_order (
id long not null,
capacity integer,
location varchar(255),
name varchar(255),
primary key (id));