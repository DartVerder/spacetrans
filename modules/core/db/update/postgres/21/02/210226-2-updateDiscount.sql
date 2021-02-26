update ST_DISCOUNT set GRADE = 10 where GRADE is null ;
alter table ST_DISCOUNT alter column GRADE set not null ;
