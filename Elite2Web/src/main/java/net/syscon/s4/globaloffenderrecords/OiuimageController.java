package net.syscon.s4.globaloffenderrecords;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.ImageOriginalsCommitBean;
import net.syscon.s4.im.beans.ImageProperties;
import net.syscon.s4.im.beans.ImagePropertiesCommitBean;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Class OiuimageController
 */
@EliteController
public class OiuimageController {
	@Autowired
	private OiuimageService oiuimageService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuimageController.class.getName());

	/**
	 * getting rgReport LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/rgReportRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> rgReportRecordGroup() {
		List<OmsModules> recordList = new ArrayList<OmsModules>();
		try {
			recordList = oiuimageService.rgReportRecordGroup();
		} catch (Exception e) {
			final OmsModules obj = new OmsModules();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgImageViewType LOV values
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/rgImageViewTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgImageViewTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiuimageService.rgImageViewTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDummyImageViewType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/rgDummyImageViewTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDummyImageViewTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiuimageService.rgDummyImageViewTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgImageProperties LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/rgImagePropertiesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgImagePropertiesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiuimageService.rgImagePropertiesRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/imagesExecuteQuery", method = RequestMethod.POST)
	public List<Images> imagesExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<>();
		try {
			searchResult = oiuimageService.imagesExecuteQuery(searchBean);
		} catch (Exception e) {
			final Images bean = new Images();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiuimage/imagesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer imagesCommit(
			@RequestParam("insertImages") List<MultipartFile> insertImages, @RequestParam("jsonData") String jsonData) {
		int liReturn = 0;
		try {
			ImagesCommitBean commitBean = new ObjectMapper().readValue(jsonData, new TypeReference<ImagesCommitBean>(){});
			if(commitBean.getInsertList() != null && commitBean.getInsertList().size()>0)  {
				int insertCount = insertImages.size();
				for (int i=0; i< insertCount; i++) {
					commitBean.getInsertList().get(i).setImageThumbnail(insertImages.get(i).getBytes());
				}
			}
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oiuimageService.imagesCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in imagesCommit {}",e.getMessage());
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/imageOriginalsExecuteQuery", method = RequestMethod.POST)
	public List<ImageOriginals> imageOriginalsExecuteQuery(@RequestBody final ImageOriginals searchBean) {
		List<ImageOriginals> searchResult = new ArrayList<>();
		try {
			searchResult = oiuimageService.imageOriginalsExecuteQuery(searchBean);
		} catch (Exception e) {
			final ImageOriginals bean = new ImageOriginals();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/imagePropertiesExecuteQuery", method = RequestMethod.POST)
	public List<ImageProperties> imagePropertiesExecuteQuery(@RequestBody final ImageProperties searchBean) {
		List<ImageProperties> searchResult = new ArrayList<>();
		try {
			searchResult = oiuimageService.imagePropertiesExecuteQuery(searchBean);
		} catch (Exception e) {
			final ImageProperties bean = new ImageProperties();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiuimage/imagePropertiesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer imagePropertiesCommit(@RequestBody final ImagePropertiesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oiuimageService.imagePropertiesCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oiuimageService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/staffMembersExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembers> staffMembersExecuteQuery(@RequestBody final StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oiuimageService.staffMembersExecuteQuery(searchBean);
		} catch (Exception e) {
			final StaffMembers bean = new StaffMembers();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiuimage/staffMembersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer staffMembersCommit(@RequestBody final StaffMembersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oiuimageService.staffMembersCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/personsExecuteQuery", method = RequestMethod.POST)
	public List<Persons> personsExecuteQuery(@RequestBody final Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		try {
			searchResult = oiuimageService.personsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Persons bean = new Persons();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting rgImageViewType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/getImageOicCodeDescription", method = RequestMethod.GET)
	public List<ReferenceCodes> getImageOicCodeDescription() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiuimageService.getImageOicCodeDescription();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oiuimage/imageOriginalsUpdateImageOriginals", method = RequestMethod.POST)
	public Integer imageOriginalsUpdateImageOriginals(@RequestBody final ImageOriginalsCommitBean commitBean) {
		Integer result = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() != 0) {
				commitBean.getUpdateList().forEach(imageOriginal -> {
					imageOriginal.setImageFull(imageOriginal.getImageOriginal());
				});
			}
			result = oiuimageService.imageOriginalsUpdateImageOriginals(commitBean);
		} catch (Exception e) {
			logger.error("imageOriginalsUpdateImageOriginals", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/getNextImageId", method = RequestMethod.GET)
	public Long getNextImageId() {
		Long imageId = null;
		try {
			imageId = oiuimageService.getNextImageId();
		} catch (Exception e) {
			logger.error("getNextImageId", e);
		}
		return imageId;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/getCode", method = RequestMethod.GET)
	public String rgGetCode(@RequestParam(value = "code") final String code) {
		String pptyCode = null;
		try {
			pptyCode = oiuimageService.rgGetCode(code);
		} catch (Exception e) {
			logger.error(e);
		}
		return pptyCode;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/allowDelete", method = RequestMethod.GET)
	public Integer allowDelete() {
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Integer checkOne = 0;
		try {
			checkOne = oiuimageService.allowDelete(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return checkOne;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/getPropertyTypeDescription", method = RequestMethod.GET)
	public String pptyGetDescription(@RequestParam(value = "code") final String code) {
		String pptyDescription = null;
		try {
			pptyDescription = oiuimageService.pptyGetDescription(code);
		} catch (Exception e) {
			logger.error(e);
		}
		return pptyDescription;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiuimage/checkUserRole", method = RequestMethod.GET)
	public Integer checkUserRole(final String moduleName) {
		Integer checkOne = 0;
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			checkOne = oiuimageService.checkUserRole(moduleName, userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return checkOne;
	}

}