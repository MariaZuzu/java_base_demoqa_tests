package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testdata.TestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {

        open("/text-box");
        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id=permanentAddress]").setValue(permanentAddress);
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text(userName));
        $("[id=output] [id=email]").shouldHave(text(userEmail));
        $("[id=output] [id=currentAddress]").shouldHave(text(currentAddress));
        $("[id=output] [id=permanentAddress]").shouldHave(text(permanentAddress));
    }

    @Test
    void MinimumFillFormTest() {

        open("/text-box");
        $("[id=userName]").setValue("Плюша");
        $("[id=submit]").click();
        $("[id=output] [id=name]").shouldHave(text("Плюша"));
    }

    @Test
    void InvalidEmailFillFormTest() {

        open("/text-box");
        $("[id=userEmail]").setValue("rose");
        $("[id=submit]").click();
        $("[id=output]").shouldNotHave(text("rose"));
    }

}
