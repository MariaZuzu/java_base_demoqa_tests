package tests;

import org.junit.jupiter.api.Test;
import tests.testdata.TestData;

public class RegistrationTests extends TestBase {
    TestData data = new TestData();

    @Test
    void successfulFillAllFormTest_with_faker() {
        registrationPage.openPage()
                .typeFirstName(data.firstName)
                .typeLastName(data.lastName)
                .typeUserEmail(data.userEmail)
                .typeUserNumber(data.userNumber)
                .setGender(data.genderWrapper)
                .setDateOfBirth(data.day, data.month, data.year)
                .typeSubjectsInput(data.subjectsInput)
                .setHobbies(data.hobbiesWrapper)
                .uploadPicture(data.uploadPicture)
                .typeCurrentAddress(data.currentAddress)
                .setStateAndCity(data.state, data.city)
                .submitForm();

        registrationResultsComponent.checkModalTitleWindowOpen(data.messageAfterSubmitting)
                .checkFormResults("Student Name", data.firstName + " " + data.lastName)
                .checkFormResults("Student Email", data.userEmail)
                .checkFormResults("Gender", data.genderWrapper)
                .checkFormResults("Mobile", data.userNumber)
                .checkFormResults("Date of Birth",data.day + " " + data.month + "," + data.year)
                .checkFormResults("Subjects", data.subjectsInput)
                .checkFormResults("Hobbies", data.hobbiesWrapper)
                .checkFormResults("Picture", data.uploadPicture)
                .checkFormResults("Address", data.currentAddress)
                .checkFormResults("State and City", data.state + " " + data.city)
                .checkModalTitleWindowClosed();

    }
}
