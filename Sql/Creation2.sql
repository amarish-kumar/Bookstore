





/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
*/
CREATE TABLE Book (
bid VARCHAR(20) NOT NULL,
title VARCHAR(60) NOT NULL,
price INT NOT NULL,
category ENUM('Science','Fiction','Engineering') NOT NULL,
quan INT NOT NULL,
PRIMARY KEY(bid)
);

/* Address
* id: address id
*
*/
CREATE TABLE Address (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
phone VARCHAR(20),
PRIMARY KEY(id)
);
/* Account
* id: login id
* address: address of the account
* fname: first name
* lname: last name
* pass: password of account
* 
*/
CREATE TABLE Account (
login VARCHAR(20) NOT NULL,
address INT UNSIGNED NOT NULL,
fname VARCHAR(20) NOT NULL,
lname VARCHAR(20) NOT NULL,
pass VARCHAR(20) NOT NULL,
PRIMARY KEY(login,address),
FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);




/* visit to website
* day: date
* bid: unique identifier of Book
* eventtype: status of purchase
*/
CREATE TABLE VisitEvent (
day varchar(8) NOT NULL,
bid varchar(20) not null REFERENCES Book.bid,
eventtype ENUM('VIEW','CART','PURCHASE') NOT NULL,
FOREIGN KEY(bid) REFERENCES Book(bid)
);



/* Purchase Order
* lname: last name
* fname: first name
* id: purchase order id
* status: status of purchase
*/
CREATE TABLE PO (
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
month int NOT NULL,
lname VARCHAR(20) NOT NULL,
fname VARCHAR(20) NOT NULL,
status ENUM('ORDERED','PROCESSED','DENIED') NOT NULL,
address INT UNSIGNED NOT NULL,
PRIMARY KEY(id),
INDEX (address),
FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);

/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
*/
CREATE TABLE POItem (
id INT UNSIGNED NOT NULL,
bid VARCHAR(20) NOT NULL,
quan INT UNSIGNED NOT NULL,
price INT UNSIGNED NOT NULL,
PRIMARY KEY(id,bid),
INDEX (id),
FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
INDEX (bid),
FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);


