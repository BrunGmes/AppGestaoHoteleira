# Sistema de Gestão de Hotel

## Introdução

Este projeto consiste no desenvolvimento de uma aplicação de consola em Java destinada à gestão básica de um hotel. A aplicação permite consultar quartos, gerir hóspedes e administrar reservas, assegurando a persistência dos dados através de ficheiros CSV. O sistema foi desenvolvido no âmbito da uniadade curricular Fundamentos da Programação, integrada na Licenciatura em **Engenharia Informática – EaD da Universidade Europeia**, com o objetivo de consolidar conceitos **fundamentais da programação em Java**.

## Enquadramento e Funcionamento do Sistema

O funcionamento da aplicação baseia‑se no carregamento inicial de dados a partir de três ficheiros CSV: quartos, hóspedes e reservas. Os quartos são considerados dados fixos e são apenas lidos no arranque da aplicação, não sendo possível adicioná‑los ou removê‑los durante a execução. Os dados relativos a hóspedes e reservas podem ser criados, alterados ou cancelados através das funcionalidades disponibilizadas nos menus.

Após o carregamento dos dados, o sistema procede à atualização do estado de ocupação dos quartos, tendo em conta as reservas ativas e a data atual. A partir desse momento, o utilizador pode interagir com a aplicação através de um menu de consola, escolhendo as operações pretendidas. No encerramento da aplicação, os dados atualizados de hóspedes e reservas são automaticamente gravados nos respetivos ficheiros CSV, garantindo a persistência da informação entre execuções.

## Organização do Projeto

O projeto segue uma organização modular, com uma separação clara de responsabilidades:

 - Quarto, Hospede e Reserva
    Classes que representam os modelos de dados do sistema.

 - GestorHotel
    Classe responsável pela lógica de negócio, incluindo criação, consulta, edição e cancelamento de dados, bem como pela aplicação das regras de validação.

- GestorDados
    Responsável pela leitura e escrita dos ficheiros CSV.

 - SistemaHotel
    Classe principal da aplicação, contendo o método main e gerindo toda a interação com o utilizador através de menus em consola.

Os ficheiros CSV seguem um formato simples, permitindo a leitura e escrita direta, sem dependências externas.

## Funcionalidades Disponibilizadas

- Gestão de Quartos
    - Listagem de todos os quartos
    - Consulta de quartos livres e ocupados
    - Consulta detalhada de um quarto específico, incluindo reservas associadas

- Gestão de Hóspedes
    - Listagem de hóspedes registados
    - Pesquisa de hóspede por documento de identificação
    - Adição de novos hóspedes
    - Edição de dados de hóspedes existentes
    - Garantia de unicidade do documento de identificação

- Gestão de Reservas
    - Criação de novas reservas com atribuição automática de quarto disponível
    - Listagem de todas as reservas
    - Consulta de reservas por quarto ou por hóspede
    - Edição de reservas existentes
    - Cancelamento de reservas

## Instruções para Iniciar o Projeto

### Pré-requisitos

- Java JDK 8 ou superior instalado no sistema ([Download do Java](https://www.oracle.com/java/technologies/javase-downloads.html))
- Editor de texto ou IDE (opcional, recomendado: VS Code, IntelliJ IDEA, Eclipse)

### Passos para Compilar e Executar

1. **Clone ou extraia o projeto para uma pasta local.**
2. **Abra o terminal e navegue até a pasta do projeto.**
	- Exemplo no Windows:
	  ```sh
	  cd caminho\..\AppGestaoHoteleira
	  ```
	- Exemplo no Linux/macOS:
	  ```sh
	  cd /caminho/../AppGestaoHoteleira
	  ```
3. **Compile todos os arquivos Java:**
	```sh
	javac *.java
	```
4. **Execute a aplicação:**
	```sh
	java SistemaHotel
	```

### Observações
 - Os ficheiros CSV devem estar na mesma pasta do projeto
 - A aplicação é compatível com Windows, Linux e macOS

## Contexto Académico

Este projeto foi desenvolvido no âmbito da unidade curricular Fundamentos da Programação, integrada na Licenciatura em Engenharia Informática – EaD da Universidade Europeia, no ano letivo de 2025–2026.


**Autores**: Bruno Gomes - 20252323; Eva Tatagiba - 20250255; Giulia Torres - 20250980; Nuno Inês - 20250211
**Curso**: Licenciatura em Engenharia Informática - EaD
