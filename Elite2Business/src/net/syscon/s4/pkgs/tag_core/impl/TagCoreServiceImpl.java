package net.syscon.s4.pkgs.tag_core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.tag_core.TagCoreRepository;
import net.syscon.s4.pkgs.tag_core.TagCoreService;

/*
 * Below comments are copied from package TAG_CORE
 * Package   : Tag Core
-- Version   : TAG6.0.0.001
-- Release   : 2000/02/28
-- MODIFICATION HISTORY
-- Person     Date        Version  Comments
-- --------   ---------   -------  ------------------------------------------
-- GJC        14-Oct-2006 2.3      SHOW_VERSION changed from procedure to function
-- Neil       02/03/2006  2.2	   Added off_rec, off_info_type and get_off_details
-- Joe Wong   25-FEB-97            For procedure MERGE_PERSON
-- Joe Wong   26-FEB-97            For procedure UNMERGE_PERSON
-- Joe Wong 		               For procedure MERGE_OFFENDER
-- Joe Wong   		               For procedure UNMERGE_OFFENDER
-- Alex Kwok  2000/02/24           Added OFFENDER_NAME function
-- David Ng   2000/02/28           Add procedure Update_db_Profile
------------------------------------------------------------------------------------------
-- =====================================================================================
   vcp_version  CONSTANT    VARCHAR2(60):='2.3 14-Oct-2006';
-- =====================================================================================
-- MODIFICATION HISTORY
-- Person      DATE         Version  Comments
-- ---------   ----------   -------  ----------------------------------------------------
-- Michael     14-JUL-05    10.2.1   Removed insert into Journal Table
-- SURYA       09-AUG-00    4.9.0.0  Added the Procedure SHOW_VERSION and Modified the
--                                   Modification History

-- Purpose: This procedure will merge the particular person with the alias_person_id
--          and then update the root_person_id of the person's child.
-- Tree Walking method.
*/
@Service
public class TagCoreServiceImpl implements TagCoreService {

	@Autowired
	private TagCoreRepository tagCoreRepository;
     
	//This method is used for retrieving details in offender table
	@Override
	public Offenders getOffDetails(final Long offenderId) {
		return tagCoreRepository.getOffDetails(offenderId);
	}

	// This method is used to addString
	@Override
	public String addString(String pMainString, String pSubString) {
		return null;
	}

	// This method is used to removeString
	@Override
	public String removeString(String pMainString, String pSubString) {
		String vString = null;
		int vPos = tagCoreRepository.removeStringInstr(pMainString, pSubString);
		if (pMainString != null && vPos != 0) {
			int vLength = pMainString == null ? 0 : pMainString.length();
			vString = tagCoreRepository.removeStringSubstr(pMainString, vPos)
					+ tagCoreRepository.stringSubstringOne(pMainString, vPos, vLength);
		} else {
			vString = pMainString;
		}
		return vString;
	}

}
