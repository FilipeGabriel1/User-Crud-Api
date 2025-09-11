# CRUD Java Maven

Este projeto é um exemplo de aplicação CRUD de usuários utilizando Java Servlet, Reflection, Generics, Inversão de Controle (IoC) e Injeção de Dependência (DI), simulando como frameworks funcionam por baixo dos panos.

## Estrutura do Projeto

```
crud/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/filipe/
│   │   │   ├── controller/UserController.java
│   │   │   ├── model/User.java
│   │   │   ├── repository/GenericRepository.java
│   │   │   ├── service/UserService.java
│   │   │   └── ioc/SimpleIoCContainer.java
│   │   └── webapp/formulario.html
│   └── test/java/
└── target/
```

## Como executar

1. Certifique-se de ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) e [Maven](https://maven.apache.org/download.cgi) instalados.
2. No terminal, navegue até a pasta do projeto e execute:

```powershell
mvn jetty:run
```

3. Acesse a aplicação em [http://localhost:8080/user](http://localhost:8080/user) ou utilize o formulário em `formulario.html`.

## Funcionalidades
- Cadastro de usuários
- Listagem de usuários
- Atualização de dados
- Remoção de usuários

## Principais arquivos
- `User.java`: Modelo de usuário
- `UserService.java`: Lógica de negócio
- `GenericRepository.java`: Persistência de dados
- `UserController.java`: Controlador da aplicação
- `formulario.html`: Interface web para interação

## Licença
Projeto para fins educacionais.
