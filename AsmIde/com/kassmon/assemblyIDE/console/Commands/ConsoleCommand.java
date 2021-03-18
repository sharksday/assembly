package com.kassmon.assemblyIDE.console.Commands;

import com.kassmon.assemblyIDE.console.Console;
import com.kassmon.library.tokenizers.Tokenizer;

public interface ConsoleCommand {
	void addCommand(ConsoleCommand command);
	void run(Tokenizer tokenizer, Console console);
}
