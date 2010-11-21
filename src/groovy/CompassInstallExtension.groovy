class CompassInstallExtension {

  public static void install(config, ant, extension, callback) {

    println "Installing extension '${extension}'..."

    ant.exec(executable: "compass") {
      def requires = ""
      config.require.each {
        requires = " -r " + it
      }
      def relative_assets_arg = config.relative_assets ? "--relative-assets" : ""
      arg(line: "install --sass-dir ${config.sass_dir} --css-dir ${config.css_dir} --images-dir ${config.images_dir} ${relative_assets_arg} --output-style ${config.output_style} ${requires} ${extension}")
    }

  }

}
