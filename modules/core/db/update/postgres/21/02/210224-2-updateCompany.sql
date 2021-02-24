alter table ST_COMPANY rename column deleted_by to deleted_by__u05167 ;
alter table ST_COMPANY rename column delete_ts to delete_ts__u40795 ;
alter table ST_COMPANY rename column updated_by to updated_by__u19061 ;
alter table ST_COMPANY rename column update_ts to update_ts__u05833 ;
alter table ST_COMPANY rename column created_by to created_by__u25732 ;
alter table ST_COMPANY rename column create_ts to create_ts__u44642 ;
alter table ST_COMPANY rename column version to version__u49819 ;
alter table ST_COMPANY alter column version__u49819 drop not null ;
