package net.syscon.s4.inst.offenderissuestracking;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;

/**
 * Interface OiuincreRepository
 */
public interface OiuincreRepository {
	List<AgencyIncidents> agencyIncidentsExecuteQuery(Integer rootOffenderId);

	OmsModules createFormGlobals(OmsModules paramBean);

}
