update ST_CUSTOMER set GRADE = 10 where GRADE is null ;
alter table ST_CUSTOMER alter column GRADE set not null ;
