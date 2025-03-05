package net.syscon.s4.inst.transportation.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.OimrouteRepository;
import net.syscon.s4.inst.transportation.maintenance.OimrouteService;
import net.syscon.s4.inst.transportation.maintenance.beans.AgyLocFeedDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetailsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Routes;
import net.syscon.s4.inst.transportation.maintenance.beans.RoutesCommitBean;
import net.syscon.s4.pkgs.tag_transport.TagTransportService;

/**
 * Class OimrouteServiceImpl
 */
@Service
public class OimrouteServiceImpl implements OimrouteService {
	
	private static Logger logger = LogManager.getLogger(OimrouteServiceImpl.class.getName());
	@Autowired
	private OimrouteRepository oimrouteRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private TagTransportService tagTransportService;

	/**
	 * Fetch the records from database table
	 *
	 *
	 * @throws SQLException
	 */
	public List<Routes> routesExecuteQuery() {
		return oimrouteRepository.routesExecuteQuery();
	}

	/**
	 * Insert,update and delete the records in database table
	 *
	 * @param commitBean
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer routesCommit(RoutesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			try {
				liReturn = oimrouteRepository.routesInsertRoutes(commitBean.getInsertList());
			}
			catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " routesCommit() insert ", e);
			}
			
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			try {
				liReturn = oimrouteRepository.routesUpdateRoutes(commitBean.getUpdateList());
			}
			catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " routesCommit() update ", e);
			}
			
		}
		return liReturn;
	}

	/**
	 * Fetch the records in database table
	 *
	 * @param routeName
	 *
	 * @throws SQLException
	 */
	public List<RouteStopDetails> routeStopDetailsExecuteQuery(String routeName) {
		List<RouteStopDetails> returnlist=null;
		try {
			returnlist= oimrouteRepository.routeStopDetailsExecuteQuery(routeName);
		}
		catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsExecuteQuery()  ", e);
		}
		return returnlist;

	}

	/**
	 * Insert,update and delete the records in database table
	 *
	 * @param commitBean
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer routeStopDetailsCommit(RouteStopDetailsCommitBean commitBean) {
		int liReturn = 0;
		String userName = "";
		String vRoute = "";
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			vRoute = commitBean.getInsertList().get(0).getRouteName();
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			userName = commitBean.getInsertList().get(0).getCreateUserId();
			try {
				liReturn = oimrouteRepository.routeStopDetailsInsertRouteStopDetails(commitBean.getInsertList());
			}
			catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsCommit() insert ", e);
				liReturn=0;
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			vRoute = commitBean.getUpdateList().get(0).getRouteName();
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			userName = commitBean.getUpdateList().get(0).getModifyUserId();
			try {
				liReturn = oimrouteRepository.routeStopDetailsUpdateRouteStopDetails(commitBean.getUpdateList());
			}
			catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsCommit() update ", e);
				liReturn=0;
			}
			
		}
     
		if (liReturn == 1) {
			try {
			routeUpdateViaRouteStopDetails(vRoute, userName);
			tagTransportService.pAgencySegmentLengths(vRoute, userName);
			tagTransportService.updateScheduledTrips(vRoute, userName);
			}
			catch(Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " routeStopDetailsCommit() ", e);
				liReturn=0;
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param AgLocId
	 *
	 * @throws SQLException
	 */
	public List<AgyLocFeedDetails> agyLocFeedDetailsExecuteQuery(String AgLocId) {
		List<AgyLocFeedDetails> returnlist=null;
		try {
			returnlist= oimrouteRepository.agyLocFeedDetailsExecuteQuery(AgLocId);
		}
		catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " agyLocFeedDetailsExecuteQuery() ", e);
		}
        return returnlist;
	}

	public Integer routeUpdateViaRouteStopDetails(String vRoute, String userName) {
        int returnval=0;
		Routes r = new Routes();
      try {
  		Long stopCur = oimrouteRepository.stopsCur(vRoute);
  		Integer activeCur = oimrouteRepository.activeCur(vRoute);
  		String stopLocation = oimrouteRepository.stopLocation(vRoute);
  		String startLoc = oimrouteRepository.startLoc(vRoute);
  		eliteDateService.getDBTime();
  		r.setNoStops(new BigDecimal(stopCur));

  		if (stopCur != null && stopCur < 0) {
  			r.setNoStops(BigDecimal.ZERO);
  		}

  		if (activeCur > 0) {
  			r.setActiveFlag("N");
  			r.setExpiryDate(eliteDateService.getDBTime());
  		} else {
  			r.setActiveFlag("Y");
  			r.setExpiryDate(null);
  		}
  		r.setStopAgyLocId(stopLocation);

  		r.setStartAgyLocId(startLoc);
  		r.setRouteName(vRoute);
  		r.setModifyUserId(userName);
  		List<Routes> listroute = new ArrayList<Routes>();
  		listroute.add(r);
  		returnval= oimrouteRepository.routesUpdateRoutes1(listroute);
      }
      catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " routeUpdateViaRouteStopDetails() ", e);
      }
      return returnval;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAgyLocRecordGroup() {
		List<ReferenceCodes> returnlist=null;
		try {
			returnlist=oimrouteRepository.rgAgyLocRecordGroup();
		}
		catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgAgyLocRecordGroup() ", e);
		}
		return returnlist;

	}

}
