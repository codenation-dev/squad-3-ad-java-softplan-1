# squad-3-ad-java-softplan-1

### Error Logger

#### Visão Geral:

Aplicação desenvolvida como requisito final da aceleração em Java promovida pela Codenation.
O Objetivo da aplicação é basicamente a realização de uma API que seja capaz de armazenar erros em 
banco de dados relacional, criando-se os endpoints para serem consumidas pelo Frontend.
Trata-se de uma aplicação Rest criada com Spring-boot e suas dependências, conforme pom.xml.
A segurança da aplicação foi desenvolvida com utiização da JWT. Embora as aulas tenham sido lecionadas
ensinando o padrão OAUTH2, como a empresa Softplan (patrocinadora do projeto) utiliza a abordagem JWT
em suas aplicações, a equipe optou por este tipo de autenticação.

#### Instalação:

##### Para instalar o projeto é necessária a realização dos seguintes passos:
###### 1. Abra o terminal e digite na pasta raiz do projeto:
```
mvn install 
```
###### 2. após isso digite:
```
cd target
java [nome do arquivo gerado]
```

#### Deploy do Projeto:

O Deploy do projeto foi realizado no site [Heroku](http://www.heroku.com)

A API se encontra disponível em: [ErrorLogger](https://errorlogcodenation.herokuapp.com/)

O frontend se encontra disponível em: [frontend]()

#### Documentação:

A documentação do projeto foi realizada através do Swagger, e se encontra disponível em [documentação](https://errorlogcodenation.herokuapp.com/swagger-ui.html)





