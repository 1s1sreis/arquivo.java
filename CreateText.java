import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.deitel.ch17.AccountRecord;

public class CreateText{

    private Formatter output; // objeto utilizado para enviar texto para arquivo
    
    // permite ao usuário abrir o arquivo
    public void openFile()
    {
        try
        {
            output = new Formatter( "clients.txt" ); // abre o arquivo
        }//fim do try
        catch ( SecurityException securityException )
        {
            System.err.println(
            "You do not have write access to this file." );
            System.exit( 1 ); // termina o programa
        } // fim do catch
        catch ( FileNotFoundException fileNotFoundException )
        {
            System.err.println( "Error opening or creating file." );
            System.exit( 1 ); // termina o programa
        } // fim do catch
    } // fim do método openFile

    // adiciona registros ao arquivo
    public void addRecords (){

        // objeto a ser gravado no arquivo
        AccountRecord record = new AccountRecord();

        Scanner input = new Scanner( System.in );
        System.out.printf( "%s\n%s\n%s\n%s\n\n",
        "To terminate input, type the end-of-file indicator ",
        "when you are prompted to enter input.",
        "On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",
        "On Windows type <ctrl> z then press Enter" );

        System.out.printf( "%s\n%s",
        "Enter account number (> 0), first name, last name and balance.",
        "? " );
        while (input. hasNext() ) // faz um loop ate o indicador de fim de arquivo
        {
        try // gera saída dos valores para o arquivo
        {
            record.setAccount( input.nextInt() ); // lê o número de conta
            record.setFirstName( input.next() ); // lê o primeiro nome
            record.setLastName( input.next() ); // lê o sobrenome
            record.setBalance( input.nextDouble() ); // lê o saldo
            
            if ( record.getAccount() > 0 )
            { 
                output.format( "%d %s %s %.2f\n", record.getAccount(),
                record.getFirstName(), record.getLastName(),
                record.getBalance() );
            } // fim do if
            else
            {   
                System.out.println(
                "Account number must be greater than 0." );
             } // fim de else
        } // fim do try
        catch ( FormatterClosedException formatterClosedException )
        {
            System.err.println( "Error writing to file." );
            return;
        } // fim do catch
        catch ( NoSuchElementException elementException )
        {
            System.err.println( "Invalid input. Please try again." );
            input.nextLine(); // descarta a entrada para que o usuario possa tentar novamente
        } // fim do catch
        System.out.printf( "%s %s\n%s", "Enter account number (>0),",
        "first name, last name and balance. ", "? " );
        } // fim do while
    } // fim do método addRecords

    // fecha o arquivo
    public void closeFile()
    {

        if ( output != null )
        output.close();
    } // fim do método closeFile
} // fim da classe CreateText