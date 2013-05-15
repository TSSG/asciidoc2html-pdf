# Test Helper for the Asciirock Ruby Gem

# Require the test suite
Dir.glob("./test/*_test.rb").each do |file|
  unless file.include? "parser_test.rb" then require file; end
end

# Require the mock
Dir.glob("./test/*_mock.rb").each do |file|
  require file
end

