# dtutils

`dtutils` is a date time utilities written in Java. Native executable is built with GraalVM. 

## Usage

```
./dtutils-<version> --help
Usage: dtutils [-hV] [COMMAND]
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  help             Display help information about the specified command.
  now              Display current time
  timestamp        Display timestamp with human readable format
  convertTimezone  Convert date time to another timezone
```

### now, n

```
./dtutils-<version> now --help
Usage: dtutils now [-h] [-of=<outputFormatter>] [-otz=<outputZoneId>]
Display current time
  -h, --help   Show this help message and exit.
      -of, --output-format=<outputFormatter>
               Output date time format. Default: yyyy-MM-dd HH:mm:ss
      -otz, --output-timezone=<outputZoneId>
               Output timezone. Default: System timezone.
```

#### Example

Display current time

```
./dtutils-<version> n
2024-03-17 12:27:07
```

Display current time with format MM/dd/yyyy HH:mm:ss

```
./dtutils-<version> n -of "MM/dd/yyyy HH:mm:ss"
03/17/2024 12:27:07
```

Display current time with New York timezone

```
./dtutils-<version> n -otz America/New_York
2024-03-16 23:27:07
```

### timestamp, t

```
./dtutils-<version> timestamp --help
Usage: dtutils timestamp [-h] [-if=<outputFormatter>] [-otz=<outputZoneId>]
                         <timestamp>
Display timestamp with human-readable format
      <timestamp>   Timestamp
  -h, --help        Show this help message and exit.
      -of, --output-format=<outputFormatter>
                    Output date time format. Default: yyyy-MM-dd HH:mm:ss
      -otz, --output-timezone=<outputZoneId>
                    Output timezone. Default: System timezone.
```

#### Example

Display timestamp with human-readable format

```
./dtutils-<version> t 1710646027
2024-03-17 12:27:07
```

Display timestamp with format MM/dd/yyyy HH:mm:ss

```
./dtutils-<version> t 1710646027 -of "MM/dd/yyyy HH:mm:ss"
03/17/2024 12:27:07
```

Display timestamp with New York timezone

```
./dtutils-<version> t 1710646027 -otz America/New_York
2024-03-16 23:27:07
```

### convertTimezone, cT

```
./dtutils-<version> convertTimezone --help
Usage: dtutils convertTimezone [-h] [-if=<inputFormatter>] [-itz=<inputZoneId>]
                               [-of=<outputFormatter>] <dateTimeString>
                               <outputZoneId>
Convert date time to another timezone
      <dateTimeString>   Input date time
      <outputZoneId>     Output timezone
  -h, --help             Show this help message and exit.
      -if, --input-format=<inputFormatter>
                         Input date time format. Default: yyyy-MM-dd HH:mm:ss
      -itz, --input-timezone=<inputZoneId>
                         Input timezone. Default: System timezone.
      -of, --output-format=<outputFormatter>
                         Output date time format. Default: yyyy-MM-dd HH:mm:ss
```

Example

Convert date time string to New York timezone

```
./dtutils-<version> cT "2024-03-17 12:27:07" "America/New_York"
2024-03-16 23:27:07
```

Convert date time string in other format to New York timezone

```
./dtutils-<version> cT "03/17/2024 12:27:07" "America/New_York" -if "MM/dd/yyyy HH:mm:ss"
2024-03-16 23:27:07
```

Convert date time string in London timezone to New York timezone

```
./dtutils-<version> cT "2024-03-17 12:27:07" "America/New_York" -itz "Europe/London"
2024-03-17 08:27:07
```

Convert date time string to New York timezone with format MM/dd/yyyy HH:mm:ss

```
./dtutils-<version> cT "2024-03-17 12:27:07" "America/New_York" -of "MM/dd/yyyy HH:mm:ss"
03/16/2024 23:27:07
```

## Build

### GraalVM

GraalVM is required to build native executable. If you have `sdkman` installed, you can just install it with `sdk install java 21-graalce`.

After you have installed it, you need to set `GRAALVM_HOME`. If you installed it by `sdkman`, you can get the value by `sdk home java 21-graalce`.

### Make

You can build the project by `make`. The executable `dtutils-<version>` is inside the `target` directory.
