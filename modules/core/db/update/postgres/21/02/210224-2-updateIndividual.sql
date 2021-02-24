alter table ST_INDIVIDUAL rename column deleted_by to deleted_by__u01458 ;
alter table ST_INDIVIDUAL rename column delete_ts to delete_ts__u53589 ;
alter table ST_INDIVIDUAL rename column updated_by to updated_by__u57498 ;
alter table ST_INDIVIDUAL rename column update_ts to update_ts__u44817 ;
alter table ST_INDIVIDUAL rename column created_by to created_by__u89033 ;
alter table ST_INDIVIDUAL rename column create_ts to create_ts__u70399 ;
alter table ST_INDIVIDUAL rename column version to version__u96739 ;
alter table ST_INDIVIDUAL alter column version__u96739 drop not null ;
