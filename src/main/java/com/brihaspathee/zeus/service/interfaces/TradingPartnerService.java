package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 10:28 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface TradingPartnerService {

    TradingPartnerList getAllTradingPartners();

    TradingPartnerDto getTradingPartnerById(String tradingPartnerId);
}
