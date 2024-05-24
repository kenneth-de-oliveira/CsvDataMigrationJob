# Csv Migration Batch - POC 

This project is a proposal for a JOB for automatic migration of lines from a CSV file to a DATABASE using the following technologies:

* _Java 21_
* _Maven 3.8.3_
* _Mysql 8.0.26_
* Spring Boot 
* Spring Batch
* Lombok

**Comprehensive guide describing the Spring batch:**

* [Spring Batch Overview](https://spring.io/projects/spring-batch/)

Other articles around Spring Batch that could be interesting:

* [Introduction to Spring Batch](https://www.baeldung.com/spring-boot-spring-batch)
* [Spring Batch Tutorial: Batch Processing Made Easy with Spring](https://medium.com/dreamifly/spring-batch-tutorial-batch-processing-made-easy-with-spring-3219b4de052)

## Business Case

This project aims to solve the problem of migrating all 30 thousand book records and students registered in a fictitious book inventory.

# Instructions

First, clone the repository at the address:

`https://github.com/kenneth-de-oliveira/CsvDataMigrationJob.git` 

After the project is cloned, open the terminal in the cloned directory **ReservationInventory**

```bash
cd CsvDataMigrationJob
mvn install
```
