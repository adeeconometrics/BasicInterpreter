//there is no __init__ method in java
// what i used is the 'this' keyword in the instances method or in constructor.

public class Position {
	   
	   private int idx;
	   private int ln;
	   private int col;
	   private String fn;
	   private String ftxt;

	    public Position(int idx,int ln, int col,String fn, String ftxt) {
	        this.idx = idx;
	        this.ln = ln;
	        this.col = col;
	        this.fn = fn;
	        this.ftxt = ftxt;
	             
	    }
	    
	   public void advance(char current_char) {  // for iteration
		   this.idx += 1; 
		   this.col += 1;
		   
		   if (current_char == '\n') {
		 	this.ln += 1;
		 	this.col =0;	   
		   }   
	   }
	   

	   public Position copy() {
		   return new Position (this.idx, this.ln, this.col, this.fn, this.ftxt);
		  
	   }
}
