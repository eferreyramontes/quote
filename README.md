Quote
=====

This project is intended for evaluation purposes. In order to run it, please do the following steps:

## Compile project

```bash
$ cd ${PROJECT_DIRECTORY}/; mvn clean package
```

## Run
To run this project, yo must pass two parameters:
* **Market File**: This file must contain information of taxes and availability of founds. In this project a file is provided in the root of the project so you can test it.
* **Loan amount**: How much is wanted to quote.

So, the invocation must be as follows:
```bash
$  java -jar ${PROJECT_DIRECTORY}/target/quote-1.0-SNAPSHOT.jar market.csv 1000
```
