update ST_SPACEPORT set LATITUDE = 0 where LATITUDE is null ;
alter table ST_SPACEPORT alter column LATITUDE set not null ;
update ST_SPACEPORT set LONGTITUDE = 0 where LONGTITUDE is null ;
alter table ST_SPACEPORT alter column LONGTITUDE set not null ;
