 SELECT b.title, poi.bid, poi.quan, poi.price
	FROM PO p, POItem poi, Book b
	WHERE p.id= 4 and
	p.id = poi.id and
	poi.bid = b.bid and
	p.status = 'PROCESSED';
	
select * from poitem;

select * from po;

select * from address;

select poi.id, p.fname, p.lname 
from POItem poi, PO p
 WHERE poi.bid ='b001' and
	p.id = poi.id;

select p.address 
		from po p 
		where p.id = 5;

select *
from (select p.address 
		from po p 
		where p.id = 5) as t, address a
where t.address = a.id;

select * from account;

			        