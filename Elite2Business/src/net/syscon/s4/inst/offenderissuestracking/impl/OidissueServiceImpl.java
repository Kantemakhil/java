package net.syscon.s4.inst.offenderissuestracking.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievanceTxnsCommitBean;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OffenderGrievancesCommitBean;
import net.syscon.s4.inst.offenderissuestracking.OidissueRepository;
import net.syscon.s4.inst.offenderissuestracking.OidissueService;

/**
 * Class OidissueServiceImpl
 */
@Service
public class OidissueServiceImpl extends BaseBusiness implements OidissueService {

	@Autowired
	private OidissueRepository oidissueRepository;

	@Autowired
	private EliteDateService dateService;

	/**
	 * Creates new OidissueServiceImpl class Object
	 */
	public OidissueServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderGrievances> offBkgOnCheckDeleteMaster(final OffenderGrievances paramBean) {
		return oidissueRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderGrievanceTxns> offenderGrievancesKeyDelrec(final OffenderGrievanceTxns paramBean) {
		return oidissueRepository.offenderGrievancesKeyDelrec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderGrievanceTxns offenderGrievancesPostQuery(final OffenderGrievanceTxns paramBean) {
		return oidissueRepository.offenderGrievancesPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyLocations offenderGrievancesWhenNewRecordInstance(final AgencyLocations paramBean) {
		return oidissueRepository.offenderGrievancesWhenNewRecordInstance(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderGrievanceTxns offenderGrievancesOnCheckDeleteMaster(final OffenderGrievanceTxns paramBean) {
		return oidissueRepository.offenderGrievancesOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes offenderGrievanceTxnsPostQuery(final ReferenceCodes paramBean) {
		return oidissueRepository.offenderGrievanceTxnsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderGrievanceTxns offenderGrievanceTxnsWhenNewRecordInstance(final OffenderGrievanceTxns paramBean) {
		return oidissueRepository.offenderGrievanceTxnsWhenNewRecordInstance(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes offenderGrievanceTxnsWhenValidateRecord(final ReferenceCodes paramBean) {
		return oidissueRepository.offenderGrievanceTxnsWhenValidateRecord(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes oidissueKeyCommit(final ReferenceCodes paramBean) {
		return oidissueRepository.oidissueKeyCommit(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes isActiveTxn(final ReferenceCodes paramBean) {
		return oidissueRepository.isActiveTxn(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderGrievances> offenderGrievancesExecuteQuery(final OffenderGrievances searchRecord) {
		List<OffenderGrievances> offenderGrievance = new ArrayList<>();
		offenderGrievance = oidissueRepository.offenderGrievancesExecuteQuery(searchRecord);
		for (final OffenderGrievances offGrievs : offenderGrievance) {
			if (Optional.ofNullable(offGrievs).isPresent() && offGrievs.getGrievanceId() != null) {
				if (Optional.ofNullable(offGrievs.getGrievType()).isPresent()) {
					final GrievanceTypes grievanceTypes = oidissueRepository
							.offenderGrievanceTxnsWhenValidateRecord(offGrievs.getGrievType());
					final Integer grievanceIdCur = oidissueRepository.grievanceIdCur(offGrievs.getGrievanceId());
					if (Optional.ofNullable(grievanceTypes).isPresent()) {
						offGrievs.setStaffInvolvedFlag(grievanceTypes.getStaffInvolvedFlag());
					}
					if (Optional.ofNullable(grievanceIdCur).isPresent()) {
						offGrievs.setGrievanceIdCur(grievanceIdCur);
					}
				}
				final OffenderGrievanceTxns offenderGrievanceTxnsStatus = oidissueRepository
						.offenderGrievancesPostQueryStatus(offGrievs);
				if (offenderGrievanceTxnsStatus.getStatus() != null) {
					offGrievs.setCurrStatus(offenderGrievanceTxnsStatus.getStatus());
					if (offenderGrievanceTxnsStatus.getGrievLevel() != null) {
						offGrievs.setCurrLevel(offenderGrievanceTxnsStatus.getGrievLevel());
					}
				} else {
					final List<OffenderGrievanceTxns> offGrievTxnsStatusGrieveLevel = oidissueRepository
							.offenderGrievancesPostQueryStatusGrievanceLevel(offGrievs);
					for (final OffenderGrievanceTxns offGrievTxns : offGrievTxnsStatusGrieveLevel) {
						if (offGrievTxns.getStatus() != null) {
							offGrievs.setCurrStatus(offGrievTxns.getStatus());
						}
						if (offGrievTxns.getGrievLevel() != null) {
							offGrievs.setCurrLevel(offGrievTxns.getGrievLevel());
						}
					}
				}
			}
			if (offGrievs.getAgyLocId() != null) {
				final String agyLocId = oidissueRepository.offenderGrievancesPostQueryAgyLocId(offGrievs);
				if (agyLocId != null) {
					offGrievs.setAgyLocIdDesc(agyLocId);
				}
			}

		}

		return offenderGrievance;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_GRIEVANCES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offenderGrievancesCommit(final OffenderGrievancesCommitBean commitBean) {
		int liReturn = 0;
		Long grievanceId = null;
		Long txnSeq = null;
		Long grievancesId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			final List<OffenderGrievances> recordSavingList = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (final OffenderGrievances offenderPropertyItemObj : commitBean.getInsertList()) {
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					grievancesId = oidissueRepository.offenderGrievancesPreInsert();
					offenderPropertyItemObj.setGrievanceId(grievancesId);
					recordSavingList.add(offenderPropertyItemObj);
					liReturn = oidissueRepository.offenderGrievancesInsertOffenderGrievances(recordSavingList);
				}
			}
		}
		if (commitBean.getOffenderGrievanceTxnsList() != null && commitBean.getOffenderGrievanceTxnsList().size() > 0) {
			List<OffenderGrievanceTxns> recordSavingObject = new ArrayList<>();
			if (commitBean.getOffenderGrievanceTxnsList().size() > 0) {
				for (int i = 0; i < commitBean.getOffenderGrievanceTxnsList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderGrievanceTxns offenderPropertyItemObj = commitBean.getOffenderGrievanceTxnsList()
							.get(i);
					offenderPropertyItemObj.setModifyUserId(null);
					if (offenderPropertyItemObj.getAssignedStaffName() != null) {
						offenderPropertyItemObj
								.setAssignedStaffId(Long.valueOf(offenderPropertyItemObj.getAssignedStaffName()));
					}
					grievanceId = oidissueRepository.maxGrievanceId();
					offenderPropertyItemObj.setGrievanceId(grievanceId);
					final String grievType = oidissueRepository.maxGrievanceType(grievanceId);
					offenderPropertyItemObj.setGrievType(grievType);
					txnSeq = oidissueRepository.offenderGrievanceTxnsPreInsert(grievanceId);
					offenderPropertyItemObj.setTxnSeq(Long.valueOf(txnSeq));
					offenderPropertyItemObj.setModifyUserId(commitBean.getCreateUserId());
					final Integer updateOffenderGrievanceTxns = oidissueRepository
							.offenderGrievanceTxnsUpdateOffenderGrievanceTxnsPreInsert(offenderPropertyItemObj);
					if (updateOffenderGrievanceTxns != null) {
					}
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidissueRepository.offenderGrievanceTxnsInsertOffenderGrievanceTxns(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderGrievances bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidissueRepository.offenderGrievancesUpdateOffenderGrievances(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			int txnsVal;
			for (OffenderGrievances bean : commitBean.getDeleteList()) {
				List<OffenderGrievanceTxns> offenderGrievanceTxnsList = new ArrayList<OffenderGrievanceTxns>();
				OffenderGrievanceTxns offenderGrievanceTxns = new OffenderGrievanceTxns();
				offenderGrievanceTxns.setGrievanceId(bean.getGrievanceId());
				offenderGrievanceTxnsList = oidissueRepository.offenderGrievanceTxnsExecuteQuery(offenderGrievanceTxns);
				if(offenderGrievanceTxnsList.size() > 0) {
					offenderGrievanceTxnsList.stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
					txnsVal = oidissueRepository.offenderGrievanceTxnsDeleteOffenderGrievanceTxns(offenderGrievanceTxnsList);
					if(txnsVal == 1) {
						liReturn = oidissueRepository.offenderGrievancesDeleteOffenderGrievances(commitBean.getDeleteList());
					}
				}else {
					liReturn = oidissueRepository.offenderGrievancesDeleteOffenderGrievances(commitBean.getDeleteList());
				}
			}		
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
	public List<OffenderGrievanceTxns> offenderGrievanceTxnsExecuteQuery(final OffenderGrievanceTxns searchRecord) {
		List<OffenderGrievanceTxns> offenderGrievanceTxns = new ArrayList<>();
		offenderGrievanceTxns = oidissueRepository.offenderGrievanceTxnsExecuteQuery(searchRecord);
		final Integer ststusCount = oidissueRepository.offenderGrievanceTxnsDaysLeft(searchRecord);
		for (final OffenderGrievanceTxns offGrievTxns : offenderGrievanceTxns) {

			if (ststusCount > 0) {
				offGrievTxns.setDaysLeft(null);
			} else {
				if (offGrievTxns.getGrievType() != null && offGrievTxns.getTxnType() != null) {
					final Long daysLeft = oidissueRepository.daysRespondData(offGrievTxns.getGrievType(),
							offGrievTxns.getTxnType());
					if (daysLeft != null) {
						offGrievTxns.setDaysLeft(daysLeft);
					}
				}
			}
			if (ststusCount > 0) {
				offGrievTxns.setDaysLeft(null);
			}
		}
		return offenderGrievanceTxns;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_GRIEVANCE_TXNS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offenderGrievanceTxnsCommit(final OffenderGrievanceTxnsCommitBean commitBean) {
		int liReturn = 0;
		Long grievanceId = null;
		Long txnSeq = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderGrievanceTxns> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(bean-> bean.setCreateUserId(commitBean.getCreateUserId()));
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderGrievanceTxns offenderPropertyItemObj = commitBean.getInsertList().get(i);
				
					grievanceId = offenderPropertyItemObj.getGrievanceId();
					txnSeq = oidissueRepository.offenderGrievanceTxnsPreInsert(grievanceId);
					offenderPropertyItemObj.setTxnSeq(Long.valueOf(txnSeq));
					final Integer updateOffenderGrievanceTxns = oidissueRepository
							.offenderGrievanceTxnsUpdateOffenderGrievanceTxnsPreInsert(offenderPropertyItemObj);
					if (updateOffenderGrievanceTxns != null) {
					}
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidissueRepository.offenderGrievanceTxnsInsertOffenderGrievanceTxns(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<OffenderGrievanceTxns> recordSavingObject = new ArrayList<>();
			if (commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(action-> action.setModifyUserId(commitBean.getCreateUserId()));
				for (int i = 0; i < commitBean.getUpdateList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderGrievanceTxns offenderPropertyItemObj = commitBean.getUpdateList().get(i);
				
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidissueRepository.offenderGrievanceTxnsUpdateOffenderGrievanceTxns(recordSavingObject);
				}
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidissueRepository.offenderGrievanceTxnsDeleteOffenderGrievanceTxns(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup() {
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = oidissueRepository.rgAgyLocRecordGroup();
		for (final AgencyLocations agyLocns : returnList) {
			if (agyLocns.getSealFlag().equals("Y")) {
				agyLocns.setCanDisplay(true);
			} else {
				agyLocns.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocAllRecordGroup() {
		return oidissueRepository.rgAgyLocAllRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTypes> rgGrievTypeRecordGroup(String user) {
		List<GrievanceTypes> returnList = new ArrayList<GrievanceTypes>();
		returnList = oidissueRepository.rgGrievTypeRecordGroup(user);
		for (GrievanceTypes grievanceTypes : returnList) {
			if (grievanceTypes.getActiveFlag().equals(ApplicationConstants.YFLAG) && grievanceTypes.getCreateFlag() != null && grievanceTypes.getCreateFlag().equals(ApplicationConstants.YFLAG))
			{
				grievanceTypes.setCanDisplay(true);
			} else {
				grievanceTypes.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgGrievReasonRecordGroup(final String grieveType , String user) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = oidissueRepository.rgGrievReasonRecordGroup(grieveType , user);
		for (ReferenceCodes referenceCodes : returnList) {
			
			if(referenceCodes.getCreateFlag()!= null && referenceCodes.getCreateFlag().equalsIgnoreCase(ApplicationConstants.YFLAG)
					&& referenceCodes.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG)) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTxns> rgTxnTypeRecordGroup(final String grieveType) {
		return oidissueRepository.rgTxnTypeRecordGroup(grieveType);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTxns> rgTxnTypeAllRecordGroup(final String grieveType) {
		List<GrievanceTxns> grievanceTxns = new ArrayList<>();
		grievanceTxns = oidissueRepository.rgTxnTypeAllRecordGroup(grieveType);
		for (final GrievanceTxns grvTxns : grievanceTxns) {
			grvTxns.setCode(grvTxns.getTxnType());
					if("Y".equals(grvTxns.getActiveFlag())) {
						grvTxns.setCanDisplay(true);
					} else {
						grvTxns.setCanDisplay(false);
					}
		}
		return grievanceTxns;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffRecordGroup(String userName) {
		List<StaffMembers> referenceCodes = new ArrayList<>();
		referenceCodes = oidissueRepository.rgStaffRecordGroup(userName);
		for (final StaffMembers refCode : referenceCodes) {
			refCode.setCode(refCode.getStaffId());
		}
		return referenceCodes;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffAllRecordGroup() {
		return oidissueRepository.rgStaffAllRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFindingRecordGroup() {
		return oidissueRepository.rgFindingRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgFindingAllRecordGroup() {
		return oidissueRepository.rgFindingAllRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgLevelRecordGroup() {
		return oidissueRepository.rgLevelRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgLevelAllRecordGroup() {
		return oidissueRepository.rgLevelAllRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		return oidissueRepository.rgStatusRecordGroup();

	}

	/**
	 * This method is used to execute VheaderBlock
	 */
	@Override
	public List<VHeaderBlock> offbkgGlobalQuery(final VHeaderBlock searchBean) {
		List<VHeaderBlock> returnList = new ArrayList<>();
		if (searchBean.getAgyLocId() != null) {
			final String caseloads = oidissueRepository.caseloadTypeData(searchBean.getCreateuserId());
			searchBean.setAgyLocType(caseloads);
		}
		returnList = oidissueRepository.offbkgInstGlobalQuery(searchBean);
		final List<VHeaderBlock> updatedList = new ArrayList<>();
		for (final VHeaderBlock listval : returnList) {
			if (listval.getOffenderIdDisplay() != null) {
				listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
				updatedList.add(listval);
			}

		}
		return updatedList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public String offenderGrievancesPostQueryReportDate(final String agyLocId) {
		return oidissueRepository.offenderGrievancesPostQueryReportDate(agyLocId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public Long daysRespondData(final String grieveType, final String txnType) {
		return oidissueRepository.daysRespondData(grieveType, txnType);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderGrievances> maxGrievanceIdComparison(final Long grievanceId) {
		return oidissueRepository.maxGrievanceIdComparison(grievanceId);
	}

	@Override
	public Integer validationStaff(Long grievanceId) {
		return oidissueRepository.validationStaff(grievanceId);
	}

	@Override
	public Integer oidissueWhenNewFormInstance(String userName) {
		return oidissueRepository.oidissueWhenNewFormInstance(userName);
	}

}
