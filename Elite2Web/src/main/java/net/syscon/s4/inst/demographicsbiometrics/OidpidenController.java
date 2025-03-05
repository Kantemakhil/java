package net.syscon.s4.inst.demographicsbiometrics;

import java.util.ArrayList;
import java.util.List;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderIdentifyingMark;
import net.syscon.s4.common.beans.OffenderIdentifyingMarksCommitBean;
import net.syscon.s4.common.beans.OffenderPhysicalAttributesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.demographicsbiometrics.impl.OidpidenServiceImpl;

@EliteController
public class OidpidenController {

	@Autowired
	private OidpidenServiceImpl oidpidenService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpidenController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/offPaExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPhysicalAttributes> offPaSearchOffenderPhysicalAttributes(
			@RequestBody final OffenderPhysicalAttributes offenderPhysicalAttributes) {
		List<OffenderPhysicalAttributes> searchResult = new ArrayList<>();
		final OffenderPhysicalAttributes bean = new OffenderPhysicalAttributes();
		try {
			searchResult = oidpidenService.offPaSearchOffenderPhysicalAttributes(offenderPhysicalAttributes);
		} catch (Exception e) {
			logger.error("offPaSearchOffenderPhysicalAttributes: ", e);
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
	@RequestMapping(value = "/oidpiden/offPaCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oidpidenOffPaCommit(
			@RequestBody final OffenderPhysicalAttributesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidpidenService.insertUpdateDeleteOffenderPhysicalAttributes(commitBean);
		} catch (Exception e) {
			logger.error("Physical attributes commit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting rgProfile LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/rgProfileRecordGroup", method = RequestMethod.GET)
	public List<ProfileCodes> rgProfileRecordGroup(@RequestParam(value = "profileType") final String profileType) {
		List<ProfileCodes> recordList = new ArrayList<ProfileCodes>();
		try {
			recordList = oidpidenService.rgProfileRecordGroup(profileType);
		} catch (Exception e) {
			logger.error("rgProfileRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rgMarkType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/rgMarkTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMarkTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpidenService.rgMarkTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgMarkTypeRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rgBodyPart LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/rgBodyPartRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgBodyPartRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpidenService.rgBodyPartRecordGroup();
		} catch (Exception e) {
			logger.error("rgBodyPartRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rgSide LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/rgSideRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSideRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpidenService.rgSideRecordGroup();
		} catch (Exception e) {
			logger.error("rgSideRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rgPartOrient LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/rgPartOrientRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPartOrientRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpidenService.rgPartOrientRecordGroup();
		} catch (Exception e) {
			logger.error("rgPartOrientRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting rgRaceCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/rgRaceCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRaceCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidpidenService.rgRaceCodeRecordGroup();
		} catch (Exception e) {
			logger.error("rgRaceCodeRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/offRaceExecuteQuery", method = RequestMethod.POST)
	public List<Offenders> offRaceSearchOffenders(@RequestBody final Offenders searchBean) {
		List<Offenders> searchResult = new ArrayList<>();
		final Offenders bean = new Offenders();
		try {
			searchResult = oidpidenService.offRaceSearchOffenders(searchBean);
		} catch (Exception e) {
			logger.error("offRaceSearchOffenders: ", e);
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
	@RequestMapping(value = "/oidpiden/offRaceCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offRaceUpdateOffenders(@RequestBody final OffendersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
				liReturn = oidpidenService.offRaceUpdateOffenders(commitBean);
		} catch (Exception e) {
			logger.error("offRaceUpdateOffenders: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/offPdExecuteQuery", method = RequestMethod.POST)
	public List<OffenderProfileDetails> offPdSearchOffenderProfileDetails(
			@RequestBody final OffenderProfileDetails searchBean) {
		List<OffenderProfileDetails> searchResult = new ArrayList<>();
		final OffenderProfileDetails bean = new OffenderProfileDetails();
		try {
			searchResult = oidpidenService.offPdSearchOffenderProfileDetails(searchBean);
		} catch (Exception e) {
			logger.error("offPdSearchOffenderProfileDetails: ", e);
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
	@RequestMapping(value = "/oidpiden/offPdCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offPdCommit(@RequestBody final OffenderProfileDetailsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
				liReturn = oidpidenService.offPdUpdateOffenderProfileDetails(commitBean);
		} catch (Exception e) {
			logger.error("offPdCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/offImExecuteQuery", method = RequestMethod.POST)
	public List<OffenderIdentifyingMark> oidpidenOffImSearchOffenderIdentifyingMarks(
			@RequestParam(value = "imageObjType") final String imageObjectType,@RequestBody final OffenderIdentifyingMark searchBean) {
		List<OffenderIdentifyingMark> searchResult = new ArrayList<>();
		final OffenderIdentifyingMark bean = new OffenderIdentifyingMark();
		try {
			searchResult = oidpidenService.offImSearchOffenderIdentifyingMarks(searchBean,imageObjectType);
		} catch (Exception e) {
			logger.error("oidpidenOffImSearchOffenderIdentifyingMarks: ", e);
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
	@RequestMapping(value = "/oidpiden/offImCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oidpidenOffImCommit(@RequestBody final OffenderIdentifyingMarksCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidpidenService.insertUpdateDeleteOffenderIdentifyingMarks(commitBean);
		} catch (Exception e) {
			logger.error("oidpidenOffImCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/offbkgpredelete", method = RequestMethod.POST)
	public @ResponseBody Offenders offBkgPreDelete(@RequestBody final Offenders paramBean) {
		Offenders dataObj = new Offenders();
		try {
			dataObj = oidpidenService.offBkgPreDelete(paramBean);
		} catch (Exception e) {
			logger.error("offBkgPreDelete: ", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/nbtdetaildescwhenvalidateitemprofiletypes", method = RequestMethod.POST)
	public @ResponseBody List<Object> nbtDetailDescWhenValidateItemprofileTypes(
			@RequestBody final ProfileTypes paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = oidpidenService.nbtDetailDescWhenValidateItemprofileTypes(paramBean);
		} catch (Exception e) {
			logger.error("nbtDetailDescWhenValidateItemprofileTypes: ", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpidenNbtdetaildescwhennewiteminstanceprofiletypes", method = RequestMethod.POST)
	public @ResponseBody ProfileTypes nbtDetailDescWhenNewItemInstanceprofileTypes(
			@RequestBody final ProfileTypes paramBean) {
		ProfileTypes dataObj = new ProfileTypes();
		try {
			dataObj = oidpidenService.nbtDetailDescWhenNewItemInstanceprofileTypes(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpidenOffpdwhennewrecordinstanceprofiletypes", method = RequestMethod.POST)
	public @ResponseBody ProfileTypes offPdWhenNewRecordInstanceprofileTypes(
			@RequestBody final ProfileTypes paramBean) {
		ProfileTypes dataObj = new ProfileTypes();
		try {
			dataObj = oidpidenService.offPdWhenNewRecordInstanceprofileTypes(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/offpdpostquerychardesccur", method = RequestMethod.POST)
	public @ResponseBody ProfileTypes offPdPostQuerycharDescCur(@RequestBody final OffenderProfileDetails paramBean) {
		ProfileTypes dataObj = null;
		try {
			dataObj = oidpidenService.offPdPostQuerycharDescCur(paramBean);
		} catch (Exception e) {
			logger.error("offPdPostQuerycharDescCur: ", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/calltoshowfingerprintold", method = RequestMethod.GET)
	public @ResponseBody List<SystemProfiles> callToShowFingerprintOld(@RequestBody final SystemProfiles paramBean) {
		List<SystemProfiles> dataObj = new ArrayList<SystemProfiles>();
		try {
			dataObj = oidpidenService.callToShowFingerprintOld(paramBean);
		} catch (Exception e) {
			logger.error("callToShowFingerprintOld: ", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/cgfkchkoffpdoffpdpflcodvaluetypecur", method = RequestMethod.POST)
	public @ResponseBody List<Object> cgfkchkOffPdOffPdPflCodvalueTypeCur(@RequestBody final ProfileTypes paramBean) {
		List<Object> dataObj = null;
		try {
			dataObj = oidpidenService.cgfkchkOffPdOffPdPflCodvalueTypeCur(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpidenCgfkchkoffpdoffpdpflcodc", method = RequestMethod.POST)
	public @ResponseBody ProfileCodes cgfkchkOffPdOffPdPflCodc(@RequestBody final OffenderProfileDetails paramBean) {
		ProfileCodes listOfRecords = null;
		try {
			listOfRecords = oidpidenService.cgfkchkOffPdOffPdPflCodc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffPdOffPdPflCodc: ", e);
		}
		return listOfRecords;
	}
	  @PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidpiden/checkImage", method = RequestMethod.GET)
	public @ResponseBody  String checkImage(@RequestParam(value = "imageObjType") final String imageObjType,
			@RequestParam(value = "offenderBookId") final String offenderBookId,
			@RequestParam(value = "markType") final String markType,
			@RequestParam(value = "bodypart") final String bodypart,
			@RequestParam(value = "objectSeq") final String objectSeq
			 ) {
		String dataObj = "";
		try {
			dataObj = oidpidenService.checkImage(imageObjType,offenderBookId,markType,bodypart,objectSeq);
		} catch (Exception e) {
			logger.error(e);
		}
		return dataObj;
	}
	  @PreAuthorize("hasEliteRole('read')")
		@RequestMapping(value = "/oidpiden/checkProfileDetails", method = RequestMethod.GET)
		public @ResponseBody  List<OffenderProfileDetails> checkProfileDetails(@RequestParam(value = "offenderBookId") final String offenderBookId,
				@RequestParam(value = "caseloadType") final String caseloadType, @RequestParam(value = "profileCategory") final String profileCategory)					
				 {
		  String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		  List<OffenderProfileDetails>  dataObj = new ArrayList<OffenderProfileDetails>();
			try {
				dataObj = oidpidenService.checkProfileDetails(offenderBookId,caseloadType,profileCategory,userName);
			} catch (Exception e) {
				logger.error(e);
			}
			return dataObj;
		}
}