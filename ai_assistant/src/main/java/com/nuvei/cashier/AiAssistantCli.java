package com.nuvei.cashier;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.nuvei.cashier.code.InputParameters;
import java.nio.file.Path;

public class AiAssistantCli {

    private InputParameters inputParameters;

    public AiAssistantCli(String[] args) throws ParseException {
        Options options = new Options();

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

        options.addOption(Option.builder()
                .option("h")
                .longOpt("help")
                .desc("Show help")
                .build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        String jarName = new java.io.File(AiAssistant.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath())
                .getName();
        String cmdLineSyntax = "java -jar " + jarName + " [options]";

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                formatter.printHelp(cmdLineSyntax, options);
                return;
            }

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
            formatter.printHelp(cmdLineSyntax, options);
            throw e;
        }
    }

    public InputParameters getInputParameters() {
        return inputParameters;
    }
}
