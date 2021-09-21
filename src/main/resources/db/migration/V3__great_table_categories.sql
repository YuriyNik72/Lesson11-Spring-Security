DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
                            id	              INT(11) NOT NULL AUTO_INCREMENT,
                            title               VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;