package net.syscon.s4.inmate.trust.checks.checksmainataince.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.BankChequeBooksCommitBean;
import net.syscon.s4.im.beans.BankChequeRegistersCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmcprinRepository;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmcprinService;

@Service
public class OtmcprinServiceImpl implements OtmcprinService {

	@Autowired
	private OtmcprinRepository otmcprinRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<BankChequeBooks> bankCbExecuteQuery(BankChequeBooks searchRecord) {
		List<BankChequeBooks> returnList = otmcprinRepository.bankCbExecuteQuery(searchRecord);
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBANK_CB
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer bankCbCommit(BankChequeBooksCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(obj -> {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = bankCbUpdateBankChequeBooks(commitBean.getUpdateList());
		}
		return liReturn;
	}

	private Integer bankCbUpdateBankChequeBooks(List<BankChequeBooks> updateList) {
		Integer listreturn = otmcprinRepository.bankCbUpdateBankChequeBooks(updateList);
		Integer nmaxCheckNum = 0;
		if (listreturn == 1) {
			for (BankChequeBooks obj : updateList) {
				 nmaxCheckNum = otmcprinRepository.getMaxCheckNum(obj.getCaseloadId(), obj.getAccountCode(),
						obj.getFirstCheckNumber(), obj.getLastCheckNumber());
				 if(nmaxCheckNum == null) {
					 nmaxCheckNum = 0;
				 }
				if ((obj.getNextCheckNumber().longValue() - nmaxCheckNum) > 1) {
					Integer checkNumber = nmaxCheckNum;
					if (obj.getNextCheckNumber() == null) {
						obj.setNextCheckNumber(BigDecimal.ZERO);
					}
					obj.setCheckNumber(checkNumber + 1);
					while (obj.getCheckNumber() <= obj.getNextCheckNumber().intValue()) {
						BigDecimal substractNum = BigDecimal.valueOf(obj.getNextCheckNumber().intValue() - 1);
						obj.setNextCheckNumber(substractNum);
						obj.setCheckNumber(obj.getNextCheckNumber().intValue());
						Integer insertRegisters = otmcprinRepository.insertIntoRegister(obj);

						obj.setCheckNumber(obj.getCheckNumber() + 1);
						if (insertRegisters != null) {
							return listreturn;
						}
					}

				}
			}

		}
		return listreturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<BankChequeRegisters> bankCrExecuteQuery(BankChequeRegisters searchRecord) {
		List<BankChequeRegisters> returnList = otmcprinRepository.bankCrExecuteQuery(searchRecord);
//		for(BankChequeRegisters obj: returnList) {
//			String desc = otmcprinRepository.getDesc(obj.getChequeStatus());
//			obj.setChequeStatus(desc);
//			
//		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBANK_CR
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer bankCrCommit(BankChequeRegistersCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(obj -> {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = bankCrUpdateBankChequeRegisters(commitBean.getUpdateList());
		}
		return liReturn;
	}

	private Integer bankCrUpdateBankChequeRegisters(List<BankChequeRegisters> updateList) {
		Integer liReturn = otmcprinRepository.bankCrUpdateBankChequeRegisters(updateList);
		if (liReturn == 1) {
			for (BankChequeRegisters obj : updateList) {
				Integer nmaxCheckNum = otmcprinRepository.getMaxCheckNum(obj.getCaseLoadId(), obj.getAccountCode(),
						obj.getFirstCheckNumber(), obj.getLastCheckNumber());
				if ((obj.getNextCheckNumber() - nmaxCheckNum) > 1) {
					Integer checkNumber = nmaxCheckNum;
					if (obj.getNextCheckNumber() == null) {
						obj.setNextCheckNumber(0);
					}
					obj.setChequeNumber(checkNumber.longValue() + 1);
					while (obj.getChequeNumber() <= obj.getNextCheckNumber()) {
						obj.setNextCheckNumber(obj.getNextCheckNumber() - 1);
						obj.setChequeNumber(obj.getNextCheckNumber().longValue());
						Integer insertRegisters = otmcprinRepository.insertIntoRegister(obj);
						obj.setChequeNumber(obj.getChequeNumber() + 1);
					}

				}
			}

		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup(final String chequeStatus) {
		List<ReferenceCodes> returnList = otmcprinRepository.cgfkBankCrChequeStatusRecordGroup(chequeStatus);
		return returnList;
		

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkBankCrCheqStatusVoidRecordGroup() {
		return otmcprinRepository.cgfkBankCrCheqStatusVoidRecordGroup();

	}

	public List<String> checkIfNewSeries(BankChequeBooks searchBean) {
		List<String> emptyList = new ArrayList<String>();
		List<String> xnewList = otmcprinRepository.checkIfNewSeries(searchBean);
		if (xnewList.size() >= 1) {
			emptyList = xnewList;
		} else {
			List<String> xOthList = otmcprinRepository.checkIfOverOthSeries(searchBean);
			if (xOthList.size() >= 1) {
				emptyList = xOthList;

			}
		}
		return emptyList;
	}

	public Long bcRowMaxChecqueNumber(final String caseloadId, final Long accountCode, final String firstCheckNumber,
			final String lastCheckNumber) {
		return otmcprinRepository.bcRowMaxChecqueNumber(caseloadId, accountCode, firstCheckNumber, lastCheckNumber);
	}

	public String isTransactionReversed(Long txnId, final Long accountCode) {
		return otmcprinRepository.isTransactionReversed(txnId, accountCode);
	}

}