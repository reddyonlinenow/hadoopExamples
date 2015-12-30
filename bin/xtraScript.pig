A = LOAD '/Pig20/data1.log' using PigStorage('\t') as (cid:int, name:chararray, sal:int);
--DESCRIBE A;
ILLUSTRATE A;
--EXPLAIN A;



