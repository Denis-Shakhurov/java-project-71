.DEFAULT_GOAL := build

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

install:
	./gradlew clean install

run-dist:
	./build/install/java-project-71/bin/java-project-71

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


.PHONY: build

build: report
	lint
	./gradlew build
	./gradlew test