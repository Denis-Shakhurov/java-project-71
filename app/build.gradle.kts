
plugins {
    application

}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.6")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.6")
}
application {
    mainClass.set("hexlet.code.App")
}
//compileJava {
  //  options.compilerArgs += ["-Aproject=${project.group}/${project.name}"]
//}

tasks.test {
    useJUnitPlatform()
}