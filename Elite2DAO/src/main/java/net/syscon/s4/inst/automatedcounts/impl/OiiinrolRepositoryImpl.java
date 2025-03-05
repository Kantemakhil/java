package net.syscon.s4.inst.automatedcounts.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VIntLocOffenders;
import net.syscon.s4.inst.automatedcounts.OiiinrolRepository;

@Repository
public class OiiinrolRepositoryImpl extends RepositoryBase implements OiiinrolRepository{


private final Map<String, FieldMapper> vIntLocOffendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("INTERNAL_LOCATION_DESC", 						new FieldMapper("internalLocationDesc"))
.put("ALERT_FLAG", 						new FieldMapper("alertFlag"))
.put("LIVING_UNIT_ID", 						new FieldMapper("livingUnitId"))
.put("LIVING_UNIT_DESC", 						new FieldMapper("livingUnitDesc"))
.put("IN_OUT_STATUS", 						new FieldMapper("inOutStatus"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("ROOT_INTERNAL_LOCATION_ID", 						new FieldMapper("rootInternalLocationId"))
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("INTERNAL_LOCATION_ID", 						new FieldMapper("internalLocationId"))
.put("PARENT_INTERNAL_LOCATION_ID", 						new FieldMapper("parentInternalLocationId"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.build();

/**
 * Creates new OiiinrolRepositoryImpl class Object 
 */
public OiiinrolRepositoryImpl() {

}

/**
* Fetch the records from database table
*
* @param objSearchDao VIntLocOffenders
*
* @return List<VIntLocOffenders>
*
*/
 public List<VIntLocOffenders> rollListExecuteQuery(final String agyLocId, final Integer internalLocationId) {
		final String sql = getQuery("OIIINROL_ROLLLIST_FIND_V_INT_LOC_OFFENDERS");
		final RowMapper<VIntLocOffenders> VIntLocOffendersRowMapper = Row2BeanRowMapper.makeMapping(sql, VIntLocOffenders.class,vIntLocOffendersMapping);
		final List<VIntLocOffenders> returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId, "internallocationid", internalLocationId), VIntLocOffendersRowMapper);
		return returnList;
} 
/**
* @param 
*
*
*/
public int PRE_INSERT() throws SQLException {
return 0;
}

}
