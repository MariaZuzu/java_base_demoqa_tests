package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.testdata.TestData.*;


public class RegistrationTests extends TestBase {

    @Test
    void successfulFillAllFormTest() {
        registrationPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .typeUserNumber(userNumber)
                .setGender(genderWrapper)
                .setDateOfBirth(day, month, year)
                .typeSubjectsInput(subjectsInput)
                .setHobbies(hobbiesWrapper)
                .uploadPicture(uploadPicture)
                .typeCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitForm();

        registrationResultsComponent.checkModalTitleWindowOpen(messageAfterSubmitting)
                .checkFormResults("Student Name", userName)
                .checkFormResults("Student Email", userEmail)
                .checkFormResults("Gender", genderWrapper)
                .checkFormResults("Mobile", userNumber)
                .checkFormResults("Date of Birth", userBirthDay)
                .checkFormResults("Subjects", subjectsInput)
                .checkFormResults("Hobbies", hobbiesWrapper)
                .checkFormResults("Picture", uploadPicture)
                .checkFormResults("Address", currentAddress)
                .checkFormResults("State and City", state + " " + city)
                .checkModalTitleWindowClosed();

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
    void successfulFillAllFormTest_old() {

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

    }