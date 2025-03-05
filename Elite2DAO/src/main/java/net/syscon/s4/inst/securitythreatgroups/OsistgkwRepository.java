package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgSearchV1;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OsistgkwRepository
 */
public interface OsistgkwRepository {

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer stgSearchV1DeleteStgSearchV1(List<StgSearchV1> lstStgSearchV1);

	List<SecurityThreatGroups> getStgGroupDescription(SecurityThreatGroups paramBean);

	Integer stgSearchV1UpdateStgSearchV1(List<StgSearchV1> lstStgSearchV1);

	Integer stgSearchV1InsertStgSearchV1(List<StgSearchV1> lstStgSearchV1);

	List<StgSearchV1> stgSearchV1ExecuteQuery(StgSearchV1 objStgSearchV1);

	String vprofvaluestgDescription();

	String vprofvaluestgDescriptionThree(BigDecimal stgId);

	String vprofvaluestgDescriptionTwo(BigDecimal stgId);

	String vprofvaluestgDescriptionOne(BigDecimal stgId);

}
