package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.triggers.OffenderPptyItemsT1Repository;

@Repository
public class OffenderPptyItemsT1RepositoryImpl extends RepositoryBase implements OffenderPptyItemsT1Repository {

	private static Logger logger = LogManager.getLogger(OffenderPptyItemsT1RepositoryImpl.class.getName());

	@Override
	public Integer insert(final OffenderPptyItems old, final OffenderPptyItems newbean,
			final Integer vPropertyContainerId, final String lAgyLocId, final Integer vDisposedToPersonId,final String userId) {
		final String sql = getQuery("OFFENDER_PPTY_ITEM_TXNS_INSERT");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("offender_book_id", newbean.getOffenderBookId(), "property_item_seq",
							newbean.getPropertyItemSeq(), "v_event_seq", null, "status_code", old.getStatusCode(),
							"v_status_code", newbean.getStatusCode(), "comment_text", newbean.getCommentText(),
							"v_property_container_id", vPropertyContainerId, "l_agy_loc_id", lAgyLocId,
							"disposed_to_corp_id", newbean.getDisposedToCorpId(), "v_disposed_to_person_id",
							vDisposedToPersonId, "disposed_to_person", newbean.getDisposedToPerson(),
							"disposed_to_offender_flag", newbean.getDisposedToOffenderFlag(), "color",
							newbean.getColor(), "make", newbean.getMake(),"serial_no", newbean.getSerialNo(),"createUserId",userId,"actionCode",newbean.getActionCode(),"actionReason",newbean.getActionReason()));
		} catch (DataAccessException e) {
			logger.error("error in insert :" + e.getMessage());
		}
		return count;
	}

}
