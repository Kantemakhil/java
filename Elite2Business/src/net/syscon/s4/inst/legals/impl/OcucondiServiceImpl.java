package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.legals.OcucondiRepository;
import net.syscon.s4.inst.legals.OcucondiService;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditionsCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.pkgs.deductions.DeductionsService;
import net.syscon.s4.triggers.OffenderCaseConditionsT1Service;
import net.syscon.s4.triggers.OffenderSentConditionsT1Service;
import net.syscon.s4.triggers.OffenderSentConditionsT2Service;
import net.syscon.s4.triggers.OffenderSentConditionsTwfService;

@Service
public class OcucondiServiceImpl implements OcucondiService {

	@Autowired
	OcucondiRepository repository;
	public static final String CONSTANTVALUE = "2";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String EXCEPTION = "exception";
	@Autowired
	private DeductionsService deductionsService;
	@Autowired
	OffenderSentConditionsT2Service offenderSentConditionsT2Service;
	@Autowired
	OffenderSentConditionsT1Service offenderSentConditionsT1Service;
	@Autowired
	OffenderSentConditionsTwfService offenderSentConditionsTwfService;
	@Autowired
	OffenderCaseConditionsT1Service offenderCaseConditionsT1Service;
	@Autowired
	OcmpconfService  ocmpconfService;

	@Override
	public List<BondType> getCategory() {
		return repository.getCategory();
	}

	@Override
	public List<BondType> getUnit() {
		return repository.getUnit();
	}

	@Override
	public List<CommonLov> getProgram() {
		return repository.getProgram();
	}

	@Override
	public List<OffenderSentConditions> getConditionTypeGrid(OffenderSentConditions condition) {
		return repository.getConditionTypeGrid(condition);
	}

	@Override
	public List<Condition> getConditionGrid(Condition condition) {
		return repository.getConditionGrid(condition);
	}

	@Override
	public List<OffenderSentConditions> getCompleteConditionGrid(OffenderSentConditions condition) {
		return repository.getCompleteConditionGrid(condition);
	}

	private String getProgramCode(Long programId) {
		return repository.getProgramCode(programId);
	}

	@Override
	public List<CommonLov> getConditionLov(Condition condition) {
		return repository.getConditionLov(condition);
	}

	@Override
	public List<CommonLov> getConditionLov(String condition, String catogry) {
		List<CommonLov> returnList = repository.getConditionLov(condition, catogry);
		for (CommonLov bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;
	}

	public Integer getPreInsertSentSentenseId() {
		return repository.getPreInsertSentSentenseId();
	}

	@Override
	public List<LegalUpdateReasons> populateCaseStatus(String orderType) {
//		return repository.populateCaseStatus();
		return ocmpconfService.rgOrderStatus(orderType);

	}

	@Override
	public String fetchOrderTypeDesc(String conditionType) {
		return repository.fetchOrderTypeDesc(conditionType);
	}

	@Override
	public Condition populateProgramComment(Condition condition) {
		Condition getCondition = repository.populateProgramComment(condition);
		if (null != getCondition.getProgramId() && getCondition.getProgramId() > 0) {
			String getProgramCode = getProgramCode(getCondition.getProgramId());
			getCondition.setProgram(getProgramCode);
		}
		return getCondition;
	}

	@Override
	public List<Category> populateSentencesCategory() {
		return repository.populateSentencesCategory();
	}

	@Override
	public List<CommonLov> populateProhibitedLov() {
		return repository.populateProhibitedLov();
	}

	@Override
	public String offSentConCommit(OffenderSentConditionsCommitBean commitBean) {
		String returnRows = null;
		List<OffenderCondTransfer> offCondTrfList = new ArrayList<OffenderCondTransfer>();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderSentConditions bean : commitBean.getInsertList()) {
				
				bean.setCreateUserId(commitBean.getCreateUserId());
				long seq = repository.preInsertGetSeq();
				bean.setOffenderSentConditionId(seq);				
				if ("ACP".equals(bean.getProgramMethod()) || "UW".equals(bean.getProgramMethod())) {
					Integer acpCount = isDuplicateDetailAcp(bean);
					if (acpCount > 0) {
						return CONSTANTVALUE;
					}
				}
				String agyLocId = repository.findOffenderAgyLocId(bean.getOffenderBookId());
				OffenderCondTransfer obj = new OffenderCondTransfer();
				obj.setOffenderSentConditionId(new BigDecimal(seq));
				obj.setOffenderBookId(new BigDecimal(bean.getOffenderBookId()));
				obj.setAgyLocId(agyLocId != null?agyLocId:bean.getAgyLocId());
				obj.setCondiStatus("UN-ASSIGNED");
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setSentenceSeq(bean.getSentenceSeq().intValue());
				offCondTrfList.add(obj);
			}
			returnRows = repository.insertoffSentConditionData(commitBean.getInsertList());
			if(returnRows != null && "1".equals(returnRows)) {
				repository.postInsertIntoOffenderCondTrf(offCondTrfList);
			}
			for (OffenderSentConditions obj : commitBean.getInsertList()) {
				offenderSentConditionsT2Service.OffenderSentConditionsT2Tgr(obj);
				offenderCaseConditionsT1Service.offenderCaseConditionsT1(obj);
			}
			for (OffenderSentConditions bean : commitBean.getInsertList()) {
				if ("FIN".equals(bean.getProgramMethod())) {
					bean.setCreateUserId(commitBean.getCreateUserId());
					String returnVal = postInsert(bean);
					if (!"success".equals(returnVal)) {
						return returnVal;
					}
				}
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderSentConditions bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				if ("ACP".equals(bean.getProgramMethod()) || "UW".equals(bean.getProgramMethod())) {
					Integer acpCount = isDuplicateDetailAcp(bean);
					if (acpCount > 0) {
						return CONSTANTVALUE;
					}
				}
				List<OffenderSentConditions> listObj = new ArrayList<OffenderSentConditions>();
				listObj.add(bean);
				offenderSentConditionsTwfService.offenderSentConditionsTwfTgr(listObj);
				returnRows = repository.updateoffsentConData(commitBean.getUpdateList());
				offenderCaseConditionsT1Service.offenderCaseConditionsT1(bean);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			//PreDelete 
			for (OffenderSentConditions condition : commitBean.getDeleteList()) {
				condition.setModifyUserId(commitBean.getCreateUserId());
				List<OffenderCondTransfer> transferConditions = new ArrayList<OffenderCondTransfer>();
				transferConditions = repository.getCondTransferData(new BigDecimal(condition.getOffenderSentConditionId()));
				//Remove Transfer Conditions
				for (OffenderCondTransfer object : transferConditions) {
					object.setModifyUserId(commitBean.getCreateUserId());
				}
				repository.deleteOffederCondTransfer(transferConditions);
				List<OffenderCaseConditions> offenderCaseConditions = new ArrayList<OffenderCaseConditions>();
				offenderCaseConditions = repository.getCasePlanConditions(new BigDecimal(condition.getOffenderSentConditionId()));
				// Remove Case Plan Conditions
				for (OffenderCaseConditions object : offenderCaseConditions) {
					object.setModifyUserId(commitBean.getCreateUserId());
				}
				repository.deleteCasePlanConditions(offenderCaseConditions);
			}
			returnRows = repository.deleteOffSenConditionData(commitBean.getDeleteList());
			if (!"1".equals(returnRows)) {
				return returnRows;
			}
		}
		if (commitBean.getTypedeleteList() != null && !commitBean.getTypedeleteList().isEmpty()) {
			for (OffenderSentConditions offenderCondTransfer : commitBean.getTypedeleteList()) {
				offenderCondTransfer.setModifyUserId(commitBean.getCreateUserId());
			}
			returnRows = repository.deleteTypeConditionData(commitBean.getTypedeleteList());
			if (!"1".equals(returnRows)) {
				return returnRows;
			}
		}
		if ("1".equals(returnRows)) {
			return SUCCESS;
		} else {
			return FAIL;
		}
	}

	@Override
	public Integer isDuplicateDetailAcp(OffenderSentConditions bean) {
		return repository.isDuplicateDetailAcp(bean);
	}

	@Override
	public List<OffenderSentConditions> getDefaultConditions(OffenderSentConditions sentConBean) {
		return repository.getDefaultConditions(sentConBean);
	}

	@Override
	public String postInsert(OffenderSentConditions bean) {
		Integer lvCnt = repository.getLvCnt(bean);
		if (lvCnt == 1) {
			Integer lvRootOffId = repository.getLvRootoffenderId(bean);
			bean.setRootOffenderId(lvRootOffId.longValue());
			String lvOpenAnAccount = repository.chkAccountStatus(bean.getCaseloadId(), bean.getRootOffenderId());
			if ("X".equals(lvOpenAnAccount)) {
				return EXCEPTION;
			} else {
				String lvCaseInfoNumber = repository.getLvCaseInfoNumber(bean);
				if (lvCaseInfoNumber != null) {
					bean.setCaseInfoNumber(lvCaseInfoNumber);
				}
//				Integer createConDed = repository.createConditionDeductions(bean);
				Integer createConDed = deductionsService.createConditionDeductions(bean, bean.getCreateUserId());
				if (createConDed == 0) {
					return EXCEPTION;
				}
			}
		}
		return SUCCESS;

	}
}
