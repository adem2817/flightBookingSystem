package com.iyzico.challenge.controller;

import com.iyzico.challenge.entity.model.SellingModel;
import com.iyzico.challenge.service.SellingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/sell")
public class SellController {

    private final SellingService sellingService;

    @PostMapping
    ResponseEntity<?> sellFligth(@Valid @RequestBody SellingModel sellingModel) {
        try {
            sellingService.sellFlight(sellingModel);
        } catch (Exception e) {
            log.error("Error while selling flight, {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error while selling flight, " +  e.getMessage());
        }
        return ResponseEntity.accepted().body("Ticket is sold successsfully");
    }
}
