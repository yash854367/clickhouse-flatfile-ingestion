package com.ingestion.clickhouseflatfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickhouse.client.ClickHouseConfig;
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
}