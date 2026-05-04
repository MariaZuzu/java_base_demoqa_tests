package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {

    String userName;
    String userEmail;
    String currentAddress;
    String permanentAddress;

    @BeforeEach
    public void setup() {
        userName = "Rose Pink";
        userEmail = "rose@pink.com";
        currentAddress = "address 1";
        permanentAddress = "address 2";
    }

    @Test
    void successfulFillFormTest() {

        open("/text-box");
        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id=permanentAddress]").setValue(permanentAddress);
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Rose Pink"));
        $("[id=output] [id=email]").shouldHave(text("rose@pink.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("address 1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("address 2"));
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
