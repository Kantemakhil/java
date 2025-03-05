package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.TransferBWOfficerCommitBean;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.iwp.OcdorassRepository;
import net.syscon.s4.iwp.OcdtapowRepository;
import net.syscon.s4.iwp.OcdtapowService;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgService;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.CasePlansT2Service;
import net.syscon.s4.triggers.CasePlansT3Service;
import net.syscon.s4.triggers.CasePlansTwfService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/**
 * Class OcdtapowServiceImpl
 */
@Service
public class OcdtapowServiceImpl extends BaseBusiness implements OcdtapowService {

	@Autowired
	private OcdtapowRepository ocdtapowRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private PimsFileTrackingService pimsFileTrackingService;

	@Autowired
	private PimsWeightService pimsWeightService; 
	
	@Autowired
	private ComunityPkgService comunityPkgService;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	@Autowired
	private CasePlansT1Service casePlansT1Service;
	@Autowired
	private CasePlansT2Service casePlansT2Service;
	@Autowired
	private CasePlansT3Service casePlansT3Service;
	@Autowired
	private CasePlansTwfService casePlansTwfService;
	@Autowired
	private OcdorassRepository ocdorassRepository;

	private static Logger logger = LogManager.getLogger(OcdtapowServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	@Override
	public List<StaffLocationRoles> staffLrExecuteQuery(final StaffLocationRoles searchRecord) {

		final List<StaffLocationRoles> staffLocRoles = ocdtapowRepository.staffLrExecuteQuery(searchRecord);

		for (final StaffLocationRoles staffLocationRole : staffLocRoles) {
			staffLocationRole.setSupervisorStaffId(searchRecord.getSupervisorStaffId());
			final String cursor = ocdtapowRepository.getpreviosWorkData(staffLocationRole);
			if (cursor != null) {
				staffLocationRole.setSkillSubTypeFlag(cursor);
			} else {
				staffLocationRole.setSkillSubTypeFlag(ApplicationConstants.NFLAG);
			}
			StaffMemberRoles roles=new StaffMemberRoles();
			BeanUtils.copyProperties(staffLocationRole, roles);
			roles.setStaffId(Integer.parseInt(staffLocationRole.getStaffId()));
			final Long workLoad = pimsWeightService.officerWork(roles,searchRecord.getCreateUserId());
			staffLocationRole.setSkillSubType(workLoad+"");
			VStaffLocation location=new VStaffLocation();
			location.setStaffId(Integer.parseInt(staffLocationRole.getStaffId()));
			location.setFromDate(staffLocationRole.getSupervisorFromDate());
			BeanUtils.copyProperties(staffLocationRole, location);
			final Long noOffender =comunityPkgService.getOfficerPo(location);
			staffLocationRole.setNoOffender(noOffender+"");
		}

		return staffLocRoles;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	@Override
	public List<OffenderBookings> offBkg1ExecuteQuery(final StaffMembers searchRecord) {
		List<OffenderBookings> offBookList = new ArrayList<OffenderBookings>();
		offBookList = ocdtapowRepository.offBkg1ExecuteQuery(searchRecord);
		for (final OffenderBookings offenderBookings : offBookList) {
			//			BigDecimal aliasOffenderId = ocdtapowRepository.getaliasOffenderId(offenderBookings);
			final Offenders offender = ocdtapowRepository.getOffenderDetails(offenderBookings);
			offenderBookings.setDspFirstName(offender.getFirstName());
			offenderBookings.setDspLastName(offender.getLastName());
			offenderBookings.setOffenderIdDisplay(offender.getOffenderIdDisplay());
			offenderBookings.setStaffId(offenderBookings.getAssignedStaffId().longValue());
			List<Integer> workedWithStaffMem = ocdorassRepository.getWorkedStaffMembers(offenderBookings.getOffenderBookId());
			offenderBookings.setWorkedStaffMembers(workedWithStaffMem);
			final Date startDate = ocdtapowRepository.getStartDate(offenderBookings);
			offenderBookings.setBookingBeginDate(startDate);
			final BigDecimal caseOfficerId = ocdtapowRepository.getcaseOfficerId(offenderBookings);
			offenderBookings.setCaseOfficerId(caseOfficerId);
		}
		return offBookList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	@Override
	public List<AgencyLocations> cgfkStaffLrDspDescriptionRecordGroup(final String caseLaadId) {
		return ocdtapowRepository.cgfkStaffLrDspDescriptionRecordGroup(caseLaadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	@Override
	public List<ReferenceCodes> cgfkVOffDetSkillTypeRecordGroup() {
		return ocdtapowRepository.cgfkVOffDetSkillTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	@Override
	public List<ReferenceCodes> cgfkVOffDetSkillSubTypeRecordGroup() {
		return ocdtapowRepository.cgfkVOffDetSkillSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	@Override
	public List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup() {
		return ocdtapowRepository.cgfkStaffLrDspLastNameRecordGroup();

	}

	@Override
	@Transactional
	public TransferBWOfficerCommitBean saveData(final TransferBWOfficerCommitBean commitBean) {
		Date nextReviewDate = null;
		Long offasId = null;
		StaffLocationRoles staffLocationRole = null;
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		final Calendar calendar = Calendar.getInstance();
		final CasePlans casePlans = new CasePlans();
		Date fromdate = null;
		final AssignmentTransfers assignmentTransfers = new AssignmentTransfers();
		OffenderCommunityFile offenderCommunityFile = new OffenderCommunityFile();
		if (commitBean != null) {
			final List<StaffLocationRoles> staffupdateList = commitBean.getStaffupdateList();
			final List<OffenderBookings> offUpdateList = commitBean.getUpdateList();
			final StaffMembers staffMembers = commitBean.getStaffMembers();
			if (staffupdateList != null) {
				staffLocationRole = staffupdateList.get(0);
				staffLocationRole.setAgyLocId(staffLocationRole.getCalAgyLocId());
			}

			for (final OffenderBookings OffenderBooking : offUpdateList) {
				final Long planId = ocdtapowRepository.getCasePlanId(OffenderBooking.getOffenderBookId());
				OffenderBooking.setModifyUserId(commitBean.getCreateUserId());
				fromdate = ocdtapowRepository.getFromDate(staffLocationRole);
				final String supervisionLevel =pimsWeightService.getSupLevel(OffenderBooking.getOffenderBookId(),commitBean.getCreateUserId());
				final Integer reviewPeriod = ocdtapowRepository.getreviewPeriod(supervisionLevel);
				final Date sysdate = eliteDateService.getDBTime();

				calendar.setTime(sysdate);
				calendar.add(Calendar.YEAR, 0);
				calendar.add(Calendar.MONTH, 0);
				calendar.add(Calendar.DATE, reviewPeriod);
				final String output = simpleDateFormat.format(calendar.getTime());
				try {
					nextReviewDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(output);
				} catch (final ParseException e) {
					logger.error("casePlanInsert", e.getMessage());
				}
				casePlans.setOffenderBookId(OffenderBooking.getOffenderBookId());
				casePlans.setFromDate(fromdate);
				casePlans.setPosition(staffLocationRole.getPosition());
				casePlans.setRole(staffLocationRole.getRole());
				casePlans.setSacStaffId(new BigDecimal(staffLocationRole.getStaffId()));
				casePlans.setCalAgyLocId(staffLocationRole.getCalAgyLocId());
				casePlans.setAgyLocId(staffLocationRole.getAgyLocId());
				casePlans.setSupervisionLevel(supervisionLevel);
				casePlans.setNextReviewDate(nextReviewDate);
				casePlans.setCasePlanStatus(OffenderBooking.getBookingStatus());
				casePlans.setCreateUserId(commitBean.getCreateUserId());
				casePlans.setModifyUserId(commitBean.getCreateUserId());
				try {
					casePlans.setCasePlanId(planId);

					ocdtapowRepository.updatecasePlans(casePlans, OffenderBooking.getOffenderBookId());
				} catch (final Exception e) {
					commitBean.setSealFlag(ApplicationConstants.NINE);
					logger.error("updatecasePlans", e.getMessage());
					return commitBean;
				}
				if (planId == 0) {
					casePlans.setCreationUser(ApplicationConstants.USER);
				} else {
					String user = ocdtapowRepository.casePlanPreInsert(planId, casePlans.getOffenderBookId());
					if (user != null) {
						casePlans.setCreationUser(user);
					} else {
						casePlans.setCreationUser(commitBean.getCreateUserId());
					}
				}
				try {
					casePlans.setCasePlanId(planId + 1);
					casePlansTwfService.casePlansTwf(casePlans);
					ocdtapowRepository.casePlanInsert(casePlans);
					casePlansT2Service.casePlansT2(casePlans, commitBean.getCreateUserId());
					casePlansT1Service.casePlansT1(casePlans);
					casePlansT3Service.casePlansT3(casePlans, commitBean.getCreateUserId());

				} catch (final Exception e) {
					commitBean.setSealFlag(ApplicationConstants.ONE);
					logger.error("casePlanInsert", e);
					return commitBean;
				}

				try {
					ocdtapowRepository.insertAssessmentSummaries(casePlans);
				} catch (final Exception e) {
					commitBean.setSealFlag(ApplicationConstants.TWO);
					logger.error("insertAssessmentSummaries", e.getMessage());
					return commitBean;
				}
				try {
					ocdtapowRepository.insertplanDetails(casePlans);
				} catch (final Exception e) {
					commitBean.setSealFlag(ApplicationConstants.THREE);
					logger.error("ocdtapowRepository", e.getMessage());
					return commitBean;
				}
				try {
					ocdtapowRepository.insertCaseWorkSteps(casePlans);
				} catch (final Exception e) {
					commitBean.setSealFlag(ApplicationConstants.FOUR);
					logger.error("insertCaseWorkSteps", e.getMessage());
					return commitBean;

				}

				final OffenderWorkAssignments offWorkAssign = ocdtapowRepository
						.getPreWorkAssignemetDet(OffenderBooking.getOffenderBookId(), staffMembers.getStaffId());
                 offWorkAssign.setModifyUserId(commitBean.getCreateUserId());
				if (offWorkAssign.getOffassId() == null) {

				} else {
					offasId = ocdtapowRepository.getOffasIdSeq();
					assignmentTransfers.setAsstraId(offasId);
					assignmentTransfers.setFromDateFrom(offWorkAssign.getFromDate());
					assignmentTransfers.setPosition(offWorkAssign.getPosition());
					assignmentTransfers.setRole(offWorkAssign.getRole());
					assignmentTransfers.setSacStaffIdFrom(offWorkAssign.getSacStaffId());
					assignmentTransfers.setCalAgyLocIdFrom(offWorkAssign.getCalAgyLocId());
					assignmentTransfers.setStatusFrom(offWorkAssign.getStatus());
					assignmentTransfers.setFromDate(fromdate);
					assignmentTransfers.setPosition(staffLocationRole.getPosition());
					assignmentTransfers.setRole(staffLocationRole.getRole());
					assignmentTransfers.setSacStaffId(new BigDecimal(staffLocationRole.getSacStaffId()));
					assignmentTransfers.setCalAgyLocId(staffLocationRole.getAgyLocId());
					assignmentTransfers.setOffassId(assignmentTransfers.getOffassId());
					assignmentTransfers.setCreateUserId(commitBean.getCreateUserId());

					try {
						offWorkAssign.setFromDate(new SimpleDateFormat(ApplicationConstants.DATEFORMAT, Locale.ENGLISH)
								.parse(staffLocationRole.getFromDate()));
						ocdtapowRepository.insertAssignmentTransfers(assignmentTransfers);
					} catch (final ParseException e) {

						commitBean.setSealFlag(ApplicationConstants.FIVE);
						logger.error("insertAssignmentTransfers", e.getMessage());
						return commitBean;
					}

					offWorkAssign.setPosition(staffLocationRole.getPosition());

					offWorkAssign.setRole(staffLocationRole.getRole());
					offWorkAssign.setCalAgyLocId(staffLocationRole.getCalAgyLocId());
					offWorkAssign.setStatus(null);
					
					try {
						ocdtapowRepository.updateWorkAssigments(offWorkAssign);

					} catch (final Exception e) {
						commitBean.setSealFlag(ApplicationConstants.SIX);
						logger.error("updateWorkAssigments", e.getMessage());
						return commitBean;
					}

				}

				offenderCommunityFile = ocdtapowRepository.getOffenderFileDetails(OffenderBooking.getOffenderId());
                offenderCommunityFile.setCreateUserId(commitBean.getCreateUserId());
                offenderCommunityFile.setModifyUserId(commitBean.getCreateUserId());
				final String sysProfiles = ocdtapowRepository.getProfileValue();
				if (ApplicationConstants.YES.equals(sysProfiles)) {
					offenderCommunityFile.setOffenderId(OffenderBooking.getOffenderId().longValue());
					if (offenderCommunityFile.getOffenderFileSeq() != 0
							&& (ApplicationConstants.UNASSIGNED.contentEquals(offenderCommunityFile.getNonOfficerStatus())
									|| offenderCommunityFile
									.getHoldingStaffId() == new BigDecimal(staffMembers.getStaffId()))) {
						try {
							offenderCommunityFile.setHoldingStaffId(new BigDecimal(staffLocationRole.getStaffId()));
							offenderCommunityFile.setHoldingAgyLocId(staffMembers.getAgyLocId());
							ocdtapowRepository.updateCommunityFinanceFiles(offenderCommunityFile);
						} catch (final Exception e) {
							commitBean.setSealFlag(ApplicationConstants.SEVEN);
							logger.error("updateCommunityFinanceFiles", e.getMessage());
							return commitBean;
						}

						try {
							pimsFileTrackingService.transferFile((int)offenderCommunityFile.getOffenderFileSeq(),offenderCommunityFile.getOffenderId() ,
									null, offenderCommunityFile.getHoldingStaffId()+"", null,
									null, offasId, offenderCommunityFile.getNonOfficerStatus(), null, offenderCommunityFile.getCreateUserId());
						} catch (final Exception e) {
							commitBean.setSealFlag(ApplicationConstants.EIGHT);
							logger.error("pimsFileTracking", e.getMessage());
							return commitBean;
						}

					}

				}

				try {
					ocdtapowRepository.updateOffenderBookings(casePlans, OffenderBooking.getOffenderId());
					offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(OffenderBooking, OffenderBooking, "UPDATING");
					offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(OffenderBooking,ApplicationConstants.UPDATING);
					offenderBookingsT7Service.offenderBookingsT7Trigger(OffenderBooking);
				} catch (final Exception e) {
					commitBean.setSealFlag(ApplicationConstants.NINE);
					logger.error("updateOffenderBookings", e.getMessage());
					return commitBean;
				}

			}
			if (commitBean.getSealFlag() == null) 
				commitBean.setSealFlag(ApplicationConstants.ZERO);
			
		}

		return commitBean;
	}

}