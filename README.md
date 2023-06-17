# Skys API - Sistema de Reservas de Passagens Aéreas
A Skys API é uma API REST desenvolvida em Java com o framework Spring. Ela oferece os recursos necessários para pesquisar voos, realizar reservas de passagens aéreas e gerenciar as informações relacionadas aos usuários e voos disponíveis.

## Tecnologias
A Skys API utiliza as seguintes tecnologias:
* Java: Linguagem de programação utilizada para desenvolver a lógica da API.
* Spring Boot: Framework Java que facilita a criação de aplicativos robustos e escaláveis.
* Spring MVC: Módulo do Spring que fornece recursos para a criação de APIs REST.
* Spring Data JPA: Biblioteca do Spring que simplifica a interação com o banco de dados.
* MySQL: Banco de dados relacional utilizado para armazenar as informações dos voos e usuários.
* Hibernate: Framework de mapeamento objeto-relacional utilizado para facilitar a integração com o MySQL.
* Maven: Ferramenta de automação de compilação e gerenciamento de dependências para o projeto Java.
Essas tecnologias foram escolhidas devido à sua popularidade, suporte ativo da comunidade e capacidade de construir uma API eficiente e escalável.

## Configuração
Antes de executar a API, certifique-se de ter as seguintes ferramentas e recursos configurados:
* Java Development Kit (JDK) 8 ou superior instalado na máquina.
* MySQL instalado e configurado corretamente.

Além disso, você precisará configurar as seguintes variáveis de ambiente:

* DB_HOST: URL de conexão com o banco de dados MySQL.
* DB_USER: Nome de usuário do banco de dados.
* DB_PASSWORD: Senha do banco de dados.

## Executando a API
Após configurar o ambiente e o banco de dados, siga as etapas abaixo para executar a Skys API:
1. Clone o repositório para sua máquina local.
`git clone https://github.com/seu-usuario/skys-api.git`
2. Acesse o diretório do projeto.
`cd skys-api`
3. Compile o projeto usando o Maven.
`mvn clean install`
4. Execute a API.
`java -jar target/skys-api.jar`
Isso iniciará o servidor da API e estará pronto para receber as requisições.
