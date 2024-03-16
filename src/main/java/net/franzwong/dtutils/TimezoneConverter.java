package net.franzwong.dtutils;

import picocli.CommandLine;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;

public class TimezoneConverter implements CommandLine.ITypeConverter<ZoneId> {
    @Override
    public ZoneId convert(String value) {
        try {
            return ZoneId.of(value);
        } catch (ZoneRulesException e) {
            throw new CommandLine.TypeConversionException("Timezone '" + value + "' is not found.");
        } catch (DateTimeException e) {
            throw new CommandLine.TypeConversionException("Invalid timezone format");
        }
    }
}
