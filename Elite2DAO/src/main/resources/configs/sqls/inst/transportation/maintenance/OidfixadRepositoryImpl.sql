
OIDFIXAD_FA_FIND_FIXED_ASSETS {
SELECT *,(SELECT count(*)
     	FROM	 scheduled_trip_assignments s,
          	 select_vehicles_v1 v
    	WHERE  s.assigned_id = v.vehicle_id
      AND 	 s.assignment_type = 'VEHICLE'
      AND 	 v.asset_id = FIXED_ASSETS.asset_id) as ASSIGN_COUNT FROM FIXED_ASSETS order By ASSET_CLASS, ASSET_TYPE ASC 
	
}
OIDFIXAD_FA_INSERT_FIXED_ASSETS {
INSERT INTO FIXED_ASSETS (ASSET_ID,ASSET_CLASS, ASSET_LOCATION_ID, ASSET_TYPE,DESCRIPTION,MAKE,MODEL,STATUS,SERIAL_NO,YEAR,COLOR,COST,LIFE_SPAN_CODE,MANUFACTURE_DATE,ACQUISITION_DATE,ACQUISITION_CORPORATION_ID,ACQUISITION_PERSON_ID,DISPOSITION_DATE,DISPOSITION_PRICE,DISPOSITION_TYPE,DISPOSITION_CORPORATE_ID,DISPOSITION_PERSON_ID,DISPOSITION_COMMENT_TEXT,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,SEAL_FLAG) 
                   VALUES( :assetId,:assetClass,:assetLocationId,:assetType,:description,:make,:model,:status,:serialNo,:year,:color,:cost,:lifeSpanCode,:manufactureDate,current_timestamp,:acquisitionCorporationId,:acquisitionPersonId,:dispositionDate,:dispositionPrice,:dispositionType,:dispositionCorporateId,:dispositionPersonId,:dispositionCommentText,current_timestamp,:createUserId,NULL,:sealFlag)
}

OIDFIXAD_FA_UPDATE_FIXED_ASSETS {
UPDATE FIXED_ASSETS set ASSET_CLASS = :assetClass , ASSET_TYPE = :assetType , MAKE = :make  , MODEL = :model , DESCRIPTION = :description , SERIAL_NO = :serialNo,  YEAR = :year ,MODIFY_DATETIME = current_timestamp, MODIFY_USER_ID = :createUserId ,COLOR =:color WHERE ASSET_ID =:assetId
}

OIDFIXAD_FA_DELETE_FIXED_ASSETS { 
	DELETE FROM FIXED_ASSETS WHERE ASSET_ID=:assetId
}

OIDFIXAD_VEH_FIND_VEHICLES {
	select * from vehicles WHERE ASSET_ID=:assetId

}
OIDFIXAD_VEH_INSERT_VEHICLES {
INSERT INTO VEHICLES(ASSET_ID,VEHICLE_ID,PLATE_NO,FUEL_TYPE,LICENCE_CLASS,CAPACITY,REMARKS,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME, SEAL_FLAG,OPTIMAL_CAPACITY) 
            VALUES(:assetId,:vehicleId,:plateNo,:fuelType,:licenceClass,:capacity,:remarks,current_timestamp,:createUserId,NULL,:sealFlag,:optimalCapacity)
}

OIDFIXAD_VEH_UPDATE_VEHICLES {
 UPDATE VEHICLES set plate_no = :plateNo ,licence_class = :licenceClass , fuel_type = :fuelType , optimal_capacity = :optimalCapacity , MODIFY_DATETIME = current_timestamp, modify_user_id = :modifyUserId ,capacity = :capacity where vehicle_id = :vehicleId
}

OIDFIXAD_GEN_ASSET_ID {
 select nextval(:seq) from dual
 }

 L_VEHICLE_ID_COUNT {
 select count(*)  from vehicles where vehicle_id = :vehicleId
 }

