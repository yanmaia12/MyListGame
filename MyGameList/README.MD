#  MyGameList

O **MyGameList** é uma aplicação de linha de comando (CLI) desenvolvida em Java, inspirada em plataformas como o MyAnimeList. Ela permite que os usuários criem contas, busquem jogos reais e gerenciem suas próprias listas de avaliações e notas.

##  Funcionalidades

* **Autenticação Segura:** Sistema de login e cadastro com criptografia de senhas utilizando **BCrypt**.
* **Integração com API Externa:** Busca de jogos em tempo real utilizando a API da **IGDB** (via autenticação da Twitch).
* **CRUD Completo:** Criação, leitura, atualização e exclusão de avaliações de jogos.
* **Banco de Dados Relacional:** Estruturado em MySQL, utilizando Chaves Estrangeiras (Foreign Keys) .
* **Validação de UX (User Experience):** Tratamento de entradas do usuário (prevenção de crashes, validação de email/username único, tratamento de vírgulas/pontos em notas decimais).

##  Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Banco de Dados:** MySQL (JDBC)
* **APIs:** IGDB API v4 / Twitch OAuth2
* **Segurança:** jBCrypt
* **Gerenciador de Dependências:** Maven / Spring Boot (Data JPA, WebMVC)

---

##  Como rodar o projeto localmente

Para executar este projeto na sua máquina, você precisará configurar o ambiente de banco de dados e as chaves de API.

### 1. Pré-requisitos
Certifique-se de ter instalado em sua máquina:
* **Java JDK 21** ou superior.
* **Servidor MySQL e Apache:** A forma mais fácil de obter os dois é baixando e instalando o [XAMPP](https://www.apachefriends.org/pt_br/index.html).

### 2. Configurando o Banco de Dados (XAMPP)
1. Abra o painel de controle do XAMPP.
2. Inicie os módulos **Apache** e **MySQL** (clicando em "Start" ao lado de cada um).
3. O banco de dados MySQL deve estar rodando na porta padrão `3306`. O programa se encarregará de criar as tabelas `usuarios` e `avaliacoes` automaticamente ao rodar pela primeira vez, graças à configuração no `application.properties`.

### 3. Configurando as Variáveis de Ambiente (Keys.java)
Por questões de segurança, o arquivo de credenciais está no `.gitignore` e não vem junto com o clone do repositório. Você precisará criá-lo:

1. Navegue até a pasta: `src/main/java/com/yanmaia12/MyGameList/`
2. Crie um arquivo chamado `Keys.java`.
3. Adicione o seguinte código, substituindo pelas suas credenciais reais do seu banco local e da sua conta de desenvolvedor da Twitch/IGDB:

```java
package com.yanmaia12.MyGameList;

public class Keys {
    // Configurações do Banco de Dados
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mygamelist";
    public static final String DB_USER = "root"; // Seu usuário do MySQL
    public static final String DB_PASSWORD = ""; // Sua senha do MySQL (vazio por padrão no XAMPP)

    // Configurações da API IGDB/Twitch
    public static final String TWITCH_CLIENT_ID = "SEU_CLIENT_ID_AQUI";
    public static final String TWITCH_CLIENT_SECRET = "SEU_CLIENT_SECRET_AQUI";
}