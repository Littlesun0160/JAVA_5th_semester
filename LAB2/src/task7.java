import java.util.Arrays;
import java.util.Scanner;

public class task7
{
    public static int[] MaxElement(int[][] Matrix)
    {
        int[] MaxElem = new int[Matrix.length];
        for (int i=0; i < Matrix.length; i++)
        {
            int max = Matrix[i][0];
            for (int j = 0; j < Matrix[i].length; j++)
            {
                max = Math.max(max, Matrix[i][j]);
            }
            MaxElem[i] = max;
        }
        return  MaxElem;
    }

    public static void main(String[] args)
    {
        //7 задание. максимальный элемент в строке
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №7");
        System.out.print("Введите количество строк: ");
        int Size1 = scan.nextInt();
        System.out.print("Введите количество столбцов: ");
        int Size2 = scan.nextInt();
        int [][] Matrix = new int[Size1][Size2];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < Size1; i++)
        {
            for (int j=0; j<Size2; j++)
            {
                Matrix[i][j] = scan.nextInt();
            }
        }

        int[] result = MaxElement(Matrix);
        System.out.println("Наибольшие элементы: " + Arrays.toString(result));
    }
}