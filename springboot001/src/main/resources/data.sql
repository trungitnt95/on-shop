-- User
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
insert into t_product (id, name, category, genre, tag, price, total, version) values (1, 'Game01', 'CATE_01', 'GENRE_A', '#gg_x', 10.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (2, 'Game02', 'CATE_01', 'GENRE_A', '#gg_y', 20.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (3, 'Game03', 'CATE_02', 'GENRE_B', '#gg_x', 30.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (4, 'Game04', 'CATE_02', 'GENRE_B', '#gg_y', 40.01, 5, 0);
insert into t_product (id, name, category, genre, tag, price, total, version) values (5, 'Game05', 'CATE_02', 'GENRE_C', '#gg_x', 50.01, 5, 0);


-- Cart
create table t_cart (
    id bigint unique not null,
    user_id bigint not null,
    product_id bigint not null,
    quantity integer not null
);
alter table t_cart add foreign key (user_id) references t_user(id);
alter table t_cart add foreign key (product_id) references t_product(id);
alter table t_cart add primary key (user_id, product_id);
insert into t_cart (id, user_id, product_id, quantity) values (1, 1, 1, 1);


-- Order & order status
create table t_order (
    id bigint primary key,
    user_id bigint not null,
    product_id bigint not null,
    price decimal(10, 2) not null,
    quantity integer not null,
    total_price decimal(10, 2) not null,
    payment_method varchar(20) not null,
    version integer not null
);
alter table t_order add foreign key (user_id) references t_user(id);
alter table t_order add foreign key (product_id) references t_product(id);

create table t_order_status (
    id bigint primary key,
    order_id bigint not null,
    order_status varchar(20) not null,
    date timestamp
);
alter table t_order_status add foreign key (order_id) references t_order(id);
