package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.web.model.InternalListTypesDto;
import com.brihaspathee.zeus.web.model.InternalRefDataList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 2:00 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceDataServiceImpl implements ReferenceDataService {

    /**
     * The reference data host
     */
    @Value("${url.host.ref-data}")
    private String refDataHost;

    /**
     * Rest-template to connect with other rest APIs
     */
    private final RestTemplate restTemplate;

    /**
     * Webclient to connect with other rest APIs
     */
    private final WebClient webClient;

    /**
     * Get all the internal list codes that are associated with the list type
     * @param listTypeName
     * @return
     */
    @Override
    public InternalRefDataList getInternalRefData(String listTypeName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<InternalRefDataList> apiResponse = restTemplate.getForEntity(refDataHost+"ref-data/internal/"+listTypeName,InternalRefDataList.class);
        return apiResponse.getBody();
    }

    /**
     * Get all the list types that are present in the system
     * @return
     */
    @Override
    public ZeusApiResponse<InternalListTypesDto> getAllInternalListTypes() {

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        ResponseEntity<ZeusApiResponse> apiResponse = restTemplate.getForEntity(refDataHost+"ref-data/internal/list-types",
//                ZeusApiResponse.class);
        ZeusApiResponse<InternalListTypesDto> apiResponse = webClient.get()
                .uri(refDataHost+"ref-data/internal/list-types")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ZeusApiResponse<InternalListTypesDto>>() {})
                .block();
        return apiResponse;
    }
}
