# design-pattern-demo
<p>
A command line Java program that does CRUD operation on Employee table. The main requirement of this program was to apply the best design practices I learned so far to make
this program as easy to maintain as possible. Concepts applied: dependency injection, using JdbcTemplate to reduce coding size (less codes = easier to maintain),
hiding as much business logic away from front-end (the only 2 classes the front-end dependent on is the AppService interface and Employee class getters and setters)
</p>

<p>I also made an addtional enhancement to allow user to view all records in the table page by page</p>
