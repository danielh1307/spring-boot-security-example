# Spring Boot Security Example

## Spring Boot Starter Security

By default, if there is only the starter, Spring generates a security password during startup. The default user is `user`.

You can call any service with this password:

```bash
$ curl -u user:26c5d824-d818-4324-9f02-aa294bcf5a7b http://localhost:8080/hello
```
