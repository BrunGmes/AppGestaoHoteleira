import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Gerir entrada e saida de dados dos ficheiros csv
public class GestorDados {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String QUARTOS_FILE = "quartos.csv";
    private static final String HOSPEDES_FILE = "hospedes.csv";
    private static final String RESERVAS_FILE = "reservas.csv";

    // Carregar quartos do ficheiro
    public static Quarto[] carregarQuartos() {
        ArrayList<Quarto> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(QUARTOS_FILE))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    // salta a primeira linha, pois tem o nome dos campos e nao os dados
                    continue;
                }
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0]);
                int numero = Integer.parseInt(partes[1]);
                int capacidade = Integer.parseInt(partes[2]);
                lista.add(new Quarto(id, numero, capacidade));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar quartos: " + e.getMessage());
        }
        return lista.toArray(new Quarto[0]);
    }

    // Carregar hóspedes do ficheiro
    public static Hospede[] carregarHospedes() {
        ArrayList<Hospede> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HOSPEDES_FILE))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    // salta a primeira linha, pois tem o nome dos campos e nao os dados
                    continue;
                }
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String documento = partes[2];
                lista.add(new Hospede(id, nome, documento));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar hóspedes: " + e.getMessage());
        }
        return lista.toArray(new Hospede[0]);
    }

    // Carregar reservas do ficheiro
    public static Reserva[] carregarReservas() {
        ArrayList<Reserva> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVAS_FILE))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    // salta a primeira linha, pois tem o nome dos campos e nao os dados
                    continue;
                }
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0]);
                int idQuarto = Integer.parseInt(partes[1]);
                int idHospede = Integer.parseInt(partes[2]);
                int numeroHospedes = Integer.parseInt(partes[3]);
                LocalDate dataInicio = LocalDate.parse(partes[4], DATE_FORMATTER);
                LocalDate dataFim = LocalDate.parse(partes[5], DATE_FORMATTER);
                boolean ativa = Boolean.parseBoolean(partes[6]);
                lista.add(new Reserva(id, idQuarto, idHospede, numeroHospedes, dataInicio, dataFim, ativa));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar reservas: " + e.getMessage());
        }
        return lista.toArray(new Reserva[0]);
    }

    // Guardar hóspedes no ficheiro
    public static void guardarHospedes(Hospede[] hospedes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOSPEDES_FILE))) {
            writer.write("id,nome,documento\n");
            for (Hospede h : hospedes) {
                if (h != null) {
                    writer.write(h.getId() + "," + h.getNome() + "," + h.getDocumento() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar hóspedes: " + e.getMessage());
        }
    }

    // Guardar reservas no ficheiro
    public static void guardarReservas(Reserva[] reservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVAS_FILE))) {
            writer.write("id,idQuarto,idHospede,numeroHospedes,dataInicio,dataFim,ativa\n");
            for (Reserva r : reservas) {
                if (r != null) {
                    writer.write(r.toCSV() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar reservas: " + e.getMessage());
        }
    }
}
