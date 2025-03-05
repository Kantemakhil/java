package net.syscon.s4.pkgs.oidadmis.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.oidadmis.OidadmisPkgRepository;
import net.syscon.s4.pkgs.oidadmis.OidadmisPkgService;

@Service
public class OidadmisPkgServiceImpl implements OidadmisPkgService {

	@Autowired
	private OidadmisPkgRepository oidadmisRepository;

	@Autowired
	private NonAssociationService nonAssociationService;

	private static Logger logger = LogManager.getLogger(OidadmisPkgServiceImpl.class.getName());
	private static final String COMM = "COMM";
	private static final String NEW = "NEW";
	private static final String INST = "INST";
	private static final String RECAP = "RECAP";
	private static final String READM = "READM";
	private static final String TRANS = "TRANS";
	private static final String REL = "REL";
	private static final String TRN = "TRN";
	private static final String OJ = "OJ";
	private static final String N = "N";

	@Override
	public Map<String, Object> getDefaults(final String caseloadId) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		Integer lvCapacity = null;
		Integer lvOccupied = null;
		String pAgyLocId = null;
		Integer pLivingUnitId = null;
		List<CaseloadAdmOtherProfiles> defList = null;

		try {
			// 1041 get_defaults_cur
			defList = oidadmisRepository.getDefaultsCur(caseloadId);

			if (defList == null) {
				pAgyLocId = null;
				pLivingUnitId = null;
			} else {
				for (final CaseloadAdmOtherProfiles clAdmOthPro : defList) {
					pAgyLocId = clAdmOthPro.getAgyLocId();
					pLivingUnitId = clAdmOthPro.getLivingUnitId();
				}

				if (pAgyLocId != null && pLivingUnitId != null) {
					// 1053 capacity_cur
					lvCapacity = oidadmisRepository.getLvCapacity(pLivingUnitId);

					// 1058 non_association.get_count_for_liv_unit(p_living_unit_id);
					final ReserveBedLocations bean = new ReserveBedLocations();
					bean.setLivingUnitId(BigDecimal.valueOf(pLivingUnitId));
					lvOccupied = nonAssociationService.getCountForLivUnit(bean);
					if (lvOccupied >= lvCapacity) {
						pAgyLocId = null;
						pLivingUnitId = null;
					}
				}
			}
			outParams.put("P_AGY_LOC_ID", pAgyLocId);
			outParams.put("P_LIVING_UNIT_ID", pLivingUnitId);
		} catch (Exception e) {
			logger.error("getDefaults", e);
		}
		return outParams;
	}

	@Override
	public String getAdmissionType(final VHeaderBlock2 searchBean) {
		String lvMovementType;
		String lvMovementReasonCode;
		String pAdmissionType = null;
		String pAdmissionReason = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();

		try {
			if (searchBean.getBookingType() != null) {
				searchBean.setBookingType(searchBean.getBookingType());
			} else {
				searchBean.setBookingType(COMM);
			}

			if (searchBean.getOffenderBookId()!=null && searchBean.getOffenderBookId().equals(0)) { 
				pAdmissionType = NEW;
			} else if (!"INST".equals(searchBean.getBookingType()) ) {
				pAdmissionType = NEW;
			} else {
				OffenderExternalMovements addmType = oidadmisRepository
						.getAdmissionTypeCur(searchBean.getOffenderBookId().longValue());
				lvMovementType= addmType.getMovementType();
				lvMovementReasonCode = addmType.getMovementReasonCode();
				if (REL.equals(lvMovementType)) {
					pAdmissionReason = getRecaptureReason(lvMovementReasonCode);

					if (pAdmissionReason != null) {
						pAdmissionType = RECAP; // RE-CAPTURE
					} else {
						pAdmissionType = READM; // RE-ADMISSION
					}
				} else if (TRN.equals(lvMovementType)) {
					if (OJ.equals(lvMovementReasonCode)) {
						pAdmissionType = READM; // RE-ADMISSION
					} else {
						pAdmissionType = TRANS; // TRANSFER
					}
				} else {
					pAdmissionType = lvMovementType;
				}
			} // end if

			returnMap.put("p_admission_type", pAdmissionType);
			returnMap.put("p_admission_reason", pAdmissionReason);
		} catch (Exception e) {
			logger.error("getAdmissionType", e);
			return pAdmissionType;
		}
		return pAdmissionType;
	}

	private String getRecaptureReason(final String pMvtReason) {
		return oidadmisRepository.getMvmentReasonCode(pMvtReason);
	}

	@Override
	public Integer checkYoungOff(final String pCaseloadId, final Integer pOffenderAge) {
		return oidadmisRepository.getMsgNumber(pCaseloadId, pOffenderAge);
	}

	@Override
	public String getTrustFlag(final String pCaseloadId) {
		String lvTrustFlag = N;
		lvTrustFlag = oidadmisRepository.getTrstFlag(pCaseloadId);
		if (lvTrustFlag == null) {
			lvTrustFlag = N;
		}
		return lvTrustFlag;
	}
}
