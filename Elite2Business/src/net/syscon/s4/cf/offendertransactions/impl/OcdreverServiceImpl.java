package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactionsCommitBean;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.offendertransactions.OcdreverRepository;
import net.syscon.s4.cf.offendertransactions.OcdreverService;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.triggers.OffFeeBillTransactionsT1Service;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Service;
import net.syscon.s4.triggers.OffFeeBillsT2Service;

@Service
public class OcdreverServiceImpl extends BaseBusiness implements OcdreverService {

	@Autowired
	private OcdreverRepository ocdreverRepository;
	@Autowired
	private OcdsupstRepository ocdsupstRepository;
	
	@Autowired
	private OffFeeBillTransactionsT1Service offFeeBillTransactionsT1Service;
	
	@Autowired
	private OffFeeBillTransactionsT2Service offFeeBillTransactionsT2Service;
	
	
	@Autowired
	private OffFeeBillsT2Service offFeeBillsT2Service;
	
	
	
	@Autowired
	private EliteDateService eliteDateService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer adjustRevCommit(OffFeeBillTransactionsCommitBean commitBean) throws Exception {
		Integer liReturn = 0;
		Map<String, Integer> ofbiltxnMap = new HashMap<String, Integer>();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			Integer staffId = ocdsupstRepository.getstaffId(commitBean.getInsertList().get(0).getBillTxnUser());
			for (OffFeeBillTransactions bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());

				if("PAID".equals(bean.getBillStatus())) {
					List<OffFeeBillTransactions> offFeeBillTxnList = ocdreverRepository.getAdjustedBills(bean.getBillId(),bean.getBillTxnNo());
					if(offFeeBillTxnList != null && offFeeBillTxnList.size() > 0) {
						if(offFeeBillTxnList.size() == 1) {
							bean.setBillStatus(offFeeBillTxnList.get(0).getBillStatus());
						}else {
							if(offFeeBillTxnList.get(0).getOriginalBillTxnNo() == null) {
								unReversedPaymentExistsFlow(bean,offFeeBillTxnList.get(0));
							}else {
								unReversedPaymentNotExistsFlow(bean);
							}
						}
					}
				}
				Integer txnNo = ocdreverRepository.getBillTranId(bean.getBillId());
				bean.setBillTxnAmount(bean.getOffAdjRevAmount());
				ofbiltxnMap.put(bean.getBillTxnNo() + bean.getBillId(), txnNo);
				bean.setBillTxnNo(txnNo);
				bean.setBillTxnStaffId(staffId);
			}
			liReturn = ocdreverRepository.offTxnCommit(commitBean.getInsertList());
			if(liReturn == 1) {
				commitBean.getInsertList().forEach(bean->{

				try {
					offFeeBillTransactionsT1Service.offFeeBillTransactionsT1(bean);
					offFeeBillTransactionsT2Service.offFeeBillTransactionsT2(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				});
			}
		}
		return liReturn;

	}

	@Override
	public List<OffFeeBillTransactions> offFeeTxnExecuteQuery(BigDecimal offenderBookId) {
		List<OffFeeBillTransactions> offFeeBillTxnList = new ArrayList<>();
		List<OffFeeBillTransactions> returnList = new ArrayList<>();
		offFeeBillTxnList = ocdreverRepository.offFeeTxnExecuteQuery(offenderBookId);
		for(OffFeeBillTransactions obj : offFeeBillTxnList) {
			if("A".equalsIgnoreCase(obj.getTxnUsage())){
				obj.setAdjustmentAmount(obj.getBillTxnAmount());
				returnList.add(obj);
			}
			
			}
		returnList = returnList.stream().sorted(Comparator.comparing(OffFeeBillTransactions::getCreateDatetime).reversed()).collect(Collectors.toList());
		return returnList;
	}
	
	public OffFeeBillTransactions unReversedPaymentNotExistsFlow(OffFeeBillTransactions bean) throws Exception {
		if(bean.getBillArDueDate() == null || eliteDateService.getDBTime().compareTo(bean.getBillArDueDate()) <= 0) {
			bean.setBillStatus("AR");
		}else {
		if(bean.getBillLdppEndDate() == null || eliteDateService.getDBTime().compareTo(bean.getBillLdppEndDate()) <= 0) {
			OffFeeBills updateBean = new OffFeeBills();
			bean.setBillStatus("LD_PP");
			LocalDateTime localDate = bean.getBillArDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			localDate = localDate.plusDays(1);
			bean.setBillLdppStartDate(Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
			updateBean.setBillExpectedLdppStartDate(Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
			updateBean.setBillId(bean.getBillId());
			
			OffFeeBills oldBeanBills = new OffFeeBills();
			
			oldBeanBills = ocdreverRepository.getOldDataOffFeeBills(bean.getBillId());
			offFeeBillsT2Service.offFeeBillsT2(bean, oldBeanBills);
			ocdreverRepository.offFeeBillsUpdateAging(updateBean);
		}else {
			bean.setBillStatus("LD_EXI");
			LocalDateTime localDate = bean.getBillLdppEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			localDate = localDate.plusDays(1);
			bean.setBillAgingStartDate(Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
		}
		}
		return bean;
	}
	
	public OffFeeBillTransactions unReversedPaymentExistsFlow(OffFeeBillTransactions bean,OffFeeBillTransactions offTxnUnRev)throws Exception{
		if(bean.getBillArDueDate() == null || bean.getBillArDueDate().compareTo(offTxnUnRev.getBillTxnDatetime()) > 0) {
			bean.setBillStatus("AR");
		}else {
			if(bean.getBillLdppEndDate() == null || eliteDateService.getDBTime().compareTo(bean.getBillLdppEndDate()) <= 0) {
				OffFeeBills updateBean = new OffFeeBills();
				bean.setBillStatus("LD_PP");
				bean.setBillLdppStartDate(offTxnUnRev.getBillTxnDatetime());
				updateBean.setBillExpectedLdppStartDate(offTxnUnRev.getBillTxnDatetime());
				updateBean.setBillId(bean.getBillId());
				OffFeeBills oldBeanBills = new OffFeeBills();
				
				oldBeanBills = ocdreverRepository.getOldDataOffFeeBills(offTxnUnRev.getBillId());
				offFeeBillsT2Service.offFeeBillsT2(bean, oldBeanBills);

				ocdreverRepository.offFeeBillsUpdateAging(updateBean);
			}else {
				bean.setBillStatus("LD_EXI");
				LocalDateTime localDate = offTxnUnRev.getBillTxnDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				localDate = localDate.plusDays(91);
				bean.setBillAgingStartDate(Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant()));
			}
		}
		return bean;
	}
}
