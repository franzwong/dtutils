package net.franzwong.dtutils;

import picocli.CommandLine;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatterConverter implements CommandLine.ITypeConverter<DateTimeFormatter> {

    @Override
    public DateTimeFormatter convert(String value) {
        try {
            return DateTimeFormatter.ofPattern(value);
        } catch (IllegalArgumentException e) {
            throw new CommandLine.TypeConversionException("Invalid date time format.");
        }
    }

}
