select ins.ANIMAL_ID, ins.ANIMAL_TYPE, ins.NAME
from ANIMAL_INS ins
join ANIMAL_OUTS outs
on ins.ANIMAL_ID = outs.ANIMAL_ID
where ins.SEX_UPON_INTAKE like "Intact%" 
and (outs.SEX_UPON_OUTCOME like "Spayed%"
    or outs.SEX_UPON_OUTCOME like "Neutered%"); 