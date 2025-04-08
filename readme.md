## 🍔 FastFood - API de Pedidos ##

Este projeto simula uma API de pedidos de um sistema de FastFood, com foco em boas práticas de arquitetura, escalabilidade e segurança. Foi desenvolvido com Spring Boot, PostgreSQL e Kubernetes, visando práticas utilizadas em ambientes de produção.

⸻

### 🔧 Requisitos do Negócio

A aplicação deve:
- Permitir a criação, leitura, atualização e exclusão de pedidos.
- Registrar o status de um pedido (ex: recebido, em preparo, pronto, entregue).
- Manter persistência dos dados utilizando PostgreSQL.
- Ser escalável e segura.

⸻

### ☁️ Arquitetura da Solução

#### Visão Geral

Cliente -> (HTTP) -> Service -> (REST) -> SpringBootApp -> (JDBC) -> PostgreSQL

Componentes
- Spring Boot App: API REST principal com endpoints para gerenciamento de pedidos
- PostgreSQL: Banco de dados relacional persistente
- Kubernetes: Orquestração dos containers (aplicação + banco) localmente
- HPA: Auto escalonamento dos pods com base na utilização de CPU
- ConfigMap/Secret: Variáveis de ambiente seguras para a aplicação e banco de dados

Infraestrutura Utilizada
- Kubernetes com Docker Desktop (local)
- Metrics Server (para HPA)
- kubectl para deploy dos recursos
- Arquivos YAML versionados na pasta k8s/
- Docker + Dockerfile para build da imagem da aplicação

⸻

### 📡 APIs Disponíveis

#### Documentação via Swagger:

http://localhost:8080/swagger-ui.html

Tudo pode ser visto com exemplos no swagger

⸻

### 🚀 Instruções de Execução

#### Pré-requisitos
- Java 21
- Maven
- Docker Desktop com Kubernetes ativado
- kubectl instalado
- Git

Passo 1: Clonar o projeto
```
git clone https://github.com/seu-usuario/fastfood.git
cd fastfood
```
Passo 2: Build da imagem
```
./mvnw clean package -DskipTests
docker build -t fastfood-app .
```
Passo 3: Deploy com Kubernetes
```
kubectl apply -f k8s/
```
Verifique os pods com:
```
kubectl get pods
```
Acesse a aplicação em:

http://localhost:8080/swagger-ui.html

### 🔐 Boas práticas implementadas
- Uso de Deployment e Service para app e banco
- ConfigMap para configurações gerais da aplicação
- Secrets para dados sensíveis (usuário/senha do banco)
- HPA com base no consumo de CPU
- Documentação OpenAPI com Swagger

⸻

### 📹 Demonstração em Vídeo

Assista no YouTube: 

O vídeo mostra:
- Estrutura dos arquivos YAML
- Aplicação rodando via Kubernetes
- Testes dos endpoints usando Swagger
- Escalabilidade com  HPA

⸻

### 📁 Estrutura de Pastas

```
.
├── k8s/                     # Arquivos YAML do Kubernetes
├── src/                     # Código fonte da API
├── Dockerfile
├── pom.xml
└── README.md
```

⸻

### 🛠️ Tecnologias Usadas
•	Java 21
•	Spring Boot
•	PostgreSQL
•	Docker
•	Kubernetes
•	Swagger/OpenAPI

⸻