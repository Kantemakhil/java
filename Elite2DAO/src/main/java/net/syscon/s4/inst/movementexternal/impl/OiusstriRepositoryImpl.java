package net.syscon.s4.inst.movementexternal.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movementexternal.OiusstriRepository;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;

@Repository
public class OiusstriRepositoryImpl extends RepositoryBase implements OiusstriRepository {

	private static Logger logger = LogManager.getLogger(OiusstriRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objvOiusstri VOiusstri
	 *
	 * @return List<VOiusstri>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VOiusstri> scheduledtripsExecuteQuery(VOiusstri objvOiusstri) {
		final String sql = getQuery("OIUSSTRI_GET_SCHEDULED_TRIPS_DATA");
		ArrayList<VOiusstri> returnList = null;
		try {
			returnList = (ArrayList<VOiusstri>) namedParameterJdbcTemplate.query(sql,
					createParams("fromDepartureDate", objvOiusstri.getFromDate(), "toDepartureDate",
							objvOiusstri.getToDate(),"departureDate", objvOiusstri.getFromDate()),
					new BeanPropertyRowMapper<VOiusstri>(VOiusstri.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " scheduledtripsExecuteQuery() ", e);
		}
		return returnList;
	}

}
