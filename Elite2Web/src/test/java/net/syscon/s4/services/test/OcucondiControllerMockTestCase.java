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
public class OcucondiControllerMockTestCase extends AbstractMockTestCase{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucondiControllerMockTestCase.class);
	
	@Test
	public void  populateCategory(){
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucondi/getCategory").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("SENT")));
		} catch (Exception e) {
			logger.error("", e);
		}		
	}
	
	@Test
	public void fetchOrderTypeDesc(){
		
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucondi/fetchOrderTypeDesc").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("COND")));
		} catch (Exception e) {
			logger.error("", e);
		}	
	}
	
	@Test
	public void  populateUnit(){
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucondi/getUnit").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("WEEKS")));
		} catch (Exception e) {
			logger.error("", e);
		}	
	}
	
	@Test
	public void populateProgram(){
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucondi/getProgram").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("ANG")));
		} catch (Exception e) {
			logger.error("", e);
		}	
	}
	
	@Test
	public void getConditionTypeGrid(){
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucondi/getConditionTypeGrid").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Sentence")));
		} catch (Exception e) {
			logger.error("", e);
		}	
	}
	
	public void getConditionGrid(){
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocucondi/getConditionGrid").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("COMM")));
		} catch (Exception e) {
			logger.error("", e);
		}	
	}
}
