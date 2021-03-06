package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 11:30 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TradingPartnerServiceImpl implements TradingPartnerService {

    @Value("${url.host.trading-partner}")
    private String tradingPartnerHost;

    private final RestTemplate restTemplate;

    private final RestTemplateBuilder restTemplateBuilder;

    private final ObjectMapper objectMapper;

    @Override
    public TradingPartnerList getAllTradingPartners() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("Trading partner host:{}", tradingPartnerHost);
        RestTemplate tpRestTemplate = restTemplateBuilder.basicAuthentication("api-gateway", "password").build();
        ResponseEntity<ZeusApiResponse> apiResponse = tpRestTemplate.getForEntity(tradingPartnerHost+"tp", ZeusApiResponse.class);
        if (apiResponse.getBody().getMessage().equals("Success")){
            TradingPartnerList responseList = objectMapper.convertValue(apiResponse.getBody().getResponse(), TradingPartnerList.class);
            log.info("Trading Partner Response:{}", responseList);
            return responseList;
        }else{
            return null;
        }
    }

    @Override
    public TradingPartnerDto getTradingPartnerById(String tradingPartnerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("Trading partner host:{}", tradingPartnerHost);
        RestTemplate tpRestTemplate = restTemplateBuilder.basicAuthentication("api-gateway", "password").build();
        ResponseEntity<ZeusApiResponse> apiResponse = tpRestTemplate.getForEntity(tradingPartnerHost+"tp/"+tradingPartnerId, ZeusApiResponse.class);
        if (apiResponse.getBody().getMessage().equals("Success")){
            TradingPartnerDto tradingPartnerDto = objectMapper.convertValue(apiResponse.getBody().getResponse(), TradingPartnerDto.class);
            log.info("Trading Partner Response:{}", tradingPartnerDto);
            return tradingPartnerDto;
        }else{
            return null;
        }
    }
}
