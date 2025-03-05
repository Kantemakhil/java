package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 *Class OidrpitmControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidrpitmControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrpitmControllerMockTestCase.class);


	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPptyItemsExecuteQueryTestMethod() {
		try {
			final OffenderPptyItems bean = new OffenderPptyItems();
			bean.setOffenderBookId(19844);
			bean.setPropertyItemSeq(4);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrpitm/offPiExecuteQuery", bean)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].propertyType ", is("ELEC")));
		} catch (Exception e) {
			logger.error(e);
		}
	}
	 /**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@Test
	public void offenderPptyItemsCommitTestMethod() {
		try {
			final OffenderPptyItems obj = new OffenderPptyItems();
			obj.setOffenderBookId(19844);
			obj.setPropertyItemSeq(4);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(obj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrpitm/offPiCommit", obj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 
	 * 
	 * /** Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderPptyItemsRecordGroupTestMethod() {
		try {
			final OffenderPptyItems list = new OffenderPptyItems();
			list.setOffenderBookId(19844);
			list.setPropertyItemSeq(4);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(list);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/oidrpitmCgfkOffPiPropertyTypeRecordGroup", list)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(e);
		}
	}
	 /**
	 *Fetching the record from database table
	 *@Param searchRecord
	 */
	@Test
	public void systemProfilesExecuteQueryTestMethod() {
		try {
			final OffenderPptyItems beanObj = new OffenderPptyItems();
			beanObj.setOffenderBookId(19844);
			beanObj.setPropertyItemSeq(4);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(beanObj);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/oidrpitmSysPflExecuteQuery", beanObj)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	 
}