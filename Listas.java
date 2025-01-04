import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Listas implements searchInterface {
    private Scanner reader;
    public ArrayList<Medico> medicos;
    public ArrayList<Consulta> consultas;
    public ArrayList<Paciente> pacientes;

    Listas(){
        medicos = new ArrayList<Medico>();
        consultas = new ArrayList<Consulta>();
        pacientes = new ArrayList<Paciente>();
    }

    

    public void onStart() {
        getSearchMedicos();
        getSearchConsultas();
        getSearchPacientes();
        getPacientesMedico();
    }



    public void getSearchMedicos() {
        String path = "_SearchMedicos.csv";

        try {
        reader = new Scanner(new FileReader(path));

        while(reader.hasNextLine())
        {
            String line = reader.nextLine();
            String[] parts = line.split(",");
            medicos.add(new Medico(parts[0], Integer.parseInt(parts[1])));

        }
        
        //Debug
        //System.out.println("nome " + medicos.get(3).nomeMedico + " ID " + medicos.get(3).ID);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void getSearchConsultas() {
        String path = "_SearchConsultas.csv";
        
        try {
        reader = new Scanner(new FileReader(path));

        while(reader.hasNextLine())
        {
            String line = reader.nextLine();
            String[] parts = line.split(",");
            LocalDate dataDmy = LocalDate.parse(parts[0],dmy);
            for (Medico medico : medicos) {
                if (Integer.parseInt(parts[2]) == medico.ID)
                {
                consultas.add(new Consulta(dataDmy, LocalTime.parse(parts[1]),medico,parts[3]));
                }
            }

        }

        //Debug
        //System.out.println(consultas.get(11).data + " " + consultas.get(11).hora + " " + consultas.get(11).medico.nomeMedico + " " + consultas.get(11).cpfPaciente);
        //System.out.println(consultas.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void getSearchPacientes()
    {
        String path = "_SearchPacientes.csv";

        try {
        reader = new Scanner(new FileReader(path));

        while(reader.hasNextLine())
        {
            String line = reader.nextLine();
            String[] parts = line.split(",");
            ArrayList<Consulta> pacienteConsultas = new ArrayList<Consulta>();
            for (Consulta consulta : consultas) {
                if(consulta.cpfPaciente.equals(parts[1]) && !pacienteConsultas.contains(consulta))
                {
                    //System.out.println("Ruffles X");
                    //System.out.println(consulta.cpfPaciente);
                    pacienteConsultas.add(consulta);
                }
            }
            pacientes.add(new Paciente(parts[0], parts[1],pacienteConsultas));
            /*  pegou nome e cpf, dai fazer uma lista de objetos das consultas
            for each consulta cpf == paciente cpf add consulta no array
            depois do for adiciona o array todo ao paciente  */
        }
        
        //Debug
        //System.out.println("nome " + pacientes.get(5).nome + " CPF " + pacientes.get(5).cpf + " ref " + pacientes.get(5).consulta.get(0).data);
        //System.out.println("consultas " + pacientes.get(5).consulta.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void getPacientesMedico()
    {
        for (Consulta consulta : consultas) {
            for(Paciente paciente : pacientes) {
                if(consulta.cpfPaciente.equals(paciente.cpf))
                    consulta.medico.setPaciente(paciente);
                //consulta.medico.setPacientes(pacientes);
            }
        }
        
        //Debug
        //System.out.println(medicos.get(3).nomeMedico);
        //System.out.println(medicos.get(3).pacientes.size());

        /* para cada consulta e para cada paciente na lista de objetos pacientes
            se cpf em consulta for igual ao cpf de paciente
            o objeto referente ao for, medico da lista consulta recebe no metodo setPaciente
            o objeto paciente referente.
        */
    }
}