import unittest

from assertpy import assert_that

from zad2Python.Car import Car


class CarTest(unittest.TestCase):
    def setUp(self):
        self.temp = Car(2007, "Fiat")

    def test_get_speed_after_init(self):
        assert_that(self.temp.get_speed()).is_zero()

    def test_get_speed_after_accelerate(self):
        self.temp.accelerate()
        assert_that(self.temp.get_speed()).is_greater_than_or_equal_to(5)

    def test_get_speed_after_twice_accelerate(self):
        self.temp.accelerate()
        self.temp.accelerate()
        assert_that(self.temp.get_speed()).is_equal_to(10)

    def test_get_speed_after_accelerate_and_brake(self):
        self.temp.accelerate()
        self.temp.brake()
        assert_that(self.temp.get_speed()).is_less_than(5)

    def test_get_speed_after_twice_brake(self):
        self.temp.brake()
        self.temp.brake()
        assert_that(self.temp.get_speed()).is_not_equal_to(-10)

    def test_older_than_first_car(self):
        assert_that(Car).raises(Exception).when_called_with(1885, "sample").is_equal_to("older than first car")

    def test_year_float_type(self):
        assert_that(Car).raises(Exception).when_called_with(2010.5, "sample").starts_with("Must"). \
            is_equal_to("Must be a int type")

    def test_other_make(self):
        car = Car(2012, "BMW")
        assert_that(car.get_make()).contains("B", "W")

    def test_other_make_list(self):
        car = Car(2012, "BMW")
        assert_that(car.get_make()).is_not_in("toyota", "Ford")

    def test_other_make_contains_only(self):
        car = Car(2012, "Toyota")
        assert_that(car.get_make()).contains_only("t", "o", "y", "a")

    def tearDown(self):
        self.temp = None
