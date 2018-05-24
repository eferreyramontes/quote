package com.blackfox.quote.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(System.class)
public class ConsoleUtilTest {

    @Test
    public void validateInputStringTest() {
        // setup:
        String inputName = "inputNameTest";
        String inputValue = "file.csv";
        String type = "String";

        // when:
        Object result = ConsoleUtil.validateInput(inputName, inputValue, type);

        // then:
        if (!(result instanceof String)) {
            Assert.fail("The validated output should be type " + type);
        }

        Assert.assertEquals(inputValue, result);
    }

    @Test
    public void validateInputIntegerTest() {
        // setup:
        String inputName = "inputNameTest";
        String inputValue = "1000";
        String type = "Integer";

        // when:
        Object result = ConsoleUtil.validateInput(inputName, inputValue, type);

        // then:
        if (!(result instanceof Integer)) {
            Assert.fail("The validated output should be type " + type);
        }

        Assert.assertEquals(1000, result);
    }
}
