import java.nio.file.Path

import io.gatling.commons.util.PathHelper._

object IDEPathHelper {

	val gatlingConfUrl: Path = getResourcePath

	val projectRootDir = gatlingConfUrl.getParent.getParent.getParent

	val mavenSourcesDirectory = projectRootDir / "src" / "test" / "scala"
	val mavenResourcesDirectory = projectRootDir / "src" / "test" / "resources"
	val mavenTargetDirectory = projectRootDir / "target"
	val mavenBinariesDirectory = mavenTargetDirectory / "test-classes"

	val dataDirectory = mavenResourcesDirectory / "data"
	val bodiesDirectory = mavenResourcesDirectory / "bodies"

	val recorderOutputDirectory = mavenSourcesDirectory
	val resultsDirectory = mavenTargetDirectory / "gatling"

	// no recorder needed
	// val recorderConfigFile = mavenResourcesDirectory / "recorder.conf"

	/**
		* getClass.getClassLoader.getResource("gatling.conf").getPath resolves to
		* "/D:/work/lifeX/ps-platform-test/perf-bench/target/test-classes/gatling.conf"
		* but this is an invalid path therefore we remove the trailing slash
    */
	private def getResourcePath: Path = {
		getClass.getClassLoader.getResource("gatling.conf").getPath
	}
}
