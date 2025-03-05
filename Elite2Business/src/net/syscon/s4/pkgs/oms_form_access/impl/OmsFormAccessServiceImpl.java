package net.syscon.s4.pkgs.oms_form_access.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessRepository;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessService;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;
/*--
 * Below comments are copied from package OMS_FORM_ACCESS 
-- Purpose: This package is essentially to be used by two Forms
--                 i) OIIBOOKS
--                ii) OIDRESLT
--      PROCEDURE check_data_available - To check if the data exists for the offender.
--
-- MODIFICATION HISTORY
-- Person     	 Date      	 	Comments
-- ---------   	------     		------------------------------------------
-- Himanshu     17-Jul-2012      Added show version function
-- Edward       16-AUG-2006      Added check_data_available_stg for OIITGDET form.
-- Girish       19-DEC-2002      Using USER_TAB_COLUMNS instead of ALL_TAB_COLUMNS due to performance issues.
--                               Removed the commented code from previous version. This package is called
--                               from Historical Summary Screen OSIHRSUM apart from OIIBOOKS and OIDRESLT
--                               and the data avaiable should be checked against all bookings only when called
--                               from OSIHRSUM screen. Tables like OFFENDER_CASES have OFFENDER_BOOK_ID column
--                               wrongly named as OFFENDER_BOOKING_ID and should be queried against OFFENDER_BOOKING_ID
--                               There are 11 tables and these have considered.
-- ***************************************************************************************/
@Service
public class OmsFormAccessServiceImpl implements OmsFormAccessService {

	@Autowired
	private OmsFormAccessRepository omsFormAccessRepository;

	private static final String TRUE = "TRUE";
	private static final String OSIHRSUM = "OSIHRSUM";
	private static final String OIDSTGIN = "OIDSTGIN";
	private static Logger logger = LogManager.getLogger(OmsFormAccessServiceImpl.class.getName());
	/* This procedure is migrated from oracle OMS_FORM_ACCESS
	 * This FUNCTION CHECK_DATA_AVAILABLE is migrated from oracle To check if the data exists for the offender.
	 */
	@Override
	public String checkDataAvailable(final FormAccessibleForms accForms) {
		String dataAvail = "FALSE";
		Integer lvBookidCount = 0;
		Integer lvRecCount = null;
		List<AccessibleFormTables> accFoTabList = null;

		try {
			// CURSOR to select the tables associated with destination form.
			accFoTabList = omsFormAccessRepository.tableCur(accForms.getOriginatingForm(),
					accForms.getDestinationForm() );
			for (final AccessibleFormTables accForTtab : accFoTabList) {
				if (TRUE.equals(dataAvail)) {
					break;
				}
				// lv_stmt select
				lvBookidCount = omsFormAccessRepository.lvStmtSelect(accForTtab.getTableName());
                //  If OFFENDER_BOOK_ID is one of the columns of the table
				if (lvBookidCount != 0) {
					if (OSIHRSUM.equals(accForms.getOriginatingForm())) {
						lvRecCount = omsFormAccessRepository.lvStmtIfSelect(accForTtab.getTableName(),
								accForms.getOffenderId());
					} else {
						lvRecCount = omsFormAccessRepository.lvStmtElseSelect(accForTtab.getTableName(),
								accForms.getBookId());
					}
				} else {
					if (accForTtab.getTableName() == "OFFENDER_BILLING_DETAILS"
							|| accForTtab.getTableName() == "OFFENDER_BILLING_DETAILS_TMP"
							|| accForTtab.getTableName() == "OFFENDER_BILLING_PROFILES"
							|| accForTtab.getTableName() == "OFFENDER_CASES"
							|| accForTtab.getTableName() == "OFFENDER_CASE_OFFICERS"
							|| accForTtab.getTableName() == "OFFENDER_INITIAL_SCHEDULES"
							|| accForTtab.getTableName() == "OFFENDER_INST_REQUESTS"
							|| accForTtab.getTableName() == "OFFENDER_INTER_MVMT_LOCATIONS"
							|| accForTtab.getTableName() == "OFFENDER_MARITAL_STATUS"
							|| accForTtab.getTableName() == "OFFENDER_OIC_APPEALS"
							|| accForTtab.getTableName() == "OFFENDER_PROGRAM_SCHEDULES") {
						if (OSIHRSUM.equals(accForms.getOriginatingForm())) {
							// Getting count
							lvRecCount = omsFormAccessRepository.lvStmtIfSelect(accForTtab.getTableName(),
									accForms.getOffenderId());
						} else {
							// Getting count
							lvRecCount = omsFormAccessRepository.lvStmtElseSelect(accForTtab.getTableName(),
									accForms.getBookId());
						}
					} else {
						// Getting count
						lvRecCount = omsFormAccessRepository.lvStmtLastElseSelect(accForTtab.getTableName(),
								accForms.getOffenderId());
					}
				}
				if (lvRecCount != 0) {
					dataAvail = TRUE;
				}
			}
		} catch (EmptyResultDataAccessException e) {
			logger.error("lvStmt", e);
			return null;
		}

		return dataAvail;
	}

	@Override
	public Boolean checkDataAvailableStg(final String orgForm, final String destForm, final Long stgdId) {
		boolean dataAvail = false;
		if (destForm != OIDSTGIN) {
			dataAvail = false;
			final List<AccessibleFormTables> list = omsFormAccessRepository.tableCur(orgForm, destForm);
			for (final AccessibleFormTables bean : list) {
				if (bean != null && bean.getTableName()!=null) {
					Integer count = omsFormAccessRepository.lvStmt(bean.getTableName(),stgdId);
					if (count != 0) {
						dataAvail = true;
					}
				}
			}
		}
		return dataAvail;
	}

}