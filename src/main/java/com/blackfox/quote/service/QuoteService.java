package com.blackfox.quote.service;

import com.blackfox.quote.model.Market;
import com.blackfox.quote.model.Offer;
import com.blackfox.quote.util.ConsoleUtil;
import com.blackfox.quote.util.InvestmentUtil;
import com.blackfox.quote.util.MarketUtil;
import com.blackfox.quote.util.PropertiesUtil;
import org.apache.log4j.Logger;

public class QuoteService {
    private static final Logger logger = Logger.getLogger(QuoteService.class);

    private static QuoteService instance;

    public static synchronized QuoteService getInstance() {
        if (instance == null) {
            instance = new QuoteService();
        }
        return instance;
    }

    private QuoteService() {
    }

    public void processQuoteRequest(String marketFile, Integer loanAmont) {

        Market market = MarketUtil.getMarketWithOrderedOffers(marketFile);

        if (market.getOffers().isEmpty()) {
            System.out.println("File provided has not offers. Please check it and try again.");
            return;
        }

        Offer suitableOffer = null;
        for (Offer offer : market.getOffers()) {
            if (offer.getAmount() >= loanAmont) {
                suitableOffer = offer;
                break;
            }
        }

        if (suitableOffer != null) {
            logger.info("There is a suitable offer: " + suitableOffer);
            float rate = Math.round(suitableOffer.getRate() * 100);
            double totalPayment = InvestmentUtil.monthlyCompoundedInterest(loanAmont, suitableOffer.getRate(), PropertiesUtil.getInstance().getLoanMonth());
            double monthlyRepayment = totalPayment / PropertiesUtil.getInstance().getLoanMonth();

            ConsoleUtil.printResults(loanAmont,
                    String.valueOf(rate),
                    String.format("%.2f", monthlyRepayment),
                    String.format("%.2f", totalPayment));
        } else {
            logger.info("There is not a suitable offer.");
            ConsoleUtil.printResultsInsufficientFound(loanAmont);
        }
    }
}
