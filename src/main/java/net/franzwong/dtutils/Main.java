package net.franzwong.dtutils;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.time.ZoneId;
import java.util.Properties;

@QuarkusMain
@CommandLine.Command(
    name = "dtutils",
    mixinStandardHelpOptions = true,
    subcommands = {CommandLine.HelpCommand.class, NowCommand.class, TimestampCommand.class, ConvertTimezoneCommand.class})
public class Main implements QuarkusApplication {

    @Inject
    private CommandLine.IFactory factory;

    public static void main(String... args) {
        Quarkus.run(Main.class, args);
    }

    @Override
    public int run(String... args) throws Exception {
        var defaultTimezone = ZoneId.systemDefault().getId();

        var defaultValueProps = new Properties();
        defaultValueProps.put("dtutils.now.output-timezone", defaultTimezone);
        defaultValueProps.put("dtutils.convertTimezone.input-timezone", defaultTimezone);
        defaultValueProps.put("dtutils.timestamp.output-timezone", defaultTimezone);

        return new CommandLine(this, factory)
            .setDefaultValueProvider(new CommandLine.PropertiesDefaultProvider(defaultValueProps))
            .setAbbreviatedSubcommandsAllowed(true)
            .execute(args);
    }

}
