################################################################
# => Author David Kirwan
# => Email dkirwan@tssg.org
# => Date 01/02/2013
# => License See LICENCE.txt
# => Version 0.0.1
# => Description
################################################################


require 'rubygems'
require 'yaml'
require 'asciirock'
require 'logger'

module AsciiRock

  ## => Config Reader Class
  class ConfigReader
    class << self
  
      attr_accessor :data
  
      def load(path, log)
        if File.exists?(path)
          @data = YAML::load( File.open( path ) )
        else
          log.fatal "Error #{path} file not found, build failed!"
          exit()
        end
        
        return @data
      end
  
    end
  end

end
