package net.syscon.s4.common.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.EliteParameterCodeRepository;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Class ReferenceCodeRepositoryImpl
 */
@Repository
public class ReferenceCodeRepositoryImpl extends RepositoryBase implements EliteParameterCodeRepository{
	
	
	public ReferenceCodeRepositoryImpl(){
		
	}
	
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();

	@Override
	public List<ReferenceCodes> searchEvidenceType(ReferenceCodes refCodeBean) throws SQLException {
		final String sql = getQuery("FIND_REFERENCE_CODE_BY_DOMAIN_CODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, referenceCodeMapping);
		ArrayList<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			ReferenceCodes refObj = (namedParameterJdbcTemplate.queryForObject(sql, createParams("domain", refCodeBean.getDomain(), "code", refCodeBean.getCode()), referenceCodeRowMapper));
			refList.add(refObj);
		} catch (EmptyResultDataAccessException e) {
			
		}
		return refList;
	}

}
