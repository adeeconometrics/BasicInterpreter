package src;

import java.util.HashMap;
import java.util.ArrayList;


enum TokenType { 
    LET, DATA, READ, RESTORE, DIM,
     
    IF, THEN, ELSE, FOR, TO, STEP, NEXT, WHILE, WEND, REPEAT,
    UNTIL, DO, LOOP, GOTO, ON, GOSUB, DEF, FN, 

    LIST, PRINT, INPUT, TAB, SPC,
    REM, USR, CALL, TRON, TROFF, ASM,
    ABS, ATN, COS, EXP, LOG, RND, SIN, TAN, SQR, 
    INT, FLOAT
}

class TokenClass {

    final TokenType type; 
    Integer integer_val = null; // should be set to const
    Float float_val = null; // should be set to const

    public TokenClass(TokenType type, Integer value){
        this.integer_val = value;
        this.type = type;
    }

    public TokenClass(TokenType type, Float value) {
        this.float_val = value;
        this.type = type;
    }

    public TokenClass(TokenType type) {
        this.type = type; 
    }

}


public class Scanner{
    private static final HashMap<String, TokenType> keywords;
    
    static {
        keywords =  new HashMap<>();
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

    private final ArrayList<TokenClass> tokens = new ArrayList<>(); 
    private final String source; 
    private int start = 0;
    private int current = 0;
    private int line = 1; 

    Scanner(String soruce){
        this.source = source;
    }

    ArrayList<TokenClass> scanTokens(){

    }

    private char advance(){

    }

    private void addToken(TokenType type){

    }

    private void addToken(TokenType type, Object literal){

    }

    private boolean isAlphaneumetric(char c){

    }

    private boolean isDigit(char c) {

    }

    private boolean isAlpha(char c){

    }
    
    private boolean isAtEnd(){
        
    }
}