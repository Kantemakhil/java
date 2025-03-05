package net.syscon.s4.pkgs.transfer_booking_core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.pkgs.TransferBookingTables;
import net.syscon.s4.pkgs.TransferTableRelationships;
import net.syscon.s4.pkgs.merge_process.impl.MergeProcessRepositoryImpl;
import net.syscon.s4.pkgs.transfer_booking_core.TransferBookingCoreRepository;
import net.syscon.s4.sa.admin.beans.MergeTransactions;

@Repository
public class TransferBookingCoreRepositoryImpl extends RepositoryBase implements TransferBookingCoreRepository{
	
	private static Logger logger = LogManager.getLogger(MergeProcessRepositoryImpl.class.getName());
	
	
	private final Map<String, FieldMapper> mrgTxnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("table_name", new FieldMapper("tableName"))
			.put("begin_date", new FieldMapper("beginDate"))
			.put("end_date", new FieldMapper("endDate"))
			.put("root_offender_id_1", new FieldMapper("rootOffenderId1"))
			.put("root_offender_id_2", new FieldMapper("rootOffenderId2"))
			.put("offender_book_id_1", new FieldMapper("offenderBookId1"))
			.put("offender_book_id_2", new FieldMapper("offenderBookId2"))
			.build();


	@Override
	public Date getAdmDate(Long pOffBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_ADM_DATE");
		Date admDate = null;
		try {
			admDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId), Date.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getAdmDate", e);
		}
		return admDate;
	}


	@Override
	public Date getIntakeDate(Long pOffBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_INTAKE_DATE");
		Date intakeDate = null;
		try {
			intakeDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId), Date.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getIntakeDate", e);
		}
		return intakeDate;
	}


	@Override
	public Date getInstEndDate(Long pOffBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_INST_END_DATE");
		Date instEndDate = null;
		try {
			instEndDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId), Date.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getInstEndDate", e);
		}
		return instEndDate;
	}


	@Override
	public Date getCommEndDate(Long pOffBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_COMM_END_DATE");
		Date commEndDate = null;
		try {
			commEndDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId), Date.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getCommEndDate", e);
		}
		return commEndDate;
	}


	@Override
	public Integer isCommBooking(Long pOffBookId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_IS_COMM_BOOKING");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method isCommBooking", e);
		}
		return count;
	}


	@Override
	public Object execPSqlDml(String pSqlDml) {
		Object obj = new Object();
		try {
			obj = namedParameterJdbcTemplate.queryForObject(pSqlDml, createParams(), new BeanPropertyRowMapper<Object>(Object.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method execPSqlDml", e);
		}
		return obj;
	}


	@Override
	public Integer insertMrgOffSqls( String pSqlDml) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_INSERT_MERG_OFF_SQLS");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql, createParams("pSqlDml", pSqlDml ));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method insertMrgOffSqls", e);
		}
		return result;
	}

	@Override
	public List<MergeTransactions> mergeTableListCur(Long pMergeTransactionId, String pApplnCode) {

		final String sql = getQuery("TRANSFER_BOOKING_CORE_MERGE_TABLE_LIST_CUR");
		List<MergeTransactions> mergeTableList = new ArrayList<MergeTransactions>();
		final RowMapper<MergeTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MergeTransactions.class, mrgTxnMapping);
		try {
			mergeTableList = namedParameterJdbcTemplate.query(sql, createParams("pMergeTransactionId", pMergeTransactionId, "pApplnCode", pApplnCode),columnRowMapper);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeTableListCur", e);
		}
		return mergeTableList;
	}


	@Override
	public List<TransferBookingTables> cascadeTrnsMergeTableListCur(String pTableName) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_CASCADE_TRANSFER_MERGE_TABLE_LIST_CUR");
		List<TransferBookingTables> casMergeTableList = new ArrayList<TransferBookingTables>();
		try {
			casMergeTableList = namedParameterJdbcTemplate.query(sql, createParams("pTableName", pTableName),
					new RowMapperResultSetExtractor<TransferBookingTables>(
							new BeanPropertyRowMapper<TransferBookingTables>(TransferBookingTables.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeTableListCur", e);
		}
		return casMergeTableList;
	}


	@Override
	public TransferBookingTables getTransferBookingTablesVals(String pTableName) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_TRANSFER_BOOKING_TABLES_VALS");
		TransferBookingTables transfBkgTables = new TransferBookingTables();
		try {
			transfBkgTables = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pTableName", pTableName),
					new BeanPropertyRowMapper<TransferBookingTables>(TransferBookingTables.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getTransferBookingTablesVals", e);
		}
		return transfBkgTables;
	}


	@Override
	public Integer getCount(String sql) {
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(),(Integer.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getCount", e);
		}
		return count;
	}


	@Override
	public TransferTableRelationships getTrTabRelData(String pParentTableName, String pTableName) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_TR_TAB_REL_DATA");
		TransferTableRelationships trTabRelData = new TransferTableRelationships();
		try {
			trTabRelData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pParentTableName", pParentTableName, "pTableName", pTableName),
					new BeanPropertyRowMapper<TransferTableRelationships>(TransferTableRelationships.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getTrTabRelData", e);
		}
		return trTabRelData;
	}


	@Override
	public TransferTableRelationships getTrnsfTblRltnData(String pParentTable, String pChildTable) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_TRNSF_TBL_RLTN_DATA");
		TransferTableRelationships trTabRelData = new TransferTableRelationships();
		try {
			trTabRelData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pParentTable", pParentTable, "pChildTable", pChildTable),
					new BeanPropertyRowMapper<TransferTableRelationships>(TransferTableRelationships.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getTrnsfTblRltnData", e);
		}
		return trTabRelData;
	}


	@Override
	public Integer getMaxSeq(String sql) {
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(),(Integer.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getMaxSeq", e);
		}
		return count;
	}


	@Override
	public TransferBookingTables getTransferBkngTablesData(String pTableName) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_TRANSFER_BKNG_TABLES_DATA");
		TransferBookingTables transfBkgTables = new TransferBookingTables();
		try {
			transfBkgTables = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pTableName", pTableName),
					new BeanPropertyRowMapper<TransferBookingTables>(TransferBookingTables.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getTransferBkngTablesData", e);
		}
		return transfBkgTables;
	}


	@Override
	public Long getPrvBookIdCur(Long pOffBookId, Long pRootOffId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_PREV_BOOK_ID");
		Long prevBookId = null;
		try {
			prevBookId = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId, "pRootOffId",pRootOffId),(Long.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getPrvBookIdCur", e);
		}
		return prevBookId;
	}
	
	@Override
	public Long getNextBookIdCur(Long pOffBookId, Long pRootOffId) {
		final String sql = getQuery("TRANSFER_BOOKING_CORE_GET_NEXT_BOOK_ID");
		Long nextBookId = null;
		try {
			nextBookId = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId, "pRootOffId",pRootOffId),(Long.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getNextBookIdCur", e);
		}
		return nextBookId;
	}
	

}
