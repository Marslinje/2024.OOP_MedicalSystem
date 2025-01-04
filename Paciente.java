import java.util.ArrayList;

public class Paciente {
    
    public String nome;
    public String cpf;
    public ArrayList<Consulta> consulta;

    Paciente(String nome, String cpf, ArrayList<Consulta> consulta)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.consulta = consulta;
    }
}
