package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.proposedmovements.OiusanctRepository;

@Repository
public class OiusanctRepositoryImpl extends RepositoryBase implements OiusanctRepository {

	/**
	 * Creates new OiusanctRepositoryImpl class Object
	 */
	private static Logger logger = LogManager.getLogger(OiusanctRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderOicSanctions
	 *
	 * @return List<OffenderOicSanctions>
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicSanctions> offenderOicSanctionsExecuteQuery(OffenderOicSanctions objSearchDao) {
		ArrayList<OffenderOicSanctions> returnList = new ArrayList<OffenderOicSanctions>();
		try {
			final String sql = getQuery("OIUSANCT_OFFENDEROICSANCTIONS_FIND_OFFENDER_OIC_SANCTIONS");
			returnList = (ArrayList<OffenderOicSanctions>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderOicSanctions>(OffenderOicSanctions.class));
		} catch (DataAccessException e) {
			logger.error("Exception occured in " + this.getClass().getName() + " offenderOicSanctionsExecuteQuery() ",
					e);
		}
		return returnList;
	}

}
