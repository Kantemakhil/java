package net.syscon.s4.inmate.trust.financialreports.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.syscon.s4.cf.deductions.OcutrdetService;
import net.syscon.s4.cf.deductions.beans.FeeAccountBalanceBean;
import net.syscon.s4.cf.deductions.beans.OcrorrecReportsBean;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.cf.offendertransactions.OcdreceiRepository;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumaglocRepository;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModuleParameters;
import net.syscon.s4.im.beans.OtrbalanReportBean;
import net.syscon.s4.im.beans.OtrbnrcnBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.inmate.trust.financialreports.OsureporRepository;
import net.syscon.s4.inmate.trust.financialreports.OsureporService;
import net.syscon.s4.inmate.trust.statements.OtstastaRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreRepository;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;

/**
 * Class OsureporServiceImpl
 */
@Service
public class OsureporServiceImpl extends BaseBusiness implements OsureporService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OsureporServiceImpl.class.getName());

	@Autowired
	private OsureporRepository osureporRepository;

	@Autowired
	private OtstastaRepository otstastaRepository;
	
	@Autowired
	private OumaglocRepository oumaglocRepo;
	
	@Autowired
	private OcdaddreRepository ocdaddreDao;
	
	@Autowired
	private OcdiplanRepository ocdiplanRepository;
	
	@Autowired
    private OcutrdetService ocutrdetService;
	
	@Autowired
	private OcdreceiRepository ocdreceiRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OmsModuleParameters> populateRecords(final OmsModuleParameters paramBean) {
		final List<OmsModuleParameters> returnList = osureporRepository.populateRecords(paramBean);
		for (final OmsModuleParameters omsModuleParameters : returnList) {
			if ("OFFID".equals(omsModuleParameters.getCommentText())
					|| "Offid".equals(omsModuleParameters.getCommentText())) {
				final String commentText = osureporRepository.profileValueData();
				if (commentText != null) {
					omsModuleParameters.setCommentText(commentText);
				}
			} else if ("FROM_OFFID".equals(omsModuleParameters.getCommentText())
					|| "From_Offid".equals(omsModuleParameters.getCommentText())) {
				final String commentText = osureporRepository.profileValueDataOne();
				if (commentText != null) {
					omsModuleParameters.setCommentText(commentText);
				}
			} else if ("TO_OFFID".equals(omsModuleParameters.getCommentText())
					|| "To_Offid".equals(omsModuleParameters.getCommentText())) {
				final String commentText = osureporRepository.profileValueDataTwo();
				if (commentText != null) {
					omsModuleParameters.setCommentText(commentText);
				}
			}
			final String pReportApplnCode = osureporRepository.reportApplnCode(paramBean.getModuleName());
			if (pReportApplnCode != null) {
				omsModuleParameters.setReportApplnCode(pReportApplnCode);
			}
		}
		return returnList;
	}

	public List<Dual> rg1cRecordGroup() {
		return osureporRepository.rg1cRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Dual> rg2cRecordGroup() {
		return osureporRepository.rg2cRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Dual> rg3cRecordGroup() {
		return osureporRepository.rg3cRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Dual> rg4cRecordGroup() {
		return osureporRepository.rg4cRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Dual> rg5cRecordGroup() {
		return osureporRepository.rg5cRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<Dual> rg5c1RecordGroup() {
		return osureporRepository.rg5c1RecordGroup();
	}

	@Override
	public List<ReferenceCodes> getDynamicLov(final String qry, final String caseload, final BigDecimal accCode) {
		final List<ReferenceCodes> resultList = osureporRepository.getDynamicLov(qry, caseload, accCode);
		resultList.forEach(data -> {
			if (BigDecimal.valueOf(120).equals(accCode)) {
				data.setDomain(data.getDescription());
				data.setDescription(data.getCode());
			}
			data.setCode(data.getDescription());
		});
		return resultList;
	}
	
	public  Date parseDate(final String strDate) {
		Date date = null;
		try {			
			date= new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
		} catch (final Exception e) {
			log.error("parseDate",e);
		}
		return date;
	}

	public List<AccountCodes> getReport(final String caseloadId, final String datetoLong, final String flag ,String userName ) {
		final Date date = parseDate(datetoLong);
		
		final String userDate = datetoLong; 
		final List<AccountCodes> masterData = osureporRepository.matserdata(caseloadId);
		if (masterData != null) {
			masterData.forEach(data -> {
				if (data.getAccountCode() != null) {
					final List<AccountCodes> childData = osureporRepository.childData(caseloadId,
							Long.valueOf(data.getAccountCode()));
					if (childData != null && !childData.isEmpty()) {
					for (final AccountCodes child : childData) {
						if (child.getAccountCode() != null) {
							final List<AccountCodes> subChildData = osureporRepository.subChildData(caseloadId,
									child.getAccountCode());
							if (subChildData != null) {
								for (final AccountCodes subChildBean : subChildData) {

									if (caseloadId != null) {
										final String CaseloadType = otstastaRepository.caseloadTypeData(caseloadId);
										if (CaseloadType != null) {
											subChildBean.setCaseloadType(CaseloadType);
										} else {
											subChildBean.setCaseloadType("INST");
										}
									}
									if ("INST".equals(subChildBean.getCaseloadType())) {
										final String headerData = "REP_HDR_INST";
										final String headerBlockData = otstastaRepository.headerBlockDataQuery(headerData);
										if (headerBlockData != null) {
											subChildBean.setfReportHeaderLabelName(headerBlockData);
										} else {
											subChildBean.setfReportHeaderLabelName("DEPARTMENT OF CORRECTIONS");
										}
									} else if ("COMM".equals(subChildBean.getCaseloadType())) {
										final String headerData = "REP_HDR_COMM";
										final String headerBlockData = otstastaRepository.headerBlockDataQuery(headerData);
										if (headerBlockData != null) {
											subChildBean.setfReportHeaderLabelName(headerBlockData);
										} else {
											subChildBean.setfReportHeaderLabelName("DEPARTMENT OF CORRECTIONS");
										}
									}
									final String caceloadNameOne = otstastaRepository.caceloadNameOneQuery(caseloadId);
									if (caceloadNameOne != null) {
										subChildBean.setfCaseloadNameOne(caceloadNameOne);
									}

									final Long accountPeriod = osureporRepository.getAccountPeriod(date);

									if ("C".equals(flag)) {
										BigDecimal ppenBalance = osureporRepository.getOpenBalance(accountPeriod,
												caseloadId, child.getAccountCode());
										if (ppenBalance == null) {
											ppenBalance = BigDecimal.ZERO;
										}
										BigDecimal ctempTxnAmount = osureporRepository.getctempTxnAmount(accountPeriod,
												caseloadId, subChildBean.getAccountCode(), date);
										if (ctempTxnAmount == null) {
											ctempTxnAmount = BigDecimal.ZERO;
										}
										final BigDecimal tempOpenBal = ppenBalance.add(ctempTxnAmount);
										subChildBean.setAmount(tempOpenBal);

									} else {

										BigDecimal tempCloseBalance = osureporRepository.getTempCloseBalance(caseloadId,
												subChildBean.getAccountCode());
										if (tempCloseBalance == null) {
											tempCloseBalance = BigDecimal.ZERO;
										}

										BigDecimal tempTxnAmount = osureporRepository.gettempTxnAmount(caseloadId,
												accountPeriod, subChildBean.getAccountCode());
										if (tempTxnAmount == null) {
											tempTxnAmount = BigDecimal.ZERO;
										}
										BigDecimal tempTxnMonAmount = osureporRepository.gettempTxnMonAmount(caseloadId,
												accountPeriod, subChildBean.getAccountCode(), date);
										if (tempTxnMonAmount == null) {
											tempTxnMonAmount = BigDecimal.ZERO;
										}
										final BigDecimal tempBalance = tempCloseBalance.add(tempTxnAmount);
										final BigDecimal tempOpenBalance = tempBalance.add(tempTxnMonAmount);
										subChildBean.setAmount(tempOpenBalance);

									}
									if ("N".equals(flag)) {
										child.setChildAccountCodes(subChildData.stream().filter(fData -> fData.getAmount() == null ||
												BigDecimal.ZERO.compareTo(fData.getAmount()) < 0).collect(Collectors.toList()));
									} else {
										child.setChildAccountCodes(subChildData);
									}

								}
							}
						}

					}
				}
					data.setChildAccountCodes(childData);
				} else {
				}
			});
		}

		final List<AccountCodes> reportData = new ArrayList<>();
		final AccountCodes report = new AccountCodes();
		final List<OtrbalanReportBean> fields = new ArrayList<>();

		masterData.forEach(data -> {
			final Integer superParent = data.getAccountCode();
			if (data.getChildAccountCodes() != null && !data.getChildAccountCodes().isEmpty()) {
				data.getChildAccountCodes().forEach(subData -> {
					subData.setSuperParentAccountCode(superParent);
					if (subData.getChildAccountCodes() != null) {
						subData.getChildAccountCodes().forEach(surSub -> {
							surSub.setSuperParentAccountCode(superParent);
							fields.add(addProp(data.getAccountName(), subData.getAccountName(), surSub.getAccountName(),
									surSub.getAmount(), surSub.getfReportHeaderLabelName(),
									surSub.getfCaseloadNameOne(), userDate, userName));
						});
					}
				});
			} else {
			}
		});
		Map<String, Object> param = new HashMap<>();
		if (fields != null && !fields.isEmpty()) {
			BigDecimal totalAssest = fields.stream().filter(data -> "ASSETS".equals(data.getParentName()) && data.getAmount() != null).map(mData -> mData.getAmount()).reduce((pre, post) -> pre.add(post)).orElse(BigDecimal.ZERO);
			BigDecimal totalLab = fields.stream().filter(data -> "LIABILITIES".equals(data.getParentName()) && data.getAmount() != null).map(mData -> mData.getAmount()).reduce((pre, post) -> pre.add(post)).orElse(BigDecimal.ZERO);
			BigDecimal totalRecpt = fields.stream().filter(data -> "RECEIPTS".equals(data.getParentName()) && data.getAmount() != null).map(mData -> mData.getAmount()).reduce((pre, post) -> pre.add(post)).orElse(BigDecimal.ZERO);
			BigDecimal totalDebs = fields.stream().filter(data -> "DISBURSEMENTS".equals(data.getParentName()) && data.getAmount() != null).map(mData -> mData.getAmount()).reduce((pre, post) -> pre.add(post)).orElse(BigDecimal.ZERO);
			if (totalAssest != null && totalLab != null) {
				param.put("proffBal", totalAssest.subtract(totalLab));
			}
			if (totalRecpt != null && totalDebs != null) {
				param.put("recpless", totalRecpt.subtract(totalDebs));
			}
		}
		report.setChildAccountCodes(masterData);
		reportData.add(report);
		final byte[] pdfReport = generateReport("OTRBALAN", param, fields);
		report.setReport(pdfReport);
		reportData.clear();
		reportData.add(report);


		return reportData;
	}

	public byte[] generateReport(final String reportName, final Map<String, Object> parameteres, final List<?> fields) {
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
		} catch (final Exception e) {

			log.error("generateReport", e);
		}
		return returnReport;
	}

	private OtrbalanReportBean addProp(final String parent, final String child, final String subChild, final BigDecimal amount,
			final String fReportHeaderLabelName, final String fCaseloadNameOne, final String userDate, final String userName) {
		final OtrbalanReportBean bean = new OtrbalanReportBean();
		bean.setParentName(parent);
		bean.setChildName(child);
		bean.setSubChildName(subChild);
		bean.setAmount(amount);
		bean.setfReportHeaderLabelName(fReportHeaderLabelName);
		bean.setfCaseloadNameOne(fCaseloadNameOne);
		bean.setUserDate(userDate);
        bean.setUserName(userName);
		return bean;
	}

	public List<OtrbnrcnBean> getBankReconciliationReport(final Long accountCode, String userDate,
			final String caseloadId ,String userName) {
		try {
			
			final Date userCDate = new SimpleDateFormat("MM/dd/yyyy").parse(userDate);
			if (userCDate != null) {
				userDate = new SimpleDateFormat("dd/MM/yyyy").format(userCDate);
			}
		} catch (final Exception e) {
			log.error("getBankReconciliationReport",e);
		}
		
		final List<OtrbnrcnBean> list = new ArrayList<OtrbnrcnBean>();
		final OtrbnrcnBean beanReport = new OtrbnrcnBean();
		final ReportBean bean = getReportValues(accountCode, userDate, caseloadId ,userName );
		beanReport.setReport(bean.getReport());
		list.add(beanReport);

		return list;
	}

	public ReportBean getReportValues(final Long accountCode, final String userDate, final String caseloadId, String userName) {
		final OtrbnrcnBean field = new OtrbnrcnBean();
		final ReportBean rBean = new ReportBean();
		final List<OtrbnrcnBean> accountCodes = osureporRepository.getBankReconciliationReport(accountCode, userDate,
				caseloadId);
		final BigDecimal totalCrAmnt  = osureporRepository.getTotalCrAmnt(accountCode,userDate,caseloadId);
		final BigDecimal totalDrAmnt  = osureporRepository.getTotalDrAmnt(accountCode,userDate,caseloadId);
		if (accountCodes != null) {
			accountCodes.forEach(data -> {
				data.setUserName(userName);
				data.setAccountCode(data.getAccountCode());
				data.setUserDate(userDate);
				if (data.getDisplayAmntBnk()== null) {
					data.setDisplayAmntBnk(BigDecimal.ZERO);
					
				}
				final BigDecimal amnt = data.getDisplayAmntBnk().subtract(totalCrAmnt).add(totalDrAmnt);
				data.setfTotalDebitAmount1(totalDrAmnt);
				if (amnt!=null) {
				data.setfReconBankBalance1(amnt);
				}
				BigDecimal sysBalN=  osureporRepository.getFsysBal(caseloadId,accountCode,userDate);
				if (sysBalN == null) {
					sysBalN =BigDecimal.ZERO;
				}
				final BigDecimal sysBalY=  osureporRepository.getFsysBalN(caseloadId,accountCode);
				final String pSysBal = osureporRepository.getProfileVal();
				if ("Y".equals(pSysBal)) {
					data.setfSystemBalance1(sysBalY);
					data.setfDifference1(data.getfReconBankBalance1().subtract(sysBalY));
				}
				if ("N".equals(pSysBal)) {
					data.setfSystemBalance1(sysBalN);
					data.setfDifference1(data.getfReconBankBalance1().subtract(sysBalN));
				}
				
				field.setAccountCode(data.getAccountCode());

			});
		}
		List<OtrbnrcnBean> queryThreeList =  osureporRepository.getQuery3List(accountCode,userDate,caseloadId);
		if (queryThreeList != null && queryThreeList.size() > 0) {
			queryThreeList = queryThreeList.stream()
					.filter(distinctByKey(dup -> dup.getCrTxn()))
					.collect(Collectors.toList());
		}
		boolean flag = false;
		final List<OtrbnrcnBean> queryFourList = osureporRepository.getQueryFourMainList(accountCode,userDate,caseloadId);
		if (queryFourList == null || queryFourList.size() == 0) {
			flag = true;
			queryFourList.add(new OtrbnrcnBean());
			if (queryFourList.size() > 0) {
				final BigDecimal amnt = osureporRepository.queryFourSubtotalAmnt(accountCode,userDate);
				for(final OtrbnrcnBean bean: queryFourList) {
					bean.setfTotalCreditAmount1(amnt);
					
					if(totalCrAmnt != null && amnt!= null) {
						final BigDecimal queryFourTotal = totalCrAmnt.subtract(amnt);
						bean.setfTotalCredit(queryFourTotal);
						
					}
					else {
						bean.setfTotalCredit(new BigDecimal(0));

					}
					
				
			}
				
			
		}
		}
		if (flag == false) {
		if (queryFourList != null || queryFourList.size() > 0) {
			final BigDecimal amnt = osureporRepository.queryFourSubtotalAmnt(accountCode,userDate);
			BigDecimal queryFourTotal = null;
				if (totalCrAmnt!=null) {
				queryFourTotal = totalCrAmnt.subtract(amnt);
				}
				
			final OtrbnrcnBean totalAdjustMent = new OtrbnrcnBean();
			totalAdjustMent.setfCrPye1("TOTAL ADJUSTMENTS");
			 final BigDecimal totalAmount = osureporRepository.queryFourAdjustmentAmount(accountCode,userDate);
			 totalAdjustMent.setfCrAmount1(totalAmount);
			 if(amnt!=null) {
			 totalAdjustMent.setfTotalCreditAmount1(amnt);
			 }
			 totalAdjustMent.setfTotalCredit(queryFourTotal);
			 queryFourList.add(totalAdjustMent);
			 
			
			
			
		}
		}
		final List<OtrbnrcnBean> queryFiveList = osureporRepository.getQuery5List(accountCode,userDate,caseloadId);
		if (queryFiveList == null || queryFiveList.size() == 0) {
			queryFiveList.add(new OtrbnrcnBean());
		}
		for (final OtrbnrcnBean query3:queryThreeList) {
			final BigDecimal crAmnt = osureporRepository.getQuery4List(query3.getCrTxn(),accountCode);
			query3.setfCrAmount2(crAmnt);
		}
		if(accountCodes.size()>0 && queryThreeList.size()> 0 && queryFourList.size()> 0 && queryFiveList.size() > 0) {
		accountCodes.get(0).setQueryThreeList(queryThreeList);
		accountCodes.get(0).setQueryFourList(queryFourList);
		accountCodes.get(0).setQueryFiveList(queryFiveList);
		}
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("totQthreeAmt", totalCrAmnt);
		final byte[] report = generateReport("OTRBNRCN", param, accountCodes);

		if (report != null) {
			rBean.setReport(report);
			rBean.setFields(accountCodes);
		}

		return rBean;
	}
	private <T> Predicate<T> distinctByKey(final Function<? super T, Object> keyExtractor)
	{
	    final Map<Object, Boolean> map = new ConcurrentHashMap<>();
	    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	@Override
	public List<OcrorrecReportsBean> printReportSupv(OffenderTransactions returnReport) {
		List<OcrorrecReportsBean> fields = new ArrayList<OcrorrecReportsBean>();
		OcrorrecReportsBean obj=new OcrorrecReportsBean();
		VAgencyAddresses addrBean = new VAgencyAddresses();
		returnReport.setRootOffenderId(returnReport.getOffenderId());
		if(returnReport.getNbtModifyUserId()!=null) {				
			obj.setfUserOne(returnReport.getNbtModifyUserId());
		}
	    obj.setfReceiptNumber(returnReport.getReceiptNumber());
	    final String fCaseloadDesc = osureporRepository.getfcaseloadDesc(returnReport.getCaseloadId());
		if (fCaseloadDesc != null) {
			obj.setCaselodHeaderLabelName(fCaseloadDesc);
		}
		if(returnReport.getCaseloadId()!=null) {
			addrBean.setAgyLocId(returnReport.getCaseloadId());
		}
		addrBean.setAddressType("MAIL");
		List<VAgencyAddresses> returnList = ocdreceiRepository.getAddreesDetails(addrBean);
		if (!returnList.isEmpty() && returnList.get(0).getStreetInformation() != null) {
			obj.setfAddressOne(returnList.get(0).getStreetInformation());
		}
		Phones phonesObj = new Phones();
		StringBuilder addressTwo = new StringBuilder();
		if (!returnList.isEmpty()) {
			if (returnList.get(0).getCityName() != null) {
				addressTwo = addressTwo.append(returnList.get(0).getCityName().concat(","));
			}
			if (returnList.get(0).getProvStateDesc() != null) {
				addressTwo = addressTwo.append(returnList.get(0).getProvStateDesc().concat(","));
			}
			if (returnList.get(0).getCountryCode() != null) {
				addressTwo = addressTwo.append(returnList.get(0).getCountryCode().concat(","));
			}
			if (returnList.get(0).getZipPostalCode() != null) {
				addressTwo = addressTwo.append(returnList.get(0).getZipPostalCode().concat(","));
			}
			if (returnList.get(0).getAddressId() != null) {
				phonesObj.setOwnerId(returnList.get(0).getAddressId());
			}
		}
		if (addressTwo != null) {
			obj.setfAddressTwo(addressTwo.toString());
		}

		List<Phones> phoneNumberData = new ArrayList<Phones>();
		phoneNumberData = oumaglocRepo.phonesExecuteQuery(phonesObj);
		if (!phoneNumberData.isEmpty() && phoneNumberData.get(0).getPhoneNo() != null) {
			obj.setfPhoneNumber(phoneNumberData.get(0).getPhoneNo());
		}
		String clientName = ocdreceiRepository.getClientName(returnReport.getOffenderBookId(),returnReport.getCreateUserId());
		if (clientName != null) {
			obj.setfClientName(clientName);
		}
		VAddresses vAddresses = new VAddresses();
		if(returnReport.getRootOffenderId() != null) {
		vAddresses.setOwnerId(BigDecimal.valueOf(returnReport.getRootOffenderId()));
		List<VAddresses> addressClientList = ocdaddreDao.vAddSearchVAddresses(vAddresses);
		if (!addressClientList.isEmpty()) {
			for (VAddresses vAddresses2 : addressClientList) {
				if ("Y".equals(vAddresses2.getPrimaryFlag())) {
					StringBuilder addressClient = new StringBuilder();
					if (vAddresses2.getCityName() != null) {
						addressClient = addressClient.append(vAddresses2.getCityName().concat(","));
					}
					if (vAddresses2.getProvStateDesc() != null) {
						addressClient = addressClient.append(vAddresses2.getProvStateDesc().concat(","));
					}
					if (vAddresses2.getCountryCode() != null) {
						addressClient = addressClient.append(vAddresses2.getCountryCode().concat(","));
					}
					if (vAddresses2.getZipPostalCode() != null) {
						addressClient = addressClient.append(vAddresses2.getZipPostalCode().concat(","));
					}
					if (addressClient != null) {
						obj.setfClientAddress(addressClient.toString());
					}
				}
			}
		}
		}
		obj.setfSidNumber(returnReport.getRootOffenderId());
		Date longSupvDate = ocdreceiRepository.getLongestSupervisionExpireDate(returnReport.getOffenderBookId());
		if (longSupvDate != null) {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			obj.setfDateOfBirth(formatter.format(longSupvDate));
		}

		List<CasePlans> casePlanList = new ArrayList<>();
		casePlanList = ocdreceiRepository.getCasePlanDetailsToGetPoName(returnReport.getOffenderBookId());
		if (!casePlanList.isEmpty()) {
			if (casePlanList.get(0).getCalAgyLocId() != null || casePlanList.get(0).getSacStaffId() != null
					|| casePlanList.get(0).getFromDate() != null || casePlanList.get(0).getRole() != null
					|| casePlanList.get(0).getPosition() != null) {
				StaffMembersV2 staffBean = new StaffMembersV2();
				staffBean = ocdiplanRepository.casPlnPostQuerySacStaffId(casePlanList.get(0));
				if (staffBean.getStaffName() != null) {
					obj.setfPoName(staffBean.getStaffName());
				}
			}
		}
		String remitterName = ocdreceiRepository.getRemitterName(returnReport.getTxnId(),
				returnReport.getTxnEntrySeq());
		if (remitterName != null) {
			obj.setfRemitter(remitterName);
		}
		obj.setGlobalMsg(osureporRepository.getMsgValue(returnReport.getModuleName()));
		obj.setCountySpecificMsg(osureporRepository.getCountySpecificMsg(returnReport.getModuleName()+"."+returnReport.getCaseloadId()));
		if ("OTRBSTAT".equals(returnReport.getModuleName())) {
          DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			if ("Y".equals(returnReport.getAddress()) && returnReport.getRootOffenderId() != null) {
				VAddressUsages addObj = osureporRepository
						.getBadAddress(BigDecimal.valueOf(returnReport.getRootOffenderId()));
				obj.setfClientAddress(addObj.getFullAddress());
			} else {
				obj.setfClientAddress(null);
			}
			List<FeeAccountBalanceBean> feeAcntBeanList = new ArrayList<FeeAccountBalanceBean>();
			List<offBillingStatements> offFeeData = new ArrayList<>();
			OcrorrecReportsBean objVal=new OcrorrecReportsBean();
				offFeeData = osureporRepository.gettingAccountBalanceOffBillingStatements(returnReport);
			if(offFeeData.size() > 0) {
				for (offBillingStatements offFeeBillTransactions : offFeeData) {
					if (returnReport.getInOutStatus() != null) {
						returnReport
								.setRootOffenderId(Long.valueOf(offFeeBillTransactions.getRootOffenderId().toString()));
						returnReport
								.setOffenderBookId(Long.valueOf(offFeeBillTransactions.getOffenderBookId().toString()));

						Date longestSupvDate = ocdreceiRepository
								.getLongestSupervisionExpireDate(returnReport.getOffenderBookId());
						if (longestSupvDate != null) {
							obj.setfDateOfBirth(formatter.format(longestSupvDate));
						}
						String cName = ocdreceiRepository.getClientName(returnReport.getOffenderBookId(),returnReport.getCreateUserId());
						if (cName != null) {
							obj.setfClientName(cName);
						}
					}
					List<CasePlans> listObj = new ArrayList<>();
					listObj = osureporRepository.getPoName(returnReport.getOffenderBookId());
					if (!listObj.isEmpty()) {
						obj.setfPoName(listObj.get(0).getOfficer());
				    }
					if ("Y".equals(returnReport.getAddress())) {
						VAddressUsages addObj = osureporRepository
								.getBadAddress(BigDecimal.valueOf(returnReport.getRootOffenderId()));
						obj.setfClientAddress(addObj.getFullAddress());
					} else {
						obj.setfClientAddress(null);
					}
					
					objVal = new OcrorrecReportsBean();
					Date endDate = osureporRepository
							.getForPeriodEndingdate(formatter.format(offFeeBillTransactions.getBillingCycleEndDate()));
					if (endDate != null) {
						objVal.setfDateOne(formatter.format(endDate));
					}
					feeAcntBeanList = new ArrayList<>();
					objVal.setCaselodHeaderLabelName(obj.getCaselodHeaderLabelName());
					objVal.setGlobalMsg(obj.getGlobalMsg());
					objVal.setCountySpecificMsg(obj.getCountySpecificMsg());
					objVal.setfPoName(obj.getfPoName());
					objVal.setfUserOne(obj.getfUserOne());
					objVal.setfClientName(obj.getfClientName());
					objVal.setfClientAddress(obj.getfClientAddress());
					objVal.setfSidNumber(Long.valueOf(offFeeBillTransactions.getRootOffenderId().toString()));
					objVal.setfDateOfBirth(obj.getfDateOfBirth());
					objVal.setfAddressOne(obj.getfAddressOne());
					objVal.setfAddressTwo(obj.getfAddressTwo());
					objVal.setfPhoneNumber(obj.getfPhoneNumber());
					FeeAccountBalanceBean feeAcntBlncBean = new FeeAccountBalanceBean();
					feeAcntBlncBean.setBeginingBalanceAmount(offFeeBillTransactions.getBeginingBalanceAmount());
					feeAcntBlncBean.setPaymentsCreditsAmount(offFeeBillTransactions.getPaymentsCreditsAmount());
					feeAcntBlncBean.setBillingsAmount(offFeeBillTransactions.getBillingsAmount());
					feeAcntBlncBean.setEndingBalanceAmount(offFeeBillTransactions.getEndingBalanceAmount());
					feeAcntBeanList.add(feeAcntBlncBean);
					objVal.setFeeAccountBalanceBean(feeAcntBeanList);
					
					List<FeeAccountBalanceBean> offFeeDataPayment = osureporRepository
							.gettingPaymentsAndCreditsForAccounts(returnReport,
									offFeeBillTransactions.getBillingStatementId());
					for (FeeAccountBalanceBean payOobj : offFeeDataPayment) {
						if ("CREDITS REVERSAL".equals(payOobj.getCaseloadDescription())
								|| "PAYMENT REVERSAL".equals(payOobj.getCaseloadDescription())) {
							payOobj.setPaymentAmount(payOobj.getPaymentAmount().negate());
						}
					}
					if (!offFeeDataPayment.isEmpty()) {
						objVal.setPaymantCreditAccount(offFeeDataPayment);
						objVal.setPaymentSumAmount(osureporRepository.gettingPaymentSumAmount(returnReport,offFeeBillTransactions.getBillingStatementId())); 
					}
					List<FeeAccountBalanceBean> offFeeBillingAcnt = osureporRepository.gettingBilingForAccountSection(returnReport,offFeeBillTransactions.getBillingStatementId());
					Integer billingsumAmount = 0;
					for (FeeAccountBalanceBean offFeeBillPaymant : offFeeBillingAcnt) {
						if(offFeeBillPaymant.getPaymentAmount()!= null) {
							billingsumAmount = billingsumAmount + Integer.valueOf(offFeeBillPaymant.getPaymentAmount().toString());	
						}				
					}
					if (!offFeeBillingAcnt.isEmpty()) {
						objVal.setBillingAccount(offFeeBillingAcnt);
						objVal.setBillingSumAmount(BigDecimal.valueOf(billingsumAmount));
					}
					fields.add(objVal);
			}
			return fields;
			} else {
				Date endDate = osureporRepository.getForPeriodEndingdate(returnReport.getToDate());
				if(endDate != null) {
					obj.setfDateOne(formatter.format(endDate));	
				}
			}
		} else {
			List<FeeAccountBalanceBean> feeAcntBeanList = new ArrayList<FeeAccountBalanceBean>();
			List<offBillingStatements> offFeeData = osureporRepository.gettingSummaryOfFeeAccount(returnReport);
			Double paymentSumCount = 0.0;
			for (offBillingStatements offFeeBillTransactions : offFeeData) {
				FeeAccountBalanceBean feeAcntBlncBean = new FeeAccountBalanceBean();
				feeAcntBlncBean.setFeeCode(offFeeBillTransactions.getCaseloadId());
				feeAcntBlncBean.setCaseloadDescription(offFeeBillTransactions.getCreateUserId());
				feeAcntBlncBean.setPaymentAmount(offFeeBillTransactions.getBillingsAmount());
				OffFeeBillTransactions data = new OffFeeBillTransactions();
				data.setOffenderFeeId(offFeeBillTransactions.getRootOffenderId());
				data.setBillId(offFeeBillTransactions.getBillId());
				List<FeeAccountBalanceBean> returNList = ocutrdetService.billTransReportExecuteQuery(data);
				if (returNList.size() > 0) {
					feeAcntBlncBean.setBeginingBalanceAmount(returNList.get(0).getBeginingBalanceAmount());
					feeAcntBlncBean.setPaymentsCreditsAmount(returNList.get(0).getPaymentsCreditsAmount());
					feeAcntBlncBean.setBillingsAmount(returNList.get(0).getBillingsAmount());
					feeAcntBlncBean.setEndingBalanceAmount(returNList.get(0).getEndingBalanceAmount());
				}
				if (feeAcntBlncBean.getEndingBalanceAmount() != null) {
					Double count = feeAcntBlncBean.getEndingBalanceAmount().doubleValue();
					paymentSumCount = paymentSumCount + count;
				}
				feeAcntBeanList.add(feeAcntBlncBean);
				
			}
			
			if (!feeAcntBeanList.isEmpty()) {
				obj.setPaymentSumAmount(BigDecimal.valueOf(paymentSumCount));
				obj.setFeeAccountBalanceBean(feeAcntBeanList);
			}
		}
		
		fields.add(obj);
		return fields;
	}
	
	@Override
	public ReportBean getReportOtrbstat(List<OffenderTransactions> paramBean) {
		final List<OcrorrecReportsBean> fields = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		if (paramBean != null && !paramBean.isEmpty()) {
			paramBean.forEach(data -> {
				final List<OcrorrecReportsBean> field = printReportSupv(data);
				if (field != null && field.size() > 0) {
					fields.addAll(field);
				}
			});
		}
		byte[] returnReport = generateReport(paramBean.get(0).getModuleName(), param, fields);
		ReportBean rBean = new ReportBean();
		rBean.setReport(returnReport);
		return rBean;
	}

	@Override
	public List<ReferenceCodes> getLovOtrbstat(final String parameterName, final String caseloadId) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		if ("P_INCLUDE_BAD_ADDRESS".equals(parameterName) || "P_INCLUE_DO_NOT_PRINT_STATEMENT".equals(parameterName)) {
			ReferenceCodes data = new ReferenceCodes();
			data.setCode("Y");
			data.setDescription("Yes");
			ReferenceCodes data1 = new ReferenceCodes();
			data1.setCode("N");
			data1.setDescription("No");
			returnList.add(data);
			returnList.add(data1);
		} else if ("P_AOS_IN_OUT".equals(parameterName)) {
			ReferenceCodes data = new ReferenceCodes();
			data.setCode("Active");
			data.setDescription("Active");
			ReferenceCodes data1 = new ReferenceCodes();
			data1.setCode("Outcount");
			data1.setDescription("Outcount");
			ReferenceCodes data2 = new ReferenceCodes();
			data2.setCode("Both");
			data2.setDescription("Both");
			returnList.add(data);
			returnList.add(data1);
			returnList.add(data2);
		} else if ("P_PO_CASELOAD".equals(parameterName)) {
			returnList = osureporRepository.getPoCasloadNames(caseloadId);
			ReferenceCodes data1 = new ReferenceCodes();
			data1.setCode("ALL PO Caseloads within the logged in Elite Caseload");
			data1.setDescription("ALL PO Caseloads within the logged in Elite Caseload");
			returnList.add(data1);
		} else if ("P_FROM_BILLING_CYCLE".equals(parameterName)) {
			returnList = osureporRepository.getFromBillingCycleDate();
			BigDecimal i = BigDecimal.ZERO;
			for(ReferenceCodes bean: returnList) {
				i = i.add(BigDecimal.ONE);
				bean.setListSeq(i);
			}
		} else if ("P_TO_BILLING_CYCLE".equals(parameterName)) {
			returnList = osureporRepository.getToBillingCycleDate();
			BigDecimal i = BigDecimal.ZERO;
			for(ReferenceCodes bean: returnList) {
				i = i.add(BigDecimal.ONE);
				bean.setListSeq(i);
			}
		}
		return returnList;
	}

}