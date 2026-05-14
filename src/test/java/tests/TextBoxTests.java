package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testdata.TestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.typeUserEmail(userEmail);
        textBoxPage.typeCurrentAddress(currentAddress);
        textBoxPage.typePermanentAddress(permanentAddress);
        textBoxPage.submitForm();

        textBoxPage.checkField("name", userName);
        textBoxPage.checkField("email", userEmail);
        textBoxPage.checkField("currentAddress", currentAddress);
        textBoxPage.checkField("permanentAddress", permanentAddress);
    }

    @Test
    void successfulFillFormTest_with_faker() {
        Faker faker = new Faker();
        Faker fakerRu = new Faker(new Locale("ru"));
        String userName = fakerRu.name().fullName();
        String userEmail = faker.internet().emailAddress();
        String currentAddress = fakerRu.address().fullAddress();
        String permanentAddress = fakerRu.address().fullAddress();

        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.typeUserEmail(userEmail);
        textBoxPage.typeCurrentAddress(currentAddress);
        textBoxPage.typePermanentAddress(permanentAddress);
        textBoxPage.submitForm();

        textBoxPage.checkField("name", userName);
        textBoxPage.checkField("email", userEmail);
        textBoxPage.checkField("currentAddress", currentAddress);
        textBoxPage.checkField("permanentAddress", permanentAddress);
    }

    @Test
    void successfulFillFormTest_chaining() {
        textBoxPage.openPage()
            .typeUserName(userName)
            .typeUserEmail(userEmail)
            .typeCurrentAddress(currentAddress)
            .typePermanentAddress(permanentAddress)
            .submitForm()
            .checkField("name",userName)
            .checkField("email",userEmail)
            .checkField("currentAddress",currentAddress)
            .checkField("permanentAddress",permanentAddress);
}

    @Test
    void MinimumFillFormTest() {

        textBoxPage.openPage();
        textBoxPage.typeUserName(userName);
        textBoxPage.submitForm();
        textBoxPage.checkField("name", userName);
    }

    @Test
    void InvalidEmailFillFormTest() {

        textBoxPage.openPage();
        $("[id=userEmail]").setValue("rose");
        textBoxPage.submitForm();
        $("[id=output]").shouldNotHave(text("rose"));
    }

//    Старый вариант тестов до применения pageObjects
//    @Test
//    void successfulFillFormTest() {
//
//        open("/text-box");
//        $("[id=userName]").setValue(userName);
//        $("[id=userEmail]").setValue(userEmail);
//        $("[id=currentAddress]").setValue(currentAddress);
//        $("[id=permanentAddress]").setValue(permanentAddress);
//        $("[id=submit]").click();
//
//        $("[id=output] [id=name]").shouldHave(text(userName));
//        $("[id=output] [id=email]").shouldHave(text(userEmail));
//        $("[id=output] [id=currentAddress]").shouldHave(text(currentAddress));
//        $("[id=output] [id=permanentAddress]").shouldHave(text(permanentAddress));
//    }
//
//    @Test
//    void MinimumFillFormTest() {
//
//        open("/text-box");
//        $("[id=userName]").setValue("Плюша");
//        $("[id=submit]").click();
//        $("[id=output] [id=name]").shouldHave(text("Плюша"));
//    }
//
//    @Test
//    void InvalidEmailFillFormTest() {
//
//        open("/text-box");
//        $("[id=userEmail]").setValue("rose");
//        $("[id=submit]").click();
//        $("[id=output]").shouldNotHave(text("rose"));
//    }

}
