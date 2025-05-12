# Bobbysoft Organizer README

Welcome to Bobbysoft Organizer, the (future) modular organizer that will allow users to create and
share organizational modules.

## Local Development

To run this project locally, docker is required. Start up the development
postgres database by running:

```bash
docker-compose up -d
```

Once the postgres docker instance is running, you can start the application from
the command line by running:

```bash
./mvnw
```

For pre-commit hooks to be performed install pre-commit `brew install pre-commit`
and run:

```bash
pre-commit install
```
