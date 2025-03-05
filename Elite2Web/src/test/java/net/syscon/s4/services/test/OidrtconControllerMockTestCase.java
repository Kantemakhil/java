package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * Class OidrtconControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidrtconControllerMockTestCase extends AbstractMockTestCase {

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderPptyContainersExecuteQueryTestMethod() {
	try {
		OffenderPptyContainers bean = new OffenderPptyContainers();
		bean.setPropertyContainerId(3189);
		String jsonInString = new ObjectMapper().writeValueAsString(bean);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidrtcon/offConExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(18773)));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void offenderPptyContainersCommitTestMethod() {
	try {
		OffenderPptyContainersCommitBean commitBean = new OffenderPptyContainersCommitBean();
		OffenderPptyContainers bean = new OffenderPptyContainers();
		bean.setPropertyContainerId(3189);
		bean.setCreateDateTime(new Date());
		bean.setPropertyOnlyFlag("Y");
		List<OffenderPptyContainers> listBean = new ArrayList<OffenderPptyContainers>();
		listBean.add(bean);
		commitBean.setUpdateList(listBean);
		String jsonInString = new ObjectMapper().writeValueAsString(commitBean);
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidrtcon/offConCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}