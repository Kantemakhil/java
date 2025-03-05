package net.syscon.s4.inst.transportation.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.transportation.maintenance.OidfixadRepository;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssets;
import net.syscon.s4.inst.transportation.maintenance.beans.Vehicles;

/**
 * Class OidfixadRepositoryImpl
 * 
 * @author
 * @version 1.0
 */
@Repository
public class OidfixadRepositoryImpl extends RepositoryBase implements OidfixadRepository {

	private static Logger logger = LogManager.getLogger(OidgenstRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao FixedAssets
	 *
	 * @return List<FixedAssets>
	 *
	 * @throws SQLException
	 */
	public List<FixedAssets> faExecuteQuery(FixedAssets objSearchDao) {
		final String sql = getQuery("OIDFIXAD_FA_FIND_FIXED_ASSETS");
		ArrayList<FixedAssets> returnList = new ArrayList<FixedAssets>();
		try {
			returnList = (ArrayList<FixedAssets>) namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<FixedAssets>(FixedAssets.class));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " faExecuteQuery in error ");
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstFixedAssets List<FixedAssets>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */

	public Integer faInsertFixedAssets(final List<FixedAssets> lstFixedAssets) {
		String sql = getQuery("OIDFIXAD_FA_INSERT_FIXED_ASSETS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (FixedAssets fixedAssets : lstFixedAssets) {
				parameters.add(new BeanPropertySqlParameterSource(fixedAssets));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " faInsertFixedAssets in error ");
		}
		if (lstFixedAssets.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstFixedAssets List<FixedAssets>
	 *
	 * @throws SQLException
	 */
	public Integer faUpdateFixedAssets(final List<FixedAssets> lstFixedAssets) {
		String sql = getQuery("OIDFIXAD_FA_UPDATE_FIXED_ASSETS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (FixedAssets fixedAssets : lstFixedAssets) {
				parameters.add(new BeanPropertySqlParameterSource(fixedAssets));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " faUpdateFixedAssets in error ");
		}
		if (lstFixedAssets.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstFixedAssets List<FixedAssets>
	 *
	 * @throws SQLException
	 */
	public Integer faDeleteFixedAssets(final List<FixedAssets> lstFixedAssets) {
		String sql = getQuery("OIDFIXAD_FA_DELETE_FIXED_ASSETS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (FixedAssets fixedAssets : lstFixedAssets) {
				parameters.add(new BeanPropertySqlParameterSource(fixedAssets));
			}
			try {
				String tableName = "FIXED_ASSETS";
				String whereCondition = "ASSET_ID=:assetId";
				batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
			} catch (Exception e) {
				logger.error(e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("fxd_ast_per_f2")) {
				return 3;
			}
			if (error.contains("fxd_ast_per_f1")) {
				return 4;
			}
			if (error.contains("fxd_ast_corp_f2")) {
				return 5;
			}
			if (error.contains("fxd_ast_corp_f1")) {
				return 6;
			}
			if (error.contains("veh_mil_fxd_ast_f1")) {
				return 7;
			}
			if (error.contains("veh_fxd_ast_f1")) {
				return 8;
			}
			if (error.contains("fxd_as_fxd_ast_f1")) {
				return 9;
			}
			if (error.contains("fxd_ae_fxd_ast_f1")) {
				return 10;
			}
			if (error.contains("ast_loa_fxd_ast_f1")) {
				return 11;
			}if (error.contains("veh_mil_veh_f1")) {
				return 12;
			}
			if (error.contains("trp_veh_f1")) {
				return 13;
			}
		}
		if (lstFixedAssets.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Vehicles
	 *
	 * @return List<Vehicles>
	 *
	 * @throws SQLException
	 */
	public List<Vehicles> vehExecuteQuery(Vehicles objSearchDao) {
		final String sql = getQuery("OIDFIXAD_VEH_FIND_VEHICLES");
		ArrayList<Vehicles> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<Vehicles>) namedParameterJdbcTemplate.query(sql,
					createParams("assetId", objSearchDao.getAssetId()),
					new BeanPropertyRowMapper<Vehicles>(Vehicles.class));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " vehExecuteQuery in error ");
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVehicles List<Vehicles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer vehInsertVehicles(final List<Vehicles> lstVehicles) {
		String sql = getQuery("OIDFIXAD_VEH_INSERT_VEHICLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (Vehicles vehicles : lstVehicles) {
				parameters.add(new BeanPropertySqlParameterSource(vehicles));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " vehInsertVehicles in error ");
		}
		if (lstVehicles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVehicles List<Vehicles>
	 *
	 * @throws SQLException
	 */
	public Integer vehUpdateVehicles(final List<Vehicles> lstVehicles) {
		String sql = getQuery("OIDFIXAD_VEH_UPDATE_VEHICLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (Vehicles vehicles : lstVehicles) {
				parameters.add(new BeanPropertySqlParameterSource(vehicles));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " vehUpdateVehicles in error ");
		}
		if (lstVehicles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method will fetch the sequence of assetsId form DB.
	 */
	@Override
	public Long genAssetId(String seqId) {
		final String sql = getQuery("OIDFIXAD_GEN_ASSET_ID");
		long count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("seq", seqId), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " genAssetId in error ");
		}
		return count;
	}

	/**
	 * This method will fetch the Existing vehicleId present in DB
	 */
	@Override
	public Integer vehicleIdCount(Long vehicleId) {
		final String sql = getQuery("L_VEHICLE_ID_COUNT");
		int count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("vehicleId", vehicleId), int.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " vehicleIdCount in error ");
		}
		return count;
	}
	/**
	 * This method will fetch the vehicle assign to any other screen.
	 */

}