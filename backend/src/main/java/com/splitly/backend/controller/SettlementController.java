package com.splitly.backend.controller;

import com.splitly.backend.dto.SettleRequest;
import com.splitly.backend.service.SettlementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping
    public void settle(@RequestBody SettleRequest request) {
        settlementService.settleSplit(request.getSplitId());
    }
}
