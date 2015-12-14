A = LOAD 'emp.log' using PigStorage('|') as (cid:int, cname:chararray, cheadcnt:int, cloc:chararray);
B = GROUP A BY cloc;
C = FOREACH B GENERATE group, FLATTEN(A);
STORE C INTO 'FLATTEN';
