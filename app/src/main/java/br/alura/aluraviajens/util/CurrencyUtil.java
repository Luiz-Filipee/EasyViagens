package br.alura.aluraviajens.util;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    public static final String PORTUGUES = "pt";
    public static final String BRAZIL = "br";
    public static final String FORMAT_DEFAULT = "R$";
    public static final String FORMAT_WITH_SPACE = "R$ ";

    @NonNull
    public static String formatCurrencyBrazilian(BigDecimal value) {
        NumberFormat formatBrazilian = DecimalFormat.getCurrencyInstance(new Locale(PORTUGUES, BRAZIL));
        String currencyBrazilian = formatBrazilian.format(value).replace(FORMAT_DEFAULT, FORMAT_WITH_SPACE);
        return currencyBrazilian;
    }
}
