package net.franzwong.dtutils;

import picocli.CommandLine;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "timestamp",
    description = "Display timestamp with human-readable format")
public class TimestampCommand implements Callable<Integer> {

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @CommandLine.Option(names = { "-h", "--help" }, description = "Show this help message and exit.", usageHelp = true)
    private boolean help;

    @CommandLine.Option(
        names = {"--output-timezone", "-otz"},
        description = "Output timezone. Default: System timezone.",
        converter = TimezoneConverter.class)
    private ZoneId outputZoneId;

    @CommandLine.Option(
        names = {"--output-format", "-of"},
        description = "Output date time format. Default: yyyy-MM-dd HH:mm:ss",
        defaultValue = "yyyy-MM-dd HH:mm:ss",
        converter = DateTimeFormatterConverter.class)
    private DateTimeFormatter outputFormatter;

    @CommandLine.Parameters(index = "0", description = "Timestamp")
    private String timestamp;

    @Override
    public Integer call() {
        var timestampValue = Long.parseLong(timestamp);

        Instant instant;
        if (timestamp.length() == 10) {
            instant = Instant.ofEpochSecond(timestampValue);
        } else if (timestamp.length() == 13) {
            instant = Instant.ofEpochMilli(timestampValue);
        } else {
            throw new CommandLine.ParameterException(spec.commandLine(), "Timestamp must be either 10 or 13 digits.");
        }

        var dtString = ZonedDateTime.ofInstant(instant, outputZoneId).toLocalDateTime().format(outputFormatter);
        System.out.println(dtString);

        return CommandLine.ExitCode.OK;
    }
}
