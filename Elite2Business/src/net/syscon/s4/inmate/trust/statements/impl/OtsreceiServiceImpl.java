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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OmsRequests;
import net.syscon.s4.common.beans.OmsRequestsCommitBean;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.Printers;
import net.syscon.s4.inmate.trust.statements.OtsreceiRepository;
import net.syscon.s4.inmate.trust.statements.OtsreceiService;

/**
 * Class OtsreceiServiceImpl
 */
@Service
public class OtsreceiServiceImpl extends BaseBusiness implements OtsreceiService {

	private static Logger logger = LogManager.getLogger(OtsreceiServiceImpl.class.getName());
	@Autowired
	private OtsreceiRepository otsreceiRepository;

	/**
	 * Creates new OtsreceiServiceImpl class Object
	 */
	public OtsreceiServiceImpl() {
		// OtsreceiServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Object omsReqPreInsert() {
		final SysDual paramBean = null;
		final Object object = otsreceiRepository.omsReqPreInsert(paramBean);
		return object;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OmsModules> otsreceiKeyCommit(final OmsModules paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Printers cgfkchkOmsReqOmsReqPrint(final Printers paramBean) {
		final Printers printers = otsreceiRepository.cgfkchkOmsReqOmsReqPrint(paramBean);
		return printers;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OmsModules> cgfkchkOmsReqOmsReqModul(final OmsModules paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public SystemProfiles runReport(final SystemProfiles paramBean) {
		final SystemProfiles systemProfiles = otsreceiRepository.runReport(paramBean);
		return systemProfiles;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OmsRequests> omsReqExecuteQuery(final OmsRequests searchRecord) {
		return otsreceiRepository.omsReqExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOMS_REQ
	 *
	 * @
	 */
	@Transactional
	public Integer omsReqCommit(final OmsRequestsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otsreceiRepository.omsReqInsertOmsRequests(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = otsreceiRepository.omsReqUpdateOmsRequests(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otsreceiRepository.omsReqDeleteOmsRequests(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		return otsreceiRepository.cgfkOmsReqPrinterIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<OmsModules> cgfkOmsReqModuleNameRecordGroup() {
		return otsreceiRepository.cgfkOmsReqModuleNameRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<CaseloadAgencyLocations> cgfkCsldDpAgyLocRecordGroup(final String caseloadId) {
		return otsreceiRepository.cgfkCsldDpAgyLocRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> cgfkRecptsCreatedUsersRecordGroup() {
		return otsreceiRepository.cgfkRecptsCreatedUsersRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<String> checkValidReceipts(final CaseloadDeductionProfiles paramBean) {
		return otsreceiRepository.checkValidReceipts(paramBean);

	}

	@Override
	public BigDecimal getDefaultCopies() {
		return otsreceiRepository.getDefaultCopies();
	}

	@Override
	public BigDecimal receiptNumExist(final CaseloadDeductionProfiles param) {
		return otsreceiRepository.receiptNumExist(param);
	}

	@Override
	public ReportBean generateOtrdrecereport(final CaseloadDeductionProfiles paramBean) {
		List<OtrdreceReportBean> result = new ArrayList<OtrdreceReportBean>();
		List<OtrdreceReportBean> fields = new ArrayList<OtrdreceReportBean>();
		byte[] returnReport = null;
		final ReportBean reportBean = new ReportBean();
		result = otsreceiRepository.generateOtrdrecereport(paramBean);
		if (result == null || result.isEmpty()) {
			reportBean.setErrorMessage("otsrecei.thrsnovalidrecipt");
			return reportBean;
		} else {
		result.forEach(data -> {
			if (data.getAmt() != null) {
				final String txnAmount = otsreceiRepository.numberToWord(data.getAmt());
				data.setAmtText(txnAmount);
			}
		});
		if (paramBean.getCopies() != null && !paramBean.getCopies().equals(BigDecimal.ZERO)) {
			final long copies = paramBean.getCopies().longValue();
			result.forEach(data -> {
				for(long num =0; num < copies; num ++) {
					fields.add(data);
				}
			});
		}
		}
		String currSymbol = otsreceiRepository.getCurrencySymbol();
		Map<String, Object> param = new HashMap<>();
		param.put("cfCurr", currSymbol);
		returnReport = generateReport("OTRDRECE", param, fields);
		reportBean.setReport(returnReport);
		return reportBean;
	}

	@Override
	public ReportBean generateOtrreceireport(final CaseloadDeductionProfiles paramBean) {
		List<OtrreceiReportBean> result = new ArrayList<OtrreceiReportBean>();
		List<OtrreceiReportBean> fields = new ArrayList<OtrreceiReportBean>();
		byte[] returnReport = null;
		final ReportBean reportBean = new ReportBean();
		result = otsreceiRepository.generateOtrreceireport(paramBean);
		if (result == null || result.isEmpty()) {
			reportBean.setErrorMessage("otsrecei.thrsnovalidrecipt");
			return reportBean;
		} else {
			result.forEach(data -> {
				if (data.getAmt() != null) {
					final String txnAmount = otsreceiRepository.numberToWord(data.getAmt());
					data.setAmtText(txnAmount);
				}
			});
			if (paramBean.getCopies() != null && !paramBean.getCopies().equals(BigDecimal.ZERO)) {
				final long copies = paramBean.getCopies().longValue();
				result.forEach(data -> {
					for(long num =0; num < copies; num ++) {
						fields.add(data);
					}
				});
			}
		}
		String currSymbol = otsreceiRepository.getCurrencySymbol();
		Map<String, Object> param = new HashMap<>();
		param.put("cfCurr", currSymbol);
		returnReport = generateReport("OTRRECEI", param, fields);
		reportBean.setReport(returnReport);
		return reportBean;
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
			return null;
		}
		return returnReport;
	}

	@Override
	public ReportBean getReport(final CaseloadDeductionProfiles paramBean) {
		ReportBean result = new ReportBean();
		if ("R".equals(paramBean.getTxnUsage())) {
			result = generateOtrreceireport(paramBean);
		} else if ("D".equals(paramBean.getTxnUsage())) {
			result = generateOtrdrecereport(paramBean);
		} else {
			result.setErrorMessage("TRUE");
		}
		return result;
	}

}