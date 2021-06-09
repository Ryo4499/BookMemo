INSERT INTO roles (authority) values ('ROLE_ADMIN');
INSERT INTO roles (authority) values ('ROLE_USER');

INSERT INTO accounts (account_name,account_email,account_password) values ('aaa','aaa@example.com','$2a$10$d8DVzpGAp5Pexvf6c3Whi./ZMVcT1gAWHXeFM.jZBhvfxhyToFa0e');
INSERT INTO account_role values (1,2);
