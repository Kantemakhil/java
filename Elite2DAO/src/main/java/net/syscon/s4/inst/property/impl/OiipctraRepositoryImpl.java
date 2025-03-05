package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.PropertyStorages;
import net.syscon.s4.inst.property.OiipctraRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Class OiipctraRepositoryImpl
 */
@Repository
public class OiipctraRepositoryImpl extends RepositoryBase implements OiipctraRepository {

	/**
	 * Creates new OiipctraRepositoryImpl class Object
	 */
	public OiipctraRepositoryImpl() {
	}

	private final Map<String, FieldMapper> propertyStoragesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRN_FROM_AGY_LOC_ID", new FieldMapper("trnFromAgyLocId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("TRN_TO_AGY_LOC_ID", new FieldMapper("trnToAgyLocId"))
			.put("DESCRIPTION", new FieldMapper(" description "))
			.build();
	private final Map<String, FieldMapper> offenderPptyConTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentInternalLocationId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> sysDualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyContainers
	 *
	 * @return List<OffenderPptyContainers>
	 *
	 * @
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers objSearchDao) {
		final String sql = getQuery("OIIPCTRA_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS");
		List<OffenderPptyContainers> returnList;
		final RowMapper<OffenderPptyContainers> OffenderPptyContainersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), OffenderPptyContainersRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyConTxns
	 *
	 * @return List<OffenderPptyConTxns>
	 *
	 * @
	 */
	public List<OffenderPptyConTxns> conTxExecuteQuery(final OffenderPptyConTxns objSearchDao) {
		final String sql = getQuery("OIIPCTRA_CONTX_FIND_OFFENDER_PPTY_CON_TXNS");
		List<OffenderPptyConTxns> returnList;
		final RowMapper<OffenderPptyConTxns> OffenderPptyConTxnsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyConTxns.class, offenderPptyConTxnsMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("propertyContainerId", objSearchDao.getPropertyContainerId()),
				OffenderPptyConTxnsRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vPheadOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		final String sql = getQuery("OIIPCTRA_V_PHEAD_ONCHECKDELETEMASTER");
		List<OffenderPptyContainers> returnList;
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offConPostQuery
	 *
	 * @param params
	 *
	 */
	public AgencyLocations offConPostQuery(final AgencyLocations paramBean) {
		final String sql = getQuery("OIIPCTRA_OFF_CON_POSTQUERY");
		AgencyLocations returnList;
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGYLOCID", paramBean.getAgyLocId()), columnRowMapper);
		} catch (Exception e) {
			returnList = new AgencyLocations();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * conTxPostQuery
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> conTxPostQuery(final AgencyLocations paramBean) {
		final String sql = getQuery("OIIPCTRA_CON_TX_POSTQUERY");
		List<AgencyLocations> returnList;
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConPpty
	 *
	 * @param params
	 *
	 */
	public List<PropertyStorages> cgfkchkOffConOffConPpty(final PropertyStorages paramBean) {
		final String sql = getQuery("OIIPCTRA_CGFKCHK_OFF_CON_OFF_CON_PPTY_");
		List<PropertyStorages> returnList;
		final RowMapper<PropertyStorages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, PropertyStorages.class,
				propertyStoragesMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public AgencyInternalLocations cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIIPCTRA_CGFKCHK_OFF_CON_OFF_CON_PPTY");
		AgencyInternalLocations returnObj = new AgencyInternalLocations();
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new AgencyInternalLocations();
		}
		return returnObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodesIntLocUsageLocationsInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> rgLocationAllRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIIPCTRA_FIND_RGSTORELOCATION");
		final String enterQuery = "ENTER-QUERY";
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyInternalLocations> internalLocUsageMapping = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("MODE", enterQuery, "CASELOADID", caseloadId), internalLocUsageMapping);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;
	}
}
