# CRUD API Java Servlet

Este projeto é um exemplo de CRUD de usuários usando Java Servlet, Reflection, Generics, Inversão de Controle (IoC) e Injeção de Dependência (DI), simulando como frameworks funcionam por baixo dos panos.

## Estrutura de Pastas

```
crud/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── filipe/
│   │   │           ├── controller/
│   │   │           │   └── UserController.java
│   │   │           ├── model/
│   │   │           │   └── User.java
│   │   │           ├── repository/
│   │   │           │   └── GenericRepository.java
│   │   │           ├── service/
│   │   │           │   └── UserService.java
│   │   │           └── ioc/
│   │   │               └── SimpleIoCContainer.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       └── form.html
│   └── test/
│       └── java/
├── pom.xml
```

## Funções e Classes

### 1. UserController (controller)
- Servlet principal que recebe requisições HTTP.
- Decide a ação (listar, buscar, criar, editar, remover) com base no parâmetro `action`.
- Usa reflection para chamar métodos do serviço.
- Responde com HTML simples.

### 2. User (model)
- Representa o usuário, com campos `id`, `name` e `email`.

### 3. GenericRepository (repository)
- Repositório genérico em memória para armazenar entidades.
- Métodos: `create`, `read`, `readAll`, `update`, `delete`.

### 4. UserService (service)
- Lógica de negócio para manipular usuários.
- Usa o repositório para CRUD.
- Métodos: `createUser`, `getUser`, `getAllUsers`, `updateUser`, `deleteUser`.

### 5. SimpleIoCContainer (ioc)
- Simula um container de Inversão de Controle.
- Gerencia instâncias e dependências das classes.

### 6. form.html
- Formulário HTML para testar todas as operações do CRUD.

## Como rodar
1. Configure o JAVA_HOME corretamente.
2. Execute:
   ```
   mvn clean package
   mvn jetty:run
   ```
3. Acesse:
   - `http://localhost:8080/user?action=list` para listar usuários.
   - `http://localhost:8080/user?action=get&id=1` para buscar por ID.
   - Use o `form.html` para criar, editar e remover usuários.

## Observações
- O projeto não usa banco de dados, apenas memória.
- O objetivo é didático, para mostrar conceitos de frameworks Java.

---
Se quiser mais exemplos ou explicações, consulte os comentários nos arquivos ou peça aqui!
