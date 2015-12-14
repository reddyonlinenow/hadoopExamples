A = LOAD '/Pig20/emp.log' using PigStorage('|') as (cid:int, cname:chararray, cheadcnt:int, cloc:chararray);
B = GROUP A BY cloc;
C = FOREACH B GENERATE group, AVG(A.cheadcnt);
STORE C INTO '/AVG20';
