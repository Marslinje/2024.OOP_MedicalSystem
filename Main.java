import java.time.LocalDate;

class Main implements searchInterface {
public static void main(String[] args) {
    search.getListas();

    String input = "";

    start();
    comandos();

    
    int getID;
    String getCpf;

    while (!input.equals("sair"))
    {
        getID = 0;
        getCpf = "";
        System.out.println("Pesquise a partir do número correspondente");
        System.out.println("Digite 'help' para ver a lista de comandos");
        System.out.print("- ");
        input = scan.nextLine();
        switch (input.toUpperCase()) {
            case "HELP":
                comandos();
                break;
            case "EXIT":
                input = "sair";
                break;
            case "1":
                search.displayMedicos();
                salvarPesquisa(input);
                break;
            case "2":
                search.displayPacientes();
                salvarPesquisa(input);
                break;
            case "3":
                System.out.println("Pacientes do[a] médico[a]");
            
                System.out.print("ID do médico: ");
                getID = Integer.parseInt(scan.nextLine());
                consultarListas.checkPacientesMedico(getID);

                salvarPesquisa(input);
                break;
            case "4":
                System.out.println("Consultas do[a] médico[a]");

                System.out.print("ID do médico: ");
                String id = scan.nextLine();
                System.out.println("Defina um intervalo entre duas datas, utilize o formato (dia-mês-ano)");
                System.out.print("Data inicial: ");
                String dateStart = scan.nextLine();
                System.out.print("Data final: ");
                String dateEnd = scan.nextLine();
                consultarListas.checkConsultasMedico(id, LocalDate.parse(dateStart,dmy), LocalDate.parse(dateEnd,dmy));

                salvarPesquisa(input);
                break;
            case "5":
                System.out.println("Médicos relacionados a um paciente");

                System.out.println("Digite o Cpf do paciente");
                getCpf = scan.nextLine();
                consultarListas.checkMedicosPaciente(getCpf);

                salvarPesquisa(input);
                break;
            case "6":
                System.out.println("Ver consultas realizadas do paciente com determinado médico");

                System.out.print("CPF do paciente: ");
                getCpf = scan.nextLine();
                System.out.print("ID do médico: ");
                input = scan.nextLine();
                if (!input.equals(""))
                {
                    getID = Integer.parseInt(input);
                    consultarListas.checkMedicoPacienteConsultaRealizada(getCpf, getID);
                    salvarPesquisa(input);
                }
                break;
            case "7":
                System.out.println("Ver consultas agendadas do paciente com determinado médico");

                System.out.print("CPF do paciente: ");
                getCpf = scan.nextLine();
                System.out.print("ID do médico: ");
                input = scan.nextLine();
                if (!input.equals(""))
                {
                    getID = Integer.parseInt(input);
                    consultarListas.checkMedicoPacienteConsultaAgendada(getCpf, getID);

                    salvarPesquisa(input);
                }
                break;
            case "8":
                System.out.println("Pacientes que nao consultam determinado médico em N mêses");

                System.out.print("ID do médico: ");
                input = scan.nextLine();
                if (!input.equals(""))
                {
                    getID = Integer.parseInt(input);
                    System.out.print("Há quantos mêses: ");
                    input = scan.nextLine();
                    consultarListas.checkPacientesNmesesMedico(getID, input);

                    salvarPesquisa(input);
                }
                break;
            default:
                System.out.println("Comando irreconhecível");
                break;
        }
    }
}
    public static void start()
    {
        System.out.println("Gerenciador de dados sobre médicos, pacientes e consultas.");
        System.out.println("Utilize os seguintes comandos para realizar pesquisas: ");
    }

    public static void comandos()
    {
        System.out.println("- [1] Ver a lista de médicos \n- [2] Ver lista de pacientes");
        System.out.println("- [3] Pacientes do[a] médico[a]");
        System.out.println("- [4] Consultas do[a] médico[a]");
        System.out.println("- [5] Médicos relacionados a um paciente");
        System.out.println("- [6] Consultas realizadas do paciente com determinado médico");
        System.out.println("- [7] Consultas agendadas do paciente com determinado médico");
        System.out.println("- [8] Pacientes que nao consultam determinado médico em N meses");
        System.out.println("- [Exit] Fecha o programa");
    }

    public static void salvarPesquisa(String input)
    {
        System.out.println();
        System.out.println("Você deseja salvar essa pesquisa? y = sim, n = nao");
        System.out.print("- ");
        input = scan.nextLine();
        if (input.equals("y"))
        {
            System.out.print("Nome do arquivo: ");
            input = scan.nextLine();
            consultarListas.savePesquisa(input);
        } else {
            consultarListas.outputLines.clear();
        }
    }
}