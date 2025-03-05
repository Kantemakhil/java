package net.syscon.s4.pkgs.tag_main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_main.TagMainRepository;
import net.syscon.s4.pkgs.tag_main.TagMainService;

@Service
public class TagMainServiceImpl implements TagMainService {

	@Autowired
	private TagMainRepository tagMainRepository;

	private static String OCDINTAK = "OCDINTAK";

	@Override
	public String defBkgWhere(final String pFormName) {
		String pDefWhere = null;
		try {
			if (OCDINTAK.equals(pFormName)) {
				pDefWhere = null;
			} else {
				pDefWhere =

						" ( ( v_header_block.OFFENDER_BOOK_ID IN " + "( SELECT vhb2.OFFENDER_BOOK_ID "
								+ "FROM offender_bookings vhb2 "
								+ "WHERE vhb2.root_offender_id = v_header_block.root_offender_id "
								+ "AND vhb2.active_flag='Y' " + "AND EXISTS ( SELECT cal.agy_loc_id "
								+ "FROM caseload_agency_locations cal, " + "caseloads csld "
								+ "WHERE cal.caseload_id = :caseload_id " + "AND csld.caseload_type = 'INST' "
								+ "AND csld.caseload_id = cal.caseload_id " + "AND cal.agy_loc_id = vhb2.agy_loc_id "
								+ "AND :caseload_type = 'INST')) " + "OR ( v_header_block.OFFENDER_BOOK_ID = "
								+ "( SELECT MAX(vhb2.OFFENDER_BOOK_ID) " + "FROM offender_bookings vhb2 "
								+ "WHERE vhb2.root_offender_id = v_header_block.root_offender_id "
								+ "AND EXISTS ( SELECT cal.agy_loc_id " + "FROM caseload_agency_locations cal, "
								+ " caseloads csld " + "WHERE cal.caseload_id = :caseload_id "
								+ "AND csld.caseload_type = 'INST' " + "AND csld.caseload_id = cal.caseload_id "
								+ "AND cal.agy_loc_id = vhb2.agy_loc_id " + "AND :caseload_type = 'INST') "
								+ "AND NOT EXISTS ( SELECT 1 " + "FROM OFFENDER_BOOKINGS BK, "
								+ "caseload_agency_locations cal, " + "caseloads csld "
								+ "WHERE BK.ROOT_OFFENDER_ID = vhb2.ROOT_OFFENDER_ID " + "AND BK.ACTIVE_FLAG = 'Y' "
								+ "AND csld.caseload_type = 'INST' " + "AND csld.caseload_id = cal.caseload_id "
								+ "AND cal.agy_loc_id = BK.agy_loc_id " + "AND :caseload_type = 'INST') ))) "
								+ "OR (  v_header_block.offender_book_id = " + "( SELECT MAX(vhb2.offender_book_id) "
								+ "FROM offender_bookings vhb2 "
								+ "WHERE vhb2.root_offender_id = v_header_block.root_offender_id "
								+ "AND ( vhb2.community_active_flag = 'Y' " + "OR (NOT EXISTS " + "( SELECT 'X' "
								+ "FROM offender_bookings vhb3 " + "WHERE vhb3.community_active_flag = 'Y' "
								+ "AND vhb3.root_offender_id = vhb2.root_offender_id))) "
								+ "AND ((vhb2.intake_agy_loc_id <> 'CLOSE' " + "AND EXISTS " + "( SELECT 'X' "
								+ "FROM offender_booking_agy_locs locs, " + " caseload_agency_locations cal1 "
								+ "WHERE cal1.caseload_id =:caseload_id "
								+ "AND cal1.agy_loc_id = locs.agy_loc_id "
								+ "AND locs.offender_book_id = vhb2.offender_book_id "
								+ "AND locs.removed_date IS  NULL " + "AND :caseload_type  = 'COMM')) "
								+ "OR (vhb2.intake_agy_loc_id = 'CLOSE' " + "AND EXISTS " + "( SELECT 'X' "
								+ "FROM offender_booking_agy_locs locs, " + "caseload_agency_locations cal1 "
								+ "WHERE cal1.caseload_id = :caseload_id "
								+ "AND cal1.agy_loc_id = locs.agy_loc_id "
								+ "AND locs.offender_book_id = vhb2.offender_book_id "
								+ "AND :caseload_type  = 'COMM'))))))";
			}
		} catch (Exception e) {
			pDefWhere = null;
		}
		return pDefWhere;

	}

}