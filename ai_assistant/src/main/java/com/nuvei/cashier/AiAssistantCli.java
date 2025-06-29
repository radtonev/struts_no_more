package com.nuvei.cashier;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AiAssistantCli {

    private String file;

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

            file = cmd.getOptionValue("f");

        } catch (ParseException e) {
            formatter.printHelp(cmdLineSyntax, options);
            throw e;
        }
    }

    public String getFile() {
        return file;
    }
}
