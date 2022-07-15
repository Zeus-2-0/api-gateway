package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.InternalListTypesDto;
import com.brihaspathee.zeus.web.model.InternalRefDataList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 10:27 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface ReferenceDataService {

    /**
     * Get all the internal list codes that are associated with the list type
     * @param listTypeName
     * @return
     */
    InternalRefDataList getInternalRefData(String listTypeName);

    /**
     * Get all the list types that are present in the system
     * @return
     */
    ZeusApiResponse<InternalListTypesDto> getAllInternalListTypes();
}
