
OIUSELVE_SELECTVEHICLES_FIND_SELECT_VEHICLES_V1 {
 	select
	*
from
	SELECT_VEHICLES_V1
where
	((:formModuleName= 'OIMSTRIP'
		and status not in (
		select
			rc.code
		from
			reference_codes rc
		where
			rc.domain = 'FA_STATUS'
			and rc.parent_code = 'INACT' )
		and vehicle_id not in (
		select
			sta.assigned_id
		from
			scheduled_trip_assignments sta
		where
			sta.scheduled_trip_id = :scheduledTripId
			and sta.assignment_type = 'VEHICLE' ))
	or (:formModuleName= 'OIDSLTRI'
		and vehicle_id in (
		select
			sta.assigned_id
		from
			scheduled_trip_assignments sta
		where
			sta.scheduled_trip_id = :scheduledTripId
			and sta.assignment_type = 'VEHICLE' )))
}