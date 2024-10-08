# API Quản lý Bài viết

Một API RESTful để quản lý các bài viết, được xây dựng bằng Spring Boot 3, Spring Security với xác thực JWT, Hibernate, và Docker cho MySQL.
## Mô tả

Dự án này minh họa cách xây dựng hệ thống quản lý bài viết cơ bản bằng Spring Boot. Nó bao gồm các tính năng như xác thực bằng JWT và các endpoint RESTful cho việc quản lý bài viết,bình luận,theo dõi người khác ,... Backend được cung cấp bởi Hibernate để quản lý cơ sở dữ liệu với MySQL.
## Cài đặt để chạy :

### Yêu cầu

Để chạy dự án này cục bộ, bạn cần cài đặt các công cụ sau:

- [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven (v.3.9.8)](https://maven.apache.org/download.cgi)

### Các bước cài đặt

1. **Clone repository**
2. **Chuyển vào thư mục dự án**
3. **Thiết lập các biến môi trường**
4. **Build dự án**:
    ```bash
    mvn clean install
    ```
5. **Chạy container Docker cho MySQL**:
    ```bash
    docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=article -p 3306:3306 -d mysql:latest
    ```

6. **Chạy ứng dụng Spring Boot**:
### Chạy trên IDE

#### IntelliJ IDEA

1. Mở dự án trong IntelliJ IDEA.
2. Đảm bảo rằng các biến môi trường đã được cấu hình trong phần Run Configurations của IDE.
3. Chạy ứng dụng bằng cách nhấp chuột phải vào lớp `Application.java` và chọn **Run 'Application'**.

#### Visual Studio Code
1. Mở dự án trong VS Code.
2. Cài đặt các extension cần thiết cho Java và Spring Boot (Java Extension Pack, Spring Boot Extension Pack).
3. Sử dụng terminal trong VS Code để chạy ứng dụng:
    ```bash
    mvn spring-boot:run
    ```
## Kiểm tra

Bạn có thể sử dụng Postman để kiểm tra API bằng cách sử dụng liên kết sau:

- [Postman Collection cho API Quản lý Bài viết](https://noname-4409.postman.co/workspace/ada56cd8-6013-4e70-b405-3ff63bf8959f/collection/31896070-959af2c9-d0a3-403d-9784-d44245424286)


