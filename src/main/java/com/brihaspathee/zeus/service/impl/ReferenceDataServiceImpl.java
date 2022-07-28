package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.reference.data.model.InternalListTypeDto;
import com.brihaspathee.zeus.reference.data.model.InternalListTypesDto;
import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public ZeusApiResponse<InternalListTypeDto> getInternalRefData(String listTypeName) {
        /*HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<InternalListTypeDto> apiResponse = restTemplate.getForEntity(refDataHost+"ref-data/internal/"+listTypeName, InternalListTypeDto.class);*/
        ZeusApiResponse<InternalListTypeDto> apiResponse = webClient.get()
                .uri(refDataHost+"ref-data/internal/"+listTypeName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ZeusApiResponse<InternalListTypeDto>>() {})
                .block();
        return apiResponse;
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

    /**
     * Get the list codes for the internal list types
     * @param internalListTypesDto
     * @return
     */
    @Override
    public ZeusApiResponse<InternalListTypesDto> getInternalCodesForListTypes(InternalListTypesDto internalListTypesDto) {
        ZeusApiResponse<InternalListTypesDto> apiResponse = webClient.post()
                .uri(refDataHost+"ref-data/internal/list-types")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(internalListTypesDto), InternalListTypesDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ZeusApiResponse<InternalListTypesDto>>() {})
                .block();
        return apiResponse;
    }
}
