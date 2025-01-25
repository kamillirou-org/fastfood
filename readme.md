# FastFood API #

Projeto do primeiro módulo da Pós-Graduação em Software Architecture - FIAP.
Este projeto utiliza Spring Boot para o backend e está containerizado com Docker e Docker Compose para facilitar a configuração e execução.

### 🛠️ Tecnologias Utilizadas
•	Java 21
•	Spring Boot 3
•	Spring Data JPA
•	PostgreSQL
•	Docker
•	Docker Compose

### 📂 Estrutura do Projeto
•	Dockerfile: Define como construir a imagem Docker da aplicação.
•	docker-compose.yml: Configura o ambiente com os serviços necessários (aplicação e banco de dados).
•	src/: Contém o código-fonte do projeto (controladores, modelos, repositórios, etc.).

### 📋 Pré-requisitos

Antes de começar, certifique-se de ter os seguintes softwares instalados na sua máquina:
•	Docker
•	Docker Compose

### 🏗️ Passos para Iniciar o Projeto Localmente

Siga as etapas abaixo para rodar o projeto em sua máquina:

1. Clone o Repositório

       $ git clone https://github.com/kamillirou/fastfood.git
       $ cd fastfood-api

2. Suba o container do postgres, o banco precisa estar disponível para o package acontecer devido aos testes
    ```    
    $ docker compose up postgres
    ```
3. Rode o seguinte comando para gerar o jar do projeto
    ```
    $ ./mvnw package
    ``` 
4. Suba o container da aplicação
    ```
    $ docker compose up app
    ```

5. Acesse a Aplicação
   - A API estará disponível em http://localhost:8080.
   - O banco de dados PostgreSQL estará acessível na porta 5432.
   - O Swagger pode ser acessado no endereço http://localhost:8080/swagger-ui/index.html