package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.triggers.ImagesT1Repository;
@Repository
public class ImagesT1RepositoryImpl extends RepositoryBase implements ImagesT1Repository{
	private static Logger logger = LogManager.getLogger(ImagesT1RepositoryImpl.class);
	@Override
	public Integer imageOriginalsInsert(List<ImageOriginals> insertList) {
		final String sql = getQuery("IMAGES_T1_INSERT_IMAGE_ORIGINALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for(ImageOriginals bean: insertList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));	
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("imageOriginalsInsert", e);
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
