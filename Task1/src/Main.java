import java.io.*;
import java.util.*;


public class Main {

    public static Long inputSumValue(InputStream stream){
        Scanner in = new Scanner(stream);
        String input;
        System.out.print("Введите желаемую сумму: ");
        input = in.nextLine();
        long sum;
        try {

            sum =  handleInput(input.trim().split("\\s+")[0]);
            return sum;

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Введено некорректное значение для суммы");

        }

    }

    public static Long[] inputNominals( InputStream stream) {
        SortedSet<Long> cashData = new TreeSet<>();
        Scanner in = new Scanner(stream);

        System.out.print("Введите номиналы: ");
        String [] inputArray = in.nextLine().trim().split("\\s+");
        in.close();
        for (String s : inputArray) {
            try {
                long inputVal = handleInput(s);
                if (inputVal == 0)
                    System.out.println("0 - недопустимое значение номинала. Оно будет пропущено");
                else
                    cashData.add(inputVal);
            } catch (NumberFormatException e) {
                System.out.println("Введены запрещенные символы. Значение проигнорированно");

            }
        }

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
                System.out.print("Новая комбинация: ");
                for (int i = 0; i < cashData.length; i++) {
                    if(currentCombination[i]>0){
                        System.out.print(currentCombination[i]);
                        if(currentCombination[i]%10==1)
                            System.out.print(" купюра номиналом ");
                        else if (currentCombination[i]%10 >=2 && currentCombination[i]%10 <=4)
                            System.out.print(" купюры номиналом ");
                        else
                            System.out.print(" купюр номиналом ");
                        System.out.print(cashData[i]+"; ");
                    }
                }
                System.out.println("\n");
                return 1;
            }else
                return 0;
        } else {
            long diffCoeff = sum / cashData[recLevel];
            long thisLevelCombination = 0;
            currentCombination[recLevel] = diffCoeff;
            for (long i = currentCombination[recLevel]; i >= 0; i--) {
                currentCombination[recLevel] = i;
                thisLevelCombination += exchangeWays((sum - i * cashData[recLevel]),cashData, currentCombination, recLevel - 1);
            }
            return thisLevelCombination;
        }
    }

    public static void main(String[] args) {

        long sum = inputSumValue(System.in);
        Long[] cashData = inputNominals(System.in);
        if (cashData.length == 0 || sum==0)
            System.out.println("Не существует способов размена суммы " + sum + " номиналами "+Arrays.toString(cashData));
        else {
            int size = cashData.length;

            Long[] currentCombination = new Long[size];
            for (int i = 0; i < size; i++) {
                currentCombination[i] = (long) 0;
            }

           long amOfComb = exchangeWays(sum, cashData, currentCombination, size - 1);
            System.out.println("Итого, существует  " + amOfComb + " способов разменять сумму номиналами "+ Arrays.toString(cashData));
        }
    }
}
