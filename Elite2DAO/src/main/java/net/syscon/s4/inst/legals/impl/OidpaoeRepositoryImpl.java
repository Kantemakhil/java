package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.genericservices.ErrorCodes;
import net.syscon.s4.inst.legals.OidpaoeRepository;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEventResponse;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;

@Repository
public class OidpaoeRepositoryImpl extends RepositoryBase implements OidpaoeRepository {

    private static final String SUCCESS = "Success";

    private static final String FAILED = "Failed";

    private static final String INSERT = "I";

    private static final String DELETE = "D";
    
    private static final String UPDATE = "U";

    private final Map<String, FieldMapper> payrolEventMapping = new ImmutableMap.Builder<String, FieldMapper>()

            .put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
            .put("PAROLE_EVENT_ID", new FieldMapper("paroleEventId"))
            .put("EVENT_DATE", new FieldMapper("eventDate"))
            .put("PAROLE_EVENT", new FieldMapper("paroleEvent"))
            .put("COMMENT", new FieldMapper("comment"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .build();

    private final Map<String, FieldMapper> offenderSentenceAdjustment = new ImmutableMap.Builder<String, FieldMapper>()

            .put("OFFENDER_ORDER_ADJUST_ID", new FieldMapper("offenderOrderAdjustId"))
            .put("ADJUST_CODE", new FieldMapper("orderAdjustCode"))
            .put("offender_book_id", new FieldMapper("offenderBookId"))
            .put("OBJECT_ID", new FieldMapper("objectId"))
            .put("OBJECT_TYPE", new FieldMapper("objectType"))
            .put("ADJUST_DATE", new FieldMapper("adjustDate"))
            .put("ADJUST_DAYS", new FieldMapper("adjustDays"))
            .put("ADJUST_FROM_DATE", new FieldMapper("adjustFromDate"))
            .put("ADJUST_TO_DATE", new FieldMapper("adjustToDate"))
            .put("COMMENT_TEXT", new FieldMapper("commentText"))
            .put("SEAL_FLAG", new FieldMapper("sealFlag"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .build();

    public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("CODE", new FieldMapper("code"))
            .put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
            .build();

    @Override
    public List<OffenderPayrolEvent> findEventByOffebderBookId(Long offenderBookId) {
        final String sql = getQuery("GET_PAYROL_EVENT_BY_OFFENDER_BOOKID");
        final RowMapper<OffenderPayrolEvent> rowMap = Row2BeanRowMapper.makeMapping(sql, OffenderPayrolEvent.class,
                payrolEventMapping);
        List<OffenderPayrolEvent> listEvents = namedParameterJdbcTemplate.query(sql,
                createParams("OFFENDER_BOOK_ID", offenderBookId),
                rowMap);
        return listEvents;
    }

    @Override
    public String insertUpdatePayrolEvent(OffenderPayrolEvent event) {
        int[] insertUpdateRecords = null;
        List<OffenderPayrolEvent> listAsset = Arrays.asList(event);

        if (null != event.getParoleEventId()) {
            insertUpdateRecords = insertUpdateRecords("UPDATE_PAYROL_EVENT", listAsset);
        } else {
            Long maxEventId = getMaxEventId(event.getOffenderBookId());
            event.setParoleEventId(maxEventId);
            insertUpdateRecords = insertUpdateRecords("INSERT_PAYROL_EVENT", listAsset);
        }

        if (insertUpdateRecords.length > 0 && insertUpdateRecords[0] > 0) {
            return "Event Inserted/Updated Sucessfully";
        }
        return "Event Insertion/Updation Failed";
    }

    private Long getMaxEventId(Long offenderBookId) {
        final String sql = getQuery("GET_MAX_EVENT_ID_BY_OFFENDER_BOOKID");
        Long queryForObject = namedParameterJdbcTemplate.queryForObject(sql,
                createParams("OFFENDER_BOOK_ID", offenderBookId),
                Long.class);
        if (null == queryForObject) {
            return 1l;
        }
        return queryForObject + 1;
    }

    private <T> int[] insertUpdateRecords(String query, List<T> lstOfProcessMain) {
        String sql = getQuery(query);
        int[] returnArray = new int[] {};
        List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

        lstOfProcessMain.forEach(t -> parameters.add(new BeanPropertySqlParameterSource(t)));

        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        return returnArray;
    }

    private <T> boolean insertUpdateRecords(String query, T myBean) {
        String sql = getQuery(query);
        SqlParameterSource params = new BeanPropertySqlParameterSource(myBean);

        try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			
			if("DELETE_ADUJSTMENT_BY_OFFENDERBOOKID_OBJECTID_OBJECTTYPE".equals(query)) {	
				inputMap.put("modifyUserId", params.getValue("modifyUserId"));
				inputMap.put("offenderBookId", params.getValue("offenderBookId"));
				inputMap.put("objectId", params.getValue("objectId"));
				inputMap.put("objectType", params.getValue("objectType"));
				updatePreDeletedRow("offender_legal_adjustments", "offender_book_id  =:offenderBookId and object_id =:objectId and object_type = :objectType", inputMap);
			}
			
			else if("DELETE_ADUJSTMENT_BY_ADJUSTMENTID".equals(query)) {	
				inputMap.put("modifyUserId", params.getValue("modifyUserId"));
				inputMap.put("offenderOrderAdjustId", params.getValue("offenderOrderAdjustId"));
				updatePreDeletedRow("offender_legal_adjustments", "offender_order_adjust_id  =:offenderOrderAdjustId", inputMap);
			}
			
			else if("DELETE_PAROLE_EVENTS_BY_OFFENDERBOOKID_PAROLEEVENTID".equals(query)) {	
				inputMap.put("modifyUserId", params.getValue("modifyUserId"));
				inputMap.put("offenderBookId", params.getValue("offenderBookId"));
				inputMap.put("paroleEventId", params.getValue("paroleEventId"));
				updatePreDeletedRow("OFFENDER_PAROLE_EVENTS", "offender_book_id = :offenderBookId AND parole_event_id =:paroleEventId", inputMap);
			}
			
			
		} catch (Exception e) {
			//logger.error("batchUpdatePreDeletedRows in deleteOffederCondTransfer"+e);
		}
        
        int updateStatus = namedParameterJdbcTemplate.update(sql, params);
        if (updateStatus > 0) {
            return true;
        }

        return false;
    }

    @Override
    public List<OffenderPayrolEventResponse> insertUpdateDeletePayrolEvent(List<OffenderPayrolEvent> events)
            throws CustomException {

        List<OffenderPayrolEventResponse> outputRespnose = new ArrayList<>();

        // iterating over payrol events
        for (OffenderPayrolEvent offenderPayrolEvent : events) {

            OffenderPayrolEventResponse reponseEvent = new OffenderPayrolEventResponse();
            outputRespnose.add(reponseEvent);
            // check Insert flag with InsertType I
            if (StringUtils.isNotBlank(offenderPayrolEvent.getRecordFlag())
                    && offenderPayrolEvent.getRecordFlag().equals(INSERT)) {

                // Getting max id from event table
                Long maxEventId = getMaxEventId(offenderPayrolEvent.getOffenderBookId());
                offenderPayrolEvent.setParoleEventId(maxEventId);

                // getting adjustment list
                List<OffenderSentenceAdjustment> listOffenderSentenceAdjustment = offenderPayrolEvent
                        .getListOffenderSentenceAdjustment();

                // inserting records in event table
                boolean insertUpdateRecords = insertUpdateRecords("INSERT_PAYROL_EVENT", offenderPayrolEvent);

                reponseEvent.setEventResponse(insertUpdateRecords ? SUCCESS : FAILED);
                reponseEvent.setParoleEventId(null == offenderPayrolEvent.getParoleEventId() ? maxEventId
                        : offenderPayrolEvent.getParoleEventId());
                if (insertUpdateRecords && null != listOffenderSentenceAdjustment
                        && !listOffenderSentenceAdjustment.isEmpty()) {

                    for (OffenderSentenceAdjustment adjust : listOffenderSentenceAdjustment) {
                        adjust.setCreateUserId(offenderPayrolEvent.getCreateUserId());
                        if (StringUtils.isNotBlank(adjust.getRecordFlag()) && adjust.getRecordFlag().equals(INSERT)) {
                            adjust.setObjectId(offenderPayrolEvent.getParoleEventId());
                            // insert adjustment
                            boolean insertAdjustment = insertUpdateRecords("INSERT_PAYROL_ADJUSTMENTS", adjust);

                            // response building
                            reponseEvent.addAdjustmentResponse(adjust.getOrderAdjustCode() + " "
                                    + adjust.getOffenderBookId() + " " + adjust.getObjectId(),
                                    insertAdjustment ? SUCCESS : FAILED);
                        }

                    }
                }
            } else if (StringUtils.isNotBlank(offenderPayrolEvent.getRecordFlag())
                    && offenderPayrolEvent.getRecordFlag().equals(DELETE)) {

                reponseEvent.setParoleEventId(offenderPayrolEvent.getParoleEventId());

                // getting adjustment list
                List<OffenderSentenceAdjustment> listOffenderSentenceAdjustment = offenderPayrolEvent
                        .getListOffenderSentenceAdjustment();

                if (null != listOffenderSentenceAdjustment
                        && !listOffenderSentenceAdjustment.isEmpty()) {

                    long count = listOffenderSentenceAdjustment.stream()
                            .filter(adjust -> adjust.getRecordFlag().equals(DELETE)).count();

                    if (count < listOffenderSentenceAdjustment.size()) {
                        reponseEvent.setEventResponse(FAILED);

                    } else {
                        int deleteCount = 0;
                        for (OffenderSentenceAdjustment adjust : listOffenderSentenceAdjustment) {
                            // delete adjustment
                            boolean deleteStatus = insertUpdateRecords(
                                    "DELETE_ADUJSTMENT_BY_OFFENDERBOOKID_OBJECTID_OBJECTTYPE", adjust);
                            if (deleteStatus) {
                                deleteCount++;
                            }
                            // response building
                            reponseEvent.addAdjustmentResponse(adjust.getOrderAdjustCode() + " "
                                    + adjust.getOffenderBookId() + " " + adjust.getObjectId(),
                                    deleteStatus ? SUCCESS : FAILED);
                        }

                        if (deleteCount == listOffenderSentenceAdjustment.size()) {
                        	offenderPayrolEvent.setModifyUserId(offenderPayrolEvent.getCreateUserId());
                            boolean deleteEventStatus = insertUpdateRecords(
                                    "DELETE_PAROLE_EVENTS_BY_OFFENDERBOOKID_PAROLEEVENTID", offenderPayrolEvent);
                            if (deleteEventStatus) {
                                reponseEvent.setEventResponse(SUCCESS);
                            }
                        } else {
                            reponseEvent.setEventResponse(FAILED);
                        }
                    }

                } else {
                	offenderPayrolEvent.setModifyUserId(offenderPayrolEvent.getCreateUserId());
                	boolean deleteEventStatus = insertUpdateRecords(
                            "DELETE_PAROLE_EVENTS_BY_OFFENDERBOOKID_PAROLEEVENTID", offenderPayrolEvent);
                    if (deleteEventStatus) {
                        reponseEvent.setEventResponse(SUCCESS);
                    }
                	
                }
            } else {
                List<OffenderSentenceAdjustment> listOffenderSentenceAdjustment = offenderPayrolEvent
                        .getListOffenderSentenceAdjustment();
                boolean updateRecords = insertUpdateRecords("UPDATE_PAYROL_EVENT", offenderPayrolEvent);

                reponseEvent.setEventResponse(updateRecords ? SUCCESS : FAILED);
                reponseEvent.setParoleEventId(offenderPayrolEvent.getParoleEventId());

                if (updateRecords && null != listOffenderSentenceAdjustment
                        && !listOffenderSentenceAdjustment.isEmpty()) {
                    for (OffenderSentenceAdjustment adjustment : listOffenderSentenceAdjustment) {
                        adjustment.setCreateUserId(offenderPayrolEvent.getCreateUserId());
                        if (StringUtils.isNotBlank(adjustment.getRecordFlag())
                                && adjustment.getRecordFlag().equals(INSERT)) {
                            adjustment.setObjectId(offenderPayrolEvent.getParoleEventId());
                            // insert adjustment
                            boolean insertAdjustment = insertUpdateRecords("INSERT_PAYROL_ADJUSTMENTS", adjustment);

                            // response building
                            reponseEvent.addAdjustmentResponse(adjustment.getOrderAdjustCode() + " "
                                    + adjustment.getOffenderBookId() + " " + adjustment.getObjectId(),
                                    insertAdjustment ? SUCCESS : FAILED);
                        } else if (StringUtils.isNotBlank(adjustment.getRecordFlag())
                                && adjustment.getRecordFlag().equals(UPDATE)){
                            // update adjustment
                            adjustment.setObjectId(null != adjustment.getObjectId() ? adjustment.getObjectId()
                                    : offenderPayrolEvent.getParoleEventId());
                            boolean insertAdjustment = insertUpdateRecords("UPDATE_PAYROL_ADJUSTMENTS", adjustment);

                            // response building
                            reponseEvent.addAdjustmentResponse(adjustment.getOrderAdjustCode() + " "
                                    + adjustment.getOffenderBookId() + " " + adjustment.getObjectId(),
                                    insertAdjustment ? SUCCESS : FAILED);
                        } else if (StringUtils.isNotBlank(adjustment.getRecordFlag())
                                && adjustment.getRecordFlag().equals(DELETE)){
                        	boolean deleteStatus = insertUpdateRecords("DELETE_ADUJSTMENT_BY_ADJUSTMENTID", adjustment);
                        }
                    }
                }
            }
        }

        return outputRespnose;
    }

    @Override
    public List<OffenderSentenceAdjustment> findAdjustmentByOffBookIdObjIdObjType(Long offenderBookId, Long objectId,
            String objectType) {

        final String sql = getQuery("GET_PAYROL_EVENT_BY_OFFENDER_BOOKID_OBJECT_ID_OBJECT_TYPE");
        final RowMapper<OffenderSentenceAdjustment> rowMap = Row2BeanRowMapper.makeMapping(sql,
                OffenderSentenceAdjustment.class,
                offenderSentenceAdjustment);
        List<OffenderSentenceAdjustment> listEvents = namedParameterJdbcTemplate.query(sql,
                createParams("OFFENDER_BOOK_ID", offenderBookId, "OBJECT_ID", objectId, "OBJECT_TYPE", objectType),
                rowMap);
        return listEvents;

    }

    @Override
    public List<ReferenceCodes> getAdjustmentTyep() {
        final String sql = getQuery("GET_ADUJSTMENT_TYPE");
        final RowMapper<ReferenceCodes> rowMap = Row2BeanRowMapper.makeMapping(sql,
                ReferenceCodes.class,
                referenceCodesMapping);
        List<ReferenceCodes> listEvents = namedParameterJdbcTemplate.query(sql, rowMap);
        return listEvents;
    }

	@Override
	public List<OffenderSentenceAdjustment> findAdjustmentByOffBookId(Long offenderBookId) {
		 final String sql = getQuery("GET_PAYROL_ADJUSTMENT_BY_OFFENDER_BOOKID");
	        final RowMapper<OffenderSentenceAdjustment> rowMap = Row2BeanRowMapper.makeMapping(sql,
	                OffenderSentenceAdjustment.class,
	                offenderSentenceAdjustment);
	        List<OffenderSentenceAdjustment> listEvents = namedParameterJdbcTemplate.query(sql,
	                createParams("OFFENDER_BOOK_ID", offenderBookId),
	                rowMap);
	        return listEvents;
	}

}
