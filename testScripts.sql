insert into users (id, name, sur_name, middle_name) values (nextval('users_id_seq'), 'test', 'test', 'test');
insert into auth_nodes(id, name, url) VALUES (nextval('auth_nodes_id_seq'), 'node_1', 'http://localhost:8081/auth.do');
insert into auth_nodes_users (id_node, id_user) VALUES (1, 1);
insert into passwords (id, password, user_id)  VALUES (nextval('passwords_id_seq'), 'test', 1);
insert into logins (id, login, user_id)  VALUES (nextval('logins_id_seq'), 'test', 1);
insert into app_services (id, name, type, host)  VALUES (nextval('app_services_id_seq'), 'Telegram', 'TLGM', 'http://localhost:8088');
insert into app_services (id, name, type, host)  VALUES (nextval('app_services_id_seq'), 'Vkontakte', 'VK', 'http://localhost:8084');