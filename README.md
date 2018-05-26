Academy
=======

[![Build Status](https://travis-ci.org/aliskhakov/stc-9-sun.svg)](https://travis-ci.org/aliskhakov/stc-9-sun)

Учебный проект

## Требования

- Java 8
- Postgres 9.6
- Apache Tomcat 7

## Установка

Небходимо задать следующие переменные окружения:

 - `ACADEMY_APP_JDBC_DRIVER_CLASS`
 - `ACADEMY_APP_JDBC_DRIVER_CLASS`
 - `ACADEMY_APP_JDBC_URL`
 - `ACADEMY_APP_JDBC_USERNAME`
 - `ACADEMY_APP_JDBC_PASSWORD`

Например, при использовании [Apache Tomcat](http://tomcat.apache.org/) на Linux или  OS X, можно прописать в файле `${catalina.base}/bin/setenv.sh`:


```sh
export ACADEMY_APP_JDBC_DRIVER_CLASS=org.postgresql.Driver
export ACADEMY_APP_JDBC_URL=jdbc:postgresql://localhost:5432/academy
export ACADEMY_APP_JDBC_USERNAME=postgres
export ACADEMY_APP_JDBC_PASSWORD=123456
```


В этом случае при запуске *Apache Tomcat* произойдет автоматическое создание необходимых переменных окружения.


## Логирование

Логи пишутся в *stdout* (`${catalina.base}/logs/catalina.out`) и в файл `${catalina.base}/logs/academy.log`.
