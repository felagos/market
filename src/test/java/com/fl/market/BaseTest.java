package com.fl.market;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class BaseTest {

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

}
