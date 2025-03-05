package net.syscon.s4.pkgs.ocmworks.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;
import net.syscon.s4.pkgs.ocmworks.OcmworksPkgRepository;
import net.syscon.s4.pkgs.ocmworks.OcmworksPkgService;

@Service
public class OcmworksPkgServiceImpl implements OcmworksPkgService {
	@Autowired
	private OcmworksPkgRepository ocmworksRepository;

	@Override
	public Integer getPreviousDays(final WorkTrigger bean) {
		return ocmworksRepository.getPrevDays(bean.getTriggerName());
	}

	@Override
	public Integer getCountIwpDocuments(final WorkIwpTemplate bean) {
		return ocmworksRepository.getCountIwpDocsCur(BigDecimal.valueOf(bean.getTemplateId()));
	}

	@Override
	public Integer getCountOffenderAssociated(final String pCaseNoteType, final String pCaseNoteSubType) {
		return ocmworksRepository.getCountOffAssociated(pCaseNoteType, pCaseNoteSubType);
	}
}
