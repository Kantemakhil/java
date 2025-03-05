package net.syscon.s4.pkgs.trust_main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.pkgs.trust_main.TrustMainRepository;
import net.syscon.s4.pkgs.trust_main.TrustMainService;

/*
 * Below comments are copied from package TRUST_MAIN
|| Purpose: This package controls the default where for Financial Modules
||
|| MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
|| -------------------------------------------------------------------------------------
|| Person     Version    Date          Comments
|| ---------  --------   ---------     ----------------------------------------------------
|| Alexey     2.17      22-Sep-2015   #25967: Changed default_where from v_trust_header for OTINAMES module  
|| Rajshree   2.16      27-May-2013   #20278:Moved from Washington to Baseline
|| Anurag     2.15.1.2   25-May-2012   Modified procedure Trust_Header_Query to show inactive offenders as well when screen OTINAMES is called from main menu for HPQC#15746.
|| Abu        2.15.1.0-1   15-Mar-2012   Added new procedure Trust_Header_Query which is called from Tagu4apl and
||                                       Otinames.fmb/pll instead of def_bkg_trst.
|| Nasir        2.14    25-MAR-2011  Modified def_bkg_trst and change v_trust_header from offender_bookings in sub query.
|| Nasir        2.13    22-MAR-2011  Modified get_offender for Centralize Commissary add canteen ID.
|| Nasir        2.12    18-MAR-2011  Modified the default where in def_pkg_trst for Centralize Commissary.
|| Mayank    2.11    16-DEC-2010  Modified the default where in def_pkg_trst for HPQC# 4592,4593
|| Mayank    2.10    15-NOV-2010  Modified the default where in def_pkg_trst for HPQC#  3833
|| Johnny     2.9      20-MAR-2008  Retreieve root_offender_id instead of offender_id in get_offender function
|| Nancy      2.8      14-Mar-2008  ready to apply to MN environment for Multi trust Single commissary configuration
|| Nancy      2.7      18-Feb-2008  Merge 6i product 1.8 version with 10g 2.5 version according to Abu's suggestion
||                                  to get a better base for 1og product which should cover
||                                  all different caseload configuration; and use v_trust_header
||                                  instead of v_trust_header_a becase in 1og product, v_trust_header and
||                                  v_trust_header_a has been merge together.
||                                  Added quotation to get_offender function for compatibility with string variables.
|| JOHNNY     2.6      26-FEB-2008  Added get_offender funtion for use in OTDRECEi and OTDDISBU form in retrieving offender.
|| Jacques    1.8      21-NOV-2005  Modified where clause for the header blocks of OTDCNTAC to
||                                  be queryable on any booking no, and not only the latest
|| Patrick    1.7      28-OCT-2003  Modified where clause for the header blocks of these screens:
||                                  OODOSALE, OODORETU, OOIOSALE, OOMOCRES, OODOSILI.
||                                  Added decode statement for selecting active booking first if
||                                  there is an existing one instead of always selecting the max booking
|| Herbert    1.6      10-MAR-2003  For Multi-Trust;Single-Commissary, fixed the multiple
||                                  restrival problem for (1) internal transfer with new
||                                  booking (this should not occur in normal situation
||                                  unless for corrupt records created by older version
||                                  of admission screen), (2) released offenders, (3)
||                                  re-admitted offenders with new booking
|| Herbert    1.5      20-FEB-2003  For Multi-Trust;Single-Commissary, if an offender has
||                                  been released (status = 'OUT' then we will refer to
||                                  offender_external_movements table for the offender's
||                                  last agency location
|| Herbert    1.4      30-JAN-2003  For Single-Trust;Multi-Commissary, agency location
||                                  is not a filter for v_trust_header_a in any Post-Sale
||                                  forms.
||                                  In these Post-Sale forms, the header should include
||                                  offenders with previous sales in global.caseload_id.
|| Patrick    1.3      05-NOV-2002  Changed the where clause to have similar condition as tag_main
|| Patrick    1.2      31-OCT-2002  Changed procedure def_bkg_trst for the change of v_trust_header_a columns
|| Patrick    1.1      30-OCT-2002  Changed procedure def_where_v_phead to change the block where
||                                  clause of the header of all payroll screens for centralized
||                                  Trust payroll caseload logic.
|| Herbert    6.1.0.4  14-AUG-2002  Procedure Def_Bkg_Trst modified:
||                                  - Coding style change on the default where clause
||                                    of Commissary header block
|| Herbert    6.1.0.3  09-AUG-2002  Procedure Def_Bkg_Trst modified for:
||                                  - In the header query criteria for Commissary
||                                    related screen, remove redundant code
||                                  - For multiple-Trust/single-Commissary sub-query,
||                                    include the comparison between the offender's last
||                                    active physical agy_loc_id with the header record
||                                    so that the only the latest offender information
||                                    will be shown
|| Herbert    6.1.0.2  19-JUL-2002  Procedure Def_Bkg_Trst modified for:
||                                  - Multi Trust/Single Commissary enhancement
|| Vipul               05-MAR-2002  Modified the package to fix the default where for TRUST
|| Vipul               21-FEB-2002  Modified the package to include the default where for TRUST
||                                  and Payroll Header.
|| Vipul               11-SEP-2000  Created Package.
||
=========================================================================================
*/
@Service
public class TrustMainServiceImpl implements TrustMainService {

	@Autowired
	private TrustMainRepository trustMainRepository;

	private static final String Y = "Y";
	private static final String N = "N";
	private static final String TRUST = "TRUST";
	private static final String COMM = "COMM";
	private static final String OODOSALE = "OODOSALE";
	private static final String OOMOCRES = "OOMOCRES";
	private static final String OODOSILI = "OODOSILI";
	private static final String PAY = "PAY";
	private static final String INST = "INST";
	private static final String OODORETU = "OODORETU";
	private static final String OOIOSALE = "OOIOSALE";
	private static final String OTDADMIT = "OTDADMIT";
	private static final String OTDCNTAC = "OTDCNTAC";
	private static final String OTINAMES = "OTINAMES";

	// This PROCEDURE trust_header_query is migrated from oracle
	@Override
	public String trustHeaderQuery(final String pCaseloadId, final String pFormName, String pDefWhere) {
		String lCaseloadId;
		String lModuleName = null;
		String lApplnCode;
		String lTrustAccFlag = null;
		String lCommissaryFlag = null;
		String lPayrollFlag = null;
		String lCommTrustCasel = null;
//		String lTrustCommissaryCaseload = null;
		String lPayrollTrustCaseload = null;
		String lCaseloadType = null;
		Caseloads getDetails = null;

		lCaseloadId = pCaseloadId;
		lModuleName = pFormName;
		// This CURSOR app_code is migrated from oracle
		lApplnCode = trustMainRepository.getTstHdrQryApplnCode(pFormName);
		// Purpose: GET_CASELOAD_DETAILS Function - Used to return the caseload_id based
		// upon the community_trust_caseload.
		getDetails = trustMainRepository.getCaseloadsDetails(pCaseloadId);

		lTrustAccFlag = getDetails.getTrustAccountsFlag();
		lCommissaryFlag = getDetails.getCommissaryFlag();
		lPayrollFlag = getDetails.getPayrollFlag();
//		String lTrustCaseloadId = getDetails.getTrustCaseloadId();
		lCommTrustCasel = getDetails.getcommissaryTrustCaseload();
//		String lTrustCommissaryCaseload = getDetails.gettrustCommissaryCaseload();
		lPayrollTrustCaseload = getDetails.getpayrollTrustCaseload();
		lCaseloadType = getDetails.getCaseloadType();

		if (lApplnCode.equals(TRUST) && lCaseloadType.equals(INST)) {
			if (lModuleName.equals(OTDADMIT)) {

//		    	  OTDADMIT

				pDefWhere = " v_header_block.OFFENDER_BOOK_ID = " + "   (select max(ob.offender_book_id) "
						+ "      from offender_bookings ob "
						+ "     where ob.root_offender_id = v_header_block.root_offender_id "
						+ "        and (ob.active_flag = 'Y' " + "            or not exists "
						+ "                     (select 1 " + "                        from offender_bookings obx "
						+ "                       where obx.root_offender_id = ob.root_offender_id "
						+ "                         and obx.active_flag = 'Y' " + "                     ) "
						+ "              ) " + "     ) ";
			} else if (lModuleName.equals(OTDCNTAC)) {
				pDefWhere = " offender_book_id IN " + "     ( SELECT vhb.offender_book_id "
						+ "         FROM v_header_block vhb, " + "              caseloads csld, "
						+ "              caseload_agency_locations cal "
						+ "        WHERE csld.caseload_id = cal.caseload_id "
						+ "          AND cal.agy_loc_id = vhb.agy_loc_id "
						+ "          AND cal.agy_loc_id NOT IN ('OUT','TRN') "
						+ "          AND csld.caseload_type = 'INST' "
						+ "          AND vhb.root_offender_id = v_header_block.root_offender_id)";
			} else if (lModuleName.equals(OTINAMES)) {
				pDefWhere = "( v_trust_header.caseload_id =" + "'" + lCaseloadId + "'"
						+ " and v_trust_header.trust_caseload_id = case when " + "'" + lTrustAccFlag + "'"
						+ " = 'Y' then " + "'" + lCaseloadId + "'" + " else case when " + "'" + lPayrollFlag + "'"
						+ " = 'Y' then " + lPayrollTrustCaseload + " else " + "'" + lCaseloadId + "'" + " end end "
						+ "  and v_trust_header.offender_book_id = " + "         (select max(ob.offender_book_id) "
						+ "            from offender_bookings ob "
						+ "           where ob.root_offender_id = v_trust_header.root_offender_id "
						+ "             and (ob.active_flag = 'Y' " + "                  or not exists "
						+ "                     (select 1 " + "                        from offender_bookings obx "
						+ "                       where obx.root_offender_id = ob.root_offender_id "
						+ "                         and obx.active_flag = 'Y' " + "                     ) "
						+ "                  ) " + "         ) " + " and exists (select 1 "
						+ "           from caseload_agency_locations cal " + "          where cal.caseload_id =" + "'"
						+ lCaseloadId + "'" + "            and cal.agy_loc_id = v_trust_header.agy_loc_id "
						+ "            and cal.agy_loc_id not in('TRN') " + "        ) " + " ) ";
			} else {
				// ALL TRUST SCREENS
				pDefWhere = "( v_trust_header.caseload_id = " + "'" + lCaseloadId + "'"
						+ "  and v_trust_header.trust_caseload_id =" + "'" + lCaseloadId + "'"
						+ "  and v_trust_header.offender_book_id = " + "         (select max(ob.offender_book_id) "
						+ "            from offender_bookings ob "
						+ "           where ob.root_offender_id = v_trust_header.root_offender_id "
						+ "             and (ob.active_flag = 'Y' " + "                  or not exists "
						+ "                     (select 1 " + "                        from offender_bookings obx "
						+ "                       where obx.root_offender_id = ob.root_offender_id "
						+ "                         and obx.active_flag = 'Y' " + "                     ) "
						+ "                  ) " + "         ) " + " ) ";
			} // end if
		} // end if

//	       appln.........: COMM
//	       csld type.....: INST

		if (lApplnCode.equals(COMM) && lCaseloadType.equals(INST)) {

//	         OODOSALE, OOMOCRES, OODOSILI  PAY") && lCaseloadType.equals("INST"

			if (lModuleName.equals(OODOSALE) || lModuleName.equals(OOMOCRES) || lModuleName.equals(OODOSILI)) {

				if (Y.equals(lCommissaryFlag) && Y.equals(lTrustAccFlag)) {

					pDefWhere = "( v_trust_header.caseload_id =" + "'" + lCaseloadId + "'"
							+ "  and v_trust_header.trust_caseload_id =" + "'" + lCaseloadId + "'"
							+ "  and v_trust_header.active_flag = 'Y' " + "  and exists " + "        (select 1 "
							+ "           from caseload_agency_locations cal " + "          where cal.caseload_id ="
							+ "'" + lCaseloadId + "'" + "            and cal.agy_loc_id = v_trust_header.agy_loc_id "
							+ "            and cal.agy_loc_id not in ('OUT', 'TRN') " + "        ) " + " ) ";

				} else if (Y.equals(lCommissaryFlag) && lCommTrustCasel != null) {
					pDefWhere = "(v_trust_header.caseload_id =" + lCaseloadId
							+ "  and v_trust_header.trust_caseload_id =" + lCommTrustCasel
							+ "  and v_trust_header.commissary_trust_caseload =" + lCommTrustCasel
							+ "  and v_trust_header.active_flag = 'Y' " + "  and exists " + "        (select 1 "
							+ "           from caseload_agency_locations cal "
							+ "          where cal.caseload_id = v_trust_header.trust_caseload_id "
							+ "            and cal.agy_loc_id = v_trust_header.agy_loc_id "
							+ "            and cal.agy_loc_id not in ('OUT','TRN') " + "        ) " + "  and exists "
							+ "        (select 1 " + "           from caseload_agency_locations cal "
							+ "          where cal.caseload_id =" + lCaseloadId
							+ "            and cal.agy_loc_id = v_trust_header.agy_loc_id "
							+ "            and cal.agy_loc_id not in ('OUT','TRN') " + "        ) " + " ) ";

				} else if (Y.equals(lCommissaryFlag) && N.equals(lTrustAccFlag)) {
					pDefWhere = "( v_trust_header.caseload_id =" + lCaseloadId
							+ "  and v_trust_header.trust_caseload_id IN " + "     (SELECT cl.caseload_id "
							+ "        FROM caseloads cl " + "       WHERE cl.trust_commissary_caseload = "
							+ lCaseloadId + "         AND cl.active_flag = 'Y' " + "      ) "
							+ "  and v_trust_header.active_flag = 'Y' " + "  and exists " + "        (select 1 "
							+ "           from caseload_agency_locations cal "
							+ "          where cal.caseload_id = v_trust_header.trust_caseload_id "
							+ "            and cal.agy_loc_id = v_trust_header.agy_loc_id "
							+ "            and cal.agy_loc_id not in ('OUT', 'TRN') " + "        ) " + "  and exists "
							+ "        (select 1 " + "           from caseload_agency_locations cal "
							+ "          where cal.caseload_id = " + lCaseloadId
							+ "            and cal.agy_loc_id = v_trust_header.agy_loc_id "
							+ "            and cal.agy_loc_id not in ('OUT', 'TRN') " + "        ) " + ") ";
				} // end if
			} // end if

//			 /******************
//	         OODORETU, OOIOSALE 
//	         *******************/

			if (lModuleName.equals(OODORETU) || lModuleName.equals(OOIOSALE)) {
				if (Y.equals(lCommissaryFlag) && Y.equals(lTrustAccFlag)) {

					pDefWhere = "( v_trust_header.caseload_id = " + "'" + lCaseloadId + "'"
							+ "  and v_trust_header.trust_caseload_id =" + "'" + lCaseloadId + "'"
							+ "  and v_trust_header.offender_book_id =" + "         (select max(ob.offender_book_id) "
							+ "            from offender_bookings ob "
							+ "           where ob.root_offender_id = v_trust_header.root_offender_id "
							+ "             and (ob.active_flag = 'Y' " + "                  or not exists "
							+ "                     (select 1 " + "                        from offender_bookings obx "
							+ "                       where obx.root_offender_id = ob.root_offender_id "
							+ "                         and obx.active_flag = 'Y' " + "                     ) "
							+ "                  ) " + "         ) " + " AND EXISTS (SELECT 1 "
							+ "               FROM sales_orders so "
							+ "              WHERE so.offender_id = v_trust_header.root_offender_id "
							+ "                AND so.caseload_id = " + "'" + lCaseloadId + "'"
							+ "                and so.sale_loc_id in "
							+ "                       (select cal.agy_loc_id "
							+ "                          from caseload_agency_locations cal "
							+ "                         where cal.caseload_id = v_trust_header.trust_caseload_id "
							+ "                       ) " + "             ) " + ") ";
				} else if (Y.equals(lCommissaryFlag) && lCommTrustCasel != null) {

					pDefWhere = "(v_trust_header.caseload_id =" + "'" + lCaseloadId
							+ "  and v_trust_header.trust_caseload_id =" + "'" + lCommTrustCasel + "'"
							+ "  and v_trust_header.commissary_trust_caseload =" + "'" + lCommTrustCasel + "'"
							+ "  and v_trust_header.offender_book_id = " + "         (select max(ob.offender_book_id) "
							+ "            from offender_bookings ob "
							+ "           where ob.root_offender_id = v_trust_header.root_offender_id "
							+ "             and (ob.active_flag = 'Y' " + "                  or not exists "
							+ "                     (select 1 " + "                        from offender_bookings obx "
							+ "                       where obx.root_offender_id = ob.root_offender_id "
							+ "                         and obx.active_flag = 'Y' " + "                     ) "
							+ "                  ) " + "         ) " + " AND EXISTS (SELECT 1 "
							+ "               FROM sales_orders so "
							+ "              WHERE so.offender_id = v_trust_header.root_offender_id "
							+ "                AND so.caseload_id = " + "'" + lCaseloadId + "'"
							+ "                and so.sale_loc_id in "
							+ "                       (select cal.agy_loc_id "
							+ "                          from caseload_agency_locations cal "
							+ "                         where cal.caseload_id = v_trust_header.trust_caseload_id "
							+ "                       ) " + "             ) " + " ) ";
				} // else if
				else if (Y.equals(lCommissaryFlag) && N.equals(lTrustAccFlag)) {

					pDefWhere = "(v_trust_header.caseload_id =" + "'" + lCaseloadId + "'"
							+ " and v_trust_header.offender_book_id =" + "         (select max(ob.offender_book_id) "
							+ "            from offender_bookings ob "
							+ "           where ob.root_offender_id = v_trust_header.root_offender_id "
							+ "             and (ob.active_flag = 'Y' " + "                  or not exists "
							+ "                     (select 1 " + "                        from offender_bookings obx "
							+ "                       where obx.root_offender_id = ob.root_offender_id "
							+ "                         and obx.active_flag = 'Y' " + "                     ) "
							+ "                  ) " + "         ) " + "  and EXISTS (SELECT 1 "
							+ "                FROM sales_orders so "
							+ "               WHERE so.offender_id = v_trust_header.root_offender_id "
							+ "                 AND so.caseload_id =" + "'" + lCaseloadId + "'"
							+ "                 and so.sale_loc_id in "
							+ "                       (select cal.agy_loc_id "
							+ "                          from caseload_agency_locations cal "
							+ "                         where cal.caseload_id = v_trust_header.trust_caseload_id "
							+ "                       ) " + "              ) " + " ) ";
				} // end iff
			} // end if
		} // main if end

//		 /********************************
//	       appln.........: PAY
//	       csld type.....: INST
//	      *********************************/

		if (lApplnCode.equals(PAY) && lCaseloadType.equals(INST)) {
			// --l_query_type := 'K'; --abutest
			pDefWhere = " EXISTS ( SELECT 'X' " + "            FROM caseload_agency_locations CAL "
					+ "           WHERE CAL.caseload_id =" + "'" + lCaseloadId + "'"
					+ "             AND CAL.agy_loc_id = V_PAYROLL_HEADER.agy_loc_id " + "         ) "
					+ "  AND V_PAYROLL_HEADER.caseload_id = " + "'" + lCaseloadId + "'";

		}

//		/********************************
//	       appln.........: TRUST
//	       csld type.....: COMM
//	      *********************************/
		if (lApplnCode.equals(TRUST) && lCaseloadType.equals(COMM)) {

			pDefWhere = " v_trust_header.trust_caseload_id =" + "'" + lCaseloadId + "'"
					+ " AND v_trust_header.offender_book_id =" + "     (SELECT MAX(vhb2.offender_book_id) "
					+ "        FROM offender_bookings   vhb2 "
					+ "       WHERE vhb2.root_offender_id = v_trust_header.root_offender_id "
					+ "         AND (vhb2.community_active_flag = 'Y' " + "              OR NOT EXISTS "
					+ "                    (SELECT 'X' " + "                       FROM offender_bookings  vhb3 "
					+ "                      WHERE vhb3.community_active_flag = 'Y' "
					+ "                        AND vhb3.root_offender_id = vhb2.root_offender_id "
					+ "                     ) " + "              ) "
					+ "         AND ( (vhb2.intake_agy_loc_id <> 'CLOSE' " + "                AND EXISTS "
					+ "                   ( SELECT 'X' "
					+ "                       FROM offender_booking_agy_locs locs, "
					+ "                            caseload_agency_locations cal1 "
					+ "                      WHERE cal1.caseload_id =" + "'" + lCaseloadId + "'"
					+ "                        AND cal1.agy_loc_id = locs.agy_loc_id "
					+ "                        AND locs.offender_book_id = vhb2.offender_book_id "
					+ "                        AND locs.removed_date IS  NULL " + "                    ) "
					+ "                ) " + "              OR (vhb2.intake_agy_loc_id = 'CLOSE' "
					+ "                  AND EXISTS " + "                     ( SELECT 'X' "
					+ "                         FROM offender_booking_agy_locs locs, "
					+ "                              caseload_agency_locations cal1 "
					+ "                        WHERE cal1.caseload_id =" + "'" + lCaseloadId + "'"
					+ "                          AND cal1.agy_loc_id = locs.agy_loc_id "
					+ "                          AND locs.offender_book_id = vhb2.offender_book_id "
					+ "                    ) " + "                  ) " + "              ) " + "       ) ";

		}
		return pDefWhere;
	}
}