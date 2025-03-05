package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.inst.securitythreatgroups.OidappndRepository;

@Repository
public class OidappndRepositoryImpl extends RepositoryBase implements OidappndRepository {
	private static Logger logger = LogManager.getLogger(OidappndRepositoryImpl.class.getName());


	@Override
	public List<OmsModules> createFormGlobalscreateFormGlobals(final OmsModules paramBean) {
		return null;
	}

	@Override
	public String getNewText(final String newText) {
		final String sql = getQuery("OIDAPPND_GET_NEW_TEXT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("newText", newText), String.class);
	}

	@Override
	public Integer oidappndOidmbrdtUpdateOffenderStgAffiliations(final StgCaseNotes commitBean) {
		final String sql = getQuery("OIDAPPND_OIDMBRDT_UPDATE_OFFENDER_STG_AFFILIATIONS");
		final String sql1 = getQuery("OIDAPPND_OCDCPTIT_OFFENDER_STG_AFFILIATIONS");
		final Map<String, Object> param = new HashMap<>();
		param.put("newValue", commitBean.getNewText());
		param.put("offenderBookId", commitBean.getOffenderBookId());
		param.put("stgSeq", commitBean.getStgSeq());
		param.put("modifyUserId", commitBean.getModifyUserId());
		try {
			String commentText=namedParameterJdbcTemplate.queryForObject(sql1, param, String.class);
			if(commentText!=null)
				param.put("newValue", commentText+commitBean.getNewText());
			namedParameterJdbcTemplate.update(sql, param);
			return 1;
		} catch (Exception e) {
			logger.error("oidappndOidmbrdtUpdateOffenderStgAffiliations"+e);
			return 0;
		}
	}

	@Override
	public Integer oidappndOidstgcnUpdateStgCaseNotes(final StgCaseNotes commitBean) {
		final String sql = getQuery("OIDAPPND_OIDSTGCN_UPDATE_STG_CASE_NOTES");
		final String sql1=getQuery("OIDAPPND_OCDCPTIT_STG_CASE_NOTES");
		final Map<String, Object> param = new HashMap<>();
		param.put("modifyUserId", commitBean.getModifyUserId());
		param.put("newValue", commitBean.getNewText());
		param.put("stgId", commitBean.getStgId());
		param.put("noteSeq", commitBean.getNoteSeq());
		String text=namedParameterJdbcTemplate.queryForObject(sql1, param,String.class);
		try {
			if(text!=null)
			param.put("newValue", text+commitBean.getNewText());
			namedParameterJdbcTemplate.update(sql, param);
			return 1;
		} catch (Exception e) {
			logger.error("oidappndOidmbrdtUpdateOffenderStgAffiliations"+e);
			return 0;
		}
	}

	@Override
	public Integer oidappndOidmbrquUpdateOffenderAssessmentItems(final StgCaseNotes commitBean) {
		final String sql = getQuery("OIDAPPND_OIDMBRQU_UPDATE_OFFENDER_ASSESSMENT_ITEMS");
		final Map<String, Object> param = new HashMap<>();
		param.put("newValue", commitBean.getNewText());
		param.put("offenderBookId", commitBean.getOffenderBookId());
		param.put("assessmentSeq", commitBean.getAssessmentSeq());
		param.put("itemSeq", commitBean.getItemSeq());

		try {
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(commitBean));
			return 1;
		} catch (Exception e) {
			logger.error("oidappndOidmbrdtUpdateOffenderStgAffiliations"+e);
			return 0;
		}
	}

	@Override
	public Integer oidappndOcdcptitUpdateOffenderPtr(final StgCaseNotes commitBean) {
		final String sql = getQuery("OIDAPPND_OCDCPTIT_UPDATE_OFFENDER_PTR");
		final Map<String, Object> param = new HashMap<>();
		param.put("newValue", commitBean.getNewText());
		param.put("ptrId", commitBean.getPtrId());

		try {
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(commitBean));
			return 1;
		} catch (Exception e) {
			logger.error("oidappndOidmbrdtUpdateOffenderStgAffiliations"+e);
			return 0;
		}
	}

}
