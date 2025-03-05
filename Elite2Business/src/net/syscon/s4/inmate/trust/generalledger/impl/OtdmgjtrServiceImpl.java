package net.syscon.s4.inmate.trust.generalledger.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;
import net.syscon.s4.inmate.trust.generalledger.OtdmgjtrRepository;
import net.syscon.s4.inmate.trust.generalledger.OtdmgjtrService;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.trust_gj.TrustGjService;
import net.syscon.s4.triggers.GlTransactionsT1Service;
import net.syscon.s4.triggers.GlTransactionsT2Service;
import net.syscon.s4.triggers.GlTransactionsTjnService;
import net.syscon.s4.triggers.TrustAudits;

/**
 * Class OtdmgjtrServiceImpl
 */
@Service
public class OtdmgjtrServiceImpl extends BaseBusiness implements OtdmgjtrService {

	@Autowired
	private OtdmgjtrRepository otdmgjtrRepository;

	@Autowired
	private GlTransactionsT1Service glTransactionsT1Service;

	@Autowired
	private GlTransactionsT2Service glTransactionsT2Service;

	@Autowired
	private GlTransactionsTjnService glTransactionsTjnService;

	@Autowired
	private TrustGjService trustGjService;


	private static Logger logger = LogManager.getLogger(OtdmgjtrServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<GlTransactions> glTxnExecuteQuery(final GlTransactions searchRecord) {
		return otdmgjtrRepository.glTxnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstGL_TXN
	 *
	 * @
	 */
	@Transactional
	public Integer glTxnCommit(final GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(GlTransactions glTxnBean : commitBean.getInsertList()) {
				glTxnBean.setCreateUserId(commitBean.getCreateUserId());

				TrustAudits trustAudits=new TrustAudits();

				BeanUtils.copyProperties(glTxnBean, trustAudits);
				trustAudits.setTxnId(glTxnBean.getTxnId()!=null?new BigDecimal(glTxnBean.getTxnId()):null);
				try {
					glTransactionsT1Service.glTransactionsT1Trigger(trustAudits);

				}catch (Exception e) {
					logger.error(e);
				}
			}

			liReturn = glTxnInsertGlTransactions(commitBean.getInsertList());
			if(commitBean.getInsertList()!=null) {

				for(final GlTransactions glTransactionsBean: commitBean.getInsertList()){
					try {

						glTransactionsT2Service.glTransactionsT2Trigger(glTransactionsBean);
					}catch (Exception e) {
						logger.error(e);
					}
					try {
						//glTransactionsTjnService.glTransactionsTjnTrigger(glTransactionsBean,"INSERTING");
					}catch (Exception e) {
						logger.error(e);
					}
				}
			}
		}

		return liReturn;
	}


	private Integer glTxnInsertGlTransactions(final List<GlTransactions> insertList) {
		final List<GlTransactions> glList=new ArrayList<GlTransactions>();
		Integer txnId=0;
		final Integer txnSeq=1;
		Integer insertCheckData=0;
		txnId=otdmgjtrRepository.getTrustTrans();
		for(final GlTransactions obj: insertList){

			obj.setTxnId(Long.valueOf(txnId.toString()));
			obj.setTxnEntrySeq(Long.valueOf(txnSeq.toString()));
			obj.setGlEntrySeq(Long.valueOf((insertList.indexOf(obj) + 1) + ""));
			obj.setTxnEntryDate(trunc(obj.getTxnEntryDate()));
			obj.setCreateDate(trunc(obj.getCreateDate()));

			if(obj.getAccountPeriodId() == null || obj.getAccountPeriodId() == BigDecimal.valueOf(0)){
				final Integer accountPeriodId =otdmgjtrRepository.lvEnteraccountPeriod(obj.getTxnEntryDate());

				obj.setAccountPeriodId(BigDecimal.valueOf(Long.valueOf(accountPeriodId.toString())));
			}

			trustGjService.reopenClosedPeriod(obj.getCaseloadId(), obj.getTxnEntryDate(), obj.getCreateUserId());

			if("Y".equals(obj.getCheckProduce()) && "CR".equals(obj.getTxnPostUsage())) {
				final Integer isAccountCheck =otdmgjtrRepository.isAccountchecking(obj.getCaseloadId(),Integer.parseInt(obj.getAccountCode().toString()));
				if(isAccountCheck <= 0){
					return -1;				
				}
			}
			if("Y".equals(obj.getCheckProduce()) && "DR".equals(obj.getTxnPostUsage())) {
				final Integer isAccountCheck =otdmgjtrRepository.isAccountchecking(obj.getCaseloadId(),Integer.parseInt(obj.getAccountCode().toString()));
				if(isAccountCheck <= 0){
					return -2;				
				}
			}
			if ("Y".equals(obj.getCheckProduce())){
				if("CR".equals(obj.getTxnPostUsage())){
					obj.setAccountCode(obj.getAccountCodeOne());

				} else {
					obj.setAccountCode(obj.getAccountCodeTwo());
				}

			}
			if(obj.getTxnPostUsageGrid() !=null && "DR".equals(obj.getTxnPostUsageGrid())){
				final String cr =obj.getTxnPostUsageCr();
				if(obj.getTxnPostUsageCr()!=null && "ÇR".equals(cr)){
					obj.setTxnPostUsage("DR");
				} else {
					obj.setTxnPostUsage("CR");
				}
			} 
			if(obj.getTxnPostUsageGrid() !=null && "CR".equals(obj.getTxnPostUsageGrid())){
				final String cr =obj.getTxnPostUsageCr();
				if(obj.getTxnPostUsageCr()!=null && "ÇR".equals(cr)){
					obj.setTxnPostUsage("DR");
				} else {
					obj.setTxnPostUsage("CR");
				}

			}
			glList.add(obj);
		}
		final Integer insertGlTrans= otdmgjtrRepository.glTxnInsertGlTransactions(glList);
		if(insertGlTrans==1){
			for(final GlTransactions objgl:glList) {	
				if ("Y".equals(objgl.getCheckProduce())){
					if("CR".equals(objgl.getTxnPostUsage())){
						objgl.setAccountCode(objgl.getAccountCodeOne());

					} else {
						objgl.setAccountCode(objgl.getAccountCodeTwo());
					}
					insertCheckData =otdmgjtrRepository.insertIntoCheckData(objgl);
				}

			}
		}
		if(insertCheckData ==1 || insertGlTrans ==1){
			return txnId;
		}

		return txnId;		
	}



	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Corporates> cgfkGlTxnPayeeCorporateIdRecordGroup() {
		return otdmgjtrRepository.cgfkGlTxnPayeeCorporateIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AccountCodes> cgfkGlTxn1AccountCodeRecordGroup(final String caseloadType,final String caseloadId) {

		final List<AccountCodes> returnList= otdmgjtrRepository.cgfkGlTxn1AccountCodeRecordGroup( caseloadType,caseloadId);
		returnList.forEach(result ->{
			result.setCode(result.getAccountCode().toString());
			result.setDescription(result.getAccountName());
			result.setaTxnPostingType(result.getTxnPostingType());

		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup() {
		return otdmgjtrRepository.cgfkGlTxnAccountCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Persons> cgfkGlTxnPayeePersonIdRecordGroup() {
		return otdmgjtrRepository.cgfkGlTxnPayeePersonIdRecordGroup();

	}

	@Override
	public List<AccountCodes> getDescandType(final String code,final String caseloadType) {
		return otdmgjtrRepository.getDescandType(code,caseloadType);
	}

	@Override
	public Integer lvlLastclosedPeriod(final String caseloadId) {
		return otdmgjtrRepository.lvlLastclosedPeriod(caseloadId);
	}
	@Override
	public Integer lvAllowedReopenPeriod(final String caseloadId) {
		return otdmgjtrRepository.lvAllowedReopenPeriod(caseloadId);
	}

	@Override
	public Integer lvEnteraccountPeriod(final Long txnEntryDate) {
		final Date txndate =new Date(txnEntryDate);
		return otdmgjtrRepository.lvEnteraccountPeriod(txndate);
	}

	@Override
	public Integer isPeriodValid(final String caseloadId, final Integer lventerAccountPeriod) {
		return otdmgjtrRepository.isPeriodValid(caseloadId,lventerAccountPeriod);
	}

	@Override
	public String lvAccountStatus(final Integer lventerAccountPeriod, final String caseloadId) {
		return otdmgjtrRepository.lvAccountStatus(lventerAccountPeriod,caseloadId);
	}

	@Override
	public Date getPeriodStartDate(final Integer lventerAccountPeriod) {
		return otdmgjtrRepository.getPeriodStartDate(lventerAccountPeriod);
	}

	@Override
	public Date getperiodEndDate(final Integer lvlastClosedPeriod) {
		return otdmgjtrRepository.getperiodEndDate(lvlastClosedPeriod);
	}

	@Override
	public Integer isAccountchecking(final String caseloadId, final Integer accountCode) {

		return otdmgjtrRepository.isAccountchecking(caseloadId,accountCode);
	}

	@Override
	public AccountCodes cStatus(final AccountCodes accountCodes) {

		final Integer lvlastClosedPeriod= otdmgjtrRepository.lvlLastclosedPeriod(accountCodes.getCaseloadId());
		final Integer lvallowedreopenPeriod= otdmgjtrRepository.lvAllowedReopenPeriod(accountCodes.getCaseloadId());
		final Integer lvEnteraccountPeriodId =otdmgjtrRepository.lvEnteraccountPeriod(accountCodes.getTxnEntryDate());
		accountCodes.setLvlastClosedPeriod(lvlastClosedPeriod);
		accountCodes.setLvallowedreopenPeriod(lvallowedreopenPeriod);
		accountCodes.setLvEnteraccountPeriodId(lvEnteraccountPeriodId);

		final String cstatusNumber= otdmgjtrRepository.cStatusNumber(accountCodes);

		accountCodes.setAccountPeriodStatus(cstatusNumber);

		return accountCodes;
	}
	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			calender.set(Calendar.AM_PM, Calendar.AM);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

	public BigDecimal getCurrentBalance(final String caseloadId, final Integer accountCode,String user) {
		return	trustGjService.getCurrentBalance(caseloadId, accountCode, user);
	}

}