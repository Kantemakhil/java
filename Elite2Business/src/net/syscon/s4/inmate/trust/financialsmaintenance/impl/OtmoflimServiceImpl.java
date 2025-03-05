package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.OffenderLimitsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmoflimRepository;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmoflimService;

/**
 * Class OtmoflimServiceImpl
 * 
 */

@Service
public class OtmoflimServiceImpl extends BaseBusiness implements OtmoflimService {

	@Autowired
	private OtmoflimRepository otmoflimRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */

	public List<OffenderLimits> offLimExecuteQuery(final OffenderLimits searchRecord) {

		return otmoflimRepository.offLimExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_LIM
	 *
	 * 
	 */
	@Transactional
	public String offLimCommit(final OffenderLimitsCommitBean commitBean) {
		Integer liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = otmoflimRepository.offLimInsertOffenderLimits(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele -> {
				ele.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmoflimRepository.offLimUpdateOffenderLimits(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele -> {
				ele.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = otmoflimRepository.offLimDeleteOffenderLimits(commitBean.getDeleteList());
		}
		return liReturn.toString();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */

	public List<ReferenceCodes> cgfkOffLimLimitTypeRecordGroup() {
		return otmoflimRepository.cgfkOffLimLimitTypeRecordGroup();

	}

}