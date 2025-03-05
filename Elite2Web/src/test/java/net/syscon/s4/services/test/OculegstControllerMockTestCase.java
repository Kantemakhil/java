package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OculegstControllerMockTestCase extends AbstractMockTestCase{
	
	private static Logger logger = LogManager.getLogger(OculegstControllerMockTestCase.class);
	
	@Test
	public void getUpdateCaseReason() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocdlegst/reasonUpdateStatus").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Active")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void populateUpdateReason() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocdlegst/populateUpdateReason").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Active")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void getUpdateConditionReason() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocdlegst/reasonUpdateStatus").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Active")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void getUpdateUser() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/ocdlegst/getUpdateUser").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("DOLAN,DAVE")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
}
