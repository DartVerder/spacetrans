create table ST_CARRIER_SPACEPORT_LINK (
    SPACEPORT_ID uuid,
    CARRIER_ID uuid,
    primary key (SPACEPORT_ID, CARRIER_ID)
);
