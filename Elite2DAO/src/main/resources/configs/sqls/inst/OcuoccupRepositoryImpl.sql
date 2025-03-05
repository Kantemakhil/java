
OCUOCCUP_FIND_RGPERSONNAME {
 select concat(P.LAST_NAME,' , ',P.FIRST_NAME) LAST_NAME , RC.DESCRIPTION CREATE_USER_ID , RC.CODE CONTACT_TYPE , RC1.DESCRIPTION MODIFY_USER_ID , RC1.CODE RELATIONSHIP_TYPE , TRUNC(MONTHS_BETWEEN(SYSDATE() , P.BIRTHDATE )/ 12 ) AGE , P.PERSON_ID PERSON_ID , OCP.OFFENDER_CONTACT_PERSON_ID OFFENDER_CONTACT_PERSON_ID , 'N' CONTACT from PERSONS P , OFFENDER_CONTACT_PERSONS OCP , REFERENCE_CODES RC , REFERENCE_CODES RC1 where RC.DOMAIN = 'CONTACTS' and RC.CODE = OCP.CONTACT_TYPE and RC1.DOMAIN = 'RELATIONSHIP' and RC1.CODE = OCP.RELATIONSHIP_TYPE and OCP.OFFENDER_BOOK_ID = :offenderBookId::Integer and OCP.PERSON_ID = P.PERSON_ID union select 'NEW OCCUPANT' , null , null , null , null , null , null , null , 'Y' from DUAL order by 1
 }

OCUOCCUP_FIND_RGCONTACTTYPES {
 	SELECT DESCRIPTION , CODE ,LIST_SEQ FROM REFERENCE_CODES WHERE DOMAIN = 'CONTACTS' AND ('' = 'ENTER-QUERY' OR ACTIVE_FLAG = 'Y' ) ORDER BY 3
}

OCUOCCUP_FIND_RGRELATIONSHIPS {
 	SELECT DESCRIPTION , CODE ,LIST_SEQ FROM REFERENCE_CODES WHERE DOMAIN = 'RELATIONSHIP' /* AND PARENT_CODE = ::NBTCONTACTCODE */ AND ('' = 'ENTER-QUERY' OR ACTIVE_FLAG = 'Y' ) AND CODE IN ( SELECT CPT.RELATIONSHIP_TYPE                     FROM CONTACT_PERSON_TYPES CPT                    WHERE CPT.CONTACT_TYPE = :contactCode                     AND CPT.ACTIVE_FLAG = 'Y'                      AND EXPIRY_DATE IS NULL  ) ORDER BY 3
}

OCUOCCUP_RPOTHEROCCUPANTS_FIND_RP_OTHER_OCCUPANTS {
 	SELECT RP_OTHER_OCCUPANT_ID ,RELEASE_PLAN_ID ,OFFENDER_CONTACT_PERSON_ID ,CONTACTED_FLAG ,RP_COMMENT ,PRIMARY_FLAG ,CONTACT_PHONE ,EXT_NO ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG,FORMAT   FROM RP_OTHER_OCCUPANTS   where RELEASE_PLAN_ID = :releasePlanId order by (SELECT p.last_name||', '||p.first_name  FROM persons    p,
  offender_contact_persons ocp  WHERE ocp.person_id        = p.person_id
 AND ocp.offender_contact_person_id = rp_other_occupants.offender_contact_person_id)
  
}
OCUOCCUP_RPOTHEROCCUPANTS_INSERT_RP_OTHER_OCCUPANTS {
	insert into RP_OTHER_OCCUPANTS(RP_OTHER_OCCUPANT_ID , RELEASE_PLAN_ID , OFFENDER_CONTACT_PERSON_ID , CONTACTED_FLAG , RP_COMMENT , PRIMARY_FLAG , CONTACT_PHONE , EXT_NO , CREATE_DATETIME , CREATE_USER_ID , MODIFY_DATETIME , SEAL_FLAG,FORMAT ) values(NEXTVAL('RP_OTHER_OCCUPANT_ID'), :releasePlanId , :offenderContactPersonId , :contactedFlag , :rpComment , :primaryFlag , :contactPhone , :extNo , current_timestamp , :createUserId , current_timestamp , :sealFlag ,:format)
}

OCUOCCUP_RPOTHEROCCUPANTS_UPDATE_RP_OTHER_OCCUPANTS {
	update RP_OTHER_OCCUPANTS set RP_OTHER_OCCUPANT_ID = :rpOtherOccupantId , RELEASE_PLAN_ID = :releasePlanId , OFFENDER_CONTACT_PERSON_ID = :offenderContactPersonId , CONTACTED_FLAG = :contactedFlag , RP_COMMENT = :rpComment , PRIMARY_FLAG = :primaryFlag , CONTACT_PHONE = :contactPhone , EXT_NO = :extNo , MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :modifyUserId , SEAL_FLAG = :sealFlag,FORMAT=:format where RP_OTHER_OCCUPANT_ID = :rpOtherOccupantId 
}

OCUOCCUP_RPOTHEROCCUPANTS_DELETE_RP_OTHER_OCCUPANTS { 
	DELETE FROM RP_OTHER_OCCUPANTS where RP_OTHER_OCCUPANT_ID  = :rpOtherOccupantId
}


OCUOCCUP_RP_OTHER_OCCUPANTS_POSTQUERY_ {
	SELECT P.LAST_NAME||', '||P.FIRST_NAME PERSON_NAME, RC.DESCRIPTION                  CONTACT_DESC, RC.CODE                         CONTACT_CODE, RC1.DESCRIPTION                 RELATIONSHIP_DESC, RC1.CODE                        RELATIONSHIP_CODE, TRUNC(MONTHS_BETWEEN(SYSDATE,P.BIRTHDATE)/12) AGE, DECODE(:RP_OTHER_OCCUPANTS.CONTACTED_FLAG,'Y','YES','N','NO',NULL) CONTACTED--, --PH.PHONE_NO, --PH.EXT_NO FROM --PHONES                   PH, PERSONS                  P, OFFENDER_CONTACT_PERSONS OCP, REFERENCE_CODES          RC, REFERENCE_CODES          RC1 WHERE --PH.OWNER_CLASS(+)    = 'PER' --AND PH.OWNER_ID(+)       = P.PERSON_ID AND RC.DOMAIN            = 'CONTACTS' AND RC.CODE              = OCP.CONTACT_TYPE AND RC1.DOMAIN           = 'RELATIONSHIP' AND RC1.CODE             = OCP.RELATIONSHIP_TYPE AND OCP.OFFENDER_BOOK_ID = :OFFENDERBOOKID AND OCP.PERSON_ID        = P.PERSON_ID AND OCP.OFFENDER_CONTACT_PERSON_ID = :OFFENDERCONTACTPERSONID
}

OCUOCCUP_OCUOCCUP_KEYEXIT_ {
	SELECT 'Y' FROM RP_OTHER_OCCUPANTS ROO WHERE RELEASE_PLAN_ID = :RELEASEPLANID
}

OCUOCCUP_OCUOCCUP_KEYEXIT_ {
	SELECT 'Y' FROM RP_OTHER_OCCUPANTS ROO WHERE RELEASE_PLAN_ID = :RELEASEPLANID AND PRIMARY_FLAG = 'Y'
}

OCUOCCUP_OCUOCCUP_POSTFORMSCOMMIT_ {
	SELECT 'Y' FROM RP_OTHER_OCCUPANTS WHERE RELEASE_PLAN_ID = :RELEASEPLANID AND PRIMARY_FLAG = 'Y'
}

OCUOCCUP_CHECK_BLOCK_ERRORS_ {
	SELECT TABLE_NAME FROM ALL_CONSTRAINTS WHERE OWNER = 'OMS_OWNER' AND CONSTRAINT_TYPE = 'R' AND CONSTRAINT_NAME = :P_CONSTRAINT_NAME
}

OCUOCCUP_CREATE_FORM_GLOBALSCREATE_FORM_GLOBALS {
	SELECT DESCRIPTION  FROM OMS_MODULES WHERE MODULE_NAME = :V_FORM_NAME
}

FINF_V_ADDRESSES {
SELECT city_code,country_code,city_name,prov_state_code,street,zip_postal_code,suite_number,street_number,street_direction,
VA.ADDRESS_ID   FROM ADDRESSES VA where VA.ADDRESS_ID = :addressId
}

OCUOCCUP_RPOTHEROCCUPANTS_PRE_ADDRESSES_INSERT{
INSERT INTO addresses (address_id, owner_class, owner_id, primary_flag, mail_flag, city_code, country_code, city_name, prov_state_code, street, zip_postal_code, suite_number, street_number, street_direction, create_user_id, create_datetime, modify_datetime ) values(NEXTVAL('ADDRESS_ID'), 'PER', :ownerId, 'N', 'N', :cityCode, :countryCode, :cityName, :provStateCode, :street, :zipPostalCode, :suiteNumber, :streetNumber, :streetDirection, :createUserId, current_timestamp, current_timestamp) }

PRE_INSERT_ADDRESS_FLAG{
 SELECT 'Y' FROM addresses ad1, addresses ad2 WHERE ad1.address_id = :addressId AND ad2.owner_class = 'PER' AND ad2.owner_id = :ownerId AND ad1.country_code = ad2.country_code AND ad1.city_code = ad2.city_code AND UPPER(COALESCE(ad1.suite_number,'XXXXX')) = UPPER(COALESCE(ad2.suite_number,'XXXXX')) AND UPPER(COALESCE(ad1.street_number,'XXXXX')) = UPPER(COALESCE(ad2.street_number,'XXXXX')) AND UPPER(COALESCE(ad1.street,'XXXXX')) = UPPER(COALESCE(ad2.street,'XXXXX')) AND substr(ad1.zip_postal_code,1,5) = substr(ad2.zip_postal_code,1,5) 
}

OCUOCCUP_OFFENDER_CONTACT_PERSONS_INSERT {
INSERT INTO offender_contact_persons (offender_contact_person_id, offender_book_id, contact_type, relationship_type, person_id, active_flag, create_user_id, create_datetime ,modify_datetime ) VALUES(:offenderContactPersonId, :offenderBookId, :contactType, :relationshipType, :personId, 'Y', :createUserId,current_timestamp,current_timestamp )
}

OCUOCCUP_OFFENDER_CONTACT_PERSONS_PRE_INSERT{
SELECT NEXTVAL('offender_contact_person_id')	    FROM dual
}