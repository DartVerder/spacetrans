-- update ST_MOON set PLANET_ID = <default_value> where PLANET_ID is null ;
alter table ST_MOON alter column PLANET_ID set not null ;
