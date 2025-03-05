package net.syscon.s4.inst.movements.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.AgyIntLocProfilesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumaglocRepository;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.LivingUnitsCommitBean;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.movements.maintenance.OimmholoRepository;
import net.syscon.s4.inst.movements.maintenance.OimmholoService;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.pkgs.oimmholo.OimmholoPkgService;
import net.syscon.s4.triggers.AgencyInternalLocationsT1Service;
import net.syscon.s4.triggers.AgyIntLocAmendments;
import net.syscon.s4.triggers.AgyIntLocProfilesT1Service;

/**
 * Class OimmholoServiceImpl
 */
@Service
public class OimmholoServiceImpl extends BaseBusiness implements OimmholoService {

	@Autowired
	private OimmholoRepository oimmholoRepository;

	@Autowired
	private OimmholoPkgService oimmholoService;

	@Autowired
	private AgyIntLocProfilesT1Service agyIntLocProfilesT1Service;

	@Autowired
	private AgencyInternalLocationsT1Service agencyInternalLocationsT1Service;
	
	@Autowired
	private OumaglocRepository oumaglocRepository;



	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<LivingUnits> livUnitsExecuteQuery(final LivingUnits searchRecord) {
		return oimmholoRepository.livUnitsExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<LivingUnits> livUnitsDialogExecuteQuery(final LivingUnits searchRecord) {
		return oimmholoRepository.livUnitsDialogExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstLIV_UNITS
	 */
	@Transactional
	public String livUnitsCommit(final LivingUnitsCommitBean commitBean) {
		String liReturn = null;
		List<AgencyInternalLocations> newInsertList=new ArrayList<AgencyInternalLocations>();

		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<LivingUnits> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final LivingUnits offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offenderPropertyItemObj.setUserDesc(offenderPropertyItemObj.getDescription());
					final BigDecimal livingUnitId = oimmholoRepository.getLivingUnitId();
					offenderPropertyItemObj.setLivingUnitId(livingUnitId);
					if (offenderPropertyItemObj.getLuType() != null) {
						final Long currenLevel = oimmholoRepository.getCurrentLevel(livingUnitId);
						if (commitBean.getHousingLevel() != null) {
							final String livimgUnitType = oimmholoRepository.getNewLuType(commitBean.getHousingLevel(),
									offenderPropertyItemObj.getAgyLocId());
							if (livimgUnitType == null) {
								liReturn = "2";
								return liReturn;
							}
							offenderPropertyItemObj.setLivingUnitType(livimgUnitType);
							offenderPropertyItemObj.setLuType(null);
							offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
						}
					} else {
						liReturn = "2";
						return liReturn;
					}
					if (offenderPropertyItemObj.getNoOfOccupant() == null) {
						offenderPropertyItemObj.setNoOfOccupant(BigDecimal.ZERO);
					}
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oimmholoRepository.livUnitsInsertLivingUnits(recordSavingObject);
					AgencyInternalLocations bean=new AgencyInternalLocations();
					for(LivingUnits newBean:recordSavingObject) {						
						AgencyInternalLocations obj=new AgencyInternalLocations();
						obj.setAgyLocId(newBean.getAgyLocId());
						obj.setInternalLocationId(newBean.getLivingUnitId()!=null?newBean.getLivingUnitId().intValue():null);
						obj.setCreateUserId(commitBean.getCreateUserId());
						obj.setInternalLocationCode(newBean.getLivingUnitCode());
						obj.setInternalLocationType(newBean.getLivingUnitType());
						obj.setDescription(newBean.getDescription());
						obj.setUserDesc(newBean.getUserDesc());
						obj.setAcaCapRating(newBean.getAcaCapRating()!=null?newBean.getAcaCapRating().intValue():null);
						obj.setSecurityLevelCode(newBean.getSecurityLevelCode());
						obj.setListSeq(newBean.getListSeq()!=null?newBean.getListSeq().intValue():null);
						obj.setParentInternalLocationId(newBean.getParentLivingUnitId()!=null?newBean.getParentLivingUnitId().intValue():null);
						//HOUSING_UNIT_TYPE
						obj.setActiveFlag(newBean.getActiveFlag());
						//CONTROL_ACTIVE_FLAG
						obj.setCnaNo(newBean.getCnaNo()!=null?newBean.getCnaNo().intValue():null);
						obj.setCapacity(newBean.getCapacity()!=null?newBean.getCapacity().intValue():null);
						obj.setOperationCapacity(newBean.getOperationCapacity()!=null?newBean.getOperationCapacity().intValue():null);
						obj.setCertifiedFlag(newBean.getCertifiedFlag());
						obj.setDeactivateDate(newBean.getDeactivateDate());
						obj.setReactivateDate(newBean.getReactivateDate());
						obj.setDeactiveReasonCode(newBean.getDeactivateReasonCode());
						obj.setCommentText(newBean.getCommentText());
						//LOWEST_LEVEL_FLAG
						//REACH_OPER_CAPACITY_FLAG
						obj.setNoOfOccupant(newBean.getNoOfOccupant()!=null?newBean.getNoOfOccupant().intValue():null);
						obj.setTrackingFlag("N");
						AgencyInternalLocations oldData=oimmholoRepository.getOldDataAgencyInternalLocation(newBean.getLivingUnitId().longValue());
						if (oldData.getParentInternalLocationId() !=null) {
							obj.setCnaNo(0);
						}
						agencyInternalLocationsT1Service.AgencyInternalLocationsT1(oldData, obj,obj.getCode());	
					}
					if (liReturn != null && liReturn.length() > 1) {
						return liReturn;
					}
					// oimmholoRepository.updateParentCapAndCna(offenderPropertyItemObj.getLivingUnitId());
					oimmholoService.updateParentCapAndCna(offenderPropertyItemObj.getLivingUnitId(),commitBean.getCreateUserId());

				}
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<LivingUnits> recordSavingObject = new ArrayList<>();
			List<AgencyInternalLocations> oldDtaList=new ArrayList<AgencyInternalLocations>();
			List<AgencyInternalLocations> newUpdateList=new ArrayList<AgencyInternalLocations>();
			if (commitBean.getUpdateList().size() > 0) {
				for (int i = 0; i < commitBean.getUpdateList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final LivingUnits offenderPropertyItemObj = commitBean.getUpdateList().get(i);
					AgencyInternalLocations oldData=oimmholoRepository.getOldDataAgencyInternalLocation(offenderPropertyItemObj.getLivingUnitId().longValue());
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderPropertyItemObj);
					oldDtaList.add(oldData);
					liReturn = oimmholoRepository.livUnitsUpdateLivingUnits(recordSavingObject);
					if(liReturn.equals("1")) {
						oimmholoRepository.updateChildLivingUnits(offenderPropertyItemObj);
					}
					AgyIntLocAmendments bean=new AgyIntLocAmendments();
					for(LivingUnits newBean:recordSavingObject) {	
						AgencyInternalLocations obj=new AgencyInternalLocations();
						obj.setAgyLocId(newBean.getAgyLocId());
						obj.setInternalLocationId(newBean.getLivingUnitId()!=null?newBean.getLivingUnitId().intValue():null);
						obj.setCreateUserId(commitBean.getCreateUserId());
						obj.setInternalLocationCode(newBean.getLivingUnitCode());
						obj.setInternalLocationType(newBean.getLivingUnitType());
						obj.setDescription(newBean.getDescription());
						obj.setUserDesc(newBean.getUserDesc());
						obj.setAcaCapRating(newBean.getAcaCapRating()!=null?newBean.getAcaCapRating().intValue():null);
						obj.setSecurityLevelCode(newBean.getSecurityLevelCode());
						obj.setListSeq(newBean.getListSeq()!=null?newBean.getListSeq().intValue():null);
						obj.setParentInternalLocationId(newBean.getParentLivingUnitId()!=null?newBean.getParentLivingUnitId().intValue():null);
						obj.setUnitType(newBean.getHousingUnitType());
						obj.setActiveFlag(newBean.getActiveFlag());
						//CONTROL_ACTIVE_FLAG
						obj.setCnaNo(newBean.getCnaNo()!=null?newBean.getCnaNo().intValue():null);
						obj.setCapacity(newBean.getCapacity()!=null?newBean.getCapacity().intValue():null);
						obj.setOperationCapacity(newBean.getOperationCapacity()!=null?newBean.getOperationCapacity().intValue():null);
						obj.setCertifiedFlag(newBean.getCertifiedFlag());
						obj.setDeactivateDate(newBean.getDeactivateDate());
						obj.setReactivateDate(newBean.getReactivateDate());
						obj.setDeactiveReasonCode(newBean.getDeactivateReasonCode());
						obj.setCommentText(newBean.getCommentText());
						//LOWEST_LEVEL_FLAG
						//REACH_OPER_CAPACITY_FLAG
						obj.setNoOfOccupant(newBean.getNoOfOccupant()!=null?newBean.getNoOfOccupant().intValue():null);
						obj.setTrackingFlag("N");
						//obj.setCnaNo(0);
						newUpdateList.add(obj);
					}
					agencyInternalLocationsT1Service.AgencyInternalLocationsT1(oldDtaList.get(i), newUpdateList.get(i),newUpdateList.get(i).getCode());	
					//oimmholoRepository.updateParentCapAndCna(offenderPropertyItemObj.getLivingUnitId());
					oimmholoService.updateParentCapAndCna(offenderPropertyItemObj.getLivingUnitId(),commitBean.getCreateUserId());

					if (!"UPDATE".equals(offenderPropertyItemObj.getCode())) {
						//oimmholoRepository.actDeactChildLu(offenderPropertyItemObj);
						oimmholoService.actDeactChildLu(offenderPropertyItemObj);

					}
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
	public List<AgyIntLocProfiles> usedForExecuteQuery(final AgyIntLocProfiles searchRecord) {
		return oimmholoRepository.usedForExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstUSED_FOR
	 */
	@Transactional
	public Integer usedForCommit(final AgyIntLocProfilesCommitBean commitBean) {
		int liReturn = 0;
		AgyIntLocAmendments newRef= new AgyIntLocAmendments();
		AgyIntLocAmendments old=new AgyIntLocAmendments();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AgyIntLocProfiles agyIntLoc : commitBean.getInsertList()) {
				//aading user to bean
				agyIntLoc.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimmholoRepository.usedForInsertAgyIntLocProfiles(commitBean.getInsertList());
			String operationType="INSERTING";
			for (AgyIntLocProfiles agyIntLoc : commitBean.getInsertList()) {
				newRef.setInternalLocationId(agyIntLoc.getInternalLocationId());
				newRef.setIntLocProfileType(agyIntLoc.getIntLocProfileType());
				newRef.setIntLocProfileCode(agyIntLoc.getIntLocProfileCode());
				newRef.setAmendUserId(agyIntLoc.getCreateUserId());
				agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger(operationType, old,newRef );

			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<AgyIntLocProfiles> agyIntLocProfiles =new ArrayList<AgyIntLocProfiles>();
			for (AgyIntLocProfiles agyIntLoc : commitBean.getUpdateList()) {
				//aading user to bean
				agyIntLoc.setModifyUserId(commitBean.getCreateUserId());
				agyIntLocProfiles = oimmholoRepository.gettingOldDataFromAgyIntLocAmendments(agyIntLoc.getRowId());
			}

			liReturn = oimmholoRepository.usedForUpdateAgyIntLocProfiles(commitBean.getUpdateList());
			String operationType="UPDATING";
			for(AgyIntLocProfiles oldVal:agyIntLocProfiles) {
				old.setInternalLocationId(oldVal.getInternalLocationId());
				old.setIntLocProfileType(oldVal.getIntLocProfileType());
				old.setIntLocProfileCode(oldVal.getIntLocProfileCode());
				for (AgyIntLocProfiles obj :  commitBean.getUpdateList()) {
					newRef.setInternalLocationId(obj.getInternalLocationId());
					newRef.setIntLocProfileType(obj.getIntLocProfileType());
					newRef.setIntLocProfileCode(obj.getIntLocProfileCode());
					newRef.setAmendUserId(obj.getCreateUserId());
					if(obj.getRowId().equals(oldVal.getRowId())) {
						agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger(operationType, old,newRef );
					}
				}

			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimmholoRepository.usedForDeleteAgyIntLocProfiles(commitBean.getDeleteList());
			String operationType="DELETING";
			for (AgyIntLocProfiles agyIntLoc : commitBean.getDeleteList()) {
				old.setInternalLocationId(agyIntLoc.getInternalLocationId());
				old.setIntLocProfileType(agyIntLoc.getIntLocProfileType());
				old.setIntLocProfileCode(agyIntLoc.getIntLocProfileCode());
				old.setAmendUserId(agyIntLoc.getCreateUserId());
				agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger(operationType,old ,newRef );
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<LivingUnitProfiles> luProfExecuteQuery(final LivingUnitProfiles searchRecord) {
		return oimmholoRepository.luProfExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AgencyLocations> rgAgyLocLovRecordGroup(String userName) {
		 List<AgencyLocations> agyLocList = oimmholoRepository.rgAgyLocLovRecordGroup(userName);
		if(Optional.ofNullable(agyLocList).isPresent()) {
			agyLocList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return agyLocList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgDeactLuRsnRecordGroup() {
		return oimmholoRepository.rgDeactLuRsnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgUsedForRecordGroup() {
		return oimmholoRepository.rgUsedForRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgHouUnitAttRecordGroup() {
		return oimmholoRepository.rgHouUnitAttRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgNonAssoTypeRecordGroup() {
		return oimmholoRepository.rgNonAssoTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AssessmentResults> rgSupLvlTypeRecordGroup() {
		List<AssessmentResults> resultList = oimmholoRepository.rgSupLvlTypeRecordGroup();
		if(Optional.ofNullable(resultList).isPresent()) {
			resultList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return resultList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgHouUnTypeRecordGroup() {
		return oimmholoRepository.rgHouUnTypeRecordGroup();

	}

	public String cellBlockData(final LivingUnits searchdao) {
		String returnData = null;
		//final String cellData = oimmholoRepository.cellBlockData(searchdao);
		final String cellData =oimmholoService.getNewLuType(searchdao);

		if (cellData != null) {
			returnData = oimmholoRepository.getCellDescription(cellData);
		}
		return returnData;
	}

	public LivingUnits getResDescValues(final LivingUnits objSearchDao) {
		//	return oimmholoRepository.getResDescValues(objSearchDao);
		return oimmholoService.defaultLivingUnitDesc(objSearchDao);

	}

	public Long getActiveFlagValidation(final Integer livingUintId) {
		return oimmholoRepository.getActiveFlagValidation(livingUintId);
	}

	public Long getFlagValidation(final Integer livingUintId) {
		return oimmholoRepository.getFlagValidation(livingUintId);
	}

	public Long checkInheritAttributes(final Integer livingUintId) {
		return oimmholoRepository.checkInheritAttributes(livingUintId);
	}

	public String attributsData(final Integer livingUintId) {
		String attributes = "Used For: \n";
		final List<LivingUnitProfiles> attributesData = oimmholoRepository.attributesData(livingUintId, "HOU_USED_FOR");
		for (final LivingUnitProfiles profileType : attributesData) {
			if (profileType.getDescription() != null) {
				attributes = attributes + profileType.getDescription() + "\n";
			}
		}
		attributes = attributes + "\n";
		attributes = attributes + "Unit Attributes: \n";
		final List<LivingUnitProfiles> attributesDataOne = oimmholoRepository.attributesData(livingUintId,
				"HOU_UNIT_ATT");
		for (final LivingUnitProfiles profileType : attributesDataOne) {
			if (profileType.getDescription() != null) {
				attributes = attributes + profileType.getDescription() + "\n";
			}
		}
		return attributes;
	}

	public String nonAssociationData(final Integer livingUintId) {
		String attributes = "Non Association Types: \n";
		final List<LivingUnitProfiles> attributesData = oimmholoRepository.attributesData(livingUintId, "NON_ASSO_TYP");
		for (final LivingUnitProfiles profileType : attributesData) {
			if (profileType.getDescription() != null) {
				attributes = attributes + profileType.getDescription() + "\n";
			}
		}
		attributes = attributes + "\n";
		attributes = attributes + "Security Levels: \n";
		final List<LivingUnitProfiles> attributesDataOne = oimmholoRepository.attributesData(livingUintId,
				"SUP_LVL_TYPE");
		for (final LivingUnitProfiles profileType : attributesDataOne) {
			if (profileType.getDescription() != null) {
				attributes = attributes + profileType.getDescription() + "\n";
			}
		}
		return attributes;
	}

	public Integer butChangeEvent(final LivingUnits searchdao) {
		//		return oimmholoRepository.butChangeEvent(searchdao);
		return oimmholoService.insertChildAllLuProfiles(searchdao);

	}

	@Override
	public String getTableName(final String liReturn) {
		return oimmholoRepository.getTableName(liReturn);
	}


	@Override
	public Integer  iepLevelCommit(final AgyIntLocProfilesCommitBean commitBean) {
		Integer liReturn=null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AgyIntLocProfiles agyIntLoc : commitBean.getInsertList()) {
				//aading user to bean
				agyIntLoc.setCreateUserId(commitBean.getCreateUserId());
				liReturn = oimmholoRepository.iepLevelCommit(agyIntLoc);
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AgyIntLocProfiles agyIntLoc : commitBean.getUpdateList()) {
				//aading user to bean
				agyIntLoc.setModifyUserId(commitBean.getCreateUserId());
				liReturn = oimmholoRepository.iepLevelCommitUpdate(agyIntLoc.getInternalLocationId(),agyIntLoc.getIepLevelCode(), agyIntLoc.getModifyUserId());
			}

		}

		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (AgyIntLocProfiles agyIntLoc : commitBean.getDeleteList()) {
				//aading user to bean
				liReturn = oimmholoRepository.iepLevelDelete(agyIntLoc.getInternalLocationId(), commitBean.getCreateUserId());
			}

		}
		return liReturn;
	}


	@Override
	public String getIEPCode(Long internalLocationId,String agyLocId) {
	Long parentLivingId=null;
		 String iepCode=oimmholoRepository.getIEPCodeForInternalLocation(internalLocationId);
		 if(iepCode!=null) {
			throw new IllegalArgumentException(iepCode);
		 }else {
			 parentLivingId= oimmholoRepository.getparentLivingUnitId(internalLocationId);
		 if(parentLivingId!=null && iepCode==null) {
			 getIEPCode(parentLivingId,agyLocId);
			 }else if(parentLivingId==null) {
				IepLevelBean bean= getFacilityIepLevel(agyLocId);
				throw new IllegalArgumentException(bean.getIepLeveldescription());
		 }
		 }
		 return iepCode;
	}

	@Override
	public IepLevelBean getFacilityIepLevel(String agyLocId) {
		return oimmholoRepository.getFacilityIepLevel(agyLocId);
	}

	
	@Override
	public String getIEPExcecuteQuery(Long internalLocationId) {
		return oimmholoRepository.getIEPCodeForInternalLocation(internalLocationId);
	}

	@Override
	public String getAdmisionIepCode(Long internalLocationId, String agyLocId) {
		String iepCode=oimmholoRepository.getLiveingUnitIdIepcode(internalLocationId);
		 if(iepCode==null) {
			 iepCode= oimmholoRepository.getAgencyUnitIdIepcode(agyLocId);
		 }if(iepCode==null) {
			 iepCode= oimmholoRepository.getDefaultIepCode();
		 }
		 return iepCode;
	}

}