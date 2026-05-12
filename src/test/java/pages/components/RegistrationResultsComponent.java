package pages.components;


import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsComponent {

    public RegistrationResultsComponent checkModalTitleWindowOpen(String value) {
        $("#example-modal-sizes-title-lg").shouldHave(Condition.exactText(value));
        return this;
    }

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
