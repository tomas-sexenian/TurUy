## Como buildear los binarios:
Se puede buildear utilizando eclipse o usando Maven desde la línea de comando:

### Servidor Central:
Usando eclipse: 

Click derecho en el proyecto EstacionTrabajo > Run as > maven build 
Esto abre una ventana para configurar maven, lo unico que hay que hacer es agregar en goals "clean package" (sin comillas) y darle "Run".


Usando maven desde la linea de comandos:
parados en el directorio raíz de EstacionTrabajo, por ejemplo "/home/luciano/git/tpgr01/tarea3/EstacionTrabajo/" ejecutamos:
```
$ mvn clean package
```

### Sitio y SitioMovil
Usando eclipse: 

Click derecho en el proyecto Sitio > Run as > maven build 
Esto abre una ventana para configurar maven, lo unico que hay que hacer es agregar en goals "clean package" (sin comillas) y darle "Run".


Usando maven desde la linea de comandos:
parados en el directorio raíz de Sitio, por ejemplo "/home/luciano/git/tpgr01/tarea3/Sitio/" ejecutamos:
```
$ mvn clean package
```

## Como desplegar la app:

### Servidor central:
Lo primero es ejecutar el jar del servidor central:
```
$ java -jar <acá ponen el path al jar del servidor central>
```

Se abre el programa y cargan los datos: Sitema > cargar datos. Luego publican el servicio: Sistema > publicar.




### Sitios web desktop y movil:

en tarea3/tomcat/bin ejecutar el script startup.sh:
```
$ ./startup.sh
```
Esto ejecuta tomcat, que escucha en localhost:8080

En el navegador, acceder a localhost:8080/manager

Acceder utilizando el usuario "grupo1" pass "grupo1"

En el apartado "Deploy" agregar:

1. Sitio de escritorio:
	Context path: "/Sitio"
	War o directory path: acá ponen el path al .war de sitio, ejemplo: "/home/luciano/git/tpgr01/tarea3/Sitio/target/Sitio-0.0.1-SNAPSHOT.war"

2. Sitio Movil:
	Context path: "/Sitio"
	War o directory path: acá ponen el path al .war de sitio movil, ejemplo: "/home/luciano/git/tpgr01/tarea3/SitioMovil/target/SitioMovil-0.0.1-SNAPSHOT.war"



