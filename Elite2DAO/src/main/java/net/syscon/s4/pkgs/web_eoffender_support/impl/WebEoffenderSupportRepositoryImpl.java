package net.syscon.s4.pkgs.web_eoffender_support.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.pkgs.web_eoffender_support.WebEoffenderSupportRepository;

@Repository
public class WebEoffenderSupportRepositoryImpl extends RepositoryBase implements WebEoffenderSupportRepository {

	private static Logger logger = LogManager.getLogger(WebEoffenderSupportRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> getUserKeyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER_ID", new FieldMapper("userId")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OBJECT_TYPE", new FieldMapper("objectType")).put("OBJECT_ID", new FieldMapper("objectId"))
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("OS_USER", new FieldMapper("osUser"))
			.put("PRISON_LOCATION", new FieldMapper("prisonLocation"))
			.put("OFF_SUP_LEVEL", new FieldMapper("offSupLevel")).put("TRIM_USER", new FieldMapper("trimUser"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay")).build();

	@Override
	public EoffenderDetails getUserFromKey(final String keyLogin) {
		final String sql = getQuery("KEY_CUR");
		EoffenderDetails obj = new EoffenderDetails();
		final RowMapper<EoffenderDetails> mRowMapper = Row2BeanRowMapper.makeMapping(sql, EoffenderDetails.class,
				getUserKeyMapping);
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_KEY", keyLogin), mRowMapper);
		} catch (Exception e) {
			logger.error("getUserFromKey :" + e);
		}
		return obj;
	}

}