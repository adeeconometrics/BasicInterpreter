/**
 * @brief contains methods for token types and TokenClass constructor
 */

package src;

public class TokenClass {
    enum TokenType{
        INT ,
        FLOAT ,
        PLUS ,
        MINUS ,
        MUL ,
        DIV ,
        LPAREN ,
        RPAREN ,
    }
    
    TokenType type;
    Integer int_value = null;
    Double float_value = null;

    public TokenClass(TokenType type, Integer value){
        this.int_value = value;
        this.type = type;
    }

    public TokenClass(TokenType type, Double value){
        this.float_value = value;
        this.type = type;
    }

    public TokenClass(TokenType type){
        this.type = type;
    }
    
    
    public String toString(){
        if(this.float_value == null && this.int_value == null){
            return this.type.toString();
        }else{
            if(this.float_value == null && this.int_value != null){
                return this.type.toString() + ":" + Integer.toString(this.int_value);
            }else{
                return this.type.toString() + ":" + Double.toString(this.float_value);
            }
        }
    }
}
