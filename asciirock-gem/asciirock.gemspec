require 'rake'

Gem::Specification.new do |s|
  s.name        = 'asciirock'
  s.version     = '0.0.1'
  s.date        = '2012-09-21'
  s.summary     = 'Toolchain wrapper for the Asciidoc tool'
  s.description = <<-DESCRIPTION
Asciirock is a toolchain wrapper for the Asciidoc tool. It allows custom coverpage
templates for PDF and HTML output.
DESCRIPTION

  s.authors     = ['David Kirwan']
  s.email       = ['00346128@mail.wit.ie']
  s.files       = FileList['lib/**/*.rb',
                      'bin/*',
                      '[A-Z]*',
                      'resources/*',
                      'test/**/*'].to_a
  s.homepage    = ''
  s.executables = ['asciirock']
  s.required_ruby_version = '>= 1.8.7'
  s.post_install_message = <<-INSTALL

INSTALL

  s.license   = ''
end
