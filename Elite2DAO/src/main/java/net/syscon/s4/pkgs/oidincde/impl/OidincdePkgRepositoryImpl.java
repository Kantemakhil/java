package net.syscon.s4.pkgs.oidincde.impl;

import java.util.ArrayList;
import java.util.List;
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
import net.syscon.s4.pkgs.oidincde.OidincdePkgRepository;

@Repository
public class OidincdePkgRepositoryImpl extends RepositoryBase implements OidincdePkgRepository {

	private static Logger logger = LogManager.getLogger(OidincdePkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	@Override
	public List<Offenders> getOffDetailsByBookId(final Long offenderBookId) {
		final String sql = getQuery("GET_OFF_DETAILS_BY_BOOKID");
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mapping);
		List<Offenders> retList = new ArrayList<Offenders>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOffDetailsByBookId", e);
			retList = null;
		}
		return retList;
	}
}
