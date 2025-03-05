
package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SecurityThreatGroupsHty;
import net.syscon.s4.common.beans.SecurityThreatGroupsHtyCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.securitythreatgroups.OimtgoptRepository;
import net.syscon.s4.inst.securitythreatgroups.OimtgoptService;

@Service
public class OimtgoptServiceImpl extends BaseBusiness implements OimtgoptService {

	@Autowired
	private OimtgoptRepository oimtgoptRepository;

	/**
	 * Creates new OimtgoptServiceImpl class Object
	 */
	public OimtgoptServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String stgHty1PostQuery(final BigDecimal param) {
		return oimtgoptRepository.stgHty1PostQuery(param);
	}

	public BigDecimal cgtewhenRadioChanged(final BigDecimal param) {
		return oimtgoptRepository.cgtewhenRadioChanged(param);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SecurityThreatGroupsHty> stgHtyExecuteQuery(final SecurityThreatGroupsHty searchRecord) {
		final List<SecurityThreatGroupsHty> result = oimtgoptRepository.stgHtyExecuteQuery(searchRecord);
		if (result != null) {
			result.stream().forEach(data -> {
				final String description = oimtgoptRepository.stgHty1PostQuery(BigDecimal.valueOf(data.getToPStgId()));
				if (description != null) {
					data.setDescription(description);
				} else {
					data.setSealFlag("DES");
				}
			});
		}
		return result;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_HTY
	 *
	 * 
	 */
	@Transactional
	public Integer stgHtyCommit(final SecurityThreatGroupsHtyCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = stgHtyInsertSecurityThreatGroupsHty(commitBean.getInsertList());
			return liReturn;
		}
		return liReturn;
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOffenderAlerts
	 *
	 * 
	 */
	@Transactional
	public Integer stgHtyInsertSecurityThreatGroupsHty(final List<SecurityThreatGroupsHty> lstOffenderAlerts) {
		Integer listReturn = null;
		lstOffenderAlerts.forEach(action -> {
			if (action.getStgId() != null) {
				final BigDecimal hytSeq = oimtgoptRepository.preInsert(action.getStgId());
				if (hytSeq != null) {
					action.setHtySeq(hytSeq);
				}
				final SecurityThreatGroups data = oimtgoptRepository.preInsertSecurityThreatGroups(action.getStgId());
				if (data != null) {
					action.setListSeq(data.getListSeq());
					action.setFromStgLevel(data.getStgLevel());
					action.setFromPStgId(data.getParentStgId());
					action.setModifyUserId(data.getCreateUserId());
				}
			}
			if ("Realign".equals(action.getAction())) {
				action.setAction("Re-align");
				oimtgoptRepository.updateSecurityThreatGroups(action);
			} else if ("Demote".equals(action.getAction()) && "GANG".equals(action.getFromStgLevel())) {
				action.setAction("Demote");
				oimtgoptRepository.updateSecurityThreatGroupsOne(action);
			} else if ("Promote".equals(action.getAction()) && "SET".equals(action.getFromStgLevel())) {
				action.setAction("Promote");
				final SecurityThreatGroups data = oimtgoptRepository.toParentStgIdData(action);
				if (data != null) {
					action.setToPStgId(data.getParentStgId());
				}
				oimtgoptRepository.updateSecurityThreatGroupsTwo(action);
			}
		});
		listReturn = oimtgoptRepository.stgHtyInsertSecurityThreatGroupsHty(lstOffenderAlerts);
		return listReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SecurityThreatGroupsHty> stgHty1ExecuteQuery(final SecurityThreatGroupsHty searchRecord) {
		return oimtgoptRepository.stgHty1ExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> lNationRecordGroup(final Integer parentStgId) {
		return oimtgoptRepository.lNationRecordGroup(parentStgId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> lGangRecordGroup(final Integer parentStgId) {
		return oimtgoptRepository.lGangRecordGroup(parentStgId);

	}

}