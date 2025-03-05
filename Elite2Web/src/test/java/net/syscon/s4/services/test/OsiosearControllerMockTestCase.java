
package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VHeaderBlock2;
import net.syscon.s4.im.beans.OffenderProfileDetails;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.TagSearchGetOffenderIdentifiers;
import net.syscon.s4.im.beans.TagSearchGetOffenderRecords;
import net.syscon.s4.im.beans.TagSearchPopulateOffDetails;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OsiosearControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OsiosearControllerMockTestCase extends AbstractMockTestCase {

	@Test
	public void rearchResultsExecuteQueryTestMethod() {
		try {
			final TagSearchGetOffenderRecords tagSearchGetOffenderRecords = new TagSearchGetOffenderRecords();
			tagSearchGetOffenderRecords.setpLastName("SMITH");
			tagSearchGetOffenderRecords.setpFirstName("");
			tagSearchGetOffenderRecords.setpMiddleName("");
			tagSearchGetOffenderRecords.setpIdentifierType("");
			tagSearchGetOffenderRecords.setpIdentifierValue("");
			tagSearchGetOffenderRecords.setOffenderIdDisplay(null);
			tagSearchGetOffenderRecords.setOffenderId("");
			tagSearchGetOffenderRecords.setRootOffenderId("");
			tagSearchGetOffenderRecords.setpSexCode("");
			tagSearchGetOffenderRecords.setpSearchType("N");
			tagSearchGetOffenderRecords.setpSwitchNames("N");
			tagSearchGetOffenderRecords.setpBirthDate(null);
			tagSearchGetOffenderRecords.setpBirthRange(BigDecimal.ZERO);
			tagSearchGetOffenderRecords.setpBirthYear("");
			tagSearchGetOffenderRecords.setpAgedate(null);
			tagSearchGetOffenderRecords.setpAgeRange(null);
			tagSearchGetOffenderRecords.setpEthnicity(null);
			tagSearchGetOffenderRecords.setpNameVariation("N");
			tagSearchGetOffenderRecords.setNbtBkgNo(null);
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/searchResultsExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].pLastName", is("SMITH")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgSearchTypeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/rgSearchTypeRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgIdentifierTypeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/rgIdentifierTypeRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Social Security Number")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgGenderRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/rgGenderRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Male")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgCrtLocationRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/rgCrtLocationRecordGroup?caseloadId=CTAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].id", is("0")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offIdExecuteQueryTestMethod() {
		try {
			final TagSearchGetOffenderIdentifiers tagSearchGetOffenderIdentifiers = new TagSearchGetOffenderIdentifiers();
			tagSearchGetOffenderIdentifiers.setOffenderId(1019022);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderIdentifiers);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/offIdExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1019022)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void imageExecuteQueryTestMethod() {
		try {
			final Images images = new Images();
			images.setImageObjectId(new BigDecimal(18773));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(images);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/imageExecuteQuery").content(jsonInString)
							.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].imageId", is(8489)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void psOffNameExecuteQueryTestMethod() {
		try {
			final TagSearchGetOffenderRecords tagSearchGetOffenderRecords = new TagSearchGetOffenderRecords();
			tagSearchGetOffenderRecords.setpSearchType("P");
			tagSearchGetOffenderRecords.setpLastName("SMITH");
			tagSearchGetOffenderRecords.setpFirstName("");
			tagSearchGetOffenderRecords.setpMiddleName("");
			tagSearchGetOffenderRecords.setpSexCode("");
			tagSearchGetOffenderRecords.setpBirthDate(null);
			tagSearchGetOffenderRecords.setpBirthYear("");
			tagSearchGetOffenderRecords.setpBirthRange(null);
			tagSearchGetOffenderRecords.setpAgedate(null);
			tagSearchGetOffenderRecords.setpAgeRange(null);
			tagSearchGetOffenderRecords.setpEthnicity("");
			tagSearchGetOffenderRecords.setpSwitchNames("N");
			tagSearchGetOffenderRecords.setpNameVariation("N");

			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/psOffNameExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].lastName", is("SMITH")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void searchResultsPostQuerynameTypeCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/searchResultsPostQuerynameTypeCur?offenderId=1019064")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.aliasNameType", is("Working Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void searchResultsWhenNewRecordInstancecOffFingerPrintsTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/searchResultsWhenNewRecordInstancecOffFingerPrints?const0=2577")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].fingerCode", is("finget_code")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void searchResultsWhennewItemInstanceLvobiTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/searchResultsWhenNewItemInstanceLvobi?offenderId=1019090")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(18815)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void captureImageFindImagingFormTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/captureImageFindImagingForm").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].profileValue", is("OIUIMAGE")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void captureImageFindImageCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/captureImageFindImageCur?imageObjectId=1101&imageObjectType=STG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void captureImageFindMarkImageCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/osiosear/captureImageFindMarkImageCur?imageObjectId=18773&imageObjectType=OFFIDM&imageObjectSeq=1")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void createFormGlobalsTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/createFormGlobals?const0=ATMONTRN")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Auto Money Transfer Batch Process")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void populateOffDetailsBlocknameTypeCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/populateOffDetailsBlocknameTypeCur?offenderId=1019951")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$.raceCode", is("Caucasian")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void populateOffDetailsBlockattrCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/populateOffDetailsBlockattrCur?offenderBookId=22353")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].heightCm", is("168")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void setInitialSearchTypeSearchCurTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/setInitialSearchTypeSearchCur").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void getLatestBookingTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/getLatestBooking?rootOffenderId=1019061")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(18840)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void profTypeDescpcTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/profTypeDescpc?profileType=CITIZEN")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Citizenship")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void profCodeDescpTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/profCodeDescp?profileType=SYMBOLISM")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[]", is(IsNull.nullValue())));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void profCodeDescpdTestMethod() {
		try {
			final ProfileCodes profileCodes = new ProfileCodes();
			profileCodes.setProfileCode("BLU");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(profileCodes);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/profCodeDescpd").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Ukraine")));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void getOffenderBookidGetBookidTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/osiosear/getOffenderBookIdGetbookId?rootOffenderId=1019061&rootOffenderIdd=1019061")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(18840)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void callToShowFingerprintOldTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/callToShowFingerprintOld?const0=0")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void populateOffDetailsTestMethod() {
		try {
			final TagSearchPopulateOffDetails tagSearchPopulateOffDetails = new TagSearchPopulateOffDetails();
			tagSearchPopulateOffDetails.setPRootOffenderId(BigDecimal.valueOf(1019061));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchPopulateOffDetails);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/osiosear/populateOffDetails?pRootOffenderId=1019061")
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offbkgGlobalQueryTestMethod() {
		try {
			final VHeaderBlock vHeaderBlock = new VHeaderBlock();
			vHeaderBlock.setOffenderBookId(BigDecimal.valueOf(1019061));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vHeaderBlock);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/offbkgGlobalQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("TRN")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offProfDtlsExecuteQueryTestMethod() {
		try {
			final OffenderProfileDetails offenderProfileDetails = new OffenderProfileDetails();
			offenderProfileDetails.setOffenderBookId(Long.valueOf(18808));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderProfileDetails);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/offProfDtlsExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].profileCode", is("HVY")));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	@Test
	public void offbkgExecuteQueryTestMethod() {
		try {
			final VHeaderBlock2 vHeaderBlock = new VHeaderBlock2();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vHeaderBlock);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/osiosear/offbkgExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("TRN")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}