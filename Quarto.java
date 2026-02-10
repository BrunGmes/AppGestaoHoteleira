import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Quarto {
    private int id;
    private int numero;
    private int capacidade;
    private boolean estaOcupado;

    public Quarto(int id, int numero, int capacidade) {
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
        this.estaOcupado = false;
    }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
    public void setEstaOcupado(boolean estaOcupado) { this.estaOcupado = estaOcupado; }
    
    // Getters
    public int getId() { return id; }
    public int getNumero() { return numero; }
    public int getCapacidade() { return capacidade; }
    public boolean estaOcupado() { return estaOcupado; }

    @Override
    public String toString() {
        return "Quarto: " +
                "id=" + id +
                ", numero=" + numero +
                ", capacidade=" + capacidade +
                ", ocupado=" + (estaOcupado ? "SIM" : "NÃO");
    }
}
