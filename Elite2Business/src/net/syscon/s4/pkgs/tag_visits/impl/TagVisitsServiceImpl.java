package net.syscon.s4.pkgs.tag_visits.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VNameSearch2;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.OidvisitRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesRepository;
import net.syscon.s4.pkgs.tag_visits.TagVisitsRepository;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;

/*
 *  Below comments are copied from package tag_visits
   ||    Purpose: This package provides procedures for Visits.
   ||
   ||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
   ||    --------------------------------------------------------------------------------
   ||    Person               DATE         Version    Comments
   ||    ---------            ---------    ---------  -----------------------------------
   ||    Edward               18-DEC-2014   2.31.1.16 HPQC#24396: get_offender_restrictions, get_visitor_restrictions, get_contact_offender_details, populate_visitor_details, 
   ||                                                 getpersonnames: Added p_visit_date parameter for future restrictions.  
   ||    Rajshree             29-Sep-2014   2.31.1.15 :-24351: Addded condition in check_timeslot
   ||    Rajshree             17-Sep-2014   2.31.1.14 :-24382:- Added condition in  is_authorised_person to check for approved flag.
   ||    Sanjeeva             23-Feb-2012   2.31.1.13 HPQC#153, 154, 12271 modified VISIT_CALC. and CHK_VISIT_CONFLICTS.
   ||    Ritu                 21-Mar-2011   2.31.1.12 HPQC#6126:Added Active_Flag field in PROCEDURE insert_off_con_per.
   ||    Ruxandra             04-Mar-2011   2.31.1.11 HPQC#1983
   ||    Anand Kumar          13-May-2010   2.31.1.10 HPQC# 2772/2773 Modifed Order By clause in procedue get_ocuavloc_unavailable and get_ocuavloc_available.
   ||    Vikas Grover         21-Jan-2010   2.31.1.9  Defect#442 Internal Location description is retrieved through new function get_inter_loc_desc
   ||    Sarah                02-Dec-2009   2.31.1.8  D#1773: follow the same logic as unavailable location if max values are null compare them with 999 instead of 0
   ||                                                 Added visit_date parameter to the recheck_time_slot procedure
   ||    Nancy                16-Jan-2009   2.31.1.7  #8088: The start time of the second can be the same as the end of the first to allow 'double visits to be booked'
   ||                                                 modified in overlap_visit and duplicate_visit
   ||    PETER^_^             13-FEB-2008   2.31.1.6  #4377: change version #.
   ||    PETER^_^             13-FEB-2008   2.31.1.5  #4377: Fix compilation error.
   ||    Rose                 12-FEB-2008   2.31.1.4  #4377: Fix bugs in procedure recheck_time_slot.
   ||    Rose                 05-FEB-2008   2.31.1.3  #4521: 'Max Visitors' and 'Max Groups' are both defined - either of them should be used
   ||    Rose                 24-JAN-2008   2.31.1.2  #4521: Fix in the procedure get_ocuavloc_available to display available locations
   ||                                                    according to Nathan as following:
   ||                                                    - Both 'Max Groups' and 'Max Visitors' are null, the physical capacity of the Location should be used to determine if space is available (counting Offender + Visitors)
   ||                                                    - 'Max Groups' is null but 'Max Visitors' is defined - then Max Visitors should be used
   ||                                                    - 'Max Visitors' is null but 'Max Groups' is defined - then number of groups should be used
   ||    Jason                13-sep-2007   2.31.1.1  fix tracker#4377, remove 1/1440
   ||    Vikas Grover       11-Apr-2007   2.31.1.0  TAG10g R2 : Commented function generate_vo_number as this is not required in TAG
   ||                                       -- All associations to offender_visit_orders and offender_vo_visitors is commented
   ||                               -- Sameway several function and procedures are commented because those aren't required for TAG
   ||                               -- A procedure visit_calc is created to calculate remaining visits for offender
   ||    A.Adekoya            28-Nov-2006       2.31  #5602: Comments updated
   ||    A.Adekoya            27-Nov-2006       2.30  #5602: Amend get_descriptions. Use correct cursor
   ||    A.Adekoya            17-Nov-2006       2.29  #5602: Version information and vcp_version updated
   ||    A.Adekoya            16-Nov-2006       2.28  #5602: Amend Cursor get_descriptions.c_address to include primary_flag
   ||    Johnson              30-Oct-2006       2.27  Minor modification done on lock_visits; added an extra parameter p_offender_visit_visitor_id
   ||    Erin                 23-Oct-2006       2.26  #5294: Modified is_authorised_offender, added p_birth_date to populate_offender_details
   ||    GJC                  14-Oct-2006       2.25  SHOW_VERSION changed from procedure to function
   ||    Johnson              13-Sep-2006       2.24  Modification done valid_balances to return number value instead of boolean.
   ||    Johnson              04-Aug-2006       2.23  Added function sort_on_time_slot.
   ||    Johnson              28-Jul-2006       2.22  Minor Modification to get_offender_details.
   ||    GJC                  25-Jul-2006       2.21  Added functionalty to overlap_visit
   ||    Johnson              20-Jul-2006       2.20  Added new function get_offender_details and
   ||                                                 made minor modification to populate_visitors.
   ||    GJC                  14-Jul-2006       2.19  Amended for Visit Changed Request
   ||    GJC                  14-Jul-2006       2.18  Amended for Visit Changed Request
   ||    Erin                 12-Jul-2006       2.17  Added PROCEDURE insert_off_con_per (for OCUAVISN)
   ||    GJC                  30-Jun-2006       2.16  Amended for Visit Changed Request
   ||    Erin                 01-Jun-2006       2.15  #2087: Remove incident information from PROCEDURE get_visit_details
   ||    Johnson              19-May-2006       2.14  Tr#1863 -- Added function insert_vo_adjustment to create relevant
   ||                                                 records in offender_visit_balance_adjs upon creation /
   ||                                                 Cancellation of Visit order type in ('VO','PVO')
   ||    Johnson              27-Apr-2006       2.13  Tr#1708--Modified function getpersonnames to retrive the restrictions.
   ||    Johnson              27-Apr-2006       2.12  Modified get_visitor_restrictions added offender_book_id as an additional parameter
   ||    Erin                 26-Apr-2006       2.9   Add FUNCTION get_visitor_exists and modify get_visitor_restrictions
   ||                                                 to display right global restrictions
   ||    GJC                  18-Apr-2006       2.8   Add ref cursor for OCUAVLOC
   ||    Johnson              12-APR-2006       2.7   Added the following fucntions get_offender_restrictions
   ||                                                 get_visitior_restrictions to populate the warning screen.
   ||    Johnson              08-MAR-2006       2.6   Added an extra out parameter in get_time_slot_details
   ||    Erin                 06-MAR-2006       2.5   Add function Check_Timeslot
   ||    GJC                  03-MAR-2006       2.4   Change to recheck_time_slot
   ||    Erin                 01-Mar-2006       2.3   Removed procedure pop_living_unit_desc
   ||    Johnson              27-Feb-2006       2.1   Added the following functions and procedures to the packages
   ||                                                 get_contact_offender_details,get_contact_offender_book_id
   ||                                                 get_root_offender_id,get_offender_person_rest_id,
   ||                                                 get_offender_cont_pe`rson_id,duplicate_contact_visitor,
   ||                                                 duplicate_contact_offender, is_person_exists
   ||    GJC                  09-FEB-2006             Added the following functions and procedures to the packages
   ||                                                 get_next_off_visit_id, get_next_off_visit_visitor_id,
   ||                                                 get_next_off_visit_bal_adj_id  IF ONLY WE COULD USE NDS!
   ||                                                 get_descriptions, generate_vo_number, check_group_leader
   ||                                                 valid_balances, decrease_balances,get_balances,update_balances
   ||                                                 vis_authorised_offender,is_authorised_person,get_event_id
   ||                                                 get_leader_flag, recheck_time_slot, is_adult
   ||    Johnson              08-FEB-2006             Added the following functions and procedures to the packages
   ||                                                 sort_on_visitor_lastname, sort_on_visitor_firstname,
   ||                                                 sort_on_offender_lastname, sort_on_offender_firstname,
   ||                                                 populate_visitor_details, populate_offender_details.
   ||    GJC                  08-FEB-2006             Initial version for work package
 */

@Service
public class TagVisitsServiceImpl implements TagVisitsService {
	@Autowired
	private TagVisitsRepository tagVisitsRepository;
	@Autowired
	private TagReferenceCodesRepository tagReferenceCodesRepository;
	@Autowired
	private NonAssociationService nonAssociationService;
	private static Logger logger = LogManager.getLogger(TagVisitsServiceImpl.class.getName());
	private static String COMP = "COMP";
	@Autowired
	private OidvisitRepository oidvisitRepository;

	/*
	 * This procedure is migrated from oracle tag_visits.
	 * 
	 * @Procedure get_capacity to be used for getting CAPACITY. search results.
	 */
	@Override
	public Integer getCapacity(final String agyLocId, final Integer internalLocationId) {
		return tagVisitsRepository.getCapacity(agyLocId, internalLocationId);
	}

	/*
	 * This procedure is migrated from oracle tag_visits.
	 * 
	 * @Procedure get_location_desc to be used for getting DESCRIPTION in
	 * AGENCY_INTERNAL_LOCATIONS table. search results.
	 */
	@Override
	public String getLocationDesc(final AgencyInternalLocations bean) {
		return tagVisitsRepository.getLocationDesc(bean);
	}

	@Override
	public Map<String, Object> getPersonNames(final Long personId, final Date pVisitDate) {
		Map<String, Object> outParams = new HashMap<String, Object>();
		Persons obj = new Persons();
		String pLastName;
		String pFirstName;
		String pRestriction;
		Integer pAge = 0;
		// 33 get_names_cur
		obj = tagVisitsRepository.getNamesCur(personId);
		Date vBirthdate;
		pLastName = obj.getLastName();
		pFirstName = obj.getFirstName();
		if (obj.getBirthdate() != null) {
			vBirthdate = obj.getBirthdate();
			// pAge = tagVisitsRepository.getAgeForPersons(vBirthdate);
			// Above DB call has been modified to below code for age calculation
			Calendar today = Calendar.getInstance();
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime(vBirthdate);
			pAge = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		}
		pRestriction = tagVisitsRepository.getParentCodeGlRestrictionCur(personId, pVisitDate);

		outParams.put("p_last_name", pLastName);
		outParams.put("p_first_name", pFirstName);
		if (pAge > 0)
			outParams.put("p_age", pAge);
		else
			outParams.put("p_age", "");
		outParams.put("p_restriction", pRestriction);

		return outParams;
	}

	@Override
	@Transactional
	public Integer insertOffenderVisitVisitor(final VOffenderVisitVisitors offVisitors, final String userName) {
		Integer retVal = 0;
		try {
			offVisitors.setGroupLeaderFlag(
					offVisitors.getGroupLeaderFlag() != null ? offVisitors.getGroupLeaderFlag() : "N");

			// offender_visit_visitors Insert Operation 2558
			tagVisitsRepository.insertOffenderVisitVisitor(offVisitors, userName);
			retVal = 1;
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertOffenderVisitVisitor", e);
		}
		return retVal;

	}

	@Override
	public OffenderVisitVisitors populatedOffenderDetails(final BigDecimal p_offender_book_id,
			final BigDecimal p_contact_offender_root_id, final Date p_visit_date, final String userId) {
		final OffenderVisitVisitors offBean = new OffenderVisitVisitors();

		final OffenderContactPersons bean = new OffenderContactPersons();
		bean.setOffenderBookId(p_offender_book_id);
		bean.setContactRootOffenderId(p_contact_offender_root_id);
		Integer vOffenderBookId = tagVisitsRepository.getContactOffenderBookId(p_contact_offender_root_id, userId);
		OffenderContactPersons offConPer = tagVisitsRepository.typrCursor(bean);
		final VNameSearch2 vNameSerch = tagVisitsRepository.offenderContactCur(BigDecimal.valueOf(vOffenderBookId),
				userId);
		final ReferenceCodes reffCodd = tagVisitsRepository.offenderContactRefCur(BigDecimal.valueOf(vOffenderBookId),
				p_visit_date);
//		final Offenders off = tagVisitsRepository.birthDateCur(BigDecimal.valueOf(vOffenderBookId));
		String p_contact_type = null;
		String p_relationship_type = null;

		if (Optional.ofNullable(offConPer) != null) {
			if (offConPer.getContactType() != null) {
				p_contact_type = tagReferenceCodesRepository.domainCursorToString("CONTACTS",
						offConPer.getContactType());
			}
			if (offConPer.getRelationshipType() != null) {
				p_relationship_type = tagReferenceCodesRepository.domainCursorToString("RELATIONSHIP",
						offConPer.getRelationshipType());
			}
		}
		offBean.setLastName(vNameSerch.getLastName());
		offBean.setFirstName(vNameSerch.getFirstName());
		offBean.setContactType(p_contact_type != null ? p_contact_type : "");
		offBean.setRelationshipType(p_relationship_type != null ? p_relationship_type : "");
		offBean.setRestriction(reffCodd.getParentCode());
		offBean.setAgyLocId(vNameSerch.getLivingUnitDescription());
		offBean.setOffenderIdDisplay(vNameSerch.getOffenderIdDisplay());
		return offBean;
	}

	@Override
	public VOffenderAuthorisedVisitors getConatactOffenderDetails(final BigDecimal contactOffenderRootId,
			final Date visitDate, final String userId) {
		final VOffenderAuthorisedVisitors returnObj = new VOffenderAuthorisedVisitors();
		Integer vOffenderBookId = tagVisitsRepository.getContactOffenderBookId(contactOffenderRootId, userId);
		final VNameSearch2 bean = tagVisitsRepository.offenderContactCur(BigDecimal.valueOf(vOffenderBookId), userId);
		final String parentCode = tagVisitsRepository.contactOffenderRestCur(BigDecimal.valueOf(vOffenderBookId),
				visitDate);

		// map.put("p_id_display", bean.getOffenderIdDisplay());
		// map.put("p_last_name", bean.getLastName());
		// map.put("p_first_name", bean.getFirstName());
		// map.put("p_location", bean.getLivingUnitDescription());
		// map.put("p_contact_offender_book_id", bean.getOffenderBookId());

		// P_RESTRICTION_TYPE is not found
		returnObj.setLocation(bean.getLivingUnitDescription());
		returnObj.setVisitorLastName(bean.getLastName());
		returnObj.setVisitorFirstName(bean.getFirstName());
		returnObj.setRestriction(parentCode);
		return returnObj;
	}

	// This method is used for iterating agency_visit_slot_id sequence and
	// retreiving from database
	@Override
	public Long getNextAgyVisitSlotId() {
		return tagVisitsRepository.getNextAgyVisitSlotId();
	}

	// Retrieving string data from database
	@Override
	public String checkTimeslot(final BigDecimal pIntLocId, final String pWeekDay, final String pAgyLocId,
			final String pStartTime) {
		return tagVisitsRepository.checkTimeslot(pIntLocId, pWeekDay, pAgyLocId, pStartTime);
	}

	@Override
	public List<VOcuavlocAvailable> getOcuavlocAvailable(final VOcuavlocAvailable objSearchDao) {
		return tagVisitsRepository.getOcuAvailable(objSearchDao);

	}

	/*
	 * This procedure is migrated from oracle tag_visits.
	 * 
	 * @Procedure GET_OCUAVLOC_UNAVAILABLE to be used for VOcuavlocUnavailable
	 * search results.
	 */
	@Override
	public List<VOcuavlocUnavailable> getOcuavlocUnavailable(final VOcuavlocUnavailable bean) {
		return tagVisitsRepository.getOcuUnavailable(bean);

	}

	@Override
	public Map<String, Object> visitCalc(final Long pAgyId, final Long pBkgId, final String pSecLvl,
			final String pVisTp, final Date pVisDt) {
		Map<String, Object> returnMap = new HashMap<>();
		// Output Variables
		String pCycTp = null;
		Date pCycEnd = null;
		BigDecimal pTermVis = null;
		BigDecimal pTermHrs = null;
		BigDecimal pTypeVis = null;
		BigDecimal pTypeHrs = null;

		Date pCycStart = null;
		BigDecimal pTotHrs = new BigDecimal(0);
		Long pTotVis = 0L;
		BigDecimal pUsedVis = new BigDecimal(0);
		Long pUsedHrs = 0L;
		Long pLimId = null;

		VisitCycleLimits cycEndCur = tagVisitsRepository.getVisitCycleLimits(pAgyId, pSecLvl, pVisDt);
		if (cycEndCur != null) {
			pCycTp = cycEndCur.getCycleType();
			pCycStart = cycEndCur.getCreateDatetime();
			pCycEnd = cycEndCur.getExpiryDate();
			pTotHrs = cycEndCur.getTotHrs();
			pTotVis = cycEndCur.getTotVisits();
			pLimId = cycEndCur.getVisitCycleLimitId();
		}

		Map<String, Object> usedVisCur = tagVisitsRepository.getUsedVisCur(pAgyId, pSecLvl, pBkgId, pCycStart, pCycEnd,
				null);
		if (usedVisCur != null) {
			pUsedVis = (BigDecimal) usedVisCur.get("p_used_vis");
			pUsedHrs = Long.valueOf(usedVisCur.get("p_used_hrs").toString());
		}

		pTermVis = new BigDecimal(pTotVis).subtract(pUsedVis);
		if (pTotHrs != null) {
			pTermHrs = pTotHrs.subtract(new BigDecimal(pUsedHrs));
		}

		VisitTypeLimits totTypeCur = tagVisitsRepository.getTotTypeCur(pLimId, pVisTp);

		if (pVisTp != null) {
			pTotHrs = null;
			pTotVis = null;
			pUsedVis = new BigDecimal(0);
			pUsedHrs = 0L;
			if (totTypeCur != null) {
				pTotHrs = totTypeCur.getMaxHrsType();
				pTotVis = totTypeCur.getMaxVisitorsType();
			}

			usedVisCur = tagVisitsRepository.getUsedVisCur(pAgyId, pSecLvl, pBkgId, pCycStart, pCycEnd, pVisTp);
			if (usedVisCur != null) {
				pUsedVis = (BigDecimal) usedVisCur.get("p_used_vis");
				pUsedHrs = Long.valueOf(usedVisCur.get("p_used_hrs").toString());
			}

			if (pTotHrs == null) {
				pTypeHrs = null;
			} else {
				pTypeHrs = pTotHrs.subtract(new BigDecimal(pUsedHrs));
			}

			if (pTotVis == null) {
				pTypeVis = null;
			} else {
				pTypeVis = new BigDecimal(pTotVis).subtract(pUsedVis);
			}
		}
		returnMap.put("P_CYC_TP", pCycTp);
		returnMap.put("P_CYC_END", pCycEnd);
		returnMap.put("P_TREM_VIS", pTermVis);
		returnMap.put("P_TREM_HRS", pTermHrs);
		returnMap.put("P_TYPE_VIS", pTypeVis);
		returnMap.put("P_TYPE_HRS", pTypeHrs);
		return returnMap;
	}

	@Override
	public Integer completeVisitors(final VOffenderVisits bean, final String userName) {
		return tagVisitsRepository.updateOffeVisitVisitors(bean.getOffenderVisitId(), COMP, userName);
	}

	@Override
	public VOffenderVisits visitCalc(final String pAgyId, final Long pBkgId, final String pSecLvl, final String pVisTp,
			final Date pVisDt) {
		final VOffenderVisits bean = new VOffenderVisits();
		Date pCycStart = null;
		BigDecimal pTotHrs = BigDecimal.ZERO;
		Long pTotVis = 0l;
		BigDecimal pUsedHrs = BigDecimal.ZERO;
		Long pUsedVis = 0l;
		Long pLimId = null;
		String pCycTp = null;
		Date pCycEnd = null;
		Long pTermVis = null;
		BigDecimal pTermHrs = null;
		Long pTypeVis = null;
		BigDecimal pTypeHrs = null;
		Long totalVists = null;
		Long usedtotalVists = null;
		Long ptotalVists = null;
		Long pusedtotalVists = 0L;
		BigDecimal totalTime = BigDecimal.ZERO;
		BigDecimal totalUsedTime = BigDecimal.ZERO;
		BigDecimal ptotalTime = BigDecimal.ZERO;
		BigDecimal ptotalUsedTime = BigDecimal.ZERO;

		try {
			final VisitCycleLimits vcl = tagVisitsRepository.cycEndCur(pVisDt, pAgyId, pSecLvl);
			if (vcl != null) {
				pCycTp = vcl.getCycleType();
				pCycStart = vcl.getCreateDatetime();
				pCycEnd = vcl.getExpiryDate();
				if (vcl.getTotHrs() != null) {
					totalTime = vcl.getTotHrs();
				} else {
					totalTime = null;
				}

				if (vcl.getTotVisits() != null) {
					totalVists = vcl.getTotVisits();
				} else {
					totalVists = null;
				}
				pLimId = vcl.getVisitCycleLimitId();
			}

			final Object[] obj = tagVisitsRepository.usedVisCurIepTotalUsed(pBkgId, pAgyId,pCycStart,pCycEnd);
			if (obj != null) {
				usedtotalVists = Long.parseLong(obj[0].toString());
				String timeUsed = obj[1].toString();
				Float timeU = (float) (Long.valueOf(timeUsed.substring(0, timeUsed.indexOf(":"))) / 24.0);
				totalUsedTime = new BigDecimal(timeU.toString());
			} else {
				totalUsedTime = BigDecimal.ZERO;
				usedtotalVists = 0L;
			}

			final VisitTypeLimits vtl = tagVisitsRepository.totTypeCur(pLimId, pVisTp);
			if (vtl != null) {
				ptotalTime = vtl.getMaxHrsType();
				ptotalVists = vtl.getMaxVisitsType();
			} else {
				ptotalVists = null;
				ptotalTime = null;
			}
			if (ptotalVists != null || ptotalTime != null) {//usedVisCur
				final Object[] array = tagVisitsRepository.usedVisCurIep(pBkgId, pAgyId, pSecLvl, pVisTp, pCycStart,
						pCycEnd);
				if (array != null && array.length > 0) {
					if (array[0].toString() != null) {
						pusedtotalVists = Long.parseLong(array[0].toString());
					} else {
						pusedtotalVists = 0L;
					}

					if (array[1].toString() != null) {
						String timeUsed = array[1].toString();
						Float timeU = (float) (Long.valueOf(timeUsed.substring(0, timeUsed.indexOf(":"))) / 24.0);
						ptotalUsedTime = new BigDecimal(timeU.toString());
					} else {
						ptotalUsedTime = BigDecimal.ZERO;
					}
				} else {
					pusedtotalVists = 0L;
					ptotalUsedTime = BigDecimal.ZERO;
				}
			}

		} catch (DataAccessException e) {
			logger.error("visitCalc :" + e);
		}
		catch (Exception e) {
			logger.error(e);
		}
		if (totalTime != null) {
			bean.setTotalRemainingVisits(null);
			bean.setRemainingVisitsType(null);
			String str = totalTime.subtract(totalUsedTime).toString();
			bean.setTotalRemainingTime(new BigDecimal(str.toString().substring(0, str.toString().indexOf(".")) + "."
					+ Math.round(Double.valueOf(0 + "."
							+ str.toString().substring(str.toString().indexOf(".") + 1, str.toString().length()))
							* 60)));
			bean.setTotalRemainingTimeTemp(str);
			if (ptotalTime != null) {
				String bg = ptotalTime.subtract(ptotalUsedTime).toString();
				bean.setRemainingTimeType(new BigDecimal(bg.toString().substring(0, bg.toString().indexOf(".")) + "."
						+ Math.round(Double.valueOf(0 + "."
								+ bg.toString().substring(bg.toString().indexOf(".") + 1, bg.toString().length()))
								* 60)));
			bean.setRemainingTimeTypeTemp(bg);
			} else {
				bean.setRemainingTimeType(null);
				bean.setRemainingTimeTypeTemp(null);
			}
		}
		if (totalVists != null) {
			bean.setTotalRemainingVisits((totalVists - usedtotalVists)<=0?0:(totalVists - usedtotalVists));
			if (ptotalVists != null) {
				bean.setRemainingVisitsType((ptotalVists - pusedtotalVists)<=0?0:(ptotalVists - pusedtotalVists));
			} else {
				bean.setRemainingVisitsType(null);
			}
			bean.setTotalRemainingTime(null);
			bean.setRemainingTimeType(null);
		}
		bean.setCycleEnds(pCycEnd);
		
		return bean;
	}

	@Override
	public Integer getContactOffenderBookId(final BigDecimal rootOffenderId, final String userId) {
		return tagVisitsRepository.getContactOffenderBookId(rootOffenderId, userId);
	}

	@Override
	public BigDecimal getRootOffenderIdFromBook(BigDecimal offBookId) {
		return tagVisitsRepository.getRootOffenderIdFromBook(offBookId);
	}

	// iterating sequence and retrieving from database
	@Override
	public BigDecimal getNextOffVisitId() {
		return tagVisitsRepository.getNextOffVisitId();
	}

	@Override
	public BigDecimal getEventId() {
		return tagVisitsRepository.getEventId();
	}

	@Override
	public BigDecimal getNextOffVisitVisitorId() {
		return tagVisitsRepository.getNextOffVisitVisitorId();
	}

	@Override
	public BigDecimal getOffenderId(final String vstOffIdDisplay) {
		return tagVisitsRepository.getOffenderId(vstOffIdDisplay);
	}

	@Override
	public String overlapVisit(final BigDecimal offenderVisitId, final BigDecimal offenderBookId,
			final Integer personId, final Date visitDate, final Date startTime, final Date endTime) {
		BigDecimal lOffenderBookId = null;
		String pOverlapDetails = null;
		if (personId != null) {
			lOffenderBookId = tagVisitsRepository.getlOffenderBookId(offenderVisitId, personId, visitDate, startTime,
					endTime);
		} else if (offenderBookId != null) {
			lOffenderBookId = tagVisitsRepository.getlOffenderBookIdOne(offenderVisitId, offenderBookId, visitDate,
					startTime, endTime);
		}

		if (lOffenderBookId != null) {
			pOverlapDetails = tagVisitsRepository.getOverlapDetails(lOffenderBookId);
		}
		return pOverlapDetails;
	}

	// This PROCEDURE populate_visitor_details
	@Override
	public Map<String, Object> populateVisitorDetails(final Long offenderBookId, final Long personId,
			final Long offContactPersonId, final Date visitsDate) {
		Map<String, Object> map = new HashMap<>();
		String pFirstName = null;
		String pLastName = null;
		Date pBirthDate = null;
		String pglRestriction = null;
		String pRestriction = null;
		String pContactType = null;
		String pRelationshipType = null;
		Long pAge = null;
		// This method is used for Selecting Visitor Details person_cur
		Persons persons = tagVisitsRepository.getPersonCur(personId);
		// This method is used for Selecting global visitor restriction
		ReferenceCodes rCodes = tagVisitsRepository.restrictionCur(offenderBookId, personId, visitsDate);
		// This method is used for Selecting Visitor restriction
		ReferenceCodes glRCodes = tagVisitsRepository.glRestrictionCur(offenderBookId, personId, visitsDate);
		// This method is used for Selecting Contact Type, Restriction Type
		List<OffenderContactPersons> offcontPerList = tagVisitsRepository.typeCur(offenderBookId, personId);
		OffenderContactPersons offcontPer = offcontPerList.get(0);
		pFirstName = persons.getFirstName();
		pLastName = persons.getLastName();
		pBirthDate = persons.getBirthdate();
		pglRestriction = glRCodes.getParentCode();
		pRestriction = rCodes.getParentCode();
		if (offcontPer != null && offcontPer.getContactType() != null) {

			pContactType = tagReferenceCodesRepository.getDescCode("CONTACTS", offcontPer.getContactType());
		}
		if (offcontPer != null && offcontPer.getRelationshipType() != null) {

			pRelationshipType = tagReferenceCodesRepository.getDescCode("RELATIONSHIP",
					offcontPer.getRelationshipType());
		}
		// This method is used to get months_between
		pAge = tagVisitsRepository.getpAge(visitsDate, pBirthDate);
		if (Optional.ofNullable(pAge).isPresent())
			pAge = pAge / 12;
		map.put("P_LAST_NAME", pLastName);
		map.put("P_FIRST_NAME", pFirstName);
		map.put("P_AGE", pAge);
		map.put("P_CONTACT_TYPE", pContactType);
		map.put("P_RELATIONSHIP_TYPE", pRelationshipType);
		map.put("P_RESTRICTION", pRestriction);
		map.put("P_GL_RESTRICTION", pglRestriction);

		return map;
	}

	@Override
	public Integer cancelVisitors(final Long pOffenderVisitId, final String outcomRreasonCode, final String userName) {
		final Integer count1 = tagVisitsRepository.updateOffenderVisitVisitors(pOffenderVisitId, outcomRreasonCode,
				userName);
		Integer count2 = null;
		if (count1 == 1) {
			count2 = tagVisitsRepository.updateOffenderVisits(pOffenderVisitId, userName);
		}
		return count2;
	}

	@Override
	public String chkVisitConflicts(final Long offenderBookId, final Long offenderVisitId, final Date visitDate,
			final Date startTime, final Date endTime, final Long visitInternalLocationId) {
		String lvNonAssocWarningMsg = null;
		String lvVictimWarningMsg = null;
		String pWarningMessage = null;
		String lvContactFlag = null;
		String lvRelationshiDesc = null;
		String lvVisitedOffenderName = tagVisitsRepository.visitedOffenderCur(offenderBookId);

		List<Offenders> otherVisitsOffslist = tagVisitsRepository.otherVisitsOffsCur(offenderBookId, offenderVisitId,
				visitDate, startTime, endTime, visitInternalLocationId);
		List<Offenders> visitVisitingOffslist = tagVisitsRepository.visitVisitingOffsCur(offenderVisitId);

		List<Persons> visitPersonsCurlist = tagVisitsRepository.visitPersonsCur(offenderVisitId);

		List<Persons> otherVisitsPersonslist = tagVisitsRepository.otherVisitsPersonsCur(offenderBookId,
				offenderVisitId, visitDate, startTime, endTime, visitInternalLocationId);

		if (otherVisitsOffslist.size() > 0 && !otherVisitsOffslist.isEmpty()) {
			for (Offenders bean : otherVisitsOffslist) {
				if (bean != null && bean.getOffenderBookId() != null) {
					if (bean.getOffenderBookId() != offenderBookId) {
						lvContactFlag = nonAssociationService.chkNaBetweenOffenders(offenderBookId, offenderVisitId);
						if (lvContactFlag.equalsIgnoreCase("Y")) {
							lvNonAssocWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagess(
									lvNonAssocWarningMsg, bean.getFirstName(), lvVisitedOffenderName);
						}
					}
				}
			}
		}
		// check 1
		if (visitVisitingOffslist.size() > 0 && !visitVisitingOffslist.isEmpty()) {
			for (Offenders bean : visitVisitingOffslist) {
				if (bean.getOffenderBookId() != null) {
					lvContactFlag = nonAssociationService.chkNaBetweenOffenders(offenderBookId, offenderVisitId);
					if (bean.getOffenderBookId() != offenderBookId) {
						if (lvContactFlag.equalsIgnoreCase("Y")) {
							lvNonAssocWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk2(
									lvNonAssocWarningMsg, bean.getFirstName(), lvVisitedOffenderName);
						}
					}
				}
			}
		}
		// check 2
		if (visitVisitingOffslist.size() > 0 && !visitVisitingOffslist.isEmpty()) {
			for (Offenders bean : visitVisitingOffslist) {
				if (bean.getOffenderBookId() != null) {
					if (visitVisitingOffslist.size() > 0 && !visitVisitingOffslist.isEmpty()) {
						for (Offenders bean1 : visitVisitingOffslist) {
							if (bean1.getOffenderBookId() != null) {
								if (bean1.getOffenderBookId() != bean.getOffenderBookId()) {
									lvContactFlag = nonAssociationService.chkNaBetweenOffenders(offenderBookId,
											offenderVisitId);
									if (lvContactFlag.equalsIgnoreCase("Y")) {
										lvNonAssocWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk3(
												lvNonAssocWarningMsg, bean.getFirstName(), bean1.getFirstName());
									}
								}
							}
						}
					}
				}
			}
		}
		// check 3
		if (visitVisitingOffslist.size() > 0 && !visitVisitingOffslist.isEmpty()) {
			for (Offenders bean : visitVisitingOffslist) {
				if (bean.getOffenderBookId() != null) {
					for (Offenders bean1 : visitVisitingOffslist) {
						if (bean1.getOffenderBookId() != null) {
							lvContactFlag = nonAssociationService.chkNaBetweenOffenders(offenderBookId,
									offenderVisitId);
							if (lvContactFlag.equalsIgnoreCase("Y")) {
								lvNonAssocWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk4(
										lvNonAssocWarningMsg, bean.getFirstName(), bean1.getFirstName());
							}
						}
					}
				}
			}
		}
		// check 4
		if (visitPersonsCurlist.size() > 0 && !visitPersonsCurlist.isEmpty()) {
			for (Persons bean : visitPersonsCurlist) {
				if (bean.getPersonId() != null) {
					lvRelationshiDesc = tagVisitsRepository.isVictimCur(offenderBookId, bean.getPersonId());
					if (lvRelationshiDesc != null) {
						lvNonAssocWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk5(
								lvNonAssocWarningMsg, bean.getFirstName(), lvRelationshiDesc);
					}
				}
			}
		}
		// check 5
		if (visitPersonsCurlist.size() > 0 && !visitPersonsCurlist.isEmpty()) {
			for (Persons bean : visitPersonsCurlist) {
				if (bean.getPersonId() != null) {
					lvRelationshiDesc = tagVisitsRepository.isVictimCur(offenderBookId, bean.getPersonId());
					if (lvRelationshiDesc != null) {
						lvVictimWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk6(lvVictimWarningMsg,
								lvRelationshiDesc, bean.getFirstName(), lvVisitedOffenderName);
					}
				}
			}
		}
		// check 6
		if (visitPersonsCurlist.size() > 0 && !visitPersonsCurlist.isEmpty()) {
			for (Persons bean : visitPersonsCurlist) {
				if (bean.getPersonId() != null) {
					for (Persons bean1 : visitPersonsCurlist) {
						if (bean1.getPersonId() != null) {
							lvRelationshiDesc = tagVisitsRepository.isVictimCur(offenderBookId, bean1.getPersonId());
							if (lvRelationshiDesc != null) {
								lvVictimWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk7(
										lvVictimWarningMsg, bean.getFirstName(), bean1.getFirstName(),
										lvRelationshiDesc);
							}
						}
					}
				}
			}
		}
		// check 7
		if (visitPersonsCurlist.size() > 0 && !visitPersonsCurlist.isEmpty()) {
			for (Persons bean : visitPersonsCurlist) {
				if (bean.getPersonId() != null) {
					for (Offenders bean1 : otherVisitsOffslist) {
						if (bean1.getOffenderBookId() != null) {
							lvRelationshiDesc = tagVisitsRepository.isVictimCur(bean1.getOffenderBookId(),
									bean.getPersonId());
							if (lvRelationshiDesc != null) {
								lvVictimWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk8(
										lvVictimWarningMsg, bean.getFirstName(), bean1.getFirstName(),
										lvRelationshiDesc);
							}
						}
					}
				}
			}
		}

		// check 8
		if (otherVisitsPersonslist.size() > 0 && !otherVisitsPersonslist.isEmpty()) {
			for (Persons bean : otherVisitsPersonslist) {
				if (bean.getPersonId() != null) {
					lvRelationshiDesc = tagVisitsRepository.isVictimCur(offenderVisitId, bean.getPersonId());
				}
				if (lvRelationshiDesc != null) {
					lvVictimWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk9(lvVictimWarningMsg,
							bean.getFirstName(), lvVisitedOffenderName, lvRelationshiDesc);
				}
			}
		}
		// check 9
		if (otherVisitsPersonslist.size() > 0 && !otherVisitsPersonslist.isEmpty()) {
			for (Persons bean : otherVisitsPersonslist) {
				if (bean.getPersonId() != null) {
					if (otherVisitsOffslist.size() > 0 && !otherVisitsOffslist.isEmpty()) {
						for (Offenders bean1 : otherVisitsOffslist) {
							if (bean1.getOffenderBookId() != null) {
								lvRelationshiDesc = tagVisitsRepository.isVictimCur(bean1.getOffenderBookId(),
										bean.getPersonId());
								if (lvRelationshiDesc != null) {
									lvVictimWarningMsg = tagVisitsRepository.lvNonAssocWarningMessageagessChk10(
											lvVictimWarningMsg, bean.getFirstName(), bean1.getFirstName(),
											lvRelationshiDesc);
								}
							}
						}
					}
				}
			}
		}
		if (lvNonAssocWarningMsg != null) {
			pWarningMessage = tagVisitsRepository.warningMsg(lvNonAssocWarningMsg);
		}
		if (lvVictimWarningMsg != null) {
			lvVictimWarningMsg = tagVisitsRepository.lVictimWarningMsg(lvVictimWarningMsg);
			if (pWarningMessage != null) {
				pWarningMessage = tagVisitsRepository.lVictimWarningMsg1(lvNonAssocWarningMsg, pWarningMessage);
			} else {
				pWarningMessage = lvNonAssocWarningMsg;
			}
		}
		return pWarningMessage;
	}

	@Override
	public VOffenderVisits iepVisitCalc(final String pAgyId, final Long pBkgId, final String iepLevel,
			final String pVisTp, final Date pVisDt) {
		final VOffenderVisits bean = new VOffenderVisits();
		Date pCycStart = null;
		BigDecimal pTotHrs = BigDecimal.ZERO;
		Long pTotVis = 0l;
		BigDecimal pUsedHrs = BigDecimal.ZERO;
		Long pUsedVis = 0l;
		Long pLimId = null;
		String pCycTp = null;
		Date pCycEnd = null;
		Long totalVists = null;
		Long usedtotalVists = null;
		Long ptotalVists = null;
		Long pusedtotalVists = 0L;
		BigDecimal totalTime = BigDecimal.ZERO;
		BigDecimal totalUsedTime = BigDecimal.ZERO;
		BigDecimal ptotalTime = BigDecimal.ZERO;
		BigDecimal ptotalUsedTime = BigDecimal.ZERO;

		try {
			final VisitCycleLimits vcl = tagVisitsRepository.cycEndCurIep(pVisDt, pAgyId, iepLevel);
			if (vcl != null) {
				pCycTp = vcl.getCycleType();
				pCycStart = vcl.getCreateDatetime();
				pCycEnd = vcl.getExpiryDate();
				if (vcl.getTotHrs() != null) {
					totalTime = vcl.getTotHrs();
				} else {
					totalTime = null;
				}

				if (vcl.getTotVisits() != null) {
					totalVists = vcl.getTotVisits();
				} else {
					totalVists = null;
				}
				pLimId = vcl.getVisitCycleLimitId();
			}

			final Object[] obj = tagVisitsRepository.usedVisCurIepTotalUsed(pBkgId, pAgyId,pCycStart,pCycEnd);
			if (obj != null) {
				usedtotalVists = Long.parseLong(obj[0].toString());
				String timeUsed = obj[1].toString();
				Float timeU = (float) (Long.valueOf(timeUsed.substring(0, timeUsed.indexOf(":"))) / 24.0);
				totalUsedTime = new BigDecimal(timeU.toString());
			} else {
				totalUsedTime = BigDecimal.ZERO;
				usedtotalVists = 0L;
			}
			final VisitTypeLimits vtl = tagVisitsRepository.totTypeCurIep(pLimId, pVisTp);
			if (vtl != null) {
				ptotalTime = vtl.getMaxHrsType();
				ptotalVists = vtl.getMaxVisitsType();
			} else {
				ptotalVists = null;
				ptotalTime = null;
			}
			if (ptotalVists != null || ptotalTime != null) {
				final Object[] array = tagVisitsRepository.usedVisCurIep(pBkgId, pAgyId, iepLevel, pVisTp, pCycStart,
						pCycEnd);
				if (array != null && array.length > 0) {
					if (array[0].toString() != null) {
						pusedtotalVists = Long.parseLong(array[0].toString());
					} else {
						pusedtotalVists = 0L;
					}

					if (array[1].toString() != null) {
						String timeUsed = array[1].toString();
						Float timeU = (float) (Long.valueOf(timeUsed.substring(0, timeUsed.indexOf(":"))) / 24.0);
						ptotalUsedTime = new BigDecimal(timeU.toString());
					} else {
						ptotalUsedTime = BigDecimal.ZERO;
					}
				} else {
					pusedtotalVists = 0L;
					ptotalUsedTime = BigDecimal.ZERO;
				}
			}
		} catch (DataAccessException e) {
			logger.error("visitCalc :" + e);
		}

		if (totalTime != null) {
			bean.setTotalRemainingVisits(null);
			bean.setRemainingVisitsType(null);
			String str = totalTime.subtract(totalUsedTime).toString();
			bean.setTotalRemainingTime(new BigDecimal(str.toString().substring(0, str.toString().indexOf(".")) + "."
					+ Math.round(Double.valueOf(0 + "."
							+ str.toString().substring(str.toString().indexOf(".") + 1, str.toString().length()))
							* 60)));
			bean.setTotalRemainingTimeTemp(str);
			if (ptotalTime != null) {
				String bg = ptotalTime.subtract(ptotalUsedTime).toString();
				bean.setRemainingTimeType(new BigDecimal(bg.toString().substring(0, bg.toString().indexOf(".")) + "."
						+ Math.round(Double.valueOf(0 + "."
								+ bg.toString().substring(bg.toString().indexOf(".") + 1, bg.toString().length()))
								* 60)));
				bean.setRemainingTimeTypeTemp(bg);
			} else {
				bean.setRemainingTimeType(null);
				bean.setRemainingTimeTypeTemp(null);
			}
		}
		if (totalVists != null) {
			bean.setTotalRemainingVisits((totalVists - usedtotalVists)<=0?0:(totalVists - usedtotalVists));
			if (ptotalVists != null) {
				bean.setRemainingVisitsType((ptotalVists - pusedtotalVists)<=0?0:(ptotalVists - pusedtotalVists));
			} else {
				bean.setRemainingVisitsType(null);
			}
			bean.setTotalRemainingTime(null);
			bean.setRemainingTimeType(null);
		}
		bean.setCycleEnds(pCycEnd);
		return bean;
	}
}