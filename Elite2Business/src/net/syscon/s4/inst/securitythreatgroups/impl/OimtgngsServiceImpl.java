package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.SecurityThreatGroupsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OimtgngsRepository;
import net.syscon.s4.inst.securitythreatgroups.OimtgngsService;

/**
 * Class OimtgngsServiceImpl
 * 
 */
@Service
public class OimtgngsServiceImpl extends BaseBusiness implements OimtgngsService {

	@Autowired
	private OimtgngsRepository oimtgngsRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SecurityThreatGroups> secGrpExecuteQuery(final SecurityThreatGroups searchRecord) {
		final List<SecurityThreatGroups> returnList = oimtgngsRepository.secGrpExecuteQuery(searchRecord);

		for (final SecurityThreatGroups obj : returnList) {
			final Integer lpValue = oimtgngsRepository.getLpValue();
			obj.setLpValue(lpValue);

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEC_GRP
	 *
	 * 
	 */
	@Transactional
	public Integer secGrpCommit(final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			  for (SecurityThreatGroups data : commitBean.getInsertList() ) {
				data.setCreateUserId(commitBean.getCreateUserId());	
			}
			  liReturn = secGrpInsertSecurityThreatGroups(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SecurityThreatGroups data : commitBean.getUpdateList() ) {
				data.setModifyUserId(commitBean.getCreateUserId());	
			}
			 liReturn = oimtgngsRepository.secGrpUpdateSecurityThreatGroups(commitBean.getUpdateList());
		}
		return liReturn;
	}

	private Integer secGrpInsertSecurityThreatGroups(final List<SecurityThreatGroups> insertList) {

		for (final SecurityThreatGroups obj : insertList) {
			final Integer nextVal = oimtgngsRepository.getNextValue();
			obj.setStgId(nextVal);

		}

		return oimtgngsRepository.secGrpInsertSecurityThreatGroups(insertList);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<SecurityThreatGroups> secGrp1ExecuteQuery(final SecurityThreatGroups searchRecord) {
		final List<SecurityThreatGroups> returnList = oimtgngsRepository.secGrp1ExecuteQuery(searchRecord);
		for (final SecurityThreatGroups obj : returnList) {
			final Integer lpGang = oimtgngsRepository.getLpGang(obj.getParentStgId());
			obj.setLpGang(lpGang);

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEC_GRP1
	 *
	 * 
	 */
	@Transactional
	public Integer secGrp1Commit(final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SecurityThreatGroups data : commitBean.getInsertList() ) {
				data.setCreateUserId(commitBean.getCreateUserId());	
			}
			liReturn = secGrp1InsertSecurityThreatGroups(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SecurityThreatGroups data : commitBean.getUpdateList() ) {
				data.setModifyUserId(commitBean.getCreateUserId());	
			}
			liReturn = oimtgngsRepository.secGrp1UpdateSecurityThreatGroups(commitBean.getUpdateList());
		}
		return liReturn;
	}

	private Integer secGrp1InsertSecurityThreatGroups(final List<SecurityThreatGroups> insertList) {
		for (final SecurityThreatGroups obj : insertList) {
			final Integer nextVal = oimtgngsRepository.getGangNextVal();
			obj.setStgId(nextVal);

		}
		return oimtgngsRepository.secGrp1InsertSecurityThreatGroups(insertList);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEC_GRP2
	 *
	 * 
	 */
	@Transactional
	public Integer secGrp2Commit(final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SecurityThreatGroups data : commitBean.getInsertList() ) {
				data.setCreateUserId(commitBean.getCreateUserId());	
			}
			liReturn = secGrp2InsertSecurityThreatGroups(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SecurityThreatGroups data : commitBean.getUpdateList() ) {
				data.setModifyUserId(commitBean.getCreateUserId());	
			}
			liReturn = oimtgngsRepository.secGrp2UpdateSecurityThreatGroups(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEC_GRP2
	 *
	 * 
	 */
	private Integer secGrp2InsertSecurityThreatGroups(final List<SecurityThreatGroups> insertList) {
		for (final SecurityThreatGroups obj : insertList) {
			final Integer nextVal = oimtgngsRepository.getSetNextVal();
			obj.setStgId(nextVal);

		}
		return oimtgngsRepository.secGrp2InsertSecurityThreatGroups(insertList);
	}

	/**
	 * Fetch the SecurityThreatGroups records from database table
	 *
	 * @param searchBean
	 *
	 * 
	 */
	public List<SecurityThreatGroups> secGrp2ExecuteQuery(final SecurityThreatGroups searchBean) {
		final List<SecurityThreatGroups> returnList = oimtgngsRepository.secGrp2ExecuteQuery(searchBean);
		for (final SecurityThreatGroups obj : returnList) {
			final Integer lpSet = oimtgngsRepository.getLpSet(obj.getParentStgId());
			obj.setLpSet(lpSet);

		}
		return returnList;
	}

	/**
	 * Fetch the duplicate records from database table
	 *
	 * @param stgCode
	 *
	 * 
	 */
	public Integer getDuplicateStgCode(final String stgCode) {
		return oimtgngsRepository.getDuplicateStgCode(stgCode);
	}

	/**
	 * Fetch the duplicate records from database table
	 *
	 * @param stgCode
	 *
	 * 
	 */
	public Integer getDuplicateGangsStgCode(final String stgCode) {
		return oimtgngsRepository.getDuplicateGangsStgCode(stgCode);
	}

	/**
	 * Fetch the duplicate records from database table
	 *
	 * @param stgCode
	 *
	 * 
	 */
	public Integer getDuplicateSetsStgCode(final String stgCode) {
		return oimtgngsRepository.getDuplicateSetsStgCode(stgCode);
	}

	/**
	 * Fetch the duplicate records from database table
	 *
	 * @param stgId
	 *
	 * 
	 */
	public Integer offStgCur(final Integer stgId) {
		return oimtgngsRepository.offStgCur(stgId);
	}

	/**
	 * Fetch the duplicate records from database table
	 *
	 * @param stgId
	 *
	 * 
	 */
	public Integer offStgCurSecGrp(final Integer stgId) {
		return oimtgngsRepository.offStgCur(stgId);
	}
}