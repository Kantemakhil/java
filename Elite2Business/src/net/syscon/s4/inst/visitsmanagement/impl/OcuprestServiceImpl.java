package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.inst.visitsmanagement.OcuprestRepository;
import net.syscon.s4.inst.visitsmanagement.OcuprestService;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictionsCommitBean;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;

/**
 * Class OcuprestServiceImpl
 */
@Service
public class OcuprestServiceImpl extends BaseBusiness implements OcuprestService {

	@Autowired
	private OcuprestRepository ocuprestRepository;

	@Autowired
	private TagVisitsService tagVisitsService;
	/**
	 * Creates new OcuprestServiceImpl class Object
	 */
	public OcuprestServiceImpl() {
		// OcuprestServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderRestrictions offOnCheckDeleteMaster(final OffenderRestrictions paramBean) {
		final List<OffenderRestrictions> returnList = ocuprestRepository.offOnCheckDeleteMaster(paramBean);
		return returnList.get(0);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VHeaderBlock> offExecuteQuery(final VHeaderBlock searchRecord) {
		//final Integer offBookId = ocuprestRepository.getOffenderBookId(searchRecord);
		final Integer offBookId = tagVisitsService.getContactOffenderBookId(searchRecord.getRootOffenderId(),searchRecord.getCreateuserId());
		searchRecord.setOffenderBookId(BigDecimal.valueOf(offBookId));
		return ocuprestRepository.offExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF
	 *
	 * @
	 */
	@Transactional
	public Integer offCommit(final VHeaderBlockCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VOffenderRestrictions> vOffRestExecuteQuery(final VOffenderRestrictions searchRecord) {
		return ocuprestRepository.vOffRestExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_REST
	 *
	 * @
	 */
	@Transactional
	public Integer vOffRestCommit(final VOffenderRestrictionsCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgAuthorisedByRecordGroup() {
		return ocuprestRepository.rgAuthorisedByRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgRestrictionTypeRecordGroup() {
		return ocuprestRepository.rgRestrictionTypeRecordGroup();

	}

}