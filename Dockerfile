# Sử dụng image Tomcat 9 với JDK 17 từ Eclipse Temurin
FROM tomcat:9.0-jdk17-temurin

# Tạo thư mục để chứa ứng dụng
WORKDIR /usr/local/tomcat/webapps

# Xóa ứng dụng mặc định (nếu cần)
RUN rm -rf ROOT

# Copy file WAR của bạn vào thư mục webapps
# Giả sử bạn đã build file: target/email-list.war
COPY target/email-list.war ./ROOT.war

# Mở port 8080
EXPOSE 8080

# Khởi động Tomcat khi container chạy
CMD ["catalina.sh", "run"]