class CompassConfiguration {

  def sass_dir
  def css_dir
  def images_dir
  def relative_assets
  def output_style
  def require

  public void init(config, callback) {

    sass_dir = config.grass?.sass_dir
    css_dir = config.grass?.css_dir
    images_dir = config.grass?.images_dir
    relative_assets = config.grass?.relative_assets == null ? true : config.compass?.relative_assets
    output_style = config.grass?.output_style ?: 'compact'
    require = config.grass?.require ?: []

    do_check sass_dir, "sass_dir is not set (GrassConfig.groovy)", callback
    do_check css_dir, "css_dir is not set (GrassConfig.groovy)", callback
    do_check images_dir, "images_dir is not set (GrassConfig.groovy)", callback
    do_check output_style, "output_style is not set (GrassConfig.groovy)", callback
    do_check require, "require is not set (GrassConfig.groovy)", callback

    println """
sass_dir = '${sass_dir}'
css_dir = '${css_dir}'
images_dir = '${images_dir}'
relative_assets = ${relative_assets}
output_style = ${output_style}
require = ${require}
"""
  }

  protected void do_check(value, msg, callback) {
    if (!value)
      callback(msg)
  }

}
