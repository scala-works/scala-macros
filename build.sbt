ThisBuild / scalaVersion := "3.2.2"


ThisBuild / organization         := "works.scala"
ThisBuild / organizationName     := "Scala Works"
ThisBuild / organizationHomepage := Some(url("https://scala.works"))
ThisBuild / homepage    := Some(url("https://scala-works.github.io/scala-macros"))
ThisBuild / description := "Scala 3 library with some reusable macro helpers."
ThisBuild / licenses    := List(
  "Apache 2" -> new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"),
)
sonatypeCredentialHost := "s01.oss.sonatype.org"
sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
ThisBuild / developers := List(
  Developer(
    id = "alterationx10",
    name = "Mark Rudolph",
    email = "mark@scala.works",
    url = url("https://alterationx10.com/"),
  ),
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "macro-helpers",
    Compile / doc / scalacOptions ++= Seq(
      "-project",
      "Scala Works Macro Helpers",
      "-Yapi-subdirectory",
      "-Ygenerate-inkuire",
      "-project-version",
      version.value,
      "-source-links:github://scala-works/scala-cmd",
      "-revision",
      "main",
      "-snippet-compiler:compile",
      "-doc-root-content",
      "README.md"
    )
  )
