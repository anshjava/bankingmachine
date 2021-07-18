package ru.kamuzta.bankingrmi.server.command;

import ru.kamuzta.bankingrmi.ConsoleHelper;
import ru.kamuzta.bankingrmi.server.Language;
import ru.kamuzta.bankingrmi.server.Operation;
import ru.kamuzta.bankingrmi.server.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class LanguageCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        Locale.setDefault(Locale.ENGLISH);
        Language language = null;
        while (language == null) {
            language = ConsoleHelper.askLanguage();
            Locale.setDefault(language.getLocale());
            ConsoleHelper.LANGUAGE = ResourceBundle.getBundle(language.getPropFileName());
        }
    }
}
