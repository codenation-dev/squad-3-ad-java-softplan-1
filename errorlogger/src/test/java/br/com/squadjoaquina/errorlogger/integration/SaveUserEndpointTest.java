package br.com.squadjoaquina.errorlogger.integration;

import br.com.squadjoaquina.errorlogger.dto.UserDTO;
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
//@Sql({"/resources/pre-sql.sql" })
public class SaveUserEndpointTest extends EndpointTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void whenRequestIsCorrect() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("a@a.com");
        userDTO.setLogin("a@a.com");
        userDTO.setPassword("a");

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/user", new Object[0])
                                      .accept("application/json")
                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                      .content(JsonUtils.toJsonIgnoreNullFields(
                                              userDTO));

        MockHttpServletResponse receivedResponse =
                this.mockMvc.perform(request)
                            .andReturn()
                            .getResponse();

        System.out.println(receivedResponse.getHeaderNames());
        String content = receivedResponse.getContentAsString();
        Assert.assertEquals(200, receivedResponse.getStatus());

        UserDTO receivedDTO = JsonUtils.toObject(content, UserDTO.class);

        Assert.assertEquals(userDTO.getEmail(), receivedDTO.getEmail());
        Assert.assertEquals(userDTO.getLogin(), receivedDTO.getLogin());
        Assert.assertNull(receivedDTO.getPassword());
    }
}