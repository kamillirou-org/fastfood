# Usar uma imagem base para Java
FROM openjdk:21-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o container
COPY target/*.jar app.jar

# Expor a porta que a aplicação irá usar
EXPOSE 8080

# Comando para executar o JAR
CMD ["java", "-jar", "app.jar"]