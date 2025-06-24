package com.nuvei.cashier;

public class AiAssistant {

    public static void main(String[] args) {
        AiAssistantCli cli = new AiAssistantCli(args);

        System.out.println(String.format("You selected file: %s", cli.getFile()));
    }
}
