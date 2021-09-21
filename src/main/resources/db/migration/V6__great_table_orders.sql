DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
                        id	      INT(11) NOT NULL AUTO_INCREMENT,
                        user_id     INT(11) NOT NULL,
                        status_id   INT(11) NOT NULL,
                        create_at   DATETIME NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
                            REFERENCES users (id),
                        CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id)
                            REFERENCES orders_statuses (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
