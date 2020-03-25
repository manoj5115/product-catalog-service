DROP TABLE IF EXISTS ecom_product;
 
CREATE TABLE ecom_product (
  sku INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price INT NOT NULL,
  brand_id INT NOT NULL,
  seller_id INT NOT NULL,
  cat_id INT NOT NULL 
);
 
INSERT INTO ecom_product (sku, name, price, brand_id, seller_id, cat_id) VALUES
  (101, 'A-1 Slimmed Formal Shirt', 1900, 1, 1, 1),
  (102, 'Classic Casual Shirt', 1800, 2, 2, 1),
  (103, 'Ultra Slim Fit Trouser', 4000, 2, 1, 2),
  (104, 'Allen Solly Formal Trouser', 3000, 3, 1, 2),
  (105, 'Allen Solly White Trouser', 2000, 3, 2, 2),
  (106, '511 Slim Fit Jeans', 2400, 4, 2, 2),
  (107, 'Denim Fit Jeans', 2300, 4, 1, 2),
  (108, 'Classic Denim Jeans', 3500, 4, 1, 2),
  (109, 'Mens leather shoes', 4500, 5, 1, 3),
  (110, 'Black formal shoes', 4600, 6, 2, 3),
  (111, 'Formal lace up shoes', 4600, 7, 2, 3),
  (112, 'Air Zoom Sports Shoes', 5000, 6, 2, 3),
  (113, 'Casual Sports Shoes',  4800, 7, 2, 3);

DROP TABLE IF EXISTS ecom_brand;
 
CREATE TABLE ecom_brand (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);
 
INSERT INTO ecom_brand (id, name) VALUES
  (1, 'Hilfiger'),
  (2, 'Van Heusen'),
  (3, 'Allen Solly'),
  (4, 'Madewell'),
  (5, 'Nike'),
  (6, 'Adidas'),
  (7, 'Bata');


DROP TABLE IF EXISTS ecom_seller;
 
CREATE TABLE ecom_seller (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  location VARCHAR(150) NOT NULL
);
 
INSERT INTO ecom_seller (id, name, location) VALUES
  (1, 'WS Retail', 'Mumbai'),
  (2, 'RetailNet', 'Bangalore');


DROP TABLE IF EXISTS ecom_category;
 
CREATE TABLE ecom_category (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cat_name VARCHAR(200) NOT NULL,
  cat_desc VARCHAR(350) NOT NULL	
);

INSERT INTO ecom_category (id, cat_name, cat_desc) VALUES
  (1, 'Shirts Top Brands', 'Mens Shirts'),
  (2, 'Pants Top Brands', 'Mens Pants'),
  (3, 'Shoes Popular Brands', 'Mens Shoes');


DROP TABLE IF EXISTS ecom_category_attributes;
 
CREATE TABLE ecom_category_attributes (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  attr_name VARCHAR(250) NOT NULL,
  attr_value VARCHAR(150) NOT NULL,
  cat_id INT NOT NULL	
);


INSERT INTO ecom_category_attributes (id, attr_name, attr_value, cat_id) VALUES
  (1, 'Color', 'Blue', 1),
  (2, 'Size', 'M', 1),
  (3, 'Sleeve', 'Full', 1),
  (4, 'Neck', 'Collar', 1),
  (5, 'Color', 'Black', 2),
  (6, 'Waist', '34', 2),
  (7, 'Sole', 'Rubber', 3),
  (8, 'Material', 'Leather', 3),
  (9, 'Size', '40', 3);


DROP TABLE IF EXISTS ecom_stocks;
 
CREATE TABLE ecom_stocks (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  seller_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL	
);

INSERT INTO ecom_stocks (id, seller_id, product_id, quantity) VALUES
  (1, 1, 101, 2),
  (2, 1, 102, 2),
  (3, 2, 103, 4),
  (4, 2, 104, 12),
  (5, 1, 105, 6),
  (6, 1, 106, 0),
  (7, 2, 107, 7),
  (8, 2, 108, 9),
  (9, 2, 109, 1);