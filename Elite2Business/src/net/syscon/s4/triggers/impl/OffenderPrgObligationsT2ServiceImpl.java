package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.OffenderPrgObligationsT2Service;

@Service
public class OffenderPrgObligationsT2ServiceImpl extends BaseBusiness implements OffenderPrgObligationsT2Service{
	
	@Autowired
	private TagServiceService tagServiceService;
	
	@Autowired
	private TagProgrammesService tagProgrammesService;

	@Override
	public Integer offenderPrgObligationsT2(OffenderPrgObligations obj) {
		ProgramServices lvPgmRec=null;
		if(obj.getProgramId() != null) {
			lvPgmRec=tagServiceService.getPrgSrvDetails(obj.getProgramId());
		}
		
		if(lvPgmRec.getProgramCategory().equals("ACP")) {
			tagProgrammesService.createProgramProfile(obj.getProgramId(), obj.getOffenderPrgObligationId(), obj.getOffenderBookId(), obj.getCreateUserId());
		}
		return null;
	}
}
