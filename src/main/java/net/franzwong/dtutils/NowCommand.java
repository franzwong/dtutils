package net.franzwong.dtutils;

import picocli.CommandLine;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "now",
    description = "Display current time")
public class NowCommand implements Callable<Integer> {

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

    @Override
    public Integer call() {
        var currentDt = ZonedDateTime.now().withZoneSameInstant(outputZoneId);
        var dtString = currentDt.toLocalDateTime().format(outputFormatter);
        System.out.println(dtString);
        return CommandLine.ExitCode.OK;
    }

}
