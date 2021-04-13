create database if not exists pizza_db;

use pizza_db;

drop table if exists pizzas;

create table pizzas (
    pizza_id int(11) not null auto_increment,
    pizza_name varchar(30) not null,
    price float(5,2) not null,
    primary key (pizza_id)
);