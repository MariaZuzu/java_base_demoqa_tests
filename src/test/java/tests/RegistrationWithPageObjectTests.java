package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static tests.testdata.TestData.*;
import static io.qameta.allure.Allure.step;

@Story("Registration form")
public class RegistrationWithPageObjectTests extends TestBase {

    @Test
    @DisplayName("Successful Fill All Form")
    void successfulFillAllFormTest() {

        step("Открытие страницы регистрации", () ->
            registrationPage.openPage());

        step("Заполнение формы регистрации", () -> {
            registrationPage.typeFirstName(firstName)
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
    @DisplayName("Successful Fill All Form Broken")
    void successfulFillAllFormTest_broken() {

        step("Открытие страницы регистрации", () ->
                registrationPage.openPage());

        step("Заполнение формы регистрации", () -> {
            registrationPage.typeFirstName(firstName)
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
                    .checkFormResults("Student Name", userEmail)
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
    @DisplayName("Fill Required Fields Form")
    void fillRequiredFieldsFormTest() {

        step("Открытие страницы регистрации", () ->
                registrationPage.openPage());

        step("Заполнение обязательных полей формы", () -> {
            registrationPage.typeFirstName(firstName)
                    .typeLastName(lastName)
                    .setGender(genderWrapper)
                    .typeUserNumber(userNumber);
        });

        step("Отправка формы", () ->
                registrationPage.submitForm());

        step("Проверка результатов", () -> {
            registrationResultsComponent
                    .checkModalTitleWindowOpen(messageAfterSubmitting)
                    .checkFormResults("Student Name", userName)
                    .checkFormResults("Gender", genderWrapper)
                    .checkFormResults("Mobile", userNumber);
        });
    }

    @Test
    @DisplayName("Empty Fields Form")
    void emptyFieldsFormTest() {

        step("Открытие страницы регистрации", () ->
                registrationPage.openPage());

        step("Отправка формы", () ->
                registrationPage.submitForm());

        step("Проверка результатов", () ->
                $(".modal-content").shouldNotBe(visible));
    }

    @Test
    @DisplayName("Invalid User Number")
    void InvalidUserNumber() {

        step("Открытие страницы регистрации", () ->
                registrationPage.openPage());

        step("Заполнение формы регистрации", () -> {
            registrationPage.typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserNumber(invalidUserNumber)
                    .setGender(genderWrapper)
                    .submitForm();
        });

        step("Проверка результатов", () ->
                $(".modal-content").shouldNotBe(visible));
    }

    /* Проверка отправки формы с невалидным email */
    @Test
    @DisplayName("Invalid User Email")
    void InvalidUserEmail() {

        step("Открытие страницы регистрации", () ->
                registrationPage.openPage());

        step("Заполнение формы регистрации", () -> {
            registrationPage.typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserEmail(invalidUserEmail)
                    .typeUserNumber(userNumber)
                    .setGender(genderWrapper)
                    .submitForm();
        });

        step("Проверка результатов", () ->
                $(".modal-content").shouldNotBe(visible));
    }

}