package net.syscon.s4.inst.automatedcounts.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocationCountsCommitBean;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.automatedcounts.OidremcsRepository;
import net.syscon.s4.inst.automatedcounts.OidremcsService;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.triggers.AgencyLocationCountsT1Service;

/**
 * Class OidremcsServiceImpl
 */
@Service
public class OidremcsServiceImpl extends BaseBusiness implements OidremcsService {

	@Autowired
	private OidremcsRepository oidremcsRepository;

	@Autowired
	private AgencyLocationCountsT1Service agencyLocationCountsT1Service;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AgencyLocationCounts> subRemCntExecuteQuery(AgencyLocationCounts searchRecord) {
		return oidremcsRepository.subRemCntExecuteQuery(searchRecord);

	}

	public Integer subRemCntUpdatesubRemCnt(final List<AgencyLocationCounts> updateList, String createUserId) {
		Integer lireturn = 0;
		Integer agySeq = 0;
		String rcntInProgressFlag = null;
		for (final AgencyLocationCounts agencyLocationCounts : updateList) {
			final List<AgencyLocationCounts> updatedList = new ArrayList<>();
			agencyLocationCounts.setModifyUserId(createUserId);
			if (agencyLocationCounts.getLivingUnitId() != null) {
				if (agencyLocationCounts.getLivingUnitId() != null && agencyLocationCounts.getLivingUnitId2() != null) {
					oidremcsRepository.checklcheck(agencyLocationCounts.getLivingUnitId(),
							agencyLocationCounts.getLivingUnitId2());
				}
				agySeq = oidremcsRepository.getAgySeq(agencyLocationCounts);
				if (agySeq != 0) {
					agencyLocationCounts.setAgySeq(agySeq);
					agencyLocationCounts.setReportingLocId(agencyLocationCounts.getReportingLocId());
				}
			} else {
				agySeq = oidremcsRepository.getInternalLoc(agencyLocationCounts);
				if (agySeq != 0) {
					agencyLocationCounts.setAgySeq(agySeq);
				}
			}
			final String checkFlag = oidremcsRepository.checkLocValidation(agencyLocationCounts);
			if ("Y".equals(checkFlag)) {
				String lCheckValueData = oidremcsRepository.getCountLcheckProc(agencyLocationCounts);
				if ("Y".equals(lCheckValueData)) {
					List<AgencyLocationCounts> returnData = new ArrayList<AgencyLocationCounts>();
					returnData.add(agencyLocationCounts);
					lireturn = oidremcsRepository.resubmitCountDeleteQuery(returnData);
					callAgencyLocationCountsT1Trigger(agencyLocationCounts, rcntInProgressFlag, createUserId);
				} else {
					return lireturn = 2;
				}
			}
			if (agencyLocationCounts.getCountTypeId() != null && agencyLocationCounts.getReportingLocId() != null && agencyLocationCounts.getAgySeq() != null) {
				updatedList.add(agencyLocationCounts);
				lireturn = oidremcsRepository.subRemCntUpdatesubRemCnt(updatedList);
				callAgencyLocationCountsT1Trigger(agencyLocationCounts, rcntInProgressFlag, createUserId);
			} else {
				lireturn = 3;
			}
		}
		return lireturn;
	}
	
	public Integer callAgencyLocationCountsT1Trigger(AgencyLocationCounts agencyLocationCounts, String rcntInProgressFlag, String createUserId) {
		TempOidcount tocbean = new TempOidcount();
		tocbean.setAgySeq(agencyLocationCounts.getAgySeq());
		tocbean.setCountTypeId(agencyLocationCounts.getCountTypeId());
		tocbean.setReportingLocId(agencyLocationCounts.getReportingLocId());
		tocbean.setDateSubmitted(agencyLocationCounts.getDateSubmitted());
		tocbean.setReportedCount(agencyLocationCounts.getReportedCount());
		tocbean.setEnteredByUserid(createUserId);
		tocbean.setActualCount(agencyLocationCounts.getActualCount());
		tocbean.setModifyUserId(createUserId);
		return agencyLocationCountsT1Service.agencyLocationCountsT1Trigger(tocbean, rcntInProgressFlag);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSUB_REM_CNT
	 *
	 * 
	 */
	@Transactional
	public Integer subRemCntCommit(final AgencyLocationCountsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = oidremcsRepository.subRemCntInsertsubRemCnt(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = subRemCntUpdatesubRemCnt(commitBean.getUpdateList(), commitBean.getCreateUserId());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final Integer countTypeId) {
		final List<LivingUnits> returnList = oidremcsRepository.cgfkHousingLevel1RecordGroup(countTypeId);
		returnList.forEach(result -> {
			result.setCode(result.getDescription());
			result.setDescription(result.getLivingUnitCode());
		});

		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final Integer countTypeId, final Integer livingUnitId) {
		final List<LivingUnits> returnList = oidremcsRepository.cgfkHousingLevel2RecordGroup(countTypeId, livingUnitId);
		returnList.forEach(result -> {
			result.setCode(result.getDescription());
			result.setDescription(result.getLivingUnitCode());

		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(final Integer countTypeId, final Integer livingUnitId) {
		final List<LivingUnits> returnList = oidremcsRepository.cgfkHousingLevel3RecordGroup(countTypeId, livingUnitId);
		returnList.forEach(result -> {
			result.setCode(result.getLivingUnitCode());
			result.setDescription(result.getDescription());

		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> cgfkInitLocCodeRecordGroup(final Integer countTypeId) {

		final List<LivingUnits> returnList = oidremcsRepository.cgfkInitLocCodeRecordGroup(countTypeId);
		returnList.forEach(result -> {
			result.setCode(result.getLocationCode());
			result.setDescription(result.getDescription());

		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<StaffMembers> cgfkConductedByRecordGroup() {
		Integer listSeq = 1;
		final List<StaffMembers> returnList = oidremcsRepository.cgfkConductedByRecordGroup();
		for (StaffMembers result : returnList) {
			result.setListSeq(listSeq++);
			result.setCode(result.getStaffId());
			if (result.getUserId() == null) {
				result.setDescription("");
			} else {
				result.setDescription(result.getUserId());
			}
			result.setTitle(result.getLastName());
		}
		return returnList;
	}

	@Override
	public Map<String, Object> changeHousingLevelOne(final Integer livingUnitId) {
		Map<String, Object> map = new HashedMap<>();
		int actualCount = oidremcsRepository.getActualCount(livingUnitId);
		int lChk = oidremcsRepository.getAgencyReportingLocsOne(livingUnitId);
		String locTwoid = oidremcsRepository.getAgencyReportingLocsTwo(livingUnitId);
		if (locTwoid != null && lChk == 0) {
			map.put("housingLevel", true);
		} else {
			map.put("housingLevel", false);
			map.put("actualCount", actualCount);
		}
		return map;
	}
	
	@Override
	public Map<String, Object> changeHousingLevelTwo(Integer livingUnitIdOne, Integer livingUnitIdTwo) {
		Map<String, Object> map = new HashedMap<>();
		int actualCount = oidremcsRepository.getActualCount(livingUnitIdTwo);
		int lChk = oidremcsRepository.getAgencyReportingLocsThree(livingUnitIdOne, livingUnitIdTwo);
		String locTwoid = oidremcsRepository.getAgencyReportingLocsFour(livingUnitIdOne, livingUnitIdTwo);
		if (locTwoid != null && lChk == 0) {
			map.put("housingLevel", true);
		} else {
			map.put("housingLevel", false);
			map.put("actualCount", actualCount);
		}
		return map;
	}

	@Override
	public Integer getInternalLocationCount(final Integer livingUnitId) {
		return oidremcsRepository.getInternalLocationCount(livingUnitId);
	}

	@Override
	public Map<String, Object> getHousingLocationLovNames(Integer countTypeId) {
		Map<String, Object> lovNames = oidremcsRepository.getHousingLocationLovNames(countTypeId);
		Map<String, Object> returnData = lovNames.entrySet().stream()
	            .collect(Collectors.toMap(entry -> entry.getKey().toUpperCase(), entry -> entry.getValue()));
		if (returnData.size() != 0) {
			if (returnData.get("HOUSINGLEVONELABLENAME") != null && !returnData.get("HOUSINGLEVONELABLENAME").equals("")) {
				String dataOne = oidremcsRepository.getLabelDescription((String) returnData.get("HOUSINGLEVONELABLENAME"));
				if (dataOne != null) {
					returnData.put("HOUSINGLEVONELABLENAME", dataOne);
				}
			}
			if (returnData.get("HOUSINGLEVTWOLABLENAME") != null && !returnData.get("HOUSINGLEVTWOLABLENAME").equals("")) {
				String dataTwo = oidremcsRepository.getLabelDescription((String) returnData.get("HOUSINGLEVTWOLABLENAME"));
				if (dataTwo != null) {
					returnData.put("HOUSINGLEVTWOLABLENAME", dataTwo);
				}
			}
			if (returnData.get("HOUSINGLEVTHREELABLENAME") != null && !returnData.get("HOUSINGLEVTHREELABLENAME").equals("")) {
				String dataThree = oidremcsRepository.getLabelDescription((String) returnData.get("HOUSINGLEVTHREELABLENAME"));
				if (dataThree != null) {
					returnData.put("HOUSINGLEVTHREELABLENAME", dataThree);
				}
			}
		}
		return returnData;
	}

	@Override
	public int calculateActualCount(Integer livingUnitId) {
		return oidremcsRepository.getActualCount(livingUnitId);
	}

}
