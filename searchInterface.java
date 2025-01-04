import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public interface searchInterface {
    Scanner scan = new Scanner(System.in);
    consultarListas search = new consultarListas();
    Listas lista = new Listas();
    
    
    DateTimeFormatter dmy = DateTimeFormatter.ofPattern("dd-MM-yyyy");

}