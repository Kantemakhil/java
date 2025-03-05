package net.syscon.s4.inst.transportation.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.AgyLocFeedDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.Routes;

/**
 * Interface OimrouteRepository
 */
public interface OimrouteRepository {

	List<Routes> routesExecuteQuery();

	Integer routesInsertRoutes(List<Routes> lstRoutes);

	Integer routesUpdateRoutes(List<Routes> lstRoutes);

	Integer routesUpdateRoutes1(List<Routes> route);

	List<RouteStopDetails> routeStopDetailsExecuteQuery(String routeName);

	Integer routeStopDetailsInsertRouteStopDetails(List<RouteStopDetails> lstRouteStopDetails);

	Integer routeStopDetailsUpdateRouteStopDetails(List<RouteStopDetails> lstRouteStopDetails);

	List<AgyLocFeedDetails> agyLocFeedDetailsExecuteQuery(String AgLocId);

	List<ReferenceCodes> rgAgyLocRecordGroup();

	Long stopsCur(String vRoute);

	Integer activeCur(String vRoute);

	String stopLocation(String vRoute);

	String startLoc(String vRoute);
}
