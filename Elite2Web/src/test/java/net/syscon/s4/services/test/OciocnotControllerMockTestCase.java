package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import net.syscon.s4.inst.casemanagement.beans.VOffenderCaseNotes;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OciocnotControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciocnotControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgTypeRecordGroupTest() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ociocnot/rgTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("ALERT")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgSubTypeRecordGroupTest() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ociocnot/rgSubTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("ADM")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void rgLocationRecordGroupTest() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ociocnot/rgLocationRecordGroup?caseLoadId=ITAG").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("123")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	@Test
	public void oichearExecuteQueryTestMethod() {
		try {
			final VOffenderCaseNotes vOffenderCaseNotes = new VOffenderCaseNotes();
			vOffenderCaseNotes.setStaffId(1);
			vOffenderCaseNotes.setContactDate(new Date(24/12/2017));
			vOffenderCaseNotes.setDateCreation(new Date(27/12/2017));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(vOffenderCaseNotes);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ociocnot/caseNoteExecuteQuery", vOffenderCaseNotes)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicHearingType", is("DISC")));

		} catch (Exception e) {
			logger.error("", e);
		}
	}
}