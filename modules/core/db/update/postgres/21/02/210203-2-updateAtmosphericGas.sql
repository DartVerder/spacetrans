-- update ST_ATMOSPHERIC_GAS set GAS_ID = <default_value> where GAS_ID is null ;
alter table ST_ATMOSPHERIC_GAS alter column GAS_ID set not null ;
