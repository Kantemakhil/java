package net.syscon.s4.cf.offendertransactions.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcugltrdRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Class OcugltrdRepositoryImpl
 */
@Repository
public class OcugltrdRepositoryImpl extends RepositoryBase implements OcugltrdRepository {

	/**
	 * Creates new OcugltrdRepositoryImpl class Object
	 */
	public OcugltrdRepositoryImpl() {
		/*
		 * OcugltrdRepositoryImpl
		 * 
		 */
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcugltrdRepositoryImpl.class);

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GlTransactions
	 *
	 * @return List<GlTransactions>
	 */
	public List<GlTransactions> glTxnExecuteQuery(GlTransactions objSearchDao) {
		final String sql = getQuery("OCUGLTRD_GLTXN_FIND_GL_TRANSACTIONS");
		final RowMapper<GlTransactions> glTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				GlTransactions.class, mMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("txnId", objSearchDao.getTxnId(), "txnEntrySeq", objSearchDao.getTxnEntrySeq()),
				glTransactionsRowMapper);
	}
}
