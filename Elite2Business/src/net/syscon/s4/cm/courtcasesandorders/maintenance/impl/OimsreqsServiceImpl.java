package net.syscon.s4.cm.courtcasesandorders.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.courtcasesandorders.maintenance.OimsreqsRepository;
import net.syscon.s4.cm.courtcasesandorders.maintenance.OimsreqsService;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceCalcTypescommitBean;
import net.syscon.s4.common.beans.SentenceCustodyStatus;
import net.syscon.s4.common.beans.SentenceCustodyStatusCommitBean;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.SentenceTermscommitBean;
import net.syscon.s4.common.beans.SentenceUpdateReasons;
import net.syscon.s4.common.beans.SentenceUpdateReasonscommitBean;
import net.syscon.s4.genericservices.BaseBusiness;

/**
 * Class OimsreqsServiceImpl
 */
@Service
public class OimsreqsServiceImpl extends BaseBusiness implements OimsreqsService {

	@Autowired
	private OimsreqsRepository oimsreqsRepository;
	
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SentenceCalcTypes> senCalcExecuteQuery(final SentenceCalcTypes searchRecord) {
		return oimsreqsRepository.senCalcExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEN_CALC
	 */
	@Transactional
	public List<SentenceCalcTypes> senCalcCommit(final SentenceCalcTypescommitBean commitBean) {
		final List<SentenceCalcTypes> liReturnData = new ArrayList<>();
		final SentenceCalcTypes sentenceCalcTypes = new SentenceCalcTypes();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (SentenceCalcTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senCalcInsertSentenceCalcTypes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (SentenceCalcTypes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senCalcUpdateSentenceCalcTypes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (SentenceCalcTypes obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senCalcDeleteSentenceCalcTypes(commitBean.getDeleteList());
		}
		sentenceCalcTypes.setListSeq(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceCalcTypes);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SentenceTerms> senTermsExecuteQuery(final SentenceTerms searchRecord) {
		return oimsreqsRepository.senTermsExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEN_TERMS
	 */
	@Transactional
	public List<SentenceTerms> senTermsCommit(final SentenceTermscommitBean commitBean) {
		final List<SentenceTerms> liReturnData = new ArrayList<>();
		final SentenceTerms sentenceterms = new SentenceTerms();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (SentenceTerms obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senTermsInsertSentenceTerms(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (SentenceTerms obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senTermsUpdateSentenceTerms(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (SentenceTerms obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senTermsDeleteSentenceTerms(commitBean.getDeleteList());
		}
		sentenceterms.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceterms);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<SentenceUpdateReasons> senUpdExecuteQuery(final SentenceUpdateReasons searchRecord) {
		return oimsreqsRepository.senUpdExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEN_UPD
	 */
	@Transactional
	public List<SentenceUpdateReasons> senUpdCommit(final SentenceUpdateReasonscommitBean commitBean) {
		final List<SentenceUpdateReasons> liReturnData = new ArrayList<>();
		final SentenceUpdateReasons sentenceUpdateReasons = new SentenceUpdateReasons();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (SentenceUpdateReasons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senUpdInsertSentenceUpdateReasons(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (SentenceUpdateReasons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senUpdUpdateSentenceUpdateReasons(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (SentenceUpdateReasons obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.senUpdDeleteSentenceUpdateReasons(commitBean.getDeleteList());
		}
		sentenceUpdateReasons.setReturnedOutput(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceUpdateReasons);
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgCatRecordGroup() {
		return oimsreqsRepository.rgCatRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgSentRecordGroup() {
		return oimsreqsRepository.rgSentRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgSvcOblRecordGroup() {
		return oimsreqsRepository.rgSvcOblRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgTermCodeRecordGroup() {
		return oimsreqsRepository.rgTermCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LegalUpdateReasons> rgReasonRecordGroup() {
		return oimsreqsRepository.rgReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		return oimsreqsRepository.rgFunctionTypeRecordGroup();

	}

	/**
	 * This method is used to get the nbt status value
	 *
	 * @param params
	 */
	public SentenceUpdateReasons getNbtStatusValue(SentenceUpdateReasons senUpdReasons) {
		senUpdReasons = oimsreqsRepository.getResDescValues(senUpdReasons);
		senUpdReasons = oimsreqsRepository.getNbtStatus(senUpdReasons);
		return senUpdReasons;
	}
	

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Integer senCalcKeyDelrec(final SentenceCalcTypes paramBean) {
		return oimsreqsRepository.senCalcKeyDelrec(paramBean);
	}
	
	
	@Override
	public List<ReferenceCodes> getCustodyStatus() {
		List<ReferenceCodes> custodyCodes=oimsreqsRepository.getCustodyStatus();
		custodyCodes.forEach(bean->{
			if (bean.getActiveFlag() != null  && bean.getActiveFlag().equals(ApplicationConstants.NFLAG)) {
				bean.setCanDisplay(false);
			}
		});
		return custodyCodes;
	}
	
	@Override
	public List<ReferenceCodes> getOrderStatus() {
		List<ReferenceCodes> orderCodes=oimsreqsRepository.getOrderStatus();
		orderCodes.forEach(bean->{
			if (bean.getActiveFlag() != null  && bean.getActiveFlag().equals(ApplicationConstants.NFLAG)) {
				bean.setCanDisplay(false);
			}
		});
		return orderCodes;
	}
	
	
	@Transactional
	public Integer custodyCommit(final SentenceCustodyStatusCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (SentenceCustodyStatus obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.saveCustodyStatus(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (SentenceCustodyStatus obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.updateCustodyStatus(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (SentenceCustodyStatus obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsreqsRepository.deleteCustodyStatus(commitBean.getDeleteList());
		}
		return liReturn;
	}

	
	@Override
	public List<SentenceCustodyStatus> fetchCustodyStatus(SentenceCustodyStatus status) {
		return oimsreqsRepository.fetchCustodyStatus(status);
	}

	
}