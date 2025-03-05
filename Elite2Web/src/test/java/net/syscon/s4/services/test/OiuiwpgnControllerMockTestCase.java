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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * class OiishlogControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OiuiwpgnControllerMockTestCase extends AbstractMockTestCase  {
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void iwp10gGetParametersExecuteQueryTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oiuiwpgn/paramsExecuteQuery").contentType(MediaType.APPLICATION_JSON)
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
	public void iwp10gGetParametersCommitTestMethod() {
//	try {
//		final Iwp10gGetParametersCommitBean commitBean = new Iwp10gGetParametersCommitBean();
//		final Iwp10gGetParameters list = new Iwp10gGetParameters();
//		final List<Iwp10gGetParameters> updateList = new ArrayList<Iwp10gGetParameters>();
//		updateList.add(list);
//		commitBean.setUpdateList(updateList);
//		final ObjectMapper mapper = new ObjectMapper();
//		final String jsonInString = mapper.writeValueAsString(commitBean);
// ResultActions resultActions = this.mockMvc
//		.perform(post("/api/oiuiwpgn/paramsCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
//		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
//		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//			.andDo(print());
//			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
//		} catch (Exception e) {
//			logger.error("", e);
//		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void rgStatusRecordGroupTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiuiwpgn/rgStatusRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}