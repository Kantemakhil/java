create table oms_owner.staff_reports_maint
(report_type			VARCHAR(12) not null,
 automatic_flag			VARCHAR(1) not null default 'N',
 length					INT4,
 length_unit			VARCHAR(12),
 create_datetime		TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP NOT NULL ,
 create_user_id         VARCHAR(32) NOT NULL ,
 modify_datetime        TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
 modify_user_id         VARCHAR(32),
 seal_flag              VARCHAR(1) 	
) tablespace tag_data;


alter table oms_owner.staff_reports_maint   
  add constraint staff_reports_maint_pk primary key (report_type)
  using index tablespace tag_indx;

comment on table oms_owner.staff_reports_maint is 'Configuration table for automatic lock on staff reports';

comment on column oms_owner.staff_reports_maint.report_type is 'To report type (codes from ref domain=STAFF_REPORT)';
comment on column oms_owner.staff_reports_maint.automatic_flag is 'Indicates automatic lock of staff report.';
comment on column oms_owner.staff_reports_maint.length is 'Indicates lock interval';
comment on column oms_owner.staff_reports_maint.length_unit is 'Indicates lock interval unit (static values DAYS,HOURS,MONTHS)';