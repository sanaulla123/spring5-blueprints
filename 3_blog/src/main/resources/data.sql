INSERT INTO blog_user(username, email, password, name, enabled) 
VALUES ('admin', 'admin@gmail.com', '12345', 'Admin User', 1);

INSERT INTO blog_user_role(username, role_name)
VALUES('admin',  'admin');