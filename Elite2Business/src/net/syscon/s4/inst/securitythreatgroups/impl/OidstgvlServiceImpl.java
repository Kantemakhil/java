package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.StgValidationsCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.securitythreatgroups.OidstgvlRepository;
import net.syscon.s4.inst.securitythreatgroups.OidstgvlService;

/**
 * Class OidstgvlServiceImpl@Service
 **/
@Service
public class OidstgvlServiceImpl extends BaseBusiness implements OidstgvlService {

	@Autowired
	private OidstgvlRepository oidstgvlRepository;

	/**
	 * Creates new OidstgvlServiceImpl class Object
	 */
	public OidstgvlServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<StgValidations> stgValidationsWhenCreateRecord(final StgValidations paramBean) {
		return oidstgvlRepository.stgValidationsWhenCreateRecord(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> stgValidationsPostQuery(final ReferenceCodes paramBean) {
		return oidstgvlRepository.stgValidationsPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<StgValidations> stgValidationsExecuteQuery(final StgValidations searchRecord) {
		return oidstgvlRepository.stgValidationsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_VALIDATIONS
	 */
	@Transactional
	public Integer stgValidationsCommit(final StgValidationsCommitBean commitBean) {
		int liReturn = 0;
		BigDecimal stgId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			for (final StgValidations offenderAlerts : commitBean.getInsertList()) {
				stgId = offenderAlerts.getStgId();
			}
			List<StgValidations> saveObj = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					saveObj = new ArrayList<>();
					final BigDecimal valueAlertSeq = oidstgvlRepository.validationSeqData(stgId);
					final StgValidations offenderAlertObj = commitBean.getInsertList().get(i);
					offenderAlertObj.setValidationSeq(valueAlertSeq);
					saveObj.add(offenderAlertObj);
					liReturn = stgvalidationsInsertStgValidations(saveObj);
				}
			}
		}
		return liReturn;
	}

	@Transactional
	public Integer stgvalidationsInsertStgValidations(final List<StgValidations> lstOffenderAlerts) {
		lstOffenderAlerts.forEach(action -> {
			final Long profileValue = oidstgvlRepository.oidstgvlKeyCommit();
			if (profileValue != null) {
				Date date = new Date(action.getValidationDate().getTime() + (24 * 60 * 60 * 1000 * profileValue));
				action.setReviewDate(date);
			}
			final Date valDate = trunc(action.getValidationDate());
			final Date revDate = trunc(action.getReviewDate());
			action.setValidationDate(valDate);
			action.setReviewDate(revDate);
		});
		return oidstgvlRepository.stgvalidationsInsertStgValidations(lstOffenderAlerts);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgActionRecordGroup() {
		return oidstgvlRepository.rgActionRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgDesignationRecordGroup() {
		return oidstgvlRepository.rgDesignationRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgReasonRecordGroup() {
		return oidstgvlRepository.rgReasonRecordGroup();
	}

	@Override
	public Date reviewDateData(final Long stgId) {
		return oidstgvlRepository.reviewDateData(stgId);
	}

	public Date trunc(final Date date) {
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
}