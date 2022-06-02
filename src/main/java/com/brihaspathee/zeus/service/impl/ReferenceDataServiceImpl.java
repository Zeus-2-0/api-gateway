package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.web.model.InternalRefDataList;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Value("${url.host.ref-data}")
    private String refDataHost;

    private final RestTemplate restTemplate;

    @Override
    public InternalRefDataList getInternalRefData(String listTypeName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<InternalRefDataList> apiResponse = restTemplate.getForEntity(refDataHost+"internal/refdata/"+listTypeName,InternalRefDataList.class);
        return apiResponse.getBody();
    }
}
