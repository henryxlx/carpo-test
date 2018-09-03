# carpo-test （Spring + Mybatis integration testing）
Test driven development for legacy project

Introduction
============
Carpo-test provides integration between the Spring testing framework and the popular MyBatis project. It allows you
to setup MyBatis configuration using simple annotations as well as inject the Mapper interface into Spring context for testing database.

The project must be configured to inheriting custom test abstraction class CarpoMybatisDaoTxJUnit4SpringContextTests.
