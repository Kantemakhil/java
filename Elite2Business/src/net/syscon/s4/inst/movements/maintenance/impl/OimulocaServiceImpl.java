package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movements.maintenance.OimulocaRepository;
import net.syscon.s4.inst.movements.maintenance.OimulocaService;
import net.syscon.s4.inst.property.bean.InternalLocationUsages;
import net.syscon.s4.inst.property.bean.InternalLocationUsagesCommitBean;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocationsCommitBean;
import net.syscon.s4.pkgs.tag_internal_locations.TagInternalLocationsService;

/**
 * Class OimulocaServiceImpl
 */
@Service
public class OimulocaServiceImpl extends BaseBusiness implements OimulocaService {

	@Autowired
	private OimulocaRepository oimulocaRepository;
	
	@Autowired
	private TagInternalLocationsService tahInternalLocSrvice;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<InternalLocationUsages> usagesExecuteQuery(final InternalLocationUsages searchRecord) {
		final List<InternalLocationUsages> returnList = oimulocaRepository.usagesExecuteQuery(searchRecord);
		for (final InternalLocationUsages bean : returnList) {
			if (bean.getInternalLocationUsage() != null) {
				final ReferenceCodes returnVal = oimulocaRepository.usagesPostQuery(bean);
				if (returnVal.getCode() != null) {
					bean.setChkPermMov(returnVal.getCode());
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstUSAGES
	 */
	@Transactional
	public Integer usagesCommit(final InternalLocationUsagesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final InternalLocationUsages bean : commitBean.getInsertList()) {
				//adding user to bean
				bean.setCreateUserId(commitBean.getCreateUserId());
				if ("Y".equals(bean.getChkPermMov())) {
					final Integer returnValue = oimulocaRepository.usagePreInsert(bean);
					if (returnValue == 1) {
						final String returnObject = oimulocaRepository.usagesInsertInternalLocationUsages(bean);
						final Integer validate = displayMsgs(returnObject);
						if (validate == 1) {
							liReturn = 1;
						} else {
							return validate;
						}
					}
				} else {
					final String returnObject = oimulocaRepository.usagesInsertInternalLocationUsages(bean);
					final Integer validate = displayMsgs(returnObject);
					if (validate == 1) {
						liReturn = 1;
					} else {
						return validate;
					}
				}

			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final InternalLocationUsages bean : commitBean.getUpdateList()) {
				//adding user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
				final ReferenceCodes returnObj = oimulocaRepository.usagesPreUpdate(bean);
				if (!returnObj.getCode().equals(bean.getChkPermMov())) {
					final Integer returnValue = oimulocaRepository.usagesPreUpdateQuery(bean);
					if (returnValue == 1) {
						liReturn = oimulocaRepository.usagesUpdateInternalLocationUsages(bean);
					}
				} else {
					liReturn = oimulocaRepository.usagesUpdateInternalLocationUsages(bean);
				}
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<IntLocUsageLocations> intLocL1ExecuteQuery(final IntLocUsageLocations searchRecord) {
		final List<IntLocUsageLocations> returnList = oimulocaRepository.intLocL1ExecuteQuery(searchRecord);
		for (final IntLocUsageLocations bean : returnList) {
			final AgencyInternalLocations returnObj = oimulocaRepository
					.getInternalLocationRecords(bean.getInternalLocationId());
			if(returnObj != null) {
				bean.setLocCode(returnObj.getInternalLocationCode());
				bean.setLocDescription(returnObj.getDescription());
				bean.setUserDescription(returnObj.getUserDesc());
			}
			
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstINT_LOC_L1
	 */
	@Transactional
	public Integer intLocL1Commit(final IntLocUsageLocationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (IntLocUsageLocations intLoc : commitBean.getInsertList()) {
				intLoc.setCreateUserId(commitBean.getCreateUserId());
			}
			final String returnValue = oimulocaRepository
					.intLocL1InsertIntLocUsageLocations(commitBean.getInsertList());
			final Integer validate = displayMsgs(returnValue);
			if (validate == 1) {
				liReturn = 1;
			} else {
				return validate;
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IntLocUsageLocations intLoc : commitBean.getUpdateList()) {
				intLoc.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimulocaRepository.intLocL1UpdateIntLocUsageLocations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			final List<Integer> listObj = commitBean.getDeleteList().stream().map(data -> data.getUsageLocationId())
					.collect(Collectors.toList());
			final Integer count = oimulocaRepository.intLocL1OnCheckDeleteMaster(listObj);
			if (count > 0) {
				return 6;
			}
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimulocaRepository.intLocL1DeleteIntLocUsageLocations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<IntLocUsageLocations> intLocL2ExecuteQuery(final IntLocUsageLocations searchRecord) {
		return oimulocaRepository.intLocL1ExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgLocationUsageRecordGroup() {
		return oimulocaRepository.rgLocationUsageRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseLoadId) {
		List<AgencyLocations> returnList = oimulocaRepository.rgAgyLocRecordGroup(caseLoadId);
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgLevelTypeRecordGroup() {
		List<ReferenceCodes> refList = oimulocaRepository.rgLevelTypeRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

	
	//procedure method integration
	public AgencyInternalLocations getInternalLocationRecords(final Integer intLocId) {
		return tahInternalLocSrvice.getInternalLocationRecord(intLocId);
	}

	public Integer displayMsgs(final String value) {
		if ("1".equals(value)) {
			return 1;
		} else if (value.contains("INT_LOC_USAGE_LOCATIONS_UK")) {
			return 2;
		} else if (value.contains("AGY_INT_LOC_USAGES_PK")) {
			return 3;
		} else if (value.contains("INTERNAL_LOCATION_USAGES_UK")) {
			return 4;
		} else if (value.contains("INTERNAL_LOCATION_USAGES_UI1")) {
			return 5;
		} else {
			return 0;
		}

	}
	
	public List<AgencyInternalLocations> intLocOneLov(final String agyLocId) {
		 List<AgencyInternalLocations> returnList = oimulocaRepository.intLocOneLov(agyLocId);
		return filterAgyIntLoc(returnList);
		}

	
	public List<AgencyInternalLocations> intLocTwoLov(final String agyIntLocId) {
		 List<AgencyInternalLocations> returnList = new ArrayList<>();
		if(agyIntLocId != null && agyIntLocId.contains("-")) {
			final String[] inputArray = agyIntLocId.split("-");
			returnList = oimulocaRepository.intLocTwoLov(Integer.valueOf(inputArray[0]),inputArray[1]);
		}
		return filterAgyIntLoc(returnList);
	}
	
	private List<AgencyInternalLocations> filterAgyIntLoc(List<AgencyInternalLocations> returnList) {
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}
	

}