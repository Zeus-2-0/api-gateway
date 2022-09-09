package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.fasterxml.jackson.core.JsonProcessingException;

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

    /**
     * Get all the trading partners in the system
     * @return
     */
    TradingPartnerList getAllTradingPartners();

    /**
     * Get the trading partner by trading partner id
     * @param tradingPartnerId
     * @return
     */
    TradingPartnerDto getTradingPartnerById(String tradingPartnerId);

    /**
     * Create a new trading partner
     * @param tradingPartnerDto
     * @return
     * @throws JsonProcessingException
     */
    TradingPartnerDto createTradingPartner(TradingPartnerDto tradingPartnerDto) throws JsonProcessingException;
}
