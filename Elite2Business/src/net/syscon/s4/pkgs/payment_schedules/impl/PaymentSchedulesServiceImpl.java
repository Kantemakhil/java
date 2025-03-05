package net.syscon.s4.pkgs.payment_schedules.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.offendertransactions.beans.OffenderPaymentPlan;
import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.pkgs.payment_schedules.PaymentSchedulesRepository;
import net.syscon.s4.pkgs.payment_schedules.PaymentSchedulesService;

@Service
public class PaymentSchedulesServiceImpl extends BaseBusiness implements PaymentSchedulesService {

	private static Logger logger = LogManager.getLogger(PaymentSchedulesServiceImpl.class.getName());
	@Autowired
	private PaymentSchedulesRepository paymentSchedulesRepository;

	private static final String Y = "Y";
	private static final String NORM = "NORM";
	private static final String VCP_SWITCH_AMOUNT = "NO_AMOUNT";
	private static final String VCP_FREQ_MONTH = "MONTHLY";
	private static final String VCP_FREQ_WEEK = "WEEKLY";
	private static final String VCP_FREQ_BIWEEK = "BIWEEKLY";
	private static final String VCP_FREQ_BIMONTH = "TWICEMONTHLY";
	private static final String VCP_FREQ_LUMPSUM = "LUMPSUM";
	private static final String VCP_SWITCH_ENDDATE = "NO_ENDDATE";
	private static final String VCP_SWITCH_NORM = "NORM";
	private static final String VCP_LENIENCY_TYPE = "CLIENT";
	private static final String VCP_LENIENCY_CODE = "LENIENCY";

	@Override
	public String getGroupCode(final BigDecimal groupId) {
		return paymentSchedulesRepository.getGroupCode(groupId);
	}

	@Override
	public String chkRecursive(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId) {
		return paymentSchedulesRepository.chkRecursive(offenderId, informationNumber, groupId);
	}

	@Override
	public BigDecimal getRecursiveAmt(final BigDecimal offenderId, final String informationNumber,
			final BigDecimal groupId) {
		return paymentSchedulesRepository.getRecursiveAmt(offenderId, informationNumber, groupId);
	}

	@Override
	public void updateOwingAmount(final BigDecimal offenderId, final String informationNumber, final BigDecimal groupId,
			final String userName) {
		Integer totalPayment = null, totalPaid = null, owingAmount = null;
		try {
			final List<OffenderDeductions> list = paymentSchedulesRepository.paymentSchedulesSelect(offenderId, informationNumber,
					groupId);
			for (final OffenderDeductions obj : list) {
				totalPayment = obj.getMaxTotalAmount().intValue();
				totalPaid = obj.getDeductionAmount().intValue();
				owingAmount = totalPayment - totalPaid;
			}
			paymentSchedulesRepository.offenderPaymentPlansUpdate(offenderId, informationNumber, groupId, owingAmount,
					userName);
		} catch (Exception e) {
			logger.error("updateOwingAmount :", e);
		}
	}

	@Override
	public Boolean adjustForRoundoffs(final BigDecimal niOffenderId, final Long niPlanId, final Long niPlanSeq,
			final String viInfo, final BigDecimal niGrpId, final String userName) {
		Boolean flag = false;
		try {
			BigDecimal nTotalDiff;
			BigDecimal nMaxTotalAmount;
			final BigDecimal nFixedAmt = null;
			Long noMonths = null;
			BigDecimal noTotalAmountSum = null;
			BigDecimal noRecurAmountSum = null;
			BigDecimal noPaidTotalSum = null;
			BigDecimal noPaidRecurSum = null;
			final BigDecimal maxTotalAmtNoPayment = null;
			String recurFlag = null;
			BigDecimal nTotalPaid = null;
			OffenderPaymentPlan rPln;
			Map<String, Object> getPlnSummary = new HashMap<String, Object>();
			rPln = getPaymentPlans(niPlanId, niPlanSeq);
			Map<String, Object> getOrgAmOwing = getOrgAmountOwing(rPln.getOffenderId(), rPln.getInformationNumber(),
					rPln.getGroupId());
			nMaxTotalAmount = (BigDecimal) getOrgAmOwing.get("NO_TOTAL_MAX");
			nTotalPaid = (BigDecimal) getOrgAmOwing.get("NO_PAID");

			getPlnSummary = getPlanSummary(niPlanId);

			noMonths = (Long) getPlnSummary.get("NO_MONTHS");
			noTotalAmountSum = (BigDecimal) getPlnSummary.get("NO_TOTAL_AMOUNT_SUM");
			noRecurAmountSum = (BigDecimal) getPlnSummary.get("NO_RECUR_AMOUNT_SUM");
			noPaidTotalSum = (BigDecimal) getPlnSummary.get("NO_PAID_TOTAL_SUM");
			noPaidRecurSum = (BigDecimal) getPlnSummary.get("NO_PAID_RECUR_SUM");

			recurFlag = chkRecursive(rPln.getOffenderId(), rPln.getInformationNumber(), rPln.getGroupId());
			nMaxTotalAmount = paymentSchedulesRepository.getNMaxTotalAmount(rPln.getOffenderId(),
					rPln.getInformationNumber(), rPln.getGroupId());
			nTotalDiff = nMaxTotalAmount.subtract(noTotalAmountSum);

			paymentSchedulesRepository.updateOffPaymentSchedule(nTotalDiff, niPlanId, niPlanSeq, userName);
			deleteDummySchedules(niPlanId, niPlanSeq,userName);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public OffenderPaymentPlan getPaymentPlans(final Long niPlanId, final Long niPlanSeq) {
		List<OffenderPaymentPlan> list = paymentSchedulesRepository.fetOffPayPlan(niPlanId, niPlanSeq);
		if (list != null && !list.isEmpty())
			return list.get(0);
		else
			return new OffenderPaymentPlan();
	}

	@Override
	public Map<String, Object> getOrgAmountOwing(BigDecimal niOffenderId, String viInfo, BigDecimal niGrpId) {
		BigDecimal noTotalMax = null;
		BigDecimal noPaid = null;
		final Map<String, Object> returnMap = new HashMap<String, Object>();

		final List<OffenderDeductions> dedCur = paymentSchedulesRepository.fetDedCur(niOffenderId, viInfo, niGrpId);
		for(OffenderDeductions obj :dedCur) {
			noTotalMax = obj.getMaxTotalAmount();
			noPaid = obj.getDeductionAmount();
		}

		returnMap.put("NO_TOTAL_MAX", noTotalMax);
		returnMap.put("NO_PAID", noPaid);
		return returnMap;
	}

	@Override
	public Map<String, Object> getPlanSummary(final Long niPlanId) {
		Long noMonths;
		BigDecimal noTotalAmountSum;
		BigDecimal noRecurAmountSum;
		BigDecimal noPaidTotalSum;
		BigDecimal noPaidRecurSum;
		Date dFirstDate;
		Date dLastDate;
		final Map<String, Object> retValues = new HashMap<String, Object>();
		final List<OffenderPaymentSchedules> schCur = paymentSchedulesRepository.fetSchCur(niPlanId);

		noTotalAmountSum = schCur.get(0).getPaymentAmount();
		noRecurAmountSum = schCur.get(0).getRecursiveAmount();
		noPaidTotalSum = schCur.get(0).getPaidAmount();
		noPaidRecurSum = schCur.get(0).getPaidRecursiveAmount();
		dFirstDate = schCur.get(0).getMinPaymentDate();
		dLastDate = schCur.get(0).getMaxPaymentDate();

		if (schCur == null) {
			noMonths = 0l;
			noTotalAmountSum = BigDecimal.valueOf(0);
			noRecurAmountSum = BigDecimal.valueOf(0);
			noPaidTotalSum = BigDecimal.valueOf(0);
			noPaidRecurSum = BigDecimal.valueOf(0);
		} else {
			noMonths = paymentSchedulesRepository.getNoMonth(dLastDate, dFirstDate);
		}
		retValues.put("NO_MONTHS", noMonths);
		retValues.put("NO_TOTAL_AMOUNT_SUM", noTotalAmountSum);
		retValues.put("NO_RECUR_AMOUNT_SUM", noRecurAmountSum);
		retValues.put("NO_PAID_TOTAL_SUM", noPaidTotalSum);
		retValues.put("NO_PAID_RECUR_SUM", noPaidRecurSum);
		return retValues;
	}

	@Override
	public void deleteDummySchedules(final Long niPlanId, final Long niPlanSeq,String modifyUserId) {
		paymentSchedulesRepository.deteleOffPaymentSchedule(niPlanId, niPlanSeq,modifyUserId);
	}

	@Override
	public void setPaymentSchedules(long paymentPlanId, long paymentPlanSeq, Boolean biAutoAdjust,
			final String userName) {
		OffenderPaymentPlan rPln;
		BigDecimal nMaxTotalAmount;
		final BigDecimal nMaxRecursiveAmount = null;
		BigDecimal nTotalPaid;
		Long noMonths;
		BigDecimal noTotalAmountSum = null;
		BigDecimal noRecurAmountSum = null;
		BigDecimal noPaidTotalSum = null;
		BigDecimal noPaidRecurSum = null;
		BigDecimal orgDiff;
		Date dDate = null;
		BigDecimal schDiff;
		final Date dDate2 = null;
		String recurFlag;
		final Boolean bFlag = false;
		final String vWeekday = null;
		final Long nToggle2 = 1l;
		Long nPeriod;
		BigDecimal nFixedAmt = BigDecimal.ZERO;
		final String vSwitch = NORM;
		rPln = getPaymentPlans(paymentPlanId, paymentPlanSeq);
		try {
			Map<String, Object> map = getOrgAmountOwing(rPln.getOffenderId(), rPln.getInformationNumber(),
					rPln.getGroupId());
			nMaxTotalAmount = (BigDecimal) map.get("NO_TOTAL_MAX");
			nTotalPaid = (BigDecimal) map.get("NO_PAID");

			final Map<String, Object> summaryMap = getPlanSummary(paymentPlanId);

			noMonths = (Long) summaryMap.get("NO_MONTHS");
			noTotalAmountSum = (BigDecimal) summaryMap.get("NO_TOTAL_AMOUNT_SUM");
			noRecurAmountSum = (BigDecimal) summaryMap.get("NO_RECUR_AMOUNT_SUM");
			noPaidTotalSum = (BigDecimal) summaryMap.get("NO_PAID_TOTAL_SUM");
			noPaidRecurSum = (BigDecimal) summaryMap.get("NO_PAID_RECUR_SUM");

			recurFlag = chkRecursive(rPln.getOffenderId(), rPln.getInformationNumber(), rPln.getGroupId());
			if (recurFlag != Y) {
				updateOwingAmount(rPln.getOffenderId(), rPln.getInformationNumber(), rPln.getGroupId(), userName);
			}

			orgDiff = nMaxTotalAmount.subtract(nTotalPaid);
			schDiff = nMaxTotalAmount.subtract(noTotalAmountSum);

			if (orgDiff.intValue() > schDiff.intValue()) {
				nMaxTotalAmount = schDiff;
			} else {
				nMaxTotalAmount = orgDiff;
			}

			if (nMaxTotalAmount.intValue() > 0 || Y.equals(recurFlag)) {
				if (VCP_SWITCH_AMOUNT.equals(vSwitch)) {
					nPeriod = numberOfPayments(rPln);
					nFixedAmt = BigDecimal.valueOf(nMaxTotalAmount.longValue() / nPeriod);
				} else if (VCP_SWITCH_ENDDATE.equals(vSwitch) || VCP_SWITCH_NORM.equals(vSwitch)) {
					nFixedAmt = rPln.getAmount().subtract(nMaxRecursiveAmount!=null?nMaxRecursiveAmount:BigDecimal.ZERO);
				}

				Map<String, Object> datemap = mgetFirstDate(dDate, rPln, vWeekday, dDate2, nToggle2, bFlag);
				dDate = (Date) datemap.get("DIO_DATE");
				if (VCP_SWITCH_ENDDATE.equals(vSwitch)) {
					if (nFixedAmt.intValue() == 0) {
						throw new ArithmeticException("End date must be specified for recursive obligations. ");
					}
					rPln.setEndDate(dDate);
				}

				if (nFixedAmt.intValue() > nMaxTotalAmount.intValue()) {
					nFixedAmt = nMaxTotalAmount;
				}

				while ((dDate.compareTo(rPln.getEndDate()) <= 0)
						&& (nMaxTotalAmount.intValue() >= nFixedAmt.intValue())) {
			//		Long payIdSeq=paymentSchedulesRepository.paymentPlanSeq(paymentPlanId);
					insertPaymentSchedules(paymentPlanId, paymentPlanSeq, rPln.getInformationNumber(),
							rPln.getGroupId(), rPln.getOffenderDeductionId(), dDate, nFixedAmt, userName);
					Map<String, Object> nextDate =mgetNextDate(dDate, rPln, vWeekday, dDate2, nToggle2, bFlag, biAutoAdjust);
					dDate=(Date) nextDate.get("DIO_DATE");

					if (VCP_SWITCH_NORM.equals(vSwitch) || VCP_SWITCH_AMOUNT.equals(vSwitch)) {
						nMaxTotalAmount = nMaxTotalAmount.subtract(nFixedAmt);
					}
					if (VCP_SWITCH_ENDDATE.equals(vSwitch)) {
						if (nMaxTotalAmount.intValue() > 0) {
							rPln.setEndDate(dDate);
						} else {
							setPlanEnddate(paymentPlanId, paymentPlanSeq, rPln.getEndDate(), userName);
						}
					} 
				} // end loop
				if (Y.equals(recurFlag)) {
					setRecurAmts(paymentPlanId, paymentPlanSeq, rPln.getStartDate(), rPln.getEndDate(),
							rPln.getAmount(), userName);
				}
				deleteDummySchedules(paymentPlanId, paymentPlanSeq,userName);
				setLeniencyFlag(rPln, userName);
				if (biAutoAdjust) {
					adjustForRoundoffs(rPln.getOffenderId(), paymentPlanId, paymentPlanSeq, rPln.getInformationNumber(),
							rPln.getGroupId(), userName);
				}
			}
		} catch (Exception e) {
			logger.error("setPaymentSchedules :" + e);
		}

	}

	@Override
	public Long numberOfPayments(final OffenderPaymentPlan riPln) {
		final Boolean bFlag = null;
		final Date dDate = null;
		final Date dDate2 = null;
		final String vWeekday = null;
		final Long nToggle2 = 1l;
		Long nPeriod = 0l;
		mgetNextDate(dDate, riPln, vWeekday, dDate2, nToggle2, bFlag, false);
		while (dDate.compareTo(riPln.getEndDate()) < 0) {
			nPeriod = nPeriod + 1l;
			mgetNextDate(dDate, riPln, vWeekday, dDate2, nToggle2, bFlag, false);
		}
		return nPeriod;
	}

	@Override
	public Map<String, Object> mgetNextDate(Date dioDate, OffenderPaymentPlan riPln, String viWeekday, Date dioDate2,
			Long nioToggle2, Boolean bioFlag, Boolean biExtend) {
		Date dNextDate2;
		final Map<String, Object> map = new HashMap<String, Object>();
		if (dioDate.compareTo(riPln.getStartDate()) < 0) {
			dioDate = riPln.getStartDate();
		}

		if (biExtend == true) {
			dioDate = riPln.getEndDate();
		}
		try {
			if (VCP_FREQ_MONTH.equals(riPln.getFrequency())) {
				final Date dioDate1 = dioDate;
				final Calendar cal = Calendar.getInstance();
				cal.setTime(dioDate1);
				cal.add(Calendar.MONTH, 1);
				dioDate = cal.getTime();

				final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				final String strBioDate = dateFormat.format(dioDate);

				dioDate = getDateValidated(strBioDate, riPln.getMonthly(), bioFlag);
			} else if (VCP_FREQ_WEEK.equals(riPln.getFrequency())) {

				final Date dioDate1 = dioDate;
				final Calendar cal = Calendar.getInstance();
				cal.setTime(dioDate1);
				cal.add(Calendar.DATE, Integer.parseInt(viWeekday));
				dioDate = cal.getTime();
			} else if (VCP_FREQ_BIWEEK.equals(riPln.getFrequency())) {
				final Date date1 = dioDate;
				final Calendar cal = Calendar.getInstance();
				cal.setTime(date1);
				cal.add(Calendar.DATE, Integer.parseInt(viWeekday));
				final Date date2 = cal.getTime();
				cal.setTime(date2);
				cal.add(Calendar.DATE, Integer.parseInt(viWeekday));
				dioDate = cal.getTime();

			} else if (VCP_FREQ_BIMONTH.equals(riPln.getFrequency())) {
				final Date dioDate1 = dioDate;
				final Calendar cal = Calendar.getInstance();
				cal.setTime(dioDate1);
				cal.add(Calendar.MONTH, 1);
				dNextDate2 = cal.getTime();
				final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				final String strBioDate = dateFormat.format(dNextDate2);
				if (nioToggle2 == 1) {
					dNextDate2 = getDateValidated(strBioDate, riPln.getTwiceMonthly1(), bioFlag);
					nioToggle2 = 2l;
				} else {
					dNextDate2 = getDateValidated(strBioDate, riPln.getTwiceMonthly2(), bioFlag);
					nioToggle2 = 1l;
				}
				dioDate = dioDate2;
				dioDate2 = dNextDate2;
			} else if (VCP_FREQ_LUMPSUM.equals(riPln.getFrequency())) {
				final Calendar cal = Calendar.getInstance();
				cal.setTime(riPln.getEndDate());
				cal.add(Calendar.DATE, 1);
				dioDate = cal.getTime();
			}

			if (dioDate.compareTo(riPln.getEndDate()) > 0) {
				bioFlag = true;
			} else {
				bioFlag = false;
			}

			map.put("DIO_DATE", dioDate);
			map.put("DIO_DATE2", dioDate2);
			map.put("NIO_TOGGLE2", nioToggle2);
			map.put("BIO_FLAG", bioFlag);
		} catch (Exception e) {

		}
		return map;
	}

	@Override
	public Date getDateValidated(final String startDate, final BigDecimal monthly, final Boolean bFlag)
			throws ParseException {
		Date dDateReturn;
		Integer vYear;
		Integer nMonth;
		final Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		final Calendar cal = Calendar.getInstance();
		 cal.setTime(date1);
		 String substring = startDate.substring(5, 7);
		 nMonth = Integer.parseInt(substring);
	//	nMonth = cal.get(Calendar.MONTH);
		if ((nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11) && monthly.intValue() == 31) {
			dDateReturn = paymentSchedulesRepository.getLastDay(date1);
		} else if (nMonth == 2 && monthly.intValue() > 28) {
			dDateReturn = paymentSchedulesRepository.getLastDay(date1);
		} else {
			vYear = cal.get(Calendar.YEAR);
			dDateReturn = paymentSchedulesRepository.getDateValidateddDateReturn(nMonth, monthly, vYear);
		}
		return dDateReturn;
	}

	@Override
	public void insertPaymentSchedules(final Long niPlanId, final Long niPlanSeq, final String viInfo,
			final BigDecimal niGrpId, final BigDecimal niDedId, final Date diDate, final BigDecimal niFixedAmt,
			final String userName) {
		try {
			paymentSchedulesRepository.insertPaymentSchedules(niPlanId, niPlanSeq, niGrpId, niDedId, diDate, niFixedAmt,
					userName);
		} catch (Exception e) {
			logger.error("insertPaymentSchedules :" + e);
		}

	}

	@Override
	public void setPlanEnddate(final Long niPlanId, final Long niPlanSeq, final Date diEndDate, final String userName) {
		paymentSchedulesRepository.setPlanEnddate(niPlanId, niPlanSeq, diEndDate, userName);
	}

	@Override
	public void setRecurAmts(final Long niPlanId, final Long niPlanSeq, final Date diPlnStart, final Date diPlnEnd,
			final BigDecimal niRecurAmt, final String userName) {
		paymentSchedulesRepository.setRecurAmts(niPlanId, niPlanSeq, niRecurAmt, userName);
		paymentSchedulesRepository.setRecurAmtsOne(niPlanId, niPlanSeq, userName);
	}

	@Override
	public void setLeniencyFlag(final OffenderPaymentPlan riPln, final String userName) {
		final Long nLeniency = (long) toNumber(getSystemProfile(VCP_LENIENCY_TYPE, VCP_LENIENCY_CODE, 1));
		Date dLastDate = paymentSchedulesRepository.setLeniencyFlagGetPaymentDate(riPln.getPaymentPlanId(),
				riPln.getPaymentPlanSeq());
		Date dEndDate = paymentSchedulesRepository.setLeniencyFlagGetEndDate(riPln.getPaymentPlanId(),
				riPln.getPaymentPlanSeq());
		final Long dateDiff = find(dEndDate, dLastDate);

		if (dateDiff >= nLeniency) {
			paymentSchedulesRepository.setLeniencyFlagUpdate(riPln.getPaymentPlanId(), userName);
		}
	}

	Long find(final Date dEndDate, final Date dLastDate) {
		Long dateDiff = 0l;
		// Create an instance of the SimpleDateFormat class
		final SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		// Use parse method to get date object of both dates
		final Date date1 = dEndDate;
		final Date date2 = dLastDate;
		// Calculate time difference in milliseconds
		final Long time_difference = date2.getTime() - date1.getTime();
		// Calculate time difference in days
		final Long days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;
		// Calculate time difference in years
		final Long years_difference = (time_difference / (1000l * 60 * 60 * 24 * 365));
		// Calculate time difference in seconds
		final Long seconds_difference = (time_difference / 1000) % 60;
		// Calculate time difference in minutes
		final Long minutes_difference = (time_difference / (1000 * 60)) % 60;

		// Calculate time difference in hours
		final Long hours_difference = (time_difference / (1000 * 60 * 60)) % 24;
		// Show difference in years, in days, hours, minutes, and seconds
		dateDiff = (years_difference * 365) + days_difference;
		return dateDiff;
	}

	@Override
	public String getSystemProfile(final String viProfileType, final String viProfileCode,
			final Integer niProfileValueInd) {
		final List<SystemProfiles> obj = paymentSchedulesRepository.getSystemProfile(viProfileType, viProfileCode);
		final String vProfileValue = obj.get(0).getProfileValue();
		final String vProfileValue2 = obj.get(0).getProfileValue2();
		if (niProfileValueInd == 1) {
			return vProfileValue;
		} else if (niProfileValueInd == 2) {
			return vProfileValue2;
		} else {
			return null;
		}
	}

	@Override
	public String getWeekday(final String viWkCode) {
		return paymentSchedulesRepository.getWeekday(viWkCode);
	}

	@Override
	public Map<String, Object> mgetFirstDate(Date dioDate, OffenderPaymentPlan riPln, String vioWeekday, Date dioDate2,
			Long nioToggle2, Boolean bioFlag) {

		Integer nDay;
		Integer vMonth;
		Integer vYear;
		Date dDateSwap;
		Map<String, Object> map = new HashMap<String, Object>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(riPln.getStartDate());
		nDay = cal.get(Calendar.DATE);
		vMonth = cal.get(Calendar.MONTH);
		vYear = cal.get(Calendar.YEAR);
		try {
			if (VCP_FREQ_MONTH.equals(riPln.getFrequency())) {
				String strBioDate = dateFormat.format(riPln.getStartDate());
				dioDate = getDateValidated(strBioDate, riPln.getMonthly(), bioFlag);
				if (nDay > riPln.getMonthly().intValue()) {
					cal.setTime(dioDate);
					cal.add(Calendar.MONTH, 1);
					dioDate = cal.getTime();
					final String strBioDate1 = dateFormat.format(dioDate);
					dioDate = getDateValidated(strBioDate1, riPln.getMonthly(), bioFlag);

				} else if (VCP_FREQ_WEEK.equals(riPln.getFrequency())) {
					vioWeekday = getWeekday(riPln.getWeekly());
					cal.setTime(riPln.getStartDate());
					cal.add(Calendar.DATE, Integer.parseInt(vioWeekday));
					dioDate = cal.getTime();

					final Long diff = find(dioDate, riPln.getStartDate());
					if (diff == 7) {
						dioDate = riPln.getStartDate();
					}
				} else if (VCP_FREQ_BIWEEK.equals(riPln.getFrequency())) {
					vioWeekday = getWeekday(riPln.getBiWeekly());
					cal.setTime(riPln.getStartDate());
					cal.add(Calendar.DATE, Integer.parseInt(vioWeekday));
					dioDate = cal.getTime();
					final Long diff = find(dioDate, riPln.getStartDate());
					if (diff == 7) {
						dioDate = riPln.getStartDate();
					}
				} else if (VCP_FREQ_BIMONTH.equals(riPln.getFrequency())) {
					strBioDate = dateFormat.format(riPln.getStartDate());
					dioDate = getDateValidated(strBioDate, riPln.getTwiceMonthly1(), bioFlag);
					dioDate2 = getDateValidated(strBioDate, riPln.getTwiceMonthly2(), bioFlag);
					if (nDay > riPln.getTwiceMonthly1().intValue()) {
						cal.setTime(dioDate);
						cal.add(Calendar.MONTH, 1);
						dioDate = cal.getTime();
						strBioDate = dateFormat.format(dioDate);
						dioDate = getDateValidated(strBioDate, riPln.getTwiceMonthly1(), bioFlag);
					}
					if (nDay > riPln.getTwiceMonthly2().intValue()) {
						cal.setTime(dioDate2);
						cal.add(Calendar.MONTH, 1);
						dioDate2 = cal.getTime();
						strBioDate = dateFormat.format(dioDate2);
						dioDate2 = getDateValidated(strBioDate, riPln.getTwiceMonthly2(), bioFlag);
					}
					if (dioDate.compareTo(dioDate2) > 0) {
						dDateSwap = dioDate;
						dioDate = dioDate2;
						dioDate2 = dDateSwap;
						nioToggle2 = 2l;
					}

				} else if (VCP_FREQ_LUMPSUM.equals(riPln.getFrequency())) {
					dioDate = riPln.getStartDate();
				}
			} // end if
			if (dioDate.compareTo(riPln.getEndDate()) > 0) {
				bioFlag = true;
			} else {
				bioFlag = false;
			}
			map.put("DIO_DATE", dioDate);
			map.put("VIO_WEEKDAY", vioWeekday);
			map.put("DIO_DATE2", dioDate2);
			map.put("NIO_TOGGLE2", nioToggle2);
			map.put("BIO_FLAG", bioFlag);

		} catch (Exception e) {

		}
		return map;
	}

	@Override
	public void reschPaymentSchedules(final long paymentPlanId, final long paymentPlanSeq, final Boolean biAutoAdjust,
			final String userName) {
		BigDecimal nMaxTotalAmount;
		BigDecimal nTotalPaid;
		Integer noMonths;
		BigDecimal noTotalAmountSum;
		BigDecimal noRecurAmountSum;
		BigDecimal noPaidTotalSum;
		BigDecimal noPaidRecurSum;
		BigDecimal nFixedAmt;
		BigDecimal maxRecurAmount;
		String recurFlag;
		final String vSwitch = NORM;
		Integer nPeriod;
		final Date dDate = null;
		final OffenderPaymentPlan rPln = getPaymentPlans(paymentPlanId, paymentPlanSeq);
		try {
			Map<String, Object> getOrgAmt = getOrgAmountOwing(rPln.getOffenderId(), rPln.getInformationNumber(),
					rPln.getGroupId());
			nMaxTotalAmount = (BigDecimal) getOrgAmt.get("NO_TOTAL_MAX");
			nTotalPaid = (BigDecimal) getOrgAmt.get("NO_PAID");
			final Map<String, Object> getPlanSummary = getPlanSummary(paymentPlanId);
			noMonths = (Integer) getPlanSummary.get("NO_MONTHS");
			noTotalAmountSum = (BigDecimal) getPlanSummary.get("NO_TOTAL_AMOUNT_SUM");
			noRecurAmountSum = (BigDecimal) getPlanSummary.get("NO_RECUR_AMOUNT_SUM");
			noPaidTotalSum = (BigDecimal) getPlanSummary.get("NO_PAID_TOTAL_SUM");
			noPaidRecurSum = (BigDecimal) getPlanSummary.get("NO_PAID_RECUR_SUM");

			recurFlag = chkRecursive(rPln.getOffenderId(), rPln.getInformationNumber(), rPln.getGroupId());
			List<OffenderPaymentSchedules> paymentSchCurList = paymentSchedulesRepository
					.getPaymentSchCur(paymentPlanId, paymentPlanSeq);
			if (recurFlag != Y) {
				nMaxTotalAmount = nMaxTotalAmount.subtract(noTotalAmountSum);
				nPeriod = numberOfPaymentsLeft(paymentPlanId, paymentPlanSeq);
				if (nMaxTotalAmount.intValue() > 0 && nPeriod > 0) {
					nFixedAmt = nMaxTotalAmount.divide(BigDecimal.valueOf(nPeriod));
				} else {
					nFixedAmt = BigDecimal.valueOf(0);
				}
				if (VCP_SWITCH_ENDDATE.equals(vSwitch)) {
					if (nFixedAmt.intValue() == 0) {
						throw new ArithmeticException("End date must be specified for recursive obligations. ");
					}
					rPln.setEndDate(dDate);
				}

				for (final OffenderPaymentSchedules obj : paymentSchCurList) {
					paymentSchedulesRepository.updateReschPaymentSchedules(obj.getPaymentPlanId(),
							obj.getPaymentPlanSeq(), nFixedAmt, obj.getPaymentDate(), userName);
				}
			} else {
				maxRecurAmount = paymentSchedulesRepository.getMaxRecursiveAmount(rPln.getOffenderId(),
						rPln.getInformationNumber(), rPln.getGroupId());
				paymentSchedulesRepository.updateReschPaymentSchedulesSecond(paymentPlanId, paymentPlanSeq,
						maxRecurAmount, userName);
			}
		} catch (Exception e) {
			logger.error("reschPaymentSchedules :" + e);
		}
	}

	@Override
	public Integer numberOfPaymentsLeft(final Long niPlanId, final Long niPlanSeq) {
		return paymentSchedulesRepository.numberOfPaymentsLeft(niPlanId, niPlanSeq);
	}

}