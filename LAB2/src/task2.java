import java.util.Scanner;
import java.util.Arrays;

public class task2
{
    public static void main(String[] args)
    {
        //2 задание. объекдинение массивов
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №2");
        int[] Array1 = {7,10,13,15};
        int Size1 = Array1.length;
        int[] Array2 = {4,5,9,11,14,16,20};
        int Size2 = Array2.length;

        int[] result = new int[Size1+Size2];
        int Index = 0;
        int Index1 = 0; int Index2 = 0;
        while (Index1 < Size1 && Index2 < Size2)
        {
            result[Index] = Math.min(Array1[Index1], Array2[Index2]);
            if (result[Index] == Array1[Index1]){Index1++;}
            else {Index2++;}
            Index++;
        }
        while (Index1 < Size1)
        {
            result[Index] = Array1[Index1];
            Index++; Index1++;
        }
        while (Index2 < Size2)
        {
            result[Index] = Array2[Index2];
            Index++; Index2++;
        }

        System.out.println("Итоговый массив: " + Arrays.toString(result));
    }
}
