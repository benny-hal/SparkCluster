name := "sbt-application"

organization := "Semantive"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val sparkV = "2.1.1"

  Seq(
    "org.apache.spark" %% "spark-core" % sparkV % "provided",
    "org.apache.spark" %% "spark-sql" % sparkV % "provided",
    "org.apache.spark" %% "spark-mllib" % sparkV % "provided",
	  "org.mongodb.spark" % "mongo-spark-connector_2.10" % "2.1.1"
  )
}

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

//--------------------------------
//---- sbt-assembly settings -----
//--------------------------------

val mainClassString = "SparkApplication"

mainClass in assembly := Some(mainClassString)

assemblyJarName := "spark-app.jar"

assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}

assemblyOption in assembly ~= { _.copy(cacheOutput = false) }

assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter { c =>
    c.data.getName.startsWith("log4j")
    c.data.getName.startsWith("slf4j-") ||
    c.data.getName.startsWith("scala-library")
  }
}

// Disable tests (they require Spark)
test in assembly := {}

// publish to artifacts directory
publishArtifact in(Compile, packageDoc) := false

publishTo := Some(Resolver.file("file", new File("artifacts")))

cleanFiles += baseDirectory { base => base / "artifacts" }.value
