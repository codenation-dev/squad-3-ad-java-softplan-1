package br.com.squadjoaquina.errorlogger.integration;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.utils.JsonUtils;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
public class SaveUserEndpointTest extends EndpointTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void whenRequestIsCorrect() throws Exception {

        User user = new User();
        user.setEmail("a@a.com");
        user.setName("a@a.com");
        user.setPassword("a");

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/user", new Object[0])
                                      .accept("application/json")
                                      .contentType(
                                              MediaType.APPLICATION_JSON_VALUE)
                                      .content(JsonUtils.toJsonIgnoreNullFields(
                                              user));

        MockHttpServletResponse receivedResponse = performRequest(request);

        String content = receivedResponse.getContentAsString();
        Assert.assertEquals(201, receivedResponse.getStatus());

        UserDTO receivedDTO = JsonUtils.toObject(content, UserDTO.class);

        Assert.assertEquals(user.getEmail(), receivedDTO.getEmail());
        Assert.assertEquals(user.getName(), receivedDTO.getName());
        Assert.assertNull(receivedDTO.getPassword());
        Assert.assertNotNull(receivedDTO.getCreatedAt());
    }

    @Test
    @Transactional
    public void whenRequestIsIncorrect() throws Exception {

        User user = new User();
        user.setEmail("a@a.com");
        user.setName("a@a.com");

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/user", new Object[0])
                                      .accept("application/json")
                                      .contentType(
                                              MediaType.APPLICATION_JSON_VALUE)
                                      .content(JsonUtils.toJsonIgnoreNullFields(
                                              user));

        MockHttpServletResponse receivedResponse = performRequest(request);

        String content = receivedResponse.getContentAsString();
        Assert.assertEquals(400, receivedResponse.getStatus());
    }
}