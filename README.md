```
mvn clean package
docker build -t teamspeak-telegram-bridge-bot:latest .
docker run -p 80:80 teamspeak-telegram-bridge-bot:latest
```