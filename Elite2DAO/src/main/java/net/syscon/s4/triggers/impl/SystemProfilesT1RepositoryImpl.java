package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.triggers.SystemProfilesT1Repository;

@Repository
public class SystemProfilesT1RepositoryImpl extends RepositoryBase implements SystemProfilesT1Repository {

	private static Logger logger = LogManager.getLogger(SystemProfilesT1RepositoryImpl.class);

	private final Map<String, FieldMapper> referCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ",                  new FieldMapper("listSeq"))
			.put("CODE",                      new FieldMapper("code"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",              new FieldMapper("profileCode"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.put("CREATE_USER_ID",            new FieldMapper("createUserId"))
			.put("SEAL_FLAG",                 new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",           new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID",            new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",            new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE",             new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME",           new FieldMapper("modifyDateTime"))
			.put("PROFILE_VALUE_2",           new FieldMapper("profileValue2"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER",                      new FieldMapper("user"))
			.put("SYSDATE",                   new FieldMapper("sysDate"))
			.put("PROFILE_VALUE",                   new FieldMapper("profileValue")).build();

	@Override
	public Integer insertDbPatches(List<SystemProfiles> lstSystem) {
		final String sql = getQuery("INSERT_INTO_DB_PATCHES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystem) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystem.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	

}

