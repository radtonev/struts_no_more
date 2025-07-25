# AiAssistant

AiAssistant is a Java-based command-line tool designed to assist with code and workflow automation using AI. It leverages [LangChain4j](https://github.com/langchain4j/langchain4j)
and [Google Gemini](https://ai.google.dev/gemini-api/docs) for advanced language model capabilities.

## Features

- AI-powered code assistance and workflow automation
- CLI interface for easy interaction
- Template-driven prompts for code modification and verification
- Logging via Log4j and SLF4J

## Getting Started

### Prerequisites

- Java 17+ (build, run)
- Maven 3.6+ (build)
- Internet connection (for AI model access) (run)

### Build

To build the project, run:
```
mvn clean package
```

This will generate `ai-assistant-0.1.jar` in the `target/` directory.

### Run

To use the Google Gemini AI API, set the API key as an environment variable:

##### Windows
```cmd
set GEMINI_API_KEY=<Your API key>
```

##### Linux
```shell
export GEMINI_API_KEY="<Your API key>"
```

Refer to the CLI help for available options:
```
java -jar ai-assistant-0.1.jar
```

```
usage: java -jar ai-assistant-0.1.jar [-c] -f <arg> [-fd <arg>]
       -fn <arg> [-fs <arg>] -ft <arg> [-ftt <arg>] [-h] [-n] -sid <arg>
 -c,--fieldCacheable             Is field cacheable?
 -f,--file <arg>                 Path to the file to modify
 -fd,--fieldDefaultValue <arg>   Default value of the field
 -fn,--fieldName <arg>           Name of the field to add
 -fs,--fieldSize <arg>           Size of the field
 -ft,--fieldType <arg>           Type of the field
 -ftt,--fieldTooltip <arg>       Tooltip of the field
 -h,--help                       Show help
 -n,--fieldNullable              Is field nullable?
 -sid,--storyId <arg>            User story ID
```

### Use case

Supposingly modules subject to modifications are in `C:\Projects\java\sample` folder.

To add new field with name `barcode`, type `String`, size 100, cacheable, not null concerning story id 123456789 for example
start the assistant:
```
java -jar ai-assistant-0.1.jar -f "C:\Projects\java\sample\Cashier\persistence\src\main\java\com\safecharge\ppp\persistence\dto\entities\MerchantSite.java" -fn barcode -ft String -fs 100 -sid 123456789 -c -n
```

## Configuration

- Logging: Configure via `src/main/resources/log4j.properties`
- AI prompts: Modify templates in `src/main/resources/templates/`

## Testing

Run unit tests with:
```
mvn test
```

## Dependencies

- LangChain4j (Google Gemini, Core)
- SLF4J & Log4j
- Apache Commons CLI
- JUnit Jupiter

## License

See [LICENSE](https://github.com/radtonev/struts_no_more/blob/a2109ddc432d5205da6047563152fa63110c596b/LICENSE) for details.

## Contributing

Pull requests are welcome. For major changes, please open an issue first.

## Author

- [Project Repository](https://github.com/radtonev/struts_no_more)
