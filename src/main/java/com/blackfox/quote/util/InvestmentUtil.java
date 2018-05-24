package com.blackfox.quote.util;

public class InvestmentUtil {

    /**
     * Method to calculate the compounded Interest
     *
     * @param investment the initial investment
     * @param rate       rate to be used
     * @param period     total period of the investment
     * @return the monthly compounded interest obtained at the end
     */
    public static double monthlyCompoundedInterest(int investment, float rate, int period) {
        return investment * Math.pow(1 + (rate / 12), period);
    }
}
