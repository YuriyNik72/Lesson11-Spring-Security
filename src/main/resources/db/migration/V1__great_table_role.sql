DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
                       id int(11) NOT NULL AUTO_INCREMENT,
                       name varchar(50) DEFAULT NULL,
                       PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
