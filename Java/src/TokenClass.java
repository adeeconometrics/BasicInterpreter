/**
 * @brief contains methods for token types and TokenClass constructor
 */

package src;

public class TokenClass {
  final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;

  TokenClass(TokenType type, String lexeme, Object literal, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
  }
  /**
   * Returns a string representation of TokenClass for analyzing the scanned tokens.
   */
  public String toString() {
    // if(type == TokenType.IDENTIFIER) 
      // return type + ":" + lexeme + ":" + literal;
    return type + ":" + lexeme;
  }
}