-- 고양이와 개가 각각 몇 마리인지 조회
-- 고양이를 개보다 먼저 조회
select ANIMAL_TYPE, count(ANIMAL_TYPE) as count
from ANIMAL_INS
group by ANIMAL_TYPE
order by ANIMAL_TYPE; 