OIVCTMNG_INSERT_VICTIM_RECORDS{
insert into victim_records (person_id,victim_id,preferred_contact_method,active_flag,registered_datetime,deactivated_datetime,note,create_datetime,create_user_id,seal_flag) values (:personId,:victimId,:preferredContactMethod,:activeFlag,:registeredDatetime,:deactivatedDatetime,:note,:createDatetime,:createUserId,:sealFlag)
}
OIVCTMNG_UPDATE_VICTIM_RECORDS{
update victim_records set person_id=:personId,victim_id=:victimId,preferred_contact_method=:preferredContactMethod,active_flag=:activeFlag,registered_datetime=:registeredDatetime,deactivated_datetime=:deactivatedDatetime,note=:note,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId,seal_flag=:sealFlag where victim_id=:victimId
}
OIVCTMNG_SELECT_VICTIM_RECORDS{
select vr.*,concat(p.last_name,', ', p.first_name) as name,
date_part('year',age(p.birthdate)) as age,
(select description  from reference_codes where domain='GENDER' and code =p.gender) as gender, (select description from reference_codes where domain = 'SEX' and code = p.sex) as sex
from victim_records vr left join persons p on (vr.person_id=p.person_id) order by 
  vr.active_flag desc , vr.create_datetime desc
}

OIVCTMNG_INSERT_VICTIM_LINKED_OFFENDERS{
insert into victim_linked_offenders (offender_id,victim_id,associated_legal_case,active_flag,deactivated_datetime,note,create_datetime,create_user_id,seal_flag) values (:offenderId,:victimId,:associatedLegalCase::bytea,:activeFlag,:deactivatedDatetime,:note,:createDatetime,:createUserId,:sealFlag)
}
OIVCTMNG_UPDATE_VICTIM_LINKED_OFFENDERS{
update victim_linked_offenders set offender_id=:offenderId,victim_id=:victimId,associated_legal_case=:associatedLegalCase::bytea,active_flag=:activeFlag,deactivated_datetime=:deactivatedDatetime,note=:note,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId,seal_flag=:sealFlag where victim_id=:victimId and offender_id=:offenderId
}
OIVCTMNG_SELECT_VICTIM_LINKED_OFFENDERS{
select vlo.*,concat(o.last_name,', ',o.first_name) as offenderName,o.offender_id_display as offenderIdDisplay,
(select AC.description  from AGENCY_LOCATIONS AC where AC.agy_loc_id =(select ob.agy_loc_id  from offender_bookings ob where ob.offender_id =vlo.offender_id 
order by  ob.create_datetime desc limit 1)) as agyLoc ,
(select ob.offender_book_id  from offender_bookings ob where ob.offender_id =vlo.offender_id 
order by  ob.create_datetime desc limit 1) as offenderBookId
from victim_linked_offenders vlo  left join offenders o  
on (vlo.offender_id =o.offender_id) where vlo.victim_id=:victimId order by vlo.create_datetime desc
}

OIVCTMNG_INSERT_VICTIM_CONTACT_LOGS{
insert into victim_contact_logs (victim_contact_log_id,victim_id,log_datetime,category,type,created_by_staff_id,contact_method,note,create_datetime,create_user_id,seal_flag) values ((select nextval('victim_contact_log_id_seq')),:victimId,:logDatetime,:category,:type,(select sm.staff_id  from staff_members sm where sm.user_id =:staffId),:contactMethod,:note,:createDatetime,:createUserId,:sealFlag)
}
OIVCTMNG_UPDATE_VICTIM_CONTACT_LOGS{
update victim_contact_logs set victim_contact_log_id=:victimContactLogId,victim_id=:victimId,log_datetime=:logDatetime,category=:category,type=:type,created_by_staff_id= (select sm.staff_id  from staff_members sm where sm.user_id =:staffId),contact_method=:contactMethod,note=:note,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId,seal_flag=:sealFlag where victim_contact_log_id=:victimContactLogId
}
OIVCTMNG_SELECT_VICTIM_CONTACT_LOGS{
select vcl.*,(select concat(sm.last_name, ', ' ,sm.first_name) as staffName from staff_members sm where staff_id =vcl.created_by_staff_id) staffName
from victim_contact_logs vcl where vcl.victim_id = :victimId  order by vcl.create_datetime desc
}

OIVCTMNG_INSERT_VICTIM_CONTACT_PREFERENCES{
insert into victim_contact_preferences (victim_contact_preferences_id,victim_id,contact_type,active_flag,deactivated_datetime,comment,create_datetime,create_user_id,seal_flag) values ((select nextval('victim_contact_preferences_id_seq')),:victimId,:contactType,:activeFlag,:deactivatedDatetime,:comment,:createDatetime,:createUserId,:sealFlag)
}
OIVCTMNG_UPDATE_VICTIM_CONTACT_PREFERENCES{
update victim_contact_preferences set victim_contact_preferences_id=:victimContactPreferencesId,victim_id=:victimId,contact_type=:contactType,active_flag=:activeFlag,deactivated_datetime=:deactivatedDatetime,comment=:comment,modify_datetime=:modifyDatetime,modify_user_id=:modifyUserId,seal_flag=:sealFlag where victim_contact_preferences_id=:victimContactPreferencesId
}

OIVCTMNG_SELECT_VICTIM_CONTACT_PREFERENCES{
select * from victim_contact_preferences  where victim_id = :victimId order by create_datetime desc
}

OIVCTMNG_GET_VICTIM_ID{
select nextval('victim_id_seq')
}
