import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private int id;
    private int idQuarto;
    private int idHospede;
    private int numeroHospedes;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativa;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Reserva(int id, int idQuarto, int idHospede, int numeroHospedes, 
                   LocalDate dataInicio, LocalDate dataFim, boolean ativa) {
        this.id = id;
        this.idQuarto = idQuarto;
        this.idHospede = idHospede;
        this.numeroHospedes = numeroHospedes;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa = ativa;
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getIdQuarto() { return idQuarto; }
    public int getIdHospede() { return idHospede; }
    public int getNumeroHospedes() { return numeroHospedes; }
    public void setNumeroHospedes(int numeroHospedes) { this.numeroHospedes = numeroHospedes; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    // Metodo para verificar se a reserva está no presente/futuro
    public boolean isPresente() {
        LocalDate hoje = LocalDate.now();
        return !dataFim.isBefore(hoje) && ativa;
    }

    // Método para verificar conflito de datas
    public boolean temConflito(LocalDate inicio, LocalDate fim) {
        if (!ativa) return false;
        return !(fim.isBefore(dataInicio) || inicio.isAfter(dataFim));
    }

    @Override
    public String toString() {
        return "Reserva: " +
                "id=" + id +
                ", idQuarto=" + idQuarto +
                ", idHospede=" + idHospede +
                ", numeroHospedes=" + numeroHospedes +
                ", dataInicio=" + dataInicio.format(DATE_FORMATTER) +
                ", dataFim=" + dataFim.format(DATE_FORMATTER) +
                ", ativa=" + ativa;
    }

    public String toCSV() {
        return id + "," + idQuarto + "," + idHospede + "," + numeroHospedes + "," +
               dataInicio.format(DATE_FORMATTER) + "," + dataFim.format(DATE_FORMATTER) + "," + ativa;
    }
}
