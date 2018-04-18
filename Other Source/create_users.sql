CREATE TABLE users (
username VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
PRIMARY KEY (username)
);
CREATE TABLE user_roles (
user_role_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
username VARCHAR(50) NOT NULL,
role VARCHAR(50) NOT NULL,
PRIMARY KEY (user_role_id),
FOREIGN KEY (username) REFERENCES users(username)
);
INSERT INTO users VALUES ('admin', 'adminpw');
INSERT INTO user_roles(username, role) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO users VALUES ('user1', 'user1pw');
INSERT INTO user_roles(username, role) VALUES ('user1', 'ROLE_USER');



/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  s1176187
 * Created: Apr 17, 2018
 */

