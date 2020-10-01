import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    long amOfCombinations;
    long sum;
    Long[] cashData;

    public Long[] inputData(int level, InputStream stream) {
        SortedSet<Long> cashData = new TreeSet<>();
        Scanner in = new Scanner(stream);
        String input;
        System.out.print("Введите желаемую сумму: ");
        input = in.nextLine();
        try {
            long inputVal=handleInput(input.trim().split("\\s+")[0]);
            this.sum =inputVal;
        } catch (NumberFormatException e) {
            if(level>50) {
                System.out.println("Сделано слишком много ошибок. Перезапустите программу");
                throw (e);
            }
            else{
                System.out.println("Введено некорректное значение. Попробуйте еще раз.");
                this.inputData(level+1,stream);
            }
        }
        System.out.println("Желаемая сумма: " + this.sum);

        System.out.print("Введите номиналы: ");
        // System.out.println("Примечание: для завершения операции введите \"end\"");
        String [] inputArray = in.nextLine().trim().split("\\s+");
        for (int i=0;i<inputArray.length;i++){
            try {
                long inputVal =handleInput(inputArray[i]);
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

    public Long handleInput(String input) {
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

    public void exchangeWays(long sum, Long[] currentCombination, int recLevel) {
        if (recLevel == 0) {
            if (sum % this.cashData[0] == 0) {
                this.amOfCombinations++;
                currentCombination[0] = sum / this.cashData[0];
                for (int i = 0; i < cashData.length; i++) {
                    System.out.print(currentCombination[i] + " of " + this.cashData[i] + " bills; ");
                }
                System.out.println('\n');
            }
        } else {
            long diffCoeff = sum / this.cashData[recLevel];
            currentCombination[recLevel] = diffCoeff;
            for (long i = currentCombination[recLevel]; i >= 0; i--) {
                //  System.out.println("Sum = "+sum);
                currentCombination[recLevel] = i;
                this.exchangeWays((sum - i * this.cashData[recLevel]), currentCombination, recLevel - 1);
            }
        }
    }

    public static void main(String[] args) {

        Main mainFunc = new Main();
        mainFunc.cashData = mainFunc.inputData(0, System.in);

        if (mainFunc.cashData.length == 0 || mainFunc.sum==0)
            System.out.println("There are no ways to exchange a given sum");
        else {

            int size = mainFunc.cashData.length;
            mainFunc.amOfCombinations = 0;
            Long[] currentCombination = new Long[size];
            for (int i = 0; i < size; i++) {
                currentCombination[i] = (long) 0;
            }

            mainFunc.exchangeWays(mainFunc.sum, currentCombination, size - 1);
            System.out.println("Finally, there are  " + mainFunc.amOfCombinations + " ways to exchange a given sum");
        }
    }
}
