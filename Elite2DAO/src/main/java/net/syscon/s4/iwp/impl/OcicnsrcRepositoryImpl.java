package net.syscon.s4.iwp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.iwp.OcicnsrcRepository;

@Repository
public class OcicnsrcRepositoryImpl extends RepositoryBase implements OcicnsrcRepository {

	private static Logger logger = LogManager.getLogger(OcdatpowRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("staffname", new FieldMapper("staffName")).put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	@Override
	public List<ReferenceCodes> getAllStaffNamesByCaseload(String caseload) {
		final String sql = getQuery("OCICNSRC_GET_ALL_STAFFNAMES_BY_CASELOAD");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				agyLocMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("workingcaseloadid", caseload), rowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  getAllStaffNamesByCaseload " + e);
		}
		return returnList;

	}

	@Override
	public List<ReferenceCodes> getAllFacilities(String caseload) {

		final String sql = getQuery("OCICNSRC_GET_ALL_FACILITIES");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();

		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				agyLocMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("workingcaseloadid", caseload), rowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  getAllFacilities " + e);
		}
		return returnList;
	}

	@Override
	public List<OffenderCaseNotes> casenoteexecuteQuery(OffenderCaseNotes offenderCaseNotes) {
		String sql = getQuery("OCICNSRC_GET_ALL_RECORDS");
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("staffId", offenderCaseNotes.getStaffId());
		params.addValue("fromdate", offenderCaseNotes.getFromDate());
		params.addValue("todate", offenderCaseNotes.getToDate());
		params.addValue("sourceCode", offenderCaseNotes.getCaseLoadType());
			params.addValue("facility", offenderCaseNotes.getFacility());
		try {
			returnList = namedParameterJdbcTemplate.query(sql, params, Row2BeanRowMapper.makeMapping(sql, OffenderCaseNotes.class,agyLocMapping)).stream().filter(bo->bo.getStaffId().equals(offenderCaseNotes.getStaffId())).collect(Collectors.toList());;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  casenoteexecuteQuery " + e);
		}
		return returnList;

	}

	@Override
	public Boolean checkPermisionForLov(String username) {

		final String sql = getQuery("OCICNSRC_CHECK_USER_STAFF_CLOG");
		Boolean permision = true;
		try {
			List<String> rolesList = namedParameterJdbcTemplate.queryForList(sql, createParams("userID", username),
					String.class);
			for (String per : rolesList) {
				if (per.equals("STAFF_CLOG")) {
					permision = false;
					break;
				}

			}

		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in  checkPermisionForLov " + e);
		}

		return permision;
	}
}
