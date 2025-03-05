package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VStaffLocation;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.iwp.OcdatpowRepository;
import net.syscon.s4.iwp.OcdatpowService;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgService;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import net.syscon.s4.triggers.CasePlansT1Service;
import net.syscon.s4.triggers.CasePlansT2Service;
import net.syscon.s4.triggers.CasePlansT3Service;
import net.syscon.s4.triggers.CasePlansTwfService;
import net.syscon.s4.triggers.OffcomfiService;
import net.syscon.s4.triggers.OmtoffirService;

@Service
public class OcdatpowServiceImpl extends BaseBusiness implements OcdatpowService {

	private static Logger logger = LogManager.getLogger(OcdatpowServiceImpl.class.getName());

	private static String YFLAG = "Y";
	private static String NFLAG = "N";
	private static String UNASSIGNED = "UNASSIGNED";

	@Autowired
	private OcdatpowRepository ocdatpowRepo;

	@Autowired
	private ComunityPkgService comunityPkgService;

	@Autowired
	private PimsFileTrackingService pimsFileTrackingService;

	@Autowired
	private CasePlansTwfService casePlansTwfService;

	@Autowired
	private CasePlansT1Service casePlansT1Service;

	@Autowired
	private CasePlansT2Service casePlansT2Service;

	@Autowired
	private CasePlansT3Service casePlansT3Service;

	@Autowired
	private OffcomfiService offcomfiService;

	@Autowired
	private OmtoffirService omtoffirService;

	public List<OffenderBookings> offBkg1ExecuteQuery(final OffenderBookings searchRecord) {
		final List<OffenderBookings> ofbookings = ocdatpowRepo.offBkg1ExecuteQuery(searchRecord);
		ofbookings.forEach(bo -> {
			final List<OffenderBookings> list = postQueryForOffbkg1(bo);
			bo.setDspFirstName(list.get(0).getDspFirstName());
			bo.setDspLastName(list.get(0).getDspLastName());
			bo.setOffenderIdDisplay(list.get(0).getAgyLocIdList());
			bo.setModifyDatetime(ocdatpowRepo.getStartDate(bo));
		});
		return ofbookings;
	}

	public List<OffenderBookings> postQueryForOffbkg1(final OffenderBookings offBookings) {
		return ocdatpowRepo.postQueryForOffbkg1(offBookings);

	}

	public List<TeamMembers> vOffDetExecuteQuery(final TeamMembers searchBean) {

		final List<TeamMembers> list = ocdatpowRepo.vOffDetExecuteQuery(searchBean);
		list.forEach(bo -> {
			VStaffLocation bean = new VStaffLocation();
			bean.setCalAgyLocId(searchBean.getAgyLocId());
			bean.setStaffId(bo.getStaffId().intValue());
			bean.setPosition(bo.getPosition());
			bean.setRole(bo.getRole());
			bean.setFromDate(bo.getLocRoleFromDate());
			if (Optional.ofNullable(bo).isPresent()) {
				// function call
				final Long offenderCount = comunityPkgService.getOfficerPo(bean);
				bo.setOffenders(offenderCount);
				final boolean teamFlag = ocdatpowRepo.omMandatory();
				bo.setTeamFlag(teamFlag);
				if (searchBean.getOffenderBookId() != null) {
					final Boolean subType = ocdatpowRepo.cgnbtSkillSubTy(bo.getStaffId(),
							searchBean.getOffenderBookId());
					bo.setSubType(subType);
				}

			}
		});
		return list;
	}

	/**
	 * getting rgPositionRecordGroup LOV values
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return ocdatpowRepo.rgPositionRecordGroup();

	}

	public List<ReferenceCodes> rgRoleRecordGroup() {
		return ocdatpowRepo.rgRoleRecordGroup();

	}

	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		return ocdatpowRepo.rgSexCodeRecordGroup();

	}

	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		return ocdatpowRepo.rgScheduleTypeRecordGroup();

	}

	public List<Teams> rgTeamRecordGroup(final String sealFlag) {
		String position = null;
		String role = null;
		Long staffid = null;
		final String[] inputArray = sealFlag.split("-");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				position = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				role = inputArray[1];
			if (inputArray.length > 2 && inputArray[2] != null && !inputArray[2].equals(""))
				staffid = Long.parseLong(inputArray[2]);
		}
		return ocdatpowRepo.rgTeamRecordGroup(position, role, staffid);
	}

	public List<TeamMembers> cgfkStaffLrDspDescriptionRecordGroup(final String caseLoadType) {
		List<TeamMembers> teamList = ocdatpowRepo.cgfkStaffLrDspDescriptionRecordGroup(caseLoadType);
		for (TeamMembers teamMembers : teamList) {
			if (YFLAG.equals(teamMembers.getActiveFlag())) {
				teamMembers.setCanDisplay(true);
			} else {
				teamMembers.setCanDisplay(false);
			}
		}
		return teamList;
	}

	public List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup() {
		return ocdatpowRepo.cgfkStaffLrDspLastNameRecordGroup();
	}

	public String assignToAllOficersToOneOfficer() {
		return null;
	}

	@Transactional
	public Integer offBkg1Commit(final OffenderBookingsCommitBean commitBean) {
		final BigDecimal v_staff_id = null;
		final List<CasePlans> casePlanesList = new ArrayList<>();
		final List<AssignmentTransfers> transferList = new ArrayList<>();
		List<OffenderWorkAssignments> assignment = new ArrayList<OffenderWorkAssignments>();

		List<OffenderCommunityFile> files = new ArrayList<OffenderCommunityFile>();
		List<OffenderBookings> bkgFlist = new ArrayList<OffenderBookings>();
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()
				&& commitBean.getOffdetUpdateList() != null && !commitBean.getOffdetUpdateList().isEmpty()) {

			TeamMembers members = new TeamMembers();
			members = commitBean.getOffdetUpdateList().get(0);

			for (final OffenderBookings bean : commitBean.getUpdateList()) {

				if (bean.getOffenderBookId() != null && bean.getActiveFlag().equals(YFLAG)) {
					final Long offender_Book_id = bean.getOffenderBookId();

					Long offenderId = bean.getOffenderId().longValue();
					Long root_offender_id = bean.getRootOffenderId().longValue();
					final Date v_next_review_date = new Date();
					final Long next_id_cur = (long) ocdatpowRepo.nextIdCur(bean);
					final Date vs_sac_cur = ocdatpowRepo.vsSacCur(members);
					final Integer count = ocdatpowRepo.casePlaneCur(bean);
					final CasePlans planes2 = new CasePlans();
					planes2.setOffenderBookId(offender_Book_id);
					planes2.setCasePlanId(next_id_cur);
					planes2.setFromDate(vs_sac_cur);
					// data copied to VStaffLocation
					VStaffLocation vStaff = new VStaffLocation();
					vStaff.setCalAgyLocId(bean.getAgyLocId());
					vStaff.setPosition(bean.getPosition());
					vStaff.setRole(members.getRole());
					vStaff.setFromDate(vs_sac_cur);
					// Procedure call
					final String v_supervision_level = comunityPkgService.getOfficerPo(vStaff).toString();
					planes2.setPosition(members.getPosition());
					planes2.setRole(members.getRole());
					planes2.setSacStaffId1(members.getStaffId().longValue());
					planes2.setCalAgyLocId(members.getIntakeAgyLocId());
					planes2.setAgyLocId(members.getIntakeAgyLocId());
					planes2.setSupervisionLevel(v_supervision_level);
					planes2.setCaseloadType(members.getAgencyLocationType());
					planes2.setNextReviewDate(v_next_review_date);
					planes2.setInstPosition(planes2.getInstPosition());
					planes2.setInstRole(planes2.getInstRole());
					planes2.setInstSacStaffId(v_staff_id);
					planes2.setCaseloadType(bean.getBookingType());
					if (count == 0) {
						planes2.setVerifiedFlag(NFLAG);
					} else {
						planes2.setVerifiedFlag(YFLAG);
					}
					planes2.setCreateUserId(commitBean.getCreateUserId());
					planes2.setModifyUserId(commitBean.getCreateUserId());
					casePlanesList.add(planes2);
					final Integer asstra_id = ocdatpowRepo.getV_Id();
					assignment = ocdatpowRepo.vsGetPrevAssignCur(bean);
					for (final OffenderWorkAssignments assig : assignment) {
						final AssignmentTransfers assignTr = new AssignmentTransfers();
						final Date v_from_date = ocdatpowRepo.vsSaccalCur(members);
						ocdatpowRepo.asStraSeq();
						if (v_from_date != null) {
							assignTr.setFromDateFrom(assig.getFromDate());
							assignTr.setPositionFrom(assig.getPosition());
							assignTr.setRoleFrom(assig.getRole());
							assignTr.setCalAgyLocIdFrom(assig.getCalAgyLocId());
							assignTr.setStatusFrom(assig.getStatus());
							assignTr.setOffassId(BigDecimal.valueOf(assig.getOffassId()));
							if (assignTr.getOffassId() != null) {
								assignTr.setAsstraId((long) asstra_id);
							}
						}
						assignTr.setPosition(members.getPosition());
						assignTr.setRole(members.getRole());
						assignTr.setCalAgyLocId(members.getIntakeAgyLocId());
						assignTr.setFromDate(vs_sac_cur);
						assignTr.setCreateUserId(commitBean.getCreateUserId());
						assignTr.setModifyUserId(commitBean.getCreateUserId());
						transferList.add(assignTr);
					}
					Boolean lvProfileValue = ocdatpowRepo.lvProfileValue();
					if (lvProfileValue) {
						bkgFlist = ocdatpowRepo.vsGetOffIdCur(offender_Book_id, root_offender_id);
						for (OffenderBookings bookings : bkgFlist) {
							if (bookings.getOffenderId() == null) {
								bookings.setOffenderId(null);
							}
						}
						files = ocdatpowRepo.fileInfoCur(offenderId);
						for (OffenderCommunityFile commfiles : files) {
							if (commfiles.getOffenderFileSeq() > 0 && commfiles.getHoldingStaffId() == null
									&& commfiles.getNonOfficerStatus() == null) {
								commfiles.setOffenderFileSeq(0);
								commfiles.setHoldingStaffId(null);
								commfiles.setNonOfficerStatus(null);
								files.add(commfiles);
							}

						}

					}

				}
				bean.setAssignedStaffId(members.getStaffId()); //To_Staff_Id to use in process
			}
		}

		// Case Update
		final Integer updateCount = ocdatpowRepo.casePlanesUpdate(casePlanesList);
		// cases Insert
		final Integer count1 = ocdatpowRepo.casePlanesinsert(casePlanesList);
		for (int i = 0; i < casePlanesList.size(); i++) {
			// Trigger call
			try {
				casePlansTwfService.casePlansTwf(casePlanesList.get(i));
			} catch (Exception e) {
				logger.error("casePlansTwf:", e);
			}
			// Trigger call
			try {
				casePlansT1Service.casePlansT1(casePlanesList.get(i));
			} catch (Exception e) {
				logger.error("casePlansT1:", e);
			}
			// Trigger call
			try {
				casePlansT2Service.casePlansT2(casePlanesList.get(i), casePlanesList.get(i).getCreateUserId());
			} catch (Exception e) {
				logger.error("casePlansT2:", e);
			}
			// Trigger call
			try {
				casePlansT3Service.casePlansT3(casePlanesList.get(i), casePlanesList.get(i).getCreateUserId());
			} catch (Exception e) {
				logger.error("casePlansT3:", e);
			}
		}
		if (!assignment.isEmpty() && updateCount == 1 && count1 == 1) {
			// Insert Assign Transfer
			final Integer count2 = ocdatpowRepo.assignmentTransfersInsert(transferList);
			// Update Assign Transfer
			if (count2 == 1) {
				final Integer count3 = ocdatpowRepo.assignmentTransfersUpdate(transferList);
				if (count3 == 1) {
					for (OffenderCommunityFile commfiles : files) {
						if (commfiles.getOffenderFileSeq() != 0 && UNASSIGNED.equals(commfiles.getNonOfficerStatus())
								|| commfiles.getHoldingStaffId() != null) {
							commfiles.setModifyUserId(commitBean.getCreateUserId());
							commfiles.setCreateUserId(commitBean.getCreateUserId());
							// update community files
							ocdatpowRepo.updateOffenderCommunityFiles(commfiles);
							// copying the data
							OffenderCommunityFiles offCommFiles = new OffenderCommunityFiles();
							BeanUtils.copyProperties(commfiles, offCommFiles);
							// Trigger call
							offcomfiService.offcomfiTrigger(offCommFiles);
							List<OffenderCommunityFiles> refOffComm = new ArrayList<>();
							refOffComm.add(offCommFiles);
							// Trigger call
							omtoffirService.omtoffirTgr(refOffComm);
							// insert community files
							Long pOffenderFileSeq = commfiles.getOffenderFileSeq();
							// Procedure call
							pimsFileTrackingService.transferFile(pOffenderFileSeq.intValue(), commfiles.getOffenderId(),
									null, commfiles.getHoldingAgyLocId(), commfiles.getHoldingAgyLocId(), null,
									commfiles.getHoldingStaffId().longValue(), commfiles.getNonOfficerStatus(), null,
									commitBean.getCreateUserId());
						}
					}
				}
			}
		}
		return count1;
	}

	@Override
	public Boolean omMandatoryGrid() {
		return ocdatpowRepo.omMandatoryGrid();
	}

}
