package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.resource.interfaces.TradingPartnerAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 02, June 2022
 * Time: 11:54 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TradingPartnerAPIImpl implements TradingPartnerAPI {

    private final TradingPartnerService tradingPartnerService;

    @Override
    public TradingPartnerList getAllTradingPartners() {
        log.info("Inside get all trading partners API");
        return tradingPartnerService.getAllTradingPartners();
    }

    @Override
    public TradingPartnerDto getTradingPartnerById(String tradingPartnerId) {
        return tradingPartnerService.getTradingPartnerById(tradingPartnerId);
    }
}
