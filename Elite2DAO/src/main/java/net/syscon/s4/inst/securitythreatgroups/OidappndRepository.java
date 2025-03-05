package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StgCaseNotes;

/**
 * 
 * Interface OidappndRepository
 */
public interface OidappndRepository {
	List<OmsModules> createFormGlobalscreateFormGlobals(OmsModules paramBean);

	String getNewText(String newText);

	Integer oidappndOidmbrdtUpdateOffenderStgAffiliations(StgCaseNotes commitBean);

	Integer oidappndOidstgcnUpdateStgCaseNotes(StgCaseNotes commitBean);

	Integer oidappndOidmbrquUpdateOffenderAssessmentItems(StgCaseNotes commitBean);

	Integer oidappndOcdcptitUpdateOffenderPtr(StgCaseNotes commitBean);

}
