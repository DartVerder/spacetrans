update ST_INDIVIDUAL set FIRST_NAME = '' where FIRST_NAME is null ;
alter table ST_INDIVIDUAL alter column FIRST_NAME set not null ;
update ST_INDIVIDUAL set LAST_NAME = '' where LAST_NAME is null ;
alter table ST_INDIVIDUAL alter column LAST_NAME set not null ;
