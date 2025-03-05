package net.syscon.s4.inst.legalscreens.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.StatutesCommitBean;
import net.syscon.s4.inst.legalscreens.OimstatuRepository;
import net.syscon.s4.inst.legalscreens.OimstatuService;

/**
 * Class OimstatuServiceImpl
 */
@Service
public class OimstatuServiceImpl extends BaseBusiness implements OimstatuService {

	@Autowired
	private OimstatuRepository oimstatuRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Statutes> statExecuteQuery(Statutes searchRecord) {
		return oimstatuRepository.statExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTAT
	 *
	 */
	@Transactional
	public List<Statutes> statCommit(StatutesCommitBean commitBean) {
		List<Statutes> list = new ArrayList<>();
		Statutes statutes = new Statutes();
		if (!commitBean.getInsertList().isEmpty()) {
			for (Statutes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			 statutes = oimstatuRepository.statInsertStatutes(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty()) {
			for (Statutes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			statutes = oimstatuRepository.statUpdateStatutes(commitBean.getUpdateList());
		}
		if (!commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			statutes = oimstatuRepository.statDeleteStatutes(commitBean.getDeleteList());
		}
		list.add(statutes);
		return list;
	}
	
	public List<OffenceResultCodes> getInactiveStatutes(OffenceResultCodes searchRecord) {
		return oimstatuRepository.getInactiveStatutes(searchRecord);

	}

}