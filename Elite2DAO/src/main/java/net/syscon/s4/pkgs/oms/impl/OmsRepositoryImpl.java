package net.syscon.s4.pkgs.oms.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.oms.OmsRepository;

@Repository
public class OmsRepositoryImpl extends RepositoryBase implements OmsRepository {

	private final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();

	// This method is migrated from oracle
	@Override
	public List<SystemProfiles> getResults(final String profiletype, final String profilecode) {
		final String sql = getQuery("SELECT_VALUES");
		final RowMapper<SystemProfiles> sysMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_Profile_type", profiletype, "p_Profile_code", profilecode), sysMapper);
	}

}