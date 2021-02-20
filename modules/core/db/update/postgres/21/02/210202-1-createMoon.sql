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
);