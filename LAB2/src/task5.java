import java.util.Scanner;
import java.util.Arrays;

public class task5
{
    public static int[] FindElements(int[] Array, int target)
    {
        int Index=0;
        for (int i = 0; i < Array.length-1; i++)
        {
            for (int j = i+1; j < Array.length; j++)
            {
                if (Array[i] + Array[j] == target)
                {
                    return new int[]{Array[i],Array[j]};
                }
            }
        }
        return null;
    }

    public static void main(String[] args)
    {
        //5 задание. сумма двух элементов
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №5");
        System.out.print("Введите длину массива: ");
        int Size = scan.nextInt();
        int [] Array = new int[Size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < Size; i++)
        {
            Array[i] = scan.nextInt();
        }
        System.out.print("Введите необходимую сумму: ");
        int target = scan.nextInt();

        int[] result = FindElements(Array, target);
        if(result == null)
        {
            System.out.println("Необходимой пары не найдено!");
        }
        else {
            System.out.println("Итого найдена пара: " + Arrays.toString(result));
        }


    }
}
