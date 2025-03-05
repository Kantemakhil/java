package net.syscon.s4.pkgs.oimmholo.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.pkgs.oimmholo.OimmholoPkgRepository;

@Repository
public class OimmholoPkgRepositoryImpl extends RepositoryBase implements OimmholoPkgRepository {

	private static Logger logger = LogManager.getLogger(OimmholoPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LEVEL", new FieldMapper("level")).put("CAPACITY", new FieldMapper("capacity"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId")).build();

	@Override
	public String getNLuType(final Integer pLevel, final String pAgyLocId) {
		final String sql = getQuery("GET_HOUS_LVL_CODE_CUR");
		String type = "";
		try {
			type = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_LEVEL", pLevel, "P_AGY_LOC_ID", pAgyLocId), String.class);
		} catch (Exception e) {
			logger.error("getNLuType :" + e);
			type = null;
		}
		return type;
	}

	@Override
	public String getDefaultLuDesc(final BigDecimal pParentLuId, final String pLivingUnitCode) {
		final String sql = getQuery("GET_PARENT_DESC_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_LIVING_UNIT_CODE", pLivingUnitCode, "P_PARENT_LU_ID", pParentLuId), String.class);
	}

	// This CURSOR get_all_levels_cur is migrated from oracle
	@Override
	public List<LivingUnits> getLivingUnitsDetails(final BigDecimal livingUnitId) {
		final String sql = getQuery("GET_ALL_LEVELS_CUR");
		final RowMapper<LivingUnits> rowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_LIVING_UNIT_ID", livingUnitId), rowMapper);
	}

	// This function is migrated from oracle
	// This CURSOR get_child_totals is migrated from oracle
	@Override
	public LivingUnits getChildTotals(final BigDecimal pParentLuId) {
		final String sql = getQuery("GET_CHILD_TOTALS");
		LivingUnits retObj = new LivingUnits();
		final RowMapper<Object[]> rowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, livingUnitsMapping);
		Object[] args = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PARENT_LU_ID", pParentLuId),
				new RowMapper<Object[]>() {

					@Override
					public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						Object[] retObj = new Object[3];
						retObj[0] = rs.getInt(1);
						retObj[1] = rs.getInt(2);
						retObj[2] = rs.getInt(3);
						return retObj;
					}

				});
		if (args != null) {
			retObj.setCapacity(new BigDecimal((Integer) args[0]));
			retObj.setCnaNo(new BigDecimal((Integer) args[1]));
			retObj.setOperationCapacity(new BigDecimal((Integer) args[2]));
		}
		return retObj;
	}

	// This function is migrated from oracle
	// This CURSOR get_liv_unit_upd_cur is migrated from oracle
	@Override
	public List<LivingUnits> getLivUnitUpdCur(final BigDecimal livingUnitId) {
		final String sql = getQuery("GET_LIV_UNIT_UPD_CUR");
		final RowMapper<LivingUnits> rowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_LIV_UNIT_ID", livingUnitId), rowMapper);

	}

	// This function is migrated from oracle
	// This CURSOR get_liv_unit_upd_cur is migrated from oracle
	@Override
	public Integer updateLivingUnits(final BigDecimal lvCapacity, final BigDecimal lvCna,
			final BigDecimal operationCapacity, final BigDecimal livingUnitId, final String userName) {
		final String sql = getQuery("UPDATE_LIVING_UNITS");
		return namedParameterJdbcTemplate.update(sql, createParams("LV_CAPACITY", lvCapacity, "LV_CNA", lvCna,
				"P_LIV_UNIT_ID", livingUnitId, "operationCapacity", operationCapacity, "modifyUserId",userName));
	}

	/*
	 * This function is migrated from oracle This function updates attributes of
	 * child records of a living unit.
	 */
	@Override
	public List<Object[]> getAllAttributesCur(final BigDecimal parentLivingUnitId) {
		final String sql = getQuery("GET_ALL_ATTRIBUTES_CUR");
		final RowMapper<Object[]> rowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, livingUnitsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_parent_lu_id", parentLivingUnitId), rowMapper);
	}

	/*
	 * This function is migrated from oracle This function checks if an attribute
	 * exists in a living unit.
	 */
	@Override
	public Integer agyIntLocProfiles(final BigDecimal livingUnitId) {
		final String sql = getQuery("AGY_INT_LOC_PROFILES");
		return namedParameterJdbcTemplate.update(sql, createParams("p_lu_id", livingUnitId));
	}

	@Override
	public Integer livingUnitsUpdate(final LivingUnits object) {
		final String sql = getQuery("LIVING_UNITS_UPDATE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_active_flag", object.getActiveFlag(), "p_reason_code", object.getDeactivateReasonCode(),
						"p_parent_lu_id", object.getLivingUnitId(), "parent_living_unit_id",
						object.getParentLivingUnitId(),"modifyUserId",object.getModifyUserId()));
	}

	@Override
	public Integer livingUnitsUpdateElse(final LivingUnits object) {
		final String sql = getQuery("LIVING_UNITS_UPDATE_ELSE");
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_active_flag", object.getActiveFlag(), "p_reason_code", object.getDeactivateReasonCode(),
						"p_parent_lu_id", object.getLivingUnitId(), "parent_living_unit_id",
						object.getParentLivingUnitId(),"modifyUserId",object.getModifyUserId()));
	}
}
