ECHO compiling license information...
del  /Q licenses\*.*

cd Elite2Angular
call npm-license-crawler --csv ..\licenses\Elite2Angular-thrid-party-licenses.csv
cd ..

call mvn -f Elite2Common\pom.xml license:add-third-party
copy Elite2Common\target\generated-sources\license\THIRD-PARTY.txt licenses\Elite2Common-third-party-licenses.txt

call mvn -f Elite2DAO\pom.xml license:add-third-party
copy Elite2DAO\target\generated-sources\license\THIRD-PARTY.txt licenses\Elite2DAO-third-party-licenses.txt

call mvn -f Elite2Business\pom.xml license:add-third-party
copy Elite2Business\target\generated-sources\license\THIRD-PARTY.txt licenses\Elite2Business-third-party-licenses.txt

call mvn -f Elite2Web\pom.xml license:add-third-party
copy Elite2Web\target\generated-sources\license\THIRD-PARTY.txt licenses\Elite2Web-third-party-licenses.txt
