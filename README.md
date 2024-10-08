 # Hệ thống quản lý bài viết

Đây là một API RESTful để quản lý các bài viết, được xây dựng bằng Spring Boot 3, sử dụng xác thực dựa trên JWT, Hibernate và MySQL. API cung cấp các tính năng bảo mật để người dùng có thể tạo, cập nhật, và quản lý bài viết ,.....
- [Giới thiệu](#giới-thiệu)
- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
- [Cài đặt](#cài-đặt)
- [Cấu hình](#cấu-hình)
- [API Documentation](#api-documentation)
- [Test](#test)

## Giới thiệu

Dự án này là một API cơ bản cho một hệ thống quản lý bài viết (Article Management System). Dự án sử dụng **Spring Boot 3** kết hợp với **Spring Security** để quản lý xác thực và phân quyền, trong đó **JWT** (JSON Web Token) được sử dụng cho authentication. 

Cơ sở dữ liệu của dự án được quản lý qua **Hibernate** với kết nối đến một **MySQL** container chạy trên **Docker**. Tôi cũng sử dụng **DBeaver** để thao tác và quản lý cơ sở dữ liệu.

Một trong những thử thách lớn nhất trong dự án là xây dựng hệ thống xác thực và phân quyền bảo mật bằng JWT và tích hợp các công nghệ như Hibernate để quản lý dữ liệu hiệu quả.
## Yêu cầu hệ thống

1. **JDK 21**: Cần cài đặt JDK 21 để phát triển và chạy ứng dụng.
2. **Maven(3.9.8)**: Sử dụng Maven để quản lý dự án và phụ thuộc.
3. **Docker**: Sử dụng Docker để chạy container MySQL.
4. **DBeaver (tùy chọn)**: Công cụ quản lý cơ sở dữ liệu MySQL.

## Cài đặt

### Cài đặt Visual Studio Code (VS Code)

1. **Tải xuống VS Code**: 
   - Truy cập [Visual Studio Code](https://code.visualstudio.com/) và tải xuống phiên bản phù hợp với hệ điều hành của bạn.
   
2. **Cài đặt**: 
   - Chạy file cài đặt và làm theo hướng dẫn.

3. **Cài đặt các extension**:
   - Mở cửa sổ extension bằng cách nhấn `Ctrl + Shift + X`.
   - Tìm kiếm và cài đặt:
     - **Java Extension Pack**
     - **Spring Boot Extension Pack**

### Cài đặt IntelliJ IDEA

1. **Tải xuống IntelliJ IDEA**: 
   - Truy cập [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) và tải xuống phiên bản Community hoặc Ultimate.

2. **Cài đặt**: 
   - Chạy file cài đặt và làm theo hướng dẫn.

## Các bước để cài đặt và chạy ứng dụng

1. **Clone dự án từ GitHub**:
    ```bash
    git clone https://github.com/username/repository-name.git
    ```
   - Thay thế `username` và `repository-name` bằng thông tin thực tế của bạn.

2. **Chuyển vào thư mục dự án**:
    ```bash
    cd repository-name
    ```

3. **Build dự án bằng Maven**:
    ```bash
    mvn clean install
    ```

4. **Chạy ứng dụng**:
    ```bash
    mvn spring-boot:run
    ```

5. **Chạy container MySQL (nếu sử dụng Docker)**:
    ```bash
    docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=mydb -p 3306:3306 -d mysql:latest
    ```

## Chạy dự án

### Trên Visual Studio Code

1. Mở terminal trong VS Code bằng cách nhấn `Ctrl + `` (dấu ngã).
2. Chạy lệnh sau để build và chạy ứng dụng:
    ```bash
    mvn spring-boot:run
    ```

### Trên IntelliJ IDEA

1. Nhấp chuột phải vào lớp chứa phương thức `main` (thường là lớp chính của ứng dụng).
2. Chọn "Run 'ChallengeApplication'" để chạy ứng dụng.

## API Documentation

API của dự án được mô tả chi tiết trong file `api.yaml`. 
- Để xem file `api.yaml`, bạn có thể mở file trực tiếp trong thư mục của dự án.

## Test

Để test các endpoint của API, bạn có thể sử dụng link Postman dưới đây:

[Link Postman để test API](https://noname-4409.postman.co/workspace/ada56cd8-6013-4e70-b405-3ff63bf8959f/collection/31896070-959af2c9-d0a3-403d-9784-d44245424286)

## Kết luận

Sau khi thực hiện các bước trên, bạn sẽ có thể chạy dự án Spring Boot của mình và thử nghiệm API với Postman. Nếu gặp bất kỳ vấn đề nào, vui lòng kiểm tra lại các bước hoặc liên hệ để được hỗ trợ!
