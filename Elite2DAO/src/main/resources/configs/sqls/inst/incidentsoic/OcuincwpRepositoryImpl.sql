OCUINCWP_INSERT_VALUES_OFFENDER_WEAPONS{
     INSERT INTO INCIDENT_OFFENDER_WEAPONS
      (AGENCY_INCIDENT_ID, 
      PARTY_SEQ,
      WEAPONS_USED, 
      WEAPONS_DETAIL,
      create_user_id ,
      create_datetime,
      modify_datetime
      ) 
      VALUES (
      :agencyIncidentId, 
      :partySeq, 
      :weaponsUsed,
      :weaponsDetail,
      :createUserId,
      current_timestamp,
      null
      )
}

OCUINCWP_DATA_OFFENDER_WEAPONS{

SELECT IWO.AGENCY_INCIDENT_ID,
		 IWO.PARTY_SEQ,
		 IWO.WEAPONS_USED,
		 IWO.WEAPONS_DETAIL,
		 IWO.CREATE_DATETIME,
		 IWO.CREATE_USER_ID,
		 IWO.MODIFY_DATETIME,
		 IWO.MODIFY_USER_ID,
		 IWO.SEAL_FLAG
		 from INCIDENT_OFFENDER_WEAPONS IWO
		  
		
}

OCUINCWP_OFFEDNERWEAPONE_UPDATE{
    
update
	INCIDENT_OFFENDER_WEAPONS
set
	WEAPONS_USED =:weaponsUsed,
	WEAPONS_DETAIL =:weaponsDetail,
	modify_user_id =:modifyUserId,
	modify_datetime = current_timestamp 
where
	AGENCY_INCIDENT_ID =:agencyIncidentId
	and WEAPONS_USED =:weaponsUsed
	and PARTY_SEQ =:partySeq
	
	}

	OCUINCWP_OFFENDER_WEAPONE_DELETE
	{
	DELETE FROM INCIDENT_OFFENDER_WEAPONS  
    WHERE AGENCY_INCIDENT_ID =:agencyIncidentId  and PARTY_SEQ =:partySeq AND WEAPONS_USED=:weaponsUsed
	}