package my.mycompany.myapp.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:root-context.xml",
		"classpath*:servlet-context.xml", "classpath*:datasource.xml" })
@TransactionConfiguration
@Transactional
public class ProductControllerTest {
private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}
	
	@Test
	public void testAddProduct() {
		try {
			this.mockMvc.perform(
					post("/products", "json")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"id\":1,\"name\":\"Lamp@@@@\",\"price\":5.78,\"description\":null}")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
