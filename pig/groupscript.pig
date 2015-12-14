A = LOAD 'emp.log' using PigStorage('|') as (cid:int, cname:chararray, cheadcnt:int, cloc:chararray);
B = GROUP A BY cloc;
STORE B INTO 'GROUP20';
