https://hub.docker.com/_/postgres

```bash
$ sudo docker run \
  --name geocoder-postgres \
  -e POSTGRES_PASSWORD=geocoder \
  -d postgres:15

$ sudo apt docker exec -it geocoder-postgres /bin/bash

```

```bash
$ chmod +x gradlew
$ ./gradlew bootJar
$ sudo docker build --build-arg JAR_FILE="./build/libs/geocoder-0.0.1-SNAPSHOT.jar" \
  -t geocoder:1.0.0 .
$ sudo run --name test -d geocoder:1.0.0
```
