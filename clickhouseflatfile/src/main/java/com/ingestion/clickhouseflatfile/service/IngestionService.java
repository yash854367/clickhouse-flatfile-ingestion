package com.ingestion.clickhouseflatfile.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import com.ingestion.clickhouseflatfile.config.ClickHouseConnector;
import com.ingestion.clickhouseflatfile.model.ClickHouseConfig;
import com.ingestion.clickhouseflatfile.model.IngestionResult;

@Service
public class IngestionService {

    public IngestionResult ingestDummyData(ClickHouseConfig config) {
        try (Connection conn = ClickHouseConnector.getConnection(
                config.getHost(),
                config.getPort(),
                config.getDatabase(),
                config.getUser(),
                config.getJwtToken())) {
            String query = "INSERT INTO test_table (id, name) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Sample");
            int rows = stmt.executeUpdate();

            return new IngestionResult(rows, "success", "Inserted " + rows + " rows.");
        } catch (SQLException e) {
            return new IngestionResult(0, "error", "Error: " + e.getMessage());
        }
    }

    public IngestionResult testConnection(ClickHouseConfig config) {
        try (Connection conn = ClickHouseConnector.getConnection(
                config.getHost(),
                config.getPort(),
                config.getDatabase(),
                config.getUser(),
                config.getJwtToken())) {
            return new IngestionResult(0, "success", "Connection successful.");
        } catch (SQLException e) {
            return new IngestionResult(0, "error", "Connection failed: " + e.getMessage());
        }
    }
}
