package com.blackfox.quote.controller;

import com.blackfox.quote.service.QuoteService;
import com.blackfox.quote.util.ConsoleUtil;
import org.apache.log4j.Logger;

public class QuoteController {
    private static final Logger logger = Logger.getLogger(QuoteController.class);

    private static QuoteService quoteService = QuoteService.getInstance();

    private static QuoteController instance;

    public static synchronized QuoteController getInstance() {
        if (instance == null) {
            instance = new QuoteController();
        }
        return instance;
    }

    private QuoteController() {
    }

    public void processRequest(String[] args) {
        // 1 - Validate inputs
        String marketFile = validateMarketFile(args[0]);

        Integer loanAmount = validateLoanAmount(args[1]);

        // 2 - Process query
        quoteService.processQuoteRequest(marketFile, loanAmount);
    }

    private String validateMarketFile(String arg) {
        String marketFile = String.valueOf(ConsoleUtil.validateInput(
                "market file",
                arg,
                String.class.getSimpleName()));

        logger.info("market file: " + marketFile);
        return marketFile;
    }

    private Integer validateLoanAmount(String arg) {
        Integer loanAmount = (Integer) ConsoleUtil.validateInput(
                "loan amount",
                arg,
                Integer.class.getSimpleName());

        logger.info("loan amount: " + loanAmount);

        assert loanAmount != null;

        if (loanAmount < 100 || loanAmount > 15000) {
            System.out.println("Loan amount must be between £1000 and £15000");
            System.exit(0);
        }

        if (loanAmount % 100 != 0) {
            System.out.println("Loan amount must multiple of £100");
            System.exit(0);
        }
        return loanAmount;
    }
}
