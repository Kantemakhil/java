package net.syscon.s4.inst.offenderissuestracking.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievanceTxnsCommitBean;
import net.syscon.s4.inst.offenderissuestracking.OiuprresRepository;
import net.syscon.s4.inst.offenderissuestracking.OiuprresService;

/**
 * Class OiuprresServiceImpl
 */
@Service
public class OiuprresServiceImpl extends BaseBusiness implements OiuprresService {

	@Autowired
	private OiuprresRepository oiuprresRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderGrievanceTxns> prresExecuteQuery(OffenderGrievanceTxns searchRecord) {
		return oiuprresRepository.prresExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPRRES
	 *
	 * @
	 */
	@Transactional
	public Integer prresCommit(final OffenderGrievanceTxnsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> {
				bean.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = oiuprresRepository.prresInsertOffenderGrievanceTxns(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				bean.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oiuprresRepository.prresUpdateOffenderGrievanceTxns(commitBean.getUpdateList());
		}
		return liReturn;
	}

	public Integer prresUpdateOffenderGrievanceTxns(final List<OffenderGrievanceTxns> updateList) {
		return oiuprresRepository.prresUpdateOffenderGrievanceTxns(updateList);
	}

}