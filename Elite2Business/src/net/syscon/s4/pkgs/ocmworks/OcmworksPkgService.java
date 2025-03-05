package net.syscon.s4.pkgs.ocmworks;

import net.syscon.s4.inst.workflow.maintenance.beans.WorkIwpTemplate;
import net.syscon.s4.inst.workflow.maintenance.beans.WorkTrigger;

public interface OcmworksPkgService {

	Integer getPreviousDays(final WorkTrigger bean);

	Integer getCountIwpDocuments(final WorkIwpTemplate bean);

	Integer getCountOffenderAssociated(final String pCaseNoteType, final String pCaseNoteSubType);
}
