package br.com.squadjoaquina.errorlogger.integration;

import br.com.squadjoaquina.errorlogger.ErrorLoggerApplication;
import lombok.NoArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ErrorLoggerApplication.class})
@TestPropertySource(locations = "classpath:application.properties")
@WebAppConfiguration
@NoArgsConstructor
@Sql({"classpath:test_data.sql"})
public abstract class EndpointTest {
    protected MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(
                this.webApplicationContext)
                                      .build();
    }

    protected MockHttpServletResponse performRequest(
            MockHttpServletRequestBuilder request)
            throws Exception {
        return this.mockMvc.perform(request)
                           .andReturn()
                           .getResponse();

    }


}







