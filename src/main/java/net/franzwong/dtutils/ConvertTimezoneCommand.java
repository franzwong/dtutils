package net.franzwong.dtutils;

import picocli.CommandLine;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "convertTimezone",
    description = "Convert date time to another timezone")
public class ConvertTimezoneCommand implements Callable<Integer> {

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @CommandLine.Option(names = { "-h", "--help" }, description = "Show this help message and exit.", usageHelp = true)
    private boolean help;

    @CommandLine.Option(
        names = {"--input-timezone", "-itz"},
        description = "Input timezone. Default: System timezone.",
        converter = TimezoneConverter.class)
    private ZoneId inputZoneId;

    @CommandLine.Option(
        names = {"--input-format", "-if"},
        description = "Input date time format. Default: yyyy-MM-dd HH:mm:ss",
        defaultValue = "yyyy-MM-dd HH:mm:ss",
        converter = DateTimeFormatterConverter.class)
    private DateTimeFormatter inputFormatter;

    @CommandLine.Option(
        names = {"--output-format", "-of"},
        description = "Output date time format. Default: yyyy-MM-dd HH:mm:ss",
        defaultValue = "yyyy-MM-dd HH:mm:ss",
        converter = DateTimeFormatterConverter.class)
    private DateTimeFormatter outputFormatter;

    @CommandLine.Parameters(index = "0", description = "Input date time")
    private String dateTimeString;

    @CommandLine.Parameters(index = "1", description = "Output timezone", converter = TimezoneConverter.class)
    private ZoneId outputZoneId;

    @Override
    public Integer call() {
        LocalDateTime localDt;
        try {
            localDt = LocalDateTime.parse(dateTimeString, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new CommandLine.ParameterException(spec.commandLine(), "Input date time is invalid.");
        }
        var zonedDt = localDt.atZone(inputZoneId);
        var dtString = zonedDt.withZoneSameInstant(outputZoneId).format(outputFormatter);
        System.out.println(dtString);
        return CommandLine.ExitCode.OK;
    }

}
