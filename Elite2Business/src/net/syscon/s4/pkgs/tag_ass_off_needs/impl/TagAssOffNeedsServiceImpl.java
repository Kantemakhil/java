package net.syscon.s4.pkgs.tag_ass_off_needs.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_ass_off_needs.TagAssOffNeedsRepository;
import net.syscon.s4.pkgs.tag_ass_off_needs.TagAssOffNeedsService;
import net.syscon.s4.triggers.AssessedOffenderNeeds;

@Service
public class TagAssOffNeedsServiceImpl implements TagAssOffNeedsService {
	Logger logger = LogManager.getLogger(TagAssOffNeedsServiceImpl.class);
	@Autowired
	TagAssOffNeedsRepository tagAssOffNeedsRepository;

	@Override
	public Integer prInsProcedure(final AssessedOffenderNeeds prOldRec, final AssessedOffenderNeeds prNewRec) {
		Integer result = 0;
		final AssessedOffenderNeeds targetObj = new AssessedOffenderNeeds();
		try {
			BeanUtils.copyProperties(prNewRec, targetObj);
			result = tagAssOffNeedsRepository.prIns(targetObj);
		} catch (final Exception e) {
			logger.error("prInsProcedure", e);
		}
		return result;
	}

	@Override
	public Integer prDelProcedure(final AssessedOffenderNeeds prOldRec, final AssessedOffenderNeeds prNewRec) {
		Integer result = 0;
		try {
			result = tagAssOffNeedsRepository.prDel(prOldRec);
		} catch (final Exception e) {
			logger.error("prDelProcedure", e);
		}
		return result;
	}

	@Override
	public Integer prUpdProcedure(final AssessedOffenderNeeds prOldRec, final AssessedOffenderNeeds prNewRec) {
		Integer result = 0;
		final AssessedOffenderNeeds targetObj = new AssessedOffenderNeeds();
		try {
			BeanUtils.copyProperties(prNewRec, targetObj);
			targetObj.setOffAssNeedId(prOldRec.getOffAssNeedId());
			result = tagAssOffNeedsRepository.prUpd(targetObj);
		} catch (final Exception e) {
			logger.error("prUpdProcedure", e);
		}
		return result;
	}

	@Override
	public String prLck(final String ptPkColumnValues) {
		/**** TODO ***/
//		 PkColumnTable      tag_text_table_type := tag_text_table_type('off_ass_need_id');
//		 ptPkColumnValues1  tag_text_table_type := tag_text_table_type(ptPkColumnValues);
		prTableLock("ASSESSED_OFFENDER_NEEDS", "", ptPkColumnValues);
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
//	   DBMS_OUTPUT.PUT_LINE('OmsErr-0001: TAG_ASS_OFF_NEEDSg.prTableLock'||SQLERRM );
//	END prTableLock;
		return null;
	}

}
