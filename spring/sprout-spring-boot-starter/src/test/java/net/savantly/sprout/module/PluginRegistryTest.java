package net.savantly.sprout.module;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import net.savantly.sprout.controllers.PluginsController;
import net.savantly.sprout.core.module.SproutModule;
import net.savantly.sprout.core.module.SproutModuleAdapter;
import net.savantly.sprout.core.module.SproutModuleConfiguration;
import net.savantly.sprout.module.PluginRegistryTest.TestContext.ExampleController;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class PluginRegistryTest {

	private static final Logger log = LoggerFactory.getLogger(PluginRegistryTest.class);

	@Autowired
	WebApplicationContext ctx;
	
	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(ctx)
				.build();
	}
	
	@Test
	public void confirmControllerBeanExists() {
		ExampleController bean = ctx.getBean(ExampleController.class);
		Assert.assertNotNull(bean);
	}
	
	@Test
	public void confirmPluginControllerBeanExists() {
		PluginsController bean = ctx.getBean(PluginsController.class);
		Assert.assertNotNull(bean);
	}
	
	@Test
	public void confirmSproutModuleBeanExists() {
		SproutModule bean = ctx.getBean(SproutModule.class);
		Assert.assertNotNull(bean);
	}
	
	@Test
	public void testPluginController() throws Exception {
		MvcResult result = mvc.perform(get("/rest/modules/example/")).andExpect(status().isOk()).andReturn();
	}
	
	
	@Configuration
	@EnableAutoConfiguration
	static class TestContext{
		
		@Bean
		public SproutModule exampleSproutModule() {
			return new ExampleModule();
		}
		
		@Bean PluginsController pluginsController(ApplicationContext context) {
			return new PluginsController(context);
		}
		
		@RestController
		@RequestMapping("/rest/modules/example")
		class ExampleController {
			@RequestMapping("/")
			public String index() {
				return "example-response";
			}
		}
		
		@SproutModuleConfiguration("example-module")
		class ExampleModule extends SproutModuleAdapter {
			
			public ExampleModule() {
				SproutModuleRegistry.registerPluginModule("exampleModule", this);
			}

			@Override
			public String name() {
				return "example";
			}

			@Override
			public String renderUrl() {
				return "example/url";
			}
			
		};
	}
}