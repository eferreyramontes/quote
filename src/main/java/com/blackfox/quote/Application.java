package com.blackfox.quote;

import com.blackfox.quote.controller.QuoteController;

public class Application {

    private static QuoteController quoteController = QuoteController.getInstance();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient about of arguments.");
            System.exit(1);
        }

        quoteController.processRequest(args);
    }
}
