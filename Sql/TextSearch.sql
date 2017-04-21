CREATE VIRTUAL TABLE BookSearch USING fts4(bid, title, category, price, quan);

INSERT INTO BookSearch SELECT bid, title, category, price, quan FROM Book;

SELECT * FROM BookSearch WHERE title MATCH '*pr*'
union
SELECT * FROM BookSearch WHERE category MATCH '*pr*'
union
SELECT * FROM BookSearch WHERE bid MATCH '*pr*';

drop table BookSearch;


