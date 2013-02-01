################################################################
# => Author David Kirwan
# => Email dkirwan@tssg.org
# => Date 01/02/2013
# => License See LICENCE.txt
# => Version 0.0.1
# => Description
################################################################


require 'rubygems'
require 'asciirock'
require 'logger'


module AsciiRock

  class Version
    class << self
      
      MAJOR = 0 unless defined? MAJOR
      MINOR = 0 unless defined? MINOR
      PATCH = 1 unless defined? PATCH

      def to_s
        [MAJOR, MINOR, PATCH].compact.join('.')
      end

    end
  end

end
