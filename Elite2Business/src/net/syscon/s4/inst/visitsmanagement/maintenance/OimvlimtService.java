package net.syscon.s4.inst.visitsmanagement.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimitsCommitBean;

/**
 * Interface OimvlimtService
 */
public interface OimvlimtService {
	Long visCycPreInsert(VisitCycleLimits paramBean);

	VisitCycleLimits visCycPreUpdate(VisitCycleLimits paramBean);

	Integer visCycCommit(VisitCycleLimitsCommitBean commitBean);

	List<AgencyLocations> rgAgyIntLocRecordGroup(String userName);

	List<ReferenceCodes> rgSecLvlRecordGroup();

	List<ReferenceCodes> rgCycTypRecordGroup();

	List<ReferenceCodes> rgVisTypRecordGroup();

	List<VisitTypeLimits> visTypExecuteQuery(VisitTypeLimits objVisitTypeLimits);

	Integer visTypCommit(VisitTypeLimitsCommitBean commitBean);

	List<VisitTypeLimits> visCycOnCheckDeleteMaster(VisitTypeLimits paramBean);

	List<VisitCycleLimits> visCycExecuteQuery(VisitCycleLimits objVisitCycleLimits);

	List<ReferenceCodes> rgStrDayRecordGroup();

	List<IepLevelBean> rgIepLevelRecordGroup();

	List<VisitCycleLimits> iepLevelExecuteQuery(VisitCycleLimits searchBean);

	List<IepLevelBean> getIEPDetails(Long offenderBookId);


	Integer saveIepSecData(String facility, String iepSecLevel, String user, String operaion);


	VisitCycleLimits getIepVisitLimis(String agylocid);

	List<ReferenceCodes> getIepSecLov();
	



}
