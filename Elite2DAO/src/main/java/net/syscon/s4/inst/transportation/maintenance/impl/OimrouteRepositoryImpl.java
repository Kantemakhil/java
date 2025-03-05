package net.syscon.s4.inst.transportation.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.OimrouteRepository;
import net.syscon.s4.inst.transportation.maintenance.beans.AgyLocFeedDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.Routes;

/**
 * Class OimrouteRepositoryImpl
 * 
 * @author Vrnda Software Technologies
 * @version 1.0
 */
@Repository
public class OimrouteRepositoryImpl extends RepositoryBase implements OimrouteRepository {
	private static Logger logger = LogManager.getLogger(OimrouteRepositoryImpl.class.getName());

	/**
	 * Creates new OimrouteRepositoryImpl class Object
	 */
	public OimrouteRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @return List<Routes>
	 *
	 * @throws SQLException
	 */
	public List<Routes> routesExecuteQuery() {
		final String sql = getQuery("OIMROUTE_ROUTES_FIND_ROUTES");
		ArrayList<Routes> returnList = null;
		try {
			returnList = (ArrayList<Routes>) namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<Routes>(Routes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routesExecuteQuery() ", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the database tables based on
	 *
	 * @param lstRoutes List<Routes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer routesInsertRoutes(final List<Routes> lstRoutes) {
		String sql = getQuery("OIMROUTE_ROUTES_INSERT_ROUTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Routes routes : lstRoutes) {
			parameters.add(new BeanPropertySqlParameterSource(routes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routesInsertRoutes() ", e);
		}
		if (lstRoutes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the database tables based on
	 *
	 * @param lstRoutes List<Routes>
	 *
	 * @throws SQLException
	 */
	public Integer routesUpdateRoutes(final List<Routes> lstRoutes) {
		String sql = getQuery("OIMROUTE_ROUTES_UPDATE_ROUTES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Routes routes : lstRoutes) {
			parameters.add(new BeanPropertySqlParameterSource(routes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routesUpdateRoutes() ", e);
		}
		if (lstRoutes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}


	/**
	 * Fetch the records from database table
	 *
	 * @param routeName 
	 *
	 * @return List<RouteStopDetails>
	 *
	 * @throws SQLException
	 */
	public List<RouteStopDetails> routeStopDetailsExecuteQuery(String routeName) {
		final String sql = getQuery("OIMROUTE_ROUTESTOPDETAILS_FIND_ROUTE_STOP_DETAILS");
		ArrayList<RouteStopDetails> returnList = null;
		try {
			returnList = (ArrayList<RouteStopDetails>) namedParameterJdbcTemplate.query(sql,
					createParams("routeName", (routeName)),
					new BeanPropertyRowMapper<RouteStopDetails>(RouteStopDetails.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsExecuteQuery() ", e);
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstRouteStopDetails List<RouteStopDetails>
	 *
	 * @return Integer
	 *
	 * @throws SQLException
	 */
	public Integer routeStopDetailsInsertRouteStopDetails(final List<RouteStopDetails> lstRouteStopDetails) {
		String sql = getQuery("OIMROUTE_ROUTESTOPDETAILS_INSERT_ROUTE_STOP_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (RouteStopDetails routeStopDetails : lstRouteStopDetails) {
			parameters.add(new BeanPropertySqlParameterSource(routeStopDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsInsertRouteStopDetails() ", e);
		}
		if (lstRouteStopDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the database tables based on
	 *
	 * @param lstRouteStopDetails List<RouteStopDetails>
	 *
	 * @throws SQLException
	 */
	public Integer routeStopDetailsUpdateRouteStopDetails(final List<RouteStopDetails> lstRouteStopDetails) {
		String sql = getQuery("OIMROUTE_ROUTESTOPDETAILS_UPDATE_ROUTE_STOP_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (RouteStopDetails routeStopDetails : lstRouteStopDetails) {
			parameters.add(new BeanPropertySqlParameterSource(routeStopDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsUpdateRouteStopDetails() ", e);
		}
		if (lstRouteStopDetails.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param AgLocId 
	 *
	 * @return List<AgyLocFeedDetails>
	 *
	 * @throws SQLException
	 */
	public List<AgyLocFeedDetails> agyLocFeedDetailsExecuteQuery(String AgLocId) {
		final String sql = getQuery("OIMROUTE_AGYLOCFEEDDETAILS_FIND_AGY_LOC_FEED_DETAILS");
		ArrayList<AgyLocFeedDetails> returnList = null;
		try {
			returnList = (ArrayList<AgyLocFeedDetails>) namedParameterJdbcTemplate.query(sql,
					createParams("AgLocId", (AgLocId)),
					new BeanPropertyRowMapper<AgyLocFeedDetails>(AgyLocFeedDetails.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " agyLocFeedDetailsExecuteQuery() ", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAgyLocRecordGroup() {
		final String sql = getQuery("OIMROUTE_FIND_RGAGYLOC");
		List<ReferenceCodes> agylov = null;
		try {
			agylov = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgAgyLocRecordGroup() ", e);
		}
		return agylov;
	}
    /**
     * Used to calculate the no of stops in RouteStopDetails based on routeName
     *  
     * @param vRoute
     * 
     * 
     */
	@Override
	public Long stopsCur(String vRoute) {
		final String sql = getQuery("OIMROUTE_NO_STOPS");
		Long returnInteger = null;
		try {
			returnInteger = namedParameterJdbcTemplate.queryForObject(sql, createParams("vRoute", vRoute), Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " stopsCur() ", e);

		}
		return returnInteger;

	}

	/**
	 * Used to set the active flag of routes
	 * 
	 * @param vRoute
	 * 
	 */
	@Override
	public Integer activeCur(String vRoute) {
		final String sql = getQuery("OIMROUTE_ACTIVE_CUR");
		Integer returnInteger = null;
		try {
			returnInteger = namedParameterJdbcTemplate.queryForObject(sql, createParams("vRoute", vRoute),
					Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " activeCur() ", e);

		}
		return returnInteger;
	}
   /**
    * Used to set the stop location of routes
    * 
    * @param vRoute
    * 
    */
	@Override
	public String stopLocation(String vRoute) {
		final String sql = getQuery("OIMROUTE_STOP_LOCATION");
		String stoplocation = "";
		try {
			stoplocation = namedParameterJdbcTemplate.queryForObject(sql, createParams("vRoute", vRoute), String.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " stopLocation() ", e);
		}
		return stoplocation;
	}
    /**
     * Used to set the start location of routes
     * 
     * @param vRoute
     * 
     */
	@Override
	public String startLoc(String vRoute) {
		final String sql = getQuery("OIMROUTE_START_LOCATION");
		String startlocation = "";
		try {
			startlocation = namedParameterJdbcTemplate.queryForObject(sql, createParams("vRoute", vRoute),
					String.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " startLoc() ", e);
		}
		return startlocation;
	}

	/**
	 * Used to update the routes from routes stop commit
	 * 
	 * @param route
	 * 
	 * 
	 */
	@Override
	public Integer routesUpdateRoutes1(List<Routes> route) {
		String sql = getQuery("OIMROUTE_ROUTES_UPDATE_ROUTES1");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Routes routes : route) {
			parameters.add(new BeanPropertySqlParameterSource(routes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routesUpdateRoutes1() ", e);
		}
		if (route.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

}
