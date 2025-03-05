package net.syscon.s4.pkgs.oumhlhis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.AgyIntLocAmendQuery;
import net.syscon.s4.pkgs.oumhlhis.OumhlhisPkgRepository;
import net.syscon.s4.pkgs.oumhlhis.OumhlhisPkgService;
import net.syscon.s4.pkgs.tag_sort.TagSortService;
/*Below comments are copied from package OUMHLHIS 
MODIFICATION HISTORY (Please put version history in reverse chronological order below)
--------------------
Person      Date        Version  Comments
---------   ----------  -------  ----------------------------------------
GJC         14-Oct-2006 2.3      Removed DBMS_OUTPUT calls.
LAURENCE    18-AUG-2005 2.2      Peer review changes.
LAURENCE    12-AUG-2005 2.1      Now orders by amend_date desc.
LAURENCE    02-AUG-2005 2.0      Initial creation
*/
@Service
public class OumhlhisPkgServiceImpl implements OumhlhisPkgService {
	@Autowired
	private OumhlhisPkgRepository oumhlhisRepository;
	@Autowired
	private TagSortService tagSortService;
	/*
	 * This procedure is migrated from oracle OUMHLHIS
	 * 
	 * @Procedure AGY_INT_LOC_AMEND_QUERY to be used for retrieving the data
	 * from v_agy_int_loc_amendments
	 */
	@Override
	public List<AgyIntLocAmendQuery> agyIntLocAmendQuery(AgyIntLocAmendQuery bean) {
		final String getOrderBy = tagSortService.getOrderBy();
		// retrieving the data from v_agy_int_loc_amendments
		return oumhlhisRepository.agyIntLocAmendQuery(bean, getOrderBy);
	}

}