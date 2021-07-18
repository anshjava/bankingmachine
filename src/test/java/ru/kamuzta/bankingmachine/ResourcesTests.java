package ru.kamuzta.bankingmachine;

import org.junit.Assert;
import org.junit.Test;
import ru.kamuzta.bankingmachine.server.Language;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourcesTests {
    @Test
    public void getLocalization() {
        System.out.println("Русские символы");
        System.out.println(String.format("file.encoding: %s", System.getProperty("file.encoding")));
        System.out.println(String.format("defaultCharset: %s", Charset.defaultCharset().name()));
        Locale.setDefault(Language.ENG.getLocale());
        ConsoleHelper.LANGUAGE = ResourceBundle.getBundle("language_en");
        Assert.assertEquals("Logging in...", ConsoleHelper.LANGUAGE.getString("login.before"));

        Locale.setDefault(Language.RUS.getLocale());
        ConsoleHelper.LANGUAGE = ResourceBundle.getBundle("language_ru");
        Assert.assertEquals("Авторизация...", ConsoleHelper.LANGUAGE.getString("login.before"));
    }
}
