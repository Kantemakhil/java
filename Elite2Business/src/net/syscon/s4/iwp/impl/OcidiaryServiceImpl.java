package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.iwp.OcidiaryRepository;
import net.syscon.s4.iwp.OcidiaryService;

/**
 * Class OcidiaryServiceImpl
 */
@Service
public class OcidiaryServiceImpl extends BaseBusiness implements OcidiaryService {

	@Autowired
	private OcidiaryRepository ocidiaryRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> DefineWhereClause(OffenderBookings paramBean) {

		return ocidiaryRepository.defineWhereClause(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules searchRecord) {
		String combineDateTime;
		if (searchRecord.getInChargeStaffName() == null) {
			searchRecord.setInChargeStaffId(null);
		}
		BigDecimal inChargeStaffId = ocidiaryRepository.getStaffName(searchRecord.getOffenderLastName(),
				searchRecord.getOffenderFirstName());
		if (inChargeStaffId != null) {
			searchRecord.setInChargeStaffId(inChargeStaffId);
		}
		if (searchRecord.getEventDate() != null && searchRecord.getStartTime() != null) {
			combineDateTime = "datePart.setTime(time)" + "searchRecord.setStartTime()";
		}
		List<VOffenderAllSchedules> returnList = ocidiaryRepository.offSchExecuteQuery(searchRecord);

		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer offSchCommit(VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		String lvResult = "N";
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VOffenderAllSchedules obj : commitBean.getUpdateList()) {
				List<VOffenderAllSchedules> lstVOffenderAllSchedules = new ArrayList<VOffenderAllSchedules>();
				lstVOffenderAllSchedules.forEach(action -> {
					action.setEventOutcome(obj.getEventOutcome());
				});
				if ("X".equals(obj.getEventOutcome() != obj.getEventOutcome())) {
					String uaCur = ocidiaryRepository.checkUaEventOutcome(lstVOffenderAllSchedules);
				}
				if (obj.getEventOutcome() != null) {
					String uaCur = ocidiaryRepository.checkUaEventOutcome(lstVOffenderAllSchedules);
				}
			}

			liReturn = ocidiaryRepository.offSchUpdateVOffenderAllSchedules(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */

	public List<AgencyLocations> rgLocationRecordGroup(final String caseloadid) {
		return ocidiaryRepository.rgLocationRecordGroup(caseloadid);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		return ocidiaryRepository.rgSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgOutcomeRecordGroup() {
		return ocidiaryRepository.rgOutcomeRecordGroup();

	}

}