package net.syscon.s4.inst.transportation.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.transportation.maintenance.OimsglenRepository;
import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengths;

/**
 * Class OimsglenRepositoryImpl
 */
@Repository
public class OimsglenRepositoryImpl extends RepositoryBase implements OimsglenRepository {

	private static Logger logger = LogManager.getLogger(OimsglenRepositoryImpl.class.getName());

	/**
	 * Creates new OimsglenRepositoryImpl class Object
	 */
	public OimsglenRepositoryImpl() {
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AgencySegmentLengths
	 *
	 * @return List<AgencySegmentLengths>
	 *
	 * @throws SQLException
	 */
	public List<AgencySegmentLengths> agencySegmentLengthsExecuteQuery(AgencySegmentLengths objSearchDao) {
		final String sql = getQuery("OIMSGLEN_AGENCYSEGMENTLENGTHS_FIND_AGENCY_SEGMENT_LENGTHS");
		final ArrayList<AgencySegmentLengths> returnList = (ArrayList<AgencySegmentLengths>) namedParameterJdbcTemplate
				.query(sql, createParams(),
						new BeanPropertyRowMapper<AgencySegmentLengths>(AgencySegmentLengths.class));
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencySegmentLengths List<AgencySegmentLengths>
	 *
	 * @throws SQLException
	 */
	public Integer agencySegmentLengthsUpdateAgencySegmentLengths(
			final List<AgencySegmentLengths> lstAgencySegmentLengths) {
		String sql = getQuery("OIMSGLEN_AGENCYSEGMENTLENGTHS_UPDATE_AGENCY_SEGMENT_LENGTHS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		
		for (AgencySegmentLengths agencySegmentLengths : lstAgencySegmentLengths) {
			parameters.add(new BeanPropertySqlParameterSource(agencySegmentLengths));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " agencySegmentLengthsUpdateAgencySegmentLengths");
		}
		if (lstAgencySegmentLengths.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

}
