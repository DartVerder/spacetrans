-- begin ST_ATMOSPHERIC_GAS
create table ST_ATMOSPHERIC_GAS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    GAS_ID uuid not null,
    VOLUME double precision not null,
    ATMOSPHERE_ID uuid,
    --
    primary key (ID)
)^
-- end ST_ATMOSPHERIC_GAS

-- begin ST_ATMOSPHERE
create table ST_ATMOSPHERE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DESCRIPTION varchar(255),
    PRESSURE double precision,
    --
    primary key (ID)
)^
-- end ST_ATMOSPHERE
-- begin ST_GAS
create table ST_GAS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ST_GAS
-- begin ST_MOON
create table ST_MOON (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME varchar(255) not null,
    MASS double precision,
    PICTURE_ID uuid,
    --
    PLANET_ID uuid,
    ATMOSPHERE_ID uuid,
    --
    primary key (ID)
)^
-- end ST_MOON
-- begin ST_PLANET
create table ST_PLANET (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME varchar(255) not null,
    MASS double precision,
    PICTURE_ID uuid,
    --
    SEMI_MAJOR_AXIS double precision not null,
    ORBITAL_PERIOD double precision not null,
    ROTATION_PERIOD double precision not null,
    ATMOSPHERE_ID uuid,
    RINGS boolean not null,
    --
    primary key (ID)
)^
-- end ST_PLANET
-- begin ST_CARRIER
create table ST_CARRIER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ST_CARRIER
-- begin ST_CUSTOMER
create table ST_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(255) not null,
    GRADE integer not null,
    EMAIL varchar(255) not null,
    --
    primary key (ID)
)^
-- end ST_CUSTOMER
-- begin ST_DISCOUNT
create table ST_DISCOUNT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    VALUE_ decimal(19, 2) not null,
    GRADE integer not null,
    --
    primary key (ID)
)^
-- end ST_DISCOUNT
-- begin ST_SPACEPORT
create table ST_SPACEPORT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    COORDINATES_LATITUDE double precision not null,
    COORDINATES_LONGTITUDE double precision not null,
    --
    NAME varchar(255) not null,
    PLANET_ID uuid,
    MOON_ID uuid,
    IS_DEFAULT boolean,
    --
    primary key (ID)
)^
-- end ST_SPACEPORT
-- begin ST_WAYBILL
create table ST_WAYBILL (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REFERENCE varchar(255) not null,
    CREATOR_ID uuid,
    SHIPPER_ID uuid not null,
    CONSIGNEE_ID uuid not null,
    DEPARTURE_PORT_ID uuid not null,
    DESTINATION_PORT_ID uuid not null,
    CARRIER_ID uuid,
    TOTAL_CHARGE decimal(19, 2),
    --
    primary key (ID)
)^
-- end ST_WAYBILL
-- begin ST_WAYBILL_ITEM
create table ST_WAYBILL_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DIM_LENGTH double precision not null,
    DIM_WIDTH double precision not null,
    DIM_HEIGHT double precision not null,
    --
    NUMBER_ integer not null,
    NAME varchar(255) not null,
    WEIGHT double precision not null,
    CHARGE decimal(19, 2),
    WAYBILL_ID uuid,
    --
    primary key (ID)
)^
-- end ST_WAYBILL_ITEM
-- begin ST_INDIVIDUAL
create table ST_INDIVIDUAL (
    ID uuid,
    --
    FIRST_NAME varchar(255) not null,
    LAST_NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end ST_INDIVIDUAL
-- begin ST_COMPANY
create table ST_COMPANY (
    ID uuid,
    --
    REGISTRATION_ID varchar(255) not null,
    COMPANY_TYPE varchar(255) not null,
    --
    primary key (ID)
)^
-- end ST_COMPANY
-- begin ST_CARRIER_SPACEPORT_LINK
create table ST_CARRIER_SPACEPORT_LINK (
    SPACEPORT_ID uuid,
    CARRIER_ID uuid,
    primary key (SPACEPORT_ID, CARRIER_ID)
)^
-- end ST_CARRIER_SPACEPORT_LINK
