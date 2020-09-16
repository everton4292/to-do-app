# :white_check_mark: To Do List

<p align="center"><br>|
  <a href="https://github.com/everton4292/to-do-app#to-do-list">:white_check_mark:To Do List</a>  | 
  <a href="https://github.com/everton4292/to-do-app#instruções-de-uso">:id:Instruções de uso</a>  | 
  <a href="https://github.com/everton4292/to-do-app#imagens">:camera:Imagens</a>  | 
  <br><br>
 </p>

## :id: Instruções de uso

### Criando o Github Oauth App, o ClientId e o ClientSecret para o projeto

Para fazer o app funcionar, será necessário criar um app no Oauth do Github, com as instruções disponíveis [aqui](https://docs.github.com/en/developers/apps/creating-an-oauth-app).

Em seguide, copie o ClientID e o ClientSecret para utilizá-los no processo. Copie também o "Authorization callback URL" especificado por você na criação do app ouath.

Esses dados serão necessários para a realização da configuração do Oauth. Dentro do projeto, na "GreetingsActivity", observe o comentário com instruções para aplicação do ClientId(linhas 35 e 85). 

No AndroidManifest, no intent-filter relacionado à "GreetingsActivity", na seção "data", insira o host e o callback referente ao seu app. Por exemplo, uma url neste formato "meuapp://callback" tem como scheme: "meuapp", e como host:"callback", campos presentes na seção "data" do intent-filter.

## :iphone: A Aplicação

### Tela de Greetings 

O app inicia-se com uma tela de boas vindas, apresentando um botão e um link para o repositório do projeto. Acionar o botão leva ao processo de Oauth utilizando o login e a senha do Github num dos browsers disponíveis no seu telefone. Após a realização do login, o app apresenta na tela um dialog com o seu token obtido através do Oauth. Pressionar em algum lugar fora do dialog ou no botão de OK levará o usuário para a tela principal do app.

### To Do List

Na tela principal do app encontra-se uma lista e dois botões. A lista representa as atividades inseridas no banco de dados local do celular, caso não hajam atividades cadastradas, a lista estará em branco. Atividades listadas apresentam-se na forma de um "cartão" onde constam título, descrição e o número da atividade.

O botão com um sinal de "+" acionará um dialog onde é possível digitar o nome e a descrição de atividades a serem adicionadas à lista. É necessário que ambos os campos sejam preenchidos, caso contrário, a atividade não será aceita. Esta forma de validação foi feita para prevenir erros de comunicação com o banco de dados e tornar os tipos de informação mais uniformes. Caso o usuário queira retornar à lista, basta pressionar fora do dialog.

O botão com uma imagem de uma cesta de lixo acionará um dialog onde é possível digitar o número da atividade a ser apagada. Ao digitar o número e pressionar "deletar", a atividade é apagada do banco de dados, não sendo possível sua recuperação. Como forma de validação, não é possível digitar letras ou palavras neste campo, tampouco é possível deixá-lo em branco. 

Pressionar um dos itens da lista por cerca de 1 segundo(clique longo) acionará um dialog que permite a edição das atividades apresentadas na lista, sendo possível modificar o nome ou a descrição. O número da atividade é um identificador único que não pode ser alterado pelo usuário.

Os dados salvos permanecerão na lista até que o usuário os delete os apague o aplicativo do celular.

Criado usando [Kotlin](https://kotlinlang.org) e [Android Studio](https://www.google.com.br/search?client=opera&q=android+studio&sourceid=opera&ie=UTF-8&oe=UTF-8)

Foram usados:
1. Coroutines
1. Clean Architecture
1. Room
1. Koin
1. MVVM Architecture
1. Mockito
1. Android Material Design
1. Github Oauth Token Authorization
1. Retrofit

### O app possui

* Coroutines integradas com o Room para chamadas assíncronas ao banco de dados
* Injeção de dependência usando o Koin
* Tests unitários para chamadas ao banco de dados
* Arquitetura View Model e separação de responsabilidades
* Design fluído com base nas orientações do Material Design do Google
* Validação nos inputs do usuário para evitar crashs

### Próximos passos

* Maior abrangência de testes unitários
* Melhorias na arquitetura
* Outras formas de login Oauth
* Revogação de token para logout
* Armazenamento do token para evitar re-login constante
* Melhorias na estabilidade e na proteção contra crashs e exceptions


## :camera: Imagens

![Mainr](https://i.imgur.com/q84ClxH.png)  ![Mainr2](https://i.imgur.com/FertoIE.png)

