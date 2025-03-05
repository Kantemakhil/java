package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.services.config.EliteSpringConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OcdiplanControllerMockTestCase extends AbstractMockTestCase {

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void casePlansExecuteQueryTestMethod() {
	try {
		final Assessments tagSearchGetOffenderRecords = new Assessments();
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanCasPlnExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void casePlansCommitTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanCasPlnCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void casePlansRecordGroupTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgCaseplanAssRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanRgCaseInfoRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgCaseInfoRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanCgfkCasPlnDspDescriptionRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanCgfkCasPlnDspDescriptionRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanCgfkCasPlnDspDescription4RecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanCgfkCasPlnDspDescription4RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanCgfkCasPlnDspStaffNameRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanCgfkCasPlnDspStaffNameRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanRgCrimNeedsStsRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgCrimNeedsStsRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanRgCaseworkRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgCaseworkRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanRgPrgCategoryRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgPrgCategoryRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanRgProgramIdRecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgProgramIdRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanRgProgramId2RecordGroup() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(get("/ocdiplanRgProgramId2RecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderCriminogenicNeedsExecuteQueryTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffCriNeedsExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderCriminogenicNeedsCommitTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffCriNeedsCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offApV1ExecuteQueryTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffActionPlansV1ExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offApV1CommitTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffActionPlansV1Commit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderCaseConditionsExecuteQueryTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffCaseCondsExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderCaseConditionsCommitTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffCaseCondsCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offApV2ExecuteQueryTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffActionPlansV2ExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offApV2CommitTestMethod() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanOffActionPlansV2Commit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void ocdiplanVSummaryCasePlanExecuteQuery() {
		try {
			final Assessments tagSearchGetOffenderRecords = new Assessments();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ocdiplanVSummaryCasePlanExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}