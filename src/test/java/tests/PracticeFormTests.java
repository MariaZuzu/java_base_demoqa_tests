package tests;

import org.junit.jupiter.api.Test;


import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests extends TestBase {

    @Test
    void successfulFillAllFormTest() {

        open("/automation-practice-form");
        $("[id=firstName]").setValue("Rose");
        $("[id=lastName]").setValue("White");
        $("[id=userEmail]").setValue("rose@white.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("[id=userNumber]").setValue("1234567890");


        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__month").$(byText("15")).click();


        $("[id=subjectsInput]").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();


        $("#uploadPicture").uploadFromClasspath("test1.jpeg");
        $("#uploadPicture").shouldHave(value("test1.jpeg"));

        $("[id=currentAddress]").setValue("г. Ярославль, ул. Чайковского, д. 3");

        $("[id=state]").click();
        $(byText("Haryana")).click();

        $("[id=city]").click();
        $(byText("Karnal")).click();

        $("[id=submit]").click();


        $(".modal-content").shouldBe(com.codeborne.selenide.Condition.visible);

        // Проверка конкретных полей в таблице результатов
        $(".modal-body table").shouldHave(text("Rose White"));
        $(".modal-body table").shouldHave(text("rose@white.com"));
        $(".modal-body table").shouldHave(text("Female"));
        $(".modal-body table").shouldHave(text("1234567890"));
        $(".modal-body table").shouldHave(text("5 June,2005"));
        $(".modal-body table").shouldHave(text("Maths"));
        $(".modal-body table").shouldHave(text("Reading"));
        $(".modal-body table").shouldHave(text("test1.jpeg"));
        $(".modal-body table").shouldHave(text("г. Ярославль, ул. Чайковского, д. 3"));
        $(".modal-body table").shouldHave(text("Haryana Karnal"));

        $("#closeLargeModal").click();

    }

    @Test
    void fillRequiredFieldsFormTest() {

        open("/automation-practice-form");

        $("[id=firstName]").shouldHave(attribute("required"));
        $("[id=lastName]").shouldHave(attribute("required"));
        $("input[name='gender']").shouldHave(attribute("required"));
        $("[id=userNumber]").shouldHave(attribute("required"));

        $("[id=firstName]").setValue("Rose");
        $("[id=lastName]").setValue("White");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=submit]").click();

        $(".modal-body table").shouldHave(
                text("Rose White"),
                text("Female"),
                text("1234567890")
        );

        $("#closeLargeModal").click();
    }


    /* Проверка отправки формы с незаполненными полями */
    @Test
    void emptyFieldsFormTest() {

        open("/automation-practice-form");
        $("[id=firstName]").shouldHave(attribute("required"));
        $("[id=lastName]").shouldHave(attribute("required"));
        $("input[name='gender']").shouldHave(attribute("required"));
        $("[id=userNumber]").shouldHave(attribute("required"));

        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
    }


    /* Проверка отправки формы с невалидным номером телефона */
    @Test
    void InvalidUserNumber() {

        open("/automation-practice-form");
        $("[id=firstName]").setValue("Rose");
        $("[id=lastName]").setValue("White");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("123");

        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
    }

    /* Проверка отправки формы с невалидным email */
    @Test
    void InvalidUserEmail() {

        open("/automation-practice-form");
        $("[id=firstName]").setValue("Rose");
        $("[id=lastName]").setValue("White");
        $("[id=userEmail]").setValue("rosewhite.com");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);

    }
}
