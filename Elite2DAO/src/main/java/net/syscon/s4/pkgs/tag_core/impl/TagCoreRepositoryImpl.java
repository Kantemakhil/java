package net.syscon.s4.pkgs.tag_core.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.tag_core.TagCoreRepository;

@Repository
public class TagCoreRepositoryImpl extends RepositoryBase implements TagCoreRepository {

	private static Logger logger = LogManager.getLogger(TagCoreRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CELL_SHARING_CONFLICT", new FieldMapper("cellSharingConflict")).build();

	@Override
	public Offenders getOffDetails(final Long lvOffenderId) {
		final String sql = getQuery("GET_OFF_DETAILS");
		Offenders retBean = null;

		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offMapping);
		try {
			retBean = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id", lvOffenderId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOffDetails", e);
		}
		return retBean;
	}
	
	@Override
	public Integer removeStringInstr(String pMainString, String pSubString) {
		final String sql = getQuery("TAG_CORE_REMOVE_STRING_INSTR");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_MAIN_STRING", pMainString, "P_SUB_STRING", pSubString), Integer.class);
		} catch (Exception e) {
			logger.error("removeStringInstr", e);
		}
		return result;
	}

	@Override
	public String removeStringSubstr(String pMainString, Integer vPos) {
		final String sql = getQuery("TAG_CORE_REMOVE_STRING_SUBSTR");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_MAIN_STRING", pMainString, "V_POS", vPos), String.class);
		} catch (Exception e) {
			logger.error("removeStringSubstr", e);
		}
		return result;
	}
	
	@Override
	public String stringSubstringOne(String pMainString, Integer vPos, Integer vLength) {
		final String sql = getQuery("TAG_CORE_REMOVE_STRING_SUBSTR_ONE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_MAIN_STRING", pMainString, "V_POS", vPos,"V_LENGTH",vLength), String.class);
		} catch (Exception e) {
			logger.error("stringSubstringOne", e);
		}
		return result;
	}
}
