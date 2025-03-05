package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.OidmpconRepository;
import net.syscon.s4.inst.property.OidmpconService;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.pkgs.oms_trigger_objects.OmsTriggerObjectsService;
import net.syscon.s4.triggers.OmtocontService;

/**
 * Class OidmpconServiceImpl
 */
@Service
public class OidmpconServiceImpl extends BaseBusiness implements OidmpconService {

	private static final Integer CELL_PPTY_CONTR_ID = 2;
	private static final Integer MISSING_PPTY_CONTR_ID = 1;
	@Autowired
	private OidmpconRepository oidmpconRepository;
	@Autowired
	private OmtocontService OmtocontService;

	@Autowired
	OmsTriggerObjectsService omsTriggerObjectsService;
	/**
	 * Creates new OidmpconServiceImpl class Object
	 */
	public OidmpconServiceImpl() {
		// OidmpconServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		final List<OffenderPptyContainers> offPptyContList = new ArrayList<>();
		return offPptyContList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers searchRecord) {

		AgencyInternalLocations agencyObj = new AgencyInternalLocations();
		List<OffenderPptyContainers> offPpptyConList = oidmpconRepository.offConExecuteQuery(searchRecord);

		// final Integer offenderBookId = offPpptyConList.get(0).getOffenderBookId();

		final Integer offenderBookId = searchRecord.getOffenderBookId();

		for (final OffenderPptyContainers offPpptyConDetails : offPpptyConList) {
			agencyObj.setInternalLocationId((offPpptyConDetails.getInternalLocationId() == null) ? 0
					: offPpptyConDetails.getInternalLocationId());
			agencyObj = oidmpconRepository.cgfkchkOffConOffConPpty(agencyObj);
			offPpptyConDetails.setDescription(agencyObj.getInternalLocationCode());
			final List<String> checkPptyValue = oidmpconRepository
					.checkPptyItems(offPpptyConDetails.getPropertyContainerId());
			final Integer containerVal = oidmpconRepository
					.checkContainerEmptyValue(offPpptyConDetails.getPropertyContainerId());
			if (!checkPptyValue.isEmpty()) {
				offPpptyConDetails.setPptyItemLength(checkPptyValue.size());
			} else {
				offPpptyConDetails.setPptyItemLength(0);
			}
			offPpptyConDetails.setContainerValue(containerVal);
		}

		// Add Two Additional Container for Missing and In Cell

		{
			OffenderPptyContainers offenderPptyContainers = new OffenderPptyContainers();

			offenderPptyContainers.setPropertyContainerId(MISSING_PPTY_CONTR_ID);
			/*offenderPptyContainers.setImages(oidmpconRepository.getImageForVirtualContainers(offenderBookId,
					offenderPptyContainers.getPropertyContainerId()));
*/
			offPpptyConList.add(offenderPptyContainers);
		}

		{
			OffenderPptyContainers offenderPptyContainers = new OffenderPptyContainers();

			offenderPptyContainers.setPropertyContainerId(CELL_PPTY_CONTR_ID);

			/*offenderPptyContainers.setImages(oidmpconRepository.getImageForVirtualContainers(offenderBookId,
					offenderPptyContainers.getPropertyContainerId()));*/

			offPpptyConList.add(offenderPptyContainers);
		}

		return offPpptyConList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CON
	 *
	 */
	@Transactional
	public Integer offConCommit(final OffenderPptyContainersCommitBean commitBean) {
		int liReturn = 0;
		for (final OffenderPptyContainers insertList : commitBean.getInsertList()) {
			if (insertList.getInternalLocationId() != null && insertList.getInternalLocationId() != 0) {
				final Integer storageValue = oidmpconRepository
						.checkStorageLocation(insertList.getInternalLocationId().toString());
				if (storageValue == 0) {
					liReturn = 5;
					return liReturn;

				}
			}

		}
		for (final OffenderPptyContainers updateList : commitBean.getUpdateList()) {
			if (updateList.getInternalLocationId() != null && updateList.getInternalLocationId() != 0) {
				final Integer storageValue = oidmpconRepository
						.checkStorageLocation(updateList.getInternalLocationId().toString());
				if (storageValue == 0) {
					liReturn = 5;
					return liReturn;
				}
			}

		}
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderPptyContainers obj : commitBean.getInsertList()) {
				// before each row
				obj.setPropertyContainerId(oidmpconRepository.getPropertyContainerId());
				OmtocontService.omtocontTrg(obj, "INSERTING");
			}
			liReturn = oidmpconRepository.offConInsertOffenderPptyContainers(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderPptyContainers obj : commitBean.getUpdateList()) {
				// before each row
				OmtocontService.omtocontTrg(obj, "UPDATING");
			}
			liReturn = oidmpconRepository.offConUpdateOffenderPptyContainers(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyInternalLocations> rgLocationAllRecordGroup(final String caseloadId) {
		List<AgencyInternalLocations> returnList;
		returnList = oidmpconRepository.rgLocationAllRecordGroup(caseloadId);
		for (final AgencyInternalLocations result : returnList) {
			if (result.getUsageLoc() == 0 && result.getCapacity() > result.getNoOfOccupant()
					&& result.getInternalLocationId() != 5350) {
				result.setCanDisplay(true);
			} else {
				result.setCanDisplay(false);
			}
		}
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

	@Override
	public List<String> findRgContainerCode() {
		return oidmpconRepository.findRgContainerCode();
	}

	@Override
	public Integer checkStorageLocation(final String internalLocId) {
		return oidmpconRepository.checkStorageLocation(internalLocId);
	}

	public List<String> checkPptyItems(final Integer propertyConId) {
		return oidmpconRepository.checkPptyItems(propertyConId);
	}

	public AgencyInternalLocations cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean) {
		return oidmpconRepository.cgfkchkOffConOffConPpty(paramBean);
	}

	@Override
	public Integer checkContainerEmptyValue(final Integer propertyConId) {
		return oidmpconRepository.checkContainerEmptyValue(propertyConId);
	}

	@Override
	public OffenderPptyContainers getSealMarkValueOfpropertyConId(final Integer propertyConId) {
		return oidmpconRepository.getSealMarkValueOfpropertyConId(propertyConId);
	}

	/*
	 * This method is used to get location value param agyLocId
	 */
	public AgencyInternalLocations getLocationValue(final String agyLocId) {
		return oidmpconRepository.getLocationValue(agyLocId);
	}

	/**
	 * Used to get the Record group values for location
	 * 
	 * @param parentField
	 * @return recordList
	 */
	public List<AgencyInternalLocations> getLocationValuesOfLov(final String parentField) {
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		if (parentField.contains(",")) {
			final String[] arr = parentField.split(",");
			final String internalLocationId = arr[0].trim();
			final String offBkgId = arr[1].trim();
			final String caseloadId = arr[2].trim();
			if (!"undefined".equals(internalLocationId) && !"null".equals(internalLocationId)) {
				final String returnObj = oidmpconRepository.lvGetAgyLoc(offBkgId, internalLocationId);
				returnList = oidmpconRepository.rgStoreLocation(returnObj);
				for (final AgencyInternalLocations result : returnList) {
					if (result.getUsageLoc() == 0 && result.getCapacity() > result.getNoOfOccupant()
							&& result.getInternalLocationId() != 5350) {
						result.setCanDisplay(true);
					} else {
						result.setCanDisplay(false);
					}
				}
			} else {
				if ("test".equals(offBkgId)) {
					returnList = rgLocationAllRecordGroup(caseloadId);
					if(Optional.ofNullable(returnList).isPresent()) {
						returnList.forEach(refcode->{
							if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
								refcode.setCanDisplay(true);
							} else {
								refcode.setCanDisplay(false);
							}
						});
					}

				} else {
					returnList = oidmpconRepository.rgDescription(caseloadId);
					for (final AgencyInternalLocations result : returnList) {
						if (result.getUsageLoc() == 0 && result.getCapacity() > result.getNoOfOccupant()
								&& result.getInternalLocationId() != 5350) {
							result.setCanDisplay(true);
						} else {
							result.setCanDisplay(false);
						}
					}
				}
			}
		}
		return returnList;
	}

	public List<AgencyInternalLocations> getAgyLocationValuesOfLov(final String agyLocId,final String caseloadId) {
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		AgencyInternalLocations listOfRecords = getLocationValue(agyLocId);
		if(listOfRecords!=null && listOfRecords.getInternalLocationId()!=null ) {
			if (!"undefined".equals(agyLocId) && !"null".equals(agyLocId)) {
				returnList = oidmpconRepository.rgStoreLocation(agyLocId);
				for (final AgencyInternalLocations result : returnList) {
					if (result.getUsageLoc() == 0 && result.getCapacity() > result.getNoOfOccupant()
							&& result.getInternalLocationId() != 5350) {
						result.setCanDisplay(true);
					} else {
						result.setCanDisplay(false);
					}
				}
			
		}
		}else {
			returnList = oidmpconRepository.rgDescription(caseloadId);
			for (final AgencyInternalLocations result : returnList) {
				if (result.getUsageLoc() == 0 && result.getCapacity() > result.getNoOfOccupant()
						&& result.getInternalLocationId() != 5350) {
					result.setCanDisplay(true);
				} else {
					result.setCanDisplay(false);
				}
			}
		}
		return returnList;
	}
	
	public Integer insertContainerImg(OffenderPptyContainers offenderPptyContainers) {
		return oidmpconRepository.insertContainerImg(offenderPptyContainers);

	}

	@Override
	public int offConUpdateSeal(OffenderPptyContainers commitBean) {
		// before each row
		if (commitBean != null && commitBean.getPropertyContainerId() != null) {
			OmtocontService.omtocontTrg(commitBean, "UPDATING");
		}
		return oidmpconRepository.offConUpdateSeal(commitBean);
	}

	@Override
	public int offConUpdateMultipleSeal(List<OffenderPptyContainers> commitBean) {
		return oidmpconRepository.offConUpdateMultipleSeal(commitBean);
	}

	@Override
	public Integer updateConatinerIntLocation(OffenderPptyContainers offContObj) {
		Integer result=null;
		result=oidmpconRepository.updateContainerLocation(offContObj);
		if(result!=null && result>0) {
			result=InsertOffContTranscationData(offContObj);
		}
		return result;
	}
	
	
	private Integer InsertOffContTranscationData(OffenderPptyContainers offContObj) {
		OffenderPptyConTxns transcObj=new OffenderPptyConTxns();
		transcObj.setPropertyContainerId(offContObj.getPropertyContainerId());
		transcObj.setActionCode("TR-INT");
		transcObj.setSealMark(offContObj.getSealMark());
		transcObj.setAgyLocId(offContObj.getAgyLocId());
		transcObj.setCommentText(offContObj.getCommentText());
		transcObj.setInternalLocationId(offContObj.getInternalLocationId());
		transcObj.setTrnFromAgyLocId(null);
		transcObj.setTrnToAgyLocId(null);
		transcObj.setCreateUserId(offContObj.getCreateUserId());
		// inserting data into OFFENDER_PPTY_CON_TXNS table
		return omsTriggerObjectsService.createContainerTransaction(transcObj);
	}
	
	@Override
	public List<AgencyInternalLocations> getAllLocations(final String caseloadId) {
		List<AgencyInternalLocations> returnList;
		returnList = oidmpconRepository.getAllLocations(caseloadId);
		for (final AgencyInternalLocations result : returnList) {
			if (result.getUsageLoc() == 0 && result.getCapacity() > result.getNoOfOccupant()
					&& result.getInternalLocationId()!= 5350) {
				result.setCanDisplay(true);
			} else {
				result.setCanDisplay(false);
			}
		}
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