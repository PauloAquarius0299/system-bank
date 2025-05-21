# Sistema bancário simples com Java 
![ChatGPT Image 21 de mai  de 2025, 12_09_50](https://github.com/user-attachments/assets/c3028304-6d55-4521-be52-00eef9b357c4)

### Introdução 
Projeto backend desenvolvido com Java Spring Boot e Spring Security, focado em demonstrar boas práticas de autenticação, autorização e proteção de APIs RESTful. 

O objetivo principal deste projeto é apresentar, de forma clara e aplicada, como implementar um sistema seguro para operações bancárias básicas, utilizando Spring Security para proteger endpoints e gerenciar o acesso de usuários com diferentes perfis.

### Funcionalidades
* Cadastro e autenticação de usuários com criptografia de senhas via BCrypt
* Autorização baseada em perfis (ADMIN, CLIENTE)
* Proteção de endpoints com regras específicas de acesso
* Implementação de JWT (JSON Web Token) para autenticação stateless
* Refresh token para renovação de sessão
* Controle de transações bancárias (simulado)
* Abertura de conta
* Consulta de saldo
* Depósito e saque
* Transferência entre contas
* Registro e histórico de operações
* Documentação da API com Swagger (OpenAPI)
* Testes básicos de autenticação e autorização
### Arquitetura de solução 
O sistema foi construído seguindo a arquitetura em camadas:

* Controller: Responsável por expor os endpoints da API REST e receber requisições dos clientes.
* ServiceImpl: Contém a lógica de negócio central da aplicação, como validações, regras de transação e autenticação.
* Repository: Comunicação com o banco de dados através de Spring Data JPA.
* Configuration: Configuração personalizada do Spring Security, incluindo filtros JWT, controle de acesso e geração/validação de tokens.

O uso de JWT permite autenticação sem estado (stateless), ideal para aplicações modernas e escaláveis, especialmente para APIs REST consumidas por frontends em React, Angular ou aplicações mobile.
### Ferramentas
* Java 21+
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* JWT (com Java JWT / jjwt)
* Lombok
* Swagger/OpenAPI
* JUnit
### 🛠️ Como Executar o Projeto
1 - Clonar o repositório
```
git clone https://github.com/PauloAquarius0299/system-bank.git
```
2- Configure o application.properties (ou application.yml) com as credenciais do banco de dados.

3- Execute a aplicação:
```
./mvnw spring-boot:run
```
4 - Acessar o Swagger UI em:
```
http://localhost:8080/swagger-ui.html
```
