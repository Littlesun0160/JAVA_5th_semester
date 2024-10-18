import java.util.Scanner;
import java.util.Arrays;

public class task3
{
    public static void main(String[] args)
    {
        //3 задание. максимальная сумма подмассива
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №3");
        System.out.print("Введите длину массива: ");
        int Size = scan.nextInt();
        int [] Array = new int[Size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < Size; i++)
        {
            Array[i] = scan.nextInt();
        }

        int StartIndex = 0;
        int result = Array[0];
        while (StartIndex < Size-1)
        {
            for (int k=StartIndex; k<Size; k++)
            {
                int InterSumm = Array[StartIndex];
                for (int j = StartIndex; j < k; j++)
                {
                    InterSumm += Array[j];
                }
                if (result < Math.max(InterSumm,Array[StartIndex]))
                {
                    result = Math.max(InterSumm,Array[StartIndex]);
                }
            }
            StartIndex++;
        }

        System.out.println("Наибольшая сумма равна " + result);
    }
}
