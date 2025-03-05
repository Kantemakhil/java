package net.syscon.s4.iwp.base.impl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.EoffenderUtilities;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.iwp.base.DocumentRepository;

@Repository
public class DocumentRepositoryImpl extends RepositoryBase implements DocumentRepository{

	private static Logger log = LogManager.getLogger(DocumentRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> iwpDocumentMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOCUMENT_ID", 					new FieldMapper("documentId"))
			.put("OFFENDER_BOOK_ID",  				new FieldMapper("offenderBookId"))
			.put("DOCUEMTN_NAME",  					new FieldMapper("documentName"))
			.put("TEMPLATE_ID",  					new FieldMapper("templateId"))
			.put("TEMPLATE_NAME",  					new FieldMapper("templateName"))
			.put("OBJECT_ID",  						new FieldMapper("objectId"))
			.put("OBJECT_TYPE",  					new FieldMapper("objectType"))
			.put("DOCUMENT_STATUS",  				new FieldMapper("documentStatus"))
			.put("DATE_CREATED",  					new FieldMapper("dateCreated"))
			.put("SIGNATURE_ACCESS",  					new FieldMapper("signatureAccess"))
			.put("MODULE_NAME",  					new FieldMapper("moduleName")).build();

	private final Map<String, FieldMapper> iwpDocumentContentMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOCUMENT_ID", 					new FieldMapper("documentId"))
			.put("OFFENDER_BOOK_ID",  				new FieldMapper("offenderBookId"))
			.put("DOCUEMTN_NAME",  					new FieldMapper("documentName"))
			.put("DOCUMENT_BODY",  					new FieldMapper("documentContent"))
			.put("TEMPLATE_ID",  					new FieldMapper("templateId"))
			.put("OBJECT_ID",  						new FieldMapper("objectId"))
			.put("OBJECT_TYPE",  					new FieldMapper("objectType"))
			.put("DOCUMENT_STATUS",  				new FieldMapper("documentStatus"))
			.put("SIGNED_DOC",  				new FieldMapper("signedDoc"))
			.put("DATE_CREATED",  					new FieldMapper("userCreated"))
			.put("MODULE_NAME",  					new FieldMapper("moduleName")).build();


	private final Map<String, FieldMapper> IWPTemplateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEMPLATE_ID", 				new FieldMapper("templateId"))
			.put("TEMPLATE_NAME",  				new FieldMapper("templateName"))
			.put("DESCRIPTION",  				new FieldMapper("description"))
			.put("LOCATION",  					new FieldMapper("location"))
			.put("ACTIVE_FLAG",  				new FieldMapper("activeFlag"))
			.put("TEMPLATE_BODY",  				new FieldMapper("templateContent"))
			.put("DATE_CREATED", 				new FieldMapper("dateCreated"))
			.put("USER_CREATED",  				new FieldMapper("userCreated"))
			.put("LOCK_PASSWORD",  				new FieldMapper("lockPassword"))
			.put("OBJECT_TYPE",  				new FieldMapper("objectType"))
			.put("CREATE_DATETIME",  			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",  			new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",  			new FieldMapper("modifyUserId"))
			.put("EDRMS_RECORD_NO",  			new FieldMapper("edrmsRecordNo"))
			.put("TEMPLATE_TYPE",  				new FieldMapper("templateType"))
			.build();

	private final Map<String, FieldMapper> offDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY",			new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME",					new FieldMapper("lastName"))
			.put("FIRST_NAME",					new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID",			new FieldMapper("offenderBookId"))
			.put("BIRTH_DATE",					new FieldMapper("birthDate"))
			.build();


	@Autowired
	private JdbcTemplate jdbcTemplate;



	@Override
	public List<IwpDocuments> getIWPDocumentList(Long offenderBookId, String moduleName) {
		List<IwpDocuments> result =new ArrayList<>();
		final String sql = getQuery("GET_IWP_DOC_BY_MODULE_OFFENDER_BOOK_ID");
		final RowMapper<IwpDocuments> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpDocuments.class, iwpDocumentMapping);
		try {
			result =  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderBookId,"moduleName",moduleName), eoffenderRowMapper);
		} catch (Exception e) {
			log.error("getIWPDocumentList "+e.getMessage());
		}
		return result;
	}

	@Override
	public List<IwpDocuments> getIWPDocumentObjectOffBkgList(Long offenderBookingId, String objectId,
															 String moduleName) {
		List<IwpDocuments> result =new ArrayList<>();
		final String sql = getQuery("GET_IWP_DOC_BY_MODULE_OFFENDER_ID_OBJECT_ID");
		final RowMapper<IwpDocuments> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpDocuments.class, iwpDocumentMapping);
		try {
			result =  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderBookingId,"objectId", objectId,"moduleName",moduleName), eoffenderRowMapper);
		} catch (Exception e) {
			log.error("getIWPDocumentList "+e.getMessage());
		}
		return result;
	}

	@Override
	public List<IwpDocuments> getIWPObjectDocumentList(String objectId, String moduleName) {
		List<IwpDocuments> result =new ArrayList<>();
		final String sql = getQuery("GET_IWP_DOC_BY_MODULE_OBJECT_ID");
		final RowMapper<IwpDocuments> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpDocuments.class, iwpDocumentMapping);
		try {
			result =  namedParameterJdbcTemplate.query(sql,createParams("objectId", objectId,"moduleName",moduleName), eoffenderRowMapper);
		} catch (Exception e) {
			log.error("getIWPDocumentList "+e.getMessage());
		}
		return result;
	}



	@Override
	public IwpTemplates getTemplateAsBlobFromDB(Long templateId) {
		BigDecimal id = new BigDecimal(templateId);
		String getBlobFromDBQuery = "SELECT * FROM iwp_templates where template_id = :ID_GENERATED";
		RowMapper<IwpTemplates> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getBlobFromDBQuery, IwpTemplates.class, IWPTemplateMapping);
		IwpTemplates iwpTemplate = namedParameterJdbcTemplate.queryForObject(getBlobFromDBQuery, createParams("ID_GENERATED", id), eoffenderRowMapper);
		return iwpTemplate;
	}



	@Override
	public IwpDocuments getIwpDocument(BigDecimal documentId) {
		String getBlobFromDBQuery = "select * from iwp_documents where document_id=:documentId";
		RowMapper<IwpDocuments> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getBlobFromDBQuery, IwpDocuments.class, iwpDocumentContentMapping);
		IwpDocuments iwpDocuments = namedParameterJdbcTemplate.queryForObject(getBlobFromDBQuery, createParams("documentId", documentId), eoffenderRowMapper);
		return iwpDocuments;
	}

	@Override
	public IwpTemplates getIwpTemplate(BigDecimal templateId) {
		String getBlobFromDBQuery = "select * from iwp_templates where template_id=:templateId";
		RowMapper<IwpTemplates> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getBlobFromDBQuery, IwpTemplates.class, IWPTemplateMapping);
		IwpTemplates iwpDocuments = namedParameterJdbcTemplate.queryForObject(getBlobFromDBQuery, createParams("templateId", templateId), eoffenderRowMapper);
		return iwpDocuments;
	}

	@Override
	public String updateDocStatus(BigDecimal id, String status,String fileName,String userName) throws Exception {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE IWP_DOCUMENTS SET DOCUMENT_STATUS = ?,DOCUMENT_NAME=?,modify_datetime = current_timestamp,modify_user_id =?  WHERE DOCUMENT_ID = ?");
			ps.setString(1, status);
			ps.setString(2, fileName);
			ps.setString(3, userName);
			ps.setBigDecimal(4, id);
			return ps;
		});
		return "SUCCESS";
	}

	@Override
	public int[] uploadDocument(List<IwpDocuments> iwpDocuments) {

		// Separate inserted Document and Updated Document list
		int[] succcessfullyAdded = null;

		List<IwpDocuments> insertIwpDocuments = iwpDocuments.stream().filter(d-> {return d.getDocumentId()==null;}).collect(Collectors.toList());

		List<IwpDocuments> updateIwpDocuments = iwpDocuments.stream().filter(d-> {return d.getDocumentId()!=null;}).collect(Collectors.toList());

		if (insertIwpDocuments != null && insertIwpDocuments.size() > 0) {
			String insetStatement = "INSERT INTO iwp_documents "
					+ "(DOCUMENT_ID,OFFENDER_BOOK_ID, DOCUMENT_NAME, TEMPLATE_ID, COMMENT_TEXT, ACTIVE_FLAG, LOCATION, OBJECT_ID, OBJECT_TYPE, DOCUMENT_STATUS, DOCUMENT_BODY,CREATE_DATETIME,MODIFY_DATETIME,CREATE_USER_ID,USER_CREATED) "
					+ "VALUES (nextval('IWP_DOCS_SEQ'),?, ?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,null,?,?)";

			int[] insertedDocument = jdbcTemplate.batchUpdate(insetStatement, new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setBigDecimal(1, insertIwpDocuments.get(i).getOffenderBookId());
							ps.setString(2, insertIwpDocuments.get(i).getDocumentName());
					ps.setBigDecimal(3, insertIwpDocuments.get(i).getTemplateId());
					ps.setString(4, insertIwpDocuments.get(i).getCommentText());
					ps.setString(5, insertIwpDocuments.get(i).getActiveFlag());
					ps.setString(6, insertIwpDocuments.get(i).getLocation());
					ps.setString(7, insertIwpDocuments.get(i).getObjectId());
					ps.setString(8, insertIwpDocuments.get(i).getObjectType());
					ps.setString(9, insertIwpDocuments.get(i).getDocumentStatus());
					ps.setBytes(10, insertIwpDocuments.get(i).getDocumentContent());
					ps.setString(11, insertIwpDocuments.get(i).getCreateUserId());
					ps.setString(12, insertIwpDocuments.get(i).getCreateUserId());
				}

				public int getBatchSize() {
					return insertIwpDocuments.size();
				}
			});
			succcessfullyAdded = insertedDocument;

		}
		if (updateIwpDocuments != null && updateIwpDocuments.size() > 0) {
			String insetStatement = "UPDATE iwp_documents SET COMMENT_TEXT = ?, DOCUMENT_STATUS=?, DOCUMENT_BODY=?,modify_datetime = current_timestamp,modify_user_id = ? where DOCUMENT_ID=?";
			int[] insertedDocument = jdbcTemplate.batchUpdate(
					insetStatement,
					new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {

					ps.setString(1, updateIwpDocuments.get(i).getCommentText());
					ps.setString(2, updateIwpDocuments.get(i).getDocumentStatus());
					ps.setBytes(3, updateIwpDocuments.get(i).getDocumentContent());
					ps.setString(4, updateIwpDocuments.get(i).getModifyUserId());
					ps.setBigDecimal(5, updateIwpDocuments.get(i).getDocumentId());
				}

				public int getBatchSize() {
					return updateIwpDocuments.size();
				}
			});
			if (succcessfullyAdded == null) {
				succcessfullyAdded = insertedDocument;
			}




		}
		return succcessfullyAdded;
	}

	@Override
	public int[] uploadTemplate(List<IwpTemplates> iwpDocuments) {


		//Separate inserted Document and Updated Document list
		int[] succcessfullyAdded = null;
		List<IwpTemplates> updateIwpDocuments = iwpDocuments.stream().filter(d-> {return d.getTemplateId()!=null;}).collect(Collectors.toList());

		if(updateIwpDocuments!=null && updateIwpDocuments.size()>0) {
			String insetStatement = "UPDATE iwp_templates SET TEMPLATE_BODY=?,modify_datetime=CURRENT_TIMESTAMP,modify_user_id=? where TEMPLATE_ID=?";
			int[] insertedDocument = jdbcTemplate.batchUpdate(
					insetStatement,
					new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setBytes(1, updateIwpDocuments.get(i).getTemplateContent());
							ps.setString(2, updateIwpDocuments.get(i).getModifyUserId());
							ps.setBigDecimal(3, updateIwpDocuments.get(i).getTemplateId());
						}
						public int getBatchSize() {
							return updateIwpDocuments.size();
						}
					});
			if(succcessfullyAdded == null) {
				succcessfullyAdded = insertedDocument;
			} 


		}
		return succcessfullyAdded;
	}



	@Override
	public BigDecimal getTempDocId() {
		final String offObsProfileCodeIdNextVal = "SELECT NEXTVAL('OFFENDER_TEMP_DOC_ID') FROM DUAL";
		BigDecimal offObsProfileCodeId = namedParameterJdbcTemplate.queryForObject(offObsProfileCodeIdNextVal, createParams(), BigDecimal.class);
		return offObsProfileCodeId;
	}

	@Override
	public String insertFileByteArrayFromWatcher(byte[] fileByteArray, String id, String fileName) throws Exception {
		BigDecimal key = new BigDecimal(id);
		// CHECK key is already present in database
		String getBlobFromDBQuery = "SELECT count(1) FROM OFFENDER_TEMP_DOC WHERE ID = :ID_GENERATED";

		MapSqlParameterSource retrieveKeyParams = new MapSqlParameterSource();
		retrieveKeyParams.addValue("ID_GENERATED", key);

		try {
			int count = namedParameterJdbcTemplate.queryForObject(getBlobFromDBQuery, retrieveKeyParams, Integer.class);

			if (count == 0) {

				jdbcTemplate.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO OFFENDER_TEMP_DOC (ID, FILE_DATA, DOCUMENT_NAME, EXT, STATUS) VALUES (?, ?, ?, ?, ?)");
					ps.setBigDecimal(1, key);
					ps.setBlob(2, new ByteArrayInputStream(fileByteArray), fileByteArray.length);
					ps.setString(3, fileName);
					ps.setString(4, EoffenderUtilities.getFileExtension(fileName));
					ps.setString(5, "D");
					return ps;
				});

			} else {

				jdbcTemplate.update(connection -> {
					PreparedStatement ps = connection
							.prepareStatement("UPDATE EOFFENDER_TEMP_DOC SET FILE_DATA = ? WHERE ID = ?");
					ps.setBlob(1, new ByteArrayInputStream(fileByteArray), fileByteArray.length);
					ps.setBigDecimal(2, key);
					return ps;
				});
			}
		} catch (Exception e) {
			log.error("insertFileByteArrayFromWatcher Save file in DB Error :: "+ e.getLocalizedMessage());
			return "FAILURE";
		}

		return "SUCCESS";

	}

	@Override
	public VHeaderBlock getOffenderDetails(Long offenderBookId,String userName) {
		List<VHeaderBlock> result =new ArrayList<>();
		final String sql = "SELECT OFFENDER_ID_DISPLAY, LAST_NAME, FIRST_NAME, OFFENDER_BOOK_ID, BIRTH_DATE FROM V_HEADER_BLOCK_FN(:USERID) V_header_block where OFFENDER_BOOK_ID = :offenderBookId";
		final RowMapper<VHeaderBlock> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class, offDetMapping);
		try {
			result =  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderBookId,"USERID",userName), eoffenderRowMapper);
		} catch (Exception e) {
			log.error("getIWPDocumentList "+e.getMessage());
		}
		return result.get(0);
	}

	@Override
	public Long getDocId() {
		final String sql = "SELECT Max(DOCUMENT_ID) from iwp_documents";
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	

	@Override
	public BigDecimal getIwpDocSeq() {
		final String sql = getQuery("GET_IWP_DOC_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public BigDecimal uploadDoc(List<IwpDocuments> iwpDocuments) {
		// Separate inserted Document and Updated Document list
		int[] succcessfullyAdded = null;
		
		
		if (iwpDocuments != null && iwpDocuments.get(0) != null) {
			if (iwpDocuments.get(0).getDocumentId() == null) {
				try {
				iwpDocuments.get(0).setDocumentId(getIwpDocSeq());
				String insetStatement = "INSERT INTO iwp_documents "
						+ "(DOCUMENT_ID,OFFENDER_BOOK_ID, DOCUMENT_NAME, TEMPLATE_ID, COMMENT_TEXT, ACTIVE_FLAG, LOCATION, OBJECT_ID, OBJECT_TYPE, DOCUMENT_STATUS, DOCUMENT_BODY,CREATE_DATETIME,MODIFY_DATETIME,CREATE_USER_ID,USER_CREATED) "
						+ "VALUES (?,?, ?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,null,?,?)";

				int[] insertedDocument = jdbcTemplate.batchUpdate(insetStatement, new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setBigDecimal(1, iwpDocuments.get(i).getDocumentId());
						ps.setBigDecimal(2, iwpDocuments.get(i).getOffenderBookId());
						ps.setString(3,
								iwpDocuments.get(i).getDocumentName().length() > 48
										? iwpDocuments.get(i).getDocumentName().substring(0, 49)
										: iwpDocuments.get(i).getDocumentName());
						ps.setBigDecimal(4, iwpDocuments.get(i).getTemplateId());
						ps.setString(5, iwpDocuments.get(i).getCommentText());
						ps.setString(6, iwpDocuments.get(i).getActiveFlag());
						ps.setString(7, iwpDocuments.get(i).getLocation());
						ps.setString(8, iwpDocuments.get(i).getObjectId());
						ps.setString(9, iwpDocuments.get(i).getObjectType());
						ps.setString(10, iwpDocuments.get(i).getDocumentStatus());
						ps.setBytes(11, iwpDocuments.get(i).getDocumentContent());
						ps.setString(12, iwpDocuments.get(i).getCreateUserId());
						ps.setString(13, iwpDocuments.get(i).getCreateUserId());
					}
					public int getBatchSize() {
						return iwpDocuments.size();
					}
				});
				succcessfullyAdded = insertedDocument;
				} catch (Exception e) {
					log.error("uploadDoc :: Inserting into IWP documents :: "+ e.getMessage());
				}

			} else {
				try {
				String insetStatement = "UPDATE iwp_documents SET COMMENT_TEXT = ?, DOCUMENT_STATUS=?, DOCUMENT_BODY=?,modify_datetime = current_timestamp,modify_user_id=?, DOCUMENT_NAME = ?  where DOCUMENT_ID=?";
				int[] insertedDocument = jdbcTemplate.batchUpdate(insetStatement, new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, iwpDocuments.get(i).getCommentText() == null ? ""
								: iwpDocuments.get(i).getCommentText());
						ps.setString(2, iwpDocuments.get(i).getDocumentStatus());
						ps.setBytes(3, iwpDocuments.get(i).getDocumentContent());
						ps.setString(4, iwpDocuments.get(i).getModifyUserId());
						ps.setString(5, iwpDocuments.get(i).getDocumentName());
						ps.setBigDecimal(6, iwpDocuments.get(i).getDocumentId());
					}
					public int getBatchSize() {
						return iwpDocuments.size();
					}
				});
				succcessfullyAdded = insertedDocument;
				} catch (Exception e) {
					log.error("uploadDoc :: Update Inserting into IWP documents :: "+ e.getMessage());
				}
			}
		}
		if (succcessfullyAdded != null && succcessfullyAdded.length > 0) {
			return iwpDocuments.get(0).getDocumentId();
		} else {
			return BigDecimal.ZERO;
		}
	}
	@Override
	public Integer insertSignedDoc(byte[] fileByteArray, BigDecimal documenId, java.sql.Date modifyDate,
			String modifyUser,String fileName) {
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection
						.prepareStatement("update iwp_documents SET signed_doc=?,MODIFY_DATETIME=?,MODIFY_USER_ID=?,DOCUMENT_STATUS = ?,DOCUMENT_NAME=? where document_id=?");
				ps.setBytes(1, fileByteArray);
				ps.setDate(2, modifyDate);
				ps.setString(3, modifyUser);
				ps.setString(4, "SIGNED");
				ps.setString(5, fileName);
				ps.setBigDecimal(6, documenId);
				
				return ps;
			});
	} catch (Exception e) {
		log.error("erro in insertSignedDoc Method :: "+ e.getMessage());
		return 0;
	}
	
	return 1;
	}

	@Override
	public List<IwpDocuments> getIwpDocumentList(Long offenderBookId) {
		List<IwpDocuments> result =new ArrayList<>();
		final String sql = getQuery("GET_OFFENDER_DOCS");
				final RowMapper<IwpDocuments> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpDocuments.class, iwpDocumentMapping);
		try {
			result =  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderBookId), eoffenderRowMapper);
		} catch (Exception e) {
			log.error("get Offender All DocumentList "+e.getMessage());
		}
		return result;
	}

	@Override
	public List<StaffMemberRoles> getStaffEliteDocDeleteRoles(String userId) {
		final String sql = getQuery("GET_STAFF_ELITE_DOC_DELETE_ROLES");
		return namedParameterJdbcTemplate.query(sql, createParams("userId",userId), new RowMapperResultSetExtractor<StaffMemberRoles>(new BeanPropertyRowMapper<StaffMemberRoles>(StaffMemberRoles.class)));
	
	}

	@Override
	public Integer deleteEliteDoc(IwpDocuments iwpDocuments) {
		final String userUpdateSql = getQuery("UPDATE_ELITE_DOC_MODIFY_USER");
		final String sql = getQuery("DELETE_ELITE_DOCUMENT");
		try {
			namedParameterJdbcTemplate.update(userUpdateSql, createParams("documentId", iwpDocuments.getDocumentId(),"user", iwpDocuments.getModifyUserId(),"deleteReason",iwpDocuments.getDeleteReason()));
			namedParameterJdbcTemplate.update(sql, createParams("documentId", iwpDocuments.getDocumentId()));
		} catch (Exception e) {
			log.error("Exception in deleteEliteDoc "+e.getMessage());
			return 0;
		}
		return 1;
	}

}
