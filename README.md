# fintechADA

<h2> Arquitetura de Software Ágil I - Projeto Final</h2>

Nesse projeto usamos o padrao service discovery para descoberta dos microservicos.

Contem 3 serviços

<p><b>fintechADAServer</b> - Nele contem o eureka para descoberta dos servicos.</p>
<p><b>fintechADAPessoa</b> - Responsavel por cadastrar pessoas. Nele tambem existe um database em memoria que é startado na porta 8782.</p>
<p><b>fintechADAConta</b> - Responsavel por cadastrar contas e realizar cambio do dinheiro de uma pessoa. Nele também existe um database que é startado na porta 8783.</p>

<p>Toda vez que o serviço de salvar pessoa é chamado, automaticamente é feito uma "replica" de algumas informações essenciais para o database de conta (PORTA 8783).</p>

<p><b>Como conectar no database?</b></p>

<p>URL : http://localhost:8763/h2</p>
<p>JDBC URL: jdbc:h2:mem:fintech</p>
<p>User Name: sa	</p>
<p>Password: </p>


<p><b>Exemplos de chamadas do serviço se encontra no arquivo FINTECH-ADA.postman_collection.json  que está na raiz do projeto fintechADA.</b></p>

