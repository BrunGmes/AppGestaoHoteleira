# Sistema de Gestão de Hotel

## Introdução

Este projeto consiste no desenvolvimento de uma aplicação de consola em Java destinada à gestão básica de um hotel. A aplicação permite consultar quartos, gerir hóspedes e administrar reservas, assegurando a persistência dos dados através de ficheiros CSV. O sistema foi desenvolvido no âmbito da unidade curricular Fundamentos da Programação, integrada na Licenciatura em Engenharia Informática – EaD da Universidade Europeia, com o objetivo de consolidar conceitos fundamentais da programação em Java.

Ao longo do desenvolvimento, procurou‑se aplicar uma abordagem estruturada e modular, recorrendo exclusivamente a classes simples e arrays de tamanho fixo, conforme definido nos requisitos da unidade curricular. O projeto privilegia a clareza do código, a validação rigorosa de dados e a separação entre modelos de dados, lógica de negócio, persistência e interação com o utilizador.

## Enquadramento e Funcionamento do Sistema

O funcionamento da aplicação baseia‑se no carregamento inicial de dados a partir de três ficheiros CSV: quartos, hóspedes e reservas. Os quartos são considerados dados fixos e são apenas lidos no arranque da aplicação, não sendo possível adicioná‑los ou removê‑los durante a execução. Os dados relativos a hóspedes e reservas podem ser criados, alterados ou cancelados através das funcionalidades disponibilizadas nos menus.

Após o carregamento dos dados, o sistema procede à atualização do estado de ocupação dos quartos, tendo em conta as reservas ativas e a data atual. A partir desse momento, o utilizador pode interagir com a aplicação através de um menu de consola, escolhendo as operações pretendidas. No encerramento da aplicação, os dados atualizados de hóspedes e reservas são automaticamente gravados nos respetivos ficheiros CSV, garantindo a persistência da informação entre execuções.

## Organização do Projeto

O projeto encontra‑se organizado de forma a refletir uma separação clara de responsabilidades. As classes Quarto, Hospede e Reserva representam os modelos de dados fundamentais do sistema. A classe GestorHotel centraliza a lógica de negócio, sendo responsável pela implementação das operações de criação, consulta, edição e cancelamento, bem como pela aplicação das regras de validação. A classe GestorDados assegura a leitura e escrita dos ficheiros CSV, enquanto a classe SistemaHotel funciona como ponto de entrada da aplicação e gere toda a interação com o utilizador através de menus em consola.

Os ficheiros CSV utilizados seguem um formato simples e consistente, permitindo uma leitura e escrita diretas sem recurso a bibliotecas externas. Esta abordagem reforça o objetivo pedagógico do projeto, centrado no domínio das estruturas básicas da linguagem Java.

## Funcionalidades Disponibilizadas

O sistema permite a consulta detalhada dos quartos existentes, possibilitando ao utilizador visualizar todos os quartos, identificar quais se encontram livres ou ocupados e consultar um quarto específico, incluindo as respetivas reservas associadas.

Relativamente à gestão de hóspedes, a aplicação permite listar todos os hóspedes registados, procurar um hóspede através do seu documento de identificação, adicionar novos registos e editar informações existentes, garantindo sempre a unicidade do documento.

No que diz respeito às reservas, o sistema possibilita a criação de novas reservas através da seleção automática de um quarto adequado, tendo em conta o número de hóspedes e a disponibilidade. É igualmente possível listar reservas, consultá‑las por quarto ou por hóspede, editar os dados de uma reserva existente ou proceder ao seu cancelamento.

## Regras de Negócio e Validações

A aplicação aplica um conjunto de regras de negócio essenciais ao correto funcionamento do sistema. O número de hóspedes associado a uma reserva deve ser compatível com a capacidade do quarto atribuído. As datas das reservas devem obedecer ao formato YYYY‑MM‑DD e a data de início não pode ser posterior à data de fim. Não são permitidas reservas ativas que se sobreponham no mesmo quarto.

Adicionalmente, o sistema valida a existência dos identificadores utilizados, impede a duplicação de documentos de hóspedes e trata situações de erro comuns, como formatos inválidos ou tentativas de ultrapassar os limites definidos para os arrays.

## Persistência e Gestão de Dados

A persistência dos dados é assegurada exclusivamente através de ficheiros CSV. Os identificadores de hóspedes e reservas são gerados automaticamente de forma incremental, com base nos dados existentes. O estado de ocupação dos quartos é calculado dinamicamente, em função das reservas ativas e da data corrente, não sendo armazenado diretamente nos ficheiros.

## Compilação e Execução

A aplicação pode ser compilada através do comando javac *.java, executado na diretoria do projeto. A execução é realizada com o comando java SistemaHotel. O sistema é compatível com ambientes Windows, Linux e macOS, desde que esteja instalada uma versão do Java igual ou superior à versão 8.

## Contexto Académico

Este projeto foi desenvolvido no âmbito da unidade curricular Fundamentos da Programação, integrada na Licenciatura em Engenharia Informática – EaD da Universidade Europeia, no ano letivo de 2025–2026.


**Autores**: Bruno Gomes - 20252323; Eva Tatagiba - 20250255; Giulia Torres - 20250980; Nuno Inês - 20250211
**Curso**: Licenciatura em Engenharia Informática - EaD
