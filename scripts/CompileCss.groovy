includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(main: "The description of the script goes here!") {
	compileCss()
}

target(compileCss: "Compile sass stylesheets") {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	
	Class configClazz = loader.parseClass(new File("$basedir/grails-app/conf/GrassConfig.groovy"))
	Class compassConfigurationClazz = loader.parseClass(new File("$grassPluginDir/src/groovy/CompassConfiguration.groovy"))
	def compassConfiguration = compassConfigurationClazz.newInstance()
    compassConfiguration.init(new ConfigSlurper().parse(configClazz)) { msg ->
		event("StatusError", [msg])
		exit(-1)
	}
	
	Class compassCompile = loader.parseClass(
		new File("$grassPluginDir/src/groovy/CompassCompile.groovy"))

	compassCompile.compile(compassConfiguration, ant) { msg ->
		event("StatusError", [msg])
		exit(-1)
	}
}

setDefaultTarget(main)
