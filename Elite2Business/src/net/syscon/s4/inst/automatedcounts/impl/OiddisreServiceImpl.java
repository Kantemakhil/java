package net.syscon.s4.inst.automatedcounts.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.OiddisreRepository;
import net.syscon.s4.inst.automatedcounts.OiddisreService;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.pkgs.oidcount.OidcountPkgService;

/**
 * Class OiddisreServiceImpl
 */
@Service
public class OiddisreServiceImpl extends BaseBusiness implements OiddisreService {

	@Autowired
	private OiddisreRepository oiddisreRepository;
	@Autowired
	private OidcountPkgService oidcountPkgService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OmsModules> CreateFormGlobals(OmsModules paramBean) {
		return oiddisreRepository.createFormGlobals(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyLocations> DefaultAgyLoc(AgencyLocations paramBean) {
		return oiddisreRepository.defaultAgyLoc(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyCounts> agencyCountsExecuteQuery(AgencyCounts searchRecord) {
		return oiddisreRepository.agencyCountsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_COUNTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agencyCountsCommit(AgencyCounts commitBean) {
		int liReturn = 0;
		if (commitBean.getSessionId() != null && commitBean.getReportingLocId() != null
				&& commitBean.getCountTypeId() != null) {
			liReturn = oiddisreRepository.agencyCountsUpdateAgencyCounts(commitBean);
			if (liReturn == 1) {
				liReturn = oiddisreRepository.agencyCountsDeleteAgencyCounts(commitBean);
				liReturn = oidcountPkgService.cancelCount(commitBean.getSessionId().longValue(),commitBean.getCreateUserId());
			}

		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkDiscrepRsnRecordGroup() {
		return oiddisreRepository.cgfkDiscrepRsnRecordGroup();

	}

}