package net.syscon.s4.cf.deductions.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcubadjsRepository;
import net.syscon.s4.cf.deductions.OcubadjsService;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.offendertransactions.OcdadjusRepository;
import net.syscon.s4.cf.offendertransactions.OcdadjusService;
import net.syscon.s4.cf.offendertransactions.OcdreverRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;

@Service
public class OcubadjsServiceImpl extends BaseBusiness implements OcubadjsService {

	@Autowired
	private OcubadjsRepository ocubadjsRepository;

	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private OcdreverRepository ocdreverRepository;
	
	@Autowired
	private OcdadjusService ocdadjusService;
	
	@Autowired
	private OcdadjusRepository ocdadjusRepository;
	
	

	@Override
	public List<ReferenceCodes> adjustmentTypeRecordGroup() {
		return ocubadjsRepository.adjustmentTypeRecordGroup();
	}

	@Transactional
	@Override
	public List<OffFeeBillTransactions> saveBillAdjustdDetCommit(final OffFeeBillTransactionsCommitBean commitBean) {
		final List<OffFeeBillTransactions> liReturnData = new ArrayList<>();
		final OffFeeBillTransactions offFeeBillObj = new OffFeeBillTransactions();
		int liReturn = 0;
		
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffFeeBillTransactions object : commitBean.getInsertList()) {
				OffFeeBillTransactions latestRec = ocubadjsRepository.getLatestRec(object);
				String txnUsage = ocubadjsRepository.getSelectedOverrideType(object);
				OffFeeBillTransactions offTxnObj = new OffFeeBillTransactions();
				offTxnObj = ocubadjsRepository.getOriginalBalanceOwing(object);
//				BigDecimal billTxnAmount = offTxnObj != null ? offTxnObj.getBillTxnAmount() : null;
				Integer staffId = ocubadjsRepository.getstaffId(object.getBillTxnUser());
				object.setBillTxnNo(ocubadjsRepository.getBillTranId(object.getBillId()));
				object.setBillTxnDatetime(eliteDateService.getDBTime());
				object.setBillTxnStaffId(staffId);
				if(latestRec != null) {
					object.setTxnStatementGeneratedFlag(latestRec.getTxnStatementGeneratedFlag());
					object.setBillAgingEndDate(latestRec.getBillAgingEndDate());
					object.setBillAgingStartDate(latestRec.getBillAgingStartDate());
					object.setBillingStatementId(latestRec.getBillingStatementId());
				}
				//bill aging flow
//				if (billTxnAmount != null ) {
					if (txnUsage != null &&  "PAID".equals(offTxnObj.getBillStatus()) && "BI".equalsIgnoreCase(txnUsage)) {
						if (offTxnObj.getBillArDueDate() == null
								|| eliteDateService.getDBTime().compareTo(offTxnObj.getBillArDueDate()) <= 0) {
							object.setBillStatus("AR");
						} else if(latestRec.getBillAgingEndDate() == null) {
							object.setBillStatus("LD_EXI");
						}
						else {
							object.setBillStatus("LD_PP");
						}
//							LocalDateTime sysDate = eliteDateService.getDBTime().toInstant()
//									.atZone(ZoneId.systemDefault()).toLocalDateTime();
//							LocalDateTime dueDate = latestRec.getBillTxnDatetime().toInstant()
//									.atZone(ZoneId.systemDefault()).toLocalDateTime();
//							long days = Duration.between(dueDate, sysDate).toDays();
//							if (days >= 91) {
//								object.setBillStatus("LD_EXI");
//								LocalDateTime localDate = object.getBillTxnDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//								localDate = localDate.plusDays(91);
//								object.setBillAgingStartDate(Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
//							} else {
//								object.setBillStatus("LD_PP");
//								object.setBillLdppStartDate(eliteDateService.getDBTime());
//								OffFeeBills updateBean = new OffFeeBills();
//								updateBean.setBillExpectedLdppStartDate(eliteDateService.getDBTime());
//								updateBean.setBillId(object.getBillId());
//								ocdreverRepository.offFeeBillsUpdateAging(updateBean);
//							}
//						}
//						object.setBillTxnAmount(billTxnAmount.add(object.getBillTxnAmount()));
					} else if( "PAID".equals(offTxnObj.getBillStatus()) && txnUsage != null && "BD".equalsIgnoreCase(txnUsage)){
						offFeeBillObj.setLiReturn(2);
						liReturnData.add(offFeeBillObj);
						return liReturnData;
					}
//				}
				else {
					if (txnUsage != null && "BD".equalsIgnoreCase(txnUsage)) {
						if(object.getBalanceOwingAmount() == null) {
							BigDecimal owingAmount = ocdadjusRepository.getPostQuery(object.getBillId());
							if(owingAmount!=null) {
								object.setBalanceOwingAmount(owingAmount);
							}
						}
						if(object.getBalanceOwingAmount() != null && object.getBalanceOwingAmount().compareTo(object.getBillTxnAmount()) < 0) {
							offFeeBillObj.setLiReturn(3);
							liReturnData.add(offFeeBillObj);
							return liReturnData;
						}
						
						BigDecimal adjustedAmnt = object.getBalanceOwingAmount().subtract(object.getBillTxnAmount());
//						object.setBillTxnAmount(adjustedAmnt);
						if (adjustedAmnt.compareTo(BigDecimal.ZERO) == 0) {
							object.setBillStatus("PAID");
						}
					} 
//					else if (txnUsage != null && "BI".equalsIgnoreCase(txnUsage)) {
////						object.setBillTxnAmount(billTxnAmount.add(object.getBillTxnAmount()));
//					}
				}

				
				
//				Integer stmtId = ocdbadjusRepository.stmtPreInsert();
//				if (commitBean.getStmtInsertList() != null && !commitBean.getStmtInsertList().isEmpty()) {
//					commitBean.getStmtInsertList().get(0).setBillingStatementId(stmtId);
//					Integer insertCount = ocdreceiRepository.offStmtCommit(commitBean.getStmtInsertList().get(0));
//					if (insertCount == 1) {
//						ocdreceiRepository.offFeeBillsUpdate(commitBean.getStmtInsertList().get(0));
//						
//					}
//				}
//				object.setBillingStatementId(stmtId);
//				object.setBillAgingStartDate(commitBean.getStmtInsertList().get(0).getBillingCycleStartDate());
//				object.setBillAgingEndDate(commitBean.getStmtInsertList().get(0).getBillingCycleEndDate());
//				object.setTxnStatementGeneratedFlag("Y");
					object.setCreateUserId(commitBean.getCreateUserId());
				liReturn = ocubadjsRepository.offFeeBillTransInsertQuery(object);
				offFeeBillObj.setLiReturn(liReturn);
				liReturnData.add(offFeeBillObj);
			}
			return liReturnData;
		}
		return liReturnData;
	}

	@Override
	public List<OffFeeBillTransactions> billAdjustDetailsExecuteQuery(OffFeeBillTransactions searchBean) {
		List<OffFeeBillTransactions> returnList = new ArrayList<>();
		List<OffFeeBillTransactions> orginalReturnList = new ArrayList<>();
		returnList = ocubadjsRepository.billAdjustDetailsExecuteQuery(searchBean);
		BigDecimal previousAmnt = BigDecimal.ZERO;
		
		for (OffFeeBillTransactions offFeeBillTransactions : returnList) {
			if(previousAmnt.equals(BigDecimal.ZERO)) {
				previousAmnt = offFeeBillTransactions.getBillTxnAmount();
			}
			if ("BI".equals(offFeeBillTransactions.getTxnUsage())) {
				//offFeeBillTransactions.setAdjustmentAmountTot(searchBean.getAdjustmentAmountTot());
				offFeeBillTransactions.setBillOverrideIncreaseDecAmount(previousAmnt.add(offFeeBillTransactions.getBillTxnAmount()));
				previousAmnt = offFeeBillTransactions.getBillOverrideIncreaseDecAmount();
				orginalReturnList.add(offFeeBillTransactions);
			} else if("BD".equals(offFeeBillTransactions.getTxnUsage())) {
				//offFeeBillTransactions.setAdjustmentAmountTot(searchBean.getAdjustmentAmountTot());
				offFeeBillTransactions.setBillOverrideIncreaseDecAmount(previousAmnt.subtract(offFeeBillTransactions.getBillTxnAmount()));
				previousAmnt = offFeeBillTransactions.getBillOverrideIncreaseDecAmount();
				orginalReturnList.add(offFeeBillTransactions);
			}
			
			
		}
		if(orginalReturnList != null && orginalReturnList.size() > 0) {
			orginalReturnList.get(0).setAdjustmentAmountTot(returnList.get(0).getBillTxnAmount());
			}
		return ocdadjusService.postExecuteQuery(orginalReturnList);
	}

	@Override
	public String getSelectedOverrideType(OffFeeBillTransactions searchBean) {
		return ocubadjsRepository.getSelectedOverrideType(searchBean);
	}

}
