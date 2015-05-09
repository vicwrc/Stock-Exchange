package com.luxoft.stockexchange.emulator.actions.template;

import org.junit.Test;

import static org.junit.Assert.*;

public class FixActivityRowTest {


    @Test
    public void testIsValidDataType() throws Exception {
        FixActivityRow row = new FixActivityRow();
        row.setDataType(Number.class);

        assertTrue(row.isValidDataType(Integer.valueOf(1)));
        assertFalse(row.isValidDataType(new Object()));
        assertFalse(row.isValidDataType("123"));
    }
}