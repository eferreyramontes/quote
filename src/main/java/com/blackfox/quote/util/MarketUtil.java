package com.blackfox.quote.util;

import com.blackfox.quote.model.Market;
import com.blackfox.quote.model.Offer;
import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MarketUtil {
    private static final Logger logger = Logger.getLogger(MarketUtil.class);

    public static Market getMarketWithOrderedOffers(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {

            List<String[]> lines = reader.readAll();
            List<Offer> offers = IntStream.range(1, lines.size()).mapToObj(lines::get)
                    .map(line -> Offer.createOffer(line[0], line[1], line[2]))
                    .filter(Objects::nonNull)
                    .sorted(Offer::compareTo)
                    .collect(Collectors.toList());

            return new Market(offers);

        } catch (Exception e) {
            logger.error("Something went wrong getting market: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        return new Market();
    }
}
