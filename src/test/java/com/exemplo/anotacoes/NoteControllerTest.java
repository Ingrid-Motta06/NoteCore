package com.exemplo.anotacoes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarUmaAnotacaoComSucesso() throws Exception {
        String jsonPayload = """
            {
              "titulo": "Nota de Teste",
              "corpo": "Testando endpoints com JUnit",
              "categoria": "Testes",
              "autor": "QA Tester",
              "tags": ["unit", "integration"]
            }
        """;

        mockMvc.perform(post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Nota de Teste"));
    }

    @Test
    public void deveListarTodasAsAnotacoes() throws Exception {
        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk());
    }
}
