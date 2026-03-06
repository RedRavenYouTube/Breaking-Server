package com.mite.breakingeverything.server;

/**
 * TimeManager for MiTE Breaking Everything 0.9.0
 * Handles the custom time calculation where days are 72 minutes long and nights are 8 minutes long.
 * Normal Minecraft day is 24000 ticks (20 minutes), MiTE stretches this to 96000 ticks total per day.
 */
public class TimeManager {

    // Normal Minecraft day length in ticks
    private static final long NORMAL_DAY_LENGTH = 24000;
    private static final long DAY_LENGTH = 12000; // Half day
    private static final long NIGHT_LENGTH = 12000; // Half day

    // MiTE advancement rates
    private static final double DAY_ADVANCEMENT = 7.2; // Makes day 86400 ticks
    private static final double NIGHT_ADVANCEMENT = 0.8; // Makes night 9600 ticks
    // Total: 86400 + 9600 = 96000 ticks per MiTE day

    /**
     * Converts normal Minecraft time to MiTE time.
     * @param normalTime The normal world time
     * @return The equivalent MiTE time
     */
    public static long getMiteTime(long normalTime) {
        long miteTime = 0;
        long days = normalTime / NORMAL_DAY_LENGTH;
        long remainder = normalTime % NORMAL_DAY_LENGTH;

        // Add full days
        miteTime += days * (DAY_LENGTH * DAY_ADVANCEMENT + NIGHT_LENGTH * NIGHT_ADVANCEMENT);

        // Add partial day
        if (remainder < DAY_LENGTH) {
            miteTime += (long) (remainder * DAY_ADVANCEMENT);
        } else {
            miteTime += (long) (DAY_LENGTH * DAY_ADVANCEMENT + (remainder - DAY_LENGTH) * NIGHT_ADVANCEMENT);
        }

        return miteTime;
    }

    /**
     * Gets the time advancement rate for the current time.
     * @param time The current world time
     * @return The advancement rate (ticks to add per real tick)
     */
    public static double getAdvancement(long time) {
        return (time % NORMAL_DAY_LENGTH < DAY_LENGTH) ? DAY_ADVANCEMENT : NIGHT_ADVANCEMENT;
    }

    /**
     * Converts MiTE time back to normal time (approximate).
     * @param miteTime The MiTE time
     * @return The approximate normal time
     */
    public static long getNormalTime(long miteTime) {
        // This is an approximation, as the conversion is not perfectly reversible
        return (long) (miteTime / (NORMAL_DAY_LENGTH / (DAY_LENGTH * DAY_ADVANCEMENT + NIGHT_LENGTH * NIGHT_ADVANCEMENT)));
    }
}