spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false
    username: root
    password: 12345678
    driver-class-name: com.mysql.cj.jdbc.Driver
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
swagger:
    enabled: true