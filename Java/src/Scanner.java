/**
 * @brief This contains methods related to the Tokenizing text
 */
package src;

import java.util.HashMap;
import java.util.ArrayList;


public class Scanner{
  private static final HashMap<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();
    keywords.put("LET", TokenType.LET);
    keywords.put("DATA", TokenType.DATA); // ok
    keywords.put("READ", TokenType.READ);
    keywords.put("RESTORE", TokenType.RESTORE);
    keywords.put("DIM", TokenType.DIM); //ok

    keywords.put("IF", TokenType.IF);
    keywords.put("THEN", TokenType.THEN);
    keywords.put("ELSE", TokenType.ELSE);
    keywords.put("FOR", TokenType.FOR);
    keywords.put("TO", TokenType.TO);
    keywords.put("STEP", TokenType.STEP);
    keywords.put("NEXT", TokenType.NEXT);
    keywords.put("WHILE", TokenType.WHILE);
    keywords.put("WEND", TokenType.WEND);
    keywords.put("REPEAT", TokenType.REPEAT);
    keywords.put("UNTIL", TokenType.UNTIL);
    keywords.put("DO", TokenType.DO);
    keywords.put("LOOP", TokenType.LOOP);
    keywords.put("GOTO", TokenType.GOTO);
    keywords.put("ON", TokenType.ON);
    keywords.put("GOSUB", TokenType.GOSUB);

    keywords.put("DEF", TokenType.DEF);
    keywords.put("FN", TokenType.FN);
    keywords.put("INT", TokenType.INT);
    keywords.put("FLOAT", TokenType.FLOAT);
  }

  private final String source;
  private final ArrayList<TokenClass> tokens = new ArrayList<>();

  private int start = 0;
  private int current = 0;
  private int line = 1;

  Scanner(String source) {
    this.source = source;
  }

  /**
   * Scans the source text passed on the class and returns a list of valid tokens
   * @return ArrayList<TokenClass>
   */
  ArrayList<TokenClass> scanTokens() {
    while (!isAtEnd()) {
      // We are at the beginning of the next lexeme.
      start = current;
      scanToken();
    }

    tokens.add(new TokenClass(TokenType.EOF, "", null, line));
    return tokens;
  }

  private void scanToken() {
    char c = advance();
    switch (c) {
      case '(': addToken(TokenType.LEFT_PAREN); break;
      case ')': addToken(TokenType.RIGHT_PAREN); break;
      case '{': addToken(TokenType.LEFT_BRACE); break;
      case '}': addToken(TokenType.RIGHT_BRACE); break;
      case ',': addToken(TokenType.COMMA); break;
      case '.': addToken(TokenType.DOT); break;
      case '-': addToken(TokenType.MINUS); break;
      case '+': addToken(TokenType.PLUS); break;
      case ';': addToken(TokenType.SEMICOLON); break;
      case '*': addToken(TokenType.STAR); break; 

      case '!':
        addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
        break;
      case '=':
        addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
        break;
      case '<':
        addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
        break;
      case '>':
        addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
        break;

      case '/':
        addToken(TokenType.SLASH);
        break;

      case '\'':
        while (peek() != '\n' && !isAtEnd()) advance();
        break;

      case ' ':
      case '\r':
      case '\t':
        break;

      case '\n':
        line++;
        break;

      case '"': string(); break;
      default:
        if (isDigit(c)) {
          number();
        } else if (isAlpha(c)) {
          identifier();
        } else {
          Main.error(line, "Unexpected character.");
        }
        break;
    }
  }

  private void identifier() {
    while (isAlphaNumeric(peek())) advance();

    String text = source.substring(start, current);
    TokenType type = keywords.get(text);
    if (type == null) type = TokenType.IDENTIFIER;
    addToken(type);
  }

  private void number() {
    // todo: has issues with loading the buffer and parsing values
    String numStr = "";
    int dotCount = 0;

    while(isDigit(peek()) || peek() == '.'){
      if(peek() == '.') {
        if(dotCount == 1) break;
        dotCount += 1;
        numStr += String.valueOf('.');
      } else {
        numStr += String.valueOf(peek());
      }
      advance();
    }

    if(dotCount == 0) {
      addToken(TokenType.INT,
        Integer.parseInt(numStr));
    } else if(dotCount == 1) {
      addToken(TokenType.FLOAT,
        Double.parseDouble(numStr));
    }
    // else {
    //   Main.error(line, "Unexpected digit format.");
    // }

  }

  private void string() {
    while (peek() != '"' && !isAtEnd()) {
      if (peek() == '\n') line++;
      advance();
    }

    if (isAtEnd()) {
      Main.error(line, "Unterminated string.");
      return;
    }

    advance();
    String value = source.substring(start + 1, current - 1);
    addToken(TokenType.STRING, value);
  }

  private boolean match(char expected) {
    if (isAtEnd()) return false;
    if (source.charAt(current) != expected) return false;

    current++;
    return true;
  }

  private char peek() {
    if (isAtEnd()) return '\0';
    return source.charAt(current);
  }

  private boolean isAlpha(char c) {
    return (c >= 'a' && c <= 'z') ||
           (c >= 'A' && c <= 'Z') ||
            c == '_';
  }

  private boolean isAlphaNumeric(char c) {
    return isAlpha(c) || isDigit(c);
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  } 

  private boolean isAtEnd() {
    return current >= source.length();
  }

  private char advance() {
    return source.charAt(current++);
  }

  private void addToken(TokenType type) {
    addToken(type, null);
  }

  private void addToken(TokenType type, Object literal) {
    String text = source.substring(start, current);
    tokens.add(new TokenClass(type, text, literal, line));
  }

}