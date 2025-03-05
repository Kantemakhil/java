package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StgCaseNotes;

/**
 * Interface OidappndService
 */
public interface OidappndService {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer stgCommit(StgCaseNotes commitBean);

}
