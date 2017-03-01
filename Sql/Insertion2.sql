
INSERT INTO Book (bid, title, price, category) VALUES ('b001', 'Little Prince', 20, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b002','Physics', 201, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b003','Mechanics' ,100,'Engineering');


INSERT INTO Address (id, street, province, country, zip, phone) VALUES (1, '123 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567');
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (2, '445 Avenue rd', 'ON',
'Canada', 'M1C 6K5' ,'416-123-8569');
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (3, '789 Keele St.', 'ON',
'Canada', 'K3C 9T5' ,'416-123-9568');

INSERT INTO PO (id, lname, fname, status, address) VALUES (1, 'John', 'White', 'PROCESSED', '1');
INSERT INTO PO (id, lname, fname, status, address) VALUES (2, 'Peter', 'Black', 'DENIED', '2');
INSERT INTO PO (id, lname, fname, status, address) VALUES (3, 'Andy', 'Green', 'ORDERED', '3');


INSERT INTO POItem (id, bid, price) VALUES (1, 'b001', '20');
INSERT INTO POItem (id, bid, price) VALUES (2, 'b002', '201');
INSERT INTO POItem (id, bid, price) VALUES (3, 'b003', '100');


INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b001', 'VIEW');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b001', 'CART');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12252015', 'b001', 'PURCHASE');