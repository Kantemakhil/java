package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursements;
import net.syscon.s4.inmate.beans.OffenderFreezeDisbursementsCommitBean;
import net.syscon.s4.inmate.trust.trustaccounts.OtdofrezRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtdofrezService;

@Service
public class OtdofrezServiceImpl extends BaseBusiness implements OtdofrezService {

	@Autowired
	private OtdofrezRepository otdofrezRepository;
	public static final Integer CONSTANTVALUE = 2;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdofrezServiceImpl.class.getName());


	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Object offBkgPreDelete() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Object offFdPreInsert() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderFreezeDisbursements> offFdExecuteQuery(final OffenderFreezeDisbursements searchRecord) {
		return otdofrezRepository.offFdExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_FD
	 *
	 * 
	 */
	@Transactional
	public Integer offFdCommit(final OffenderFreezeDisbursementsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderFreezeDisbursements bean: commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				Integer dupCount = otdofrezRepository.preInsert(bean);
				if(dupCount >0) {
					return  CONSTANTVALUE;
				}
			}
			liReturn = offFdInsertOffenderFreezeDisbursements(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderFreezeDisbursements obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offFdUpdateOffenderFreezeDisbursements(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = offFdDeleteOffenderFreezeDisbursements(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Transactional
	public Integer offFdInsertOffenderFreezeDisbursements(final List<OffenderFreezeDisbursements> insertList) {

		return otdofrezRepository.offFdInsertOffenderFreezeDisbursements(insertList);

	}
	public Integer offFdUpdateOffenderFreezeDisbursements(final List<OffenderFreezeDisbursements> updateList) {
		return otdofrezRepository.offFdUpdateOffenderFreezeDisbursements(updateList);
	}
	
	public Integer offFdDeleteOffenderFreezeDisbursements(final List<OffenderFreezeDisbursements> deleteList) {
		return otdofrezRepository.offFdDeleteOffenderFreezeDisbursements(deleteList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffFdFreezeReasonCodeRecordGroup() {
		final List<ReferenceCodes> resultList = otdofrezRepository.cgfkOffFdFreezeReasonCodeRecordGroup();
		resultList.forEach(data -> {
			data.setDomain(data.getDescription());
			data.setDescription(data.getCode());
		});
		return resultList;

	}

}