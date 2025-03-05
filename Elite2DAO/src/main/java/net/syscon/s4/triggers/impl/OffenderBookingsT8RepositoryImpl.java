package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.triggers.OffenderBookingsT8Repository;
@Repository
public class OffenderBookingsT8RepositoryImpl extends RepositoryBase implements OffenderBookingsT8Repository {

	private final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	@Override
	public List<Long> lCheckExistCur(final Long rootOffenderId) {
		final String sql=getQuery("L_CHECK_EXIST_CUR");
		final RowMapper<Long> mapper= Row2BeanRowMapper.makeMapping(sql, Long.class, courtCasesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("l_root_offender_id",rootOffenderId),mapper);
	}
	
	@Override
	public Integer trgEventIdSeq() {
		final String sql=getQuery("TRG_EVENT_ID_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	@Override
	public Integer offenderAssocPEventNotifsInsert(final Long lOffenderBookIdOld,final Integer trgEventIdSeq,String createUserId) {
		final String sql=getQuery("OFFENDER_ASSOC_P_EVENT_NOTIFS_INSERT");
		return namedParameterJdbcTemplate.update(sql, createParams("l_trg_event_id",trgEventIdSeq,"l_offender_book_id_old",lOffenderBookIdOld,"createUserId",createUserId));
	}
}
