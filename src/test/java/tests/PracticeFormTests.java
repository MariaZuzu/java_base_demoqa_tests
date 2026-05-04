package tests;

import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testdata.TestData.*;


public class PracticeFormTests extends TestBase {

    @Test
    void successfulFillAllFormTest() {

        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(userEmail);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("[id=userNumber]").setValue(userNumber);


        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month").$(byText(day)).click();


        $("[id=subjectsInput]").setValue(subjectsInput).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbiesWrapper)).click();


        $("#uploadPicture").uploadFromClasspath(uploadPicture);
        $("#uploadPicture").shouldHave(value(uploadPicture));

        $("[id=currentAddress]").setValue(currentAddress);

        $("[id=state]").click();
        $(byText(state)).click();

        $("[id=city]").click();
        $(byText(city)).click();

        $("[id=submit]").click();


        $(".modal-content").shouldBe(com.codeborne.selenide.Condition.visible);

        // Проверка конкретных полей в таблице результатов
        $(".modal-body table").shouldHave(text(userName));
        $(".modal-body table").shouldHave(text(userEmail));
        $(".modal-body table").shouldHave(text(genderWrapper));
        $(".modal-body table").shouldHave(text(userNumber));
        $(".modal-body table").shouldHave(text(userBirthDay));
        $(".modal-body table").shouldHave(text(subjectsInput));
        $(".modal-body table").shouldHave(text(hobbiesWrapper));
        $(".modal-body table").shouldHave(text(uploadPicture));
        $(".modal-body table").shouldHave(text(currentAddress));
        $(".modal-body table").shouldHave(text(stateAndCity));

        $("#closeLargeModal").click();

    }

    @Test
    void fillRequiredFieldsFormTest() {

        open("/automation-practice-form");

        $("[id=firstName]").shouldHave(attribute("required"));
        $("[id=lastName]").shouldHave(attribute("required"));
        $("input[name='gender']").shouldHave(attribute("required"));
        $("[id=userNumber]").shouldHave(attribute("required"));

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("[id=userNumber]").setValue(userNumber);

        $("[id=submit]").click();

        $(".modal-body table").shouldHave(
                text(userName),
                text(genderWrapper),
                text(userNumber)
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
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("[id=userNumber]").setValue(userNumber);

        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
    }

    /* Проверка отправки формы с невалидным email */
    @Test
    void InvalidUserEmail() {

        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(userEmail);
        $("#genterWrapper").$(byText(genderWrapper)).click();
        $("[id=userNumber]").setValue(userNumber);

        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);

    }
}
