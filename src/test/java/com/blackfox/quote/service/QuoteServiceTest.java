package com.blackfox.quote.service;

import com.blackfox.quote.util.ConsoleUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConsoleUtil.class)
public class QuoteServiceTest {

    private QuoteService quoteService = QuoteService.getInstance();

    @Test
    public void testProcessQuoteRequest() {
        // Setup:
        PowerMockito.mockStatic(ConsoleUtil.class);

        final String marketFilePath = "market.csv";
        final int loanAmount = 100;

        // when:
        quoteService.processQuoteRequest(marketFilePath, loanAmount);

        // then:
        PowerMockito.verifyStatic(ConsoleUtil.class, Mockito.times(1));
        ConsoleUtil.printResults(loanAmount, "7.0", "3.41", "122.93");
    }

    @Test
    public void testProcessQuoteRequestInsufficientFound() {
        // Setup:
        PowerMockito.mockStatic(ConsoleUtil.class);

        final ClassLoader classLoader = getClass().getClassLoader();
        final String marketFilePath = classLoader.getResource("market.csv").getPath();
        final int loanAmount = 10000;

        // when:
        quoteService.processQuoteRequest(marketFilePath, loanAmount);

        // then:
        PowerMockito.verifyStatic(ConsoleUtil.class, Mockito.times(1));
        ConsoleUtil.printResultsInsufficientFound(loanAmount);
    }
}
