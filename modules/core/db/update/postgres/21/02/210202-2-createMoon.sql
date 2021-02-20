alter table ST_MOON add constraint FK_ST_MOON_ON_PLANET foreign key (PLANET_ID) references ST_PLANET(ID);
alter table ST_MOON add constraint FK_ST_MOON_ON_ATMOSPHERE foreign key (ATMOSPHERE_ID) references ST_ATMOSPHERE(ID);
alter table ST_MOON add constraint FK_ST_MOON_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID);
create unique index IDX_ST_MOON_UK_NAME on ST_MOON (NAME) where DELETE_TS is null ;
create unique index IDX_ST_MOON_UK_PICTURE_ID on ST_MOON (PICTURE_ID) where DELETE_TS is null ;
create index IDX_ST_MOON_ON_PLANET on ST_MOON (PLANET_ID);
create index IDX_ST_MOON_ON_ATMOSPHERE on ST_MOON (ATMOSPHERE_ID);
create index IDX_ST_MOON_ON_PICTURE on ST_MOON (PICTURE_ID);
