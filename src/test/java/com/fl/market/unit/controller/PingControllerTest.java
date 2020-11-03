package com.fl.market.unit.controller;

import com.fl.market.unit.BaseUnitTest;
import com.fl.market.web.controller.PingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class PingControllerTest extends BaseUnitTest {

    @InjectMocks
    private PingController pingController;

    @Test
    public void doPing() {
        var response = pingController.ping();

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
