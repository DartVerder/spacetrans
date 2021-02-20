alter table ST_COORDINATES add column LONGTITUDE double precision ^
update ST_COORDINATES set LONGTITUDE = 0 where LONGTITUDE is null ;
alter table ST_COORDINATES alter column LONGTITUDE set not null ;
alter table ST_COORDINATES add column LATITUDE double precision ^
update ST_COORDINATES set LATITUDE = 0 where LATITUDE is null ;
alter table ST_COORDINATES alter column LATITUDE set not null ;
