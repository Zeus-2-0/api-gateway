package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, January 2022
 * Time: 4:29 PM
 * Project: Zeus
 * Package Name: com.zeus.web.response
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalRefDataList {

    private List<InternalRefData> internalRefDataList;

    @Override
    public String toString() {
        return "InternalReferenceDataList{" +
                "internalRefDataList=" + internalRefDataList +
                '}';
    }
}
