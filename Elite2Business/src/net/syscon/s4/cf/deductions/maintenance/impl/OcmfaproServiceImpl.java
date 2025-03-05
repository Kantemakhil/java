package net.syscon.s4.cf.deductions.maintenance.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OcmfaproCommitBean;
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.CaseloadDedBeneficiariesCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.CaseloadDeductionDetailsCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfilesCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.maintenance.OcmfaproRepository;
import net.syscon.s4.cf.deductions.maintenance.OcmfaproService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OcmfaproServiceImpl extends BaseBusiness implements OcmfaproService {

	@Autowired
	private OcmfaproRepository ocmfaproRepository;

	@Override
	public List<ReferenceCodes> feeActTypeRecordGroup() {
		List<ReferenceCodes> returnList=new ArrayList<>();
		returnList=ocmfaproRepository.feeActTypeRecordGroup();
		for (ReferenceCodes referenceCodes : returnList) {
			if("Y".equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> locationRecordGroup(final String agyLocType) {
		return ocmfaproRepository.locationRecordGroup(agyLocType);
	}

	@Override
	public List<ReferenceCodes> creditDedToRecordGroup(final String caseloadType) {
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList =  ocmfaproRepository.creditDedToRecordGroup(caseloadType);
		for (ReferenceCodes obj : returnList) {
			if ("Y".equals(obj.getSealFlag())) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> frequencyRecordGroup() {
		return ocmfaproRepository.frequencyRecordGroup();
	}

	@Override
	public List<ReferenceCodes> codeRecordGroup() {
		return ocmfaproRepository.codeRecordGroup();
	}

	@Override
	public List<ReferenceCodes> reciptTypeRecordGroup(final String caseloadType) {
		List<ReferenceCodes> returnData =  ocmfaproRepository.reciptTypeRecordGroup(caseloadType);
		for (ReferenceCodes robj : returnData) {
			if (robj.getActiveFlag().equals("Y")) {
				robj.setCanDisplay(true);
			} else {
				robj.setCanDisplay(false);
			}
		}
		return returnData;
	}

	/**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(final CaseloadDeductionProfiles searchRecord)  {
		List<CaseloadDeductionProfiles> resultList = ocmfaproRepository.caseloadDedProfExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			final String nbtModifyUserId = ocmfaproRepository.calculateOn(data.getDeductionType());
			data.setNbtModifyUserId(nbtModifyUserId);
		});
		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 *
	 */
	@Transactional
	@Override
	public Integer csldDpCommit(CaseloadDeductionProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
//		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
//			liReturn = ocmfaproRepository.caseloadDedProfInsert(commitBean.getInsertList());
//		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocmfaproRepository.caseloadDedProfUpdate(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(obj -> {
				checkFeeActAssigned(obj);
				obj.setModifyUserId(commitBean.getCreateUserId());
			});

			liReturn = ocmfaproRepository.caseloadDedProfDelete(commitBean.getDeleteList());
		}

		return liReturn;
	}



	@Transactional
	private CaseloadDeductionProfilesCommitBean preInsert(final CaseloadDeductionProfilesCommitBean commitBean) {

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CaseloadDeductionProfiles bean : commitBean.getInsertList()) {
				List<CaseloadDeductionProfiles> insertList =new ArrayList<>();
				BigDecimal extPrt = BigDecimal.ONE;
				BigDecimal intPrt = BigDecimal.ONE;
				Map<String, BigDecimal> getNextExpInp = getNextExpInp(bean.getCaseloadId());
				if (getNextExpInp != null) {
					if (getNextExpInp.get("extPrt") != null) {
						extPrt = getNextExpInp.get("extPrt");
					}
					if (getNextExpInp.get("intPrt") != null) {
						intPrt = getNextExpInp.get("intPrt");
					}
				}
				bean.setFifoFlag("Y");
				bean.setPercentage(BigDecimal.ZERO);
				bean.setExternalPriorityNo(extPrt);
				bean.setInternalPriorityNo(intPrt);

				insertList.add(bean);

				ocmfaproRepository.caseloadDedProfInsert(insertList);

			}
		}

		return commitBean;

	}

	private Map<String, BigDecimal> getNextExpInp(final String caseloadId) {
		Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();
		BigDecimal lvTmpExt = BigDecimal.ONE;
		BigDecimal lvTmpInt = BigDecimal.ONE;
		lvTmpExt = ocmfaproRepository.getMaxExternalPriorityNo(caseloadId);
		if (lvTmpExt.compareTo(BigDecimal.valueOf(99)) > 0) {
			lvTmpExt = BigDecimal.ONE;
			boolean isLoop = true;
			while (isLoop) {
				lvTmpInt = ocmfaproRepository.getMaxInternalPriorityNo(caseloadId, lvTmpExt);

				if (lvTmpInt.compareTo(BigDecimal.valueOf(99)) > 0) {
					if (BigDecimal.valueOf(99).equals(lvTmpExt)) {
						throw new RuntimeException("otmfopro.nomreexpcomb");
					} else {
						lvTmpExt = lvTmpExt.add(BigDecimal.ONE);
					}
				} else {
					isLoop = false;
				}
			}
		}

		result.put("extPrt", lvTmpExt);
		result.put("intPrt", lvTmpInt);

		return result;
	}


	/**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<CaseloadDedBeneficiaries> caseloadDedbenficExecuteQuery(final CaseloadDedBeneficiaries searchRecord)  {
		List<CaseloadDedBeneficiaries> resultList = ocmfaproRepository.caseloadDedBenficExecuteQuery(searchRecord);
		resultList.forEach(data -> {
			if (data.getPersonId() != null) {
				Persons person = ocmfaproRepository.cgfkchkCsldDbenCsldDben(data.getPersonId().longValue());
				if (person != null) {
					data.setDspLastName(person.getLastName());
					data.setDspFirstName(person.getFirstName());
				}
			}

			if (data.getCorporateId() != null) {
				Corporates corporates = ocmfaproRepository.cgfkchkCsldDbenCsldDben(data.getCorporateId());
				if (corporates != null) {
					data.setDspCorporateName(corporates.getCorporateName());
				}
			}

		});

		return resultList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 *
	 */
	@Transactional
	@Override
	public Integer csldDbCommit(final CaseloadDedBeneficiariesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(CaseloadDedBeneficiaries obj : commitBean.getInsertList()) {
				Long cbenId = ocmfaproRepository.csldDbenPreInsert();
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setCaseloadDedBeneficiaryId(new BigDecimal(cbenId));
			}
			liReturn = ocmfaproRepository.caseloadDedBenficInsert(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(obj->{
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmfaproRepository.caseloadDedBenficiUpdate(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean != null && commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(obj->{
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmfaproRepository.caseloadDedBenficDelete(commitBean.getDeleteList());
		}

		return liReturn;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String singleCommit(OcmfaproCommitBean commitBean) {
		CaseloadDeductionProfilesCommitBean commitBeanCdp = commitBean.getCslddpCommitModel();
		commitBeanCdp = preInsert(commitBeanCdp);
		commitBean.getCslddbenCommitModel().setCreateUserId(commitBean.getCreateUserId());
		csldDbCommit(commitBean.getCslddbenCommitModel());
		if (!commitBeanCdp.getUpdateList().isEmpty() || !commitBeanCdp.getDeleteList().isEmpty()) {
			csldDpCommit(commitBeanCdp);
		}
	//	csldDbCommit(commitBean.getCslddbenCommitModel());
		if(commitBean.getCsldddCommitModel() != null) {
			commitBean.getCsldddCommitModel().setCreateUserId(commitBean.getCreateUserId());
		}
		csldDdCommit(commitBean.getCsldddCommitModel());

		// checking total equal or not
		try {
			commitBean.getCslddpCommitModel().getInsertList().forEach(data -> {
				BigDecimal maxAmount=ocmfaproRepository.getMaxAmount(data.getDeductionType(), data.getCaseloadId());
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(),maxAmount);
			});
			commitBean.getCslddpCommitModel().getUpdateList().forEach(data -> {
				BigDecimal maxAmount=ocmfaproRepository.getMaxAmount(data.getDeductionType(), data.getCaseloadId());
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(),maxAmount);
			});
			commitBean.getCslddpCommitModel().getDeleteList().forEach(data -> {
				BigDecimal maxAmount=ocmfaproRepository.getMaxAmount(data.getDeductionType(), data.getCaseloadId());
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(),maxAmount);
			});


			commitBean.getCslddbenCommitModel().getInsertList().forEach(data -> {
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(),data.getMaxTotalAmount());
			});
			commitBean.getCslddbenCommitModel().getUpdateList().forEach(data -> {
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(),data.getMaxTotalAmount());
			});
			commitBean.getCslddbenCommitModel().getDeleteList().forEach(data -> {
				checkMaxAmount(data.getCaseloadId(), data.getDeductionType(),data.getMaxTotalAmount());
			});
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		// updating percentage
		try {
			commitBean.getCslddbenCommitModel().getInsertList().forEach(data -> {
				percentage(data);
			});
			commitBean.getCslddbenCommitModel().getUpdateList().forEach(data -> {
				percentage(data);
			});
			commitBean.getCslddbenCommitModel().getDeleteList().forEach(data -> {
				percentage(data);
			});
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.othrerrinprce");
		}

		// post forms
		try {
			commitBean.getCslddpCommitModel().getInsertList().forEach(bean -> {
				postFormsCommit(bean);
			});
			commitBean.getCslddpCommitModel().getUpdateList().forEach(bean -> {
				postFormsCommit(bean);
			});
			commitBean.getCsldddCommitModel().getInsertList().forEach(bean -> {
				CaseloadDeductionProfiles cdp = new CaseloadDeductionProfiles();
				cdp.setCaseloadId(bean.getCaseloadId());
				cdp.setDeductionType(bean.getDeductionType());
				postFormsCommit(cdp);
			});
			commitBean.getCsldddCommitModel().getUpdateList().forEach(bean -> {
				CaseloadDeductionProfiles cdp = new CaseloadDeductionProfiles();
				cdp.setCaseloadId(bean.getCaseloadId());
				cdp.setDeductionType(bean.getDeductionType());
				postFormsCommit(cdp);
			});
		}catch (Exception e) {
			throw new RuntimeException("otmfopro.ublefnddedprofle");
		}

		
		List<SystemProfiles> sysProfList = ocmfaproRepository.sysPflExecuteQueryUpdateFeeAccounts();
		if(!sysProfList.isEmpty() && "Y".equals(sysProfList.get(0).getProfileValue())) {		
			commitBean.getCslddpCommitModel().getUpdateList().forEach(data -> {
				checkExistingOffenderAndUpdate(data);
			});
			
			// checking existing benficiary data in ocdoffacc
			if (!commitBean.getCslddbenCommitModel().getUpdateList().isEmpty()) {
				commitBean.getCslddbenCommitModel().getUpdateList().get(0).setModifyUserId(commitBean.getCreateUserId());
				checkExistingBenData(commitBean.getCslddbenCommitModel().getUpdateList().get(0));
			}
			
			// checking existing deduction details data in ocdoffacc
			if (!commitBean.getCsldddCommitModel().getUpdateList().isEmpty()) {
				commitBean.getCsldddCommitModel().getUpdateList().get(0).setModifyUserId(commitBean.getCreateUserId());
				checkExistingDedData(commitBean.getCsldddCommitModel().getUpdateList().get(0));
			}
		}


		return String.valueOf(1);
	}

	@Transactional
	private void checkExistingBenData(CaseloadDedBeneficiaries data) {
		CaseloadDeductionProfiles cdp= new CaseloadDeductionProfiles();
		cdp.setDeductionType(data.getDeductionType());
		List<FeeAccountProfiles> offList = ocmfaproRepository.getExistingOffenders(cdp);
		if (!offList.isEmpty()) {
			offList.forEach(obj -> {
				if(obj.getCaseloadId() != null && obj.getCaseloadId().equalsIgnoreCase(data.getCaseloadId())) {
				if (obj.getFeeActStatus().equals("A") || obj.getFeeActStatus().equals("P")) {

					if (obj.getFeeCode().equals(data.getDeductionType())) {

//						BigDecimal totAmount = ocmfaproRepository.getAmount(data.getCaseloadId(), data.getDeductionType());
//						obj.setAmount(totAmount);
//						Integer count = ocmfaproRepository.updateAmountOffenderFeeAct(obj);
							obj.setModifyUserId(data.getModifyUserId());
							Integer returnVal = ocmfaproRepository.deleteBenfData(obj);
							if (returnVal == 1) {
								List<CaseloadDedBeneficiaries> benList = ocmfaproRepository.caseloadDedBenficExecuteQuery(data);
								if (!benList.isEmpty()) {
									for (CaseloadDedBeneficiaries cdb : benList) {
										cdb.setOffenderFeeId(obj.getOffenderFeeId());
									}
									ocmfaproRepository.insertCsldBenData(benList);
								}
							}

					}

				}
				}
			});
		}

	}

	@Transactional
	private void checkExistingDedData(CaseloadDeductionDetails data) {
		CaseloadDeductionProfiles cdp= new CaseloadDeductionProfiles();
		cdp.setDeductionType(data.getDeductionType());
		List<FeeAccountProfiles> offList = ocmfaproRepository.getExistingOffenders(cdp);
		if (!offList.isEmpty()) {
			offList.forEach(obj -> {
				if(obj.getCaseloadId() != null && obj.getCaseloadId().equalsIgnoreCase(data.getCaseloadId())) {
				if (obj.getFeeActStatus().equals("A")  || obj.getFeeActStatus().equals("P")) {

					if (obj.getFeeCode().equals(data.getDeductionType())) {
						obj.setModifyUserId(data.getModifyUserId());
						Integer returnVal = ocmfaproRepository.deleteDedDetails(obj);
						if (returnVal == 1) {
							List<CaseloadDeductionDetails> dedDetails = ocmfaproRepository.caseloadDedDetExecuteQuery(data);
							if (!dedDetails.isEmpty()) {
								for (CaseloadDeductionDetails cdb : dedDetails) {
									cdb.setOffenderFeeId(obj.getOffenderFeeId());
									cdb.setReceiptPercent(cdb.getPercentage());
								}
								ocmfaproRepository.insertCsldDedDetails(dedDetails);
							}
						}
					}

				}
				}
			});

		}
	}

	@Transactional
	private void checkExistingOffenderAndUpdate(CaseloadDeductionProfiles data) {
		List<FeeAccountProfiles> offList = ocmfaproRepository.getExistingOffenders(data);
		List<FeeAccountProfiles> inputList = new ArrayList<>();
		if (!offList.isEmpty()) {
			offList.forEach(obj -> {
				if(obj.getCaseloadId() != null && obj.getCaseloadId().equalsIgnoreCase(data.getCaseloadId())) {
				if (obj.getFeeActStatus().equals("A") || obj.getFeeActStatus().equals("P") || obj.getFeeActStatus().equals("S")) {
					//obj.setFeeCode(data.getDeductionType());
					boolean isUpdate = false;
					if(obj.getAmount() != null && obj.getAmount().compareTo(BigDecimal.valueOf(data.getAmount())) != 0) {
						obj.setAmount(new BigDecimal(data.getAmount()));
						isUpdate = true;
					}
					
					if (data.getDayOfMonth() != null) {
						if(obj.getDayOfMonth() == null) {
							obj.setDayOfMonth(data.getDayOfMonth().intValue());
							isUpdate = true;
						}else if(data.getDayOfMonth().compareTo(obj.getDayOfMonth().longValue()) != 0) {
						obj.setDayOfMonth(data.getDayOfMonth().intValue());
						isUpdate = true;
						}
					}
					if(isUpdate) {
					inputList.add(obj);
					}
				}
				}

			});
			if(!inputList.isEmpty()){
				ocmfaproRepository.updateExistingOff(inputList);
			}
		}

	}

	private boolean checkFeeActAssigned(CaseloadDeductionProfiles obj) {
		try {
			List<FeeAccountProfiles> offList = ocmfaproRepository.getExistingOffenders(obj);
			if (!offList.isEmpty()) {
				throw new RuntimeException("ocfmafro.offAssigned");
			}
		} catch(Exception e){
			if ("ocfmafro.offAssigned".equalsIgnoreCase(e.getMessage())) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return true;
	}

	/**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<CaseloadDeductionDetails> caseloadDedDetExecuteQuery(final CaseloadDeductionDetails searchRecord)  {
		return ocmfaproRepository.caseloadDedDetExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 *
	 */
	@Transactional
	@Override
	public Integer csldDdCommit(final CaseloadDeductionDetailsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(obj -> {
				obj.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmfaproRepository.caseloadDedDetInsert(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(obj -> {
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = ocmfaproRepository.caseloadDedDetUpdate(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = ocmfaproRepository.caseloadDedDetDelete(commitBean.getDeleteList());
		}

		return liReturn;
	}

	@Override
	public Persons cgfkchkCsldDbenCsldDben(final Long personId) {
		Persons persons = ocmfaproRepository.cgfkchkCsldDbenCsldDben(personId);
		return persons;
	}

	@Override
	public Corporates cgfkchkCsldDbenCsldDben(final BigDecimal corporateId) {
		Corporates corporates = ocmfaproRepository.cgfkchkCsldDbenCsldDben(corporateId);
		return corporates;
	}

	public Boolean percentage(final CaseloadDedBeneficiaries bean) {
		BigDecimal myPercentage = BigDecimal.ZERO;
		try {
			List<CaseloadDedBeneficiaries> dedPriorities = ocmfaproRepository.dedPriorities(bean);
			if (dedPriorities != null) {
				for (CaseloadDedBeneficiaries data : dedPriorities) {
					if (BigDecimal.ZERO.equals(data.getAmount())) {
						myPercentage = BigDecimal.ZERO;
					} else {
						BigDecimal priorityAmt = getPriorityAmount(data.getCaseloadId(), data.getDeductionType(),
								data.getPriority(), data.getAmount());
						myPercentage = data.getAmount().multiply(BigDecimal.valueOf(100)).divide(priorityAmt, 2, RoundingMode.HALF_UP);
						if (myPercentage != null) {
							MathContext mcontext = new MathContext(2);
							myPercentage = myPercentage.round(mcontext);
						}
					}

					ocmfaproRepository.updateCaseloadDedBeneficiariesPercentage(myPercentage,
							data.getCaseloadDedBeneficiaryId(),bean.getCreateUserId());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.otrerrupdtingunltamt");
		}

		return true;
	}


	public BigDecimal getPriorityAmount(final String caseloadId, final String deductionType, final BigDecimal priority,
										final BigDecimal amount) {
		BigDecimal priorityAmount = BigDecimal.ZERO;
		try {
			priorityAmount = ocmfaproRepository.getPriorityAmount(caseloadId, deductionType, priority);
			if (priorityAmount.compareTo(BigDecimal.ZERO) > 0) {
				return priorityAmount;
			} else {
				return amount;
			}
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.errgtngpritorytot");
		}
	}

	public Boolean checkMaxAmount(final String caseloadId, final String deductionType, final BigDecimal maxTotalAmount ) {
		try {
			BigDecimal vBenCount = null;
			BigDecimal vBenTotal = null;
			BigDecimal vMaxTotal = maxTotalAmount;

			if (vMaxTotal == null) {
				vMaxTotal = BigDecimal.ZERO;
			}

			Map<String, Object> calcBenTotal = calcBenTotal(caseloadId, deductionType);
			if (calcBenTotal != null) {
				if (calcBenTotal.get("P_BEN_COUNT") != null) {
					vBenCount = new BigDecimal(calcBenTotal.get("P_BEN_COUNT").toString());
				}
				if (calcBenTotal.get("P_BEN_TOTAL") != null) {
					vBenTotal = new BigDecimal(calcBenTotal.get("P_BEN_TOTAL").toString());
				}
			}
			if (vBenCount.compareTo(BigDecimal.ZERO) > 0) {
				if (vMaxTotal.compareTo(vBenTotal) != 0) {
					throw new RuntimeException("otmfopro.benetotisnteqal");
				}
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

	public Boolean checkMaxAmountDp(final CaseloadDeductionProfiles bean) {
		try {
			BigDecimal vBenCount = null;
			BigDecimal vBenTotal = null;
			BigDecimal vMaxTotal = bean.getMaxTotalAmount();

			if (vMaxTotal == null) {
				vMaxTotal = BigDecimal.ZERO;
			}

			if (!"Y".equals(bean.getCoCreditWhenIndigentFlag())) {
				Map<String, Object> calcBenTotal = calcBenTotal(bean.getCaseloadId(), bean.getDeductionType());
				if (calcBenTotal != null) {
					if (calcBenTotal.get("P_BEN_COUNT") != null) {
						vBenCount = new BigDecimal(calcBenTotal.get("P_BEN_COUNT").toString());
					}
					if (calcBenTotal.get("P_BEN_TOTAL") != null) {
						vBenTotal = new BigDecimal(calcBenTotal.get("P_BEN_TOTAL").toString());
					}
				}
				if (vBenCount.compareTo(BigDecimal.ZERO) > 0) {
					if (vMaxTotal.compareTo(vBenTotal) != 0) {
						throw new RuntimeException("otmfopro.benetotisnteqal");
					}
				}

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

	public Map<String, Object> calcBenTotal(final String caseloadId, final String deductionType) {
		return ocmfaproRepository.calcBenTotal(caseloadId, deductionType);
	}

	private Integer postFormsCommit(final CaseloadDeductionProfiles bean) {
		String vCsldId = bean.getCaseloadId();
		try {
			BigDecimal receiptPercentage = null;
			String dedType = bean.getDeductionType();
			receiptPercentage = ocmfaproRepository.getReceiptPercentage(dedType, vCsldId);
			ocmfaproRepository.updateCaseloadDeductionProfilesPercentage(receiptPercentage, vCsldId, dedType,bean.getCreateUserId());
		} catch (Exception e) {
			throw new RuntimeException("otmfopro.ublefnddedprofle");
		}
		return 1;

	}

	@Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		return ocmfaproRepository.sysPflExecuteQuery();

	}

    @Override
    public String calculateOn(final String deductionType) {
        return ocmfaproRepository.calculateOn(deductionType);

    }

	@Override
	public List<SystemProfiles> sysPfl2ExecuteQuery() {
		return ocmfaproRepository.sysPfl2ExecuteQuery();
	}
}
