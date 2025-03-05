package net.syscon.s4.cf.statements.impl;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import net.syscon.s4.cf.offendertransactions.OcdbreciRepository;
import net.syscon.s4.cf.offendertransactions.OcdbreciService;
import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.cf.offendertransactions.impl.OcdbreciServiceImpl;
import net.syscon.s4.cf.statements.OcsreceiRepository;
import net.syscon.s4.cf.statements.OcsreceiService;
import net.syscon.s4.cf.statements.beans.ocsreceiReportsBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OmsRequestsCommitBean;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Class OcsreceiServiceImpl
 */
@Service
public class OcsreceiServiceImpl implements OcsreceiService {

	@Autowired
	private OcsreceiRepository ocsreceiRepository;
	@Autowired
	private OcdbreciServiceImpl ocdbreciService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcsreceiServiceImpl.class.getName());

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String cgfkchkOmsReqOmsReqPrint(Printers paramBean) {
		return ocsreceiRepository.cgfkchkOmsReqOmsReqPrint(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String cgfkchkOmsReqOmsReqOms(OmsModules paramBean) {
		return ocsreceiRepository.cgfkchkOmsReqOmsReqOms(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OmsRequests> omsReqExecuteQuery(OmsRequests searchRecord) {
		return ocsreceiRepository.omsReqExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOMS_REQ
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer omsReqCommit(OmsRequestsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OmsRequests obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocsreceiRepository.omsReqInsertOmsRequests(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = ocsreceiRepository.omsReqUpdateOmsRequests(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OmsRequests obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocsreceiRepository.omsReqDeleteOmsRequests(commitBean.getDeleteList());
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
	public List<OffenderTransactions> offTxnExecuteQuery(OmsRequests searchRecord) {
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		returnList = ocsreceiRepository.offTxnExecuteQuery(searchRecord);
		for (OffenderTransactions obj : returnList) {
			OffenderBookings returnBean = ocsreceiRepository.offTxnPostQueryTwo(obj.getOffenderId());
			obj.setNbtOffenderIdDisplay(returnBean.getCgnbtBookingStatus());
			obj.setNbtNameText(returnBean.getDspLastName());
			if (obj.getReceiptPrintedFlag() == null) {
				obj.setReceiptPrintedFlag("N");
			}
			 if ("N".equals(obj.getReceiptPrintedFlag())) {
				obj.setNbtReceiptPrintFlag("Y");

			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OffenderTransactions> offTxnCommit(OffenderTransactionsCommitBean commitBean) {
		commitBean.getOmsReqBean().setScreenId("OCSRECEI");
		List<OffenderTransactions> returnData = new ArrayList<>();
		OffenderTransactions offTrxnBean = new OffenderTransactions();
		final OffenderTransactions report = new OffenderTransactions();
		byte[] pdfReport = null;
		int liReturn = 0;
		for (PrintReceiptsTmp obj : commitBean.getPrintRcptInsertList()) {
			obj.setCreateUserId(commitBean.getCreateUserId());
		}
		liReturn = insertToTmpTable(commitBean.getPrintRcptInsertList());
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderTransactions obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());			
				}
			liReturn = ocsreceiRepository.offTxnUpdateOffenderTransactions(commitBean.getUpdateList());
		}
		if ("OCRDRECE".equals(commitBean.getOmsReqBean().getModuleName())) {
			commitBean.getOmsReqBean().setCreateUserId(commitBean.getCreateUserId());
			pdfReport = ocrdreceRunReportQuery(commitBean.getOmsReqBean());
		} else {
			offTrxnBean.setCaseloadId(commitBean.getOmsReqBean().getCaseLoadId());
			offTrxnBean.setSessionId(commitBean.getOmsReqBean().getSessionId());
			offTrxnBean.setModuleName(commitBean.getOmsReqBean().getScreenId());
			pdfReport = ocdbreciService.ocrdreceRunReportQuery(offTrxnBean);
		}
		liReturn = aftereReport(commitBean.getOmsReqBean().getScreenId(), commitBean.getOmsReqBean().getSessionId() ,commitBean.getCreateUserId() );
		if (liReturn >= 1) {
			report.setReport(pdfReport);
			report.setSealFlag("1");
			returnData.clear();
			returnData.add(report);
		}
		return returnData;
	}

	public Integer insertToTmpTable(final List<PrintReceiptsTmp> insertList) {
		Integer liReturn = 0;
		liReturn = ocsreceiRepository.printReceiptsTmpDeletequery(insertList.get(0).getSessionId(),insertList.get(0).getCreateUserId());
		liReturn = ocsreceiRepository.printReceitsInsertQuery(insertList);
		return liReturn;

	}

	public byte[] ocrdreceRunReportQuery(OmsRequests bean) {
		byte[] returnReport = null;
		final ReportBean rBean = new ReportBean();
		List<ocsreceiReportsBean> fields = new ArrayList<>();
		SystemProfiles sysPflBean = ocsreceiRepository.getFclientValue();
		String fCaseloadDesc = ocsreceiRepository.getfcaseloadDesc(bean.getCaseLoadId());
		fields = ocsreceiRepository.getReportData(bean);
		String caseloadId = bean.getCaseLoadId();
		if (fields.size() > 0) {
			for(ocsreceiReportsBean element : fields) {
				if (element.getTotalAmount() != null) {
					final String totAmount = ocsreceiRepository.numberToWord(element.getTotalAmount());
					element.setForAmount(totAmount);
				}
				if (sysPflBean.getDescription() != null) {
					element.setfClient(sysPflBean.getDescription());
				}
				if (sysPflBean.getProfileValue() != null) {
					element.setfLocName(sysPflBean.getProfileValue());
				}
				if (fCaseloadDesc != null) {
					element.setfClDesc(fCaseloadDesc);
				}
				if (element.getOffenderId() != null) {
					String offenderId = ocsreceiRepository.getOffenderIdData(caseloadId,
							element.getOffenderId(),bean.getCreateUserId());
					if (offenderId != null) {
						element.setfOffId(offenderId);
					}
				}
			}
		}
		Map<String, Object> param = new HashMap<>();
		returnReport = generateReport("OCRDRECE", param, fields);
		rBean.setReport(returnReport);
		return returnReport;

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
			logger.error("generateReport", e);
		}
		return returnReport;
	}

	public Integer aftereReport(String modulename, Long sessionId, String modifyUserId) {
		Integer returnCount;
		returnCount = ocsreceiRepository.updateOffenderTransactionsrpt(modulename, sessionId , modifyUserId);
		returnCount = ocsreceiRepository.printReceiptsTmpDeletequeryRpt(modulename, sessionId, modifyUserId);
		return returnCount;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		return ocsreceiRepository.cgfkOmsReqPrinterIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsModules> cgfkOmsReqModuleNameRecordGroup() {
		return ocsreceiRepository.cgfkOmsReqModuleNameRecordGroup();

	}

}