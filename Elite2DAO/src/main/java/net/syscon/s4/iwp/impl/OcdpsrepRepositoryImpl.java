package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CourtReportCharges;
import net.syscon.s4.common.beans.OrderPpslCondActivities;
import net.syscon.s4.common.beans.OrderProposalConditions;
import net.syscon.s4.common.beans.OrderProposals;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.beans.OrdersHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.iwp.OcdpsrepRepository;


@Repository
public class OcdpsrepRepositoryImpl extends RepositoryBase implements OcdpsrepRepository {

	 private static Logger logger =
			  LogManager.getLogger(OcdpsrepRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> orderPpslCondActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();
	private final Map<String, FieldMapper> ordersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> orderProposalConditionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CODE", new FieldMapper("orderProposalCode"))
			.put("ORDER_ID", new FieldMapper("orderId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("PROGRAM_CODE", new FieldMapper("programCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", 							new FieldMapper("staffId"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();
	
	private final Map<String, FieldMapper> chargesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 	new FieldMapper("offenderBookId"))
			.put("CHARGE_ID", 			new FieldMapper("chargeId"))
			.put("ORDER_ID", 			new FieldMapper("orderId"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDatetime"))
			.build();

	public List<Orders> ordExecuteQuery( final String offenderBookId) {
		final String sql = getQuery("OCDPSREP_ORD_EXECUTEQUERY");
		final RowMapper<Orders> ordersRowMapper = Row2BeanRowMapper.makeMapping(sql, Orders.class, ordersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID",offenderBookId),ordersRowMapper);
				
	}
	
	public String orderStatus(final String code) {
		final String sql = getQuery("OCDPSREP_ORD_POSTQUERY");
		 String desc=null;
		 try {
			 desc=namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE",code), String.class);	
		 }
		  catch(Exception e) {
			  logger.error("Exception :", e);
		  }
		 return desc;
	}
	
	
	public Integer ordUpdateOrders(final List<Orders> lstOrders) {
		final String sql = getQuery("OCDPSREP_ORD_UPDATE_ORDERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Orders orders : lstOrders) {
			parameters.add(new BeanPropertySqlParameterSource(orders));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OcdpsrepReposiotyImpl class : ordUpdateOrders method ::",e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public void ordUpdateStatus(final BigDecimal orderId,String orderStatus) {
		final String sql = getQuery("OCDPSREP_ORD_POSTUPDATESTATUS");
		namedParameterJdbcTemplate.update(sql, createParams("order_id",orderId,"order_status",orderStatus));	
		
	}
	  
	
	
	public List<OrderProposals> ordProposalsExecuteQuery(final BigDecimal orderId) {
		final String sql = getQuery("OCDPSREP_ORDPROPOSALS_EXECUTEQUERY");
		final RowMapper<OrderProposals> ordPropRowMapper = Row2BeanRowMapper.makeMapping(sql,OrderProposals.class,mMapping );
		final List<OrderProposals> returnList =namedParameterJdbcTemplate.query(sql,
				createParams("order_id",orderId), ordPropRowMapper);
		return returnList;
	}
	 
	
	 public Long propCondsPreInsert() {
		 final String sql = getQuery("OCDPSREP_ORDPROPCONDS_PREINSERT");
		 return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);	
	 }
	 
	 
	
	public Integer ordProposalsInsert(final List<OrderProposals> lstOrderProposals) {
		final String sql = getQuery("OCDPSREP_ORDPROPOSALS_INSERT_ORDER_PROPOSALS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OrderProposals orderProposals : lstOrderProposals) {
			parameters.add(new BeanPropertySqlParameterSource(orderProposals));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch(Exception e) {
			  logger.error("Exception :", e);
			  if(e.getMessage().contains("ERROR: duplicate key value violates unique constraint ")) {
				return 2;
			  }
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	  
	  }
	
	
		public Integer ordProposalsUpdate(final List<OrderProposals> lstOrderProposals) {
			final String sql = getQuery("OCDPSREP_ORDPROPOSALS_UPDATE_ORDER_PROPOSALS");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (OrderProposals orderProposals : lstOrderProposals) {
				parameters.add(new BeanPropertySqlParameterSource(orderProposals));
			}
			try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			}  catch(Exception e) {
				  logger.error("Exception :", e);
				  if(e.getMessage().contains("ord_pps_cond_ord_ppsl_fk")) {
					  return 3;
				  }
			 }
			if ( returnArray.length > 0) {
				return 1;
			} else {
				return 0;
			}

		}
	  
	
	
		public Integer ordProposalsDelete(final List<OrderProposals> lstOrderProposals) {
			final String sql = getQuery("OCDPSREP_ORDPROPOSALS_DELETE_ORDER_PROPOSALS");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (OrderProposals orderProposals : lstOrderProposals) {
				parameters.add(new BeanPropertySqlParameterSource(orderProposals));
			}
			try {
				String tableName = "ORDER_PROPOSALS";
				String whereCondition = "ORDER_ID=:orderId and ORDER_PROPOSAL_CODE=:orderProposalCode";
				batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
			} catch (Exception e) {
				logger.error(e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if ( returnArray.length > 0) {
				return 1;
			} else {
				return 0;
			}

		}
	  
	
	public List<OrderProposalConditions> ordPropCondsExecuteQuery(final OrderProposals proposals) {
		final String sql = getQuery("OCDPSREP_ORDER_PROPOSAL_CONDITIONS_EXECUTEQUERY");
		final RowMapper<OrderProposalConditions> orderPropCondMapper = Row2BeanRowMapper.makeMapping(sql,
				OrderProposalConditions.class, orderProposalConditionsMapping);
		return  namedParameterJdbcTemplate.query(sql, createParams("order_id",proposals.getOrderId(),"ORDER_PROPOSAL_CODE",proposals.getOrderProposalCode()), orderPropCondMapper);
	}
	 
	
	public Integer ordPropCondsInsertOrderProposalConditions(final List<OrderProposalConditions> lstOrderPropConds) {
		final String sql = getQuery("OCDPSREP_ORDPROPCONDS_INSERT_ORDER_PROPOSAL_CONDITIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OrderProposalConditions ordPropCond : lstOrderPropConds) {
			parameters.add(new BeanPropertySqlParameterSource(ordPropCond));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("ordPropCondsInsertOrderProposalConditions", e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}
	  
	
	
		public Integer ordPropCondsUpdateOrderProposalConditions(final List<OrderProposalConditions> lstOrderPropConds) {
			final String sql = getQuery("OCDPSREP_ORDPROPCONDS_UPDATE_ORDER_PROPOSAL_CONDITIONS");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (OrderProposalConditions ordPropCond : lstOrderPropConds) {
				parameters.add(new BeanPropertySqlParameterSource(ordPropCond));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (returnArray.length > 0) {
				return 1;
			} else {
				return 0;
			}

		}
	  
	
		public Integer ordPropCondsDeleteOrderProposalConditions(final List<OrderProposalConditions> lstOrderPropConds) { 
			final String sql = getQuery("OCDPSREP_ORDPROPCONDS_DELETE_ORDER_PROPOSAL_CONDITIONS");  
			int[] returnArray = new int[] {}; 
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>(); 
			for (OrderProposalConditions ordPropCond :lstOrderPropConds) { 
				parameters.add(new BeanPropertySqlParameterSource(ordPropCond)); } 
			try {
				String tableName = "ORDER_PROPOSAL_CONDITIONS";
				String whereCondition = "ORDER_PROPOSAL_CONDITION_ID=:orderProposalConditionId";
				batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
			} catch (Exception e) {
				logger.error(e);
			}
			returnArray =namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0])); 
			if ( returnArray.length > 0) { 
				return 1; 
			}
			else {
				return 0; 
			}
	  
	  }
	  
	
	
	public List<OrderPpslCondActivities> ordPpslCondActExecuteQuery(final BigDecimal orderId) {
		final String sql = getQuery("OCDPSREP_ORDPPSLCONDACT_EXECUTEQUERY");
		final RowMapper<OrderPpslCondActivities> ordPpslCondActMap = Row2BeanRowMapper.makeMapping(sql,
				OrderPpslCondActivities.class, orderPpslCondActivitiesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ORDER_PROPOSAL_CONDITION_ID",orderId), ordPpslCondActMap);
		 
	}
	 
	
	  public Long ordPpslcondActpreInnsert() {
		  	final String sql = getQuery("OCDPSREP_ORD_PPSL_COND_ACT_PREINSERT_");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);	
		 
		  
	  }
	

	public Integer ordPpslCondActInsertOrderPpslCondActivities(final List<OrderPpslCondActivities> lstOrdPpslCondAct) {
		final String sql = getQuery("OCDPSREP_ORDPPSLCONDACT_INSERT_ORDER_PPSL_COND_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OrderPpslCondActivities ordPpslCondAct : lstOrdPpslCondAct) {
			parameters.add(new BeanPropertySqlParameterSource(ordPpslCondAct));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if ( returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}
	  
	
	
	public Integer ordPpslCondActUpdateOrderPpslCondActivities(final List<OrderPpslCondActivities> lstOrdPpslCondAct) {
		final String sql = getQuery("OCDPSREP_ORDPPSLCONDACT_UPDATE_ORDER_PPSL_COND_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OrderPpslCondActivities ordPpslCondAct : lstOrdPpslCondAct) {
			parameters.add(new BeanPropertySqlParameterSource(ordPpslCondAct));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if ( returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}
	  

		public Integer ordPpslCondActDeleteOrderPpslCondActivities(final List<OrderPpslCondActivities> lstOrdPpslCondAct) {
			String sql = getQuery("OCDPSREP_ORDPPSLCONDACT_DELETE_ORDER_PPSL_COND_ACTIVITIES");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (OrderPpslCondActivities ordPpslCondAct : lstOrdPpslCondAct) {
				parameters.add(new BeanPropertySqlParameterSource(ordPpslCondAct));
			}
			try {
				String tableName = "ORDER_PPSL_COND_ACTIVITIES";
				String whereCondition = "ORDER_PPSL_COND_ACTIVITY_ID=:orderPpslCondActivityId";
				batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
			} catch (Exception e) {
				logger.error(e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			
			if (returnArray.length > 0) {
				return 1;
			} else {
				return 0;
			}

		}
	
	public List<ReferenceCodes> rgOrderTypeRecordGroup() {
		final String sql = getQuery("OCDPSREP_FIND_RGORDERTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	 
	public List<ReferenceCodes> rgReportProposalRecordGroup() {
		final String sql = getQuery("OCDPSREP_FIND_ORDER_PROPOSAL_DESC");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		
	}
	
	
	public List<ReferenceCodes> rgCourtAgyLocDescRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCDPSREP_FIND_RGISSUINGAGYLOCID");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseload_id",caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	

		public List<ReferenceCodes> rgProposedSentenceRecordGroup(String sentenceCategory) {
			final String sql = getQuery("OCDPSREP_FIND_NBT_SENT_CALC_TYPE");
			final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("sentence_category",sentenceCategory), mRowMapper);
		}
	
		 
	public List<ReferenceCodes> rgProposedCategoryRecordGroup() {
		final String sql = getQuery("OCDPSREP_FIND_RGPROPOSEDCATEGORY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}
	 
	
	public List<ReferenceCodes> rgProposedRequirementRecordGroup(final String sentenceCategory) {
		final String sql = getQuery("OCDPSREP_FIND_NBT_COMM_CONDITION_DESC");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("sentence_category",sentenceCategory), mRowMapper);	
	}
	
	
	public void createReportDone(Orders orders) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("p_off_book_id", Types.NUMERIC), 
				new SqlParameter("p_report_type", Types.VARCHAR),
				new SqlParameter("p_court_desc", Types.VARCHAR),
				new SqlParameter("p_compl_date", Types.DATE),
				new SqlParameter("p_user", Types.VARCHAR),
				new SqlParameter("p_workflow_id", Types.NUMERIC),
				 };
		
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WF_COURT_REPORT").withProcedureName("CREATE_REPORT_DONE").declareParameters(sqlParameters);  
		
		inParamMap.put("p_off_book_id", orders.getOffenderBookId());
		inParamMap.put("p_report_type",orders.getOrderType());
		inParamMap.put("p_court_desc",orders.getIssuingAgyLocId());
		inParamMap.put("p_compl_date",orders.getCompleteDate());
		inParamMap.put("p_user",orders.getCreateUserId());
		inParamMap.put("p_workflow_id",orders.getWorkflowId());
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			
		} catch (final Exception e) {
			logger.error("Exception  :", e);
		}
		
	}

	@Override
	public Integer ordInsertOrders(List<Orders> lstOrders) {
		final String sql = getQuery("OCDPSREP_ORD_INSERT_ORDERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Orders orders : lstOrders) {
			parameters.add(new BeanPropertySqlParameterSource(orders));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in ordInsertOrders :", e.getMessage());
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Teams> rgTeamRecordGroup(Long offenderBookId ,String userId) {
		final String sql = getQuery("OCDPSREP_FIND_RGTEAM");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offender_book_id",offenderBookId , "userId" , userId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method rgTeamRecordGroup", e);
			return Collections.emptyList();
		}
	}
	
	public List<ReferenceCodes> rgStaffMembersRecordGroup(String caseLoadId,String teamResponsible) {
		String sql = null;
		Integer data=null;
		if(teamResponsible != null && !teamResponsible.equalsIgnoreCase("null") && !teamResponsible.equals("") && !teamResponsible.equals("undefined")) {
			sql = getQuery("OCDPSREP_FIND_TEAM_RGSTAFFMEMBERS");
			 data=Integer.parseInt(teamResponsible);
		}else {
			sql = getQuery("OCDPSREP_FIND_RGSTAFFMEMBERS");
		}
		final RowMapper<ReferenceCodes> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				staffMembersMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("teamResponsible",data,"caseloadId",caseLoadId), mMMRowMapper);
		} catch (Exception e) {
			logger.error("In method rgStaffMembersRecordGroup", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public Integer ordInsertOrdersHty(List<OrdersHty> lstOrders) {
		final String sql = getQuery("OCDPSREP_ORD_INSERT_ORDERS_HTY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OrdersHty OrdersHty : lstOrders) {
			parameters.add(new BeanPropertySqlParameterSource(OrdersHty));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in ordInsertOrdersHty :", e.getMessage());
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OrdersHty> ordHistoryExecuteQuery(BigDecimal orderId) {
		final String sql = getQuery("OCDPSREP_ORD_HISTORY_EXECUTEQUERY");
		final RowMapper<OrdersHty> ordersRowMapper = Row2BeanRowMapper.makeMapping(sql, OrdersHty.class, ordersMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("orderId",orderId),ordersRowMapper);
	}
	
	@Override
	public List<ReferenceCodes> rgAccreditedProgramsRecordGroup() {
		String sql = getQuery("OCDPSREP_RG_ACCREDITED_PROGRAMS_RECORD_GROUP");
		final RowMapper<ReferenceCodes> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				staffMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMMRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAccreditedProgramsRecordGroup", e);
			return Collections.emptyList();
		}
	}
	 
	@Override
	public BigDecimal getOrdersSeq() {
		final String sql = getQuery("OCDPSREP_SEQUENCE_ORDER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public Integer ordAuthorCommit(Orders ordersBean) {
		final String sql = getQuery("OCDPSREP_UPD_ORD_AUTHORS_COMMENT_TEXT");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("order_id",ordersBean.getOrderId(),"comment_text",ordersBean.getCommentText(),"modify_user_id",ordersBean.getModifyUserId()));
		} catch (Exception e) {
			logger.error("In method ordAuthorCommit", e);
			return 0;
		}
			
	}

	@Override
	public Orders getOldRecordToCompareTeamAndStaff(BigDecimal orderId) {
		final String sql = getQuery("GETTING_OLD_RECORDS");
		Orders orders=new Orders();
		try {
			orders = namedParameterJdbcTemplate.queryForObject(sql, createParams("ORDER_ID", orderId), new BeanPropertyRowMapper<Orders>(Orders.class));
		} catch (Exception e) {
			logger.error("In method getOldRecord", e);
		}
		return orders;
	}
	
	@Override
	public List<CourtReportCharges> chargesExecuteQuery(CourtReportCharges searchBean) {
		final String sql = getQuery("OCDPSREP_CHARGES_EXECUTE_QUERY");
		final RowMapper<CourtReportCharges> chargesRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtReportCharges.class,
				chargesMapping);
		final ArrayList<CourtReportCharges> returnList = (ArrayList<CourtReportCharges>) namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", searchBean.getOffenderBookId(), "orderId", searchBean.getOrderId()), chargesRowMapper);
		return returnList;
	}

	@Override
	public Integer insertCharges(List<CourtReportCharges> lstCourtReportCharges) {
		final String sql = getQuery("OCDPSREP_INSERT_CHARGES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourtReportCharges courtReportCharges : lstCourtReportCharges) {
			parameters.add(new BeanPropertySqlParameterSource(courtReportCharges));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OcdpsrepReposiotyImpl class : insertCharges method ::",e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteCharges(List<CourtReportCharges> lstCourtReportCharges) {
		final String sql = getQuery("OCDPSREP_DELETE_CHARGES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourtReportCharges courtReportCharges : lstCourtReportCharges) {
			parameters.add(new BeanPropertySqlParameterSource(courtReportCharges));
		}
		try {
			String tableName = "off_court_report_charges";
			String whereCondition = "offender_book_id = :offenderBookId and order_id = :orderId and charge_id = :chargeId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in OcdpsrepReposiotyImpl class : deleteCharges method ::",e);
		}
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
