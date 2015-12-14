REGISTER Batch58-pig-epoch.jar;
iniData = LOAD 'epochTimeData.log' as (timeData:chararray);
convertedData = FOREACH iniData GENERATE timeData, com.pig.epochusecase.Epoch(timeData);
DUMP convertedData;