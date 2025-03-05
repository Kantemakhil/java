package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.AddressesCommitBean;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderEducations;
import net.syscon.s4.common.beans.OffenderEducationsCommitBean;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.OffenderEmploymentsCommitBean;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.beans.VOffenderEmployAddresses;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdedempControllerMockTestCase
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OcdedempControllerMockTestCase  extends AbstractMockTestCase {

	@Test
	public void offEducationsSearchOffenderEducations() {
		try {
			final OffenderEducations offendereducations = new OffenderEducations();
			offendereducations.setOffenderBookId(Long.valueOf(22110));
			offendereducations.setEducationSeq(2);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offendereducations);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offEducationsExecuteQuery", offendereducations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(22110)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEducationsCommit() {
		final OffenderEducations offendereducations = new OffenderEducations();
		final List<OffenderEducations> insertList = new ArrayList<OffenderEducations>();
		final List<OffenderEducations> updateList = new ArrayList<OffenderEducations>();
		final List<OffenderEducations> deleteList = new ArrayList<OffenderEducations>();
		offendereducations.setOffenderBookId(Long.valueOf(20505));
		offendereducations.setEducationSeq(4);
		offendereducations.setNumberOfYears(5);
		offendereducations.setGraduationYear(2);
		offendereducations.setSchoolName("hjk");
		offendereducations.setSpecialEducationFlag("Y");
		offendereducations.setRootOffenderId(1237);
		offendereducations.setCreateUserId("456");
		offendereducations.setCreateDatetime(new Timestamp(12));

		insertList.add(offendereducations);
		updateList.add(offendereducations);
		deleteList.add(offendereducations);
		final OffenderEducationsCommitBean offenderCommitBean = new OffenderEducationsCommitBean();
		offenderCommitBean.setInsertList(insertList);
		offenderCommitBean.setUpdateList(updateList);
		offenderCommitBean.setDeleteList(deleteList);
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offEducationsCommit", offenderCommitBean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(offenderCommitBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void eduSchedRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/eduSchedRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Full time")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void payPeriodRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/payPeriodRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Monthly")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void occupationRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/occupationRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Armed forces")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void scheduleTypeRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/scheduleTypeRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Weekly")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void employStsRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/employStsRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("CAS")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void studyAreaRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/studyAreaRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[2].description", is("Accountancy")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void eduLevelRgRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdedemp/eduLevelRgRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("ES")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void vOffEduAddrSearchVOffenderEducationAddresses() {
		try {
			final VOffenderEducationAddresses vOffenderEducationAddresses = new VOffenderEducationAddresses();
			vOffenderEducationAddresses.setAddressId(13161);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffenderEducationAddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/vOffEduAddrExecuteQuery", vOffenderEducationAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].addressId", is(13161)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsSearchOffenderEmployments() {
		try {
			final OffenderEmployments offenderemployments = new OffenderEmployments();
			offenderemployments.setOffenderBookId(Long.valueOf(19344));
			offenderemployments.setEmploySeq(3);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderemployments);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offEmploymentsExecuteQuery", offenderemployments)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(19344)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsCommit() {
		final OffenderEmployments offenderemployments = new OffenderEmployments();
		final List<OffenderEmployments> insertList = new ArrayList<OffenderEmployments>();
		final List<OffenderEmployments> updateList = new ArrayList<OffenderEmployments>();
		final List<OffenderEmployments> deleteList = new ArrayList<OffenderEmployments>();
		offenderemployments.setOffenderBookId(Long.valueOf(9876));
		offenderemployments.setEmploySeq(5);
		offenderemployments.setEmploymentPostCode("5rt6");
		offenderemployments.setEmployerName("dsalk");
		offenderemployments.setWage(BigDecimal.valueOf(7654));
		offenderemployments.setRootOffenderId(8531);
		offenderemployments.setScheduleHours(6);
		offenderemployments.setHoursWeek(50);
		offenderemployments.setEmployerAwareFlag("Y");
		offenderemployments.setContactEmployerFlag("Y");
		offenderemployments.setCreateDatetime(new Timestamp(12));
		offenderemployments.setCreateUserId("987");
		offenderemployments.setModifyDatetime(new Timestamp(11));
		offenderemployments.setSealFlag("Y");

		insertList.add(offenderemployments);
		updateList.add(offenderemployments);
		deleteList.add(offenderemployments);
		final OffenderEmploymentsCommitBean offenderEmploymentsCommitBean = new OffenderEmploymentsCommitBean();
		offenderEmploymentsCommitBean.setInsertList(insertList);
		offenderEmploymentsCommitBean.setUpdateList(updateList);
		offenderEmploymentsCommitBean.setDeleteList(deleteList);
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offEmploymentsCommit", offenderEmploymentsCommitBean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(offenderEmploymentsCommitBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void vOffEmpAddrSearchVOffenderEmployAddresses() {
		try {
			final VOffenderEmployAddresses vOffenderEmployAddresses = new VOffenderEmployAddresses();
			vOffenderEmployAddresses.setAddressId(10766);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffenderEmployAddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/vOffEmpAddrExecuteQuery", vOffenderEmployAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].addressId", is(10766)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offBkgOnCheckDeleteMasteroffEducationsCur() {
		try {
			final OffenderEducations offenderEducations = new OffenderEducations();
			offenderEducations.setOffenderBookId(Long.valueOf(22110));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderEducations);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offbkgoncheckdeletemasteroffeducationscur", offenderEducations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offBkgOnCheckDeleteMasteroffEmploymentsCur() {
		try {
			final OffenderEmployments offenderEmployments = new OffenderEmployments();
			offenderEmployments.setOffenderBookId(Long.valueOf(20505));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderEmployments);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offbkgoncheckdeletemasteroffemploymentscur", offenderEmployments)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEducationsOnCheckDeleteMastervOffEduAddrCur() {
		try {
			final VOffenderEducationAddresses vOffenderEducationAddresses = new VOffenderEducationAddresses();
			vOffenderEducationAddresses.setOffenderBookId(13112);
			vOffenderEducationAddresses.setEducationSeq(1);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffenderEducationAddresses);
			ResultActions resultActions = this.mockMvc.perform(
					post("/api/ocdedemp/offeducationsoncheckdeletemastervoffeduaddrcur", vOffenderEducationAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsOnCheckDeleteMastervOffEmpAddrCur() {
		try {
			final VOffenderEmployAddresses vOffenderEmployAddresses = new VOffenderEmployAddresses();
			vOffenderEmployAddresses.setOffenderBookId(18733);
			vOffenderEmployAddresses.setEmploymentSeq(1);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffenderEmployAddresses);
			ResultActions resultActions = this.mockMvc.perform(
					post("/api/ocdedemp/offemploymentsoncheckdeletemastervoffempaddrcur", vOffenderEmployAddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsPreDeleteAddress() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setOwnerId(BigDecimal.valueOf(18733));
			addresses.setOwnerSeq(BigDecimal.valueOf(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offemploymentspredeleteaddress", addresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(4)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsPreDeletePhones() {
		try {
			final Phones phones = new Phones();
			phones.setOwnerId(BigDecimal.valueOf(12654));
			phones.setOwnerSeq(BigDecimal.valueOf(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(phones);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offemploymentspredeletephones", phones)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(2)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsPreDeleteInternetAddress() {
		try {
			final InternetAddresses internetaddresses = new InternetAddresses();
			internetaddresses.setOwnerId(BigDecimal.valueOf(20384));
			internetaddresses.setOwnerSeq(BigDecimal.valueOf(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(internetaddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offemploymentspredeleteinternetaddress", internetaddresses)
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
	public void offEducationsPreDeleteAddress() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setOwnerId(BigDecimal.valueOf(22110));
			addresses.setOwnerSeq(BigDecimal.valueOf(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offeducationspredeleteaddress", addresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(4)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEducationsPreDeletePhones() {
		try {
			final Phones phones = new Phones();
			phones.setOwnerId(BigDecimal.valueOf(10354));
			phones.setOwnerSeq(BigDecimal.valueOf(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(phones);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offeducationspredeletephones", phones)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(3)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEducationsPreDeleteInternetAddress() {
		try {
			final InternetAddresses internetaddresses = new InternetAddresses();
			internetaddresses.setOwnerId(BigDecimal.valueOf(18832));
			internetaddresses.setOwnerSeq(BigDecimal.valueOf(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(internetaddresses);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offeducationspredeleteinternetaddress", internetaddresses)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(2)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEducationsPreInsert() {
		try {
			final OffenderEducations offenderEducations = new OffenderEducations();
			offenderEducations.setOffenderBookId(Long.valueOf(22110));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderEducations);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offeducationspreinsert", offenderEducations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(4)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offEmploymentsPreInsert() {
		try {
			final OffenderEmployments offenderEmployments = new OffenderEmployments();
			offenderEmployments.setOffenderBookId(Long.valueOf(20505));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderEmployments);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdedemp/offemploymentspreinsert", offenderEmployments)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(3)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addressWhenCreateRecordgetCountryDesc() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/addresswhencreaterecordgetcountrydesc")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("United States")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addressKeyDelrecphoneEx() {
		try {
			final Phones phones = new Phones();
			phones.setOwnerId(BigDecimal.valueOf(555));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(phones);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdoapop/addresskeydelrecphoneex", phones)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].ownerId", is(555)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void preInsertaddressIdCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/ocdoapoppreinsertaddressidcur").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(13297)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void validateCityInfogetCityDescription() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/validatecityinfogetcitydescription")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(2019)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void citySystemProfilecityProfileCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/citysystemprofilecityprofilecur")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.profileValue", is("Y")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgCityRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgCityRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Edmonton")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgCountyRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgCountyRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Los Angeles County")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgCountryRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgCountryRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("United States")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgTypeRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Mailing Address")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgSpecialNeedsRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgSpecialNeedsRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Hearing Impaired Translation")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgProvStateCodeRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgProvStateCodeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("California")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgStreetDirRgroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/rgStreetDirRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("NORTH")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addressSearchAddresses() {
		try {
			final Addresses addresses = new Addresses();
			addresses.setAddressId(12550);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(addresses);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/ocdoapop/addressExecuteQuery", addresses).contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].addressId", is(12550)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void validateCityInfogetCityCode() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocdoapop/validatecityinfogetcitycode").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Permanente")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void addressCommit() {
		final Addresses addresses = new Addresses();
		final List<Addresses> insertList = new ArrayList<Addresses>();
		final List<Addresses> updateList = new ArrayList<Addresses>();
		final List<Addresses> deleteList = new ArrayList<Addresses>();
		addresses.setAddressId(13148);
		addresses.setOwnerClass("vvvvv");
		addresses.setOwnerCode("34");
		addresses.setAddressType("hjk");
		addresses.setCityCode("ghj");
		addresses.setCountryCode("23");
		addresses.setPrimaryFlag("Y");
		addresses.setMailFlag("Y");
		addresses.setCreateUserId("12354");
		addresses.setCreateDatetime(new Date());

		insertList.add(addresses);
		updateList.add(addresses);
		deleteList.add(addresses);
		final AddressesCommitBean addressesCommitBean = new AddressesCommitBean();
		addressesCommitBean.setInsertList(insertList);
		addressesCommitBean.setUpdateList(updateList);
		addressesCommitBean.setDeleteList(deleteList);
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdoapop/addressCommit", addressesCommitBean)
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(addressesCommitBean))
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));

		} catch (Exception e) {
			logger.error("", e);
		}
	}

}