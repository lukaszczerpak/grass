includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(main: "The description of the script goes here!") {
	compassInstall()
}

target(compassInstall: "Installing Compass extension") {

    if (args) {
        extension = args.trim()
    } else {
        Ant.input(addProperty:"compass.install.extension", message:"Specify Compass extension, e.g. fancy-buttons:")
        extension = Ant.antProject.properties."compass.install.extension"
    }

	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	
    Class configClazz = loader.parseClass(new File("$basedir/grails-app/conf/GrassConfig.groovy"))
    Class compassConfigurationClazz = loader.parseClass(new File("$grassPluginDir/src/groovy/CompassConfiguration.groovy"))
    def compassConfiguration = compassConfigurationClazz.newInstance()
    compassConfiguration.init(new ConfigSlurper().parse(configClazz)) { msg ->
        event("StatusError", [msg])
        exit(-1)
    }

	Class compassInstall = loader.parseClass(
		new File("$grassPluginDir/src/groovy/CompassInstallExtension.groovy"))

	compassInstall.install(compassConfiguration, ant, extension) { msg ->
		event("StatusError", [msg])
		exit(-1)
	}
}

setDefaultTarget(main)
