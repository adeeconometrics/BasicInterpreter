/**
 * @brief contains methods for token types and TokenClass constructor
 */

public class Tokens {
    //      ENUM CLASS FOR BASIC TOKEN TYPE
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
    
    //      ACCEPTS INTEGER VALUE
    public Tokens(TokenType type, Integer value){
        this.int_value = value;
        this.type = type;
    }
    
    //      ACCEPTS FLOAT/DOUBLE VALUE
    public Tokens(TokenType type, Double value){
        this.float_value = value;
        this.type = type;
    }
    
    //      ELSE IF VALUE IS NULL
    public Tokens(TokenType type){
        this.type = type;
    }
    
    //      TOKEN OUTPUT FORMAT
    public String toStringToken(){
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
