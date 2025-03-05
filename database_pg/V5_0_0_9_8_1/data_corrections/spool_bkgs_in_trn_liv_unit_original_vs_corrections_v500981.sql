\o 'bkgs_in_trn_liv_unit_original_vs_corrections_v500981.txt'

select offbkgs.offender_book_id, offbkgs.booking_no, offbkgs.living_unit_id as corrected_living_unit_id, offbkgs_cpy.living_unit_id as original_living_unit_id
  from oms_owner.offender_bookings_partial_cpy_500981 offbkgs_cpy
 inner join oms_owner.offender_bookings offbkgs on offbkgs.offender_book_id = offbkgs_cpy.offender_book_id;

\o