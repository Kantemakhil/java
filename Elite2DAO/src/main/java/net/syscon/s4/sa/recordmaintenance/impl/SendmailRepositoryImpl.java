package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.SendmailRepository;

@Repository
public class SendmailRepositoryImpl extends RepositoryBase implements SendmailRepository {
	
	private static Logger logger = LogManager.getLogger(SendmailRepositoryImpl.class.getName());
	public SendmailRepositoryImpl() {
		// SendmailRepositoryImpl
	}
	
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))
			.build();
	
	@Override
	public List<SystemProfiles> getClicksendConfDet() {
		final String sql = getQuery("SENDMAIL_GET_CLICKSEND_CONF_DET");
		final RowMapper<SystemProfiles> clicksendRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), clicksendRowMapper);
		return returnList;
	}

	@Override
	public String getMailType() {
		String mailType = "";
		final String sql = getQuery("SENDMAIL_GET_MAIL_TYPE");
		try {
			mailType = namedParameterJdbcTemplate.queryForObject(sql,createParams(),String.class);
		} catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return mailType;
	}
	

}
