/**
 * @brief contains entry point for main program
 */


package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class Main{
    static boolean hadError = false; 
    
    /**
     * Main function.
     * @param args
     * @throws IOException
     */
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: basic [script]");
      System.exit(64); 
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }
  /**
   * Read the source file and calls the run function.
   * @param path
   * @throws IOException
   */
  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));
    if (hadError) System.exit(65);
  }
  /**
   * Runs the REPL and immediately tokenize the command. 
   * @throws IOException
   */
  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) { 
      System.out.print("basic > ");
      String line = reader.readLine();
      if (line == null) break;
      run(line);
      hadError = false;
    }
  }
  /**
   * Reads the source file or text and calls the Scanner.scanTokens. 
   * @param source
   */
  private static void run(String source) {
    Scanner scanner = new Scanner(source);
    ArrayList<TokenClass> tokens = scanner.scanTokens();

    System.out.println(Arrays.toString(tokens.toArray()));
  }
  /**
   * Responsible for reporting an occurrence of an error
   * @param line
   * @param message
   */
  static void error(int line, String message) {
    report(line, "", message);
  }
  /**
   * Responsible for printing out the error
   * @param line
   * @param where
   * @param message
   */
  private static void report(int line, String where,
                             String message) {
    System.err.println(
        "[line " + line + "] Error" + where + ": " + message);
    hadError = true;
  }
}