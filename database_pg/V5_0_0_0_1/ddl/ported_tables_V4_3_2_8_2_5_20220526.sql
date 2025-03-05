DROP TABLE  oms_owner.abc_trust_report_tmp_jn;

-----------------------------------------------------------------------------------------------
------------------------------ 3.2.0.0 --------------------------------------------------------
-----------------------------------------------------------------------------------------------


--Community Fees Part 1
alter table oms_owner.CASELOAD_DEDUCTION_PROFILES	add FREQUENCY_TYPE VARCHAR(12);
alter table oms_owner.CASELOAD_DEDUCTION_PROFILES	add FREQUENCY_CODE VARCHAR(12);
alter table oms_owner.CASELOAD_DEDUCTION_PROFILES	add DAY_OF_MONTH bigint;
alter table oms_owner.CASELOAD_DEDUCTION_PROFILES	add NON_BILLABLE_STATUS VARCHAR(1) default 'N' not null;
alter table oms_owner.CASELOAD_DEDUCTION_PROFILES	add BACK_BILL VARCHAR(1);

comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.FREQUENCY_TYPE is 'Frequence type [domain = FEE_ACT_FREQ (ex:recurrent/one time)]';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.FREQUENCY_CODE is 'Frequency code  when frequency type is reccuring [domain = RECUR_FREQ (ex: monthly /daily)]';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.DAY_OF_MONTH is 'Day of the month';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.NON_BILLABLE_STATUS is 'Indicates if the status of the offender fee account will be suspended or completed';
comment on column oms_owner.CASELOAD_DEDUCTION_PROFILES.BACK_BILL is 'Indicates if back billing is permitted';

 CREATE TABLE oms_owner.off_fee_account_profile
   (	offender_fee_id bigint NOT NULL ,
	offender_book_id bigint NOT NULL ,
	caseload_id VARCHAR(6) NOT NULL ,
	fee_code VARCHAR(6) NOT NULL ,
	odp int NOT NULL ,
	amount bigint NOT NULL ,
	day_of_month int,
	start_date DATE NOT NULL ,
	effective_date DATE,
	expiry_date DATE,
	fee_act_status VARCHAR(12) NOT NULL ,
	status_effective_date DATE NOT NULL ,
	info_number VARCHAR(15),
	service_date DATE,
	comment_text VARCHAR(240),
	create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	create_user_id VARCHAR(32)NOT NULL ,
	modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modify_user_id VARCHAR(32),
	seal_flag VARCHAR(1),
	 CONSTRAINT off_fee_account_profile_pk PRIMARY KEY (offender_fee_id),
	 CONSTRAINT off_fee_account_profile_fk1 FOREIGN KEY (offender_book_id)
	  REFERENCES oms_owner.offender_bookings (offender_book_id) ,
	 CONSTRAINT off_fee_account_profile_fk2 FOREIGN KEY (caseload_id, fee_code)
	  REFERENCES oms_owner.caseload_deduction_profiles (caseload_id, deduction_type) )
  TABLESPACE tag_data;
  
  create index off_fee_account_profile_ni1 on off_fee_account_profile (caseload_id, fee_code) tablespace tag_indx;
  
  comment on table oms_owner.OFF_FEE_ACCOUNT_PROFILE is 'Offender fee account details';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.OFFENDER_FEE_ID is 'Unique fee account record id [existing sequence=DEDUCTION_ID ]';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.CASELOAD_ID is 'Caseload id as per Maintain Fee Accounts Profile (OCMFAPRO)';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.FEE_CODE is 'Deduction type as per Maintain Fee Accounts Profile (OCMFAPRO)';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.ODP is 'Obligation priority';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.AMOUNT is 'Offender fee account amount'; 
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.DAY_OF_MONTH is 'Billing day';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.START_DATE is 'Fee Account start date ';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EFFECTIVE_DATE is 'Effective date of the account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EXPIRY_DATE is 'Expiry date of an fee account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.FEE_ACT_STATUS is 'Fee account status';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.STATUS_EFFECTIVE_DATE is 'Fee account status effective date';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.INFO_NUMBER is 'Record docket / info number';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.SERVICE_DATE is 'Service date ';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.COMMENT_TEXT is 'The comment text of the Fee Account';

  
  CREATE TABLE oms_owner.off_fee_account_overrides
   (	OFF_FEE_OVERRIDE_ID bigint,
	OFFENDER_FEE_ID bigint NOT NULL ,
	OVERRIDE_TYPE VARCHAR(12) NOT NULL ,
	OVERRIDE_START_DATE DATE NOT NULL ,
	OVERRIDE_END_DATE DATE,
	OVERRIDE_AMOUNT bigint NOT NULL ,
	COMMENT_TEXT VARCHAR(240),
	PRIORITY_INDICATOR int,
	ADDED_BY_STAFF_ID bigint NOT NULL ,
	ADDED_DATE DATE NOT NULL ,
	create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	create_user_id VARCHAR(32)NOT NULL ,
	modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modify_user_id VARCHAR(32),
	SEAL_FLAG VARCHAR(1),
	 CONSTRAINT OFF_FEE_ACCOUNT_OVERRIDES_PK PRIMARY KEY (OFF_FEE_OVERRIDE_ID),
	 CONSTRAINT OFF_FEE_ACCOUNT_OVERRIDES_FK1 FOREIGN KEY (OFFENDER_FEE_ID)
	  REFERENCES OMS_OWNER.OFF_FEE_ACCOUNT_PROFILE (OFFENDER_FEE_ID) 
   )
  TABLESPACE TAG_DATA;
  
  comment on table oms_owner.OFF_FEE_ACCOUNT_OVERRIDES is 'Offender fee account override details';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OFF_FEE_OVERRIDE_ID is 'Unique record identifier [sequence=OFF_FEE_OVERRIDE_ID]';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OFFENDER_FEE_ID is 'Offender fee account id';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_TYPE is 'Override type [domain=CF_FOV_TYP]';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_START_DATE is 'Start date of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_END_DATE is 'End date of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.OVERRIDE_AMOUNT is 'Amount of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.COMMENT_TEXT  is 'Comment text of the override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.PRIORITY_INDICATOR is 'Priority of the fee override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.ADDED_BY_STAFF_ID is 'The staff who created the override';
comment on column oms_owner.OFF_FEE_ACCOUNT_OVERRIDES.ADDED_DATE is 'The date and time when the override was created';
  
  CREATE TABLE oms_owner.off_fee_account_profile_hty
   (	OFFENDER_FEE_ID bigint NOT NULL ,
	RECORD_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	AMOUNT bigint NOT NULL ,
	DAY_OF_MONTH int,
	START_DATE DATE NOT NULL ,
	EFFECTIVE_DATE DATE,
	EXPIRY_DATE DATE,
	FEE_ACT_STATUS VARCHAR(12) NOT NULL ,
	STATUS_EFFECTIVE_DATE DATE NOT NULL ,
	CREATE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	CREATE_USER_ID VARCHAR(32) NOT NULL ,
	MODIFY_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	MODIFY_USER_ID VARCHAR(32),
	SEAL_FLAG VARCHAR(1),
	COMMENT_TEXT VARCHAR(240), 
	 CONSTRAINT OFF_FEE_ACCOUNT_PROF_HTY_FK1 FOREIGN KEY (OFFENDER_FEE_ID)
	  REFERENCES OMS_OWNER.OFF_FEE_ACCOUNT_PROFILE (OFFENDER_FEE_ID) 
   )
  TABLESPACE TAG_DATA;
  
  create index OFF_FEE_ACCOUNT_PROF_HTY_NI1 on oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY (OFFENDER_FEE_ID) tablespace tag_indx;
  
  
  comment on table oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY is 'History of offender fee account profile for items that affect billing or reporting';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY.OFFENDER_FEE_ID is 'Unique fee account record id';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE_HTY.RECORD_DATETIME is 'Date and time when the history record was created';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.AMOUNT is 'Offender fee account amount'; 
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.DAY_OF_MONTH is 'Billing day';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.START_DATE is 'Fee Account start date ';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EFFECTIVE_DATE is 'Effective date of the account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.EXPIRY_DATE is 'Expiry date of an fee account';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.FEE_ACT_STATUS is 'Fee account status';
comment on column oms_owner.OFF_FEE_ACCOUNT_PROFILE.STATUS_EFFECTIVE_DATE is 'Fee account status effective date';

  
  
  CREATE TABLE oms_owner.off_fee_ded_beneficiaries
   (	OFF_FEE_DED_BENEFICIARY_ID bigint NOT NULL ,
	OFFENDER_FEE_ID bigint NOT NULL ,
	PERSON_ID bigint,
	CORPORATE_ID bigint,
	PRIORITY int NOT NULL ,
	AMOUNT bigint NOT NULL ,
	PERCENT bigint NOT NULL ,
	CREATE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	CREATE_USER_ID VARCHAR(32)NOT NULL ,
	MODIFY_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	MODIFY_USER_ID VARCHAR(32),
	SEAL_FLAG VARCHAR(1),
	 CONSTRAINT OFF_FEE_DED_BENFICIARIES_PK PRIMARY KEY (OFF_FEE_DED_BENEFICIARY_ID),
	 CONSTRAINT OFF_FEE_DED_BENEFICIARIES_UK1 UNIQUE (OFFENDER_FEE_ID, PERSON_ID, CORPORATE_ID),
	 CONSTRAINT OFF_FEE_DED_BENEFICIARIES_FK1 FOREIGN KEY (OFFENDER_FEE_ID)
	  REFERENCES OMS_OWNER.OFF_FEE_ACCOUNT_PROFILE (OFFENDER_FEE_ID) ,
	 CONSTRAINT OFF_FEE_DED_BENEFICIARIES_FK2 FOREIGN KEY (CORPORATE_ID)
	  REFERENCES OMS_OWNER.CORPORATES (CORPORATE_ID) ,
	 CONSTRAINT OFF_FEE_DED_BENEFICIARIES_FK3 FOREIGN KEY (PERSON_ID)
	  REFERENCES OMS_OWNER.PERSONS (PERSON_ID) 
   )
  TABLESPACE TAG_DATA;
  
  comment on table  oms_owner.OFF_FEE_DED_BENEFICIARIES is 'Beneficiary details for the offender fee account';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.OFF_FEE_DED_BENEFICIARY_ID is 'Unique record id [existing sequence=BENEFICIARY_ID]'; 
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.OFFENDER_FEE_ID is 'Offender fee account id';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.PERSON_ID is 'For person beneficiaries, uniquely identifies the person';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.CORPORATE_ID is 'For corporate beneficiaries, uniquely identifies the corporation';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.PRIORITY is 'The priority for the collection against the beneficiary in the context of multiple beneficiaries';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.AMOUNT is 'The Amount to be collected for the beneficiary';
comment on column oms_owner.OFF_FEE_DED_BENEFICIARIES.PERCENT is 'Percentage of the amount collected to be applied to the beneficiary';

  
  
  CREATE TABLE oms_owner.off_fee_ded_receipts
   (	OFF_FEE_DED_RECEIPT_ID bigint,
	OFFENDER_FEE_ID bigint NOT NULL ,
	RECEIPT_TXN_TYPE VARCHAR(6) NOT NULL ,
	RECEIPT_PERCENT bigint,
	FLAT_RATE bigint,
	MINIMUM_TRUST_BALANCE_FLAG VARCHAR(1),
	LIST_SEQ bigint,
	CREATE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	CREATE_USER_ID VARCHAR(32)NOT NULL ,
	MODIFY_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	MODIFY_USER_ID VARCHAR(32),
	SEAL_FLAG VARCHAR(1),
	 CONSTRAINT OFF_FEE_DED_RECEIPTS_PK PRIMARY KEY (OFF_FEE_DED_RECEIPT_ID),
	 CONSTRAINT OFF_FEE_DED_RECEIPTS_UK1 UNIQUE (OFFENDER_FEE_ID, RECEIPT_TXN_TYPE),
	 CONSTRAINT OFF_FEE_DED_RECEIPTS_FK1 FOREIGN KEY (OFFENDER_FEE_ID)
	  REFERENCES OMS_OWNER.OFF_FEE_ACCOUNT_PROFILE (OFFENDER_FEE_ID) ,
	 CONSTRAINT OFF_FEE_DED_RECEIPTS_FK2 FOREIGN KEY (RECEIPT_TXN_TYPE)
	  REFERENCES OMS_OWNER.TRANSACTION_TYPES (TXN_TYPE) 
   )
  TABLESPACE TAG_DATA;
  
  comment on table oms_owner.OFF_FEE_DED_RECEIPTS is 'Offender fee account deduction details';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.OFF_FEE_DED_RECEIPT_ID is 'Unique record id [sequence=OFF_FEE_DED_RECEIPT_ID]';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.OFFENDER_FEE_ID is 'Offender fee account id';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.RECEIPT_TXN_TYPE is 'The receipt type, as defined in TRANSACTION_TYPES table with TXN_USAGE = R';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.RECEIPT_PERCENT is 'The percentage of the receipt amount that will be deducted';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.FLAT_RATE is 'The flat rate amount that will be collected against the receipt amount';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.MINIMUM_TRUST_BALANCE_FLAG is 'Minimum trust balance flag';
comment on column oms_owner.OFF_FEE_DED_RECEIPTS.LIST_SEQ is 'Controls the screen display order';
  
  CREATE TABLE OMS_OWNER.OFF_INTAKE_REVIEW_QUEUE
   (	OFFENDER_BOOK_ID bigint NOT NULL ,
	QUEUE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	AGY_LOC_ID_FROM VARCHAR(6) NOT NULL ,
	AGY_LOC_ID_TO VARCHAR(6) NOT NULL ,
	INTAKE_REASON VARCHAR(12) NOT NULL ,
	INTAKE_DATE DATE NOT NULL ,
	SUP_STATUS VARCHAR(12),
	SUP_STATUS_DATETIME DATE,
	ACCEPTED_FLAG VARCHAR(1) DEFAULT 'N' NOT NULL ,
	ACCEPTED_DATE DATE,
	CREATE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	CREATE_USER_ID VARCHAR(32) NOT NULL ,
	MODIFY_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	MODIFY_USER_ID VARCHAR(32),
	SEAL_FLAG VARCHAR(1),
	 CONSTRAINT OFF_INTAKE_REVIEW_QUEUE_PK PRIMARY KEY (OFFENDER_BOOK_ID, QUEUE_DATETIME),
	 CONSTRAINT OFF_INTAKE_REVIEW_QUEUE_FK1 FOREIGN KEY (OFFENDER_BOOK_ID)
	  REFERENCES OMS_OWNER.OFFENDER_BOOKINGS (OFFENDER_BOOK_ID) 
   )
  TABLESPACE TAG_DATA;
  
  comment on table oms_owner.OFF_INTAKE_REVIEW_QUEUE is 'Offender intake review queue';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking'; 
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.QUEUE_DATETIME is 'The date and time on which the offender was added to the intake review queue';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.AGY_LOC_ID_FROM is 'The community agency location from where the inmate is admitted';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.AGY_LOC_ID_TO is 'The community agency location to which the inmate is admitted';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.INTAKE_REASON is 'Intake reason [ domain=INTAKE_RSN]';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.INTAKE_DATE is 'The community intake date'; 
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.SUP_STATUS is 'Supervision status [ domain=SUP_STATUS, parent code = B denotes billable supervision]';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.SUP_STATUS_DATETIME is 'Supervision status start datetime';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.ACCEPTED_FLAG is 'Indicates if the intake was accepted after review(Y/N values)';
comment on column oms_owner.OFF_INTAKE_REVIEW_QUEUE.ACCEPTED_DATE is 'The date the review was accepted';

  
  
  CREATE TABLE oms_owner.off_supervision_details
   (	OFFENDER_BOOK_ID bigint NOT NULL ,
	LONGEST_SUPV_EXP_DATE DATE NOT NULL ,
	CREATE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
	CREATE_USER_ID VARCHAR(32) NOT NULL ,
	MODIFY_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	MODIFY_USER_ID VARCHAR(32),
	SEAL_FLAG VARCHAR(1),
	 CONSTRAINT OFF_SUPERVISION_DETAILS_UK1 UNIQUE (OFFENDER_BOOK_ID),
	 CONSTRAINT OFF_SUPERVISION_DETAILS_FK1 FOREIGN KEY (OFFENDER_BOOK_ID)
	  REFERENCES OMS_OWNER.OFFENDER_BOOKINGS (OFFENDER_BOOK_ID) 
   )
  TABLESPACE TAG_DATA;
  
  comment on table oms_owner.OFF_SUPERVISION_DETAILS  is 'Offender supervision details';
comment on column oms_owner.OFF_SUPERVISION_DETAILS.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking';
comment on column oms_owner.OFF_SUPERVISION_DETAILS.LONGEST_SUPV_EXP_DATE is 'The longest supervision expiry date';

  
  
  CREATE TABLE oms_owner.off_supervision_sts_hty 
   (	OFFENDER_SUP_ID bigint NOT NULL , 
	OFFENDER_BOOK_ID bigint NOT NULL , 
	SUP_STATUS VARCHAR(12) NOT NULL , 
	START_DATETIME TIMESTAMP NOT NULL , 
	END_DATETIME TIMESTAMP, 
	COMMENT_TEXT VARCHAR(240), 
	ERROR_FLAG VARCHAR(1) DEFAULT 'N' NOT NULL , 
	CREATE_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL , 
	CREATE_USER_ID VARCHAR(32) NOT NULL , 
	MODIFY_DATETIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
	MODIFY_USER_ID VARCHAR(32), 
	SEAL_FLAG VARCHAR(1), 
	 CONSTRAINT OFF_SUPERVISION_STS_HTY_PK PRIMARY KEY (OFFENDER_SUP_ID), 
	 CONSTRAINT OFF_SUPERVISION_STS_HTY_FK1 FOREIGN KEY (OFFENDER_BOOK_ID)
	  REFERENCES OMS_OWNER.OFFENDER_BOOKINGS (OFFENDER_BOOK_ID) 
   )
  TABLESPACE TAG_DATA;
  
comment on table oms_owner.OFF_SUPERVISION_STS_HTY is 'Offender supervision status history';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.OFFENDER_SUP_ID is 'Unique record id [sequence=OFFENDER_SUP_ID]';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.OFFENDER_BOOK_ID is 'The system generated identifier for an offender booking';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.SUP_STATUS is 'Supervision status [ domain=SUP_STATUS, parent code = B denotes billable supervision]';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.START_DATETIME is 'Supervision start date and time (hour and minutes only)';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.END_DATETIME is 'Supervision end date and time (hour and minutes only)'; 
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.COMMENT_TEXT is 'The comment text of the supervision';
comment on column oms_owner.OFF_SUPERVISION_STS_HTY.ERROR_FLAG is 'The record is marked as error instead of deleting it';


--System labels 

ALTER TABLE oms_owner.system_labels ADD CONSTRAINT SYSTEM_LABELS_UK1 unique (module_name, msg_key);

-----------------------------------------------------------------------------------------------
-----------------------------------3.2.2.0------some incorporated------------------------------
-----------------------------------------------------------------------------------------------

--OIC penalties
ALTER TABLE oms_owner.OFFENDER_OIC_APPEAL_PENALTIES ALTER COLUMN STATUS TYPE VARCHAR(12);

-----------------------------------------------------------------------------------------------
----------------------------------3.2.3.0---some incorporated----------------------------------
-----------------------------------------------------------------------------------------------

DROP VIEW oms_owner.v_offender_profile_statistics ;

--Offender Profiles
ALTER TABLE oms_owner.OFFENDER_PROFILE_DETAILS ALTER COLUMN PROFILE_CODE TYPE VARCHAR(1000);

CREATE OR REPLACE VIEW oms_owner.v_offender_profile_statistics (ag_loc, profile_type, profile_code, male_count, female_count, other_count, total_count) AS SELECT
       OFF_BK.AGY_LOC_ID
     , OFF_PD.PROFILE_TYPE
     , OFF_PD.PROFILE_CODE
     , SUM(CASE WHEN OFF.SEX_CODE='M' THEN 1  ELSE 0 END )
     , SUM(CASE WHEN OFF.SEX_CODE='F' THEN 1  ELSE 0 END )
     , SUM(CASE WHEN OFF.SEX_CODE='F' THEN 0 WHEN OFF.SEX_CODE='M' THEN  0  ELSE 1 END )
     , COUNT(*)
 FROM
  OFFENDER_BOOKINGS OFF_BK,
  OFFENDERS OFF,
  OFFENDER_PROFILE_DETAILS OFF_PD
  WHERE   OFF_BK.ACTIVE_FLAG = 'Y' AND
   OFF_BK.OFFENDER_BOOK_ID = OFF_PD.OFFENDER_BOOK_ID AND
          OFF_BK.OFFENDER_ID  = OFF.OFFENDER_ID
  GROUP BY OFF_BK.AGY_LOC_ID, OFF_PD.PROFILE_TYPE,OFF_PD.PROFILE_CODE; 


-----------------------------------------------------------------------------------------------
----------------------------------3.2.4.0------------------------------------------------------
-----------------------------------------------------------------------------------------------

--already incorporated

-----------------------------------------------------------------------------------------------
----------------------------------3.2.5.0------------------------------------------------------
-----------------------------------------------------------------------------------------------

--Automation
CREATE table oms_owner.AUTOMATION_TEAMS
(
TEAM_ID  bigint  constraint AUTOMATION_TEAMS_PK primary key,
TEAM_CODE varchar(20),
DESCRIPTION varchar(40),
ACTIVE_FLAG varchar(1),
EXPIRY_DATE DATE,
create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
create_user_id VARCHAR(32)NOT NULL ,
modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modify_user_id VARCHAR(32),
SEAL_FLAG  VARCHAR(1),
   CONSTRAINT AUTOMATION_TEAMS_UK1 UNIQUE (TEAM_CODE)
) 
TABLESPACE TAG_DATA;


CREATE table oms_owner.TEAM_AGENCY_LOC
(
TEAM_ID  bigint,
AGY_LOC_ID varchar(20),
ACTIVE_FLAG varchar(1),
create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
create_user_id VARCHAR(32)NOT NULL ,
modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modify_user_id VARCHAR(32),
SEAL_FLAG  VARCHAR(1), 
	 CONSTRAINT TEAM_AGENCY_LOC_PK PRIMARY KEY (TEAM_ID, AGY_LOC_ID), 
	 CONSTRAINT TEAM_AGENCY_LOC_FK1 FOREIGN KEY (TEAM_ID)
	  REFERENCES AUTOMATION_TEAMS (TEAM_ID),
     CONSTRAINT TEAM_AGENCY_LOC_FK2 FOREIGN KEY (AGY_LOC_ID)
	  REFERENCES AGENCY_LOCATIONS (AGY_LOC_ID) 
) 
TABLESPACE TAG_DATA;




CREATE table oms_owner.TEAM_STAFF_MEMBERS
(
TEAM_MEMBER_ID  bigint,
TEAM_ID  bigint,
STAFF_ID  bigint,
ACTIVE_FLAG varchar(1),
EXPIRY_DATE DATE,
create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
create_user_id VARCHAR(32)NOT NULL ,
modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modify_user_id VARCHAR(32),
SEAL_FLAG  VARCHAR(1),
constraint TEAM_STAFF_MEMBERS_PK primary key(TEAM_MEMBER_ID), 
	 CONSTRAINT TEAM_STAFF_MEMBERS_FK1 FOREIGN KEY (TEAM_ID)
	  REFERENCES AUTOMATION_TEAMS (TEAM_ID),
     CONSTRAINT TEAM_STAFF_MEMBERS_FK2 FOREIGN KEY (STAFF_ID)
	  REFERENCES STAFF_MEMBERS (STAFF_ID) 
) 
TABLESPACE TAG_DATA;

--not sure where this came from, but it does indee not exist in V4 Product Project
alter table oms_owner.TEAM_FUNCTIONS drop constraint  TEAM_FUNCTIONS_TEAMS_FK;

-----------------------------------------------------------------------------------------------
----------------------------------3.2.6.0------------------------------------------------------
-----------------------------------------------------------------------------------------------

--alreday incorporated

-----------------------------------------------------------------------------------------------
----------------------------------3.2.7.0------------------------------------------------------
-----------------------------------------------------------------------------------------------
DROP VIEW oms_owner.v_acp_schedules;

DROP VIEW oms_owner.v_course_phase_offerings;

DROP VIEW oms_owner.v_course_phase_offerings_2;

DROP VIEW oms_owner.v_course_activities;

DROP VIEW oms_owner.v_course_phases;

ALTER TABLE oms_owner.COURSE_ACTIVITIES ALTER COLUMN PROVIDER_PARTY_CODE TYPE VARCHAR(20);

CREATE OR REPLACE VIEW oms_owner.v_acp_schedules (program_id, program_code, program_desc, program_instance_id, program_instance_code, program_instance_desc, phase_code, phase_description, phase_provider_party_class, phase_provider_party_id, phase_provider_party_code, phase_provider_name, phase_instance_code, phase_instance_id, phase_list_seq, phase_session_length, phase_instance_desc, module_instance_id, module_list_seq, module_instance_desc, crs_sch_id, schedule_date, start_time, end_time, session_no, catch_up_session_flag, internal_location_desc, schedule_status) AS SELECT
/* =========================================================
   Version Number = 2.3 Date Modified = 17/06/2011
 ========================================================= */
-- MODIFICATION HISTORY
-- Person      Date        Comments
-- ---------   ------      -------------------------------------------
/* MODIFICATION HISTORY
   Person       Date      Version Comments
   ---------    ------     ------ ------------------------------
   Manjul       17/06/2011 2.3    Modified query for HPQC# 8431
   GJC          08/09/2006 2.2    Add column program_id
   David Ng     03/06/2006 2.1    NOMIS project(10.2.1)
   David Ng     24/08/2006 2.2    Add phase Provider info
*/
PS.program_id
,PS.Program_code
,PS.Description
,AP.crs_acty_ID       Program_instance_ID
,AP.Code
,AP.Description      Program_instance_desc
,PHPS.Program_code
,PHPS.Description
,PH.Provider_Party_Class
,PH.Provider_Party_ID
,PH.Provider_Party_Code
,Tag_Prg_Provider_Name(PH.Provider_Party_Class, PH.Provider_Party_ID, PH.Provider_Party_Code)
,PH.Code             Phase_code
,PH.crs_acty_id      Phase_instance_ID
,PH.list_seq         Phase_list_seq
,PH.SESSION_LENGTH   Phase_Session_Length
,PHPS.Description    phase_instance_desc
,MOD.crs_acty_ID     Module_instance_ID
,MOD.list_seq        Module_list_seq
,MODPS.description   module_instance_desc
,CS.CRS_SCH_ID
,CS.SCHEDULE_DATE
,CS.START_TIME
,CS.END_TIME
,CS.SESSION_NO
,CASE WHEN coalesce(CS.Catch_up_CRS_SCH_ID::text, '') = '' THEN  'N'  ELSE 'Y' END
,AIL.Description
,CS.SCHEDULE_STATUS
FROM program_services ps, program_services phps, course_activities ph, program_services modps, course_activities mod, course_schedules cs, course_activities ap
LEFT OUTER JOIN agency_internal_locations ail ON (AP.Internal_Location_ID = AIL.Internal_location_ID)
WHERE AP.crs_acty_id = PH.parent_crs_acty_ID --AND    PH.Internal_Location_ID = AIL.Internal_location_ID(+) --Commented by Manjul, HPQC# 8431
   --Commented by Manjul, HPQC# 8431
  AND PH.crs_acty_id = MOD.parent_crs_acty_id AND CS.crs_acty_ID = MOD.crs_acty_id AND PHPS.Program_id = PH.Program_id AND MODPS.Program_id = MOD.Program_id AND PS.Program_ID  = AP.Program_ID AND PS.program_category = 'ACP'
 
UNION ALL

SELECT
PS.program_id
,PS.Program_code
,PS.Description
,AP.crs_acty_ID       Program_instance_ID
,AP.Code
,AP.Description      Program_instance_desc
,PHPS.Program_code
,PHPS.Description
,PH.Provider_Party_Class
,PH.Provider_Party_ID
,PH.Provider_Party_Code
,Tag_Prg_Provider_Name(PH.Provider_Party_Class, PH.Provider_Party_ID, PH.Provider_Party_Code)
,PH.Code             Phase_code
,PH.crs_acty_id      Phase_instance_ID
,PH.list_seq         Phase_list_seq
,PH.SESSION_LENGTH   Phase_SESSION_LENGTH
,PHPS.Description    phase_instance_desc
,NULL                Module_instance_ID
,NULL                module_list_seq
,NULL                module_instance_desc
,CS.CRS_SCH_ID
,CS.SCHEDULE_DATE
,CS.START_TIME
,CS.END_TIME
,CS.SESSION_NO
,CASE WHEN coalesce(CS.Catch_up_CRS_SCH_ID::text, '') = '' THEN  'N'  ELSE 'Y' END 
,AIL.Description
,CS.SCHEDULE_STATUS
FROM program_services ps, program_services phps, course_activities ph, course_schedules cs, course_activities ap
LEFT OUTER JOIN agency_internal_locations ail ON (AP.Internal_Location_ID = AIL.Internal_location_ID)
WHERE AP.crs_acty_id = PH.parent_crs_acty_ID --AND    PH.Internal_Location_ID = AIL.Internal_location_ID(+) --Commented by Manjul, HPQC# 8431
   --Commented by Manjul, HPQC# 8431
  AND CS.crs_acty_ID = PH.crs_acty_id AND PHPS.Program_id = PH.Program_id AND PS.Program_ID  = AP.Program_ID AND PS.program_category = 'ACP';
  
  
CREATE OR REPLACE VIEW oms_owner.v_course_phase_offerings_2 (program_id, program_phase_id, course_id, caseload_type, provider_party_class, provider_party_id, provider_party_code, crs_services_address_id, crs_internal_location_id, program_list_seq, program_description, program_capacity, program_no_of_sessions, program_session_length, program_module_flag) AS SELECT
/* MODIFICATION HISTORY
   Person     	 Date      Version     	 Comments
   ---------    ------     ---------  	 ------------------------------
   David Ng     03/06/2006 2.1           NOMIS project(10.2.0)
*/
ps.Parent_program_ID,
ps.program_id,
CRS.Crs_acty_ID,
CRS.Caseload_type,
CRS.PROVIDER_PARTY_CLASS,
CRS.PROVIDER_PARTY_ID,
CRS.PROVIDER_PARTY_CODE,
CRS.services_Address_ID,
CRS.INternal_location_ID,
ps.list_seq,
ps.description,
ps.capacity,
ps.no_of_sessions,
ps.session_length,
ps.module_flag
FROM PROGRAM_SERVICES ps, COurse_ACTIVITIES CRS
WHERE ps.Program_Class = 'PRG_PH'
AND   CRS.Course_Class = 'COURSE'
AND   CRS.Program_ID   = ps.Parent_Program_ID;


CREATE OR REPLACE VIEW oms_owner.v_course_phase_offerings (program_id, program_phase_id, course_id, course_caseload_type, provider_party_class, provider_party_id, provider_party_code, course_phase_id, offering_flag, ph_list_seq, ph_description, ph_capacity, ph_no_of_sessions, ph_session_length, ph_module_flag, cp_caseload_type, cp_caseload_type_desc, cp_internal_location_id, cp_internal_location_desc, cp_services_address_id, cp_list_seq, cp_active_flag, cp_expiry_date, cp_start_date, cp_end_date, cp_no_of_sessions, cp_session_length, cp_capacity, cp_placement_corporate_id, cp_comment_text, cp_course_activity_type, cp_check_sum) AS SELECT
/*
   Person       Date      Version       Comments
   ---------    ------     ---------    ------------------------------
   Neil         08/08/2006 2.1           Added cp_course_activity_type.
   David Ng     03/06/2006 2.1           NOMIS project(10.2.0)
*/
V.program_ID,
V.program_Phase_id,
V.COURSE_ID,
V.caseload_type,
V.PROVIDER_PARTY_CLASS,
V.PROVIDER_PARTY_ID,
V.PROVIDER_PARTY_CODE,
CP.Crs_ACty_ID,
CASE WHEN coalesce(CP.crs_acty_id::text, '') = '' THEN  'N'  ELSE 'Y' END ,
V.program_list_seq,
V.program_description,
V.program_capacity,
V.program_no_of_sessions,
V.program_session_length,
V.program_module_flag,
CP.caseload_type,
SUBSTR(Oms_Miscellaneous_getdesccode('CSLD_CODE',
                                        CP.caseload_type
                                                ),
                  1,
                  40
                 ),
CP.Internal_location_ID,
Tag_Int_Loc_int_loc_desc(CP.internal_location_id),
CP.SERVICES_ADDRESS_ID,
CP.list_seq,
CP.Active_Flag,
CP.Expiry_date,
CP.schedule_start_date,
CP.schedule_end_date,
CP.no_of_sessions,
CP.session_length,
CP.capacity,
CP.placement_corporate_ID,
CP.comment_text,
CP.course_activity_type,
Tag_Schedule_check_sum(coalesce(CP.modify_datetime, CP.create_datetime))
FROM v_course_phase_offerings_2 v
LEFT OUTER JOIN course_activities cp ON (V.program_Phase_id = CP.program_id AND V.COURSE_ID = CP.parent_crs_acty_ID);


CREATE OR REPLACE VIEW oms_owner.v_course_phases (course_phase_id, course_phase_name, parent_crs_acty_id, program_phase_id, caseload_type, caseload_type_desc, capacity, schedule_start_date, schedule_end_date, active_flag, expiry_date, comment_text, provider_party_class, provider_party_id, provider_party_code, services_address_id, placement_corporate_id, internal_location_id, internal_location_desc) AS SELECT
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   David Ng     03/01/2006 2.0           NOMIS project(10.2.0)
   David Ng     23/01/2006 2.1          Rename Crs_acty_id to parent_crs_acty_ID
*/
          cp.crs_acty_id, PS.description, cp.parent_crs_acty_id, cp.program_id,
          cp.caseload_type,
          SUBSTR(oms_miscellaneous_getdesccode('CSLD_CODE',
                                                 ca.caseload_type
                                                ),
                  1,
                  40
                 ),
          cp.capacity, cp.schedule_start_date, cp.schedule_end_date,
          cp.active_flag, cp.expiry_date, cp.comment_text,
          cp.provider_party_class, cp.provider_party_id,
          cp.provider_party_code, cp.services_address_id,
          cp.placement_corporate_id, cp.internal_location_id,
          tag_int_loc_int_loc_desc(cp.internal_location_id)
     FROM course_activities cp, course_activities ca, Program_services PS
    WHERE cp.course_class = 'CRS_PH'
      AND cp.parent_crs_acty_id = ca.crs_acty_id
      AND cp.Program_ID = PS.program_id;


CREATE OR REPLACE VIEW oms_owner.v_course_activities (course_class, crs_acty_id, course_activity_code, course_phase_id, program_category, program_category_desc, program_id, program_services_desc, phase_desc, course_activity_desc, environment, environment_desc, active_flag, provider_class, provider_id, provider_code, provider_name, placement_corporate_id, agency_contact, schedule_start_date, schedule_end_date, capacity, vacancy, pqs_number, comment_text, services_address_id, suite_number, street_information, city, state, postal_code, country, country_desc, agy_loc_id, agy_loc_desc, area_code, noms_region_code, internal_location_id, internal_location_desc) AS SELECT
/* =========================================================
 Version Number = 10.2.0.2 Date Modified = 07/22/2010
 ========================================================= */
/* MODIFICATION HISTORY
   Person       Date      Version       Comments
   Rose         22/07/2010  10.2.0.2    HPQC#3257. Work Release, Using course_activities.agy_loc_id as agy_loc_id instead of provider_party_code.
   GJC          22/08/2006  2.4         Add VACANCY derivation by calling Tag_Prg.course_vacancy (ca.crs_acty_id)
   NDB          03/08/2006  2.3         Corrected call to Tag_Prg.course_vacancy
   David Ng     15/10/2005  2.0         NOMIS project(10.2.0)
   David Ng     17/01/2006  2.1         Add Course_Class
   David Ng     27/07/2006  2.2         Add Course Activity Code
*/
          ca.course_class, ca.crs_acty_id, ca.code, NULL, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, NULL, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CSLD_CODE',
                                                 ca.caseload_type),
                  1,
                  40
                 ),
          ca.active_flag, ca.provider_party_class, ca.provider_party_id, ca.provider_party_code,
          Tag_Prg_provider_name(ca.provider_party_class, ca.provider_party_ID, ca.provider_party_code),
          ca.placement_corporate_id, corp.corporate_name,
          ca.schedule_start_date, ca.schedule_end_date,
          ca.capacity, Tag_Prg_course_vacancy(ca.crs_acty_id) VACANCY
          , 0, ca.comment_text, ca.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc, ca.provider_party_code,
          al.description, al.area_code, al.noms_region_code,
          ca.internal_location_id, ail.description
     FROM program_services ps, course_activities ca
LEFT OUTER JOIN agency_internal_locations ail ON (ca.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (ca.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (ca.placement_corporate_id = corp.corporate_id)
LEFT OUTER JOIN agency_locations al ON (ca.provider_party_code = al.agy_loc_id)
WHERE ps.program_id = ca.program_id AND (ps.program_category <> 'WR' or coalesce(ps.program_category::text, '') = '')   -- @@@ Rose 22-JUL-2010 Work Release @@@ --
UNION ALL

   SELECT 'CRS_PH', cp.parent_crs_acty_id, ca.code, cp.course_phase_id, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, ph.description, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CLOAD_TYPE',
                                                 ca.caseload_type
                                                ),
                  1,
                  40
                 ),
          cp.active_flag, cp.provider_party_class, cp.provider_party_id, cp.provider_party_code,
          Tag_Prg_provider_name(cp.provider_party_class, cp.provider_party_ID, cp.provider_party_code),
          cp.placement_corporate_id, corp.corporate_name,
          cp.schedule_start_date, cp.schedule_end_date, cp.capacity,
          Tag_Prg_course_vacancy(cp.course_phase_id), 0, cp.comment_text,
          cp.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc,  ca.provider_party_code,
          al.description,
          al.area_code, al.noms_region_code, ca.internal_location_id,
          ail.description
     FROM program_services ps, v_program_phases ph, course_activities ca, v_course_phases cp
LEFT OUTER JOIN agency_internal_locations ail ON (cp.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (cp.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (cp.placement_corporate_id = corp.corporate_id)
LEFT OUTER JOIN agency_locations al ON (cp.provider_party_code = al.agy_loc_id)
WHERE cp.parent_crs_acty_id = ca.crs_acty_id AND cp.program_phase_id = ph.program_phase_id AND ps.program_id = ph.program_id AND (ps.program_category <> 'WR' or coalesce(ps.program_category::text, '') = '')   -- @@@ Rose 22-JUL-2010 Work Release @@@ --
     
UNION ALL

   SELECT
          ca.course_class, ca.crs_acty_id, ca.code, NULL, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, NULL, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CSLD_CODE',
                                                 ca.caseload_type),
                  1,
                  40
                 ),
          ca.active_flag, ca.provider_party_class, ca.provider_party_id, ca.provider_party_code,
          Tag_Prg_provider_name(ca.provider_party_class, ca.provider_party_ID, ca.provider_party_code),
          ca.placement_corporate_id, corp.corporate_name,
          ca.schedule_start_date, ca.schedule_end_date,
          ca.capacity, Tag_Prg_course_vacancy(ca.crs_acty_id) VACANCY
          , 0, ca.comment_text, ca.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc, ca.agy_loc_id,
          al.description, al.area_code, al.noms_region_code,
          ca.internal_location_id, ail.description
     FROM program_services ps, course_activities ca
LEFT OUTER JOIN agency_internal_locations ail ON (ca.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (ca.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (ca.placement_corporate_id = corp.corporate_id)
LEFT OUTER JOIN agency_locations al ON (ca.agy_loc_id = al.agy_loc_id)
WHERE ps.program_id = ca.program_id AND ps.program_category = 'WR' -- @@@ Rose 22-JUL-2010 Work Release @@@ --
     
UNION ALL

   SELECT 'CRS_PH', cp.parent_crs_acty_id, ca.code, cp.course_phase_id, ps.program_category,
          SUBSTR(Oms_Miscellaneous_getdesccode('PS_CATEGORY',
                                                 ps.program_category
                                                ),
                  1,
                  40
                 ),
          ps.program_id, ps.description, ph.description, ca.description,
          ca.caseload_type,
          SUBSTR(Oms_Miscellaneous_getdesccode('CLOAD_TYPE',
                                                 ca.caseload_type
                                                ),
                  1,
                  40
                 ),
          cp.active_flag, cp.provider_party_class, cp.provider_party_id, cp.provider_party_code,
          Tag_Prg_provider_name(cp.provider_party_class, cp.provider_party_ID, cp.provider_party_code),
          cp.placement_corporate_id, corp.corporate_name,
          cp.schedule_start_date, cp.schedule_end_date, cp.capacity,
          Tag_Prg_course_vacancy(cp.course_phase_id), 0, cp.comment_text,
          cp.services_address_id, va.suite_number,
          va.street_information, va.city_name, va.prov_state_desc,
          va.zip_postal_code, va.country, va.country_desc,  ca.agy_loc_id,
          al.description,
          al.area_code, al.noms_region_code, ca.internal_location_id,
          ail.description
     FROM program_services ps, v_program_phases ph, course_activities ca
LEFT OUTER JOIN agency_locations al ON (ca.agy_loc_id = al.agy_loc_id)
, v_course_phases cp
LEFT OUTER JOIN agency_internal_locations ail ON (cp.internal_location_id = ail.internal_location_id)
LEFT OUTER JOIN v_addresses va ON (cp.services_address_id = va.address_id)
LEFT OUTER JOIN corporates corp ON (cp.placement_corporate_id = corp.corporate_id)
WHERE cp.parent_crs_acty_id = ca.crs_acty_id AND cp.program_phase_id = ph.program_phase_id AND ps.program_id = ph.program_id AND ps.program_category = 'WR' -- @@@ Rose 22-JUL-2010 Work Release @@@ --
;

-----------------------------------------------------------------------------------------------
----------------------------------3.2.7.1------------------------------------------------------
-----------------------------------------------------------------------------------------------

--view only, see script for view 

-----------------------------------------------------------------------------------------------
----------------------------------3.2.7.2------------------------------------------------------
-----------------------------------------------------------------------------------------------
--
DROP VIEW oms_owner.v_clear_account_payables;

ALTER TABLE oms_owner.SYSTEM_PROFILES ALTER COLUMN PROFILE_VALUE TYPE VARCHAR(150); 
ALTER TABLE oms_owner.SYSTEM_PROFILES_JN ALTER COLUMN PROFILE_VALUE TYPE VARCHAR(150);

CREATE OR REPLACE VIEW oms_owner.v_clear_account_payables (account_code, corporate_id, person_id, corporate_name, total_amount, caseload_id) AS SELECT
/* =========================================================
   Version Number = 6.1.0.0  Date Created = 14-Feb-2002
   ========================================================= */
/* MODIFICATION HISTORY
   Person        Date          Version      Comments
   -------------------------------------------------------------------------------
   Johnny        26-NOV-2008   10.2.1.0      Modified so that it will bring up the same records as the screen/package is trying to process (OCDCBENE).
                                             Added criteria on locked_modules.
   Abu           14-Feb-2002     6.1.0.0      #11090: New view on which module
                                              OCDCBENE is based.
*/
 ac.account_code account_code,
 co.corporate_id corporate_id,
 (NULL)::numeric  person_id,
 co.corporate_name corporate_name,
 SUM(CASE WHEN bt.txn_post_usage='DR' THEN             CASE WHEN ac.txn_posting_type='DR' THEN                    coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='CR' THEN                    coalesce(bt.txn_entry_amount, 0) * -1 END  WHEN bt.txn_post_usage='CR' THEN             CASE WHEN ac.txn_posting_type='CR' THEN                    coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='DR' THEN                    coalesce(bt.txn_entry_amount, 0) * -1 END  END ) total_amount,
 bt.caseload_id caseload_id
  FROM beneficiary_transactions bt, account_codes ac, corporates co
 WHERE (bt.corporate_id IS NOT NULL AND bt.corporate_id::text <> '')
   AND bt.corporate_id = co.corporate_id
   AND bt.account_code = ac.account_code
   AND bt.txn_entry_time <
       (SELECT locked_date
          FROM locked_modules
         WHERE module_name = 'OCDCBENE'
           AND caseload_id = bt.caseload_id)
   AND (bt.beneficiary_cleared_flag = 'N' OR
       coalesce(bt.beneficiary_cleared_flag::text, '') = '')
   AND bt.txn_entry_date <=
       (( SELECT LOCALTIMESTAMP - '1 day'::interval * COALESCE(max(s1.profile_value::numeric), 0::numeric)::double precision
          FROM system_profiles s1, system_profiles s2
         WHERE s1.profile_type = 'TRUST_INF'
           AND s1.profile_code = 'CHECK_AGING'
           AND s2.profile_type = 'CHECK_AGING'
           AND s2.profile_code = bt.receipt_txn_type))
 GROUP BY ac.account_code,
          co.corporate_id,
          (NULL)::numeric ,
          co.corporate_name,
          bt.caseload_id

UNION ALL

SELECT ac.account_code account_code,
       (NULL)::numeric  corporate_id,
       pe.person_id person_id,
       pe.last_name || ', ' || first_name,
       SUM(CASE WHEN bt.txn_post_usage='DR' THEN                   CASE WHEN ac.txn_posting_type='DR' THEN                          coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='CR' THEN                          coalesce(bt.txn_entry_amount, 0) * -1 END  WHEN bt.txn_post_usage='CR' THEN                   CASE WHEN ac.txn_posting_type='CR' THEN                          coalesce(bt.txn_entry_amount, 0) WHEN ac.txn_posting_type='DR' THEN                          coalesce(bt.txn_entry_amount, 0) * -1 END  END ) total_amount,
       bt.caseload_id caseload_id
  FROM beneficiary_transactions bt, account_codes ac, persons pe
 WHERE (bt.person_id IS NOT NULL AND bt.person_id::text <> '')
   AND bt.person_id = pe.person_id
   AND bt.account_code = ac.account_code
   AND (bt.beneficiary_cleared_flag = 'N' OR
       coalesce(bt.beneficiary_cleared_flag::text, '') = '')
   AND bt.txn_entry_time <
       (SELECT locked_date
          FROM locked_modules
         WHERE module_name = 'OCDCBENE'
           AND caseload_id = bt.caseload_id)
   AND bt.txn_entry_date <=
       (( SELECT LOCALTIMESTAMP - '1 day'::interval * COALESCE(max(s1.profile_value::numeric), 0::numeric)::double precision
          FROM system_profiles s1, system_profiles s2
         WHERE s1.profile_type = 'TRUST_INF'
           AND s1.profile_code = 'CHECK_AGING'
           AND s2.profile_type = 'CHECK_AGING'
           AND s2.profile_code = bt.receipt_txn_type))
 GROUP BY ac.account_code,
          pe.person_id,
          (NULL)::numeric ,
          pe.last_name || ', ' || pe.first_name,
          bt.caseload_id;

-----------------------------------------------------------------------------------------------
----------------------------------3.2.8.0------------------------------------------------------
-----------------------------------------------------------------------------------------------

--legals

  CREATE TABLE oms_owner.ocdchgsu_data
   (	id bigint,
	form_info_json bytea ,
	form_identifier VARCHAR(1000),
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    create_user_id VARCHAR(32)NOT NULL ,
    modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_user_id VARCHAR(32),
	seal_flag VARCHAR(1), 
	 CONSTRAINT ocdchgsu_info_pk PRIMARY KEY (ID)
   ) 
   TABLESPACE tag_data;


  CREATE TABLE oms_owner.ocdleglo_data  
   (	id bigint, 
	form_info_json bytea, 
	form_identifier VARCHAR(1000), 
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    create_user_id VARCHAR(32)NOT NULL ,
    modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_user_id VARCHAR(32),
	seal_flag VARCHAR(1), 
	 CONSTRAINT OCDLEGLO_DATA_PK PRIMARY KEY (ID)
   )
  TABLESPACE tag_data;


  CREATE TABLE oms_owner.ocdlegls_data
   (	ID bigint,
	form_info_json bytea,
	form_identifier VARCHAR(1000),
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    create_user_id VARCHAR(32)NOT NULL ,
    modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_user_id VARCHAR(32),
	seal_flag VARCHAR(1) 
   )
  TABLESPACE tag_data;


--Case Notes
  
  ALTER TABLE oms_owner.OFF_CASE_NOTE_RECIPIENTS DROP CONSTRAINT OFF_CN_RECPT_TEAM_FK;

  ALTER TABLE oms_owner.OFF_CASE_NOTE_RECIPIENTS ADD CONSTRAINT OFF_CN_RECPT_TEAM_FK FOREIGN KEY(TEAM_ID) REFERENCES AUTOMATION_TEAMS(TEAM_ID);

-----------------------------------------------------------------------------------------------
----------------------------------3.2.8.1------------------------------------------------------
-----------------------------------------------------------------------------------------------

--Active Directory users
CREATE TABLE oms_owner.ad_user
   (USER_ID VARCHAR(50),
	PWD VARCHAR(50),
	CONSTRAINT AD_USER_PK PRIMARY KEY (USER_ID),
    create_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    create_user_id VARCHAR(32)NOT NULL ,
    modify_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_user_id VARCHAR(32),
	seal_flag VARCHAR(1) 
	)
    TABLESPACE TAG_DATA;
	
--Court Events
alter table oms_owner.COURT_EVENTS add APPEARANCE_TYPE VARCHAR(100);
alter table oms_owner.COURT_EVENTS add APPEARANCE_LOCATION VARCHAR(100);
alter table oms_owner.COURT_EVENTS add MATTER VARCHAR(255);	

--Staff members
alter table oms_owner.STAFF_MEMBERS add MAIL_ID VARCHAR(50);

-----------------------------------------------------------------------------------------------
----------------------------------3.2.8.2------------------------------------------------------
-----------------------------------------------------------------------------------------------

--Staff members 
alter table oms_owner.STAFF_MEMBERS add AD_USER VARCHAR(1);

COMMENT ON COLUMN OMS_OWNER.STAFF_MEMBERS.AD_USER IS 'Store "Y" for Active Directory users';

--Case Notes
ALTER TABLE oms_owner.WORKS ADD CASE_NOTE_TEXT VARCHAR(4000);

-----------------------------------------------------------------------------------------------
----------------------------------3.2.8.2_5----------------------------------------------------
-----------------------------------------------------------------------------------------------

--incorporated above under 3.2.8.0

-----------------------------------------------------------------------------------------------
----------------------------------missing seal_flag column----------------------------------------------------
-----------------------------------------------------------------------------------------------

alter table oms_owner.ACTION_API drop column row_id;
alter table oms_owner.ACTION_API add SEAL_FLAG VARCHAR(1);
alter table oms_owner.ACTION_API add row_id bigserial;

alter table oms_owner.AUTOMATION_API_QUERY add SEAL_FLAG VARCHAR(1);
alter table oms_owner.AUTOMATION_QUERY_PARAMETERS add	SEAL_FLAG VARCHAR(1); --order issue

alter table oms_owner.BPMN_PROCESS add SEAL_FLAG VARCHAR(1); --order issue
alter table oms_owner.DMN_PROCESS	add SEAL_FLAG VARCHAR(1);

alter table oms_owner.MODULE_TRIGGERS	add SEAL_FLAG VARCHAR(1); --order issues

alter table WORK_ITEMS add SEAL_FLAG VARCHAR(1);

