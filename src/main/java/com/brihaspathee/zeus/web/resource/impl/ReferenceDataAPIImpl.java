package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.web.model.InternalListTypesDto;
import com.brihaspathee.zeus.web.model.InternalRefDataList;
import com.brihaspathee.zeus.web.resource.interfaces.ReferenceDataAPI;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 2:14 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ReferenceDataAPIImpl implements ReferenceDataAPI {

    /**
     * The reference data service to perform the request operations
     */
    private final ReferenceDataService referenceDataService;


    /**
     * Get all the internal codes of a list type
     * @return Internal Reference Data List
     */
    @Override
    public InternalRefDataList getInternalRefDataList(String listTypeName) {
        return referenceDataService.getInternalRefData(listTypeName);
    }

    /**
     * Get all the internal lists that are present in the system
     * @return
     */
    @Override
    public ResponseEntity<ZeusApiResponse<InternalListTypesDto>> getAllInternalListTypes() {

        return ResponseEntity.ok(referenceDataService.getAllInternalListTypes());
    }
}
