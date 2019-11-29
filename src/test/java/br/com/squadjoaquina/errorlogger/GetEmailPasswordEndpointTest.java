package br.com.squadjoaquina.errorlogger;

import br.com.squadjoaquina.errorlogger.dto.EmailDTO;
import br.com.squadjoaquina.errorlogger.dto.ErrorDTO;
import br.com.squadjoaquina.errorlogger.dto.UserDTO;
import br.com.squadjoaquina.errorlogger.integration.EndpointTest;
import br.com.squadjoaquina.errorlogger.model.Environment;
import br.com.squadjoaquina.errorlogger.model.Level;
import br.com.squadjoaquina.errorlogger.model.User;
import br.com.squadjoaquina.errorlogger.utils.JsonUtils;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
public class GetEmailPasswordEndpointTest extends EndpointTest {

    @Value("${spring.mail.password}")
    private String gmailPasswd;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void whenRequestIsIncorrect() throws Exception {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail("123123@abcabc.com");
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.post("/auth/forgot", new Object[0])
                        .accept("application/json")
                        .contentType(
                                MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonUtils.toJsonIgnoreNullFields(
                                emailDTO));

        MockHttpServletResponse receivedResponse = performRequest(request);
        Assert.assertEquals(404, receivedResponse.getStatus());
    }

    @Test
    @Transactional
    public void whenRequestIsCorrect() throws Exception {
        // Caso a senha de envio de email esteja oculta, ignora o teste
        if (!gmailPasswd.isEmpty()) {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setEmail("teste@testemail.com");
            MockHttpServletRequestBuilder request =
                    MockMvcRequestBuilders.post("/auth/forgot", new Object[0])
                            .accept("application/json")
                            .contentType(
                                    MediaType.APPLICATION_JSON_VALUE)
                            .content(JsonUtils.toJsonIgnoreNullFields(
                                    emailDTO));
            MockHttpServletResponse receivedResponse = performRequest(request);
            Assert.assertEquals(204, receivedResponse.getStatus());
        }
    }


}
