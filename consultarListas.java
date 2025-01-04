import java.time.LocalDate;
import java.util.ArrayList;

public class consultarListas implements searchInterface{
    public static Listas lista = new Listas();

    static ArrayList<String> outputLines = new ArrayList<>();

    // Inicia o sistema para lêr os arquivos csv e organiza-los em classes
    public void getListas()
    {
        lista.onStart();
    }
    
    // 1 Lista Medicos
    public void displayMedicos()
    {
        System.out.println("Lista de médicos: ");
        for (Medico medico : lista.medicos) {
            String line = ("ID: " + medico.ID + " - " + medico.nomeMedico);
            getOutput(line);
        }
    }

    // 2 Lista Pacientes
    public void displayPacientes()
    {
        System.out.println("Lista de pacientes: ");
        for (Paciente paciente : lista.pacientes) {
            String line = ("Cpf: " + paciente.cpf + " - " + paciente.nome);
            getOutput(line);
        }
    }

    // 3 Lista Pacientes do[a] médico[a]
    public static void checkPacientesMedico(int id)
    {
        for (Medico medico : lista.medicos) {
            if(id == medico.ID)
            {
                String line = ("Pacientes do(a) medico(a) " + medico.nomeMedico + ", ID " +medico.ID + ":");
                getOutput(line);
                for(int i = 0; i < medico.pacientes.size(); i++)
                {
                    String line1 = ("Cpf: " + medico.pacientes.get(i).cpf + " - " + medico.pacientes.get(i).nome);
                    getOutput(line1);
                }
            }
        }
    }

    // 4 Consultas do[a] médico[a]
    public static void checkConsultasMedico(String id, LocalDate dateStart, LocalDate dateEnd)
    {
        LocalDate tempDate = dateStart;

        if (!dateStart.isBefore(dateEnd))
        {
        tempDate = dateStart;
        dateStart = dateEnd;
        dateEnd = tempDate;
        }

        for (Medico medico : lista.medicos) {
            if (medico.ID == Integer.parseInt(id))
            {
                String line = ("Data de consultas entre " + dateStart.format(dmy) + " e " + dateEnd.format(dmy));
                getOutput(line);
                String line1 = ("do(a) médico(a) " + medico.nomeMedico + ", ID " + medico.ID + ":");
                getOutput(line1);
            }
        }
        for (Consulta consulta : lista.consultas) {
            if(consulta.medico.ID == Integer.parseInt(id))
            {
                if (consulta.data.isAfter(dateStart) && consulta.data.isBefore(dateEnd))
                {
                    for (Paciente paciente : lista.pacientes) {
                        if (paciente.cpf.equals(consulta.cpfPaciente))
                        {
                        String line2 = ("- " + consulta.data.format(dmy) + " as " + consulta.hora + " com o(a) paciente " + paciente.nome + " de CPF " + consulta.cpfPaciente);
                        getOutput(line2);
                        }
                        
                    }
                }
            }
        }
    }

    // 5 Médicos relacionados a um paciente
    public static void checkMedicosPaciente(String cpf)
    {
        for (Paciente paciente : lista.pacientes) {
            if (paciente.cpf.equals(cpf))
            {
                String line = ("Paciente " + paciente.nome + ", CPF " + cpf + ", tem consultas com: ");
                getOutput(line);
            }
        }
        for (Consulta consulta : lista.consultas)
        {
            if (consulta.cpfPaciente.equals(cpf))
            {
                String line1 = ("ID: " + consulta.medico.ID + " Médico(a) " + consulta.medico.nomeMedico + " em " + consulta.data.format(dmy) + " as " + consulta.hora);
                getOutput(line1);
            }
        }
    }

    // 6 Consultas realizadas do paciente com determinado médico
    public static void checkMedicoPacienteConsultaRealizada(String cpf, int idMed)
    {
        for (Paciente paciente : lista.pacientes)
        {
            if(paciente.cpf.equals(cpf))
            {
                for (Medico medico : lista.medicos) {
                    if(medico.ID == idMed)
                    {
                    String line = ("Consultas de " + paciente.nome + ", CPF " + paciente.cpf + "\nrealizadas com o(a) médico(a) " + medico.nomeMedico + " até a data de " + LocalDate.now().format(dmy));
                    getOutput(line);
                    }
                }
                for (int i = 0; i < paciente.consulta.size(); i++) {
                    if(paciente.consulta.get(i).medico.ID == idMed && paciente.consulta.get(i).data.isBefore(LocalDate.now()))
                    {
                        String line1 = ("- " + paciente.consulta.get(i).data.format(dmy) + " as " + paciente.consulta.get(i).hora);
                        getOutput(line1);
                    }
                }
            }
        }
    }

    // 7 Consultas agendadas do paciente com determinado médico
    public static void checkMedicoPacienteConsultaAgendada(String cpf, int idMed)
    {
        for (Paciente paciente : lista.pacientes)
        {
            if(paciente.cpf.equals(cpf))
            {
                for (Medico medico : lista.medicos) {
                    if(medico.ID == idMed)
                    {
                    String line = ("Consultas de " + paciente.nome + ", CPF " + paciente.cpf + "\nagendadas com o(a) médico(a) " + medico.nomeMedico) + " até a data de " + LocalDate.now().format(dmy);
                    getOutput(line);
                    }
                }
                for (int i = 0; i < paciente.consulta.size(); i++) {
                    if(paciente.consulta.get(i).medico.ID == idMed && paciente.consulta.get(i).data.isAfter(LocalDate.now()))
                    {
                        String line1 = ("Data " + paciente.consulta.get(i).data.format(dmy) + " as " + paciente.consulta.get(i).hora);
                        getOutput(line1);
                    }
                }
            }
        }
    }

    //8 Pacientes que nao consultam determinado médico em N meses
    public static void checkPacientesNmesesMedico(int idMed, String input)
    {
        int Nmes = (Integer.parseInt(input));

        for (Medico medico : lista.medicos) {
            if (medico.ID == idMed)
            {
                String line0 = ("Pacientes que nao se consultam há " + input + " mêses com o médico(a) " + medico.nomeMedico + ":");
                getOutput(line0);

                for (int i = 0; i < medico.pacientes.size(); i++)
                {
                    for (int j = 0; j < medico.pacientes.get(i).consulta.size(); j++) {
                        if (medico.pacientes.get(i).consulta.get(j).medico.ID == idMed)
                        {
                            if(medico.pacientes.get(i).consulta.get(j).data.plusMonths(Nmes).isBefore(LocalDate.now()))  //se a data do paciente + número de meses for antes da data de hoje
                            {
                                String line1 = medico.pacientes.get(i).nome + " cpf: " + medico.pacientes.get(i).cpf;
                                getOutput(line1);
                                String line2 = "Ultima consulta em " + medico.pacientes.get(i).consulta.get(j).data.format(dmy);
                                getOutput(line2);
                            }
                        }
                    }
                }
            }
        }

    }

    public static void savePesquisa(String name)
    {
        writePesquisa.writeToFile(outputLines, name);
        outputLines.clear();
    }

    public static void getOutput(String line)
    {
        outputLines.add(line);
        System.out.println(line);
    }

}
