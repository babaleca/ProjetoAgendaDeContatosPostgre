# Agenda de Contatos — PostgreSQL

Aplicação desktop desenvolvida em **Java** com interface gráfica (Swing) e banco de dados **PostgreSQL**. Permite gerenciar contatos de forma simples, com operações de cadastro, edição, busca e exclusão.

---

## Funcionalidades

- Cadastrar contatos com nome, e-mail e telefone
- Listar todos os contatos em uma tabela
- Editar informações de um contato existente
- Excluir contatos
- Formatar telefone automaticamente no padrão `+DD (DDD) XXXXX-XXXX`

---

## Pré-requisitos

Antes de rodar o projeto, você precisará ter instalado:

- [Java JDK 8+](https://www.oracle.com/java/technologies/downloads/)
- [PostgreSQL](https://www.postgresql.org/download/)
- [NetBeans IDE](https://netbeans.apache.org/) *(recomendado)*
- Driver JDBC do PostgreSQL (`postgresql-xx.jar`) — já incluso no projeto

---

## Configuração do Banco de Dados

1. Abra o PostgreSQL e crie um banco de dados chamado `trabalho`:
   ```sql
   CREATE DATABASE trabalho;
   ```

2. Crie a tabela de contatos:
   ```sql
   CREATE TABLE contatos (
       id_contato SERIAL PRIMARY KEY,
       nome_completo VARCHAR(100),
       email VARCHAR(100),
       ddi VARCHAR(5),
       ddd VARCHAR(3),
       numero VARCHAR(15)
   );
   ```

3. No arquivo `src/persistencia/ConexaoPostgre.java`, ajuste as credenciais conforme o seu ambiente:
   ```java
   private static final String URL  = "jdbc:postgresql://localhost:5432/trabalho";
   private static final String USER = "postgres";
   private static final String PASS = "sua_senha_aqui";
   ```

---

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/babaleca/ProjetoAgendaDeContatosPostgre.git
   ```

2. Abra o projeto no **NetBeans** via `File > Open Project`

3. Certifique-se de que o banco de dados está rodando e as credenciais estão configuradas

4. Execute o projeto com **F6** ou clicando em `Run Project`

---

## Autor

Desenvolvido por **Barbara Raquel Ferreira Bandarra**
