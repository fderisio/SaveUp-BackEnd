delete from user;
delete from category;
delete from income;
delete from payment_method;
delete from expense;

-- BCrypt encoded "test"   --> "$2a$10$0iRS.6c1mJBa08Ru/pWdJeizfFObi3bbD4e.JXDrBJcbP0kdl64ca"
-- BCrypt encoded "master" --> "$2a$10$xk76Fs2eNWVQ3nVRhJb0We6QaA27X.tElKf7vxjZG4AqoXcIPZUwi"
insert into user(id, first_name, last_name, email, password) values (1L, 'Eva', 'Dawson', 'eva@aol.com', 'hello123');
insert into user(id, first_name, last_name, email, password) values (2L, 'Jack', 'Law', 'jack12@aol.com', 'lala456');
insert into user(id, first_name, last_name, email, password) values (3L, 'Susan', 'Lam', 'susan20@aol.com', 'lala454');

insert into category(id, name, user_id, fixed) values (1L, 'Leisure', 1L, false);
insert into category(id, name, user_id, fixed) values (2L, 'Chemist', 1L, false);
insert into category(id, name, user_id, fixed) values (3L, 'Clothing', 2L, false);

insert into income(id, user_id, amount, started_at, monthly) values (1L, 1L, 8000, '01.01.2017', true);
insert into income(id, user_id, amount, started_at, monthly) values (2L, 2L, 5000, '01.01.2017', true);
insert into income(id, user_id, amount, started_at, monthly) values (3L, 1L, 9000, '01.06.2017', true);

insert into payment_method(id, user_id, name, bank) values (1L, 1L, 'MasterCard', 'CitiBank');
insert into payment_method(id, user_id, name, bank) values (2L, 2L, 'Visa', 'CitiBank');
insert into payment_method(id, user_id, name, bank) values (3L, 2L, 'MasterCard', 'UBS');
insert into payment_method(id, user_id, name, bank) values (4L, 1L, 'American Express', 'Credit Suisse');

insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(1L, 1L, 'Dinner', 'Restaurant Bindella', {ts '2017-01-01 00:00:00.00'}, 120, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(2L, 1L, 'Cinema', '', {ts '2017-01-01 00:00:00.00'}, 15, 1L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(3L, 3L, 'Business Suit', 'H&M', {ts '2017-01-01 00:00:00.00'}, 100, 3L);
insert into expense(id, category_id, text, store, expense_date, total, payment_method_id) values 
(4L, 3L, 'Sweater', 'C&A', {ts '2017-01-01 00:00:00.00'}, 20, 4L);