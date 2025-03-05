package net.syscon.s4.inst.transportation.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.AgyLocFeedDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetailsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Routes;
import net.syscon.s4.inst.transportation.maintenance.beans.RoutesCommitBean;

/**
 * Interface OimrouteService
 */
public interface OimrouteService {
	List<Routes> routesExecuteQuery();

	Integer routesCommit(RoutesCommitBean CommitBean);

	List<RouteStopDetails> routeStopDetailsExecuteQuery(String routeName);

	Integer routeStopDetailsCommit(RouteStopDetailsCommitBean CommitBean);

	List<AgyLocFeedDetails> agyLocFeedDetailsExecuteQuery(String AgLocId);

	List<ReferenceCodes> rgAgyLocRecordGroup();

}
