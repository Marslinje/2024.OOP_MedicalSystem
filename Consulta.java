import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {

    public LocalDate data;
    public LocalTime hora;
    public Medico medico;
    public String cpfPaciente;

    Consulta(LocalDate data, LocalTime hora, Medico medico, String cpfPaciente)
    {
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.cpfPaciente = cpfPaciente;

    }

}
