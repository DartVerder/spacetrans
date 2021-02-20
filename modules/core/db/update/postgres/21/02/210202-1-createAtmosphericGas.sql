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
    GAS_ID uuid,
    VOLUME double precision not null,
    ATMOSPHERE_ID uuid not null,
    --
    primary key (ID)
);