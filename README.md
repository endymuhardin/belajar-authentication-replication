# Session Management untuk Replikasi Aplikasi #

Cara menjalankan :

1. Cari tau alamat IP localhost, misalnya `192.168.52.10`

2. Isikan IP tersebut di konfigurasi load balancer, `nginx/loadbalancer.conf`

    ```
    # List of application servers
    upstream api_servers {
        server 192.168.52.10:8080;
        server 192.168.52.10:8081;
    }
    ```

3. Jalankan load balancer dan database

    ```
    docker compose up
    ```

4. Jalankan instance pertama di port 8080

    ```
    mvn clean spring-boot:run
    ```

5. Jalankan instance kedua di port 8081

    ```
    SERVER_PORT=8081 mvn clean spring-boot:run
    ```

6. Browse ke `http://localhost:10000/api/public/appinfo`. Refresh beberapa kali untuk memastikan port aplikasi berganti-ganti (round robin)

    ```json
    {
        "client-port" : 57807,
        "client-ip" : "0:0:0:0:0:0:0:1",
        "server-ip" : "0:0:0:0:0:0:0:1",
        "server-port" : 8080,
        "app-version" : "0a7e7c8"
    }
    ```

    Setelah direfresh, portnya harusnya ganti

    ```json
    {
        "client-port" : 57807,
        "client-ip" : "0:0:0:0:0:0:0:1",
        "server-ip" : "0:0:0:0:0:0:0:1",
        "server-port" : 8081,
        "app-version" : "0a7e7c8"
    }
    ```

    Bila port tidak berganti, bisa dicoba untuk mematikan salah satu instance.

7. Browse ke url terproteksi `http://localhost:10000/api/protected/userinfo`. Pada request pertama, user akan diminta untuk login. Masukkan username `coba` dan password `coba123`. Response yang didapatkan seperti ini

    ```json
    {
        "client-port" : 58682,
        "permissions" : [ {
            "authority" : "VIEW_USER_INFO"
        } ],
        "client-ip" : "192.168.52.10",
        "server-ip" : "192.168.52.10",
        "server-port" : 8080,
        "username" : "coba"
    }
    ```

7. Dengan menyimpan data session di database, pada saat terjadi perpindahan IP user tidak disuruh login ulang pada saat mengakses url `http://localhost:10000/api/protected/userinfo`. Coba refresh berkali-kali sampai mendapatkan port berbeda. Bila port tidak berganti, coba matikan salah satu instance.