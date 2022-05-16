
import java.util.Vector;

public class Lexer {
    
    String fn, text;
    Position pos;
    char current_char;
    
    Lexer(String fn, String text){
         this.fn = fn;
         this.text = text;
         this.pos = Position(-1, 0, -1, fn, text);
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
    
    public Vector<Token> makeToken(){
        Vector<Token> tokens = new Vector<Token>();
        
        while (this.current_char != (char)0){
            if (this.current_char == ' ' || this.current_char == '\t')
                this.advance();
            else if (isDigit(this.current_char))
                tokens.add(this.makeNumber());
            else if (this.current_char == '+'){
                tokens.add(Token("PLUS"));
                this.advance();
            }
            else if (this.current_char == '-'){
                tokens.add(Token("MINUS"));
                this.advance();
            }
            else if (this.current_char == '*'){
                tokens.add(Token("MUL"));
                this.advance();
            }
            else if (this.current_char == '/'){
                tokens.add(Token("DIV"));
                this.advance();
            }
            else if (this.current_char == '('){
                tokens.add(Token("LPAREN"));
                this.advance();
            }
            else if (this.current_char == ')'){
                tokens.add(Token("RPAREN"));
                this.advance();
            }
            else
                return null;
        }
        
        return tokens;
    }
    
    Token make_number(){
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
            return Token("INT", numStr);
        else
            return Token("FLOAT", numStr);
}
