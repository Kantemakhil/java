package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.offendertransactions.OcudpdisRepository;
import net.syscon.s4.cf.offendertransactions.OcudpdisService;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.pkgs.financial.impl.FinancialServiceImpl;

/**
 * Class OcudpdisServiceImpl
 */
@Service
public class OcudpdisServiceImpl extends BaseBusiness implements OcudpdisService {

	@Autowired
	private OcudpdisRepository ocudpdisRepository;
	@Autowired
	private EliteDateService dateService;

	@Autowired
	private FinancialServiceImpl financialServiceImpl;
	/**
	 * Insert the records into database table
	 *
	 * @param commitBean
	 *
	 * @throws SQLException
	 */
	@Transactional(rollbackFor = Exception.class)
	public OffenderTransactions tbdCommit(final OffenderDeductionsCommitBean commitBean) {
		final Map<String, Object> returnMap = new HashMap<>();
		int liReturn = 0;
		final OffenderTransactions returnObject = new OffenderTransactions();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final OffenderDeductions obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());

				if (obj.getOffenderId() != null) {
					final OffenderTransactions objTrans = ocudpdisRepository.getSubAccTypeDetails(obj);
					if (objTrans != null && objTrans.getSubAccountType() != null) {
						objTrans.setTxnId(ocudpdisRepository.preInsert());
						returnObject.setTxnId(objTrans.getTxnId());
						objTrans.setOffenderId(obj.getOffenderId());
						objTrans.setCaseloadId(obj.getCaseloadId());
						objTrans.setAmount(obj.getAmount());
						returnMap.put("txnId", objTrans.getTxnId());
						objTrans.setOffenderBookId(ocudpdisRepository.getOffenderBookID(obj));
						objTrans.setTxnEntrySeq(1);
						final Date transDate = trunc(dateService.getDBTime());
						objTrans.setTxnEntryDate(transDate);
						objTrans.setTxnType(obj.getTxnType());
						objTrans.setInfoNumber(obj.getInformationNumber());
						objTrans.setCreateUserId(commitBean.getCreateUserId());
						//liReturn = ocudpdisRepository.tbdDetailsComitt(objTrans);
						
				
						try {
							financialServiceImpl.doDeductionsFinancial(objTrans.getCaseloadId(),objTrans.getOffenderId(),objTrans.getOffenderBookId(),objTrans.getTxnType(),
									objTrans.getTxnId().longValue(),objTrans.getTxnEntryDate(),objTrans.getSubAccountType(),"Y" , objTrans.getAmount(),null,
									null , objTrans.getTxnEntrySeq(),objTrans.getCreateUserId());
							liReturn = 1;
						} catch (Exception e) {
							// TODO: handle exception
							liReturn = 0;
						}
						
						if (liReturn != 1) {
							returnObject.setSealFlag("2");
							return returnObject;
						}

					} else {
						returnObject.setSealFlag("1");
						return returnObject;
					}
				}

			}

		}

		return returnObject;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public OffenderDeductions tbdExecuteQuery(final OffenderDeductions paramBean) {
		List<OffenderDeductions> offDeductionList;
		BigDecimal balance;
		offDeductionList = ocudpdisRepository.offTransTypeExecuteQuery(paramBean);
		if (offDeductionList != null && !offDeductionList.isEmpty()) {
			paramBean.setTxnType(offDeductionList.get(0).getTxnType());
			balance = ocudpdisRepository.offAccBalExecuteQuery(paramBean);
			paramBean.setAmount(balance);
			paramBean.setTxnDescription(offDeductionList.get(0).getTxnDescription());
		}

		return paramBean;
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);

			return calender.getTime();
		}
		return null;
	}

}
