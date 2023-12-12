# desafio-java

# Primeiro Acesso

Para utilizar a aplicação, execute o arquivo 

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

## Configuração da Integração Slack

Por padrão, a aplicação não realiza o envio de mensagens ao Slack.
Para que o envio de mensagens seja realizado, é necessário configurar o Webhook
do Slack que deve ser então inserido na aplicação.

A aplicação irá enviar uma mensagem ao Slack sempre que um componente ultrapassar
o limite de alerta ou limite crítico.

** Criando um WebHook **

Acesse o link:

https://api.slack.com/apps

Crie ou selecione um App Slack do seu WorkSpace.

Após selecionar o App, no menu à esquerda selecione "Incoming Webhooks", na seção
"Features". No final da página clique em "Add New Webhook to Workspace", e selecione 
o Canal do Slack onde as mensagens devem ser postadas.

Copie a URL fornecida para o Webhook, e cole a mesma através da opção 3 do Menu
Principal.