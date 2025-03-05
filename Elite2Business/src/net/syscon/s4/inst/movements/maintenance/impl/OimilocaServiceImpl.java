package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyInternalLocationsCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movements.maintenance.OimilocaRepository;
import net.syscon.s4.inst.movements.maintenance.OimilocaService;
import net.syscon.s4.pkgs.tag_internal_locations.TagInternalLocationsService;
import net.syscon.s4.triggers.AgencyInternalLocationsT1Service;

/**
 * Class OimilocaServiceImpl
 */
@Service
public class OimilocaServiceImpl extends BaseBusiness implements OimilocaService {

	@Autowired
	private OimilocaRepository oimilocaRepository;
	@Autowired
	private TagInternalLocationsService tagInternalLocationsService;

	@Autowired
	private AgencyInternalLocationsT1Service agencyInternalLocationsT1Service;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLTennCheckDeleteMaster(final AgencyInternalLocations paramBean) {
		return oimilocaRepository.intLocLOneOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLTwoOnCheckDeleteMaster(final AgencyInternalLocations paramBean) {
		return oimilocaRepository.intLocLTwoOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<AgencyInternalLocations> intLocLThreeOnCheckDeleteMaster(final AgencyInternalLocations paramBean) {
		return oimilocaRepository.intLocLThreeOnCheckDeleteMaster(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<AgencyInternalLocations> intLocLOneExecuteQuery(final AgencyInternalLocations searchRecord) {
		return oimilocaRepository.intLocLOneExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstINT_LOC_L1
	 */
	@Transactional
	public AgencyInternalLocations intLocLOneCommit(final AgencyInternalLocationsCommitBean commitBean) {
		AgencyInternalLocations returnData = new AgencyInternalLocations();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<AgencyInternalLocations> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					for (AgencyInternalLocations obj : commitBean.getInsertList()) {
						obj.setCreateUserId(commitBean.getCreateUserId());

					}
					recordSavingObject = new ArrayList<>();
					final AgencyInternalLocations offPropItemObj = commitBean.getInsertList().get(i);
					final Integer internalLocationId = tagInternalLocationsService.getInternalLocationId();
					offPropItemObj.setInternalLocationId(internalLocationId);
					recordSavingObject.add(offPropItemObj);
					liReturn = oimilocaRepository.intLocLOneInsertAgencyInternalLocations(recordSavingObject);
					if (liReturn == 1) {
						returnData.setSealFlag("1");
					} else {
						returnData.setSealFlag("0");
					}
				}
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			AgencyInternalLocations oldRef = null;
			for (AgencyInternalLocations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				oldRef = oimilocaRepository.gettingOldData(obj.getInternalLocationId());

			}
			liReturn = oimilocaRepository.intLocLOneUpdateAgencyInternalLocations(commitBean.getUpdateList());

			if (commitBean.getUpdateList().size() > 0) {
				for (int i = 0; i < commitBean.getUpdateList().size(); i++) {
					final AgencyInternalLocations offPropItemObj = commitBean.getUpdateList().get(i);
					offPropItemObj.setCreateUserId(commitBean.getCreateUserId());
					if (!offPropItemObj.getActiveFlag().equals(offPropItemObj.getActiveFlagData())) {
						liReturn = tagInternalLocationsService.actDeactIntChildLocation(offPropItemObj);

					}
					if (!offPropItemObj.getTrackingFlag().equals(offPropItemObj.getTrackingFlagData())) {
						liReturn = tagInternalLocationsService.updateRelatedTrackingFlags(offPropItemObj);
					}
				}

			}
			for (AgencyInternalLocations newRef : commitBean.getUpdateList()) {
				agencyInternalLocationsT1Service.AgencyInternalLocationsT1(oldRef, newRef, null);
			}

			if (liReturn == 1) {
				returnData.setSealFlag("1");
			} else {
				returnData.setSealFlag("0");
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			returnData = oimilocaRepository.intLocLOneDeleteAgencyInternalLocations(commitBean.getDeleteList());
		}
		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<AgencyInternalLocations> intLocLTwoExecuteQuery(final AgencyInternalLocations searchRecord) {
		return oimilocaRepository.intLocLTwoExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(String userName) {
		List<AgencyLocations> returnList = oimilocaRepository.rgAgyLocRecordGroup(userName);
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
	public List<ReferenceCodes> rgLevelOneTypeRecordGroup() {
		final List<ReferenceCodes> returnList = oimilocaRepository.rgLevelTypeRecordGroup();
		returnList.forEach(action -> {
			action.setDescription(action.getCode());
		});
		return returnList;
	}

	public AgencyInternalLocations getResDescValues(final AgencyInternalLocations objSearchDao) {
		return tagInternalLocationsService.defaultLocationDescription(objSearchDao);

	}
	
	public List<ReferenceCodes> locationTypeLOVRecordGroup(String unitType) {
		String domain = null;
		if(unitType != null && !unitType.equalsIgnoreCase("null") && !unitType.equalsIgnoreCase("") && !unitType.equalsIgnoreCase("undefined")) {
			domain = "LIVING_UNIT";
		} else {
			domain = "ILOC_TYPE";
		}
		final List<ReferenceCodes> returnList = oimilocaRepository.locationTypeLOVRecordGroup(domain);
		returnList.forEach(action -> {
			if(action.getActiveFlag().equalsIgnoreCase("N"))
				action.setCanDisplay(false);
		});
		return returnList;
	}
	
}