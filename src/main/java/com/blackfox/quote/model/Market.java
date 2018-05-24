package com.blackfox.quote.model;

import java.util.List;
import java.util.Optional;

public class Market {
    private List<Offer> offers;

    public Market() {
    }

    public Market(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Market{" +
                "offers=" + offers +
                '}';
    }

    @Override
    public int hashCode() {
        Optional<Integer> hashCode = offers.stream().map(Offer::hashCode).reduce(Integer::sum);

        return hashCode.isPresent() ? hashCode.get() : -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Market) {
            return ((Market) obj).getOffers().equals(offers);
        } else {
            return false;
        }
    }
}
