package net.syscon.s4.inst.visitsmanagement.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;

/**
 * Interface OimvlimtRepository
 */
public interface OimvlimtRepository {
	VisitCycleLimits visCycPreUpdate(VisitCycleLimits paramBean);

	List<AgencyLocations> rgAgyIntLocRecordGroup(String userName);

	List<ReferenceCodes> rgCycTypRecordGroup();

	List<ReferenceCodes> rgVisTypRecordGroup();

	Integer visCycInsertVisitCycleLimits(List<VisitCycleLimits> lstVisitCycleLimits);

	List<VisitTypeLimits> visCycOnCheckDeleteMaster(VisitTypeLimits paramBean);

	List<ReferenceCodes> rgSecLvlRecordGroup();

	Integer visCycUpdateVisitCycleLimits(List<VisitCycleLimits> lstVisitCycleLimits);

	List<VisitTypeLimits> visTypExecuteQuery(VisitTypeLimits objVisitTypeLimits);

	Integer visTypUpdateVisitTypeLimits(List<VisitTypeLimits> lstVisitTypeLimits);

	Integer visTypInsertVisitTypeLimits(List<VisitTypeLimits> lstVisitTypeLimits);

	List<VisitCycleLimits> visCycExecuteQuery(VisitCycleLimits objVisitCycleLimits);

	Long visCycPreInsert(VisitCycleLimits paramBean);

	List<ReferenceCodes> rgStrDayRecordGroup();

	Long visitCycleLimitId();

	List<IepLevelBean> rgIepLevelRecordGroup();

	List<VisitCycleLimits> iepLevelExcuteQuery(VisitCycleLimits searchBean);

	List<IepLevelBean> getIEPDetails(Long offenderBookId);


	Integer saveIepSecData(String facility, String iepSecLevel, String user, String operaion);


	VisitCycleLimits getIepVisitLimis(String agyLocId);

	List<ReferenceCodes> getIepSecLov();

}
