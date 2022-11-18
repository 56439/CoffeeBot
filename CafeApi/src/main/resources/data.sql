-- DRINKS
insert into drinks ("name", description) VALUES ('Американо', 'молотый кофе, вода');
insert into drinks ("name", description) VALUES ('Капучино', 'эспрессо, молоко');
insert into drinks ("name", description) VALUES ('Латте', 'эспрессо, молоко');
insert into drinks ("name", description) VALUES ('Мокачино', 'эспрессо, молоко, темный шоколад');
insert into drinks ("name", description) VALUES ('Раф', 'эспрессо, сливки, ванильный сахар');
insert into drinks ("name", description) VALUES ('Флэт уайт', 'двойной эспрессо, молоко');
insert into drinks ("name", description) VALUES ('Какао', 'темный шоколад, молоко');

-- DRINK_VOLUMES
insert into drink_volumes (drink_id, volume, price) VALUES (1, 'S', 111);
insert into drink_volumes (drink_id, volume, price) VALUES (1, 'M', 222);
insert into drink_volumes (drink_id, volume, price) VALUES (1, 'L', 333);

insert into drink_volumes (drink_id, volume, price) VALUES (2, 'S', 111);
insert into drink_volumes (drink_id, volume, price) VALUES (2, 'M', 222);
insert into drink_volumes (drink_id, volume, price) VALUES (2, 'L', 333);

insert into drink_volumes (drink_id, volume, price) VALUES (3, 'S', 111);
insert into drink_volumes (drink_id, volume, price) VALUES (3, 'M', 222);
insert into drink_volumes (drink_id, volume, price) VALUES (3, 'L', 333);

insert into drink_volumes (drink_id, volume, price) VALUES (4, 'S', 111);
insert into drink_volumes (drink_id, volume, price) VALUES (4, 'M', 222);
insert into drink_volumes (drink_id, volume, price) VALUES (4, 'L', 333);

insert into drink_volumes (drink_id, volume, price) VALUES (5, 'S', 111);
insert into drink_volumes (drink_id, volume, price) VALUES (5, 'M', 222);
insert into drink_volumes (drink_id, volume, price) VALUES (5, 'L', 333);

insert into drink_volumes (drink_id, volume, price) VALUES (6, 'S', 111);
insert into drink_volumes (drink_id, volume, price) VALUES (6, 'M', 222);

insert into drink_volumes (drink_id, volume, price) VALUES (7, 'M', 222);
insert into drink_volumes (drink_id, volume, price) VALUES (7, 'L', 333);
-- ###############################################################

-- CAFE
insert into cafe (address, coordinates) VALUES ('пр. Энгельса, 120Д', '60.03703557916698, 30.322379705794816');
insert into cafe (address, coordinates) VALUES ('Политехническая ул., 29', '60.0096011201345, 30.369778077304876');
insert into cafe (address, coordinates) VALUES ('ул. Константина Заслонова, 107А', '59.92103424437164, 30.35368563821624');

-- USERS
insert into users (chat_id, "name") VALUES (263138888, 'Иван');

-- ORDERS
insert into orders (drink_id, volume, user_id, cafe_id) VALUES (2, 0, 1, 3);

-- ADMINS
insert into admins(username, password) VALUES ('admin', 'admin');

-- ADMIN_ROLE
insert into admin_role(admin_id, roles) VALUES (1, 'ADMIN');