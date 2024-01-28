
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class MethodManager {


    void hybridMethod(String filepath, int methodSwitchThreshold) {
        methodSwitchThreshold = Math.min(methodSwitchThreshold, 100);
        ENumber eNumber = new ENumber("77");
        ThresholdReader thresholdReader = new ThresholdReader();

        //reading input file
        List<Integer> thresholds = null;
        try {
            thresholds = thresholdReader.readThresholds(filepath);
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }

        if (thresholds != null) {
            thresholds = thresholds.stream()
                    .sorted()
                    .toList();


            HashMap<Integer, ENumber> integerENumberMap = new HashMap<>();
            int totalElementsAmount = thresholds.get(thresholds.size() - 1);
            int[] assignment = new int[totalElementsAmount + 1];
            assignment[0] = -1;

            int numbersWithNoERepresentation = 0;
            for (int i = 1; i < totalElementsAmount + 1; i++) {
                if (i % 5 == 0 || i % 16 == 0) {
                    assignment[i] = -1;
                    numbersWithNoERepresentation++;
                }
            }

            // filling out assignment array
            int currentMapElement = 1;
            boolean elementAddedInCurrentLoopIteration;
            BigInteger bigIntegerOf0 = new BigInteger("0");
            BigInteger bigIntegerOf1 = new BigInteger("1");
            BigInteger bigIntegerOfTopThreshold = new BigInteger(Integer.toString(totalElementsAmount));




            ProcessProgressPrinter processProgressPrinter = new ProcessProgressPrinter(totalElementsAmount - numbersWithNoERepresentation);
            int numbersCalculated = 0;
            int numbersToCalculate = (int)((totalElementsAmount - numbersWithNoERepresentation) * (methodSwitchThreshold/100.0));

            while (numbersCalculated < numbersToCalculate) {
                elementAddedInCurrentLoopIteration = false;
                ArrayList<Integer> divisors = MyMath.findDivisors(eNumber.getNumber(), bigIntegerOf0, bigIntegerOf1, bigIntegerOfTopThreshold);

                for (int divisor : divisors) {
                    if (assignment[divisor] == 0) {
                        assignment[divisor] = currentMapElement;//tu trzeba włożyć wartośc z przypisania do tablicy
                        numbersCalculated++;
                        elementAddedInCurrentLoopIteration = true;
                    }
                }
                if (elementAddedInCurrentLoopIteration) {
                    integerENumberMap.put(currentMapElement, new ENumber(eNumber));
                    currentMapElement++;
                }

                processProgressPrinter.printProgress(numbersCalculated);
                eNumber.increment();
            }



            int mapElementOnSwitch = currentMapElement++;
            for (int i = 0; i < assignment.length; i++) {
                if(assignment[i] == 0) {
                    ENumber find = ENumber.findNumber(i);

                    for (Map.Entry<Integer, ENumber> entry : integerENumberMap.entrySet()) {//looking if this enumber is in map
                        if (entry.getValue().equals(find)) {// if it is in map, put its corresponding integer into assignment array
                            assignment[i] = entry.getKey();
                        }
                    }
                    if (assignment[i] == 0) {//it it isnt found, put it to map
                        assignment[i] = currentMapElement;
                        integerENumberMap.put(currentMapElement, new ENumber(find));
                        currentMapElement++;
                    }
                    numbersCalculated++;
                }
                processProgressPrinter.printProgress(numbersCalculated);
            }




            System.out.println("\n");
            for (int threshold : thresholds) {
                int noENumber = 0;

                for (int i = 0; i < threshold; i++) {
                    if (assignment[i] == -1) {
                        noENumber++;
                    }
                }

                integerENumberMap.put(-1, new ENumber("77"));
                int biggestENumberCorrespondingNormalNumber = 0;
                ENumber biggestENumber = new ENumber("77");
                for (int i = 0; i < threshold; i++) {
                    if (integerENumberMap.get(assignment[i]).compareTo(biggestENumber) > 0) {
                        biggestENumber = new ENumber(integerENumberMap.get(assignment[i]));
                        biggestENumberCorrespondingNormalNumber = i;
                    }
                }
                System.out.println(noENumber + " " + biggestENumberCorrespondingNormalNumber + " " + biggestENumber);
            }
            System.out.println();

        }
    }


    void linearMethod(String filepath) {
        ENumber eNumber = new ENumber("77");
        ThresholdReader thresholdReader = new ThresholdReader();

        //reading input file
        List<Integer> thresholds = null;
        try {
            thresholds = thresholdReader.readThresholds(filepath);
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }

        if (thresholds != null) {
            thresholds = thresholds.stream()
                    .sorted()
                    .toList();


            HashMap<Integer, ENumber> integerENumberMap = new HashMap<>();
            int totalElementsAmount = thresholds.get(thresholds.size() - 1);
            int[] assignment = new int[totalElementsAmount + 1];
            assignment[0] = -1;

            int numbersWithNoERepresentation = 0;
            for (int i = 1; i < totalElementsAmount + 1; i++) {
                if (i % 5 == 0 || i % 16 == 0) {
                    assignment[i] = -1;
                    numbersWithNoERepresentation++;
                }
            }

            // filling out assignment array
            int currentMapElement = 1;
            boolean elementAddedInCurrentLoopIteration;
            BigInteger bigIntegerOf0 = new BigInteger("0");
            BigInteger bigIntegerOf1 = new BigInteger("1");
            BigInteger bigIntegerOfTopThreshold = new BigInteger(Integer.toString(totalElementsAmount));


            int elementsToCalculate = totalElementsAmount - numbersWithNoERepresentation;
            ProcessProgressPrinter processProgressPrinter = new ProcessProgressPrinter(elementsToCalculate);
            int numbersCalculated = 0;


            while (numbersCalculated < elementsToCalculate) {
                elementAddedInCurrentLoopIteration = false;
                ArrayList<Integer> divisors = MyMath.findDivisors(eNumber.getNumber(), bigIntegerOf0, bigIntegerOf1, bigIntegerOfTopThreshold);

                for (int divisor : divisors) {
                    if(assignment[divisor] == 0) {
                        assignment[divisor] = currentMapElement;//tu trzeba włożyć wartośc z przypisania do tablicy
                        numbersCalculated++;
                        elementAddedInCurrentLoopIteration = true;
                    }
                }
                if (elementAddedInCurrentLoopIteration) {
                    integerENumberMap.put(currentMapElement, new ENumber(eNumber));
                    currentMapElement++;
                }

                processProgressPrinter.printProgress(numbersCalculated);
                eNumber.increment();
            }

            //Printing end results
            System.out.println("\n");
            for (int threshold : thresholds) {
                int noENumber = 0;
                int largestNumberIndex = 0;

                for (int i = 0; i < threshold; i++) {
                    if (assignment[i] == -1) {
                        noENumber++;
                    }
                    if (assignment[i] > assignment[largestNumberIndex]) {
                        largestNumberIndex = i;
                    }
                }
                System.out.println(noENumber + " " + largestNumberIndex + " " + integerENumberMap.get(assignment[largestNumberIndex]));
            }
            System.out.println();

        }
    }

}
