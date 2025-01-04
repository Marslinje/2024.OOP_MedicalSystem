import java.util.ArrayList;

public class Medico {

   public String nomeMedico;
   public int ID;
   public ArrayList<Paciente> pacientes;

   Medico(String nomeMedico, int ID) {
      this.nomeMedico = nomeMedico;
      this.ID = ID;
      pacientes = new ArrayList<Paciente>();
   }

   public void setPaciente(Paciente paciente) {
      if (!pacientes.contains(paciente))
         pacientes.add(paciente);
   }
}
