# *POA*

A aplicação tem por objetivo, consumir a API de Linhas e Itinerários do transporte público do Município de Porto Alegre.

- [Lista de Linhas de Ônibus] (http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o)
- [Consulta Itinerários a partir de determinada linha de ônibus] (http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566)


#### Banco de dados 

O banco de dados escolhido foi o ORACLE, por ser uma opção que utilizo no meu dia-a-dia. Utilizei uma imagem pública disponível no Docker hub (https://hub.docker.com/r/thebookpeople/oracle-xe-11g).

#### Spring Framework

Foi utilizado [Spring](https://spring.io/) e suas ferramentas para facilitar o desenvolvimento e a comunicação com o banco de dados.

#### Requisições

No arquivo Requisições API POA - Dimed.postman_collection.json, que se encontra no diretório raiz, estão as coleções do Postman com exemplos de requisições. 
Na requisição do Postman será possível encontrar os seguintes exemplos de requisições:
• Carregar Linhas e Itinerários na base de dados;
• Buscar Linhas da API;
• Buscar Linhas da API por nome;
• Buscar Itinerários da API por id da Linha;
• Cadastrar Cliente e suas Linhas;
• Buscar Linhas por Latitude, Longitude e Raio;
• Buscar Latitudes por Latitude, Longitude, Raio e id da Linha.

#### Aplicação

Para rodar a aplicação corretamente, será necessário executar a request de carregamento da base (/poa/carregar/linhas). Isso poderá levar alguns minutos.
Após o carregamento da base de Linhas, Itinerários e Posicionamentos, as requisições poderão ser realizadas normalmente, sem a necessidade de ordem de chamada. 
A aplicação está configurada para apagar o banco de dados toda vez que o servidor for reiniciado. Para não precisar executar o carregamento novamente, é preciso modificar o arquivo [application.properties]( https://github.com/luanaaniceto/poa/tree/master/src/main/resources/application.properties), alterando o atributo (spring.jpa.hibernate.ddl-auto) para *update*.


#### Requisitos

Os requisitos necessários são:
• IDE compatível com Projeto JAVA – MAVEN;
• Docker (https://docs.docker.com/docker-for-windows/install/)
• Postman (https://www.getpostman.com/downloads/)

#### Execução

Ao abrir o projeto na IDE desejada, executar o UPDATE PROJECTS do Maven, para que as dependências existentes no arquivo pom.xml sejam instaladas.
Para inicializar o banco, será necessário executar o bat (docker_oracle_run.bat), que se encontra no diretório raiz. 
Executar o tópico (#### Aplicação).

