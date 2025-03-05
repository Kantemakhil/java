package net.syscon.s4.pkgs.tag_off_ap_v2.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.pkgs.tag_off_ap_v2.TagOffApV2Repository;
import net.syscon.s4.pkgs.tag_off_ap_v2.TagOffApV2Service;

@Service
public class TagOffApV2ServiceImpl implements TagOffApV2Service {
	Logger logger = LogManager.getLogger(TagOffApV2ServiceImpl.class);
	@Autowired
	TagOffApV2Repository tagOffApV2Repository;

	@Override
	public Integer prInsProcedure(final OffenderActionPlans lrOldRec, final OffenderActionPlans lrNewRec) {
		Integer result = 0;
		final OffenderActionPlans targetObj = new OffenderActionPlans();
		try {
			BeanUtils.copyProperties(lrNewRec, targetObj);
			result = tagOffApV2Repository.prIns(targetObj);
		} catch (final Exception e) {
			logger.error("prInsProcedure", e);
		}
		return result;
	}

	@Override
	public Integer prDelProcedure(final OffenderActionPlans lrOldRec, final OffenderActionPlans lrNewRec) {
		Integer result = 0;
		try {
			result = tagOffApV2Repository.prDel(lrOldRec);
		} catch (final Exception e) {
			logger.error("prDelProcedure", e);
		}
		return result;
	}

	@Override
	public Integer prUpdProcedure(final OffenderActionPlans lrOldRec, final OffenderActionPlans lrNewRec) {
		Integer result = 0;
		final OffenderActionPlans targetObj = new OffenderActionPlans();
		try {
			BeanUtils.copyProperties(lrNewRec, targetObj);
			targetObj.setOffActionPlanId(lrOldRec.getOffActionPlanId());
			result = tagOffApV2Repository.prUpd(targetObj);
		} catch (final Exception e) {
			logger.error("prUpdProcedure", e);
		}
		return result;
	}

	@Override
	public String prLck(final String ptPkColumnValues) {
		/**** TODO ***/
//		 PkColumnTable      tag_text_table_type := tag_text_table_type('off_action_plan_id');
//		  ptPkColumnValues1  tag_text_table_type := tag_text_table_type(ptPkColumnValues);
		prTableLock("OFFENDER_ACTION_PLANS", "PkColumnTable", ptPkColumnValues);
		return null;
	}

	@Override
	public String prTableLock(final String pvTableName, final String ptPkColumnNames, final String ptPkColumnValues) {
		/**** TODO ***/
//		lvSQL         VARCHAR2(4000) := '';
//	    lvWhereClause VARCHAR2(2000) := '';
//	  BEGIN
//	    IF ptPkColumnValues.COUNT = 0 THEN
//	      -- Lock Table
//	      lvSQL := 'LOCK TABLE ' || pvTableName || ' IN EXCLUSIVE MODE NOWAIT';
//	      EXECUTE IMMEDIATE lvSQL;
//	    ELSIF ptPkColumnNames.COUNT != ptPkColumnValues.COUNT THEN
//	      RAISE_APPLICATION_ERROR(-20001,
//	                              'Programming error, PK Columns/Values Mismatch!');
//	    ELSE
//	      FOR i IN ptPkColumnNames.FIRST .. ptPkColumnNames.LAST LOOP
//	        IF i = 1 THEN
//	          lvWhereClause := ptPkColumnNames(i) || ' = :' || TO_CHAR(i) || ' ';
//	        ELSE
//	          lvWhereClause := lvWhereClause || ' AND ' || ptPkColumnNames(i) ||
//	                           ' = :' || TO_CHAR(i) || ' ';
//	        END IF;
//	      END LOOP;
//	      lvSQL := 'SELECT 1 FROM ' || pvTableName || ' WHERE ' ||
//	               lvWhereClause || 'FOR UPDATE NOWAIT';
//	      IF ptPkColumnValues.COUNT = 1 THEN
//	        EXECUTE IMMEDIATE lvSQL
//	          USING ptPkColumnValues(1);
//	      ELSIF ptPkColumnValues.COUNT = 2 THEN
//	        EXECUTE IMMEDIATE lvSQL
//	          USING ptPkColumnValues(1), ptPkColumnValues(2);
//	      ELSIF ptPkColumnValues.COUNT = 3 THEN
//	        EXECUTE IMMEDIATE lvSQL
//	          USING ptPkColumnValues(1), ptPkColumnValues(2), ptPkColumnValues(3);
//	      ELSIF ptPkColumnValues.COUNT = 4 THEN
//	        EXECUTE IMMEDIATE lvSQL
//	          USING ptPkColumnValues(1), ptPkColumnValues(2), ptPkColumnValues(3), ptPkColumnValues(4);
//	      ELSIF ptPkColumnValues.COUNT = 5 THEN
//	        EXECUTE IMMEDIATE lvSQL
//	          USING ptPkColumnValues(1), ptPkColumnValues(2), ptPkColumnValues(3), ptPkColumnValues(4), ptPkColumnValues(5);
//	      END IF;
//	    END IF;
//	    EXCEPTION
//	       WHEN OTHERS THEN
//	   DBMS_OUTPUT.PUT_LINE('OmsErr-0001: tag_off_ap_v2_pkg.prTableLock'||SQLERRM );
//	END prTableLock;
		return null;
	}

}
