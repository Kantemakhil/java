package net.syscon.s4.pkgs.oms_trigger_objects.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_trigger_objects.DeleteItemTransactionRepository;
@Repository
public class DeleteItemTransactionRepositoryImpl extends RepositoryBase implements DeleteItemTransactionRepository {
    
	private static Logger logger = LogManager.getLogger(DeleteItemTransactionRepositoryImpl.class.getName()); 
	
	@Override
	public Integer offenderPptyItemTxnsDelete(final Integer offenderBookId,final Integer propertyItemSeq,final String pToStatusCode,String modifyUserId) {
		final String sql=getQuery("OFFENDER_PPTY_ITEM_TXNS_DELETE");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "OFFENDER_PPTY_ITEM_TXNS";
			String whereCondition = "offender_book_id = :p_offender_book_id AND property_item_seq = :p_property_item_seq AND to_status_code = :p_to_status_code";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_property_item_seq", propertyItemSeq);
			inputMap.put("p_to_status_code", pToStatusCode);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offenderPptyItemTxnsDelete " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",offenderBookId,"p_property_item_seq",propertyItemSeq,"p_to_status_code",pToStatusCode));
	}
	
	@Override
	public Integer offenderPptyItemTxnsUpdate(final OffenderPptyItems item) {
		final String sql=getQuery("OFFENDER_PPTY_ITEM_TXNS_UPDATE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id",item.getOffenderBookId(),"p_property_item_seq",item.getPropertyItemSeq(),"p_to_status_code","REGISTERED","p_comment_text",item.getCommentText(),"createUserId",item.getCreateUserId()));

	}
}
