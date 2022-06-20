/**
 * class InfixToPostfix
 */
public class InfixToPostfix {
    private MYStack<String> operatorStack ;
    private MYStack<Float> operandStack ;
    private static final String OPERATORS = "+-*/()sca" ;
    private static final int [] PRECEDENCE = {1, 1 , 2 , 2 , -1 , -1 , 3 , 3 , 3} ;
    private StringBuilder postfix ;
    private String infix ;
    private String[] var ;
    public InfixToPostfix(String[] str){
        infix = str[str.length-1];
        var = str ;
    }

    /**
     * class SyntaxErrorException
     */
    public static class SyntaxErrorException extends Exception {
        /**
         * one param constructor
         * @param message the string
         */
        SyntaxErrorException(String message) {
            super(message);
        }
    }

    /**
     * evaluate the value of the post fix exp
     * @return the value of the postfix exp
     */
    public float eval(){
        int i = 0 ;
        float value ,result = 0  ;
        operandStack = new MYStack<Float>() ;
        final String pos = new String(postfix);
        try{
            String nextToken ;
            while (i < pos.length()){
                nextToken = next_token(pos,i);
                i += nextToken.length()+1;
                char firstChar = nextToken.charAt(0);
                if(Character.isDigit(firstChar)){
                    value = Float.valueOf(nextToken);
                    operandStack.push(value) ;
                }
                else if(nextToken.length() > 1 ){
                    if(Character.isDigit(nextToken.charAt(1))){
                        value = Float.valueOf(nextToken);
                        operandStack.push(value) ;
                    }
                    else if (is_s_c_a(nextToken)){
                        result = evalOp(firstChar) ;
                        operandStack.push(result);
                    }
                    else{
                        for(int j = 0 ; j < var.length-1 ; j++){
                            if(var[j].contains(nextToken)){
                                String temp = var[j].substring(nextToken.length()+3);
                                value = Float.valueOf(temp);
                                operandStack.push(value) ;
                            }
                        }
                    }
                }
                else if(isOperator(firstChar) && firstChar != 's' && firstChar != 'c' && firstChar != 'a'){
                    result = evalOp(firstChar) ;
                    operandStack.push(result);
                }
                else if(Character.isLetter(firstChar)){
                    for(int j = 0 ; j < var.length-1 ; j++){
                        if(var[j].contains(nextToken)){
                            String temp = var[j].substring(nextToken.length()+3);
                            value = Float.valueOf(temp);
                            operandStack.push(value) ;
                        }
                    }
                }
                else{
                    throw new SyntaxErrorException ("Invalid character encountered : " + firstChar) ;
                }
            }
            float answer = operandStack.pop() ;
            if (operandStack.isempty()) {
                return answer ;
            } else {
                throw new SyntaxErrorException ("Syntax Error : Stack should be empty") ;
            }
        }catch (SyntaxErrorException e){
            System.out.println(e);
        }
        return 0;
    }

    /**
     * convert from infix to postfix
     * @return return the postfix exp as a string
     * @throws SyntaxErrorException syntax erorr
     */
    public String convert() throws SyntaxErrorException {
        operatorStack = new MYStack<String>();
        postfix = new StringBuilder();
        int i = 0 ;
        try{
            String nextToken ;
            while(i < infix.length()){
                nextToken = next_token(infix,i);
                i += nextToken.length()+1;
                Character firstChar = nextToken.charAt(0) ;
                if(is_sin_cos_abs(nextToken)){
                    nextToken = nextToken.substring(0,3);
                    processOperator(nextToken);
                    i-=2;
                }
                else if(Character.isJavaIdentifierStart(firstChar) || Character.isDigit(firstChar)){

                    postfix.append(nextToken) ;
                    postfix.append(' ') ;
                }
                else if (isOperator(firstChar)) {
                    if (nextToken.length() > 1 ) {
                        char secondchar = nextToken.charAt(1);
                        if ((firstChar == '-' || firstChar == '+') &&
                                Character.isJavaIdentifierStart(secondchar) || Character.isDigit(secondchar)) {
                            postfix.append(nextToken);
                            postfix.append(' ');
                        }
                    }
                    else {
                        processOperator(firstChar.toString());
                    }
                }
                else {
                        throw new SyntaxErrorException("Unexpected Character Encountered : "+ firstChar) ;
                }
            }
            while (!operatorStack.isempty()){
                String op = operatorStack.pop();
                if (op.compareTo("(") == 0 )
                    throw new SyntaxErrorException ("Unmatched openi ng parenthesi s") ;
                postfix.append(op) ;
                postfix.append(' ') ;
            }
            return postfix.toString() ;
        }catch (SyntaxErrorException e){
            System.out.println(e);
        }
        return postfix.toString() ;
    }

    /**
     *
     * @param op the operator
     */
    private void processOperator (String op) {
        if (operatorStack.isempty() || op.charAt(0) == '(') {
            operatorStack.push(op);
        } else {
            String topOp = operatorStack.peek();
            if (precedence(op.charAt(0)) > precedence(topOp.charAt(0))) {
                operatorStack.push(op);
            } else {
                while (!operatorStack.isempty() && precedence(op.charAt(0)) <= precedence(topOp.charAt(0))) {
                    operatorStack.pop();
                    if (topOp.compareTo("(")==0) {
                        break;
                    }
                    postfix.append(topOp);
                    postfix.append(' ');
                    if ( !operatorStack.isempty()) {
                        topOp = operatorStack.peek() ;
                    }
                }
                if (op.charAt(0) != ')' )
                    operatorStack.push(op) ;

            }
        }
    }


    private boolean isOperator(char ch) {
        return OPERATORS.indexOf(ch) != -1;
    }
    private int precedence(char op) {
        return PRECEDENCE [OPERATORS.indexOf(op)];
    }
    private String next_token(String str , int i){
        String temp = "";
            while (i < str.length() && str.charAt(i) != ' '){
                temp += str.charAt(i) ;
                i++;
            }
        return temp ;
    }

    private boolean is_sin_cos_abs (String str){
        return (str.compareTo("sin(") == 0 || str.compareTo("cos(") == 0 || str.compareTo("abs(") == 0);
    }
    private boolean is_s_c_a(String str){
        return (str.compareTo("sin") == 0 || str.compareTo("cos") == 0 || str.compareTo("abs") == 0);
    }

    private float evalOp(char op){
        float resault = 0 ;
        float rhs =0, lhs =0;
        rhs = operandStack.pop() ;
        if(op != 's' && op != 'c' && op != 'a')
            lhs = operandStack.pop() ;
        switch (op){
            case '+' : resault = lhs + rhs ;
                        break;
            case '-' : resault = lhs - rhs ;
                        break;
            case '*' : resault = lhs * rhs ;
                        break;
            case '/' : resault = lhs / rhs ;
                        break;
            case 's' : resault = math.sin(rhs)  ;
                        break;
            case 'c' : resault = math.cos(rhs)  ;
                        break;
            case 'a' : resault = math.abs(rhs)  ;
                        break;
        }
        return  resault ;
    }
}
