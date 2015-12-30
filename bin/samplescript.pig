A = LOAD 'data1.log' using PigStorage('\t') as (id:int, name:chararray,sal:int);
B = LOAD 'data2.log' using PigStorage('\t') as (Id:int, Name:chararray,Sal:int);
uniData = UNION A, B;
DUMP uniData;