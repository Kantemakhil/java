package net.syscon.s4.pkgs.non_association.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.movements.beans.OffRec;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.pkgs.non_association.NonAssociationRepository;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_core.TagCoreService;

/*
 * Below comments are copied from GET_NON_ASSOCIATIONS function
-- (Please modify the vcp_version with proper version number and modification date when
--  this package is being modified.)
-- ====================================================================================
   vcp_version   CONSTANT VARCHAR2 (60) := '2.24 03-DEC-2012';

-- ====================================================================================
 MODIFICATION HISTORY

 (Please put version history in a reverse-chronological order below)

 Purpose: Package containing the procedure for checking NON_ASSOCIATION and SECURITY.
 --------------------
 Person        Date                         Comments
 ---------   ---------    --------------------------------------------------------------------------
 Patrick     03-DEC-2012  2.24 Added procedure chk_na_prg_srv_conflict_rt, get_na_prg_srv_realtime, add_to_program_list, remove_from_program_list, clear_temp_list and get_temp_count
                                            For checking non-association with Programs
 Bahadur     12-OCT-2012  HPQC#15842. Changes made in chk_na_prg_conflict, get_na_prg, chk_na_prg_srv_conflict, get_na_prg_srv procedures.
 Kuldeep     20-SEP-2012  HPQC#15842. Added procedure chk_na_prg_conflict,get_na_prg for screen OCDXPROG
 Rose        28-AUG-2010  HPQC#152: Added procedure chk_na_between_offenders. When adding an offender as an authorized visitor,
                              check non-association between the offender and the visitor offender.
 Johnny/Sarah07/Nov/2009  QC#1806
 Sarah       28/Oct/2009  QC#1696: Added get_na_vst_sch, get_stg_vst_sch, chk_na_vst_sch_conflict for OIDVISIT to check non-association validation.
 Sarah       09/Jan/2009  Tr#7875:Modified check_stg_non_association and get_stg_non_ass_msg to retrieve
                          active booking first if not then max offender_book_id. The reason for that is to
                          be able to check non association for admission screen and Transfer screen which
                          offender active flag is N.
 Sarah       08/Jan/2009  Tr#7875: Added get_stg_non_ass_msg to message STG non association in OMUAVBED Screen
 Johnny      31/Dec/2008  Link offender_bookings and offenders with extra condition for retrieving correct name.
 Johnny      30/Dec/2008  Changed join for offender_bookings and offenders with root_offender_id in get_na_prg_srv
 Johnny      23/Dec/2008  Fixed get_na_prg_srv for retrieve only working name.
 Johnny      16/Dec/2008  Added 3 procedures for OCISCATA for non-association validation.
                           1) get_na_prg_srv
                           2) get_stg_na_prg_srv
                           3) chk_na_prg_srv_conflict
 Sarah       30/Apr/2008  Used p_off_info as IN and OUT parameter.
 Sarah       29/Apr/2008  Added following func/proc to check non association for OIDBSIAP:
                           a) get_na_int_sch
                           b) get_stg_int_sch
                           c) chk_na_int_sch_conflict
                           d) get_na_int_realtime
 Sarah       24/Apr/2008  Added following func/proc
                           a) get_conflict_warning to display conflict message
                           b) get_na_int_realtime to check non association between
                              2 offenders before committing into DB for Internal location.
                           c) Modified get_na_int_loc, get_stg_na_int_loc, chk_na_int_loc_conflict
                              to pass the list of offenders and work with conflict warning proc
 Sarah       18/Apr/2008  Added following func/proc to develop the mechanism to
                           check non association including stg for internal location:
                           a) check_restric_parent_loc
                           b) check_stg_restric_parent_loc
                           c) get_na_int_loc
                           d) get_stg_na_int_loc
                           e) chk_na_int_loc_conflict (used in OIDINTMV.fmb)
 Sarah       31/Jan/2008  Added check_stg_non_association and modified check _non_association
                          to call and handle STG too.
 GJC         14/10/2006   Remove DBMS_OUTPUT calls
 Neil        02/03/2006   Version 2.5. Rewrote check_non_association. Added get_non_associations
 Krishna     25-Jul-2005  Tr+674: Modified declaration of variable in check_non_association function. It was referring to
                          v_liv_unit view which is no longer existing.
 Patrick     18-AUG-2005  T+ 676 Rewrite check_security, check_non_association, and get_count_for_liv_unit for new data model changes.
 Patrick     23-AUG-2005  T+ 676 Rewrite check_security, for new data model changes.
 Patrick     25-AUG-2005  T+ 676 Changed version number.
 Rajshree    22-NOV-2005  T+ 860 Added function chk_na_conflict for checking if non-association exists between the offender ,At present
                          this fuction is used in OIDSCMOV(Schedule court movement)
 Girish      29-JAN-2003  Tracking # 15162 : Modified procedure destroy_offender_tab.
 Simanta     15/07/2002   Following procedures have been added to the package to manipulate a PL-SQL table which will
                          be used by OIDSCEXM  form module :
                           a)destry_offender_tab,
                           b)is_offender_exists
                           c)remove_offender_from_the_list
 Simanta     25/04/2001 Added following database objects to develop the mechanism for
                        capacity checking in OIDCHOLO  :
                        a) TWO PL-SQL TABLES.
                        b) PROCEDURE DESTRY_PL_SQL_TABLES TO RE-SET THEM
                        c) PROCEDURE SET_CURR_ASS_COUNT TO STORE CURRENT ASSIGNMENT IN THE TABLE USING LIVING_UNIT_ID
                           AS AN INDEX.
                        d) FUNCTION GET_CURR_ASS_COUNT TO GET THE VALUE FROM THE PL-SQL TABLE
                           STORED AGAINST LIVING_UNIT_ID.
 Simanta     16/05/2001 Removed the defination of the functions/procedures that are no longer being used any where.
                        Modified the functions check_non_association,check_security to address some problems.
 Simanta     13/06/2001 Modified the check_non_association procedure to use root_offender_id instead of offender_id
                          to find non associative offenders records.
 Joe Wong    09/11/2000 Adds SHOW_VERSION procedure for Package Versioning..
 Simanta     12/19/2000 Modified the procedure CHECK_NON_ASSOCIATION
                        to avoid self non-associativity.
 Simanta     12/22/2000 Modified the procedure CHECK_SECURITY.


 */
@Service
public class NonAssociationServiceImpl implements NonAssociationService {

	@Autowired
	private NonAssociationRepository nonAssociationRepository;

	@Autowired
	private TagCoreService tagCoreService;

	private static final String Y = "Y";
	private static final String N = "N";
	private List<String> offenderTab = new ArrayList<>();
	private Integer lvOffTabIndex = 0;

	private static Logger logger = LogManager.getLogger(NonAssociationRepository.class.getName());

	@Override
	@Transactional
	public String chkNaIntLocConflict(final Long pOffenderId, final Long pOffenderBookId,
			final Integer pInternalLocId) {
		String lvConflictFlag = null;
		try {
			final Map<String, Object> outParams = new HashMap<String, Object>();
			List<Offenders> offListOne = new ArrayList<Offenders>();
			List<Offenders> offListTwo = new ArrayList<Offenders>();
			String lvStgConflictFlag = null;
			String lvNaConflictFlag = null;
			Boolean lvNaFlag = null;
			Boolean lvStgFlag = null;
			try {
				// get_na_int_loc(p_offender_id, p_internal_loc_id, lv_off_info, lv_na_flag);
				// 1118
				offListOne = getNaIntLoc(pOffenderId, pInternalLocId);
				if (!offListOne.isEmpty()) {
					lvNaFlag = true;
				}

				if (lvNaFlag) {
					lvNaConflictFlag = Y;
				} else {
					lvNaConflictFlag = N;
				}
				// get_stg_na_int_loc(p_offender_book_id, p_internal_loc_id, lv_off_info,
				// lv_stg_flag); 1127
				offListTwo = getStgNaIntLoc(pOffenderBookId, pInternalLocId);

				if (!offListTwo.isEmpty()) {
					lvStgFlag = true;
				}
				if (lvStgFlag) {
					lvStgConflictFlag = Y;
				} else {
					lvStgConflictFlag = N;
				}
				if (N.equals(lvStgConflictFlag) && N.equals(lvNaConflictFlag)) {
					lvConflictFlag = N;
				} else {
					lvConflictFlag = Y;
				}
				outParams.put("P_CONFLICT_FLAG", lvConflictFlag);
				outParams.put("P_OFF_INFO", offListOne);
				outParams.put("P_OFF_INFO", offListTwo);

			} catch (Exception e) {
				logger.error("chkNaIntLocConflict", e);
				lvConflictFlag = N;
			}
		} catch (Exception e) {
			logger.error("Exeption in chkNaIntLocConflict ", e);
		}
		return lvConflictFlag;
	}

	@Override
	public List<Offenders> getStgNaIntLoc(final Long pOffenderBookId, final Integer pInternalLocId) {
		final List<Offenders> offOutList = new ArrayList<Offenders>();
		try {
			List<AgyIntLocProfiles> agyIntLoPr = new ArrayList<AgyIntLocProfiles>();
			List<Offenders> offenList = new ArrayList<Offenders>();
			// get_parent_ns_type_loc(p_internal_loc_id, 'Y');
			agyIntLoPr = getParentNsTypeLoc(pInternalLocId, Y);
			if (agyIntLoPr.size() > 0) {
				for (int i = 0; i < agyIntLoPr.size(); i++) {
					// check_enemy_cur cursor
					offenList = nonAssociationRepository.checkEnemyCur(agyIntLoPr.get(i).getInternalLocationId(), "STG",
							pOffenderBookId);
					for (final Offenders offndrs : offenList) {
						final Offenders off = new Offenders();
						off.setOffenderIdDisplay(offndrs.getOffenderIdDisplay());
						off.setLastName(offndrs.getLastName());
						off.setFirstName(offndrs.getFirstName());
						offOutList.add(off);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exeption in getStgNaIntLoc ", e);
		}
		// if condition is moved to parent method
		return offOutList;
	}

	@Override
	public List<Offenders> getNaIntLoc(final Long pOffenderId, final Integer pInternalLocId) {
		final List<Offenders> offOutList = new ArrayList<Offenders>();
		try {
			List<AgyIntLocProfiles> agyIntLoPr = new ArrayList<AgyIntLocProfiles>();
			List<Offenders> offenList = new ArrayList<Offenders>();
			// get_parent_ns_type_loc(p_internal_loc_id, 'N'); 989
			agyIntLoPr = getParentNsTypeLoc(pInternalLocId, N);
			if (agyIntLoPr.size() > 0) {
				for (int i = 0; i < agyIntLoPr.size(); i++) {
					// non_ass_cur Cursor 995
					offenList = nonAssociationRepository.nonAssCur(agyIntLoPr.get(i).getInternalLocationId(),
							agyIntLoPr.get(i).getIntLocProfileCode(), pOffenderId);
					for (final Offenders offndrs : offenList) {
						final Offenders off = new Offenders();
						off.setOffenderIdDisplay(offndrs.getOffenderIdDisplay());
						off.setLastName(offndrs.getLastName());
						off.setFirstName(offndrs.getFirstName());
						offOutList.add(off);
					}
				}
			}
			// if condition is moved to parent method
		} catch (Exception e) {
			logger.error("Exeption in getNaIntLoc ", e);
		}
		return offOutList;
	}

	@Override
	public List<AgyIntLocProfiles> getParentNsTypeLoc(final Integer pInternalLocId, final String flag) {
		final List<AgyIntLocProfiles> tNsParentType = new ArrayList<AgyIntLocProfiles>();
		try {
			Integer parentTypeInd = 1;
			Boolean lTypeFound = false;
			// parent_location_cur Cursor
			List<AgencyInternalLocations> agyIntLocList = new ArrayList<>();
			List<AgyIntLocProfiles> agyIntLocProList = new ArrayList<AgyIntLocProfiles>();
			agyIntLocList = nonAssociationRepository.parentLocationCur(pInternalLocId, flag);
			for (final AgencyInternalLocations agyInLoc : agyIntLocList) {
				// na_type_cur Cursor
				agyIntLocProList = nonAssociationRepository.naTypeCur(agyInLoc.getInternalLocationId(), flag);
				for (final AgyIntLocProfiles agyIntLocPro : agyIntLocProList) {
					if (tNsParentType.size() > 0) {
						for (int i = 0; i < tNsParentType.size(); i++) {
							if (tNsParentType.get(i).getIntLocProfileCode() == agyIntLocPro.getIntLocProfileCode()) {
								tNsParentType.get(i).setInternalLocationId(agyIntLocPro.getInternalLocationId());
								lTypeFound = true;
							}
						}
						if (!lTypeFound) {
							tNsParentType.get(parentTypeInd).setIntLocProfileCode(agyIntLocPro.getIntLocProfileCode());
							tNsParentType.get(parentTypeInd)
									.setInternalLocationId(agyIntLocPro.getInternalLocationId());
							parentTypeInd = parentTypeInd + 1;
						}
						lTypeFound = false;
					} else {
						final AgyIntLocProfiles agyInPro = new AgyIntLocProfiles();
						agyInPro.setIntLocProfileCode(agyIntLocPro.getIntLocProfileCode());
						agyInPro.setInternalLocationId(agyIntLocPro.getInternalLocationId());
						tNsParentType.add(agyInPro);
						parentTypeInd = parentTypeInd + 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exeption in getParentNsTypeLoc ", e);
		}
		return tNsParentType;
	}

	@Override
	public Map<String, Object> chkNaPrgSrvConflictRt(final ProgramsNonAssocTmp offPrg, final String coursePhase) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			boolean lvNaFlag;
			String lvNaConflictFlag = null;
			String lvStgConflictFlag = null;
			String lvConflictFlag = null;
			List<OffRec> lvOffInfo = new ArrayList<OffRec>();
			boolean lvStgFlag;
			String pConflictFlag = null;
			List<OffRec> pOffInfo = new ArrayList<OffRec>();
			final Map<String, Object> mpReaTime = getNaPrgSrvRealtime(offPrg.getOffenderId().longValue(),
					new BigDecimal(coursePhase).longValue(), null, null);
			lvNaFlag = (boolean) mpReaTime.get("P_NA_FLAG");
			lvOffInfo = (List<OffRec>) mpReaTime.get("P_OFF_INFO");
			if (lvNaFlag) {
				lvNaConflictFlag = Y;
			} else {
				lvNaConflictFlag = N;
			}
			final Map<String, Object> mapGetStgNaPrgSrv = getStgNaPrgSrv(offPrg.getOffenderId().longValue(),
					new BigDecimal(coursePhase).longValue(), lvOffInfo);
			lvStgFlag = (boolean) mapGetStgNaPrgSrv.get("P_STG_FLAG");
			lvOffInfo = (List<OffRec>) mapGetStgNaPrgSrv.get("P_OFF_INFO");
			if (lvStgFlag) {
				lvStgConflictFlag = Y;
			} else {
				lvStgConflictFlag = N;
			}

			if (lvStgConflictFlag.equals(N) && lvNaConflictFlag.equals(N)) {
				lvConflictFlag = N;
			} else {
				lvConflictFlag = Y;
			}
			pConflictFlag = lvConflictFlag;
			pOffInfo = lvOffInfo;
			returnMap.put("P_OFF_INFO", pOffInfo);
			returnMap.put("P_CONFLICT_FLAG", pConflictFlag);
		} catch (Exception e) {
			logger.error("Exeption in chkNaPrgSrvConflictRt ", e);
		}
		return returnMap;
	}

	@Override
	public Map<String, Object> getNaPrgSrvRealtime(final Long pOffenderId, final Long pCrsActyId,
			final Date pOffStartDate, final Date pOffenderEndDate) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			boolean lvNaFlag = false;
			List<Offenders> lvOffInfo = new ArrayList<Offenders>();
			int pOffInfoCount;
			final List<Offenders> pOffInfo = new ArrayList<Offenders>();
			boolean pNaFlag;
			lvOffInfo = nonAssociationRepository.getNonAssCur(pOffenderId, pCrsActyId, pOffStartDate, pOffenderEndDate);
			if (lvOffInfo.size() > 0) {
				lvNaFlag = true;
				pOffInfoCount = pOffInfo.size();
				for (final Offenders offenders : lvOffInfo) {
					pOffInfo.add(offenders);
					pOffInfoCount = pOffInfoCount++;
				}
			} else {
				lvNaFlag = false;
			}
			pNaFlag = lvNaFlag;
			returnMap.put("P_OFF_INFO", pOffInfo);
			returnMap.put("P_NA_FLAG", pNaFlag);
		} catch (Exception e) {
			logger.error("Exeption in getNaPrgSrvRealtime ", e);
		}
		return returnMap;
	}

	@Override
	public Map<String, Object> getStgNaPrgSrv(final Long pOffenderBookId, final Long pCrsActyId,
			final List<OffRec> listOffInfoType) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			boolean lvStgFlag;
			List<OffRec> lvOffInfo = new ArrayList<OffRec>();
			int pOffInfoCount;
			// List<Offenders> pOffInfo = new ArrayList<Offenders>();
			boolean pStgFlag;
			lvOffInfo = nonAssociationRepository.getGetStgNaPrgSrv(pOffenderBookId, pCrsActyId);
			if (lvOffInfo.size() > 0) {
				lvStgFlag = true;
				pOffInfoCount = listOffInfoType.size();
				for (final OffRec offenders : lvOffInfo) {
					listOffInfoType.add(offenders);
					pOffInfoCount = pOffInfoCount++;
				}
			} else {
				lvStgFlag = false;
			}
			pStgFlag = lvStgFlag;
			returnMap.put("P_OFF_INFO", listOffInfoType);
			returnMap.put("P_STG_FLAG", pStgFlag);
		} catch (Exception e) {
			logger.error("Exeption in getStgNaPrgSrv ", e);
		}
		return returnMap;

	}

	@Override
	public Map<String, Object> getConflictWarning(final Long offenderId, final List<OffRec> pOffInfo) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		try {
			String pWarningMsg = null;
			String pWarningPrompt = null;
			String lvWarningMsg = null;
			Offenders offDeRec = new Offenders();
			final OffRec lvOffRec = new OffRec();
			List<OffRec> lvOffInfo = new ArrayList<OffRec>();
			offDeRec = tagCoreService.getOffDetails(offenderId);
			lvOffRec.setFirst_name(offDeRec.getFirstName());
			lvOffRec.setLast_name(offDeRec.getLastName());
			lvOffRec.setOffender_id_display(offDeRec.getOffenderIdDisplay());
			lvOffInfo = pOffInfo;
			lvWarningMsg = addMsg(lvWarningMsg,
					"A non-association linkage exists between ".concat(formatOffRec(lvOffRec)).concat(" and ")
							.concat(formatOffList(lvOffInfo)).concat(" in this location."));
			if (lvWarningMsg != null) {
				if (lvWarningMsg.length() > 4000) {
					lvWarningMsg = lvWarningMsg.substring(1, 3999);
				}
				pWarningMsg = lvWarningMsg;
				pWarningPrompt = "Before proceeding with location change investigate possible risk."
						+ " Only proceed if you are satisfied with the risk. Do you wish to proceed ?";
			}
			outParams.put("P_WARNING_MSG", pWarningMsg);
			outParams.put("P_WARNING_PROMPT", pWarningPrompt);
		} catch (Exception e) {
			logger.error("Exeption in getConflictWarning ", e);
		}
		return outParams;
	}

	@Override
	public String addMsg(String pMsg, final String pAddMsg) {
		try {
			if (pMsg != null) {
				pMsg = pMsg.concat(pAddMsg);
			} else {
				pMsg = pAddMsg;
			}
		} catch (Exception e) {
			logger.error("Exeption in addMsg ", e);
		}
		return pMsg;
	}

	@Override
	public String formatOffList(final List<OffRec> lvOffInfo) {
		String lvOffList = "";
		try {
			for (final OffRec off : lvOffInfo) {
				if (lvOffList != null) {
					lvOffList = lvOffList.concat(";");
				}
				lvOffList = lvOffList.concat(formatOffRec(off));
			}
		} catch (Exception e) {
			logger.error("Exeption in formatOffList ", e);
		}
		return lvOffList;
	}

	@Override
	public String formatOffRec(final OffRec lvOffRec) {
		String returnString = null;
		try {
			returnString = lvOffRec.getLast_name().concat(",").concat(lvOffRec.getFirst_name()).concat(" ")
					.concat(lvOffRec.getOffender_id_display());
		} catch (Exception e) {
			logger.error("Exeption in formatOffRec ", e);
		}
		return returnString;
	}

	@Override
	public Integer getCountForLivUnit(final ReserveBedLocations bean) {
		Integer returnCnt = 0;
		try {
			Integer lvObCnt;
			Integer lvRbdCnt;
			// 381 get_off_bkg_cnt_cur
			lvObCnt = nonAssociationRepository.getOffBkgCntCur(bean.getLivingUnitId().intValue());
			lvRbdCnt = nonAssociationRepository.getRevBedCntCur(bean.getLivingUnitId().intValue());
			returnCnt = (lvObCnt != null ? lvObCnt : 0) + (lvRbdCnt != null ? lvRbdCnt : 0);
		} catch (Exception e) {
			logger.error("Exeption in getCountForLivUnit ", e);
		}
		return returnCnt;
	}

	@Override
	public Integer addToProgramList(final VOffenderPrgObligations vOff, final String userName) {
		Integer count = 0;
		Long lvSeq = 0L;
		try {
			lvSeq = nonAssociationRepository.getLastSeq();
			nonAssociationRepository.insertProgramNonAssocTemp(lvSeq, vOff.getOffenderId(), vOff.getOffenderBookId(),
					vOff.getProgramId(), null, userName);
			count = 1;
		} catch (Exception e) {
			count = 0;
			logger.error("Exeption in addToProgramList ", e);
		}
		return count;
	}

	// This method is used for retrieving multiple offender details
	@Override
	public List<Offenders> getNonAssociations(final BigDecimal offId, final BigDecimal livingUnitId) {
		// CURSOR na_cur
		final List<Offenders> retList = new ArrayList<Offenders>();
		try {
			List<Offenders> ofenList = new ArrayList<Offenders>();
			Boolean chkConflict = false;
			ofenList = nonAssociationRepository.naCurSelectOperation(offId);// ok
			for (final Offenders off : ofenList) {
				if (livingUnitId.compareTo(new BigDecimal(off.getOffenderId())) == 0) {
					chkConflict = chkNaLivUnitConflict(livingUnitId, off.getOffenderId(), off.getNameType());
					if (chkConflict == true) {
						final Offenders offdrs = new Offenders();
						offdrs.setOffenderIdDisplay(off.getOffenderIdDisplay());
						offdrs.setLastName(off.getLastName());
						offdrs.setFirstName(off.getFirstName());
						retList.add(offdrs);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exeption in getNonAssociations ", e);
		}
		return retList;
	}

	@Override
	public Boolean chkNaLivUnitConflict(final BigDecimal livingUnitId, final Long offenderId, final String nameType) {
		Boolean returnVal = false;
		try {
			returnVal = nonAssociationRepository.chkNaLivUnitConflict(livingUnitId, offenderId, nameType);
		} catch (Exception e) {
			logger.error("Exeption in chkNaLivUnitConflict ", e);
		}
		return returnVal;
	}

	@Override
	public Map<String, Object> getStgNonAssMsg(final BigDecimal livingUnitId, final BigDecimal offId,
			String lvEnmFlag) {
		final Map<String, Object> outParam = new HashMap<String, Object>();
		try {
			BigDecimal offBookId = null;
			BigDecimal parentLocationId = null;
			Integer lvNaCount = 0;
			List<Offenders> checkEnemyCurList = new ArrayList<Offenders>();
			final List<Offenders> retList = new ArrayList<Offenders>();
			List<AgencyInternalLocations> agInteLocList = new ArrayList<AgencyInternalLocations>();
			if (offId != null) {
				// l_offender_cur Cursor
				offBookId = nonAssociationRepository.offenderCur(offId);// ok
			}
			if (offBookId != null) {
				// l_parent_location_cur
				agInteLocList = nonAssociationRepository.parentLocationCur(livingUnitId);// ok
				for (final AgencyInternalLocations agyInLoc : agInteLocList) {
					// l_restrictions_cur
					parentLocationId = nonAssociationRepository.restrictionsCur(agyInLoc.getInternalLocationId());// ok
				}
				if (parentLocationId != null) {
					// l_check_enemy_cur
					checkEnemyCurList = nonAssociationRepository.checkEnemyCur(offBookId, parentLocationId);// ok
					for (final Offenders offenders : checkEnemyCurList) {
						final Offenders off = new Offenders();
						off.setOffenderIdDisplay(offenders.getOffenderIdDisplay());
						off.setLastName(offenders.getLastName());
						off.setFirstName(offenders.getFirstName());
						retList.add(off);
						lvNaCount = lvNaCount + 1;
					}

					if (lvNaCount > 0) {
						lvEnmFlag = "Y";
					} else {
						lvEnmFlag = "N";
					}
				} else {
					lvEnmFlag = "N";
				}
			} else {
				lvEnmFlag = "N";
			}
			outParam.put("retList", retList);
			outParam.put("flag", lvEnmFlag);
		} catch (Exception e) {
			logger.error("Exeption in getStgNonAssMsg ", e);
		}
		return outParam;
	}

	@Override
	public Integer clearTempList(String modifyUserId) {
		Integer returnval = 0;
		try {
			returnval = nonAssociationRepository.deleteFmprogNonAssocTmp(modifyUserId);
		} catch (Exception e) {
			logger.error("Exeption in clearTempList ", e);
		}
		return returnval;
	}

	@Override
	public Integer removeFromProgramList(final VOffenderPrgObligations vOff) {
		Integer returnval = 0;
		try {
			returnval = nonAssociationRepository.programsNonAssocTmp(vOff);
		} catch (Exception e) {
			logger.error("Exeption in removeFromProgramList ", e);
		}
		return returnval;
	}

	@Override
	public String checkNonAssociation(final BigDecimal offenderId, final BigDecimal livingUnitId) {
		// CURSOR na_cur
		String lvReturn = null;
		try {
			List<Offenders> ofenList = new ArrayList<Offenders>();
			Boolean chkConflict = false;
			Boolean lvConflict = false;
			String lEnemyFoundFlag = null;
			String lv1Return = null;
			String lv2Return = null;

			ofenList = nonAssociationRepository.naCurSelectOperation(offenderId);
			for (final Offenders off : ofenList) {
				chkConflict = chkNaLivUnitConflict(livingUnitId, off.getOffenderId(), off.getNameType());
				if (chkConflict) {
					lvConflict = true;
				}
			}
			if (lvConflict) {
				lv1Return = N;
			} else {
				lv1Return = Y;
			}
			if (offenderId != null) {
				lEnemyFoundFlag = checkStgNonAssociation(livingUnitId, offenderId, lEnemyFoundFlag);
			}
			if (Y.equals(lEnemyFoundFlag)) {
				lv2Return = N;
			} else {
				lv2Return = Y;
			}
			if (Y.equals(lv2Return) && Y.equals(lv1Return)) {
				lvReturn = Y;
			} else {
				lvReturn = N;
			}
		} catch (Exception e) {
			logger.error("Exeption in checkNonAssociation ", e);
		}
		return lvReturn;
	}

	@Override
	public String checkStgNonAssociation(final BigDecimal livingUnitId, final BigDecimal offenderId,
			String lEnemyFoundFlag) {
		try {
			BigDecimal lOffenderBookId = null;
			Integer lvParntLocId = null;
			Integer lParentLocationId = null;
			List<AgencyInternalLocations> allRec = null;
			String flag = null;
			lOffenderBookId = nonAssociationRepository.lOffenderCur(offenderId);
			if (lOffenderBookId != null) {
				allRec = nonAssociationRepository.lParentLocationCur(livingUnitId); // this line is pending
				for (final AgencyInternalLocations result : allRec) {
					lvParntLocId = nonAssociationRepository.lRestrictionsCur(result.getInternalLocationId());
					if (lvParntLocId != null) {
						lParentLocationId = lvParntLocId;
					}
				}
				if (lParentLocationId != null) {
					flag = nonAssociationRepository.lCheckEnemyCur(lParentLocationId, lOffenderBookId);
					if (Y.equals(flag)) {
						lEnemyFoundFlag = Y;
					} else {
						lEnemyFoundFlag = N;
					}
				} else {
					lEnemyFoundFlag = N;
				}
			}
		} catch (Exception e) {
			logger.error("Exeption in checkStgNonAssociation ", e);
		}
		return lEnemyFoundFlag;
	}

	@Override
	public String checkSecurity(final BigDecimal offenderBookId, final BigDecimal livingUnitId) {
		List<OffenderAssessment> lvSupLevel = null;
		Integer lvPassSecurity = null;
		String retVal = null;
		try {
			lvSupLevel = nonAssociationRepository.getRevSupLevelCur(offenderBookId);
			if (lvSupLevel != null && lvSupLevel.size() > 0) {
				for (OffenderAssessment offenderAssessment : lvSupLevel) {
					if (offenderAssessment.getReviewSupLevelType() == null) {
						return Y;
					} else {
						lvPassSecurity = nonAssociationRepository.checkLivUnitSecurityCur(livingUnitId,
								offenderAssessment.getReviewSupLevelType());
						if (lvPassSecurity > 0) {
							retVal = Y;
						} else {
							retVal = N;
						}
					}
				}
				return retVal == null ? N : retVal;
			} else {
				retVal = Y;
			}
		} catch (Exception e) {
			logger.error("Exeption in checkSecurity ", e);
		}
		return retVal;
	}

	@Override
	public String chkNaBetweenOffenders(final Long pOffenderBookId, final Long pVisOffenderBookId) {
		String pConflictFlag = null;
		try {
			String lvNaFlag = N;
			String lvStgFlag = N;
			lvNaFlag = nonAssociationRepository.chkNaBetweenOffendersOne(pOffenderBookId, pVisOffenderBookId);
			lvStgFlag = nonAssociationRepository.chkNaBetweenOffendersTwo(pOffenderBookId, pVisOffenderBookId);
			if (Y.equals(lvNaFlag) || Y.equals(lvStgFlag)) {
				pConflictFlag = Y;
			} else {
				pConflictFlag = N;
			}
		} catch (Exception e) {
			logger.error("Exeption in chkNaBetweenOffenders ", e);
		}
		return pConflictFlag;
	}

	public Integer isOffenderExists(String pOffIdDisplay) {
		Boolean lvFoundflag = false;
		try {
			Integer lvTabindex = 1;
			String lvCurrValue = null;
			while (lvTabindex <= lvOffTabIndex && lvFoundflag == false) {
				try {
					if (offenderTab.get(lvTabindex) != null) {
						lvCurrValue = offenderTab.get(lvTabindex);
					}
				} catch (Exception e) {
					logger.fatal(e);
					lvCurrValue = "&*+";
				}
				if (lvCurrValue != null && lvCurrValue.equals(pOffIdDisplay)) {
					lvFoundflag = true;
				}
				lvTabindex++;
			}
			if (lvFoundflag == false) {
				lvOffTabIndex++;
				try {
					offenderTab.set(lvOffTabIndex, pOffIdDisplay);
				} catch (Exception e) {
					logger.fatal(e);
				}
			}
		} catch (Exception e) {
			logger.error("Exeption in isOffenderExists ", e);
		}
		return lvFoundflag ? 1 : 0;

	}

	@Override
	public List<OffenderNaDetails> getNonAssociationOffenderList(Integer offenderBookId) {
		List<OffenderNaDetails> offenderNaDetails = null;
		try {
			offenderNaDetails = nonAssociationRepository.getNonAssociationOffenderList(offenderBookId);
		} catch (Exception e) {
			logger.error("Exeption in getNonAssociationOffenderList ", e);
		}
		return offenderNaDetails;
	}

	@Override
	public List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(BigDecimal offenderBookId,
			BigDecimal livingUnitId, String livingUnitCode) {
		List<OffenderNaDetails> offenderNaDetailsList = null;
		try {
			List<AgyIntLocProfiles> agyIntLocProfilesList = null;
			if (offenderBookId != null && livingUnitId != null) {
				agyIntLocProfilesList = nonAssociationRepository.getNonAssociationConfigForLocation(livingUnitId);
			}
			if (offenderBookId != null && livingUnitCode != null) {
				agyIntLocProfilesList = nonAssociationRepository.getNonAssociationConfigForLocationCode(livingUnitCode);
			}
			if (agyIntLocProfilesList != null) {
				offenderNaDetailsList = nonAssociationRepository.getNonAssociationOffenders(offenderBookId,
						agyIntLocProfilesList);
			}
		} catch (Exception e) {
			logger.error("Exeption in checkNonAssociationOffendersWithLivingUnit ", e);
		}
		return offenderNaDetailsList;
	}

	@Override
	public List<OffenderStgAffiliations> getOffendersOfNonAssociationGroup(BigDecimal offenderBookId) {
		List<OffenderStgAffiliations> offenderStgAffiliations = null;
		try {
			offenderStgAffiliations = nonAssociationRepository.getOffendersOfNonAssociationGroup(offenderBookId);
		} catch (Exception e) {
			logger.error("Exeption in getOffendersOfNonAssociationGroup ", e);
		}
		return offenderStgAffiliations;
	}

	@Override
	public String checkAgyNonAssociation(Long offenderId, String agyLocId) {
		String lvReturn = "N";
		Boolean lvConflict = false;
		List<OffenderBookings> list = nonAssociationRepository.checkAgyNonAssociationNaCur(offenderId);
		for (OffenderBookings obj : list) {
			if (chkAgyConflict(agyLocId, obj.getOffenderBookId(), obj.getNsType())) {
				lvConflict = true;
				break;
			}
		}
		if (lvConflict) {
			lvReturn = "N";
		} else {
			lvReturn = "Y";
		}
		return lvReturn;
	}

	@Override
	public Boolean chkAgyConflict(String pAgyLoc, Long offenderBookId, String nsType) {
		String lvNsAgyLoc = nonAssociationRepository.chkAgyConflict(offenderBookId);
		if (lvNsAgyLoc.equals(pAgyLoc)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String checkAnyNonAssociation(Long offenderId) {
		int lvCount = 0;
		String lvReturn = null;
		try {
			List<OffenderBookings> getListValue = new ArrayList<OffenderBookings>();
			getListValue = nonAssociationRepository.lOffenderCur(offenderId);
			for (OffenderBookings ele : getListValue) {
				lvCount+= nonAssociationRepository.offNaDetCur(ele.getRootOffenderId().longValue());
				lvCount+= nonAssociationRepository.stgNonAssoCur(ele.getOffenderId());
			}
		} catch (Exception e) {
			logger.error("Exeption in checkAnyNonAssociation ", e);
		}
		if(lvCount>0) {
			lvReturn = "Y";
		}else {
			lvReturn = "N";
		}
		return lvReturn;
	}
}
