import unittest
from assertpy import assert_that, add_extension, assert_warn

from zad4Python.Car import Car


def is_model_of_car_with_b(self):
    models = ["bentley", "berliet", "bmw", "bugatti", "buick"]
    if self.val.lower() not in models:
        return self.error(f'{self.val} is different model of car and not beginning with letter B')
    return self


def have_legal_speed_in_town(self):
    if self.val > 50:
        return self.error(f'{self.val} is too much speed for town area')
    return self


add_extension(is_model_of_car_with_b)
add_extension(have_legal_speed_in_town)


class CustomAssertpyMatcherTests(unittest.TestCase):
    def test_bentley(self):
        car = Car(2002, "Bentley")
        assert_that(car.get_make()).is_model_of_car_with_b()

    def test_toyota(self):
        car = Car(2002, "Toyota")
        assert_warn(car.get_make()).is_model_of_car_with_b()

    def test_low_speed(self):
        car = Car(2002, "Toyota")
        car.accelerate()
        assert_that(car.get_speed()).have_legal_speed_in_town()

    def test_high_speed(self):
        car = Car(2002, "Toyota")
        for _ in range(15):
            car.accelerate()
        print(car.get_speed())
        assert_warn(car.get_speed()).have_legal_speed_in_town()
