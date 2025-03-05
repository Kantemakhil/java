package net.syscon.s4.inst.programswithoutschedules.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CreateAdhocEmail;
import net.syscon.s4.im.beans.EmailRecipients;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.programswithoutschedules.OsuemailRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OsuemailRepositoryImpl
 */
@Repository
public class OsuemailRepositoryImpl extends RepositoryBase implements OsuemailRepository {
	private static final String WORK_ID = "workId";
	private static final String V_RETURN = "v_Return";
	private static final String OMS_OWNER = "OMS_OWNER";
	private static final String TAG_WORKFLOW_ADHOC = "TAG_WORKFLOW_ADHOC";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsuemailRepositoryImpl.class.getName());

	/**
	 * Creates new OsuemailRepositoryImpl class Object
	 */
	public OsuemailRepositoryImpl() {
		/* OsuemailRepositoryImpl */
	}

	private final Map<String, FieldMapper> mreferenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_TYPE", new FieldMapper("workType")).put("WORK_ID", new FieldMapper(WORK_ID))
			.put("WORK_SUB_TYPE", new FieldMapper("workSubType")).build();
	private final Map<String, FieldMapper> emailRecipientsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNET_ADDRESS", new FieldMapper("internetAddress"))
			.put("INTERNET_ADDRESS_CLASS", new FieldMapper("internetAddressClass"))
			.put("EMAIL_SUBJECT", new FieldMapper("emailSubject")).put("EMAIL_BODY", new FieldMapper("emailBody"))
			.put("OWNER_ID", new FieldMapper("ownerId")).build();
	private final Map<String, FieldMapper> offendersFirstName = new ImmutableMap.Builder<String, FieldMapper>()
			.put("offenderName", new FieldMapper("firstName")).build();
	private final Map<String, FieldMapper> workMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EMAIL_SUBJECT", new FieldMapper("emailSubject")).put("EMAIL_BODY", new FieldMapper("emailBody"))
			.build();

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Work>
	 */
	public List<Work> rgWorksRecordGroup(final String caseloadType) {
		final String sql = getQuery("OSUEMAIL_FIND_RGWORKS");
		final RowMapper<Work> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, Work.class,
				mreferenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("caseloadType", caseloadType, "caseloadType", caseloadType), referenceCodesRowMapper);
		} catch (final Exception e) {

			logger.error("rgWorksRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Work>
	 */
	@Override
	public Integer createAdhocEmail(final CreateAdhocEmail createAdhocEmail) {
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlParameter("P_WORKFLOW_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_WORK_ID", OracleTypes.NUMERIC),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMERIC),
				new SqlParameter("P_EMAIL_SUBJECT", OracleTypes.VARCHAR),
				new SqlParameter("P_EMAIL_BODY", OracleTypes.CLOB),
				new SqlParameter("P_EMAIL_SENDER", OracleTypes.VARCHAR),
				new SqlParameter("P_EMAIL_FROM", OracleTypes.VARCHAR),
				new SqlParameter("P_TO_RECIPIENTS_LIST", OracleTypes.ARRAY),
				new SqlParameter("P_CC_RECIPIENTS_LIST", OracleTypes.ARRAY),
				new SqlParameter("P_BCC_RECIPIENTS_LIST", OracleTypes.ARRAY), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_WORKFLOW_ADHOC).withProcedureName("CREATE_ADHOC_EMAIL")
				.declareParameters(sqlParameters);
		inParamMap.put("P_WORKFLOW_TYPE", createAdhocEmail.getpWorkflowType());
		inParamMap.put("P_WORK_ID", createAdhocEmail.getpWorkId());
		inParamMap.put("P_OFFENDER_BOOK_ID", createAdhocEmail.getpOffenderBookId());
		inParamMap.put("P_EMAIL_SUBJECT", createAdhocEmail.getpEmailSubject());
		inParamMap.put("P_EMAIL_BODY", createAdhocEmail.getpEmailBody());
		inParamMap.put("P_EMAIL_SENDER", createAdhocEmail.getpEmailSender());
		inParamMap.put("P_EMAIL_FROM", createAdhocEmail.getpEmailFrom());
		inParamMap.put("P_TO_RECIPIENTS_LIST", createAdhocEmail.getpToRecipientsList());
		inParamMap.put("P_CC_RECIPIENTS_LIST", createAdhocEmail.getpCcRecipientsList());
		inParamMap.put("P_BCC_RECIPIENTS_LIST", createAdhocEmail.getpBccRecipientsList());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		int sealFlag;
		try {
			simpleJDBCCall.execute(inParameter);
			sealFlag = 1;
		} catch (final Exception e) {
			logger.error("createAdhocEmail :", e);
			sealFlag = 2;
		}
		return sealFlag;

	}

	/**
	 * Used to save email Details
	 * 
	 */
	public Integer sendOsuemail(final CreateAdhocEmail createAdhocEmail) {
		final String sql = getQuery("OSUEMAIL_STSK_INSERT_ADHOC_EMAIL");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(createAdhocEmail));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to save email Details
	 * 
	 */
	public Integer saveAdhocEmailRecipients(final List<EmailRecipients> emailRecipientsList) {
		final String sql = getQuery("OSUEMAIL_STSK_INSERT_ADHOC_EMAIL_RECIPIENTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final EmailRecipients emailRecipients : emailRecipientsList) {
			parameters.add(new BeanPropertySqlParameterSource(emailRecipients));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (emailRecipientsList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture Email Body from Procedure
	 * 
	 * @return String
	 */
	@Override
	public String getEmailBody() {
		Map<String, Object> returnObject = null;
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlOutParameter(V_RETURN, OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_WORKFLOW_ADHOC).withFunctionName("GET_EMAIL_BODY")
				.declareParameters(sqlParameters);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			code = (String) returnObject.get(V_RETURN);
		} catch (final Exception e) {
			logger.error("getEmailBody :", e);
		}
		return code;
	}

	/**
	 * Used to capture Email Subject from Procedure
	 * 
	 * @return String
	 */
	@Override
	public String getEmailSubject() {
		String code = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlOutParameter(V_RETURN, OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_WORKFLOW_ADHOC).withFunctionName("GET_EMAIL_SUBJECT")
				.declareParameters(sqlParameters);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			code = (String) returnObject.get(V_RETURN);
		} catch (final Exception e) {
			logger.error("getEmailSubject :", e);
		}
		return code;
	}

	/**
	 * Used to capture Email Sender from Procedure
	 * 
	 * @return String
	 */
	@Override
	public String getEmailSender() {
		String code = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlOutParameter(V_RETURN, OracleTypes.VARCHAR), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName(OMS_OWNER)
				.withCatalogName(TAG_WORKFLOW_ADHOC).withFunctionName("GET_EMAIL_SENDER")
				.declareParameters(sqlParameters);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			code = (String) returnObject.get(V_RETURN);
		} catch (final Exception e) {
			logger.error("getEmailSender :", e);
		}
		return code;
	}

	/**
	 * Used to capture Email Recipients from select query
	 * 
	 * @return String
	 */
	@Override
	public List<EmailRecipients> getEmailRecipients(final long workId) {
		final String sql = getQuery("OSUEMAIL_FIND_EMAIL_RECIPIENTS");
		final RowMapper<EmailRecipients> staffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				EmailRecipients.class, emailRecipientsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(WORK_ID, workId), staffMembersRowMapper);
		} catch (final Exception e) {
			logger.error("getEmailRecipients :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture Offenders Details from select query
	 * 
	 * @return Offenders
	 */
	@Override
	public Offenders getOffendersDetails(final Integer offenderBookId) {
		final String sql = getQuery("OSUEMAIL_FIND_OFFENDERS_NAME");
		final RowMapper<Offenders> offendersRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersFirstName);

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					offendersRowMapper);
		} catch (final Exception e) {
			logger.error("getOffendersDetails :", e);
			return null;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return Work
	 */
	public Work getEmailBodySubject(final Integer workId) {
		final String sql = getQuery("OSUEMAIL_FIND_EMAIL_BODY_SUBJECT");
		try {
			final RowMapper<Work> workRowMapper = Row2BeanRowMapper.makeMapping(sql, Work.class, workMapping);
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(WORK_ID, workId), workRowMapper);
		} catch (final Exception e) {
			logger.error("getEmailBodySubject", e);
			return null;
		}

	}

	/**
	 * Used to capture sequence from select query
	 * 
	 */
	@Override
	public Long adhocEmailSeq() {
		final String sql = getQuery("OUSEMAIL_ADHOC_EMAIL_SEQ_NEXTVAL");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("adhocEmailSeq", e);
			return 0l;
		}
	}
}
