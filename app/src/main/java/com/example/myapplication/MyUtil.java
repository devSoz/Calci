package com.example.myapplication;

import java.util.Stack;

public class MyUtil {
    public static float  Evaluatestring(String str)
    {

            char[] tokens = str.toCharArray();

            Stack<Float> values = new
                    Stack<Float>();

            Stack<Character> ops = new
                    Stack<Character>();

            for (int i = 0; i < tokens.length; i++)
            {

                if (tokens[i] == ' ')
                    continue;

                if ((tokens[i] >= '0' &&
                        tokens[i] <= '9')||(tokens[i]=='.'))
                {
                    StringBuffer sbuf = new
                            StringBuffer();

                    while ((i < tokens.length) &&((tokens[i] >= '0' && tokens[i] <= '9') || (tokens[i]=='.')))
                        sbuf.append(tokens[i++]);
                    values.push(Float.parseFloat(sbuf.
                            toString()));

                    i--;
                }

                else if (tokens[i] == '(')
                    ops.push(tokens[i]);

                else if (tokens[i] == ')')
                {
                    while (ops.peek() != '(')
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));
                    ops.pop();
                }

                else if (tokens[i] == '+' ||
                        tokens[i] == '-' ||
                        tokens[i] == '*' ||
                        tokens[i] == '/')
                {

                    while (!ops.empty() &&
                            hasPrecedence(tokens[i],
                                    ops.peek()))
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));

                    ops.push(tokens[i]);
                }
            }


            while (!ops.empty())
                values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()));


            return values.pop();
        }


        public static boolean hasPrecedence(
        char op1, char op2)
        {
            if (op2 == '(' || op2 == ')')
                return false;
            if ((op1 == '*' || op1 == '/') &&
                    (op2 == '+' || op2 == '-'))
                return false;
            else
                return true;
        }


        public static Float applyOp(char op,
        Float b, Float a)
        {
            switch (op)
            {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0f)
                        throw new
                                UnsupportedOperationException(
                                "Cannot divide by zero");
                    return a / b;
            }
            return 0f;
        }

    public static boolean isAnOperator(char c) {
        switch (c) {
            case '*':
            case '/':
            case '+':
            case '-':
            case '%':
                return true;
            default:
                return false;
        }
    }
    public static boolean isANumber(char c){
        return ((int)c) >= 48 && ((int)c) <= 57;
    }

    public static boolean isValidExpression(String expression) {
        if (isAnOperator(expression.charAt(0)) || isAnOperator(expression.charAt(expression.length() - 1))) {
            return false;
        }

        int openParenthCount = 0;
        boolean lastWasOp = false;
        boolean lastWasOpen = false;

        for (char c : expression.toCharArray()) {
            if(c == ' ') continue;
            if (c == '(') {
                openParenthCount++;
                lastWasOpen = true;
                continue;
            } else if (c == ')') {
                if (openParenthCount <= 0 || lastWasOp) {
                    return false;
                }
                openParenthCount--;
            }else if (isAnOperator(c)){
                if (lastWasOp || lastWasOpen) return false;
                lastWasOp = true;
                continue;
            }else if(!isANumber(c)){
                return false;
            }
            lastWasOp = false;
            lastWasOpen = false;
        }
        if(openParenthCount != 0) return false;
        if(lastWasOp || lastWasOpen) return false;
        return true;
    }
    }

