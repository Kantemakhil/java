OIMROUTE_ROUTES_FIND_ROUTES {
select
	ROUTE_NAME,
	DESCRIPTION,
	NO_STOPS,
	START_AGY_LOC_ID,
	STOP_AGY_LOC_ID,
	ACTIVE_FLAG,
	EXPIRY_DATE,
	CREATE_DATETIME
from
	ROUTES r
order by
	ROUTE_NAME desc
}
OIMROUTE_ROUTES_INSERT_ROUTES {
	INSERT INTO ROUTES(ROUTE_NAME,DESCRIPTION,NO_STOPS,START_AGY_LOC_ID,STOP_AGY_LOC_ID,ACTIVE_FLAG,EXPIRY_DATE,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID) VALUES(:routeName,:description,:noStops,:startAgyLocId,:stopAgyLocId,:activeFlag,:expiryDate,CURRENT_TIMESTAMP,:createUserId,NULL,NULL)
}
OIMROUTE_ROUTES_UPDATE_ROUTES {
	UPDATE ROUTES set DESCRIPTION=:description , MODIFY_DATETIME =CURRENT_TIMESTAMP,MODIFY_USER_ID= :modifyUserId  where ROUTE_NAME =:routeName
}
OIMROUTE_ROUTES_UPDATE_ROUTES1{
 UPDATE ROUTES SET NO_STOPS = :noStops, 
		           START_AGY_LOC_ID=:startAgyLocId,
			       STOP_AGY_LOC_ID = :stopAgyLocId,
			       ACTIVE_FLAG = :activeFlag,
			       EXPIRY_DATE = :expiryDate,
			       MODIFY_DATETIME =CURRENT_TIMESTAMP,
			       MODIFY_USER_ID= :modifyUserId
		           WHERE route_name = :routeName
}
OIMROUTE_ROUTESTOPDETAILS_FIND_ROUTE_STOP_DETAILS {
 	SELECT LEG_ID as temp_legid,LEG_SEQ as temp_legseq,AGY_LOC_ID as temp_agylocid,LEG_ID,LEG_SEQ,AGY_LOC_ID,COUNT_FLAG,INTAKE_LOC_FLAG,OVERNIGHT_FLAG,ACTIVE_FLAG,EXPIRY_DATE,CREATE_DATETIME  FROM route_stop_details WHERE ROUTE_NAME=:routeName ORDER BY route_name asc,leg_id asc,leg_seq asc;
}
OIMROUTE_ROUTESTOPDETAILS_INSERT_ROUTE_STOP_DETAILS {
	INSERT INTO ROUTE_STOP_DETAILS(ROUTE_NAME,LEG_ID,LEG_SEQ,AGY_LOC_ID,ACTIVE_FLAG,EXPIRY_DATE,CREATE_USER_ID,CREATE_DATETIME,MODIFY_DATETIME,MODIFY_USER_ID) VALUES(:routeName,:legId,:legSeq,:agyLocId,:activeFlag,:expiryDate,:createUserId,CURRENT_TIMESTAMP,NULL,NULL)
}
OIMROUTE_ROUTESTOPDETAILS_UPDATE_ROUTE_STOP_DETAILS{
update route_stop_details
set
    leg_id =:legId,
    leg_seq =:legSeq,
    agy_loc_id =:agyLocId,
	active_flag =:activeFlag,
	EXPIRY_DATE =:expiryDate,
	MODIFY_USER_ID =:modifyUserId,
	MODIFY_DATETIME = CURRENT_TIMESTAMP
where
	route_name =:routeName
	and leg_id =:tempLegid
	and leg_seq =:tempLegseq
	and agy_loc_id =:tempAgylocid
}
OIMROUTE_AGYLOCFEEDDETAILS_FIND_AGY_LOC_FEED_DETAILS{
    SELECT FEED_AGY_LOC_ID , (SELECT DESCRIPTION
    FROM   AGENCY_LOCATIONS
    WHERE AGY_LOC_ID = FEED_AGY_LOC_ID) as description FROM AGY_LOC_FEED_DETAILS WHERE AGY_LOC_ID=:AgLocId and active_flag = 'Y' ORDER BY FEED_AGY_LOC_ID ASC
}
OIMROUTE_FIND_RGAGYLOC{
SELECT AGY_LOC.AGY_LOC_ID AS CODE , AGY_LOC.DESCRIPTION AS DESCRIPTION FROM AGENCY_LOCATIONS AGY_LOC  WHERE AGY_LOC.DEACTIVATION_DATE IS NULL    AND AGY_LOC.AGY_LOC_ID NOT IN ('OUT' , 'TRN' ) ORDER BY AGY_LOC_ID;
}
OIMROUTE_AGYLOCFEEDDETAILS_FIND_DESCRIPTION_IN_AGENCYLOCATION{
SELECT DESCRIPTION
    FROM   AGENCY_LOCATIONS
    WHERE AGY_LOC_ID = :P_AGY_LOC_ID;
}
OIMROUTE_NO_STOPS{
SELECT COUNT(*) 
			FROM route_stop_details
			WHERE route_name = :vRoute
				and active_flag = 'Y'
}
OIMROUTE_ACTIVE_CUR{
SELECT count(*)
			FROM route_stop_details
			where route_name = :vRoute
				and active_flag = 'N'
				and not exists
				       (select 1
				          from route_stop_details rsdx
				         where rsdx.route_name = :vRoute
				           and rsdx.active_flag = 'Y'
				       )
}
OIMROUTE_STOP_LOCATION{
SELECT agy_loc_id 
			FROM route_stop_details 
			WHERE leg_seq = (SELECT max(leg_seq) FROM route_stop_details 
												WHERE route_name = :vRoute
				  							AND leg_id = (SELECT max(leg_id) 
				  														FROM route_stop_details 
				  														WHERE route_name = :vRoute
				  																	AND active_flag = 'Y')
				  							AND active_flag = 'Y')
				AND leg_id = (SELECT max(leg_id) 
											FROM route_stop_details 
				  						WHERE route_name = :vRoute
				  								AND active_flag = 'Y')
				AND route_name = :vRoute
				AND active_flag = 'Y'
}
OIMROUTE_START_LOCATION{
SELECT agy_loc_id 
			FROM route_stop_details 
			where route_name = :vRoute  AND active_flag = 'Y' and  leg_seq = (SELECT min(leg_seq) FROM route_stop_details WHERE route_name = :vRoute AND leg_id = (SELECT min(leg_id) 
				  														FROM route_stop_details 
				  														WHERE route_name = :vRoute
				  																	AND active_flag = 'Y') AND active_flag = 'Y')
				AND  leg_id = (SELECT min(leg_id) FROM route_stop_details WHERE route_name = :vRoute AND active_flag = 'Y')
}