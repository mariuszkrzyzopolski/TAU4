import unittest

from hamcrest import assert_that, equal_to, raises, calling, all_of, instance_of, is_not, close_to, \
    greater_than_or_equal_to

from zad1Python.ProductionWorker import ProductionWorker


class ProductionWorkerTest(unittest.TestCase):
    def setUp(self):
        self.temp = ProductionWorker()
        self.temp.set_change_number(1)
        self.temp.set_pay_hour(20)

    def test_initial_change_number(self):
        assert_that(self.temp.get_change_number(), equal_to(1))

    def test_initial_pay_hour(self):
        assert_that(self.temp.get_pay_hour(), all_of(equal_to(20), instance_of(int)))

    def test_set_change_number(self):
        self.temp.set_change_number(2)
        assert_that(self.temp.get_change_number(), all_of(equal_to(2), instance_of(int), is_not(float)))

    def test_set_pay_hour(self):
        self.temp.set_pay_hour(22.2)
        assert_that(self.temp.get_pay_hour(), all_of(equal_to(22.2), instance_of(float), close_to(22, 0.5),
                                                     greater_than_or_equal_to(22)))

    def test_set_exceeding_change_number(self):
        assert_that(calling(self.temp.set_change_number).with_args(3),
                    raises(Exception, pattern="Change can be only 1 or 2"))

    def test_set_wrong_change_number(self):
        assert_that(calling(self.temp.set_change_number).with_args(1.2),
                    raises(Exception, pattern="Change can be only 1 or 2"))

    def test_set_wrong_pay_hour(self):
        assert_that(calling(self.temp.set_pay_hour).with_args("not a number"),
                    raises(Exception, pattern="Must be a number"))

    def test_set_pay_hour_less_than_0(self):
        assert_that(calling(self.temp.set_pay_hour).with_args(-2), raises(Exception))

    def tearDown(self):
        self.temp = None
