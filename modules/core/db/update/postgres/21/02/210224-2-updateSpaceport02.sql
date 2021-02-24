alter table ST_SPACEPORT rename column longtitude to longtitude__u26072 ;
alter table ST_SPACEPORT alter column longtitude__u26072 drop not null ;
alter table ST_SPACEPORT rename column latitude to latitude__u17690 ;
alter table ST_SPACEPORT alter column latitude__u17690 drop not null ;
alter table ST_SPACEPORT add column COORDINATES_LONGTITUDE double precision ^
update ST_SPACEPORT set COORDINATES_LONGTITUDE = 0 where COORDINATES_LONGTITUDE is null ;
alter table ST_SPACEPORT alter column COORDINATES_LONGTITUDE set not null ;
alter table ST_SPACEPORT add column COORDINATES_LATITUDE double precision ^
update ST_SPACEPORT set COORDINATES_LATITUDE = 0 where COORDINATES_LATITUDE is null ;
alter table ST_SPACEPORT alter column COORDINATES_LATITUDE set not null ;
