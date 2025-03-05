package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcdsenchRepository;

@Repository
public class OcdsenchRepositoryImpl extends RepositoryBase implements OcdsenchRepository{
	
	private final Map<String, FieldMapper> uiBeanMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMP_ID",                  new FieldMapper("compId"))
			.put("COMP_TYPE",                new FieldMapper("compType"))
			.put("CREATED_BY",               new FieldMapper("createdBy"))
			.put("MODIFIED_BY",              new FieldMapper("modifiedBy"))
			.put("CREATE_DATE",              new FieldMapper("createDate"))
			.put("MODIFY_DATE",              new FieldMapper("modifyDate"))
			.put("COMP_CONFIG",              new FieldMapper("compConfig"))
			.put("COMP_CONFIG_DEF",          new FieldMapper("compConfigDef"))
			.build();

	@Override
	public List<OdynfrmSubmitDataBean> getSentenceHistoryData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		String sql = getQuery("DYNAMIC_SENTENCE_HISTORY_DATA").replace("%formName%", odynfrmSubmitDataBean.getFormName().toLowerCase());
		final RowMapper<OdynfrmSubmitDataBean> uiBeanMapper = Row2BeanRowMapper.makeMapping(sql, OdynfrmSubmitDataBean.class,
				uiBeanMapping);
		String searchString = StringUtils.join(Arrays.asList(odynfrmSubmitDataBean.getSearchString().split(";")),"%') AND LOWER(FORM_IDENTIFIER"
				+ "::text) LIKE LOWER('%");
		sql = sql + searchString + "%\')  order by CREATE_DATETIME desc";
		System.out.println(sql);
		final ArrayList<OdynfrmSubmitDataBean> returnList = (ArrayList<OdynfrmSubmitDataBean>) namedParameterJdbcTemplate.query(sql, uiBeanMapper);
		if(returnList.isEmpty()) {
			return new ArrayList<OdynfrmSubmitDataBean>();
		}
		for (OdynfrmSubmitDataBean obj : returnList) {
			obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
		}
		return returnList;
	}

}
