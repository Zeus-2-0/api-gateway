package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.resource.interfaces.TradingPartnerAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 11:54 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TradingPartnerAPIImpl implements TradingPartnerAPI {

    private final TradingPartnerService tradingPartnerService;

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllTradingPartners() {
        log.info("Inside get all trading partners API");
        TradingPartnerList tradingPartnerList =  tradingPartnerService.getAllTradingPartners();
        ZeusApiResponse<TradingPartnerList> apiResponse = ZeusApiResponse.<TradingPartnerList>builder()
                .response(tradingPartnerList)
                .status(HttpStatus.OK)
                .statusCode(200)
                .message("Successfully Retrieved Trading Partners")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerDto>> getTradingPartnerById(String tradingPartnerId) {
        ZeusApiResponse<TradingPartnerDto> apiResponse = ZeusApiResponse.<TradingPartnerDto>builder()
                .response(tradingPartnerService.getTradingPartnerById(tradingPartnerId))
                .status(HttpStatus.OK)
                .statusCode(200)
                .message("Successfully Retrieved Trading Partners")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerList>> getTradingPartnerByParameter(Map<String, String> searchParams) {
        log.info("Search Parameters:{}", searchParams);
        String tradingPartnerId = searchParams.get("tradingPartnerId");
        TradingPartnerList tradingPartnerList = TradingPartnerList.builder()
                .tradingPartnerDtos(List.of(tradingPartnerService.getTradingPartnerById(tradingPartnerId)))
                .build();
        ZeusApiResponse<TradingPartnerList> apiResponse = ZeusApiResponse.<TradingPartnerList>builder()
                .response(tradingPartnerList)
                .status(HttpStatus.OK)
                .statusCode(200)
                .message("Successfully Retrieved Trading Partners")
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
