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

import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.services.config.EliteSpringConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OiicmociControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vCourtEventsExecuteQueryTestMethod() {
		try {
			VCourtEvents tagSearchGetOffenderRecords = new VCourtEvents();
			tagSearchGetOffenderRecords.setCourtEventType("SCH");
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tagSearchGetOffenderRecords);
			ResultActions resultActions = this.mockMvc
					.perform(post("/oiicmociOffSchExecuteQuery", tagSearchGetOffenderRecords)
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("Name")));
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
	public void vCourtEventsRecordGroup_TestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/oiicmoci/RgAgyLocIdRecordGroup?caseLoadId=ITAG").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Name")));
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
	public void vCourtEventrgLu1RecordGroup_TestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/oiicmoci/rgLu1RecordGroup?agyLocId=itag").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("Name")));
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
	public void vCourtEventsrgLu2RecordGroup_TestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/oiicmoci/rgLu2RecordGroup?agyLocId=ITAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("Name")));
		} catch (Exception e) {

		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vCourtEventsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/oiicmoci/rgLu3RecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}