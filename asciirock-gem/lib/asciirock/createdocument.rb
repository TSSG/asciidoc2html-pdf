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



# => Require Statements
require 'rubygems'
require 'asciirock'
require 'logger'
require 'fileutils'
require 'date'

      
# => Date, used in some of the directory/filename generation
date = DateTime.now
@theDate = date.year.to_s() + "-" + "%02d" % date.month.to_s() + "-" + "%02d" % date.day.to_s()

module AsciiRock
  
  class CreateDocument
    class << self
      
      attr_accessor :scriptPath

      
      def structure(docpath, log)
        scriptPath = File.expand_path(File.dirname(__FILE__))
        resourcePath = scriptPath + "/../../resources/"
        
        
        log.debug "Checking to see if #{docpath} exists."
        if File.directory?(docpath)
          log.debug "Error folder already exists, cannot determine document path"
          
        else
          log.debug "Folder does not exist, creating." 
          FileUtils.mkdir(docpath)
          FileUtils.cp(resourcePath + "/Rakefile", docpath)
          FileUtils.mkdir(docpath + "/src")
          FileUtils.mkdir(docpath + "/src/images")
          
          docname = File.basename(docpath)
          f = File.open(docpath + "/src/#{docname}.asciidoc", "w")
          f.write(template(nil, docname, nil, nil, nil, nil,
                          @theDate, nil, @theDate, nil, nil, nil, nil))
          f.close
          
        end
        
      end
      
      # Boilerplate template for the newly generated file
      def template(reporttype, reportname, authorlist, emaillist, group, address,
        revdate, revnumber, docdate, description, legal, encoding, toc)
        
        sampledoc = <<-TEMPLATE
:reporttype:    #{reporttype || 'The type of report'}
:reporttitle:   #{reportname || 'The name of the report'}
:author:        #{authorlist || 'Author McList, Authorette OName'}
:email:         #{emaillist || 'someone@somehost.com, someoneelse@anotherhost.net'}
:group:         #{group || 'Marketing? Research? Edit me...'}
:address:       #{address || 'Company Address....'}
:revdate:       #{revdate || 'January 1, 2012'}
:revnumber:     #{revnumber || '0.0.1'}
:docdate:       #{docdate || 'January 1, 2012'}
:description:   #{description || 'Barebones Sample Document'}
:legal:         #{legal || '(C) Copyrighted by who?'}
:encoding:      #{encoding || 'iso-8859-1'}
:toc:           #{toc || 'true'}

<<<
== Introduction ==
Hi This is an introduction!
TEMPLATE
        return sampledoc
      end
      
      
       
    end
  end
  
end