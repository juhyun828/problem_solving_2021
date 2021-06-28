select ins.NAME, ins.DATETIME
from ANIMAL_INS ins
left outer join ANIMAL_OUTS outs
on ins.ANIMAL_ID = outs.ANIMAL_ID
where outs.DATETIME is null
order by ins.DATETIME 
limit 3;