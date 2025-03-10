CREATE OR REPLACE FUNCTION oms_owner.tag_assessment_chk_occupant_csa(p_liv_unit_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
DECLARE
      occs_cur CURSOR FOR
         select
	'Y'
from
	DUAL
where
	exists (
	select
		'Y'
	from
		OFFENDER_BOOKING_DETAILS
	where
		cell_sharing_alert_flag = 'Y'
		and offender_book_id in (
		select
			offender_book_id
		from
			OFFENDER_BOOKINGS
		where
			living_unit_id in (select living_unit_id  from living_units_new lu where parent_living_unit_id =((select case when ((select parent_living_unit_id  from living_units_new lu where living_unit_id = p_liv_unit_id) is  not  null) 
then (select parent_living_unit_id  from living_units_new lu where living_unit_id = p_liv_unit_id) 
else  p_liv_unit_id end))) or living_unit_id= p_liv_unit_id) );
      lv_conflict   varchar(1 ) := 'N';

BEGIN
      OPEN occs_cur;
      FETCH occs_cur
       INTO lv_conflict;
      CLOSE occs_cur;
     if lv_conflict is null then
     return 'N';
     else
      RETURN( lv_conflict );
     end if;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$function$
;