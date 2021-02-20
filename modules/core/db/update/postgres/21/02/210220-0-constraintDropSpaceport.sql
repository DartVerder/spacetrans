alter table st_carrier_spaceport_link drop constraint FK_CARSPA_ON_SPACEPORT ;
alter table st_waybill drop constraint FK_ST_WAYBILL_ON_DEPARTURE_PORT ;
alter table st_waybill drop constraint FK_ST_WAYBILL_ON_DESTINATION_PORT ;
