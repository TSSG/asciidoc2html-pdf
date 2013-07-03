require 'rubygems'
require 'test/unit'
require 'rack/test'
require 'asciirock'
require 'logger'
require 'json'

ENV['RACK_ENV'] = 'test'


class PacketTest < Test::Unit::TestCase

  include Rack::Test::Methods

  # Fixtures Setup Function
  def setup
  end


  # Fixtures Teardown Function
  def teardown
  end

end
