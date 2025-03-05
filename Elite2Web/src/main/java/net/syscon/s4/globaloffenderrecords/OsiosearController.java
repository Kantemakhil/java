package net.syscon.s4.globaloffenderrecords;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderFingerprints;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.beans.VPropertyHeaderBlock;
import net.syscon.s4.globalconfiguration.OumsypflService;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.PopulateSearch;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagSearchGetOffenderIdentifiers;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecordsCommitBean;
import net.syscon.s4.im.beans.TagSearchGetPartialRecords;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OsiosearController
 */
@EliteController
public class OsiosearController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsiosearController.class.getName());

	@Autowired
	private OsiosearService osiosearService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OumsypflService oumsypflservice;
	
	@Autowired
	private CommonService commonService;

	/**
	 * returning GetOffenderRecords table values
	 * 
	 * @param searchBean
	 * @return List<TagSearchGetOffenderRecords>
	 */
	@SuppressWarnings("static-access")
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/searchResultsExecuteQuery", method = RequestMethod.POST)
	public List<TagSearchGetOffenderRecords> searchResultsExecuteQuery(
			@RequestBody final TagSearchGetOffenderRecords searchBean, @RequestHeader HttpHeaders headers) {
		List<TagSearchGetOffenderRecords> searchResult = new ArrayList<TagSearchGetOffenderRecords>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			//searchBean.setIntCorrelationId(osiosearService.getIntCorrelationIdSeq());
			//logger.error(this.getClass().getName()+" getIntCorrelationIdSeq");
			searchResult = osiosearService.searchResultsExecuteQuery(searchBean);
			SystemProfiles bean = new SystemProfiles();
			bean.setProfileCode("PIN");
			bean.setProfileType("CLIENT");
			List<SystemProfiles> sysList = oumsypflservice.getSystemProfileRecords(bean);
			if (searchResult != null && searchResult.size() == 0 && sysList!=null && ApplicationConstants.YES.equalsIgnoreCase(sysList.get(0).getProfileValue())) {
				List<TagSearchGetOffenderRecords> list = new ArrayList<TagSearchGetOffenderRecords>();
				list.add(searchBean);
				TagSearchGetOffenderRecordsCommitBean commitBean = new TagSearchGetOffenderRecordsCommitBean();
				commitBean.setInsertList(list);
				List<String> authorizationList = headers.get("Authorization");
				String authorization = authorizationList.get(0).split(",")[0];
				prosmainService.enableTriggers(commitBean, authorization, "117");
			}
			if(searchBean != null) {
				// To save in Auditflag
				osiosearService.setGlobalOffSearchAuditLogData(searchResult, searchBean, searchBean.getParentForm());
			}
		} catch (Exception e) {
			logger.error("searchResultsExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * getting rgSearchType LOV values
	 ** 
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/rgSearchTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSearchTypeRecordGroup(final ReferenceCodes referenceCodes) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = osiosearService.rgSearchTypeRecordGroup(referenceCodes);
		} catch (Exception e) {
			logger.error("rgSearchTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgIdentifier LOV values
	 * 
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/rgIdentifierTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup(final ReferenceCodes referenceCodes) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = osiosearService.rgIdentifierTypeRecordGroup(referenceCodes);
		} catch (Exception e) {
			logger.error("rgIdentifierTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgGender LOV values
	 * 
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/rgGenderRecordGroup", method = RequestMethod.POST)
	public List<ReferenceCodes> rgGenderRecordGroup(final ReferenceCodes referenceCodes) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = osiosearService.rgGenderRecordGroup(referenceCodes);
		} catch (Exception e) {
			logger.error("rgGenderRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * getting rgCrtLocation LOV values
	 * 
	 * @param caseloadIid
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/rgCrtLocationRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCrtLocationRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = osiosearService.rgCrtLocationRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgCrtLocationRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * @param searchBean
	 * @return List<TagSearchGetOffenderIdentifiers>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/offIdExecuteQuery", method = RequestMethod.POST)
	public List<TagSearchGetOffenderIdentifiers> offIdExecuteQuery(
			@RequestBody final TagSearchGetOffenderIdentifiers searchBean) {
		List<TagSearchGetOffenderIdentifiers> searchResult = new ArrayList<TagSearchGetOffenderIdentifiers>();
		final TagSearchGetOffenderIdentifiers bean = new TagSearchGetOffenderIdentifiers();
		try {
			searchResult = osiosearService.offIdExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offIdExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * @Param searchBean
	 * @return List<Images>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osiosear/imageExecuteQuery", method = RequestMethod.POST)
	public List<Images> imageExecuteQuery(@RequestBody final Images searchBean) {
		List<Images> searchResult = new ArrayList<Images>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (!checkOffenderModulesAccess(userName, "read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = osiosearService.imageExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("imageExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * returning GetPartialRecords table values
	 * 
	 * @Param searchBean
	 * @return List<TagSearchGetPartialRecords>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/psOffNameExecuteQuery", method = RequestMethod.POST)
	public List<TagSearchGetPartialRecords> psOffNameExecuteQuery(
			@RequestBody final TagSearchGetOffenderRecords searchBean) {
		List<TagSearchGetPartialRecords> searchResult = new ArrayList<>();
		try {
			searchResult = osiosearService.psOffNameExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("psOffNameExecuteQuery", e);
		}
		return searchResult != null ? searchResult : Collections.emptyList();
	}

	/**
	 * returning Offenders table values
	 * 
	 * @param offenderId
	 * @return Offenders
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/searchResultsPostQuerynameTypeCur", method = RequestMethod.GET)
	public @ResponseBody Offenders searchresultspostquerynametypecur(
			@RequestParam(value = "offenderId") final String offenderId) {
		Offenders listOfRecords = null;
		final Offenders bean = new Offenders();
		try {
			listOfRecords = osiosearService.searchResultsPostQuerynameTypeCur(offenderId);
		} catch (Exception e) {
			logger.error("searchresultspostquerynametypecur", e);
			bean.setErrorMessage(e.getMessage());
		}
		return listOfRecords;
	}

	/**
	 * @param const0
	 * @return List<OffenderFingerprints>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/searchResultsWhenNewRecordInstancecOffFingerPrints", method = RequestMethod.GET)
	public @ResponseBody List<OffenderFingerprints> searchResultsWhenNewRecordInstancecOffFingerPrints(
			@RequestParam(value = "const0") final String const0) {
		List<OffenderFingerprints> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.searchResultsWhenNewRecordInstancecOffFingerPrints(const0);
		} catch (Exception e) {
			logger.error("searchResultsWhenNewRecordInstancecOffFingerPrints", e);
		}
		return dataObj;
	}

	/**
	 * @param offenderId
	 * @return List<OffenderBookings>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/searchResultsWhenNewItemInstanceLvobi", method = RequestMethod.GET)
	public @ResponseBody List<OffenderBookings> searchResultsWhenNewItemInstanceLvobi(
			@RequestParam(value = "offenderId") final String offenderId) {
		List<OffenderBookings> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.searchResultsWhenNewItemInstanceLvobi(offenderId);
		} catch (Exception e) {
			logger.error("searchResultsWhenNewItemInstanceLvobi", e);
		}
		return dataObj;
	}

	/**
	 * @param const0
	 * @return List<SystemProfiles>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/captureImageFindImagingForm", method = RequestMethod.GET)
	public @ResponseBody List<SystemProfiles> captureImageFindImagingForm(
			@RequestParam(value = "const0") final String const0) {
		List<SystemProfiles> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.captureImageFindImagingForm(const0);
		} catch (Exception e) {
			logger.error("captureImageFindImagingForm", e);
		}
		return dataObj;
	}

	/**
	 * @params imageObjectId,imageObjectType
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/captureImageFindImageCur", method = RequestMethod.GET)
	public @ResponseBody List<String> captureImageFindImageCur(
			@RequestParam(value = "imageObjectId") final String imageObjectId,
			@RequestParam(value = "imageObjectType") final String imageObjectType) {
		List<String> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.captureImageFindImageCur(imageObjectId, imageObjectType);
		} catch (Exception e) {
			logger.error("captureImageFindImageCur", e);
		}
		return dataObj;
	}

	/**
	 * @params imageObjectId,imageObjectType,imageObjectSeq
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/captureImageFindMarkImageCur", method = RequestMethod.GET)
	public @ResponseBody List<String> captureImagefindMarkImageCur(
			@RequestParam(value = "imageObjectIid") final String imageObjectId,
			@RequestParam(value = "imageObjectType") final String imageObjectType,
			@RequestParam(value = "imageObjectSseq") final String imageObjectSeq) {
		List<String> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.captureImageFindMarkImageCur(imageObjectId, imageObjectType, imageObjectSeq);
		} catch (Exception e) {
			logger.error("captureImagefindMarkImageCur", e);
		}
		return dataObj;
	}

	/**
	 * @params const0
	 * @return List<OmsModules>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/createFormGlobals", method = RequestMethod.GET)
	public @ResponseBody List<OmsModules> createFormGlobals(@RequestParam(value = "const0") final String const0) {
		List<OmsModules> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.createFormGlobals(const0);
		} catch (Exception e) {
			logger.error("createFormGlobals", e);
		}
		return dataObj;
	}

	/**
	 * @params offenderId
	 * @return Offenders
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/populateOffdetailsBlocknameTypecur", method = RequestMethod.GET)
	public @ResponseBody List<Offenders> populateOffDetailsBlocknameTypeCur(
			@RequestParam(value = "offenderId") final String offenderId,@RequestParam(value = "pRootOffenderId") final String pRootOffenderId) {
		List<Offenders> listOfRecords = null;
		try {
			listOfRecords = osiosearService.populateOffDetailsBlocknameTypeCur(offenderId,pRootOffenderId);
		} catch (Exception e) {
			logger.error("populateOffDetailsBlocknameTypeCur", e);
		}
		return listOfRecords;
	}

	/**
	 * returning OffenderPhysicalAttributes table values
	 * 
	 * @params offenderBookId
	 * @return List<OffenderPhysicalAttributes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/populateOffDetailsBlockattrCur", method = RequestMethod.GET)
	public @ResponseBody List<OffenderPhysicalAttributes> populateOffdetailsBlockattrCur(
			@Valid @NotNull @RequestParam(value = "offenderBookId") final String offenderBookId) {
		List<OffenderPhysicalAttributes> listOfRecords = new ArrayList<OffenderPhysicalAttributes>();
		final OffenderPhysicalAttributes bean = new OffenderPhysicalAttributes();
		try {
			listOfRecords = osiosearService.populateOffDetailsBlockattrCur(offenderBookId);
		} catch (Exception e) {
			logger.error("populateOffdetailsBlockattrCur", e);
			bean.setErrorMessage(e.getMessage());
			if (listOfRecords != null) {
				listOfRecords.add(bean);
			}
		}
		return listOfRecords;
	}

	/**
	 * @params const0
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/setInitialSearchTypeSearchCur", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> setInitialSearchTypeSearchCur(
			@RequestParam(value = "const0") final String const0) {
		List<ReferenceCodes> listOfRecords = null;
		final ReferenceCodes bean = new ReferenceCodes();
		try {
			listOfRecords = osiosearService.setInitialSearchTypeSearchCur(const0);
		} catch (Exception e) {
			logger.error("setInitialSearchTypeSearchCur", e);
			bean.setErrorMessage(e.getMessage());
			if (listOfRecords != null) {
				listOfRecords.add(bean);
			}
		}
		return listOfRecords;
	}

	/**
	 * returning max bookId from offenderBookings
	 * 
	 * @params rootOffenderId
	 * @return String
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/getLatestBooking", method = RequestMethod.GET)
	public @ResponseBody String getLatestBooking(@RequestParam(value = "rootOffenderId") final String rootOffenderId) {
		String dataObj = null;
		try {
			dataObj = osiosearService.getLatestBooking(rootOffenderId);
		} catch (Exception e) {
			logger.error("getLatestBooking", e);
		}
		return dataObj;
	}

	/**
	 * @params profileType
	 * @return List<ProfileTypes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/profTypeDescpc", method = RequestMethod.GET)
	public @ResponseBody List<ProfileTypes> profTypeDescpc(
			@RequestParam(value = "profileType") final String profileType) {
		List<ProfileTypes> dataList = null;
		try {
			dataList = osiosearService.profTypeDescpc(profileType);
		} catch (Exception e) {
			logger.error("profTypeDescpc", e);
		}
		return dataList;
	}

	/**
	 * @params profileType
	 * @return List<String>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/profCodeDescp", method = RequestMethod.POST)
	public @ResponseBody List<String> profCodeDescp(@RequestParam(value = "profileType") final String profileType) {
		List<String> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.profCodeDescp(profileType);
		} catch (Exception e) {
			logger.error("profCodeDescp", e);
		}
		return dataObj;
	}

	/**
	 * @params const0
	 * @return List<ProfileCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/profCodeDescpd", method = RequestMethod.GET)
	public @ResponseBody List<ProfileCodes> profCodeDescpd(@RequestBody final ProfileCodes searchBean) {
		List<ProfileCodes> dataList = null;
		final ProfileCodes dataObj = new ProfileCodes();
		try {
			dataList = osiosearService.profCodeDescpd(searchBean);
		} catch (Exception e) {
			logger.error("profCodeDescpd", e);
			dataObj.setErrorMessage(e.getMessage());
		}
		return dataList;
	}

	/**
	 * @params rootOffenderId,rootOffenderIdd
	 * @return List<OffenderBookings>>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/getOffenderBookIdGetbookId", method = RequestMethod.GET)
	public @ResponseBody List<OffenderBookings> getOffenderBookIdGetbookId(
			@RequestParam(value = "rootOffenderId") final String rootOffenderId,
			@RequestParam(value = "rootOffenderIdd") final String rootOffenderIdd) {
		List<OffenderBookings> dataObj = new ArrayList<>();
		try {
			dataObj = osiosearService.getOffenderBookIdGetbookId(rootOffenderId, rootOffenderIdd);
		} catch (Exception e) {
			logger.error("getOffenderBookIdGetbookId", e);
		}
		return dataObj;
	}

	/**
	 * @params const0
	 * @return SystemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/callToShowFingerprintOld", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles callToShowFingerprintOld(@RequestParam(value = "const0") final String const0) {
		SystemProfiles dataObj = new SystemProfiles();
		try {
			dataObj = osiosearService.callToShowFingerprintOld(const0);
		} catch (Exception e) {
			logger.error("callToShowFingerprintOld", e);
		}
		return dataObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/offProfDtlsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProfileDetails> offProfDtlsExecuteQuery(@RequestBody final OffenderProfileDetails searchBean) {
		List<OffenderProfileDetails> searchResult = new ArrayList<OffenderProfileDetails>();
		final OffenderProfileDetails bean = new OffenderProfileDetails();
		try {
			searchResult = osiosearService.offProfDtlsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offProfDtlsExecuteQuery", e);
			if (bean != null) {
				bean.setErrorMessage(e.getMessage());

			}
		}
		return searchResult;
	}

	/**
	 * @Param pRootOffenderId
	 * @return List<PopulateSearch>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/populateOffDetails", method = RequestMethod.GET)
	public List<PopulateSearch> populateOffDetails(
			@RequestParam(value = "pRootOffenderId") final String pRootOffenderId, @RequestParam(value = "offenderId") final String offenderId) {
		final List<PopulateSearch> resultData = new ArrayList<>();
		final PopulateSearch pop = new PopulateSearch();
		final TagSearchPopulateOffDetails searchBean = new TagSearchPopulateOffDetails();
		List<Images> listimage = new ArrayList<>();
		final TagSearchPopulateOffDetails resultBean = new TagSearchPopulateOffDetails();
		searchBean.setPRootOffenderId(new BigDecimal(pRootOffenderId));
		List<TagSearchPopulateOffDetails> searchResult = new ArrayList<>();
		final TagSearchPopulateOffDetails bean = new TagSearchPopulateOffDetails();

		try {
			searchResult = osiosearService.populateOffDetails(searchBean);
			if (searchResult != null && !searchResult.isEmpty()) {
				for(final TagSearchPopulateOffDetails tagSearchOffDetails:searchResult){
					BeanUtils.copyProperties(resultBean, tagSearchOffDetails);
				}
			}
			pop.setTspo(resultBean);

			final Images i = new Images();
			if (resultBean.getPOffenderBookId() != null) {
				i.setImageObjectId(resultBean.getPOffenderBookId());
				i.setImageObjectType("OFF_BKG");
				i.setActiveFlag("Y");
				listimage = imageExecuteQuery(i);
			}

			if (listimage != null && !listimage.isEmpty()) {
				for(final Images images:listimage){
					pop.setImgs(images);
				}
			}

			final OffenderProfileDetails odl = new OffenderProfileDetails();
			odl.setOffenderBookId(Long.valueOf(pRootOffenderId));
			final List<OffenderProfileDetails> l = offProfDtlsExecuteQuery(odl);
			if (l != null && !l.isEmpty()) {
				pop.setOfd(l);
			}
			if (resultBean.getPOffenderBookId() != null) {
				final List<OffenderPhysicalAttributes> lopa = osiosearService.populateOffDetailsBlockattrCur(String.valueOf(resultBean.getPOffenderBookId().intValue()));
				if (lopa != null && !lopa.isEmpty()) {
					for(final OffenderPhysicalAttributes physicalAttributes:lopa){
						pop.setOpa(physicalAttributes);
					}
				}
			}

			final List<Offenders> o = populateOffDetailsBlocknameTypeCur(pRootOffenderId,offenderId);
			pop.setOffs(o);
			resultData.add(pop);

		} catch (Exception e) {
			logger.error("Populate Details ::", e);
			searchResult.add(resultBean);
			bean.setErrorMessage(e.getMessage());
		}
		return resultData;
	}

	/**
	 * return Header block details
	 * 
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osiosear/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean, @RequestHeader HttpHeaders headers) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkOffenderModulesAccess(userName,"read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgGlobalQuery(searchBean);
			
			 //To save in ViewAuditLog
			osiosearService.auditLogInjector(searchResult,searchBean,searchBean.getparentForm(),userName);
			 
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			} else {
				for(VHeaderBlock vHeaderBlock : searchResult) {
					vHeaderBlock.setCreateuserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
				}
				VHeaderBlockCommitBean commitBean = new VHeaderBlockCommitBean();
				commitBean.setInsertList(searchResult);
				prosmainService.enableTriggers(commitBean, authorization, "124");
			}
		} catch (Exception e) {
			logger.error("", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * return Header block details
	 * 
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/osiosear/offbkgGlobalQueryIntakeQueue", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQueryIntakeQueue(@Valid @RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = osiosearService.offbkgGlobalQueryQueue(searchBean);
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			}
		} catch (Exception e) {
			logger.error("", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	
	
	/**
	 * Method is used to get the data from VPropertyHeaderBlock
	 * @param searchBean
	 * @return List<VPropertyHeaderBlock>
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/osiosear/offbkgVPHeadGlobalQuery", method = RequestMethod.POST)
	public List<VPropertyHeaderBlock> offbkgVPHeadGlobalQuery(@Valid @RequestBody final VPropertyHeaderBlock searchBean) {
		List<VPropertyHeaderBlock> searchResult = new ArrayList<>();
		final VPropertyHeaderBlock bean = new VPropertyHeaderBlock();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkOffenderModulesAccess(userName,"read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgVPHeadGlobalQuery(searchBean);
			
			 //To save in ViewAuditLog
			 osiosearService.auditLogInjProcessResult(searchResult,searchBean,searchBean.getParentForm(),userName);
		} catch (Exception e) {
			logger.error("", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/osiosear/offbkgExecuteQuery", method=RequestMethod.POST)
	public List<VHeaderBlock2> offbkgExecuteQuery(@RequestBody final VHeaderBlock2 searchBean) {
		List<VHeaderBlock2> searchResult = new ArrayList<>();
		final VHeaderBlock2 bean = new VHeaderBlock2();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkOffenderModulesAccess(userName,"read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.searchVHeaderBlock2(searchBean);
			 //To save in ViewAuditLog
			 osiosearService.auditLogInjProcessResult(searchResult,searchBean, searchBean.getParentForm(),userName);
		} catch (Exception e) {
			logger.error("searchVHeaderBlock2", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}
	 @PreAuthorize("hasEliteRole('no')")
		@RequestMapping(value="/osiosear/getCaptureImage", method=RequestMethod.GET)
		public String  getCaptureImage() {
			 String screenName = "";
			try {
				screenName = osiosearService.getCaptureImage();
			} catch (Exception e) {
				logger.error("getCaptureImage", e);
			}
			return screenName;
		}
	 @PreAuthorize("hasEliteRole('no')")
	 @RequestMapping(value="/osiosear/offbkgVTrustHeadGlobalQuery", method=RequestMethod.POST)	 
	 public List<VTrustHeader> offbkgVTrustHeadGlobalQuery(@RequestBody final VTrustHeader param ) {
		 List<VTrustHeader> resultList = new ArrayList<>();
		 String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		 if(!checkOffenderModulesAccess(userName,"read")) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
		 try {
			 param.setCreateUserId(userName);
			 resultList = osiosearService.offbkgVTrustHeadGlobalQuery(param);

			 //To save in Auditflag
			 osiosearService.auditLogInjProcessResult(resultList,param,param.getModuleName(),userName);
			 
		 } catch(Exception e) {
			 logger.error("offbkgVTrustHeadGlobalQuery : ", e);
		 }
		 return resultList;
	 }
	 
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/osiosear/getDataFromJisCommonSystem", method = RequestMethod.GET)
		public List<TagSearchGetOffenderRecords> getDataFromJisCommonSystem(
				@RequestParam("intCorrelationId") Long intCorrelationId, @RequestParam("nameSearchType") String nameSearchType ,@RequestParam("moduleName") String moduleName) {
			List<TagSearchGetOffenderRecords> list = new ArrayList<TagSearchGetOffenderRecords>();
			try {
				list = osiosearService.getDataFromJisCommonSystem(intCorrelationId,nameSearchType,moduleName);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " getDataFromJisCommonSystem : ", e);
				return null;
			}	
			return list;
		}
		
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/osiosear/getCorrelationId", method = RequestMethod.GET)
		public Long getCorrelationId() {
			Long liReturn = (long) 0;
			try {
				liReturn = osiosearService.getCorrelationId();
			} catch (Exception e) {
				logger.error("getCorrelationId", e);
			}
			return liReturn;
		}
		
		@PreAuthorize("hasEliteRole('no')") 
		@RequestMapping(value = "/osiosear/offenderImages", method = RequestMethod.POST)
		public List<Images> offenderImages(@RequestBody final Images searchBean) {
			List<Images> searchResult = new ArrayList<Images>();
			String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			String recentOffenders = null;
			try {
				recentOffenders = osiosearService.getRecentOffenders(searchBean.getImageObjectId().longValue(), userId);
			}catch (Exception e) {
				logger.error("offenderImages", e);
			}
			if(recentOffenders == null || !("Y".equals(recentOffenders))) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
			}
			try {
				searchResult = osiosearService.imageExecuteQuery(searchBean);
			} catch (Exception e) {
				logger.error("offenderImages", e);
			}
			return searchResult;
		}
		
		private Boolean checkOffenderModulesAccess(String userId, String role) {
			return commonService.checkOffenderSpecificScreenAccess(userId, role);
		}
		@PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value="/osiosear/getSearchTypeCode", method=RequestMethod.GET)
		public String searchTypeCodeRetrieve() {
			String searchCode = null;
			try {
				searchCode = osiosearService.searchTypeCodeRetrieve();
			} catch (Exception e) {
				logger.error("searchTypeCodeRetrieve",e);
			}
			return searchCode;
		}
		
		
}