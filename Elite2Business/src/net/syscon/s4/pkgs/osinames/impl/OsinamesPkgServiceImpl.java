package net.syscon.s4.pkgs.osinames.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.pkgs.osinames.OsinamesPkgRepository;
import net.syscon.s4.pkgs.osinames.OsinamesPkgService;

/*
 * Below comments are copied from package OSINAMES.
 *  MODIFICATION HISTORY
Version     Developer       Date          Comments
---------   -----------     ----------    --------------------------------------------
Himanshu    17-Jul-2012     2.12        HPQC#3728 Added function Show version and commented procedure show version
Erin        15-Aug-2006     2.11          Modififed FUNCTION getoffname (set lv_off_name to 72 chars)
Erin        01-Aug-2006     2.10          Added get_offender_name
Aasim       12-May-2006     2.9           Added routine to get Offender_book_id, first_name, last_name and agy_loc_id using the offender_book_id_display.
Krishna     08-Dec-2005     2.7           Added get_offender_full_details procedure
Rajshree    22-Nov-2005     2.6           Modified get_offender_details.Added procedure get_off_details_bybook_id
Surya       18-Nov-2005     2.5           Added the get_offender_details procedure to retrieve the
                                          offender last_name, first_name, book id and agy_loc_id.
                                          As per the Rajshree advice removed the getoff_last_firstname
                                          function, as it will be no longer in use.
                                          Modified the version history as it was not indented properly.
Rajshree    08-Nov-2005     2.4           Modified to display names of active offenders only.
Rajshree    07-NOV-2005     2.3           Modified function to get offender last name first name by
                                          offender_id_dsplay, active in institution..
Rajshree    04-NOV-2005     2.2           Modified function to get offender last name first name by
                                          offender_id_dsplay.
Rajshree    02-NOV-2005     2.2           Added function to get offender last name first name by
                                          offender_id_dsplay.
Patrick     12-SEP-2005     2.1           Fixed bug after peer review.
Patrick     07-SEP-2005     2.0           Initial version.
*/
/***************************************************************************************/

@Service
public class OsinamesPkgServiceImpl implements OsinamesPkgService {

	@Autowired
	private OsinamesPkgRepository osinamesRepository;

	/*
	 * This procedure is migrated from oracle OSINAMES.
	 * 
	 * @Procedure get_offender_details to be used for returning p_offender_id_display, p_last_name, p_first_name, p_agy_loc_id
	 * search results.
	 */
	@Override
	@Transactional
	public Map<String, Object> getOffDetailsByBookId(final Long offenderBookId) {
		final Object[] obj = osinamesRepository.offDetCur(offenderBookId);
		if (obj.length > 0) {
			final Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("P_OFFENDER_ID_DISPLAY", obj[0]);
			returnMap.put("P_LAST_NAME", obj[1]);
			returnMap.put("P_FIRST_NAME", obj[2]);
			returnMap.put("P_AGY_LOC_ID", obj[3]);
			return returnMap;
		} else {
			return null;
		}
	}
	/*
	 * This procedure is migrated from oracle OSINAMES.
	 * 
	 * @Procedure get_offender_details to be used for returning p_offender_book_id, p_last_name, p_first_name, p_agy_loc_id
	 * search results.
	 */
	@Override
	public Map<String, Object> getOffenderDetails(final String pOffenderIdDisplay, String pAgyLocId,
			final String pCaseloadId) {
		Long pOffenderBookId;
		String pLastName;
		String pFirstName;
		final Map<String, Object> map = new HashMap<String, Object>();
		final OffenderBookings obj = osinamesRepository.getOffenderDetails(pOffenderIdDisplay, pAgyLocId != null && !pAgyLocId.equalsIgnoreCase("undefined")?pAgyLocId:null, pCaseloadId);
		pOffenderBookId = obj.getOffenderBookId();
		pLastName = obj.getDspLastName();
		pFirstName = obj.getDspFirstName();
		pAgyLocId = obj.getAgyLocId();
		map.put("P_OFFENDER_BOOK_ID", pOffenderBookId);
		map.put("P_LAST_NAME", pLastName);
		map.put("P_FIRST_NAME", pFirstName);
		map.put("P_AGY_LOC_ID", pAgyLocId);
		return map;
	}

	@Override
	public BigDecimal getOffBookId(final String vstOffIdDisplay,final String userId) {
		return osinamesRepository.getOffBookId(vstOffIdDisplay,userId);
	}

}