package net.syscon.s4.pkgs.oms_miscellaneous.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SentenceUpdateReasons;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousRepository;

@Repository("OmsMiscellaneous_mg")
public class OmsMiscellaneousRepositoryImpl extends RepositoryBase implements OmsMiscellaneousRepository {
	private static Logger logger = LogManager.getLogger(OmsMiscellaneousRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> systemProfiles = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 			new FieldMapper("profileType"))
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", 		new FieldMapper("profileValue2"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 			new FieldMapper("oldTableName"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put(" SEAL_FLAG  ", 			new FieldMapper("sealFlag"))
			.build();
	
	@Override
	public String getDescCode(final String areaClass) {
		final String sql = getQuery("OMS_MISC_GET_DESC_CODE");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("areaClass", areaClass), String.class);
		} catch (Exception e) {
			logger.error("cArea", e);
			desc = null;
		}
		return desc;
	}

	@Override
	public SystemProfiles getProfileValue(final String profileType, final String profileCode) {
		final String sql = getQuery("SELECT_PROFILE_VALUE");
		List<SystemProfiles> result = null;
		try {
			final RowMapper<SystemProfiles> systemProfileMapper = Row2BeanRowMapper.makeMapping(sql,
					SystemProfiles.class, systemProfiles);
			result = namedParameterJdbcTemplate.query(sql, createParams("p_profile_type", profileType, "p_profile_code", profileCode),
					systemProfileMapper);
			if(result != null && !result.isEmpty())
				return result.get(0);
		} catch (Exception e) {
			logger.error("getProfileVale", e);
		}
		return null;
	}

	@Override
	public String staffName(final Long staffId) {
		final String sql = getQuery("GET_STAFF_NAME");
		String staffName = null;
		try {
			final String sName = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_staff_id", staffId),
					String.class);
			if (Optional.ofNullable(sName).isPresent()) {
				staffName = sName;
			}
		} catch (Exception e) {
			logger.error("staffName", e);
			return null;
		}
		return staffName;
	}

	@Override
	public String getDescCode(final String domain, final String refCode) {
		final String sql = getQuery("GET_DESC_CODE_TWO_PARAMS");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_domain", domain, "p_refcode", refCode), String.class);
		} catch (Exception e) {
			logger.error("getDescCode", e);
		}
		return retVal;
	}
	//This method is used for get_profile_values from database
	@Override
	public SystemProfiles getProfileValues(final SystemProfiles bean) {
		final String sql = getQuery("GET_PROFILE_VALUES");
		List<SystemProfiles> result = null;
		final RowMapper<SystemProfiles> systemProfileMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfiles);
		result = namedParameterJdbcTemplate.query(sql, createParams("p_profile_type", bean.getProfileType(), "p_profile_code", bean.getProfileCode()),
				systemProfileMapper);
		if(result != null && !result.isEmpty())
			return result.get(0);
		else
			return null;
	}

}