package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatusesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
* class OidistatControllerMockTestCase
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidistatControllerMockTestCase extends AbstractMockTestCase {
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderImprisonStatusesExecuteQueryTestMethod() {
	try {
		final OffenderImprisonStatuses offimpsModel = new OffenderImprisonStatuses();
		final ObjectMapper mapper = new ObjectMapper();
        offimpsModel.setOffenderBookId(new Long(22408));
        final String jsonInString = mapper.writeValueAsString(offimpsModel);
		ResultActions resultActions = this.mockMvc
				.perform(post("/api/oidistat/offImpsExecuteQuery", offimpsModel)
						.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
						.accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderImprisonStatusesCommitTestMethod() {
	try {
		final OffenderImprisonStatusesCommitBean commitBean = new OffenderImprisonStatusesCommitBean();
		final OffenderImprisonStatuses list = new OffenderImprisonStatuses();
		list.setGlobalCaseloadId("ITAG");
		list.setOffenderBookId(new Long(22408));
		list.setEffectiveDate(new Date());
		list.setEffectiveTime(new Date());
		list.setImprisonmentStatus("FED");
		list.setAgyLocId("ITAG");
		list.setCommentText("Testing Mock");
		list.setErrorFlag("N");
		final List<OffenderImprisonStatuses> insertList = new ArrayList<OffenderImprisonStatuses>();
		insertList.add(list);
		commitBean.setInsertList(insertList);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(commitBean);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidistat/offImpsCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
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
	public void rgOffenderImprisonStatusesRecordGroupTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidistat/rgImprisonmentStaRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void offenderImprisonStatusesRgAgyLocIdRecordGroupTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidistat/rgAgyLocIdRecordGroup?caseloadId = 'ITAG' ").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}