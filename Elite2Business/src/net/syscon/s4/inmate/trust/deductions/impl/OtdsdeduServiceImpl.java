package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.SuspendDeductionTypes;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.SuspendDeductionTypesCommitBean;
import net.syscon.s4.inmate.beans.SuspendDeductionsCommitBean;
import net.syscon.s4.inmate.trust.deductions.OtdsdeduRepository;
import net.syscon.s4.inmate.trust.deductions.OtdsdeduService;
import net.syscon.s4.pkgs.deductions.DeductionsService;

/**
 * Class OtdsdeduServiceImpl
 */
@Service
public class OtdsdeduServiceImpl extends BaseBusiness implements OtdsdeduService {

	@Autowired
	private OtdsdeduRepository otdsdeduRepository;

	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private DeductionsService deductionsService;

	/**
	 * Creates new OtdsdeduServiceImpl class Object
	 */
	public OtdsdeduServiceImpl() {
		// OtdsdeduServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Caseloads cgfkchkSusDedSusDedCasel(final Caseloads paramBean) {
		return otdsdeduRepository.cgfkchkSusDedSusDedCasel(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public DeductionTypes cgfkchkSusDtSusDtDedTyp(final DeductionTypes paramBean) {
		return otdsdeduRepository.cgfkchkSusDtSusDtDedTyp(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SuspendDeductions> susDedExecuteQuery(final SuspendDeductions searchRecord) {
		return otdsdeduRepository.susDedExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSUS_DED
	 *
	 * 
	 */
	@Transactional
	public String susDedCommit(final SuspendDeductionsCommitBean commitBean) {
		int liReturn = 0;
		Long suspendDeductionId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<SuspendDeductions> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (SuspendDeductions data : commitBean.getInsertList()) {
					suspendDeductionId = otdsdeduRepository.suspendDeductionsPreInsertc();
					data.setSuspendDeductionId(suspendDeductionId);
					data.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(data);
				}
				try {
				liReturn = otdsdeduRepository.susDedInsertSuspendDeductions(recordSavingObject);
				}catch (Exception e) {
					if (e.getMessage().contains("suspend_deductions_uk")) {
						return "SUSPEND_DEDUCTIONS_UK";
				}
			}
		}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SuspendDeductions obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otdsdeduRepository.susDedUpdateSuspendDeductions(commitBean.getUpdateList());
			if (liReturn != 0) {
				liReturn = susDedPostUpdate(commitBean.getUpdateList());
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (SuspendDeductions obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otdsdeduRepository.susDedDeleteSuspendDeductions(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	private Integer susDedPostUpdate(List<SuspendDeductions> updateList) {
		int liReturn = 0;
		for (SuspendDeductions data : updateList) {
			if (data.getStartDate() != null
					&& trunc(data.getStartDate()).compareTo(trunc(eliteDateService.getDBTime())) == 0) {
				liReturn = deductionsSuspendOffDeductions();
				if (liReturn == 0) {
					throw new RuntimeException("Other Error in (DB Object Name): ");
				}
			} else {
				liReturn = 1;
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SuspendDeductionTypes> susDtExecuteQuery(final SuspendDeductionTypes searchRecord) {
		return otdsdeduRepository.susDtExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSUS_DT
	 *
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public String susDtCommit(final SuspendDeductionTypesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SuspendDeductionTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otdsdeduRepository.susDtInsertSuspendDeductionTypes(commitBean.getInsertList());
			if (liReturn != 0) {
				liReturn = susDtPostInsert(commitBean.getInsertList());
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SuspendDeductionTypes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otdsdeduRepository.susDtUpdateSuspendDeductionTypes(commitBean.getUpdateList());
			if (liReturn != 0) {
				liReturn = susDtPostUpdate(commitBean.getUpdateList());
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (SuspendDeductionTypes obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = otdsdeduRepository.susDtDeleteSuspendDeductionTypes(commitBean.getDeleteList());
		}
		return String.valueOf(liReturn);
	}

	private Integer susDtPostUpdate(List<SuspendDeductionTypes> updateList) {
		int liReturn = 0;
		for (SuspendDeductionTypes data : updateList) {
			if (data.getStartDate() != null
					&& trunc(data.getStartDate()).compareTo(trunc(eliteDateService.getDBTime())) != 1) {
				if ("Y".equals(data.getSuspendedFlag())) {
					liReturn = deductionsUpdateDeductionStatus(data.getSuspendDeductionId(), data.getCaseloadId(), "Y",
							data.getDeductionType(),data.getCreateUserId());
				} else {
					liReturn = deductionsUpdateDeductionStatus(data.getSuspendDeductionId(), data.getCaseloadId(), "N",
							data.getDeductionType(),data.getCreateUserId());
				}

				if (liReturn == 0) {

					throw new RuntimeException("Other Error in (DB Object Name): ");
				}
			} else {
				liReturn = 1;
			}
		}
		return liReturn;
	}

	private Integer susDtPostInsert(List<SuspendDeductionTypes> insertList) {
		int liReturn = 0;
		for (SuspendDeductionTypes data : insertList) {
			liReturn = deductionsUpdateDeductionStatus(data.getSuspendDeductionId(), data.getCaseloadId(), "Y",
					data.getDeductionType(),data.getCreateUserId());
			if (liReturn == 0) {
				throw new RuntimeException("Error in POST UPDATE for SUS_DED. ");
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<Caseloads> cgfkSusDedCaseloadIdRecordGroup() {
		return otdsdeduRepository.cgfkSusDedCaseloadIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<DeductionTypes> cgfkSusDtDeductionTypeRecordGroup(final String caseloadType) {
		return otdsdeduRepository.cgfkSusDtDeductionTypeRecordGroup(caseloadType);

	}

	@Override
	public Integer deductionsUpdateDeductionStatus(final Long pSusDedId, final String pCaseloadId,
			final String pSuspendFlag, final String pDeductionType, String userId) {
		try {
		//	otdsdeduRepository.deductionsUpdateDeductionStatus(pSusDedId, pCaseloadId, pSuspendFlag, pDeductionType);
			SuspendDeductions sd = new SuspendDeductions();
			sd.setSuspendDeductionId(pSusDedId);
			sd.setCaseloadId(pCaseloadId);
			sd.setDeductionType(pDeductionType);
			sd.setSuspendedFlag(pSuspendFlag);
			deductionsService.updateDeductionStatus(sd,userId);
			
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public Integer deductionsSuspendOffDeductions() {
		return otdsdeduRepository.deductionsSuspendOffDeductions();
	}

	private Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	@Override
	public BigDecimal chkOverlapDate(final String caseloadId, final String startDate, final String endDate,
			final String flag) {
		return otdsdeduRepository.chkOverlapDate(caseloadId, startDate, endDate, flag);
	}

}