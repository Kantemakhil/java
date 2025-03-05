package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecords;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.admin.mergeoffenders.OummerofRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OumtrnbkRepository;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionProcesses;
import oracle.jdbc.OracleTypes;

/**
 * Class OumtrnbkRepositoryImpl
 */
@Repository
public class OumtrnbkRepositoryImpl extends RepositoryBase implements OumtrnbkRepository {

	private final JdbcTemplate jdbcTemplate;
	


	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumtrnbkRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mergeProcessesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TIMEFRAME_REQUIRED_FLAG", new FieldMapper(" timeframeRequiredFlag "))
			.put("DEFAULT_ON_FLAG", new FieldMapper(" defaultOnFlag "))
			.put("PROCESS_ID", new FieldMapper("processId"))
			.build();
	private final Map<String, FieldMapper> vmtpMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BOOKINGSTARTDATE", new FieldMapper("bookingStartdate"))
			.put("BOOKINGENDDATE", new FieldMapper("bookingEndDate"))
			.put("ISPROCESSTIMEREQUIRED", new FieldMapper("isProcessTimeRequired"))
			.put("PREVBKGENDDATE", new FieldMapper("prevBkgEndDate"))
			.put("NEXTBKGSTARTDATE", new FieldMapper("nextBkgStartDate")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OumtrnbkRepositoryImpl class Object
	 */
	public OumtrnbkRepositoryImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VMergeTransactionProcesses
	 *
	 * @return List<VMergeTransactionProcesses>
	 *
	 * @throws SQLException
	 */
	public List<VMergeTransactionProcesses> mrgProcExecuteQuery(VMergeTransactionProcesses objSearchDao) {
		final String sql = getQuery("OUMTRNBK_MRGPROC_FIND_V_MERGE_TRANSACTION_PROCESSES");
		final RowMapper<VMergeTransactionProcesses> VMergeTransactionProcessesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, VMergeTransactionProcesses.class, vmtpMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("MERGE_TRANSACTION_ID", objSearchDao.getMergeTransactionId(), "P_FROM_OFF_BOOK_ID",
						objSearchDao.getpFromOffBookId(), "P_FROM_ROOT_OFF_ID", objSearchDao.getpFromRootOffId()),
				VMergeTransactionProcessesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVMergeTransactionProcesses
	 *            List<VMergeTransactionProcesses>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer mrgProcInsertVMergeTransactionProcesses(final List<MergeTransactionProcesses> listObj) {
		String sql = getQuery("OUMTRNBK_MRGPROC_INSERT_MERGE_TRANSACTION_PROCESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (MergeTransactionProcesses mergeTransactionProcesses : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(mergeTransactionProcesses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVMergeTransactionProcesses
	 *            List<VMergeTransactionProcesses>
	 *
	 * @throws SQLException
	 */
	public Integer mrgProcDeleteVMergeTransactionProcesses(final List<MergeTransactionProcesses> listObj) {
		String sql = getQuery("OUMTRNBK_MRGPROC_DELETE_MERGE_TRANSACTION_PROCESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (MergeTransactionProcesses mergeTransactionProcesses : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(mergeTransactionProcesses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OUMTRNBK_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * isProcessTimeRequired
	 *
	 * @param params
	 *
	 */
	public List<MergeProcesses> isProcessTimeRequired(MergeProcesses paramBean) {
		final String sql = getQuery("OUMTRNBK_IS_PROCESS_TIME_REQUIRED");
		final RowMapper<MergeProcesses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MergeProcesses.class,
				mergeProcessesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * isProcessDefaultTransfer
	 *
	 * @param params
	 *
	 */
	public List<MergeProcesses> isProcessDefaultTransfer(MergeProcesses paramBean) {
		final String sql = getQuery("OUMTRNBK_IS_PROCESS_DEFAULT_TRANSFER");
		final RowMapper<MergeProcesses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MergeProcesses.class,
				mergeProcessesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public BigDecimal createMergeTransaction(final MergeTransactionBean bean) {
		BigDecimal mergeTxnId = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[13];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RETURN_VALUE", Types.INTEGER),
				new SqlParameter("p_offender_book_id_1", Types.NUMERIC),
				new SqlParameter("p_root_offender_id_1", Types.NUMERIC),
				new SqlParameter("p_offender_id_1", Types.NUMERIC),
				new SqlParameter("p_offender_id_display_1", Types.VARCHAR),
				new SqlParameter("p_last_name_1", Types.VARCHAR), new SqlParameter("p_first_name_1", Types.VARCHAR),
				new SqlParameter("p_offender_book_id_2", Types.NUMERIC),
				new SqlParameter("p_root_offender_id_2", Types.NUMERIC),
				new SqlParameter("p_offender_id_2", Types.NUMERIC),
				new SqlParameter("p_offender_id_display_2", Types.VARCHAR),
				new SqlParameter("p_last_name_2", Types.VARCHAR), new SqlParameter("p_first_name_2", Types.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("MERGE_TRANSACTION_REQUEST").withFunctionName("CREATE_TRANSFER_BKG_REQUEST")
				.declareParameters(sqlParameters);

		inParamMap.put("p_offender_book_id_1", bean.getpFromOffBookId());
		inParamMap.put("p_root_offender_id_1", bean.getpFromRootOffId());
		inParamMap.put("p_offender_id_1", bean.getpFromOffenderId());
		inParamMap.put("p_offender_id_display_1", bean.getpFromOffIdDisplay());
		inParamMap.put("p_last_name_1", bean.getpFromLastname());
		inParamMap.put("p_first_name_1", bean.getpFromFirstName());
		inParamMap.put("p_offender_book_id_2", bean.getpToOffBookId());
		inParamMap.put("p_root_offender_id_2", bean.getpToRootOffId());
		inParamMap.put("p_offender_id_2", bean.getpToOffenderId());
		inParamMap.put("p_offender_id_display_2", bean.getpToOffIdDisplay());
		inParamMap.put("p_last_name_2", bean.getpToLastName());
		inParamMap.put("p_first_name_2", bean.getpToFirstName());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
			mergeTxnId = new BigDecimal(returnObject.get("RETURN_VALUE").toString());
		} catch (Exception e) {
			logger.error("Exception in createMergeTransaction:", e);
		}
		return mergeTxnId;
	}

	@Override
	public Date getBookingStartDate(long fromOffBookId) {
		Date startDate = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[13];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RETURN_VALUE", Types.DATE),
				new SqlParameter("P_OFF_BOOK_ID", Types.NUMERIC) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRANSFER_BOOKING_CORE").withFunctionName("GET_BOOKING_START_DATE")
				.declareParameters(sqlParameters);

		inParamMap.put("P_OFF_BOOK_ID", fromOffBookId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
			startDate = (Date) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			logger.error("Exception in getBookingStartDate:", e);
		}
		return startDate;
	}

	@Override
	public Date getBookingEndDate(long fromOffBookId) {
		Date startDate = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[13];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RETURN_VALUE", Types.DATE),
				new SqlParameter("P_OFF_BOOK_ID", Types.NUMERIC) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRANSFER_BOOKING_CORE").withFunctionName("GET_BOOKING_END_DATE")
				.declareParameters(sqlParameters);

		inParamMap.put("P_OFF_BOOK_ID", fromOffBookId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
			startDate = (Date) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			logger.error("Exception in getBookingEndDate:", e);
		}
		return startDate;
	}

	@Override
	public String chkOffendersForTransfer(final MergeTransactionBean bean) {
		String result = null;
	
		return result;
	}

	public String processTransferTransaction(final MergeTransactionBean paramBean) {
		String returnVal = "success";
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_MERGE_TRANSACTION_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID_1", OracleTypes.NUMBER),
				new SqlParameter("P_ROOT_OFFENDER_ID_1", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID_2", OracleTypes.NUMBER),
				new SqlParameter("P_ROOT_OFFENDER_ID_2", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("MERGE_TRANSACTION_REQUEST").withProcedureName("PROCESS_TRN_BKG_REQUEST")
				.declareParameters(sqlParameters);
		inParamMap.put("P_MERGE_TRANSACTION_ID", paramBean.getpMergeTransactionId());
		inParamMap.put("P_OFFENDER_BOOK_ID_1", paramBean.getpFromOffBookId());
		inParamMap.put("P_ROOT_OFFENDER_ID_1", paramBean.getpFromRootOffId());
		inParamMap.put("P_OFFENDER_BOOK_ID_2", paramBean.getpToOffBookId());
		inParamMap.put("P_ROOT_OFFENDER_ID_2", paramBean.getpToRootOffId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return e.getMessage();
		}
		return returnVal;
	}
	
	
	@Override
	public Integer countOffBookings(Long pFromOffRootId) {
		final String sql = getQuery("OUMTRNBK_COUNT_OFF_BOOKINGS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("pFromOffRootId", pFromOffRootId),Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method countOffBookings", e);
		}
		return count;
	}

	@Override
	public Integer getInstBookActive(Long pFromOffBookId) {
		final String sql = getQuery("OUMTRNBK_GET_INST_BOOK_ACTIVE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("pFromOffBookId", pFromOffBookId),Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getInstBookActive", e);
		}
		return count;
	}

	@Override
	public Integer getToBookActive(Long pToOffRootId) {
		final String sql = getQuery("OUMTRNBK_GET_TO_BOOK_ACTIVE");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("pToOffRootId", pToOffRootId),Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getToBookActive", e);
		}
		return count;
	}

	@Override
	public Integer getActiveBookingCur(Long pOffBookId) {
		final String sql = getQuery("OUMTRNBK_GET_ACTIVE_BOOKING_CUR");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffBookId", pOffBookId),Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getActiveBookingCur", e);
		}
		return count;
	}
	
	@Override
	public List<VMergeTransactionProcesses> mergeProcExecuteQuery(Integer mergeTransactionId) {
		final String sql = getQuery("OUMTRNBK_MRGPROC_GET_V_MERGE_TRANSACTION_PROCESSES");
		List<VMergeTransactionProcesses> list = new ArrayList<VMergeTransactionProcesses>();
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams("mergeTransactionId", mergeTransactionId),
					new RowMapperResultSetExtractor<VMergeTransactionProcesses>(
							new BeanPropertyRowMapper<VMergeTransactionProcesses>(VMergeTransactionProcesses.class)));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeProcExecuteQuery", e);
		}
		return list;
	}
	
	@Override
	public Integer updateMergeTransProc(List<MergeTransactionProcesses> updateList) {
		String sql = getQuery("OUMTRNBK_MRGPROC_UPDATE_MERGE_TRANSACTION_PROCESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MergeTransactionProcesses mrgPrcs : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(mrgPrcs));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateMergeTransProc method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}
}
