package com.afn.afnapp.utils;

import org.joda.time.LocalDate;

public class Helper {

    public static String getPuasa(LocalDate hijriDate, int dayDate) {
        LocalDate todayHijri = hijriDate;

        if ((todayHijri.getMonthOfYear() == 10 && todayHijri.getDayOfMonth() == 1) ||
                (todayHijri.getMonthOfYear() == 12 && todayHijri.getDayOfMonth() == 10) ||
                (todayHijri.getMonthOfYear() == 12 && (todayHijri.getDayOfMonth() >= 11 && todayHijri.getDayOfMonth() <= 13))) {
            return Constans.PUASA_DILARANG;
        } else if (todayHijri.getMonthOfYear() == 10 && (todayHijri.getDayOfMonth() >= 2 && todayHijri.getDayOfMonth() <= 7)) {
            return Constans.PUASA_SYAWWAL;
        } else if (todayHijri.getMonthOfYear() == 9) {
            return Constans.PUASA_RAMADHAN;
        } else if (todayHijri.getMonthOfYear() == 12 && todayHijri.getDayOfMonth() == 9) {
            return Constans.PUASA_ARAFAH;
        } else if ((todayHijri.getDayOfMonth() >= 13 && todayHijri.getDayOfMonth() <= 15) ||
                (todayHijri.getMonthOfYear() == 12 && (todayHijri.getDayOfMonth() >= 14 && todayHijri.getDayOfMonth() <= 16))) {
            return Constans.PUASA_AYYAMUL_BIDH;
        } else if (dayDate == 1 || dayDate == 4) {
            return Constans.PUASA_SENIN_KAMIS;
        } else {
            return "";
        }
    }

    public class Constans {

        // Hijri Calendar
        public static final String PUASA_DILARANG = "Hari yang dilarang Puasa";
        public static final String PUASA_SYAWWAL = "Puasa 6 hari dibulan Syawwal";
        public static final String PUASA_RAMADHAN = "Puasa Ramadhan";
        public static final String PUASA_ARAFAH = "Puasa Arafah";
        public static final String PUASA_AYYAMUL_BIDH = "Puasa Ayyamul Bidh";
        public static final String PUASA_SENIN_KAMIS = "Puasa Senin dan Kamis";

        public static final String PUASA_DILARANG_INFO = "Iedul Fitri, Iedul Adha, Hari Tasyriq dalam HR. Bukhari 1990 dan Muslim 1141";
        public static final String PUASA_SYAWWAL_INFO = "HR. Muslim 1164";
        public static final String PUASA_RAMADHAN_INFO = "QS. Al-Baqarah: 185";
        public static final String PUASA_ARAFAH_INFO = "HR. Muslim 1162";
        public static final String PUASA_AYYAMUL_BIDH_INFO = "HR. Abu Daud 2449";
        public static final String PUASA_SENIN_KAMIS_INFO = "HR. Tirmidzi 747";

    }

}
