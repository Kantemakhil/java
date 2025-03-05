package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.casemanagement.OiioscedRepository;
import net.syscon.s4.inst.casemanagement.OiioscedService;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.impl.OidintmvRepositoryImpl;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Class OiioscedServiceImpl
 */
@Service
public class OiioscedServiceImpl extends BaseBusiness implements OiioscedService {

	@Autowired
	private OiioscedRepository oiioscedRepository;
	
	@Autowired
	private OidintmvRepositoryImpl oidintmvRepositoryImpl;
	/**
	 * Logger object used to print the log in the file
	 */


	/**
	 * Creates new OiioscedServiceImpl class Object
	 */
	public OiioscedServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(final VOffenderAllSchedules paramBean) {
		final List<VOffenderAllSchedules> vOffenderAllSchedulesList = new ArrayList<>();
		return vOffenderAllSchedulesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> vOffenderAllSchedulesExecuteQuery(final VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> returnList = oiioscedRepository.vOffenderAllSchedulesExecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			returnList.forEach(ele -> {
				if (ele.getAppearanceLocation() != null) {
					ReferenceCodes referenceCodes = oidintmvRepositoryImpl.courtEventsLocation(ele.getAgyLocId(),
							ele.getAppearanceLocation());
					if (referenceCodes != null && referenceCodes.getDescription() != null)
						ele.setAppearanceLocation(referenceCodes.getDescription());
					else
						ele.setAppearanceLocation(null);
				}
			});
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFFENDER_ALL_SCHEDULES
	 *
	 */
	@Transactional
	public Integer vOffenderAllSchedulesCommit(final VOffenderAllSchedulesCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgSchTypeRecordGroup(final String domain) {
		return oiioscedRepository.rgSchTypeRecordGroup(domain);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<MovementReasons> rgSchReaExtRecordGroup(final String schTypeCode) {
		return oiioscedRepository.rgSchReaExtRecordGroup(schTypeCode);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<InternalScheduleReasons> rgSchReaIntRecordGroup(final String schTypeCode) {
		return oiioscedRepository.rgSchReaIntRecordGroup(schTypeCode);

	}

	@Override
	public List<ReferenceCodes> rgSchFilterRecordGroup() {
		List<ReferenceCodes> finalList = new ArrayList<>();
		ReferenceCodes ref1 = new ReferenceCodes();
		ref1.setCode("All");
		ref1.setDescription("All");
		finalList.add(ref1);
		ReferenceCodes ref2 = new ReferenceCodes();
		ref2.setCode("Internal");
		ref2.setDescription("Internal");
		finalList.add(ref2);
		ReferenceCodes ref3 = new ReferenceCodes();
		ref3.setCode("External");
		ref3.setDescription("External");
		finalList.add(ref3);
		return finalList;
	}

}
