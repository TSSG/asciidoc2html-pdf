################################################################
# => Author David Kirwan
# => Email dkirwan@tssg.org
# => Date 01/02/2013
# => License See LICENCE.txt
# => Version 0.0.1
# => Description
################################################################


# => Require Statements
require 'rubygems'
require 'nokogiri'
require 'asciirock'
require 'logger'


module AsciiRock


  # Utility methods Class for working with the Coverpage data
  class Coverpage
    class << self
      
      attr_accessor :placeholder

      # Method to parse the coverpage header in the input asciidoc file
      def parseheader(i)
        value = i.match(/^:reporttype:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:reporttitle:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:author:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:email:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:group:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:address:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:revdate:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:revnumber:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:docdate:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:description:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:legal:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:encoding:\s+(.*)/)
        unless value.nil? then return value[1] end
          
        value = i.match(/^:toc:\s+(.*)/)
        unless value.nil? then return value[1] end  
        
      end
      
      
      # Method to construct the XML version of the coverpage header
      def createHeader(header)
        
        # Should only execute when there is no header information picked up at all
        if header.nil? or header.size == 0
          raise 'Header is nil or header size is 0'
          exit()
          
        # Should only execute if a malformed header was parsed         
        elsif header.size < 13
          raise 'Header is malformed!'
          exit()
        end

        # Construct the XML from the header information
        builder = Nokogiri::XML::Builder.new do |xml|
          
          xml.coverpage {
            xml.minititle(header[0])
            xml.mininame(header[1])     # merged with covertitle content
            xml.covertitle(header[1])
            xml.group(header[4])
            xml.address(header[5])
            xml.authorlist(header[2])
            xml.emaillist(header[3])
            xml.revdate(header[6])
            xml.revnumber(header[7])
            xml.date(header[8])
            xml.legal(header[10])
            xml.synopsis(header[9])
            xml.encoding(header[11])
            xml.tocname(header[12])
          }
          
        end
        
        return builder
      end
      
      
      def handleheader()
        #################################################################
        # => Location of this file
        scriptPath = File.expand_path(File.dirname(__FILE__))
        
        testInput = scriptPath + "/../../resources/amazon-ec2-vpn.asciidoc"
        
        f = File.readlines(testInput)
        
        
        header = Array.new
        
        f.each do |i|
          line = AsciiRock::Coverpage.parseheader(i)
          
          unless line.nil? then header << line end
          
        end
        
        @log.debug "Logging the contents of the parsed header"
        header.each {|i| @log.debug i}
        
        builder = AsciiRock::Coverpage.createHeader(header)
        
        test = builder.to_xml
        
        cover = Nokogiri::XML(builder.to_xml) do |config|
          config.strict.nonet
        end
        theChild = cover.xpath(".//coverpage")
        
        
        f = File.open(scriptPath + "/../../resources/amazon-ec2-vpn.output.xml")
        
        doc = Nokogiri::XML(f) do |config|
          config.strict.nonet
        end
        f.close
        
        doc.root().add_child(theChild)
        
        
        h = File.open(scriptPath + "/../../resources/amazon-ec2-vpn.output-backup.xml", "w")
        h.write(doc)
        h.close
        
        
        theChild.children().each do |i| 
          unless i.name == 'text' then puts i.name + ", " + i.child end
        end

      end
      
      


    end
  end

end






