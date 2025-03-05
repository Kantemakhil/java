insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values (nextval('lable_id_sequence'),'OUMUCREAT','oumucreat.insightUser','Insights User','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
UPDATE OMS_OWNER.SYSTEM_LABELS SET MSG_VALUE = 'Insights Access' WHERE MODULE_NAME = 'OUMUCREAT' AND MSG_KEY = 'oumucreat.insightUser';

UPDATE OMS_OWNER.SYSTEM_LABELS SET MSG_VALUE = 'Allow Insights Access' WHERE MODULE_NAME = 'OUMUSERS' AND MSG_KEY = 'oumusers.createinsightsuser';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.update','Update','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.delete','Delete','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.until','Until','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.repeatevery','Repeat Every','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.repeat','Repeat','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.daily','Daily','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.monthly','Monthly','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.weekly','Weekly','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.never','Never','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.comment','Comment','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);



INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.tolocation','Location','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);



INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.startdate','Start date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.starttime','Start time','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);



INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.enddate','End date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.endtime','End time','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);



INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.updateschedule','Update Schedule','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.addschedule','Add Schedule','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.end','End','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.repeateverycannotbelessthanone','Repeat every can not be less than one','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'CALSCH','calsch.pleaseselectatleastoneweekday','Please select atleast one weekday','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.no', 'No', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.type', 'Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.termtypeandlength', 'Term', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.commencetype', 'Commence Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.linkto', 'Related To', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.commencedate', 'Commence Date', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.erd', 'ERD', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.ped', 'PED', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.lrd', 'LRD', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'custorder.status', 'Status', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.no', 'No', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.type', 'Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.termtypeandlength', 'Duration', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.commencetype', 'Commence Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.linkto', 'Related To', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.commencedate', 'Commence', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.expiryDate', 'Expiry Date', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ncustorder.status', 'Status', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.receiveddate', 'Date Received', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.agency', 'Agency', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.type', 'type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.refnumber', 'Reference Number', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.commencedate', 'Commence Date', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.expirydate', 'Expiry Date', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'hwd.status', 'Status', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ocdlegls.custodialdetails', 'Active Custodial Orders', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ocdlegls.holdswarrants', 'Active Holds, Warrants, & Detainers', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS( LABEL_ID , MODULE_NAME , MSG_KEY , MSG_VALUE , MSG_TYPE ,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES( NEXTVAL('lable_id_sequence') , 'OCDLEGLS', 'ocdlegls.noncustodialdetails', 'Active Non-Custodial Orders & Dates', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--

UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Court Actions' WHERE MSG_KEY = 'ocdenfor.title';
 
insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.courtorders','Parole/Court Orders','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.courtactions','Actions','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.teamresponsible','Team Responsible','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.staffresponsible','Staff Responsible', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.enterproceedingtype','Select the proceeding type.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.enterstartdate','Enter the start date.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.enterhearingbody','Select the hearing body.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.notearliertthanstartdate','Outcome date cannot be earlier than the start date.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.notlaterthansysdate','Outcome date cannot be later than the system date.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.cantsetallocated','This status requires selection of a team or an officer.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.cantsetunallocated','This status cannot be selected when the report is assigned to a team or an officer.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.no','No.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.ordereddate','Ordered Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.matter','Matter#','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.commencetype','Commence Type','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.linkto','Related To','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.commencedate','Commence Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.duration','Duration','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

insert into OMS_OWNER.SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values (nextval('lable_id_sequence'),'OCDENFOR','ocdenfor.expiryDate','Expiry Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--

INSERT INTO SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
   VALUES(NEXTVAL('lable_id_sequence'),'OCDENFOR','ocdenfor.courtEvents','Court Events','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  VALUES(NEXTVAL('lable_id_sequence'),'OCDENFOR','ocdenfor.nonCustodialOrder','Non-Custodial Orders','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
  VALUES(NEXTVAL('lable_id_sequence'),'OCDENFOR','ocdenfor.custodialOrder','Custodial Orders','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.activeprojects','Active Projects ','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.providerscheduledays','Providers Scheduled Day(s)','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.hasavailablecapacity','Has Available Capacity','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
 
INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.conditionstartdate','Condition Start Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.conditionenddate','Condition End Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.remainingHours','Hours Remaining','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.days','Day(s)','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCSPROIN','ocsproin.communityserviceprojectinquiry','Community Service Project Inquiry','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderstartdatecannotbeearlier','Offender Start Date cannot be earlier than current date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.ocduprojyoycannot','ocduprojyoycannot','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.recordcantdltedmod','Record cannot be deleted or modified, as a child record exists in the OFFENDER_COURSE_APPT_GRPS table','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderstartdatecant','Offender Start Date cannot be earlier than project start date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);



INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderenddatecant','Offender Start Date cannot be later than project end date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offendersentencestartdate','Offender Start Date cannot be earlier than sentence start date ','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderenddatelater','Offender End Date cannot be later than project end date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderenddateprjassign','Offender End Date will end the project assignment prior to its commencement','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderenddatecurrent','Offender End Date cannot be earlier than current Date.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.penaltylessthenhour','Penalty Should be less than hours + travel','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.adjustmentvalidate','Error: Adjustment Date cannot be earlier than sentence start date [','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.adjustmentdatelatervalidate','Error: Adjustment Date cannot be later than current date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.youcannotupdatecanctoanother','You can not update Cancelled to another.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.errorrowexistsinoffendercourseskills','Error: Row exists already in the OFFENDER_COURSE_SKILLS table','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.erroradjustmentdatecannotbelaterthancurrentdate','Error: Adjustment Date cannot be later than current date.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.emailFlag', 'Email Flag', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.smsFlag', 'Sms Flag', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.emailScheduleHrsBefore', 'Email Schedule Hours Before', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.smsScheduleHrsBefore', 'Sms Schedule Hours Before', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.smsScheduleHrsBeforemust', 'Sms Schedule Hours Before must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.emailScheduleHrsBeforemust', 'Email Schedule Hours Before must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.offendderdoesnthavephonenumberconfiguration', 'Offender does not have Phone Number(s) Configuration', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.offendderdoesnthaveemailconfiguration', 'Offender does not have email(s) Configuration', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.totalcountgreaterthanzero', 'Total Count must be greater than Zero.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.untildatecantlessthanschstartdate', 'Until Date cannot be less than Schedule Start Date.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.totalcount', 'Count', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.excludeholiday', 'Exclude Holiday?', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.pleaseselectsubtype', 'Schedule Sub Type must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.pleaseselecttype', 'Schedule Type must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.startdatemustbeentered', 'Start Date must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.starttimemustbeentered', 'Start Time must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.endtimeshouldgreaterthanstarttime', 'End Time must be greater than Start Time', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.pleaseselecttolocation', 'Location must be entered.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.schedulesavesuccess', 'Schedule(s) saved', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.scheduleupdatesuccess', 'Schedule(s) updated', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.panelheading', 'Community Schedules', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.movementtype', 'Movement Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.scheduletype', 'Schedule Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.schedulesubtype', 'Schedule Sub Type', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'oiuscinq.proceedwithnoconflicts', 'Proceed Schedules with no Conflicts', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.title','Export Process','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.prcessdescription','Process Description','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.lastsaved','Last Saved','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.createduser','Created User','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.modifieduser','Modified User','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.selectatleaseonerecord','Please select atleast one record to export','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.export','Export','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);




INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.title','Export Quick Action and API','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.selectatleaseonerecord','Please select atleast one record to export','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.export','Export','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.querykey','Query Key','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.apiid','Api Id','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.apidescription','Api Description','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPQAC','oiexpqac.url','Operation','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.doyouwanttoeditsinglescheduleorseries', 'Do you want to edit only this schedule or entire series ?', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.editonlythisschedule', 'Edit only this Schedule', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.editentireseries', 'Edit entire series', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.rangeofrecurrencemustbeentered', 'End must be entered.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.untildatemustbeentered', 'Until Date must be entered.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.totalcountmustbeentered', 'Total Count must be entered.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.noschedulesarecreatedforgivendetails', 'No schedules are created for given Inputs.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.scheduledeletesuccess', 'Schedule(s) deleted', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCDCLOGS', 'ocdclogs.doyouwantdeleteentireseries', 'Do you want to Delete only this schedule or entire series from selected scheduled Date ?', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCDCLOGS', 'ocdclogs.deleteonlythisschedule', 'Delete only this Schedule', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'OCDCLOGS', 'ocdclogs.deleteentireseries', 'Delete entire series', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.doyouwantdeleteentireseries', 'Do you want to Delete only this schedule or entire series from selected scheduled Date ?', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.deleteonlythisschedule', 'Delete only this Schedule', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.deleteentireseries', 'Delete entire series', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

update system_labels set msg_value = 'Event Type' where msg_key = 'calsch.movementtype';

update system_labels set msg_value = 'Recurring?' where msg_key = 'calsch.repeat';

update system_labels set msg_value = 'Exclude Holidays?' where msg_key = 'calsch.excludeholiday';

update system_labels set msg_value = 'End After' where msg_key = 'calsch.end';

update system_labels set msg_value = 'End Date' where msg_key = 'calsch.until';

update system_labels set msg_value = 'Number of Occurrences' where msg_key = 'calsch.totalcount';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.weeklyactivityplanner','Weekly Activity Planner','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.fromdate','(From) Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.todate','(To) Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emtag','EM Tag#','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emtagstrapsize','Em Tag Strap Size','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emdailychargingperiod','EM Daily Charging Period','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.printstaffcopy','Print Staff Copy','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.printoffendercopy','Print Offender Copy','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.finalise','Finalise','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.copyover','Copy Over','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.version','Version','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.serial','Serial #','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.sysemgenerated','Sysem Generated','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.day','Day','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.activity','Activity','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.wapactivity','Wap Activity','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.activityaddress','Activity Address','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.departtime','Depart Time','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.activitystart','Activity Start','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.activityfinish','Activity Finish','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.returntime','Return Time','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.modeoftransport','Mode Of Transport','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.updateindicator','Update Indicator','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.date','Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.onlymondayisallowedinfromdate','Only Monday is allowed in From Date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.title','Import Quick Action and Api','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.import','Import','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.browse','Browse','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.cancel','Cancel','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.importanddeploy','Import and Deploy','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.pleaseselectfile','Please select file to import','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.selectatleastonerecord','Please select atleast one record','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.conflict','Conflict','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.grid','Imported data','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.querykey','Query Key','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.apiid','Api Id','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.apidescription','Api Description','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.url','Operation','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);



INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.confirmationmessage','All quick action API is created for selected process?','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.confirmation','Confirmation','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPQAC','oiimpqac.successfullyimported','Actions has been successfully imported','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.successfullyimported','Process has been successfully imported','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

---

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.offenderstartdatemustbefuturedate',
'Offender Start Date must be a future date or current date','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDPSREP','ocdpsrep.duplicatekeyviolatesuniqueconstraint',
'Duplicate key value violates unique constraint ORDER_PROPOSALS_PK','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'), 'OCUNAWRN', 'ocunawrn.title', 'Non-Association Conflict Warning', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.proceedwithnoconlflicts', 'Proceed with no Conflicts', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);

---

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OIDTRWJU','oidtrwju.externalmovementcardtitle',
'External Movement','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

update system_labels set msg_value = 'End Date must be entered.' where msg_key = 'calsch.untildatemustbeentered';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OIDINTMV','oidintmv.housinglocation',
'Housing Location','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OIDBUTAB','oidbutab.offenderscardtitle',
'Offenders','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES ( NEXTVAL('lable_id_sequence'), 'CALSCH', 'calsch.pastschedulescannotbemodifiedordeleted', 'Past Schedules cannot be Modified or Deleted.', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

update system_labels set msg_value = 'End Date must be entered.' where msg_key = 'calsch.untildatemustbeentered';



INSERT INTO system_labels ( label_id, module_name, msg_key, msg_value, msg_type,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
 VALUES ( NEXTVAL('lable_id_sequence'), 'OIICIPON', 'oiicipon.faclitymustbeentered', 'Facility must be entered', 'LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.selectatleaseonerecord','Please select atlease one record','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.saveanddeploy','Save And Deploy','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.title','Import Process','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.import','Import','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.browse','Browse','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.cancel','Cancel','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.importanddeploy','Import and Deploy','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.pleaseselectfile','Please select file to import','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.conflict','Conflict','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.prcessdescription','Process Description','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.lastsaved','Last Saved','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.createduser','Created User','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.modifieduser','Modified User','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIIMPPRO','oiimppro.grid','Imported data','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

---
INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OUMPERSO','oumperso.usersignature','User Signature','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OUMPERSO','oumperso.drawsignature','Sign below','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

---
insert into SYSTEM_LABELS (LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
 values (nextval('lable_id_sequence'),'CMNPROSS','cmnpross.title','Common Process','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

update system_labels set msg_value  = 'Amend Comment' where msg_key  = 'oweacpln.ammendcomment';

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'), 'OWEACPLN', 'oweacpln.wapactivitymustbeentered', 'Wap Activity must be entered.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'), 'OWEACPLN', 'oweacpln.activitydategreterthanequal', 'Activity Date must be greater than or equal to current Date.', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);

--
update system_labels set msg_value = 'Number of Occurrences must be entered.' where  msg_key = 'calsch.totalcountmustbeentered' and module_name = 'CALSCH';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emtagmustenter','EM Tag# must be entered','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emtagstrapsizemust','EM Tag Strap Size must be entered','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emtagmchargingperiodmust','EM Daily charging period must be entered','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.wapschedule','WAP Schedule','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.ammendcomment','Ammend Comment','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'), 'OWEACPLN', 'oweacpln.notForOffenderFlag', 'Not For Offender', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);


INSERT INTO oms_owner.system_labels (label_id, module_name, msg_key, msg_value, msg_type, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'), 'OWEACPLN', 'oweacpln.wapactivitydatemustbeentered', 'Wap Activity Date must be entered', 'LABEL', current_timestamp, 'OMS_OWNER', current_timestamp, 'OMS_OWNER', NULL);

---

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,	MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(	NEXTVAL('lable_id_sequence'),'OCDUPROJ','ocduproj.invalidtime',
'Please enter valid Hours','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIIMPPRO','oiimppro.pleaseselectaprocess','Please select a process','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIIMPPRO','oiimppro.importedprocessdata','Imported Process Data','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIIMPPRO','oiimppro.importedcommonprocessdata','Imported Common Process Data','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(nextval('lable_id_sequence'),'OIEXPPRO','oiexppro.pleaseselectaprocess','Please select a process','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

delete from SYSTEM_LABELS where msg_key = 'oweacpln.emtagstrapsize';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.emtagstrapsize','EM Tag Strap Size','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

delete from SYSTEM_LABELS where msg_key = 'oweacpln.wapactivity';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.wapactivity','WAP Activity','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

delete from SYSTEM_LABELS where msg_key = 'oweacpln.wapactivitydatemustbeentered';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.wapactivitydatemustbeentered','WAP Activity Date must be entered','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

delete from SYSTEM_LABELS where msg_key = 'oweacpln.ammendcomment';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.ammendcomment','Append Comment','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

delete from SYSTEM_LABELS where msg_key = 'oweacpln.wapactivitymustbeentered';

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OWEACPLN','oweacpln.wapactivitymustbeentered','WAP Activity must be entered.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIDSIAPP','oidsiapp.nonassociationconflictmsg','The selected offender has a non-association with below offender(s), who has an Internal Appointment at the same location on the same date.','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
	
INSERT INTO SYSTEM_LABELS(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
VALUES(NEXTVAL('lable_id_sequence'),'OIDSIAPP','oidsiapp.doyouwanttocontinue','Do you want to continue with appointment?','LABEL',current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--
UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Elite 5.0.0.0.9' WHERE MODULE_NAME = 'LOGIN' AND MSG_KEY IN ('home.title.header', 'login.header');