select NAME, count(NAME) as count
from ANIMAL_INS
group by NAME
having count(NAME) >= 2
order by NAME;