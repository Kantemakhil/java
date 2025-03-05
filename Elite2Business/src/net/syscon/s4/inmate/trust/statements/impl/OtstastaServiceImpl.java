package net.syscon.s4.inmate.trust.statements.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SubAccountBalancesBean;
import net.syscon.s4.im.beans.TransStatementBean;
import net.syscon.s4.inmate.beans.AccountCodesCommitBean;
import net.syscon.s4.inmate.beans.Printers;
import net.syscon.s4.inmate.trust.statements.OtstastaRepository;
import net.syscon.s4.inmate.trust.statements.OtstastaService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT1Service;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT3Service;
import net.syscon.s4.triggers.OffenderBookingsT4Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderBookingsT8Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/**
 * Class OtstastaServiceImpl
 */
@Service
public class OtstastaServiceImpl extends BaseBusiness implements OtstastaService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtstastaServiceImpl.class.getName());

	@Autowired
	private OtstastaRepository otstastaRepository;
	
	@Autowired
	private OffenderBookingsT1Service offenderBookingsT1Service;
	
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	
	@Autowired
	private OffenderBookingsT4Service offenderBookingsT4Service;
	
	@Autowired
	private OffenderBookingsT3Service offenderBookingsT3Service;
	
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	
	@Autowired
	private OffenderBookingsT8Service offenderBookingsT8Service;
	
	/**
	 * Creates new OtstastaServiceImpl class Object
	 */
	public OtstastaServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Printers CgfkchkOmsReqOmsReqPrint(final Printers paramBean) {
		Printers printers = otstastaRepository.cgfkchkOmsReqOmsReqPrint(paramBean);
		return printers;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> CgfkchkAcCodeAcSubAcct(final ReferenceCodes paramBean) {
		List<ReferenceCodes> referenceCodesList = null;
		return referenceCodesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AccountCodes> acCodeExecuteQuery(final AccountCodes searchRecord) {
		return otstastaRepository.acCodeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAC_CODE
	 *
	 * 
	 */
	@Transactional
	public Integer acCodeCommit(final AccountCodesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(AccountCodes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = otstastaRepository.acCodeInsertAccountCodes(commitBean.getInsertList());
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
	public List<OffenderBookings> offBkg1ExecuteQuery(final OffenderBookings searchRecord) {
		return otstastaRepository.offBkg1ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKG1
	 *
	 * 
	 */
	@Transactional
	public Integer offBkg1Commit(final OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		final String operation = null;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderBookings obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				offenderBookingsT1Service.offenderBookingsT1(obj);
			}
			liReturn = otstastaRepository.offBkg1InsertOffenderBookings(commitBean.getInsertList());
			for(OffenderBookings obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			offenderBookingsT3Service.offenderBookingsT3(obj);
			offenderBookingsT4Service.offenderBookingsT4(obj.getOffenderBookId(), obj.getCreateUserId());
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(obj, obj, operation);
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(obj,ApplicationConstants.INSERTING);
			offenderBookingsT7Service.offenderBookingsT7Trigger(obj);
			offenderBookingsT8Service.offenderBookingsT8(obj.getRootOffenderId().longValue(),commitBean.getCreateUserId());
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderBookings obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				offenderBookingsT2Service.offenderBookingsT2(obj, obj);
			}
			liReturn = otstastaRepository.offBkg1UpdateOffenderBookings(commitBean.getUpdateList());
			for(OffenderBookings obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(obj, obj, operation);
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(obj,ApplicationConstants.UPDATING);
			offenderBookingsT7Service.offenderBookingsT7Trigger(obj);
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
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return otstastaRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * 
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		return otstastaRepository.cgfkOmsReqPrinterIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup() {
		return otstastaRepository.cgfkAcCodeSubAccountTypeRecordGroup();

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReportBean mainProcess(final TransStatementBean paramBean) {
		byte[] returnReport = null;
		final ReportBean rBean = new ReportBean();
		List<TransStatementBean> fields = new ArrayList<TransStatementBean>();
		fields = otstastaRepository.mainHeaderQuery(paramBean);
		if (fields.size() > 0) {
			fields.forEach(transStatementBean -> {
				transStatementBean.setfDateOne(paramBean.getfDateOne());
				transStatementBean.setfUserOne(paramBean.getfUserOne());
				transStatementBean.setSubAccountType(paramBean.getSubAccountType());
				transStatementBean.setOffenderId(paramBean.getOffenderId());
				String subAccountType = paramBean.getSubAccountType();
				transStatementBean.setDisclosureFlag(paramBean.getDisclosureFlag());
				if (paramBean.getBeginDate() != null && paramBean.getEndDate() != null) {
					transStatementBean.setBeginDate(paramBean.getBeginDate());
					transStatementBean.setEndDate(paramBean.getEndDate());
				}
				if (transStatementBean.getCaseloadId() != null) {
					String CaseloadType = otstastaRepository.caseloadTypeData(transStatementBean.getCaseloadId());
					if (CaseloadType != null) {
						transStatementBean.setCaseloadType(CaseloadType);
					} else {
						transStatementBean.setCaseloadType("INST");
					}
				}
				if ("INST".equals(transStatementBean.getCaseloadType())) {
					String headerData = "REP_HDR_INST";
					String headerBlockData = otstastaRepository.headerBlockDataQuery(headerData);
					if (headerBlockData != null) {
						transStatementBean.setfReportHeaderLabelName(headerBlockData);
					} else {
						transStatementBean.setfReportHeaderLabelName("DEPARTMENT OF CORRECTIONS");
					}
				} else {
					String headerData = "REP_HDR_COMM";
					String headerBlockData = otstastaRepository.headerBlockDataQuery(headerData);
					if (headerBlockData != null) {
						transStatementBean.setfReportHeaderLabelName(headerBlockData);
					} else {
						transStatementBean.setfReportHeaderLabelName("DEPARTMENT OF CORRECTIONS");
					}
				}
				String caceloadNameOne = otstastaRepository.caceloadNameOneQuery(transStatementBean.getCaseloadId());
				if (caceloadNameOne != null) {
					transStatementBean.setfCaseloadNameOne(caceloadNameOne);
				}
				String facilityData = otstastaRepository.fFacilityData();
				if (facilityData != null) {
					transStatementBean.setfLoc(facilityData + ":");
				} else {
					transStatementBean.setfLoc("Facility" + ":");
				}
				String fDob = otstastaRepository.fDobData();
				if (fDob != null) {
					transStatementBean.setfDob(fDob + ":");
				} else {
					transStatementBean.setfDob("DOB#" + ":");
				}
				String profileId = otstastaRepository.profileValueId();
				if (profileId != null) {
					transStatementBean.setfDoc(profileId + ":");
				} else {
					transStatementBean.setfDoc("DOC#");
				}
				BigDecimal balance = BigDecimal.ZERO;
				BigDecimal bal = BigDecimal.ZERO;
				BigDecimal balOne = BigDecimal.ZERO;
				List<SubAccountBalancesBean> subAccntBal = otstastaRepository.subAccountBalanceData(transStatementBean);
				if (subAccntBal != null && subAccntBal.size() > 0) {
					for (SubAccountBalancesBean data : subAccntBal) {
						if (data.getfAccountName() != null) {
							String accountCodeData = otstastaRepository.accountCodesRegSavData(
									transStatementBean.getCaseloadType(), data.getfAccountName());
							if (accountCodeData != null) {
								if ("REG".equals(data.getfAccountName())) {
									data.setfAccountName(accountCodeData);
									balance = otstastaRepository.gettingBalance(data.getfOpeningBalance(),
											data.getfTxnEntryAmount());
									if (balance == null) {
										balance = BigDecimal.ZERO;
									}
								} else if ("SAV".equals(data.getfAccountName())) {
									data.setfAccountName(accountCodeData);
								}
							}
						}
						if (data.getfOpeningBalance() != null && data.getfTxnEntryAmount() != null) {
							final BigDecimal balanceData = otstastaRepository.gettingBalance(data.getfOpeningBalance(),
									data.getfTxnEntryAmount());
							if (balanceData != null) {
								data.setfBalance(balanceData);
							} else {
								data.setfBalance(BigDecimal.ZERO);
							}
						}
					}
					transStatementBean.setSubAccountBalancesBean(subAccntBal);
				}
				List<SubAccountBalancesBean> debsObigationsData = otstastaRepository
						.debsObligationsQueryDataBase(transStatementBean);
				if (debsObigationsData != null && debsObigationsData.size() > 0) {
					debsObigationsData.forEach(ele -> {
						Double amount = null;
						Double writeOff = null;
						if (null == ele.getMaxMonthlyAmount() && null == ele.getMaxTotalAmount()) {
							ele.setfAmountOne("UNLIMITED");
						} else {
							if (ele.getfAmount() != null) {
								amount = Double.valueOf(ele.getfAmount().toString());
							} else {
								amount = 0.0;
							}
							if (ele.getfWriteOff() != null) {
								writeOff = Double.valueOf(ele.getfWriteOff().toString());
							} else {
								writeOff = 0.0;
							}
							Double finalAmount = amount - writeOff;
							ele.setfAmountOne(finalAmount.toString());
						}
					});
					transStatementBean.setDebsObligationsBean(debsObigationsData);
				}
				List<SubAccountBalancesBean> subAccountTransaction = new ArrayList<>();
				if ("REG".equals(subAccountType) || "SAV".equals(subAccountType)) {
					subAccountTransaction = otstastaRepository.subAccountTransactionQueryDataBase(transStatementBean);
				} else {
					subAccountTransaction = otstastaRepository
							.subAccountTransactionQueryDataBaseWithoutSubAccType(transStatementBean);
				}
				try {
					final String booklabel = otstastaRepository.bookLabelData();
					if (subAccountTransaction != null && !subAccountTransaction.isEmpty()) {
						final List<SubAccountBalancesBean> reg = new ArrayList<>();
						final List<SubAccountBalancesBean> sav = new ArrayList<>();
						String det = "true";
						for (final SubAccountBalancesBean data : subAccountTransaction) {
							if (data.getOffenderBookId() != null) {
								final String bookingNumber = otstastaRepository
										.bookingNumberData(data.getOffenderBookId());
								data.setfBookNumberOne(bookingNumber);
							}
							data.setfBookLabel(booklabel);
							if (data.getfTxnEntryAmount() != null) {
								if ("true".equals(det)) {
									bal = balance.add(data.getfTxnEntryAmount());
									data.setfBalance(bal);
									det = "false";
								} else {
									if ("REG".equals(data.getSubAccountType())) {
										bal = bal.add(data.getfTxnEntryAmount());
										data.setfBalance(bal);
									} else if ("SAV".equals(data.getSubAccountType())) {
										balOne = balOne.add(data.getfTxnEntryAmount());
										data.setfBalance(balOne);
									}
								}
								balance = null;
							}
							if ("REG".equals(data.getSubAccountType())) {
								reg.add(data);
							} else if ("SAV".equals(data.getSubAccountType())) {
								sav.add(data);
							}
						}
						transStatementBean.setSubAccountTransactionsBean(subAccountTransaction);
						transStatementBean.setRegAccountTransactionsBean(reg);
						transStatementBean.setSavAccountTransactionsBean(sav);
					}
				} catch (Exception e) {
					logger.error("SuB Account Fail", e);
					throw new RuntimeException("SuB Account Fail : " + e.getMessage());
				}
			});
		}
		Map<String, Object> param = new HashMap<>();
		String dollar = otstastaRepository.dollarSymbolQuery();
		if (dollar != null) {
			param.put("cfCurr", dollar);
		} else {
			param.put("cfCurr", null);
		}
		param.put("discloser", paramBean.getDisclosureFlag());
		param.put("subAcc", paramBean.getSubAccountType());
		returnReport = generateReport("OTRTASTA", param, fields);
		rBean.setReport(returnReport);
		return rBean;
	}

	List<TransStatementBean> fields(final TransStatementBean paramBean) {
		List<TransStatementBean> fields = new ArrayList<TransStatementBean>();
		fields = otstastaRepository.mainHeaderQuery(paramBean);
		if (fields.size() > 0) {
			fields.forEach(transStatementBean -> {
				transStatementBean.setfDateOne(paramBean.getfDateOne());
				transStatementBean.setfUserOne(paramBean.getfUserOne());
				transStatementBean.setSubAccountType(paramBean.getSubAccountType());
				String subAccountType = paramBean.getSubAccountType();
				transStatementBean.setOffenderId(paramBean.getOffenderId());
				transStatementBean.setDisclosureFlag(paramBean.getDisclosureFlag());
				if (paramBean.getBeginDate() != null && paramBean.getEndDate() != null) {
					transStatementBean.setBeginDate(paramBean.getBeginDate());
					transStatementBean.setEndDate(paramBean.getEndDate());
				}
				if (transStatementBean.getCaseloadId() != null) {
					String CaseloadType = otstastaRepository.caseloadTypeData(transStatementBean.getCaseloadId());
					if (CaseloadType != null) {
						transStatementBean.setCaseloadType(CaseloadType);
					} else {
						transStatementBean.setCaseloadType("INST");
					}
				}
				if ("INST".equals(transStatementBean.getCaseloadType())) {
					String headerData = "REP_HDR_INST";
					String headerBlockData = otstastaRepository.headerBlockDataQuery(headerData);
					if (headerBlockData != null) {
						transStatementBean.setfReportHeaderLabelName(headerBlockData);
					} else {
						transStatementBean.setfReportHeaderLabelName("DEPARTMENT OF CORRECTIONS");
					}
				} else {
					String headerData = "REP_HDR_COMM";
					String headerBlockData = otstastaRepository.headerBlockDataQuery(headerData);
					if (headerBlockData != null) {
						transStatementBean.setfReportHeaderLabelName(headerBlockData);
					} else {
						transStatementBean.setfReportHeaderLabelName("DEPARTMENT OF CORRECTIONS");
					}
				}
				String caceloadNameOne = otstastaRepository.caceloadNameOneQuery(transStatementBean.getCaseloadId());
				if (caceloadNameOne != null) {
					transStatementBean.setfCaseloadNameOne(caceloadNameOne);
				}
				String facilityData = otstastaRepository.fFacilityData();
				if (facilityData != null) {
					transStatementBean.setfLoc(facilityData + ":");
				} else {
					transStatementBean.setfLoc("Facility" + ":");
				}
				String fDob = otstastaRepository.fDobData();
				if (fDob != null) {
					transStatementBean.setfDob(fDob + ":");
				} else {
					transStatementBean.setfDob("DOB#" + ":");
				}
				String profileId = otstastaRepository.profileValueId();
				if (profileId != null) {
					transStatementBean.setfDoc(profileId + ":");
				} else {
					transStatementBean.setfDoc("DOC#");
				}
				BigDecimal balance = BigDecimal.ZERO;
				BigDecimal bal = BigDecimal.ZERO;
				BigDecimal balOne = BigDecimal.ZERO;
				List<SubAccountBalancesBean> subAccntBal = otstastaRepository.subAccountBalanceData(transStatementBean);
				if (subAccntBal != null && subAccntBal.size() > 0) {
					for (SubAccountBalancesBean data : subAccntBal) {
						if (data.getfAccountName() != null) {
							String accountCodeData = otstastaRepository.accountCodesRegSavData(
									transStatementBean.getCaseloadType(), data.getfAccountName());
							if (accountCodeData != null) {
								if ("REG".equals(data.getfAccountName())) {
									data.setfAccountName(accountCodeData);
									balance = otstastaRepository.gettingBalance(data.getfOpeningBalance(),
											data.getfTxnEntryAmount());
									if (balance == null) {
										balance = BigDecimal.ZERO;
									}
								} else if ("SAV".equals(data.getfAccountName())) {
									data.setfAccountName(accountCodeData);
								}
							}
						}
						if (data.getfOpeningBalance() != null && data.getfTxnEntryAmount() != null) {
							final BigDecimal balanceData = otstastaRepository.gettingBalance(data.getfOpeningBalance(),
									data.getfTxnEntryAmount());
							if (balanceData != null) {
								data.setfBalance(balanceData);
							} else {
								data.setfBalance(BigDecimal.ZERO);
							}
						}
					}
					transStatementBean.setSubAccountBalancesBean(subAccntBal);
				}
				List<SubAccountBalancesBean> debsObigationsData = otstastaRepository
						.debsObligationsQueryDataBase(transStatementBean);
				if (debsObigationsData != null && debsObigationsData.size() > 0) {
					debsObigationsData.forEach(ele -> {
						Double amount = null;
						Double writeOff = null;
						if (null == ele.getMaxMonthlyAmount() && null == ele.getMaxTotalAmount()) {
							ele.setfAmountOne("UNLIMITED");
						} else {
							if (ele.getfAmount() != null) {
								amount = Double.valueOf(ele.getfAmount().toString());
							} else {
								amount = 0.0;
							}
							if (ele.getfWriteOff() != null) {
								writeOff = Double.valueOf(ele.getfWriteOff().toString());
							} else {
								writeOff = 0.0;
							}
							Double finalAmount = amount - writeOff;
							ele.setfAmountOne(finalAmount.toString());
						}
					});
					transStatementBean.setDebsObligationsBean(debsObigationsData);
				}
				List<SubAccountBalancesBean> subAccountTransaction = new ArrayList<>();
				if ("REG".equals(subAccountType) || "SAV".equals(subAccountType)) {
					subAccountTransaction = otstastaRepository.subAccountTransactionQueryDataBase(transStatementBean);
				} else {
					subAccountTransaction = otstastaRepository
							.subAccountTransactionQueryDataBaseWithoutSubAccType(transStatementBean);
				}
				try {
					final String booklabel = otstastaRepository.bookLabelData();
					if (subAccountTransaction != null && !subAccountTransaction.isEmpty()) {
						final List<SubAccountBalancesBean> reg = new ArrayList<>();
						final List<SubAccountBalancesBean> sav = new ArrayList<>();
						String det = "true";
						for (final SubAccountBalancesBean data : subAccountTransaction) {
							if (data.getOffenderBookId() != null) {
								final String bookingNumber = otstastaRepository
										.bookingNumberData(data.getOffenderBookId());
								data.setfBookNumberOne(bookingNumber);
							}
							data.setfBookLabel(booklabel);
							if (data.getfTxnEntryAmount() != null) {
								if ("true".equals(det)) {
									bal = balance.add(data.getfTxnEntryAmount());
									data.setfBalance(bal);
									det = "false";
								} else {
									if ("REG".equals(data.getSubAccountType())) {
										bal = bal.add(data.getfTxnEntryAmount());
										data.setfBalance(bal);
									} else if ("SAV".equals(data.getSubAccountType())) {
										balOne = balOne.add(data.getfTxnEntryAmount());
										data.setfBalance(balOne);
									}
								}
								balance = null;

							}
							if ("REG".equals(data.getSubAccountType())) {
								reg.add(data);
							} else if ("SAV".equals(data.getSubAccountType())) {
								sav.add(data);
							}
						}
						transStatementBean.setSubAccountTransactionsBean(subAccountTransaction);
						transStatementBean.setRegAccountTransactionsBean(reg);
						transStatementBean.setSavAccountTransactionsBean(sav);
					}
				} catch (Exception e) {
					logger.error("SuB Account Fail", e);
					throw new RuntimeException("SuB Account Fail : " + e.getMessage());
				}
			});
		}
		return fields;
	}

	public ReportBean superFilese(final List<TransStatementBean> paramBean) {
		final List<TransStatementBean> fields = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		if (paramBean != null && !paramBean.isEmpty()) {
			paramBean.forEach(data -> {
				final List<TransStatementBean> field = fields(data);
				if (field != null && field.size() > 0) {
					fields.addAll(field);
				}
				param.put("discloser", data.getDisclosureFlag());
				param.put("subAcc", data.getSubAccountType());
			});
		}
		String dollar = otstastaRepository.dollarSymbolQuery();
		if (dollar != null) {
			param.put("cfCurr", dollar);
		} else {
			param.put("cfCurr", null);
		}
		byte[] returnReport = generateReport("OTRTASTA", param, fields);
		ReportBean rBean = new ReportBean();
		rBean.setReport(returnReport);
		return rBean;
	}

	public byte[] generateReport(final String reportName, final Map<String, Object> parameteres, List<?> fields) {
		byte[] returnReport = null;
		JasperPrint jasperPrint = null;
		try {
			final InputStream reportInStream = this.getClass().getClassLoader()
					.getResourceAsStream("resource/jasperreports/" + reportName + ".jrxml");
			final JasperReport jasperReport = JasperCompileManager.compileReport(reportInStream);
			if ((fields != null && !fields.isEmpty())) {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres,
						new JRBeanCollectionDataSource(fields));
			} else {
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameteres, new JREmptyDataSource());
			}
			returnReport = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			logger.error("Exception in generateReport : ", e);
			throw new RuntimeException("Exception in generateReport : " + e.getMessage());
		}
		return returnReport;
	}

}