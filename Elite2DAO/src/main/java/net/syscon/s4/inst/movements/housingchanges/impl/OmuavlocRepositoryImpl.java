package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.movements.housingchanges.OmuavlocRepository;
import net.syscon.s4.inst.movements.housingchanges.beans.VLivUnits;

/**
 * Class OmuavlocRepositoryImpl
 */
@Repository
public class OmuavlocRepositoryImpl extends RepositoryBase implements OmuavlocRepository {

	private final Map<String, FieldMapper> vLivUnitMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OmuavlocRepositoryImpl class Object
	 */
	public OmuavlocRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VLivUnit
	 *
	 * @return List<VLivUnit>
	 *
	 */
	public List<VLivUnits> livUnitExecuteQuery(final CaseloadAgencyLocations objSearchDao) {
		final String sql = getQuery("OMUAVLOC_LIVUNIT_FIND_V_LIV_UNIT");
		final RowMapper<VLivUnits> VLivUnitRowMapper = Row2BeanRowMapper.makeMapping(sql, VLivUnits.class,
				vLivUnitMapping);
		final ArrayList<VLivUnits> returnList = (ArrayList<VLivUnits>) namedParameterJdbcTemplate.query(sql,
				createParams("toAgyLocId", objSearchDao.getAgyLocId(), "caseloadId", objSearchDao.getCaseloadId()),
				VLivUnitRowMapper);
		return returnList;
	}

}
