# Brandon Internship Project

## Backend

* To launch the backend, first build the maven project with:

```bash
./mvnw clean install
```

* Then launch the jar build of the maven project with:

```bash
java -jar target/project-0.0.1-SNAPSHOT.jar
```

## Frontend

* To launch the frontend, first install the dependencies with:

```bash
npm install
```

* Then launch the frontend server with:

```bash
npm run start
```

## Database

* You can make a local copy of a MySQL DB either through workbench or through docker. The Schema to initialize the database is here [Database folder](./Database/Schema.sql)
* To get database running on docker, run:

```bash
docker-compose up
```

* from the root of this repository, this will bring up a docker container with the database running on port 3306 locally and will run the Schema.sql file to initialize the database

## Other Notes

* Run all of these commands from within the Backend and Frontend folders
* The backend will run on port 8080 and the frontend will run on port 4200
* There is only one admin user for the database for now, the username is admin and the password is password
