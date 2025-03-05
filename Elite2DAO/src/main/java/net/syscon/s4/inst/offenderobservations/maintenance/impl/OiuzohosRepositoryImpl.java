package net.syscon.s4.inst.offenderobservations.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.offenderobservations.maintenance.OiuzohosRepository;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;

@Repository
public class OiuzohosRepositoryImpl extends RepositoryBase implements OiuzohosRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuzohosRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	@Override
	public List<ReferenceCodes> rgUnitTypeRecordGroup() {
		final String sql = getQuery("GET_UNIT_TYPE_LOV");
		List<ReferenceCodes> list = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, mRowMapper);
		} catch (Exception e) {
			logger.error("In rgUnitTypeRecordGroup method : ", e);
		}
		return list;
	}

	@Override
	public List<LivingUnits> rgLevel1LovData(final String unitTypeValue,  final String facility) {
		final String sql = getQuery("GET_LEVEL1_DATA");
		List<LivingUnits> list = new ArrayList<LivingUnits>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,createParams("AGYLOCID",facility,"HOUSINGUNITTYPE",unitTypeValue) ,mRowMapper);
		} catch (Exception e) {
			logger.error("In rgLevel1LovData method : ", e);
		}
		return list;
	}

	@Override
	public List<LivingUnits> rgLevel2LovData(Integer livigUnitId) {
		final String sql = getQuery("GET_LEVEL2_DATA");
		List<LivingUnits> list = new ArrayList<LivingUnits>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,createParams("LIVINGUNITID",livigUnitId) ,mRowMapper);
		} catch (Exception e) {
			logger.error("In rgLevel2LovData method : ", e);
		}
		return list;
	}

	@Override
	public List<LivingUnits> rgLevel3LovData(Integer parentLivigUnitId) {
		final String sql = getQuery("GET_LEVEL3_DATA");
		List<LivingUnits> list = new ArrayList<LivingUnits>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,createParams("LIVINGUNITID",parentLivigUnitId) ,mRowMapper);
		} catch (Exception e) {
			logger.error("In rgLevel3LovData method : ", e);
		}
		return list;
	}
	
	@Override
	public List<LivingUnits> getLevel4Data(Integer parentLivigUnitId) {
		final String sql = getQuery("GET_LEVEL4_DATA");
		List<LivingUnits> list = new ArrayList<LivingUnits>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,createParams("LIVINGUNITID",parentLivigUnitId) ,mRowMapper);
		} catch (Exception e) {
			logger.error("In getLevel4Data method : ", e);
		}
		return list;
	}

	@Override
	public List<LivingUnits> housingDetailsExecuteQuery(LivingUnits searchBean) {
		String sql = getQuery("GET_LEVEL_HOUSING_LOCATIONS_DATA_BASED_LOV_CODE");
		List<LivingUnits> list = new ArrayList<LivingUnits>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		
		try {
			List<LivingUnits> livingUnitCount = getLivingUnit(searchBean.getLivingUnitId());
			if(searchBean.getHousingUnitType() != null && searchBean.getLivingUnitId() == null) {
				sql = sql.replace("#WHERECOND", " and (coalesce(:housingUnitType , '') = '' or  housing_unit_type  = :housingUnitType) ");
			}else {
				sql = sql.replace("#WHERECOND", " ");
			} 
			if(livingUnitCount != null && livingUnitCount.size() == 1 && livingUnitCount.get(0).getParentLivingUnitId() == null) {
				sql = sql.replace("where_cond", "living_unit_id");
			}else {
				sql = sql.replace("where_cond", "parent_living_unit_id");
				
			}
			list = namedParameterJdbcTemplate.query(sql,createParams("livingUnitId", searchBean.getLivingUnitId(),"housingUnitType",searchBean.getHousingUnitType(),"agyLocId",searchBean.getAgyLocId()) ,mRowMapper);
		} catch (Exception e) {
			logger.error("In rgLevel1LovData method : ", e);
		}
		return list;
	}
	
	@Override
	public List<LivingUnits> getLivingUnit(BigDecimal livingUnit) {
		String sql = getQuery("GET_LIVING_UNIT_COUNT");
		List<LivingUnits> list = new ArrayList<LivingUnits>();
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,createParams("livingUnitId", livingUnit) ,mRowMapper);
		} catch (Exception e) {
			logger.error("In rgLevel1LovData method : ", e);
		}
		return list;
	}

	@Override
	public Integer getZoneAssignedCount(OffObsZoneDetails searchBean) {
		final String sql = getQuery("OIUZOHOS_GET_INETRANAL_LOCATION_COUNT");
		Integer returnValue = 0;
		try {		
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("internalLocationId",
					searchBean.getInternalLocationId()), Integer.class);
		} catch(Exception e) {
			logger.error("Exception  in " + this.getClass().getName() + " getZoneAssignedCount ", e);
			return returnValue;
		}
		return returnValue;
	}

}
