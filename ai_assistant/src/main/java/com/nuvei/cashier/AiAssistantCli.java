package com.nuvei.cashier;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.nuvei.cashier.code.InputParameters;
import java.nio.file.Path;

public class AiAssistantCli {

    private InputParameters inputParameters;

    public AiAssistantCli(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        String jarName = new java.io.File(AiAssistant.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath())
                .getName();
        String cmdLineSyntax = "java -jar " + jarName + " [options]";

        try {
            Options options = getHelpOptions();

            CommandLine helpCmd = parser.parse(options, args, true);

            if (helpCmd.hasOption("h") || args.length == 0) {
                throw new MissingOptionException("");
            }
        } catch (ParseException e) {
            // In case of invalid or -h parsing, still show help
            formatter.printHelp(cmdLineSyntax, getFullOptions(), true);
            throw e;
        }

        try {
            Options options = getFullOptions();

            CommandLine cmd = parser.parse(options, args);

            inputParameters = new InputParameters(
                Path.of(cmd.getOptionValue("f")),
                cmd.getOptionValue("fn"),
                cmd.getOptionValue("ft"),
                cmd.getOptionValue("fs", ""),
                cmd.getOptionValue("ftt", ""),
                cmd.getOptionValue("fd", ""),
                cmd.hasOption("c"),
                cmd.hasOption("n"),
                cmd.getOptionValue("sid"));

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(cmdLineSyntax, getFullOptions(), true);
            throw e;
        }
    }

    public InputParameters getInputParameters() {
        return inputParameters;
    }

    private Option getHelpOption() {
        return Option.builder("h")
                .longOpt("help")
                .desc("Show help")
                .build();
    }

    private Options getHelpOptions() {
        Options options = new Options();
        options.addOption(getHelpOption());

        return options;
    }

    private Options getFullOptions() {
        Options options = new Options();
        options.addOption(getHelpOption());

        options.addOption(Option.builder()
                .option("f")
                .longOpt("file")
                .hasArg()
                .required()
                .desc("Path to the file to modify")
                .build());

        options.addOption(Option.builder()
                .option("fn")
                .longOpt("fieldName")
                .hasArg()
                .required()
                .desc("Name of the field to add")
                .build());

        options.addOption(Option.builder()
                .option("ft")
                .longOpt("fieldType")
                .hasArg()
                .required()
                .desc("Type of the field")
                .build());

        options.addOption(Option.builder()
                .option("fs")
                .longOpt("fieldSize")
                .hasArg()
                .desc("Size of the field")
                .build());

        options.addOption(Option.builder()
                .option("ftt")
                .longOpt("fieldTooltip")
                .hasArg()
                .desc("Tooltip of the field")
                .build());

        options.addOption(Option.builder()
                .option("fd")
                .longOpt("fieldDefaultValue")
                .hasArg()
                .desc("Default value of the field")
                .build());

        options.addOption(Option.builder()
                .option("c")
                .longOpt("fieldCacheable")
                .desc("Is field cacheable?")
                .build());

        options.addOption(Option.builder()
                .option("n")
                .longOpt("fieldNullable")
                .desc("Is field nullable?")
                .build());

        options.addOption(Option.builder()
                .option("sid")
                .longOpt("storyId")
                .hasArg()
                .required()
                .desc("User story ID")
                .build());

        return options;
    }
}
