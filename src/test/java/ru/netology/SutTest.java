package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static com.codeborne.selenide.Selenide.*;

public class SutTest {

    @Test
    @DisplayName("Should submit request")
    void shouldSubmitRequest() {

        open("http://localhost:9999");

        $$("#root .form-field").find(Condition.hidden);
        $("[data-test-id=city] input").setValue("Москва");

        LocalDate date = LocalDate.now().plusDays(3);
        $("[data-test-id=date] input").setValue(String.valueOf(date));

        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79051112233");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(Condition.visible, 15000);
    }
}
