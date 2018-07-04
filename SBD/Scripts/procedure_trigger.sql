/*Procedure*/

create or replace function compra_ingresso()
returns void as $$
declare tupla record;
begin
	for tupla in select * from ingresso
	loop
	update copa.ingresso set quantidade = quantidade - (select sum(c.quantidade) from copa.compra c
												   where tupla.idingresso = c.idingresso )
												   where tupla.idingresso = idingresso;
	
	
	end loop;
	
end $$ language 'plpgsql';

/*Trigger*/

create or replace function att_quantidade_in()
returns trigger as $att_ingresso$
begin
		if(TG_OP = 'INSERT') then
			update copa.ingresso set quantidade = quantidade - new.quantidade where idingresso = new.idingresso;
			return NEW;
		
		elseif(TG_OP = 'DELETE') then
			update copa.ingresso set quantidade = quantidade + old.quantidade where idingresso = old.idingresso;
			return OLD;
		
		else
			update copa.ingresso set quantidade = quantidade + (old.quantidade - new.quantidade) where idingresso = new.idingresso;
			return NEW;
		end if;
end $att_ingresso$ language 'plpgsql';

create trigger att_ingresso after insert or delete or update of quantidade on compra for each row execute procedure att_quantidade_in();

