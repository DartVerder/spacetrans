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
    ATMOSPHERE_ID uuid not null,
    RINGS boolean not null,
    --
    primary key (ID)
);