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
import net.syscon.s4.triggers.ImagesTiRepository;

@Repository
public class ImagesTiRepositoryImpl extends RepositoryBase implements ImagesTiRepository {
	private final Logger logger = LogManager.getLogger(ImagesTiRepositoryImpl.class.getName());

	@Override
	public Integer insert(final Integer imageId,final String UserName) {
		final ImageOriginals iO = new ImageOriginals();
		iO.setImageId(imageId);
		iO.setCreateUserId(UserName);
		Integer returnValue = 0;
		final String sql = getQuery("IMAGES_TI_IMAGE_ORIGINALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(iO));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insert ", e);
		}
		return returnValue;

	}
}
