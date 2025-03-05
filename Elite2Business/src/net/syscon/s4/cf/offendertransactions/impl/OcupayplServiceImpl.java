package net.syscon.s4.cf.offendertransactions.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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
import net.syscon.s4.cf.offendertransactions.OcupayplRepository;
import net.syscon.s4.cf.offendertransactions.OcupayplService;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlanCommitBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanReportBean;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.cf.offendertransactions.beans.VOffenderPaymentSchedule;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.pkgs.payment_schedules.PaymentSchedulesService;
import net.syscon.s4.pkgs.tag_visits.TagVisitsRepository;

/**
 * Class OcupayplServiceImpl
 */
@Service
public class OcupayplServiceImpl extends BaseBusiness implements OcupayplService {

	@Autowired
	private OcupayplRepository ocupayplRepository;
	@Autowired
	private PaymentSchedulesService paymentSchedulesService;
	@Autowired
	private TagVisitsRepository tagVisitsRepository;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcupayplServiceImpl.class.getName());
	
	private static final String DATEFORMAT = "yyyy-MM-dd";
	private static final String DATFORMAT = "dd-MM-yyyy";
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPaymentPlan> payPlnExecuteQuery(final OffenderPaymentPlan searchRecord) {

		List<OffenderPaymentPlan> list = new ArrayList<>();
		String code = null;

		list = ocupayplRepository.payPlnExecuteQuery(searchRecord);

		for (final OffenderPaymentPlan payPlan : list) {

			payPlan.setPaymentPlanId(payPlan.getPaymentPlanId());
			payPlan.setPaymentPlanSeq(payPlan.getPaymentPlanSeq());

			if (payPlan.getGroupId() != null) {
			//	code = ocupayplRepository.payPlnPostQuery(payPlan.getGroupId());
				code=paymentSchedulesService.getGroupCode(payPlan.getGroupId());
				payPlan.setCode(code);
				if (payPlan.getPaymentClosedDate() != null) {
					payPlan.setPaymentClosedFlag("Y");
				}
			}
		}

		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPAY_PLN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OffenderPaymentPlan> payPlnCommit(final OffenderPaymentPlanCommitBean commitBean) {
		final List<OffenderPaymentPlan> liReturnData = new ArrayList<>();
		final OffenderPaymentPlan paymtPlan = new OffenderPaymentPlan();
		List<OffenderPaymentPlan> offenderPlan = null;
		int liReturn = 0;
		Long payPlanId = null;
		long planSeq = 0;
		// insertRecords
		if (!commitBean.getInsertList().isEmpty()) {
			offenderPlan = commitBean.getInsertList();
			for (final OffenderPaymentPlan payPlan : offenderPlan) {
				payPlan.setCreateUserId(commitBean.getCreateUserId());
				payPlanId = ocupayplRepository.preInsertPlanId(payPlan.getInformationNumber(), payPlan.getGroupId(),
				payPlan.getOffenderId());

				planSeq = ocupayplRepository.preInsertPlanSeq(payPlanId);
				payPlan.setLeniencyFlag("N");
				payPlan.setPaymentClosedFlag("N");
				payPlan.setRegenerationFlag("N");
				payPlan.setPaymentPlanId(payPlanId);
				payPlan.setPaymentPlanSeq(planSeq);
		
				

				liReturn = ocupayplRepository.payPlnInsertOffenderPaymentPlans(commitBean.getInsertList());
				payPlan.setListSeq(BigDecimal.valueOf(liReturn));
				payPlan.setCount(liReturn);

				liReturnData.add(payPlan);
			}

		}
		// updateRecords
		if (!commitBean.getUpdateList().isEmpty()) {
			offenderPlan = commitBean.getUpdateList();
			for (final OffenderPaymentPlan payPlan : offenderPlan) {
				payPlan.setModifyUserId(commitBean.getCreateUserId());
				liReturn = ocupayplRepository.payPlnUpdateOffenderPaymentPlans(commitBean.getUpdateList());
				payPlan.setListSeq(BigDecimal.valueOf(liReturn));
				payPlan.setCount(commitBean.getUpdateList().size());
				liReturnData.add(payPlan);
			}
		}

		// deleteRecords
		if (!commitBean.getDeleteList().isEmpty()) {
			final Integer returnCount = validateDelRow(commitBean.getDeleteList());
			if (returnCount > 0) {
				paymtPlan.setListSeq(BigDecimal.valueOf(5));
				liReturnData.add(paymtPlan);
				return liReturnData;
			}
			offenderPlan = commitBean.getDeleteList();
			for (final OffenderPaymentPlan payPlan : offenderPlan) {
				payPlan.setModifyUserId(commitBean.getCreateUserId());
				liReturn = ocupayplRepository.payPlnDeleteOffenderPaymentPlans(commitBean.getDeleteList());
				payPlan.setCount(liReturn);
				payPlan.setListSeq(BigDecimal.valueOf(liReturn));
				liReturnData.add(payPlan);
			}

		}

		return liReturnData;
	}

	private Integer validateDelRow(final List<OffenderPaymentPlan> deleteList) {
		final List<Long> returnObj = deleteList.stream().map(data -> data.getPaymentPlanId())
				.collect(Collectors.toList());
		return ocupayplRepository.validateDelRow(returnObj);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffenderPaymentSchedule> paySchExecuteQuery(final VOffenderPaymentSchedule searchRecord) {
		return ocupayplRepository.paySchExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @throws ParseException
	 *
	 * @throws SQLException
	 */
	public List<PaymentPlanTransaction> ppTxnExecuteQuery(final PaymentPlanTransaction searchRecord)
			throws ParseException {
		return ocupayplRepository.ppTxnExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return ocupayplRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {

		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderDeductions> cgfkPayPlnInformationNumbeRecordGroup(final Long offenderId, final String caseloadId) {

		return ocupayplRepository.cgfkPayPlnInformationNumbeRecordGroup(offenderId,caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkPayPlnDspDescription4RecordGroup() {
		return ocupayplRepository.cgfkPayPlnDspDescription4RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkPayPlnDspDescription3RecordGroup() {
		return ocupayplRepository.cgfkPayPlnDspDescription3RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkPayPlnDspDescriptionRecordGroup() {
		return ocupayplRepository.cgfkPayPlnDspDescriptionRecordGroup();

	}

	@Override
	public OffenderPaymentPlan whenValidateItem( final  OffenderPaymentPlan searchBean) throws ParseException {

		final OffenderPaymentPlan returnData = new OffenderPaymentPlan();
		BigDecimal paymentAmount = new BigDecimal(0);
		String code = null;
		String recurFlag = null;
		String recurExists = null;
		BigDecimal recurAmt = null;
		long nPlanId = 0;
		long nPlanSeq = 0;
		final BigDecimal offenderId = searchBean.getOffenderId();
		final String infoNumber = searchBean.getInformationNumber();
		final BigDecimal groupId = searchBean.getGroupId();
		final String parentInfo = searchBean.getParentInfoId();
		
		if (searchBean.getMonthly() != null) {
			paymentAmount = calculatePaymentAmount(searchBean);
			returnData.setAmount(paymentAmount);

		}
		if (searchBean.getGroupId() != null) {
		//	code = ocupayplRepository.getGroupCode(searchBean.getGroupId());
			code = paymentSchedulesService.getGroupCode(searchBean.getGroupId());

			returnData.setCode(code);
		}
		if (searchBean.getGroupId() != null && searchBean.getInformationNumber() != null) {
//			recurFlag = ocupayplRepository.chkRecursive(searchBean.getOffenderId(), searchBean.getInformationNumber(),
//					searchBean.getGroupId());
			
			recurFlag =paymentSchedulesService.chkRecursive(searchBean.getOffenderId(), searchBean.getInformationNumber(), searchBean.getGroupId());


			if (recurFlag != null) {
				if ("Y".equals(recurFlag)) {
					recurFlag = "Y";
					try {
						recurExists = ocupayplRepository.recurtExists(searchBean.getOffenderId(),
								searchBean.getInformationNumber(), searchBean.getGroupId());
						returnData.setSealFlag(ONE); // 'Please close the previous schedule before you create a new
														// recursive payment schedule.'
					} catch (Exception e) {
						recurExists = "N";
					}
					try {
//						recurAmt = ocupayplRepository.getRecursiveAmt(searchBean.getOffenderId(),
//								searchBean.getInformationNumber(), searchBean.getGroupId());
						
						recurAmt =	paymentSchedulesService.getRecursiveAmt(searchBean.getOffenderId(), searchBean.getInformationNumber(), searchBean.getGroupId());
						searchBean.setAmount(recurAmt);
					} catch (Exception e) {
						logger.error("Exception :", e);

					}
				}
			} else {
				recurFlag = "N";
			}
			try {
				 OffenderPaymentPlan payPlanandSeq  = new OffenderPaymentPlan();
				  payPlanandSeq = ocupayplRepository.getMaxPlanIdAndSeq(searchBean.getOffenderId(),
						searchBean.getInformationNumber(), searchBean.getGroupId());
				nPlanId = payPlanandSeq.getPaymentPlanId();
				nPlanSeq = payPlanandSeq.getPaymentPlanSeq();
				returnData.setPaymentPlanId(nPlanId);
				returnData.setPaymentPlanSeq(nPlanSeq);
				returnData.setOffenderId(payPlanandSeq.getOffenderId());

			} catch (Exception e) {
				nPlanId = 0;
				nPlanSeq = 0;
			}
			final OffenderPaymentPlan amount = showGroupDiff(offenderId, infoNumber, groupId,parentInfo,searchBean.getPaymentPlanId());
			returnData.setGroupDifference(amount.getGroupDifference());
			returnData.setGroupUnpaidAmount(amount.getGroupUnpaidAmount());
			returnData.setCode(code);
			returnData.setOffenderId(searchBean.getOffenderId());
			returnData.setInformationNumber(searchBean.getInformationNumber());
			returnData.setGroupId(searchBean.getGroupId());
			returnData.setTotalArrears(amount.getTotalArrears());
			if (amount.getGroupDifference().compareTo(BigDecimal.ZERO) <= 0) {
				returnData.setSealFlag(THREE); // 'This group obligation has been paid off or is suspended.'
			}

		}
		
		return returnData;
	}

	private OffenderPaymentPlan showGroupDiff(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId, final String parentInfoId, final long paymentPlanId) {

		final OffenderPaymentPlan amounts = new OffenderPaymentPlan();
		BigDecimal totalSch = null;
		BigDecimal schPaid = null;
		BigDecimal totalPayment = null;
		BigDecimal totalPaid = null;
		BigDecimal diff = null;
		BigDecimal totalUnpaid = null;
		BigDecimal totAr = null;

		OffenderDeductions offDeduts = new OffenderDeductions();

	//	ocupayplRepository.chkRecursive(offenderId, informationNumber, groupId);
		
		paymentSchedulesService.chkRecursive(offenderId, informationNumber, groupId);

		
		totalSch = ocupayplRepository.getPaymentAmount(offenderId, informationNumber, groupId);
		schPaid = ocupayplRepository.getPaidAmount(offenderId, informationNumber, groupId);
		offDeduts = ocupayplRepository.getTotDedAmount(offenderId, informationNumber, groupId);
		
		totalPayment = offDeduts.getMaxTotalAmount();
		totalPaid = offDeduts.getDeductionAmount();
               
		if(paymentPlanId != 0 ) {
			totAr = ocupayplRepository.getTotalArrears(offenderId, informationNumber, groupId);
               }
		else
		{
			totAr = ocupayplRepository.getTotalArrears(offenderId, parentInfoId, groupId);
		}
		
		diff = totalPayment.subtract(totalPaid.max(totalSch.add(schPaid)));
		totalUnpaid = totalPayment.subtract(totalPaid);
		amounts.setGroupDifference(diff);
		amounts.setGroupUnpaidAmount(totalUnpaid);
		amounts.setOffenderId(offenderId);
		amounts.setInformationNumber(informationNumber);
		amounts.setGroupId(groupId);
        amounts.setTotalArrears(totAr);
		return amounts;

	}

	private BigDecimal calculatePaymentAmount(final OffenderPaymentPlan searchBean) throws ParseException {
		int dayOfWeek = 0;
		BigDecimal startDay = new BigDecimal(0);
		Date firstDate = new Date();
		final Boolean bFlag = false;
		String endDate = null;
		String startDate = null;
		Date strDate = null;
		//Double lvNoPayment = null;
		BigDecimal eachPaymt = new BigDecimal(ZERO);
		BigDecimal lvAmount = new BigDecimal(ZERO);
	//	lvAmount = searchBean.getGroupDifference();
		lvAmount = searchBean.getAmount();
		BigDecimal nvl = new BigDecimal(ZERO);
		final SimpleDateFormat dateform = new SimpleDateFormat(DATEFORMAT,Locale.ENGLISH);
		final DateFormat dayFormat = new SimpleDateFormat(DATEFORMAT,Locale.ENGLISH);
		final SimpleDateFormat day = new SimpleDateFormat(DATFORMAT,Locale.ENGLISH);

		if ("MONTHLY".equals(searchBean.getFrequency())) {
			endDate = new SimpleDateFormat(DATEFORMAT,Locale.ENGLISH).format(searchBean.getEndDate());
			startDate = dateform.format(searchBean.getStartDate());
			strDate = dayFormat.parse(startDate);

			final String dateIfo = dayFormat.format(strDate);
			final Calendar cal = Calendar.getInstance();
			cal.setTime(searchBean.getStartDate());

			final String paymemtDate = day.format(searchBean.getStartDate());
			dayOfWeek = ocupayplRepository.getDayOfMonth(paymemtDate);

			startDay = BigDecimal.valueOf(dayOfWeek);
		//	firstDate = ocupayplRepository.getDateValidated(dateIfo, searchBean.getMonthly(), bFlag);
			firstDate=	paymentSchedulesService.getDateValidated(dateIfo, searchBean.getMonthly(), bFlag);
			if (searchBean.getMonthly().compareTo(startDay) < 0) {
				cal.setTime(firstDate);
				cal.add(Calendar.MONTH, 1);
				firstDate = cal.getTime();
			}

			final String firstDates = dateform.format(firstDate);

//			long monthsBetween = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(firstDates)),
//					YearMonth.from(LocalDate.parse(endDate)));
			 Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(endDate); 
			 
			long monthsBetween =tagVisitsRepository.getpAge(firstDate, date1);
			
			long months = Math.abs(monthsBetween);

			final Double lvNoPayment = Math.floor(months+1);

			if (lvNoPayment.equals(null)) {
				nvl = new BigDecimal(1);
			} else {
				nvl = new BigDecimal(lvNoPayment);
			}
			eachPaymt = lvAmount !=null?lvAmount.divide(nvl, MathContext.DECIMAL128):null;

		}
		eachPaymt=eachPaymt !=null?eachPaymt:new BigDecimal(ZERO);
		
		return eachPaymt;

//		if (eachPaymt.equals (null)) {
//			return new BigDecimal(ZERO);
//
//		} else {
//			return eachPaymt;
//		}

	}

	@Override
	public OffenderPaymentPlan whenNewRecordInsatnce(final OffenderPaymentPlan searchBean) {
		final OffenderPaymentPlan jsPlan = new OffenderPaymentPlan();
		String jsFlag = null;

		jsFlag = getJSStatus(searchBean.getOffenderId(), searchBean.getInformationNumber(), searchBean.getGroupId());
		if (jsFlag != null) {
			jsFlag = "Y";
		} else {
			jsFlag = "N";
		}

		jsPlan.setjSFlag(jsFlag);
		jsPlan.setOffenderId(searchBean.getOffenderId());
		jsPlan.setInformationNumber(searchBean.getInformationNumber());
		jsPlan.setGroupId(searchBean.getGroupId());
		jsPlan.setGroupDifference(searchBean.getGroupDifference());
		jsPlan.setGroupUnpaidAmount(searchBean.getGroupUnpaidAmount());
		return jsPlan;
	}

	private String getJSStatus(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId) {
		return ocupayplRepository.getJSStatus(offenderId, informationNumber, groupId);

	}

	@Override
	public OffenderPaymentPlan postBlockPlan(final OffenderPaymentPlan searchBean) {
		final OffenderPaymentPlan offPlan = new OffenderPaymentPlan();
		VOffenderPaymentSchedule shedule = new VOffenderPaymentSchedule();
		final OffenderPaymentPlan payPln = showGroupDiff(searchBean.getOffenderId(), searchBean.getInformationNumber(),
				searchBean.getGroupId(),searchBean.getParentInfoId(),searchBean.getPaymentPlanId() );
		offPlan.setGroupDifference(payPln.getGroupDifference());
		offPlan.setGroupUnpaidAmount(payPln.getGroupUnpaidAmount());
		shedule = ocupayplRepository.postBlockPlan(searchBean);
		offPlan.setTotalArrears(shedule.getTotalArrears());
		offPlan.setGroupId(searchBean.getGroupId());
		offPlan.setOffenderId(searchBean.getOffenderId());
		offPlan.setInformationNumber(searchBean.getInformationNumber());
		offPlan.setPaymentPlanId(searchBean.getPaymentPlanId());
		offPlan.setPaymentPlanSeq(searchBean.getPaymentPlanSeq());
		offPlan.setPaymentClosedDate(searchBean.getPaymentClosedDate());
		offPlan.setStartDate(searchBean.getStartDate());
		offPlan.setEndDate(searchBean.getEndDate());
		return offPlan;
	}

	@Override
	public int whenNewBlockInstanceSch(final long paymentPlanId) {
		return ocupayplRepository.whenNewBlockInstanceSch(paymentPlanId);
	}

	@Override
	public OffenderPaymentPlan whenCheckboxChanged(final OffenderPaymentPlan searchBean) {
		OffenderPaymentPlan returnData = new OffenderPaymentPlan();
		String strDt = null;
		Date currentDate = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		final Calendar nowDate = Calendar.getInstance();
		strDt = formatter.format(nowDate.getTime());
		try {
			currentDate = formatter.parse(strDt);
		} catch (ParseException e1) {
			logger.error("Exception :", e1);
		}

		if ("Y".equals(searchBean.getPaymentClosedFlag())) {

			returnData = purgeClosedSchedule(searchBean.getPaymentPlanId(), searchBean.getPaymentPlanSeq(), searchBean.getModifyUserId());

			try {
//				ocupayplRepository.updateOwingAmount(searchBean.getOffenderId(), searchBean.getInformationNumber(),
//						searchBean.getGroupId());
				
				paymentSchedulesService.updateOwingAmount(searchBean.getOffenderId(), searchBean.getInformationNumber(), searchBean.getGroupId(), searchBean.getCreateUserId());
			} catch (Exception e) {
				returnData.setSealFlag(THREE);// 'Error: When updating owing amount '||sqlerrm
			}
			// commit;
			returnData = purgeClosedPlan(searchBean.getOffenderId(), searchBean.getPaymentPlanId(),
					searchBean.getPaymentPlanSeq(), searchBean.getModifyUserId());
			// commit;
			returnData.setPaymentClosedFlag("N");
			returnData.setPaymentClosedDate(currentDate);
		}

		return returnData;

	}

	private OffenderPaymentPlan purgeClosedPlan(final BigDecimal offenderId, final long paymentPlanId,
			final long paymentPlanSeq, String modifyUserId) {
		String lvEverPaid = null;
		final OffenderPaymentPlan returnData = new OffenderPaymentPlan();
		try {
			lvEverPaid = checkEverPaid(paymentPlanId, paymentPlanSeq);
		} catch (Exception e) {
			returnData.setSealFlag(ONE);// 'Error: When purging closed plan record.'

		}
		if ("N".equals(lvEverPaid)) {
			ocupayplRepository.deletePayPlan(paymentPlanId, paymentPlanSeq, modifyUserId);
		}

		return returnData;
	}

	private OffenderPaymentPlan purgeClosedSchedule(final long paymentPlanId, final long paymentPlanSeq, String modifyUserId) {
		String lvEverPaid = null;
		final OffenderPaymentPlan returnData = new OffenderPaymentPlan();

		try {

			lvEverPaid = checkEverPaid(paymentPlanId, paymentPlanSeq);
		} catch (Exception e) {
			logger.error("Exception :", e);

			returnData.setSealFlag(TWO); // Error: When purging closed schedule records.'
		}
		if ("Y".equals(lvEverPaid)) {
			ocupayplRepository.modifyDelSchdule(paymentPlanId, paymentPlanSeq);
		} else {
			ocupayplRepository.deleteSchdule(paymentPlanId, paymentPlanSeq, modifyUserId);
		}

		return returnData;

	}

	private String checkEverPaid(final long paymentPlanId, final long paymentPlanSeq) {
		String lvEverPaid = null;
		BigDecimal lvSumPaid = null;
		try {
			lvSumPaid = ocupayplRepository.checkEverPaid(paymentPlanId, paymentPlanSeq);

			if (lvSumPaid.compareTo(BigDecimal.ZERO) > 0) {
				lvEverPaid = "Y";
			} else {
				lvEverPaid = "N";
			}

		} catch (Exception e) {
			lvEverPaid = "N";

		}
		return lvEverPaid;
	}

	@Override
	public OffenderPaymentPlan keyCommit(final OffenderPaymentPlan offenderPlan) {
		OffenderPaymentPlan returnData = new OffenderPaymentPlan();
		if (offenderPlan.getGroupDifference() == null
				|| (offenderPlan.getGroupDifference().compareTo(BigDecimal.ZERO) > 0)) {
			returnData = generateSchedules(offenderPlan);
		}
		return returnData;
	}

	private OffenderPaymentPlan generateSchedules(final OffenderPaymentPlan offenderPlan) {
		OffenderPaymentPlan returnData = new OffenderPaymentPlan();

		String recurFlag = null;
		try {
			if ("BUT_RESCH".equals(offenderPlan.getDistribute())) {
//				ocupayplRepository.reschPaymentSchedules(offenderPlan.getPaymentPlanId(),
//						offenderPlan.getPaymentPlanSeq(), false);
				
				paymentSchedulesService.reschPaymentSchedules(offenderPlan.getPaymentPlanId(), offenderPlan.getPaymentPlanSeq(), false, offenderPlan.getCreateUserId());

				returnData.setPaymentPlanId(offenderPlan.getPaymentPlanId());
			} else {
//				ocupayplRepository.setPaymentSchedules(offenderPlan.getPaymentPlanId(),
//						offenderPlan.getPaymentPlanSeq(), false);
				paymentSchedulesService.setPaymentSchedules(offenderPlan.getPaymentPlanId(), offenderPlan.getPaymentPlanSeq(), false, offenderPlan.getCreateUserId());
				returnData.setPaymentPlanId(offenderPlan.getPaymentPlanId());
			}

//			recurFlag = ocupayplRepository.chkRecursive(offenderPlan.getOffenderId(),
//					offenderPlan.getInformationNumber(), offenderPlan.getGroupId());
			
			recurFlag =paymentSchedulesService.chkRecursive(offenderPlan.getOffenderId(), offenderPlan.getInformationNumber(), offenderPlan.getGroupId());

			if (!"Y".equals(recurFlag)) {
				returnData = showGroupDiff(offenderPlan.getOffenderId(), offenderPlan.getInformationNumber(),
						offenderPlan.getGroupId(),offenderPlan.getParentInfoId(),offenderPlan.getPaymentPlanId());
				returnData.setGroupDifference(returnData.getGroupDifference());
				returnData.setGroupUnpaidAmount(returnData.getGroupUnpaidAmount());
				returnData.setPaymentPlanId(offenderPlan.getPaymentPlanId());

				if(offenderPlan.getGroupDifference()!= null) {
				if (offenderPlan.getGroupDifference().abs().compareTo(BigDecimal.ZERO) > 0) {
					if (offenderPlan.getGroupDifference().compareTo(BigDecimal.ZERO) > 0) {
						returnData.setSealFlag(TWO); // 'Do you want to apply the residual amount to the last payment
														// schedule?'
					}

				}
			}

			}
		} catch (Exception e) {
			logger.error("Exception :", e);
			returnData.setSealFlag(THREE); // 'exception: ' || SQLERRM

		}
		return returnData;
	}

	@Override
	public OffenderPaymentPlan keyListVal(final OffenderPaymentPlan payPlan) {
		final OffenderPaymentPlan paymntPlan = new OffenderPaymentPlan();
		OffenderDeductions offDed = new OffenderDeductions();

		final String groupId = ocupayplRepository.payPlnPostQuery(payPlan.getGroupId());

		paymntPlan.setCode(groupId);

		offDed = ocupayplRepository.getTotalAmt(payPlan.getOffenderId(), payPlan.getInformationNumber());
		paymntPlan.setGroupDifference(offDed.getMaxTotalAmount());
		paymntPlan.setGroupUnpaidAmount(offDed.getMaxTotalAmount());
		paymntPlan.setInformationNumber(offDed.getInformationNumber());
		return paymntPlan;

	}

	@Override
	public OffenderPaymentPlan adjustForRoundoffs(final OffenderPaymentPlan offenderPlan) {

//		ocupayplRepository.adjustForRoundoffs(offenderPlan.getOffenderId(), offenderPlan.getInformationNumber(),
//				offenderPlan.getGroupId(), offenderPlan.getPaymentPlanId(), offenderPlan.getPaymentPlanSeq());
		paymentSchedulesService.adjustForRoundoffs(offenderPlan.getOffenderId(),offenderPlan.getPaymentPlanId(), offenderPlan.getPaymentPlanSeq(), offenderPlan.getInformationNumber(), offenderPlan.getGroupId(), offenderPlan.getCreateUserId());
		offenderPlan.setSealFlag(ONE); // success
		offenderPlan.setPaymentPlanId(offenderPlan.getPaymentPlanId());
		offenderPlan.setPaymentPlanSeq(offenderPlan.getPaymentPlanSeq());
		offenderPlan.setOffenderId(offenderPlan.getOffenderId());
		offenderPlan.setGroupId(offenderPlan.getGroupId());
		offenderPlan.setInformationNumber(offenderPlan.getInformationNumber());

		return offenderPlan;
	}

	@Override
	public ReportBean printPlan(final OffenderPaymentPlan searchBean) {
		final Map<String, Object> param = new HashMap<>();
		byte[] returnReport = null;
		final ReportBean rBean = new ReportBean();
		String profileDesc = null;
		if ("COMM".equals(searchBean.getCaseloadType())) {
			profileDesc = ocupayplRepository.getProfileDesc("REP_HDR_COMM");
		} else {
			profileDesc = ocupayplRepository.getProfileDesc("REP_HDR_INST");
		}
		String informNumber = null;
		BigDecimal groupId = null;
		BigDecimal offenderId = null;
		String paymentClosedFlag = null;
		long paymentPlanId = 0;
		long paymentPlanSeq = 0;
		final BigDecimal paidAmount = null;
		final String dollar = "$";
		final List<PaymentPlanReportBean> fields = new ArrayList<>();
		final PaymentPlanReportBean reportBean = new PaymentPlanReportBean();
		final String caseLoaddesc = ocupayplRepository.getCaseLoad(searchBean.getCaseLoadId());
		final VTrustHeader offPlan = ocupayplRepository.offenderDisplay(searchBean);
		informNumber = searchBean.getInformationNumber();
		groupId = searchBean.getGroupId();
		offenderId = searchBean.getOffenderId();
		paymentClosedFlag = searchBean.getPaymentClosedFlag();
		paymentPlanId = searchBean.getPaymentPlanId();
		paymentPlanSeq = searchBean.getPaymentPlanSeq();
	//	final String groupCode = ocupayplRepository.getGroupCode(searchBean.getGroupId());
		
		final String groupCode = paymentSchedulesService.getGroupCode(searchBean.getGroupId());
		
		final OffenderPaymentPlan planSummary = ocupayplRepository.paymtSummary(informNumber, groupId, offenderId);
		if (caseLoaddesc != null) {
			reportBean.setCaseLoadDesc(caseLoaddesc);
		}
		if (profileDesc != null) {
			reportBean.setProfileDesc(profileDesc);
		}
		if (offPlan != null) {
			reportBean.setfOffenderId(offPlan.getOffenderIdDisplay());
		}
		if (offPlan != null) {
			reportBean.setfOffName(offPlan.getName());
		}
		if (searchBean != null) {
			reportBean.setUserId(searchBean.getId());
		}
		if (planSummary != null) {
			reportBean.setfCaseNumber(planSummary.getInformationNumber());
			reportBean.setfStartDate(planSummary.getStartDate());
			reportBean.setfEndDate(planSummary.getEndDate());
			if (planSummary.getPaymentAmount() != null) {
				
				if(planSummary.getPaymentAmount().compareTo(java.math.BigDecimal.ZERO) >=0) {
					
					reportBean.setfTotalDue(dollar.concat(planSummary.getPaymentAmount().setScale(2, java.math.BigDecimal.ROUND_HALF_UP).toPlainString()));
					
				}
				
			
				
			}
            
			reportBean.setfGroupCode(groupCode);

		}
		List<PaymentPlanBean> gridList = ocupayplRepository.schedulePymt(informNumber, groupId, paymentClosedFlag,
				paymentPlanId, paymentPlanSeq, paidAmount);
		if (!gridList.isEmpty()) {
			
			for(final PaymentPlanBean  planList :gridList) {
				if(planList.getAmountDues().compareTo(java.math.BigDecimal.ZERO) >=0) {
					planList.setAmountDue(dollar.concat(planList.getAmountDues().abs().setScale( 2, java.math.BigDecimal.ROUND_HALF_UP ).toPlainString()));
				}else {


				}
				
			}
			
			reportBean.setList(gridList);
		}
		fields.add(reportBean);
		returnReport = generateReport("OCRPPLAN", param, fields);
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
			throw new RuntimeException("Exception in generateReport : " + e.getMessage());
		}
		return returnReport;
	}

	public Long gettingGroupId(BigDecimal offenderId, String groupId, String caseLoadId) {

		return ocupayplRepository.gettingGroupId(offenderId, groupId,caseLoadId);
	}

}
