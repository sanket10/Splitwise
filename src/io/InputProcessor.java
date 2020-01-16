package io;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

public class InputProcessor {
	private Map<String, Consumer<String>> handlers;
	private static InputProcessor inputProcessor = new InputProcessor();
	
	private InputProcessor() {
		this.handlers = new HashMap<String, Consumer<String>>();
	}
	
	public static InputProcessor getInstance() {
		return InputProcessor.inputProcessor;
	}
	
	public void listen() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		while (true) {
			String inputCommand = null;
			try {
				inputCommand = reader.readLine();
				this.handlers.get(this.getCommand(inputCommand)).accept(inputCommand);
			} catch(Exception e) {
				System.out.println("Invalid Input : " + e.getMessage());
				break;
			}
		}
	}
	
	private String getCommand(String input) {
		input = input.trim();
		return input.split(" ")[0];
	}
	
	public void addHandler(String command, Consumer<String> handler) {
		if (this.handlers.containsKey(command)) {
			System.out.println("Handler Already Exist");
			return;
		}
		this.handlers.put(command, handler);
	}
}
