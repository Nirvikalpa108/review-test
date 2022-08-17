val Http4sVersion = "0.23.14"
val CirceVersion = "0.14.2"
val CirceFs2Version = "0.14.0"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.2.11"
val MunitCatsEffectVersion = "1.0.7"
val Fs2Version = "3.2.12"
val CatsEffectVersion = "3.3.14"
val PureConfigVersion = "0.17.1"

lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "hivetest",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.8",
    scalacOptions += "-Wconf:cat=unused:info", //TODO remove
    libraryDependencies ++= Seq(
      "com.github.pureconfig" %% "pureconfig" % PureConfigVersion,
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "io.circe"        %% "circe-fs2"           % CirceFs2Version,
      "co.fs2"          %% "fs2-core"            % Fs2Version,
      "co.fs2"          %% "fs2-io"              % Fs2Version,
      "org.typelevel"   %% "cats-effect"         % CatsEffectVersion,
      "org.typelevel"   %% "cats-effect-kernel"  % CatsEffectVersion,
      "org.typelevel"   %% "cats-effect-std"     % CatsEffectVersion,
      "org.scalameta"   %% "munit"               % MunitVersion           % Test,
      "org.typelevel"   %% "munit-cats-effect-3" % MunitCatsEffectVersion % Test,
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion         % Runtime,
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.2" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework")
  )
