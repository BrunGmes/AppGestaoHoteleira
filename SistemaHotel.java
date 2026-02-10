import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SistemaHotel {
    private GestorHotel gestor;
    private Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SistemaHotel() {
        this.gestor = new GestorHotel();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTÃO DE HOTEL       ║");
        System.out.println("║   Bem-vindo!                       ║");
        System.out.println("╚════════════════════════════════════╝");
        
        boolean executar = true;
        while (executar) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opção: ");
            
            switch (opcao) {
                case 1:
                    menuQuartos();
                    break;
                case 2:
                    menuHospedes();
                    break;
                case 3:
                    menuReservas();
                    break;
                case 0:
                    executar = false;
                    gestor.guardarDados();
                    System.out.println("\nAplicação encerrada. Dados guardados!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n╔════ MENU PRINCIPAL ════╗");
        System.out.println("║ 1 - Quartos            ║");
        System.out.println("║ 2 - Hóspedes           ║");
        System.out.println("║ 3 - Reservas           ║");
        System.out.println("║ 0 - Sair               ║");
        System.out.println("╚════════════════════════╝");
    }

    private void menuQuartos() {
        boolean submenu = true;
        while (submenu) {
            System.out.println("\n╔════ MENU QUARTOS ════╗");
            System.out.println("║ 1 - Listar todos     ║");
            System.out.println("║ 2 - Listar livres    ║");
            System.out.println("║ 3 - Listar ocupados  ║");
            System.out.println("║ 4 - Ver quarto       ║");
            System.out.println("║ 0 - Voltar           ║");
            System.out.println("╚══════════════════════╝");
            
            int opcao = lerInteiro("Escolha uma opção: ");
            
            switch (opcao) {
                case 1:
                    gestor.listarTodosQuartos();
                    break;
                case 2:
                    gestor.listarQuartosLivres();
                    break;
                case 3:
                    gestor.listarQuartosOcupados();
                    break;
                case 4:
                    int idQuarto = lerInteiro("ID do quarto: ");
                    gestor.listarQuartoEspecifico(idQuarto);
                    break;
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuHospedes() {
        boolean submenu = true;
        while (submenu) {
            System.out.println("\n╔════ MENU HÓSPEDES ════╗");
            System.out.println("║ 1 - Listar todos      ║");
            System.out.println("║ 2 - Procurar          ║");
            System.out.println("║ 3 - Adicionar novo    ║");
            System.out.println("║ 4 - Editar            ║");
            System.out.println("║ 0 - Voltar            ║");
            System.out.println("╚═══════════════════════╝");
            
            int opcao = lerInteiro("Escolha uma opção: ");
            
            switch (opcao) {
                case 1:
                    gestor.listarHospedes();
                    break;
                case 2: {
                    String documento = lerString("Documento: ");
                    gestor.procurarHospede(documento);
                    break;
                }
                case 3: {
                    String nome = lerString("Nome: ");
                    String documento = lerString("Documento: ");
                    gestor.adicionarHospede(nome, documento);
                    break;
                }
                case 4: {
                    int id = lerInteiro("ID do hóspede: ");
                    String novoNome = lerString("Novo nome (enter para não mudar): ");
                    String novoDocumento = lerString("Novo documento (enter para não mudar): ");
                    gestor.editarHospede(id, novoNome, novoDocumento);
                    break;
                }
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuReservas() {
        boolean submenu = true;

        // aproveita e atualizar reservas antigas
        //gestor.cancelaReservasAntigas();

        while (submenu) {
            System.out.println("\n╔════ MENU RESERVAS ════╗");
            System.out.println("║ 1 - Criar nova        ║");
            System.out.println("║ 2 - Listar todas      ║");
            System.out.println("║ 3 - Por quarto        ║");
            System.out.println("║ 4 - Por hóspede       ║");
            System.out.println("║ 5 - Editar            ║");
            System.out.println("║ 6 - Cancelar          ║");
            System.out.println("║ 0 - Voltar            ║");
            System.out.println("╚═══════════════════════╝");
            
            int opcao = lerInteiro("Escolha uma opção: ");
            
            switch (opcao) {
                case 1: {
                    int idHospede= lerInteiro("ID do hóspede: ");
                    int numeroHospedes = lerInteiro("Número de hóspedes: ");

                    String dataInicioStr = lerString("Data de início (yyyy-MM-dd): ");
                    String dataFimStr = lerString("Data de fim (yyyy-MM-dd): ");

                    try {
                        LocalDate dataInicio = LocalDate.parse(dataInicioStr, DATE_FORMATTER);
                        LocalDate dataFim = LocalDate.parse(dataFimStr, DATE_FORMATTER);
                        gestor.criarReserva(idHospede, numeroHospedes, dataInicio, dataFim);
                    } catch (Exception e) {
                        System.out.println("Datas erradas! Pf. Corrija");
                    }
                    break;
                }
                case 2:
                    gestor.listarTodasReservas();
                    break;
                case 3: {
                    int idQuarto = lerInteiro("ID do quarto: ");
                    gestor.listarReservasPorQuarto(idQuarto);
                    break;
                }
                case 4: {
                    int idHospede = lerInteiro("ID do hóspede: ");
                    gestor.listarReservasPorHospede(idHospede);
                    break;
                }
                case 5: {
                    int idReserva = lerInteiro("ID da reserva: ");
                    System.out.println("Deixe vazio para não alterar");
                    String numStr = lerString("Novo número de hóspedes: ");
                    Integer novoNum = numStr.isEmpty() ? null : Integer.parseInt(numStr);
                    
                    String dataInicioStr = lerString("Nova data de início: ");
                    LocalDate novaDataInicio = dataInicioStr.isEmpty() ? null : LocalDate.parse(dataInicioStr, DATE_FORMATTER);
                    
                    String dataFimStr = lerString("Nova data de fim: ");
                    LocalDate novaDataFim = dataFimStr.isEmpty() ? null : LocalDate.parse(dataFimStr, DATE_FORMATTER);
                    
                    try {
                        gestor.editarReserva(idReserva, novoNum, novaDataInicio, novaDataFim);
                    } catch (Exception e) {
                        System.out.println("Erro ao editar: " + e.getMessage());
                    }
                    break;
                }
                case 6: {
                    int idReserva = lerInteiro("ID da reserva a cancelar: ");
                    gestor.cancelarReserva(idReserva);
                    break;
                }
                case 0:
                    submenu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Métodos auxiliares para entrada de dados
    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido!");
            return -1;
        }
    }

    private String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static void main(String[] args) {
        SistemaHotel sistema = new SistemaHotel();
        sistema.iniciar();
    }
}
