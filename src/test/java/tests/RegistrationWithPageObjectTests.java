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
    }

    @Test
    @DisplayName("Negative Email Test")
    void negativeEmailTest() {
            registrationPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(invalidUserEmail)
                .submitForm()
                .checkBorderColorUserEmail();
    }

    @Test
    @DisplayName("Fill Required Fields Form")
    void fillRequiredFieldsFormTest() {
                registrationPage.openPage()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .setGender(genderWrapper)
                    .typeUserNumber(userNumber)
                    .submitForm();

            registrationResultsComponent
                    .checkModalTitleWindowOpen(messageAfterSubmitting)
                    .checkFormResults("Student Name", userName)
                    .checkFormResults("Gender", genderWrapper)
                    .checkFormResults("Mobile", userNumber);
    }

    @Test
    @DisplayName("Empty Fields Form")
    void emptyFieldsFormTest() {
                registrationPage.openPage()
                    .submitForm();

                $(".modal-content").shouldNotBe(visible);
    }

    @Test
    @DisplayName("Invalid User Number")
    void InvalidUserNumber() {
                registrationPage.openPage()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserNumber(invalidUserNumber)
                    .setGender(genderWrapper)
                    .submitForm();

                $(".modal-content").shouldNotBe(visible);
    }

    @Test
    @DisplayName("Invalid User Email")
    void InvalidUserEmail() {
                registrationPage.openPage()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserEmail(invalidUserEmail)
                    .typeUserNumber(userNumber)
                    .setGender(genderWrapper)
                    .submitForm();

                $(".modal-content").shouldNotBe(visible);
    }
}