package com.fl.market.unit;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class BaseUnitTest {

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

}
