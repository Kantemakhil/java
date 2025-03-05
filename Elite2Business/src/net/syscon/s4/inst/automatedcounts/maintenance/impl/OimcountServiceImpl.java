package net.syscon.s4.inst.automatedcounts.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyReportingLocs;
import net.syscon.s4.im.beans.AgencyReportingLocsCommitBean;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypesCommitBean;
import net.syscon.s4.inst.automatedcounts.maintenance.OimcountRepository;
import net.syscon.s4.inst.automatedcounts.maintenance.OimcountService;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;

/**
 * Class OimcountServiceImpl
 */
@Service
public class OimcountServiceImpl extends BaseBusiness implements OimcountService {

	@Autowired
	private OimcountRepository oimcountRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer agencyReportingLocsHousWhenNewRecordInstance(final AgencyReportingLocs paramBean) {
		return oimcountRepository.agencyReportingLocsHousWhenNewRecordInstance(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		return oimcountRepository.createFormGlobals(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> agencyLocationsExecuteQuery(final AgencyLocations searchRecord) {
		return oimcountRepository.agencyLocationsExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyCountTypes> agencyCountTypesExecuteQuery(final AgencyCountTypes searchRecord) {
		return oimcountRepository.agencyCountTypesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_COUNT_TYPES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agencyCountTypesCommit(final AgencyCountTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (AgencyCountTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			final Integer dupCountValue = getAcctypeInsertDupCount(commitBean.getInsertList());
			for (final AgencyCountTypes bean : commitBean.getInsertList()) {
				if (dupCountValue > 0) {
					return 2;
				}
			}
			liReturn = oimcountRepository.agencyCountTypesInsertAgencyCountTypes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AgencyCountTypes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			final Integer dupCount = getAcctypeUpdateDupCount(commitBean.getUpdateList());
			if (dupCount > 0) {
				return 3;
			}
			liReturn = oimcountRepository.agencyCountTypesUpdateAgencyCountTypes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			final List<Integer> returnObj = commitBean.getDeleteList().stream().map(data -> data.getCountTypeId())
					.collect(Collectors.toList());
			final Integer childCount = oimcountRepository.agencyCountTypesOnCheckDeleteMasters(returnObj);
			if (childCount > 0) {
				return 4;
			}
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimcountRepository.agencyCountTypesDeleteAgencyCountTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyReportingLocs> agencyReportingLocsHousExecuteQuery(final AgencyReportingLocs searchRecord) {
		final List<AgencyReportingLocs> returnList = oimcountRepository
				.agencyReportingLocsHousExecuteQuery(searchRecord);
		for (final AgencyReportingLocs bean : returnList) {
			Integer count = oimcountRepository.agencyReportingLocsHousWhenNewRecordInstance(bean);
			bean.setActiveCount(count);
			if (bean.getLocation1Id() != null) {
				final LivingUnits returnObj = oimcountRepository
						.agencyReportingLocsHousPostQuery(bean.getLocation1Id());
				if (returnObj.getLivingUnitCode() != null) {
					bean.setLocation1Code(returnObj.getLivingUnitCode());
				}
			}
			if (bean.getLocation2Id() != null) {
				final LivingUnits returnObj = oimcountRepository
						.agencyReportingLocsHousPostQuery(bean.getLocation2Id());
				if (returnObj.getLivingUnitCode() != null) {
					bean.setLocation2Code(returnObj.getLivingUnitCode());
				}
			}
			if (bean.getLocation3Id() != null) {
				final LivingUnits returnObj = oimcountRepository
						.agencyReportingLocsHousPostQuery(bean.getLocation3Id());
				if (returnObj.getLivingUnitCode() != null) {
					bean.setLocation3Code(returnObj.getLivingUnitCode());
				}
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_REPORTING_LOCS_HOUS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agencyReportingLocsHousCommit(final AgencyReportingLocsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final AgencyReportingLocs bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				final Integer dupCount = oimcountRepository.agencyReportingLocsHousPreInsert(bean);
				if (dupCount > 0) {
					return 2;
				}
				final Integer seqValue = oimcountRepository.housPreInsertGetSeq(bean);
				bean.setAgySeq(seqValue);
				liReturn = oimcountRepository.agencyReportingLocsHousInsertAgencyReportingLocs(bean);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AgencyReportingLocs obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimcountRepository.agencyReportingLocsHousUpdateAgencyReportingLocs(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			final Integer childCount = delRecValidations(commitBean.getDeleteList());
			if (childCount > 0) {
				return childCount;
			}
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimcountRepository.agencyReportingLocsHousDeleteAgencyReportingLocs(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer delRecValidations(final List<AgencyReportingLocs> deleteList) {
		final List<Integer> returnObj = deleteList.stream().map(data -> data.getCountTypeId())
				.collect(Collectors.toList());
		final Integer count = oimcountRepository.cgkeyDelrec(returnObj);
		if (count > 0) {
			return 3;
		}
		final Integer alcCount = oimcountRepository.cgkeyDelrecAlc(returnObj);
		if (alcCount > 0) {
			return 4;
		}
		final Integer acCount = oimcountRepository.cgkeyDelrecAc(returnObj);
		if (acCount > 0) {
			return 4;
		}
		return 0;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyReportingLocs> agencyReportingLocsInitExecuteQuery(final AgencyReportingLocs searchRecord) {
		final List<AgencyReportingLocs> returnList = oimcountRepository
				.agencyReportingLocsInitExecuteQuery(searchRecord);
		for (final AgencyReportingLocs bean : returnList) {
			Integer count = oimcountRepository.agencyReportingLocsHousWhenNewRecordInstance(bean);
			bean.setActiveCount(count);
			final VIntLocSummaries returnObj = oimcountRepository.agencyReportingLocsInitPostQuery(bean);
			if (returnObj.getInternalLocationCode() != null) {
				bean.setLocation1Code(returnObj.getInternalLocationCode());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_REPORTING_LOCS_INIT
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agencyReportingLocsInitCommit(final AgencyReportingLocsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			final List<String> locType = commitBean.getInsertList().stream().map(data -> data.getLocationType())
					.collect(Collectors.toList());
			final List<Integer> countTypeId = commitBean.getInsertList().stream().map(data -> data.getCountTypeId())
					.collect(Collectors.toList());
			final List<Integer> locId = commitBean.getInsertList().stream().map(data -> data.getLocation1Id())
					.collect(Collectors.toList());
			final Integer dupCount = oimcountRepository.agencyReportingLocsInitPreInsert(locType, countTypeId, locId);
			if (dupCount > 0) {
				return 2;
			}
			for (final AgencyReportingLocs bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				final Integer seqValue = oimcountRepository.housPreInsertGetSeq(bean);
				bean.setAgySeq(seqValue);
				liReturn = oimcountRepository.agencyReportingLocsHousInsertAgencyReportingLocs(bean);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (AgencyReportingLocs obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimcountRepository.agencyReportingLocsHousUpdateAgencyReportingLocs(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			final Integer childCount = delRecValidations(commitBean.getDeleteList());
			if (childCount > 0) {
				return childCount;
			}
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimcountRepository.agencyReportingLocsHousDeleteAgencyReportingLocs(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadid) {
		return oimcountRepository.cgfkAgyLocIdRecordGroup(caseloadid);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup() {
		return oimcountRepository.cgfkCountTypesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> cgfkHousingLevel1RecordGroup(final String agyLocId) {
		List<LivingUnits> returnList = oimcountRepository.cgfkHousingLevel1RecordGroup(agyLocId);
		for(LivingUnits bean: returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> cgfkHousingLevel2RecordGroup(final String parentField) {
		List<LivingUnits> recordList = new ArrayList<>();
		if (parentField.contains("-")) {
			final String[] returnArray = parentField.split("-");
			recordList = oimcountRepository.cgfkHousingLevel2RecordGroup(returnArray[1], returnArray[0]);
		}
		return recordList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> cgfkHousingLevel3RecordGroup(final String parentField) {
		List<LivingUnits> recordList = new ArrayList<>();
		if (parentField.contains("-")) {
			final String[] returnArray = parentField.split("-");
			recordList = oimcountRepository.cgfkHousingLevel3RecordGroup(returnArray[1], returnArray[0]);
		}
		return recordList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VIntLocSummaries> cgfkInitLocCodeRecordGroup(final String agyLocId) {
		final List<VIntLocSummaries> returnLlist = oimcountRepository.cgfkInitLocCodeRecordGroup(agyLocId);
		return returnLlist;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public Integer acctypeCheckboxChenged(final AgencyCountTypes object) {
		return oimcountRepository.acctypeCheckboxChenged(object);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> livingUnitsQuery(final String agylocId) {
		return oimcountRepository.livingUnitsQuery(agylocId);

	}
	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> livingUnitsQueryOne(final String agylocId) {
		return oimcountRepository.livingUnitsQueryOne(agylocId);

	}
	@Override
	public List<AgencyReportingLocs> processCopy(final AgencyReportingLocs paramBean) {
		return null;
	}

	@Override
	public AgencyLocations agencyLocationsWhenNewRecordInstance(final AgencyLocations paramBean) {
		AgencyLocations returnData = new AgencyLocations();
		returnData = oimcountRepository.agencyLocationsWhenNewRecordInstance(paramBean);
		if (returnData != null) {
			if (returnData != null && returnData.getHousingLev1Code() != null) {
				String dataOne = oimcountRepository.getLabelDescription(returnData.getHousingLev1Code());
				if (dataOne != null) {
					returnData.setHousingLev1Code(dataOne);
				}
			}
			if (returnData != null && returnData.getHousingLev2Code() != null) {
				String dataTwo = oimcountRepository.getLabelDescription(returnData.getHousingLev2Code());
				if (dataTwo != null) {
					returnData.setHousingLev2Code(dataTwo);
				}
			}
			if (returnData != null && returnData.getHousingLev3Code() != null) {
				String dataThree = oimcountRepository.getLabelDescription(returnData.getHousingLev3Code());
				if (dataThree != null) {
					returnData.setHousingLev3Code(dataThree);
				}
			}
		}
		return returnData;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_COUNT_TYPES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agencyCountReportsCommit(final AgencyCountTypesCommitBean commitBean) {
		int liReturn = 0;
		Integer countTypeIdNxtValue = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (AgencyCountTypes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());	
			}
			final Integer dupCountValue = getAcctypeInsertDupCount(commitBean.getInsertList());

			for (final AgencyCountTypes bean : commitBean.getInsertList()) {

				if ("Y".equals(bean.getActiveFlag()) && bean.getExpiryDate() == null && dupCountValue > 0) {
					return 2;
				}
				countTypeIdNxtValue = oimcountRepository.getCountTypeId();
				bean.setCountTypeId(countTypeIdNxtValue);
			}
			liReturn = oimcountRepository.agencyCountReportsCommit(commitBean.getInsertList());
			if (liReturn == 1) {
				if (commitBean.getReportInsertList() != null && !commitBean.getReportInsertList().isEmpty()) {
					for (final AgencyReportingLocs bean : commitBean.getReportInsertList()) {
						bean.setCountTypeId(countTypeIdNxtValue);
						final Integer dupCount = oimcountRepository.agencyReportingLocsHousPreInsert(bean);
						if (dupCount > 0) {
							return 5;
						}
						final Integer seqValue = oimcountRepository.housPreInsertGetSeq(bean);
						bean.setAgySeq(seqValue);
						liReturn = oimcountRepository.agencyReportingLocsInsert(bean);
					}
				}
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (AgencyCountTypes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			final Integer dupCount = getAcctypeUpdateDupCount(commitBean.getUpdateList());
			if (dupCount > 0) {
				return 3;
			}
			liReturn = oimcountRepository.agencyCountTypesUpdateAgencyCountTypes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			final List<Integer> returnObj = commitBean.getDeleteList().stream().map(data -> data.getCountTypeId())
					.collect(Collectors.toList());
			final Integer childCount = oimcountRepository.agencyCountTypesOnCheckDeleteMasters(returnObj);
			if (childCount > 0) {
				return 4;
			}
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimcountRepository.agencyCountTypesDeleteAgencyCountTypes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer getAcctypeInsertDupCount(final List<AgencyCountTypes> listObj) {
		final List<String> agyLocId = listObj.stream().map(data -> data.getAgyLocId()).collect(Collectors.toList());
		final List<String> countTypeCode = listObj.stream().map(data -> data.getCountTypeCode())
				.collect(Collectors.toList());
		final List<String> scheduleTime = listObj.stream().map(data -> data.getScheduledTime())
				.collect(Collectors.toList());
		return oimcountRepository.agencyCountTypesPreInsert(agyLocId, countTypeCode, scheduleTime);

	}

	public Integer getAcctypeUpdateDupCount(final List<AgencyCountTypes> listObj) {
		final List<String> agyLocId = listObj.stream().map(data -> data.getAgyLocId()).collect(Collectors.toList());
		final List<String> countTypeCode = listObj.stream().map(data -> data.getCountTypeCode())
				.collect(Collectors.toList());
		final List<String> scheduleTime = listObj.stream().map(data -> data.getScheduledTime())
				.collect(Collectors.toList());
		final List<Integer> countTypeId = listObj.stream().map(data -> data.getCountTypeId())
				.collect(Collectors.toList());
		return oimcountRepository.agencyCountTypesPreUpdate(agyLocId, countTypeCode, scheduleTime, countTypeId);

	}
	
	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<LivingUnits> livingUnitsQueryTwo(final String parentField) {
		List<LivingUnits> recordList = new ArrayList<>();
		if (parentField.contains("-")) {
			final String[] returnArray = parentField.split("-");
			recordList = oimcountRepository.livingUnitsQueryTwo(returnArray[1], returnArray[0]);
		}
		return recordList;

	}

}