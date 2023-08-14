# BookMemo

## Usage

```sh
# change db connection settings
vi .env
docker compose build 
docker compose up -d

# Trouble shooting
# hardsource run
./run.sh
# docker run
./run.sh docker
# run jar file
java -jar $JAVA_OPTS -Dserver.port=$PORT build/libs/BookMemo-0.0.1-SNAPSHOT.jar
```

## How to Deploy

```sh
# install lib
sudo apt update
sudo apt upgrade -y
sudo apt install -y uidmap nginx certbot python3-certbot-nginx

# edit nginx conf
sudo vim /etc/nginx/sites-enabled/$DOMAIN
# validate nginx conf
sudo nginx -t

# setting dns at cloud dns

# generate crt files
sudo certbot --nginx -d $DOMAIN -d www.$DOMAIN
# setting certbot renew
sudo crontab -u root -e

# install rootless docker
curl -fsSL https://get.docker.com/rootless | sh
# set rootless docker PATH and Sock
vim .bashrc 
systemctl --user start docekr

# install docker compose
mkdir -p ~/.docker/cli-plugins
curl -L "https://github.com/docker/compose/releases/download/v2.16.0/docker-compose-linux-x86_64" -o ~/.docker/cli-plugins/docker-compose
chmod 744 ~/.docker/cli-plugins/docker-compose

cd bookmemo
# set docker env
vim .env
# run
docker compose build
docker compose up 
# press ctrl + z
bg 1
disown %1
```
