package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Offence;
import net.syscon.s4.common.beans.OffenceByStatute;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.StatutesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OimoffenRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimoffenService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenceCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenceIndicatorsCommitBean;

/**
 * Class OimoffenServiceImpl
 */
@Service
public class OimoffenServiceImpl extends BaseBusiness implements OimoffenService {

	@Autowired
	private OimoffenRepository oimoffenRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Offence statOnCheckDeleteMaster(final Offence paramBean) {
		return oimoffenRepository.statOnCheckDeleteMaster(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AllowableOffenceTypes ofnOnCheckDeleteMaster(final AllowableOffenceTypes paramBean) {
		return oimoffenRepository.ofnOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<OffenceIndicator> oimoffenPreInsert(final OffenceIndicator paramBean) {
		return oimoffenRepository.oimoffenPreInsert(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Statutes> statExecuteQuery(final Statutes searchRecord) {
		List<Statutes> returnList = oimoffenRepository.statExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			if (ele.getLegislatingBodyCode() != null) {
				String data = oimoffenRepository.gettingLegaslatingId(ele.getLegislatingBodyCode());
				if (data != null) {
					ele.setSealFlag(data);
				}
			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAT
	 *
	 */
	@Transactional
	public Integer statCommit(final StatutesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Offence> ofnExecuteQuery(Offence searchRecord) {
		return oimoffenRepository.ofnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFN
	 *
	 */
	@Transactional
	public Offence ofnCommit(final OffenceCommitBean commitBean) {
		int liReturn = 0;
		Offence returnData = new Offence();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Offence bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			returnData = oimoffenRepository.ofnInsertOffences(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Offence bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffenRepository.ofnUpdateOffences(commitBean.getUpdateList());
			if (liReturn == 1) {
				returnData.setSealFlag("1");
			} else {
				returnData.setSealFlag("0");
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			returnData = oimoffenRepository.ofnDeleteOffences(commitBean.getDeleteList());
		}
		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<AllowableOffenceTypes> alwOtExecuteQuery(final AllowableOffenceTypes searchRecord) {
		return oimoffenRepository.alwOtExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstALW_OT
	 *
	 */
	@Transactional
	public Integer alwOtCommit(final AllowableOffenceTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AllowableOffenceTypes bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffenRepository.alwOtInsertAllowableOffenceTypes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AllowableOffenceTypes bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffenRepository.alwOtUpdateAllowableOffenceTypes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimoffenRepository.alwOtDeleteAllowableOffenceTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenceIndicator> offIndExecuteQuery(final OffenceIndicator searchRecord) {
		return oimoffenRepository.offIndExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_IND
	 */
	@Transactional
	public Integer offIndCommit(final OffenceIndicatorsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenceIndicator> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final Long valueAlertSeq = oimoffenRepository.offenceIndicatorId();
					final OffenceIndicator offenderAlertObj = commitBean.getInsertList().get(i);

					offenderAlertObj.setOffenceIndicatorId(valueAlertSeq);
					offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderAlertObj);
					liReturn = oimoffenRepository.offIndInsertOffenceIndicators(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenceIndicator bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimoffenRepository.offIndUpdateOffenceIndicators(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimoffenRepository.offIndDeleteOffenceIndicators(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOfnSeverityRankingRecordGroup() {
		return oimoffenRepository.cgfkOfnSeverityRankingRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkAlwOtOffenceTypeRecordGroup() {
		return oimoffenRepository.cgfkAlwOtOffenceTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> offIndLovRecordGroup() {
		return oimoffenRepository.offIndLovRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> ofnHoOffSubclRecordGroup() {
		return oimoffenRepository.ofnHoOffSubclRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Statutes> statStatutesCodeRecordGroup() {
		return oimoffenRepository.statStatutesCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<HoCodes> rgHoCodeRecordGroup() {
		return oimoffenRepository.rgHoCodeRecordGroup();

	}

	public Offence oimoffenStatOncheckdeletemasterOffences(final Offence paramBean) {
		Offence obj = new Offence();
		final Long count = oimoffenRepository.oimoffenOfnOncheckdeletemasterOffenderCharges(paramBean);
		if (count > 0) {
			obj.setMaxSentenceLength(BigDecimal.valueOf(count));
		} else {
			obj.setMaxSentenceLength(BigDecimal.ZERO);
		}
		final Long countOne = oimoffenRepository.oimoffenStatOncheckdeletemasterOffences(paramBean);
		if (count > 0) {
			obj.setListSeq(BigDecimal.valueOf(countOne));
		} else {
			obj.setListSeq(BigDecimal.ZERO);
		}
		return obj;
	}

	@Override
	public List<Offence> oimoffenPreInsert(final Offence paramBean) {
		return null;
	}
	
	@Override
	public List<OffenceByStatute> getOffencesOnStatute(final Offence paramBean) {
		
		final List<OffenceByStatute> offData = oimoffenRepository.getOffencesOnStatute(paramBean);
		return offData;
	}

	@Override
	public List<OffenceByStatute> getOffencesOnStatuteList() {
		return oimoffenRepository.getOffencesOnStatuteList();
	}
	
	@Override
	public Boolean isChgDependOnOffences(Integer offenceId) {
		return oimoffenRepository.isChgDependOnOffences(offenceId);
	}
	
}