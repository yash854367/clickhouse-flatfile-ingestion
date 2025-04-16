package com.ingestion.clickhouseflatfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingestion.clickhouseflatfile.controller.IngestionController;
import com.ingestion.clickhouseflatfile.model.ClickHouseConfig;
import com.ingestion.clickhouseflatfile.model.IngestionResult;
import com.ingestion.clickhouseflatfile.service.IngestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IngestionController.class)
public class IngestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngestionService ingestionService;

    private ClickHouseConfig config;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        config = new ClickHouseConfig();
        config.setHost("localhost");
        config.setPort(8123);
        config.setDatabase("default");
        config.setUser("default");
        config.setJwtToken("token");
    }

    @Test
    void testIngestToClickHouse_success() throws Exception {
        IngestionResult result = new IngestionResult(1, "success", "Inserted 1 rows.");

        when(ingestionService.ingestDummyData(any())).thenReturn(result);

        mockMvc.perform(post("/api/ingestion/clickhouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Inserted 1 rows."));
    }

    @Test
    void testTestConnection_success() throws Exception {
        IngestionResult result = new IngestionResult(0, "success", "Connection successful.");

        when(ingestionService.testConnection(any())).thenReturn(result);

        mockMvc.perform(post("/api/ingestion/clickhouse/connect")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Connection successful."));
    }
}
