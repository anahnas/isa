# Projekat iz predmeta ISA 2019/2020
Clanovi tima: Bojan Markovic RA 143/2016, Veljko Maksimovic RA 145/2016, Ana Tomic RA 20/2016 <br/>

Backend: https://github.com/anahnas/isa <br/>

Frontend: https://github.com/bole25/client-app <br/>


Za izradu projekta korišteni su: 
<ul>
  <li> Spring Boot </li>
  <li> MySQL baza </li>
  <li> Angular 8 </li>
</ul>

Korištena radna okruženja: IntelliJ i za backend i za frontend i MySqlWorkbench za MySql bazu

Pokretanje back dijela ( isa folder)
<ul> 
 <li> U lokalu kreirati MySQL bazu podataka sa nazivom isa. </li>  
  <li> Zamijeniti trenutni sadržaj aplication.properties fajla sa sledećim:
    <pre>
      spring.datasource.initialization-mode=always
      spring.datasource.platform=mysql
      spring.datasource.url=jdbc:mysql://localhost:3306/clinicalsys?useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
      spring.datasource.username=root
      spring.datasource.password=cefaleksin
      spring.jpa.show-sql = false
      spring.jpa.hibernate.ddl-auto = create-drop
      spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

  <li> Uraditi maven update projekta </li>
  <li> Projekat se pokreće na portu 8080. </li>
  <li> startup_script - skripta za kreiranje baze i insert podataka </li>
   <li> Za kontinualnu integraciju i kontinualno testiranje koristen je Travis CI Cloud servis </li>
    <li> Za proveru kvaliteta koda koristen je SonarLint</li>
</ul>

Frontend::Angular aplikacija  
<ul>
  <li> npm install </li> 
  <li> npm run serve </li>
</ul>
<br>

  Aplikaciji se pristupa putem:
  <ul>
    <li><a href="http://localhost:4200">http://localhost:4200</a></li>
  </ul>
  <br>
  Deployment:
    
  <ul>
    <li><a href="https://klijent.herokuapp.com/">Link</a></li>
    <li>(Na deploy-ovanoj bazi se nalazi podskup podataka iz gore navedene skripte)</li>
    <li>Za deployment Angular aplikacije i Java spring boot aplikacije koristen Heroku</li>
    <li>MySql baza deploy-ovana na remotemysql.com</li>
  </ul>





