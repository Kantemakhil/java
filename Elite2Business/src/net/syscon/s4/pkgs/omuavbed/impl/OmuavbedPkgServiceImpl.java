package net.syscon.s4.pkgs.omuavbed.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.non_association.impl.NonAssociationRepositoryImpl;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgRepository;
import net.syscon.s4.pkgs.omuavbed.OmuavbedPkgService;
import net.syscon.s4.pkgs.tag_assessment.TagAssessmentService;
import net.syscon.s4.pkgs.tag_core.TagCoreService;

@Service
public class OmuavbedPkgServiceImpl implements OmuavbedPkgService {
	@Autowired
	private OmuavbedPkgRepository omuavbedRepository;

	@Autowired
	private TagCoreService tagCoreService;

	@Autowired
	private NonAssociationService nonAssociationService;

	@Autowired
	private TagAssessmentService tagAssessmentService;
	@Autowired
	private NonAssociationRepositoryImpl nonAssociationRepositoryImpl;
	// Global variable
	String gvSortOrder;

	private static final String Y = "Y";

	@Override
	public List<OmuavbedLivUnitsQuery> livUnitsQuery(final OmuavbedLivUnitsQuery objSearchDao) {

		List<OmuavbedLivUnitsQuery> agyInterLocation = new ArrayList<OmuavbedLivUnitsQuery>();
		Long offenId = null;
		Integer emptySearchCount = 0;
		String orderBy = null;
		String tagSortOrder = null;

		// liv_units_cursor Procedure
		if (objSearchDao.getpOffenderBookId() != null) {
//			 off_id_cursor cursor
			 offenId = omuavbedRepository.offIdCursor(objSearchDao.getpOffenderBookId());
		} else {
			offenId = objSearchDao.getpOffenderId();
		}

		// empty_search_cur cursor
		emptySearchCount = omuavbedRepository.emptySearchCur();

		// (tag_sort.get_order_by, //235 **pending
		tagSortOrder = getOrderBy();

		if (tagSortOrder != null) {
			orderBy = tagSortOrder;
		} else {
			orderBy = " ORDER BY list_seq ";
		}

		objSearchDao.setpOffenderId(offenId);
		objSearchDao.setEmptySearchCount(emptySearchCount);
		// p_refcursor Cursor
		agyInterLocation = omuavbedRepository.pRefcursor(objSearchDao, orderBy);

		return agyInterLocation;
	}

	private String getOrderBy() {
		String lvSortOrder;
		if (gvSortOrder != null) {
			lvSortOrder = " ORDER BY " + gvSortOrder;
		} else {
			lvSortOrder = null;
		}
		gvSortOrder = null;
		return lvSortOrder;
	}

	@Override
	public Map<String, Object> getConflictWarning(final BigDecimal offenderBookId, final BigDecimal offenderId,
			final BigDecimal livingUnitId , final String agyLocId) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		LivingUnits lvLivUnitRec = new LivingUnits();
		LivingUnits cellSharingRecord = new LivingUnits();
		Offenders lvOffRec = new Offenders();
		List<Offenders> getOccupantsList = new ArrayList<Offenders>();
		List<Offenders> getCsaOccupantsList = new ArrayList<Offenders>();
		String pWarningMsg = null;
		BigDecimal offId = null;
		final String lvEnemyFoundFlag = null;
		String lvWarningMsg = null;
		String pWarningPrompt = null;
		BigDecimal lvOffId = null;

		if (offenderBookId != null && offenderId == null) {
			offId = omuavbedRepository.offIdSelectOperation(offenderBookId);// ok
		} else {
			offId = offenderId;
		}

		// function get_liv_unit_info
		lvLivUnitRec = getLivUnitInfo(offenderBookId, offId, livingUnitId);//ok
         
		OmuavbedLivUnitsQuery searchObject=new OmuavbedLivUnitsQuery();
		searchObject.setpOffenderBookId(offenderBookId.longValue());
		searchObject.setpOffenderId(offId.longValue());
		searchObject.setLivingUnitId(livingUnitId.longValue());
		searchObject.setpAgyLocId(agyLocId);
		List<OmuavbedLivUnitsQuery> livUnitQueryResult= livUnitsQuery(searchObject);
		
		List<OmuavbedLivUnitsQuery> resultList = livUnitQueryResult.stream().filter(object -> object.getLivingUnitId() == livingUnitId.longValue()).collect(Collectors.toList());
		if(!resultList.isEmpty()) {	
			if(resultList.get(0)!=null && resultList.get(0).getSecurityConflict()!=null) {			
				cellSharingRecord.setSecurityConflict(resultList.get(0).getSecurityConflict());		
			}
			if(resultList.get(0)!=null && resultList.get(0).getDescription()!=null) {
				cellSharingRecord.setDescription(resultList.get(0).getDescription());
			}
		}
		// tag_core.get_off_details
		if(offId!=null) {
			lvOffRec = tagCoreService.getOffDetails(offId.longValue());
		}
		//ok
			// Function get_non_associations
		 nonAssociationService.getNonAssociations(offId, livingUnitId);//ok
			String nonAssDetails="";
		List<String> offendersNonAssList =	nonAssociationRepositoryImpl.getOffendersDetailsByLoc(offId,livingUnitId.intValueExact());
		
		if (offendersNonAssList != null && offendersNonAssList.size() > 0) {
			for (String str : offendersNonAssList)
				if(str != null) {
				nonAssDetails += str + "\n";
		}
		}
		
			// non_association.get_stg_non_ass_msg
			nonAssociationService.getStgNonAssMsg(livingUnitId, offId, lvEnemyFoundFlag);//ok

//		if (nonAssDetails.length() > 0) {
//			lvWarningMsg = addMsg(lvWarningMsg, " omuavbed.selectedOffender ".concat(formatOffRec(lvOffRec))
//					.concat(" omuavbed.housedinsameunit \n").concat(nonAssDetails));
//		}

		// Gang conflit
		String gangConflitDetails = "";
		List<String> offendersGangList = nonAssociationRepositoryImpl.getGangConflit(livingUnitId, offenderBookId);

		if (offendersGangList != null && offendersGangList.size() > 0) {
			for (String str : offendersGangList)
				gangConflitDetails += str + "\n";
		}

		String finalMsg = getFinalMessageString(nonAssDetails, gangConflitDetails);

		if (finalMsg.length() > 0) {
				lvWarningMsg = addMsg(lvWarningMsg,
						" omuavbed.selectedOffender ".concat(formatOffRec(lvOffRec)).concat(" omuavbed.housedinsameunit \n")
					.concat(finalMsg));
		} 
			
		if (Y.equals(lvLivUnitRec.getCellSharingConflict())) {
			// tag_assessment.chk_bkg_csa
			Integer count = tagAssessmentService.chkBkgCsa(offenderBookId);//ok
			if (count > 0) {
				// omuavbed.get_occupants
				getOccupantsList = getOccupants(livingUnitId);

				lvWarningMsg = addMsg(lvWarningMsg,"\n"+ formatOffRec(lvOffRec)
						.concat(" has a cell sharing risk indicated. The selected location is already assigned to ")
						.concat(formatOffDetailsList(getOccupantsList)).concat("."));
				getOccupantsList = null;
			}
			// tag_assessment.get_csa_occupants
			getCsaOccupantsList = tagAssessmentService.getCsaOccupants(livingUnitId);//ok
			if (getCsaOccupantsList.size() > 0) {
				lvWarningMsg = addMsg(lvWarningMsg,
						"\nThe selected location ".concat(lvLivUnitRec.getDescription()).concat(" is assigned to ")
								.concat(formatOffDetailsList(getCsaOccupantsList))
								.concat("This/these offender(s) have a cell sharing risk indicated.\n"));

			}
			getCsaOccupantsList = null;
		}

		if (Y.equals(cellSharingRecord.getSecurityConflict())) {
			if(lvOffRec != null) {
			lvWarningMsg = addMsg(lvWarningMsg,
					"\n The selected location ".concat(cellSharingRecord.getDescription())
							.concat(" does not have a security rating that is suitable for ")
							.concat(formatOffRec(lvOffRec)).concat("."));
			}
		}

		if (Y.equals(lvLivUnitRec.getUnitAtCapacity())) {//---doubt
			lvWarningMsg = addMsg(lvWarningMsg, "You are exceeding the operational capacity for this location.");
		}

		if (offenderBookId != null) {
			lvOffId = omuavbedRepository.getlvOffId(offenderBookId);//ok
		} else {
			lvOffId = offenderId;
		}
		// reserved_bed_cur
		final Integer lvReservedBed = omuavbedRepository.reservedBedCur(lvOffId, livingUnitId);//ok
		if (lvReservedBed > 0) {
			lvWarningMsg = addMsg(lvWarningMsg,
					"Current location is reserved for another offender.");
		}

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

		return outParams;
	}

	@Override
	public List<Offenders> getOccupants(final BigDecimal livingUnitId) {
		return omuavbedRepository.getOccupants(livingUnitId);
	}

	@Override
	public LivingUnits getLivUnitInfo(final BigDecimal offenderBookId, final BigDecimal offId,
			final BigDecimal livingUnitId) {
		return omuavbedRepository.getLivUnitInfo(offenderBookId, offId, livingUnitId);
	}

	@Override
	public String addMsg(String pMsg, final String pAddMsg) {
		if (pMsg != null) {
			pMsg = pMsg.concat(pAddMsg);
		} else {
			pMsg = pAddMsg;
		}
		return pMsg;
	}

	@Override
	public String formatOffList(final List<Offenders> lvOffInfo) {
		String lvOffList = "";
		for (final Offenders offenders : lvOffInfo) {
			if (lvOffList != null) {
				lvOffList = lvOffList.concat("");
			}
			lvOffList = lvOffList.concat(formatOffRec(offenders));
		}
		return lvOffList;
	}
	

	@Override
	public String formatOffRec(final Offenders lvOffRec) {
		return lvOffRec.getLastName().concat(", ").concat(lvOffRec.getFirstName()).concat(", ")
				.concat(lvOffRec.getOffenderIdDisplay());
	}

	@Override
	public Map<String, Object> getConflictWarningNoCs(final Long offenderBookId, final Long offenderId,
			final BigDecimal livingUnitId) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		String pWarningMsg = null;
		String pWarningPrompt = null;
		Long lvOffenderId = null;
		LivingUnits lvLivUnitRec = new LivingUnits();
		Offenders lvOffRec = new Offenders();
		List<Offenders> lvOffInfo = new ArrayList<Offenders>();
		String lvWarningMsg = null;

		if (offenderBookId != null) {
			// 601 get offId
			lvOffenderId = omuavbedRepository.getOffenderId(offenderBookId);//ok
		} else {
			lvOffenderId = offenderId;
		}
		// 608 get_liv_unit_info
		lvLivUnitRec = getLivUnitInfo(offenderBookId!=null?BigDecimal.valueOf(offenderBookId):null, lvOffenderId!=null?BigDecimal.valueOf(lvOffenderId):null,
				livingUnitId);
		// 613 get get_off_details
		lvOffRec = omuavbedRepository.getOffDetails(lvOffenderId);//ok

		//if (Y.equals(lvLivUnitRec.getPrisonerConflict())) {
			// 619 get_non_associations
			lvOffInfo = nonAssociationService.getNonAssociations(BigDecimal.valueOf(lvOffenderId), livingUnitId);//ok

			// format_off_rec 624
			final String frmtOffRec = formatOffRec(lvOffRec);
			// format_off_list 625
			final String frmtOffList = formatOffList(lvOffInfo);
			// addmsg 622
			lvWarningMsg = addMsg(lvWarningMsg, "A non-association linkage exists between ".concat(frmtOffRec)
					.concat("and").concat(frmtOffList).concat(" housed in this unit."));
			lvOffInfo = null;
		//}
		if (Y.equals(lvLivUnitRec.getSecurityConflict())) {
			lvWarningMsg = addMsg(lvWarningMsg,
					"The selected location ".concat(lvLivUnitRec.getDescription())
							.concat("does not have a security rating that is suitable for")
							.concat(formatOffRec(lvOffRec)).concat("."));
		}
		if (Y.equals(lvLivUnitRec.getUnitAtCapacity())) {
			lvWarningMsg = addMsg(lvWarningMsg, "You are exceeding the operational capacity for this location.");
		}
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

		return outParams;
	}
	
	public String formatOffDetailsList(final List<Offenders> lvOffInfo) {
		String lvOffList = "";
		if (lvOffInfo != null && lvOffInfo.size() > 0) {
			for (final Offenders offenders : lvOffInfo) {
				lvOffList =lvOffList+ "\n"+ offenders.getLastName() + ", " + offenders.getFirstName() + ", "
						+ offenders.getOffenderIdDisplay();
			}
		}
		return lvOffList;
	}
	
	public String getFinalMessageString(String nonAssDetails, String gangConflitDetails) {
		String returnMessage = "";
		if ((nonAssDetails != null && !nonAssDetails.equalsIgnoreCase(""))
				&& (gangConflitDetails != null && !gangConflitDetails.equalsIgnoreCase(""))) {
			returnMessage = "INDIVIDUAL NON-ASSOCIATION CONFLICTS: \n" + nonAssDetails + "\n GANG NON-ASSOCIATION CONFLICTS: \n"
					+ gangConflitDetails;
		} else if (nonAssDetails != null && !nonAssDetails.equalsIgnoreCase("")) {
			returnMessage = "INDIVIDUAL NON-ASSOCIATION CONFLICTS: \n" + nonAssDetails;
		} else if (gangConflitDetails != null && !gangConflitDetails.equalsIgnoreCase("")) {
			returnMessage = "GANG NON-ASSOCIATION CONFLICTS: \n" + gangConflitDetails;
		}
		return returnMessage;
	}

}
