package com.blackfox.quote.util;

import com.blackfox.quote.model.Market;
import com.blackfox.quote.model.Offer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
public class MarketUtilTest {

    @Test
    public void testGetMarket() {
        // setup:
        final ClassLoader classLoader = getClass().getClassLoader();
        final String marketFilePath = classLoader.getResource("market.csv").getPath();

        // when:
        Market market = MarketUtil.getMarketWithOrderedOffers(marketFilePath);

        // then:
        List<Offer> offers = new ArrayList<>();

        offers.add(new Offer("Jane", 0.069F, 480));
        offers.add(new Offer("Fred", 0.071F, 520));
        offers.add(new Offer("Angela", 0.071F, 60));
        offers.add(new Offer("Dave", 0.074F, 140));
        offers.add(new Offer("Bob", 0.075F, 640));
        offers.add(new Offer("John", 0.081F, 320));
        offers.add(new Offer("Mary", 0.104F, 170));

        Market marketExpected = new Market(offers);

        Assert.assertEquals(marketExpected, market);
    }
}
