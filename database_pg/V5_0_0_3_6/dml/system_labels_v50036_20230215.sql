UPDATE SYSTEM_LABELS SET MSG_VALUE = 'Row exists already with the same Offence code and Description',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER' WHERE MSG_KEY = 'oimoffen.rowexistswithsameoffencecode' AND MODULE_NAME = 'OIMOFFEN';

insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUSERS','oumusers.mailidrequired','Insight access can not be provided without email id.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUSERS','oumusers.createinsightsuser','Allow Insights Access','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OMSHELP','omshelp.moduleName','Module Name','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.incidentfollowup','Incident Follow-up','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMSYLAB','oumsylab.btnLabel','Label','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMSYLAB','oumsylab.btnProfile','Profile','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OLISET','oliset.btnSave','Save','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OLISET','oliset.btnImport','Import','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'INSIGHTS','insights.UserEmailNotAssociatedWithInsights','User Email Not Associated With Insights','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'INSIGHTS','insights.itSeemsYouDontHaveAccessOfCreation','It Seems You Dont Have Access Of Creation','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'INSIGHTS','insights.datasourceAccessDenied','Datasource Access Denied','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'INSIGHTS','insights.RecordsNotFound','Records Not Found','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'INSIGHTS','insights.dashboardAccessDenied','Dashboard Access Denied','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDINCDE','oidincde.Entrydateandtimecannotbeinthepast','This Date Cannot Be Earlier Than The Incident Date.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'CALSCH','calsch.schedule','Schedule','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUACOND','ocuacond.selectcondcategory','Please select Condition Category','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDLEGLO','ocdleglo.cannotselectrfc','Cannot select Upon Release From Custody(RFC) for Custodial Orders','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDMPITM','oidmpitm.imageflag','Image Flag','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUCREAT','oumucreat.faildtoassigninsightaccess','User was created but, faild to assign Insight Access.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUSERS','oumusers.faildtoassigninsightaccess','Faild to assign Insight Access.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUCREAT','oumucreat.pleasechecktheinsightaccess','Please check the Insights Access','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMINSDB','ouminsdb.moduleexists','Module cannot be duplicated.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMPCONF','ocmpconf.mytaskfill','My Task Color','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMPCONF','ocmpconf.teamtaskfill','Team Task Color','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUSERS','oumusers.activeflag','Active','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMMHOLO','oimmholo.description','Description','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIPROLL','oiiproll.view','View','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIHISCO','oiihisco.countdetail','Count Detail','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIOSCED','oiiosced.offenderschedules','Offender Schedules','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIOSCED','oiiosced.schedulereason','Schedule Reason','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMCOUNT','oimcount.building','Building','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMCOUNT','oimcount.unitorwing','Unit/Wing','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMCOUNT','oimcount.cell','Cell','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTABS','oidstabs.schedules','Schedules','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTABS','oidstabs.businesslocationfield','Business location','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIIWLTWJ','oiiwltwj.waitList','Waiting List','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMSVASS','ocmsvass.assessmentsandevaluationmeasure','Assessments & Evalution Measures','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMILOCA','oimiloca.locationcode','Location Code','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMILOCA','oimiloca.locationdescription','Location System Code','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMILOCA','oimiloca.userDesc','Location Description','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMILOCA','oimiloca.locationtype','Location Type','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMILOCA','oimiloca.internallocation','Internal Location	','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OWEACPLN','oweacpln.save','Save','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUCALCR','ocucalcr.save','Save','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDINTAK','ocdintak.activestatus','Status','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCDINTAK','ocdintak.bookstatus','Status','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);


update system_labels set msg_value = 'User',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER' where label_id =( select label_id from system_labels where msg_key = 'ocuverif.userid');

update system_labels set msg_value = 'Override User',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER' where label_id =( select label_id from system_labels where msg_key = 'ocdnoque.overrideuserid');

update system_labels set msg_value = 'User',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER' where label_id =( select label_id from system_labels where msg_key = 'oidvcont.userid');

update system_labels set msg_value = 'User',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER' where label_id =( select label_id from system_labels where msg_key = 'oiiptran.userid');

update system_labels set msg_value = 'User',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER' where label_id =( select label_id from system_labels where msg_key = 'oidcount.staffid');


insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDISSUE','oidissue.user','User','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMSPRAC','ocmsprac.duplicateCode','Activity code should not be duplicate','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMFOPRO','otmfopro.expirydate','Expiry Date','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMFOPRO','otmfopro.effectivedate','Effective Date','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIMMHOLO','oimmholo.ieplevelInsert','You Can Insert Only One Record In This Grid','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMAGLOC','oumagloc.ieplevelInsert','You Can Insert Only One Record In This Grid','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OMUAVBED','omuavbed.nonassocationmsg','Non-assocation linkage exits between','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OMUAVBED','omuavbed.nonassocationhouse','housed in this unit','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCSPROGR','ocsprogr.retrieve','Retrieve','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCSPROGR','ocsprogr.clear','Clear','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);

UPDATE oms_owner.system_labels
set msg_value='Transferred Conditions', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where  msg_key ='otkcondtrf.conditions';


UPDATE oms_owner.system_labels
set msg_value='Transferred To(Team)', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where  msg_key ='otkcondtrf.transfertoteam';


UPDATE oms_owner.system_labels
set msg_value='Transferred To(Location)', modify_datetime=current_timestamp, modify_user_id='OMS_OWNER' where  msg_key ='otkcondtrf.transfertoloc';

update
	system_labels
set
	msg_value = 'IEP Level',modify_datetime = CURRENT_TIMESTAMP,modify_user_id= 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels sl
	where
		msg_key = 'oimmholo.facilityieplevel');
		
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDADMIS','oidadmis.fromAgyLocId','From location must be enter','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMUSERS','oumusers.thiscaseloadcannotbedeletedbecausestaffalredyhasbeenworking','This caseload is working caseload of this staff so that cannot be deleted','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCUOCCUP','ocuoccup.phoneFormat','Phone Format','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'INSIGHTS','insights.SelectOffenderFirst','Select Offender First','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIPASINF','oipasinf.title','Password','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIPASINF','oipasinf.notallowtoaccess','You cannot access the password','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OUMSYPFL','oumsypfl.invaliddateformat','Invalid date format','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'COMMON','common.intloccode','InternalLocation Code','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'COMMON','common.intlocid','InternalLocation Id','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL); 
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSTFRP','oidstfrp.reporteddateearliervalidation','Reported Date cannot be earlier then the  Incident Report Created Date','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OIDSCEXM','oidscexm.validationShow','Cannot schedule this movement, the offender is already out','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMFOPRO','otmfopro.effectiveearliertodaydate','The Effective Date cannot be earlier than todays date.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);  
  
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMALPRO','otmalpro.effectivedategreterthanexpirtdate','The Effective Date cannot be Greater than Expiry Date.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMTFPRO','otmtfpro.warndateenteredmustbegreaterorequaltothecurrentdate','OMS 19 WARN Date entered must be greater or equal to the current date','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMALPRO','otmalpro.childRecordFound','You cannot delete the parent record when child record exists','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMTROPS','ocmtrops.rowealreadyexists','Row exists already with same Caseload,Module Name,Trans Type','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OTMFOPRO','otmfopro.eitherpersonidorcorporateidshouldbeselected','Either Person ID or Corporate ID should be selected','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCOACT','ocmcoact.cannotdeletechartofwhiledependentminimumpayablebalancesexists','Cannot delete Chart of while dependent MINIMUM_PAYABLE_BALANCES exists','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
insert into OMS_OWNER.SYSTEM_LABELS
  (LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag)
values
  (nextval('LABLE_ID_SEQUENCE'),'OCMCOACT','ocmcoact.cannotdeletechartofaccountwhiledependentaccountcodesexists','Cannot delete Chart of account while dependent ACCOUNT_CODES exists','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);

update
	system_labels
set
	msg_value = 'Description',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmtrans.description');

update
	system_labels
set
	msg_value = 'Txn Usg',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmtrans.txnusg');

update
	system_labels
set
	msg_value = 'Txn Type',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmtrans.txntype');

update
	system_labels
set
	msg_value = 'Ded Cat',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmdedut.dedcatmandatory');

update
	system_labels
set
	msg_value = 'Cal On',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmdedut.calonmandatory');

update
	system_labels
set
	msg_value = 'Ded Code',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmdedut.dedcodemandatory');

update
	system_labels
set
	msg_value = 'Description',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'ocmdedut.description');

update
	system_labels
set
	msg_value = 'Caseload',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	label_id =(
	select
		label_id
	from
		system_labels
	where
		msg_key = 'common.caseloadmandatory');

update
	system_labels sl
set
	msg_value = 'Row exists already with same Account Code,Txn',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'otmfreez.txntypealready';

update
	system_labels sl
set
	msg_value = 'Row exists already with same Account Code,Txn Type',
	modify_datetime = current_timestamp,
	modify_user_id = 'OMS_OWNER'
where
	msg_key = 'otmfreez.txntypealready';  

INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.userdonthaveaccesstoverify','User do not have access to verify','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.userdonthaveaccesstooverride','User do  not have access to override','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.verified','Verified','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.verifybtn','verify','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.override','Override','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.indefiniteoverride','Indefinite(Override)','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.valueoverride','Override','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.pleaseenteroverride','Please enter either an override date or an indefinite date','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdlegls.fieldcannotupdate','This field cannot be updated when the key date is indefinite','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCDLEGLS','ocdleglo.overridelaunch','Override','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCUOVKEY','ocuovkey.title','Override Sentence Level Key Dates','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCUOVKEY','ocuovkey.save','Save','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCUOVKEY','ocuovkey.exit','Exit','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.title','Verify Keydates','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.ok','Ok','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS
(LABEL_ID, MODULE_NAME, MSG_KEY, MSG_VALUE, MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES
(NEXTVAL('LABLE_ID_SEQUENCE'),'OCUVERKD','ocuverkd.cancel','Cancel','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS 
(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES 
(NEXTVAL('LABLE_ID_SEQUENCE'),'OUMINSDB','ouminsdb.numbersnotallowed','Numbers and Special characters are not allowed for Module.','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
INSERT INTO OMS_OWNER.SYSTEM_LABELS 
(LABEL_ID,MODULE_NAME,MSG_KEY,MSG_VALUE,MSG_TYPE,create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) 
VALUES 
(NEXTVAL('LABLE_ID_SEQUENCE'),'OIDMPITM','oidmpitm.clearItemGroups','Clear Items/Groups','LABEL',current_timestamp,'OMS_OWNER',NULL,NULL,NULL);
------------------------------------------------------------------
update
	SYSTEM_LABELS
set
	modify_datetime = current_timestamp
   ,modify_user_id = 'OMS_OWNER' 
   ,MSG_VALUE = 'Elite 5.0.0.3.6'
where
	MODULE_NAME = 'LOGIN'
	and MSG_KEY in ('home.title.header', 'login.header');    