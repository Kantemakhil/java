package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.VStgLocationPresence;
import net.syscon.s4.common.beans.VStgRacialMakeup;
import net.syscon.s4.im.beans.OmsModules;

public interface OiitgdetRepository {
	List<OmsModules> createFormGlobals(OmsModules paramBean);

	List<VStgLocationPresence> stgLocationPresenceExecuteQuery(VStgLocationPresence objVStgLocation);

	BigDecimal fafPostQueryTwoValidIwpCur(BigDecimal param);

	List<SecurityThreatGroups> rgStg3RecordGroup();

	BigDecimal fafPostQueryVAlidDataCurTwo(BigDecimal param);

	List<SecurityThreatGroups> oiitgdetWhenNewFormInstanceOne(SecurityThreatGroups paramBean);

	List<VStgRacialMakeup> stgRaceMakeupExecuteQuery(VStgRacialMakeup objVStgRacial);

	List<FormAccessibleForms> fafExecuteQuery(FormAccessibleForms objFormAccessible);

	BigDecimal stgDetailKeyExeqry(BigDecimal param);

	BigDecimal stgDetailKeyExeqryValidated(BigDecimal param);

	String fafPostQueryDescription(String param);

	List<SecurityThreatGroups> oiitgdetWhenNewFormInstance(SecurityThreatGroups paramBean);

	List<SecurityThreatGroups> rgStg1RecordGroup();

	String oiitgdetOiitgdetWhennewforminstanceStgDescriptionCur2(String lvGroup);

	List<SecurityThreatGroups> rgStg2RecordGroup();

	List<ReferenceCodes> stgValidationsPostQuery(ReferenceCodes paramBean);

	BigDecimal fafPostQueryVAlidDataCur(BigDecimal param);

	String oiitgdetWhenNewFormInstance();

	List<StgValidations> stgValidationsExecuteQuery(StgValidations objStgValidations);

	String checkDataAvailableStg(String destForm, BigDecimal stgId);

	String oiitgdetPrimaryCur(BigDecimal stgId);

	String oiitgdetGetProfileValue(String profileType, String profileCode);

	String oiitgdetGetGroupPrivilege(String username);

}
