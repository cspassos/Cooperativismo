# API de Votação - COOPERATIVISMO

Foi desenvolvido uma api de votação na qual será utilzada no cooperativismo. Cada associado possui um voto e as decisões são tomadas em assembleias, por votação.

## Objetivo

* Cadastrar uma nova pauta;
* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
* Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
* Contabilizar os votos e dar o resultado da votação na pauta;

## Tarefas bônus

* Tarefa Bônus 1 - Integração com sistemas externos;
* Tarefa Bônus 4 - Versionamento da API:
	R= Versionaria atraves do número da versão nas URLs da API, como exemplo utilizando o v1 ex: http://localhost:8080/v1/sessao .

## Tecnologias

* Java 8;
* Spring Boot Web; JPA; Data;
* PostgreSQL;
* Lombok

## Instalação

```
$ git clone https://github.com/cspassos/Cooperativismo
$ Dentro do projeto con figurar o application.properties de acordo ao seu PostgreSQL;
$ application.properties : colocar a porta do seu PostgreSQL no "spring.datasource.url";
	* OBS: Geralmente a porta vai ser a 5432;
$ application.properties : colocar username e password do seu PostgreSQL;
$ Executar um maven clean;
$ Executar um maven update;
