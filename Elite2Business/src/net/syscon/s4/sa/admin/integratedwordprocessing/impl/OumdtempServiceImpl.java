package net.syscon.s4.sa.admin.integratedwordprocessing.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.CustomMultipartFile;
import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.iwp.base.DocumentRepository;
import net.syscon.s4.pkgs.iwp_10g.Iwp10gService;
import net.syscon.s4.sa.admin.beans.IwpBookmarkParameters;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;
import net.syscon.s4.sa.admin.beans.IwpParameterMappings;
import net.syscon.s4.sa.admin.beans.IwpParameterMappingsCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplateModulesCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplateRoles;
import net.syscon.s4.sa.admin.beans.IwpTemplateRolesCommitBean;
import net.syscon.s4.sa.admin.beans.IwpTemplatesCommitBean;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumdtempRepository;
import net.syscon.s4.sa.admin.integratedwordprocessing.OumdtempService;

/**
 * Class OumdtempServiceImpl
 */
@Service
public class OumdtempServiceImpl extends BaseBusiness implements OumdtempService {

	@Autowired
	private OumdtempRepository oumdtempRepository;
	public static final Integer CONSTANTVALUE = 2;
	public static final Integer SUCCESSVALUE = 1;
	private static final  int BUFFER_SIZE = 2048;
	
	@Autowired
	private DocManageUtilities docManageUtilities;
	
	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	private Iwp10gService iwp10gService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Integer aIwpTagRelationsPostUpdate(IwpParameterMappings paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<IwpParameterMappings> aIwpTagRelationsOnCheckDeleteMaster(IwpParameterMappings paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<IwpBookmarkParameters> iwpParameterMappingsPostQuery(IwpBookmarkParameters paramBean) {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<IwpTemplates> aIwpTemplatesExecuteQuery(IwpTemplates searchRecord) {
		List<IwpTemplates> returnList = oumdtempRepository.aIwpTemplatesExecuteQuery(searchRecord);
		/*for (IwpTemplates bean : returnList) {
			String value = whenActChangedEvent(bean);
			if (value != null) {
				bean.setActiveCheckFlag(value);
			}
		}*/
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstA_IWP_TEMPLATES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer aIwpTemplatesCommit(IwpTemplatesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (IwpTemplates bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setUserCreated(commitBean.getCreateUserId());
			}
			if (templateNameexists(commitBean.getInsertList()) > 0) {
				return CONSTANTVALUE;
			}
			liReturn = oumdtempRepository.aIwpTemplatesInsertIwpTemplates(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IwpTemplates bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setUserCreated(commitBean.getCreateUserId());
			}
			liReturn = oumdtempRepository.aIwpTemplatesUpdateIwpTemplates(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oumdtempRepository.aIwpTemplatesDeleteIwpTemplates(commitBean.getDeleteList());
		}
		return liReturn;
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public Integer templateNameexists(List<IwpTemplates> insertList) {
		final List<String> returnObj = insertList.stream().map(data -> data.getTemplateName())
				.collect(Collectors.toList());
		return oumdtempRepository.templateNameexists(returnObj);

	}
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<IwpTemplateModules> aIwpTagRelationsExecuteQuery(IwpTemplateModules searchRecord) {
		return oumdtempRepository.aIwpTagRelationsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstA_IWP_TAG_RELATIONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer aIwpTagRelationsCommit(IwpTemplateModulesCommitBean commitBean) {
		int liReturn = 0;
		List<IwpTemplateModules> upadteList = new ArrayList<>();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (IwpTemplateModules bean : commitBean.getInsertList()) {
				BigDecimal tempModuleId = oumdtempRepository.aIwpTagRelationsPreInsert();
				bean.setTemplateModuleId(tempModuleId);
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			String returnVal = oumdtempRepository.aIwpTagRelationsInsertIwpTemplateModules(commitBean.getInsertList());
			if(returnVal.contains("iwp_template_modules_uk")) {
				return CONSTANTVALUE;
			} 
			if ("1".equals(returnVal)) {
				liReturn = SUCCESSVALUE;
				if(commitBean.getInsertList().get(0).getBlockName()!= null){
				liReturn = oumdtempRepository.tagRelationsPostInsert(commitBean.getInsertList());
				}
			} 
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (IwpTemplateModules bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			String  returnVal= oumdtempRepository.aIwpTagRelationsUpdateIwpTemplateModules(commitBean.getUpdateList());
			if(returnVal.contains("IWP_TEMPLATE_MODULES_UK")) {
				return CONSTANTVALUE;
			} 
			if ("1".equals(returnVal)) {
				liReturn = SUCCESSVALUE;
				if(commitBean.getUpdateList().get(0).getBlockName()!= null) {
				for (IwpTemplateModules bean : commitBean.getUpdateList()) {
					Integer parmCount = oumdtempRepository.aIwpTagRelationsPostUpdate(bean);
					if (parmCount == 0) {
						upadteList.add(bean);

					}
				}
				if (!upadteList.isEmpty()) {
					liReturn = oumdtempRepository.tagRelationsPostInsert(upadteList);
				}
				}
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oumdtempRepository.aIwpTagRelationsDeleteIwpTemplateModules(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<IwpParameterMappings> iwpParameterMappingsExecuteQuery(IwpParameterMappings searchRecord) {
		return oumdtempRepository.iwpParameterMappingsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIWP_PARAMETER_MAPPINGS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer iwpParameterMappingsCommit(IwpParameterMappingsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (IwpParameterMappings bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumdtempRepository.iwpParameterMappingsUpdateIwpParameterMappings(commitBean.getUpdateList());
		}
		return liReturn;
	}

	
	 public List<IwpTemplateRoles>  iwpRolesExecuteQuery(IwpTemplates searchRecord) {
	 return  iwp10gService.getTemplRoles(searchRecord);
	 }
	
	
	 @Transactional
	 public Integer templRolesCommit(IwpTemplateRolesCommitBean commitBean) {
		 int liReturn = 0;
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (IwpTemplateRoles bean : commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
				}
				if(isTemplRoleExists(commitBean.getInsertList()) >0){
					return CONSTANTVALUE;
				}
				liReturn = oumdtempRepository.aIwpTagRelationsInsertQuery(commitBean.getInsertList());

			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = oumdtempRepository.aIwpTagRelationsDeleteQuery(commitBean.getDeleteList());
			}
			return liReturn;
	 }
	 public Integer  isTemplRoleExists(List<IwpTemplateRoles> listObj) {
		 final List<Long> tempId = listObj.stream().map(data -> data.getTemplateId())
					.collect(Collectors.toList());
		 final List<String> roleCode = listObj.stream().map(data -> data.getRoleCode())
					.collect(Collectors.toList());
		 
		return oumdtempRepository.isTemplRoleExists(tempId,roleCode);
		 
	 }

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsRoles> rgRolesRecordGroup() {
		return oumdtempRepository.rgRolesRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<IwpBookmarks> rgBmListRecordGroup() {
		return oumdtempRepository.rgBmListRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsModules> rgOmsModuleRecordGroup() {
		return oumdtempRepository.rgOmsModuleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OmsModules> rgReportNameRecordGroup() {
		List<OmsModules> reportList =  oumdtempRepository.rgReportNameRecordGroup();
		List<OmsModules> docList = oumdtempRepository.rgDocNameRecordGroup();
		for(OmsModules bean : docList) {
			bean.setCanDisplay(false);
			reportList.add(bean);
		}
		return reportList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffRecordGroup() {
		return oumdtempRepository.rgStaffRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgObjectTypeRecordGroup() {
		return oumdtempRepository.rgObjectTypeRecordGroup();

	}

	@Override
	public String whenActChangedEvent(IwpTemplates searchBean) {
		String returnValue = null;
		Integer vGrpCnt= oumdtempRepository.bookmarkGrpCur(searchBean);
		if (vGrpCnt >0) {
			Integer vContextCnt= oumdtempRepository.isContextRecCur(searchBean);
			if (vContextCnt > 0) {
				if (searchBean.getBlockName() == null && searchBean.getBlockDescription() == null) {
					return  "context";
				} else {
					Integer vCnt= oumdtempRepository.recExistsCur(searchBean);
					if (vCnt > 0 ) {
						return "Bookmark";
					}
				}
			}
		}
		return returnValue;
	}

	
	
	private  boolean uncompress(String inFile, String outFile){
	    GZIPInputStream fin = null;
	    OutputStream out = null;
	    try{
	       out = new FileOutputStream(outFile);
	       fin = new GZIPInputStream(new FileInputStream(inFile));
	       byte[] buf = new byte[BUFFER_SIZE];
	       int len = 0;
	       while((len = fin.read(buf)) > 0){
	         out.write(buf, 0, len);
	       }
	       fin.close();
	       out.close();
	       return true;
	    }catch(Exception e){
	       try{
	         if (fin != null) fin.close();
	         if (out != null) out.close();
	       }catch(Exception ex){}
	       return false;
	    }
	  }
	
	private File saveFileToTempLoc(String pathToDirectory, byte[] templateByteArray,
			String offenderIdDisplay, String fileType) throws IOException {
		
		Calendar cal = Calendar.getInstance();
		File directoryPath = new File(pathToDirectory);
		if (!directoryPath.exists()) {
			directoryPath.mkdirs();
		}
		String saveDir = pathToDirectory.concat(offenderIdDisplay + "_").concat(cal.getTimeInMillis() + "").concat(fileType);
		File trimFile = new File(saveDir);
		CustomMultipartFile customMultipartFile = new CustomMultipartFile(templateByteArray);
		customMultipartFile.transferTo(trimFile);
		return trimFile;
	}

	@Override
	public IwpTemplates viewTemplate(BigDecimal documentId, String path)  {
		IwpTemplates iwpDocuments = documentRepository.getIwpTemplate(documentId);
		
		File downloadedFile;
		try {
			
			String fileExt = docManageUtilities.getFileExtension(iwpDocuments.getTemplateName())==""?".docx":docManageUtilities.getFileExtension(iwpDocuments.getTemplateName());
			downloadedFile = saveFileToTempLoc(path, iwpDocuments.getTemplateContent(), documentId+"",".zip");
			String newFilePath = downloadedFile.getAbsolutePath().replace(".zip", fileExt);
			boolean isUncomressed = uncompress(downloadedFile.getAbsolutePath(), newFilePath);
			iwpDocuments.setTemplateContent(Files.readAllBytes(Paths.get(newFilePath)));
			Files.deleteIfExists(Paths.get(newFilePath));	
			newFilePath = downloadedFile.getAbsolutePath().replace(fileExt,".zip");
			Files.deleteIfExists(Paths.get(newFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return iwpDocuments;
	}

	@Transactional
	@Override
	public int[] uploadTemplate(List<IwpTemplates> iwpDocuments, String pathToDirectory) {
		int count=0;
		for(IwpTemplates iwpDocument: iwpDocuments) {
			File downloadedFile;
			try {
				downloadedFile = saveFileToTempLoc(pathToDirectory, iwpDocument.getTemplateContent(), iwpDocument.getTemplateId()+"", iwpDocument.getFileExtension());
				String newFilePath = downloadedFile.getAbsolutePath().replace(iwpDocument.getFileExtension(), ".zip");
				byte[] copressedContent = compress(downloadedFile.getAbsolutePath(), newFilePath);
				iwpDocument.setTemplateContent(copressedContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int[] iwpDocumentsList = documentRepository.uploadTemplate(iwpDocuments);
		return iwpDocumentsList;
	}
	
	private byte[] compress(String inFile, String outFile){
	    FileInputStream fin = null;
	    GZIPOutputStream out = null;
	    byte[] documentContent = null;
	    try{
	       fin = new FileInputStream(inFile);
	       out = new GZIPOutputStream(new FileOutputStream(outFile));
	       int len = 0;
	       byte[] buf = new byte[BUFFER_SIZE];
	       while((len = fin.read(buf)) > 0){
	         out.write(buf, 0, len);
	       }
	       fin.close();
	       out.finish();
	       out.close();    
	       documentContent = Files.readAllBytes(Paths.get(outFile));
	    } catch(Exception e) {
	       try{
	         if (fin != null) fin.close();
	         if (out != null) out.close();
	       }catch(Exception ex){
	    	   
	       }
	    } finally {
	    }
	    return documentContent;
	  }
	
}