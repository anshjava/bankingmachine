package ru.kamuzta.bankingrmi.server;

import java.util.Locale;

public enum Language {
    DEFAULT("language_en",Locale.ENGLISH),
    ENG("language_en",Locale.ENGLISH),
    RUS("language_ru",new Locale("ru"));

    private String propFileName;
    private Locale locale;

    public String getPropFileName() {
        return propFileName;
    }

    public Locale getLocale() {
        return locale;
    }

    Language(String propFileName, Locale locale) {
        this.propFileName = propFileName;
        this.locale = locale;
    }

    public static Language getAllowableLanguageByOrdinal(Integer i) {
        if (i < 1 || i > 2) {
            throw new IllegalArgumentException();
        }
        return Language.values()[i];
    }
}
