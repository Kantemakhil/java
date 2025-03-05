package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
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

import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OcuverifControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcuverifControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void workFlowLogsExecuteQueryTestMethod() {
		try {
			final OffenderAlerts bean = new OffenderAlerts();
			bean.setOffenderBookId(new Long(22112));
			bean.setAlertSeq(new Long(1));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuverif/workFlExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	// @Test
	// public void workFlowLogsRecordGroupTestMethod() {
	// try {
	// ResultActions resultActions = this.mockMvc
	// .perform(get("/api/ocuverif/navigationDummyRecordGroup").contentType(MediaType.APPLICATION_JSON)
	// .accept(MediaType.APPLICATION_JSON_UTF8))
	// .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	// .andDo(print());
	// resultActions.andExpect(jsonPath("$[0].name", is("Name")));
	// } catch (Exception e) {
	// logger.error("", e);
	// }
	// }

}