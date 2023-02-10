insert into user (username, password, role, created_on, update_on) values ('admin', '$2a$10$uZDFhNY5YhQV365wvFVF/O.vk/oXXJm6jlCS1X8A1c/mAvzJ.bUd.', 0, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('pollenbuttel0', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 1, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('aciccarello1', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 2, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('elilleman2', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 1, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('fpritchard3','$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 2, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('jairey4', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 1, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('asarl5', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 2, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('nhehl6', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 1, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('swurst7', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 2, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('dfolkard8', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 1, '2023-02-09','2023-02-09');
insert into user (username, password, role, created_on, update_on) values ('lpalumbo9', '$2a$10$rbf2Gy5YT9zsr7/zYm7Sf.8lUpdLEc4GHF4AFDNc4AAVLxUDdloua', 2, '2023-02-09','2023-02-09');

insert into product (name, price, category, count, created_on, update_on) values ('Cheese - La Sauvagine', 5, 0, 1, '2023-02-09', '2023-02-09');
insert into product (name, price, category, count, created_on, update_on) values ('Towel Multifold', 2, 0, 3, '2023-01-09', '2023-01-09');
insert into product (name, price, category, count, created_on, update_on) values ('Clams - Canned', 11, 1, 4, '2023-01-09', '2023-01-09');
insert into product (name, price, category, count, created_on, update_on) values ('Bagelers - Cinn / Brown', 5, 0, 6, '2023-02-08', '2023-02-08');
insert into product (name, price, category, count, created_on, update_on) values ('Taro Root', 6, 0, 1,  '2023-02-08', '2023-02-08');
insert into product (name, price, category, count, created_on, update_on) values ('Pasta - Lasagna, Dry', 7, 0, 8, '2023-02-07', '2023-02-09');
insert into product (name, price, category, count, created_on, update_on) values ('Burger', 7, 1, 7, '2023-02-07', '2023-02-09');
insert into product (name, price, category, count, created_on, update_on) values ('Coriander - Ground', 6, 0, 10, '2023-02-07', '2023-02-09');
insert into product (name, price, category, count, created_on, update_on) values ('Foam Cup 6 Oz', 7, 0, 2, '2023-02-07', '2023-02-09');
insert into product (name, price, category, count, created_on, update_on) values ('Knife Plastic - White', 2, 0, 10, '2023-02-07', '2023-02-09');

insert into orders (created_on, update_on, product_count, status, total_price, product_id, user_id) values ('2023-02-09', '2023-02-09', 1, 1, 2.3, 1, 1)