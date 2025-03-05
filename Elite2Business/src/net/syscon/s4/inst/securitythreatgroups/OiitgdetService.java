package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.StgValidationsCommitBean;
import net.syscon.s4.common.beans.VStgLocationPresence;
import net.syscon.s4.common.beans.VStgLocationPresenceCommitBean;
import net.syscon.s4.common.beans.VStgRacialMakeup;
import net.syscon.s4.common.beans.VStgRacialMakeupCommitBean;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;

public interface OiitgdetService {
	Map<String, BigDecimal> stgDetailKeyExeqry(BigDecimal paramBean);

	List<VStgLocationPresence> stgLocationPresenceExecuteQuery(VStgLocationPresence objVStgLocation);

	List<SecurityThreatGroups> rgStg3RecordGroup();

	List<IwpTemplates> fafPostQuery(OmsModules paramBean);

	List<VStgRacialMakeup> stgRaceMakeupExecuteQuery(VStgRacialMakeup objVStgRacial);

	List<FormAccessibleForms> fafExecuteQuery(FormAccessibleForms objFormAccessible);

	Integer stgRaceMakeupCommit(VStgRacialMakeupCommitBean commitBean);

	Integer stgLocationPresenceCommit(VStgLocationPresenceCommitBean commitBean);

	Integer fafCommit(FormAccessibleFormsCommitBean commitBean);

	List<ReferenceCodes> stgValidationsPostQuery(ReferenceCodes paramBean);

	Map<String, String> oiitgdetWhenNewFormInstance();

	Integer stgValidationsCommit(StgValidationsCommitBean commitBean);

	List<SecurityThreatGroups> rgStg1RecordGroup();

	List<SecurityThreatGroups> rgStg2RecordGroup();

	List<StgValidations> stgValidationsExecuteQuery(StgValidations objStgValidations);

	String oiitgdetPrimaryCur(BigDecimal stgId);

	String oiitgdetGetProfileValue(String profileType, String profileCode);

	String oiitgdetGetGroupPrivilege(String username);

}
