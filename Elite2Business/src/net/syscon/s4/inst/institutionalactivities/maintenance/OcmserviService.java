package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.casemanagement.beans.ProgramServicesCommitBean;

/**
 * Interface OcmserviService
 */
public interface OcmserviService {
	ProgramServices prgSrvCommit(ProgramServicesCommitBean commitBean);

	List<ReferenceCodes> rgPsCategoryRecordGroup();

	List<ProgramServices> prgSrvExecuteQuery(ProgramServices obj);

	List<ReferenceCodes> rgFunctionTypeRecordGroup();

	String getTableName(String sealFlag);

}
