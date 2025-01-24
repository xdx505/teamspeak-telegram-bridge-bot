# Teamspeak Telegram Bridge

A simple bridge between a Teamspeak server and a Telegram chat.

## Features:

- Sends a message when a user connects to your Teamspeak server.
- Displays the list of online users (`/list`).
- Shows information about your server (`/info`).

## Environment Requirements:

- OpenJDK 17
- Maven 3.9.6
- Docker

## Installation Steps:

1. **Create a self-hosted or hosted Teamspeak server.** Save the host and port for later steps.
2. **Create your Teamspeak Server Query panel credentials.** Save the login and password for later steps.
3. **Register or use an existing Telegram bot.** Save the token for later steps.
4. **Create a `.env` file based on the `.env.example` template:**

    ```properties
    TS_SERVER_HOST=host.ru         # Teamspeak server host
    TS_SERVER_PORT=9996            # Teamspeak server port
    TS_ADMIN_LOGIN=admin4ik        # Teamspeak Server Query panel admin login
    TS_ADMIN_PASSWORD=P@ssw0rd     # Teamspeak Server Query panel password
    TS_BOT_NAME=anyBot             # Random bot name
    TS_MESSAGE_TIMEOUT_IN_MINUTES=30  # Timeout on user join action message sending (to prevent flooding)
    
    TG_BOT_TOKEN=123456789:AaLWS7DAGy8MEilssjmFbyevuTBpQZY2-58  # Telegram bot token
    TG_CHAT_ID=                    # Set of Telegram chat IDs. If not specified, will be set automatically
    ```

5. **Build and Run:**
    ```bash
    mvn clean package
   ```
   ```bash
    docker build -t teamspeak-telegram-bridge-bot:latest .
   ```
   ```bash
    docker run -d teamspeak-telegram-bridge-bot:latest
    ```

## Contributing

If you want to contribute to this project, feel free to submit pull requests or open issues.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.