alter table ST_PLANET add constraint FK_ST_PLANET_ON_ATMOSPHERE foreign key (ATMOSPHERE_ID) references ST_ATMOSPHERE(ID);
alter table ST_PLANET add constraint FK_ST_PLANET_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID);
create unique index IDX_ST_PLANET_UK_PICTURE_ID on ST_PLANET (PICTURE_ID) where DELETE_TS is null ;
create unique index IDX_ST_PLANET_UK_NAME on ST_PLANET (NAME) where DELETE_TS is null ;
create index IDX_ST_PLANET_ON_ATMOSPHERE on ST_PLANET (ATMOSPHERE_ID);
create index IDX_ST_PLANET_ON_PICTURE on ST_PLANET (PICTURE_ID);
