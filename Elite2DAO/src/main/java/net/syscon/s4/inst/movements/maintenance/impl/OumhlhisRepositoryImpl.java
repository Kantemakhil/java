package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.maintenance.OumhlhisRepository;

/**
 * Class OumhlhisRepositoryImpl
 */
@Repository
public class OumhlhisRepositoryImpl extends RepositoryBase implements OumhlhisRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumhlhisRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> livingUntsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LEVEL_2_CODE",                           new FieldMapper("level2Code"))
			.put("LEVEL_1_CODE",                           new FieldMapper("level1Code"))
			.put("LEVEL_4_CODE",                          new FieldMapper("level4Code"))
			.put("LEVEL_3_CODE",                           new FieldMapper("level3Code"))
			.put("LEVEL_1_ID",                           new FieldMapper("level1Id"))
			.put("LEVEL_2_ID",                           new FieldMapper("level2Id"))
			.put("LEVEL_3_ID",                          new FieldMapper("level3Id"))
			.put("LEVEL_4_ID",                           new FieldMapper("level4Id"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE",                   new FieldMapper("livingUnitCode"))
			.put("ACTIVE_FLAG", 					   new FieldMapper("activeFlag"))
			.build();

	/**
	 * Creates new OumhlhisRepositoryImpl class Object
	 */
	public OumhlhisRepositoryImpl() {
	}


	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livingUnitOneRgRecordGroup(final String agyLocId) {
		final String sql = getQuery("OUMHLHIS_FIND_LIVINGUNIT1RG");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID",agyLocId), mRowMapper);
		} catch (Exception e) {

			logger.error("livingUnitOneRgRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livingUnitTwoRgRecordGroup(final Long livingUnitId, final String level1Code) {
		final String sql = getQuery("OUMHLHIS_FIND_LIVINGUNIT2RG");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("LEVEL1ID", livingUnitId,"AGY_LOC_ID",level1Code), mRowMapper);
		} catch (Exception e) {

			logger.error("livingUnitTwoRgRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livingUnitThreeRgRecordGroup(final Long livingUnitId, final String level2Code) {
		final String sql = getQuery("OUMHLHIS_FIND_LIVINGUNIT3RG");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("LEVEL2ID", livingUnitId,"AGY_LOC_ID",level2Code), mRowMapper);
		} catch (Exception e) {

			logger.error("livingUnitThreeRgRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livingUnitFourRgRecordGroup(final Long livingUnitId, final String level3Code) {
		final String sql = getQuery("OUMHLHIS_FIND_LIVINGUNIT4RG");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("LEVEL3ID", livingUnitId,"AGY_LOC_ID",level3Code), mRowMapper);

		} catch (Exception e) {
			logger.error("livingUnitFourRgRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setLivingUnitLevels
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> setLivingUnitLevels(final LivingUnits paramBean) {
		final String sql = getQuery("OUMHLHIS_SET_LIVING_UNIT_LEVELS");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUntsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("setLivingUnitLevels", e);
		}
		return returnList;
	}

	/**
	 * This method is getAgyLocIdDesc
	 * 
	 * setLivingUnitLevels
	 *
	 * @param params
	 */

	@Override
	public String getAgyLocIdDesc(final String userId) {
		final String sql = getQuery("OUMHLHIS_CASELOAD_ID");
		String returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
		} catch (Exception e) {
			logger.error("getEscSeq", e);
		}
		return returnArray;
	}

	/**
	 * This method is getAgyLocIdDesc
	 * 
	 * setLivingUnitLevels
	 *
	 * @param params
	 */
	@Override
	public String getAgyLocIdDescReturn(final String userId) {
		final String sql = getQuery("OUMHLHIS_CASELOAD_CODE");
		final String caseloadId = getAgyLocIdDesc(userId);
		String returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOADID", caseloadId),
					String.class);
		} catch (Exception e) {
			logger.error("getEscSeq", e);
		}
		return returnArray;
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setLivingUnitLevels
	 *
	 * @param params
	 *
	 */
	public List<LivingUnits> getLivingunitId(final String agyLocId) {
		final String sql = getQuery("OUMHLHIS_GET_LIVING_UNIT_ID");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUntsMapping);
		List<LivingUnits> returnList = new ArrayList<>();
		String agLocId=caseLoad(agyLocId);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID",agLocId), columnRowMapper);
		} catch (Exception e) {
			logger.error("setLivingUnitLevels", e);
		}
		return returnList;
	}

	private String caseLoad(String desxcription) {
		final String sql=getQuery("GETTING_CASELOAD_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DESCRIPTION",desxcription.toUpperCase()), String.class);
	}
	
}
