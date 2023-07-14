package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

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

    /**
     * The trading partner URL retrieved from application.yaml file
     */
    @Value("${url.host.trading-partner}")
    private String tradingPartnerHost;

    /**
     * Rest Template object to make API calls
     */
    private final RestTemplate restTemplate;

    /**
     * Resttemplate builder to create the rest template object
     */
    private final RestTemplateBuilder restTemplateBuilder;

    /**
     * The object mapper instance to convert objects into string and vice versa
     */
    private final ObjectMapper objectMapper;

    /**
     * Get all the trading partners
     * @return
     */
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

    /**
     * Get trading partner by id
     * @param tradingPartnerId
     * @return
     */
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

    /**
     * Create a new trading partner
     * @param tradingPartnerDto
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public TradingPartnerDto createTradingPartner(TradingPartnerDto tradingPartnerDto) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("Trading partner host:{}", tradingPartnerHost);
        String tradingPartnerAsString = objectMapper.writeValueAsString(tradingPartnerDto);
        HttpEntity<String> httpEntity = new HttpEntity<>(tradingPartnerAsString, headers);
        RestTemplate tpRestTemplate = restTemplateBuilder.basicAuthentication("api-gateway", "password").build();
        ResponseEntity<ZeusApiResponse> apiResponse = tpRestTemplate.postForEntity(tradingPartnerHost+"tp", httpEntity, ZeusApiResponse.class);
        if (apiResponse.getBody().getMessage().equals("Success")){
            TradingPartnerDto savedTradingPartner = objectMapper.convertValue(apiResponse.getBody().getResponse(), TradingPartnerDto.class);
            log.info("Trading Partner Response:{}", savedTradingPartner);
            return savedTradingPartner;
        }else{
            return null;
        }
    }

    @Override
    public void updateTradingPartner(TradingPartnerDto tradingPartnerDto, UUID tradingPartnerSK) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("Trading partner host:{}", tradingPartnerHost);
        String tradingPartnerAsString = objectMapper.writeValueAsString(tradingPartnerDto);
        HttpEntity<String> httpEntity = new HttpEntity<>(tradingPartnerAsString, headers);
        RestTemplate tpRestTemplate = restTemplateBuilder.basicAuthentication("api-gateway", "password").build();
        tpRestTemplate.put(tradingPartnerHost+"tp/"+tradingPartnerSK.toString(), httpEntity, ZeusApiResponse.class);
    }
}
