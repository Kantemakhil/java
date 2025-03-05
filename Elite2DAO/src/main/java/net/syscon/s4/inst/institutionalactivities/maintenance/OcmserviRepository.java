package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;

/**
 * Interface OcmserviRepository
 */
public interface OcmserviRepository {
	Integer prgSrvUpdateProgramServices(List<ProgramServices> object);

	List<ReferenceCodes> rgPsCategoryRecordGroup();

	List<ProgramServices> prgSrvExecuteQuery(ProgramServices object);

	List<ReferenceCodes> rgFunctionTypeRecordGroup();

	String prgSrvInsertProgramServices(List<ProgramServices> object);

	String prgSrvDeleteProgramServices(List<ProgramServices> object);

	Integer preUpdate(ProgramServices object);

	BigDecimal preInsertPrgSrv(ProgramServices obj);

	Integer checkProgramCode(ProgramServices object);

	String getTableName(String errorName);

}
