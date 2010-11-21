class CompassCompile {

  public static void compile(config, ant, callback) {

    println "Compiling sass stylesheets..."

    ant.exec(executable: "compass") {
      def requires = ""
      config.require.each {
        requires += " -r " + it
      }
      def relative_assets_arg = config.relative_assets ? "--relative-assets" : ""
      arg(line: "compile --sass-dir ${config.sass_dir} --css-dir ${config.css_dir} --images-dir ${config.images_dir} ${relative_assets_arg} --output-style ${config.output_style} ${requires}")
    }

  }
}
