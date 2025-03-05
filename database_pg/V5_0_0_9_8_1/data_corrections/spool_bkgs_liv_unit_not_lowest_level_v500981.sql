\o 'bkgs_liv_unit_not_lowest_level_v500981.txt'

select offender_book_id, booking_no, living_unit_id 
from oms_owner.offender_bookings 
where living_unit_id IS NOT NULL 
  and exists (select 1 from oms_owner.agency_internal_locations where oms_owner.agency_internal_locations.parent_internal_location_id = oms_owner.offender_bookings.living_unit_id);

\o