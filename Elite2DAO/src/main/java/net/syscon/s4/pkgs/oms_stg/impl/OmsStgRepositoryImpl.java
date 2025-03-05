package net.syscon.s4.pkgs.oms_stg.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.pkgs.oms_stg.OmsStgRepository;

@Repository("OmsStgRepositoryImpl_pkg")
public class OmsStgRepositoryImpl extends RepositoryBase implements OmsStgRepository {
	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<StgRelationships> stgIdCur(final Long stgId, final BigDecimal relatedStgId) {
		final String sql = getQuery("STG_ID_CUR");
		final RowMapper<StgRelationships> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgRelationships.class, courtListMapping);
		List<StgRelationships> retlist = new ArrayList<>();
		try {

			retlist = namedParameterJdbcTemplate.query(sql,
					createParams("p_stg_id", stgId, "p_related_id", relatedStgId), referenceCodeRowMapper);
		} catch (Exception e) {
			return retlist;
		}
		return retlist;
	}

	@Override
	public Integer seqCur(final Long stgId) {
		final String sql = getQuery("SEQ_CUR_RELATIONSHIP_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("l_stg_id", stgId), Integer.class);
	}

	@Override
	public Integer omsStgInsert(final StgRelationships data) {
		final String sql = getQuery("OMS_STG_INSERT");
		Integer count=null;
		try {
			count= namedParameterJdbcTemplate.update(sql,
				createParams("stgId", data.getStgId(),"relationshipSeq",data.getRelationshipSeq(),"relatedStgId",data.getRelatedStgId(), "reason", data.getReason(),
						"effectiveDate", data.getEffectiveDate(), "createUserId", data.getCreateUserId(), "activeFlag",
						data.getActiveFlag(), "expiryDate", data.getExpiryDate(), "expiredBy",
						data.getExpiredBy(), "commentText", data.getCommentText()));
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
}
