package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidcapprControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidcapprControllerMockTestCase extends AbstractMockTestCase{
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcapprControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void cgfkOffAss1ReviewCommitteRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidcappr/cgfkOffAss1ReviewCommitteRecordGroup?caseLoadType=INST")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("PTS_REV")));
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
	public void cgfkOffAss1ReviewPlaceAgyRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidcappr/cgfkOffAss1ReviewPlaceAgyRecordGroup?caseLoadType=INST")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("CCC")));
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
	public void cgfkOffAss1ReviewSupLevelRecordGroup() {
		try {
			final AssessmentResults bean = new AssessmentResults();
			bean.setAssessmentId(3315);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidcappr/cgfkOffAss1ReviewSupLevelRecordGroup", bean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("CONFIRM")));
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
	public void vOffassAssRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidcappr/cgfkOffAss1EvaluationResulRecordGroup")
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("REJ")));
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
	public void voffenderAssessmentsExecuteQueryTestMethod() {
		try {
			final VOffassAss obj = new VOffassAss();
			obj.setOffenderBookId(BigDecimal.valueOf(18733));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(obj);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidcappr/offAssExecuteQuery", obj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].assessStaffId", is(3004)));
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
	public void offenderAssessmentsExecuteQueryTestMethod() {
		try {
			final OffenderAssessments obj = new OffenderAssessments();
			 obj.setOffenderBookId(Long.valueOf(18733));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(obj);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidcappr/offAss1ExecuteQuery", obj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].reviewSupLevelType", is("MAX")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}