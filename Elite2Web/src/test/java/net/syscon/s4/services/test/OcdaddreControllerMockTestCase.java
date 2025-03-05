package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.AddressUsagesCommitBean;
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.AddressesCommitBean;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdaddreControllerMockTestCase
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcdaddreControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaddreControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vAddressesExecuteQueryTestMethod() {
		try {
			final VAddresses vAddresses = new VAddresses();
			vAddresses.setAddressId(BigDecimal.valueOf(12486));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vAddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/vAddExecuteQuery", vAddresses).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].addressId", is(13148)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgTownRecordGroupTestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/rgTownRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Edmonton")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgCountryRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/rgCountryRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("United States")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgSdirRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/rgSdirRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("NORTH")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgStateRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/rgStateRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("California")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgAddressTypeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/rgAddressTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("House/Apt with Family Short Term")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgPhoneTypeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/rgPhoneTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Home")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void addrExecuteQueryTestMethod() {
		try {
			final Addresses addresses = new Addresses();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrExecuteQuery", addresses).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].addressType", is("hjk")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addrCommitTestMethod() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setAddressId(Long.valueOf(13275));
			addresses.setOwnerClass("ARKIN");
			addresses.setOwnerId(BigDecimal.valueOf(12456));
			addresses.setOwnerSeq(BigDecimal.valueOf(4578));
			addresses.setOwnerCode("BBB");
			addresses.setAddressType("kkkk");
			addresses.setCityCode("HYD");
			addresses.setCountryCode("IND");
			addresses.setValidatedPafFlag("Y");
			addresses.setPrimaryFlag("Y");
			addresses.setMailFlag("Y");
			addresses.setCapacity(BigDecimal.valueOf(1234));
			addresses.setCommentText("YES");
			addresses.setCreateDatetime(new Date());
			addresses.setCreateUserId("SRIKANTH");
			addresses.setModifyDatetime(new Date());
			addresses.setModifyUserId("BBBB");
			addresses.setNoFixedAddressFlag("N");
			addresses.setServicesFlag("Y");
			addresses.setSpecialNeedsCode("BHJU");
			addresses.setContactPersonName("ANY ONE");
			addresses.setBusinessHour("BGTT");
			addresses.setStartDate(new Date());
			addresses.setEndDate(new Date());
			addresses.setCityName("HYDERABAD");
			addresses.setProvStateCode("10120");
			addresses.setStreet("lllll");
			addresses.setZipPostalCode("500025");
			addresses.setSuiteNumber("417");
			addresses.setStreetNumber("16");
			addresses.setStreetDirection("tytyttt");
			addresses.setMailCareOf("Y");
			addresses.setSealFlag("N");

			final List<Addresses> listAdresses = new ArrayList<Addresses>();
			listAdresses.add(addresses);

			final AddressesCommitBean addressesCommitBean = new AddressesCommitBean();
			addressesCommitBean.setInsertList(listAdresses);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addressesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrCommit", addressesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addrUpdateTestMethod() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setOwnerClass("TECH");
			addresses.setOwnerId(BigDecimal.valueOf(12456));
			addresses.setOwnerSeq(BigDecimal.valueOf(4578));
			addresses.setOwnerCode("BBB");
			addresses.setAddressType("kkkk");
			addresses.setCityCode("HYD");
			addresses.setCountryCode("IND");
			addresses.setValidatedPafFlag("Y");
			addresses.setPrimaryFlag("Y");
			addresses.setMailFlag("Y");
			addresses.setCapacity(BigDecimal.valueOf(1234));
			addresses.setCommentText("YES");
			addresses.setCreateDatetime(new Date());
			addresses.setCreateUserId("SRIKANTH");
			addresses.setModifyDatetime(new Date());
			addresses.setModifyUserId("BBBB");
			addresses.setNoFixedAddressFlag("N");
			addresses.setServicesFlag("Y");
			addresses.setSpecialNeedsCode("BHJU");
			addresses.setContactPersonName("ANY ONE");
			addresses.setBusinessHour("BGTT");
			addresses.setStartDate(new Date());
			addresses.setEndDate(new Date());
			addresses.setCityName("HYDERABAD");
			addresses.setProvStateCode("10120");
			addresses.setStreet("lllll");
			addresses.setZipPostalCode("500025");
			addresses.setSuiteNumber("417");
			addresses.setStreetNumber("16");
			addresses.setStreetDirection("tytyttt");
			addresses.setMailCareOf("Y");
			addresses.setSealFlag("N");
			addresses.setAddressId(Long.valueOf(13275));

			final List<Addresses> listAdresses = new ArrayList<Addresses>();
			listAdresses.add(addresses);

			final AddressesCommitBean addcommitBean = new AddressesCommitBean();
			addcommitBean.setUpdateList(listAdresses);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addcommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrCommit", addcommitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addrDeleteTestMethod() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setAddressId(Long.valueOf(13275));

			final List<Addresses> listAdresses = new ArrayList<Addresses>();
			listAdresses.add(addresses);

			final AddressesCommitBean addCommit = new AddressesCommitBean();
			addCommit.setDeleteList(listAdresses);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addCommit);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrCommit", addCommit).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void usagesExecuteQueryTestMethod() {
		try {
			final AddressUsages addressUsages = new AddressUsages();
			addressUsages.setAddressId(Long.valueOf(10546));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addressUsages);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrUsageExecuteQuery", addressUsages)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].addressUsage", is("HOTEL")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void addressUsagesCommitTestMethod() {
		try {
			final AddressUsages addressUsages = new AddressUsages();
			addressUsages.setAddressId(Long.valueOf(13103));
			addressUsages.setAddressUsage("Sriii");
			addressUsages.setActiveFlag("Y");
			addressUsages.setCreateDatetime(new Date());
			addressUsages.setCreateUserId("SR");
			addressUsages.setModifyDatetime(new Date());
			addressUsages.setModifyUserId("SR");
			addressUsages.setSealFlag("N");
			final List<AddressUsages> insertAddressUsagesList = new ArrayList<AddressUsages>();
			insertAddressUsagesList.add(addressUsages);
			final AddressUsagesCommitBean addressUsagesCommitBeanList = new AddressUsagesCommitBean();
			addressUsagesCommitBeanList.setInsertList(insertAddressUsagesList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addressUsagesCommitBeanList);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrUsageCommit", addressUsagesCommitBeanList)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addressUsagesUpdateTestMethod() {
		try {
			final AddressUsages addressUsages = new AddressUsages();
			addressUsages.setActiveFlag("N");
			addressUsages.setCreateDatetime(new Date());
			addressUsages.setCreateUserId("SR");
			addressUsages.setModifyDatetime(new Date());
			addressUsages.setModifyUserId("SR");
			addressUsages.setSealFlag("N");
			addressUsages.setAddressId(Long.valueOf(13103));
			addressUsages.setAddressUsage("HOTEL");
			final List<AddressUsages> updateAddressUsagesList = new ArrayList<AddressUsages>();
			updateAddressUsagesList.add(addressUsages);
			final AddressUsagesCommitBean addressUsagesupdateBeanList = new AddressUsagesCommitBean();
			addressUsagesupdateBeanList.setUpdateList(updateAddressUsagesList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addressUsagesupdateBeanList);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrUsageCommit", addressUsagesupdateBeanList)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addressUsagesDeleteTestMethod() {
		try {
			final AddressUsages addressUsages = new AddressUsages();
			addressUsages.setAddressId(Long.valueOf(13103));
			addressUsages.setAddressUsage("Sriii");
			final List<AddressUsages> deleteAddressUsagesList = new ArrayList<AddressUsages>();
			deleteAddressUsagesList.add(addressUsages);
			final AddressUsagesCommitBean addressUsagesdeleteBeanList = new AddressUsagesCommitBean();
			addressUsagesdeleteBeanList.setDeleteList(deleteAddressUsagesList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addressUsagesdeleteBeanList);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addrUsageCommit", addressUsagesdeleteBeanList)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void phoneAddrExecuteQueryTestMethod() {
		try {
			final Phones phones = new Phones();
			phones.setPhoneId(Long.valueOf(4739));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(phones);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/phoneAddrExecuteQuery", phones).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].modifyUserId", is("OMS_OWNER")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void phoneAddrCommitTestMethod() {
		try {
			final Phones phones = new Phones();
			phones.setPhoneId(Long.valueOf(12351));
			phones.setOwnerClass("Srik");
			phones.setOwnerId(BigDecimal.valueOf(1111));
			phones.setOwnerSeq(BigDecimal.valueOf(22222));
			phones.setOwnerCode("Arkin");
			phones.setPhoneType("gjhg");
			phones.setPhoneNo("99999999");
			phones.setExtNo("ghgjkg");
			phones.setCreateDatetime(new Date());
			phones.setCreateUserId("ssss");
			phones.setModifyDatetime(new Date());
			phones.setModifyUserId("kkkk");
			phones.setSealFlag("N");
			final List<Phones> phoneList = new ArrayList<Phones>();
			phoneList.add(phones);
			final PhonesCommitBean phonesInsertBean = new PhonesCommitBean();
			phonesInsertBean.setInsertList(phoneList);
			final String jsonInString = new ObjectMapper().writeValueAsString(phonesInsertBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/phoneAddrCommit", phonesInsertBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void phoneAddrUpdateTestMethod() {
		try {
			final Phones phones = new Phones();
			phones.setOwnerClass("arkinsoft");
			phones.setPhoneId(Long.valueOf(12351));
			final List<Phones> phoneList = new ArrayList<Phones>();
			phoneList.add(phones);
			final PhonesCommitBean phonesUpdateBean = new PhonesCommitBean();
			phonesUpdateBean.setUpdateList(phoneList);
			final String jsonInString = new ObjectMapper().writeValueAsString(phonesUpdateBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/phoneAddrCommit", phonesUpdateBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void phoneAddrDeleteTestMethod() {
		try {
			final Phones phones = new Phones();
			phones.setPhoneId(Long.valueOf(12351));
			final List<Phones> phoneList = new ArrayList<Phones>();
			phoneList.add(phones);
			final PhonesCommitBean phonesDeleteBean = new PhonesCommitBean();
			phonesDeleteBean.setDeleteList(phoneList);
			final String jsonInString = new ObjectMapper().writeValueAsString(phonesDeleteBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/phoneAddrCommit", phonesDeleteBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	/*
	 * @Test public void phonesExecuteQueryTestMethod() { // no impl try {
	 * TagSearchGetOffenderRecords tagSearchGetOffenderRecords = new
	 * TagSearchGetOffenderRecords(); String jsonInString = new
	 * ObjectMapper().writeValueAsString(tagSearchGetOffenderRecords);
	 * ResultActions resultActions = this.mockMvc
	 * .perform(post("/ocdaddrePhoneGlobalExecuteQuery",
	 * tagSearchGetOffenderRecords)
	 * .contentType(MediaType.APPLICATION_JSON).content(jsonInString)
	 * .accept(MediaType.APPLICATION_JSON_UTF8))
	 * .andExpect(status().isOk()).andExpect(content().contentType(MediaType.
	 * APPLICATION_JSON_UTF8_VALUE)) .andDo(print());
	 * resultActions.andExpect(jsonPath("$[0].name", is("Name"))); } catch
	 * (Exception e) { logger.error("", e); } }
	 */
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */

	/*
	 * @Test public void phonesCommitTestMethod() { // no impl try {
	 * TagSearchGetOffenderRecords tagSearchGetOffenderRecords = new
	 * TagSearchGetOffenderRecords(); String jsonInString = new
	 * ObjectMapper().writeValueAsString(tagSearchGetOffenderRecords);
	 * ResultActions resultActions = this.mockMvc
	 * .perform(post("/ocdaddrePhoneGlobalCommit", tagSearchGetOffenderRecords)
	 * .contentType(MediaType.APPLICATION_JSON).content(jsonInString)
	 * .accept(MediaType.APPLICATION_JSON_UTF8))
	 * .andExpect(status().isOk()).andExpect(content().contentType(MediaType.
	 * APPLICATION_JSON_UTF8_VALUE)) .andDo(print());
	 * resultActions.andExpect(jsonPath("$[0].name", is("Name"))); } catch
	 * (Exception e) { logger.error("", e); } }
	 */
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void internetAddressesExecuteQueryTestMethod() {
		try {
			final InternetAddresses internetAddresses = new InternetAddresses();
			internetAddresses.setInternetAddressId(Long.valueOf(2261));
			final String jsonInString = new ObjectMapper().writeValueAsString(internetAddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/emailExecuteQuery", internetAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].internetAddress", is("ram@gmail.cou")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void internetAddressesCommitTestMethod() {
		try {
			final InternetAddresses internetAddresses = new InternetAddresses();
			internetAddresses.setInternetAddressId(Long.valueOf(3474));
			internetAddresses.setOwnerClass("srikkka");
			internetAddresses.setOwnerId(BigDecimal.valueOf(111));
			internetAddresses.setOwnerSeq(BigDecimal.valueOf(222));
			internetAddresses.setOwnerCode("Arkin");
			internetAddresses.setInternetAddressClass("EMAIL");
			internetAddresses.setInternetAddress("sk@arkin.com");
			internetAddresses.setCreateDatetime(new Date());
			internetAddresses.setCreateUserId("srikanth");
			internetAddresses.setModifyDatetime(new Date());
			internetAddresses.setModifyUserId("bbbbb");
			internetAddresses.setSealFlag("N");

			final List<InternetAddresses> internetAddressesList = new ArrayList<InternetAddresses>();
			internetAddressesList.add(internetAddresses);

			final InternetAddressesCommitBean internetAddressesCommitBean = new InternetAddressesCommitBean();
			internetAddressesCommitBean.setInsertList(internetAddressesList);
			final String jsonInString = new ObjectMapper().writeValueAsString(internetAddressesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/emailCommit", internetAddressesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void internetAddressesUpdateTestMethod() {
		try {
			final InternetAddresses internetAddresses = new InternetAddresses();
			internetAddresses.setOwnerClass("bbbbbb");
			internetAddresses.setOwnerId(BigDecimal.valueOf(111));
			internetAddresses.setOwnerSeq(BigDecimal.valueOf(222));
			internetAddresses.setOwnerCode("Arkin");
			internetAddresses.setInternetAddressClass("EMAIL");
			internetAddresses.setInternetAddress("sk@arkin.com");
			internetAddresses.setCreateDatetime(new Date());
			internetAddresses.setCreateUserId("srikanth");
			internetAddresses.setModifyDatetime(new Date());
			internetAddresses.setModifyUserId("bbbbb");
			internetAddresses.setSealFlag("N");
			internetAddresses.setInternetAddressId(Long.valueOf(3474));

			final List<InternetAddresses> internetAddressesList = new ArrayList<InternetAddresses>();
			internetAddressesList.add(internetAddresses);

			final InternetAddressesCommitBean internetAddressesCommitBean = new InternetAddressesCommitBean();
			internetAddressesCommitBean.setUpdateList(internetAddressesList);
			final String jsonInString = new ObjectMapper().writeValueAsString(internetAddressesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/emailCommit", internetAddressesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void internetAddressesDeleteTestMethod() {
		try {
			final InternetAddresses internetAddresses = new InternetAddresses();
			internetAddresses.setInternetAddressId(Long.valueOf(3470));

			final List<InternetAddresses> internetAddressesList = new ArrayList<InternetAddresses>();
			internetAddressesList.add(internetAddresses);

			final InternetAddressesCommitBean internetAddressesCommitBean = new InternetAddressesCommitBean();
			internetAddressesCommitBean.setDeleteList(internetAddressesList);
			final String jsonInString = new ObjectMapper().writeValueAsString(internetAddressesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/emailCommit", internetAddressesCommitBean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void offbkgoncheckdeletemastervaddcurTestMethod() {
		try {
			final VAddresses vAddresses = new VAddresses();
			vAddresses.setOwnerId(BigDecimal.valueOf(1019061));
			final String jsonInString = new ObjectMapper().writeValueAsString(vAddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/offbkgoncheckdeletemastervaddcur", vAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void offbkgoncheckdeletemasterphoneglobalcurTestMethod() {
		try {
			final Phones phones = new Phones();
			phones.setOwnerId(BigDecimal.valueOf(10188));
			final String jsonInString = new ObjectMapper().writeValueAsString(phones);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/offbkgoncheckdeletemasterphoneglobalcur", phones)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void offbkgoncheckdeletemasteremailcurTestMethod() {
		try {
			final InternetAddresses internetAddresses = new InternetAddresses();
			internetAddresses.setOwnerId(BigDecimal.valueOf(1024155));
			final String jsonInString = new ObjectMapper().writeValueAsString(internetAddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/offbkgoncheckdeletemasteremailcur", internetAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void offbkgoncheckdeletemasteraddrcurTestMethod() {
		try {
			final Addresses address = new Addresses();
			address.setOwnerId(BigDecimal.valueOf(1019061));
			final String jsonInString = new ObjectMapper().writeValueAsString(address);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/offbkgoncheckdeletemasteraddrcur", address)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void vaddoncheckdeletemasteraddrcurTestMethod() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setAddressId(Long.valueOf(12486));
			final String jsonInString = new ObjectMapper().writeValueAsString(addresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/vaddoncheckdeletemasteraddrcur", addresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void nbtcitykeylistvalgetcitydescriptionTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/nbtcitykeylistvalgetcitydescription", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(6)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void addroncheckdeletemasterphoneaddrcurTestMethod() {
		try {
			final Phones phones = new Phones();
			phones.setOwnerId(BigDecimal.valueOf(10188));
			final String jsonInString = new ObjectMapper().writeValueAsString(phones);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addroncheckdeletemasterphoneaddrcur", phones)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void addroncheckdeletemasteraddrusagecurTestMethod() {
		try {
			final AddressUsages addressUsages = new AddressUsages();
			addressUsages.setAddressId(Long.valueOf(10546));
			final String jsonInString = new ObjectMapper().writeValueAsString(addressUsages);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addroncheckdeletemasteraddrusagecur", addressUsages)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addrwhencreaterecordgetcountrycurTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setDomain("CITY");
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/addrwhencreaterecordgetcountrycur", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Toronto")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	/*
	 * @Test public void ocdaddreCreateformglobals() { // query not available
	 * try { TagSearchGetOffenderRecords tagSearchGetOffenderRecords = new
	 * TagSearchGetOffenderRecords(); String jsonInString = new
	 * ObjectMapper().writeValueAsString(tagSearchGetOffenderRecords);
	 * ResultActions resultActions = this.mockMvc
	 * .perform(get("/api/ocdaddreCreateformglobals",
	 * tagSearchGetOffenderRecords)
	 * .contentType(MediaType.APPLICATION_JSON).content(jsonInString)
	 * .accept(MediaType.APPLICATION_JSON_UTF8))
	 * .andExpect(status().isOk()).andExpect(content().contentType(MediaType.
	 * APPLICATION_JSON_UTF8_VALUE)) .andDo(print());
	 * resultActions.andExpect(jsonPath("$[0].name", is("Name"))); } catch
	 * (Exception e) { logger.error("", e); } }
	 */
	/**
	 * method for query callings
	 */
	@Test
	public void addresstypecheckactivetypecurTestMethod() {
		try {
			final AddressUsages addressUsages = new AddressUsages();
			addressUsages.setAddressId(Long.valueOf(10546));
			final String jsonInString = new ObjectMapper().writeValueAsString(addressUsages);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/addresstypecheckactivetypecur", addressUsages)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void validatecityinfogetcitydescriptionTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/validatecityinfogetcitydescription", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(6)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void validatecityinfogetcitycodeTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			final String jsonInString = new ObjectMapper().writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdaddre/validatecityinfogetcitycode", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("MONTGOMERY")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void checkhdcaddressactivecheckhdcaddresscurTestMethod() {
		try {
			final VAddresses vaddresses = new VAddresses();
			vaddresses.setOwnerId(BigDecimal.valueOf(1019061));
			final String jsonInString = new ObjectMapper().writeValueAsString(vaddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/checkhdcaddressactivecheckhdcaddresscur", vaddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(11764)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	/*
	 * @Test public void ocdaddreCheckhdcaddressactive() { // no sql try {
	 * TagSearchGetOffenderRecords tagSearchGetOffenderRecords = new
	 * TagSearchGetOffenderRecords(); String jsonInString = new
	 * ObjectMapper().writeValueAsString(tagSearchGetOffenderRecords);
	 * ResultActions resultActions = this.mockMvc
	 * .perform(get("/ocdaddreCheckhdcaddressactive",
	 * tagSearchGetOffenderRecords)
	 * .contentType(MediaType.APPLICATION_JSON).content(jsonInString)
	 * .accept(MediaType.APPLICATION_JSON_UTF8))
	 * .andExpect(status().isOk()).andExpect(content().contentType(MediaType.
	 * APPLICATION_JSON_UTF8_VALUE)) .andDo(print());
	 * resultActions.andExpect(jsonPath("$[0].name", is("Name"))); } catch
	 * (Exception e) { logger.error("", e); } }
	 */
	/**
	 * method for query callings
	 */
	@Test
	public void checkhdcaddressexistcheckhdcaddresscurTestMethod() {
		try {

			final VAddresses vaddresses = new VAddresses();
			vaddresses.setOwnerId(BigDecimal.valueOf(1019061));
			vaddresses.setAddressId(BigDecimal.valueOf(11764));

			final String jsonInString = new ObjectMapper().writeValueAsString(vaddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdaddre/checkhdcaddressexistcheckhdcaddresscur", vaddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(11764)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	
}