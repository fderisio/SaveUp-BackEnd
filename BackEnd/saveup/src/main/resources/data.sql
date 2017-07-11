-- BCrypt encoded "test"   --> "$2a$10$0iRS.6c1mJBa08Ru/pWdJeizfFObi3bbD4e.JXDrBJcbP0kdl64ca"
-- BCrypt encoded "master" --> "$2a$10$xk76Fs2eNWVQ3nVRhJb0We6QaA27X.tElKf7vxjZG4AqoXcIPZUwi"

insert into user(id, first_name, last_name, email, password) values 
(1L, 'Eva', 'Dawson', 'eva@aol.com', 'hello123');
insert into user(id, first_name, last_name, email, password) values 
(2L, 'Jack', 'Law', 'jack12@aol.com', 'lala456');
insert into user(id, first_name, last_name, email, password) values 
(3L, 'Susan', 'Lam', 'susan20@aol.com', 'lala454');

insert into category(id, name, user_id, fixed) values (1L, 'Leisure', 1L, false);
insert into category(id, name, user_id, fixed) values (2L, 'Chemist', 1L, false);
insert into category(id, name, user_id, fixed) values (3L, 'Groceries', 1L, false);
insert into category(id, name, user_id, fixed) values (4L, 'Holidays', 1L, false);
insert into category(id, name, user_id, fixed) values (5L, 'Clothing', 1L, false);

insert into category(id, name, user_id, fixed) values (6L, 'Leisure', 2L, false);
insert into category(id, name, user_id, fixed) values (7L, 'Chemist', 2L, false);
insert into category(id, name, user_id, fixed) values (8L, 'Groceries', 2L, false);
insert into category(id, name, user_id, fixed) values (9L, 'Holidays', 2L, false);
insert into category(id, name, user_id, fixed) values (10L, 'Clothing', 2L, false);

insert into category(id, name, user_id, fixed) values (11L, 'Rent', 1L, true);
insert into category(id, name, user_id, fixed) values (12L, 'Cell phone', 1L, true);
insert into category(id, name, user_id, fixed) values (13L, 'Internet', 1L, true);
insert into category(id, name, user_id, fixed) values (14L, 'Health Insurance', 1L, true);
insert into category(id, name, user_id, fixed) values (15L, 'Home Insurance', 1L, true);
insert into category(id, name, user_id, fixed) values (16L, 'Cable - TV', 1L, true);
insert into category(id, name, user_id, fixed) values (17L, 'Transportation Abo', 1L, true);

insert into income(id, user_id, amount, started_at, monthly) values (1L, 1L, 5500, '01.01.2017', true);
insert into income(id, user_id, amount, started_at, monthly) values (2L, 2L, 4700, '01.06.2017', true);

insert into payment_method(id, user_id, name, bank) values (1L, 1L, 'MasterCard', 'CitiBank');
insert into payment_method(id, user_id, name, bank) values (2L, 1L, 'American Express', 'Credit Suisse');
insert into payment_method(id, user_id, name, bank) values (3L, 1L, 'Cash', '');

insert into payment_method(id, user_id, name, bank) values (4L, 2L, 'Visa', 'CitiBank');
insert into payment_method(id, user_id, name, bank) values (5L, 2L, 'MasterCard', 'UBS');
insert into payment_method(id, user_id, name, bank) values (6L, 2L, 'Cash', '');

insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(1L, 1L, 'Dinner', 'Restaurant Bindella', '2017-07-14T00:00:00.000Z', 120, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(2L, 1L, 'Cinema', '', '2017-07-01T00:00:00.000Z', 13, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(3L, 5L, 'Swimwear', 'H&M', '2017-07-03T00:00:00.000Z', 80, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(4L, 5L, 'Sweater', 'C&A', '2017-07-01T00:00:00.000Z', 20, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(5L, 3L, 'Weekly groceries', 'Migros', '2017-07-11T00:00:00.000Z', 40.50, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(6L, 3L, 'Weekly groceries', 'Migros', '2017-07-10T00:00:00.000Z', 46.50, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(7L, 3L, 'Weekly groceries', 'COOP', '2017-07-06T00:00:00.000Z', 6.75, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(8L, 3L, 'Weekly groceries', 'Denner', '2017-07-05T00:00:00.000Z', 31.50, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(9L, 3L, 'Weekly groceries', 'Denner', '2017-06-14T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(10L, 1L, 'Lunch', 'MigrosRestaurant', '2017-06-14T00:00:00.000Z', 24.50, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(11L, 5L, 'Summer clothing', 'H&M', '2017-07-03T00:00:00.000Z', 45, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(12L, 1L, 'Cinema', '', '2017-07-05T00:00:00.000Z', 17, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(13L, 1L, 'Cinema', '', '2017-07-12T00:00:00.000Z', 17, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(14L, 1L, 'Drinks', 'Clouds', '2017-06-02T00:00:00.000Z', 55, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(15L, 1L, 'Cinema', '', '2017-07-02T00:00:00.000Z', 55, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(16L, 1L, 'Concert', '', '2017-06-20T00:00:00.000Z', 55, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(17L, 1L, 'Cinema', '', '2017-06-25T00:00:00.000Z', 25, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(18L, 1L, 'Dinner', 'SantaMaria', '2017-06-05T00:00:00.000Z', 45, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(19L, 3L, 'Weekly groceries', 'Migros', '2017-06-14T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(20L, 3L, 'Weekly groceries', 'COOP', '2017-06-01T00:00:00.000Z', 5.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(21L, 3L, 'Weekly groceries', 'Denner', '2017-06-02T00:00:00.000Z', 8.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(22L, 3L, 'Weekly groceries', 'Migros', '2017-06-24T00:00:00.000Z', 45.50, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(23L, 3L, 'Weekly groceries', 'Denner', '2017-06-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(24L, 11L, '', '', '2017-06-15T00:00:00.000Z', 1900, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(25L, 12L, '', 'Swisscom', '2017-06-15T00:00:00.000Z', 69, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(26L, 13L, '', 'Cablecom', '2017-06-15T00:00:00.000Z', 90, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(27L, 3L, 'Weekly groceries', 'Denner', '2017-05-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(28L, 3L, 'Weekly groceries', 'Denner', '2017-05-15T00:00:00.000Z', 85.50, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(29L, 3L, 'Weekly groceries', 'Denner', '2017-05-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(30L, 3L, 'Weekly groceries', 'Denner', '2017-04-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(31L, 3L, 'Weekly groceries', 'Denner', '2017-04-15T00:00:00.000Z', 85.50, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(32L, 3L, 'Weekly groceries', 'Denner', '2017-04-15T00:00:00.000Z', 85.50, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(33L, 3L, 'Weekly groceries', 'Denner', '2017-03-15T00:00:00.000Z', 85.50, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(34L, 3L, 'Weekly groceries', 'Denner', '2017-02-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(35L, 3L, 'Weekly groceries', 'Denner', '2017-02-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(36L, 3L, 'Weekly groceries', 'Denner', '2017-01-15T00:00:00.000Z', 85.50, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(37L, 4L, 'New York', '', '2017-01-02T00:00:00.000Z', 1500, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(38L, 4L, 'Buenos Aires Stay', '', '2017-03-02T00:00:00.000Z', 900, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(39L, 4L, 'Buenos Aires Flight', '', '2017-01-30T00:00:00.000Z', 1500, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(40L, 14L, '', '', '2017-01-01T00:00:00.000Z', 300, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(41L, 15L, '', '', '2017-01-01T00:00:00.000Z', 40, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(42L, 16L, '', '', '2017-01-01T00:00:00.000Z', 40, 2L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(43L, 17L, '', '', '2017-01-01T00:00:00.000Z', 340, 1L);