package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legals.OcucondiRepository;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.OffenderProceedings;
import net.syscon.s4.inst.legals.beans.OffenderProceedingsCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditionsCommitBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.iwp.OcdenforRepository;
import net.syscon.s4.iwp.OcdenforService;

/**
 * Class OcdenforServiceImpl
 */
@Service
public class OcdenforServiceImpl extends BaseBusiness implements OcdenforService {
	private BigDecimal proceedingId;
	private static Logger logger = LogManager.getLogger(OcdenforServiceImpl.class.getName());

	@Autowired
	private OcdenforRepository ocdenforRepository;
	
	@Autowired
	OcucondiRepository repository;

	public List<AgencyLocations> rgAgyLocsRecordGroup(String proceedingType) {
		List<AgencyLocations> result = new ArrayList<AgencyLocations>();
		try {
			result = ocdenforRepository.rgAgyLocsRecordGroup(proceedingType);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " rgAgyLocsRecordGroup and Exception is : {}", e.getMessage());
		}
		return result;
	}

	@Override
	public List<ReferenceCodes> rgTeamResponsibleRecordGroup(String userId, String agylocId) {
		List<ReferenceCodes> result = new ArrayList<ReferenceCodes>();
		try {
			result = ocdenforRepository.rgTeamResponsibleRecordGroup(userId, agylocId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " rgTeamResponsibleRecordGroup and Exception is : {}",
					e.getMessage());
		}
		return result;
	}

	@Override
	public List<ReferenceCodes> rgStaffResponsibleRecordGroup(String caseloadId, String teamResponsible) {
		List<ReferenceCodes> result = new ArrayList<ReferenceCodes>();
		try {
			result = ocdenforRepository.rgStaffResponsibleRecordGroup(caseloadId, teamResponsible);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " rgStaffResponsibleRecordGroup and Exception is : {}",
					e.getMessage());
		}
		return result;
	}

	@Override
	public List<OffenderProceedings> actionsExecuteQuery(OffenderProceedings searchBean) {
		List<OffenderProceedings> result = new ArrayList<OffenderProceedings>();
		try {
			result = ocdenforRepository.actionsExecuteQuery(searchBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " actionsExecuteQuery and Exception is : {}", e.getMessage());
		}
		return result;
	}

	@Transactional
	public Integer actionsCommit(OffenderProceedingsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (OffenderProceedings offenderProceedings : commitBean.getInsertList()) {
					proceedingId = ocdenforRepository.getNextProceedingId();
					offenderProceedings.setOffenderProceedingId(proceedingId);
				}
				liReturn = ocdenforRepository.insertCourtActions(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				liReturn = ocdenforRepository.updateCourtActions(commitBean.getUpdateList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " actionsCommit and Exception is : {}", e.getMessage());
		}
		return liReturn;
	}

	@Override
	public Long scheduleConflict(CourtEvents searchBean) {
		return (long) ocdenforRepository.getScheduleCount(searchBean);
	}

	@Override
	public List<OffenderSentConditions> getConditionTypeGridData(OffenderSentConditions condition) {
		List<OffenderSentConditions> returnList = new ArrayList<OffenderSentConditions>();
		returnList = ocdenforRepository.getConditionTypeGridData(condition);
		for (OffenderSentConditions offenderSentConditions : returnList) {
			if (offenderSentConditions.getLinkedCount() > 0) {
				offenderSentConditions.setLinkFlag("Y");
			} else if (offenderSentConditions.getLinkedCount() == 0
					&& offenderSentConditions.getLinkedToOtherProceeding() == 0) {
				offenderSentConditions.setLinkFlag("N");
			}
		}
		return returnList;
	}

	@Override
	@Transactional
	public Integer conditionProceedSave(OffenderSentConditionsCommitBean commitBean) {
		int liReturn = 0;
		List<OffenderSentConditions> insertList=new ArrayList<OffenderSentConditions>();
		try {
			if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
				List<OffenderSentConditions> resultData = ocdenforRepository
						.getExistingOffenderProceedingData(commitBean.getUpdateList().get(0));
				if (!resultData.isEmpty()) {
					ocdenforRepository.deleteExistingOffenderProceedingData(resultData);
				}

				for (OffenderSentConditions offenderSentConditions : commitBean.getUpdateList()) {
					if("Y".equals(offenderSentConditions.getLinkFlag())) {				
						BigDecimal offProceedingCondId = ocdenforRepository.getNextOffProceedingCondId();
						offenderSentConditions.setOffProceedingCondId(offProceedingCondId);
						offenderSentConditions.setCreateUserId(commitBean.getCreateUserId());
						insertList.add(offenderSentConditions);
					}
				}
				if(!insertList.isEmpty()) {				
					liReturn = ocdenforRepository.insertOffenderProceedingCondition(insertList);
				} else {
					liReturn =1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + " conditionProceedSave and Exception is : {}", e.getMessage());
		}
		return liReturn;
	}

	@Override
	public String getPersutHideShowValue(String code) {
		return ocdenforRepository.getPersutHideShowValue(code);
	}
	

	@Override
	public List<CommonLov> getProgram() {
		return repository.getProgram();
	}

}