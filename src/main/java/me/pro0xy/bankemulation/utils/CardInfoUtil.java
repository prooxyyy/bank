package me.pro0xy.bankemulation.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 08:39</p>
 */

@UtilityClass
public class CardInfoUtil {
    private static final String[] CARD_PREFIXES = {"4", "5", "3", "6"};
    private static final int CVV_LENGTH = 3;

    private static final Random random = new Random();

    public static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        cardNumber.append(CARD_PREFIXES[random.nextInt(CARD_PREFIXES.length)]);
        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    public static String generateCardHolderName(@NonNull String firstName, @NonNull String lastName) {
        return firstName.toUpperCase() + " " + lastName.toUpperCase();
    }

    public static String generateCvv() {
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < CVV_LENGTH; i++) {
            cvv.append(random.nextInt(10));
        }
        return cvv.toString();
    }
}
