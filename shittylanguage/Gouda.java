package shittylanguage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Scanner;

public class Gouda {
  static boolean hasError = false;

  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jGouda [script]");
      System.exit(64);
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader bReader = new BufferedReader(input);

    for (;;) {
      System.out.print("> ");
      String line = bReader.readLine();
      if (line == null)
        break;
      run(line);
    }
  }

  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    Stream<String> tokens = scanner.tokens();
    tokens.forEach(el -> {
      System.out.print(el);

    });
    scanner.close();
  }

  static void error(int line, String message) {
    report(line, "", message);
  }

  private static void report(int line, String where,
      String message) {
    System.out.print("[Line" + line + "] " + where + message);
  }

}