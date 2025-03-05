\o 'occupancy_original_vs_corrections_v500981.txt'

select ail.internal_location_id, ail.description, ail.no_of_occupant as corrected_no_of_occupant, ailc.no_of_occupant as original_no_of_occupant, ail.parent_internal_location_id  
  from oms_owner.agency_internal_locations_cpy_v500981 ailc
 inner join oms_owner.agency_internal_locations ail on ail.internal_location_id = ailc.internal_location_id
where ail.no_of_occupant != ailc.no_of_occupant
order by ail.description;

\o
