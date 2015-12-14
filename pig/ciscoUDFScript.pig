REGISTER Batch58-Pig-Concat.jar;
A = LOAD 'ciscoLogData.log' using PigStorage('|') as (logid:int,logerror:chararray,logloc:chararray,logdate:chararray);
B = FOREACH A GENERATE logid, com.pig.concat.UPPER(logerror),logloc;
DUMP B;