-- User
create sequence t_user_seq start with 2 increment by 50;
create table t_user (
    id bigint primary key,
    name varchar(250) not null,
    version int not null
);
insert into t_user (id, name, version) values (1, 'mock-user', 0);


-- Products
create table t_product (
    id bigint primary key,
    name varchar(250) not null,
    category varchar(20),
    genre varchar(20),
    tag varchar(20),
    price decimal(10, 2) not null,
    total integer not null,
    version integer not null
);
create sequence t_product_seq start with 6 increment by 50;

insert into t_product (id, name, category, genre, tag, price, total, version) values (1, 'Game01', 'CATE_01', 'GENRE_A', '#gg_x', 10.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (2, 'Game02', 'CATE_01', 'GENRE_A', '#gg_y', 20.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (3, 'Game03', 'CATE_02', 'GENRE_B', '#gg_x', 30.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (4, 'Game04', 'CATE_02', 'GENRE_B', '#gg_y', 40.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (5, 'Game05', 'CATE_02', 'GENRE_C', '#gg_x', 50.01, 5, 0);


-- Cart
create table t_cart_product (
    id bigint unique not null,
    user_id bigint not null,
    product_id bigint not null,
    quantity integer not null
);
create sequence t_cart_product_seq start with 2 increment by 50;

alter table t_cart_product add foreign key (user_id) references t_user(id);
alter table t_cart_product add foreign key (product_id) references t_product(id);
alter table t_cart_product add primary key (user_id, product_id);

-- insert into t_cart_product (id, user_id, product_id, quantity) values (1, 1, 1, 1);


-- Order & order status
create table t_order (
    id bigint primary key,
    user_id bigint not null,
    total_price decimal(10, 2),
    payment_method varchar(20),
    version integer not null default 0
);
alter table t_order add foreign key (user_id) references t_user(id);

create table t_order_product (
     id bigint primary key,
     order_id bigint not null,
     product_id bigint not null,
     quantity integer not null
);
alter table t_order_product add foreign key (order_id) references t_order(id);
alter table t_order_product add foreign key (product_id) references t_product(id);

create table t_order_status (
    id bigint primary key,
    order_id bigint not null,
    order_status varchar(20) not null,
    date timestamp
);

create sequence t_order_seq start with 1 increment by 50;
create sequence t_order_product_seq start with 1 increment by 50;
create sequence t_order_status_seq start with 1 increment by 50;

alter table t_order_status add foreign key (order_id) references t_order(id);
