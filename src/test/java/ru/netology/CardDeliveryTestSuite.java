package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTestSuite {

    @Test
    @DisplayName("Should submit request")
    void shouldSubmitRequest() {

        open("http://localhost:9999");

        $$("#root .form-field").find(Condition.hidden);
        $("[data-test-id=city] input").setValue("Москва");

        LocalDate date = LocalDate.now().plusDays(3);
        $("[data-test-id=date] input").setValue(String.valueOf(date));

        $("[data-test-id=name] input").setValue("Кириллов Игорь");
        $("[data-test-id=phone] input").setValue("+79051112233");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
    }

    @Test
    @DisplayName("Should check empty line")
    void shouldCheckEmptyLine() {

        open("http://localhost:9999");

        $$("#root .form-field").find(Condition.hidden);
        $("[data-test-id=city] input").setValue("Москва");

        LocalDate date = LocalDate.now().plusDays(3);
        $("[data-test-id=date] input").setValue(String.valueOf(date));

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79051112233");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    @DisplayName("Should check city")
    void shouldCheckCity() {

        open("http://localhost:9999");

        $$("#root .form-field").find(Condition.hidden);
        $("[data-test-id=city] input").setValue("Клин");

        LocalDate date = LocalDate.now().plusDays(3);
        $("[data-test-id=date] input").setValue(String.valueOf(date));

        $("[data-test-id=name] input").setValue("Кириллов Игорь");
        $("[data-test-id=phone] input").setValue("+79051112233");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    @DisplayName("Should check incorrect name")
    void shouldCheckIncorrectName() {

        open("http://localhost:9999");

        $$("#root .form-field").find(Condition.hidden);
        $("[data-test-id=city] input").setValue("Москва");

        LocalDate date = LocalDate.now().plusDays(3);
        $("[data-test-id=date] input").setValue(String.valueOf(date));

        $("[data-test-id=name] input").setValue("Kirillov Igor");
        $("[data-test-id=phone] input").setValue("+79051112233");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    @DisplayName("Should check incorrect phone number")
    void shouldCheckIncorrectPhoneNumber() {

        open("http://localhost:9999");

        $$("#root .form-field").find(Condition.hidden);
        $("[data-test-id=city] input").setValue("Москва");

        LocalDate date = LocalDate.now().plusDays(3);
        $("[data-test-id=date] input").setValue(String.valueOf(date));

        $("[data-test-id=name] input").setValue("Кириллов Игорь");
        $("[data-test-id=phone] input").setValue("89051112233");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
}
