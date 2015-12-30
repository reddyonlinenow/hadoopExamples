A = Load 'token.log' using PigStorage('\n') as (line:chararray);
B = FOREACH A GENERATE TOKENIZE(line);
DUMP B;