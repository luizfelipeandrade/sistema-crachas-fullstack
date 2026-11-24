FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY src ./src
COPY lib ./lib

RUN mkdir -p out && \
    find src -name "*.java" > sources.txt && \
    javac -cp "lib/*" -d out @sources.txt

EXPOSE 8080

CMD ["java", "-cp", "out:lib/*", "br.com.projetoorientadoobjeto.main.ApiServer"]