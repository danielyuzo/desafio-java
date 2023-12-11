# desafio-java

# Primeiro Acesso

- Usuário: ADM
- Login: urubu100

Após realizar o login, a aplicação verifica se o servidor já existe no banco. Em caso positivo, as configurações desse servidor são carregadas. Em caso negativo, os componentes do servidor são cadastrados com configurações padrão:

- Registros: Uso de CPU, uso de RAM e uso de Disco;
- Unidade: %;
- Limite de alerta: > 70% de uso;
- Limite crítico: > 90% de uso;
- Meta para estado de alerta: < 10% do tempo;
- Meta para estado crítico: < 5% do tempo.

Após o cadastro inicial, é exibido o menu principal, onde se pode iniciar o monitoramento, alterar as configurações dos componentes e visualizar o relatório do servidor.



Após o Início do monitoramento, o sistema registra o uso de cada componente a cada 30 segundos. Além disso, as integrações com o Jira e Slack são realizadas a cada 5 minutos.


## Configuração de Componentes

## Configuração de Integrações

Por padrão, a aplicação não realiza a criação automática de chamados e envio de mensagens; para tal, é necessário adicionar as configurações relevantes.

**Criação de Chamados Jira**

**Envio de mensagens ao Slack**

A aplicação irá enviar uma mensagem ao Slack sempre que um chamado no Jira for:
- For criado;
- Mudar de status;
- Permanecer no mesmo status por 24 horas;
- For encerrado.
