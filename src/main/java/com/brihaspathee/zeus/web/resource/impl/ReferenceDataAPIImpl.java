package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.web.model.InternalRefDataList;
import com.brihaspathee.zeus.web.resource.interfaces.ReferenceDataAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ReferenceDataService referenceDataService;


    @Override
    public InternalRefDataList getInternalRefDataList(String listTypeName) {
        return referenceDataService.getInternalRefData(listTypeName);
    }
}
