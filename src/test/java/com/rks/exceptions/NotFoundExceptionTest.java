package com.rks.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotFoundExceptionTest {

    @Test
    public void ProductNotFoundExceptionException() {
        assertEquals("NotFoundExceptionTest", new NotFoundException("NotFoundExceptionTest").getMessage());
    }
}
