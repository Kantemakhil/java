package net.syscon.s4.globaloffenderrecords.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.EliteViewLog;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderFingerprints;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.common.beans.VPropertyHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.globaloffenderrecords.OsiosearRepository;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OffenderPhysicalAttributes;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagSearchGetOffenderIdentifiers;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.TagSearchGetPartialRecords;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.pkgs.tag_search.TagSearchService;
import net.syscon.s4.pkgs.trust_main.TrustMainService;
import net.syscon.s4.sa.admin.OumpurgeRepository;

/**
 * Class OsiosearServiceImpl
 */
@Service
public class OsiosearServiceImpl extends BaseBusiness implements OsiosearService {

	private static Logger log = LogManager.getLogger(OsiosearServiceImpl.class.getName());

	@Autowired
	private OsiosearRepository osiosearDao;

	@Autowired
	private EliteDateService dateService;

	@Autowired
	private TagSearchService tagSearchService;

	@Autowired
	private TrustMainService trustMainService;

	@Autowired
	private OumpurgeRepository oumpurgeRepository;
	
	@Autowired
	private Omss40Service omss40Service;

	public OsiosearServiceImpl() {
		// OsiosearServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public Offenders searchResultsPostQuerynameTypeCur(final String offenderId) {
		return osiosearDao.searchResultsPostQuerynameTypeCur(offenderId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<OffenderFingerprints> searchResultsWhenNewRecordInstancecOffFingerPrints(final String const0) {
		return osiosearDao.searchResultsWhenNewRecordInstancecOffFingerPrints(const0);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<OffenderBookings> searchResultsWhenNewItemInstanceLvobi(final String offenderId) {
		return osiosearDao.searchResultsWhenNewItemInstanceLvobi(offenderId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<SystemProfiles> captureImageFindImagingForm(final String const0) {
		return osiosearDao.captureImageFindImagingForm(const0);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<String> captureImageFindImageCur(final String imageObjectId, final String imageObjectType) {
		return osiosearDao.captureImageFindImageCur(imageObjectId, imageObjectType);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<String> captureImageFindMarkImageCur(final String imageObjectId, final String imageObjectType,
			final String imageObjectSeq) {
		return osiosearDao.captureImageFindMarkImageCur(imageObjectId, imageObjectType, imageObjectSeq);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<OmsModules> createFormGlobals(final String const0) {
		return osiosearDao.createFormGlobals(const0);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<Offenders> populateOffDetailsBlocknameTypeCur(final String rootoffenderId, final String offenderId) {
		return osiosearDao.populateOffDetailsBlocknameTypeCur(rootoffenderId, offenderId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<OffenderPhysicalAttributes> populateOffDetailsBlockattrCur(final String offenderBookId) {
		return osiosearDao.populateOffDetailsBlockattrCur(offenderBookId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<ReferenceCodes> setInitialSearchTypeSearchCur(final String const0) {
		return osiosearDao.setInitialSearchTypeSearchCur(const0);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public String getLatestBooking(final String rootOffenderId) {
		return osiosearDao.getLatestBooking(rootOffenderId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<ProfileTypes> profTypeDescpc(final String profileType) {
		return osiosearDao.profTypeDescpc(profileType);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<String> profCodeDescp(final String profileType) {
		return osiosearDao.profCodeDescp(profileType);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<ProfileCodes> profCodeDescpd(final ProfileCodes searchBean) {
		return osiosearDao.profCodeDescpd(searchBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public List<OffenderBookings> getOffenderBookIdGetbookId(final String rootOffenderId,
			final String rootOffenderIdd) {
		return osiosearDao.getOffenderBookIdGetbookId(rootOffenderId, rootOffenderIdd);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 */
	@Override
	public SystemProfiles callToShowFingerprintOld(final String const0) {
		return osiosearDao.callToShowFingerprintOld(const0);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @throws Exception
	 */
	public List<TagSearchGetOffenderRecords> searchResultsExecuteQuery(final TagSearchGetOffenderRecords objSearchDao) {
		List<TagSearchGetOffenderRecords> returnList = new ArrayList<TagSearchGetOffenderRecords>();
		Offenders offenders = new Offenders();
		offenders.setSearchType(objSearchDao.getpSearchType());
		offenders.setLastName(objSearchDao.getpLastName());
		offenders.setFirstName((objSearchDao.getpFirstName() != null && objSearchDao.getpFirstName().equals("")) ? null
				: objSearchDao.getpFirstName());
		offenders.setMiddleName(
				(objSearchDao.getpMiddleName() != null && objSearchDao.getpMiddleName().equals("")) ? null
						: objSearchDao.getpMiddleName());
		offenders.setIdentifierType(objSearchDao.getpIdentifierType());
		offenders.setIdentifier(objSearchDao.getpIdentifierValue());
		offenders.setOffenderIdDisplay(objSearchDao.getOffenderIdDisplay());
		
		offenders.setSexCode((objSearchDao.getpSexCode() != null && objSearchDao.getpSexCode().equals("")) ? null
				: objSearchDao.getpSexCode());
		offenders.setBirthDate(objSearchDao.getpBirthDate());
		offenders.setBirthYear(objSearchDao.getpBirthYear());
		offenders
				.setBirthRange(objSearchDao.getpBirthRange() != null ? objSearchDao.getpBirthRange().intValue() : null);
		offenders.setAgeDate(objSearchDao.getpAgedate());
		offenders.setAgeRange(objSearchDao.getpAgeRange() != null ? objSearchDao.getpAgeRange().intValue() : null);
		offenders.setRaceCode(objSearchDao.getpEthnicity());
		offenders.setNameVariation(objSearchDao.getpNameVariation());
		offenders.setSwitchNames(objSearchDao.getpSwitchNames());
		offenders.setBookingNo(objSearchDao.getpBookNo());
		offenders.setGenderCode(objSearchDao.getpGenderCode());
		offenders.setsecondMiddleName(
				(objSearchDao.getsecondMiddleName() != null && objSearchDao.getsecondMiddleName().equals("")) ? null
						: objSearchDao.getsecondMiddleName());

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		List<Offenders> offList = tagSearchService.getOffenderRecords(offenders);
		if (offList != null && offList.size() > 0) {
			returnList = new ArrayList<TagSearchGetOffenderRecords>();
			for (Offenders data : offList) {
				TagSearchGetOffenderRecords bean = new TagSearchGetOffenderRecords();
				bean.setpLastName(data.getLastName());
				bean.setpFirstName(data.getFirstName());
				bean.setpMiddleName(data.getMiddleName());
				bean.setpSexCode(data.getSexCode());
				bean.setpGenderCode(data.getGenderCode());

				try {
					bean.setpBirthDate(format.parse(format.format(data.getBirthDate())));
				} catch (Exception e) {
					log.error("searchResultsExecuteQuery", e);
				}
				bean.setOffenderId(data.getOffenderId() != null ? data.getOffenderId().toString() : null);
				bean.setRootOffenderId(data.getRootOffenderId() != null ? data.getRootOffenderId().toString() : null);
				bean.setOffenderIdDisplay(data.getOffenderIdDisplay());
				bean.setWorkingNameFlag(data.getWorkingNameFlag());
				bean.setsecondMiddleName(data.getsecondMiddleName());
				if(objSearchDao.getpSearchType().equals("B") && data.getPinIdentifier()!= null) {
					bean.setPin(data.getPinIdentifier());
					bean.setNameType(data.getAliasNameType());

				}
				if (!objSearchDao.getpSearchType().equals("B") && data.getOffenderId() != null) {
					bean.setNameType(data.getAliasNameType());
					bean.setPin(osiosearDao.getIdentifierData(new BigDecimal(data.getOffenderId())));
				}
				bean.setpAgeRange(BigDecimal.valueOf(dateService.calculateAge(bean.getpBirthDate())));
				returnList.add(bean);
			}

		}

		return returnList;

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @throws Exception
	 */
	public List<TagSearchGetOffenderIdentifiers> offIdExecuteQuery(final TagSearchGetOffenderIdentifiers searchRecord) {
		List<TagSearchGetOffenderIdentifiers> returnList = null;
		List<OffenderIdentifier> list = tagSearchService
				.getOffenderIdentifiers(searchRecord.getOffenderId()!= null ? searchRecord.getOffenderId().longValue(): null);
		if (list != null && list.size() > 0) {
			returnList = new ArrayList<TagSearchGetOffenderIdentifiers>();
			for (OffenderIdentifier data : list) {
				TagSearchGetOffenderIdentifiers bean = new TagSearchGetOffenderIdentifiers();
				bean.setIdentifier(data.getIdentifier());
				bean.setIdentifierType(data.getIdentifierType());
				bean.setOffenderId(Long.valueOf(data.getOffenderId()).intValue());
				returnList.add(bean);
			}

		}
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @throws Exception
	 */
	@Override
	public List<Images> imageExecuteQuery(final Images searchBean) {
		return osiosearDao.imageExecuteQuery(searchBean);

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param searchRecord
	 * @throws Exception
	 */
	public List<TagSearchGetPartialRecords> psOffNameExecuteQuery(final TagSearchGetOffenderRecords objSearchDao) {
		List<TagSearchGetPartialRecords> returnList = null;
		Offenders offenders = new Offenders();
		offenders.setSearchType(objSearchDao.getpSearchType());
		offenders.setLastName(objSearchDao.getpLastName());
		offenders.setFirstName(objSearchDao.getpFirstName());
		offenders.setMiddleName(objSearchDao.getpMiddleName());
		offenders.setSexCode(objSearchDao.getpSexCode());
		offenders.setBirthDate(objSearchDao.getpBirthDate());
		offenders.setBirthYear(objSearchDao.getpBirthYear());
		offenders
				.setBirthRange(objSearchDao.getpBirthRange() != null ? objSearchDao.getpBirthRange().intValue() : null);
		offenders.setAgeDate(objSearchDao.getpAgedate());
		offenders.setAgeRange(objSearchDao.getpAgeRange() != null ? objSearchDao.getpAgeRange().intValue() : null);
		offenders.setRaceCode(objSearchDao.getpEthnicity());
		offenders.setsecondMiddleName(objSearchDao.getsecondMiddleName());
		List<Offenders> list = tagSearchService.getPartialRecords(offenders);
		if (list != null && list.size() > 0) {
			returnList = new ArrayList<TagSearchGetPartialRecords>();
			for (Offenders data : list) {
				TagSearchGetPartialRecords bean = new TagSearchGetPartialRecords();
				bean.setHits(data.getHits() != null ? data.getHits().toString() : null);
				bean.setLastName(data.getLastName());
				bean.setsecondMiddleName(data.getsecondMiddleName());
				returnList.add(bean);
			}

		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 */
	@Override
	public List<ReferenceCodes> rgSearchTypeRecordGroup(final ReferenceCodes referenceCodes) {
		return osiosearDao.rgSearchTypeRecordGroup(referenceCodes);

	}

	/**
	 * This method is used to execute a record group
	 */
	@Override
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup(final ReferenceCodes referenceCodes) {
		return osiosearDao.rgIdentifierTypeRecordGroup(referenceCodes);

	}

	/**
	 * This method is used to execute a record group
	 */
	@Override
	public List<ReferenceCodes> rgGenderRecordGroup(final ReferenceCodes referenceCodes) {
		return osiosearDao.rgGenderRecordGroup(referenceCodes);

	}

	/**
	 * This method is used to execute a record group
	 */
	@Override
	public List<ReferenceCodes> rgCrtLocationRecordGroup(final String caseLoadId) {
		return osiosearDao.rgCrtLocationRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a procedure
	 */
	@Override
	public List<TagSearchPopulateOffDetails> populateOffDetails(final TagSearchPopulateOffDetails searchBean) {
		return tagSearchService.populateOffDetails(searchBean);
	}

	/**
	 * This method is used to execute VheaderBlock
	 */
	@Override
	public List<VHeaderBlock> offbkgGlobalQuery(final VHeaderBlock searchBean) {
		List<VHeaderBlock> returnList = new ArrayList<>();
		List<VHeaderBlock> updatedList = new ArrayList<>();
		
		Caseloads caseloads = new Caseloads();
		Long offenderId = null;
		if (searchBean.getAgyLocId() != null) {
			caseloads.setCaseloadId(!searchBean.getAgyLocId().endsWith("OIIGRIEV") ? searchBean.getAgyLocId()
					: searchBean.getAgyLocId().substring(0, searchBean.getAgyLocId().length() - 8));
			caseloads = osiosearDao.caseloadTypeData(caseloads);
			searchBean.setAgyLocType(caseloads.getCaseloadType());
		}
		if (searchBean.getAgyLocType() != null) {
			returnList = osiosearDao.offbkgGlobalQuery(searchBean);
		} else {
			return updatedList;
		}

		for (VHeaderBlock listval : returnList) {
			offenderId = listval.getOffenderId().longValue();
			if (listval.getOffenderIdDisplay() != null) {
				listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
				if (listval.getBookingNo() != null) {
					VTrustHeader vTrustHeader = new VTrustHeader();
					vTrustHeader.setOffenderIdDisplay(listval.getOffenderIdDisplay());
					vTrustHeader.setBookingNo(listval.getBookingNo());
					String whereClause = null;
					if (vTrustHeader.getAgyLocId() != null && vTrustHeader.getModuleName() != null) {
						whereClause = trustMainService.trustHeaderQuery(vTrustHeader.getAgyLocId(),
								vTrustHeader.getModuleName().replaceFirst("/", ""), null);
					}
					if (ApplicationConstants.YFLAG.equals(listval.getTrustFlag())) {
						listval.setTrustAccount(true);
					} else {
						listval.setTrustAccount(false);
					}
				}
				listval.setCreateAccount(true);
				updatedList.add(listval);
			}
			updatedList = removeSealOffGlobalHeader(updatedList,searchBean.getCreateuserId());
		}
		return updatedList;
	}

	/**
	 * This method is used to get the data from VPropertyHeaderBlock
	 */
	@Override
	public List<VPropertyHeaderBlock> offbkgVPHeadGlobalQuery(final VPropertyHeaderBlock searchBean) {
		List<VPropertyHeaderBlock> returnList = new ArrayList<>();
		returnList = osiosearDao.offbkgVPHeadGlobalQuery(searchBean);
		List<VPropertyHeaderBlock> updatedList = new ArrayList<>();
		for (final VPropertyHeaderBlock listval : returnList) {
			if (listval.getOffenderIdDisplay() != null) {
				listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
				updatedList.add(listval);
			}
		}
		updatedList=removeSealOffPropertyHeader(updatedList,searchBean.getCreateUserId());
		return updatedList;
	}

	public List<OffenderProfileDetails> offProfDtlsExecuteQuery(final OffenderProfileDetails searchRecord) {
		List<OffenderProfileDetails> returnList = new ArrayList<OffenderProfileDetails>();
		final List<OffenderProfileDetails> returnListtemp = new ArrayList<OffenderProfileDetails>();

		returnList = osiosearDao.offProfDtlsExecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			for (final OffenderProfileDetails obj : returnList) {
				if (obj != null) {

					if (obj.getProfileType() != null) {
						final List<ProfileTypes> values = osiosearDao.profTypeDescpc(obj.getProfileType());
						for (ProfileTypes profileTypes : values) {
							obj.setProfileType(profileTypes.getDescription());
						}
					}

					if (obj.getProfileCode() != null) {
						final ProfileCodes bean = new ProfileCodes();
						bean.setProfileCode(obj.getProfileCode());
						final List<ProfileCodes> values = osiosearDao.profCodeDescpd(bean);
						for (ProfileCodes profileCodes : values) {
							obj.setProfileCode(profileCodes.getDescription());
						}
					}
					if (obj.getProfileCode() != null) {
						returnListtemp.add(obj);
					}
				}
			}

		}
		return returnListtemp;

	}

	@Override
	public List<VHeaderBlock2> searchVHeaderBlock2(final VHeaderBlock2 searchBean) {
		List<VHeaderBlock2> list = new ArrayList<>();
		VHeaderBlock vhbBean = new VHeaderBlock();
		list = osiosearDao.searchVHeaderBlock2(searchBean);
		for (final VHeaderBlock2 listval : list) {
			listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
			if (BigDecimal.ZERO.compareTo(listval.getOffenderBookId()) != 0) {
				vhbBean = new VHeaderBlock();
				vhbBean.setOffenderBookId(listval.getOffenderBookId());
				vhbBean.setCreateuserId(searchBean.getCreateUserId());
				if (listval.getOffenderIdDisplay() != null && listval.getBookingNo() != null) {
					VHeaderBlock data = new VHeaderBlock();
					data.setOffenderIdDisplay(listval.getOffenderIdDisplay());
					data.setBookingNo(listval.getBookingNo());
					data.setAgyLocId(searchBean.getAgyLocId());
					data.setCreateUserId(searchBean.getCreateUserId());
				}
			} else {
				vhbBean.setOffenderId(listval.getOffenderId());
				listval.setGender(osiosearDao.getVheaderBlockDataFromOffenderId(vhbBean).getGender());
			}
		}
		list = removeSealOffGlobalExecuteHeader(list,searchBean.getCreateUserId());
		return list;
	}

	@Override
	public String getCaptureImage() {
		return osiosearDao.getCaptureImage();
	}

	@Override
	public List<VTrustHeader> offbkgVTrustHeadGlobalQuery(VTrustHeader param) {
		final String whereClause = trustMainService.trustHeaderQuery(param.getAgyLocId(),
				param.getModuleName().replaceFirst("/", ""), null);
		List<VTrustHeader> resultList = osiosearDao.offbkgVTrustHeadGlobalQuery(param, whereClause);
		resultList.forEach(data -> {
			if (!"Y".equals(data.getIndigentFlag())) {
				data.setIndigentFlag(null);
			}
			data.setTrustAccount(true);
		});
		resultList = removeSealOffTrustHeader(resultList, param.getCreateUserId());
		return resultList;
	}

	@Override
	public List<VHeaderBlock> offbkgGlobalQueryQueue(final VHeaderBlock searchBean) {
		List<VHeaderBlock> returnList = new ArrayList<>();
		List<VTrustHeader> vTrustData = new ArrayList<>();
		List<VHeaderBlock> updatedList = new ArrayList<>();
		returnList = osiosearDao.offbkgGlobalQuery(searchBean);
		for (VHeaderBlock listval : returnList) {
			if (listval.getOffenderIdDisplay() != null)  {
				listval.setAge(BigDecimal.valueOf(dateService.calculateAge(listval.getBirthDate())));
				if (listval.getBookingNo() != null) {
					VTrustHeader vTrustHeader = new VTrustHeader();
					vTrustHeader.setOffenderIdDisplay(searchBean.getOffenderIdDisplay());
					vTrustHeader.setBookingNo(searchBean.getBookingNo());
					final String whereClause = trustMainService.trustHeaderQuery(vTrustHeader.getAgyLocId(),
							vTrustHeader.getModuleName().replaceFirst("/", ""), null);
					vTrustData = osiosearDao.offbkgVTrustHeadGlobalQuery(vTrustHeader, whereClause);
					if (vTrustData.size() > 0) {
						listval.setTrustAccount(true);
					} else {
						listval.setTrustAccount(false);
					}
				}
				listval.setCreateAccount(true);
				updatedList.add(listval);
			}
		}
		return updatedList;
	}

	@Override
	public Long getIntCorrelationIdSeq() {
		return osiosearDao.getIntCorrelationIdSeq();
	}
	
	
	@Override
	public List<TagSearchGetOffenderRecords> getDataFromJisCommonSystem(Long intCorrelationId, String nameSearchType,
			String moduleName) {
		if (ApplicationConstants.I.equalsIgnoreCase(nameSearchType)) {
			return getDataFromJisCommonSystemForPin(intCorrelationId);
		} else {
			return getDataFromJisCommonSystemForNameSearch(intCorrelationId);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<TagSearchGetOffenderRecords> getDataFromJisCommonSystemForPin(Long intCorrelationId) {
		TagSearchGetOffenderRecords obj = osiosearDao.getDataFromJisCommonSystem(intCorrelationId);
		
		List<TagSearchGetOffenderRecords> listMap = new ArrayList<TagSearchGetOffenderRecords>();
		if (obj != null &&obj.getResponseData() != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String data = new String(obj.getResponseData());
				Map<String, Object> map = objectMapper.readValue(data, Map.class);
				Map<String, Object> body = (Map<String, Object>) map.get("body");
				List<Map<String, Object>> aliases = (List<Map<String, Object>>) body.get("aliases");
				for (Map<String, Object> mapOne : aliases) {
					TagSearchGetOffenderRecords bean = new TagSearchGetOffenderRecords();
					bean.setpLastName((String) mapOne.get("surName"));
					bean.setpFirstName((String) mapOne.get("firstName"));
					bean.setpMiddleName((String) mapOne.get("middleName"));
					bean.setsecondMiddleName((String) mapOne.get("secondMiddleName"));
					bean.setNameType("JIS Common - Alias");
					Map<String, Object> personIdentityId = (Map<String, Object>) mapOne.get("personIdentityId");
					if(personIdentityId != null) {
						bean.setPnin(personIdentityId.get("PNIN").toString());
					}
					//bean.setPin("1234");
					String dob = (String) mapOne.get("dob");
					if (dob != null) {
						String dateFormatPattern = "MM/dd/yyyy";
						try {
							Date date = convertStringToDate(dob, dateFormatPattern);
							bean.setpBirthDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					bean.setpAgeRange(
							getAge(bean.getpBirthDate()) != null ? new BigDecimal(getAge(bean.getpBirthDate())) : null);
					listMap.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (listMap != null && listMap.size() > 0) ? listMap : null;
	}

	
	
	@SuppressWarnings("unchecked")
	private List<TagSearchGetOffenderRecords> getDataFromJisCommonSystemForNameSearch(Long intCorrelationId) {
		TagSearchGetOffenderRecords obj = osiosearDao.getDataFromJisCommonSystem(intCorrelationId);
		List<TagSearchGetOffenderRecords> listMap = new ArrayList<TagSearchGetOffenderRecords>();
		if (obj != null && obj.getResponseData() != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String data = new String(obj.getResponseData());
				Map<String, Object> map = objectMapper.readValue(data, Map.class);
				Map<String, Object> body = (Map<String, Object>) map.get("body");
				List<Map<String, Object>> aliases = (List<Map<String, Object>>) body.get("personDetails");
				for (Map<String, Object> mapOne : aliases) {
					TagSearchGetOffenderRecords bean = new TagSearchGetOffenderRecords();
					bean.setpLastName((String) mapOne.get("surName"));
					bean.setpFirstName((String) mapOne.get("firstName"));
					bean.setpMiddleName((String) mapOne.get("middleName"));
					bean.setsecondMiddleName((String) mapOne.get("secondMiddleName"));
					bean.setNameType("JIS Common - Alias");
					bean.setPin(mapOne.get("pin") != null ? mapOne.get("pin").toString() : null);
					String dob = (String) mapOne.get("dob");
					if (dob != null) {
						String dateFormatPattern = "MM/dd/yyyy";
						try {
							Date date = convertStringToDate(dob, dateFormatPattern);
							bean.setpBirthDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					bean.setpAgeRange(
							getAge(bean.getpBirthDate()) != null ? new BigDecimal(getAge(bean.getpBirthDate())) : null);
					listMap.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (listMap != null && listMap.size() > 0) ? listMap : null;
	}

	
	private Long getAge(Date dob) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime()-dob.getTime());
		int mYear = c.get(Calendar.YEAR)-1970;
		return (long) mYear;
	}

	public static Date convertStringToDate(String dateString, String dateFormatPattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		return dateFormat.parse(dateString);
	}

	private List<VHeaderBlock> removeSealOffGlobalHeader(List<VHeaderBlock> updatedList, String userId) {
		List<VHeaderBlock> resultList = new ArrayList<VHeaderBlock>();
		Integer returnValue=getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}

	private List<VPropertyHeaderBlock> removeSealOffPropertyHeader(List<VPropertyHeaderBlock> updatedList, String userId) {
		List<VPropertyHeaderBlock> resultList = new ArrayList<VPropertyHeaderBlock>();
		Integer returnValue=getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
	
	private List<VTrustHeader> removeSealOffTrustHeader(List<VTrustHeader> updatedList, String userId) {
		List<VTrustHeader> resultList = new ArrayList<VTrustHeader>();
		Integer returnValue=getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
	
	@Override
	public Integer getSystemProfileUserAccValue(String userId) {
		Integer returnValue;
		List<SystemProfiles> systemProfileConfData = new ArrayList<SystemProfiles>();
		SystemProfiles searchRecord = new SystemProfiles();
		searchRecord.setProfileType("CLIENT");
		searchRecord.setProfileCode("SEAL_RECORDS");
		systemProfileConfData = oumpurgeRepository.sysPflSearchSystemProfiles(searchRecord);
		if (!systemProfileConfData.isEmpty()) {
			if (systemProfileConfData.get(0).getProfileValue() != null) {
				Integer accessStaffValue = oumpurgeRepository
						.getAccessStaffCount(Integer.valueOf(systemProfileConfData.get(0).getProfileValue()), userId);
				if (accessStaffValue > 0) {
					returnValue = 1;
				} else {
					returnValue = 0;
				}
			} else {
				returnValue = 0;
			}

		} else {
			returnValue = 0;
		}
		return returnValue;
	}
	
	private List<VHeaderBlock2> removeSealOffGlobalExecuteHeader(List<VHeaderBlock2> updatedList, String userId) {
		List<VHeaderBlock2> resultList = new ArrayList<VHeaderBlock2>();
		Integer returnValue=getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
	
	@Override
	public Long getCorrelationId() {
		return osiosearDao.getCorrelationId();
	}
	
	@Override
	public String getRecentOffenders(Long offenderBookId, String user) {
		return osiosearDao.getRecentOffenders(offenderBookId, user);
	}
	@Override
	public String searchTypeCodeRetrieve() {
		return osiosearDao.searchTypeCodeRetrieve();
	}
	
	
	@Override
	@Async
	public void setGlobalOffSearchAuditLogData(List<TagSearchGetOffenderRecords> searchResult,TagSearchGetOffenderRecords searchBean, String moduleName) {
		List<VHeaderBlock> resultSet = new ArrayList<VHeaderBlock>();
		VHeaderBlock copyObj = new VHeaderBlock();
		for (TagSearchGetOffenderRecords obj : searchResult) {
			try {
				copyObj = new VHeaderBlock();
				BeanUtils.copyProperties(copyObj, obj);
				copyObj.setLastName(obj.getpLastName());
				copyObj.setFirstName(obj.getpFirstName());
				copyObj.setMiddleName(obj.getpMiddleName());
				copyObj.setBirthDate(obj.getpBirthDate());
				copyObj.setBookingNo(obj.getpBookNo());
				resultSet.add(copyObj);
			} catch (Exception e) {
				log.error("Exception in OsiosearService in setGlobalOffSearchResultset ::  {} ",e.getMessage());
			}
		}
		VHeaderBlock auditInputParams = new VHeaderBlock();
		try {
			BeanUtils.copyProperties(auditInputParams, searchBean);
			auditInputParams.setLastName(searchBean.getpLastName());
			auditInputParams.setFirstName(searchBean.getpFirstName());
			auditInputParams.setMiddleName(searchBean.getpMiddleName());
			auditInputParams.setSecondMiddleName(searchBean.getsecondMiddleName());
			auditInputParams.setSex(searchBean.getpSexCode());
			auditInputParams.setGender(searchBean.getpGenderCode());
			auditInputParams.setBirthDate(searchBean.getpBirthDate());
			auditInputParams.setBirthYear(searchBean.getpBirthYear());
			auditInputParams.setBirthRange(searchBean.getpBirthRange());
		} catch (Exception e) {
			log.error("Exception in OsiosearService in setGlobalOffSearchParams ::  {} ",e.getMessage());
		}
		auditLogInjector(resultSet, auditInputParams, moduleName, searchBean.getCreateUserId());
	}
	
	@Override
	@Async
	public  <T> void auditLogInjProcessResult(List<T> resultList,T searchParam, String moduleName, String userId) {
        List<VHeaderBlock> auditResultSet = new ArrayList<>();
        VHeaderBlock auditResultSetObj = new VHeaderBlock();
        for (T source : resultList) {
        	try {
        		auditResultSetObj = new VHeaderBlock();
        		BeanUtils.copyProperties(auditResultSetObj,source);
        		auditResultSet.add(auditResultSetObj);
			} catch (Exception e) {
				log.error("Exception in OsiosearService in auditLogInjProcessResult ResultSet ::  {} ",e.getMessage());
			}
        }
        VHeaderBlock auditInputParams = new VHeaderBlock();
        try {
        	BeanUtils.copyProperties(auditInputParams, searchParam);
		} catch (Exception e) {
			log.error("Exception in OsiosearService in auditLogInjProcessResult SearchBean ::  {} ",e.getMessage());
		}
        auditLogInjector(auditResultSet,auditInputParams, moduleName,userId);
    }
	
	@Override
	@Async
	public void auditLogInjector(List<VHeaderBlock> resultSet, VHeaderBlock inputParameters , String moduleName, String userId) {
		try {
			log.info("OsiosearService auditLogInjector is called for modulename ::  {}, staffid :: {} ",moduleName,userId);
			String[] linksplit = moduleName.split("/");
			String link = linksplit[1];
			link = link.toUpperCase();
			EliteViewLog auditObject = new EliteViewLog();
			auditObject.setInputSearchParameters(inputParameters);
			auditObject.setInputSearchResult(resultSet);
			auditObject.setUserId(userId);
			auditObject.setModuleName(link);
			omss40Service.auditPage(auditObject);
		} catch (Exception e) {
			log.error("Exception in OsiosearService in auditLogInjector ::  {} ",e.getMessage());
			log.error("Exception in OsiosearService in auditLogInjector  Payload resultSet :: {} , inputParameters :: {} Modulename ::  {} ",resultSet,inputParameters,moduleName);
		}
	}

}
