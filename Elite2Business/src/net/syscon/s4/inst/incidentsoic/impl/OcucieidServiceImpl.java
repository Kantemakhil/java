package net.syscon.s4.inst.incidentsoic.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffenses;
import net.syscon.s4.im.incidentsoic.beans.ExternalInvestigationOffensesCommitBean;
import net.syscon.s4.inst.incidentsoic.OcucieidRepository;
import net.syscon.s4.inst.incidentsoic.OcucieidService;

@Service
public class OcucieidServiceImpl extends BaseBusiness implements OcucieidService {

	private static Logger logger = LogManager.getLogger(OcucieidServiceImpl.class.getName());
	@Autowired
	private OcucieidRepository ocucieidRepository;

	@Override
	public ExternalInvestigationOffenses checkForInsertOrUpdateAndDeleteExternalInvst(final String userName) {

		ExternalInvestigationOffenses extInvOffences = new ExternalInvestigationOffenses();
		extInvOffences.setChargeSeq(ocucieidRepository.checkForInsertOrUpdateExternalInvst(userName));
		extInvOffences.setEidSeq(ocucieidRepository.checkForDeleteExternalInvst(userName));
		return extInvOffences;
	}

	@Override
	public List<ExternalInvestigationOffenses> getAllExternalInvstDetails(final 
			ExternalInvestigationOffenses extInvOffences) {
		return ocucieidRepository.getAllExternalInvstDetails(extInvOffences);
	}

	@Transactional
	@Override
	public Integer insertOrUpdateOrDelete(ExternalInvestigationOffensesCommitBean commitBean) {
		Integer result = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			try {
				result = ocucieidRepository.insertExternalInvstDetails(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in insertExternalInvstDetails :: " + e);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			try {
				result = ocucieidRepository.updateExternalInvstDetails(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in updateExternalInvstDetails :: " + e);
			}

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			try {
				result = ocucieidRepository.deteleExternalInvstDetails(commitBean.getDeleteList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in deteleExternalInvstDetails :: " + e);
			}

		}
		return result;
	}

}
