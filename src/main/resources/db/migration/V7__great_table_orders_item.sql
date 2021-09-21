DROP TABLE IF EXISTS orders_item;

CREATE TABLE orders_item (
                             id	      INT(11) NOT NULL AUTO_INCREMENT,
                             product_id  INT(11) NOT NULL,
                             order_id    INT(11) NOT NULL,
                             quantity    INT NOT NULL,
                             total_price DECIMAL(8,2) NOT NULL,
                             PRIMARY KEY (id),
                             CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
                                 REFERENCES orders (id),
                             CONSTRAINT FK_PRODUCT_ID FOREIGN KEY (product_id)
                                 REFERENCES products (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;