package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.proposedmovements.OidpeximRepository;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedMvmnts;
import net.syscon.s4.inst.property.impl.OidvcontRepositoryImpl;


/**
 * 
 * Class OidpeximRepositoryImpl
 * 
 */
@Repository
public class OidpeximRepositoryImpl extends RepositoryBase implements OidpeximRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpeximRepositoryImpl.class.getName());


	/**
	 * Fetch the records from database table
	 *
	 * @param objOffenderProposedMvmnts OffenderProposedMvmnts
	 *
	 * @return List<OffenderProposedMvmnts> 
	 * @throws SQLException
	 */
	@Override
	public List<OffenderProposedMvmnts> propMoveExecuteQuery(OffenderProposedMvmnts Offpropmvts) {
		List<OffenderProposedMvmnts> returnList = new ArrayList<OffenderProposedMvmnts>();
		
		final String sql = getQuery("OIDPEXIM_PROPMOVE_FIND_OFFENDER_PROPOSED_MVMNTS");
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", Offpropmvts.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderProposedMvmnts>(OffenderProposedMvmnts.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " propMoveExecuteQuery() ", e);
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> fromAgyRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final String sql = getQuery("OIDPEXIM_FIND_FROM_AGY_RECORDGROUP");
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " fromAgyRecordGroup() ", e);
		}
		return returnList;
	}

	/**
	 * Fetch the Lov from database table
	 *
	 * @return List<ReferenceCodes>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgMoveReasonRecordGroup(final String movementType) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final String sql = getQuery("OIDPEXIM_FIND_RGMOVEREASON");
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("movementType", movementType),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgMoveReasonRecordGroup() ", e);
		}
		return returnList;
	}

	/**
	 * Fetch the Lov from database table
	 *
	 * @return List<ReferenceCodes>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final String sql = getQuery("OIDPEXIM_FIND_RGMOVETYPE");
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgMoveTypeRecordGroup() ", e);
		}
		return returnList;
	}

	/**
	 * Fetch the Lov from database table
	 *
	 * @return List<ReferenceCodes>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<ReferenceCodes> rgAgyRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final String sql = getQuery("OIDPEXIM_FIND_RGAGY");
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " rgAgyRecordGroup() ", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param insertList List<OffenderProposedMvmnts>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */

	@Override
	public Integer proposedMvmntsInsert(final List<OffenderProposedMvmnts> insertList) {
	
		String sql = getQuery("OIDPEXIM_PROPOSED_MVMNTS_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (OffenderProposedMvmnts obj : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(obj));
				
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " proposedMvmntsInsert in error ");
		    }
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer proposedMvmntsUpdate(final List<OffenderProposedMvmnts> updateList) {
		int insertCount = 0;
		String sql = getQuery("OIDPEXIM_PROPOSED_MVMNTS_UPDATE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderProposedMvmnts proposedMvmntsUpdate : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(proposedMvmntsUpdate));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " proposedMvmntsUpdate in error ");
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffenderExternalMovements> getExtInCur() {
		List<OffenderExternalMovements> returnList = new ArrayList<OffenderExternalMovements>();
		final String sql = getQuery("OIDPEXIM_GET_EXTIN_CUR");
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getExtInCur() ", e);
		}
		return returnList;

	}

	@Override
	public List<OffenderExternalMovements> offExtMovExecuteQuery(OffenderExternalMovements Offextmvts) {
		List<OffenderExternalMovements> returnList = new ArrayList<OffenderExternalMovements>();
		final String sql = getQuery("OIDPEXIM_OFFENDER_EXTERNAL_MVMNTS");
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", Offextmvts.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " offExtMovExecuteQuery() ", e);
		}
		return returnList;
	}

}
