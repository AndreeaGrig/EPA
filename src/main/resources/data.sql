insert into author(first_name, last_name) values('Mihai','Eminescu');
insert into author(first_name, last_name) values('Vasile','Alecsandri');
insert into author(first_name, last_name) values('Ion','Creanga');
insert into pub_house( name, city) values('RAO','Bucuresti');
insert into pub_house( name, city) values('ALL','Bucuresti');
insert into role(name) values ('ROLE_USER');
insert into role(name) values ('ROLE_ADMIN');
insert into book_info(id,info) values (1,'Book description');
insert into book(id,add_date, date_created, category, cover, number_of_pages, title, url_pdf, book_info_id, pub_house_id) values(1,CURDATE(), CURDATE(), 'Art',	null, 455, 'title', '1.pdf', 1, 2);
insert into users(username, email, password)values('andreea','andreea@gmail.com','$2a$10$d8ztEkiK5EDrjEyTeTBkeurv9nzE8GxVfMHo6lEG4cWqYtyym6kG6');
insert into users(username, email, password)values('alex','alex@gmail.com','$2a$10$YhzNEo7Ty7YUxT5Ap84YS.fs5JPLuLXozOyeprUPiZ9NpOof7k/t6');
insert into users_roles(user_id, role_id) values(1,2);
insert into users_roles values (2,2);
insert into review(id, text) values(1,'I Like this book!');
insert into favourites(id,book_fav_id, user_fav_id) values(1,1,1);