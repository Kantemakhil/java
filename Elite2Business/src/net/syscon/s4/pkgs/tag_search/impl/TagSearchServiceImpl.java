package net.syscon.s4.pkgs.tag_search.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_search.TagSearchRepository;
import net.syscon.s4.pkgs.tag_search.TagSearchService;

/*
 * Below comments are copied from package tag_search
//@@@ Purpose: Search Package - encompassing all of the different searches performed on Offender
   ||
   ||    MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
   ||PLEASE MODIFY THE VCP_VERSION WITH PROPER VERSION NUMBER AND MODIFICATION DATE WHEN
||    THIS PACKAGE IS BEING MODIFIED.
||    --------------------------------------------------------------------------------------------------------
||    Person        Date           Version      Comments
||    --------------------------------------------------------------------------------------------------------
||    Mohit         04-MAR-2014    2.6.1.6    HPQC#22197: Added alias ob1 in the prison_details_inactive_cur cursor in populate_off_details procedure.  
||    Abhishek      04-JUN-2012    2.6.1.5    HPQC#15742: Modify get_offender_records procedure to get correct Working_Name_Flag for two offender bookings of same offender.
||    Abhishek      04-JUN-2012    2.6.1.4    HPQC#15742: Modify get_offender_records procedure to get correct Working_Name_Flag for two offender bookings of same offender.
||    Nasir         12-DEC-2011    2.6.1.3    Modify populate_off_details procedure to get correct community location - HPQC#10202
||    Vikas Grover  29-Jun-2007  2.6.1.2   Logic added in get_offender_records new procedure for search on booking_no
||                                       Also  the offender_id_display padding problem is solved in this procedure
||    Niko          18-Apr-2007  2.6.1.1   Modified Procedure Populate Offender Details to display correct inmate status
||    Vikas Grover  21-FEB-2007  2.6.1.0   Overloaded get_offender_records procedure with booking no, as parameter
||                                               This one is in accordance with TAG standard to search on booking no. too.
||    Rajshree     25-OCT-2005 2.6       Modified code in 'populate_off_details' to display offender_details of inactive offenders too.Defect ID 5366
||    GJC          14-Oct-2006 2.5       Removed DBMS_OUTPUT calls
||    Erin         08-Jun-2006 2.3       modified PROCEDURE get_partial_records to search with variable p_agedate
||    Erin         23-May-2006 2.2       Remove AGE from offender_record, add PROCEDURE get_age_date_range to determine birth date (p_agedate) from entered AGE,
||                                       modified PROCEDURE get_offender_records to search with variable p_agedate
||    Surya       20-Jun-2005  1.4       Tr#569: Modified the populate_off_details procedure as per the Address
||                                       data model changes,i.e., replaced the offender_residences with the new
||                                       table addresses in prison and community cursors.
||    Rajshree    17-DEC-2005  10.1.1    Modified code in 'populate_off_details' to display the address (offender search screen)
||                                       which have most  recent date in from date as per Christina's suggestion in tracking #405
||    Vipul       21-DEC-2004  10.1.0    Created the Packge and all the associated procedures.
||
||    --------------------------------------------------------------------------------------------------------
*/

@Service
public class TagSearchServiceImpl extends BaseBusiness implements TagSearchService {
	private static Logger logger = LogManager.getLogger(TagSearchServiceImpl.class.getName());
	@Autowired
	private TagSearchRepository tagSearchRepository;

	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	@Autowired
	private EliteDateService eliteDateService;

	// This method is used for Get Offender Identifier Details for a particular
	// offender id.
	@Override
	public List<OffenderIdentifier> getOffenderIdentifiers(final Long offenderId) {
		return tagSearchRepository.getOffenderIdentifiers(offenderId);
	}

	private static String S = "S";
	private static String P = "P";
	private static String TRUE = "TRUE";

	// Procedure get_offender_search to be used with Name Search and Identifier
	// Search.
	@Override
	public List<Offenders> getOffenderRecords(final Offenders offenders) {

		List<Offenders> offList = new ArrayList<Offenders>();
		String pLnameKey = null;
		String vDispFlag = "N";
		String vOffenderIdDisplay = null;
		Date[] lvFrToDateArry = new Date[2];
		Date[] lvFrmToAgeDateArry = new Date[2];

		try {
			// 439 oms_miscellaneous.get_profile_value()
			vDispFlag = tagSearchRepository.getProfileValue("DISPLAY", "ID_DISPLAY");

			if (offenders.getSearchType() != null) {
				if ("N".equals(offenders.getSearchType())) {
					pLnameKey = offenders.getLastName().substring(0, 1);
					offenders.setLastNameAlphaKey(pLnameKey);

					if (offenders.getBirthYear() != null) {
						// get_date_range 455
						offenders.setBirthRange(offenders.getBirthRange() != null ? offenders.getBirthRange() : 2);
						lvFrToDateArry = getDateRange(offenders.getBirthYear(), offenders.getBirthRange());

						offenders.setLvFromDate(lvFrToDateArry[0]);
						offenders.setLvToDate(lvFrToDateArry[1]);
					}
					if (offenders.getAgeDate() != null) {
						offenders.setAgeRange(offenders.getAgeRange() != null ? offenders.getAgeRange() : 2);

						// get_age_date_range 463
						lvFrmToAgeDateArry = getAgeDateRange(offenders.getAgeDate(), offenders.getAgeRange());

						offenders.setLvFromAgedate(lvFrmToAgeDateArry[0]);
						offenders.setLvToAgedate(lvFrmToAgeDateArry[1]);
					}
					if ("N".equals(offenders.getSwitchNames()) && "N".equals(offenders.getNameVariation())) {
						// 475 resultset
						offList = tagSearchRepository.resultSetOperationOne(offenders);
					} else if ("Y".equals(offenders.getSwitchNames()) && "N".equals(offenders.getNameVariation())) {
						// 524 resultset
						offList = tagSearchRepository.resultSetOperationTwo(offenders);
					} else if ("N".equals(offenders.getSwitchNames()) && "Y".equals(offenders.getNameVariation())) {
						// 564 resultset
						offList = tagSearchRepository.resultSetOperationThree(offenders);
					}
				} 					
				else if ("B".equals(offenders.getSearchType())) {
					if (offenders.getBirthYear() != null && offenders.getBirthRange() != null) {
						// get_date_range 455
						lvFrToDateArry = getDateRange(offenders.getBirthYear(), offenders.getBirthRange());

						offenders.setLvFromDate(lvFrToDateArry[0]);
						offenders.setLvToDate(lvFrToDateArry[1]);
						offenders.setBirthYear(null);
					}
					if (offenders.getAgeDate() != null) {
						offenders.setAgeRange(offenders.getAgeRange() != null ? offenders.getAgeRange() : 2);

						// get_age_date_range 463
						lvFrmToAgeDateArry = getAgeDateRange(offenders.getAgeDate(), offenders.getAgeRange());

						offenders.setLvFromAgedate(lvFrmToAgeDateArry[0]);
						offenders.setLvToAgedate(lvFrmToAgeDateArry[1]);
					}
						offList = tagSearchRepository.resultSetOperationDob(offenders);
				

				}
				
					else if ("I".equals(offenders.getSearchType())) {
				
					if (offenders.getOffenderIdDisplay() != null) {
						if ("Y".equals(vDispFlag)) {
							vOffenderIdDisplay = String.format("%10s", "" + offenders.getOffenderIdDisplay())
									.replace(' ', '0');
						} else {
							vOffenderIdDisplay = offenders.getOffenderIdDisplay();
						}
					}
					if (vOffenderIdDisplay != null && offenders.getBookingNo() == null
							&& offenders.getIdentifierType() == null && offenders.getIdentifier() == null) {
						// 638 resultset
						offList = tagSearchRepository.resultSetOperationFour(vOffenderIdDisplay);
					} else if (vOffenderIdDisplay != null && offenders.getBookingNo() != null
							&& offenders.getIdentifierType() == null && offenders.getIdentifier() == null) {
						// 665 resultset
						offList = tagSearchRepository.resultSetOperationFive(vOffenderIdDisplay,
								offenders.getBookingNo());
					} else if (vOffenderIdDisplay == null && offenders.getBookingNo() != null
							&& offenders.getIdentifierType() == null && offenders.getIdentifier() == null) {
						// 699 resultset
						offList = tagSearchRepository.resultSetOperationSix(offenders.getBookingNo());
					} else if (vOffenderIdDisplay == null && offenders.getIdentifierType() != null
							&& offenders.getIdentifier() != null) {
						// 727 resultset
						offList = tagSearchRepository.resultSetOperationSeven(offenders.getBookingNo(),
								offenders.getIdentifier(), offenders.getIdentifierType());
					} else if (vOffenderIdDisplay != null && offenders.getIdentifierType() != null
							&& offenders.getIdentifier() != null) {
						// 766 resultset
						offList = tagSearchRepository.resultSetOperationEigth(offenders.getBookingNo(),
								offenders.getIdentifier(), offenders.getIdentifierType(), vOffenderIdDisplay);
					}
					}
				}
			
		} catch (Exception e) {
			logger.error("getOffenderRecords", e);
		}
		return offList;
	}

	/*
	 * This procedure is migrated from oracle GET_PARTIAL_RECORDSGET_PARTIAL_RECORDS
	 * 
	 * @Procedure get_partial_searches to be used for returning partial and soundex
	 * search results.
	 */
	@Override
	public List<Offenders> getPartialRecords(final Offenders offenders) {
		List<Offenders> offList = new ArrayList<Offenders>();
		Date lvFromDate = null;
		Date lvToDate = null;
		Date lvFromAgedate = null;
		Date lvToAgedate = null;
		String pSoundexLname = null;
		Date[] lvFrToDateArry = new Date[2];
		Date[] lvFrmToAgeDateArry = new Date[2];
		// Get pSoundexLname Soundex Function 1022
		pSoundexLname = getPSoundexLname(offenders.getLastName());
		offenders.setLastNameSoundex(pSoundexLname);
		try {
			if (offenders.getSearchType() != null) {
				if (offenders.getBirthYear() != null) {
					offenders.setBirthRange(offenders.getBirthRange() != null ? offenders.getBirthRange() : 2);
					// get_date_range Procedure 1028
					lvFrToDateArry = getDateRange(offenders.getBirthYear(), offenders.getBirthRange());
					lvFromDate = lvFrToDateArry[0];
					lvToDate = lvFrToDateArry[1];
					offenders.setLvFromDate(lvFromDate);
					offenders.setLvToDate(lvToDate);
				}
				if (offenders.getAgeDate() != null) {
					offenders.setAgeRange(offenders.getAgeRange() != null ? offenders.getAgeRange() : 2);
					// get_age_date_range 1036
					lvFrmToAgeDateArry = getAgeDateRange(offenders.getAgeDate(), offenders.getAgeRange());
					lvFromAgedate = lvFrmToAgeDateArry[0];
					lvToAgedate = lvFrmToAgeDateArry[1];
					offenders.setLvFromAgedate(lvFromAgedate);
					offenders.setLvToAgedate(lvToAgedate);
				}
				if ("P".equals(offenders.getSearchType())) {
					// This method is used to get offenders list if
					// "P".equals(offenders.getSearchType()
					offList = tagSearchRepository.getPartialRecordsSelectOperation(offenders);
				} else if ("S".equals(offenders.getSearchType())) {
					// This method is used to get offenders list else
					// "S".equals(offenders.getSearchType()
					offList = tagSearchRepository.getPartialRecordsSelectOperationOne(offenders);
				}
			}
		} catch (Exception e) {
			logger.error("GetPartialRecords", e);
		}
		return offList;
	}

	@Override
	public Date[] getAgeDateRange(final Date ageDate, final Integer ageRange) {
		Date[] retArray = new Date[2];
		Integer vFromDate = null;
		Integer vToDate = null;
		String frmStrg = null;
		String toStrg = null;
		Calendar cal = Calendar.getInstance();

		try {
			cal.setTime(ageDate);
			vFromDate = cal.get(Calendar.YEAR) - ageRange;
			frmStrg = new SimpleDateFormat("dd/MM").format(ageDate).concat("/").concat(vFromDate.toString());
			retArray[0] = new SimpleDateFormat("dd/MM/yyyy").parse(frmStrg);

			vToDate = cal.get(Calendar.YEAR) + ageRange;
			toStrg = new SimpleDateFormat("dd/MM").format(ageDate).concat("/").concat(vToDate.toString());
			retArray[1] = new SimpleDateFormat("dd/MM/yyyy").parse(toStrg);

		} catch (Exception e) {
			logger.error("getAgeDateRange", e);
		}
		return retArray;
	}

	@Override
	public Date[] getDateRange(final String birthYear, final Integer birthRange) {
		Date[] retArray = new Date[2];
		String frmStrg = null;
		String toStrg = null;

		try {
			if (birthYear != null && birthRange != null) {
				frmStrg = (birthYear != null ? birthYear : "1850").concat("-01-01");
				LocalDate startDate = LocalDate.parse(frmStrg).minusYears(birthRange);
				Date fromDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				retArray[0] = fromDate;

				toStrg = (birthYear != null ? birthYear : "2050").concat("-12-31");
				LocalDate endDate = LocalDate.parse(toStrg).plusYears(birthRange);
				Date toDate1 = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				retArray[1] = toDate1;
			}

		} catch (Exception e) {
			retArray[0] = new GregorianCalendar(1850, Calendar.JANUARY, 01).getTime();
			retArray[1] = new GregorianCalendar(2050, Calendar.DECEMBER, 31).getTime();
			logger.error("getDateRange", e);
		}
		return retArray;
	}

	private String getPSoundexLname(final String lastName) {
		char[] x = lastName.toUpperCase().toCharArray();

		char firstLetter = x[0];

		// RULE [ 2 ]
		// Convert letters to numeric code
		for (int i = 0; i < x.length; i++) {
			switch (x[i]) {
			case 'B':
			case 'F':
			case 'P':
			case 'V': {
				x[i] = '1';
				break;
			}

			case 'C':
			case 'G':
			case 'J':
			case 'K':
			case 'Q':
			case 'S':
			case 'X':
			case 'Z': {
				x[i] = '2';
				break;
			}

			case 'D':
			case 'T': {
				x[i] = '3';
				break;
			}

			case 'L': {
				x[i] = '4';
				break;
			}

			case 'M':
			case 'N': {
				x[i] = '5';
				break;
			}

			case 'R': {
				x[i] = '6';
				break;
			}

			default: {
				x[i] = '0';
				break;
			}
			}
		}

		// Remove duplicates
		// RULE [ 1 ]
		String output = "" + firstLetter;

		// RULE [ 3 ]
		for (int i = 1; i < x.length; i++)
			if (x[i] != x[i - 1] && x[i] != '0')
				output += x[i];

		// RULE [ 4 ]
		// Pad with 0's or truncate
		output = output + "0000";
		return output.substring(0, 4);
	}

//-- Procedure Populate Offender Details 
	@Override
	public List<TagSearchPopulateOffDetails> populateOffDetails(final TagSearchPopulateOffDetails searchBean) {
		Long lvInstBookId = null;
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		final List<TagSearchPopulateOffDetails> lListObj = new ArrayList<TagSearchPopulateOffDetails>();
		Long pOffenderBookId = null;
		String pPrisonLocation = searchBean.getPPrisonLocation();
		String pCommunityOfficer = searchBean.getPCommunityOfficer();
		String pPrisonStatus = searchBean.getPPrisonStatus();
		String pCommunityStatus = searchBean.getPCommunityStatus();

		// Long pOffenderBookId = searchBean.getPOffenderBookId().longValue();
		if (searchBean.getPOffenderBookId() != null) {
			pOffenderBookId = searchBean.getPOffenderBookId().longValue();
		}
		String pAddress = searchBean.getPAddress();

		try {
			// This methos is used to get prison_details_cur
			OffenderBookings prisonDetailCur = tagSearchRepository.getPrisonDetailsCur(searchBean.getPRootOffenderId());
			String prisonLocation = subStr(getPrisonLocation(prisonDetailCur.getAgyLocId(),
					prisonDetailCur.getLivingUnitId(), prisonDetailCur.getAgencyImlId()), 0, 105);
			pOffenderBookId = prisonDetailCur.getOffenderBookId();
			pPrisonLocation = prisonLocation;
			pPrisonStatus = prisonDetailCur.getPrisonStatus();
			pAddress = prisonDetailCur.getAddress();

			if (prisonDetailCur == null) {
				// This methos is used to get prison_details_inactive_cur
				OffenderBookings prisonDetails_InactiveCur = tagSearchRepository
						.prisonDetailsInactiveCur(searchBean.getPRootOffenderId());
				String prisonInactiveLocation = subStr(getPrisonLocation(prisonDetails_InactiveCur.getAgyLocId(),
						prisonDetails_InactiveCur.getLivingUnitId(), prisonDetails_InactiveCur.getAgencyImlId()), 0,
						105);
				pOffenderBookId = prisonDetails_InactiveCur.getOffenderBookId();
				pPrisonLocation = prisonInactiveLocation;
				pPrisonStatus = prisonDetails_InactiveCur.getPrisonStatus();
				pAddress = prisonDetails_InactiveCur.getAddress();
			}

			lvInstBookId = pOffenderBookId;
			// This methos is used to get community_details_cur
			OffenderBookings commuDetailsCur = tagSearchRepository
					.getCommunityDetailsCur(searchBean.getPRootOffenderId());
			String offenderManager = getAgencyDescp(commuDetailsCur.getCommunityAgyLocId());// value of
																							// p_community_officer
			pOffenderBookId = commuDetailsCur.getOffenderBookId();
			pCommunityOfficer = offenderManager;
			pCommunityStatus = commuDetailsCur.getCommStatus();
			pAddress = commuDetailsCur.getAddress();

			if (pOffenderBookId == null) {
				pOffenderBookId = lvInstBookId;
			}
			if (commuDetailsCur == null) {
				pOffenderBookId = null;
				pCommunityOfficer = null;
				pCommunityStatus = null;
			}

			returnMap.put("P_OFFENDER_BOOK_ID", pOffenderBookId);
			returnMap.put("P_PRISON_LOCATION", pPrisonLocation);
			returnMap.put("P_PRISON_STATUS", pPrisonStatus);
			returnMap.put("P_ADDRESS", pAddress);
			returnMap.put("P_COMMUNITY_OFFICER", pCommunityOfficer);
			returnMap.put("P_COMMUNITY_STATUS", pCommunityStatus);

			final TagSearchPopulateOffDetails bean = new TagSearchPopulateOffDetails();
			bean.setPAddress(returnMap.get("P_ADDRESS") != null ? String.valueOf(returnMap.get("P_ADDRESS")) : null);
			bean.setPOffenderBookId(returnMap.get("P_OFFENDER_BOOK_ID") != null
					? new BigDecimal(String.valueOf(returnMap.get("P_OFFENDER_BOOK_ID")))
					: null);
			bean.setPCommunityStatus(
					returnMap.get("P_COMMUNITY_STATUS") != null ? String.valueOf(returnMap.get("P_COMMUNITY_STATUS"))
							: null);
			bean.setPPrisonStatus(
					returnMap.get("P_PRISON_STATUS") != null ? String.valueOf(returnMap.get("P_PRISON_STATUS")) : null);
			bean.setPCommunityOfficer(
					returnMap.get("P_COMMUNITY_OFFICER") != null ? String.valueOf(returnMap.get("P_COMMUNITY_OFFICER"))
							: null);
			bean.setPPrisonLocation(
					returnMap.get("P_PRISON_LOCATION") != null ? String.valueOf(returnMap.get("P_PRISON_LOCATION"))
							: null);
			lListObj.add(bean);

		} catch (Exception e) {
			logger.error("populateOffDetails", e);
		}
		return lListObj;
	}

	private String getAgencyDescp(final String pAgyLocId) {
		return tagSearchRepository.getAgyDescription(pAgyLocId);
	}

	private String getPrisonLocation(final String pAgyLocId, final BigDecimal pLivUnitId, final BigDecimal pAgyImlId) {

		String lvLocDescription = null;
		String lvReturnString = null;
		lvLocDescription = tagSearchRepository.getAgyDescription(pAgyLocId);
		if (pLivUnitId != null) {
			lvReturnString = pAgyLocId + " [" + intLocPath(pLivUnitId.intValue()) + "]";
			if (pAgyImlId != null)
				lvReturnString = lvReturnString + "-[" + intLocPath(pAgyImlId.intValue()) + "]";
		} else {
			lvReturnString = lvLocDescription;
		}
		return lvReturnString;
	}

	private String intLocPath(final Integer pInternalLocationId) {
		String lvDesc;
		Integer lvParentIntLocId;
		List<AgencyInternalLocations> objList = tagSearchRepository.getInternalLocId(pInternalLocationId);
		AgencyInternalLocations obj = objList.get(0);
		lvDesc = obj.getInternalLocationCode();
		lvParentIntLocId = obj.getParentInternalLocationId();

		if (lvParentIntLocId == null)
			return lvDesc;
		else
			return intLocPath(lvParentIntLocId) + "-" + lvDesc;
	}

	@Override
	@Transactional
	public Integer deleteCourseActivityAreas(final Long crsActyId,String modifyUserId) {
		return tagSearchRepository.deleteCourseActivityAreasDeleteOperation(crsActyId,modifyUserId);
	}

	@Override
	public String getCourseActivityAreaDesc(final String areaCode) {
		List<Areas> areaCoAndCls = null;
		String areaCodDesce = null, areaClass = null, areaTypeDesc = null;

		try {
			areaCoAndCls = tagSearchRepository.cArea(areaCode);

			for (final Areas areas : areaCoAndCls) {
				areaCodDesce = areas.getDescription();
				areaClass = areas.getAreaClass();
			}
			if (areaClass != null) {
				areaTypeDesc = omsMiscellaneousService.getDescCode(areaClass);
			}
		} catch (Exception e) {
			logger.error("getCourseActivityAreaDesc", e);
		}
		if (areaTypeDesc == null) {
			return TRUE;
		} else {
			return areaTypeDesc;
		}
	}

}