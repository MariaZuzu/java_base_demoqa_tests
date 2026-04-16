package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "safari";
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = "10000";  // default 4000
    }

    @Test
    void successfulFillFormTest() {

        open("/text-box");
        $("[id=userName]").setValue("Rose Pink");
        $("[id=userEmail]").setValue("rose@pink.com");
        $("[id=currentAddress]").setValue("address 1");
        $("[id=permanentAddress]").setValue("address 2");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Rose Pink"));
        $("[id=output] [id=email]").shouldHave(text("rose@pink.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("address 1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("address 2"));


    }


}
