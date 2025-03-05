INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.partytype', 'Party Type', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.partydesc', 'Party Description', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.comment', 'Comment', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.partytypemsg', 'Party Type must be entered', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.partydescmsg', 'Party Description must be entered', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.title', 'Interested Parties', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDINTPA', 'ocdintpa.close', 'Close', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OIDCRTEV', 'oidcrtev.intParties', 'Interested Parties', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDLEGLO', 'ocdleglo.intParties', 'Interested Parties', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('LABLE_ID_SEQUENCE'), 'OCDNCODE', 'ocdncode.intparties', 'Interested Parties', 'LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS 
 (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSREQS','oimsreqs.chargesflag','Charges Flag','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLO','ocdleglo.holdExpirydate','Hold Expiry Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLO','ocdleglo.laterthancommencedate','%fieldname% must be later than or equal to the Commence Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLO','ocdleglo.chargesConfirmation','You are saving a order without linking charges. Do you want to proceed?','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLO','ocdleglo.recordmatternumber','Because this order does not have any linked charges, you must record matter number(s).','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCISCATA','ociscata.program','having active non-Assocation for the same instance of the program','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.code','Code','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.descriptionfield','Description','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.length','Length','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.automatic','Automatic ','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.lengthunits','Units','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.lengthmustbeentered','Length Must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.unitmustbeentered','Unit Must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDBSIAP','oidbsiap.doyouwanttocontinue','Do you want to continue?','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDBSIAP','oidbsiap.nonassociationconflictmsg','having an active non-association, and who have an internal appointment on the same date at the same time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDBSIAP','oidbsiap.indinonassocconflict','INDIVIDUAL NON-ASSOCIATION CONFLICTS','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDBSIAP','oidbsiap.gangnonassocconflict','GANG NON-ASSOCIATION CONFLICTS','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.significantincident','Significant Incident','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.forceused','Force Used','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.reportcomplete','Report Complete','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.staffreport','Staff Report','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.staffreporttype','Staff Report type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.costcannotbenegative','Cost cannot be negative','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.typemustbeentered','Type must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.entrydatemustbeentered','Entry date must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMSVACP','ocmsvacp.sessionlen','Session Length','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.youdonothaveaccesstostaffreport','You do not have access to this staff report ','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.pleasesaverecordsbeforecontinuing','Please save changes before continuing.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIOBALX','oiiobalx.accountid','Account Id','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.blockinfo','Blocks Info','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.block','Block','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.audit','Audit','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.module','Module','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

   insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values (nextval('LABLE_ID_SEQUENCE'),'OCONDTRF','ocondtrf.legaltext','Legal Text','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

 insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values (nextval('LABLE_ID_SEQUENCE'),'OCONDAWAIT','ocondawait.legaltext','Legal Text','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

 insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 values (nextval('LABLE_ID_SEQUENCE'),'OCONDAWAIT','ocondawait.nodataismodifiedtosave','No Data is modified To save.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL); 

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.program','Program','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.referraldate','Referral Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.priority','Priority','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.status','Status','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.decisiondate','Decision Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.projectcode','Project Code','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.provider','Provider','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.offenderstartdate','Offender Start Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.enddate','End Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.endreason','End Reason','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.eventdate','Event Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.outtime','OUT Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.intime','IN Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.providertype','Provider','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.outtimesec','OUT Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.intimesec','IN Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.cancelreason','Cancel Reason','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.type','Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.subtype','Sub-Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.notetext','Note Text','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.offenderworkreleaseprojects','Offender Work Release Projects','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.offenderworkreleasereferrals','Offender Work Release Referrals','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.updateprogramstatus','Update Program Status','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.placement','Placement','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.comment','Comment','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.scheduleoffender','Schedule Offender','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.offenderscheduletitle','Offender Schedule','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.progressnotestitle','Progress Notes','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.progressnotes','Progress Notes','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.creationdate','Creation Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.time','Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.staffname','Staff Name','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.Cannotdeletemasterrecordwhenmatchingdetailrecordsexist','Cannot delete master record when matching detail records exist.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.offenderschextmovmovements','Offender Schedule - External Movements','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.contactdate','Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.contacttime','Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.programmustbeentered','Program must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.referraldatemustbeentered','Referral Date must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.offenderisnotyetacceptedfortheprogram','Offender is not yet accepted for the program.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.enddatecannotbelessthanstartdate','End Date can not be less than Start Date.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.sameprojectcodeisalreadyallocated','Same Project Code is already allocated.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.projectcodemustbeentered','Project Code must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.endreasonmustbeentered','End Reason must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.enddatemustbeentered','End Date must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.pleaseentertheenddatefirst','Please enter the End Date first.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.youcannotdeletethisrecord','You cannot delete this record.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.cancelreasonmustbeenteredforexpiredschedules','Cancel Reason must be entered for Expired schedules.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.datemustbeentered','Date must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.timemustbeentered','Time must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.datecannotbefuturedate','Date cannot be future date.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.timecannotbefuturedate','Time cannot be future date.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.programlocation','Program Location','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.scheduled','Scheduled','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values(nextval('LABLE_ID_SEQUENCE'),'OIDOWREL','oidowrel.actual','Actual','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.title','Maintain Case Note Permissions','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.userroles','User Roles','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.roleid','Role ID','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.casenotetype','Case Note Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.casenotesubtype','Case Note Sub Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
 insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.createamend','Create/Amend','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.casenotepermissions','Case Note Permissions','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCNPER','ocmcnper.revokeviewoption','You dont have access to revoke view option when create/amend case notes is checked','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
 insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.caseNotesSearch','Case Notes Search','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.staffName','Staff Name','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.fromdate','From Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.todate','To Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.facility','Facility','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.casenotetitle','Case Notes','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.casenotes','Case Note Text','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.contactDate','Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.contactTime','Time','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.noteSourceCode','Source','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.offenderIdDisplay','PID#','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.lastName','Last Name','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.firstName','First Name','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.caseNoteType','Note Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.caseNoteSubType','Note Sub Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.amendmentFlag','Amended','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.staffnamemustbeselect','Please select the Staff Name','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.fromdatecannotbelaterthantodate','From date sholud be lessthan to date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
 
 insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.todatecannotbefuture','To date cannot be a future date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.fromdateshouldbe','From date should be in past 6 months','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.fromdatemust','From date must be enter','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCICNSRC','ocicnsrc.todatemust','To date must be enter','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL); 
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.nocreateflagforselectedtypeandsubtype','Create flag is uncheck for this casenotesubtype','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.youdonothavepermissionstoamendthiscasenote','You do not have permissions to amend this case note.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.go','Go','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.d','D','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.a','A','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
 

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.casenoteflga','Attempt Contact','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);



insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.casenoteflgc','ANT Result/Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.casenoteflge','Case Review','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);  


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.casenoteflgb','Collateral','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDCNOTE','oidcnote.casenoteflgd','Other[Monthly Report]','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
 


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCLOGS','ocdclogs.casenoteflga','Attempted contact','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCLOGS','ocdclogs.casenoteflgc','ANT Date/Result','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCLOGS','ocdclogs.casenoteflge','Case Review','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCLOGS','ocdclogs.casenoteflgb','Collateral','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCLOGS','ocdclogs.casenoteflgd','Other(Monthly Report)','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCSCH','ocdcsch.eventdatecannotbepriortostartdateofthebooking','Event Date cannot be prior to the start date of the booking','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIUOVRES','oiuovres.arestrictionofthistypealreadyexistsfortheoffender','A restriction of this type already exists for the offender.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OMUVREST','omuvrest.arestrictionofthistypealreadyexistsforthevisitor','A restriction of this type already exists for the visitor.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDPACTI','oidpacti.inactiveoffenderscannotbeassignedtoInstitutionalActivities','Inactive offenders cannot be assigned to Institutional Activities','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSIAPP','oidsiapp.inactiveoffenderscannotbeassignedtointernalappointment','Inactive offenders cannot be assigned to Internal Appointment','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTABS','oidstabs.inactiveoffenderscannotbeassignedtotemporaryabsence','Inactive offenders cannot be assigned to Temporary Absence','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
   
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDPROGR','ocdprogr.inactiveoffenderscannotbeassignedtoassignments','Inactive offenders cannot be assigned to Assignments','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.program','Program','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.startDate','Start Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.endDate','End Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.capacity','Capacity','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.active','Active','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.expiryDate','Expiry Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.codemustbeentered','Code must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.Code','Code','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.codeisalreadyinusepleaseenteruniquecode','Code is already in use. Please enter unique code','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.enddateshouldbeequalorgreaterthanstartdate','End date should be equal or greater than start date.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.programmustbeentered','Program must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.startdatemustbeentered','Start Date must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.capacitymustbeentered','Capacity must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.providermust beentered','Provider must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.descriptionmustbeentered','Description Of Activity must be entered.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkrdialog.description','Description','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkrdialog.suiteNumber','SuiteNumber','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.descriptionofactivity','Description Of Activity','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.deletenotpermittedaschildrecordsexist','Delete not permitted as child records exist.','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.facility','Facility','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMWORKR','oimworkr.provider','Provider','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

update system_labels set msg_value = 'having an active non-association, and who have an internal appointment on the same date at the same location'
where	msg_key = 'oidbsiap.nonassociationconflictmsg';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLODET','ocdlodet.legalorderdetails','Legal Order Details','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLODET','ocdlodet.ordertype','Order Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLODET','ocdlodet.commencedate','Commence Date','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLODET','ocdlodet.lineno','Line No','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLODET','ocdlodet.court','Court','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.staffreportlockunlock','Staff Report Lock/Unlock configuration','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.referencecode','Reference Code','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
      
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.refrencedomain','Reference Domain','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
     

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDPROGR','ocdprogr.ordertype','Order Type','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values (nextval('LABLE_ID_SEQUENCE'),'OCDIPLAN','ocdiplan.team','Team','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
values (nextval('LABLE_ID_SEQUENCE'),'OCDIPLAN','ocdiplan.Officer','Officer','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDCLOGS','ocdclogs.offendderdoesnthavephonenumberconfiguration','Offender does not have Phone Number(s) Configuration','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.triggerExist','log_elite_generic_trigger for relation &tableName& already exists','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.tableauditinfo','Table Audit Info','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

update SYSTEM_LABELS set msg_value ='Module' where msg_key='oumtagre.module';

insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.moduletableinfo','Module Table Info','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
(nextval('LABLE_ID_SEQUENCE'),'OUMTAGRE','oumtagre.tableassociationwarn','The Trigger on the Table will be Removed if you proceed.Do You Want to Proceed ?','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.wapactivitystarttimemustbeentered','Activity Start Time must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.wapactivitynewtimemustbeentered','WAP Activity  must be entered','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'COMMON','common.selectcategory','Please Select the Category','LABEL',current_timestamp, 'OMS_OWNER',current_timestamp,NULL,NULL);
  
  --
UPDATE SYSTEM_LABELS 
   SET modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER' 
	  ,MSG_VALUE = 'Elite 5.0.0.2.2' 
 WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header');  
 
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMSRLUC','oimsrluc.reporttype','Report Type','LABEL',current_timestamp,'OMS_OWNER',current_timestamp,NULL,NULL); 
 