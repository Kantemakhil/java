package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import net.syscon.s4.im.beans.OffenderLanguages;
import net.syscon.s4.im.beans.OffenderLanguagesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OcdlangsControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcdlangsControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdlangsControllerMockTestCase.class);

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offenderLanguagesExecuteQueryTestMethod() {
		try {
			final OffenderLanguages bean = new OffenderLanguages();
			bean.setOffenderBookId(Long.valueOf(21148));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdlangs/offPrimLangExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].languageType", is("PRIM")));
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
	public void offenderLanguagesCommitTestMethod() {
		try {
			final OffenderLanguages bean = new OffenderLanguages();
			// bean.setReadSkill("Y");
			// bean.setWriteSkill("Y");
			// bean.setSpeakSkill("Y");
			// bean.setCommentText("TestUpdate");
			// bean.setModifyDatetime(new Date());
			// bean.setModifyUserId("AA");
			// bean.setCreateDatetime(new Date());
			// bean.setCreateUserId("Ab");
			// bean.setNumeracySkill("Y");
			// bean.setPreferedWriteFlag("Y");
			// bean.setPreferedSpeakFlag("Y");
			// bean.setInterpreterRequestedFlag("N");
			// bean.setSealFlag(null);
			bean.setOffenderBookId(Long.valueOf(21148));
			bean.setLanguageType("Test");
			bean.setLanguageCode("ENG");
			final List<OffenderLanguages> list = new ArrayList<>();
			list.add(bean);
			final OffenderLanguagesCommitBean commitBean = new OffenderLanguagesCommitBean();
			final OffenderLanguagesCommitBean updateList = new OffenderLanguagesCommitBean();
			updateList.setDeleteList(list);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(updateList);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocdlangs/offPrimLangCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}