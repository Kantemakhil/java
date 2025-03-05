package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.offendertransactions.OcdcrefuRepository;
import net.syscon.s4.cf.offendertransactions.OcdcrefuService;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;

/**
 * Class OcdcrefuServiceImpl
 */
@Service
public class OcdcrefuServiceImpl extends BaseBusiness implements OcdcrefuService {
	Logger logger = LogManager.getLogger(OcdcrefuServiceImpl.class);
	@Autowired
	private OcdcrefuRepository ocdcrefuRepository;

	@Autowired
	private EliteDateService eliteDateService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public OffenderTransactions offTxnExecuteQuery(final OffenderTransactions searchRecord) {
		final OffenderTransactions offenderTransactionsModel = new OffenderTransactions();
		try {
			final Double nbtPayment = ocdcrefuRepository.offTxnExecuteQuery(searchRecord);
			offenderTransactionsModel.setNbtOverpaymentAmount(nbtPayment);
		} catch (final Exception e) {
			offenderTransactionsModel.setSealFlag("1");
		}

		try {
			final String reason = ocdcrefuRepository.getDescription();
			offenderTransactionsModel.setTxnEntryDesc(reason);
		} catch (final Exception e) {
			offenderTransactionsModel.setSealFlag("2");
		}
		return offenderTransactionsModel;

	}

	/**
	 * offTxnValidateQuery
	 *
	 * @param searchRecord
	 *
	 */
	@Override
	public OffenderTransactions offTxnValidateQuery(final OffenderTransactions searchBean) {
		final OffenderTransactions offenderTransactionsModel = new OffenderTransactions();
		Double minPayAmount = 0.0;
		OffenderTransactions offenderTransaction = new OffenderTransactions();
		try {
			offenderTransaction = ocdcrefuRepository.offTxnValidateQuery(searchBean);
		} catch (final Exception e) {
			offenderTransactionsModel.setSealFlag("1");
			return offenderTransactionsModel;
		}

		try {
			minPayAmount = ocdcrefuRepository.offTxngetMinPayment(offenderTransaction);
		} catch (final Exception e) {

		}

		if (minPayAmount > searchBean.getTxnEntryAmount()) {
			offenderTransactionsModel.setSealFlag("2");
			offenderTransactionsModel.setAmount(BigDecimal.valueOf(minPayAmount));
			return offenderTransactionsModel;
		}

		if (searchBean.getTxnEntryAmount() > searchBean.getNbtOverpaymentAmount()) {
			offenderTransactionsModel.setSealFlag("3");
			return offenderTransactionsModel;
		} else if (searchBean.getTxnEntryAmount() <= 0) {
			offenderTransactionsModel.setSealFlag("4");
			return offenderTransactionsModel;
		}
		return offenderTransactionsModel;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 */
	@Transactional
	public OffenderTransactions offTxnCommit(OffenderTransactions commitBean) {
		int liReturn = 0;
		Integer txnId = 0;
		// insertRecords
		if (commitBean != null) {
			txnId = ocdcrefuRepository.genTrustTrans("TXN_ID");
			commitBean.setTxnId(txnId);
			liReturn = ocdcrefuRepository.offTxnInsertOffenderTransactions(commitBean);
			if (liReturn == 0) {
				commitBean.setSealFlag("1");
				return commitBean;
			}
			commitBean = postInsert(commitBean);
		}
		return commitBean;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_TXN
	 *
	 * 
	 */
	private OffenderTransactions postInsert(final OffenderTransactions commitBean) {
		TransactionOperation transactionOperation = new TransactionOperation();
		String payeeName;
		final Date newDate = trunc(eliteDateService.getDBTime());
		commitBean.setTxnEntryDate(newDate);
		ocdcrefuRepository.processGlTransNew(commitBean);
		try {

			ocdcrefuRepository.updateOffenderBalance(commitBean, newDate);

		} catch (final Exception e) {
			logger.error("postInsert", e);
		}

		transactionOperation = ocdcrefuRepository.ocdcrefuChkProdflagAcccode(commitBean);// used
																							// for
																							// set
																							// account
																							// code
		commitBean.setCrAccountCode(transactionOperation.getCrAccountCode().intValue());
		if (transactionOperation != null && transactionOperation.getChequeProductionFlag().equals("Y")) {
			payeeName = commitBean.getLastName().concat(commitBean.getFirstName());
			commitBean.setPayeeName(payeeName);
			ocdcrefuRepository.insertIntoChkData(commitBean);

			ocdcrefuRepository.insertBankChequeBeneficiaries(commitBean);

		} else {
			commitBean.setSealFlag("2");
		}

		return commitBean;
	}

	private Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}
}