package br.com.squadjoaquina.errorlogger.integration;

import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.utils.JsonUtils;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
public class GetErrorEndpointTest extends EndpointTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void whenRequestIsCorrectAndErrorExists() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/v1/error/1", new Object[0])
                                      .accept("application/json");

        MockHttpServletResponse receivedResponse = performRequest(request);

        Assert.assertEquals(200, receivedResponse.getStatus());

        ErrorDTO errorDTO = JsonUtils.toObject(
                receivedResponse.getContentAsString(),
                ErrorDTO.class);

        Assert.assertEquals(java.util.Optional.of(1L),
                            java.util.Optional.of(errorDTO.getId()));
        Assert.assertNotNull(errorDTO.getArchivedAt());
        Assert.assertNotNull(errorDTO.getCreatedAt());
        Assert.assertEquals("NULL POINTER", errorDTO.getDescription());
        Assert.assertEquals("NULL POINTER", errorDTO.getTitle());
        Assert.assertEquals("127.0.0.1", errorDTO.getOrigin());

        Assert.assertEquals(java.util.Optional.of(1L),
                            java.util.Optional.of(errorDTO.getUser().getId()));
        Assert.assertEquals(Level.ERROR, errorDTO.getLevel());
        Assert.assertEquals(Environment.PRODUCTION, errorDTO.getEnvironment());
    }

    @Test
    @Transactional
    public void whenRequestIsCorrectAndErrorDoesNotExist() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/v1/error/100000",
                                           new Object[0])
                                      .accept("application/json");

        MockHttpServletResponse receivedResponse = performRequest(request);

        Assert.assertEquals(404, receivedResponse.getStatus());
    }

    @Test
    @Transactional
    public void whenRequestIsIncorrect() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/v1/error/abc",
                                           new Object[0])
                                      .accept("application/json");

        MockHttpServletResponse receivedResponse = performRequest(request);

        Assert.assertEquals(400, receivedResponse.getStatus());
    }


}
