package net.syscon.s4.pkgs.oidcoasi.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;
import net.syscon.s4.pkgs.oidcoasi.OidcoasiPkgService;
import net.syscon.s4.pkgs.oidcoasi.OidcoasiPkgRepository;

@Service
public class OidcoasiPkgServiceImpl implements OidcoasiPkgService {
	@Autowired
	private OidcoasiPkgRepository oidcoasiRepository;

	@Override
	public List<OidcoasiOffenderAssignments> offenderAssignmentsQuery(final OidcoasiOffenderAssignments offAssign) {
		List<OidcoasiOffenderAssignments> pResultSet = new ArrayList<OidcoasiOffenderAssignments>();
		String vLivingUnitDesc = null;
		Long vCaseOfficerId = null;
		BigDecimal vLivingUnitId = null;
		final String pLivingUnitCode1 = offAssign.getLivingUnitCodeOne();
		final String pLivingUnitCode2 = offAssign.getLivingUnitCodeTwo();
		final String pLivingUnitCode3 = offAssign.getLivingUnitCodethree();
		final String pLivingUnitCode4 = offAssign.getLivingUnitCodeFour();
		final String pAgyLocId = offAssign.getAgyLocId(); 
		
		if (pLivingUnitCode1 != null && pLivingUnitCode2 != null && pLivingUnitCode3 != null
				&& pLivingUnitCode4 != null) {
			vLivingUnitDesc = pAgyLocId + "-" + pLivingUnitCode1 + "-" + pLivingUnitCode2 + "-" + pLivingUnitCode3 + "-"
					+ pLivingUnitCode4;
		} else if (pLivingUnitCode1 != null && pLivingUnitCode2 != null && pLivingUnitCode3 != null
				&& pLivingUnitCode4 == null) {
			vLivingUnitDesc = pAgyLocId + "-" + pLivingUnitCode1 + "-" + pLivingUnitCode2 + "-" + pLivingUnitCode3;
		} else if (pLivingUnitCode1 != null && pLivingUnitCode2 != null && pLivingUnitCode3 == null
				&& pLivingUnitCode4 == null) {
			vLivingUnitDesc = pAgyLocId + "-" + pLivingUnitCode1 + "-" + pLivingUnitCode2;
		} else if (pLivingUnitCode1 != null && pLivingUnitCode2 == null && pLivingUnitCode3 == null
				&& pLivingUnitCode4 == null) {
			vLivingUnitDesc = pAgyLocId + "-" + pLivingUnitCode1;
		} else {
			vLivingUnitDesc = null;
		}

		if (vLivingUnitDesc != null) {
			vLivingUnitId = oidcoasiRepository.getLivingUnitIdFmLU(vLivingUnitDesc);
		}

		if (offAssign.getCurrentOfficerStaffId() != null) {
			vCaseOfficerId = offAssign.getCurrentOfficerStaffId().longValue();
		} else {
			vCaseOfficerId = offAssign.getCurrentOfficerStaffId();
		}

		offAssign.setvLivingUnitDesc(vLivingUnitDesc);
		offAssign.setvLivingUnitId(vLivingUnitId);
		offAssign.setvCaseOfficerId(vCaseOfficerId);
		if ("N".equals(offAssign.getUnassignedFlag())) {
			if (vCaseOfficerId != null) {
				pResultSet = oidcoasiRepository.getoffAssigQuerySelectOne(offAssign);
			} else {
				pResultSet = oidcoasiRepository.getoffAssigQuerySelectSecond(offAssign);
			}
		} else {
			pResultSet = oidcoasiRepository.getoffAssigQuerySelectThird(offAssign);
		}
		return pResultSet;
	}

}
