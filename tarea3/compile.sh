#!/usr/bin/env bash
#
# turismoUy compiling utility
# Usage: compile.sh [--all]
#
# 11-11-2022

usage() {
  echo "
    Usage: compile.sh [--all]
             -a o --all:  Compile all components
    " 1>&2
}


parse_arg() {

  #set default values
  all_flag=FALSE

  #parsea las opciones, si las hay
  for arg in "$@"; do
    shift
    case "$arg" in
      "--help" | "-h" ) usage; exit 0 ;;
      "--all"  | "-a" ) all_flag=TRUE ;;
      "--"*    | "-"* )  echo  "\n""Illegal option ${arg}" 1>&2; usage; exit 1;;
      *               )  set -- "$@" "$arg" ;;
    esac
  done

  shiftcount=$(( OPTIND - 1 ))
  shift $shiftcount

}

servidorCentral() {
  cd EstacionTrabajo
  mvn install:install-file -Dfile=JTimeChooser_0.1.0.jar -DgroupId=lu.tudor.santec -DartifactId=jtimechooser -DgeneratePom=true -Dversion=0.1.0 -Dpackaging=jar
  mvn clean package
  cd ..
  cp EstacionTrabajo/target/estacion-trabajo-0.0.1-SNAPSHOT.jar servidor.jar
}

servidorWeb() {
  cd Sitio
  mvn clean package
  cd ..
  cp Sitio/target/Sitio-0.0.1-SNAPSHOT.war web.war
}

servidorMovil() {
  cd SitioMovil
  mvn clean package
  cd ..
  cp SitioMovil/target/SitioMovil-0.0.1-SNAPSHOT.war movil.war
}

finalizar() {
  mkdir -p ~/.turismoUy
  cp -r EstacionTrabajo/img/ ~/.turismoUy/
  cp EstacionTrabajo/config.properties ~/.turismoUy/
  echo pathImagenes=$HOME/.turismoUy/img/ >> ~/.turismoUy/config.properties
  echo ...
}

printMenu() {
    echo "----------------------------------------------"
    echo "-        turismoUy compiling utility         -"
    echo "----------------------------------------------"
    echo "- Please select an option                    -"
    echo "- 1. Compile All Components                  -"
    echo "- 2. Compile ServidorCentral                 -"
    echo "- 3. Compile ServidorWeb                     -"
    echo "- 4. Compile ServidorMovil                   -"
    echo "- 5. Exit                                    -"
    echo "----------------------------------------------"
    read  -p "Input Selection: " input
    if [ $input = "1" ]; then
        servidorCentral
	servidorWeb
	servidorMovil
	finalizar
    elif [ $input = "2" ]; then
        servidorCentral
    elif [ $input = "3" ]; then
        servidorWeb
    elif [ $input = "4" ]; then
        servidorMovil
    elif [ $input = "5" ]; then
        exit
    else
        clear
        printMenu
    fi
}

mv settings.xml ~/.m2/
parse_arg "$@"
if [ "$all_flag" = TRUE ]; then
  servidorCentral
  servidorWeb
  servidorMovil
  finalizar
else
  printMenu
fi
