package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.im.beans.VOffenderCourseAttendancesCommitBean;
import net.syscon.s4.im.beans.VPrisonActivities;
import net.syscon.s4.inst.institutionalactivities.OidpaattRepository;
import net.syscon.s4.inst.institutionalactivities.OidpaattService;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuRepository;

/**
 * Class OidpaattServiceImpl
 */
@Service
public class OidpaattServiceImpl extends BaseBusiness implements OidpaattService {

	@Autowired
	private OidpaattRepository oidpaattRepository;
	
	@Autowired
	private OcmpssetService ocmpssetService;

	@Autowired
	VOffenderCourseEventsTuRepository vOffenderCourseEventsTuRepository;
	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> defaultAgency(AgencyLocations paramBean) {
		return oidpaattRepository.defaultAgency(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderCourseAttendances> vActAttExecuteQuery(VOffenderCourseAttendances searchRecord) {
		return oidpaattRepository.vActAttExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_ACT_ATT
	 *
	 */
	@Transactional
	public Integer vActAttCommit(VOffenderCourseAttendancesCommitBean commitBean) {
		int liReturn = 0;
		List<VOffenderCourseAttendances> insrtUpdList = new ArrayList<>();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(action-> action.setModifyUserId(commitBean.getCreateUserId()));
			for (VOffenderCourseAttendances bean : commitBean.getUpdateList()) {
				if (bean.getEventId() == null) {
					bean.setEventId(vOffenderCourseEventsTuRepository.getEventId().longValue());
					insrtUpdList = new ArrayList<>();
					bean.setCreateUserId(commitBean.getCreateUserId());
					insrtUpdList.add(bean);
					
					liReturn = oidpaattRepository.insertCrsAttendance(insrtUpdList);
				} else {
					insrtUpdList = new ArrayList<>();
					bean.setModifyUserId(commitBean.getCreateUserId());
					insrtUpdList.add(bean);
					liReturn = oidpaattRepository.vActAttUpdateVOffenderCourseAttendances(insrtUpdList);
				}
				ocmpssetService.updateSchedulePay(new BigDecimal(bean.getOffenderBookId()),
						Integer.valueOf(bean.getEventId().toString()), "INST_ACT", bean.getModifyUserId());
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgPsActPerfRecordGroup() {
		return oidpaattRepository.rgPsActPerfRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgOutcomesRecordGroup() {
		return oidpaattRepository.rgOutcomesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<VPrisonActivities> rgServicesRecordGroup(final String agyLocId) {
		return oidpaattRepository.rgServicesRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		return oidpaattRepository.rgAgyLocRecordGroup(caseloadId);

	}

	public VOffenderCourseAttendances defaultAttendanceData() {
		VOffenderCourseAttendances returnDate = new VOffenderCourseAttendances();
		String outcome = oidpaattRepository.getDefAttOutcome();
		if (outcome != null) {
			returnDate.setEventOutcome(outcome);
		}
		String performance = oidpaattRepository.getDefAttPerformance();
		if (performance != null) {
			returnDate.setPerformanceCode(performance);
		}
		return returnDate;

	}
}