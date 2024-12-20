import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main
{
    public static void main(String[] args) {

        Board board = new Board();
        board.init();
        board.setColorGame('w');

        System.out.println();

        boolean isGame = true;
        Scanner in = new Scanner(System.in);


        while (isGame)
        {
            board.print_board();
            System.out.println();

            System.out.println("Взятые Белые: " + board.getTakeWhite().toString() );
            System.out.println("Взятые Черные: " + board.getTakeBlack().toString());
            switch (board.getColorGame())
            {
                case 'w':
                    if (board.isCheck('w'))
                    {
                        System.out.println("Объявлен шах белым!");
                    }
                    System.out.println("Ход белых: ");
                break;
                case 'b':
                    if (board.isCheck('b'))
                    {
                        System.out.println("Объявлен шах белым!");
                    }
                    System.out.println("Ход черных: ");
                break;
            }

            String inputLine = in.nextLine();
            int row, col, row1, col1;
            String [] coords = inputLine.split(" ");
            row = Integer.parseInt(coords[0]);
            col = Integer.parseInt(coords[1]);
            row1 = Integer.parseInt(coords[2]);
            col1 = Integer.parseInt(coords[3]);



            while ((!board.canMoveOnBoard(row,col,row1,col1) || !board.move_figure(row, col, row1, col1) || board.isCheck(board.getColorGame())))
            {
                System.out.println("Такого хода не существует! Измените ход.");
                switch (board.getColorGame())
                {
                    case 'w': System.out.println("Ход белых: ");
                        break;
                    case 'b': System.out.println("Ход черных: ");
                        break;
                }
                inputLine = in.nextLine();
                coords = inputLine.split(" ");
                row = Integer.parseInt(coords[0]);
                col = Integer.parseInt(coords[1]);
                row1 = Integer.parseInt(coords[2]);
                col1 = Integer.parseInt(coords[3]);
            }
            switch (board.getColorGame())
            {
                case 'w':
                    if (board.isMate(('b')))
                    {
                        System.out.println("Черным мат! Вы выиграли :р");
                        isGame = false;
                        break;
                    }
                    board.setColorGame('b');
                break;
                case 'b':
                    if (board.isMate(('w')))
                    {
                        System.out.println("Белым мат! Вы выиграли :р");
                        isGame = false;
                        break;
                    }
                    board.setColorGame('w');
                break;
            }
        }

    }
}