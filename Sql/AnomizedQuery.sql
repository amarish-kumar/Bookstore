select acc.login, sum(poi.price) as amount_spent
from PO p, Account acc, POItem poi
where 
	p.id = poi.id and
	p.address = acc.address
group by acc.login;
	  

select * from poitem;