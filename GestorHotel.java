import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorHotel {
    private Quarto[] quartos;
    private Hospede[] hospedes;
    private Reserva[] reservas;
    
    private int proxIdHospede;
    private int proxIdReserva;
    
    private static final int MAX_QUARTOS = 200;
    private static final int MAX_HOSPEDES = 1000;
    private static final int MAX_RESERVAS = 1000;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public GestorHotel() {
        this.quartos = GestorDados.carregarQuartos();
        this.hospedes = new Hospede[MAX_HOSPEDES];
        this.reservas = new Reserva[MAX_RESERVAS];
        
        // Carregar dados existentes
        Hospede[] hospedesCarregados = GestorDados.carregarHospedes();
        System.arraycopy(hospedesCarregados, 0, hospedes, 0, hospedesCarregados.length);
        
        Reserva[] reservasCarregadas = GestorDados.carregarReservas();
        System.arraycopy(reservasCarregadas, 0, reservas, 0, reservasCarregadas.length);
        
        // Calcular próximos IDs
        proxIdHospede = obterProximoIdHospede();
        proxIdReserva = obterProximoIdReserva();
        
        atualizarEstadoQuartos();
        cancelaReservasAntigas();
    }

    // MÉTODOS DE QUARTOS

    public void listarTodosQuartos() {
        System.out.println("\n===== TODOS OS QUARTOS =====");
        for (Quarto q : quartos) {
            if (q != null) {
                System.out.println(q);
            }
        }
    }

    public void listarQuartosLivres() {
        System.out.println("\n===== QUARTOS LIVRES =====");
        boolean encontrou = false;
        for (Quarto q : quartos) {
            if (q != null && !q.estaOcupado()) {
                System.out.println(q);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum quarto livre disponível.");
    }

    public void listarQuartosOcupados() {
        System.out.println("\n===== QUARTOS OCUPADOS =====");
        boolean encontrou = false;
        for (Quarto q : quartos) {
            if (q != null && q.estaOcupado()) {
                System.out.println(q);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum quarto ocupado.");
    }

    public void listarQuartoEspecifico(int idQuarto) {
        Quarto q = obterQuarto(idQuarto);
        if (q == null) {
            System.out.println("Quarto não encontrado!");
            return;
        }
        System.out.println("\n===== QUARTO " + q.getNumero() + " =====");
        System.out.println(q);
        System.out.println("\nReservas deste quarto:");
        boolean encontrou = false;

        //Buscar reservas existentes do quarto
        for (Reserva r : reservas) {
            if (r != null && r.getIdQuarto() == idQuarto) {
                System.out.println(r);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma reserva neste quarto.");
    }

    // MÉTODOS DE HÓSPEDES 
    
    public void listarHospedes() {
        System.out.println("\n===== LISTA DE HÓSPEDES =====");
        boolean encontrou = false;
        for (Hospede h : hospedes) {
            if (h != null) {
                System.out.println(h);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum hóspede registado.");
    }

    // Buscar hóspede por documento
    public void procurarHospede(String documento) {
        System.out.println("\n===== PROCURAR HÓSPEDE =====");
        for (Hospede h : hospedes) {
            if (h != null && h.getDocumento().equals(documento)) {
                System.out.println(h);
                return;
            }
        }
        System.out.println("Hóspede com documento '" + documento + "' não encontrado.");
    }

    public boolean adicionarHospede(String nome, String documento) {
        if (nome == null || nome.isEmpty()) {
            System.out.println("Nome não pode estar vazio!");
            return false;
        }
        
        // Verificar duplicação de documento
        for (Hospede h : hospedes) {
            if (h != null && h.getDocumento().equals(documento)) {
                System.out.println("Documento já existe!");
                return false;
            }
        }
        
        // Encontrar espaço livre
        for (int i = 0; i < hospedes.length; i++) {
            if (hospedes[i] == null) {
                hospedes[i] = new Hospede(proxIdHospede++, nome, documento);
                System.out.println("Hóspede adicionado com sucesso! ID: " + (proxIdHospede - 1));
                return true;
            }
        }
        System.out.println("Capacidade máxima de hóspedes atingida!");
        return false;
    }

    // Editar hóspede por ID
    public boolean editarHospede(int idHospede, String novoNome, String novoDocumento) {
        Hospede h = obterHospede(idHospede);
        if (h == null) {
            System.out.println("Hóspede não encontrado!");
            return false;
        }
        
        if (novoNome != null && !novoNome.isEmpty()) {
            h.setNome(novoNome);
        }
        
        if (novoDocumento != null && !novoDocumento.isEmpty()) {
            // Verificar se documento já existe
            for (Hospede hh : hospedes) {
                if (hh != null && hh.getId() != idHospede && hh.getDocumento().equals(novoDocumento)) {
                    System.out.println("Documento já existe!");
                    return false;
                }
            }
            h.setDocumento(novoDocumento);
        }
        
        System.out.println("Hóspede atualizado com sucesso!");
        return true;
    }

    // MÉTODOS DE RESERVAS 
    
    public void criarReserva(int idHospede, int numeroHospedes, LocalDate dataInicio, LocalDate dataFim) {
        Hospede hospede = obterHospede(idHospede);

        // Validar hóspede
        if (hospede == null) {
            System.out.println("Hóspede não encontrado!");
            return;
        }
        
        // Validar datas
        if (dataInicio.isAfter(dataFim)) {
            System.out.println("Data de início não pode ser após data de fim!");
            return;
        }

        // embora o programa não deva aceitar reservas com datas anteriores a hoje, vamos manter este controlo
        // desativado para permitir testar o programa
        /*
        if (dataInicio.isBefore(LocalDate.now()) ||  dataFim.isBefore(LocalDate.now()) ) {
            System.out.println("Data(s) não pode(m) ser inferior(es) a hoje!");
            return;
        }
        */
        
        // Encontrar quarto 
        Quarto quartoDisponivel = encontrarQuartoDisponivel(numeroHospedes, dataInicio, dataFim);
        if (quartoDisponivel == null) {
            System.out.println("Nenhum quarto disponível com capacidade suficiente!");
            return;
        }
        
        // Criar reserva
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] == null) {
                reservas[i] = new Reserva(proxIdReserva++, quartoDisponivel.getId(), idHospede, 
                                         numeroHospedes, dataInicio, dataFim, true);
                System.out.println("Reserva criada com sucesso! ID: " + (proxIdReserva - 1));
                System.out.println("Quarto: " + quartoDisponivel.getNumero());
                atualizarEstadoQuartos();
                return;
            }
        }
        System.out.println("Capacidade máxima de reservas atingida!");
    }

    public boolean verificaReservasAtivas() {
        boolean encontrou = false;
        for (Reserva r : reservas) {
            if (r != null && r.isAtiva()) {
                encontrou = true;
            }
        }
        return encontrou;
    }

    public void listarTodasReservas() {
        System.out.println("\n===== TODAS AS RESERVAS =====");
        for (Reserva r : reservas) {
            if (r != null )
                System.out.println(r);
        }
    }

    public void listarReservasPorQuarto(int idQuarto) {
        System.out.println("\n===== RESERVAS DO QUARTO " + idQuarto + " =====");
        boolean encontrou = false;
        for (Reserva r : reservas) {
            if (r != null && r.getIdQuarto() == idQuarto && r.isPresente()) {
                System.out.println(r);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma reserva presente/futura neste quarto.");
    }

    public void listarReservasPorHospede(int idHospede) {
        System.out.println("\n===== RESERVAS DO HÓSPEDE " + idHospede + " =====");
        boolean encontrou = false;
        for (Reserva r : reservas) {
            if (r != null && r.getIdHospede() == idHospede && r.isPresente()) {
                System.out.println(r);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma reserva presente/futura para este hóspede.");
    }

    public boolean editarReserva(int idReserva, Integer novoNumeroHospedes, LocalDate novaDataInicio, LocalDate novaDataFim) {
        Reserva r = obterReserva(idReserva);
        if (r == null) {
            System.out.println("Reserva não encontrado!");
            return false;
        }
        
        // Aplicar mudanças
        if (novoNumeroHospedes != null) r.setNumeroHospedes(novoNumeroHospedes);
        if (novaDataInicio != null) r.setDataInicio(novaDataInicio);
        if (novaDataFim != null) r.setDataFim(novaDataFim);
        
        // Revalidar
        Quarto q = obterQuarto(r.getIdQuarto());
        if (r.getNumeroHospedes() > q.getCapacidade()) {
            System.out.println("Número de hóspedes excede a capacidade do quarto!");
            return false;
        }
        
        if (r.getDataInicio().isAfter(r.getDataFim())) {
            System.out.println("Data de início não pode ser após data de fim!");
            return false;
        }

        // embora o programa não deva aceitar reservas com datas anteriores a hoje, vamos manter este controlo
        // desativado para permitir testar o programa
        /*
        if (r.getDataInicio().isBefore(LocalDate.now()) ||  r.getDataFim().isBefore(LocalDate.now()) ) {
            System.out.println("Data(s) não pode(m) ser inferior(es) a hoje!");
            return false;
        }
         */
        
        // Verificar conflitos
        for (Reserva outras : reservas) {
            if (outras != null && outras.getId() != idReserva && outras.getIdQuarto() == r.getIdQuarto()) {
                if (outras.temConflito(r.getDataInicio(), r.getDataFim())) {
                    System.out.println("Existe conflito de datas com outra reserva!");
                    return false;
                }
            }
        }
        
        System.out.println("Reserva atualizada com sucesso!");
        atualizarEstadoQuartos();
        return true;
    }

    public boolean cancelarReserva(int idReserva) {
        Reserva r = obterReserva(idReserva);
        if (r == null) {
            System.out.println("Reserva não encontrado!");
            return false;
        }
        
        r.setAtiva(false);
        System.out.println("Reserva cancelada com sucesso!");
        atualizarEstadoQuartos();
        return true;
    }

    // cancela, coloca inativas reservas com datas anterior a hoje
    public void cancelaReservasAntigas() {
        for (Reserva r : reservas) {
            if (r != null && r.isAtiva() && r.getDataFim().isBefore(LocalDate.now())) {
                r.setAtiva(false);
            }
        }
    }

    // MÉTODOS AUXILIARES
    
    private Quarto obterQuarto(int id) {
        for (Quarto q : quartos) {
            if (q != null && q.getId() == id) return q;
        }
        return null;
    }

    private Hospede obterHospede(int id) {
        for (Hospede h : hospedes) {
            if (h != null && h.getId() == id) return h;
        }
        return null;
    }

    private Reserva obterReserva(int id) {
        for (Reserva r : reservas) {
            if (r != null && r.getId() == id) return r;
        }
        return null;
    }

    private Quarto encontrarQuartoDisponivel(int numeroHospedes, LocalDate dataInicio, LocalDate dataFim) {
        Quarto melhor = null;
        int melhorDiferenca = Integer.MAX_VALUE;
        
        for (Quarto q : quartos) {
            if (q == null) continue;
            if (q.getCapacidade() < numeroHospedes) continue;
            
            // Verificar disponibilidade
            boolean disponivel = true;
            for (Reserva r : reservas) {
                if (r != null && r.getIdQuarto() == q.getId() && r.temConflito(dataInicio, dataFim)) {
                    disponivel = false;
                    break;
                }
            }
            
            if (disponivel) {
                int diferenca = q.getCapacidade() - numeroHospedes;
                if (diferenca < melhorDiferenca) {
                    melhorDiferenca = diferenca;
                    melhor = q;
                }
            }
        }
        
        return melhor;
    }

    //Atualizar ocupação do quarto
    private void atualizarEstadoQuartos() {
        LocalDate hoje = LocalDate.now();
        for (Quarto q : quartos) {
            if (q == null) continue;
            q.setEstaOcupado(false);
            
            for (Reserva r : reservas) {
                if (r != null && r.getIdQuarto() == q.getId() && r.isAtiva()) {
                    if (r.getDataFim().isAfter(hoje) && (r.getDataInicio().isBefore(hoje) || r.getDataInicio().isEqual(hoje))) {
                        q.setEstaOcupado(true);
                        break;
                    }
                }
            }
        }
    }

    //Percorrer os hóspedes e devolver quantidade
    private int obterProximoIdHospede() {
        int max = 0;
        for (Hospede h : hospedes) {
            if (h != null && h.getId() > max) max = h.getId();
        }
        return max + 1;
    }

    //Percorrer as reservas e devolver quantidade
    private int obterProximoIdReserva() {
        int max = 0;
        for (Reserva r : reservas) {
            if (r != null && r.getId() > max) max = r.getId();
        }
        return max + 1;
    }

    // Getters para acesso aos dados
    public Hospede[] getHospedes() { return hospedes; }
    public Reserva[] getReservas() { return reservas; }

    // Guardar dados
    public void guardarDados() {
        GestorDados.guardarHospedes(hospedes);
        GestorDados.guardarReservas(reservas);
        System.out.println("Dados guardados com sucesso!");
    }
}
