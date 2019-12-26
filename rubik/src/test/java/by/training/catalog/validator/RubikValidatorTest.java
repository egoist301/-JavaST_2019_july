package by.training.catalog.validator;

import by.training.catalog.bean.RubiksCube;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RubikValidatorTest {
    private static final String MAX_INFO =
            "При объектно-ориентированном тестировании методы не "
                    + "отделяются от своего класса, происходит замена "
                    + "модульного тестирования на классовое тестирование.\n"
                    + "Объекты класса необходимо протестировать во всех "
                    + "возможных состояниях. То есть, симитировать все "
                    + "события, вызывающие изменения состояния объекта. "
                    + "При объектно-ориентированном подходе "
                    + "интеграционное тестирование заменяют тестированием"
                    + " функциональных возможностей, при котором "
                    + "тестируется набор классов, которые должны "
                    + "реагировать на один входной сигнал или одно "
                    + "системное событие. Так же, интеграционные тесты "
                    + "могут быть заменены эксплуатационным "
                    + "тестированием, при котором описывается один способ"
                    + " применения системы, основанный на сценариях "
                    + "случаев эксплуатации. Тестирование взаимодействия "
                    + "объектов следует по путям сообщений с целью "
                    + "отследить последовательность взаимодействия "
                    + "объектов, которое заканчивается только тогда, "
                    + "когда был вызван последний объект. При этом, не "
                    + "посылается сообщение и не вызываются службы любого"
                    + " другого объекта. Последующие виды тестирования: "
                    + "системное тестирование, альфа и бета – практически"
                    + " не изменяются и проходят по тем же случаям. В "
                    + "хронологическом порядке тестирование классов "
                    + "проводится аналогично модульному тестированию, то "
                    + "есть, вспомощью обзоров кода и разработки тестовых"
                    + " случаев. Прежде, чем приступать к тестированию "
                    + "класса необходимо определить: тестировать его "
                    + "автономно, то есть как отдельный модуль или как "
                    + "более крупный \n"
                    + "Необходимо выяснить следующие моменты.\n"
                    + "1)\tРоль класса в системе и в частности степень "
                    + "связанности с ним.\n"
                    + "2)\tСложность класса.\n"
                    + "3)\tОбъём круга затрат, связанных с разработкой "
                    + "тестового драйвера для тестирования этого класса. \n"
                    + "Если тестируемый класс входит в состав библиотеки,"
                    + " то целесообразно проводить его все сторонние "
                    + "тестирования даже, если затраты на разработку "
                    + "окажутся высокие. При тестирование классов можно "
                    + "выделить 5 оцениваемых факторов:\n"
                    + "1)\tКто выполняет тестирование(программист). "
                    + "Основной недостаток то, что исходный код и тесты "
                    + "разрабатываются одним персоналом, то есть неверное"
                    + " понимание спецификации ведет к неверным тестовым "
                    + "наборам и тестовым драйверам. Решение проблемы: "
                    + "привлечение других разработчиков для code review"
                    + ".\n";

    @DataProvider(name = "Incorrect price")
    private Object[][] priceIncorrectProvider() {
        return new Object[][]{
                {""},
                {"0"},
                {"2001"},
                {null}
        };
    }

    @DataProvider(name = "Incorrect weight")
    private Object[][] weightIncorrectProvider() {
        return new Object[][]{
                {"0"},
                {"3001"},
                {null}
        };
    }

    @DataProvider(name = "Incorrect info with exception")
    private Object[][] infoWithException() {
        return new Object[][]{
                {1},
                {new RubiksCube(1)}
        };
    }

    @DataProvider(name = "Incorrect info")
    private Object[][] infoIncorrectProvider() {
        return new Object[][]{
                {""},
                {null},
                {MAX_INFO}
        };
    }

    @DataProvider(name = "Incorrect size")
    private Object[][] sizeIncorrectProvider() {
        return new Object[][]{
                {"dwa"},
                {null}
        };
    }

    @DataProvider(name = "Incorrect model")
    private Object[][] modelIncorrectProvider() {
        return new Object[][]{
                {"man"},
                {null},
                {"qwetyefrdgsefemnkenajkvnjkenkvjnsvavwavwavavwavwwvawav"}
        };
    }

    @DataProvider(name = "is invalid parameters")
    private Object[][] isInValidProvider() {
        List<String> parametersWithMinLengthModel = new ArrayList<>();
        parametersWithMinLengthModel.add("man");
        parametersWithMinLengthModel.add("500");
        parametersWithMinLengthModel.add("64");
        parametersWithMinLengthModel.add("info");
        parametersWithMinLengthModel.add("3x3x3");
        List<String> parametersWithMaxLengthModel = new ArrayList<>();
        parametersWithMaxLengthModel
                .add("manmanmanmanmanmanmanmanmanmanmanman");
        parametersWithMaxLengthModel.add("500");
        parametersWithMaxLengthModel.add("64");
        parametersWithMaxLengthModel.add("info");
        parametersWithMaxLengthModel.add("3x3x3");
        List<String> parametersWithNullModel = new ArrayList<>();
        parametersWithNullModel.add(null);
        parametersWithNullModel.add("500");
        parametersWithNullModel.add("64");
        parametersWithNullModel.add("info");
        parametersWithNullModel.add("3x3x3");

        List<String> parametersWithMinPrice = new ArrayList<>();
        parametersWithMinPrice.add("manman");
        parametersWithMinPrice.add("0");
        parametersWithMinPrice.add("64");
        parametersWithMinPrice.add("info");
        parametersWithMinPrice.add("3x3x3");
        List<String> parametersWithMaxPrice = new ArrayList<>();
        parametersWithMaxPrice.add("manman");
        parametersWithMaxPrice.add("20000");
        parametersWithMaxPrice.add("64");
        parametersWithMaxPrice.add("info");
        parametersWithMaxPrice.add("3x3x3");
        List<String> parametersWithNullPrice = new ArrayList<>();
        parametersWithNullPrice.add("null");
        parametersWithNullPrice.add(null);
        parametersWithNullPrice.add("64");
        parametersWithNullPrice.add("info");
        parametersWithNullPrice.add("3x3x3");

        List<String> parametersWithMinWeight = new ArrayList<>();
        parametersWithMinWeight.add("manman");
        parametersWithMinWeight.add("64");
        parametersWithMinWeight.add("0");
        parametersWithMinWeight.add("info");
        parametersWithMinWeight.add("3x3x3");
        List<String> parametersWithMaxWeight = new ArrayList<>();
        parametersWithMaxWeight.add("manman");
        parametersWithMaxWeight.add("64");
        parametersWithMaxWeight.add("20000");
        parametersWithMaxWeight.add("info");
        parametersWithMaxWeight.add("3x3x3");
        List<String> parametersWithNullWeight = new ArrayList<>();
        parametersWithNullWeight.add("null");
        parametersWithNullWeight.add("64");
        parametersWithNullWeight.add(null);
        parametersWithNullWeight.add("info");
        parametersWithNullWeight.add("3x3x3");

        List<String> parametersWithMinInfo = new ArrayList<>();
        parametersWithMinInfo.add("manman");
        parametersWithMinInfo.add("64");
        parametersWithMinInfo.add("123");
        parametersWithMinInfo.add("");
        parametersWithMinInfo.add("3x3x3");
        List<String> parametersWithMaxInfo = new ArrayList<>();
        parametersWithMaxInfo.add("manman");
        parametersWithMaxInfo.add("64");
        parametersWithMaxInfo.add("200");
        parametersWithMaxInfo
                .add(MAX_INFO);
        parametersWithMaxInfo.add("3x3x3");
        List<String> parametersWithNullInfo = new ArrayList<>();
        parametersWithNullInfo.add("null");
        parametersWithNullInfo.add("64");
        parametersWithNullInfo.add("64");
        parametersWithNullInfo.add(null);
        parametersWithNullInfo.add("3x3x3");

        List<String> parametersWithIncorrectSize = new ArrayList<>();
        parametersWithIncorrectSize.add("manman");
        parametersWithIncorrectSize.add("64.2");
        parametersWithIncorrectSize.add("123");
        parametersWithIncorrectSize.add("info");
        parametersWithIncorrectSize.add("3x");
        List<String> parametersWithNullSize = new ArrayList<>();
        parametersWithNullSize.add("null");
        parametersWithNullSize.add("64");
        parametersWithNullSize.add("123.1");
        parametersWithNullSize.add("info");
        parametersWithNullSize.add(null);
        return new Object[][]{
                {parametersWithMinLengthModel},
                {parametersWithMaxLengthModel},
                {parametersWithNullModel},
                {parametersWithMinPrice},
                {parametersWithMaxPrice},
                {parametersWithNullPrice},
                {parametersWithMinWeight},
                {parametersWithMaxWeight},
                {parametersWithNullWeight},
                {parametersWithIncorrectSize},
                {parametersWithNullSize},
                {parametersWithMinInfo},
                {parametersWithMaxInfo},
                {parametersWithNullInfo},
        };
    }


    @Test(dependsOnGroups = "validate cube parameter")
    public void testIsValid() {
        List<String> parameters = new ArrayList<>();
        parameters.add("x-man");
        parameters.add("500");
        parameters.add("64");
        parameters.add("info");
        parameters.add("3x3x3");
        boolean valid = RubikValidator.isValid(parameters);
        assertTrue(valid);
    }

    @Test(dataProvider = "is invalid parameters",
            dependsOnGroups = "validate cube parameter")
    public void testIsInvalid(final List<String> parameters) {
        boolean valid = RubikValidator.isValid(parameters);
        assertFalse(valid);
    }

    @Test(dataProvider = "Incorrect price", groups = "validate cube parameter")
    public void testInvalidatePrice(final String price) {
        boolean result = RubikValidator.invalidatePrice(price);
        assertTrue(result);
    }

    @Test(groups = "validate cube parameter")
    public void testValidatePrice() {
        boolean result = RubikValidator.invalidatePrice("64.3");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect weight", groups = "validate cube parameter")
    public void testInvalidateWeight(final String weight) {
        boolean result = RubikValidator.invalidateWeight(weight);
        assertTrue(result);
    }

    @Test(groups = "validate cube parameter")
    public void testValidateWeight() {
        boolean result = RubikValidator.invalidateWeight("64.3");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect info", groups = "validate cube parameter")
    public void testInvalidateInfo(final String info) {
        boolean result = RubikValidator.invalidateInfo(info);
        assertTrue(result);
    }

    @Test(groups = "validate cube parameter", expectedExceptions =
            IllegalArgumentException.class,
            dataProvider = "Incorrect info with exception")
    public void testInvalidateInfoException(final String parameter) {
        boolean result = RubikValidator.invalidateInfo(parameter);
    }

    @Test(groups = "validate cube parameter")
    public void testValidateInfo() {
        boolean result = RubikValidator.invalidateInfo("model");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect size", groups = "validate cube parameter")
    public void testInvalidateSize(final String size) {
        boolean result = RubikValidator.invalidateSize(size);
        assertTrue(result);
    }

    @Test(groups = "validate cube parameter")
    public void testValidateSize() {
        boolean result = RubikValidator.invalidateSize("3x3x3");
        assertFalse(result);
    }

    @Test(dataProvider = "Incorrect model", groups = "validate cube parameter")
    public void testInvalidateModel(final String model) {
        boolean result = RubikValidator.invalidateModel(model);
        assertTrue(result);
    }

    @Test(groups = "validate cube parameter")
    public void testValidateModel() {
        boolean result = RubikValidator.invalidateModel("model");
        assertFalse(result);
    }
}