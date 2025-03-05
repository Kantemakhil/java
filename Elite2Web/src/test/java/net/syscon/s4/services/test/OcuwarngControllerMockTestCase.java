package net.syscon.s4.services.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * Class OcuwarngControllerMockTestCase 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OcuwarngControllerMockTestCase  {
	

}