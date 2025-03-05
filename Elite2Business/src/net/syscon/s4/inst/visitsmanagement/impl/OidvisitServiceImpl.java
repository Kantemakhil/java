package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyVisitTimes;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.visitsmanagement.OidvisitRepository;
import net.syscon.s4.inst.visitsmanagement.OidvisitService;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitorsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitorsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.ValidateVisitorBean;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;
import net.syscon.s4.triggers.OffenderVisitsT1Service;

/**
 * Class OidvisitServiceImpl
 */
@Service
public class OidvisitServiceImpl extends BaseBusiness implements OidvisitService {

	@Autowired
	private OidvisitRepository oidvisitRepo;

	@Autowired
	private EliteDateService dateService;

	@Autowired
	private TagVisitsService tagVisitsService;

	@Autowired
	private PimsWeightService pimsWeightService;

	@Autowired
	private OffenderVisitsT1Service offenderVisitsT1Service;

	@Autowired
	private NonAssociationService nonAssociationService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvisitServiceImpl.class.getName());
	
	/**
	 * Creates new OidvisitServiceImpl class Object
	 */
	public OidvisitServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 *
	 */
	public List<VOffenderVisits> offVstExecuteQuery(final VOffenderVisits searchRecord) {
		String supLevel;
		String iepLevel;

		final SimpleDateFormat localTimeFormat = new SimpleDateFormat("HH:mm");
		List<VOffenderVisits> returnList = new ArrayList<>();
		returnList = oidvisitRepo.offVstExecuteQuery(searchRecord);
		String iepSecConfig =oidvisitRepo.checkIepSecConfig(searchRecord.getAgyLocId());
		if(iepSecConfig != null) {
		if(iepSecConfig.equals("SEC_LEVEL")) {
		supLevel = pimsWeightService.getSupLevel(
				searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
				searchRecord.getUserName());
		if (supLevel == null) {
			supLevel = oidvisitRepo.classPending();
		}
		
	
		for (final VOffenderVisits row : returnList) {
			if (row.getAgencyVisitSlotId() != null) {
				VOffenderVisits returnBean = oidvisitRepo
						.getTimeSlotDetails(Integer.valueOf(row.getAgencyVisitSlotId().toString()));
				row.setDescription(returnBean.getDescription());
				final String originalTimeSlot = localTimeFormat.format(returnBean.getStartTime());
				row.setTimeSlot(returnBean.getCode());
				row.setOriginalTimeSlot(originalTimeSlot);
			}
			
			if (row.getOffenderVisitId() != null) {
				final VOffenderVisits remVisits = tagVisitsService.visitCalc(searchRecord.getAgyLocId(),
						searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
						supLevel, row.getVisitType(), row.getVisitDate());
				row.setCycleEnds(remVisits.getCycleEnds() != null ? remVisits.getCycleEnds() : null);
				if (remVisits.getTotalRemainingVisits() != null && remVisits.getTotalRemainingVisits() <= 0) {
					row.setTotalRemainingVisits(supLevel != null ? 0L : null);
				} else {
					row.setTotalRemainingVisits(
							remVisits.getTotalRemainingVisits() != null ? remVisits.getTotalRemainingVisits() : null);
				}
				if (remVisits.getRemainingVisitsType() != null && remVisits.getRemainingVisitsType() <= 0) {
					row.setRemainingVisitsType(supLevel != null ? 0L : null);
				} else {
					row.setRemainingVisitsType(
							remVisits.getRemainingVisitsType() != null ? remVisits.getRemainingVisitsType() : null);
				}
				if (remVisits.getTotalRemainingTime() != null
						&& remVisits.getTotalRemainingTime().compareTo(BigDecimal.ZERO) <= 0) {
					row.setTotalRemainingTime(supLevel != null ? BigDecimal.valueOf(0) : null);
				} else {
					row.setTotalRemainingTime(
							remVisits.getTotalRemainingTime() != null ? remVisits.getTotalRemainingTime() : null);
				}
				if (remVisits.getRemainingTimeType() != null
						&& remVisits.getRemainingTimeType().compareTo(BigDecimal.ZERO) <= 0) {
					row.setRemainingTimeType(supLevel != null ? BigDecimal.valueOf(0) : null);
				} else {
					row.setRemainingTimeType(
							remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeType() : null);
				}
				
			}
		}
		
			
		}
		else {
			if(iepSecConfig.equals("IEP_LEVEL")) {
				iepLevel = oidvisitRepo.getOffenderIepConfigData(
						searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null);
				if (iepLevel == null) {
					iepLevel = oidvisitRepo.checkIeplevel();
				}
				for (final VOffenderVisits row : returnList) {
					if (row.getAgencyVisitSlotId() != null) {
						VOffenderVisits returnBean = oidvisitRepo
								.getTimeSlotDetails(Integer.valueOf(row.getAgencyVisitSlotId().toString()));
						row.setDescription(returnBean.getDescription());
						final String originalTimeSlot = localTimeFormat.format(returnBean.getStartTime());
						row.setTimeSlot(returnBean.getCode());
						row.setOriginalTimeSlot(originalTimeSlot);
					}
					
					if (row.getOffenderVisitId() != null) {
						final VOffenderVisits remVisits = tagVisitsService.iepVisitCalc(searchRecord.getAgyLocId(),
								searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
										iepLevel, row.getVisitType(), row.getVisitDate());
						row.setCycleEnds(remVisits.getCycleEnds() != null ? remVisits.getCycleEnds() : null);
						if (remVisits.getTotalRemainingVisits() != null && remVisits.getTotalRemainingVisits() <= 0) {
							row.setTotalRemainingVisits(iepLevel != null ? 0L : null);
						} else {
							row.setTotalRemainingVisits(
									remVisits.getTotalRemainingVisits() != null ? remVisits.getTotalRemainingVisits() : null);
						}
						if (remVisits.getRemainingVisitsType() != null && remVisits.getRemainingVisitsType() <= 0) {
							row.setRemainingVisitsType(iepLevel != null ? 0L : null);
						} else {
							row.setRemainingVisitsType(
									remVisits.getRemainingVisitsType() != null ? remVisits.getRemainingVisitsType() : null);
						}
						if (remVisits.getTotalRemainingTime() != null
								&& remVisits.getTotalRemainingTime().compareTo(BigDecimal.ZERO) <= 0) {
							row.setTotalRemainingTime(iepLevel != null ? BigDecimal.valueOf(0) : null);
						} else {
							row.setTotalRemainingTime(
									remVisits.getTotalRemainingTime() != null ? remVisits.getTotalRemainingTime() : null);
						}
						if (remVisits.getRemainingTimeType() != null
								&& remVisits.getRemainingTimeType().compareTo(BigDecimal.ZERO) <= 0) {
							row.setRemainingTimeType(iepLevel != null ? BigDecimal.valueOf(0) : null);
						} else {
							row.setRemainingTimeType(
									remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeType() : null);
						}
						
					}
				}
				
					
				}
			
		}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_VST
	 *
	 *
	 */
	@Transactional
	public Integer offVstCommit(final VOffenderVisitsCommitBean commitBean) {
		Integer liReturn = 0;
		BigDecimal offenderVisitId = null;
		BigDecimal offenderBookId = null;
		List<VOffenderVisits> insertList = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			insertList = commitBean.getInsertList();
			for (final VOffenderVisits insertBean : insertList) {
				insertBean.setUserName(commitBean.getCreateUserId());
				offenderVisitId = tagVisitsService.getNextOffVisitId();
				insertBean.setOffenderVisitId(offenderVisitId);
				final BigDecimal eventId = tagVisitsService.getEventId();
				insertBean.setEventId(eventId);
				insertBean.setGroupLeaderFlag("N");
				insertBean.setAssistedVisitFlag("N");
				insertBean.setRecordUserId(commitBean.getCreateUserId());
				offenderVisitsT1Service.offenderVisitsT1(insertBean.getVisitStatus());
			}
			try {
				liReturn = oidvisitRepo.insertIntoOffenderVisits(insertList);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "error in offVstCommit  : ", e);
			}
			try {
				liReturn = oidvisitRepo.offvstInsertOffenderVisitVisitors(insertList);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "error in offVstCommit  : ", e);
			}

			if (commitBean.getvOffenderVisitVisitorsList() != null
					&& commitBean.getvOffenderVisitVisitorsList().size() > 0) {
				for (final VOffenderVisitVisitors visitPerInsertBean : commitBean.getvOffenderVisitVisitorsList()) {
					if (visitPerInsertBean.getOffenderVisitId() == null) {
						visitPerInsertBean.setOffenderVisitId(offenderVisitId);
					}
					visitPerInsertBean.setOffenderBookId(null);
					visitPerInsertBean.setUserName(commitBean.getCreateUserId());
				}
				try {
					liReturn = oidvisitRepo
							.offVstPersInsertVOffenderVisitVisitors(commitBean.getvOffenderVisitVisitorsList());
				} catch (Exception e) {
					logger.error(this.getClass().getName() + "error in offVstCommit  : ", e);
				}
			}

			if (commitBean.getOffenderVisitVisitorsList() != null
					&& commitBean.getOffenderVisitVisitorsList().size() > 0) {
				for (final OffenderVisitVisitors visitOffInstBean : commitBean.getOffenderVisitVisitorsList()) {
					visitOffInstBean.setCreateUserId(commitBean.getCreateUserId());
					if (visitOffInstBean.getOffenderVisitId() == null) {
						visitOffInstBean.setOffenderVisitId(offenderVisitId);
					}
					if (visitOffInstBean.getOffenderIdDisplay() != null) {
						offenderBookId = oidvisitRepo.getOffenderBookId(visitOffInstBean.getOffenderIdDisplay(),
								commitBean.getCreateUserId());
						visitOffInstBean.setOffenderBookId(offenderBookId);
					}
				}
				try {
					liReturn = oidvisitRepo
							.offVstOffInsertOffenderVisitVisitors(commitBean.getOffenderVisitVisitorsList());
				} catch (Exception e) {
					logger.error(this.getClass().getName() + "error in offVstCommit  : ", e);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				bean.setUserName(commitBean.getCreateUserId());
				bean.setRecordUserId(commitBean.getCreateUserId());
				offenderVisitsT1Service.offenderVisitsT1(bean.getVisitStatus());
			});
			liReturn = oidvisitRepo.offVstUpdateVOffenderVisits(commitBean.getUpdateList());
			for (final VOffenderVisits visitsPostUpdate : commitBean.getUpdateList()) {
				if (visitsPostUpdate.getOutcomeReasonCode() != null) {
					 tagVisitsService.cancelVisitors(
							visitsPostUpdate.getOffenderVisitId() == null ? null
									: visitsPostUpdate.getOffenderVisitId().longValue(),
							visitsPostUpdate.getOutcomeReasonCode(), commitBean.getCreateUserId());
				} else if (visitsPostUpdate.getVisitStatus() != null) {
					 tagVisitsService.completeVisitors(visitsPostUpdate, commitBean.getCreateUserId());
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
	 */
	public List<VOffenderVisitVisitors> offVstPersExecuteQuery(final VOffenderVisitVisitors searchRecord) {
		List<VOffenderVisitVisitors> returnList = new ArrayList<>();
		try {
			returnList = oidvisitRepo.offVstPersExecuteQuery(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in offVstPersExecuteQuery  : ", e);
		}
		for (final VOffenderVisitVisitors row : returnList) {
			if (row.getPersonId() != null) {
				final Map<String, Object> retMap = tagVisitsService.populateVisitorDetails(
						searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
						row.getPersonId() != null ? row.getPersonId().longValue() : null, 0L,
						searchRecord.getVisitDate());
				row.setLastName(retMap.get("P_LAST_NAME") != null ? retMap.get("P_LAST_NAME").toString() : null);
				row.setFirstName(retMap.get("P_FIRST_NAME") != null ? retMap.get("P_FIRST_NAME").toString() : null);
				if (retMap.get("P_AGE") != null) {
					row.setAge(new BigDecimal(retMap.get("P_AGE").toString()));
				} else {
					row.setAge(null);
				}
				row.setContactTypeDesc(
						retMap.get("P_CONTACT_TYPE") != null ? retMap.get("P_CONTACT_TYPE").toString() : null);
				row.setRelationshipTypeDesc(
						retMap.get("P_RELATIONSHIP_TYPE") != null ? retMap.get("P_RELATIONSHIP_TYPE").toString()
								: null);
				row.setRestriction(retMap.get("P_RESTRICTION") != null ? retMap.get("P_RESTRICTION").toString() : null);
				row.setGlobalRestriction(
						retMap.get("P_GL_RESTRICTION") != null ? retMap.get("P_GL_RESTRICTION").toString() : null);
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_VST_PERS
	 *
	 */
	@Transactional
	public Integer offVstPersCommit(final VOffenderVisitVisitorsCommitBean commitBean) {
		int liReturn = 0;
		Integer dupVisitor = 0;
		VOffenderVisits offVstBean;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final VOffenderVisitVisitors insertlist : commitBean.getInsertList()) {
				/**
				 * duplicate visitor check
				 */
				insertlist.setOffenderBookId(null);
				dupVisitor = oidvisitRepo.duplicateVisitorPerson(insertlist);
				if (dupVisitor == 1) {
					return 2130;
				}
				insertlist.setOffenderVisitVisitorId(tagVisitsService.getNextOffVisitVisitorId());
				liReturn = tagVisitsService.insertOffenderVisitVisitor(insertlist, commitBean.getCreateUserId());

			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			int count = 0;
			for (final VOffenderVisitVisitors updatelist : commitBean.getUpdateList()) {
				updatelist.setUserName(commitBean.getCreateUserId());
				if ("ABS".equals(updatelist.getEventOutcome())) {
					final Integer visitorsCount = oidvisitRepo.offVstPersMaster(updatelist.getOffenderVisitId(),
							updatelist.getVisitOffenderBookId());
					count = count + 1;
					if (count == visitorsCount) {
						offVstBean = new VOffenderVisits();
						offVstBean = oidvisitRepo.getOnVisitMaster(updatelist.getOffenderVisitId(),
								updatelist.getVisitOffenderBookId());
						if (offVstBean.getVisitDate().before(dateService.getDBTime())
								&& offVstBean.getOutcomeReasonCode() == null) {
							offVstBean.setEventOutcome("ABS");
							offVstBean.setEventStatus("CANC");
							offVstBean.setVisitStatus("CANC");
							offVstBean.setOutcomeReasonCode("NSHOW");
							tagVisitsService.cancelVisitors(
 									offVstBean.getOffenderVisitId() == null ? null
											: offVstBean.getOffenderVisitId().longValue(),
									offVstBean.getOutcomeReasonCode(), commitBean.getCreateUserId());
						}
					}
				}
			
			}
			liReturn = oidvisitRepo.offVstPersUpdateVOffenderVisitVisitors(commitBean.getUpdateList());
			if(liReturn==1) {
				Integer countValue=null;
				if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
					countValue=oidvisitRepo.OidvisitAttendedChanges(commitBean.getUpdateList().get(0).getOffenderVisitId());
						if(countValue==0) {
							oidvisitRepo.updateOffenderVisits(commitBean.getUpdateList().get(0).getOffenderVisitId(),commitBean.getUpdateList().get(0).getModifyUserId());
					}
				}
			}
			
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidvisitRepo.offVstPersDeleteVOffenderVisitVisitors(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Images> imagesVisitorsExecuteQuery(final Images searchRecord) {
		try {
			return oidvisitRepo.imagesVisitorsExecuteQuery(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in imagesVisitorsExecuteQuery  : ", e);
			return null;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderVisitVisitors> offVstOffExecuteQuery(final OffenderVisitVisitors searchRecord) {
		List<OffenderVisitVisitors> returnList = new ArrayList<>();
		BigDecimal rootOffId = null;
		try {
			returnList = oidvisitRepo.offVstOffExecuteQuery(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in offVstOffExecuteQuery  : ", e);
		}
		for (final OffenderVisitVisitors row : returnList) {
			if (row.getOffenderVisitVisitorId() != 0) {
				rootOffId = tagVisitsService.getRootOffenderIdFromBook(row.getOffenderBookId());
				row.setVisitorOffenderId(rootOffId);
				final OffenderVisitVisitors offDetails = tagVisitsService.populatedOffenderDetails(
						searchRecord.getOffenderBookId(), rootOffId, searchRecord.getVisitDate(),
						searchRecord.getCreateUserId());

				row.setLastName(offDetails.getLastName() != null ? offDetails.getLastName() : "");
				row.setFirstName(offDetails.getFirstName() != null ? offDetails.getFirstName() : "");
				row.setContactType(offDetails.getContactType() != null ? offDetails.getContactType() : "");
				row.setRelationshipType(
						offDetails.getRelationshipType() != null ? offDetails.getRelationshipType() : "");
				row.setRestriction(offDetails.getRestriction() != null ? offDetails.getRestriction() : "");
				row.setAgyLocId(offDetails.getAgyLocId() != null ? offDetails.getAgyLocId() : "");
				row.setOffenderIdDisplay(
						offDetails.getOffenderIdDisplay() != null ? offDetails.getOffenderIdDisplay() : "");
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_VST_OFF
	 *
	 */
	@Transactional
	public Integer offVstOffCommit(final OffenderVisitVisitorsCommitBean commitBean) {
		int liReturn = 0;
		VOffenderVisits offVstBean;
		BigDecimal offenderBookId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderVisitVisitors insertBean : commitBean.getInsertList()) {
				insertBean.setCreateUserId(commitBean.getCreateUserId());
				if (insertBean.getOffenderIdDisplay() != null) {
					try {
						offenderBookId = oidvisitRepo.getOffenderBookId(insertBean.getOffenderIdDisplay(),
								commitBean.getCreateUserId());
					} catch (Exception e) {
						logger.error(this.getClass().getName() + "error in offVstOffCommit  : ", e);
					}
					insertBean.setOffenderBookId(offenderBookId);

				}
			}
			try {
				liReturn = oidvisitRepo.offVstOffInsertOffenderVisitVisitors(commitBean.getInsertList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "error in offVstOffCommit  : ", e);
				return 5;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			int count = 0;
			for (final OffenderVisitVisitors updatelist : commitBean.getUpdateList()) {
				commitBean.getUpdateList().forEach(bean -> {
					bean.setModifyUserId(commitBean.getCreateUserId());
				});
				if ("ABS".equals(updatelist.getEventOutcome())) {
					final Integer visitorsCount = oidvisitRepo.offVstOffMaster(updatelist.getOffenderVisitId());
					count = count + 1;
					if (count == visitorsCount) {
						offVstBean = new VOffenderVisits();
						offVstBean = oidvisitRepo.getOnVisitMasterFromVisitingOffenders(updatelist.getOffenderVisitId(),
								updatelist.getOffenderBookId());
						if (offVstBean.getVisitDate().before(dateService.getDBTime())
								&& offVstBean.getOutcomeReasonCode() == null) {
							offVstBean.setEventOutcome("ABS");
							offVstBean.setEventStatus("CANC");
							offVstBean.setVisitStatus("CANC");
							offVstBean.setOutcomeReasonCode("NSHOW");
							tagVisitsService.cancelVisitors(
									offVstBean.getOffenderVisitId() == null ? null
											: offVstBean.getOffenderVisitId().longValue(),
									offVstBean.getOutcomeReasonCode(), commitBean.getCreateUserId());

						}
					}
				}
			}
			try {
				liReturn = oidvisitRepo.offVstOffUpdateOffenderVisitVisitors(commitBean.getUpdateList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "error in offVstOffCommit  : ", e);

			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			try {
				liReturn = oidvisitRepo.offVstOffDeleteOffenderVisitVisitors(commitBean.getDeleteList());
			} catch (Exception e) {
				logger.error(this.getClass().getName() + "error in offVstOffCommit  : ", e);
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Images> imagesVisitingOffExecuteQuery(final Images searchRecord) {
		try {
			return oidvisitRepo.imagesVisitingOffExecuteQuery(searchRecord);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in imagesVisitingOffExecuteQuery  : ", e);
			return null;
		}

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgVisitTypeRecordGroup() {
		try {
			return oidvisitRepo.rgVisitTypeRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in rgVisitTypeRecordGroup  : ", e);
			return null;
		}

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> rgMoveCancRsRecordGroup() {
		try {
			return oidvisitRepo.rgMoveCancRsRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in rgMoveCancRsRecordGroup  : ", e);
			return null;
		}

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<VOffenderVisits> rgVisitTimeSlotsRecordGroup(final String parentField) {
		List<VOffenderVisits> returnList = new ArrayList<>();
		final String[] arr = parentField.split(",");
		final String[] vDateArr = arr[0].toString().split(":");
		final String[] rAgyLocIdArr = arr[2].toString().split(":");
		final String[] hAgyLocIdArr = arr[1].toString().split(":");
		final String visitAgyLocId = rAgyLocIdArr.length == 2 ? rAgyLocIdArr[1].trim() : null;
		final String agyLocId = hAgyLocIdArr[1].trim();
		final String visitDate = vDateArr[1].trim();
		try {
			returnList = oidvisitRepo.rgVisitTimeSlotsRecordGroup(visitAgyLocId, agyLocId, visitDate);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in rgVisitTimeSlotsRecordGroup  : ", e);
		}
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag()) && ApplicationConstants.YFLAG.equals(refcode.getAgyLocId())) {
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
	 *
	 *
	 */
	public List<ReferenceCodes> rgVisCompleteRecordGroup() {
		try {
			return oidvisitRepo.rgVisCompleteRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in rgVisCompleteRecordGroup  : ", e);
			return null;
		}

	}

	/**
	 * This method is used to execute when the endTime field is entered
	 *
	 *
	 */
	@Override
	public VOffenderVisits endTimeValidateQuery(final VOffenderVisits searchRecord) {
		final VOffenderVisits returnObject = new VOffenderVisits();
		String supLevel = null;
		String iepSecConfig=null;
		String iepLevel;
	   iepSecConfig =oidvisitRepo.checkIepSecConfig(searchRecord.getAgyLocId());
	   if(iepSecConfig != null) {
		if(iepSecConfig.equals("SEC_LEVEL")) {
		supLevel = pimsWeightService.getSupLevel(
				searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
				searchRecord.getUserName());
		if (supLevel == null) {
			supLevel = oidvisitRepo.classPending();
		}
		final VOffenderVisits remVisits = tagVisitsService.visitCalc(searchRecord.getAgyLocId(),
				searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
				supLevel, searchRecord.getVisitType(), searchRecord.getVisitDate());

		returnObject.setCycleEnds(remVisits.getCycleEnds() != null ? remVisits.getCycleEnds() : null);
		returnObject.setTotalRemainingVisits(
				remVisits.getTotalRemainingVisits() != null ? remVisits.getTotalRemainingVisits() : null);
		returnObject.setRemainingVisitsType(
				remVisits.getRemainingVisitsType() != null ? remVisits.getRemainingVisitsType() : null);
		returnObject.setTotalRemainingTime(
				remVisits.getTotalRemainingTime() != null ? remVisits.getTotalRemainingTime() : null);
		returnObject.setRemainingTimeType(
				remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeType() : null);
	   } else {
		   if(iepSecConfig.equals("IEP_LEVEL")) {
				iepLevel = oidvisitRepo.getOffenderIepConfigData(
						searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null);
				if (iepLevel == null) {
					iepLevel = oidvisitRepo.checkIeplevel();
				}
				returnObject.setSupLevel(iepLevel);

				final VOffenderVisits remVisits = tagVisitsService.iepVisitCalc(searchRecord.getAgyLocId(),
						searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
								iepLevel, searchRecord.getVisitType(), searchRecord.getVisitDate());

				returnObject.setCycleEnds(remVisits.getCycleEnds() != null ? remVisits.getCycleEnds() : null);
				returnObject.setTotalRemainingVisits(
						remVisits.getTotalRemainingVisits() != null ? remVisits.getTotalRemainingVisits() : null);
				returnObject.setRemainingVisitsType(
						remVisits.getRemainingVisitsType() != null ? remVisits.getRemainingVisitsType() : null);
				returnObject.setTotalRemainingTime(
						remVisits.getTotalRemainingTime() != null ? remVisits.getTotalRemainingTime() : null);
				returnObject.setRemainingTimeType(
						remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeType() : null);
	   }
	}
	   }
		return returnObject;
	   
}

	/**
	 * This method is used to execute when the visitType field is entered
	 *
	 *
	 */
	@Override
	public VOffenderVisits visitTypeValidateQuery(final VOffenderVisits searchRecord) {
		final VOffenderVisits returnObject = new VOffenderVisits();
		
		String supLevel = null;
		String iepSecConfig=null;
		String iepLevel;
	  iepSecConfig =oidvisitRepo.checkIepSecConfig(searchRecord.getAgyLocId());
	  if(iepSecConfig != null) {
		if(iepSecConfig.equals("SEC_LEVEL")) {
		supLevel = pimsWeightService.getSupLevel(
				searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
				searchRecord.getUserName());
		if (supLevel == null) {
			supLevel = oidvisitRepo.classPending();
		}
		returnObject.setSupLevel(supLevel);

		final VOffenderVisits remVisits = tagVisitsService.visitCalc(searchRecord.getAgyLocId(),
				searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
				supLevel, searchRecord.getVisitType(), searchRecord.getVisitDate());

		returnObject.setCycleEnds(remVisits.getCycleEnds() != null ? remVisits.getCycleEnds() : null);
		returnObject.setTotalRemainingVisits(
				remVisits.getTotalRemainingVisits() != null ? remVisits.getTotalRemainingVisits() : null);
		returnObject.setRemainingVisitsType(
				remVisits.getRemainingVisitsType() != null ? remVisits.getRemainingVisitsType() : null);
		returnObject.setTotalRemainingTime(
				remVisits.getTotalRemainingTime() != null ? remVisits.getTotalRemainingTime() : null);
		returnObject.setRemainingTimeType(
				remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeType() : null);
		returnObject.setRemainingTimeTypeTemp(
				remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeTypeTemp() : null);
		returnObject.setTotalRemainingTimeTemp(
				remVisits.getTotalRemainingTimeTemp() != null ? remVisits.getTotalRemainingTimeTemp() : null);
			}else 
				if(iepSecConfig.equals("IEP_LEVEL")) {
					iepLevel = oidvisitRepo.getOffenderIepConfigData(
							searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null);
					if (iepLevel == null) {
						iepLevel = oidvisitRepo.checkIeplevel();
					}
					returnObject.setSupLevel(iepLevel);

					final VOffenderVisits remVisits = tagVisitsService.iepVisitCalc(searchRecord.getAgyLocId(),
							searchRecord.getOffenderBookId() != null ? searchRecord.getOffenderBookId().longValue() : null,
									iepLevel, searchRecord.getVisitType(), searchRecord.getVisitDate());

					returnObject.setCycleEnds(remVisits.getCycleEnds() != null ? remVisits.getCycleEnds() : null);
					returnObject.setTotalRemainingVisits(
							remVisits.getTotalRemainingVisits() != null ? remVisits.getTotalRemainingVisits() : null);
					returnObject.setRemainingVisitsType(
							remVisits.getRemainingVisitsType() != null ? remVisits.getRemainingVisitsType() : null);
					returnObject.setTotalRemainingTime(
							remVisits.getTotalRemainingTime() != null ? remVisits.getTotalRemainingTime() : null);
					returnObject.setRemainingTimeType(
							remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeType() : null);
					returnObject.setRemainingTimeTypeTemp(
							remVisits.getRemainingTimeType() != null ? remVisits.getRemainingTimeTypeTemp() : null);
					returnObject.setTotalRemainingTimeTemp(
							remVisits.getTotalRemainingTimeTemp() != null ? remVisits.getTotalRemainingTimeTemp() : null);
			}
	  }
		return returnObject;
	}

	/**
	 * This method is used to execute the duplicateVisit
	 *
	 *
	 */
	@Override
	public Integer duplicateVisit(final VOffenderVisits searchRecord) {
		String returnDup = null;
		try {
			if (searchRecord.getOffenderVisitId() == null) {
				returnDup = oidvisitRepo.duplicateVisit(searchRecord);
			} else {
				returnDup = oidvisitRepo.duplicateVisitQueryTwo(searchRecord);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in duplicateVisit  : ", e);

		}
		return (returnDup == null) ? 0 : 1;
	}

	/**
	 * This method is used to execute the validateVisitor
	 *
	 *
	 */
	@Override
	public String validateVisitor(final ValidateVisitorBean bean) {
		String returnValue = null;
		final StringBuffer validateList = new StringBuffer();
		if (bean.getvPersonId() != null) {
			if (!bean.getVisitOfflist().isEmpty()) {
				for (final OffenderVisitVisitors offenderObj : bean.getVisitOfflist()) {
					if (offenderObj.getOffenderBookId() != null) {
						final Integer personId = bean.getvPersonId() != null
								? Integer.parseInt(bean.getvPersonId().toString())
								: 0;
						final Integer offNum = oidvisitRepo.isAuthorisedPerson(personId,
								Integer.parseInt(offenderObj.getOffenderBookId().toString()));
						if (offNum == 0) {
							validateList.append(offenderObj.getOffenderIdDisplay());
							validateList.append(" - ");
							validateList.append(offenderObj.getLastName());
							validateList.append(", ");
							validateList.append(offenderObj.getFirstName());
							validateList.append(". ");
						}

					}

				}
			}
		} else {
			if (!bean.getVisitOfflist().isEmpty()) {
				for (final OffenderVisitVisitors offenderObj : bean.getVisitOfflist()) {
					if (offenderObj.getOffenderBookId() != null && bean.getvOffenderBookId() != null
							&& bean.getvOffenderBookId().compareTo(offenderObj.getOffenderBookId()) != 0) {
						final Integer offBkdId = bean.getvOffenderBookId() != null
								? Integer.parseInt(bean.getvOffenderBookId().toString())
								: 0;
						final Integer offNum = isAuthorisedOffender(offBkdId,
								Integer.parseInt(offenderObj.getOffenderBookId().toString()));
						if (offNum == 0) {
							validateList.append(offenderObj.getOffenderIdDisplay());
							validateList.append(" - ");
							validateList.append(offenderObj.getLastName());
							validateList.append(", ");
							validateList.append(offenderObj.getFirstName());
							validateList.append(". ");
						}

					}

				}
			}
		}
		if (bean.getvPersonId() == null) {
			if (!bean.getVisitorList().isEmpty()) {
				for (final VOffenderVisitVisitors visitorrObj : bean.getVisitorList()) {
					if (visitorrObj.getPersonId() != null) {
						final Integer offBkdId = bean.getvOffenderBookId() != null
								? Integer.parseInt(bean.getvOffenderBookId().toString())
								: 0;
						final Integer perNum = oidvisitRepo
								.isAuthorisedPerson(Integer.parseInt(visitorrObj.getPersonId().toString()), offBkdId);
						if (perNum == 0) {
							validateList.append(visitorrObj.getPersonId());
							validateList.append(" - ");
							validateList.append(visitorrObj.getLastName());
							validateList.append(", ");
							validateList.append(visitorrObj.getFirstName());
							validateList.append(". ");
						}
					}
				}
			}
		}
		if (validateList.toString().trim().isEmpty()) {
			returnValue = "NULL";
		} else {
			returnValue = validateList.toString();
		}

		return returnValue;
	}

	/**
	 * This method is used to execute the visitPerPreInsert
	 *
	 *
	 */
	@Override
	public Integer visitPerPreInsert(final VOffenderVisitVisitors searchBean) {
		Integer returnValue = 0;
		if (searchBean.getOffenderVisitId() == null) {
			searchBean.setOffenderVisitId(tagVisitsService.getNextOffVisitId());
		}
		try {
		returnValue = oidvisitRepo.duplicateVisitorPerson(searchBean);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + "error in visitPerPreInsert  : ", e);
		}
		if (returnValue == 1) {
			return 2130;
		} else {
			if (searchBean.getPersonId() != null) {
				final BigDecimal offBookId = oidvisitRepo.overlapVisit(searchBean);
				returnValue = (offBookId == null || offBookId.compareTo(BigDecimal.ZERO) == 0) ? 0 : 1;
				if (returnValue == 1) {
					return 2142;
				}
			}
		}
		return returnValue;
	}

	/**
	 * This method is used to execute the checkVisitorLimit
	 *
	 *
	 */
	@Override
	public Integer checkVisitorLimit(final VOffenderVisits searchBean) {
		Integer returnVal = null;
		if (searchBean.getOffenderVisitId() == null) {
			searchBean.setOffenderVisitId(tagVisitsService.getNextOffVisitId());
		}
		String supLevel = pimsWeightService.getSupLevel(
				searchBean.getOffenderBookId() != null ? searchBean.getOffenderBookId().longValue() : null,
				searchBean.getUserName());
		if (supLevel == null) {
			supLevel = oidvisitRepo.classPending();
		}
		searchBean.setSupLevel(supLevel);
		if (supLevel == null) {
			return 1;
		}
		final Integer totalBooked = oidvisitRepo.totalBooked(searchBean);
		final Integer maxVisitorType = oidvisitRepo.findMaxVisitorType(searchBean);
		if (maxVisitorType != null) {
			if ((totalBooked + 2) > maxVisitorType) {
				returnVal = 0;
			} else {
				returnVal = 1;
			}
		} else {
			returnVal = 1;
		}
		return returnVal;
	}

	/**
	 * This method is used to execute the recheckTimeSlots
	 *
	 *
	 */
	@Override
	public Integer recheckTimeSlots(final VOffenderVisits searchBean) {
		Integer returnVal = null;
		Integer adultAge;
		if (searchBean.getOffenderVisitId() == null) {
			searchBean.setOffenderVisitId(tagVisitsService.getNextOffVisitId());
		}
		adultAge = oidvisitRepo.findAdultAge();
		searchBean.setAdultAge(adultAge);
		final AgencyCounts cSlots = oidvisitRepo.findCSlots(searchBean.getAgencyVisitSlotId());
		final Integer maxGroups = cSlots.getTotalActual() != null ? cSlots.getTotalActual() : 999;
		final Integer maxAdults = cSlots.getOutTotal() != null ? cSlots.getOutTotal() : 999;
		final Integer capacity = cSlots.getTotalOtherOut() != null ? cSlots.getTotalOtherOut() : 999;
		final AgencyCounts cVis = oidvisitRepo.findCVis(searchBean);
		final Integer groupsBooked = cVis.getTotalActual() != null ? cVis.getTotalActual() : 1;
		final Integer adultsBooked = cVis.getOutTotal() != null ? cVis.getOutTotal() : 0;
		final Integer totalBooked = cVis.getTotalOtherOut() != null ? cVis.getTotalOtherOut() : 0;
		final AgencyCounts cAll = oidvisitRepo.findCAll(searchBean);
		final Integer groupsAlloc = cAll.getTotalActual() != null ? cAll.getTotalActual() : 0;
		final Integer adultsAlloc = cAll.getOutTotal() != null ? cAll.getOutTotal() : 0;
		final Integer totalAlloc = cAll.getTotalOtherOut() != null ? cAll.getTotalOtherOut() : 0;
		if ((groupsBooked + searchBean.getGroup() + groupsAlloc > maxGroups)
				|| (adultsBooked + searchBean.getAdult() + adultsAlloc >= maxAdults)
				|| (totalBooked + searchBean.getPerson() + totalAlloc >= capacity)) {
			returnVal = 0;
		} else {
			returnVal = 1;
		}
		return returnVal;
	}

	/**
	 * This method is used to execute the visitOffPreInsert
	 *
	 *
	 */
	@Override
	public String visitOffPreInsert(final OffenderVisitVisitors searchBean) {
		String returnValue = null;
		Integer dupCheck = 0;
		Integer returnFlag = 0;
		if (searchBean.getOffenderVisitId() == null) {
			searchBean.setOffenderVisitId(tagVisitsService.getNextOffVisitId());
		}
		dupCheck = oidvisitRepo.duplicateVisitorOffender(searchBean);
		if (dupCheck == 1) {
			return "3115";
		} else {
			if (searchBean.getPersonId() == null) {
				final BigDecimal offBookId = oidvisitRepo.overlapVisitBookId(searchBean.getOffenderBookId(),
						searchBean.getOffenderVisitId(), searchBean.getVisitDate(), searchBean.getStartTime(),
						searchBean.getEndTime());
				returnFlag = offBookId == null || offBookId.compareTo(BigDecimal.ZERO) == 0 ? 0 : 1;
				if (returnFlag == 1) {
					returnValue = oidvisitRepo.getlOverlapDetails(offBookId);
				} else {
					returnValue = "NULL";
				}
			} else {
				returnValue = "NULL";
			}
		}
		return returnValue;
	}

	@Override
	public Integer getOffenderRestrictions(final VOffenderVisits searchBean) {
		Integer returnValue = 0;
		if (searchBean.getOffenderBookId() == null) {
			searchBean.setOffenderBookId(
					oidvisitRepo.getOffenderBookId(searchBean.getOffenderIdDisplay(), searchBean.getUserName()));
		}
		returnValue = oidvisitRepo.getOffenderRestrictions(searchBean);
		return returnValue;
	}

	@Override
	public VOffenderVisits getOffenderDetails(final String offenderIdDisplay, String userName) {
		final VOffenderVisits returnVal = new VOffenderVisits();
		returnVal.setOffenderId(oidvisitRepo.getOffenderId(offenderIdDisplay));
		returnVal.setOffenderBookId(oidvisitRepo.getOffenderBookId(offenderIdDisplay, userName));
		return returnVal;
	}

	@Override
	public Integer isAuthorisedOffender(final Integer hoffenderBookId, final Integer voffenderBookId) {
		final Integer returnValue;
		final BigDecimal contactRootOffId = oidvisitRepo.getRootOffenderIdFromBook(voffenderBookId);
		returnValue = oidvisitRepo.isAuthorisedOffender(contactRootOffId, hoffenderBookId);
		return returnValue;
	}

	@Override
	public Integer isAuthorisedPerson(final Integer personId, final Integer offenderBookId) {
		final Integer returnValue;
		returnValue = oidvisitRepo.isAuthorisedPerson(personId, offenderBookId);
		return returnValue;
	}

	@Override
	public Integer getVisitorRestrictions(final VOffenderVisitVisitors searchBean) {
		Integer returnValue = 0;
		returnValue = oidvisitRepo.getVisitorRestrictions(searchBean.getPersonId(), searchBean.getOffenderBookId(),
				searchBean.getVisitDate());
		return returnValue;
	}

	@Override
	public String overlapVisitForVisitors(final VOffenderVisits searchBean) {
		String returnValue = null;
		if (searchBean.getOffenderVisitId() == null) {
			searchBean.setOffenderVisitId(tagVisitsService.getNextOffVisitId());
		}
		if (searchBean.getPersonId() != null) {
			final BigDecimal offBookId = oidvisitRepo.overlapVisitPerId(searchBean.getPersonId(),
					searchBean.getOffenderVisitId(), searchBean.getVisitDate(), searchBean.getStartTime(),
					searchBean.getEndTime());
			returnValue = (offBookId == null || offBookId.compareTo(BigDecimal.ZERO) == 0) ? null
					: offBookId.toString();
			if (returnValue != null) {
				returnValue = oidvisitRepo.getlOverlapDetails(offBookId);
			} else {
				returnValue = "false";
			}
		}
		return returnValue;
	}

	@Override
	public Integer checkContactActive(final Integer offenderBookId, final Integer personId) {
		final Integer returnValue;
		returnValue = oidvisitRepo.checkContactActive(offenderBookId, personId);
		return returnValue;
	}

	@Override
	public List<AgencyVisitTimes> oidvisitCheckListEntry() {
		return oidvisitRepo.oidvisitCheckListEntry();
	}

	@Override
	public VOffenderVisits chkVisitConflicts(final VOffenderVisits searchBean) {
		final VOffenderVisits returnVal = new VOffenderVisits();
		final String warngMsg = tagVisitsService.chkVisitConflicts(
				searchBean.getOffenderBookId() != null ? searchBean.getOffenderBookId().longValue() : null,
				searchBean.getOffenderVisitId() != null ? searchBean.getOffenderVisitId().longValue() : null,
				searchBean.getVisitDate(), searchBean.getStartTime(), searchBean.getEndTime(),
				searchBean.getVisitInternalLocationId() != null ? searchBean.getVisitInternalLocationId().longValue()
						: null);
		if (warngMsg != null) {
			returnVal.setWarningMsg(warngMsg);
		}
		return returnVal;
	}

	@Override
	public String checkNonAssocationDetails(VOffenderVisitsCommitBean commitBean) {
		String finalData = "";
		String individalConflict = "";
		String gangConflict = "";
		List<VOffenderVisits> vOffenderVisitsList = new ArrayList<VOffenderVisits>();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				vOffenderVisitsList.addAll(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				vOffenderVisitsList.addAll(commitBean.getUpdateList());
			}
			if (vOffenderVisitsList != null && vOffenderVisitsList.size() > 0) {
				for (VOffenderVisits vOffenderVisits : vOffenderVisitsList) {
					List<OffenderNaDetails> nonAssocationOffenderList = new ArrayList<OffenderNaDetails>();
					BigDecimal offenderBookId = vOffenderVisits.getVisitOffenderBookId();
					Integer offenderBookIdInt = Integer.valueOf(offenderBookId.intValue());
					BigDecimal internalLocationId = vOffenderVisits.getVisitInternalLocationId();
					Integer locationId = Integer.valueOf(internalLocationId.intValue());
					// individualConflict
					try {
						nonAssocationOffenderList = nonAssociationService
								.getNonAssociationOffenderList(offenderBookIdInt);
					} catch (Exception e) {
						logger.error(this.getClass().getName() + "error in checkNonAssocationDetails  : ", e);
					}
					if (nonAssocationOffenderList != null && nonAssocationOffenderList.size() > 0) {
						for (OffenderNaDetails offenderNaDetails : nonAssocationOffenderList) {
							List<VOffenderVisits> vOffenderVisitsDeatils = new ArrayList<VOffenderVisits>();
							try {
								vOffenderVisitsDeatils = oidvisitRepo.checkNonAssocationDetails(
										offenderNaDetails.getNsOffenderBookId(), vOffenderVisits.getVisitDate(),
										locationId);
							} catch (Exception e) {
								vOffenderVisitsDeatils = Collections.emptyList();
								logger.error(this.getClass().getName() + "error in checkNonAssocationDetails  : ", e);
							}
							individalConflict = individalConflict + offenderDetails(vOffenderVisitsDeatils);
						}
					}
					// gangConflictDetails
					List<OffenderStgAffiliations> nonAssocationOffenderListByGang = new ArrayList<OffenderStgAffiliations>();
					try {
						nonAssocationOffenderListByGang = nonAssociationService
								.getOffendersOfNonAssociationGroup(offenderBookId);
					} catch (Exception e) {
						nonAssocationOffenderListByGang = null;
						logger.error(this.getClass().getName() + "error in checkNonAssocationDetails  : ", e);
					}
					if (nonAssocationOffenderListByGang != null && nonAssocationOffenderListByGang.size() > 0) {
						for (OffenderStgAffiliations offenderStgAffiliations : nonAssocationOffenderListByGang) {
							List<VOffenderVisits> vOffenderVisitsDeatils = new ArrayList<VOffenderVisits>();
							try {
								vOffenderVisitsDeatils = oidvisitRepo.checkNonAssocationDetails(
										offenderStgAffiliations.getOffenderBookId(), vOffenderVisits.getVisitDate(),
										locationId);
							} catch (Exception e) {
								logger.error(this.getClass().getName() + "error in checkNonAssocationDetails  : ", e);
							}
							gangConflict = gangConflict + offenderDetails(vOffenderVisitsDeatils);
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(" Exception ");
		}
		if (individalConflict.length() != 0 && gangConflict.length() != 0)
			finalData = "oidvisit.indinonassocconflict:\n\n" + individalConflict + "\n\n"
					+ "oidvisit.gangnonassocconflict:\n\n" + gangConflict;
		else if (individalConflict.length() != 0)
			finalData = "oidvisit.indinonassocconflict:\n\n" + individalConflict;
		else if (gangConflict.length() != 0)
			finalData = "oidvisit.gangnonassocconflict:\n\n" + gangConflict;

		return (finalData.length() != 0) ? "oidvisit.visitSchedule" + "\n\n" + finalData : ApplicationConstants.EMPTYDATA;

	}

	public String offenderDetails(List<VOffenderVisits> vOffenderVisitsDeatils) {
		String offenderDetails = "";
		for (VOffenderVisits vOffenderVisitsData : vOffenderVisitsDeatils) {
			String offenderName = vOffenderVisitsData.getOffenderLastName() + " "
					+ vOffenderVisitsData.getOffenderFirstName();
			String offenderId = vOffenderVisitsData.getOffenderIdDisplay();
			java.util.Date vDate = vOffenderVisitsData.getVisitDate();
			java.util.Date sDate = vOffenderVisitsData.getStartTime();
			java.util.Date eDate = vOffenderVisitsData.getEndTime();
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			String visitDate = sdf1.format(vDate);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String startTime = sdf.format(sDate);
			String endTime = sdf.format(eDate);
			offenderDetails = offenderDetails + offenderName + "," + offenderId + "," + visitDate + "," + startTime
					+ "," + endTime + "\n";
		}
		return offenderDetails;
	}

	@Override
	public Integer getMaxVisitors(VOffenderVisits vOffVisitrs) {
		Integer maxVisitors = oidvisitRepo.getMaxVisitors(vOffVisitrs);
		return maxVisitors;
	}

@Override
	public List<ReferenceCodes> rgVisitTypeRecordGroup(String agyLocId, Long offenderBookId, String iepSecLevels,String caseLoadType) {
		List<ReferenceCodes> refList=new ArrayList<ReferenceCodes>();
		if (iepSecLevels.equals("IEP_LEVEL")) {
			refList= oidvisitRepo.rgVisitTypeRecordGroup(agyLocId, offenderBookId);
		} 
		if (iepSecLevels.equals("SEC_LEVEL")) {
			String supLvlType = oidvisitRepo.getSupLevel(offenderBookId, caseLoadType);
			if (!supLvlType.equals("")) {
				refList= oidvisitRepo.getRgVisitType(agyLocId, offenderBookId, caseLoadType, supLvlType);
			} else {
				refList= oidvisitRepo.getRgVisitTypesOff(agyLocId, offenderBookId, caseLoadType);
			}
		}
		return refList;
	}
	
}