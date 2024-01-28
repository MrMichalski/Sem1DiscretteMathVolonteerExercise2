import java.math.BigInteger;
import java.util.*;

public class MyMath {

    static ArrayList<Integer> findDivisors2(BigInteger num, BigInteger bigIntegerOf0, BigInteger bigIntegerOf1, BigInteger bigIntegerOfTopThreshold) {
        ArrayList<Integer> divisors = new ArrayList<>();

        BigInteger sqrt = num.sqrt();
        sqrt = sqrt.compareTo(bigIntegerOfTopThreshold) < 0 ? sqrt : bigIntegerOfTopThreshold;
        for (BigInteger i = new BigInteger("1"); i.compareTo(sqrt) <= 0;  i = i.add(bigIntegerOf1)) {
            if (num.remainder(i).compareTo(bigIntegerOf0) == 0) {
                divisors.add(i.intValue());
                BigInteger otherDivisor = num.divide(i);
                if (!otherDivisor.equals(i)) {
                    divisors.add(otherDivisor.intValue());
                }
            }
        }
        return divisors;
    }

    static ArrayList<Integer> findDivisors(BigInteger num, BigInteger bigIntegerOf0, BigInteger bigIntegerOf1, BigInteger bigIntegerOfTopThreshold) {
        ArrayList<Integer> divisors = new ArrayList<>();
        BigInteger sqrt = num.sqrt();
        boolean shouldStartAddingSecondDivisors = false;
        if (sqrt.compareTo(bigIntegerOfTopThreshold) < 0) {
            for (BigInteger i = new BigInteger("1"); i.compareTo(sqrt) < 0; i = i.add(bigIntegerOf1)) {
                if (num.remainder(i).compareTo(bigIntegerOf0) == 0) {
                    divisors.add(i.intValue());
                    BigInteger otherDivisor = num.divide(i);
                    if(!shouldStartAddingSecondDivisors && otherDivisor.compareTo(bigIntegerOfTopThreshold) <= 0) {
                        shouldStartAddingSecondDivisors = true;
                    }
                    if (shouldStartAddingSecondDivisors) {
                        divisors.add(otherDivisor.intValue());
                    }
                }
            }
            if(num.remainder(sqrt).compareTo(bigIntegerOf0) == 0) {
                divisors.add(sqrt.intValue());
            }
        } else {
            for (BigInteger i = new BigInteger("1"); i.compareTo(bigIntegerOfTopThreshold) <= 0; i = i.add(bigIntegerOf1)) {
                if (num.remainder(i).compareTo(bigIntegerOf0) == 0) {
                    divisors.add(i.intValue());
                }
            }
        }
        return divisors;
    }

    static int comparison(HashMap<Integer, ENumber> map, int key1, int key2) {
        if(key1 == -1 || key2 == -1) {

        }

        BigInteger val1 = map.get(key1).getNumber();
        BigInteger val2 = map.get(key2).getNumber();
        int length1 = val1.toString().length();
        int length2 = val2.toString().length();
        int compare = val1.compareTo(val2);//is val1 greater than val2? if yes, 1 if no, -1
        if (length1 == length2) {
            if (compare < 0) {
                return key1;
            } else {
                return key2;
            }
        } else {
            if (compare > 0) {
                return key1;
            } else {
                return key2;
            }
        }
    }







    static ArrayList<Integer> findAllDivisor2(int num) {// this method was used for testing purposes
        ArrayList<Integer> divisors = new ArrayList<>();

        int sqrt = (int) Math.sqrt(num);
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }

    static HashSet<Integer> multiplyAllPiecesVariations(List<Integer> pieces) {
        HashSet<Integer> integers = new HashSet<>(pieces);

        for (int i = 2; i <= pieces.size(); i++) {
            integers.addAll(mulitplyAllPiecesVariationsWithNElements(pieces, i));
        }
        return integers;
    }

    static HashSet<Integer> mulitplyAllPiecesVariationsWithNElements(List<Integer> pieces, int howManyPiecesPerMultiplication) { // this method returns all variations of multiplication of N ammount of pieces
        HashSet<Integer> multiplications = new HashSet<>();
        int[] indexes = new int[howManyPiecesPerMultiplication];
        int[] endingIndexes = new int[howManyPiecesPerMultiplication];
        int indexesMaxIndex = indexes.length - 1;
        for (int i = 0; i <= indexesMaxIndex; i++) {//this loop fills out the indexes array
            indexes[i] = i;
        }
        indexes[indexesMaxIndex]--;

        {//this block of code is here so below 2 values are not visible outside it - they are only there to fill out endingIndexes
            int endingIndexesFillingIndex = indexesMaxIndex;
            int endingIndexesFillingValue = pieces.size() - 1;
            while (endingIndexesFillingIndex >= 0) {
                endingIndexes[endingIndexesFillingIndex] = endingIndexesFillingValue;
                endingIndexesFillingIndex--;
                endingIndexesFillingValue--;
            }
        }

        do {

            // this block of code brings indexes back when one or more get to their ending point
            if (indexes[indexesMaxIndex] == endingIndexes[indexesMaxIndex]) {
                int carryValue = 0;
                int carryIndex = 0;
                //System.out.println(indexesMaxIndex + "aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                for (int j = indexesMaxIndex; j >= 0; j--) {//this loop finds first index that isnt in its ending positon, sets temp to incremented value for first not last index,
                    if (endingIndexes[j] != indexes[j]) {
                        carryIndex = j;
                        carryValue = indexes[j];
                        break;
                    }
                }
                for (int j = carryIndex; j <= indexesMaxIndex; j++) {
                    carryValue++;
                    indexes[j] = carryValue;
                }
            } else {
                indexes[indexesMaxIndex]++;//this moves last index forward
            }


            //this block of code multiplies elements that are on indexes specified by indexes array
            int multiplication = 1;
            for (int j = 0; j <= indexesMaxIndex; j++) {
                multiplication *= pieces.get(indexes[j]);
            }
            multiplications.add(multiplication);


        } while (indexes[0] != endingIndexes[0]);

        return multiplications;
    }

}
