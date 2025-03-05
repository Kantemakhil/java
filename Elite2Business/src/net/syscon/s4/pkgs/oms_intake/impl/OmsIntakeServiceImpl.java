package net.syscon.s4.pkgs.oms_intake.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.pkgs.oms_intake.OmsIntakeRepository;
import net.syscon.s4.pkgs.oms_intake.OmsIntakeService;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousRepository;
import net.syscon.s4.pkgs.pims_weight.PimsWeightService;

@Service
public class OmsIntakeServiceImpl implements OmsIntakeService {

	@Autowired
	private PimsWeightService pimsWeightService;

	@Autowired
	private OmsIntakeRepository omsIntakeRepository;
	
	@Autowired
	private Omss40Service omss40Service;
	
	@Autowired
	private OmsMiscellaneousRepository omsMiscellaneousRepository;

	@Override
	public String omsIntakeGetOffenderSupervision(Long offenderBookId, String userName) {
		String lvLevelCode = pimsWeightService.getSupLevel(offenderBookId, userName);
		// Below line comented becuase that is not used
		// String lvCaseloadType =pimsWeightService.getCaseloadType(userName);
		String lvDomain = "SUP_LVL_TYPE";
		String lvLevelDescription = omsIntakeRepository.refCur(lvLevelCode, lvDomain);
		if (lvLevelDescription == null) {
			String caseLoadType = omss40Service.getCurrentCaseloadType(userName);
			final SystemProfiles symProfile = omsMiscellaneousRepository.getProfileValue("CLIENT", "PENDING_STAT");
			if ("COMM".equalsIgnoreCase(caseLoadType) && symProfile != null && symProfile.getProfileValue2() != null && symProfile.getProfileValue2() != "") {
				lvLevelDescription = symProfile.getProfileValue2();
			} else if ("INST".equalsIgnoreCase(caseLoadType) && symProfile != null && symProfile.getProfileValue() != null && symProfile.getProfileValue() != "") {
				lvLevelDescription = symProfile.getProfileValue();
			}
		}
		return lvLevelDescription;
	}

}
