create database cosza_store;
use cosza_store;

create table size(
	id int auto_increment,
    name varchar(10),
    
    primary key(id)
);

create table color(
	id int auto_increment,
    name varchar(10),
    
    primary key(id)
);

create table product(
	id int auto_increment,
    image text,
    name varchar(255),
    price decimal,
    description text,
    quantity int,
    image_detail text,
    size_id int,
    color_id int,
    category_id int,
    
    primary key(id)
);

create table category(
	id int auto_increment,
    name varchar(50),
    
    primary key(id)
);

create table category_tag(
	category_id int,
    tag_id int,
    
    primary key(category_id,tag_id)
);

create table tag(
	id int auto_increment,
    name varchar(50),
    
    primary key(id)
);

create table order_detail(
	product_id int,
    order_id int,
    price decimal,
    quantity int,
    
    primary key(product_id,order_id)
);

create table country(
	id int auto_increment,
    name varchar(100),
    price_ship decimal,
    
    primary key(id)
);

create table u_order(
	id int auto_increment,
    country_id int,
    user_id int,
    date timestamp default current_timestamp,
    
    primary key(id)
);

create table user(
	id int auto_increment,
    username varchar(255),
    password varchar(255),
    email varchar(255),
    
    primary key(id)
);

create table tag_blog(
	tag_id int,
    blog_id int,
    
    primary key(tag_id,blog_id)
);

create table blog(
	id int auto_increment,
    image text,
    title varchar(255),
    description text,
    user_id int,
    content text,
    create_date timestamp default current_timestamp,
    
    primary key(id)
);

create table u_comment(
	id int auto_increment,
    blog_id int,
    comment text,
    create_date timestamp default current_timestamp,
    name varchar(255),
    email varchar(255),
    website varchar(255),
    
    primary key(id)
);

alter table product add constraint fk_product_size_id foreign key(size_id) references size(id);
alter table product add constraint fk_product_color_id foreign key(color_id) references color(id);
alter table product add constraint fk_product_category_id foreign key(category_id) references category(id);

alter table category_tag add constraint fk_category_tag_category_id foreign key(category_id) references category(id);
alter table category_tag add constraint fk_category_tag_tag_id foreign key(tag_id) references tag(id);

alter table order_detail add constraint fk_corder_detail_product_id foreign key(product_id) references product(id);
alter table order_detail add constraint fk_corder_detail_order_id foreign key(order_id) references u_order(id);

alter table u_order add constraint fk_u_corder_country_id foreign key(country_id) references country(id);
alter table u_order add constraint fk_u_corder_user_id foreign key(user_id) references user(id);

alter table tag_blog add constraint fk_tag_blog_tag_id foreign key(tag_id) references tag(id);
alter table tag_blog add constraint fk_tag_blog_blog_id foreign key(blog_id) references blog(id);

alter table u_comment add constraint fk_comment_blog_id foreign key(blog_id) references blog(id);

alter table blog add constraint fk_blog_user_id foreign key(user_id) references user(id);

