package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legalscreens.maintenance.OumhocodRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;

/**
 * Class OumhocodRepositoryImpl
 */
@Repository
public class OumhocodRepositoryImpl extends RepositoryBase implements OumhocodRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimlegstRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> hoCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("HO_CODE", 						new FieldMapper("hoCode"))
			.put("CREATE_DATE", 						new FieldMapper("createDate"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();

	/**
	 * Creates new OumhocodRepositoryImpl class Object
	 */
	public OumhocodRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            HoCodes
	 *
	 * @return List<HoCodes>
	 */

	public List<HoCodes> hoCodesExecuteQuery(final HoCodes objSearchDao) {
		final String sql = getQuery("OUMHOCOD_HOCODES_FIND_HO_CODES");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getHoCode() != null && !objSearchDao.getHoCode().isEmpty() && !objSearchDao.getHoCode().trim().equals("")) {
				pSql.append(" HO_CODE LIKE :hoCode  AND ");
				param.addValue("hoCode", objSearchDao.getHoCode().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty() && !objSearchDao.getDescription().trim().equals("")) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if (objSearchDao.getActiveFlag() == "true") {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				pSql.append(" ACTIVE_FLAG LIKE :activeFlag AND ");
				param.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				pSql.append(" EXPIRY_DATE = :expiryDate AND ");
				param.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by ho_code ");
		final RowMapper<HoCodes> HoCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, HoCodes.class, hoCodesMapping);
		ArrayList<HoCodes> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<HoCodes>) namedParameterJdbcTemplate.query(preparedSql, param, HoCodesRowMapper);
		} catch (Exception e) {
			logger.error("hoCodesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstHoCodes
	 *            List<HoCodes>
	 *
	 * @return List<Integer>
	 */
	public Integer hoCodesInsertHoCodes(final List<HoCodes> lstHoCodes) {
		final String sql = getQuery("OUMHOCOD_HOCODES_INSERT_HO_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoCodes hoCodes : lstHoCodes) {
			parameters.add(new BeanPropertySqlParameterSource(hoCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstHoCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstHoCodes
	 *            List<HoCodes>
	 */
	public Integer hoCodesUpdateHoCodes(final List<HoCodes> lstHoCodes) {
		final String sql = getQuery("OUMHOCOD_HOCODES_UPDATE_HO_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoCodes hoCodes : lstHoCodes) {
			parameters.add(new BeanPropertySqlParameterSource(hoCodes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstHoCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstHoCodes
	 *            List<HoCodes>
	 */
	public Integer hoCodesDeleteHoCodes(final List<HoCodes> lstHoCodes) {
		final String sql = getQuery("OUMHOCOD_HOCODES_DELETE_HO_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoCodes hoCodes : lstHoCodes) {
			parameters.add(new BeanPropertySqlParameterSource(hoCodes));
		}
		try {
			batchUpdatePreDeletedRows("HO_CODES", "HO_CODE  = :hoCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in hoCodesDeleteHoCodes"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstHoCodes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * hoCodesKeyDelrec
	 *
	 * @param params
	 */
	@Override
	public Integer hoCodesCheckDeleteMaster(final HoCodes searchBean) {
		final String sql = getQuery("OUMHOCOD_HO_CODES_KEYDELREC");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("HOCODE", searchBean.getHoCode()),
				Integer.class);
		return returnValue;
	}

}
