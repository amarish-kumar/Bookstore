select t.bid, t.title, t.category, t.price, max(number_sold) as most_sold
from (select b.bid, b.title, b.category, b.price, sum(poi.quan) as number_sold
		from PO p, POItem poi, Book b
		where p.id = poi.id and
	 		  poi.bid = b.bid
		group by b.bid, b.title, b.category, b.price) t;
