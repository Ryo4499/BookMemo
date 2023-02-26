# BookMemo

## Usage

```sh
# change db connection settings
vi .env
docker compose build 
docker compose up -d

# Trouble shooting
docker-compose exec -it app bash
# build
./gradlew build
# run
./gradlew bootRun
```