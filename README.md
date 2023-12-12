# desafio-java

## Configurando a Aplicação

Antes de se utilizar a aplicação, deve-se configurar um banco de dados local MySQL.
O script para criação do Banco de Dados se encontra no arquivo ScriptBancoDesafio.sql,
na raiz deste repositório.

Após configurado o banco de dados, para utilizar a aplicação, execute o arquivo 
desafio-java-1.0-SNAPSHOT-jar-with-dependencies.jar, localizado na pasta *target* 
deste repositório.

## Primeiro Acesso

- Usuário: admin@graphcar.com
- Login: urubu100

Após realizar o login, a aplicação verifica se o servidor já existe no banco. 
Em caso positivo, as configurações desse servidor são carregadas. 
Em caso negativo, os componentes do servidor são cadastrados com configurações padrão:

- Registros: Uso de CPU, uso de RAM e uso de Disco;
- Unidade: %;
- Limite de alerta: > 70% de uso;
- Limite crítico: > 90% de uso.

Após o cadastro inicial, é exibido o menu principal, onde se pode iniciar o 
monitoramento, alterar as configurações dos componentes e alterar o WebHook
do Slack.


Após o Início do monitoramento, o sistema registra o uso de cada componente a cada 30 segundos. 
Além disso, a integração com o Slack é realizada a cada 2 minutos.


## Configuração de Componentes

Neste menu é possível alterar tanto quais componentes devem ser monitorados
quanto seus respectivos limites para alertas.

Após selecionar qual componente se deseja configurar, são exibidas as configurações
atuais do mesmo, e as opções do que é possível alterar.

## Configuração da Integração Slack

Por padrão, a aplicação não realiza o envio de mensagens ao Slack.
Para que o envio de mensagens seja realizado, é necessário configurar o Webhook
do Slack que deve ser então inserido na aplicação.

A aplicação irá enviar uma mensagem ao Slack sempre que um componente ultrapassar
o limite de alerta ou limite crítico.

### Criando um WebHook

Acesse o link:

https://api.slack.com/apps

Crie ou selecione um App Slack do seu WorkSpace.

Após selecionar o App, no menu à esquerda selecione "Incoming Webhooks", na seção
"Features". No final da página clique em "Add New Webhook to Workspace", e selecione 
o Canal do Slack onde as mensagens devem ser postadas.

Copie a URL fornecida para o Webhook, e cole a mesma através da opção 3 do Menu
Principal.


# Testando a Aplicação

Realize o primeiro Login, conforme detalhado no início deste readme. Caso se deseje
testar o Slack, crie um WebHook e adicione-o à aplicação.

Inicie o monitoramento, e abra um acesso ao banco MySQL, para verificar a inserção
dos dados. Execute a query:

SELECT * FROM Dados ORDER BY idDados DESC LIMIT 5;

Verifique se todos os 3 componentes estão sendo inseridos. Acesse então a opção 2 
do menu principal. Escolha um componente qualquer, e então altere o seu monitoramento.
Aguarde alguns segundos, e então execute a query novamente. Verifique se o componente
não está mais sendo monitorado.

Caso a integração com o Slack esteja ativa, verifique os valores que estão sendo
inseridos para os componentes. Altere a configuração dos componentes, de modo
que os limites de alerta/crítico estejam abaixo dos valores que estão sendo inseridos
no Banco. Aguarde alguns minutos, e verifique então se foram enviadas mensagens
ao canal do Slack.