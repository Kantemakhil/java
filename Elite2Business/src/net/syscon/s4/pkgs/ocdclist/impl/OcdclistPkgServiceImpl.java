package net.syscon.s4.pkgs.ocdclist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.pkgs.ocdclist.OcdclistPkgRepository;
import net.syscon.s4.pkgs.ocdclist.OcdclistPkgService;
import net.syscon.s4.pkgs.tag_sort.TagSortService;


/*
 * Purpose: This package contains the procedures for the form OCDCLIST.  The based blocks of the
        form OCDCLIST are built upon from these procedures.
 * */
@Service
public class OcdclistPkgServiceImpl implements OcdclistPkgService {

	@Autowired
	private OcdclistPkgRepository ocdclistRepository;
	@Autowired
	private TagSortService tagSortService;

	public OcdclistPkgServiceImpl() {
	}

	/*
	 * This procedure is migrated from oracle OCDCLIST.
	 * 
	 * @Procedure get_partial_searches to be used for Definition of the record for
	 * the base table of CTL_LST for OCDCLIST search results.
	 */
	@Override
	public List<OcdclistCourtListQuery> courtListQuery(final OcdclistCourtListQuery courListQuery) {
		List<OcdclistCourtListQuery> couEveList;
		String orderBy;
		String tagSortOrder;
		// This method is used for getting order by records into query.
		tagSortOrder = tagSortService.getOrderBy();
		if (tagSortOrder != null) {
			orderBy = tagSortOrder;
		} else {
			orderBy = " ORDER BY START_TIME, LAST_NAME, FIRST_NAME, MIDDLE_NAME ";
		}
		// This method is used for getting records into v_court_events.
		couEveList = ocdclistRepository.courtListDataSelect(courListQuery, orderBy);
		return couEveList;
	}
}
