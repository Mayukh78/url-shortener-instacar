package com.example.urlshortener;

import java.util.concurrent.ThreadLocalRandom;

public class CodeDetails {
    private int  code_char_limit = 7; //maximum character limit of shortened_code
    private int radix = 62;
    private long maximum = (long) Math.pow(radix, code_char_limit); //generate what can be max value of combination
    private String code;
    private final static String dict="0123456789"+"ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz"; //maintain different digits that are possible

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //This method generate a random number and convert it to Base62
    public void codeGeneration()
    {
        StringBuilder code=new StringBuilder();
        long number = ThreadLocalRandom.current().nextLong(1000000, maximum); //generate a random number between the range

        //logic to convert to Base62
        while(number>0)
        {
            int digit= (int)(number %62);
            number=number/62;
            char c= dict.charAt(digit);
            code.append(c);
        }
        this.code = code.reverse().toString();
    }
}
