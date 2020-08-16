package com.codefresh.cli.actions;

import com.codefresh.cli.handler.CommandLine;

public abstract class BaseAction {

    protected final CommandLine commandLine;

    public BaseAction(CommandLine commandLine) {
        this.commandLine = commandLine;
    }
}
