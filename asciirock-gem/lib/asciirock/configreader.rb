################################################################
# => Author
# => Email
# => Date
# => License
# => Version
# => Description
#
#
#
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