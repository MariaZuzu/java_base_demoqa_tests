package pages.components;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsComponent {

    public RegistrationResultsComponent checkModalTitleWindowOpen(String value) {
        $(".modal-content").shouldHave(Condition.exactText(value));
        return this;
    }

    @Step("Проверить что поле \"{fieldName}\" имеет значение \"{expectedValue}\"")
    public RegistrationResultsComponent checkFormResults(String fieldName, String expectedValue) {
        $(".table-responsive")
                .$$( "tr")
                .findBy(text(fieldName))
                .shouldHave(text(expectedValue));
        return this;
    }

    public void checkModalTitleWindowClosed() {
        $(byText("Close")).click();
    }
}
