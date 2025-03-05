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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidreleaControllerMockTestCase 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidreleaControllerMockTestCase extends AbstractMockTestCase {

		/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderExternalMovementsExecuteQueryTestMethod() {
		try {
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(18733));
			offenderExternalMovements.setMovementSeq(Long.valueOf(6));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovements);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/offEmExecuteQuery", offenderExternalMovements)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].activeFlag", is("N")));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Test
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			systemProfiles.setProfileType("LABEL");
			systemProfiles.setProfileCode("PS_CORS_CODE");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(systemProfiles);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/sysPflExecuteQuery").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Course Acticity Code Label")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offempreinsertcTestMethod() {
		try {
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(18773));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovements);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/offempreinsertc", offenderExternalMovements)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(91)));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Test
	public void cgfkOffEmMovementReasonCoRecordGroupTestMethod() {
		try {
			this.mockMvc
					.perform(get("/api/oidrelea/cgfkOffEmMovementReasonCoRecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].movementReasonCode").value("ESCP"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgMovementReasonCodeRecordGroup() {
		try {
			this.mockMvc
					.perform(get("/api/oidrelea/rgMovementReasonCodeRecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$[0].description").value("Escape"));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void offemCommitTestMethod() {
		try {
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(22192));
			offenderExternalMovements.setMovementSeq(Long.valueOf(94));
			offenderExternalMovements.setMovementDate(new Date());
			offenderExternalMovements.setMovementTime(new Date());
			offenderExternalMovements.setInternalScheduleType(null);
			offenderExternalMovements.setInternalScheduleReasonCode(null);
			offenderExternalMovements.setMovementType("ADM");
			offenderExternalMovements.setMovementReasonCode("NEW");
			offenderExternalMovements.setDirectionCode("GG");
			offenderExternalMovements.setArrestAgencyLocId("EE");
			offenderExternalMovements.setToProvStatCode("CC");
			offenderExternalMovements.setEscortCode("WW");
			offenderExternalMovements.setFromAgyLocId("SDTC");
			offenderExternalMovements.setToAgyLocId("ITAG");
			offenderExternalMovements.setActiveFlag("Y");
			offenderExternalMovements.setEscortText("nn");
			offenderExternalMovements.setCommentText("test");
			offenderExternalMovements.setReportingDate(new Date());
			offenderExternalMovements.setToCity("BAN");
			offenderExternalMovements.setFromCity("HYD");
			offenderExternalMovements.setToCity("BAN");
			offenderExternalMovements.setReportingTime(new Date());
			offenderExternalMovements.setEventId(BigDecimal.valueOf(313131));
			offenderExternalMovements.setParentEventId(BigDecimal.valueOf(1234));
			offenderExternalMovements.setToCountryCode("AA");
			offenderExternalMovements.setOjLocationCode("VV");
			offenderExternalMovements.setApplicationDate(new Date());
			offenderExternalMovements.setApplicationTime(new Date());
			offenderExternalMovements.setToAddressId(BigDecimal.valueOf(10076));
			offenderExternalMovements.setFromAddressId(BigDecimal.valueOf(10076));
			offenderExternalMovements.setSealFlag("N");
			offenderExternalMovements.setCreateDatetime(new Date());
			offenderExternalMovements.setCreateUserId("QA");
			offenderExternalMovements.setModifyDatetime(new Date());
			offenderExternalMovements.setModifyUserId("AA");
			final List<OffenderExternalMovements> insertList = new ArrayList<OffenderExternalMovements>();
			insertList.add(offenderExternalMovements);
			final OffenderExternalMovementsCommitBean offenderExternalMovementsCommitBean = new OffenderExternalMovementsCommitBean();
			offenderExternalMovementsCommitBean.setInsertList(insertList);
		 	final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovementsCommitBean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/offEmCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void cgfkchkOffEmOffEmRefMovcTestMethod() {
		try {
			final ReferenceCodes referenceCodes = new ReferenceCodes();
			referenceCodes.setCode("DEVALIDATE");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(referenceCodes);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/cgfkchkOffEmOffEmRefMovc", referenceCodes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Test
	public void cgfkchkOffEmOffEmMoveRscTestMethod() {
		try {
			final MovementReasons movementReasons = new MovementReasons();
			movementReasons.setMovementReasonCode("NEW");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(movementReasons);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/cgfkchkOffEmOffEmMoveRsc", movementReasons)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is("1")));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Test
	public void cgwhenNewFormInstancecTestMethod() {
		try {
			final Dual dual = new Dual();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(dual);
			this.mockMvc
					.perform(post("/api/oidrelea/cgwhenNewFormInstancec", dual).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Test
	public void cgfkchkOffEmOffEmMove2cTestMethod() {
		try {
			final MovementReasons movementReasons = new MovementReasons();
			movementReasons.setMovementType("REL");
			movementReasons.setMovementReasonCode("DTH");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(movementReasons);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/cgfkchkOffEmOffEmMove2c", movementReasons)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Death")));
		} catch (Exception e) {
			logger.error(e);
		}

	}

	@Test
	public void callToShowFingerprintTestMethod() {
		try {
			final SystemProfiles systemProfiles = new SystemProfiles();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(systemProfiles);
			this.mockMvc
					.perform(get("/api/oidrelea/callToShowFingerprint").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error(e);
		}

	}
	@Test
	public void omsMovementsCheckActiveSentenceTestMethod() {
		try {
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(18733));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovements);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/omsMovementsCheckActiveSentence", offenderExternalMovements)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(2)));
		} catch (Exception e) {
			logger.error(e);
		}

	}
	@Test
	public void offExtMvntsReleaseDateCheck() {
		try {
			final OffenderExternalMovements offenderExternalMovements = new OffenderExternalMovements();
			offenderExternalMovements.setOffenderBookId(Long.valueOf(18975));
			offenderExternalMovements.setMovementReasonCode("BAIL");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(offenderExternalMovements);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrelea/offExtMvntsReleaseDateCheck", offenderExternalMovements)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].activeFlag", is("N")));
		} catch (Exception e) {
			logger.error(e);
		}

	}

}
