select b.bid, b.title, b.category, b.price, sum(poi.quan) as number_sold
		from PO p, POItem poi, Book b
		where p.id = poi.id and
	 		  poi.bid = b.bid
		group by b.bid, b.title, b.category, b.price;