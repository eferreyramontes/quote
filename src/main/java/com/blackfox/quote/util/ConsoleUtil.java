package com.blackfox.quote.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class ConsoleUtil {
    private static final Logger logger = Logger.getLogger(ConsoleUtil.class);

    public static Object validateInput(String inputName, String inputValue, String type) {

        if (StringUtils.isEmpty(inputValue)) {
            System.out.println("Please, define a " + inputName + "  to proceed.");
            return null;
        }

        Object value = null;
        switch (type) {
            case "Integer":
                try {
                    value = Integer.valueOf(inputValue);
                } catch (NumberFormatException ex) {
                    logger.warn("Something went wrong trying to parse Integer to value " + inputValue + ": " + ex.getMessage());
                    System.out.println("Something is wrong with the value of " + inputName
                            + ". Could you check it and try again? If problem persist, please get in contact with the administrator");
                    System.exit(1);
                }
                break;
            case "String":
                return inputValue;
            default:
                System.out.println("Incompatible type for " + inputName + " input. Please check it and try again.");
                System.exit(0);
        }

        return value;
    }

    public static void printResults(int loanAmont, String rate, String monthlyPayment, String totalPayment) {
        System.out.println("Request amount: £ " + loanAmont);
        System.out.println("Rate: " + rate + "%");
        System.out.println("Monthly repayment: £ " + monthlyPayment);
        System.out.println("Total repayment: £ " + totalPayment);
    }

    public static void printResultsInsufficientFound(int loanAmont) {
        System.out.println("There's not enough pounds in the market to suit your request: £" + loanAmont);
    }
}
