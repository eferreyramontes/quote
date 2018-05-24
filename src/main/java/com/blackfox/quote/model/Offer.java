package com.blackfox.quote.model;

import org.apache.log4j.Logger;

public class Offer implements Comparable<Offer> {
    private static final Logger logger = Logger.getLogger(Offer.class);

    private String name;
    private float rate;
    private int amount;

    public static Offer createOffer(String name, String rate, String amount) {
        Offer offer = new Offer();
        offer.name = name;
        try {
            offer.rate = Float.parseFloat(rate);
        } catch (NumberFormatException e) {
            logger.error("Rate " + rate + " couldn't be read: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        try {
            offer.amount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            logger.error("Amount " + amount + " couldn't be read: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return offer;
    }

    public Offer() {
    }

    public Offer(String name, float rate, int amount) {
        this.name = name;
        this.rate = rate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Offer offerToCompare) {
        return Float.compare(rate, offerToCompare.getRate());
    }

    @Override
    public int hashCode() {
        return name.hashCode()
                + String.format("%s", rate).replace(".","").hashCode()
                + amount;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Offer){
            Offer offer = (Offer)obj;
            return name.equals(offer.name) && String.format("%s", offer.rate).replace(".","")
                    .equals(String.format("%s", rate).replace(".","")) &&
                    offer.amount == amount;
        }
        else {
            return false;
        }
    }
}
