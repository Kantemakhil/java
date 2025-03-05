CREATE OR REPLACE FUNCTION oms_owner.non_association_chk_na_liv_unit_conflict(p_off_liv_unit_id bigint, p_occ_liv_unit_id bigint, p_ns_type character varying)
 RETURNS boolean
 LANGUAGE plpgsql
AS $function$ declare conflict_cur cursor for with recursive cte as (
select
	'Y',
	ail.parent_internal_location_id,
	ail.internal_location_id 
from
	agency_internal_locations ail
where
	internal_location_id = p_off_liv_unit_id
union all
select
	'Y',
	ail.parent_internal_location_id,
	ail.internal_location_id 
from
	agency_internal_locations ail
join cte c on
	(c.parent_internal_location_id = ail.internal_location_id) )
select
	'Y'
from
	cte
where
	exists (
	select
		'Y'
	from
		agy_int_loc_profiles ail
	where
		internal_location_id = cte.internal_location_id
		and int_loc_profile_type = 'NON_ASSO_TYP'
		and int_loc_profile_code = p_ns_type)
		and cte.internal_location_id in(with recursive cte as (
		select
			olu.internal_location_id,
			olu.parent_internal_location_id
		from
			agency_internal_locations olu
		where
			olu.internal_location_id = p_occ_liv_unit_id
	union all
		select
			olu.internal_location_id,
			olu.parent_internal_location_id
		from
			agency_internal_locations olu
		join cte c on
			(c.parent_internal_location_id = olu.internal_location_id) )
		select
			internal_location_id
		from
			cte);

lv_dummy VARCHAR (1) ;

begin lv_dummy := null ;

open conflict_cur ;

fetch conflict_cur
into
	lv_dummy ;

close conflict_cur ;

if lv_dummy = 'Y' then return (true) ;
else return (false) ;
end if ;
end;

$function$
;