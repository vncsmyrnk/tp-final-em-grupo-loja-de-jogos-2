# Loja de Jogos

Este é um projeto em java para a gerência de informações de uma loja de jogos.

## Docker

Um container Docker poderá ser utilizado para executar a aplicação. O seguinte comando instancia um container:

```bash
docker run -it --rm -v "$(pwd)":/usr/src/app maven:3.3-jdk-8 bash
```

## Execução

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.lojaJogos.App"
```

## Testes

```bash
mvn clean test
```

Os reports de cobertura dos testes estarão em `target/site/jacoco/index.html`

### Reports

```bash
mvn surefire-report:report
```

O report geral de testes estará em `target/site/surefire-report.html`.
