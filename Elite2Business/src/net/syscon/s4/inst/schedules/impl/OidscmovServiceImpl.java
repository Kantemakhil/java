package net.syscon.s4.inst.schedules.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.schedules.OidscmovRepository;
import net.syscon.s4.inst.schedules.OidscmovService;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.osinames.OsinamesPkgService;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;

/**
 * Class OidscmovServiceImpl
 */
@Service
public class OidscmovServiceImpl extends BaseBusiness implements OidscmovService {

	@Autowired
	private OidscmovRepository oidscmovRepo;
	@Autowired
	private OsinamesPkgService osinamesService;
	@Autowired
	private OffCourtEventVineIntfTrgService OffCourtEventVineIntfTrgService;
	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OciscataRepository ociscataRepository;

	/**
	 * Creates new OidscmovServiceImpl class Object
	 */
	public OidscmovServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Object crtEvePreInsert() {
		return oidscmovRepo.crtEvePreInsert();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CourtEvents> crtEveExecuteQuery(final CourtEvents searchRecord) {

		final VNameSearch objSearchDao = new VNameSearch();
		List<VNameSearch> listNames = new ArrayList<VNameSearch>();
		final List<CourtEvents> lstCourtEvents = (List<CourtEvents>) oidscmovRepo.crtEveExecuteQuery(searchRecord);
		if (lstCourtEvents != null && lstCourtEvents.size() > 0) {
			for (CourtEvents objCourtEvents : lstCourtEvents) {
				if (objCourtEvents != null) {
					if (objCourtEvents.getOffenderBookId() > 0) {
						objSearchDao.setOffenderBookId(objCourtEvents.getOffenderBookId());
						final VNameSearch bean = new VNameSearch();
						Map<String, Object> returnObject = osinamesService
								.getOffDetailsByBookId(objSearchDao.getOffenderBookId().longValue());
						bean.setOffenderIdDisplay(String.valueOf(returnObject.get("P_OFFENDER_ID_DISPLAY")));
						bean.setFirstName(String.valueOf(returnObject.get("P_FIRST_NAME")));
						bean.setLastName(String.valueOf(returnObject.get("P_LAST_NAME")));
						bean.setAgyLocId(String.valueOf(returnObject.get("P_AGY_LOC_ID")));
						listNames.add(bean);
						for (final VNameSearch vNameSearch : listNames) {

							objCourtEvents.setNbtOffenderIdDisplay(vNameSearch.getOffenderIdDisplay());
							if (vNameSearch.getLastName() != null) {
								objCourtEvents.setNbtLastName(vNameSearch.getLastName());
							}
							if (vNameSearch.getFirstName() != null && !(vNameSearch.getFirstName().equals("null"))) {
								objCourtEvents.setNbtFirstName(vNameSearch.getFirstName());
							}
							if (vNameSearch.getAgyLocId() != null
									&& !vNameSearch.getAgyLocId().equalsIgnoreCase("null")) {
								objCourtEvents.setNbtInst(vNameSearch.getAgyLocId());
							}
						}
					}
				}
			}

		}
		return lstCourtEvents.stream().filter(obj -> obj.getNbtInst() != null && !obj.getNbtInst().equals("null"))
				.collect(Collectors.toList());
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRT_EVE
	 *
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer crtEveCommit(final CourtEventsCommitBean commitBean) {

		int liReturn = 0;
		final CourtEvents courtEvents = new CourtEvents();
		List<CourtEvents> listObj = new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			if (commitBean.getInsertList().size() > 1) {
				for (final CourtEvents obj : commitBean.getInsertList()) {
					courtEvents.setOffenderBookId(obj.getOffenderBookId());
					courtEvents.setEventDate(obj.getEventDate());
					obj.setCreateUserId(commitBean.getCreateUserId());
					final Integer returnValue = oidscmovRepo.checkScheduleConflict(courtEvents);
					if (returnValue > 0 && !obj.getConflictFlag()) {
						throw new ArithmeticException("Conflict Occured");
					} else {
						listObj = new ArrayList<>();
						listObj.add(obj);
						liReturn = oidscmovRepo.crtEveInsertCourtEvents(listObj);
						for (CourtEvents bean : listObj) {
							  OffCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "INSERTING");
						}
					}
				}

			} else {
				for (CourtEvents bean : commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oidscmovRepo.crtEveInsertCourtEvents(commitBean.getInsertList());
				for (CourtEvents bean : commitBean.getInsertList()) {
					 OffCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "INSERTING");
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CourtEvents bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidscmovRepo.crtEveUpdateCourtEvents(commitBean.getUpdateList());
			for (CourtEvents bean : commitBean.getUpdateList()) {
				 OffCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "UPDATING");
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			List<CourtEvents> newList = new ArrayList<CourtEvents>();
			try {
				for (CourtEvents bean1 : commitBean.getDeleteList()) {
					bean1.setModifyUserId(commitBean.getCreateUserId());
					newList.add(bean1);
				}
				liReturn = oidscmovRepo.crtEveDeleteCourtEvents(commitBean.getDeleteList());
				for (CourtEvents bean : newList) {
					 OffCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(bean, "DELETING");
				}
			} catch (DataIntegrityViolationException e) {
				throw new NullPointerException("Null value");
			} catch (Exception e) {
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgCtrlInstRecordGroup(final String caseloadId) {
		return oidscmovRepo.rgCtrlInstRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<MovementReasons> rgCtrlReasonRecordGroup() {
		return oidscmovRepo.rgCtrlReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgCtrlCourtRecordGroup() {
		return oidscmovRepo.rgCtrlCourtRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<MovementReasons> rgCourtReaRecordGroup() {
		return oidscmovRepo.rgCourtReaRecordGroup();

	}

	/**
	 * 
	 * @return Date
	 */
	public Date getCurrentDate() {
		return oidscmovRepo.getCurrentDate();
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param searchRecord
	 * @return Integer
	 */
	public Integer checkScheduleConflict(final CourtEvents objCourts) {
		return oidscmovRepo.checkScheduleConflict(objCourts);
	}

	/**
	 * Retrieves offender details for the given offender display Id , global
	 * caseload id & agyloc id
	 * 
	 * @param nbtOffDisplayId
	 * @param agyLocId
	 * @param caseloadId
	 * @return List<CourtEvents>
	 * 
	 */
	public List<CourtEvents> getOffenderDetails(final String nbtOffDisplayId, final String agyLocId,
			final String caseloadId) {
		List<CourtEvents> lstCourt = new ArrayList<>();
		Map<String, Object> map = osinamesService.getOffenderDetails(nbtOffDisplayId, agyLocId, caseloadId);
		final CourtEvents bean = new CourtEvents();
		bean.setNbtOffenderIdDisplay(nbtOffDisplayId);
		bean.setNbtFirstName(String.valueOf(map.get("P_FIRST_NAME")));
		bean.setNbtLastName(String.valueOf(map.get("P_LAST_NAME")));
		bean.setNbtInst(String.valueOf(map.get("P_AGY_LOC_ID")));
		bean.setOffenderBookId(Integer.valueOf(String.valueOf(map.get("P_OFFENDER_BOOK_ID"))));
		lstCourt.add(bean);
		return lstCourt;
	}

	@Override
	public Boolean getChkNaConflictFlag(final Integer offenderBookId, final String agyLocId, final Date eventDate) {
		Integer lvNaCount = 0;
		Boolean lvConflict = false;
		int conflictCount = 0;
		lvNaCount = oidscmovRepo.getLvNaCount(offenderBookId);
		if (lvNaCount < 1) {
			lvConflict = false;
		} else {
			lvNaCount = 0;
			List<OffenderBookings> list = oidscmovRepo.getNaCur(offenderBookId);
			for (final OffenderBookings obj : list) {
				lvNaCount = oidscmovRepo.getLvNaCountCur(obj.getOffenderBookId(), agyLocId, eventDate);

				if (lvNaCount > 0) {
					conflictCount++;
				} 
			}
		}

		if (conflictCount > 0) {
			lvConflict = true;
		}
		return lvConflict;
	}

	@Override
	public List<CourseActivities> getNonAssociationWarnings(List<CourseActivities> courseActivitiesList) {
		List<Integer> offenderBookIdList = new ArrayList<Integer>();
		for (CourseActivities offebderBookId : courseActivitiesList) {
			offenderBookIdList.add(offebderBookId.getOffenderBookId().intValue());
		}
		for (int i = 0; i < courseActivitiesList.size(); i++) {
			List<Offenders> offenderInd = new ArrayList<Offenders>();
			List<Offenders> offenderGang = new ArrayList<Offenders>();
			List<OffenderNaDetails> nonAssList = nonAssociationService
					.getNonAssociationOffenderList(courseActivitiesList.get(i).getOffenderBookId().intValue());
			List<OffenderStgAffiliations> nonAssListGang = nonAssociationService
					.getOffendersOfNonAssociationGroup(courseActivitiesList.get(i).getOffenderBookId());
			if (nonAssList != null && nonAssList.size() > 0) {
				for (OffenderNaDetails offenderNaDetails : nonAssList) {
					if (offenderBookIdList.contains(offenderNaDetails.getNsOffenderBookId().intValue())) {

						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderNaDetails.getNsOffenderBookId());
						offender.get(0).setOffenderBookId(offenderNaDetails.getNsOffenderBookId().longValue());
						offenderInd.addAll(offender);
					}
				}
			}

			if (nonAssListGang != null && nonAssListGang.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : nonAssListGang) {
					if (offenderBookIdList.contains(offenderStgAffiliations.getOffenderBookId().intValue())) {
						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderStgAffiliations.getOffenderBookId());
						offender.get(0).setOffenderBookId(offenderStgAffiliations.getOffenderBookId().longValue());
						offenderGang.addAll(offender);
					}

				}
			}
			if (offenderGang != null && offenderGang.size() > 0)
				courseActivitiesList.get(i).setOffenderNonAssociationsByGang(offenderGang);
			else
				courseActivitiesList.get(i).setOffenderNonAssociationsByGang(null);
			if (offenderInd != null && offenderInd.size() > 0)
				courseActivitiesList.get(i).setOffenderNonAssociationsByInd(offenderInd);
			else
				courseActivitiesList.get(i).setOffenderNonAssociationsByInd(null);
		}

		return courseActivitiesList;
	}

	@Override
	public CourtEventsCommitBean getNonAssociationWarningsForINPOrVIDOrOME(CourtEventsCommitBean commitBean) {

		if (commitBean != null && commitBean.getInsertAndUpdateList().size() > 0) {
			for (int i = 0; i < commitBean.getInsertAndUpdateList().size(); i++) {

				if (commitBean.getInsertAndUpdateList().get(i).getAppearanceType().equals("INP")) {
					CourtEvents courtEventsInp = nonAssDetForINP(commitBean.getInsertAndUpdateList().get(i),
							commitBean.getInsertAndUpdateList());
					if (courtEventsInp != null) {

						commitBean.getInsertAndUpdateList().get(i)
								.setExternalNonAssDetailsInd(courtEventsInp.getExternalNonAssDetailsInd());

						commitBean.getInsertAndUpdateList().get(i)
								.setOffenderNonAssociationsByInd(courtEventsInp.getOffenderNonAssociationsByInd());

						commitBean.getInsertAndUpdateList().get(i)
								.setOffenderNonAssociationsByGang(courtEventsInp.getOffenderNonAssociationsByGang());

					}
				}


				if (commitBean.getInsertAndUpdateList().get(i).getAppearanceType().equals("OME")
						|| commitBean.getInsertAndUpdateList().get(i).getAppearanceType().equals("VID")) {

					CourtEvents courtEventsOMEOrVID = nonAssDetForOMEOrVID(commitBean.getInsertAndUpdateList().get(i),
							commitBean.getInsertAndUpdateList());

					if (courtEventsOMEOrVID != null) {

						commitBean.getInsertAndUpdateList().get(i)
								.setExternalNonAssDetailsInd(courtEventsOMEOrVID.getExternalNonAssDetailsInd());

						commitBean.getInsertAndUpdateList().get(i)
								.setOffenderNonAssociationsByInd(courtEventsOMEOrVID.getOffenderNonAssociationsByInd());

						commitBean.getInsertAndUpdateList().get(i).setOffenderNonAssociationsByGang(
								courtEventsOMEOrVID.getOffenderNonAssociationsByGang());

					}
				}

			}
		}
		return commitBean;
	}


	public CourtEvents nonAssDetForINP(CourtEvents courtEvents, List<CourtEvents> inputList) {

		List<Integer> internalList = new ArrayList<Integer>();

		inputList.stream()
				.filter(e -> e.getAppearanceType().equals("INP")
						&& e.getOffenderBookId() != courtEvents.getOffenderBookId()
						&& (convertUtilDateToString(e.getEventDate())
								.compareTo(convertUtilDateToString(courtEvents.getEventDate())) == 0)
						&& e.getAgyLocId().equals(courtEvents.getAgyLocId()))
				.collect(Collectors.toList()).forEach(ele -> internalList.add(ele.getOffenderBookId()));

		CourtEvents courtEventsForIndAndGang = new CourtEvents();

		try {
			if (courtEvents != null) {
				String extNonAssInd = "";
				String extNonAssGang = "";
				List<Offenders> listIndExt = oidscmovRepo.getExternalIndNonAssocationForINP(
						new Long(courtEvents.getOffenderBookId()), courtEvents.getAgyLocId(),
						courtEvents.getEventDate());

				List<Offenders> listGangExt = oidscmovRepo.getExternalGangNonAssocationForINP(
						new Long(courtEvents.getOffenderBookId()), courtEvents.getAgyLocId(),
						courtEvents.getEventDate());

				if (listIndExt != null && listIndExt.size() > 0) {
					extNonAssInd = offenderDetails(listIndExt);
				}
				if (listGangExt != null && listGangExt.size() > 0) {
					extNonAssGang = offenderDetails(listGangExt);
				}
				courtEventsForIndAndGang.setExternalNonAssDetailsInd(returnString(extNonAssInd, extNonAssGang));

				if (internalList != null && internalList.size() > 0) {
					List<Integer> listIndInt = oidscmovRepo.getInternalIndNonAssocationForINPOrVIDOrOME(
							new Long(courtEvents.getOffenderBookId()), internalList);

					courtEventsForIndAndGang.setOffenderNonAssociationsByInd(listIndInt.size() > 0 ? listIndInt : null);

					List<Integer> listGangInt = oidscmovRepo.getInternalGangNonAssocationForINPOrVIDOrOME(
							new Long(courtEvents.getOffenderBookId()), internalList);

					courtEventsForIndAndGang
							.setOffenderNonAssociationsByGang(listGangInt.size() > 0 ? listGangInt : null);

				}

			}

		} catch (Exception e) {
			courtEventsForIndAndGang = null;
		}

		return courtEventsForIndAndGang;
	}


	public CourtEvents nonAssDetForOMEOrVID(CourtEvents courtEvents, List<CourtEvents> inputList) {

		List<Integer> internalList = new ArrayList<Integer>();
		inputList.stream()
				.filter(e -> (e.getAppearanceType().equals("VID") || e.getAppearanceType().equals("OME"))
						&& e.getOffenderBookId() != courtEvents.getOffenderBookId()
						&& (convertUtilDateToString(e.getEventDate())
								.compareTo(convertUtilDateToString(courtEvents.getEventDate())) == 0)
						&& e.getAppearanceLocation().equals(courtEvents.getAppearanceLocation()))
				.collect(Collectors.toList()).forEach(ele -> internalList.add(ele.getOffenderBookId()));

		CourtEvents courtEventsForIndAndGang = new CourtEvents();

		try {
			if (courtEvents != null) {

				List<Offenders> listIndExt = oidscmovRepo.getExternalIndNonAssocationForVIDOrOME(
						new Long(courtEvents.getOffenderBookId()), courtEvents.getAppearanceLocation(),
						courtEvents.getEventDate(), courtEvents.getCaseLoad());

				List<Offenders> listGangExt = oidscmovRepo.getExternalGangNonAssocationForVIDOrOME(
						new Long(courtEvents.getOffenderBookId()), courtEvents.getAppearanceLocation(),
						courtEvents.getEventDate(),courtEvents.getCaseLoad());
				String extNonAssInd = "";
				String extNonAssGang = "";

				if (listIndExt != null && listIndExt.size() > 0) {
					extNonAssInd = offenderDetails(listIndExt);
				}
				if (listGangExt != null && listGangExt.size() > 0) {
					extNonAssGang = offenderDetails(listGangExt);
				}
				courtEventsForIndAndGang.setExternalNonAssDetailsInd(returnString(extNonAssInd, extNonAssGang));

				if (internalList != null && internalList.size() > 0) {
					List<Integer> listIndInt = oidscmovRepo
							.getInternalIndNonAssocationForINPOrVIDOrOMELocation(courtEvents, internalList);

					courtEventsForIndAndGang.setOffenderNonAssociationsByInd(listIndInt);

					List<Integer> listGangInt = oidscmovRepo.getInternalGangNonAssocationForINPOrVIDOrOME(
							new Long(courtEvents.getOffenderBookId()), internalList);

					courtEventsForIndAndGang.setOffenderNonAssociationsByGang(listGangInt);
				}

			}

		} catch (Exception e) {
			courtEventsForIndAndGang = null;
		}

		return courtEventsForIndAndGang;
	}

	public String convertUtilDateToString(Date date) {
		java.util.Date givenDate = date;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		return (date != null) ? sdf1.format(givenDate) : " ";
	}

	public String offenderDetails(List<Offenders> offendersList) {
		String message = "";
		for (Offenders offenders : offendersList) {
			java.util.Date stDate = offenders.getCreateDateTime();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String startTime = stDate != null ? sdf.format(stDate) : "";
			String loc = offenders.getIdentifier();
			String name = offenders.getLastName() + " " + offenders.getFirstName();
			String offenderDispalyId = offenders.getOffenderIdDisplay();

			message = message + " " + name + " " + offenderDispalyId + " " + loc + " " + startTime + "\n";
		}

		return message;
	}

	public String returnString(String str1, String str2) {

		return (str1.length() > 0 && str2.length() > 0)
				? "oidscmov.individualNonAss \n" + str1 + "\n oidscmov.gangNonAss \n" + str2
				: (str1.length() > 0) ? "oidscmov.individualNonAss \n" + str1
						: (str2.length() > 0) ? " oidscmov.gangNonAss \n" + str2 : ApplicationConstants.EMPTYDATA;

	}
}
