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

import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OsiosearControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OciiplanControllerMockTestCase  extends AbstractMockTestCase {
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void casePlansExecuteQueryTestMethod() {
	try {
		final CasePlans tagSearchGetOffenderRecords = new CasePlans();
		final ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
 ResultActions resultActions = this.mockMvc
		.perform(post("/ociiplanCasePlansExecuteQuery", tagSearchGetOffenderRecords).contentType(MediaType.APPLICATION_JSON)
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
	public void ociiplanRgInstAgyLocRecordGroup() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/ociiplanRgInstAgyLocRecordGroup").contentType(MediaType.APPLICATION_JSON).
		accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void ociiplanRgVerifiedFilterRecordGroup() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/ociiplanRgVerifiedFilterRecordGroup").contentType(MediaType.APPLICATION_JSON)
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
	public void casePlansRecordGroupTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/ociiplanComInstAgyLocRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	@Test
	public void ociiplanTagMainGetOffender() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ociiplan/ociiplanTagMainGetOffender?caseLoadId=ITAG&caseLoadType=null&offenderIdDisplay=0001019263")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(true)));
		} catch (Exception e) {
			logger.error("ociiplanTagMainGetOffender", e);
		}
	}

}