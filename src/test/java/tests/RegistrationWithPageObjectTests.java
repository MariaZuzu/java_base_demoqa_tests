package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static tests.testdata.TestData.*;

@Story("Registration form")
public class RegistrationWithPageObjectTests extends TestBase {

    @Test
    @DisplayName("Successful Fill All Form")
    void successfulFillAllFormTest() {

        step("Открытие страницы регистрации", () ->
            registrationPage.openPage());

        step("Заполнение формы регистрации", () -> {
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
        });

        step("Проверка результатов заполнения формы регистрации", () -> {
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
        });
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