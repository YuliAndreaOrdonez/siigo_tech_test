## Executing the tests
To run the sample project, you can either just run the `CucumberTestSuite` test runner class, or run either `mvn verify` or `gradle test` from the command line.

You need first to set up the siigo username and password, this variables must be present in the serenity.properties file

```
siigo.username=
siigo.password=
```

By default, the tests will run using Chrome. You can run them in Firefox by overriding the `driver` system property, e.g.
```
$ mvn clean verify 
```
Or
```
$ gradle clean test
```

The test results will be recorded in the `target/site/serenity` directory.
