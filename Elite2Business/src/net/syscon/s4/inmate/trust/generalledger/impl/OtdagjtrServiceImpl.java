package net.syscon.s4.inmate.trust.generalledger.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.trust.generalledger.OtdagjtrRepository;
import net.syscon.s4.inmate.trust.generalledger.OtdagjtrService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.trust.TrustService;
import net.syscon.s4.pkgs.trust_gj.TrustGjService;
import net.syscon.s4.triggers.GlTransactionsT1Service;
import net.syscon.s4.triggers.GlTransactionsT2Service;
import net.syscon.s4.triggers.TrustAudits;

@Service
public class OtdagjtrServiceImpl extends BaseBusiness implements OtdagjtrService {

	@Autowired
	private OtdagjtrRepository otdagjtrRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	
	@Autowired
	private TrustService trustService;
	
	@Autowired
	private GlTransactionsT1Service glTransactionsT1Service;
	
	@Autowired
	private GlTransactionsT2Service glTransactionsT2Service;
	
	@Autowired
	private TrustGjService trustgjservice;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdagjtrServiceImpl.class.getName());

	/**
	 * Creates new OtdagjtrServiceImpl class Object
	 */
	public OtdagjtrServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Corporates CgfkchkGlTxnGlTxnCorpF8(Corporates paramBean) {
		Corporates corporates = otdagjtrRepository.cgfkchkGlTxnGlTxnCorpF8(paramBean);
		return corporates;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Persons CgfkchkGlTxnGlTxnPerF7(Persons paramBean) {
		Persons persons = otdagjtrRepository.cgfkchkGlTxnGlTxnPerF7(paramBean);
		return persons;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public TransactionTypes CgfkchkGlTxnGlTxnTxnTyp(TransactionTypes paramBean) {
		TransactionTypes transactionTypes = otdagjtrRepository.cgfkchkGlTxnGlTxnTxnTyp(paramBean);
		return transactionTypes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AccountCodes CgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean) {
		AccountCodes accountCodes = otdagjtrRepository.cgfkchkGlTxnGlTxnAcCode(paramBean);
		return accountCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AccountCodes CgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean) {
		AccountCodes accountCodesList = otdagjtrRepository.cgfkchkGlTxn1GlTxnAcCod(paramBean);
		return accountCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<GlTransactions> CgrichkGlTransactions(GlTransactions paramBean) {
		List<GlTransactions> glTransactions = otdagjtrRepository.cgrichkGlTransactions(paramBean);
		return glTransactions;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GlTransactions> glTxnExecuteQuery(GlTransactions searchRecord) {
		return otdagjtrRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer glTxnCommit(GlTransactionsCommitBean commitBean) {
		int liReturn = 0;

		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			List<GlTransactions> listGlTransactions=new ArrayList<GlTransactions>();
			for(GlTransactions gltransactions:commitBean.getInsertList()) {
				if(gltransactions.getAccountCode()!=null) {
					listGlTransactions.add(gltransactions);
				}
			}
			commitBean.setInsertList(listGlTransactions);
			List<GlTransactions> commitData = setAuditInsFlds(commitBean.getInsertList());
			if (!commitData.isEmpty()) {
				GlTransactions glTxnModule = commitData.get(0);
				for (GlTransactions obj : commitData) {
					obj.setCreateUserId(commitBean.getCreateUserId());
					TrustAudits newRef = new TrustAudits();
					try {
						BeanUtils.copyProperties(newRef, obj);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					glTransactionsT1Service.glTransactionsT1Trigger(newRef);
				}
				liReturn = otdagjtrRepository.glTxnInsertGlTransactions(commitData);
				for (GlTransactions obj : commitData) {
					Date transDate = trunc(eliteDateService.getDBTime());
					obj.setTxnEntryDate(transDate);
					glTransactionsT2Service.glTransactionsT2Trigger(obj);
				}
				if (liReturn == 1) {
					if ("Y".equals(glTxnModule.getNbtTxnType())) {
						glTxnModule.setTxnEntryDate(trunc(glTxnModule.getTxnEntryDate()));
						BankChequeData bankchequedata=new  BankChequeData();
						bankchequedata.setCaseloadId(glTxnModule.getCaseloadId());
						bankchequedata.setTxnId(glTxnModule.getTxnId());
						bankchequedata.setTxnEntryAmount(glTxnModule.getTxnEntryAmount());
						bankchequedata.setChequeFlag("G");
						bankchequedata.setStartTxnId(glTxnModule.getTxnEntryAmount());
						bankchequedata.setEndTxnId(glTxnModule.getTxnEntryAmount());
						bankchequedata.setPayeePersonId(glTxnModule.getPayeePersonId());
						bankchequedata.setPayeeCorporateId(glTxnModule.getPayeeCorporateId());
						bankchequedata.setPayeeNameText( glTxnModule.getPayeeNameText());
						bankchequedata.setOffenderId(null);
						bankchequedata.setSingleEntryFlag("1");
						bankchequedata.setBankAccountCode(glTxnModule.getAccountCode().longValue());
						bankchequedata.setModuleName("OTDAGJTR");
						bankchequedata.setTxnType("GJ");
						trustService.insertIntoChequeData(bankchequedata, glTxnModule.getCreateUserId());
					}
					trustgjservice.reopenClosedPeriod(glTxnModule.getCaseloadId(), glTxnModule.getTxnEntryDate(), glTxnModule.getCreateUserId());
					
					return Integer.valueOf(glTxnModule.getTxnId() + "");
				}

			}
		}
		return liReturn;
	}

	private List<GlTransactions> setAuditInsFlds(final List<GlTransactions> insertList) {
		final List<GlTransactions> returnData = new ArrayList<GlTransactions>();

		if (insertList != null && !insertList.isEmpty()) {
			final GlTransactions glTxnModel = insertList.get(0);
			Integer txnId = otdagjtrRepository.genTrustTrans("TXN_ID");
			final BigDecimal accountPeriodId = otdagjtrRepository.trustGjGetAccountPeriod(glTxnModel.getTxnEntryDate());
			for(GlTransactions data: insertList) {
				data.setTxnId(Long.valueOf(txnId + ""));
				data.setTxnEntryDate(trunc(glTxnModel.getTxnEntryDate()));
				data.setTxnEntryTime(eliteDateService.getDBTime());
				data.setCreateDate(trunc(eliteDateService.getDBTime()));
				data.setCreateUserId(glTxnModel.getCreateUserId());
				data.setTxnType(glTxnModel.getTxnType());
				data.setTxnEntrySeq(1l);
				data.setTxnId(glTxnModel.getTxnId());
				data.setCaseloadId(glTxnModel.getCaseloadId());
				data.setPayeePersonId(glTxnModel.getPayeePersonId());
				data.setPayeeCorporateId(glTxnModel.getPayeeCorporateId());
				data.setPayeeNameText(glTxnModel.getPayeeNameText());
				final Long glEntrySeq = Long.valueOf((insertList.indexOf(data) + 1) + "");
				data.setGlEntrySeq(glEntrySeq);
				data.setAccountPeriodId(accountPeriodId);
				if (glEntrySeq == 1) {
					if ("CR".equals(glTxnModel.getTxnPostUsage())) {
						data.setTxnPostUsage("CR");
					} else {
						data.setTxnPostUsage("DR");
					}
					returnData.add(data);
				} else if (glEntrySeq > 1) {
					if ("DR".equals(glTxnModel.getTxnPostUsage())) {
						data.setTxnPostUsage("CR");
					} else {
						data.setTxnPostUsage("DR");
					}
					returnData.add(data);
				}				
				else {
					data.setTxnPostUsage("DR");
					if (data.getTxnEntryAmount().compareTo(BigDecimal.ZERO) > 0) {
						returnData.add(data);
					}
				}

			}
		}
		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<GlTransactions> glTxn1ExecuteQuery(GlTransactions searchRecord) {
		return otdagjtrRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN1
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer glTxn1Commit(GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdagjtrRepository.glTxnInsertGlTransactions(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles searchRecord) {
		return otdagjtrRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otdagjtrRepository.sysPflInsertSystemProfiles(commitBean.getInsertList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otdagjtrRepository.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<TransactionTypes> cgfkGlTxnTxnTypeRecordGroup(final String caseloadId, final String caseloadType) {
		return otdagjtrRepository.cgfkGlTxnTxnTypeRecordGroup(caseloadId, caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Persons> cgfkGlTxnPayeePersonIdRecordGroup(final Integer personId) {
		return otdagjtrRepository.cgfkGlTxnPayeePersonIdRecordGroup(personId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup(final Integer corporateId) {
		return otdagjtrRepository.cgfkGlTxnPayeeCorporateIdRecordGroup(corporateId);

	}

	@Override
	public Date trustGLReopenClosedPeriod(final String caseloadId) {
		return otdagjtrRepository.trustGLReopenClosedPeriod(caseloadId);
	}

	@Override
	public Map<String, Object> onTxnEntryDateBlur(final String caseloadId, final Long txnDate) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (caseloadId != null && txnDate != null) {
			final Date txnEntryDate = new Date(txnDate);
			Calendar txnDateC = Calendar.getInstance();
			txnDateC.setTime(txnEntryDate);
			Calendar currentDate = Calendar.getInstance();
			currentDate.setTime(eliteDateService.getDBTime());
			if (txnDateC.compareTo(currentDate) > 0) {
				returnMap.put("error", true);
				returnMap.put("msg", "otdagjtr.futurepostingarenotallowed");
				return returnMap;
			}
			BigDecimal lvLastClosedPeriod = otdagjtrRepository.trustGjGetLastClosedPeriod(caseloadId);
			BigDecimal lvAllowedReopenPeriod = otdagjtrRepository.trustGjGetAllowedBackDatedPeriod(caseloadId);
			BigDecimal lvEnterAccountPeriod = otdagjtrRepository.trustGjGetAccountPeriod(txnEntryDate);
			if (lvEnterAccountPeriod == null) {
				lvEnterAccountPeriod = BigDecimal.ZERO;
			}
			BigDecimal periodDays = otdagjtrRepository.checkPeriodDays(caseloadId, lvEnterAccountPeriod);
			if (periodDays == null || periodDays.compareTo(BigDecimal.ZERO) == 0) {
				returnMap.put("error", true);
				returnMap.put("msg", "otdagjtr.theaccountperiodoftheenderddateisnotvalid");
				return returnMap;

			}
			BigDecimal currentAccountPeriod = otdagjtrRepository
					.trustGjGetAccountPeriod(trunc(eliteDateService.getDBTime()));
			if (currentAccountPeriod == null) {
				currentAccountPeriod = BigDecimal.ZERO;
			}
			if (lvEnterAccountPeriod == null) {
				lvEnterAccountPeriod = BigDecimal.ZERO;
			}

			if (currentAccountPeriod.compareTo(lvEnterAccountPeriod) < 0) {
				returnMap.put("error", true);
				returnMap.put("msg", "otdagjtr.thecurrentperiodislessthantransactiondate");
				return returnMap;
			}

			if (lvAllowedReopenPeriod != null) {
				if (lvEnterAccountPeriod.compareTo(lvAllowedReopenPeriod) < 0) {
					returnMap.put("error", true);
					returnMap.put("msg","otdagjtr.backdatecontactadmin");
					return returnMap;
				}
			}

			String lvAccountStatus = otdagjtrRepository.cAccountStatus(caseloadId, lvEnterAccountPeriod);

			if ("R".equals(lvAccountStatus) || "O".equals(lvAccountStatus)) {
				returnMap.put("error", false);
				return returnMap;
			} else {
				if (lvEnterAccountPeriod.compareTo(lvAllowedReopenPeriod) >= 0
						&& lvEnterAccountPeriod.compareTo(lvLastClosedPeriod) <= 0) {
					returnMap.put("error", true);
					Date startDate = otdagjtrRepository.trustGjGetPeriodStartDate(lvEnterAccountPeriod);
					Date endDate = otdagjtrRepository.trustGjGetPeriodEndDate(lvLastClosedPeriod);
					returnMap.put("codeOne", dateToText(startDate, "MM/yyyy"));
					returnMap.put("codeTwo", dateToText(endDate, "MM/yyyy"));
					returnMap.put("msg", "otdagjtr.tispstwloptprdfrm");
					return returnMap;
				}
			}

		} else {
			if (txnDate == null) {
				returnMap.put("error", true);
				returnMap.put("msg", "otdagjtr.pleaseenterthetransactiondate");
				return returnMap;
			}
		}
		returnMap.put("error", false);
		return returnMap;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			calender.set(Calendar.AM_PM, Calendar.AM);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	public String dateToText(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	@Override
	public Map<String, Object> onTxnTypeValueChange(String caseloadId, String caseloadType, String txnType,String userName) {
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, Object> prGetPrimaryAccount = prGetPrimaryAccount(caseloadId, caseloadType, txnType, userName);
		if (prGetPrimaryAccount != null) {
			if (prGetPrimaryAccount.get("error") != null) {
				return prGetPrimaryAccount;
			} else {
				Map<String, Object> prGjTransSetup = prGjTransSetup(caseloadId, caseloadType, txnType);
				if (prGjTransSetup != null) {
					if (prGjTransSetup.get("error") != null) {
						return prGjTransSetup;
					} else {
						returnMap.putAll(prGetPrimaryAccount);
						returnMap.putAll(prGjTransSetup);
					}
				}
			}
		}

		return returnMap;
	}

	private Map<String, Object> prGjTransSetup(String caseloadId, String caseloadType, String txnType) {
		Map<String, Object> returnMap = new HashMap<>();
		if (caseloadId != null && caseloadType != null && txnType != null) {
			String lvTxnType = txnType;
			String lvChequeProdFlag = "N";
			String lvChqpaytyp = "N";
			String lvCaseloadId = caseloadId;
			String lvCaseloadType = caseloadType;
			try {
				Map<String, Object> getProductionAndPayeeFlag = otdagjtrRepository
						.getProductionAndPayeeFlag(lvCaseloadId, lvCaseloadType, lvTxnType);
				if (getProductionAndPayeeFlag != null) {
					if (getProductionAndPayeeFlag.get("error") != null) {
						return getProductionAndPayeeFlag;
					}
					if (getProductionAndPayeeFlag.get("CHEQUE_PRODUCTION_FLAG") != null) {
						lvChequeProdFlag = getProductionAndPayeeFlag.get("CHEQUE_PRODUCTION_FLAG").toString();
						returnMap.put("lvChequeProdFlag", lvChequeProdFlag);
					}
					if (getProductionAndPayeeFlag.get("CHEQUE_PAYEE_TYPE") != null) {
						lvChqpaytyp = getProductionAndPayeeFlag.get("CHEQUE_PAYEE_TYPE").toString();
						returnMap.put("lvChqpaytyp", lvChqpaytyp);
						if ("T".equals(lvChqpaytyp)) {
							Map<String, Object> defaultCorpPerson = defaultCorpPerson(lvTxnType);
							if (defaultCorpPerson != null) {
								returnMap.putAll(defaultCorpPerson);
							}
						}
					}
				} else {
					returnMap.put("error", true);
					returnMap.put("msg", "otdagjtr.pleasechecksetup");
					return returnMap;
				}
			} catch (Exception e) {
				logger.error("OtdagjtrSerive prGjTransSetup Please check setup of Transaction Operation for OTDAGJTR.",
						e);
				returnMap.put("error", true);
				returnMap.put("msg", "otdagjtr.pleasechecksetup");
				return returnMap;
			}

		}
		return returnMap;
	}

	private Map<String, Object> defaultCorpPerson(String txnType) {
		Map<String, Object> returnMap = new HashMap<>();
		BigDecimal tempPerson = BigDecimal.ZERO;
		BigDecimal tempCorporate = BigDecimal.ZERO;
		Map<String, Object> defaultCorpPerson = otdagjtrRepository.defaultCorpPerson(txnType);
		if (defaultCorpPerson != null) {
			if (defaultCorpPerson.get("PAYEE_PERSON_ID") != null) {
				tempPerson = new BigDecimal(defaultCorpPerson.get("PAYEE_PERSON_ID").toString());
				if (!BigDecimal.ZERO.equals(tempPerson)) {
					String personName = otdagjtrRepository.getPersonName(tempPerson);
					if (personName != null) {
						returnMap.put("payeePersonId", tempPerson);
						returnMap.put("payeeNameText", personName);
					}
				}
			}
			if (defaultCorpPerson.get("PAYEE_CORPORATE_ID") != null) {
				tempCorporate = new BigDecimal(defaultCorpPerson.get("PAYEE_CORPORATE_ID").toString());
				if (!BigDecimal.ZERO.equals(tempCorporate)) {
					String corporateName = otdagjtrRepository.getCorporateName(tempCorporate);
					if (corporateName != null) {
						returnMap.put("payeeCorporateId", tempCorporate);
						returnMap.put("payeeNameText", corporateName);
					}
				}
			}
		}
		return returnMap;
	}

	private Map<String, Object> prGetPrimaryAccount(String caseloadId, String caseloadType, String txnType, String userName) {
		Map<String, Object> getTxnOp = otdagjtrRepository.getTxnOp(caseloadId, caseloadType, txnType);
		String tmpDr = null;
		String tmpCr = null;
		String tmpFlag = null;
		Map<String, Object> returnMap = new HashMap<>();
		try {
			if (getTxnOp != null) {
				if (getTxnOp.get("DR_ACCOUNT_CODE") != null) {
					tmpDr = getTxnOp.get("DR_ACCOUNT_CODE").toString();
				}
				if (getTxnOp.get("CR_ACCOUNT_CODE") != null) {
					tmpCr = getTxnOp.get("CR_ACCOUNT_CODE").toString();
				}
				if (getTxnOp.get("CHEQUE_PRODUCTION_FLAG") != null) {
					tmpFlag = getTxnOp.get("CHEQUE_PRODUCTION_FLAG").toString();
				}
				if ((tmpDr != null && tmpCr != null) || (tmpDr == null && tmpCr == null)) {
					returnMap.put("error", true);
					returnMap.put("msg", "otdagjtr.txnoperationtableerror");
					return returnMap;
				} else if (tmpDr != null) {
					returnMap.put("txnPostUsage", "DR");
					returnMap.put("nbtTxnType", tmpFlag);
					returnMap.put("accountCode", tmpDr);

				} else {
					returnMap.put("txnPostUsage", "CR");
					returnMap.put("nbtTxnType", tmpFlag);
					returnMap.put("accountCode", tmpCr);
				}
			} else {
				returnMap.put("error", true);
				returnMap.put("msg", "otdagjtr.txnoperationtableerror");
				return returnMap;
			}
			if (returnMap != null && returnMap.get("accountCode") != null) {
				BigDecimal accountCode = new BigDecimal(returnMap.get("accountCode").toString());
				Map<String, Object> prGetAccountDesc = prGetAccountDesc(accountCode, BigDecimal.ONE, caseloadId,
						caseloadType, userName);
				returnMap.putAll(prGetAccountDesc);
			}
		} catch (Exception e) {
			logger.error("OtdagjtrServiceImpl prGjTransSetup Pr_Get_Primary_Accounts: Internal ERROR.", e);
			returnMap.put("error", true);
			returnMap.put("msg", "otdagjtr.primaryaccountinternalerror");
			return returnMap;
		}

		return returnMap;
	}

	public Map<String, Object> prGetAccountDesc(BigDecimal acct, BigDecimal blk, String caseloadId,
			String caseloadType, String userId) {
		Map<String, Object> returnMap = new HashMap<>();
		if (acct != null && blk != null && caseloadId != null && caseloadType != null) {
			String tmpName = "";
			String tmpPostType = "";
			BigDecimal tmpBal = null;
			String tmpCaseId = caseloadId;
			String csldType = caseloadType;
			try {
				Map<String, Object> getAccNameAdTxnPstngTpe = otdagjtrRepository.getAccountTypeAndPostingType(acct,
						csldType);
				if (getAccNameAdTxnPstngTpe != null) {
					if (getAccNameAdTxnPstngTpe.get("ACCOUNT_NAME") != null) {
						tmpName = getAccNameAdTxnPstngTpe.get("ACCOUNT_NAME").toString();
					}
					if (getAccNameAdTxnPstngTpe.get("TXN_POSTING_TYPE") != null) {
						tmpPostType = getAccNameAdTxnPstngTpe.get("TXN_POSTING_TYPE").toString();
					}
				} else {
					returnMap.put("error", true);
					returnMap.put("codeOne", acct.toString());
					returnMap.put("msg", "otdagjtr.plzchksetup");
					return returnMap;
				}
			} catch (Exception e) {
				logger.error("Please check setup of chart of accounts for account " + acct.toString() + " ", e);
				returnMap.put("error", true);
				returnMap.put("codeOne", acct.toString());
				returnMap.put("msg", "otdagjtr.plzchksetup");
				return returnMap;
			}
			try {
				tmpBal = otdagjtrRepository.getCurrentBalance(acct, tmpCaseId, userId);
				if (tmpBal != null) {
					if (!blk.equals(BigDecimal.ONE) && !blk.equals(BigDecimal.valueOf(2))) {
						returnMap.put("error", true);
						returnMap.put("codeOne", acct.toString());
						returnMap.put("msg", "otdagjtr.plzchksetup");
						return returnMap;
					} else if (blk.equals(BigDecimal.valueOf(2))) {
						returnMap.put("dspAccountName", tmpName);
						returnMap.put("dspTxnPostingType", tmpPostType);

						returnMap.put("nbtBalance", tmpBal);
					} else {
						returnMap.put("dspAccountName", tmpName);
						returnMap.put("dspTxnPostingType", tmpPostType);

						returnMap.put("nbtBalance", tmpBal);
					}
				} else {
					returnMap.put("error", true);
					returnMap.put("codeOne", acct.toString());
					returnMap.put("msg", "otdagjtr.plzchksetup");
					return returnMap;
				}
			} catch (Exception e) {
				logger.error("Please check setup of chart of accounts for account " + acct.toString() + " ", e);
				returnMap.put("error", true);
				returnMap.put("codeOne", acct.toString());
				returnMap.put("msg", "otdagjtr.plzchksetup");
				return returnMap;
			}
		}
		return returnMap;
	}

	@Override
	public List<GlTransactions> prGetOffsetAccounts(final GlTransactions paramBean) {
		List<GlTransactions> prGetOffsetAccountsData = otdagjtrRepository.prGetOffsetAccounts(paramBean);
		GlTransactions errorBean = new GlTransactions();
		Map<String, Object> onTxnEntryDateBlur = onTxnEntryDateBlur(paramBean.getCaseloadId(),
				paramBean.getTxnEntryDate().getTime());
		if (onTxnEntryDateBlur != null && onTxnEntryDateBlur.get("error") != null) {
			if ((boolean) onTxnEntryDateBlur.get("error")) {
				try {
					
					if (onTxnEntryDateBlur.get("msg") != null) {
						errorBean.setErrorMessage(onTxnEntryDateBlur.get("msg").toString());
						prGetOffsetAccountsData.clear();
						prGetOffsetAccountsData.add(errorBean);
					}
				}catch (Exception e) {
					logger.error("e");
					return prGetOffsetAccountsData;
				}
			}
		}
		for (GlTransactions data : prGetOffsetAccountsData) {
			if ("DR".equals(paramBean.getTxnPostUsage())) {
				data.setAccountCode(data.getCrAccountCode());
			} else {
				data.setAccountCode(data.getDrAccountCode());
			}
			Map<String, Object> prGetAccountDesc = prGetAccountDesc(data.getAccountCode(), BigDecimal.valueOf(2),
					paramBean.getCaseloadId(), paramBean.getCaseloadType(),paramBean.getCreateUserId());
			if (prGetAccountDesc != null) {
				if (prGetAccountDesc.get("error") != null) {
					if ((boolean) onTxnEntryDateBlur.get("error")) {
						if (onTxnEntryDateBlur.get("msg") != null) {
							errorBean.setErrorMessage(onTxnEntryDateBlur.get("msg").toString());
							prGetOffsetAccountsData.clear();
							prGetOffsetAccountsData.add(errorBean);
							return prGetOffsetAccountsData;
						}
					}
				}
				if (prGetAccountDesc.get("dspTxnPostingType") != null) {
					data.setDspTxnPostingType(prGetAccountDesc.get("dspTxnPostingType").toString());
				}
				if (prGetAccountDesc.get("nbtBalance") != null) {
					data.setNbtBalance(
							BigDecimal.valueOf(Double.valueOf(prGetAccountDesc.get("nbtBalance").toString())));
					if (data.getNbtBalance() != null) {
						if (data.getNbtBalance().compareTo(BigDecimal.ZERO) < 0) {
							data.setNbtBalanceDisplay(
									"<" + data.getNbtBalance().abs().setScale(2, BigDecimal.ROUND_HALF_UP) + ">");
						} else {
							data.setNbtBalanceDisplay(
									data.getNbtBalance().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						}
					}
				}
				if (prGetAccountDesc.get("dspAccountName") != null) {
					data.setDspAccountName(prGetAccountDesc.get("dspAccountName").toString());
				}
				if (paramBean.getTxnEntryDesc() != null && !paramBean.getTxnEntryDesc().trim().isEmpty()) {
					data.setTxnEntryDesc(paramBean.getTxnEntryDesc());
				}
				data.setTxnEntryAmount(BigDecimal.ZERO);
			}
		}
		return prGetOffsetAccountsData;
	}
}
