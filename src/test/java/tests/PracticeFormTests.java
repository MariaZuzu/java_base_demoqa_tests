package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests extends TestBase {

    @Test
    void successfulFillAllFormTest() {

        open("/automation-practice-form");
        $("[id=firstName]").setValue("Rose");
        $("[id=lastName]").setValue("White");
        $("[id=userEmail]").setValue("rose@white.com");
        $("[id=gender-radio-2]").click();
        $("[id=permanentAddress]").setValue("address 2");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Rose Pink"));
        $("[id=output] [id=email]").shouldHave(text("rose@pink.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("address 1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("address 2"));


    }

}
