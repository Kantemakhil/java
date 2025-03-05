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

import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.OffenderProfileDetailsCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.services.config.EliteSpringConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidpinfoControllerMockTestCase extends AbstractMockTestCase {
	

	@Test
	public void offendersExecuteQueryTestMethod() {
		try {

			final Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(1019650));
			final String jsonInString = new ObjectMapper().writeValueAsString(offenders);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/offNameExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].birthPlace", is("Sacramento")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersCommitTestMethod() {
		try {
			final Offenders offenders = new Offenders();
			offenders.setOffenderId(Long.valueOf(1019061));
			offenders.setOffenderNameSeq(BigDecimal.valueOf(0));
			offenders.setIdSourceCode("ARKIN");
			offenders.setLastName("TEST LAST_LAST");
			offenders.setNameType("");
			offenders.setFirstName("");
			offenders.setMiddleName("");
			offenders.setBirthDate(new Date(0, 0, 0, 0, 0, 0));
			offenders.setCreateDateTime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenders.setCreateUserId("OMS_OWNER");
			offenders.setModifyDateTime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenders.setModifyUserId("ADMINQA");
			offenders.setModifyDateTime(null);
			offenders.setSexCode("M");
			offenders.setSuffix("");
			offenders.setLastNameSoundex("");
			offenders.setBirthPlace("");
			offenders.setBirthCountryCode("");
			offenders.setCreateDate(new Date(0, 0, 0, 0, 0, 0));
			offenders.setLastNameKey("ROBSON_LAST");
			offenders.setAliasOffenderId(null);
			offenders.setFirstNameKey("");
			offenders.setMiddleNameKey("");
			offenders.setOffenderIdDisplay("");
			offenders.setRootOffenderId(BigDecimal.valueOf(0));
			offenders.setCaseloadType("");
			offenders.setAliasNameType("");
			offenders.setParentOffenderId(BigDecimal.valueOf(0));
			offenders.setUniqueObligationFlag(null);
			offenders.setSuspendedFlag(null);
			offenders.setSuspendedDate(null);
			offenders.setRaceCode("");
			offenders.setRemarkCode(null);
			offenders.setAddInfoCode("");
			offenders.setBirthCounty("");
			offenders.setBirthState(null);
			offenders.setMiddleName2(null);
			offenders.setTitle(null);
			offenders.setAge(BigDecimal.valueOf(0));
			offenders.setLastNameAlphaKey(null);
			offenders.setNameSequence("");
			offenders.setSealFlag("");
			final List<Offenders> updateList = new ArrayList<Offenders>();
			updateList.add(offenders);
			final OffendersCommitBean offendersCommitBean = new OffendersCommitBean();
			offendersCommitBean.setUpdateList(updateList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offendersCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/offNameCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGroupTestMethod() {

		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpinfo/cgfkOffNameDspDescriptionRecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("United States")));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	
	public void offendersRecordGroupTestMethods() {
		try {
			final ProfileCodes profileCodes = new ProfileCodes();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(profileCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpinfo/cgfkOffPdDspDescriptionRecordGroup?profileType=DIET")
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].profileType", is("CITIZEN")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offendersRecordGroupTestMethodes() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpinfo/rgBirthStateRecordGroup").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("California")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offenderProfileDetailsExecuteQueryTestMethod() {

		try {
			final OffenderProfileDetails offenderProfileDetails = new OffenderProfileDetails();
			offenderProfileDetails.setOffenderBookId(Long.valueOf(18773));
			final String jsonInString = new ObjectMapper().writeValueAsString(offenderProfileDetails);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/offPdExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].caseloadType", is("INST")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offenderProfileDetailsCommitTestMethod() {
		try {

			final OffenderProfileDetails offenderProfileDetails = new OffenderProfileDetails();
			offenderProfileDetails.setOffenderBookId(Long.valueOf(21807));
			offenderProfileDetails.setProfileSeq(Long.valueOf(1));
			offenderProfileDetails.setProfileType("TEST_OH");
			offenderProfileDetails.setProfileCode(null);
			offenderProfileDetails.setListSeq(BigDecimal.valueOf(99));
			offenderProfileDetails.setCommentText(null);
			offenderProfileDetails.setCaseloadType("INST");
			offenderProfileDetails.setModifyUserId(null);
			offenderProfileDetails.setModifyDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderProfileDetails.setCreateDatetime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			offenderProfileDetails.setCreateUserId("ADMINQA");
			offenderProfileDetails.setSealFlag(null);
			final List<OffenderProfileDetails> updateList = new ArrayList<OffenderProfileDetails>();
			updateList.add(offenderProfileDetails);
			final OffenderProfileDetailsCommitBean offenderProfileDetailsCommitBean = new OffenderProfileDetailsCommitBean();
			offenderProfileDetailsCommitBean.setUpdateList(updateList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderProfileDetailsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/offPdCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oidpinfoOffbkgoncheckdeletemasteroffpdcur() {
		try {
			final OffenderProfileDetails offenderProfileDetails = new OffenderProfileDetails();
			offenderProfileDetails.setOffenderBookId(Long.valueOf(18773));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderProfileDetails);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/offBkgOnCheckDeleteMasteroffPdCur")
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oidpinfoOffnamepostquerycoffbirthstate() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode("AL");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/oidpinfo/offNamePostQuerycOffBirthState").contentType(MediaType.APPLICATION_JSON)
									.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.description", is("Alabama")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oidpinfoProfilecodepostchange() {
		try {
			final ProfileTypes profileTypes = new ProfileTypes();
			profileTypes.setProfileType("DNA");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(profileTypes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/profileCodePostChange").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.lvTemp", is("CODE")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oidpinfoCgwhenNewFormInstancecTestMethod() {

		try {
			final Dual dual = new Dual();
			final ObjectMapper mapper = new ObjectMapper();

			final String jsonInString = mapper.writeValueAsString(dual);

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidpinfo/cgWhenNewFormInstancec").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].user", is("OMS_OWNER")));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	@Test
	public void oidpinfoDspdescriptionwhenvalidateitemprofiletypes() {
		try {
			final ProfileTypes profileTypes = new ProfileTypes();
			profileTypes.setProfileType("CITIZEN");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(profileTypes);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidpinfo/dspDescriptionWhenValidateItemprofileTypes")
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.codeValueType", is("CODE")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	
}