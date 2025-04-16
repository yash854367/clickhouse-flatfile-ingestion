package com.ingestion.clickhouseflatfile;

import com.ingestion.clickhouseflatfile.config.ClickHouseConnector;
import com.ingestion.clickhouseflatfile.model.ClickHouseConfig;
import com.ingestion.clickhouseflatfile.model.IngestionResult;
import com.ingestion.clickhouseflatfile.service.IngestionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngestionServiceTest {

    private IngestionService ingestionService;
    private ClickHouseConfig config;

    @BeforeEach
    void setUp() {
        ingestionService = new IngestionService();
        config = new ClickHouseConfig();
        config.setHost("localhost");
        config.setPort(8123);
        config.setDatabase("default");
        config.setUser("default");
        config.setJwtToken("fake_token");
    }

    @Test
    void testConnection_successful() throws SQLException {
        Connection mockConnection = mock(Connection.class);

        try (MockedStatic<ClickHouseConnector> mocked = mockStatic(ClickHouseConnector.class)) {
            mocked.when(() -> ClickHouseConnector.getConnection(any(), anyInt(), any(), any(), any()))
                  .thenReturn(mockConnection);

            IngestionResult result = ingestionService.testConnection(config);

            assertEquals("success", result.getStatus());
            assertEquals("Connection successful.", result.getMessage());
        }
    }

    @Test
    void testConnection_failure() throws SQLException {
        try (MockedStatic<ClickHouseConnector> mocked = mockStatic(ClickHouseConnector.class)) {
            mocked.when(() -> ClickHouseConnector.getConnection(any(), anyInt(), any(), any(), any()))
                  .thenThrow(new SQLException("Invalid credentials"));

            IngestionResult result = ingestionService.testConnection(config);

            assertEquals("error", result.getStatus());
            assertTrue(result.getMessage().contains("Connection failed"));
        }
    }

    @Test
    void testIngestDummyData_success() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        try (MockedStatic<ClickHouseConnector> mocked = mockStatic(ClickHouseConnector.class)) {
            mocked.when(() -> ClickHouseConnector.getConnection(any(), anyInt(), any(), any(), any()))
                  .thenReturn(mockConnection);

            IngestionResult result = ingestionService.ingestDummyData(config);

            assertEquals("success", result.getStatus());
            assertEquals(1, result.getTotalRecords());
            assertEquals("Inserted 1 rows.", result.getMessage());
        }
    }

    @Test
    void testIngestDummyData_failure() throws SQLException {
        try (MockedStatic<ClickHouseConnector> mocked = mockStatic(ClickHouseConnector.class)) {
            mocked.when(() -> ClickHouseConnector.getConnection(any(), anyInt(), any(), any(), any()))
                  .thenThrow(new SQLException("Table not found"));

            IngestionResult result = ingestionService.ingestDummyData(config);

            assertEquals("error", result.getStatus());
            assertTrue(result.getMessage().contains("Error"));
        }
    }
}
