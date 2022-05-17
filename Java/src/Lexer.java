package src;

import java.util.Vector;

import src.Position;
import src.TokenClass;

/**
 TODO:
    - See if this compiles
    - Check if it works correctly
    - Address poor code quality
    - Document the module 
 */

public class Lexer {
    
    String filename, text;
    Position pos;
    char current_char;
    Error error;
    
    Lexer(String filename, String text){
         this.filename = filename;
         this.text = text;
         this.pos = Position(-1, 0, -1, filename, text);
         this.current_char = ' ';
         this.advance();
    }
    
    private void advance(){
        this.pos.advance(this.current_char);
        if (this.pos.idx < this.text.length()){
            this.current_char = this.text.charAt(this.pos.idx);
        } else {
            this.current_char = (char)0;
        }
    }
    
    private boolean isDigit(char current_char){
        char [] Digits = {'1' ,'2', '3', '4', '5', '6', '7', '8', '9', '0'};
        for (int i = 0; i < 10; i++){
            if (current_char == Digits [i] )
                return true;
        }
        return false;
    }
    
    public Vector<TokenClass> makeToken(){
        Vector<TokenClass> tokens = new Vector<TokenClass>();
        
        while (this.current_char != (char)0){
            if (this.current_char == ' ' || this.current_char == '\t')
                this.advance();
            else if (isDigit(this.current_char))
                tokens.add(this.makeNumber());
            else if (this.current_char == '+'){
                tokens.add(TokenClass("PLUS"));
                this.advance();
            }
            else if (this.current_char == '-'){
                tokens.add(TokenClass("MINUS"));
                this.advance();
            }
            else if (this.current_char == '*'){
                tokens.add(TokenClass("MUL"));
                this.advance();
            }
            else if (this.current_char == '/'){
                tokens.add(TokenClass("DIV"));
                this.advance();
            }
            else if (this.current_char == '('){
                tokens.add(TokenClass("LPAREN"));
                this.advance();
            }
            else if (this.current_char == ')'){
                tokens.add(TokenClass("RPAREN"));
                this.advance();
            }
            else{
                Position pos_start = this.pos.copy();
                char holder = this.current_char;
                // check into this problem
                this.error = new IllegalCharError(pos_start, this.pos, "'" + holder + "'");
                return null;
            }
        }
        
        return tokens;
    }
    
    TokenClass make_number(){
        String numStr = "";
        int dotCount = 0;
        
        while (this.current_char != (char)0 && (isDigit(this.current_char) || this.current_char == '.')){
            if (this.current_char == '.'){
                if (dotCount == 1) break;
                dotCount += 1;
                numStr += '.';
            }else
                numStr += this.current_char;
            this.advance();
        }
        
        if (dotCount == 0)
            return TokenClass("INT", numStr);
        else
            return TokenClass("FLOAT", numStr);
    }
}