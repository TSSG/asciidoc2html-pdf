require 'test/unit/assertions'
World(Test::Unit::Assertions)

Given /^I have just passed a folder (\w+) of (\w+)/ do |var, value|
  instance_variable_set("@#{var}", value)
end

Given /^(\w+) does not exist already$/ do |name|
  
end

begin
  require 'rubygems'
  require 'ardtweeno'
  
  Then /^when the script has finished (\w+) should exist$/ do |name|
    
  end

rescue LoadError

  STDERR.puts LoadError
  
end