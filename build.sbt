name := "hackathonmesse"
 
version := "1.0" 
      
lazy val `hackathonmesse` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

maintainer := "code.databyte@gmail.com"

javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null"
)