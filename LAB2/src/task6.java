import java.util.Scanner;

public class task6
{
    public static int SummOfMatrix(int[][] Matrix)
    {
        int Summ = 0;
        for (int i = 0; i < Matrix.length; i++)
        {
            for (int j = 0; j < Matrix[i].length; j++)
            {
                Summ += Matrix[i][j];
            }
        }
        return Summ;
    }

    public static void main(String[] args)
    {
        //6 задание. сумма всех элементов массива
        Scanner scan = new Scanner(System.in);
        System.out.println("Задание №6");
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

        int result = SummOfMatrix(Matrix);
        System.out.println("Сумма всех элементов равна " + result);
    }
}