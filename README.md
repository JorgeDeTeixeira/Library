# Library

# Biblioteca de Livros - API

Este projeto é uma API para gerenciar uma biblioteca de livros, desenvolvida em Java utilizando o framework Spring Boot e banco de dados MySQL.

## Requisitos

A API deve suportar as seguintes operações:

1. **Criar um livro**: Adicionar um novo livro à biblioteca.
2. **Listar todos os livros**: Recuperar todos os livros da biblioteca.
3. **Buscar um livro por ID**: Recuperar detalhes de um livro específico.
4. **Atualizar um livro**: Atualizar as informações de um livro existente.
5. **Excluir um livro**: Remover um livro da biblioteca.

## Modelagem do Banco de Dados

O banco de dados MySQL terá uma tabela `book` com os seguintes campos:

- `id` (int, chave primária)
- `title` (varchar)
- `author` (varchar)
- `published_date` (date)
- `isbn` (varchar)
- `price` (decimal)

## Configuração do Projeto

1. Crie um projeto Spring Boot com as seguintes dependências:
   - Spring Web
   - Spring Data JPA
   - MySQL Driver

2. Configure o arquivo `application.properties` para conectar ao banco de dados MySQL.

## Passos para Desenvolvimento

1. **Criar as Entidades JPA**: Mapear a tabela `book` para a classe `Book`.
2. **Criar os Repositórios**: Interfaces para acesso ao banco de dados.
3. **Criar os Serviços**: Lógica de negócios.
4. **Criar os Controladores**: Endpoints da API.
5. **Testar a API**: Garantir que tudo está funcionando corretamente.

## Estrutura do Projeto

- `src/main/java/com/seuprojeto/biblioteca`:
  - `controller`: Controladores da API.
  - `service`: Lógica de negócios.
  - `repository`: Interfaces de acesso ao banco de dados.
  - `model`: Classes de entidade JPA.

## Como Executar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/seuusuario/biblioteca.git
