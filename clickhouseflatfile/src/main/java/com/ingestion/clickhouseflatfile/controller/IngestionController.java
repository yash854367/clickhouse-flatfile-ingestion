package com.ingestion.clickhouseflatfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ingestion.clickhouseflatfile.model.ClickHouseConfig;
import com.ingestion.clickhouseflatfile.model.IngestionResult;
import com.ingestion.clickhouseflatfile.service.IngestionService;

@RestController
@RequestMapping("/api/ingestion")
@CrossOrigin(origins = "*")
public class IngestionController {

    @Autowired
    private IngestionService ingestionService;

    @PostMapping("/clickhouse")
    public IngestionResult ingestToClickHouse(@RequestBody ClickHouseConfig config) {
        return ingestionService.ingestDummyData(config);
    }

    @PostMapping("/clickhouse/connect")
    public IngestionResult testConnection(@RequestBody ClickHouseConfig config) {
        return ingestionService.testConnection(config);
    }
}
