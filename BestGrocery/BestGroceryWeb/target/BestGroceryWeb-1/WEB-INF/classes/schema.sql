insert into CategoryType values ('aad8247d-2cf3-4a29-9750-c1456812a33f', 'Meat');
insert into CategoryType values ('a5796e06-f5d3-47be-bb07-2c1453b30f23', 'Seafood');
insert into CategoryType values ('b757d8c2-407b-4323-8f88-d63f00626287', 'Fruits and Vegetables');

insert into Category (id, name, category_type, onSale, image) values ('75e6285d-9ab3-4e5c-91f6-56b7e2c0c3e8', 'Beef','aad8247d-2cf3-4a29-9750-c1456812a33f',true, 'Beef.jpg');
insert into Category (id, name, category_type, onSale, image) values ('b56778c0-c2c7-4c83-9585-b169befb80db', 'Chicken','aad8247d-2cf3-4a29-9750-c1456812a33f',true, 'Chicken.jpg');
insert into Category (id, name, category_type, onSale, image) values ('eebe07bb-7348-4346-882f-8d11c68458d6', 'Pork','aad8247d-2cf3-4a29-9750-c1456812a33f',true, 'Pork.jpg');
insert into Category (id, name, category_type, onSale, image) values ('ebabc0fb-31cf-428f-bb46-301d3562fa59', 'Sausage','aad8247d-2cf3-4a29-9750-c1456812a33f',true, 'Sausage.jpg');
insert into Category (id, name, category_type, onSale, image) values ('638df091-067d-4e60-8164-4aef46087c34', 'Fish','a5796e06-f5d3-47be-bb07-2c1453b30f23',true, 'Fish.jpg');
insert into Category (id, name, category_type, onSale, image) values ('29582b1c-eb02-4479-9e64-1bdb2be646cc', 'Fruits','b757d8c2-407b-4323-8f88-d63f00626287',true, 'Fruits.jpg');
insert into Category (id, name, category_type, onSale, image) values ('83b0ba66-f0e3-4bbc-9519-4657931cc5e3', 'Vegetables','b757d8c2-407b-4323-8f88-d63f00626287',true, 'Vegetables.jpg');

insert into Product (id, name, price, stock, category, onSale, image) values ('314fb44a-8d67-4da2-9112-c99a2011ec07', 'Chicken Wing', 1000, 50, 'b56778c0-c2c7-4c83-9585-b169befb80db', true, 'wings.jpg');
insert into Product (id, name, price, stock, category, onSale, image) values ('c19e0c8a-1792-45a6-9a2d-cf5f6161243a', 'Chicken Breast', 3200, 250, 'b56778c0-c2c7-4c83-9585-b169befb80db', true, 'breasts.jpg');
insert into Product (id, name, price, stock, category, onSale, image) values ('a6f413da-aa31-426c-9d5c-6cbb90f03c2f', 'Whole Chicken', 9000, 30, 'b56778c0-c2c7-4c83-9585-b169befb80db', true, 'Chicken.jpg');
insert into Product (id, name, price, stock, category, onSale, image) values ('21673624-8743-4033-b7ff-88d4a01a03c4', 'Mango', 1000, 300, '29582b1c-eb02-4479-9e64-1bdb2be646cc', true, 'mango.jpg');
insert into Product (id, name, price, stock, category, onSale, image) values ('ce7c6dc3-cad6-4313-8e38-c7c2dd987379', 'Cherry', 7000, 100, '29582b1c-eb02-4479-9e64-1bdb2be646cc', true, 'cherry.jpg');
insert into Product (id, name, price, stock, category, onSale, image) values ('b52e391d-44da-4406-a656-9d84d6520570', 'Strawberry', 4400, 120, '29582b1c-eb02-4479-9e64-1bdb2be646cc', true, 'straw.jpg');
insert into Township values ('82aa15fa-7edd-4c15-8034-420b2c3113b2' , 'North Dagon');