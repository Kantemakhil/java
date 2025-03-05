GET_OFF_ID_CURSOR{
SELECT OFFENDER_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :offBookId
}

EMPTY_SEARCH_CUR{
 SELECT COUNT(*) FROM TEMP_LIVING_UNIT_PROFILES
}

P_REFCURSOR_SELECT_OPERATION{
SELECT lu.internal_location_id living_unit_id, lu.description description, lu.capacity capacity, lu.no_of_occupant no_of_occupant, lu.capacity - lu.no_of_occupant no_of_available, substr(tag_int_loc.operation_flag(lu.internal_location_id), 1, 1) unit_at_capacity, decode(:offenderId, NULL, 'N', decode(non_association.check_non_association(:offenderId, lu.internal_location_id), 'Y', 'N', 'Y')) prisoner_conflict, decode(:offenderBookId, NULL, 'N', decode(omuavbed.check_security(:offenderBookId, lu.internal_location_id), 'Y', 'N' , 'Y')) security_conflict, omuavbed.chk_cs_conflict(:offenderBookId, lu.internal_location_id, lu.no_of_occupant) cell_sharing_conflict FROM agency_internal_locations lu WHERE lu.agy_loc_id = :agyLocId AND ( SELECT decode(COUNT(*), 0, 'Y', 'N') lowest_level_flag FROM agency_internal_locations ail2 WHERE ail2.parent_internal_location_id = lu.internal_location_id ) = 'Y' AND ( :livingUnitType IS NULL OR lu.unit_type = :livingUnitType ) AND ( :level1Code IS NULL OR tag_int_loc.level_code(lu.description, 1) = :level1Code ) AND ( :level2Code IS NULL OR tag_int_loc.level_code(lu.description, 2) = :level2Code ) AND ( :level3Code IS NULL OR tag_int_loc.level_code(lu.description, 3) = :level3Code ) AND ( :level4Code IS NULL OR tag_int_loc.level_code(lu.description, 4) = :level4Code ) AND lu.active_flag = 'Y' AND lu.capacity > lu.no_of_occupant
}

OFF_ID_SELECT_OPERATION{
SELECT OFFENDER_ID FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = :offenderBookId
}

GET_LIV_UNIT_INFO{
select
	lu.living_unit_id living_unit_id,
	lu.description description,
	lu.capacity capacity,
	lu.no_of_occupant no_of_occupant,
	lu.capacity - lu.no_of_occupant no_of_available,
	reach_oper_capacity_flag unit_at_capacity,
	case
		when coalesce(:offenderId::text, '') = '' then 'N'
		else
		case
			when non_association_check_non_association(coalesce(:offenderId::bigint, null::bigint),
			lu.living_unit_id)= 'Y' then 'N'
			else 'Y'
		end
	end prisoner_conflict,
	case
		when coalesce(:offenderBookId::text, '') = '' then 'N'
		else
		case
			when non_association_check_security(coalesce(:offenderBookId::bigint, null::bigint),
			lu.living_unit_id)= 'Y' then 'N'
			else 'Y'
		end
	end security_conflict,
	omuavbed_chk_cs_conflict(coalesce(:offenderBookId::bigint, null::bigint),
	lu.living_unit_id,
	lu.no_of_occupant) cell_sharing_conflict
from
	living_units lu
where
	lu.living_unit_id = :livingUnitId
}

GET_OCCUPANTS{
SELECT bkg.offender_book_id offender_book_id ,off.offender_id_display offender_id_display ,off.last_name last_name ,off.first_name first_name 
FROM offender_bookings bkg, offenders off WHERE bkg.offender_id = off.offender_id AND bkg.offender_book_id in
(SELECT bkg2.offender_book_id FROM offender_bookings bkg2 WHERE bkg2.living_unit_id in (
(select living_unit_id  from living_units lu where parent_living_unit_id =((select case when ((select parent_living_unit_id  from living_units lu where living_unit_id = :livingUnitId) is  not  null) 
then (select parent_living_unit_id  from living_units lu where living_unit_id = :livingUnitId) 
else  :livingUnitId end)))) or bkg2.living_unit_id=:livingUnitId)
}

RESERVED_BED_CUR{
 SELECT count(*) FROM reserve_bed_locations WHERE living_unit_id = :livingUnitId AND  reserve_until_date > current_timestamp --AND offender_id <> :offId
}

GET_LV_OFF_ID{
SELECT root_offender_id FROM offender_bookings WHERE offender_book_id = :p_offender_book_id
}

GET_OFFENDER_ID{
 SELECT offender_id INTO lv_offender_id FROM offender_bookings WHERE offender_book_id = :p_offender_book_id
}

GET_OFF_DETAILS{
SELECT o.offender_id_display offender_id_display, o.last_name last_name, o.first_name first_name FROM offenders o WHERE o.offender_id = :p_offender_id
}
GET_OFFENDERID_BY_PASSING_BOOKID
{
select offender_id  from offender_bookings ob where offender_book_id =:offenderBookId
}

