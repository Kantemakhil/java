package net.syscon.s4.core.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.core.LovRepository;

@Repository
public class LovRepositoryImpl extends RepositoryBase implements LovRepository {

	public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();

	public List<ReferenceCodes> getReferenceDomainCodes(final String domain, final String parent,String userId,String moduleName) throws SQLException {
		String sql = getQuery("GET_REFERENCE_CODES_BY_DOMAIN");
		MapSqlParameterSource paramMap = null;
		List<ReferenceCodes> referenceList = null;
		if (sql.contains("#dynamicwhere")) {
			if (null != parent && !parent.isEmpty()) {
				sql = sql.replace("#dynamicwhere", " and PARENT_CODE = :parent");
				paramMap = createParams("domain", domain, "parent", parent);
			} else {
				sql = sql.replace("#dynamicwhere", "");
				paramMap = createParams("domain", domain);
			}
			paramMap.addValue("userId", userId);
			paramMap.addValue("moduleName", moduleName);
		
			final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
					ReferenceCodes.class, referenceCodesMapping);
			referenceList = namedParameterJdbcTemplate.query(sql, paramMap, referenceCodeRowMapper);
		}
		return referenceList;
	}
}
