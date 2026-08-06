package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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
    @DisplayName("Successful Fill All Form Broken")
    void successfulFillAllFormTest_broken() {

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

}