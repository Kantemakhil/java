package net.syscon.s4.common.relatedscreens.impl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.RelatedScreens;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.common.relatedScreen.RelatedScreensRepository;

@Repository
public class RelatedScreensRepositoryImpl extends RepositoryBase implements RelatedScreensRepository {
	
	
	

	private static Logger log = LogManager.getLogger(RelatedScreensRepository.class.getName());

	public final Map<String, FieldMapper> relatedScreensMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("ACCESS_MODULE_NAME", new FieldMapper("accessModuleName"))
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();

	public List<RelatedScreens> getRelatedScreens() throws SQLException {
		List<RelatedScreens> refList = new ArrayList<>();
		String sql = getQuery("GET_RELATED_SCREENS");
		final RowMapper<RelatedScreens> relatedScreensRowMapper = Row2BeanRowMapper.makeMapping(sql,
				RelatedScreens.class, relatedScreensMapping);
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams(), relatedScreensRowMapper);
		} catch (Exception e) {
			log.error("Exceptions in RelatedScreensRepository", e);
		}
		return refList;
	}
	
	/**
	 * Creates new RelatedScreensRepositoryImpl class Object
	 */
	public RelatedScreensRepositoryImpl() {
		// RelatedScreensRepositoryImpl
	}

}
