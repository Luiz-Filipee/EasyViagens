package br.alura.aluraviajens.util;

import androidx.annotation.NonNull;

public class DaysUtil {

    public static final String PLURAL = " dias";
    public static final String SINGULAR = " dia";

    @NonNull
    public static String formatDaysInText(int numberOfDays) {
        String daysInText = "";
        if (numberOfDays > 1) {
             return daysInText = numberOfDays + PLURAL;
        }
            return daysInText = numberOfDays + SINGULAR;
    }
}
