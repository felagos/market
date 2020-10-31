package com.fl.market.integration.controller;

import com.fl.market.web.response.ResponseController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class CategoryIntegrationTest extends BaseIntegrationTest {

    @Test
    public void get_by_category_id() throws Exception {
        var response = restTemplate.getForEntity(createURLWithPort("/api/category/8"), ResponseController.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotEquals(response.getBody().getData(), null);
    }

}
