update ST_COMPANY set REGISTRATION_ID = '' where REGISTRATION_ID is null ;
alter table ST_COMPANY alter column REGISTRATION_ID set not null ;
update ST_COMPANY set COMPANY_TYPE = '' where COMPANY_TYPE is null ;
alter table ST_COMPANY alter column COMPANY_TYPE set not null ;
