
class CompassCompile {

	public static void compile(config, ant, check) {
		def sass_dir = config.compass?.sass_dir
		def css_dir = config.compass?.css_dir
		def images_dir = config.compass?.images_dir
		def relative_assets = config.compass?.relative_assets == null ? true : config.compass?.relative_assets
		def output_style = config.compass?.output_style ?: 'compact'

		check(sass_dir, "sass_dir is not set (CompassConfig.groovy)")
		check(css_dir, "css_dir is not set (CompassConfig.groovy)")
		check(images_dir, "images_dir is not set (CompassConfig.groovy)")
		check(output_style, "output_style is not set (CompassConfig.groovy)")

		println """
sass_dir = '${sass_dir}'"
css_dir = '${css_dir}'
images_dir = '${images_dir}'
relative_assets = ${relative_assets}
output_style = ${output_style}
"""

		println "Compiling sass stylesheets..."

		ant.exec(executable: "compass") {
			def relative_assets_arg = relative_assets ? "--relative-assets" : ""
			arg(line: "--sass-dir ${sass_dir} --css-dir ${css_dir} --images-dir ${images_dir} ${relative_assets_arg} --output-style ${output_style}")
		}

	}

}