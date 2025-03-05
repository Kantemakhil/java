package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.booking.beans.PersonEmployments;
import net.syscon.s4.inst.booking.beans.PersonEmploymentsCommitBean;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.PersonIdentifiersCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.booking.beans.VPersonAddress;
import net.syscon.s4.inst.visitsmanagement.OsipsearRepository;
import net.syscon.s4.inst.visitsmanagement.OsipsearService;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersons;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPartialSoundexPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.TagPersonSearchGetPersons;
import net.syscon.s4.pkgs.tag_person_search.TagPersonSearchService;

@Service
public class OsipsearServiceImpl extends BaseBusiness implements OsipsearService {

	@Autowired
	private OsipsearRepository osipsearRepository;
	@Autowired
	private TagPersonSearchService tagpersonsearchservice;

	@Autowired
	private EliteDateService dateService;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OsipsearServiceImpl class Object
	 */
	public OsipsearServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VAddresses> personsOnCheckDeleteMaster(VAddresses paramBean) {
		List<VAddresses> vPersonAddressesList = null;
		return vPersonAddressesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<TagPersonSearchGetPersons> personsExecuteQuery(TagPersonSearchGetPersons searchRecord) {
		List<TagPersonSearchGetPersons> returnList = new ArrayList<>();
		
		Persons perBean = new Persons();
		perBean.setSearchType(searchRecord.getpSearchType() != null ? searchRecord.getpSearchType().toString() : null);
		perBean.setLastName(searchRecord.getpLastName() != null ? searchRecord.getpLastName().toString() : null);
		perBean.setMiddleName(searchRecord.getpMiddleName() != null ? searchRecord.getpMiddleName().toString() : null);
		perBean.setFirstName(searchRecord.getpFirstName() != null ? searchRecord.getpFirstName().toString() : null);
		perBean.setBirthdate(searchRecord.getpBirthDate() != null ? (Date) searchRecord.getpBirthDate() : null);
		perBean.setPersonId((searchRecord.getpPersonId() != null
				? Long.valueOf(searchRecord.getpPersonId().toString()) : null));
		perBean.setIdentifierType(searchRecord.getpIdentifierType() != null ? searchRecord.getpIdentifierType().toString() : null);
		perBean.setIdentifier(searchRecord.getpIdentifierValue() != null ? searchRecord.getpIdentifierValue().toString() : null);
		perBean.setBirthYear((searchRecord.getpBirthYear() != null
				? Integer.valueOf(searchRecord.getpBirthYear().toString()) : null));
		perBean.setBirthRange((searchRecord.getpBirthRange() != null
				? Integer.valueOf(searchRecord.getpBirthRange().toString()) : null));
		perBean.setSex(searchRecord.getpSex() != null
				? searchRecord.getpSex() : null);
		perBean.setsecondMiddleName(searchRecord.getsecondMiddleName() != null ? searchRecord.getsecondMiddleName().toString() : null);
		perBean.setSexDescription(searchRecord.getSexDescription() != null ? searchRecord.getSexDescription().toString() : null);
			try {
				List<Persons> 	returnObj = tagpersonsearchservice.getPersons(perBean);
				returnObj.forEach(result -> {
					TagPersonSearchGetPersons bean = new TagPersonSearchGetPersons();
					bean.setLastName(result.getLastName() != null ? result.getLastName().toString() : null);
					bean.setMiddleName(result.getMiddleName() != null ? result.getMiddleName().toString() : null);
					bean.setFirstName(result.getFirstName() != null ? result.getFirstName().toString() : null);
					bean.setBirthDate(result.getBirthdate() != null ? (Date) result.getBirthdate() : null);
					bean.setSex(result.getSex() != null ? result.getSex().toString() : null);
					bean.setPersonId((result.getPersonId() != null
							? BigDecimal.valueOf(Long.valueOf(result.getPersonId().toString())) : null));
					if (result.getBirthdate() != null) {
						bean.setAge(dateService.calculateAge(result.getBirthdate()));
					}
					bean.setsecondMiddleName(result.getsecondMiddleName() != null ? result.getsecondMiddleName().toString() : null);
					bean.setSexDescription(result.getSexDescription() != null ? result.getSexDescription().toString() : null);
					//For PIN
					bean.setPin(osipsearRepository.getIdentifierData(bean.getPersonId()));
					returnList.add(bean);
				});
			} catch (Exception e) {
			}
			// Adding Additional Alias Names Data
			if (ApplicationConstants.NFLAG.equalsIgnoreCase(searchRecord.getpSearchType())) {
				List<TagPersonSearchGetPersons> list = getAdditionaNamesAsParent(searchRecord);
				if(list!=null && list.size()>0) {
					returnList.addAll(list);
				}
			}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPERSONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer personsCommit(TagPersonSearchGetPersons CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VPersonAddress> perAddrExecuteQuery(VPersonAddress searchRecord) {
		List<VPersonAddress> returnList= osipsearRepository.perAddrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
		for(VPersonAddress address:returnList) {
              if(address.getProvStateDesc() == null) {
            		address.setProvStateDesc(address.getProvStateCode());
              }
			
              if(address.getCityName() == null) {
          		address.setCityName(address.getCityCode());
            }
		}
		}
		return returnList;

	

	

	

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_ADDR
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer perAddrCommit(AddressesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<PersonIdentifiers> perIdentExecuteQuery(PersonIdentifiers searchRecord) {
		return osipsearRepository.perIdentExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_IDENT
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer perIdentCommit(PersonIdentifiersCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<PersonIdentifiers> dataList = new ArrayList<PersonIdentifiers>();
			for(PersonIdentifiers data: commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				dataList.add(data);
				liReturn = osipsearRepository.perIdentInsertPersonIdentifiers(dataList);
				dataList.clear();
			}
			
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(PersonIdentifiers data: commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = osipsearRepository.perIdentUpdatePersonIdentifiers(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = osipsearRepository.perIdentDeletePersonIdentifiers(commitBean.getDeleteList());
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
	public List<Images> imageExecuteQuery(Images searchRecord) {
		return osipsearRepository.imageExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstIMAGE
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer imageCommit(ImagesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Persons> perInfoExecuteQuery(Persons searchRecord) {
		return osipsearRepository.perInfoExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_INFO
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer perInfoCommit(PersonsCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(result -> {
				if (result.getInterpreterRequired() != null && result.getInterpreterRequired().equals("true")) {
					result.setInterpreterRequired("Y");
				} else {
					result.setInterpreterRequired("N");
				}

				if (result.getStaffFlag() != null && result.getStaffFlag().equals("true")) {
					result.setStaffFlag("Y");
				} else {
					result.setStaffFlag("N");
				}
				result.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = osipsearRepository.perInfoUpdatePersons(commitBean.getUpdateList());
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
	public List<PersonEmployments> perEmpExecuteQuery(PersonEmployments searchRecord) {
		return osipsearRepository.perEmpExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER_EMP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer perEmpCommit(PersonEmploymentsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<PersonEmployments> dataList = new ArrayList<>();
			for(PersonEmployments data: commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
				dataList.add(data);
				liReturn = osipsearRepository.perEmpInsertPersonEmployments(dataList);
				dataList.clear();
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(PersonEmployments data: commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = osipsearRepository.perEmpUpdatePersonEmployments(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = osipsearRepository.perEmpDeletePersonEmployments(commitBean.getDeleteList());
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
	public List<TagPersonSearchGetPartialSoundexPersons> psPersonNameExecuteQuery(
			TagPersonSearchGetPartialSoundexPersons searchRecord) {
		// return osipsearRepository.psPersonNameExecuteQuery(searchRecord);
		List<TagPersonSearchGetPartialSoundexPersons> returnList = new ArrayList<>();

		Persons personBean = new Persons();
		personBean.setSearchType(searchRecord.getpSearchType() != null ? searchRecord.getpSearchType().toString() : null);
		//personBean.setLastName(searchRecord.getpSearchType() != null ? searchRecord.getpSearchType().toString() : null);
		personBean.setLastName(searchRecord.getpLastName() != null ? searchRecord.getpLastName().toString() : null);
		personBean
				.setMiddleName(searchRecord.getpMiddleName() != null ? searchRecord.getpMiddleName().toString() : null);
		personBean.setFirstName(searchRecord.getpFirstName() != null ? searchRecord.getpFirstName().toString() : null);
		personBean.setBirthdate(searchRecord.getPBirthDate() != null ? (Date) searchRecord.getPBirthDate() : null);
		personBean.setPersonId(
				(searchRecord.getpPersonId() != null ? Long.valueOf(searchRecord.getpPersonId().toString()) : null));
		personBean.setIdentifierType(
				searchRecord.getpIdentifierType() != null ? searchRecord.getpIdentifierType().toString() : null);
		personBean.setIdentifier(
				searchRecord.getpIdentifierValue() != null ? searchRecord.getpIdentifierValue().toString() : null);
		personBean.setBirthYear((searchRecord.getpBirthYear() != null
				? Integer.valueOf(searchRecord.getpBirthYear().toString()) : null));
		personBean.setBirthRange((searchRecord.getpBirthRange() != null
				? Integer.valueOf(searchRecord.getpBirthRange().toString()) : null));
		personBean.setsecondMiddleName(searchRecord.getsecondMiddleName() != null 
				? searchRecord.getsecondMiddleName().toString() : null);
		try {
			List<Persons> returnObj = tagpersonsearchservice.getPartialSoundexPersons(personBean);
			returnObj.forEach(result -> {
				TagPersonSearchGetPartialSoundexPersons bean = new TagPersonSearchGetPartialSoundexPersons();
				bean.setLastName(result.getLastName() != null ? result.getLastName().toString() : null);
				bean.setHits((result.getHits() != null ? BigDecimal.valueOf(Long.valueOf(result.getHits().toString()))
						: null));
				bean.setsecondMiddleName(result.getsecondMiddleName() != null ? result.getsecondMiddleName().toString() : null);
				returnList.add(bean);
			});
		} catch (Exception e) {
		}

		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPS_PERSON_NAME
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer psPersonNameCommit(TagPersonSearchGetPartialSoundexPersonsCommitBean commitBean) {
		int liReturn = 0;
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = osipsearRepository
					.psPersonNameDeleteTagPersonSearchGetPartialSoundexPersons(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgLanguageCodeRecordGroup() {
		 List<ReferenceCodes> refList = osipsearRepository.rgLanguageCodeRecordGroup();
		return filterRefCodeList(refList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgMaritalStatusRecordGroup() {
		List<ReferenceCodes> refList = osipsearRepository.rgMaritalStatusRecordGroup();
		return filterRefCodeList(refList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		return osipsearRepository.rgSexCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSearchTypeRecordGroup() {
		List<ReferenceCodes> refList = osipsearRepository.rgSearchTypeRecordGroup();
		return filterRefCodeList(refList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgIdentifierTypeRecordGroup() {
		 List<ReferenceCodes> refList = osipsearRepository.rgIdentifierTypeRecordGroup();
		return filterRefCodeList(refList);
	}

	private List<ReferenceCodes> filterRefCodeList(List<ReferenceCodes> refList) {
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}
	
	
	@Override
	public List<Offenders> getAdditionalNames(Long personId) {
		return osipsearRepository.getAdditionalNames(personId);
	}

	
	@Override
	public Integer personAddNamesCommit(OffendersCommitBean commitBean) {
		Integer result = 0;
		if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			result = osipsearRepository.offInsertOffenders(commitBean.getInsertList());
		}
		if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			result = osipsearRepository.updateOffenders(commitBean.getUpdateList());
		}
		if (commitBean != null && commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			result = osipsearRepository.deleteOffenders(commitBean.getDeleteList());
		}
		return result;
	}
	
	
	
	@Override
	public List<TagPersonSearchGetPersons> getDataFromJisCommonSystemForPerson(Long intCorrelationId,
			String nameSearchType, String moduleName) {
		if (ApplicationConstants.I.equalsIgnoreCase(nameSearchType)) {
			return getDataFromJisCommonSystemForPersonPin(intCorrelationId);
		} else {
			return getDataFromJisCommonSystemForPersonNameSearch(intCorrelationId);
		}
	}

	//For PIN
	private List<TagPersonSearchGetPersons> getDataFromJisCommonSystemForPersonPin(Long intCorrelationId) {
		TagPersonSearchGetPersons obj = osipsearRepository.getDataFromJisCommonSystem(intCorrelationId);
		List<TagPersonSearchGetPersons> listMap = new ArrayList<TagPersonSearchGetPersons>();
		if (obj != null &&obj.getResponseData() != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String data = new String(obj.getResponseData());
				Map<String, Object> map = objectMapper.readValue(data, Map.class);
				Map<String, Object> body = (Map<String, Object>) map.get("body");
				List<Map<String, Object>> aliases = (List<Map<String, Object>>) body.get("aliases");
				for (Map<String, Object> mapOne : aliases) {
					TagPersonSearchGetPersons bean = new TagPersonSearchGetPersons();
					bean.setLastName((String) mapOne.get("surName"));
					bean.setFirstName((String) mapOne.get("firstName"));
					bean.setMiddleName((String) mapOne.get("middleName"));
					bean.setsecondMiddleName((String) mapOne.get("secondMiddleName"));
					//bean.setNameType("JIS Common - Alias");
					Map<String, Object> personIdentityId = (Map<String, Object>) mapOne.get("personIdentityId");
					if(personIdentityId != null) {
						bean.setPnin(personIdentityId.get("PNIN").toString());
					}
					String dob = (String) mapOne.get("dob");
					if (dob != null) {
						String dateFormatPattern = "MM/dd/yyyy";
						try {
							Date date = convertStringToDate(dob, dateFormatPattern);
							bean.setBirthDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					//bean.setpAgeRange(getAge(bean.getpBirthDate()) != null ? new BigDecimal(getAge(bean.getpBirthDate())) : null);
					listMap.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (listMap != null && listMap.size() > 0) ? listMap : null;

	}

	//For Name Search
	private List<TagPersonSearchGetPersons> getDataFromJisCommonSystemForPersonNameSearch(Long intCorrelationId) {
		TagPersonSearchGetPersons obj = osipsearRepository.getDataFromJisCommonSystem(intCorrelationId);
		List<TagPersonSearchGetPersons> listMap = new ArrayList<TagPersonSearchGetPersons>();
		if (obj != null && obj.getResponseData() != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String data = new String(obj.getResponseData());
				Map<String, Object> map = objectMapper.readValue(data, Map.class);
				Map<String, Object> body = (Map<String, Object>) map.get("body");
				List<Map<String, Object>> aliases = (List<Map<String, Object>>) body.get("personDetails");
				for (Map<String, Object> mapOne : aliases) {
					TagPersonSearchGetPersons bean = new TagPersonSearchGetPersons();
					bean.setLastName((String) mapOne.get("surName"));
					bean.setFirstName((String) mapOne.get("firstName"));
					bean.setMiddleName((String) mapOne.get("middleName"));
					bean.setsecondMiddleName((String) mapOne.get("secondMiddleName"));
					//bean.setNameType("JIS Common - Alias");
					bean.setPin(mapOne.get("pin") != null ? mapOne.get("pin").toString() : null);
					String dob = (String) mapOne.get("dob");
					if (dob != null) {
						String dateFormatPattern = "MM/dd/yyyy";
						try {
							Date date = convertStringToDate(dob, dateFormatPattern);
							bean.setBirthDate(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					//bean.setpAgeRange(getAge(bean.getpBirthDate()) != null ? new BigDecimal(getAge(bean.getpBirthDate())) : null);
					listMap.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (listMap != null && listMap.size() > 0) ? listMap : null;
	}
	
	private Long getAge(Date dob) {
		Period age = Period.between(LocalDate.of(dob.getYear() + 1900, dob.getMonth(), dob.getDate()), LocalDate.now());
		return (long) age.getYears();
	}

	public static Date convertStringToDate(String dateString, String dateFormatPattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		return dateFormat.parse(dateString);
	}
	
	
	private List<TagPersonSearchGetPersons> getAdditionaNamesAsParent(TagPersonSearchGetPersons searchRecord){
		List<TagPersonSearchGetPersons>  list = osipsearRepository.getAdditionaNamesAsParent(searchRecord);
		if(list!=null && list.size()>0 && !list.isEmpty()) {
			list.forEach(bo->{
				TagPersonSearchGetPersons obj = osipsearRepository.getgetAdditionaNamesDetails(bo.getPersonId());
				if(obj!=null) {
					obj.setpBirthDate(obj.getBirthDate());
					obj.setPin(obj.getPin());
				}
			});
			return list;
		}
		return null;
	}

}