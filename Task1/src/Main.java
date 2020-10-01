import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    long amOfCombinations;
    long sum;
    Long[] cashData;

    public static Long inputSumValue(int level, InputStream stream){
        Scanner in = new Scanner(stream);
        String input;
        System.out.print("Введите желаемую сумму: ");
        input = in.nextLine();
        long sum;
        try {
            long inputVal=handleInput(input.trim().split("\\s+")[0]);
            sum = inputVal;
            return sum;
        } catch (NumberFormatException e) {
            if(level>50) {
                System.out.println("Сделано слишком много ошибок. Перезапустите программу");
                throw (e);
            }
            else{
                System.out.println("Введено некорректное значение. Попробуйте еще раз.");
                inputSumValue(level+1,stream);
            }
        }
        return null;
    }

    public static Long[] inputNominals( InputStream stream) {
        SortedSet<Long> cashData = new TreeSet<>();
        Scanner in = new Scanner(stream);
        String input;
        System.out.print("Введите номиналы: ");
        String [] inputArray = in.nextLine().trim().split("\\s+");
        in.close();
        for (int i=0;i<inputArray.length;i++){
            try {
                long inputVal = handleInput(inputArray[i]);
                if(inputVal==0)
                    System.out.println("0 - недопустимое значение номинала. Оно будет пропущено");
                else
                    cashData.add(inputVal);
            } catch (NumberFormatException e) {
                System.out.println("Введены запрещенные символы. Значение проигнорированно");

            }
        }
        //this.cashData = cashData.toArray(new Long[0]);
        return cashData.toArray(new Long[0]);
    }

    public static Long handleInput(String input) {
        long ans;

        if (input.matches("[+-]?\\d+\\.\\d*"))
            ans = Long.parseLong((input.split("\\."))[0]);
        else if (input.matches("[+-]?\\d+,\\d*"))
            ans = Long.parseLong((input.split(","))[0]);
        else if (input.matches("-\\d*"))
            ans = Long.parseLong(input);
        else
            return Long.parseLong(input);

        if (Long.signum(ans) == -1) {
            System.out.println("Было введено отрицательное число. Программа будет работать с его модулем.");
            return Math.abs(ans);
        } else
            return ans;
    }

    public static long exchangeWays(long sum, Long[] cashData, Long[] currentCombination,  int recLevel) {
        if (recLevel == 0) {
            if (sum % cashData[0] == 0) {

                currentCombination[0] = sum / cashData[0];
                for (int i = 0; i < cashData.length; i++) {
                    System.out.print(currentCombination[i] + " of " + cashData[i] + " bills; ");
                }
                System.out.println('\n');
                return 1;
            }else
                return 0;
        } else {
            long diffCoeff = sum / cashData[recLevel];
            long thisLevelCombination = 0;
            currentCombination[recLevel] = diffCoeff;
            for (long i = currentCombination[recLevel]; i >= 0; i--) {
                //  System.out.println("Sum = "+sum);
                currentCombination[recLevel] = i;
                thisLevelCombination += exchangeWays((sum - i * cashData[recLevel]),cashData, currentCombination, recLevel - 1);
            }
            return thisLevelCombination;
        }
    }

    public static void main(String[] args) {

        Main mainFunc = new Main();
        long sum = inputSumValue(0,System.in);
        Long[] cashData = inputNominals(System.in);

        if (cashData.length == 0 || sum==0)
            System.out.println("There are no ways to exchange a given sum");
        else {
            int size = cashData.length;
            //mainFunc.amOfCombinations = 0;
            Long[] currentCombination = new Long[size];
            for (int i = 0; i < size; i++) {
                currentCombination[i] = (long) 0;
            }

           long amOfComb = exchangeWays(sum, cashData, currentCombination, size - 1);
            System.out.println("Finally, there are  " + amOfComb + " ways to exchange a given sum");
        }
    }
}
