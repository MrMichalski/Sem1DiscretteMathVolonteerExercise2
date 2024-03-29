import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class ENumber {
    private BigInteger number;

    public ENumber(String val) {
        this.number = new BigInteger(val);
    }

    public ENumber(ENumber eNumber) {
        this.number = eNumber.getNumber();
    }




     void increment() {
        char[] charArray = number.toString().toCharArray();

        if (checkIfAllNine(charArray)) {
            char[] newNumber = new char[charArray.length + 2];
            incrementHelper(charArray, newNumber);
            newNumber[0] = '7';
            newNumber[1] = '7';
            number = new BigInteger(new String(newNumber));
        } else {
            incrementHelper(charArray, charArray);
            number = new BigInteger(new String(charArray));
        }
    }
    void incrementHelper(char[] oldNumber, char[] newNumber) {
        int newLength = newNumber.length;
        int oldLength = oldNumber.length;

        for (int i = 1; i < oldLength; i += 2) { // length - 2 because new number is longer by 2 than old number
            char c = ++oldNumber[oldLength - i];
            if (c == '8' || c == '9') {//if char on that index is 7 or 8
                newNumber[newLength - i] = c;
                newNumber[newLength - i - 1] = c;
                break;
            } else {
                newNumber[newLength - i] = '7';
                newNumber[newLength - i - 1] = '7';
            }
        }
    }

    static boolean checkIfAllNine(char[] eNumberCharArray) {
        for(int i = 0; i < eNumberCharArray.length - 1; i++) {
            if (eNumberCharArray[i] != '9') {
                return false;
            }
        }
        return true;
    }
    
    
    static ENumber findNumber(int num) {//converts num to E notation
        ENumber find = new ENumber("77");
        BigInteger numBigInteger = new BigInteger(String.valueOf(num));
        BigInteger zeroBigInteger = new BigInteger("0");

        for (int i = 0; i < 100000; i++) {
            if(find.getNumber().remainder(numBigInteger).compareTo(zeroBigInteger) != 0) {
                find.increment();
            } else {
                return find;
            }
        }
        return new ENumber("-1");
    }

    public BigInteger getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }

    public boolean equals(ENumber eNumber) {
        return Objects.equals(number, eNumber.number);
    }

    ArrayList<Integer> findNumbersThatThisENumberMayRepresent() {//This method was used for testing purposes
        BigInteger bigIntegerOf0 = new BigInteger("0");
        BigInteger bigIntegerOf1 = new BigInteger("1");
        BigInteger bigInteger = new BigInteger("150000");
        return MyMath.findDivisors(this.getNumber(), bigIntegerOf0, bigIntegerOf1, bigInteger);
    }

    public int compareTo(ENumber eNumber) {
        if (this.getNumber().compareTo(eNumber.getNumber()) > 0) return 1;
        if (this.getNumber().compareTo(eNumber.getNumber()) == 0) return 0;
        return -1;
    }
}
