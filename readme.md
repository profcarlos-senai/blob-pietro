# Exemplo de Salvar arquivos em banco de dados

Esse programa usa o banco de dados H2 (que fica armazenado na pasta /data do projeto)

- você pode acessar o console do banco através de `localhost:8080/h2-console`
- o campo JDBC URL é `jdbc:h2:file:./data/database`
- user name `sa`
- senha `admin`

API
- GET `localhost:8080/arquivos` - a lista as descrições de todos os arquivos
- GET `localhost:8080/arquivos/{id}` - recupera a descrição do arquivo com a id informada
- GET `localhost:8080/arquivos/download{id}` - faz o download do arquivo com a id informada
- POST `localhost:8080/arquivos` - envia um arquivo

PARA ENVIAR UM arquivo

- copie um arquivo (com menos de 10MB) para uma pasta
- abra a linha de comando CMD.EXE (não pode ser o powershell)
- execute o comando abaixo:

	`curl -X POST http://localhost:8080/arquivos/upload  -F "arquivo=@meuarquivo.pdf" -F "descricao=Meu lindo arquivo"`
	
- você também pode separar o comando CURL em várias linhas no windows usando `^` ao fim de cada linha

	`curl -X POST http://localhost:8080/arquivos/upload  ^`
	
	`-F "arquivo=@meuarquivo.pdf" ^`
	
	`-F "descricao=Meu lindo arquivo"`
	
O programa já tem alguns arquivos cadastrados. Você pode consultar usando CURL na linha de comando ou pelo navegador. Se você instalar uma extensão de JSON no chrome ou firefox ele mostra de um jeito mais bonito

exemplos:

	CURL localhost:8080/arquivos		(retorna um JSON com todos)
	CURL localhost:8080/arquivos/1		(retorna um JSON com a descrição de um arquivo sem os dados)
	CURL localhost:8080/arquivos/1234	(retorna 404 not found, use -v para ver detalhes da resposta)
	CURL -o slides.pdf localhost:8080/arquivos/download/1	(faz o download do arquivo)
	
É só usar essas URLS sem o curl na frente para ver no navegador