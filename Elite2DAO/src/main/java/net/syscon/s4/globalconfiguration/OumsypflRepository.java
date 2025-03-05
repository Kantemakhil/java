package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;

/**
 * Interface OumsypflRepository
 */
public interface OumsypflRepository {

	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer sysPflUpdateSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	ReferenceCodes cgfkchkSysPflSystemProfil(ReferenceCodes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<ReferenceCodes> cgfkSysPflProfileTypeRecordGroup();

	List<SystemProfiles> getSystemProfileRecords(SystemProfiles searchRecord);

	String whenNewFormInstance(String paramBean);

	Dual cgwhenNewFormInstance(String createUserId);

}
