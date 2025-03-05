package net.syscon.s4.pkgs.tag_header.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.tag_header.TagHeaderPrisonLocationRepository;

@Repository
public class TagHeaderPrisonLocationRepositoryImpl extends RepositoryBase  implements TagHeaderPrisonLocationRepository{

	Logger logger = LogManager.getLogger(TagHeaderPrisonLocationRepositoryImpl.class);

	private final Map<String, FieldMapper> agencyInternalLocationsMapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_CODE",             new FieldMapper("internalLocationCode"))
			.put("PARENT_INTERNAL_LOCATION_ID",        new FieldMapper("parentInternalLocationId")).build();

	@Override
	public String getDescCur(String agyLocId) {
		final String sqlname = getQuery("TAG_HEADER_GET_DESC_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("agyLocId", agyLocId), String.class);
		} catch (final Exception e) {
			logger.error("cnoteCur", e);
			return null;
		}
	}

	@Override
	public Long checkMultiLocsCur(Long offenderBookId) {
		final String sqlname = getQuery("TAG_HEADER_CHECK_MULTI_LOCS_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId",offenderBookId), Long.class);
		} catch (final Exception e) {
			logger.error("cnoteCur", e);
			return null;
		}
	}

	@Override
	public String commAgyCur(Long offenderBookId) {
		final String sqlname = getQuery("TAG_HEADER_COMM_AGY_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId), String.class);
		} catch (final Exception e) {
			logger.error("cnoteCur", e);
			return null;
		}
	}

	@Override
	public String getCommStaffCur(Long offenderBookId) {
		final String sqlname = getQuery("TAG_HEADER_GET_COMM_STAFF_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId), String.class);
		} catch (final Exception e) {
			logger.error("cnoteCur", e);
			return null;
		}
	}

	public String getInstStaffCur(Long offenderBookId) {
		final String sqlname = getQuery("TAG_HEADER_GET_INST_STAFF_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId), String.class);
		} catch (final Exception e) {
			logger.error("cnoteCur", e);
			return null;
		}
	}

	@Override
	public List<AgencyInternalLocations> getTagIntLocIntLocPath(Integer internalLocationId) {
		final String sql = getQuery("TAG_HEADER_TAG_INT_LOC_INT_LOC_PATH");
		final RowMapper<AgencyInternalLocations> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapper);
		List<AgencyInternalLocations> refList = new ArrayList<AgencyInternalLocations>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("internalLocationId", internalLocationId), rowMapper);
		} catch (Exception e) {
			logger.error("rgIdentifierTypeRecordGroup", e);
		}
		return refList;


	}

}
