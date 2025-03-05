package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TempLivingUnitProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.TempLivingUnitProfiles;
import net.syscon.s4.inst.demographicsbiometrics.OmuavbedRepository;
import net.syscon.s4.inst.demographicsbiometrics.OmuavbedService;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgService;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;

/**
 * Class OmuavbedBusinessa
 */
@Service
public class OmuavbedServiceImpl extends BaseBusiness implements OmuavbedService {

	@Autowired
	private OmuavbedRepository omuavbedRepository;
	
	@Autowired
	private OmuavbedPkgService omuavbedService;
    
    @Autowired
	private TagEstablishmentService tagEstablishmentService;
	

	/**
	 * Creates new OmuavbedServiceImpl class Object
	 */
	public OmuavbedServiceImpl() {
		// OmuavbedServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<AgencyLocations> whenNewFormInstancelevelCur(final String pAgyLocId) {
		return omuavbedRepository.whenNewFormInstancelevelCur(pAgyLocId);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<TempLivingUnitProfiles> livuprofuforExecuteQuery(final TempLivingUnitProfiles searchRecord) {
		return omuavbedRepository.livuprofuforExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTempLivingUnitProfiles
	 */
	@Transactional
	public Integer livuprofuforInsertTempLivingUnitProfiles(
			final List<TempLivingUnitProfiles> lstTempLivingUnitProfiles) {
		return omuavbedRepository.livuprofuforInsertTempLivingUnitProfiles(lstTempLivingUnitProfiles);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstTempLivingUnitProfiles
	 */
	@Transactional
	public Integer livuProfUforUpdateTempLivingUnitProfiles(
			final List<TempLivingUnitProfiles> lstTempLivingUnitProfiles) {
		return omuavbedRepository.livuProfUforUpdateTempLivingUnitProfiles(lstTempLivingUnitProfiles);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTempLivingUnitProfiles
	 */
	@Transactional
	public Integer livuProfUforDeleteTempLivingUnitProfiles(
			final List<TempLivingUnitProfiles> lstTempLivingUnitProfiles) {
		return omuavbedRepository.livuProfUforDeleteTempLivingUnitProfiles(lstTempLivingUnitProfiles);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<LivingUnits> livingUnitsTypeSearchLivingUnits(final LivingUnits searchRecord) {
		return omuavbedRepository.livingUnitsTypeSearchLivingUnits(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstLivingUnits
	 */
	@Transactional
	public Integer livingunitstypeInsertLivingUnits(final List<LivingUnits> lstLivingUnits) {
		return omuavbedRepository.livingUnitsTypeInsertLivingUnits(lstLivingUnits);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstLivingUnits
	 */
	@Transactional
	public Integer livingUnitsTypeUpdateLivingUnits(final List<LivingUnits> lstLivingUnits) {
		return omuavbedRepository.livingUnitsTypeUpdateLivingUnits(lstLivingUnits);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OmuavbedLivUnitsQuery> livingunitsExecuteQuery(final OmuavbedLivUnitsQuery searchRecord) {
		if (searchRecord.getpLevel1Code() != null) {
			final List<LivingUnits> listuti = rgLivingUnitRecordGroup(searchRecord.getpAgyLocId());
			for (LivingUnits obj : listuti) {
				if (obj.getCode().equals(searchRecord.getpLevel1Code())) {
					searchRecord.setpLevel1Code(obj.getDescription());
				}
			}
		}
		List<OmuavbedLivUnitsQuery> resultList =omuavbedService.livUnitsQuery(searchRecord);
		resultList.forEach(param -> {
			if (param.getCellSharingConflict() == null) {
				param.setCellSharingConflict("N");
			}
		});
		if(searchRecord.getProfileType() != null && searchRecord.getProfileTypeOne() == null) {
			searchRecord.setProfileTypeOne(searchRecord.getProfileType());
			
		} else if(searchRecord.getProfileType() == null && searchRecord.getProfileTypeOne() != null) {
			searchRecord.setProfileType(searchRecord.getProfileTypeOne());
		}
		if(searchRecord.getProfileType() != null && searchRecord.getProfileTypeOne() != null) {
			final List<String> usedForList = omuavbedRepository
					.livingUnitProfilesHouUsedFor(searchRecord.getpAgyLocId(), searchRecord.getProfileType(),searchRecord.getProfileTypeOne());
			if (!usedForList.isEmpty()) {
				resultList = resultList.stream().filter(element -> usedForList.contains(element.getDescription()))
						.collect(Collectors.toList());
			} else {
				resultList.clear();
			}
		}
		
		if(searchRecord.getProfileCode() != null && searchRecord.getProfileCodeOne() == null) {
			searchRecord.setProfileCodeOne(searchRecord.getProfileCode());
		}
		else if(searchRecord.getProfileCode() == null && searchRecord.getProfileCodeOne() != null) {
			searchRecord.setProfileCode(searchRecord.getProfileCodeOne());
		}
		
		if (searchRecord.getProfileCode() != null && searchRecord.getProfileCodeOne() != null) {
			final List<String> attributeList = omuavbedRepository
					.livingUnitProfilesHouUnitAtt(searchRecord.getpAgyLocId(), searchRecord.getProfileCode(), searchRecord.getProfileCodeOne());
			if (!attributeList.isEmpty()) {
				resultList = resultList.stream().filter(element -> attributeList.contains(element.getDescription()))
						.collect(Collectors.toList());
			} else {
				resultList.clear();
			}
		}
		if ((searchRecord.getProfileType() != null && !"".equals(searchRecord.getProfileType().trim()))
				|| searchRecord.getProfileCode() != null && !"".equals(searchRecord.getProfileCode().trim())) {
			Collections.sort(resultList, new Comparator<OmuavbedLivUnitsQuery>() {

				@Override
				public int compare(OmuavbedLivUnitsQuery objectOne, OmuavbedLivUnitsQuery objectTwo) {
					return Long.compare(objectOne.getLivingUnitId(), objectTwo.getLivingUnitId());
				}
			});
		}
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> rgLivingUnitRecordGroup(final String agencyLocationId) {
		final List<LivingUnits> listuti = omuavbedRepository.rgLivingUnitRecordGroup(agencyLocationId);
		for (final LivingUnits livingUnits : listuti) {
			livingUnits.setCode(livingUnits.getLivingUnitId().toString());
			livingUnits.setDescription(livingUnits.getLivingUnitCode());
		}
		return fileterLovOnActiveFlag(listuti);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<LivingUnits> rgLivingUnitPagyRecordGroup(final Long livingUnitId, final String level1Code) {
		final List<LivingUnits> listuti = omuavbedRepository.rgLivingUnitPagyRecordGroup(livingUnitId, level1Code);
		for (final LivingUnits livingUnits : listuti) {
			livingUnits.setCode(livingUnits.getLivingUnitId().toString());
			livingUnits.setDescription(livingUnits.getLivingUnitCode());
		}
		return fileterLovOnActiveFlag(listuti);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> rgLivingUnitLocIdRecordGroup(final Integer livingUnitId, final String level2Code) {
		final List<LivingUnits> listuti = omuavbedRepository.rgLivingUnitLocIdRecordGroup(livingUnitId, level2Code);
		for (final LivingUnits livingUnits : listuti) {
			livingUnits.setCode(livingUnits.getLivingUnitId().toString());
			livingUnits.setDescription(livingUnits.getLivingUnitCode());
		}
		return fileterLovOnActiveFlag(listuti);

	}



	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<LivingUnits> rgLivingUnitLevelIdRecordGroup(final Integer livingUnitId, final String level3Code) {
		final List<LivingUnits> listuti = omuavbedRepository.rgLivingUnitLevelIdRecordGroup(livingUnitId, level3Code);
		for (final LivingUnits livingUnits : listuti) {
			livingUnits.setCode(livingUnits.getLivingUnitId().toString());
			livingUnits.setDescription(livingUnits.getLivingUnitCode());
		}
		fileterLovOnActiveFlag(listuti);
		return listuti;

	}
	
	private List<LivingUnits> fileterLovOnActiveFlag(final List<LivingUnits> listuti) {
		if(Optional.ofNullable(listuti).isPresent()) {
			listuti.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return listuti;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgLivingUnitTypeRecordGroup() {
		return omuavbedRepository.rgLivingUnitTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgUsedForRecordGroup() {
		return omuavbedRepository.rgUsedForRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgAttributesRecordGroup() {
		return omuavbedRepository.rgAttributesRecordGroup();

	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@Transactional
	public Integer livuprofuforCommit(final TempLivingUnitProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = livuprofuforInsertTempLivingUnitProfiles(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = livuProfUforUpdateTempLivingUnitProfiles(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = livuProfUforDeleteTempLivingUnitProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}
	
	public AgencyLocations gettingLabels(final String agyLocId) {
        	AgencyLocations agencyLocations =  tagEstablishmentService.getHousingLabels(agyLocId);
		if (agencyLocations.getHousingLev1Code() != null) {
			agencyLocations.setHousingLev1Code(omuavbedRepository.getLabelDescription(agencyLocations.getHousingLev1Code()));
		}
		if (agencyLocations.getHousingLev2Code() != null) {
			agencyLocations.setHousingLev2Code(omuavbedRepository.getLabelDescription(agencyLocations.getHousingLev2Code()));
		}
		if (agencyLocations.getHousingLev3Code() != null) {
			agencyLocations.setHousingLev3Code(omuavbedRepository.getLabelDescription(agencyLocations.getHousingLev3Code()));
		}
		if (agencyLocations.getHousingLev4Code() != null) {
			agencyLocations.setHousingLev4Code(omuavbedRepository.getLabelDescription(agencyLocations.getHousingLev4Code()));
		}
		return agencyLocations;
	}
}