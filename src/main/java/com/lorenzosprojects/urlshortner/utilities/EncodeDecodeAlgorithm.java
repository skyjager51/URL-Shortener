package com.lorenzosprojects.urlshortner.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EncodeDecodeAlgorithm {

    //mapping for the values of the Base62 encoding/decoding
    public final Map<Long, Character> BASE62_MAP = new HashMap<Long, Character>() {{
            put(0L, '0'); put(1L, '1'); put(2L, '2'); put(3L, '3'); put(4L, '4');
            put(5L, '5'); put(6L, '6'); put(7L, '7'); put(8L, '8'); put(9L, '9');
            put(10L, 'A'); put(11L, 'B'); put(12L, 'C'); put(13L, 'D'); put(14L, 'E');
            put(15L, 'F'); put(16L, 'G'); put(17L, 'H'); put(18L, 'I'); put(19L, 'J');
            put(20L, 'K'); put(21L, 'L'); put(22L, 'M'); put(23L, 'N'); put(24L, 'O');
            put(25L, 'P'); put(26L, 'Q'); put(27L, 'R'); put(28L, 'S'); put(29L, 'T');
            put(30L, 'U'); put(31L, 'V'); put(32L, 'W'); put(33L, 'X'); put(34L, 'Y');
            put(35L, 'Z'); put(36L, 'a'); put(37L, 'b'); put(38L, 'c'); put(39L, 'd');
            put(40L, 'e'); put(41L, 'f'); put(42L, 'g'); put(43L, 'h'); put(44L, 'i');
            put(45L, 'j'); put(46L, 'k'); put(47L, 'l'); put(48L, 'm'); put(49L, 'n');
            put(50L, 'o'); put(51L, 'p'); put(52L, 'q'); put(53L, 'r'); put(54L, 's');
            put(55L, 't'); put(56L, 'u'); put(57L, 'v'); put(58L, 'w'); put(59L, 'x');
            put(60L, 'y'); put(61L, 'z');
        }};


    //function to encode the id
    public String encodeAlgorithm(String id){

        //taking the id and transforming it into a character array for the bit conversion
        char[] toCharArray = id.toCharArray();
        String convertedString = "";

        //looping inside the array and converting each char to its binary value
        for (char letter : toCharArray){
            int l = (int) letter;

            //giving the right lenght to cutoff binary values
            String formatString = Integer.toBinaryString(l);

            while(formatString.length() < 8){
                formatString = "0" + formatString;
            }
            convertedString = convertedString.concat(formatString);
        }

        //counter to keep track of the base 10 transformation of the value 
        long counter = 0;

        //tranforming the value to a char array
        char[] charSet = convertedString.toCharArray();

        //looping in the array to convert the value into a base 10 number 
        for (int i = charSet.length -1; i >= 0; i--){

            long exponent = charSet.length -1-i;
            
            long charSetValue = Character.getNumericValue(charSet[i]);

            long DecimalValue = (long)(charSetValue * Math.pow(2, exponent));

            counter = counter + DecimalValue;

        }

        //calculating the converted base62 value
        List<Long> base62Value = new ArrayList<>();

        while(counter > 0){
            base62Value.add(counter % 62);
            counter = counter / 62;
        }

        //map the list elements to create the encoded base62 value
        StringBuilder encodedValue = new StringBuilder();

        for(int i = base62Value.size() -1; i >= 0; i--){
            Long conversionValue = base62Value.get(i);

            if (BASE62_MAP.get(conversionValue) != null){
                encodedValue.append(BASE62_MAP.get(conversionValue));
            }
            else{
                System.out.println("Log: long value does not exist in Bae62 map");
            }
        }
        
        return encodedValue.toString();
    }


    //decode funciton
    public String decodeAlgorithm(String value){
        char[] toCharArray = value.toCharArray();
         
        long counter = 0;

        //looping to decode the value from Base62 to base 10.
        for (int i = toCharArray.length -1; i >= 0; i--){

            long exponent = toCharArray.length -1-i;
            
            //create the base 10 value form the base62 table (taking the keys)
            long charSetDecimalValue = 0;
            for(Map.Entry<Long, Character> entry : BASE62_MAP.entrySet()){
                if (entry.getValue().equals(toCharArray[i])){
                    charSetDecimalValue = entry.getKey();
                }
            }

            //converitng the numbers (still referenced in base62) to be the values on base 10
            long DecimalValue = (long)(charSetDecimalValue * Math.pow(62, exponent));

            counter = counter + DecimalValue;

        }
    
        //convert the integer (base 10) back to a string
        StringBuilder originalString = new StringBuilder();
        long tempNumber = counter;
        while(tempNumber > 0){
            //get the last characther ascii value
            char c = (char) (tempNumber % 256);

            //append the result
            originalString.append(c);

            //reduce the number to get the next char
            tempNumber /= 256;
        }

        return originalString.reverse().toString();
    }

}
