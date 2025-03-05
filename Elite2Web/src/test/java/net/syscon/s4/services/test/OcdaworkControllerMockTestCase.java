package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdaworkControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcdaworkControllerMockTestCase extends AbstractMockTestCase {
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueExecuteQueryTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdaworkTeamQueueExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueCommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdaworkTeamQueueCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgReasonRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgSexRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgWorkTypeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgWorkSubTypeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgWorkflowTypeRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgPositionRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgRoleRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgTeamStaffRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void tagWorkflow.
//
//	browseQueueRecordGroupTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(get("/ocdaworkRgTeamMembersRecordGroup", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void staffMembers sm,
//	teamMembers tm, teams
//	t where tm.staffId=
//	sm.staffId and tm.teamId=
//	t.teamId and tm.activeFlag='y'
//
//	ExecuteQueryTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdaworkTeamMembersExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	/**
//	 *Fetching the record from database table
//	 *@Param searchRecord
//	*/
//	@Test
//	public void staffMembers sm,
//	teamMembers tm, teams
//	t where tm.staffId=
//	sm.staffId and tm.teamId=
//	t.teamId and tm.activeFlag='y'
//
//	CommitTestMethod() {
//	try {
// ResultActions resultActions = this.mockMvc
//		.perform(post("/ocdaworkTeamMembersCommit", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
//		}
//
//	@Configuration
//	@EnableWebMvc
//	public static class BeanConfiguration {
//		@Bean
//
//		public ocdaworkController contactController() {
//			return new ocdaworkController();
//		}
//	}
}