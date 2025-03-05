DESCP_CUR{
      SELECT living_unit_description FROM v_name_search2_fn(:userId) v_name_search2 WHERE offender_book_id = :p_offender_bkg_id
}