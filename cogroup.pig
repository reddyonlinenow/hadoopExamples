A = LOAD 'emp.log' using PigStorage('|') as (cid:int, cname:chararray, cheadcnt:int, cloc:chararray);
B = COGROUP A BY cloc;
STORE B INTO '/COGRP';
