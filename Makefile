.DEFAULT_GOAL := build

setup:
	.app/gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

install:
	./gradlew clean install

run-dist:
	./build/install/java-project-71/bin/java-project-71

run:
	./gradlew run


report:
	.app/gradlew jacocoTestReport

lint:
	.app/gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


.PHONY: build

build: report
	lint
	.app/gradlew build
	.app/gradlew test