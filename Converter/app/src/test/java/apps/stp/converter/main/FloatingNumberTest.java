package apps.stp.converter.main;

import apps.stp.converter.util.Misc;
import org.junit.Test;

public class Kekejfkef {
    @Test
    public void main(){
//        String resultNumber = convertNumber("-17.875", "10", "16");
//        String resultNumber = convertNumber("AA.4", "16", "10");
        String resultNumber = convertNumber("010100111.10101", "2", "10");
        System.out.println(resultNumber);
    }

   public String convertNumber(String inputNumber, String inputNumberSystem, String resultNumberSystem) {
        if(!inputNumber.contains(Misc.DOT_SYMBOL)) {
            String convertedNumber = convertNumberNoDot(inputNumber, inputNumberSystem, resultNumberSystem);
            return convertedNumber.toUpperCase();
        }
        String [] splitNumber = inputNumber.split("\\" + Misc.DOT_SYMBOL);
        String leftPart = convertNumberNoDot(splitNumber[0], inputNumberSystem, resultNumberSystem);
        String rightPart = getNumberAfterDot(splitNumber[1], inputNumberSystem, resultNumberSystem);
        return leftPart + "." + rightPart.toUpperCase();
    }

    private String convertNumberNoDot(String inputNumber, String inputNumberSystem, String resultNumberSystem){
        String result = Integer.toString(Integer.parseInt(inputNumber, Integer.parseInt(inputNumberSystem)), Integer.parseInt(resultNumberSystem));
        return result;
    }

    private String getNumberAfterDot(String numberAfterDot, String inputNumberSystem, String resultNumberSystem){
        if(Integer.valueOf(resultNumberSystem) == 10) {
            return convertNumberNoDot(numberAfterDot, inputNumberSystem, resultNumberSystem);
        }
        int resultNumberAfterDot = (int) (Double.parseDouble(resultNumberSystem) * Double.parseDouble("0." + numberAfterDot));
        return convertNumberNoDot(String.valueOf(resultNumberAfterDot), inputNumberSystem, resultNumberSystem);
    }
}
