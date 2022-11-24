import unittest

from hamcrest import all_of, equal_to, instance_of, assert_that, starts_with, ends_with, contains_string, calling, \
    raises

from zad1Python.Employee import Employee


class EmployeeTest(unittest.TestCase):
    def setUp(self):
        self.temp = Employee()
        self.temp.set_firstname("John")
        self.temp.set_lastname("Test")

    def test_initial_firstname(self):
        assert_that(self.temp.get_firstname(), all_of(equal_to("John"), instance_of(str)))

    def test_initial_lastname(self):
        assert_that(self.temp.get_lastname(), all_of(starts_with("T"), instance_of(str), ends_with("t")))

    def test_set_firstname(self):
        self.temp.set_firstname("Sebastian")
        assert_that(self.temp.get_firstname(), contains_string("Seba"))

    def test_set_lastname(self):
        self.temp.set_lastname("Oak")
        assert_that(self.temp.get_lastname(), starts_with("O"))

    def test_set_wrong_firstname_int(self):
        assert_that(calling(self.temp.set_firstname).with_args(55))

    def test_set_wrong_firstname(self):
        assert_that(calling(self.temp.set_firstname).with_args("55.0"),
                    raises(Exception, pattern="Firstname need to have only letters"))

    def test_set_wrong_lastname_int(self):
        assert_that(calling(self.temp.set_lastname).with_args(55))

    def test_set_wrong_lastname(self):
        assert_that(calling(self.temp.set_lastname).with_args("55.0"),
                    raises(Exception, pattern="Lastname need to have only letters"))

    def tearDown(self):
        self.temp = None



