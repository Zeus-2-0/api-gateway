package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.reference.data.model.InternalListTypeDto;
import com.brihaspathee.zeus.reference.data.model.InternalListTypesDto;
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
    ZeusApiResponse<InternalListTypeDto> getInternalRefData(String listTypeName);

    /**
     * Get all the list types that are present in the system
     * @return
     */
    ZeusApiResponse<InternalListTypesDto> getAllInternalListTypes();

    /**
     * Get all the codes for the list types passed in
     * @param internalListTypesDto
     * @return
     */
    ZeusApiResponse<InternalListTypesDto> getInternalCodesForListTypes(InternalListTypesDto internalListTypesDto);
}
