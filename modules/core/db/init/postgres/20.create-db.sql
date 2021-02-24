-- begin ST_ATMOSPHERIC_GAS
alter table ST_ATMOSPHERIC_GAS add constraint FK_ST_ATMOSPHERIC_GAS_ON_GAS foreign key (GAS_ID) references ST_GAS(ID)^
alter table ST_ATMOSPHERIC_GAS add constraint FK_ST_ATMOSPHERIC_GAS_ON_ATMOSPHERE foreign key (ATMOSPHERE_ID) references ST_ATMOSPHERE(ID)^
create index IDX_ST_ATMOSPHERIC_GAS_ON_GAS on ST_ATMOSPHERIC_GAS (GAS_ID)^
create index IDX_ST_ATMOSPHERIC_GAS_ON_ATMOSPHERE on ST_ATMOSPHERIC_GAS (ATMOSPHERE_ID)^
-- end ST_ATMOSPHERIC_GAS
-- begin ST_MOON
alter table ST_MOON add constraint FK_ST_MOON_ON_PLANET foreign key (PLANET_ID) references ST_PLANET(ID)^
alter table ST_MOON add constraint FK_ST_MOON_ON_ATMOSPHERE foreign key (ATMOSPHERE_ID) references ST_ATMOSPHERE(ID)^
alter table ST_MOON add constraint FK_ST_MOON_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID)^
create unique index IDX_ST_MOON_UK_NAME on ST_MOON (NAME) where DELETE_TS is null ^
create unique index IDX_ST_MOON_UK_PICTURE_ID on ST_MOON (PICTURE_ID) where DELETE_TS is null ^
create index IDX_ST_MOON_ON_PLANET on ST_MOON (PLANET_ID)^
create index IDX_ST_MOON_ON_ATMOSPHERE on ST_MOON (ATMOSPHERE_ID)^
create index IDX_ST_MOON_ON_PICTURE on ST_MOON (PICTURE_ID)^
-- end ST_MOON
-- begin ST_PLANET
alter table ST_PLANET add constraint FK_ST_PLANET_ON_ATMOSPHERE foreign key (ATMOSPHERE_ID) references ST_ATMOSPHERE(ID)^
alter table ST_PLANET add constraint FK_ST_PLANET_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID)^
create unique index IDX_ST_PLANET_UK_PICTURE_ID on ST_PLANET (PICTURE_ID) where DELETE_TS is null ^
create unique index IDX_ST_PLANET_UK_NAME on ST_PLANET (NAME) where DELETE_TS is null ^
create index IDX_ST_PLANET_ON_ATMOSPHERE on ST_PLANET (ATMOSPHERE_ID)^
create index IDX_ST_PLANET_ON_PICTURE on ST_PLANET (PICTURE_ID)^
-- end ST_PLANET
-- begin ST_CARRIER
create unique index IDX_ST_CARRIER_UK_NAME on ST_CARRIER (NAME) where DELETE_TS is null ^
-- end ST_CARRIER
-- begin ST_CUSTOMER
create unique index IDX_ST_CUSTOMER_UK_NAME on ST_CUSTOMER (NAME) where DELETE_TS is null ^
-- end ST_CUSTOMER
-- begin ST_SPACEPORT
alter table ST_SPACEPORT add constraint FK_ST_SPACEPORT_ON_PLANET foreign key (PLANET_ID) references ST_PLANET(ID)^
alter table ST_SPACEPORT add constraint FK_ST_SPACEPORT_ON_MOON foreign key (MOON_ID) references ST_MOON(ID)^
create unique index IDX_ST_SPACEPORT_UK_NAME on ST_SPACEPORT (NAME) where DELETE_TS is null ^
create index IDX_ST_SPACEPORT_ON_PLANET on ST_SPACEPORT (PLANET_ID)^
create index IDX_ST_SPACEPORT_ON_MOON on ST_SPACEPORT (MOON_ID)^
-- end ST_SPACEPORT
-- begin ST_WAYBILL
alter table ST_WAYBILL add constraint FK_ST_WAYBILL_ON_CREATOR foreign key (CREATOR_ID) references SEC_USER(ID)^
alter table ST_WAYBILL add constraint FK_ST_WAYBILL_ON_SHIPPER foreign key (SHIPPER_ID) references ST_CUSTOMER(ID)^
alter table ST_WAYBILL add constraint FK_ST_WAYBILL_ON_CONSIGNEE foreign key (CONSIGNEE_ID) references ST_CUSTOMER(ID)^
alter table ST_WAYBILL add constraint FK_ST_WAYBILL_ON_DEPARTURE_PORT foreign key (DEPARTURE_PORT_ID) references ST_SPACEPORT(ID)^
alter table ST_WAYBILL add constraint FK_ST_WAYBILL_ON_DESTINATION_PORT foreign key (DESTINATION_PORT_ID) references ST_SPACEPORT(ID)^
alter table ST_WAYBILL add constraint FK_ST_WAYBILL_ON_CARRIER foreign key (CARRIER_ID) references ST_CARRIER(ID)^
create index IDX_ST_WAYBILL_ON_CREATOR on ST_WAYBILL (CREATOR_ID)^
create index IDX_ST_WAYBILL_ON_SHIPPER on ST_WAYBILL (SHIPPER_ID)^
create index IDX_ST_WAYBILL_ON_CONSIGNEE on ST_WAYBILL (CONSIGNEE_ID)^
create index IDX_ST_WAYBILL_ON_DEPARTURE_PORT on ST_WAYBILL (DEPARTURE_PORT_ID)^
create index IDX_ST_WAYBILL_ON_DESTINATION_PORT on ST_WAYBILL (DESTINATION_PORT_ID)^
create index IDX_ST_WAYBILL_ON_CARRIER on ST_WAYBILL (CARRIER_ID)^
-- end ST_WAYBILL
-- begin ST_WAYBILL_ITEM
alter table ST_WAYBILL_ITEM add constraint FK_ST_WAYBILL_ITEM_ON_WAYBILL foreign key (WAYBILL_ID) references ST_WAYBILL(ID)^
create unique index IDX_ST_WAYBILL_ITEM_UK_NAME on ST_WAYBILL_ITEM (NAME) where DELETE_TS is null ^
create index IDX_ST_WAYBILL_ITEM_ON_WAYBILL on ST_WAYBILL_ITEM (WAYBILL_ID)^
-- end ST_WAYBILL_ITEM
-- begin ST_CARRIER_SPACEPORT_LINK
alter table ST_CARRIER_SPACEPORT_LINK add constraint FK_CARSPA_ON_SPACEPORT foreign key (SPACEPORT_ID) references ST_SPACEPORT(ID)^
alter table ST_CARRIER_SPACEPORT_LINK add constraint FK_CARSPA_ON_CARRIER foreign key (CARRIER_ID) references ST_CARRIER(ID)^
-- end ST_CARRIER_SPACEPORT_LINK
