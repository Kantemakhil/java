package net.syscon.s4.pkgs.ocdenfor.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.pkgs.ocdenfor.OcdenforPkgRepository;
import net.syscon.s4.pkgs.ocdenfor.OcdenforPkgService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesService;

@Service
public class OcdenforPkgServiceImpl implements OcdenforPkgService {
	@Autowired
	private OcdenforPkgRepository OcdenforRepository;
	@Autowired
	private TagReferenceCodesService tagReferenceCodesService;
	@Autowired
	private OmsUtilsService omsUtilsService;

	final static Logger logger = LogManager.getLogger(OcdenforPkgServiceImpl.class.getName());
	private static final String AGY = "AGY";
	private static final String TEAM = "TEAM";
	private static final String CORP = "CORP";

	@Override
	public VOffenderSentenceEvents getActivityDetails(final VOffenderSentenceEvents vOffender) {
		VOffenderSentenceEvents vOffSenEvents = new VOffenderSentenceEvents();
		CourseActivities coursActys = new CourseActivities();
		String pLocation = null;
		String pRequirement = null;

		try {
			if (vOffender.getProgramId() == null) {
				coursActys = OcdenforRepository.getCourseActivitiesData(vOffender.getCrsActyId().longValue());
			} else {
				coursActys.setProgramId(vOffender.getProgramId().longValue());
			}
			// get pRequirement
			pRequirement = OcdenforRepository.getRequirement(coursActys.getProgramId());

			if (AGY.equals(coursActys.getProviderPartyClass())) {
				pLocation = OcdenforRepository.getLocation(coursActys.getProviderPartyCode());

			} else if (TEAM.equals(coursActys.getProviderPartyClass())) {
				pLocation = OcdenforRepository.getLocationFrmTeams(coursActys.getProviderPartyId());

			} else if (CORP.equals(coursActys.getProviderPartyClass())) {
				pLocation = OcdenforRepository.getLocationFrmCorporates(coursActys.getProviderPartyId());
			}

			vOffSenEvents.setLocation(pLocation);
			vOffSenEvents.setCondition(pRequirement);
		} catch (Exception e) {
			vOffSenEvents = null;
			logger.error("getActivityDetails", e);
		}
		return vOffSenEvents;
	}

	@Override
	public VOffenderProceedingSents getCourtEventSentence(final VOffenderProceedingSents bean) {
		VOffenderProceedingSents vOffeSenEve = new VOffenderProceedingSents();
		OffenceResultCodes offrstcode = new OffenceResultCodes();
		try {
			offrstcode = OcdenforRepository.getCourtEventsentence(bean);

			vOffeSenEve.setResultCode(offrstcode.getChargeStatus());
			vOffeSenEve.setResltDescription(offrstcode.getDescription());
			vOffeSenEve.setDispositionCode(offrstcode.getDispositionCode());
		} catch (Exception e) {
			vOffeSenEve = null;
			logger.error("getCourtEventSentence", e);
		}
		return vOffeSenEve;
	}

	@Override
	public String popOcuwarni(final OffenderCaseNotes offenderCaseNotes) {
		String noteSourseCode = null;
		String caseNoteSbType = null;
		String pNbtStaffNameDesc = null;
		final Map<String, Object> returnMap = new HashMap<String, Object>();

		try {
			if (offenderCaseNotes.getNoteSourceCode() != null) {
				noteSourseCode = tagReferenceCodesService.getDescCode("NOTE_SOURCE",
						offenderCaseNotes.getNoteSourceCode());
			}

			if (offenderCaseNotes.getCaseNoteSubType() != null) {
				caseNoteSbType = tagReferenceCodesService.getDescCode("TASK_SUBTYPE",
						offenderCaseNotes.getCaseNoteSubType());
			}
			if (offenderCaseNotes.getStaffId() != null) {
				pNbtStaffNameDesc = omsUtilsService.getStaffName(BigDecimal.valueOf(offenderCaseNotes.getStaffId()));
			}

			returnMap.put("P_NBT_NOTE_SOURCE_CODE_DESC", noteSourseCode);
			returnMap.put("P_NBT_CASE_NOTE_SUB_TYPE_DESC", caseNoteSbType);
			returnMap.put("P_NBT_STAFF_NAME_DESC", pNbtStaffNameDesc);
		} catch (Exception e) {
			logger.error("popOcuwarni", e);
			return null;
		}
		return noteSourseCode;
	}

	@Override
	public Integer updateCourtEventSentence(final VOffenderProceedingSents objSearchDao, final String userName) {
		Integer retVal = 0;
		try {
			if (objSearchDao.getResultCode() == null) {
				objSearchDao.setModifyUserId(userName);
				retVal = OcdenforRepository.deleteCourtEventSentence(objSearchDao);
			} else {
				objSearchDao.setModifyUserId(userName);
				retVal = OcdenforRepository.updateCourtEventSentence(objSearchDao);
				if (retVal == 0) {
					objSearchDao.setCreateUserId(userName);
					retVal = OcdenforRepository.insertCourtEventSentences(objSearchDao);
				}
			}
		} catch (Exception e) {
			logger.error("popOcuwarni", e);
			return 0;
		}
		return 1;
	}
}
