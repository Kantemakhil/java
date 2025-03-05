package net.syscon.s4.cf.deductions.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.cf.deductions.OcdofaccService;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfilesCommitBean;
import net.syscon.s4.cf.deductions.beans.OcmofaccCommitBean;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.triggers.OffFeeAccountOverridesThtyService;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;
import net.syscon.s4.triggers.OffFeeBillsT1Service;
import net.syscon.s4.triggers.OffFeeBillsT2Service;

import java.text.SimpleDateFormat;

/**
 * Class OtdocfeeServiceImpl
 */
@Service
public class OcdofaccServiceImpl extends BaseBusiness implements OcdofaccService {

	private static Logger logger = LogManager.getLogger(OcdofaccServiceImpl.class.getName());
	@Autowired
	private OcdofaccRepository ocdofaccRepository;
	@Autowired
	private OcdsupstRepository ocdsupstRepository;
	
	@Autowired
	private OffFeeAccountProfileT2Service offFeeAccountProfileT2Service;
	@Autowired
	private OffFeeBillsT1Service offFeeBillsT1Service;
	@Autowired
	private OffFeeBillTransactionsT1Service offFeeBillTransactionsT1Service;
	@Autowired
	private OffFeeBillTransactionsT2Service offFeeBillTransactionsT2Service;
	@Autowired
	private OffFeeAccountOverridesThtyService offFeeAccountOverridesThtyService;
	@Autowired
	private OffFeeBillsT2Service offFeeBillsT2Service;
	
	@Override
	public List<ReferenceCodes> feeActTypeRecordGroup(final String caseloadId) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = ocdofaccRepository.feeActTypeRecordGroup(caseloadId);
		for (ReferenceCodes referenceCodes : returnList) {
			if ("Y".equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}

		}
		return returnList;
	}

	@Override
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(final CaseloadDeductionProfiles searchRecord) {
		List<CaseloadDeductionProfiles> resultList = ocdofaccRepository.caseloadDedProfExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			final String nbtModifyUserId = ocdofaccRepository.calculateOn(data.getDeductionType());
			// ocdofaccRepository.getFreequencyValues(searchRecord.getLocation(),searchRecord.getDeductionType());
			data.setNbtModifyUserId(nbtModifyUserId);
			final String frequency = ocdofaccRepository.getDescription(data.getFrequencyType(), "FEE_ACT_FREQ");
			data.setNbtFrequency(frequency);
			final String code = ocdofaccRepository.getDescription(data.getFrequencyCode(), "RECUR_FREQ");
			data.setNbtCode(code);
		});
		return resultList;

	}

	@Override
	public List<CaseloadDedBeneficiaries> caseloadDedbenficExecuteQuery(final CaseloadDedBeneficiaries searchRecord) {
		List<CaseloadDedBeneficiaries> resultList = ocdofaccRepository.caseloadDedBenficExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			data.setOffenderFeeId(searchRecord.getOffenderFeeId());
			if (data.getPersonId() != null) {
				Persons person = ocdofaccRepository.ocmofaccPersons(data.getPersonId().longValue());
				if (person != null) {
					data.setDspLastName(person.getLastName());
					data.setDspFirstName(person.getFirstName());
				}
			}

			if (data.getCorporateId() != null) {
				Corporates corporates = ocdofaccRepository.ocmofaccCorporates(data.getCorporateId());
				if (corporates != null) {
					data.setDspCorporateName(corporates.getCorporateName());
				}
			}

		});

		return resultList;
	}

	@Override
	public List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(final CaseloadDeductionDetails searchRecord) {
		List<CaseloadDeductionDetails> returnList = new ArrayList<>();
		for (CaseloadDeductionDetails data : returnList) {
			data.setOffenderFeeId(searchRecord.getOffenderFeeId());
		}
		returnList = ocdofaccRepository.caseloadDedDetExecuteQuery(searchRecord);
		return returnList;

	}

	@Override
	public List<FeeAccountProfiles> offDedExecuteQuery(FeeAccountProfiles searchObject) {
		List<FeeAccountProfiles> returnList = new ArrayList<>();
		returnList = ocdofaccRepository.offDedExecuteQuery(searchObject);
		for (FeeAccountProfiles feeAccountProfiles : returnList) {
			OffenderBookingAgyLocs searchBean = new OffenderBookingAgyLocs();
			searchBean.setCaseloadId(searchObject.getLocation());
			searchBean.setOffenderBookId(feeAccountProfiles.getOffenderBookId());
			final List<OffenderBookingAgyLocs> returnListOne = ocdsupstRepository.offBkgAgyLocExecuteQuery(searchBean);
			for (OffenderBookingAgyLocs offenderBookingAgyLocs : returnListOne) {
				if (offenderBookingAgyLocs != null
						&& offenderBookingAgyLocs.getCaseloadId().equals(feeAccountProfiles.getCaseloadId())) {
					feeAccountProfiles.setCaseloadUpdateAllowFlag("Y");
					break;
				}
			}
			List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(feeAccountProfiles.getFeeCode(),
					feeAccountProfiles.getCaseloadId());
			if (!returnData.isEmpty()) {
				feeAccountProfiles.setFoAlAllOffenderFlag(returnData.get(0).getFoAlAllOffenderFlag());
				feeAccountProfiles.setBackBill(returnData.get(0).getBackBill());
			}

			List<CaseloadDeductionProfiles> returnObject = ocdofaccRepository
					.getFreequencyValues(feeAccountProfiles.getCaseloadId(), feeAccountProfiles.getFeeCode());
			if (!returnObject.isEmpty()) {
				feeAccountProfiles.setFreequencyType(returnObject.get(0).getFrequencyType());
				feeAccountProfiles.setFrequencyCode(returnObject.get(0).getFrequencyCode());
				final String frequency = ocdofaccRepository.getDescription(returnObject.get(0).getFrequencyType(),
						"FEE_ACT_FREQ");
				feeAccountProfiles.setNbtFrequency(frequency);
				final String code = ocdofaccRepository.getDescription(returnObject.get(0).getFrequencyCode(),
						"RECUR_FREQ");
				feeAccountProfiles.setNbtCode(code);
				feeAccountProfiles.setNonBillableStatus(returnObject.get(0).getNonBillableStatus());
			}
			if ("Y".equals(feeAccountProfiles.getCaseloadUpdateAllowFlag())) {
				feeAccountProfiles.setCaseloadUpdateAllowFlag("Y");
			} else {
				feeAccountProfiles.setCaseloadUpdateAllowFlag("N");
			}
		}
		return returnList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer ocmofaccCommit(OcmofaccCommitBean commitBean) {
		int returnValue = 0;
		String exclude="N";
		OcmofaccCommitBean deleteObject = new OcmofaccCommitBean();
		if (!commitBean.getOffdedCommitBean().getDeleteList().isEmpty()) {
			List<FeeAccountProfiles> deleteList = commitBean.getOffdedCommitBean().getDeleteList();
			commitBean.getOffdedCommitBean().setDeleteList(new ArrayList<>());
			FeeAccountProfilesCommitBean feeObject = new FeeAccountProfilesCommitBean();
			feeObject.setDeleteList(deleteList);
			deleteObject.setOffdedCommitBean(feeObject);
		}
		if (!commitBean.getCslddbenCommitBean().getDeleteList().isEmpty()) {
			List<CaseloadDedBeneficiaries> deleteListOne = commitBean.getCslddbenCommitBean().getDeleteList();
			commitBean.getCslddbenCommitBean().setDeleteList(new ArrayList<>());
			CaseloadDedBeneficiariesCommitBean benObject = new CaseloadDedBeneficiariesCommitBean();
			benObject.setDeleteList(deleteListOne);
			deleteObject.setCslddbenCommitBean(benObject);
		}
		if (!commitBean.getCsldddCommitBean().getDeleteList().isEmpty()) {
			List<CaseloadDeductionDetails> deleteListTwo = commitBean.getCsldddCommitBean().getDeleteList();
			commitBean.getCsldddCommitBean().setDeleteList(new ArrayList<>());
			CaseloadDeductionDetailsCommitBean dedObject = new CaseloadDeductionDetailsCommitBean();
			dedObject.setDeleteList(deleteListTwo);
			deleteObject.setCsldddCommitBean(dedObject);
		}
		if(!commitBean.getCslddbenCommitBean().getUpdateList().isEmpty()) {
			exclude ="Y";
		} else {
			exclude ="N";
		}
		offdedCommitBean(commitBean.getOffdedCommitBean(), commitBean.getLongSupvModelUpdate(),exclude,commitBean.getCreateUserId());
		cslddbenCommitBean(commitBean.getCslddbenCommitBean(), commitBean.getCreateUserId());
		csldddCommitBean(commitBean.getCsldddCommitBean(), commitBean.getCreateUserId());
		if ((deleteObject.getOffdedCommitBean() != null && deleteObject.getOffdedCommitBean().getDeleteList() != null)
				|| (deleteObject.getCslddbenCommitBean() != null
						&& deleteObject.getCslddbenCommitBean().getDeleteList() != null)
				|| (deleteObject.getCsldddCommitBean() != null
						&& deleteObject.getCsldddCommitBean().getDeleteList() != null)) {
			for (final CaseloadDeductionDetails object : deleteObject.getCsldddCommitBean().getDeleteList()) {
				object.setModifyUserId(commitBean.getCreateUserId());
			}
			Integer gridDelete = deleteOcdofaccGridDetails(deleteObject);
		}
		if (commitBean.getLongSupvModelUpdate() != null
				&& "UPDATE".equals(commitBean.getLongSupvModelUpdate().getInsertUpdateString())
				&& commitBean.getLongSupvModelUpdate().getLongestSupvExpDate() != null) {
			updateSupvLongExpiryDate(commitBean.getLongSupvModelUpdate(),commitBean.getOffdedCommitBean(), commitBean.getCreateUserId());
		}

		if (commitBean.getLongSupvModelUpdate() != null
				&& "INSERT".equals(commitBean.getLongSupvModelUpdate().getInsertUpdateString())
				&& commitBean.getLongSupvModelUpdate().getLongestSupvExpDate() != null) {
			insertSupvLongExpiryDate(commitBean.getLongSupvModelUpdate(),commitBean.getOffdedCommitBean(), commitBean.getCreateUserId());
		}

		try {
			commitBean.getCslddbenCommitBean().getInsertList().forEach(data -> {
				BigDecimal maxAmount = ocdofaccRepository.getMaxAmount(data.getOffenderFeeId());
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(), maxAmount, data.getOffenderFeeId());
			});
			commitBean.getCslddbenCommitBean().getUpdateList().forEach(data -> {
				BigDecimal maxAmount = ocdofaccRepository.getMaxAmount(data.getOffenderFeeId());
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(), maxAmount, data.getOffenderFeeId());
			});
			if(deleteObject.getCslddbenCommitBean() != null) {
			deleteObject.getCslddbenCommitBean().getDeleteList().forEach(data -> {
				BigDecimal maxAmount = ocdofaccRepository.getMaxAmount(data.getOffenderFeeId());
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(), maxAmount, data.getOffenderFeeId());
			});
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		// updating percentage
		try {
			commitBean.getCslddbenCommitBean().getInsertList().forEach(data -> {
				percentage(data,commitBean.getCreateUserId());
			});
			commitBean.getCslddbenCommitBean().getUpdateList().forEach(data -> {
				percentage(data,commitBean.getCreateUserId());
			});
			commitBean.getCslddbenCommitBean().getDeleteList().forEach(data -> {
				percentage(data,commitBean.getCreateUserId());
			});
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.othrerrinprce");
		}
		returnValue = 1;

		
		
		
		return returnValue;
	}

	@Transactional
	public Integer updateSupvLongExpiryDate(FeeAccountProfiles longSupvModelUpdate,FeeAccountProfilesCommitBean offdedCommitBean, String userId) {
		Integer retValue = 0;
		List<FeeAccountProfiles> updateList = new ArrayList<FeeAccountProfiles>();
		longSupvModelUpdate.setModifyUserId(userId);
		updateList.add(longSupvModelUpdate);
		 retValue = ocdofaccRepository.updateSupvLongExpiryDate(updateList);
		if(retValue == 1 && (offdedCommitBean.getInsertList() != null && offdedCommitBean.getInsertList().size() == 0)
				&& (offdedCommitBean.getUpdateList() != null && offdedCommitBean.getUpdateList().size() == 0)) {
			ocdofaccRepository.updateFeeAcntProfileExpireDate(longSupvModelUpdate);
		}
		return retValue;
	}

	@Transactional
	private Integer insertSupvLongExpiryDate(FeeAccountProfiles longSupvModelUpdate,FeeAccountProfilesCommitBean offdedCommitBean, String userId) {
		Integer retValue = 0;
		List<FeeAccountProfiles> updateList = new ArrayList<FeeAccountProfiles>();
		longSupvModelUpdate.setCreateUserId(userId);
		updateList.add(longSupvModelUpdate);
		retValue = ocdofaccRepository.insertSupvLongExpiryDate(updateList);
		if(retValue == 1 && (offdedCommitBean.getInsertList() != null && offdedCommitBean.getInsertList().size() == 0)
				&& (offdedCommitBean.getUpdateList() != null && offdedCommitBean.getUpdateList().size() == 0)) {
			longSupvModelUpdate.setModifyUserId(userId);
			ocdofaccRepository.updateFeeAcntProfileExpireDate(longSupvModelUpdate);
		}
		return retValue;

	}

	public Integer deleteOcdofaccGridDetails(OcmofaccCommitBean deleteObject) {
		Integer retValue = 0;
		if ((deleteObject.getCsldddCommitBean() != null && deleteObject.getCsldddCommitBean().getDeleteList() != null)
				&& (deleteObject.getCsldddCommitBean() != null
						&& deleteObject.getCsldddCommitBean().getDeleteList().size() > 0)) {
			
			retValue = ocdofaccRepository.csldddDeleteQuery(deleteObject.getCsldddCommitBean().getDeleteList());
		}
		if ((deleteObject.getCslddbenCommitBean() != null
				&& deleteObject.getCslddbenCommitBean().getDeleteList() != null)
				&& (deleteObject.getCslddbenCommitBean() != null
						&& deleteObject.getCslddbenCommitBean().getDeleteList().size() > 0)) {
			retValue = ocdofaccRepository.cslddbenDeleteQuery(deleteObject.getCslddbenCommitBean().getDeleteList());
		}
		if ((deleteObject.getOffdedCommitBean() != null && deleteObject.getOffdedCommitBean().getDeleteList() != null)
				&& (deleteObject.getOffdedCommitBean() != null
						&& deleteObject.getOffdedCommitBean().getDeleteList().size() > 0)) {
			
			for (FeeAccountProfiles object : deleteObject.getOffdedCommitBean().getDeleteList()) {
				List<FeeAccountProfiles> returnList = ocdofaccRepository.feeAccountProfileHistory(object);
				if (returnList != null && !returnList.isEmpty()) {
					for (final FeeAccountProfiles obj : returnList) {
						obj.setModifyUserId(object.getModifyUserId());
					}
					retValue = ocdofaccRepository.deleteFeeAccountProfileHistory(returnList);
				}
				/*
				 * if("Y".equals(object.getNonBillableStatus())) {
				 * ocdofaccRepository.deletSupvHistoryRecord(object); }
				 */
			}
			retValue = ocdofaccRepository.offdedDeleteQuery(deleteObject.getOffdedCommitBean().getDeleteList());
		}
		return retValue;
	}

	@Transactional
	@Override
	public Integer offdedCommitBean(FeeAccountProfilesCommitBean offdedCommitBean, FeeAccountProfiles longestSupvDate, String exclude, String userId) {
		Integer retValue = 0;

		if (offdedCommitBean.getInsertList() != null && offdedCommitBean.getInsertList().size() > 0) {
			for(FeeAccountProfiles obj : offdedCommitBean.getInsertList()) {
				if("Y".equals(obj.getNonBillableStatus()) && (longestSupvDate != null && longestSupvDate.getLongestSupvExpDate() != null)) {
					obj.setExpiryDate(longestSupvDate.getLongestSupvExpDate());					
				}
				obj.setCreateUserId(userId);
			}
			retValue = ocdofaccRepository.offdedInsertQuery(offdedCommitBean.getInsertList());
			for (FeeAccountProfiles obj : offdedCommitBean.getInsertList()) {
				obj.setCreateUserId(userId);
				offFeeAccountProfileT2Service.offFeeAccountProfileT2(obj);
			}
			//ocdofaccRepository
			
		}

		if (offdedCommitBean.getUpdateList() != null && offdedCommitBean.getUpdateList().size() > 0) {
			for(FeeAccountProfiles obj: offdedCommitBean.getUpdateList()) {
				if("Y".equals(obj.getNonBillableStatus()) && (longestSupvDate != null && longestSupvDate.getLongestSupvExpDate() != null)) {
					obj.setExpiryDate(longestSupvDate.getLongestSupvExpDate());
					obj.setIsTriggerEnable("Y");
					obj.setModifyUserId(userId);
				}
				if("Y".equalsIgnoreCase(obj.getIsTriggerEnable())) {
					retValue = ocdofaccRepository.offDedUpdateFullQuery(obj);
					//obj.setCreateUserId(userId);
					offFeeAccountProfileT2Service.offFeeAccountProfileT2(obj);
				}else {
					retValue = ocdofaccRepository.offFeeAcntUpdateQuery(obj);
				}
				if("N".equals(exclude)) {					
					BigDecimal maxAmount = ocdofaccRepository.getMaxAmount(obj.getOffenderFeeId());
					checkMaxAmount(obj.getCaseloadId(), obj.getFeeCode(), maxAmount, obj.getOffenderFeeId());
				}
				
				
				
			}
			//retValue = ocdofaccRepository.offdedUpdateQuery(offdedCommitBean.getUpdateList());
		}
		return retValue;
	}

	@Transactional
	@Override
	public Integer cslddbenCommitBean(CaseloadDedBeneficiariesCommitBean cslddbenCommitBean, String userId) {
		Integer retValue = 0;

		if (cslddbenCommitBean.getInsertList() != null && cslddbenCommitBean.getInsertList().size() > 0) {
			for (CaseloadDedBeneficiaries obj : cslddbenCommitBean.getInsertList()) {
				obj.setCreateUserId(userId);
			}
			retValue = ocdofaccRepository.cslddbenInsertQuery(cslddbenCommitBean.getInsertList());
		}

		if (cslddbenCommitBean.getUpdateList() != null && cslddbenCommitBean.getUpdateList().size() > 0) {
			for (CaseloadDedBeneficiaries obj : cslddbenCommitBean.getUpdateList()) {
				obj.setModifyUserId(userId);
			}
			retValue = ocdofaccRepository.cslddbenUpdateQuery(cslddbenCommitBean.getUpdateList());
		}
		return retValue;
	}

	@Transactional
	@Override
	public Integer csldddCommitBean(CaseloadDeductionDetailsCommitBean csldddCommitBean, String userId) {
		Integer retValue = 0;

		if (csldddCommitBean.getInsertList() != null && csldddCommitBean.getInsertList().size() > 0) {
			for (CaseloadDeductionDetails obj : csldddCommitBean.getInsertList()) {
				obj.setCreateUserId(userId);
			}
			retValue = ocdofaccRepository.csldddInsertQuery(csldddCommitBean.getInsertList());
		}

		if (csldddCommitBean.getUpdateList() != null && csldddCommitBean.getUpdateList().size() > 0) {
			for (CaseloadDeductionDetails obj : csldddCommitBean.getUpdateList()) {
				obj.setModifyUserId(userId);
			}
			retValue = ocdofaccRepository.csldddUpdateQuery(csldddCommitBean.getUpdateList());
		}

		return retValue;
	}

	@Override
	public Persons ocmofaccPersons(final Long personId) {
		return ocdofaccRepository.ocmofaccPersons(personId);
	}

	public Corporates ocmofaccCorporates(BigDecimal corporateId) {
		return ocdofaccRepository.ocmofaccCorporates(corporateId);
	}

	@Override
	public Long feeOverrideDetailsExecuteQuery(long feeId) {
		return ocdofaccRepository.feeOverrideDetailsExecuteQuery(feeId);
	}

	public Boolean percentage(final CaseloadDedBeneficiaries bean, String userId) {
		BigDecimal myPercentage = BigDecimal.ZERO;
		try {
			List<CaseloadDedBeneficiaries> dedPriorities = ocdofaccRepository.dedPrioritiesFeeBenf(bean);
			if (dedPriorities != null) {
				for (CaseloadDedBeneficiaries data : dedPriorities) {
					if (BigDecimal.ZERO.equals(data.getAmount())) {
						myPercentage = BigDecimal.ZERO;
					} else {
						BigDecimal priorityAmt = getPriorityAmount(data.getCaseloadId(), data.getDeductionType(),
								data.getPriority(), data.getAmount(), data.getOffenderFeeId());
						myPercentage = data.getAmount().multiply(BigDecimal.valueOf(100)).divide(priorityAmt,2,RoundingMode.HALF_UP);
//						if (myPercentage != null) {
//							MathContext mcontext = new MathContext(2);
//							myPercentage = myPercentage.round(mcontext);
//						}
					}

					ocdofaccRepository.updateCaseloadDedBeneficiariesPercentage(myPercentage,
							data.getOffFeeDedBeneficiaryId(), data.getOffenderFeeId(), null);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.otrerrupdtingunltamt");
		}

		return true;
	}

	public BigDecimal getPriorityAmount(final String caseloadId, final String deductionType, final BigDecimal priority,
			final BigDecimal amount, final BigDecimal feeId) {
		BigDecimal priorityAmount = BigDecimal.ZERO;
		try {
			priorityAmount = ocdofaccRepository.getPriorityAmount(caseloadId, deductionType, priority, feeId);
			if (priorityAmount.compareTo(BigDecimal.ZERO) > 0) {
				return priorityAmount;
			} else {
				return amount;
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.errgtngpritorytot");
		}
	}

	public Boolean checkMaxAmount(final String caseloadId, final String deductionType, final BigDecimal maxTotalAmount,
			final BigDecimal feeId) {
		try {
			BigDecimal vBenCount = null;
			BigDecimal vBenTotal = null;
			BigDecimal vMaxTotal = maxTotalAmount;

			if (vMaxTotal == null) {
				vMaxTotal = BigDecimal.ZERO;
			}

			Map<String, Object> calcBenTotal = calcBenTotal(caseloadId, deductionType, feeId);
			if (calcBenTotal != null) {
				if (calcBenTotal.get("P_BEN_COUNT") != null) {
					vBenCount = new BigDecimal(calcBenTotal.get("P_BEN_COUNT").toString());
				}
				if (calcBenTotal.get("P_BEN_TOTAL") != null) {
					vBenTotal = new BigDecimal(calcBenTotal.get("P_BEN_TOTAL").toString());
				}
			}
				if (vMaxTotal.compareTo(vBenTotal) != 0) {
					throw new RuntimeException("otmfopro.benetotisnteqal");
				}

		} catch (Exception e) {
			if ("otmfopro.benetotisnteqal".equalsIgnoreCase(e.getMessage())) {
				throw new RuntimeException(e.getMessage());
			} else {
				throw new RuntimeException("otmfopro.errchkmaxamt");
			}
		}
		return true;
	}

	public Map<String, Object> calcBenTotal(final String caseloadId, final String deductionType, BigDecimal feeId) {
		return ocdofaccRepository.calcBenTotal(caseloadId, deductionType, feeId);
	}

	@Override
	public List<ReferenceCodes> reciptTypeRecordGroup(final String caseloadType) {
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = ocdofaccRepository.reciptTypeRecordGroup(caseloadType);
		for (ReferenceCodes agencyLocations : returnList) {
			if ("Y".equals(agencyLocations.getActiveFlag())) {
				agencyLocations.setCanDisplay(true);
			} else {
				agencyLocations.setCanDisplay(false);
			}
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> alAgyLocIdRgRecordGroup() {
		return ocdofaccRepository.alAgyLocIdRgRecordGroup();
	}

	@Override
	public Map<String, Object> getlongSupvDate(final String caseloadId) {
		Map<String, Object> returnMap = new HashMap();
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, 2);
		Date nextYear = cal.getTime();
		Date supvPeriodStartDate = ocdofaccRepository.getSupvPeriodDate(caseloadId);
		returnMap.put("longSupvDate", nextYear);
		returnMap.put("supvPeriodStartDate", supvPeriodStartDate);
		return returnMap;
	}

	@Override
	public List<CaseloadDedBeneficiaries> caseloadFeeDedBenficExecuteQuery(
			final CaseloadDedBeneficiaries searchRecord) {
		List<CaseloadDedBeneficiaries> resultList = ocdofaccRepository.caseloadFeeDedBenficExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			if (data.getPersonId() != null) {
				Persons person = ocdofaccRepository.ocmofaccPersons(data.getPersonId().longValue());
				if (person != null) {
					data.setDspLastName(person.getLastName());
					data.setDspFirstName(person.getFirstName());
				}
			}

			if (data.getCorporateId() != null) {
				Corporates corporates = ocdofaccRepository.ocmofaccCorporates(data.getCorporateId());
				if (corporates != null) {
					data.setDspCorporateName(corporates.getCorporateName());
				}
			}

		});

		return resultList;
	}

	@Override
	public List<CaseloadDeductionDetails> caseloadFeeDetExecuteQuery(final CaseloadDeductionDetails searchRecord) {
		return ocdofaccRepository.caseloadFeeDetExecuteQuery(searchRecord);

	}

	@Override
	public BigDecimal offdedPreInsert() {
		return ocdofaccRepository.offdedPreInsert();

	}

	@Override
	public List<FeeAccountProfiles> offdedPrevExecteQuery(FeeAccountProfiles searchObject) {
		List<FeeAccountProfiles> finalList = new ArrayList<>();
		List<OffenderBookings> offbookObj = new ArrayList<>();
		List<OffenderBookings> offbookingsList = new ArrayList<>();
		offbookObj = ocdofaccRepository.returnOffenderBookingObject(searchObject.getOffenderBookId());
		if (!offbookObj.isEmpty()) {
			offbookingsList = ocdofaccRepository
					.getOffenderPreviousOffenderBoookings(offbookObj.get(0).getRootOffenderId());
		}
		for (OffenderBookings offenderBookings : offbookingsList) {
			if (!searchObject.getOffenderBookId().equals(offenderBookings.getOffenderBookId())) {
				FeeAccountProfiles newObj = new FeeAccountProfiles();
				List<FeeAccountProfiles> returnList = new ArrayList<>();
				newObj.setOffenderBookId(offenderBookings.getOffenderBookId());
				returnList = ocdofaccRepository.offdedPrevExecteQuery(newObj);
				List<FeeAccountProfiles> supvDateObj = ocdofaccRepository.sysLongSupPflExecuteQuery(newObj);

				returnList.forEach(data -> {
					final String frequency = ocdofaccRepository.getDescription(data.getFrequency(), "FEE_ACT_FREQ");
					data.setNbtFrequency(frequency);
					final String code = ocdofaccRepository.getDescription(data.getCode(), "RECUR_FREQ");
					data.setNbtCode(code);
					data.setSupervisionPeriod(offenderBookings.getBookingNo());
					if (!supvDateObj.isEmpty()) {
						data.setLongestSupvExpDate(supvDateObj.get(0).getLongestSupvExpDate());
					}
					finalList.add(data);
				});

			}
		}
		for (FeeAccountProfiles feeAccountProfiles : finalList) {
			List<CaseloadDeductionProfiles> returnObject = ocdofaccRepository
					.getFreequencyValues(feeAccountProfiles.getCaseloadId(), feeAccountProfiles.getFeeCode());
			if (!returnObject.isEmpty()) {
				feeAccountProfiles.setFreequencyType(returnObject.get(0).getFrequencyType());
				feeAccountProfiles.setFrequencyCode(returnObject.get(0).getFrequencyCode());
				final String frequency = ocdofaccRepository.getDescription(returnObject.get(0).getFrequencyType(),
						"FEE_ACT_FREQ");
				feeAccountProfiles.setNbtFrequency(frequency);
				final String code = ocdofaccRepository.getDescription(returnObject.get(0).getFrequencyCode(),
						"RECUR_FREQ");
				feeAccountProfiles.setNbtCode(code);
				feeAccountProfiles.setNonBillableStatus(returnObject.get(0).getNonBillableStatus());
			}
		}
		return finalList;
	}

	@Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		return ocdofaccRepository.sysPflExecuteQuery();

	}

	@Override
	public List<FeeAccountProfiles> sysLongSupPflExecuteQuery(FeeAccountProfiles object) {
		return ocdofaccRepository.sysLongSupPflExecuteQuery(object);

	}

	@Override
	public void updateStatusForLongSupvDate() {
		List<FeeAccountProfiles> feeActProfList = ocdofaccRepository.getSupvFeeActProfilesDet();
		List<FeeAccountProfiles> newFeeActList = new ArrayList();
		if (!feeActProfList.isEmpty()) {
			for (FeeAccountProfiles fap : feeActProfList) {
				List<CaseloadDeductionProfiles> returnData = ocdofaccRepository.getBackBill(fap.getFeeCode(),
						fap.getCaseloadId());
				if (!returnData.isEmpty()
						&& "Y".equals(returnData.get(0).getNonBillableStatus())) {
					Date currDate = new Date();
					if (fap.getLongestSupvExpDate()!=null && fap.getLongestSupvExpDate().compareTo(currDate) <= 0) {
						if(!"C".equals(fap.getFeeActStatus())) {
						fap.setFeeActStatus("C");
						fap.setExpiryDate(fap.getLongestSupvExpDate());
						newFeeActList.add(fap);
						}
					}

				} else if ((!"C".equals(fap.getFeeActStatus())) && "N".equals(returnData.get(0).getNonBillableStatus())) {
					Date currDate = new Date();
					if (!"C".equals(fap.getFeeActStatus()) && fap.getExpiryDate()!=null && fap.getExpiryDate().compareTo(currDate) <= 0) {											
							fap.setFeeActStatus("C");
							newFeeActList.add(fap);
					}
				}

			}
		}
		if (!newFeeActList.isEmpty()) {
			logger.info("Updated Data is Saving now" + newFeeActList);
			List<FeeAccountProfiles> updatingList = new ArrayList<FeeAccountProfiles>();
			for (FeeAccountProfiles feeAccountProfiles : newFeeActList) {
				List<FeeAccountProfiles> currentDataRecord = ocdofaccRepository.getCurrentDataRecord(feeAccountProfiles.getOffenderFeeId());
			if(!currentDataRecord.isEmpty()) {
				if(!currentDataRecord.get(0).getFeeActStatus().equals(feeAccountProfiles.getFeeActStatus())){
					updatingList.add(feeAccountProfiles);
				}
			}
			}
			ocdofaccRepository.updateFeeActStatusForLongSupv(updatingList);
		}

	}

	@Override
	public List<SystemProfiles> sysPflDedExecuteQuery() {
		return ocdofaccRepository.sysPflDedExecuteQuery();
	}
	@Override
	public Date getOffenderEventDate(Long offenderBookId) {
		// TODO Auto-generated method stub
		return ocdofaccRepository.getOffenderEventDate(offenderBookId);
	}
	
	@Override
	public Map<String, Object>  offFeeBillsTrigger(String feeBillsBean) {
		Map<String, Object> respone = new HashMap<String, Object>();
		feeBillsBean = feeBillsBean.replace("\"[", "[").replace("\"{", "{").replace("}\"", "}").replace("]\"", "]");
		JSONObject obj = new JSONObject(feeBillsBean);
		JSONArray arr  = obj.getJSONArray("TriggerBean");
		String triggerName  = obj.getString("TriggerName");	
		SimpleDateFormat dateFormatOne = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat dateFormatTwo = new SimpleDateFormat("yyyy-MM-dd");
		if(triggerName.equalsIgnoreCase("OFF_FEE_BILLS_T1")) {
			OffFeeBills newBean = new OffFeeBills();
			try {
				if(arr != null) {
					for (int i = 0; i < arr.length(); i++) { 
						newBean.setBackBill(arr.getJSONObject(0).get("back_bill").toString().equalsIgnoreCase("")?null:arr.getJSONObject(0).get("back_bill").toString());
						if(!(arr.getJSONObject(i).get("billDate").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("billDate").equals(""))) {
							newBean.setBillDate(dateFormatOne.parse(arr.getJSONObject(i).get("billDate").toString()));}
						//Trigger call OFFFEEBILLS_T1
						String retVal = offFeeBillsT1Service.offFeeBillsT1(newBean);
						respone.put("Response", retVal);
					}
				}
			}
			catch (Exception e) {
				if(e.getMessage().contains("Unparseable date")) {
					try {
						for (int i = 0; i < arr.length(); i++) { 
							newBean.setBackBill(arr.getJSONObject(0).get("back_bill").toString().equalsIgnoreCase("")?null:arr.getJSONObject(0).get("back_bill").toString());
							newBean.setBillDate(dateFormatTwo.parse(arr.getJSONObject(i).get("billDate").toString()));
							//Trigger call OFFFEEBILLS_T1
							String retVal = offFeeBillsT1Service.offFeeBillsT1(newBean);
							respone.put("Response", retVal);
						}
					}
					catch (Exception et) {
						logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_BILLS_T1 block "+e);
						respone.put("Response", et.getMessage());
					}

				}
				else {
					logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_BILLS_T1 block "+e);
					respone.put("Response", e.getMessage());
				}
			}
		} 

		if(triggerName.equalsIgnoreCase("OFF_FEE_BILLS_T2")) {
			try {
				OffFeeBills oldBean = new OffFeeBills();
				OffFeeBillTransactions newBean = new OffFeeBillTransactions();
				if(arr != null) {
					for (int i = 0; i < arr.length(); i++) { 
						if(!(arr.getJSONObject(i).get("bill_ar_due_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("bill_ar_due_date").equals(""))) {
							newBean.setBillArDueDate(dateFormatTwo.parse(arr.getJSONObject(i).get("bill_ar_due_date").toString()));}
						if(!(arr.getJSONObject(i).get("bill_ar_start_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("bill_ar_start_date").equals(""))) {
							newBean.setBillArStartDate(dateFormatTwo.parse(arr.getJSONObject(i).get("bill_ar_start_date").toString()));}
						oldBean = ocdofaccRepository.getOldDataOffFeeBills(arr.getJSONObject(i).get("bill_id").toString());
						//Trigger call OFFFEEBILLS_T2
						String retVal = offFeeBillsT2Service.offFeeBillsT2(newBean,oldBean);
						respone.put("Response", retVal);
					}
				}
			}
			catch (Exception e) {
				logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_BILLS_T2 block "+e);
				respone.put("Response", e.getMessage());
			}
		} 

		if(triggerName.equalsIgnoreCase("OFF_FEE_BILL_TRANSACTIONS_T1")) {
			try {
				OffFeeBillTransactions newBean = new OffFeeBillTransactions();
				if(arr!=null) {
					for (int i = 0; i < arr.length(); i++) { 
						newBean.setBillStatus(arr.getJSONObject(i).get("billStatus").toString().equals("")?null:arr.getJSONObject(i).get("billStatus").toString());
						if(!(arr.getJSONObject(i).get("bill_aging_end_date").toString().equals("")||arr.getJSONObject(i).get("bill_aging_end_date").toString().equalsIgnoreCase("null"))){
							newBean.setBillAgingEndDate(dateFormatOne.parse(arr.getJSONObject(i).get("bill_aging_end_date").toString()));}
						//Trigger call OFF_FEE_BILL_TRANSACTIONS_T1
						offFeeBillTransactionsT1Service.offFeeBillTransactionsT1(newBean);
					}
				}
			}  catch (Exception e) {
				logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_BILL_TRANSACTIONS_T1 block "+e);
				respone.put("Response",e.getMessage());
			}
		}
		if(triggerName.equalsIgnoreCase("OFF_FEE_BILL_TRANSACTIONS_T2")) {
			try {
				OffFeeBillTransactions newBean = new OffFeeBillTransactions();
				if(arr!=null) {
					for (int i = 0; i < arr.length(); i++) { 
						newBean.setTrustTxnId(Integer.parseInt(arr.getJSONObject(i).get("trustTxnId").toString().equals("")?"0":(arr.getJSONObject(i).get("trustTxnId").toString())));
						newBean.setOffAdjTxnId(Integer.parseInt(arr.getJSONObject(i).get("offAdjTxnId").toString().equals("")?"0":(arr.getJSONObject(i).get("offAdjTxnId").toString())));
						newBean.setOriginalBillId(arr.getJSONObject(i).get("originalBillId").toString().equals("")?null:(arr.getJSONObject(i).get("originalBillId").toString()));
						newBean.setOffAdjRevRsn(arr.getJSONObject(i).get("offAdjRevRsn").toString().equals("")?null:(arr.getJSONObject(i).get("offAdjRevRsn").toString()));
						//Trigger call OFF_FEE_BILL_TRANSACTIONS_T1
						offFeeBillTransactionsT2Service.offFeeBillTransactionsT2(newBean);
					}
				}
			}catch (Exception e) {
				logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_BILL_TRANSACTIONS_T2 block "+e);
				respone.put("Response",e.getMessage());
			}

		}
		if(triggerName.equalsIgnoreCase("OFF_FEE_ACCOUNT_OVERRIDES_THTY")) {
			try {
				FeeOverrideDetails newBean = new FeeOverrideDetails();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if(arr!=null) {
					for (int i = 0; i < arr.length(); i++) { 
						newBean.setOperation("INS");
						newBean.setOffenderFeeId(Long.parseLong(arr.getJSONObject(i).get("offender_fee_id").toString().equalsIgnoreCase("null")?"0":arr.getJSONObject(i).get("offender_fee_id").toString()));
						newBean.setOffFeeOverrideId(Long.parseLong(arr.getJSONObject(i).get("off_fee_override_id").toString().equalsIgnoreCase("null")?"0L":arr.getJSONObject(i).get("off_fee_override_id").toString()));
						newBean.setOverrideType(arr.getJSONObject(i).get("off_fee_override_id").toString().equals("")?null:arr.getJSONObject(i).get("off_fee_override_id").toString());
						if(!(arr.getJSONObject(i).get("override_start_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("override_start_date").equals(""))) {
							newBean.setOverrideStartDate(simpleDateFormat.parse(arr.getJSONObject(i).get("override_start_date").toString()));}
						if(!(arr.getJSONObject(i).get("override_end_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("override_end_date").equals(""))) {
							newBean.setOverrideEndDate(simpleDateFormat.parse(arr.getJSONObject(i).get("override_end_date").toString()));}
						newBean.setOverrideAmount(Long.parseLong(arr.getJSONObject(i).get("override_amount").toString().equalsIgnoreCase("null")?"0L":arr.getJSONObject(i).get("override_amount").toString()));
						newBean.setPriorityIndicator(Integer.parseInt(arr.getJSONObject(i).get("priority_indicator").toString().equalsIgnoreCase("null")?"0":arr.getJSONObject(i).get("priority_indicator").toString()));
						newBean.setCreateUserId("SYSTEM");
						//Trigger call OFF_FEE_ACCOUNT_OVERRIDES_THTY
						Integer retVal = offFeeAccountOverridesThtyService.OffFeeAccountOvverRideHistory(newBean);
						respone.put("Response",retVal);
					}
				}
			} catch (ParseException e) {
				logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_ACCOUNT_OVERRIDES_THTY block "+e);
				respone.put("Response",e.getMessage());
			}
		}
		if(triggerName.equalsIgnoreCase("OFF_FEE_ACCOUNT_PROFILE_T2")) {
			try {
				FeeAccountProfiles newBean = new FeeAccountProfiles();
				if(arr!=null) {
					for (int i = 0; i < arr.length(); i++) { 
						newBean.setOffenderBookId(Long.parseLong(arr.getJSONObject(i).get("offender_book_id").toString()));
						newBean.setOffenderFeeId(new BigDecimal(arr.getJSONObject(i).get("offender_fee_id").toString()));
						newBean.setAmount(new BigDecimal(arr.getJSONObject(i).get("amount").toString()));
						if(!(arr.getJSONObject(i).get("start_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("start_date").equals(""))) {
							newBean.setStartDate(dateFormatOne.parse(arr.getJSONObject(i).get("start_date").toString()));} 
						if(!(arr.getJSONObject(i).get("expiry_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("expiry_date").equals(""))) {
							newBean.setExpiryDate(dateFormatOne.parse(arr.getJSONObject(i).get("expiry_date").toString()));}
						newBean.setFeeActStatus(arr.getJSONObject(i).get("fee_act_status").toString().equalsIgnoreCase("null")?null:arr.getJSONObject(i).get("fee_act_status").toString());
						if(!(arr.getJSONObject(i).get("effective_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("effective_date").equals(""))) {
							newBean.setEffectiveDate(dateFormatOne.parse(arr.getJSONObject(i).get("effective_date").toString()));} 
						if(!(arr.getJSONObject(i).get("status_effective_date").toString().equalsIgnoreCase("null")||arr.getJSONObject(i).get("status_effective_date").equals(""))) {
							newBean.setStatusEffectiveDate(dateFormatOne.parse(arr.getJSONObject(i).get("status_effective_date").toString()));} 
						newBean.setCreateUserId(arr.getJSONObject(i).get("createUserId").toString());
						//Trigger call OFF_FEE_ACCOUNT_PROFILE_T2
						Integer retVal = offFeeAccountProfileT2Service.offFeeAccountProfileT2(newBean);
						respone.put("Response",retVal);
					}
				}
			} catch (Exception e) {
				logger.error(this.getClass().getName()+" offFeeBillsTrigger method call, error occured in OFF_FEE_ACCOUNT_PROFILE_T2 block "+e);
				respone.put("Response",e.getMessage());
			} 
		}
		return respone;
	}
}
